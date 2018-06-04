/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MI;

import MvxAPI.MvxSockJ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import shahi.Action.database.connection;

/**
 *
 * @author Vivek
 */
public class UserBaseMI {
        protected MvxSockJ javaMI;
    public int rc;
    private String program;
    ResourceBundle aResourcBundle = null;
    public UserBaseMI() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    public int connect(String userid) throws Exception{
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet resultSet=null;
        try{
            javaMI = new MvxSockJ();
            conn= new connection().getConnection();
            stat = conn.prepareStatement("SELECT M3_USER,M3_PASSWORD FROM PO_APPROVAL_USERS WHERE EMP_CODE=?");
            stat.setString(1, userid.trim());
            resultSet = stat.executeQuery();
            if(resultSet.next()){
                rc = javaMI.mvxConnect(getValue("MIIP"), Integer.parseInt(getValue("MIPORT")), resultSet.getString("M3_USER"), resultSet.getString("M3_PASSWORD"), program, null);
                if (rc > 0) {
                    System.out.println("Failed to initiate " + javaMI.mvxGetLastError());
                }
            }
            else{
                System.out.println("You are not Authorised Person.");
            }
        }
        catch(Exception e){
            System.out.println("PPS600MI: "+e);
        }
        finally{
            if(conn!=null)
                conn.close();
            if(stat!=null)
                stat.close();
            if(resultSet!=null)
                resultSet.close();
        }
        return rc;
    }
    public void destroyMI() {
        javaMI.mvxClose();
        javaMI = null;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
