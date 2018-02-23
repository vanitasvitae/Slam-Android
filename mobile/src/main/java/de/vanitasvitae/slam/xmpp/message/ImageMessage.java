package de.vanitasvitae.slam.xmpp.message;

import org.jxmpp.jid.Jid;

import java.util.Date;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public class ImageMessage extends MediaMessage {

    public ImageMessage(Jid sender, Jid recipient, Date sent) {
        super(sender, recipient, sent);
    }
}
