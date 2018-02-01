package de.vanitasvitae.slam.activity.abstr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.Arrays;

import de.vanitasvitae.slam.R;

/**
 * AppCompatActivity that can easily be themed.
 */
public abstract class ThemedAppCompatActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String PREF_APP_THEME = "pref_app_theme";
    private static final String THEME_LIGHT = "defaultTheme";
    private static final String THEME_DARK = "darkTheme";

    private SharedPreferences preferences;

    @SuppressLint("ApplySharedPref")
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String theme = preferences.getString(PREF_APP_THEME, THEME_LIGHT);
        switch (theme) {
            case THEME_DARK:
                setTheme(R.style.Slam_Dark);
                break;
            // add more themes here...

            default:
                setTheme(R.style.Slam);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        preferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    protected void onResume() {
        preferences.registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.debug_switch_theme:
                toggleTheme();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toggleTheme() {
        // ...and here
        String[] themes = new String[]{THEME_LIGHT, THEME_DARK};
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String current = pref.getString(PREF_APP_THEME, THEME_LIGHT);
        int index = Arrays.asList(themes).indexOf(current);
        String next = themes[(index + 1) % themes.length];
        pref.edit().putString(PREF_APP_THEME, next).apply();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PREF_APP_THEME)) {
            finish();
            final Intent intent = getIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
