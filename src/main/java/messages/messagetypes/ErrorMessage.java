package messages.messagetypes;

import messages.MessageType;

public class ErrorMessage extends MessageClass {
    public ErrorMessage(String data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return null;
    }
}
