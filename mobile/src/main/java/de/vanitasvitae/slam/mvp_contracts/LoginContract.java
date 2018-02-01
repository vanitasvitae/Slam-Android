package de.vanitasvitae.slam.mvp_contracts;

/**
 * Model-View-Presenter contract of the login screen.
 * Created by Paul Schaub on 01.02.18.
 */
public interface LoginContract {

    interface View {
        void showInvalidJidError();
        void hideInvalidJidError();
        void showInvalidPasswordError();
        void showIncorrectPasswordError();
        void hidePasswordError();
        void showServerNotFoundError();
        void showProgressIndicator();
        void hideProgressIndicator();
        void navigateToMainActivity();
    }

    interface Presenter {
        void jidChanged(String jid);
        void passwordChanged(String password);
        void loginClicked();
    }
}
