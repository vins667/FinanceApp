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
 * @author Vivek
 */
public class ConnectionCrm {

    ResourceBundle aResourcBundle = null;
    Connection con;

    public ConnectionCrm() {
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
            Class.forName(getValue("driverCRM"));
            this.con = DriverManager.getConnection(getValue("urlCRM"), getValue("usernameCRM"), getValue("passwordCRM"));
        } catch (Exception ee) {
            System.out.println("Error in CRM Connection : " + ee.getMessage());
        }
        return this.con;
    }
}
