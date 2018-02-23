package de.vanitasvitae.slam.mvp.contracts.message;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public interface MediaMessageContract {

    interface View extends AbstractMessageContract.View {
        void setDownloadable(boolean downloadable);
        void setDownloaded(boolean downloaded);
        void updateDownloadProgress(float percent);
        void setSize(int bytes);
    }

    interface Presenter extends AbstractMessageContract.Presenter{

    }
}
