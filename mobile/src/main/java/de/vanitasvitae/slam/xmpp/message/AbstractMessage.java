package de.vanitasvitae.slam.xmpp.message;

import org.jxmpp.jid.Jid;

import java.util.Date;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public abstract class AbstractMessage {

    private final Jid sender;
    private final Jid recipient;
    private final Date sent;

    private boolean read = false;

    public AbstractMessage(Jid sender, Jid recipient, Date sent) {
        this.sender = sender;
        this.recipient = recipient;
        this.sent = sent;
    }

    public Jid getSender() {
        return sender;
    }

    public Jid getRecipient() {
        return recipient;
    }

    public Date getSent() {
        return sent;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = true;
    }
}
