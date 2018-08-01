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
package de.vanitasvitae.slam.mvp.presenter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;
import java.net.UnknownHostException;

import de.vanitasvitae.slam.mvp.contracts.LoginContract;
import de.vanitasvitae.slam.service.SlamXmppConnection;
import de.vanitasvitae.slam.service.SlamXmppService;
import de.vanitasvitae.slam.xmpp.Account;

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";
    private final LoginContract.View view;

    private BareJid jid;
    private String password;
    private SlamXmppService service;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void jidChanged(String jid) {
        try {
            this.jid = JidCreate.entityBareFrom(jid);
            view.hideInvalidJidError();

            if (password != null && !password.isEmpty()) {
                view.enableLoginButton();
            }

        } catch (XmppStringprepException e) {
            this.jid = null;
            view.showInvalidJidError();
            view.disableLoginButton();
        }
    }

    @Override
    public void passwordChanged(String password) {
        this.password = password;
        if (password == null || password.isEmpty()) {
            view.disableLoginButton();
        } else if (jid != null) {
            view.enableLoginButton();
        }
    }

    @Override
    public void loginClicked() {
        if (jid == null) {
            view.showInvalidJidError();
            return;
        }

        if (password == null) {
            view.showInvalidPasswordError();
            return;
        }

        if (service != null) {
            view.disableLoginButton();
            final Account account = new Account(jid);

            Thread loginThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    SlamXmppConnection connection = null;
                    try {
                        connection = new SlamXmppConnection(
                                jid.asEntityBareJidOrThrow(), password);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        ((AppCompatActivity)view).runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText((Context)view, "Unknown host", Toast.LENGTH_SHORT).show();
                            }
                        });

                        return;
                    }

                    service.addConnection(account, connection);
                    final SlamXmppConnection finalConnection = connection;
                    connection.login(new SlamXmppConnection.LoginCallback() {
                        @Override
                        public void success() {
                            ((AppCompatActivity)view).runOnUiThread(new Runnable() {
                                public void run() {
                                    view.navigateToMainActivity();
                                }
                            });
                            ChatManager.getInstanceFor(finalConnection.getConnection())
                                    .addIncomingListener(new IncomingChatMessageListener() {
                                        @Override
                                        public void newIncomingMessage(final EntityBareJid from, final Message message, Chat chat) {
                                            final AppCompatActivity activity = (AppCompatActivity) view;
                                            activity.runOnUiThread(new Runnable() {
                                                public void run() {
                                                    Toast.makeText(activity.getApplicationContext(), from.toString() + ": " + message.getBody(), Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    });
                        }

                        @Override
                        public void failure(final Exception e) {
                            e.printStackTrace();
                            ((AppCompatActivity)view).runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText((Context)view, e.getMessage(), Toast.LENGTH_LONG).show();
                                    view.enableLoginButton();
                                }
                            });
                        }
                    });
                }
            });
            loginThread.start();

        } else {
            view.showServerNotFoundError();
        }
    }

    @Override
    public void bindService(SlamXmppService service) {
        Log.d(TAG, "bindService()");
        this.service = service;
    }

    @Override
    public void unbindService() {
        Log.d(TAG, "unbindService()");
        this.service = null;
    }
}
