/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author simon
 */
public class Admin extends User{
    
    public Admin(int userID, String firstname, String lastname, String username, String password, Enum seclevel){
        super(userID, firstname, lastname, username, password, seclevel);
    }
    
    
}
