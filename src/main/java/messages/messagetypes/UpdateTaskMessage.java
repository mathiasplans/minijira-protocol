package messages.messagetypes;

import data.RawTask;
import messages.MessageType;

public class UpdateTaskMessage extends MessageClass {
    public UpdateTaskMessage(RawTask data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.UPDATETASK;
    }
}
