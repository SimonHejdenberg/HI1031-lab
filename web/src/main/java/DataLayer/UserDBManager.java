/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Logic.User;

/**
 *
 * @author simon
 */
public class UserDBManager {
    public Connection con = null;
    
    private PreparedStatement addNewUser(User user) throws IOException, SQLException{
        String addUserString = "INSERT INTO t_user (name, username, passwordhash, seclevel) VALUES(?,?,?,?);";
        PreparedStatement addUserRtnStmt = con.prepareStatement(addUserString, Statement.RETURN_GENERATED_KEYS);
        
        addUserRtnStmt.setString(1, user.getFirstname());
        addUserRtnStmt.setString(2, user.getUsername());
        addUserRtnStmt.setString(3, " "); //??????????
        addUserRtnStmt.setInt(4, 0);    //??????????
        return addUserRtnStmt;
    }
}
