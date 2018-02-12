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
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.ArrayList;
import java.util.List;

import de.vanitasvitae.slam.mvp.contracts.ConversationContract;

public class DummyConversationPresenter implements ConversationContract.Presenter {

    private final ConversationContract.View view;

    private final List<Message> dummyMessages = new ArrayList<>();

    public DummyConversationPresenter(ConversationContract.View view) {
        this.view = view;
        populateDummyMessages();
        this.view.addMessageItems(dummyMessages, true);
    }

    private void populateDummyMessages() {
        try {
            BareJid alice = JidCreate.bareFrom("alice@wonderland.lit");

            Message m1 = new Message();
            m1.setFrom(alice);
            m1.setBody("Hello World!");
            dummyMessages.add(m1);

            Message m2 = new Message();
            m2.setFrom(alice);
            m2.setBody("This is a demonstration of a SLAM! conversation.");
            dummyMessages.add(m2);

            Message m3 = new Message();
            m3.setFrom(alice);
            m3.setBody("As you can see, long messages are displayed just as nice as short messages.");
            dummyMessages.add(m3);

            Message m4 = new Message();
            m4.setFrom(alice);
            m4.setBody("Easy usability is one of SLAM!s main focus points. I hope, that SLAM! will develop further to become one very easy and intuitive messenger.");
            dummyMessages.add(m4);
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPeersJid(EntityBareJid jid) {

    }

    @Override
    public void onConversationScrolledToTop() {

    }

    @Override
    public void onComposingMessageChanged(String composingMessage) {

    }

    @Override
    public void onMessageItemClick() {

    }

    @Override
    public void onMessageItemLongClick() {

    }

    @Override
    public void onMessageItemSenderClick() {

    }
}
