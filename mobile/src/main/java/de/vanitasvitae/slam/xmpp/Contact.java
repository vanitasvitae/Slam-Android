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

import org.jxmpp.jid.BareJid;

/**
 * Created by Paul Schaub on 11.02.18.
 */
public class Contact {

    private final BareJid jid;
    private final Account account;
    private String nickname;

    public Contact(BareJid jid, Account account) {
        this(jid, account, null);
    }

    public Contact(BareJid jid, Account account, String nickname) {
        this.jid = jid;
        this.nickname = nickname;
        this.account = account;
    }

    public BareJid getJid() {
        return jid;
    }

    public String getNickname() {
        return nickname;
    }

    public Account getAccount() {
        return account;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
