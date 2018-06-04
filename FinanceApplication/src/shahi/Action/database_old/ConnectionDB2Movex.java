package shahi.Action.database;
/**
 *
 * @author Vivek
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
public class ConnectionDB2Movex {
ResourceBundle aResourcBundle = null;
  Connection con;

  public ConnectionDB2Movex()
  {
    this.con = null;
    this.aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
  }

  private String getValue(String key) {
    return this.aResourcBundle.getString(key);
  }

  private int getIntValue(String key) {
    return Integer.parseInt(getValue(key));
  }

  public Connection getConnection()
  {
    try
    {
      Class.forName(getValue("driverMov"));
      this.con = DriverManager.getConnection(getValue("URLMOV"), getValue("UserNameMov"), getValue("PasswordMov"));
    }
    catch (Exception ee)
    {
      System.out.println("Error in shahihris Connection : " + ee.getMessage());
    }
    return this.con;
  }
}
