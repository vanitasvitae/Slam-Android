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

import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import de.vanitasvitae.slam.mvp.contracts.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;

    private BareJid jid;
    private String password;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void jidChanged(String jid) {
        try {
            this.jid = JidCreate.entityBareFrom(jid);
            view.hideInvalidJidError();
        } catch (XmppStringprepException e) {
            this.jid = null;
            view.showInvalidJidError();
        }
    }

    @Override
    public void passwordChanged(String password) {

    }

    @Override
    public void loginClicked() {

    }
}
