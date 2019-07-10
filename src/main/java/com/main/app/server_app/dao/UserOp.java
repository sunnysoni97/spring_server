package com.main.app.server_app.dao;

import java.sql.*;

import com.main.app.server_app.common.consoleLogger;
import com.main.app.server_app.configurations.JdbcConfig;
import com.main.app.server_app.models.User;

public class UserOp{
    
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String query = null;

    private void createConnection(){
        try{
            Class.forName(JdbcConfig.JdbcDriver);
            conn = DriverManager.getConnection(JdbcConfig.DbUrl, JdbcConfig.UserName, JdbcConfig.UserPass);
            stmt = conn.createStatement();
            consoleLogger.info("SQL Connection Created!");
        } catch(SQLException se){
            consoleLogger.errorMsg("Error while creating sql connection - sqlexception");
            se.printStackTrace();  
        } catch (Exception e){
            consoleLogger.errorMsg("Error while creating sql connection - forname exception");
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try{
            conn.close();
            consoleLogger.info("SQL Connection Closed!");
        } catch (SQLException se){
            consoleLogger.errorMsg("Error while closing sql connection - sqlexception");
            se.printStackTrace();
        }
    }

    private void insertUser(User user){
        query = "insert into users values ('" + user.getUserName() + "','" + user.getUserPass() + "');";
        try{
            stmt.executeUpdate(query);
        } catch (SQLException se){
            consoleLogger.errorMsg("Error while inserting user data - sqlexception");
        }
    }

    private Boolean checkDuplicateUser(User user){
        query = "select * from users where user_name = '" + user.getUserName() + "';";
        try{
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                consoleLogger.info("Duplicate Value Exists in db (users)!");
                return true;
            }
            else
            {   consoleLogger.info("users db is clean for insertion!");
                return false;
            }
        } catch(SQLException se){
            consoleLogger.errorMsg("Error while fetching user data - sqlexception");
            return true;
        }
    }

    public Boolean registerUser(User user){
        Boolean res = false;
        createConnection();
        if(!checkDuplicateUser(user))
        {
            insertUser(user);
            res = true;
            consoleLogger.info("User inserted into the db");
        }
        else{
            consoleLogger.info("User not inserted into the db");
        }
        closeConnection();
        return res;
    }
}