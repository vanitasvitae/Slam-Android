/*
 * Copyright 2018 Paul Schaub
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package de.vanitasvitae.slam.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jxmpp.jid.BareJid;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.mvp.contracts.ContactListContract;
import de.vanitasvitae.slam.ui.ContactListEntry;
import de.vanitasvitae.slam.xmpp.Contact;

/**
 * Created by Paul Schaub on 11.02.18.
 */
public class ContactListFragment extends Fragment implements ContactListContract.View {

    @BindView(R.id.recycler_list)
    RecyclerView recyclerView;

    private final ContactListContract.Presenter presenter;

    private final List<Contact> contacts = new ArrayList<>();

    public ContactListFragment() {
        super();
        this.presenter = PresenterFactory.getInstance().createContactListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation_contact_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(contactsAdapter);
        contactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void addContactListItems(List<Contact> contacts) {
        this.contacts.addAll(contacts);
        contactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearContactListItems() {
        this.contacts.clear();
        contactsAdapter.notifyDataSetChanged();
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

    private final RecyclerView.Adapter<ContactListEntry> contactsAdapter = new RecyclerView.Adapter<ContactListEntry>() {
        @Override
        public ContactListEntry onCreateViewHolder(ViewGroup parent, int viewType) {
            View contactView = LayoutInflater.from(getActivity()).inflate(R.layout.item_contact, parent, false);
            return new ContactListEntry(contactView);
        }

        @Override
        public void onBindViewHolder(ContactListEntry holder, int position) {
            final Contact contact = contacts.get(holder.getAdapterPosition());
            holder.bind(contact);
            holder.setOnEntryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToContactDetail(contact.getJid());
                }
            });
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
    };

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

    @Override
    public void navigateToContactDetail(BareJid contact) {
        startActivity(new Intent(getContext(), ContactDetailActivity.class));
    }


}
