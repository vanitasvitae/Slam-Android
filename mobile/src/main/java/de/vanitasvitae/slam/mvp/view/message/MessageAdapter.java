package de.vanitasvitae.slam.mvp.view.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.xmpp.message.AbstractMessage;
import de.vanitasvitae.slam.xmpp.message.AudioMessage;
import de.vanitasvitae.slam.xmpp.message.ImageMessage;
import de.vanitasvitae.slam.xmpp.message.TextMessage;
import de.vanitasvitae.slam.xmpp.message.VideoMessage;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public abstract class MessageAdapter extends RecyclerView.Adapter<MessageView> {

    public static final int
            TYPE_TEXT   = 0,
            TYPE_IMAGE  = 1,
            TYPE_VIDEO  = 2,
            TYPE_AUDIO  = 3;

    private Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MessageView onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout;
        switch (viewType) {
            case TYPE_TEXT:
                layout = View.inflate(context, R.layout.item_conversation_message, null);
                return new TextMessageView(layout);

            case TYPE_IMAGE:
                layout = View.inflate(context, R.layout.item_conversation_message, null);
                return new ImageMessageView(layout);

            case TYPE_VIDEO:
                layout = View.inflate(context, R.layout.item_conversation_message, null);
                return new VideoMessageView(layout);

            case TYPE_AUDIO:
                layout = View.inflate(context, R.layout.item_conversation_message, null);
                return new AudioMessageView(layout);
        }

        throw new IllegalArgumentException("Illegal viewType: " + viewType);
    }

    @Override
    public void onBindViewHolder(MessageView holder, int position) {
        AbstractMessage message = getItemAt(position);
        holder.bind(message);
    }

    @Override
    public int getItemViewType(int position) {
        AbstractMessage message = getItemAt(position);

        if (message instanceof TextMessage) {
            return TYPE_TEXT;
        }

        if (message instanceof ImageMessage) {
            return TYPE_IMAGE;
        }

        if (message instanceof VideoMessage) {
            return TYPE_VIDEO;
        }

        if (message instanceof AudioMessage) {
            return TYPE_AUDIO;
        }

        return -1; // ERROR
    }

    public abstract AbstractMessage getItemAt(int position);
}
