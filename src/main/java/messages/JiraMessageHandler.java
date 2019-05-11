package messages;

import data.*;

public interface JiraMessageHandler {
    // TODO: add missing methods for other message types.

    RawError createTask(RawTask newTask);



    RawError removeTask(Long taskId);


    RawError updateTask(RawTask updatedTask);

    RawError getServerTaskList(Object unimplemented);

    /**
     * Server sends session information back to client upon successful login.
     * Client should remember the session information to connect to server again without login.
     * @param session the session key, 16 bytes.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    RawError setSession(RawSession session);

    /**
     * Client sends user login credentials when connecting for the first time.
     * @param rawLogin a message that contains the username or email and password.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    RawError login(RawLogin rawLogin);

    /**
     * Client requests the list of projects.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    RawError getProjectList();

    /**
     * Server returns the list of projects for the given user.
     * @param projectNames a message that contains the project names and project IDs.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    RawError setProjectList(RawProjectNameList projectNames);

    /**
     * Client requests the tasklist of a project.
     * @param projectId a message that contains the project id.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    RawError getProject(Long projectId);

    /**
     * Server returns the tasklist of a project.
     * @param rawProject a message that contains the task list.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    RawError setProject(RawProject rawProject);

    /**
     * Client or server shares data of a user account.
     * @param rawUser a message that contains full or partial info on a user.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    RawError userInfo(RawUser rawUser);

}
