package messages.messagetypes;

import messages.MessageType;

public class GetServerTaskListMessage extends MessageClass {

    /**
     * Server->Server
     */
    public GetServerTaskListMessage(Long projectId) {
        super(projectId);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GETSERVERTASKLIST;
    }
}
