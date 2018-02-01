package de.vanitasvitae.slam;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

/**
 * Abstract listener, that listens on a TextView for ActionDone events.
 */
public abstract class EditorActionDoneListener implements TextView.OnEditorActionListener {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onEditorActionDone(v);
            return true;
        }
        return false;
    }

    public abstract void onEditorActionDone(TextView v);
}
