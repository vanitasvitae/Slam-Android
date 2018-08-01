package de.vanitasvitae.slam.task;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.jxmpp.jid.EntityBareJid;

import java.net.InetAddress;
import java.net.UnknownHostException;

import de.vanitasvitae.slam.mvp.contracts.LoginContract;
import de.vanitasvitae.slam.service.SlamXmppConnection;

/**
 * Created by Paul Schaub on 26.02.18.
 */
public class LoginTask extends AsyncTask<LoginTask.Credentials, Void, SlamXmppConnection> {

    private final LoginContract.View view;
    private Exception exception;

    public LoginTask(LoginContract.View view) {
        this.view = view;
    }

    @Override
    protected SlamXmppConnection doInBackground(Credentials... credentials) {
        if (credentials.length != 1) {
            throw new IllegalArgumentException("Please only present one set of credentials at a time.");
        }

        SlamXmppConnection connection;
        try {
            connection = new SlamXmppConnection(credentials[0].jid, credentials[0].password);
        } catch (UnknownHostException e) {
            exception = e;
            return null;
        }

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(SlamXmppConnection result) {
        if (exception == null) {
            return;
        }

        if (exception instanceof UnknownHostException) {
            view.showServerNotFoundError();
            return;
        }
    }

    public static class Credentials {
        private final EntityBareJid jid;
        private final String password;

        public Credentials(@NonNull EntityBareJid jid, @NonNull String password) {
            this.jid = jid;
            this.password = password;
        }
    }
}
