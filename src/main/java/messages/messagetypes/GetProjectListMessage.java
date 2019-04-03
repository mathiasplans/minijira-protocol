package messages.messagetypes;

import messages.MessageType;

public class GetProjectListMessage extends MessageClass {
    public GetProjectListMessage() {
        super(null);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.GETPROJECTLIST;
    }
}
