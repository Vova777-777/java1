package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SessionManager777 {

    private List<UserSession> sessions = new ArrayList<>();
    private int sessionValid;

    UserSession userSessionNewDate;

    public SessionManager777(int sessionValid){
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession){
        sessions.add(userSession);
    }

    public UserSession find(String userName) throws NullPointerException{
        UserSession userSession = null;
        userSession = getUserSessionByAnyParameter(userName, userSession);
        return userSession;
    }

    public UserSession get(int sessionHandle) throws NullPointerException{
        UserSession userSession = null;
        userSession = getUserSessionByAnyParameter(sessionHandle, userSession);
        return userSession;
    }

    public <T> UserSession getUserSessionByAnyParameter (T t, UserSession userSession) throws NullPointerException{
        String s = "";
        if (sessions.isEmpty()) return null;
        for (int i = 0; i < sessions.size(); i++){
            if (t.getClass().equals(s.getClass())){
            if (t.equals(sessions.get(i).getUserName()) ) {userSession = sessions.get(i); break;}}
            else if (t.equals(sessions.get(i).getSessionHandle()) ) {userSession = sessions.get(i); break;}
        }
        if (!checkValid(userSession)) return null;
        userSession.updateLastAccess();
        return userSession;
    }

    public boolean checkValid(UserSession userSession) {
        Instant ins = Instant.now();
        if (userSession == null) return false; /* вэтом случае в методах откуда вызывется этот метод
                                            выбрасывается null, что и требует задание*/
        if (ins.equals(userSession.getLastAccess().toInstant().plusSeconds((long) sessionValid))) return true;
        if (ins.isBefore(userSession.getLastAccess().toInstant().plusSeconds((long) sessionValid))) return true;
        else return false;
    }

    public void delete(int sessionHandle) throws NullPointerException {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessionHandle == sessions.get(i).getSessionHandle()) { sessions.remove(i); return;}
        }
    }

    public void deleteExpired() {
        for (int i = 0; i < sessions.size(); i++) {
            if (!checkValid(sessions.get(i))) { sessions.remove(i); return;}
        }
    }

    public static void main(String[] args) throws InterruptedException, NullPointerException {
        // System.out.println(new SessionManager(10).find("asd"));
        UserSession userSession = new UserSession("asd");
        ru.progwards.java1.lessons.datetime.SessionManager sessionManager = new ru.progwards.java1.lessons.datetime.SessionManager(5);
        sessionManager.add(userSession);
        System.out.println(sessionManager.get(userSession.getSessionHandle()).toString());
        Thread.sleep(3000);
        System.out.println(sessionManager.get(userSession.getSessionHandle()).toString());

    }

    }

