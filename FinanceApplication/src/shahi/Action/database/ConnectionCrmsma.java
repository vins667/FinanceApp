/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 *
 * @author Ranjeet
 */
public class ConnectionCrmsma {

    ResourceBundle aResourcBundle = null;
    Connection con;

    public ConnectionCrmsma() {
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
            Class.forName(getValue("driverCRMSMA"));
            this.con = DriverManager.getConnection(getValue("urlCRMSMA"), getValue("usernameCRMSMA"), getValue("passwordCRMSMA"));
        } catch (Exception ee) {
            System.out.println("Error in CRMSMA Connection : " + ee.getMessage());
        }
        return this.con;
    }
    
     
}
