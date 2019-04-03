package messages.messagetypes;

import messages.MessageType;

public class GetProjectMessage extends MessageClass {
    public GetProjectMessage(Long projectId) {
        super(projectId);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GETPROJECT;
    }
}
