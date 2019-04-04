package messages.messagetypes;

import data.RawTask;
import messages.MessageType;

public class UpdateTaskMessage extends MessageClass {

    /**
     * Client->Server
     *
     * Client tries to updete a task.
     * Can be used to update the time spent on task, task status, etc.
     * @param data the RawTask containing the updated task.
     */
    public UpdateTaskMessage(RawTask data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.UPDATETASK;
    }
}
