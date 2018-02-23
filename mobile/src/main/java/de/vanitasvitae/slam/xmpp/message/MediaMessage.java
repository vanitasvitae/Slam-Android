package de.vanitasvitae.slam.xmpp.message;

import org.jxmpp.jid.Jid;

import java.util.Date;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public abstract class MediaMessage extends AbstractMessage {

    public MediaMessage(Jid sender, Jid recipient, Date sent) {
        super(sender, recipient, sent);
    }
}
