package messages.messagetypes;

import messages.MessageType;

public class EchoMessage extends MessageClass {

    /**
     * Client<->Server
     *
     * The message should be echoed back.
     * @param data the data to be echoed back.
     */
    public EchoMessage(byte[] data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.ECHO;
    }
}
