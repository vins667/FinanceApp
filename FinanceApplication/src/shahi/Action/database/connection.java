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



public class connection
{
	ResourceBundle aResourcBundle = null;
	private Connection con=null;
    public connection()
    {
             aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");

    }
   
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
           Class.forName(getValue("driver"));
           con = DriverManager.getConnection(getValue("URL"), getValue("UserName"), getValue("Password"));

            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.1.250:1521:shahi", "webtest", "webtestitip1");
        }
        catch(Exception ee)
        {
            System.out.println("Error in shahi Connection : " + ee.getMessage());
        }
        return con;
    }

    
}
