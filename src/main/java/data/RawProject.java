package data;

public class RawProject {
    public long projectId = -1;
    public RawTask[] tasks = null;
    public String projectName = null;
    public String repositoryUrl = null;

    public RawProject(long projectId, RawTask[] tasks, String projectName, String repositoryUrl) {
        this.projectId = projectId;
        this.tasks = tasks;
        this.projectName = projectName;
        this.repositoryUrl = repositoryUrl;
    }
}
