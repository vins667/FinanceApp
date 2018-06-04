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
 * @author Shilpa
 */
public class ConnectionShahihrisOld {
    Connection con=null;
    ResourceBundle aResourcBundle=null;
    
    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
    
     public ConnectionShahihrisOld()
     {
        con = null;
             aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
     }
     
      public Connection getConnection() {
        try {
//            Class.forName(getValue("driverShahiHris"));
//            con = DriverManager.getConnection(getValue("URLShahiHrisOld"), getValue("UserNameShahiHrisOld"), getValue("PasswordShahiHrisOld"));

            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.3.46:1522:shahinew", "shahihris", "shahihris");
        } catch (Exception ee) {
            System.out.println("Error in shahi Connection : " + ee.getMessage());
        }
        return con;
    }
    
}
