package Logic;

import DataLayer.UserDB;
import Enums.SecurityLevel;
import UI.UserInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jemsann
 */
public class UserManager {

    public static ArrayList<UserInfo> users;

    public static User validateUser(UserInfo user) {
        return UserDB.ValidateUser(user.getUsername(), user.getHashcode());
    }

    public static int registerNewUser(User user) {
        int userID = -1;
        try {
            userID = UserDB.AddNewUser(user);
            user.setUserID(userID);
        } catch (IOException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userID;
    }

    public static Collection GetAllUsers() {
        Collection c = User.GetAllUsers();
        users = new ArrayList<>();
        for (Iterator it = c.iterator(); it.hasNext();) {
            User user = (User) it.next();
            users.add(new UserInfo(user.getUserID(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getSecLevel()));
        }
        return users;
    }

    public static UserInfo GetUser(int id) {
        if (users != null) {
            Iterator<UserInfo> it = users.iterator();
            while (it.hasNext()) {
                UserInfo u = it.next();
                if (u.getUserID() == id) {
                    return u;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public static boolean EditUser(User user, String username, String password, SecurityLevel level) {
        try {
            return UserDB.editUser(user, username, password, level);
            //return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
