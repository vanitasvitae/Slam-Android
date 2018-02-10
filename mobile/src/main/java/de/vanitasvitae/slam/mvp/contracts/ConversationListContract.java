package de.vanitasvitae.slam.mvp.contracts;

import java.util.List;

/**
 * Model-View-Presenter contract of the conversation list fragment.
 * Created by Paul Schaub on 01.02.18.
 */
public interface ConversationListContract {

    interface View {
        void populateConversationList(List<?> conversations);
    }

    interface Presenter {
        void onConversationClick();
        void onConversationLongClick();
        void load();
    }
}
