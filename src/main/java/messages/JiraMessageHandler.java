package messages;

import messages.messagetypes.*;

public interface JiraMessageHandler {

    //boolean receiveMessage(MessageClass message);

    ErrorMessage createTask(CreateTaskMessage message);


    ErrorMessage removeTask(RemoveTaskMessage message);


    ErrorMessage updateTask(UpdateTaskMessage message);

    ErrorMessage getServerTaskList(GetServerTaskListMessage message);

    //ErrorMessage getTaskList(GetTaskListMessage message);

    /**
     * Server sends session information back to client upon successful login.
     * Client should remember the session information to connect to server again without login.
     * @param session session information
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    ErrorMessage setSession(SetSessionMessage session);

    /**
     * Client sends user login credentials.
     * @param message a message that contains the username or email and password.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    ErrorMessage login(LoginMessage message);

    /**
     * Client requests the list of projects.
     * @param message a message that contains the user Id of the user whose projects should be returned.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    ErrorMessage getProjectList(GetProjectListMessage message);

    /**
     * Server returns the list of projects for the given user.
     * @param message a message that contains the project names and project IDs.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    ErrorMessage setProjectList(SetProjectListMessage message);

    /**
     * Client requests the tasklist of a project.
     * @param message a message that contains the project id.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    ErrorMessage getProject(GetProjectMessage message);

    /**
     * Server returns the tasklist of a project.
     * @param message a message that contains the task list.
     * @return an ErrorMessage that contains description of the error, null otherwise.
     */
    ErrorMessage setProject(SetProjectMessage message);

}
