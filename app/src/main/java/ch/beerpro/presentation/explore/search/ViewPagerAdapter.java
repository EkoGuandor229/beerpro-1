package ch.beerpro.presentation.explore.search;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ch.beerpro.presentation.explore.search.beers.SearchResultFragment;
import ch.beerpro.presentation.explore.search.suggestions.SearchSuggestionsFragment;
import ch.beerpro.presentation.explore.search.SearchFilterFragment;
import ch.beerpro.presentation.profile.mybeers.MyBeersFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "ViewPagerAdapter";

    private Fragment searchSuggestionsFragment;
    private Fragment searchResultFragment;
    private Fragment myBeersFragment;
    private Fragment filterFragment;

    private boolean showSuggestions = true;
    private boolean hasChanged = false;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        searchSuggestionsFragment = new SearchSuggestionsFragment();
        searchResultFragment = new SearchResultFragment();
        myBeersFragment = new MyBeersFragment();
        filterFragment = new SearchFilterFragment();
    }

    public void setShowSuggestions(boolean showSuggestions) {
        this.showSuggestions = showSuggestions;
        this.hasChanged = true;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (showSuggestions) {
                    return searchSuggestionsFragment;
                } else {
                    return searchResultFragment;
                }
            case 1:
                return myBeersFragment;
            case 2:
                return filterFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        // https://stackoverflow.com/questions/7746652/fragment-in-viewpager-using-fragmentpageradapter-is-blank-the-second-time-it-is
        if (object instanceof MyBeersFragment) {
            return POSITION_UNCHANGED;
        } else if (hasChanged) {
            this.hasChanged = false;
            return POSITION_NONE;
        } else {
            return POSITION_UNCHANGED;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ALLE BIERE";
            case 1:
                return "MEINE BIERE";
            case 2:
                return "Filter";
        }
        return null;
    }

}