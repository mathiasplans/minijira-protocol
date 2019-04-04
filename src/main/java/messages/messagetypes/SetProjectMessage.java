package messages.messagetypes;

import data.RawProject;
import messages.MessageType;

public class SetProjectMessage extends MessageClass {

    /**
     * Server->Client
     *
     * Server sends a project to client.
     * @param data the RawProject that contains data about the project.
     */
    public SetProjectMessage(RawProject data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SETPROJECT;
    }
}
