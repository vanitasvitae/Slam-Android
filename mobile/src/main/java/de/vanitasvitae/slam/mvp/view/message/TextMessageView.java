package de.vanitasvitae.slam.mvp.view.message;

import android.view.View;

import de.vanitasvitae.slam.mvp.contracts.message.TextMessageContract;
import de.vanitasvitae.slam.xmpp.message.TextMessage;

/**
 * Created by Paul Schaub on 23.02.18.
 */
public class TextMessageView extends MessageView<TextMessage>
        implements TextMessageContract.View {

    public TextMessageView(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(TextMessage message) {

    }

    @Override
    public void setContent(CharSequence content) {

    }

    @Override
    public void setStatusCorrected() {

    }
}
