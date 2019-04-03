package messages.messagetypes;

import messages.MessageType;

public class RemoveTaskMessage extends MessageClass {
    public RemoveTaskMessage(Long taskId) {
        super(taskId);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.REMOVETASK;
    }
}
