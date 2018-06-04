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



public class connectionSpinova
{


    public connectionSpinova()
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
            

            Class.forName(getValue("SPINOVAdriver"));
            
           con = DriverManager.getConnection(getValue("SPINOVAURL"), getValue("SPINOVAUserName"), getValue("SPINOVAPassword"));
          
        }
        catch(Exception ee)
        {
            System.out.println("Error in connectionSpinova Connection : " + ee.toString());
        }
        return con;
    }

    Connection con;
}
