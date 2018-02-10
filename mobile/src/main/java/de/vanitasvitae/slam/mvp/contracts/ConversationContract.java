package de.vanitasvitae.slam.mvp.contracts;

import java.util.List;

import de.vanitasvitae.slam.mvp.view.ConversationFragment;

/**
 * Model-View-Presenter contract for the {@link ConversationFragment}.
 * Created by Paul Schaub on 01.02.18.
 */
public interface ConversationContract {

    interface View {
        void addMessageItems(List<?> messages, boolean end);
        void highlightMessageItem();
        void correctMessageItem();
        void navigateToContactProfile();
    }

    interface Presenter {
        void onConversationScrolledToTop();
        void onComposingMessageChanged(String composingMessage);
        void onMessageItemClick();
        void onMessageItemLongClick();
        void onMessageItemSenderClick();
    }
}
