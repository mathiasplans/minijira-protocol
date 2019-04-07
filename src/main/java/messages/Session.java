package messages;

import data.RawUser;

public class Session {
    private static final int SESSION_DURATION_MS = 30*60*1000; // session expires in 30 minutes.

    byte[] sessionKey;
    long sessionid;
    long userID;
    long timeExpired;

    String clientIP;
    int clientPort;
    String serverIP;
    int serverPort;

    public byte[] getSessionKey() {
        return sessionKey;
    }

    public long getSessionid() {
        return sessionid;
    }

    public long getUserID() {
        return userID;
    }

    public long getTimeExpired() {
        return timeExpired;
    }

    public String getClientIP() {
        return clientIP;
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public int getServerPort() {
        return serverPort;
    }

    public boolean isValid() {
        return System.currentTimeMillis() < timeExpired;
    }

    void resetExpire() {
        timeExpired = System.currentTimeMillis() + SESSION_DURATION_MS;
    }

    public Session() {

    }

    public Session(byte[] sessionKey, long sessionid, long userID, String clientIP, int clientPort, String serverIP, int serverPort) {
        this.sessionKey = sessionKey;
        this.sessionid = sessionid;
        this.userID = userID;
        this.timeExpired = System.currentTimeMillis() + SESSION_DURATION_MS;
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
}
