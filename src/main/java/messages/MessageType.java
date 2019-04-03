package messages;

public enum MessageType {

    /**
     * None type. This message can be sent but it is not handled.
     */
    NONE(0),

    /**
     * An error occurred while handling the command/message.
     */
    ERROR(1),

    /**
     * Acknowledgement.
     */
    RESPONSE(2),

    /**
     * This message will be sent back as RESPONSE message.
     */
    ECHO(3),

    /**
     * Task creation request.
     */
    CREATETASK(4),

    /**
     * Task removal request Return error if client
     * does not have permission or has not logged in.
     */
    REMOVETASK(5),

    /**
     * Update the spent time on task.
     */
    UPDATETASK(6),

    /*
     * Change the status of task. Use UPDATETASK(6) instead.
     */
    //SETSTATUSTASK(7),

    /**
     * server requests the task list from another server.
     */
    GETSERVERTASKLIST(8),

    /*
     * client requests the task list from server.
     */
    //GETTASKLIST(9),

    /**
     * client sends login credentials so the server knows if
     * this user has permission to remove tasks.
     */
    LOGIN(10),

    /**
     * Server sends session information back to client upon successful login.
     * Client should remember the session information to connect to server again without login.
     */
    SETSESSION(11),

    /**
     * Client requests the list of projects from server.
     */
    GETPROJECTLIST(12),

    /**
     * Server returns project list for the given userId
     */
    SETPROJECTLIST(13),

    /**
     * Client requests the tasklist of a project.
     */
    GETPROJECT(14),

    /**
     * Server returns the tasklist of a project.
     */
    SETPROJECT(15),

    /**
     * Client requests the list of users on a project
     */
    //TODO: implement usages
    GETPROJECTUSERS(16),

    /**
     * Server returns the list of users on a project
     */
    SETPROJECTUSERS(17);

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
