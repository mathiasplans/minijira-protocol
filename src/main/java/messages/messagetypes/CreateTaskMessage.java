package messages.messagetypes;

import data.RawTask;
import messages.MessageType;

public class CreateTaskMessage extends MessageClass {
    public CreateTaskMessage(RawTask data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.CREATETASK;
    }


}
