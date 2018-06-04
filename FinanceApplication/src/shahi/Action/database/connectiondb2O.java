/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.database;

/**
 *
 * @author Ranjeet Gautam
 */
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;



public class connectiondb2O
{

    public connectiondb2O()
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
            //System.out.println(getValue("driverDB2")+getValue("UserNameDB2"));

            Class.forName(getValue("driverDB2O"));
           con = DriverManager.getConnection(getValue("urlDB2O"), getValue("usernameDB2O"), getValue("passwordDB2O"));
            //con = DriverManager.getConnection("jdbc:as400://172.17.3.105", "MSRVADM", "Movex@550");
        }
        catch(Exception ee)
        {
            System.out.println("Error in DB2 M3 Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}
