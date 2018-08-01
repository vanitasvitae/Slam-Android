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

import java.util.List;

import de.vanitasvitae.slam.xmpp.Resource;

/**
 * Model-View-Presenter contract for the {@link de.vanitasvitae.slam.mvp.view.ContactDetailActivity}.
 */
public interface ContactDetailContract {

    interface View extends BaseContract.BaseView<Presenter> {
        void setContactAvatar();
        void setNickname(String nickname);
        void setResources(List<Resource> presences);
        void clearFingerprints();
        void addFingerprints(List<?> fingerprints);
    }

    interface Presenter extends BaseContract.BasePresenter {
        void onAvatarClick();
        void onSharedMediaClick();
        void onAudioCallClick();
        void onVideoCallClick();
        void onShareContactClick();
        void onBlockContactClick();
        void onEditClick();
        void onDeleteClick();
        void onFingerprintTrustChanged();
    }
}
