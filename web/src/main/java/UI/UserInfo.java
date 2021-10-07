package UI;

/**
 *
 * @author jemsann
 */
public class UserInfo extends Logic.User {

    private String username;
    private String password;

    public UserInfo(String firstname, String lastname, String username, String password, Enum seclevel) {
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
