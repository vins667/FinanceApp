package shahi.Action.MI;

import java.io.PrintStream;

public class PMS050MI
        extends BaseMI {

    public PMS050MI() {
        setProgram("PMS050MI");
    }

    public String RptReceipt(String FACI, String PRNO, String MFNO, String RPDT, String TRDT, String RPQA, String STAS, String REND, String WHSL, String CHID) {
        String status = null;
        this.javaMI.mvxClearFields();
        this.javaMI.mvxSetField("CONO", "111");
        this.javaMI.mvxSetField("FACI", FACI);
        this.javaMI.mvxSetField("PRNO", PRNO);
        this.javaMI.mvxSetField("MFNO", MFNO);
        this.javaMI.mvxSetField("RPDT", RPDT);
        this.javaMI.mvxSetField("TRDT", TRDT);
        this.javaMI.mvxSetField("RPQA", RPQA);
        this.javaMI.mvxSetField("STAS", STAS);
        this.javaMI.mvxSetField("REND", REND);
        this.javaMI.mvxSetField("WHSL", WHSL);
        this.javaMI.mvxSetField("CHID", CHID);
        javaMI.mvxSetField("DSP1", "1");
        javaMI.mvxSetField("DSP2", "1");
        javaMI.mvxSetField("DSP3", "1");
        javaMI.mvxSetField("DSP4", "1");
        javaMI.mvxSetField("DSP5", "1");
        javaMI.mvxSetField("DSP6", "1");
        javaMI.mvxSetField("DSP7", "1");
        javaMI.mvxSetField("DSP8", "1");


        int recFlag = this.javaMI.mvxAccess("RptReceipt");
        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + this.javaMI.mvxGetLastError());
            status = this.javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String RptReceiptFab(String FACI, String PRNO, String MFNO, String RPDT, String TRDT, String RPQA, String WHSL, String BANO, String BREF, String BRE2, String STAS) {
        String status = null;
        this.javaMI.mvxClearFields();
        this.javaMI.mvxSetField("CONO", "111");
        this.javaMI.mvxSetField("FACI", FACI);
        this.javaMI.mvxSetField("PRNO", PRNO);
        this.javaMI.mvxSetField("MFNO", MFNO);
        this.javaMI.mvxSetField("RPDT", RPDT);
        this.javaMI.mvxSetField("TRDT", TRDT);
        this.javaMI.mvxSetField("RPQA", RPQA);
        this.javaMI.mvxSetField("WHSL", WHSL);
        this.javaMI.mvxSetField("BANO", BANO);
        this.javaMI.mvxSetField("BREF", BREF);
        this.javaMI.mvxSetField("BRE2", BRE2);
        this.javaMI.mvxSetField("STAS", STAS);
        this.javaMI.mvxSetField("REND", "0");
        javaMI.mvxSetField("DSP1", "1");
        javaMI.mvxSetField("DSP2", "1");
        javaMI.mvxSetField("DSP3", "1");
        javaMI.mvxSetField("DSP4", "1");
        javaMI.mvxSetField("DSP5", "1");
        javaMI.mvxSetField("DSP6", "1");
        javaMI.mvxSetField("DSP7", "1");
        javaMI.mvxSetField("DSP8", "1");
        int recFlag = this.javaMI.mvxAccess("RptReceipt");
        if (recFlag > 0) {
            status = this.javaMI.mvxGetLastError();
            System.out.println("Returned from applicationserver" + this.javaMI.mvxGetLastError());
            status = "NOK-S";
        } else {
            status = "OK-S";
        }
        return status;
    }

    public String RptIssueMo(String FACI, String PRNO, String MFNO, String OPNO, String REND, String vmwosq) {
        String status = null;
        this.javaMI.mvxClearFields();
        this.javaMI.mvxSetField("CONO", "111");
        this.javaMI.mvxSetField("FACI", FACI);
        this.javaMI.mvxSetField("PRNO", PRNO);
        this.javaMI.mvxSetField("MFNO", MFNO);
        this.javaMI.mvxSetField("REND", REND);
        javaMI.mvxSetField("DSP1", "1");
        javaMI.mvxSetField("DSP2", "1");
        javaMI.mvxSetField("DSP3", "1");
        javaMI.mvxSetField("DSP4", "1");
        javaMI.mvxSetField("DSP5", "1");
        javaMI.mvxSetField("DSP6", "1");
        javaMI.mvxSetField("DSP7", "1");
        javaMI.mvxSetField("DSP8", "1");



        int recFlag = this.javaMI.mvxAccess("RptReceipt");
        if (recFlag > 0) {
            status = "NO";
            System.out.println("ranjeet Returned from application server" + this.javaMI.mvxGetLastError());
        } else {
            status = "Yes";
        }
        return status;
    }

    public String RptReceiptWithLot(String FACI, String PRNO, String MFNO, String RPDT, String TRDT, String RPQA, String STAS, String REND, String WHSL, String CHID, String BANO) {
        String status = null;
        this.javaMI.mvxClearFields();
        this.javaMI.mvxSetField("CONO", "111");
        this.javaMI.mvxSetField("FACI", FACI);
        this.javaMI.mvxSetField("PRNO", PRNO);
        this.javaMI.mvxSetField("MFNO", MFNO);
        this.javaMI.mvxSetField("RPDT", RPDT);
        this.javaMI.mvxSetField("TRDT", TRDT);
        this.javaMI.mvxSetField("RPQA", RPQA);
        this.javaMI.mvxSetField("STAS", STAS);
        this.javaMI.mvxSetField("REND", REND);
        this.javaMI.mvxSetField("WHSL", WHSL);
        this.javaMI.mvxSetField("CHID", CHID);
        this.javaMI.mvxSetField("BANO", BANO);

        javaMI.mvxSetField("DSP1", "1");
        javaMI.mvxSetField("DSP2", "1");
        javaMI.mvxSetField("DSP3", "1");
        javaMI.mvxSetField("DSP4", "1");
        javaMI.mvxSetField("DSP5", "1");
        javaMI.mvxSetField("DSP6", "1");
        javaMI.mvxSetField("DSP7", "1");
        javaMI.mvxSetField("DSP8", "1");


        int recFlag = this.javaMI.mvxAccess("RptReceipt");
        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + this.javaMI.mvxGetLastError());
            status = this.javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }
}
