/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.database;

import java.sql.DriverManager;
import java.util.ResourceBundle;
import java.sql.Connection;

/**
 *
 * @author Vivek
 */
public class ConnectionShahiHris {
    Connection con;
    ResourceBundle aResourcBundle = null;

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
    public ConnectionShahiHris()
     {
        con = null;
             aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");

    }
    public Connection getConnection() {
        try {
            Class.forName(getValue("driverShahiHris"));
            con = DriverManager.getConnection(getValue("URLShahiHris"), getValue("UserNameShahiHris"), getValue("PasswordShahiHris"));

            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.1.250:1521:shahi", "webtest", "webtestseh");
        } catch (Exception ee) {
            System.out.println("Error in shahi Connection : " + ee.getMessage());
        }
        return con;
    }
}
