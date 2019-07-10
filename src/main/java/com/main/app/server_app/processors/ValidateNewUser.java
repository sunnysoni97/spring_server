package com.main.app.server_app.processors;

import com.main.app.server_app.common.consoleLogger;
import com.main.app.server_app.models.User;

public class ValidateNewUser{
    
    interface conditions{
        Boolean condition1();
        Boolean condition2();
    } 
    private int flag;
    public int checkData(User user)
    {  
        conditions c = new conditions(){
            public Boolean condition1(){  
                if(user.getUserPass().equals(user.getUserPassConfirm()))
                    return true;
                else
                {    
                    flag=3;
                    return false;
                }
            };

            public Boolean condition2(){
                if(user.getUserPass().length() < 8)
                {
                    flag=2;
                    return false;
                }
                else
                    return true;
            }
        };
        if(c.condition2() && c.condition1() && flag==0)
        {
            consoleLogger.info("New User credentials validated true!");
        }
        else
        {
            consoleLogger.info("New User credentials validated false!");
        }
        
        return flag;
    }

}