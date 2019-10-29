package ch.beerpro.presentation.profile.myfridge;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;

import java.util.List;

import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.FridgeRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;

public class MyFridgeViewModel extends ViewModel implements CurrentUser {

    private static final String TAG = "MyFridgeViewModel";

    private final MutableLiveData<String> currentUserId = new MutableLiveData<>();
    private final FridgeRepository myFridgeRepository;
    private final BeersRepository beersRepository;

    public MyFridgeViewModel() {
        myFridgeRepository = new FridgeRepository();
        beersRepository = new BeersRepository();

        currentUserId.setValue(getCurrentUser().getUid());
    }

    public LiveData<List<Pair<FridgeItem, Beer>>> getMyFridgeWithBeers() {
        return myFridgeRepository.getMyFridgeWithBeers(currentUserId, beersRepository.getAllBeers());
    }

    public Task<Void> toggleItemInFridgeList(String itemId) {
        return myFridgeRepository.deleteBeerFromFridge(getCurrentUser().getUid(), itemId);
    }

    public Task<Void> changeAmountOfFridgeBeer(String itemId, int amount) {
        return myFridgeRepository.changeAmountInFridge(getCurrentUser().getUid(), itemId, amount);
    }

    public Task<Void> addAmountToFridgeBeer (String itemId) {
        return myFridgeRepository.addUserFridgeItem(getCurrentUser().getUid(), itemId);
    }

    public Task<Void> removeAmountToFridgeBeer (String itemId) {
        return myFridgeRepository.substractUserFridgeBeer(getCurrentUser().getUid(), itemId);
    }
}
