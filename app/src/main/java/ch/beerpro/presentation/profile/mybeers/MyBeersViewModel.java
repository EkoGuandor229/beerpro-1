package ch.beerpro.presentation.profile.mybeers;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.MyBeersRepository;
import ch.beerpro.data.repositories.RatingsRepository;
import ch.beerpro.data.repositories.WishlistRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.MyBeer;
import ch.beerpro.domain.models.Rating;
import ch.beerpro.domain.models.Wish;

import static androidx.lifecycle.Transformations.map;
import static ch.beerpro.domain.utils.LiveDataExtensions.zip;

public class MyBeersViewModel extends ViewModel implements CurrentUser {

    private static final String TAG = "MyBeersViewModel";
    private final MutableLiveData<String> searchTerm = new MutableLiveData<>();

    private final WishlistRepository wishlistRepository;
    private final LiveData<List<MyBeer>> myFilteredBeers;

    public MyBeersViewModel() {

        wishlistRepository = new WishlistRepository();
        BeersRepository beersRepository = new BeersRepository();
        MyBeersRepository myBeersRepository = new MyBeersRepository();
        RatingsRepository ratingsRepository = new RatingsRepository();

        LiveData<List<Beer>> allBeers = beersRepository.getAllBeers();
        MutableLiveData<String> currentUserId = new MutableLiveData<>();
        LiveData<List<Wish>> myWishlist = wishlistRepository.getMyWishlist(currentUserId);
        LiveData<List<Rating>> myRatings = ratingsRepository.getMyRatings(currentUserId);

        LiveData<List<MyBeer>> myBeers = myBeersRepository.getMyBeers(allBeers, myWishlist, myRatings);

        myFilteredBeers = map(zip(searchTerm, myBeers), MyBeersViewModel::filter);

        currentUserId.setValue(getCurrentUser().getUid());
    }

    private static List<MyBeer> filter(Pair<String, List<MyBeer>> input) {
        String searchTerm1 = input.first;
        List<MyBeer> myBeers = input.second;
        if (Strings.isNullOrEmpty(searchTerm1)) {
            return myBeers;
        }
        if (myBeers == null) {
            return Collections.emptyList();
        }
        ArrayList<MyBeer> filtered = new ArrayList<>();
        String[] searchedValues = new String[3];
        getSearchedStrings(searchTerm1, searchedValues);

        if (searchedValues[1].equals("null") && searchedValues[2].equals("null")) {
            for (MyBeer beer : myBeers) {
                if (beer.getBeer().getName().toLowerCase().contains(searchedValues[0].toLowerCase())) {
                    filtered.add(beer);
                }
            }
        } else if (searchedValues[2].equals("null") && searchedValues[0].equals("null")) {
            for (MyBeer beer : myBeers) {
                if (beer.getBeer().getCategory().toLowerCase().contains(searchedValues[1].toLowerCase())) {
                    filtered.add(beer);
                }
            }
        } else if (searchedValues[0].equals("null") && searchedValues[1].equals("null")) {
            for (MyBeer beer : myBeers) {
                if (beer.getBeer().getManufacturer().toLowerCase().contains(searchedValues[2].toLowerCase())) {
                    filtered.add(beer);
                }
            }
        } else if (searchedValues[1].equals("null")) {
            for (MyBeer beer : myBeers) {
                if (beer.getBeer().getManufacturer().toLowerCase().contains(searchedValues[2].toLowerCase()) &&
                        beer.getBeer().getName().toLowerCase().contains(searchedValues[0].toLowerCase())) {
                    filtered.add(beer);
                }
            }
        } else if (searchedValues[2].equals("null")) {
            for (MyBeer beer : myBeers) {
                if (beer.getBeer().getCategory().toLowerCase().contains(searchedValues[1].toLowerCase()) &&
                        beer.getBeer().getName().toLowerCase().contains(searchedValues[0].toLowerCase())) {
                    filtered.add(beer);
                }
            }
        }
        return filtered;
    }

    public LiveData<List<MyBeer>> getMyFilteredBeers() {
        return myFilteredBeers;
    }

    public void toggleItemInWishlist(String beerId) {
        wishlistRepository.toggleUserWishlistItem(getCurrentUser().getUid(), beerId);
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm.setValue(searchTerm);
    }

    private static void getSearchedStrings(String s, String[] values) {

        String[] search = s.split(",");
        values[0] = search[0];
        values[1] = search[1];
        values[2] = search[2];
        /* Matcher dont work ...
        Pattern r_name = Pattern.compile("Name: (\\w+)");
        Pattern r_manufacturer = Pattern.compile("Manufacturer: (\\w+)");
        Pattern r_category = Pattern.compile("Category: (\\w+)");
        Matcher m1 = r_name.matcher(search[0]);
        Matcher m2 = r_manufacturer.matcher(search[1]);
        Matcher m3 = r_category.matcher(search[2]);
        if (m1.find()) {
            System.out.println("Found value Beer: " + m1.group(1)); */
    }
}
