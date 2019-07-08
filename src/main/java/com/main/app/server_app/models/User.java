package com.main.app.server_app.models;

public class User{
    private String userName;
    private String userPass;
    
    public User(String userName, String userPass){
        this.userName = userName;
        this.userPass = userPass;
    }
    public User(){
        userName = "";
        userPass = "";
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserPass(String userPass){
        this.userPass = userPass;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserPass(){
        return userPass;
    }


}