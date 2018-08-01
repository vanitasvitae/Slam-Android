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
package de.vanitasvitae.slam.mvp.contracts;

import org.jxmpp.jid.BareJid;

import java.util.List;

import de.vanitasvitae.slam.mvp.view.ConversationListFragment;
import de.vanitasvitae.slam.xmpp.Contact;

/**
 * Model-View-Presenter contract for the {@link ConversationListFragment}.
 *
 * Created by Paul Schaub on 01.02.18.
 */
public interface ContactListContract {

    interface View extends BaseContract.BaseView<Presenter> {
        void addContactListItems(List<Contact> contacts);
        void clearContactListItems();
        void onUpdateContactPresence();
        void showContactListLoadingIndicator();
        void hideContactListLoadingIndicator();
        void navigateToConversation(BareJid contact);
        void navigateToContactDetail(BareJid contact);
    }

    interface Presenter extends BaseContract.BasePresenter {
        void onContactListItemClick();
        void onContactListItemLongClick();
        void addNewContact();
        void deleteContact();
    }
}
