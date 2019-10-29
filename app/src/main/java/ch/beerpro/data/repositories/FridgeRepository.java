package ch.beerpro.data.repositories;

import android.util.Pair;

import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Entity;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.domain.utils.FirestoreQueryLiveDataArray;

import static androidx.lifecycle.Transformations.map;
import static androidx.lifecycle.Transformations.switchMap;
import static ch.beerpro.domain.utils.LiveDataExtensions.combineLatest;

public class FridgeRepository {
    private final FirestoreQueryLiveDataArray<FridgeItem> fridgeItems = new FirestoreQueryLiveDataArray<>(
            FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                    .orderBy(FridgeItem.FIELD_CREATION_DATE, Query.Direction.DESCENDING), FridgeItem.class);

    public static LiveData<List<FridgeItem>> getFridgeItemsByUser(String userId) {
        return new FirestoreQueryLiveDataArray<>(FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .orderBy(FridgeItem.FIELD_CREATION_DATE, Query.Direction.DESCENDING)
                .whereEqualTo(FridgeItem.FIELD_USER_ID, userId), FridgeItem.class);
    }

    public static LiveData<List<FridgeItem>> getFridgeItemsByBeer(String beerId) {
        return new FirestoreQueryLiveDataArray<>(FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .orderBy(FridgeItem.FIELD_CREATION_DATE, Query.Direction.DESCENDING)
                .whereEqualTo(FridgeItem.FIELD_BEER_ID, beerId), FridgeItem.class);
    }

    public LiveData<List<Pair<FridgeItem, Beer>>> getMyFridgeWithBeer(LiveData<String> currentUserId, LiveData<List<Beer>> allBeers) {
        return map(combineLatest(getMyFridgeItems(currentUserId), map(allBeers, Entity::entitiesById)), input -> {
            List<FridgeItem> fridgeItems = input.first;
            HashMap<String, Beer> beersById = input.second;

            ArrayList<Pair<FridgeItem, Beer>> result = new ArrayList<>();
            for (FridgeItem fridgeItem : fridgeItems) {
                Beer beer = beersById.get(fridgeItem.getBeerId());
                result.add(Pair.create(fridgeItem, beer));
            }
            return result;
        });
    }

    public LiveData<List<FridgeItem>> getAllItems() {
        return fridgeItems;
    }

    public LiveData<List<FridgeItem>> getMyFridgeItems(LiveData<String> currentUserId) {
        return switchMap(currentUserId, FridgeRepository::getFridgeItemsByUser);
    }

    public void removeFridgeItem(String userId, String itemId, Integer amount) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fridgeItemId = FridgeItem.generateId(userId, itemId);
        DocumentReference fridgeItemQuery = db.collection(FridgeItem.COLLECTION).document(fridgeItemId);


    }
}