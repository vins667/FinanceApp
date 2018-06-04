package shahi.Action.database;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;


public class ConnectionSeplWeb{
	
	public ConnectionSeplWeb()
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
           Class.forName(getValue("driversepl"));
           con = DriverManager.getConnection(getValue("URLSEPL"), getValue("UserNameSepl"), getValue("PasswordSepl"));
 
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@172.17.1.250:1521:shahi", "webtest", "webtestitip1");
        }
        catch(Exception ee)
        {
            System.out.println("Error in shahi Connection : " + ee.getMessage());
        }
        return con;
    }
 
    Connection con;
}



