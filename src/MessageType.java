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
     * Task removal request
     */
    REMOVETASK(5),

    /**
     * Update the spent time on task
     */
    UPDATETIMETASK(6),

    /**
     * Change the status of task
     */
    SETSTATUSTASK(7);

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
