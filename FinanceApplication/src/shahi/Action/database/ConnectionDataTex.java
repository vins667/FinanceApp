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



public class ConnectionDataTex
{

    public ConnectionDataTex()
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
            
           Class.forName(getValue("driverDATATEX"));
           con = DriverManager.getConnection(getValue("URLDATATEX"), getValue("UserNameDATATEX"), getValue("PasswordDATATEX"));
     }
        catch(Exception ee)
        {
            System.out.println("Error in DATATEX Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}
