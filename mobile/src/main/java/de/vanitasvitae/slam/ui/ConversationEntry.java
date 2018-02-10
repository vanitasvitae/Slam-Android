package de.vanitasvitae.slam.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.vanitasvitae.slam.R;

/**
 * Created by Paul Schaub on 30.01.18.
 */
public class ConversationEntry extends RecyclerView.ViewHolder {

    private View view;

    public ConversationEntry(View itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void bind(String username, String message, String date, boolean read) {
        View v = view;
        ((TextView)v.findViewById(R.id.contact_name)).setText(username);
        ((TextView)v.findViewById(R.id.contact_textcontent)).setText(message);
        ((TextView)v.findViewById(R.id.contact_date)).setText(date);
        v.findViewById(R.id.send_indicator).setVisibility(read ? View.VISIBLE : View.GONE);
    }
}
