package shahi.Action.Mis;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shahi.Action.Mis.Beans.ShahiInformationBean;
import shahi.Action.Mis.Beans.UnitBean;
import shahi.Action.database.ConnectionShahiHris;
import shahi.Action.database.connection;

public class ShahiInformationList
{
  private Connection con;
  private ResultSet rs;
  private String sqlScript;
  private ConnectionShahiHris connectionShahiHris = new ConnectionShahiHris();
  
  public List unitListBYLoct(String loct)
    throws SQLException
  {
    if ((loct != null) && (loct.length() > 0)) {
      loct = "and ppfaci='" + loct + "'";
    } else {
      loct = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select distinct unit from movex.mpwcdt where PPCONO='111' " + loct + "  and PROS_NAME='CUTTING' and pch is not null order by 1");
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(this.rs.getString(1));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List unitListBYLoctNew(String loct)
    throws SQLException
  {
    if ((loct != null) && (loct.length() > 0)) {
      loct = "and ppfaci='" + loct + "'";
    } else {
      loct = " ";
    }
    List list = new ArrayList();
    this.sqlScript = "SELECT CTSTKY, CTTX15 FROM PRODBI.CSYTAB WHERE CTCONO='111' AND CTSTCO='REAR'";
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new UnitBean(this.rs.getString("CTSTKY"), this.rs.getString("CTTX15")));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List LineListBYLoctandunit(String loct, String unit)
    throws SQLException
  {
    if ((loct != null) && (loct.length() > 0)) {
      loct = "and ppfaci='" + loct + "'";
    } else {
      loct = " ";
    }
    if ((unit != null) && (unit.length() > 0)) {
      unit = "and UNIT='" + unit + "'";
    } else {
      unit = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select distinct PPPLGR from movex.mpwcdt where PPCONO='111' " + loct + " " + unit + " and PROS_NAME='CUTTING' and pch is not null order by 1");
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(this.rs.getString(1));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List LineListBYLoctandunitNew(String loct, String unit)
    throws SQLException
  {
    if ((unit != null) && (unit.length() > 0)) {
      unit = "and PPREAR='" + unit + "'";
    } else {
      unit = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select distinct PPPLGR,PPPLNM from prodbi.mpdwct where PPCONO='111'" + unit + " and PPWCRF IN ('CUT','CCU') and PPPLTP=6 order by PPPLNM");
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new UnitBean(this.rs.getString("PPPLGR"), this.rs.getString("PPPLNM")));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List warehouseListBYLoct(String loct)
    throws SQLException
  {
    if ((loct != null) && (loct.length() > 0)) {
      loct = "and MWFACI='" + loct + "'";
    } else {
      loct = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select MWWHLO,MWWHNM||' - '||MWWHLO MWWHNM  from prodbi.mitwhl SQL1 where MWCONO='111' " + loct + " order by 1");
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new ShahiInformationBean(this.rs.getString(1), this.rs.getString(2)));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List locationListBYLoctandWhsl(String loct, String whsl)
    throws SQLException
  {
    if ((loct != null) && (loct.length() > 0)) {
      loct = "and mswhlo='" + loct + "'";
    } else {
      loct = " ";
    }
    if ((whsl != null) && (whsl.length() > 0)) {
      whsl = "and mswhsl like '" + whsl.toUpperCase() + "%'";
    } else {
      whsl = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select distinct mswhsl,mswhsl||' - '||mssltp msltp,mssltp,MSSLDS,mswhlo from prodbi.mitpce where mscono='111'   " + loct + " " + whsl + " order by 1,2");
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new ShahiInformationBean(this.rs.getString("mswhlo"), this.rs.getString("mswhsl"), this.rs.getString("msltp"), this.rs.getString("mssltp"), this.rs.getString("MSSLDS")));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List loctlistforput(String loct, String whsl)
    throws SQLException
  {
    if ((loct != null) && (loct.length() > 0)) {
      loct = "and mswhlo='" + loct + "'";
    } else {
      loct = " ";
    }
    if ((whsl != null) && (whsl.length() > 0)) {
      whsl = "and mswhsl like '" + whsl.toUpperCase() + "%'";
    } else {
      whsl = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select distinct mswhsl,mswhsl||' - '||mssltp msltp,mssltp,MSSLDS,mswhlo from prodbi.mitpce where mscono='111' and msdest='2'  " + loct + " " + whsl + " order by 1,2");
    System.out.println();
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new ShahiInformationBean(this.rs.getString("mswhlo"), this.rs.getString("mswhsl"), this.rs.getString("msltp"), this.rs.getString("mssltp"), this.rs.getString("MSSLDS")));
      }
    }
    catch (SQLException se)
    {
      System.out.println(se.toString());
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List departmentListBYcode(String deptcode)
    throws SQLException
  {
    if ((deptcode != null) && (deptcode.length() > 0)) {
      deptcode = "and DEPT_CODE='" + deptcode + "'";
    } else {
      deptcode = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select  DEPT_CODE,DEPT_DESC||' - '||DEPT_CODE from HRDEPT where FLAG='A' " + deptcode + " order by 2");
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new ShahiInformationBean(this.rs.getString(1), this.rs.getString(2)));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List subdepartmentListBYcodeandsubcode(String deptcode, String subdeptcode)
    throws SQLException
  {
    if ((deptcode != null) && (deptcode.length() > 0)) {
      deptcode = "and DEPT_CODE='" + deptcode + "'";
    } else {
      deptcode = " ";
    }
    if ((subdeptcode != null) && (subdeptcode.length() > 0)) {
      subdeptcode = "and SUB_DEPT_CODE='" + subdeptcode + "'";
    } else {
      subdeptcode = " ";
    }
    List list = new ArrayList();
    this.sqlScript = ("select  SUB_DEPT_CODE,SUB_DEPT_DESC||' - '||SUB_DEPT_CODE  from HRSUBDEPT where FLAG='A' " + deptcode + " " + subdeptcode + " order by 2");
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new ShahiInformationBean(this.rs.getString(1), this.rs.getString(2)));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List facilityList()
    throws SQLException
  {
    List list = new ArrayList();
    this.sqlScript = "select distinct cffaci,cffacn||' ( '||cffaci||' )' cffacn from prodbi.cfacil where cfcono='111' order by 1";
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new ShahiInformationBean(this.rs.getString(1), this.rs.getString(2)));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List pchList()
    throws SQLException
  {
    List list = new ArrayList();
    this.sqlScript = "select distinct PCH from pch_master order by 1";
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(this.rs.getString(1));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List pchListCRM()
    throws SQLException
  {
    List list = new ArrayList();
    this.sqlScript = "SELECT CTSTKY FROM PRODBI.CSYTAB WHERE CTCONO='111' AND CTSTCO='BUAR' ORDER by 1";
    try
    {
      this.con = new ConnectionShahiHris().getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(this.rs.getString(1));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public String pch1ListCRMbyPCH(String PCH)
    throws SQLException
  {
    String list = null;
    this.sqlScript = ("select PCH1 from pch_master where PCH='" + PCH + "' and FLAG='A' order by 1");
    try
    {
      this.con = new connection().getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        if (list == null) {
          list = "'" + this.rs.getString(1) + "'";
        } else {
          list = list + ",'" + this.rs.getString(1) + "'";
        }
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List buyerList()
    throws SQLException
  {
    List list = new ArrayList();
    this.sqlScript = "select  okcusu,okcunm from prodbi.ocusma where OKCONO='111' and  okstat='20' and okcusu is not null order by 2";
    try
    {
      this.con = this.connectionShahiHris.getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(new ShahiInformationBean(this.rs.getString(1), this.rs.getString(2)));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
  
  public List pchListWithBill()
    throws SQLException
  {
    List list = new ArrayList();
    this.sqlScript = "select  PCH from bill_pch_master where  BILL_FLAG='A' order by 1";
    try
    {
      this.con = new connection().getConnection();
      
      Statement stmt = this.con.createStatement();
      this.rs = stmt.executeQuery(this.sqlScript);
      while (this.rs.next()) {
        list.add(this.rs.getString(1));
      }
    }
    catch (SQLException se) {}catch (Exception e)
    {
      System.out.println(e.toString());
    }
    finally
    {
      this.con.close();
      this.rs.close();
    }
    return list;
  }
}
