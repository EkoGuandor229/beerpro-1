package ch.beerpro.presentation.explore.search;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.common.base.Strings;

import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.presentation.details.DetailsActivity;
import ch.beerpro.presentation.explore.BeerCategoriesFragment;
import ch.beerpro.presentation.explore.BeerManufacturersFragment;
import ch.beerpro.presentation.explore.search.beers.SearchResultFragment;
import ch.beerpro.presentation.explore.search.suggestions.SearchSuggestionsFragment;
import ch.beerpro.presentation.profile.mybeers.MyBeersViewModel;
import ch.beerpro.presentation.profile.mybeers.OnMyBeerItemInteractionListener;

public class SearchActivity extends AppCompatActivity
        implements SearchResultFragment.OnItemSelectedListener, SearchSuggestionsFragment.OnItemSelectedListener,
        OnMyBeerItemInteractionListener, BeerCategoriesFragment.OnItemSelectedListener, BeerManufacturersFragment.OnItemSelectedListener{

    private SearchViewModel searchViewModel;
    private ViewPagerAdapter adapter;
    private EditText searchEditText;
    private MyBeersViewModel myBeersViewModel;
    private TabLayout tabLayout;
    private String category;
    private String manufacturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = findViewById(R.id.searchEditText);
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String text = searchEditText.getText().toString();
                handleSearch(text);
                addSearchTermToUserHistory(text);
            }
            return false;
        });

        findViewById(R.id.clearFilterButton).setOnClickListener(view -> {
            searchEditText.setText(null);
            category = null;
            manufacturer = null;
            searchEditText.setHint("Bier suchen");
            handleSearch(null);
        });

        ViewPager viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setSaveFromParentEnabled(false);
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        myBeersViewModel = ViewModelProviders.of(this).get(MyBeersViewModel.class);

        Bundle getextra = getIntent().getExtras();
        if (getextra != null) {
            if (getextra.getString("Category") != null) {
                category = getextra.getString("Category");
                searchEditText.setHint("Filter: " + category);
            }
            if (getextra.getString("Manufacturer") != null) {
                manufacturer = getextra.getString("Manufacturer");
                searchEditText.setHint("Filter: " + manufacturer);

            }
            handleSearch(null);
        }
    }

    private void handleSearch(String text) {
        String search_text = "" + text + "," + category + "," + manufacturer;
        searchViewModel.setSearchTerm(search_text);
        myBeersViewModel.setSearchTerm(search_text);
        adapter.setShowSuggestions(Strings.isNullOrEmpty(text) && Strings.isNullOrEmpty(category) && Strings.isNullOrEmpty(manufacturer));
        adapter.notifyDataSetChanged();
    }

    private void addSearchTermToUserHistory(String text) {
        searchViewModel.addToSearchHistory(text);
    }

    @Override
    public void onSearchResultListItemSelected(View animationSource, Beer item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.ITEM_ID, item.getId());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, animationSource, "image");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onSearchSuggestionListItemSelected(String text) {
        searchEditText.setText(text);
        searchEditText.setSelection(text.length());
        hideKeyboard();
        handleSearch(text);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        }
    }

    @Override
    public void onMoreClickedListener(ImageView photo, Beer item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.ITEM_ID, item.getId());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, photo, "image");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onWishClickedListener(Beer item) {
        searchViewModel.toggleItemInWishlist(item.getId());
    }

    @Override
    public void onBeerCategorySelected(String name) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("Category",name);
        startActivity(intent);
    }

    @Override
    public void onBeerManufacturerSelected(String name) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("Manufacturer",name);
        startActivity(intent);

    }
}
