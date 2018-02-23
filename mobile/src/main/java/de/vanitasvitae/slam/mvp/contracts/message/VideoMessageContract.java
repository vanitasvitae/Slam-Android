package de.vanitasvitae.slam.mvp.contracts.message;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public interface VideoMessageContract {

    interface View extends MediaMessageContract.View {
        void setThumbnail();
        void displayVideo();
    }

    interface Presenter extends MediaMessageContract.Presenter {

    }
}
