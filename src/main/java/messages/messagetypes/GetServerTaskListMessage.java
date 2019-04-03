package messages.messagetypes;

import messages.MessageType;

public class GetServerTaskListMessage extends MessageClass {
    public GetServerTaskListMessage(Long projectId) {
        super(projectId);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GETSERVERTASKLIST;
    }
}
