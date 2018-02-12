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
package de.vanitasvitae.slam.mvp.presenter.dummy;

import org.jivesoftware.smack.packet.Message;

import java.util.ArrayList;
import java.util.List;

import de.vanitasvitae.slam.mvp.DummyPresenterFactory;
import de.vanitasvitae.slam.mvp.contracts.ConversationListContract;
import de.vanitasvitae.slam.xmpp.Contact;
import de.vanitasvitae.slam.xmpp.Conversation;

public class DummyConversationListPresenter implements ConversationListContract.Presenter {

    private final ConversationListContract.View view;

    public DummyConversationListPresenter(ConversationListContract.View view) {
        this.view = view;
        List<Conversation> conversationList = new ArrayList<>();
        for (Contact contact : DummyPresenterFactory.STORE.contacts) {
            List<Message> m = DummyPresenterFactory.STORE.conversations.get(contact.getJid());
            if (m != null) {
                conversationList.add(new Conversation(contact, m.get(m.size() - 1).getBody(), "now"));
            }
        }
        view.populateConversationList(conversationList);
    }

    @Override
    public void onConversationClick() {

    }

    @Override
    public void onConversationLongClick() {

    }

    @Override
    public void load() {

    }
}
