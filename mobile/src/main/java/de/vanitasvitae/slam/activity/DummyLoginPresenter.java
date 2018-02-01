package de.vanitasvitae.slam.activity;

import android.os.Handler;

import de.vanitasvitae.slam.mvp_contracts.LoginContract;

/**
 * Dummy presenter, that has no model.
 * This is used to demonstrate the capabilities of the view.
 * Created by Paul Schaub on 01.02.18.
 */
public class DummyLoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public DummyLoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void jidChanged(String jid) {
        if (jid.length() < 10) {
            view.showInvalidJidError();
        } else {
            view.hideInvalidJidError();
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
        }, 2000);

        // startActivity
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.navigateToMainActivity();
            }
        }, 2500);
    }
}
