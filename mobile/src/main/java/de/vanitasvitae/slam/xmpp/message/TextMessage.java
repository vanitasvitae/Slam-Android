package de.vanitasvitae.slam.xmpp.message;

import org.jxmpp.jid.Jid;

import java.util.Date;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public class TextMessage extends AbstractMessage {

    private String content;

    public TextMessage(Jid sender, Jid recipient, String content, Date sent) {
        super(sender, recipient, sent);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
