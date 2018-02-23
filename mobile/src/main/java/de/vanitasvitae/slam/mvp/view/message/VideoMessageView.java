package de.vanitasvitae.slam.mvp.view.message;

import android.view.View;

import de.vanitasvitae.slam.mvp.contracts.message.VideoMessageContract;
import de.vanitasvitae.slam.xmpp.message.VideoMessage;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public class VideoMessageView extends MediaMessageView<VideoMessage>
        implements VideoMessageContract.View {

    public VideoMessageView(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(VideoMessage message) {

    }

    @Override
    public void setThumbnail() {

    }

    @Override
    public void displayVideo() {

    }
}
