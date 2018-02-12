/*
 * Copyright 2018 Paul Schaub
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package de.vanitasvitae.slam.mvp.view;

import android.app.Fragment;

import java.util.List;

import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.mvp.contracts.SearchContract;

/**
 * Fragment that shows search results from contacts and messages (and maybe services?).
 *
 * Created by Paul Schaub on 01.02.18.
 */
public class SearchFragment extends Fragment implements SearchContract.View {

    private final SearchContract.Presenter presenter;

    public SearchFragment() {
        this.presenter = PresenterFactory.getInstance().createSearchPresenter(this);
    }

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
