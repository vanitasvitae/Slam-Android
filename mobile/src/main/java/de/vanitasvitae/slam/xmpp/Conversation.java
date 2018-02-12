package de.vanitasvitae.slam.xmpp;

/**
 * Created by Paul Schaub on 11.02.18.
 */
public class Conversation {

    private final Contact contact;
    private final String lastMessage;
    private final String date;

    public Conversation(Contact contact) {
        this(contact, null, null);
    }

    public Conversation(Contact contact, String lastMessage, String date) {
        this.contact = contact;
        this.lastMessage = lastMessage;
        this.date = date;
    }

    public Contact getContact() {
        return contact;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getDate() {
        return date;
    }
}
