package ru.progwards.java1.lessons.datetime;

import java.util.Date;
import java.util.Random;

public class UserSession {
    private int sessionHandle;
    private String userName;
    private Date lastAccess;



    public UserSession(String userName){
        this.userName = userName;
        sessionHandle = new Random().nextInt(100000);
        updateLastAccess();
    }

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void updateLastAccess(){
        lastAccess = new Date();
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionHandle=" + sessionHandle +
                ", userName='" + userName + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }
}
