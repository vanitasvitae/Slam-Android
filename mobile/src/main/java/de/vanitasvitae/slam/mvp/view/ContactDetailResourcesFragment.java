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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.xmpp.Resource;

/**
 * Created by Paul Schaub on 13.02.18.
 */
public class ContactDetailResourcesFragment extends Fragment {

    @BindView(R.id.contact_detail_resources_recycler_view)
    RecyclerView resourcesRecyclerView;

    private final List<Resource> resources = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_detail__resources, container, false);
        ButterKnife.bind(this, view);
        resourcesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        resourcesRecyclerView.setAdapter(resourcesAdapter);
        resourcesAdapter.notifyDataSetChanged();
        return view;
    }

    public void setResources(List<Resource> resources) {
        this.resources.clear();
        this.resources.addAll(resources);
        resourcesAdapter.notifyDataSetChanged();
    }

    private final RecyclerView.Adapter<ResourceView> resourcesAdapter = new RecyclerView.Adapter<ResourceView>() {
        @Override
        public ResourceView onCreateViewHolder(ViewGroup parent, int viewType) {
            View resourceView = LayoutInflater.from(getActivity()).inflate(R.layout.item_resource, parent, false);
            return new ResourceView(resourceView);
        }

        @Override
        public void onBindViewHolder(final ResourceView holder, final int position) {
            Resource resource = resources.get(holder.getAdapterPosition());
            holder.bind(resource);
        }

        @Override
        public int getItemCount() {
            return resources.size();
        }
    };


    static class ResourceView extends RecyclerView.ViewHolder {

        public ResourceView(View itemView) {
            super(itemView);
        }

        public void bind(Resource resource) {
            ((TextView)itemView.findViewById(R.id.resource__resource_value)).setText(resource.getResource());
            ((TextView)itemView.findViewById(R.id.resource__status_value)).setText(resource.getStatus());
            ((TextView)itemView.findViewById(R.id.resource__client_value)).setText(resource.getClient());
            ((TextView)itemView.findViewById(R.id.resource__system_value)).setText(resource.getSystem());
        }
    }
}
