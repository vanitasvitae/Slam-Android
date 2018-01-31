package de.vanitasvitae.slam.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.ui.ChatListEntry;

/**
 * Created by Paul Schaub on 30.01.18.
 */
public class ChatListFragment extends Fragment {

    @BindView(R.id.recycler_chatlist)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatlist, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        final String[] usernames = {"alice@wonderland.lit", "Bob the Builder", "Marvin"};
        final String[] messages = {"But I don’t want to go among mad people", "Yes we can!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", "My name is Marvin!"};
        final String[] dates = {"13:37", "yesterday", "24.12.2018"};
        final boolean[] reads = {true, false, true};

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerView.Adapter<ChatListEntry>() {
            @Override
            public ChatListEntry onCreateViewHolder(ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.chatlist_singlechat, parent, false);
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new ChatFragment()).commit();
                    }
                });
                return new ChatListEntry(view1);
            }

            @Override
            public void onBindViewHolder(ChatListEntry holder, int position) {
                holder.bind(
                        usernames[position],
                        messages[position],
                        dates[position],
                        reads[position]);
            }

            @Override
            public int getItemCount() {
                return usernames.length;
            }
        });
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}
