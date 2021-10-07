package DataLayer;

/**
 *
 * @author jemsann
 */
public class UserDB extends Logic.User {
    public UserDB(int userID, String firstname, String lastname, String username, String password, Enum seclevel){
        super(0, firstname, lastname, username, password, seclevel);
    }
}
