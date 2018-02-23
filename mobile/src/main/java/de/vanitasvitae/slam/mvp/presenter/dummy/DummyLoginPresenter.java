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
package de.vanitasvitae.slam.mvp.presenter.dummy;

import android.os.Handler;

import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import de.vanitasvitae.slam.mvp.contracts.LoginContract;

/**
 * Dummy presenter, that has no model.
 * This is used to demonstrate the capabilities of the view.
 *
 * Created by Paul Schaub on 01.02.18.
 */
public class DummyLoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public DummyLoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void jidChanged(String jid) {
        // Check if jid is valid XMPP ID.
        try {
            JidCreate.entityBareFrom(jid);
            view.hideInvalidJidError();
        } catch (XmppStringprepException e) {
            view.showInvalidJidError();
        }
    }

    @Override
    public void passwordChanged(String password) {
        if (password.length() < 5) {
            view.showInvalidPasswordError();
        } else if(!password.equals("swordfish")) {
            view.showIncorrectPasswordError();
        } else {
            view.hidePasswordError();
        }
    }

    @Override
    public void loginClicked() {
        // show indicator
        view.showProgressIndicator();
        final Handler handler = new Handler();

        // Hide indicator
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.hideProgressIndicator();
            }
        }, 100);

        // startActivity
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.navigateToMainActivity();
            }
        }, 250);
    }
}
