package com.main.app.server_app.models;

public class formData{
    private String s;
    private String p;

    public formData(String p, String s){
        this.s = s;
        this.p = p;
    }

    public void setS(String s)
    {
        this.s = s;
    }
    
    public void setP(String p)
    {
        this.p = p;
    }

    public String getS()
    {
        return s;
    }

    public String getP()
    {
        return p;
    }

}