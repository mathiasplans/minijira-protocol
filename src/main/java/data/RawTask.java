package data;

/**
 * This class can be used to serialize and deserialize tasks with gson
 */

public class RawTask {
    public long taskId = -1;
    public boolean isCompleted = false;
    public String title = null;
    public String description = null;
    public Integer priority = null;
    public Long createdBy = null;
    public Long deadlineMS = null;      // milliseconds since 1970
    public Long dateCreatedMS = null;   // milliseconds since 1970
    public Long masterTaskId = null;
    public long[] assignedEmployees = null;
}
