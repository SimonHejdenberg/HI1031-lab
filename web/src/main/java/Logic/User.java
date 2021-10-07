/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Objects;

/**
 *
 * @author simon
 */
public class User {

    private int userID = -1;    //????????
    private String firstname, lastname, username, password;  // password HASHning mot DB, dvs vi tar psw, kör hash funktin, skickar hash till DB?
    private Enum seclevel = null;   //kanske inte funkar med enums i sql. DOCK så har mysql en enumtyp https://dev.mysql.com/doc/refman/8.0/en/enum.html

    public User(int userID, String firstname, String lastname, String username, String password, Enum seclevel) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.seclevel = seclevel;

        //inga getters eller setters för psw eller seclevel
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return true;
    }
    
    

    public boolean validateUser(String username, String password) {
        return true;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        try {
            if (userID >= 0) {
                return userID;
            }
        } catch (Exception e) { //throw exception?
            e.printStackTrace();
        }
        return -1;
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

    // OBS INGEN GETTER FÖR PSW ELLER SECLEVEL
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
