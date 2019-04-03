package data;

/**
 * This class can be used to serialize and deserialize tasks with gson
 */

public class RawTask {
    public long taskId = -1;
    public boolean isCompleted = false;
    public Long projectId = null;
    public String title = null;
    public String description = null;
    public Integer priority = null;
    public Long createdBy = null;
    public Long deadlineMS = null;      // milliseconds since 1970
    public Long dateCreatedMS = null;   // milliseconds since 1970
    public Long masterTaskId = null;
    public long[] assignedEmployees = null;
    public long[] boards;               // Projects/board/whatever where this task belongs

    public RawTask(long taskId, boolean isCompleted, String title, String description, Integer priority, Long createdBy, Long deadlineMS, Long dateCreatedMS, Long masterTaskId, long[] assignedEmployees, long[] boards) {
        this.taskId = taskId;
        this.isCompleted = isCompleted;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.createdBy = createdBy;
        this.deadlineMS = deadlineMS;
        this.dateCreatedMS = dateCreatedMS;
        this.masterTaskId = masterTaskId;
        this.assignedEmployees = assignedEmployees;
        this.boards = boards;
    }
}
