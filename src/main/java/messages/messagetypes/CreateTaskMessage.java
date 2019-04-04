package messages.messagetypes;

import data.RawTask;
import messages.MessageType;

public class CreateTaskMessage extends MessageClass {

    /**
     * Client->Server
     *
     * Server should check if the user of the session has rights to create tasks.
     * Task will be added to the project(s) referenced by RawTask.boards
     * @param data the task to be added
     */
    public CreateTaskMessage(RawTask data) {
        super(data);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.CREATETASK;
    }


}
