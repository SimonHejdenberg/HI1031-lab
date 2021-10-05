/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author simon
 */
public class User {
    
    private int userID = -1;    //????????
    private String firstname, lastname, username, password;  // password plaintext?
    private Enum seclevel = null;   //kanske inte funkar med enums i sql. DOCK så har mysql en enumtyp https://dev.mysql.com/doc/refman/8.0/en/enum.html

    public User(int userID, String firstname, String lastname, String username, String password, Enum seclevel){
        this.userID = userID;
        this.firstname = firstname;
        this.firstname = lastname;
        this.firstname = username;
        this.firstname = password;
        this.seclevel = seclevel;
        
        //inga getters eller setters för psw eller seclevel
        
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
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
