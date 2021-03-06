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
