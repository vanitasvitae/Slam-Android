package de.vanitasvitae.slam.mvp.contracts.message;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public interface AudioMessageContract {

    interface View extends MediaMessageContract.View {
        void setWaveFormPreview();
        void setLength(int seconds);
        void updatePlayProgress(float percent);
    }

    interface Presenter extends MediaMessageContract.Presenter {

    }
}
