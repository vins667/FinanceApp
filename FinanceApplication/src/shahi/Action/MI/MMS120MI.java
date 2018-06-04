/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.MI.Beans.MMS120MILstAllocationBean;

/**
 * @author Vivek
 * @Category MMS100MI
 * @since 10/07/2011
 */
public class MMS120MI extends BaseMI {

    public MMS120MI() {
        setProgram("MMS120MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public String PerfDetAlloc(String Q0CONO, String Q0WHLO, String QOITNO, String Q0RIDI, String Q0ORCA, String Q0RIDN, String Q0RIDL, String Q0NWSL, String Q0NBNO, String Q0NQTY) {
        int recFlag;
        String identity = "PerfDetAlloc";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("Q0CONO", Q0CONO);
        javaMI.mvxSetField("Q0WHLO", Q0WHLO);
        javaMI.mvxSetField("QOITNO", QOITNO);
        javaMI.mvxSetField("Q0RIDI", Q0RIDI);
        javaMI.mvxSetField("Q0ORCA", Q0ORCA);
        javaMI.mvxSetField("Q0RIDN", Q0RIDN);
        javaMI.mvxSetField("Q0RIDL", Q0RIDL);
        javaMI.mvxSetField("Q0NWSL", Q0NWSL);
        javaMI.mvxSetField("Q0NBNO", Q0NBNO);
        javaMI.mvxSetField("Q0NQTY", Q0NQTY);


        recFlag = javaMI.mvxAccess("PerfDetAlloc");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            //status="NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }

    public String AllocateOrdLine(String TTYP, String RIDN, String RIDL, String RIDX, String ALQT, String WHSL,
            String BANO) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("TTYP", TTYP);//             Stock transaction type
        javaMI.mvxSetField("RIDN", RIDN);//		Order number
        javaMI.mvxSetField("RIDL", RIDL);//		Order line
        javaMI.mvxSetField("RIDX", RIDX);//		Line suffix
        javaMI.mvxSetField("MAAL", "M");//		Allocation origin
        javaMI.mvxSetField("ALQT", ALQT);//		Allocated quantity - basic U/M
        javaMI.mvxSetField("WHSL", WHSL);//		Location
        javaMI.mvxSetField("BANO", BANO);//		Lot Number

        recFlag = javaMI.mvxAccess("AllocateOrdLine");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
            status = status.replaceAll("line", "CO -");
            status = status + ", Please check line No -" + RIDL;
        } else {
            status = "Yes";
        }
        return status;
    }

    public String DeAllocateOrLne(String TTYP, String RIDN, String RIDL, String RIDX) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("TTYP", TTYP);//             Stock transaction type
        javaMI.mvxSetField("RIDN", RIDN);//		Order number
        javaMI.mvxSetField("RIDL", RIDL);//		Order line
        javaMI.mvxSetField("RIDX", RIDX);//		Line suffix
        javaMI.mvxSetField("MAAL", "M");//		Allocation origin
        recFlag = javaMI.mvxAccess("DeAllocateOrLne");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public List getLstAllocation(String WHLO, String ITNO, String TTYP, String RIDN, String RIDL) {
        List ls = new ArrayList();
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("TTYP", TTYP);
        javaMI.mvxSetField("RIDN", RIDN);
        javaMI.mvxSetField("RIDL", RIDL);

        recFlag = javaMI.mvxAccess("LstAllocation");
        if (recFlag > 0) {
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                MMS120MILstAllocationBean mms120miLstAllocationBean = new MMS120MILstAllocationBean();
                mms120miLstAllocationBean.setCONO(javaMI.mvxGetField("CONO"));   //Company
                mms120miLstAllocationBean.setWHLO(javaMI.mvxGetField("WHLO"));   //Warehouse
                mms120miLstAllocationBean.setITNO(javaMI.mvxGetField("ITNO"));   //Item Number
                mms120miLstAllocationBean.setWHSL(javaMI.mvxGetField("WHSL"));   //Location
                mms120miLstAllocationBean.setBANO(javaMI.mvxGetField("BANO"));   //Lot Number
                mms120miLstAllocationBean.setTTYP(javaMI.mvxGetField("TTYP"));   //Transaction Type
                mms120miLstAllocationBean.setRIDN(javaMI.mvxGetField("RIDN"));   //Order Number
                mms120miLstAllocationBean.setRIDL(javaMI.mvxGetField("RIDL"));   //Order Line
                mms120miLstAllocationBean.setRIDI(javaMI.mvxGetField("RIDI"));   //Delivery Number
                mms120miLstAllocationBean.setRIDX(javaMI.mvxGetField("RIDX"));   //Line Suffix
                mms120miLstAllocationBean.setPLSX(javaMI.mvxGetField("PLSX"));   //Picking List Suffix
                mms120miLstAllocationBean.setSTAT(javaMI.mvxGetField("STAT"));   //Status
                mms120miLstAllocationBean.setSLTP(javaMI.mvxGetField("SLTP"));   //Stock Zone
                mms120miLstAllocationBean.setALQT(javaMI.mvxGetField("ALQT"));   //Allocate Qty Basic U/M
                mms120miLstAllocationBean.setMAAL(javaMI.mvxGetField("MAAL"));   //Allocation Origin
                mms120miLstAllocationBean.setRFTX(javaMI.mvxGetField("RFTX"));   //Reference Tet
                mms120miLstAllocationBean.setTWSL(javaMI.mvxGetField("TWSL"));   //To Location
                mms120miLstAllocationBean.setTRQT(javaMI.mvxGetField("TRQT"));   //Transaction Qty
                mms120miLstAllocationBean.setPLRN(javaMI.mvxGetField("PLRN"));   //Reporting Number Pick Line

                ls.add(mms120miLstAllocationBean);
                javaMI.mvxAccess(null);
            }
        }

        return ls;
    }

    public String DeAllocateOrder(String TTYP, String RIDN) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("TTYP", TTYP);//             Stock transaction type
        javaMI.mvxSetField("RIDN", RIDN);//		Order number
        javaMI.mvxSetField("MAAL", "M");//		Allocation origin
        recFlag = javaMI.mvxAccess("DeAllocateOrder");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }
}
