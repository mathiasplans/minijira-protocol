package messages.messagetypes;

import data.RawProject;
import messages.MessageType;

public class SetProjectMessage extends MessageClass {
    public SetProjectMessage(RawProject data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SETPROJECT;
    }
}
