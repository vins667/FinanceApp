/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author RANJEET
 */
public class XPZ100MI extends BaseMI {

    public XPZ100MI() {
        setProgram("XPZ100MI");
    }

    public String InsertRecord(String CONO, String DIVI, String SUNO, String SINO, String YEA4, String INDX, String CUAM, String PRCT, String AIT1, String AIT2, String AIT3, String AIT4, String AIT5, String AIT6, String AIT7, String CSNO, String GEOT, String GEOF, String ZGST, String TAXC) {

        int recFlag;
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("SINO", SINO);
        javaMI.mvxSetField("YEA4", YEA4);
        javaMI.mvxSetField("INDX", INDX);
        javaMI.mvxSetField("CUAM", CUAM);
        javaMI.mvxSetField("PRCT", PRCT);
        javaMI.mvxSetField("AIT1", AIT1);
        javaMI.mvxSetField("AIT2", AIT2);
        javaMI.mvxSetField("AIT3", AIT3);
        javaMI.mvxSetField("AIT4", AIT4);
        javaMI.mvxSetField("AIT5", AIT5);
        javaMI.mvxSetField("AIT6", AIT6);
        javaMI.mvxSetField("AIT7", AIT7);
        javaMI.mvxSetField("CSNO", CSNO);
        javaMI.mvxSetField("GEOT", GEOT);
        javaMI.mvxSetField("GEOF", GEOF);
        javaMI.mvxSetField("ZGST", ZGST);
        javaMI.mvxSetField("TAXC", TAXC);


        recFlag = javaMI.mvxAccess("InsertRecord");
        String status = "OK";
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            status = "NOT-OK";

        }
        return status;
    }
}
