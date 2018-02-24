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
package de.vanitasvitae.slam.mvp;

import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.vanitasvitae.slam.xmpp.Contact;

public class DummyStore {

    public final Map<BareJid, List<Message>> conversations = new HashMap<>();
    public final List<Contact> contacts = new ArrayList<>();

    public DummyStore() throws XmppStringprepException {
        contacts.addAll(contacts());
        conversations.put(contacts.get(0).getJid(), aliceMessages());
        conversations.put(contacts.get(1).getJid(), bobMessages());
    }

    private List<Contact> contacts() throws XmppStringprepException {
        List<Contact> l = new ArrayList<>();
        l.add(new Contact(JidCreate.entityBareFrom("alice@wonderland.lit"), null, "Alice"));
        l.add(new Contact(JidCreate.entityBareFrom("bob@builder.tv"), null));
        l.add(new Contact(JidCreate.entityBareFrom("juliet@capulet.lit"), null, "Juliet"));
        l.add(new Contact(JidCreate.entityBareFrom("romeo@montague.lit"), null, "Romeo <3"));
        return l;
    }

    private List<Message> aliceMessages() throws XmppStringprepException {
        BareJid jid = JidCreate.entityBareFrom("alice@wonderland.lit");
        List<Message> m = new ArrayList<>();

        Message m1 = new Message();
        m1.setFrom(jid);
        m1.setBody("Hi!");
        m.add(m1);

        return m;
    }

    private List<Message> bobMessages() throws XmppStringprepException {
        BareJid jid = JidCreate.entityBareFrom("bob@builder.tv");
        List<Message> m = new ArrayList<>();

        Message m1 = new Message();
        m1.setFrom(jid);
        m1.setBody("Hello, I'm Bob!");
        m.add(m1);

        Message m2 = new Message();
        m2.setFrom(jid);
        m2.setBody("Do you think we can do it?");
        m.add(m2);

        Message m3 = new Message();
        m3.setFrom(jid);
        m3.setBody("YES WE CAN!!!");
        m.add(m3);

        return m;
    }
}
