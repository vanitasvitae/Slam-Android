package de.vanitasvitae.slam.mvp.view;

import android.app.Fragment;
import android.os.Bundle;

import org.jxmpp.jid.BareJid;

import java.util.List;

import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.mvp.contracts.ContactListContract;

/**
 * Created by Paul Schaub on 11.02.18.
 */
public class ContactListFragment extends Fragment implements ContactListContract.View {

    private final ContactListContract.Presenter presenter;

    public ContactListFragment() {
        super();
        this.presenter = PresenterFactory.getInstance().createContactListPresenter(this);
    }

    @Override
    public void addContactListItems(List<?> contacts) {

    }

    @Override
    public void clearContactListItems() {

    }

    @Override
    public void onUpdateContactPresence() {

    }

    @Override
    public void showContactListLoadingIndicator() {

    }

    @Override
    public void hideContactListLoadingIndicator() {

    }

    @Override
    public void navigateToConversation(BareJid contact) {
        ConversationFragment fragment = new ConversationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConversationFragment.KEY_JID, contact.toString());
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack("conversation")
                .commit();
    }
}
