package de.vanitasvitae.slam.xmpp;

import org.jxmpp.jid.BareJid;

/**
 * Created by Paul Schaub on 11.02.18.
 */
public class Contact {

    private final BareJid jid;
    private String nickname;

    public Contact(BareJid jid) {
        this(jid, null);
    }

    public Contact(BareJid jid, String nickname) {
        this.jid = jid;
        this.nickname = nickname;
    }

    public BareJid getJid() {
        return jid;
    }

    public String getNickname() {
        return nickname;
    }
}
