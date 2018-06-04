/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.MI.Beans.MWS420MILstPickHeaderBean;

/**
 *
 * @author User
 */
public class MWS420MI extends BaseMI {

    public MWS420MI() {
        setProgram("MWS420MI");
    }

    public List LstPickHeader(String DLIX) {
        int recFlag;
        List ls = new ArrayList();
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("DLIX", DLIX);//		Delivery number
        recFlag = javaMI.mvxAccess("LstPickHeader");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                MWS420MILstPickHeaderBean mws420miLstPickHeaderBean = new MWS420MILstPickHeaderBean();
                mws420miLstPickHeaderBean.setCONO(javaMI.mvxGetField("CONO"));   //Cono number
                mws420miLstPickHeaderBean.setDLIX(javaMI.mvxGetField("DLIX"));   //Delivery number
                mws420miLstPickHeaderBean.setPLSX(javaMI.mvxGetField("PLSX"));   //Picking list suffix	
                ls.add(mws420miLstPickHeaderBean);
                javaMI.mvxAccess(null);
            }
        }
        return ls;
    }

    public String Confirm(String DLIX, String PLSX) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("DLIX", DLIX);//		Delivery number
        javaMI.mvxSetField("PLSX", PLSX);//		//Picking list suffix	
        recFlag = javaMI.mvxAccess("Confirm");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String CorrectPicking(String CONO, String DLIX, String PLSX, String WHLO, String TTYP, String RIDN, String RIDL, String ITNO, String BANO, String WHSL, String TRQT, String CAMU, String RIDX) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", CONO);					//	Company
        javaMI.mvxSetField("DLIX", DLIX);					//	Delivery Number
        javaMI.mvxSetField("PLSX", PLSX);					//	Picking Suffix
        javaMI.mvxSetField("WHLO", WHLO);					//	Warehouse
        javaMI.mvxSetField("TTYP", TTYP);					//	Transaction Type
        javaMI.mvxSetField("RIDN", RIDN);					//	Order Number
        javaMI.mvxSetField("RIDL", RIDL);					//	Order Line
        javaMI.mvxSetField("ITNO", ITNO);					//	Item Number
        javaMI.mvxSetField("BANO", BANO);					//	Lot Number
        javaMI.mvxSetField("WHSL", WHSL);					//	Location
        javaMI.mvxSetField("TRQT", TRQT);					//	Correct Quantity
        javaMI.mvxSetField("CAMU", CAMU);					//	Container
        javaMI.mvxSetField("RIDX", RIDX);					//	Line Suffix
        recFlag = javaMI.mvxAccess("CorrectPicking");

        if (recFlag > 0) {

            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = "NO";
        } else {
            status = "Yes";
        }
        return status;
    }
}
