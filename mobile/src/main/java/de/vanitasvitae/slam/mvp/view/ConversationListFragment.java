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

import android.app.Fragment;
import android.os.Bundle;
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
import de.vanitasvitae.slam.mvp.contracts.ConversationListContract;
import de.vanitasvitae.slam.ui.ConversationEntry;
import de.vanitasvitae.slam.xmpp.Conversation;

/**
 * Fragment that lists conversations the user takes part in.
 *
 * Created by Paul Schaub on 30.01.18.
 */
public class ConversationListFragment extends Fragment implements ConversationListContract.View {

    @BindView(R.id.recycler_chatlist)
    RecyclerView recyclerView;

    private final ConversationListContract.Presenter presenter;
    private final List<Conversation> conversations = new ArrayList<>();

    private final RecyclerView.Adapter<ConversationEntry> conversationEntryAdapter = new RecyclerView.Adapter<ConversationEntry>() {
        @Override
        public ConversationEntry onCreateViewHolder(ViewGroup parent, int viewType) {
            View conversationView = LayoutInflater.from(getActivity()).inflate(R.layout.chatlist_singlechat, parent, false);
            return new ConversationEntry(conversationView);
        }

        @Override
        public void onBindViewHolder(final ConversationEntry holder, final int position) {
            Conversation conversation = conversations.get(holder.getAdapterPosition());
            String name = conversation.getContact().getNickname();

            holder.bind(
                    name != null ? name : conversation.getContact().getJid().toString(),
                    conversation.getLastMessage(),
                    conversation.getDate(),
                    true);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    navigateToConversation(conversations.get(pos).getContact().getJid());
                }
            });
        }

        @Override
        public int getItemCount() {
            return conversations.size();
        }
    };

    public ConversationListFragment() {
        super();
        this.presenter = PresenterFactory.getInstance().createConversationListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatlist, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(conversationEntryAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void populateConversationList(List<Conversation> conversations) {
        this.conversations.clear();
        this.conversations.addAll(conversations);
        conversationEntryAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToConversation(BareJid contact) {
        Bundle bundle = new Bundle();
        bundle.putString(ConversationFragment.KEY_JID, contact.toString());

        ConversationFragment fragment = new ConversationFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack("conversation")
                .commit();
    }
}
