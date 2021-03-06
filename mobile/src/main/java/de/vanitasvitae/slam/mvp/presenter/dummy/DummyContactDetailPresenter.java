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
package de.vanitasvitae.slam.mvp.presenter.dummy;

import android.util.Log;

import org.jxmpp.jid.BareJid;

import java.util.ArrayList;
import java.util.List;

import de.vanitasvitae.slam.mvp.contracts.ContactDetailContract;
import de.vanitasvitae.slam.xmpp.Resource;

public class DummyContactDetailPresenter implements ContactDetailContract.Presenter {

    private final ContactDetailContract.View view;
    private BareJid contact;

    public DummyContactDetailPresenter(ContactDetailContract.View view) {
        this.view = view;

        Log.d("SLAM", "Set dummy resources");
        view.setResources(dummyResources());
    }

    private List<Resource> dummyResources() {
        List<Resource> r = new ArrayList<>();
        r.add(new Resource("Mobile", "Away since 15min", "Conversations 1.23.4", "Android"));
        r.add(new Resource("Laptop", "online", "Gajim 16.0.6", "Arch Linux"));
        r.add(new Resource("Desktop", "I like coffee!", "Dino", "Debian Stable"));
        return r;
    }

    @Override
    public void onAvatarClick() {

    }

    @Override
    public void onSharedMediaClick() {

    }

    @Override
    public void onAudioCallClick() {

    }

    @Override
    public void onVideoCallClick() {

    }

    @Override
    public void onShareContactClick() {

    }

    @Override
    public void onBlockContactClick() {

    }

    @Override
    public void onEditClick() {

    }

    @Override
    public void onDeleteClick() {

    }

    @Override
    public void onFingerprintTrustChanged() {

    }
}
