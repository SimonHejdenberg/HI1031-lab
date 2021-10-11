package DataLayer;

import Enums.SecurityLevel;
import Logic.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jemsann
 */
public class UserDB extends Logic.User {

    private int UserID;

    public UserDB(int userID, String firstname, String lastname, String username, String password, SecurityLevel seclevel) {
        super(firstname, lastname, username, password, seclevel);
        this.UserID = userID;
    }

    public Connection con = null;

    public static int AddNewUser(User user) throws IOException, SQLException {
        String addUserString = "INSERT INTO t_users (firstname,lastname, username, hash, securitylevel) VALUES(?,?,?,?,?);";
        int userID = -1;
        try ( Connection con = DBManager.getConnection()) {
            try ( PreparedStatement addUserStmt = con.prepareStatement(addUserString)) {
                addUserStmt.setString(1, user.getFirstname());
                addUserStmt.setString(2, user.getLastname());
                addUserStmt.setString(3, user.getUsername());
                addUserStmt.setInt(4, user.hashCode());
                addUserStmt.setInt(5, SecurityLevel.Customer.ordinal());    //??????????

                userID = addUserStmt.executeUpdate();

            } catch (SQLException innerSqlEx) {
                System.out.println("SQL Add new user Exception");
                System.out.println(innerSqlEx.getMessage());
                System.out.println(innerSqlEx.getSQLState());
                System.out.println(innerSqlEx.getErrorCode());
            }

        } catch (SQLException outerSqlEx) {
            System.out.println("SQL Connection Exception");
            System.out.println(outerSqlEx.getMessage());
            System.out.println(outerSqlEx.getSQLState());
            System.out.println(outerSqlEx.getErrorCode());
        }
        return userID;
    }
    
    public static boolean editUser(User user, String newUsername, String newPassword, SecurityLevel newSecurityLevel) throws IOException, SQLException {
        if (newUsername == null && newPassword == null && newSecurityLevel == null) {
            return false;
        }
        try ( Connection con = DBManager.getConnection()) {
            
            StringBuilder sqlbBuilder = new StringBuilder();
            
            sqlbBuilder.append("UPDATE t_user SET ");
            int arrayCounter = 1;
            int usernameIndex = -1, passwordIndex = -1, securityIndex = -1;
            
            if (!(newUsername == null || user.getUsername().equals(newUsername))) {
                sqlbBuilder.append("username = ?");
                usernameIndex = arrayCounter;
                arrayCounter++;
                if (!(newPassword == null || newSecurityLevel == null || user.getSecLevel().equals(newSecurityLevel))) {
                    sqlbBuilder.append(", ");
                }
            }
            if (!(newPassword == null)) {
                sqlbBuilder.append("hash = ?");
                passwordIndex = arrayCounter;
                arrayCounter++;
                if (!(newSecurityLevel == null || user.getSecLevel().equals(newSecurityLevel))) {
                    sqlbBuilder.append(", ");
                }
            }
            if (!(newSecurityLevel == null || user.getSecLevel().equals(newSecurityLevel))) {
                sqlbBuilder.append("securitylevel = ?");
                securityIndex = arrayCounter;
                arrayCounter++;
            }
            
            sqlbBuilder.append(" WHERE userID = ?;");
            
            PreparedStatement stmt = con.prepareStatement(sqlbBuilder.toString());
            
            if (!(usernameIndex == -1)) {
                stmt.setString(usernameIndex, newUsername);
            }
            if (!(passwordIndex == -1)) {
                stmt.setInt(passwordIndex, newPassword.hashCode());
            }
            if (!(securityIndex == -1)) {
                stmt.setInt(arrayCounter, newSecurityLevel.ordinal());
            }
            stmt.setInt(arrayCounter, user.getUserID());
            
            return (stmt.executeUpdate() > 0);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean ValidateUser(String username, int password) {
        try ( Connection con = DBManager.getConnection()) {
            String sql = "SELECT * FROM t_users WHERE username = ?";
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getString("username").equals(username)) {
                        int retrievedHash = rs.getInt("hash");
                        if (retrievedHash == password) {
                            return true;
                        }
                    }

                }
                return false;
            } catch (SQLException innerSqlEx) {
                innerSqlEx.printStackTrace();
            }

        } catch (SQLException outerSqlEx) {
            System.out.println("SQL Connection Exception");
            System.out.println(outerSqlEx.getMessage());
            System.out.println(outerSqlEx.getSQLState());
            System.out.println(outerSqlEx.getErrorCode());
        }
        return false;
    }
}
