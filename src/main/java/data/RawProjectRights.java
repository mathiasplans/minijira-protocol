package data;


public enum RawProjectRights {

    /**
     * No rights for this project.
     */
    NOBODY(0),

    /**
     * WANNABE - Somebody who wants to join a project.
     *
     * Can be seen by users who have QUEST rights or more.
     * BOSS can either add more rights to WANNABE or make him NOBODY.
     */
    WANNABE(1),

    /**
     * WANNABE rights +
     * See tasks.
     * See all users who have WANNABE rights or more for this project.
     */
    QUEST(2),

    /**
     * QUEST rights +
     * Create task.
     * Edit task.
     * Complete task.
     */
    CONTRIBUTOR(3),

    /**
     * CONTRIBUTOR rights +
     * Delete task.
     * Edit other peoples rights for this project.
     * NB: BOSS is a can only edit someones rights if they are already WANNABE or more.
     */
    BOSS(4);

    int rightsValue; // Different for each variant.

    RawProjectRights(int rightsValue) {
        this.rightsValue = rightsValue;
    }

    public int getRightsValue() {
        return rightsValue;
    }
}

/*
 * Following rights values are not in the enum, because it does not make sense to have those rights in a project.
 *
 *
 *
 * EVERY HUMAN
 *
 * Become user.
 *
 *
 *
 * EVERY USER
 *
 * Edit username, password and email.
 * Create project and become BOSS.
 * Delete own account.
 *
 *
 *
 * ADMIN
 *
 * This should be binary flag for each user.
 *
 * Delete users.
 * Give user ADMIN rights.
 *
 * First ADMIN ever will be built into software. He will change his password and add other ADMINS.
 */
