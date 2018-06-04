/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM.tax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shahi.Action.ReportFolder.EPM.tax.bean.UtilBean;
import shahi.Action.database.ConnectionSeplWeb;

/**
 *
 * @author Vivek
 */
public class TAXUtil {
    Connection conn = null;

    public TAXUtil() {
        conn = new ConnectionSeplWeb().getConnection();
    }
    public List<UtilBean> getCompany(){
        List<UtilBean> beans = new ArrayList<UtilBean>();
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try{
            stat = conn.prepareStatement("SELECT DISTINCT M4CONO,'Shahi Exports Pvt Ltd-' || M4CONO M4CONODESC FROM M4_WHLO_MASTER WHERE M4GEOC IS NOT NULL AND GSTN IS NOT NULL");
            resultSet  = stat.executeQuery();
            while(resultSet.next()){
                beans.add(new UtilBean(resultSet.getString("M4CONO"),resultSet.getString("M4CONODESC")));
            }
        } catch(Exception e){
            System.out.println("TAXUtil.class"+e.getMessage());
        } finally{
            if(resultSet!=null) try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAXUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(stat!=null) try {
                stat.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAXUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return beans;
    }
    public List<UtilBean> getDivision(String COMPANY){
        List<UtilBean> beans = new ArrayList<UtilBean>();
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try{
            stat = conn.prepareStatement("SELECT DISTINCT M4DIVI,CCCONM FROM M4_WHLO_MASTER A,PRODBI.CMNDIV@AMS B WHERE A.M4CONO=B.CCCONO AND A.M4DIVI=B.CCDIVI AND M4GEOC IS NOT NULL AND GSTN IS NOT NULL AND A.M4CONO=?");
            stat.setString(1, COMPANY);
            resultSet  = stat.executeQuery();
            while(resultSet.next()){
                beans.add(new UtilBean(resultSet.getString("M4DIVI"),resultSet.getString("CCCONM")+"-"+resultSet.getString("M4DIVI")));
            }
        } catch(Exception e){
            System.out.println("TAXUtil.class"+e.getMessage());
        } finally{
            if(resultSet!=null) try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAXUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(stat!=null) try {
                stat.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAXUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return beans;
    } 
    public List<UtilBean> getGSTIN(String COMPANY,String DIVISION){
        List<UtilBean> beans = new ArrayList<UtilBean>();
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try{
            stat = conn.prepareStatement("SELECT DISTINCT GSTN,M4GEOC,M4STAT FROM M4_WHLO_MASTER WHERE M4CONO=? AND M4DIVI=? AND M4GEOC IS NOT NULL AND GSTN IS NOT NULL ORDER BY M4STAT");
            stat.setString(1, COMPANY);
            stat.setString(2, DIVISION);
            resultSet  = stat.executeQuery();
            while(resultSet.next()){
                beans.add(new UtilBean(resultSet.getString("M4GEOC"),resultSet.getString("GSTN")+"-"+resultSet.getString("M4STAT")));
            }
        } catch(Exception e){
            System.out.println("TAXUtil.class"+e.getMessage());
        } finally{
            if(resultSet!=null) try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAXUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(stat!=null) try {
                stat.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAXUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return beans;
    }
    public void close(){
        if(conn!=null) try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TAXUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
