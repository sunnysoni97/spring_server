package com.main.app.server_app.models;

public class EntryStatus{
    private int EntryCode;
    private String EntryMessage;

    public EntryStatus(int EntryCode){
        this.EntryCode = EntryCode;
        if (EntryCode == 0){
            EntryMessage = "";
        }
        else
        {
            EntryMessage = "Try registering with a different username!";
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