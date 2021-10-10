/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Enums.SecurityLevel;

/**
 *
 * @author simon
 */
public class Staff extends User{
    public Staff(String firstname, String lastname, String username, String password, SecurityLevel seclevel){
        super(firstname, lastname, username, password, seclevel);
    }
}
