package shahi.Action.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class ConnectionLocal {
	ResourceBundle aResourcBundle = null;

    public ConnectionLocal()
    {
        con = null;
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
           Class.forName(getValue("driverLocal"));
           con = DriverManager.getConnection(getValue("URLLocal"), getValue("UserNameLocal"), getValue("PasswordLocal"));
        }
        catch(Exception ee)
        {
            System.out.println("Error in shahi Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}
