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
package de.vanitasvitae.slam.mvp.view.message;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.protobuf.Message;

import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.mvp.contracts.message.AbstractMessageContract;
import de.vanitasvitae.slam.xmpp.message.AbstractMessage;

/**
 * Created by Paul Schaub on 30.01.18.
 */
public abstract class MessageView<T extends AbstractMessage> extends RecyclerView.ViewHolder
        implements AbstractMessageContract.View {

    public MessageView(View itemView) {
        super(itemView);
    }

    public abstract void bind(T message);

    public void setOnAvatarClickListener(View.OnClickListener listener) {
        itemView.findViewById(R.id.contact_image).setOnClickListener(listener);
    }

    @Override
    public void setDirection(AbstractMessageContract.Direction direction) {

    }

    @Override
    public void setStatusSending() {

    }

    @Override
    public void setStatusSendingFailed() {

    }

    @Override
    public void setStatusSent() {

    }

    @Override
    public void setStatusRead() {

    }

    @Override
    public void setSelected() {

    }

    @Override
    public void displayMessageInformation() {

    }

    @Override
    public void displayErrorMessage() {

    }
}
