package de.vanitasvitae.slam.mvp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.vanitasvitae.slam.R;
import de.vanitasvitae.slam.ui.ChatMessageEntry;

/**
 * Fragment that shows the conversation with a user.
 *
 * Created by Paul Schaub on 30.01.18.
 */
public class ConversationFragment extends Fragment {

    @BindView(R.id.recycler_chat)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        final String[] messages = new String[] {"This is an example message.", "null","This is a very long message. Its purpose is to demonstrate Slam!s ability to render longer messages in a sane way without looking too crowded."};
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerView.Adapter<ChatMessageEntry>() {
            @Override
            public ChatMessageEntry onCreateViewHolder(ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_chatmessage, parent, false);
                return new ChatMessageEntry(view1);
            }

            @Override
            public void onBindViewHolder(ChatMessageEntry holder, int position) {
                View content;
                if (position != 1) {
                    content = new TextView(getActivity());
                    ((TextView)content).setText(messages[position]);
                } else {
                    content = new ImageView(getActivity());
                    ((ImageView)content).setImageResource(R.drawable.ic_add_a_photo_black_48dp);
                }
                holder.bind("alice@wonderland.lit",
                        "Member", content, "just now");
            }

            @Override
            public int getItemCount() {
                return messages.length;
            }
        });
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}
