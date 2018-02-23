package de.vanitasvitae.slam.mvp.view.message;

import android.view.View;

import de.vanitasvitae.slam.mvp.contracts.message.AudioMessageContract;
import de.vanitasvitae.slam.xmpp.message.AudioMessage;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public class AudioMessageView extends MediaMessageView<AudioMessage> implements AudioMessageContract.View {

    public AudioMessageView(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(AudioMessage message) {

    }

    @Override
    public void setWaveFormPreview() {

    }

    @Override
    public void setLength(int seconds) {

    }

    @Override
    public void updatePlayProgress(float percent) {

    }
}
