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



public class connectionblr
{

    public connectionblr()
    {
        con = null;
    }

    public Connection getConnection()
    {
        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@200.200.200.16:1521:shahidb", "knits", "knits");
        }
        catch(Exception ee)
        {
            System.out.println("Error in shahi Connection : " + ee.getMessage());
        }
        return con;
    }

    Connection con;
}
