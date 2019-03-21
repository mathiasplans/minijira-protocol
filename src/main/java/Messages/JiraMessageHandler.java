package Messages;

public interface JiraMessageHandler {

    /**
     * @param data
     * @return
     */
    boolean createTask(byte[] data);

    /**
     * @param data
     * @return
     */
    boolean removeTask(byte[] data);

    /**
     * @param data
     * @return
     */
    boolean updateTimeTask(byte[] data);

    /**
     * @param data
     * @return
     */
    boolean setStatusTask(byte[] data);
}
