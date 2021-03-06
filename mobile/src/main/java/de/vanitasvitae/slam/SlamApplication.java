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
package de.vanitasvitae.slam;

import android.app.Application;
import android.content.Intent;

import de.vanitasvitae.slam.mvp.DummyPresenterFactory;
import de.vanitasvitae.slam.mvp.PresenterFactory;
import de.vanitasvitae.slam.service.SlamXmppService;

public class SlamApplication extends Application {

    public SlamApplication() {
        super();
        PresenterFactory.setInstance(new PresenterFactory());
    }
}
