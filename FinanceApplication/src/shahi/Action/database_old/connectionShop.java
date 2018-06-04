/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package  shahi.Action.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle; 

/**
 *
 * @author Ranjeet
 */
public class connectionShop { 

    ResourceBundle aResourcBundle = null;
    Connection con;

    public connectionShop() {
        this.con = null;
        this.aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }



    private String getValue(String key) {
        return this.aResourcBundle.getString(key);
    }



    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
    public Connection getConnection() {
        try {
            Class.forName(getValue("driverSHOP"));
            this.con = DriverManager.getConnection(getValue("urlSHOP"), getValue("usernameSHOP"), getValue("passwordSHOP"));
        } catch (Exception ee) {
            System.out.println("Error in BHANE SHOP Connection : " + ee.getMessage());
        }
        return this.con;
    }


}
