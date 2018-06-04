package shahi.Action.MI;

/**
 *
 * @author Ranjeet
 */
public class MHS850MI extends BaseMI {

    public MHS850MI() {
        setProgram("MHS850MI");
    }
    String identity = null;

    public String AddPOPutaway(String WHLO, String TRDT, String REPN, String PUNO, String LINO, String RPQA,
            String WHSL, String BANO) {
        identity = "AddPOPutaway";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("TRDT", TRDT);
        javaMI.mvxSetField("REPN", REPN);
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("LINO", LINO);
        javaMI.mvxSetField("RPQA", RPQA);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("BANO", BANO);



        javaMI.mvxSetField("RESP", "MSRVADM");
        recFlag = javaMI.mvxAccess("AddPOPutaway");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }

    public String AddPOPutawayG(String WHLO, String TRDT, String REPN, String PUNO, String LINO, String RPQA,
            String WHSL, String BANO, String SUNO) {
        identity = "AddPOPutaway";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("TRDT", TRDT);
        javaMI.mvxSetField("REPN", REPN);
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("LINO", LINO);
        javaMI.mvxSetField("RPQA", RPQA);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("BREM", SUNO);



        javaMI.mvxSetField("RESP", "MSRVADM");
        recFlag = javaMI.mvxAccess("AddPOPutaway");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }

    public String AddPOInspect(String WHLO, String SUNO, String RIDN, String RIDL, String ITNO, String WHSL,
            String QTY, String REPN, String ROLL_NO, String SHADELOT, String QCRA, String BANO, String SCRE) {
        identity = "AddPOInspect";
        int recFlag;

        if (SHADELOT != null && SHADELOT.length() > 12) {
            SHADELOT = SHADELOT.substring(0, 12);

        }

        javaMI.mvxClearFields();
        javaMI.mvxSetField("PRFL", "*EXE");
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("E0PA", "R100");
        javaMI.mvxSetField("E065", "DESADV");
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("SUTY", "0");
        javaMI.mvxSetField("RIDN", RIDN);
        javaMI.mvxSetField("RIDL", RIDL);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("QCRA", QCRA);
        javaMI.mvxSetField("QTY", QTY);
        javaMI.mvxSetField("REPN", REPN);

        javaMI.mvxSetField("BREF", ROLL_NO);
        javaMI.mvxSetField("BRE2", SHADELOT);
        javaMI.mvxSetField("RESP", "MSRVADM");
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("SCRE", SCRE);
        javaMI.mvxSetField("BREM", SUNO);

        recFlag = javaMI.mvxAccess("AddPOInspect");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK-I";
        } else {
            status = "OK-I";
        }
        return status;
    }

    public String AddPOInspect1(String WHLO, String SUNO, String RIDN, String RIDL, String ITNO, String WHSL,
            String QTY, String REPN, String ROLL_NO, String SHADELOT, String QCRA, String BANO, String SCRE) {
        identity = "AddPOInspect";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("PRFL", "*EXE");
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("E0PA", "R100");
        javaMI.mvxSetField("E065", "DESADV");
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("SUTY", "0");
        javaMI.mvxSetField("RIDN", RIDN);
        javaMI.mvxSetField("RIDL", RIDL);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("QCRA", QCRA);
        javaMI.mvxSetField("QTY", QTY);
        javaMI.mvxSetField("REPN", REPN);
        javaMI.mvxSetField("RESP", "MSRVADM");
        javaMI.mvxSetField("SCRE", SCRE);
        javaMI.mvxSetField("BREM", SUNO);

        recFlag = javaMI.mvxAccess("AddPOInspect");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK-I";
        } else {
            status = "OK-I";
        }
        return status;
    }

    public String AddDO(String MSGN, String PRMD, String WHLO, String GEDT, String ITNO, String WHS,
            String BANO, String ALQT, String DLQT, String RORN, String RORL, String CUNO) {
        identity = "AddDO";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PRMD", PRMD);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("MSGN", MSGN);
        javaMI.mvxSetField("PACN", "1");
        javaMI.mvxSetField("GEDT", GEDT);
        javaMI.mvxSetField("E0PA", "R100");
        javaMI.mvxSetField("E0PB", "R100");
        javaMI.mvxSetField("E065", "DESADV");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHS", WHS);
        javaMI.mvxSetField("TWSL", WHS);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("ALQT", ALQT);
        javaMI.mvxSetField("DLQT", DLQT);
        javaMI.mvxSetField("TRTP", "TR2");
        javaMI.mvxSetField("RESP", "MSRVADM");
        javaMI.mvxSetField("PMSN", MSGN);
        javaMI.mvxSetField("RORC", "3");
        javaMI.mvxSetField("RORN", RORN);
        javaMI.mvxSetField("RORL", RORL);
        javaMI.mvxSetField("CUNO", CUNO);

        recFlag = javaMI.mvxAccess("AddDO");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK";
        } else {
            status = "OK";
        }
        return status;
    }

    public String AddWhsLine(String MSGN, String WHLO, String FACI, String ITNO,
            String WHSL, String BANO, String ALQT, String DLQT, String RORN, String RORL, String CUNO) {
        identity = "AddWhsLine";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("MSGN", MSGN);
        javaMI.mvxSetField("PACN", "1");
        javaMI.mvxSetField("QLFR", "51CR");
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("ALQT", ALQT);
        javaMI.mvxSetField("DLQT", DLQT);
        javaMI.mvxSetField("TTYP", "51");
        javaMI.mvxSetField("RESP", "MSRVADM");
        javaMI.mvxSetField("TRTP", "TR2");
        javaMI.mvxSetField("TWSL", WHSL);
        javaMI.mvxSetField("RORC", "3");
        javaMI.mvxSetField("RORN", RORN);
        javaMI.mvxSetField("RORL", RORL);
        javaMI.mvxSetField("CUNO", CUNO);

        recFlag = javaMI.mvxAccess("AddWhsLine");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK";
        } else {
            status = "OK";
        }
        return status;
    }

    public String PrcWhsTran(String MSGN) {
        identity = "PrcWhsTran";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("MSGN", MSGN);
        javaMI.mvxSetField("PACN", "1");
        //javaMI.mvxSetField("MSLN", MSLN);
        javaMI.mvxSetField("PRFL", "*EXE");


        recFlag = javaMI.mvxAccess("PrcWhsTran");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK";
        } else {
            status = "OK";
        }
        return status;
    }
}
