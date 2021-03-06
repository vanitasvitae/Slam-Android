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
package de.vanitasvitae.slam.mvp.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.vanitasvitae.slam.AbstractTextWatcher;
import de.vanitasvitae.slam.EditorActionDoneListener;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.mvp.view.abstr.ThemedAppCompatActivity;
import de.vanitasvitae.slam.mvp.contracts.LoginContract;
import de.vanitasvitae.slam.service.SlamXmppService;

public class LoginActivity extends ThemedAppCompatActivity implements LoginContract.View, ServiceConnection {

    // Presenter of this view
    private LoginContract.Presenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.login_username_layout)
    TextInputLayout inputUsernameLayout;

    @BindView(R.id.login_username)
    TextInputEditText inputUsername;

    @BindView(R.id.login_password_layout)
    TextInputLayout inputPasswordLayout;

    @BindView(R.id.login_password)
    TextInputEditText inputPassword;

    @BindView(R.id.button_login)
    Button buttonLogin;

    private SlamXmppService slamService;

    public LoginActivity() {
        super();
        setPresenter(PresenterFactory.getInstance().createLoginPresenter(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        // attempt login on editor action done
        inputPassword.setOnEditorActionListener(new EditorActionDoneListener() {
            @Override
            public void onEditorActionDone(TextView v) {
                login();
            }
        });

        inputUsername.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                presenter.jidChanged(s.toString());
            }
        });

        inputPassword.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                presenter.passwordChanged(s.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Start xmpp service
        Intent serviceIntent = new Intent(this, SlamXmppService.class);
        bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick(R.id.button_login)
    void login() {
        Intent serviceIntent = new Intent(getApplicationContext(), SlamXmppService.class);
        getApplicationContext().startService(serviceIntent);
        presenter.loginClicked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public void showInvalidJidError() {
        inputUsernameLayout.setError(getResources().getText(R.string.login__error_invalid_jid));
    }

    @Override
    public void showInvalidPasswordError() {
        inputPasswordLayout.setError(getResources().getText(R.string.login__error_invalid_password));
    }

    @Override
    public void showIncorrectPasswordError() {
        inputPasswordLayout.setError(getResources().getText(R.string.login__error_incorrect_password));
    }

    @Override
    public void hideInvalidJidError() {
        inputUsernameLayout.setError(null);
    }

    @Override
    public void hidePasswordError() {
        inputPasswordLayout.setError(null);
    }

    @Override
    public void showServerNotFoundError() {
        Toast.makeText(this, R.string.login__error_server_not_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideProgressIndicator() {
        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void disableLoginButton() {
        buttonLogin.setEnabled(false);
    }

    @Override
    public void enableLoginButton() {
        buttonLogin.setEnabled(true);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        SlamXmppService.SlamBinder binder = (SlamXmppService.SlamBinder) service;
        slamService = binder.getService();
        presenter.bindService(slamService);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        slamService = null;
        presenter.unbindService();
    }
}

