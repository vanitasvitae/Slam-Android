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
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.view.abstr.ThemedAppCompatActivity;

/**
 * Main activity that hosts some fragments.
 */
public class MainActivity extends ThemedAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "Slam!";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.login__error_incorrect_password, R.string.login__error_invalid_jid);
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment chatListFragment = new ConversationListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, chatListFragment)
                .addToBackStack("conversation_list")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.navdrawer__item_conversations:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ConversationListFragment())
                        .commit();
                return true;

            case R.id.navdrawer__item_contacts:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ContactListFragment())
                        .commit();
                return true;
            case R.id.navdrawer__item_bookmarks:
            case R.id.navdrawer__item_blogging:
            case R.id.navdrawer__item_settings:
                Toast.makeText(this, R.string.feature_not_implemented, Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
