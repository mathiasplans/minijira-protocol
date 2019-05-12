package messages;

import com.google.gson.Gson;
import data.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public enum MessageType {

    /**
     * None type. This message can be sent but it is not handled.
     *
     * Direction: any.
     * Expected answer: no answer.
     */
    NONE(0, null),



    /**
     * An error occurred while handling the command/message.
     *
     * Direction: server -> client | server -> server. TODO: Is server -> server necessary.
     * Expected answer: no answer.
     */
    ERROR(1, RawError.class),

    /**
     * Acknowledgement.
     *
     * Direction: any. TODO: When should this be used? Only after echo? Is this a general conformation message from server?
     *                       Should we have separate OK message type for conforming success.
     * Expected answer: no answer.
     */
    RESPONSE(2, byte[].class),

    /**
     * This message will be sent back as RESPONSE message.
     *
     * Direction: any.
     * Expected answer: RESPONSE.
     */
    ECHO(3, byte[].class),



    /**
     * Task creation request.
     * Add new task to n projects. User must have neccesary rights in all projects.
     * TODO: Should the operation fail or partially finish if user does not have rights in all projects.
     *
     * Direction: client -> server.
     * Expected answer: TODO: Is conformation neccesary.
     *
     */
    CREATETASK(4, RawTask.class),

    /**
     * Task removal request Return error if client
     * does not have permission or has not logged in.
     *
     *
     * TODO: Should a task shared between projects be deleted from all projects or only the ones that the user has access to.
     *
     * Direction: client -> server.
     * Expected answer: TODO: Is conformation neccesary.
     */
    REMOVETASK(5, Long.class),

    /**
     * Update the task.
     * if task is shared between projects, the task changes in both projects.
     * required RawProjectRights.CONTRIBUTOR rights.
     *
     * Direction: client -> server.
     * Expected answer: TODO: Is conformation neccesary.
     */
    UPDATETASK(6, RawTask.class),

    /**
     * Client requests the task with the given ID
     *
     * Direction: client -> server.
     * Expected answer: TODO: Is conformation neccesary.
     */
    GETTASK(7, Long.class),

    /**
     * Client requests the task with the given ID
     *
     * Direction: client -> server.
     * Expected answer: TODO: Is conformation neccesary.
     */
    SETTASK(8, RawTask.class),



    /**
     * User creation request.
     * User will have to supply username, password and email.
     * TODO: How to send password?
     *
     * Direction: client -> server.
     * Expected answer: ERROR or TODO: Is conformation neccesary.
     *
     */
    //CREATEUSER(4, RawUser.class),

    /**
     * Remove user with Id.
     * Request submitter must have admin rights or be owner of the account.
     *
     * Direction: client -> server.
     * Expected answer: TODO: Is conformation neccesary.
     */
    //REMOVEUSER(5, Long.class),

    /**
     * Update the user
     *
     * User can only do following things with his account:
     * 1. Edit username
     * //2. Edit password TODO: how?
     * 3. Edit email.
     * 4. TODO: Add or remove his projects. Is this the right place for it?
     *
     * Direction: client -> server.
     * Expected answer: TODO: Is conformation neccesary.
     */
    //UPDATEUSER(6, RawUser.class),




    /**
     * server requests the task list of a project with the given project id from another server.
     *
     * Direction: server -> server.
     * Expected answer: ERROR or TODO: a new Raw server data class that contains all projects, tasks and users
     */
    GETSERVERTASKLIST(9, Long.class),

    /*
     * client requests the task list from server.
     */
    //GETTASKLIST(9),

    /**
     * client sends login credentials so the server knows if
     * this user has permission to remove tasks.
     *
     * Direction: client -> server.
     * Expected answer: ERROR or SETSESSION.
     */
    LOGIN(10, RawLogin.class),

    /**
     * Server sends session information back to client upon successful login.
     * Client should remember the session information to connect to server again without login.
     *
     * Direction: server -> client.
     * Expected answer: no answer.
     */
    SETSESSION(11, RawSession.class),



    /**
     * Client requests the list of projects from server.
     *
     * Direction: client -> server.
     * Expected answer: ERROR or SETPROJECTLIST.
     */
    GETPROJECTLIST(12, null),

    /**
     * Server returns project list for the given userId
     * User will see only projects that he has right to see.
     *
     * Direction: server -> client.
     * Expected answer: no answer.
     */
    SETPROJECTLIST(13, RawProjectNameList.class),

    /**
     * Client requests the tasklist of a project.
     *
     * Direction: client -> server.
     * Expected answer: ERROR or SETPROJECT.
     */
    GETPROJECT(14, Long.class),

    /**
     * Server returns the tasklist of a project.
     *
     * Direction: server -> client.
     * Expected answer: no answer.
     */
    SETPROJECT(15, RawProject.class),

    /**
     * Client updates a project. Should be used for removing and adding tasks to projects.
     * Client can set a repository url for a project.
     *
     * Direction: client -> server.
     * Expected answer: no answer.
     */
    UPDATEPROJECT(16, RawProject.class),



    /**
     * Client requests the list of users on a project
     */
    //TODO: implement usages
    GETPROJECTUSERS(17, null),

    /**
     * Server returns the list of users on a project
     */
    SETPROJECTUSERS(18, null),

    /**
     * Server returns info on a user, client sends limited info to register a user.
     */
    USERINFO(19, RawUser.class);

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
