package de.vanitasvitae.slam.mvp.view.message;

import android.view.View;

import de.vanitasvitae.slam.mvp.contracts.message.AbstractMessageContract;
import de.vanitasvitae.slam.mvp.contracts.message.MediaMessageContract;
import de.vanitasvitae.slam.xmpp.message.MediaMessage;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public abstract class MediaMessageView<T extends MediaMessage> extends MessageView<T>
        implements MediaMessageContract.View {

    public MediaMessageView(View itemView) {
        super(itemView);
    }

    @Override
    public void setDirection(AbstractMessageContract.Direction direction) {

    }

    @Override
    public void setStatusSending() {

    }

    @Override
    public void setStatusSendingFailed() {

    }

    @Override
    public void setStatusSent() {

    }

    @Override
    public void setStatusRead() {

    }

    @Override
    public void setSelected() {

    }

    @Override
    public void displayMessageInformation() {

    }

    @Override
    public void displayErrorMessage() {

    }

    @Override
    public void setDownloadable(boolean downloadable) {

    }

    @Override
    public void setDownloaded(boolean downloaded) {

    }

    @Override
    public void updateDownloadProgress(float percent) {

    }

    @Override
    public void setSize(int bytes) {

    }
}
