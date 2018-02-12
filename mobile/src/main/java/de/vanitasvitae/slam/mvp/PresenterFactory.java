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
package de.vanitasvitae.slam.mvp;

import org.jxmpp.jid.BareJid;

import de.vanitasvitae.slam.mvp.contracts.ContactDetailContract;
import de.vanitasvitae.slam.mvp.contracts.ContactListContract;
import de.vanitasvitae.slam.mvp.contracts.ConversationContract;
import de.vanitasvitae.slam.mvp.contracts.ConversationListContract;
import de.vanitasvitae.slam.mvp.contracts.LoginContract;
import de.vanitasvitae.slam.mvp.contracts.SearchContract;
import de.vanitasvitae.slam.mvp.presenter.ContactDetailPresenter;
import de.vanitasvitae.slam.mvp.presenter.ContactListPresenter;
import de.vanitasvitae.slam.mvp.presenter.ConversationListPresenter;
import de.vanitasvitae.slam.mvp.presenter.ConversationPresenter;
import de.vanitasvitae.slam.mvp.presenter.LoginPresenter;
import de.vanitasvitae.slam.mvp.presenter.SearchPresenter;

public class PresenterFactory {

    private static PresenterFactory INSTANCE;

    public static PresenterFactory getInstance() {
        return INSTANCE;
    }

    public static void setInstance(PresenterFactory instance) {
        INSTANCE = instance;
    }

    public ContactDetailContract.Presenter createContactDetailPresenter(ContactDetailContract.View view) {
        return new ContactDetailPresenter(view);
    }

    public ContactListContract.Presenter createContactListPresenter(ContactListContract.View view) {
        return new ContactListPresenter(view);
    }

    public ConversationListContract.Presenter createConversationListPresenter(ConversationListContract.View view) {
        return new ConversationListPresenter(view);
    }

    public ConversationContract.Presenter createConversationPresenter(ConversationContract.View view) {
        return new ConversationPresenter(view);
    }

    public LoginContract.Presenter createLoginPresenter(LoginContract.View view) {
        return new LoginPresenter(view);
    }

    public SearchContract.Presenter createSearchPresenter(SearchContract.View view) {
        return new SearchPresenter(view);
    }
}
