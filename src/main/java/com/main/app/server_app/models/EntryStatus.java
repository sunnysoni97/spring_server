package com.main.app.server_app.models;

public class EntryStatus{
    private int EntryCode;
    private String EntryMessage;

    public EntryStatus(int EntryCode){
        this.EntryCode = EntryCode;
        if (EntryCode == 0){
            EntryMessage = "";
        }
        else if (EntryCode == 1)
        {
            EntryMessage = "Try registering with a different username!";
        }
        else if (EntryCode == 2){
            EntryMessage = "Password Entered is too short!";
        }
        else if (EntryCode == 3)
        {
            EntryMessage = "Entered Passwords do not match!";
        }
        else if (EntryCode == 4)
        {
            EntryMessage = "User Account Doesnt Exist!";
        }
        else if (EntryCode == 5)
        {
            EntryMessage = "Incorrect Password Entered!";
        }
        else
        {
            EntryMessage = "Unknown Entry Code!";
        }
    }

    public int getEntryCode(){
        return EntryCode;
    }

    public void setEntryCode(int EntryCode){
        this.EntryCode = EntryCode;
    }

    public String getEntryMessage(){
        return EntryMessage;
    }

    public void setEntryMessage(String EntryMessage){
        this.EntryMessage = EntryMessage;
    }
}