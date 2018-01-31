package de.vanitasvitae.slam.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.vanitasvitae.slam.R;

/**
 * Created by Paul Schaub on 30.01.18.
 */
public class ChatMessageEntry extends RecyclerView.ViewHolder {

    private View view;

    public ChatMessageEntry(View itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void bind(String sender, String role, View content, String date) {
        ((TextView)view.findViewById(R.id.message_sender)).setText(sender);
        ((TextView)view.findViewById(R.id.message_sender_role)).setText(role);
        ((RelativeLayout)view.findViewById(R.id.message_content)).addView(content);
        ((TextView)view.findViewById(R.id.message_date)).setText(date);
    }
}
