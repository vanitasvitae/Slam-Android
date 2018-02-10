package de.vanitasvitae.slam.mvp.presenter;

import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import de.vanitasvitae.slam.mvp.contracts.LoginContract;

/**
 * Created by Paul Schaub on 01.02.18.
 */
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
