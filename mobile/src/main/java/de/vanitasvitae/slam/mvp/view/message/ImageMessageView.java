package de.vanitasvitae.slam.mvp.view.message;

import android.view.View;

import de.vanitasvitae.slam.mvp.contracts.message.ImageMessageContract;
import de.vanitasvitae.slam.xmpp.message.ImageMessage;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public class ImageMessageView extends MediaMessageView<ImageMessage> implements ImageMessageContract.View {

    public ImageMessageView(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(ImageMessage message) {

    }

    @Override
    public void setThumbnail() {

    }

    @Override
    public void displayImage() {

    }
}
