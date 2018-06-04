/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;


public class ConnectionDB2HR 
{
    
     public ConnectionDB2HR()
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
            
           Class.forName(getValue("driverDB2HR"));
           con = DriverManager.getConnection(getValue("URLDB2HR"), getValue("UserNameDB2HR"), getValue("PasswordDB2HR"));
           
        }
        catch(Exception ee)
        {
            System.out.println("Error in shahi DB2 HRIS Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}