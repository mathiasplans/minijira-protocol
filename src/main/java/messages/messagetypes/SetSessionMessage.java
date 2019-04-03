package messages.messagetypes;

import messages.MessageType;
import messages.Session;

public class SetSessionMessage extends MessageClass {
    public SetSessionMessage(Session data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SETSESSION;
    }
}
