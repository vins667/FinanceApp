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



public class connectiondb2
{

    public connectiondb2()
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

            Class.forName(getValue("driverDB2"));
           con = DriverManager.getConnection(getValue("URLDDB2"), getValue("UserNameDB2"), getValue("PasswordDB2"));
            //con = DriverManager.getConnection("jdbc:as400://172.17.3.65", "MSRVADM", "Movex@550");
        }
        catch(Exception ee)
        {
            System.out.println("Error in shahihris Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}
