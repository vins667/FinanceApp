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
public class ConnectionShahiHrisNew {
    public ConnectionShahiHrisNew()
    {
        con = null;
             aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");

    }
   ResourceBundle aResourcBundle = null;
    private String getValue(String key) {
    return aResourcBundle.getString(key);
  }

  private int getIntValue(String key) {
    return Integer.parseInt(getValue(key));
  }
    public Connection getConnection()
    {
        try
        {
           Class.forName(getValue("drivershahihrisnew"));
           con = DriverManager.getConnection(getValue("URLSHAHIHRISNEW"), getValue("UserNameshahihrisnew"), getValue("Passwordshahihrisnew"));

            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.1.250:1521:shahi", "webtest", "webtestseh");
        }
        catch(Exception ee)
        {
            System.out.println("Error in shahi Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}
