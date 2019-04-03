package messages.messagetypes;

import messages.MessageType;

public class EchoMessage extends MessageClass {
    public EchoMessage(byte[] data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.ECHO;
    }
}
