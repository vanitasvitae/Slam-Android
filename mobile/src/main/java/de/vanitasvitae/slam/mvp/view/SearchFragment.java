package de.vanitasvitae.slam.mvp.view;

import android.app.Fragment;

import java.util.List;

import de.vanitasvitae.slam.mvp.contracts.SearchContract;

/**
 * Fragment that shows search results from contacts and messages (and maybe services?).
 *
 * Created by Paul Schaub on 01.02.18.
 */
public class SearchFragment extends Fragment implements SearchContract.View {

    @Override
    public void addSearchResults(List<?> results) {

    }

    @Override
    public void clearSearchResults() {

    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showEmptySearchResults() {

    }

    @Override
    public void hideEmptySearchResults() {

    }
}
