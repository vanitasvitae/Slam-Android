package de.vanitasvitae.slam.mvp.contracts;

import java.util.List;

/**
 * Model-View-Presenter contract for the {@link de.vanitasvitae.slam.mvp.view.SearchFragment}.
 * Created by Paul Schaub on 01.02.18.
 */
public interface SearchContract {

    interface View {
        void addSearchResults(List<?> results);
        void clearSearchResults();
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showEmptySearchResults();
        void hideEmptySearchResults();
    }

    interface Presenter {
        void onSearchQueryChanged(String query);
        void onSearchResultClick();
        void onSearchScrolledToBottom();
    }
}
