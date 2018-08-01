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

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.mvp.contracts.ContactDetailContract;
import de.vanitasvitae.slam.mvp.view.abstr.ThemedAppCompatActivity;
import de.vanitasvitae.slam.ui.NonPagingViewPager;
import de.vanitasvitae.slam.xmpp.Resource;

/**
 * Main activity that hosts some fragments.
 */
public class ContactDetailActivity extends ThemedAppCompatActivity
        implements ContactDetailContract.View, AppBarLayout.OnOffsetChangedListener {

    public static final String TAG = "Slam!";

    private ContactDetailContract.Presenter presenter;

    private final ContactDetailResourcesFragment resourcesFragment = new ContactDetailResourcesFragment();
    private final ContactDetailInfoFragment infoFragment = new ContactDetailInfoFragment();
    private final ContactDetailSecurityFragment securityFragment = new ContactDetailSecurityFragment();

    @BindView(R.id.contact_detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.contact_detail_appbar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.contact_detail_tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.contact_detail_viewpager)
    NonPagingViewPager pager;

    @BindView(R.id.contact_detail_profile_circle)
    CircleImageView profileCircle;

    private int mMaxScrollSize;
    private boolean isProfileCircleShown;
    private int animateProfileCirclePercent = 30;

    public ContactDetailActivity() {
        setPresenter(PresenterFactory.getInstance().createContactDetailPresenter(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        appBarLayout.addOnOffsetChangedListener(this);
        pager.setAdapter(new DetailFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= animateProfileCirclePercent && isProfileCircleShown) {
            isProfileCircleShown = false;

            profileCircle.animate()
                    .scaleY(0).scaleX(0)
                    .alpha(0)
                    .setDuration(300)
                    .start();
        }

        if (percentage <= animateProfileCirclePercent && !isProfileCircleShown) {
            isProfileCircleShown = true;

            profileCircle.animate()
                    .scaleY(1).scaleX(1)
                    .alpha(1)
                    .start();
        }
    }


    @Override
    public void setContactAvatar() {

    }

    @Override
    public void setNickname(String nickname) {

    }

    @Override
    public void setResources(List<Resource> resources) {
        resourcesFragment.setResources(resources);
    }

    @Override
    public void clearFingerprints() {

    }

    @Override
    public void addFingerprints(List<?> fingerprints) {

    }

    @Override
    public void setPresenter(ContactDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    class DetailFragmentPagerAdapter extends FragmentPagerAdapter {

        public DetailFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0: return infoFragment;
                case 1: return resourcesFragment;
                case 2: return securityFragment;
                default: return infoFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //TODO
            switch (position) {
                case 0:
                    return "Info";
                case 1:
                    return "Devices";
                case 2:
                    return "Security";
            }
            return null;
        }
    }
}
