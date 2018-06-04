/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MI;

/**
 *
 * @author Vivek
 */
public class GLS181MI  extends BaseMI{
    String identity=null;
    public GLS181MI() {
        setProgram("GLS181MI");
    }

    public String Reconcile(String CONO,String DIVI,String YEA4,String JRNO, String JSNO, String AIT1, String STMN)
    {
     identity = "Reconcile";
     int recFlag;
     javaMI.mvxClearFields();
     javaMI.mvxSetField("CONO","111");
     javaMI.mvxSetField("DIVI",DIVI);
     javaMI.mvxSetField("YEA4",YEA4);
     javaMI.mvxSetField("JRNO",JRNO);
     javaMI.mvxSetField("JSNO",JSNO);
     javaMI.mvxSetField("AIT1",AIT1);
     javaMI.mvxSetField("STMN",STMN);
     recFlag = javaMI.mvxAccess("Reconcile");
     String status="1";
     if (recFlag > 0) {
         status = javaMI.mvxGetLastError();
         System.out.println("UpdateChkNo " + status);
         //sendError("UpdateChkNo " + SUNO + status);
        }
        return status;
    }
}
