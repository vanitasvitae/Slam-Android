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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jivesoftware.smack.packet.Presence;
import org.jxmpp.jid.BareJid;

import java.util.List;

import butterknife.ButterKnife;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.mvp.contracts.ContactDetailContract;

public class ContactDetailFragment extends Fragment implements ContactDetailContract.View {

    private final ContactDetailContract.Presenter presenter;

    public ContactDetailFragment() {
        this.presenter = PresenterFactory.getInstance().createContactDetailPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setContactAvatar() {

    }

    @Override
    public void setNickname(String nickname) {

    }

    @Override
    public void setPresence(Presence presence) {

    }

    @Override
    public void clearFingerprints() {

    }

    @Override
    public void addFingerprints(List<?> fingerprints) {

    }
}
