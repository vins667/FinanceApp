/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.SMAMI;

import shahi.Action.MI.BaseSMAMI;
import java.util.ArrayList;
import java.util.List;
import shahi.Action.LstDelivery.beans.GetItmBasicBean;
import shahi.Action.Fabric.Beans.MaterialBean;
import shahi.Action.PcdEntry.Beans.MMS200MIGetStyleBasicBean;

/**
 *
 * @author Vivek
 */
public class MMS200MI extends BaseSMAMI {

    public MMS200MI() {
        setProgram("MMS200MI");
    }
    //vivek

    public List GetItmBasic(String ITNO) {
        int recFlag;
        String identity = "GetItmBasic";
        List getItmBasicList = new ArrayList();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("ITNO", ITNO);
        recFlag = javaMI.mvxAccess("GetItmBasic");
        if (recFlag > 0) {
            getItmBasicList.add(javaMI.mvxGetLastError());
        } else {

            getItmBasicList.add(new GetItmBasicBean(javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("ITDS"), javaMI.mvxGetField("FUDS")));
            javaMI.mvxAccess(null);


        }
        return getItmBasicList;
    }
    ////

    public int GetItmBasic(String CONO, String ITNO) {
        int recFlag;
        int dplace = 0;

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        recFlag = javaMI.mvxAccess("GetItmBasic");
        if (recFlag > 0) {
            dplace = 0;
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
        } else {

            String DCCD = javaMI.mvxGetField("DCCD");
            dplace = Integer.parseInt(DCCD);
            javaMI.mvxAccess(null);


        }
        return dplace;
    }

    public String[] GetItmBasic1(String ITNO) {
        int recFlag;
        String identity = "GetItmBasic";
        String par[] = new String[5];

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("GetItmBasic");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            par[3] = javaMI.mvxGetLastError();
        } else {
            par[0] = javaMI.mvxGetField("ITDS");
            if (javaMI.mvxGetField("CHCD").trim().equalsIgnoreCase("2")) {
                par[1] = "Y";
            } else {
                par[1] = "N";
            }
            par[2] = javaMI.mvxGetField("PRGP");
            par[3] = javaMI.mvxGetField("WAPC");
            par[4] = javaMI.mvxGetField("FUDS");


        }
        return par;
    }

    public MMS200MIGetStyleBasicBean GetStyleItmBasicViewDetail(String ITNO) {
        String identity = "GetStyleItmBasicViewDetail";
        MMS200MIGetStyleBasicBean viewBean = new MMS200MIGetStyleBasicBean();
        int recFlag;

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("GetItmBasic");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            viewBean.setITNO(javaMI.mvxGetLastError());
        } else {
            viewBean.setITCL(javaMI.mvxGetField("ITCL"));
            viewBean.setITGR(javaMI.mvxGetField("ITGR"));
            viewBean.setITDS(javaMI.mvxGetField("ITDS"));
            viewBean.setFUDS(javaMI.mvxGetField("FUDS"));
            viewBean.setITNO(javaMI.mvxGetField("ITNO"));
            viewBean.setITTY(javaMI.mvxGetField("ITTY"));
            viewBean.setCFI1(javaMI.mvxGetField("CFI1"));
            viewBean.setCFI2(javaMI.mvxGetField("CFI2"));
            viewBean.setCFI3(javaMI.mvxGetField("CFI3"));
            viewBean.setCFI4(javaMI.mvxGetField("CFI4"));
            viewBean.setCFI5(javaMI.mvxGetField("CFI5"));
            viewBean.setBUAR(javaMI.mvxGetField("BUAR"));
            viewBean.setRESP(javaMI.mvxGetField("RESP"));
            viewBean.setUNMS(javaMI.mvxGetField("UNMS"));
            viewBean.setPRGP(javaMI.mvxGetField("PRGP"));
            String MABU = javaMI.mvxGetField("MABU");

            if (Integer.parseInt(MABU) == 1) {
                viewBean.setMABU("1-Manufactured");
            } else {
                viewBean.setMABU("2-Purchased");
            }
            viewBean.setWAPC(javaMI.mvxGetField("WAPC"));

            viewBean.setFRE3(javaMI.mvxGetField("FRE3"));
            viewBean.setFRE4(javaMI.mvxGetField("FRE4"));


            javaMI.mvxClearFields();
            javaMI.mvxSetField("CONO", "777");
            javaMI.mvxSetField("ITNO", ITNO);
            recFlag = javaMI.mvxAccess("GetItmPrice");
            if (recFlag > 0) {
            } else {
                viewBean.setSAPR(javaMI.mvxGetField("SAPR"));
                viewBean.setCUCS(javaMI.mvxGetField("CUCS"));
            }
            javaMI.mvxClearFields();
            javaMI.mvxSetField("CONO", "777");
            javaMI.mvxSetField("ITNO", ITNO);
            javaMI.mvxSetField("WHLO", "200");
            recFlag = javaMI.mvxAccess("GetItmWhsBasic");
            if (recFlag > 0) {
            } else {
                viewBean.setLEA1(javaMI.mvxGetField("LEA1"));
                viewBean.setPLAN(javaMI.mvxGetField("RESP"));
                viewBean.setORTY(javaMI.mvxGetField("ORTY"));
            }
        }
        return viewBean;
    }

    public List<MaterialBean> LstItmByStyle(String ITNO) {
        String identity = "LstItmByStyle";
        int recFlag;
        String result = "";
        List<MaterialBean> items = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("LstItmByStyle");

        if (recFlag > 0) {
            result = javaMI.mvxGetLastError();
        } else {
            items = new ArrayList<MaterialBean>();
            while (javaMI.mvxMore()) {

                items.add(new MaterialBean(javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("ITDS"),
                        javaMI.mvxGetField("CHCD"), javaMI.mvxGetField("UNMS"), javaMI.mvxGetField("BUAR"),
                        javaMI.mvxGetField("ITTY"), javaMI.mvxGetField("PRGP"), javaMI.mvxGetField("STAT"), javaMI.mvxGetField("WAPC"), javaMI.mvxGetField("FUDS"), javaMI.mvxGetField("STGS"), ""));
                javaMI.mvxAccess(null);
            }
        }
        return items;
    }

    public String AddFreeField(String ITNO, String HDPR, String CF11, String USID) {
        String identity = "AddFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField("CFD3", CF11);
        javaMI.mvxSetField("USID", USID);
        recFlag = javaMI.mvxAccess("AddFreeField");
        String status;
        if (recFlag > 0) {
            // CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String UpdateFreeField(String ITNO, String HDPR, String CF12, String USID, String CF) {
        String identity = "UpdateFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField(CF, CF12);
        javaMI.mvxSetField("USID", USID);
        recFlag = javaMI.mvxAccess("UpdateFreeField");
        String status;
        if (recFlag > 0) {
            // CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String ListFreeFieldIndata(String ITNO, String HDPR) {
        String date = null;
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                date = javaMI.mvxGetField("CF11");

                if (date != null && date.length() > 0) {
                    date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                }

                javaMI.mvxAccess(null);


            }
        }

        return date;
    }

    public String ListFreeFieldIndata1(String ITNO, String HDPR) {
        String date = null;
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                date = javaMI.mvxGetField("CF12");

                if (date != null && date.length() > 0) {

                    date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);

                }

                javaMI.mvxAccess(null);


            }
        }

        return date;
    }

    public String ListFreeFieldInFirstSale(String ITNO, String HDPR) {
        String date = null;
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                date = javaMI.mvxGetField("CFI1");
                javaMI.mvxAccess(null);


            }
        }

        return date;
    }

    public String[] ListFreeFieldIndatastyle(String ITNO, String HDPR) {
        String par[] = new String[18];
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {

            par[2] = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                par[0] = javaMI.mvxGetField("CFI1");
                par[1] = javaMI.mvxGetField("CFI6");
                par[3] = javaMI.mvxGetField("CFI7");
                par[4] = javaMI.mvxGetField("CFI8");

                par[5] = javaMI.mvxGetField("CF11");
                par[6] = javaMI.mvxGetField("CFI9");
                par[7] = javaMI.mvxGetField("CF12");
                par[8] = javaMI.mvxGetField("CF13");
                par[9] = javaMI.mvxGetField("CF14");
                par[10] = javaMI.mvxGetField("CF15");
                par[11] = javaMI.mvxGetField("CFI3");
                par[12] = javaMI.mvxGetField("CF10");
                par[13] = javaMI.mvxGetField("CFI5");

                par[14] = javaMI.mvxGetField("CFD1");
                par[15] = javaMI.mvxGetField("CFS1");
                par[16] = javaMI.mvxGetField("CFS2");
                par[17] = javaMI.mvxGetField("CFS3");




                javaMI.mvxAccess(null);
            }
        }

        return par;
    }

    public String UpdateFreeField(String ITNO, String HDPR, String FABDATE, String FABFLOW1, String FABFLOW2, String FABRICIMP, String REMARKS1, String REMARKS2, String REMARKS3, String usrid, String CFD2) {
        String identity = "UpdateFreeField";
        int recFlag;

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField("CF13", FABDATE);
        javaMI.mvxSetField("CF14", FABFLOW1);
        javaMI.mvxSetField("CF15", FABFLOW2);
        javaMI.mvxSetField("CFI3", FABRICIMP);
        javaMI.mvxSetField("CFS1", REMARKS1);
        javaMI.mvxSetField("CFS2", REMARKS2);
        javaMI.mvxSetField("CFS3", REMARKS3);
        javaMI.mvxSetField("USID", usrid);
        javaMI.mvxSetField("CFD2", CFD2);
        recFlag = javaMI.mvxAccess("UpdateFreeField");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }
}
