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
import android.widget.TextView;

import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.mvp.contracts.ConversationContract;
import de.vanitasvitae.slam.ui.ChatMessageEntry;

/**
 * Fragment that shows the conversation with a user.
 */
public class ConversationFragment extends Fragment implements ConversationContract.View {

    public static final String KEY_JID = "conversationfragment_jid";

    @BindView(R.id.recycler_chat)
    RecyclerView recyclerView;

    private final ConversationContract.Presenter presenter;

    Map<String, Integer> messageIdIndizes = new HashMap<>();
    List<Message> messages = new ArrayList<>();

    private final RecyclerView.Adapter<ChatMessageEntry> chatMessageAdapter = new RecyclerView.Adapter<ChatMessageEntry>() {
        @Override
        public ChatMessageEntry onCreateViewHolder(ViewGroup parent, int viewType) {
            View messageView = LayoutInflater.from(getActivity()).inflate(R.layout.item_chatmessage, parent, false);
            messageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ContactDetailFragment())
                            .commit();
                }
            });
            return new ChatMessageEntry(messageView);
        }

        @Override
        public void onBindViewHolder(ChatMessageEntry holder, int position) {
            Message message = messages.get(position);
            String sender = message.getFrom().toString();
            String role = "Member";
            View content;
            content = new TextView(getActivity());
            ((TextView)content).setText(message.getBody());

            holder.bind(sender, role, content, "now");
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }
    };

    public ConversationFragment() {
        super();
        this.presenter = PresenterFactory.getInstance().createConversationPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        String jidString = arguments.getString(KEY_JID);
        if (jidString != null) {
            try {
                EntityBareJid jid = JidCreate.entityBareFrom(jidString);
                presenter.setPeersJid(jid);
            } catch (XmppStringprepException e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(chatMessageAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void addMessageItems(List<Message> messages, boolean end) {
        if (end) {
            this.messages.addAll(messages);
        } else {
            this.messages.addAll(0, messages);
        }
        chatMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void highlightMessageItem() {

    }

    @Override
    public void correctMessageItem() {

    }

    @Override
    public void navigateToContactProfile() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.fragment_container, new ContactDetailFragment())
                .addToBackStack("detail")
                .commit();
    }
}
