package messages.messagetypes;

import messages.MessageType;

public class RemoveTaskMessage extends MessageClass {

    /**
     * Client->Server
     *
     * Client tries to delete a task.
     * Server should check if the user of the session has rights to delete tasks.
     * Task will be removed from the project(s) referenced by RawTask.boards.
     * @param taskId the id of the task to be added.
     */
    public RemoveTaskMessage(Long taskId) {
        super(taskId);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.REMOVETASK;
    }
}
