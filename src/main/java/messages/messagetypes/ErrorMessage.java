package messages.messagetypes;

import messages.MessageType;

public class ErrorMessage extends MessageClass {

    /**
     * Client<->Server
     *
     * Indicates that an error occurred while processing the previous message.
     * @param data the error message that will be printed to System.out on the other side.
     */
    public ErrorMessage(String data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.ERROR;
    }
}
