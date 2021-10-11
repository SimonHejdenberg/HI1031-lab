package Logic;

import DataLayer.UserDB;
import Enums.SecurityLevel;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author simon
 */
public class User {

    private int userID = -1;    //????????
    private String firstname, lastname, username, password;  // password HASHning mot DB, dvs vi tar psw, kör hash funktin, skickar hash till DB?
    private SecurityLevel seclevel = null;   //kanske inte funkar med enums i sql. DOCK så har mysql en enumtyp https://dev.mysql.com/doc/refman/8.0/en/enum.html
    private int hashcode;
    Cart cart = null;

    public User(int userId, String firstname, String lastname, String username, SecurityLevel level) {
        this.userID = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.seclevel = level;
    }

    public User(String firstname, String lastname, String username, String password, SecurityLevel seclevel) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.seclevel = seclevel;
        this.hashcode = password.hashCode();
        //inga getters eller setters för psw eller seclevel
    }

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
        this.hashcode = password.hashCode();
    }

    public static Collection GetAllUsers() {
        return UserDB.GetAllUsers();
    }

    public int getHashcode() {
        
        return hashcode;
    }

    /*
    public static boolean validateUser(String username, String password, int hashInput) {
        User u = new User(username, password);
        int hash = password.hashCode();
        return true;
    }
     */
    /**
     * @return the userID
     */
    public int getUserID() {
        try {
            if (userID > -1) {
                return userID;
            }
        } catch (Exception e) { //throw exception?
            e.printStackTrace();
        }
        return -1;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public Cart getCart() {
        if (cart == null) {
            cart = new Cart(this);
        }
        return cart;
    }

    public SecurityLevel getSecLevel() {
        return seclevel;
    }

    public void setSeclevel(SecurityLevel seclevel) {
        this.seclevel = seclevel;
    }

    // OBS INGEN GETTER FÖR PSW ELLER SECLEVEL
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
