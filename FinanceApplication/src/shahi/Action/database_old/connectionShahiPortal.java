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



public class connectionShahiPortal
{

    public connectionShahiPortal()
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
            
           Class.forName(getValue("driverShahiPortal"));
           con = DriverManager.getConnection(getValue("URLShahiPortal"), getValue("UserNameShahiPortal"), getValue("PasswordShahiPortal"));

             }
        catch(Exception ee)
        {
            System.out.println("Error in shahi portal Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}
