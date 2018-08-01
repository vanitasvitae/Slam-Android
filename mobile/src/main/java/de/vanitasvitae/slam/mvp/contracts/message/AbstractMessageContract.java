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
package de.vanitasvitae.slam.mvp.contracts.message;

import de.vanitasvitae.slam.mvp.contracts.BaseContract;

/**
 * Model-View-Presenter contract for an abstract message.
 */
public interface AbstractMessageContract {

    interface View extends BaseContract.BaseView<Presenter> {
        void setDirection(Direction direction);
        void setStatusSending();
        void setStatusSendingFailed();
        void setStatusSent();
        void setStatusRead();
        void setSelected();
        void displayMessageInformation();
        void displayErrorMessage();
    }

    interface Presenter extends BaseContract.BasePresenter {
        void onDeleteMessage();
        void onReadMessage();
        void onMessageClick();
        void onMessageLongClick();
    }

    enum Direction {
        sent,       // We are the author (the message was sent from one of the users devices)
        received,   // We are not the author
    }
}
