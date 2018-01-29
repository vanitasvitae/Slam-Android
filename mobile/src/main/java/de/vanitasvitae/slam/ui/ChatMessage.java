package de.vanitasvitae.slam.ui;

import android.content.Context;
import android.view.ViewGroup;

import de.vanitasvitae.slam.R;

/**
 * Created by Paul Schaub on 29.01.18.
 */
public class ChatMessage extends ViewGroup {

    public ChatMessage(Context context) {
        super(context);
        inflate(context, R.layout.item_chatmessage, this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
