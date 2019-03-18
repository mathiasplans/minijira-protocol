public enum MessageType {

    /**
     * None type. This message can be sent but it is not handled
     */
    NONE(0),

    /**
     * An error occurred while handling the command/message
     */
    ERROR(1),

    /**
     * Acknowledgement
     */
    RESPONSE(2),

    /**
     * This message will be sent back as RESPONSE message
     */
    ECHO(3),

    /**
     * Task creation request
     */
    CREATETASK(4),

    /**
     * Task removal request Return error if client
     * does not have permission or has not logged in.
     */
    REMOVETASK(5),

    /**
     * Update the spent time on task
     */
    UPDATETIMETASK(6),

    /**
     * Change the status of task
     */
    SETSTATUSTASK(7),

    /**
     * Server requests the task list from another server
     */
    GETSERVERTASKLIST(8),

    /**
     * Client requests the task list from server
     */
    GETTASKLIST(9),

    /**
     * Client sends login credentials so the server knows if
     * this user has permission to remove tasks.
     */
    LOGIN(10);

    private final int type;

    MessageType(int type) {
        this.type = type;
    }

    public int getAsInt() {
        return type;
    }

    static MessageType getMessageType(int type) throws IllegalArgumentException {
        for(MessageType e: MessageType.values()) {
            if(e.type == type) {
                return e;
            }
        }
        throw new IllegalArgumentException("No such message type");
    }
}
