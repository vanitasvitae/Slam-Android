package de.vanitasvitae.slam.mvp.contracts.message;

/**
 * Model-View-Presenter contract for an abstract message.
 */
public interface AbstractMessageContract {

    interface View {
        void setDirection(Direction direction);
        void setStatusSending();
        void setStatusSendingFailed();
        void setStatusSent();
        void setStatusRead();
        void setSelected();
        void displayMessageInformation();
        void displayErrorMessage();
    }

    interface Presenter {
        void onDeleteMessage();
        void onReadMessage();
        void onMessageClick();
        void onMessageLongClick();
    }

    enum Direction {
        sent,       // We are the author (the message was sent from one of the users devices)
        received,   // We are not the author
    }
}
