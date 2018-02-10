package de.vanitasvitae.slam.mvp.contracts;

import java.util.List;

import de.vanitasvitae.slam.mvp.view.ConversationListFragment;

/**
 * Model-View-Presenter contract for the {@link ConversationListFragment}.
 *
 * Created by Paul Schaub on 01.02.18.
 */
public interface ContactListContract {

    interface View {
        void addContactListItems(List<?> contacts);
        void clearContactListItems();
        void onUpdateContactPresence();
        void showContactListLoadingIndicator();
        void hideContactListLoadingIndicator();
    }

    interface Presenter {
        void onContactListItemClick();
        void onContactListItemLongClick();
        void addNewContact();
        void deleteContact();
    }
}
