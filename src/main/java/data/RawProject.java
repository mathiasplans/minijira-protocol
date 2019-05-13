package data;

public class RawProject {
    public long projectId = -1;
    public RawTask[] tasks = null;
    public long[] taskIds = null;
    public String projectName = null;
    public String repositoryUrl = null;

    public RawProject(long projectId, RawTask[] tasks, long[] taskIds, String projectName, String repositoryUrl) {
        this.projectId = projectId;
        this.tasks = tasks;
        this.taskIds = taskIds;
        this.projectName = projectName;
        this.repositoryUrl = repositoryUrl;
    }
}
