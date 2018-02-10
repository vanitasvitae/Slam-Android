package de.vanitasvitae.slam;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Abstract TextWatcher, that has method stubs for all methods..
 */
public abstract class AbstractTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Do nothing
    }
}
