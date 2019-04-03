package messages.messagetypes;

import messages.MessageType;

public class ResponseMessage extends MessageClass {
    public ResponseMessage(byte[] data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.RESPONSE;
    }
}
