package messages;

import com.google.gson.Gson;
import data.RawLogin;
import data.RawProject;
import data.RawProjectNameList;
import data.RawTask;
import java.nio.charset.StandardCharsets;

public enum MessageType {

    /**
     * None type. This message can be sent but it is not handled.
     */
    NONE(0, null),

    /**
     * An error occurred while handling the command/message.
     */
    ERROR(1, String.class),

    /**
     * Acknowledgement.
     */
    RESPONSE(2, byte[].class),

    /**
     * This message will be sent back as RESPONSE message.
     */
    ECHO(3, byte[].class),

    /**
     * Task creation request.
     */
    CREATETASK(4, RawTask.class),

    /**
     * Task removal request Return error if client
     * does not have permission or has not logged in.
     */
    REMOVETASK(5, Long.class),

    /**
     * Update the task.
     */
    UPDATETASK(6, RawTask.class),

    /*
     * Change the status of task. Use UPDATETASK(6) instead.
     */
    //SETSTATUSTASK(7),

    /**
     * server requests the task list of a procejt with the given project id from another server.
     */
    GETSERVERTASKLIST(8, Long.class),

    /*
     * client requests the task list from server.
     */
    //GETTASKLIST(9),

    /**
     * client sends login credentials so the server knows if
     * this user has permission to remove tasks.
     */
    LOGIN(10, RawLogin.class),

    /**
     * Server sends session information back to client upon successful login.
     * Client should remember the session information to connect to server again without login.
     */
    SETSESSION(11, Session.class),

    /**
     * Client requests the list of projects from server.
     */
    GETPROJECTLIST(12, null),

    /**
     * Server returns project list for the given userId
     */
    SETPROJECTLIST(13, RawProjectNameList.class),

    /**
     * Client requests the tasklist of a project.
     */
    GETPROJECT(14, Long.class),

    /**
     * Server returns the tasklist of a project.
     */
    SETPROJECT(15, RawProject.class),

    /**
     * Client requests the list of users on a project
     */
    //TODO: implement usages
    GETPROJECTUSERS(16, null),

    /**
     * Server returns the list of users on a project
     */
    SETPROJECTUSERS(17, null);

    private final int type;
    private final Class typeclass;

    MessageType(int type, Class typeclass) {
        this.type = type;
        this.typeclass = typeclass;
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

    private static final Gson gson = new Gson();

    Class getTypeclass() {
        return typeclass;
    }

    public Object fromJson(byte[] data) {
        if (typeclass == null) return null;
        return gson.fromJson(new String(data, StandardCharsets.UTF_8), typeclass);
    }

    public byte[] toJsonBytes(Object rawObject) {
        return gson.toJson(rawObject).getBytes(StandardCharsets.UTF_8);
    }
}
