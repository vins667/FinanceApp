package shahi.Action.MI;

import java.io.PrintStream;

public class PMS070MI
        extends BaseMI {

    public PMS070MI() {
        setProgram("PMS070MI");
    }

    public String RptOperation(String FACI, String PRNO, String MFNO, String OPNO, String REND, String vmwosq, String RPDT) {
        String status = null;
        this.javaMI.mvxClearFields();
        this.javaMI.mvxSetField("CONO", "111");
        this.javaMI.mvxSetField("FACI", FACI);
        this.javaMI.mvxSetField("PRNO", PRNO);
        this.javaMI.mvxSetField("MFNO", MFNO);
        this.javaMI.mvxSetField("OPNO", OPNO);
        this.javaMI.mvxSetField("REND", REND);
        this.javaMI.mvxSetField("WOSQ", vmwosq);
        this.javaMI.mvxSetField("RPDT", RPDT);


        int recFlag = this.javaMI.mvxAccess("RptOperation");
        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from application server" + this.javaMI.mvxGetLastError());
        } else {
            status = "Yes";
        }
        return status;
    }
}
