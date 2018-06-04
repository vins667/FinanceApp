/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.database;

import java.util.ResourceBundle;
import java.sql.*;

/**
 *
 * @author Vivek
 */
public class ConnectionBhane {
    ResourceBundle aResourcBundle = null;
  Connection con;
  
  public ConnectionBhane()
  {
    this.con = null;
    this.aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
  }
  
  private String getValue(String key)
  {
    return this.aResourcBundle.getString(key);
  }
  
  private int getIntValue(String key)
  {
    return Integer.parseInt(getValue(key));
  }
  
  public Connection getConnection()
  {
    try
    {
      Class.forName(getValue("driverBHANE"));
      this.con = DriverManager.getConnection(getValue("urlBHANE"), getValue("usernameBHANE"), getValue("passwordBHANE"));
    }
    catch (Exception ee)
    {
      System.out.println("Error in BHANE Connection : " + ee.getMessage());
    }
    return this.con;
  }
}
