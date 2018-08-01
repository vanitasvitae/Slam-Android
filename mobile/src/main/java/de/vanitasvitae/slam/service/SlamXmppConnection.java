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
package de.vanitasvitae.slam.service;

import android.util.Log;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.StringUtils;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Paul Schaub on 24.02.18.
 */
public class SlamXmppConnection {

    private static final String TAG = "SlamConn";

    private final EntityBareJid jid;
    private final AbstractXMPPConnection connection;
    private final Resourcepart resourcepart;

    public SlamXmppConnection(EntityBareJid jid, String password) throws UnknownHostException {
        this.jid = jid;
        // Resource
        String res = "Slam-" + StringUtils.randomString(12);
        try {
            this.resourcepart = Resourcepart.from(res);
        } catch (XmppStringprepException e) {
            throw new AssertionError("Resourcepart \"" + res + "\" appears to be invalid.", e);
        }

        // Configuration
        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
                .setHost(this.jid.getDomain().toString())
                .setHostAddress(InetAddress.getByName(jid.getDomain().toString()))
                .setXmppDomain(jid.asDomainBareJid())
                .setUsernameAndPassword(jid.getLocalpart().toString(), password)
                .setResource(resourcepart)
                .build();

        this.connection = new XMPPTCPConnection(configuration);
    }

    public AbstractXMPPConnection getConnection() {
        return connection;
    }

    public void login(final LoginCallback callback) {
        try {
            getConnection().connect().login();
            Log.d(TAG, "Account " + jid.toString() + " logged in.");
            callback.success();
        } catch (Exception e) {
            Log.d(TAG, "Account " + jid.toString() + " could not log in.");
            e.printStackTrace();
            callback.failure(e);
        }
    }

    public interface LoginCallback {
        void success();
        void failure(Exception e);
    }
}
