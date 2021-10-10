package UI;

import Enums.SecurityLevel;

/**
 *
 * @author jemsann
 */
public class UserInfo extends Logic.User {

    private String username;
    private String password;
    private int userId;
    private String firstname;
    private String lastname;
    private SecurityLevel level;

    public UserInfo(int userId, String firstname, String lastname, String username, SecurityLevel level) {
        super(userId, firstname, lastname, username, level);
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.level = level;
    }

    public UserInfo(String firstname, String lastname, String username, String password, SecurityLevel seclevel) {
        super(firstname, lastname, username, password, seclevel);
        this.username = username;
        this.password = password;
    }

    public UserInfo(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

}
