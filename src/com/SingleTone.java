package com;

public class SingleTone {
    private static SingleTone instance;
    private String login;
    private int accessLevel;
    private SingleTone(String login){
        this.login=login;
    }
    public static SingleTone getInstance(String login){
        if(instance==null){
            instance=new SingleTone(login);
        }
        return instance;
    }
    public String getLogin(){
        return login;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setLogin(String login){
        this.login=login;
    }

}
