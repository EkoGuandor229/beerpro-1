package ch.beerpro.data.repositories;

import android.util.Pair;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Entity;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.domain.utils.FirestoreQueryLiveData;
import ch.beerpro.domain.utils.FirestoreQueryLiveDataArray;

import static androidx.lifecycle.Transformations.map;
import static androidx.lifecycle.Transformations.switchMap;
import static ch.beerpro.domain.utils.LiveDataExtensions.combineLatest;

public class FridgeRepository {
    private static LiveData<List<FridgeItem>> getFridgeBeersByUser(String userId) {
        return new FirestoreQueryLiveDataArray<>(FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .orderBy(FridgeItem.FIELD_AMOUNT, Query.Direction.DESCENDING).whereEqualTo(FridgeItem.FIELD_USER_ID, userId),
                FridgeItem.class);
    }

    private static LiveData<FridgeItem> getUserFridgeListFor(Pair<String, Beer> input) {
        String userId = input.first;
        Beer beer = input.second;
        DocumentReference document = FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .document(FridgeItem.generateId(userId, beer.getId()));
        return new FirestoreQueryLiveData<>(document, FridgeItem.class);
    }

    public Task<Void> addUserFridgeItem(String userId, String itemId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fridgeBeerId = FridgeItem.generateId(userId, itemId);
        DocumentReference fridgeEntryQuery = db.collection(FridgeItem.COLLECTION).document(fridgeBeerId);
        return fridgeEntryQuery.get().continueWithTask(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                return fridgeEntryQuery.update("amount", task.getResult().getLong(FridgeItem.FIELD_AMOUNT) + 1);
            } else if (task.isSuccessful()) {
                return fridgeEntryQuery.set(new FridgeItem(fridgeBeerId, userId, itemId, 1, new Date()));
            } else {
                throw task.getException();
            }
        });
    }

    public Task<Void> substractUserFridgeBeer(String userId, String itemId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fridgeBeerId = FridgeItem.generateId(userId, itemId);
        DocumentReference fridgeEntryQuery = db.collection(FridgeItem.COLLECTION).document(fridgeBeerId);
        return fridgeEntryQuery.get().continueWithTask(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                return fridgeEntryQuery.update("amount", task.getResult().getLong(FridgeItem.FIELD_AMOUNT) - 1);
            } else {
                throw task.getException();
            }
        });
    }

    public Task<Void> changeAmountInFridge(String userId, String itemId, int newAmount) {
        if (newAmount == 0) deleteBeerFromFridge(userId, itemId);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fridgeBeerId = FridgeItem.generateId(userId, itemId);
        DocumentReference fridgeEntryQuery = db.collection(FridgeItem.COLLECTION).document(fridgeBeerId);
        return fridgeEntryQuery.get().continueWithTask(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                return fridgeEntryQuery.update("amount", newAmount);
            } else {
                throw task.getException();
            }
        });
    }

    public Task<Void> deleteBeerFromFridge(String userId, String itemId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fridgeBeerId = FridgeItem.generateId(userId, itemId);
        DocumentReference fridgeEntryQuery = db.collection(FridgeItem.COLLECTION).document(fridgeBeerId);
        return fridgeEntryQuery.get().continueWithTask(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                return fridgeEntryQuery.delete();
            } else {
                throw task.getException();
            }
        });
    }

    public LiveData<List<Pair<FridgeItem, Beer>>> getMyFridgeWithBeers(LiveData<String> currentUserId,
                                                                       LiveData<List<Beer>> allBeers) {
        return map(combineLatest(getMyFridge(currentUserId), map(allBeers, Entity::entitiesById)), input -> {
            List<FridgeItem> fridgeBeers = input.first;
            HashMap<String, Beer> beersById = input.second;
            ArrayList<Pair<FridgeItem, Beer>> result = new ArrayList<>();
            for (FridgeItem fridgeBeer : fridgeBeers) {
                Beer beer = beersById.get(fridgeBeer.getBeerId());
                result.add(Pair.create(fridgeBeer, beer));
            }
            return result;
        });
    }

    public LiveData<List<FridgeItem>> getMyFridge(LiveData<String> currentUserId) {
        return switchMap(currentUserId, FridgeRepository::getFridgeBeersByUser);
    }

    public LiveData<FridgeItem> getMyFridgeForBeer(LiveData<String> currentUserId, LiveData<Beer> beer) {
        return switchMap(combineLatest(currentUserId, beer), FridgeRepository::getUserFridgeListFor);
    }
}
