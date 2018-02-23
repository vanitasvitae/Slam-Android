package de.vanitasvitae.slam.mvp.contracts.message;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public interface TextMessageContract {

    interface View extends AbstractMessageContract.View {
        void setContent(CharSequence content);
        void setStatusCorrected();
    }

    interface Presenter extends AbstractMessageContract.Presenter {
        void onCorrectMessage();
    }
}
