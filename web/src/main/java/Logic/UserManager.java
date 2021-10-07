package Logic;

import DataLayer.UserDB;
import UI.UserInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jemsann
 */
public class UserManager {

    public static boolean validateUser(UserInfo user) {
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

}
