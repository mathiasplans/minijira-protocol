package messages.messagetypes;

import messages.MessageType;
import messages.Session;

public class SetSessionMessage extends MessageClass {

    /**
     * Server<->Client
     *
     * Server sends the session information to client.
     * Or Client sends the session to Server to access the projects without login.
     * @param data Session.
     */
    public SetSessionMessage(Session data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SETSESSION;
    }
}
