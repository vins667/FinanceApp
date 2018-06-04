/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ResourceBundle;
import shahi.Action.database.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vivek
 */
public class PPS600MI extends UserBaseMI{

    public PPS600MI() {
       setProgram("PPS600MI");
    }

    public String PrintPO(String PUNO, String CPPL,String PRPO) {
        String status;
        int recFlag;
        String identity = "PrintPO";
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMddhhmmss");
        //Date date = new Date();
        String date = fromUser.format(new Date());
        System.out.println(date);
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("CPPL", CPPL);
        javaMI.mvxSetField("PRPO", PRPO);
        javaMI.mvxSetField("LITX", date+PUNO);
        recFlag = javaMI.mvxAccess("PrintPO");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status="NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }    
}
