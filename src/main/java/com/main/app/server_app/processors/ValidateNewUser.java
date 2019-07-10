package com.main.app.server_app.processors;

import com.main.app.server_app.common.consoleLogger;
import com.main.app.server_app.models.User;

import org.apache.commons.lang3.mutable.MutableBoolean;;

public class ValidateNewUser{
    
    interface conditions{
        Boolean condition1();
        Boolean condition2();
    } 

    static public void checkData(MutableBoolean bool, User user)
    {  
        conditions c = new conditions(){
            public Boolean condition1(){  
                if(user.getUserPass().equals(user.getUserPassConfirm()))
                    return true;
                else
                    return false;
            };

            public Boolean condition2(){
                if(user.getUserPass().length() < 8)
                    return false;
                else
                    return true;
            }
        };
        if(c.condition1() && c.condition2())
        {
            consoleLogger.info("New User credentials validated true!");
            bool.setValue(true);
            return;
        }
        else
        {
            consoleLogger.info("New User credentials validated false!");
            bool.setValue(false);
            return;
        }
    }

}