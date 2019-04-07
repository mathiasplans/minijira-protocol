package data;

/**
 * This class can be used to serialize and deserialize users with gson
 */

public class RawUser {
    public long userId;
    public String username;
    public byte[] passwordHash = null;
    public String userEmail = null;
    public Long lastOnlineMS = null;      // date in milliseconds since 1970, when the user last logged in
    public long[] projects = null;      // project IDs where this user collaborates
    // TODO: make enum for the user rights
    public int[] projectRights = null;  // user rights in projects where this user collaborates; projects.length == projectRights.length
                                        // rights: 0 - no rights, 1 - can see tasks, 2 - can create tasks,
                                        // 3 - can create and complete tasks, 4 - all rights: manage other users, delete tasks
    public long[] friendList = null;    // IDs of other users in this user's friend list

    public RawUser(long userId, String username, byte[] passwordHash, String userEmail, Long lastOnlineMS, long[] projects, int[] projectRights, long[] friendList) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.userEmail = userEmail;
        this.lastOnlineMS = lastOnlineMS;
        this.projects = projects;
        this.projectRights = projectRights;
        this.friendList = friendList;
    }
}
