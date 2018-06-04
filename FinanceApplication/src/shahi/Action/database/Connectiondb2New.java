package shahi.Action.database;


import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;


public class Connectiondb2New
{

    public Connectiondb2New()
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

            Class.forName(getValue("driverDB2New"));
           con = DriverManager.getConnection(getValue("URLDDB2New"), getValue("UserNameDB2New"), getValue("PasswordDB2New"));
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

