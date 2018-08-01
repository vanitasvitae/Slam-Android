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

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;

import de.vanitasvitae.slam.xmpp.Account;

/**
 * Created by Paul Schaub on 24.02.18.
 */
public class SlamXmppService extends Service {

    private static final String TAG = "SlamService";

    private final IBinder binder = new SlamBinder();
    private Account currentAccount;
    private final HashMap<Account, SlamXmppConnection> connections = new HashMap<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand()");
        return Service.START_STICKY;
    }

    public void addConnection(@NonNull Account account, SlamXmppConnection connection) {
        Log.d(TAG, "Adding a new connection for " + account.getJid().toString());
        this.connections.put(account, connection);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        for (SlamXmppConnection c : connections.values()) {
            c.getConnection().disconnect();
        }
        super.onDestroy();
    }

    public SlamXmppConnection getConnection(@NonNull Account account) {
        return connections.get(account);
    }

    public class SlamBinder extends Binder {
        public SlamXmppService getService() {
            return SlamXmppService.this;
        }
    }
}
