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

import org.jxmpp.stringprep.XmppStringprepException;

import de.vanitasvitae.slam.mvp.contracts.ContactDetailContract;
import de.vanitasvitae.slam.mvp.contracts.ContactListContract;
import de.vanitasvitae.slam.mvp.contracts.ConversationContract;
import de.vanitasvitae.slam.mvp.contracts.ConversationListContract;
import de.vanitasvitae.slam.mvp.contracts.LoginContract;
import de.vanitasvitae.slam.mvp.contracts.SearchContract;
import de.vanitasvitae.slam.mvp.presenter.dummy.DummyContactDetailPresenter;
import de.vanitasvitae.slam.mvp.presenter.dummy.DummyContactListPresenter;
import de.vanitasvitae.slam.mvp.presenter.dummy.DummyConversationListPresenter;
import de.vanitasvitae.slam.mvp.presenter.dummy.DummyConversationPresenter;
import de.vanitasvitae.slam.mvp.presenter.dummy.DummyLoginPresenter;
import de.vanitasvitae.slam.mvp.presenter.dummy.DummySearchPresenter;

public class DummyPresenterFactory extends PresenterFactory {

    public static DummyStore STORE;

    public DummyPresenterFactory() {
        super();
        try {
            STORE = new DummyStore();
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactDetailContract.Presenter createContactDetailPresenter(ContactDetailContract.View view) {
        return new DummyContactDetailPresenter(view);
    }

    @Override
    public ContactListContract.Presenter createContactListPresenter(ContactListContract.View view) {
        return new DummyContactListPresenter(view);
    }

    @Override
    public ConversationListContract.Presenter createConversationListPresenter(ConversationListContract.View view) {
        return new DummyConversationListPresenter(view);
    }

    @Override
    public ConversationContract.Presenter createConversationPresenter(ConversationContract.View view) {
        return new DummyConversationPresenter(view);
    }

    @Override
    public LoginContract.Presenter createLoginPresenter(LoginContract.View view) {
        return new DummyLoginPresenter(view);
    }

    @Override
    public SearchContract.Presenter createSearchPresenter(SearchContract.View view) {
        return new DummySearchPresenter(view);
    }
}
