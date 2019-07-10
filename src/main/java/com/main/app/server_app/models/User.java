package com.main.app.server_app.models;

public class User{
    private String userName;
    private String userPass;
    private String userPassConfirm;


    public User(String userName, String userPass){
        this.userName = userName;
        this.userPass = userPass;
        this.userPassConfirm = userPass;
    }
    public User(){
        userName = "";
        userPass = "";
        userPassConfirm= "";
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserPass(String userPass){
        this.userPass = userPass;
    }

    public void setUserPassConfirm(String userPassConfirm){
        this.userPassConfirm = userPassConfirm;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserPass(){
        return userPass;
    }

    public String getUserPassConfirm(){
        return userPassConfirm;
    }

}