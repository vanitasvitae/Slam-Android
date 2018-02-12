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

/**
 * Created by Paul Schaub on 11.02.18.
 */
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
        l.add(new Contact(JidCreate.entityBareFrom("alice@wonderland.lit"), "Alice"));
        l.add(new Contact(JidCreate.entityBareFrom("bob@builder.tv")));
        l.add(new Contact(JidCreate.entityBareFrom("juliet@capulet.lit"), "Juliet"));
        l.add(new Contact(JidCreate.entityBareFrom("romeo@montague.lit"), "Romeo <3"));
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
