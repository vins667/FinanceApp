/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;

import shahi.Action.Beans.MMS200MIListFreeFieldIndata;
import shahi.Action.LstDelivery.beans.GetItmBasicBean;
import shahi.Action.Fabric.Beans.MaterialBean;
import shahi.Action.HelperConstantsFnl;
import shahi.Action.MI.Beans.MMS200MIGetBean;
import shahi.Action.MI.Beans.MMS200MILstItmByItmBean;
import shahi.Action.MI.Beans.MMS200miGetItmFacBean;
import shahi.Action.PcdEntry.Beans.MMS200MIGetStyleBasicBean;
import shahi.Action.UnitEmbForecast.Beans.MMS200MIGetEmbBasicBean;

/**
 *
 * @author Ranjeet
 */
public class MMS200MI extends BaseMI {

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

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public String getStatus(String ITNO) {
        int recFlag;
        //identity = "isStatus90";
        String isStatus = "";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("GetItmBasic");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            isStatus = javaMI.mvxGetField("STAT");

        }
        return isStatus;
    }

    public int GetItmBasic(String CONO, String ITNO) {
        int recFlag;
        int dplace = 0;

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
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
        javaMI.mvxSetField("CONO", "111");
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
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("GetItmBasic");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);

            viewBean.setITNO("NOK");
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
            // viewBean.setDCCD(javaMI.mvxGetField("DCCD"));

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
            javaMI.mvxSetField("CONO", "111");
            javaMI.mvxSetField("ITNO", ITNO);
            recFlag = javaMI.mvxAccess("GetItmPrice");
            if (recFlag > 0) {
            } else {
                viewBean.setSAPR(javaMI.mvxGetField("SAPR"));
                viewBean.setCUCS(javaMI.mvxGetField("CUCS"));
            }
            javaMI.mvxClearFields();
            javaMI.mvxSetField("CONO", "111");
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

    public MMS200MIGetEmbBasicBean GetEmbBasicViewDetail(String ITNO) {
        String identity = "GetStyleItmBasicViewDetail";
        MMS200MIGetEmbBasicBean viewBean = new MMS200MIGetEmbBasicBean();
        int recFlag;

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("GetItmBasic");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);

            viewBean.setITNO("NOK");
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
            javaMI.mvxSetField("CONO", "111");
            javaMI.mvxSetField("ITNO", ITNO);
            recFlag = javaMI.mvxAccess("GetItmPrice");
            if (recFlag > 0) {
            } else {
                viewBean.setSAPR(javaMI.mvxGetField("SAPR"));
                viewBean.setCUCS(javaMI.mvxGetField("CUCS"));
            }
            javaMI.mvxClearFields();
            javaMI.mvxSetField("CONO", "111");
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
        javaMI.mvxSetField("CONO", "111");
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
        javaMI.mvxSetField("CONO", "111");
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
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField(CF, CF12);
        //javaMI.mvxSetField("USID", USID);
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

    public String UpdateFreeFieldFlow(String ITNO, String HDPR, String CF12, String CF, String FABFLOW1, String FABFLOW2, String FABFLOW3, String FABFLOW4, String FABFLOW5, String FABFLOW6, String FILERECEIPTDATE) {
        String identity = "UpdateFreeField";
        int recFlag;


        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField(CF, CF12);

        javaMI.mvxSetField("CFI2", FABFLOW1);
        javaMI.mvxSetField("CFS1", FABFLOW2);
        javaMI.mvxSetField("CFS2", FABFLOW3);
        javaMI.mvxSetField("CFS3", FABFLOW4);
        javaMI.mvxSetField("CFS4", FABFLOW5);
        javaMI.mvxSetField("CFS5", FABFLOW6);
        javaMI.mvxSetField("CF13", FILERECEIPTDATE);
        recFlag = javaMI.mvxAccess("UpdateFreeField");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String UpdateFreeField(String ITNO, String HDPR, String CF12, String USID, String CF, String CFN2) {
        String identity = "UpdateFreeField";
        int recFlag;
        if (CFN2 == null) {
            CFN2 = "0";
        }
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField(CF, CF12);
        javaMI.mvxSetField("CFN2", CFN2);
        //javaMI.mvxSetField("USID", USID);
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
        javaMI.mvxSetField("CONO", "111");
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
        javaMI.mvxSetField("CONO", "111");
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
        javaMI.mvxSetField("CONO", "111");
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
        String par[] = new String[25];
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
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


                par[18] = (int) (Double.parseDouble(javaMI.mvxGetField("CFN2"))) + "";
                par[19] = javaMI.mvxGetField("CFI4"); //fabric
                par[20] = javaMI.mvxGetField("CFI5"); //approver

                par[21] = (int) (Double.parseDouble(javaMI.mvxGetField("CFN4"))) + "";

                par[22] = javaMI.mvxGetField("CFI2"); //date
                par[23] = javaMI.mvxGetField("CFS4");
                par[24] = javaMI.mvxGetField("CFS5"); //date




                javaMI.mvxAccess(null);
            }
        }

        return par;
    }

    public String[] ListFreeFieldIndataNew(String ITNO, String HDPR) {
        String par[] = new String[21];
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {

            // CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
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
                par[18] = javaMI.mvxGetField("CFI4");
                par[19] = javaMI.mvxGetField("CFD4");
                par[20] = javaMI.mvxGetField("CFN1");




                javaMI.mvxAccess(null);
            }
        }

        return par;
    }

    public MMS200MIListFreeFieldIndata ListFreeFieldIndataAll(String ITNO, String HDPR) {
        MMS200MIListFreeFieldIndata mms200miListFreeFieldIndata = new MMS200MIListFreeFieldIndata();
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            // CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            //par[2] = javaMI.mvxGetLastError();   
        } else {
            while (javaMI.mvxMore()) {
                mms200miListFreeFieldIndata.setCONO(javaMI.mvxGetField("CONO"));
                mms200miListFreeFieldIndata.setITNO(javaMI.mvxGetField("ITNO"));
                mms200miListFreeFieldIndata.setHDPR(javaMI.mvxGetField("HDPR"));
                mms200miListFreeFieldIndata.setCHCD(javaMI.mvxGetField("CHCD"));
                mms200miListFreeFieldIndata.setCFI1(javaMI.mvxGetField("CFI1"));
                mms200miListFreeFieldIndata.setCFI2(javaMI.mvxGetField("CFI2"));
                mms200miListFreeFieldIndata.setCFI3(javaMI.mvxGetField("CFI3"));
                mms200miListFreeFieldIndata.setCFI4(javaMI.mvxGetField("CFI4"));
                mms200miListFreeFieldIndata.setCFI5(javaMI.mvxGetField("CFI5"));
                mms200miListFreeFieldIndata.setCFI6(javaMI.mvxGetField("CFI6"));
                mms200miListFreeFieldIndata.setCFI7(javaMI.mvxGetField("CFI7"));
                mms200miListFreeFieldIndata.setCFI8(javaMI.mvxGetField("CFI8"));
                mms200miListFreeFieldIndata.setCFI9(javaMI.mvxGetField("CFI9"));
                mms200miListFreeFieldIndata.setCF10(javaMI.mvxGetField("CF10"));
                mms200miListFreeFieldIndata.setCF11(javaMI.mvxGetField("CF11"));
                mms200miListFreeFieldIndata.setCF12(javaMI.mvxGetField("CF12"));
                mms200miListFreeFieldIndata.setCF13(javaMI.mvxGetField("CF13"));
                mms200miListFreeFieldIndata.setCF14(javaMI.mvxGetField("CF14"));
                mms200miListFreeFieldIndata.setCF15(javaMI.mvxGetField("CF15"));
                mms200miListFreeFieldIndata.setUSID(javaMI.mvxGetField("USID"));
                mms200miListFreeFieldIndata.setCFS1(javaMI.mvxGetField("CFS1"));
                mms200miListFreeFieldIndata.setCFS2(javaMI.mvxGetField("CFS2"));
                mms200miListFreeFieldIndata.setCFS3(javaMI.mvxGetField("CFS3"));
                mms200miListFreeFieldIndata.setCFS4(javaMI.mvxGetField("CFS4"));
                mms200miListFreeFieldIndata.setCFS5(javaMI.mvxGetField("CFS5"));
                mms200miListFreeFieldIndata.setCFN1(javaMI.mvxGetField("CFN1"));
                mms200miListFreeFieldIndata.setCFN2(javaMI.mvxGetField("CFN2"));
                mms200miListFreeFieldIndata.setCFN3(javaMI.mvxGetField("CFN3"));
                // mms200miListFreeFieldIndata.setCFN4(javaMI.mvxGetField("CFN4"));
                mms200miListFreeFieldIndata.setCFN4((int) (Double.parseDouble(javaMI.mvxGetField("CFN4"))) + "");
                mms200miListFreeFieldIndata.setCFN5(javaMI.mvxGetField("CFN5"));
                mms200miListFreeFieldIndata.setCFD1(javaMI.mvxGetField("CFD1"));
                mms200miListFreeFieldIndata.setCFD2(javaMI.mvxGetField("CFD2"));
                mms200miListFreeFieldIndata.setCFD3(javaMI.mvxGetField("CFD3"));
                mms200miListFreeFieldIndata.setCFD4(javaMI.mvxGetField("CFD4"));
                mms200miListFreeFieldIndata.setCFD5(javaMI.mvxGetField("CFD5"));
                javaMI.mvxAccess(null);
            }
        }

        return mms200miListFreeFieldIndata;
    }

    public String UpdateFreeField(String ITNO, String HDPR, String FABDATE, String FABFLOW1, String FABFLOW2, String FABRICIMP, String REMARKS1, String REMARKS2, String REMARKS3, String usrid, String CFD2) {
        String identity = "UpdateFreeField";
        int recFlag;

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField("CF13", FABDATE);
        javaMI.mvxSetField("CF14", FABFLOW1);
        javaMI.mvxSetField("CF15", FABFLOW2);
        javaMI.mvxSetField("CFI3", FABRICIMP);
        javaMI.mvxSetField("CFS1", REMARKS1);
        javaMI.mvxSetField("CFS2", REMARKS2);
        javaMI.mvxSetField("CFS3", REMARKS3);
        //javaMI.mvxSetField("USID", usrid);
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

    public String[] getItemHdpr(String ITNO) {
        int recFlag;
        //identity = "isStatus90";
        String isStatus = "";
        String par[] = new String[3];
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("GetItmBasic");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {

            isStatus = javaMI.mvxGetField("HDPR");
            if (isStatus != null && isStatus.length() == 0) {
                isStatus = null;
            }
            par[0] = isStatus;
            par[1] = javaMI.mvxGetField("PRGP");
            par[2] = javaMI.mvxGetField("ITTY");


        }
        return par;
    }

    public String UpdateFreePD(String ITNO, String HDPR, String CFN3) {
        String identity = "UpdateFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("HDPR", HDPR);
        javaMI.mvxSetField("CFN3", CFN3); //PD merchant
        //javaMI.mvxSetField("CFN4", CFN3);   //PM merchant

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

    public String updateItmwarehouse(String ITNO, String WHLO) {
        String identity = "updItmWhs";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("CONC", "0");
        recFlag = javaMI.mvxAccess("UpdItmWhs");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String updateItmBasic(String ITNO) {
        String identity = "UpdItmBasic";
        int recflag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("STAT", "80");
        recflag = javaMI.mvxAccess("UpdItmBasic");
        String status;
        if (recflag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public List<MaterialBean> LstvarByStyle(String ITNO) {
        String identity = "LstItmVarient";
        int recFlag;
        String result = "";
        List<MaterialBean> items = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FSTY", ITNO);
        javaMI.mvxSetField("TSTY", ITNO);

        recFlag = javaMI.mvxAccess("LstItmByItm");

        if (recFlag > 0) {
            result = javaMI.mvxGetLastError();
        } else {
            items = new ArrayList<MaterialBean>();
            while (javaMI.mvxMore()) {
                if (javaMI.mvxGetField("HDPR") != null && javaMI.mvxGetField("HDPR").trim().length() > 0) {
                    items.add(new MaterialBean(javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("ITDS"),
                            javaMI.mvxGetField("CHCD"), javaMI.mvxGetField("UNMS"), javaMI.mvxGetField("BUAR"),
                            javaMI.mvxGetField("ITTY"), javaMI.mvxGetField("PRGP"), javaMI.mvxGetField("STAT"), javaMI.mvxGetField("WAPC"),
                            javaMI.mvxGetField("FUDS"), javaMI.mvxGetField("STGS"), javaMI.mvxGetField("SALE")));
                }
                javaMI.mvxAccess(null);

            }
        }
        return items;
    }

    public List<MaterialBean> LstItmByItem1(String ITNO) {
        String identity = "LstItmByStyle";
        int recFlag;
        String result = "";
        List<MaterialBean> items = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("LstItmByItm");

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

    public List<MaterialBean> LstItmByItem(String ITNO) {
        String identity = "LstItmByStyle";
        int recFlag;
        String result = "";
        List<MaterialBean> items = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("CHCD", "3");
        javaMI.mvxSetField("FSTY", ITNO);
        javaMI.mvxSetField("TSTY", ITNO + "ZZZZZZZZZZZ");

        recFlag = javaMI.mvxAccess("LstItmByItm");

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

    public String[] ListFreeFieldIn(String ITNO, String HDPR) {
        String par[] = new String[25];
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
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
                par[11] = javaMI.mvxGetField("CFI3"); // for item image
                par[12] = javaMI.mvxGetField("CF10");
                par[13] = javaMI.mvxGetField("CFI5");

                par[14] = javaMI.mvxGetField("CFD1");
                par[15] = javaMI.mvxGetField("CFS1");
                par[16] = javaMI.mvxGetField("CFS2");
                par[17] = javaMI.mvxGetField("CFS3");
                par[18] = javaMI.mvxGetField("CFI4");
                par[19] = javaMI.mvxGetField("CFD4");
                par[20] = javaMI.mvxGetField("CFN1");
                par[21] = javaMI.mvxGetField("USID");
//                   par[22] = UtilHelper.roundToString(javaMI.mvxGetField("CFN3"));
//                   par[23] = UtilHelper.roundToString(javaMI.mvxGetField("CFN4"));
//                   par[24] = UtilHelper.roundToString(javaMI.mvxGetField("CFN5"));



                javaMI.mvxAccess(null);
            }
        }

        return par;
    }

    public String[] ListFreeFieldIndata2(String ITNO, String HDPR) {
        String par[] = new String[25];
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
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
                par[11] = javaMI.mvxGetField("CFI3"); // for item image
                par[12] = javaMI.mvxGetField("CF10");
                par[13] = javaMI.mvxGetField("CFI5");

                par[14] = javaMI.mvxGetField("CFD1");
                par[15] = javaMI.mvxGetField("CFS1");
                par[16] = javaMI.mvxGetField("CFS2");
                par[17] = javaMI.mvxGetField("CFS3");
                par[18] = javaMI.mvxGetField("CFI4");
                par[19] = javaMI.mvxGetField("CFD4");
                par[20] = javaMI.mvxGetField("CFN1");
                par[21] = javaMI.mvxGetField("USID");
                par[22] = roundToString(javaMI.mvxGetField("CFN3"));
                par[23] = roundToString(javaMI.mvxGetField("CFN4"));
                par[24] = roundToString(javaMI.mvxGetField("CFN5"));



                javaMI.mvxAccess(null);
            }
        }

        return par;
    }

    public static String roundToString(String s) {
        double d = Double.parseDouble(s);
        return (int) d + "";
    }

    public List<MMS200MILstItmByItmBean> LstItmByItm(String CONO, String ITNO, String LNCD, String CHCD, String FSTY, String TSTY) {
        List<MMS200MILstItmByItmBean> mms200MILstItmByItmBeans = new ArrayList<MMS200MILstItmByItmBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstItmByItm";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("LNCD", LNCD);
        javaMI.mvxSetField("CHCD", CHCD);
        javaMI.mvxSetField("FSTY", FSTY);
        javaMI.mvxSetField("TSTY", TSTY);

        recFlag = javaMI.mvxAccess("LstItmByItm");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            MMS200MI mms200mi = new MMS200MI();
            mms200mi.connect();
            while (javaMI.mvxMore()) {
                MMS200MILstItmByItmBean mms200MILstItmByItmBean = new MMS200MILstItmByItmBean();
                mms200MILstItmByItmBean.setSTAT(javaMI.mvxGetField("STAT"));
                mms200MILstItmByItmBean.setITNO(javaMI.mvxGetField("ITNO"));
                mms200MILstItmByItmBean.setITDS(javaMI.mvxGetField("ITDS"));
                mms200MILstItmByItmBean.setFUDS(javaMI.mvxGetField("FUDS"));
                mms200MILstItmByItmBean.setDWNO(javaMI.mvxGetField("DWNO"));
                mms200MILstItmByItmBean.setRESP(javaMI.mvxGetField("RESP"));
                mms200MILstItmByItmBean.setRENM(javaMI.mvxGetField("RENM"));
                mms200MILstItmByItmBean.setUNMS(javaMI.mvxGetField("UNMS"));
                mms200MILstItmByItmBean.setDS01(javaMI.mvxGetField("DS01"));
                mms200MILstItmByItmBean.setITGR(javaMI.mvxGetField("ITGR"));
                mms200MILstItmByItmBean.setDS02(javaMI.mvxGetField("DS02"));
                mms200MILstItmByItmBean.setITCL(javaMI.mvxGetField("ITCL"));
                mms200MILstItmByItmBean.setDS03(javaMI.mvxGetField("DS03"));
                mms200MILstItmByItmBean.setBUAR(javaMI.mvxGetField("BUAR"));
                mms200MILstItmByItmBean.setDS04(javaMI.mvxGetField("DS04"));
                mms200MILstItmByItmBean.setITTY(javaMI.mvxGetField("ITTY"));
                mms200MILstItmByItmBean.setDS05(javaMI.mvxGetField("DS05"));
                mms200MILstItmByItmBean.setTPCD(javaMI.mvxGetField("TPCD"));
                mms200MILstItmByItmBean.setMABU(javaMI.mvxGetField("MABU"));
                mms200MILstItmByItmBean.setCHCD(javaMI.mvxGetField("CHCD"));
                mms200MILstItmByItmBean.setSTCD(javaMI.mvxGetField("STCD"));
                mms200MILstItmByItmBean.setBACD(javaMI.mvxGetField("BACD"));
                mms200MILstItmByItmBean.setVOL3(javaMI.mvxGetField("VOL3"));
                mms200MILstItmByItmBean.setNEWE(javaMI.mvxGetField("NEWE"));
                mms200MILstItmByItmBean.setGRWE(javaMI.mvxGetField("GRWE"));
                mms200MILstItmByItmBean.setPPUN(javaMI.mvxGetField("PPUN"));
                mms200MILstItmByItmBean.setDS06(javaMI.mvxGetField("DS06"));
                mms200MILstItmByItmBean.setBYPR(javaMI.mvxGetField("BYPR"));
                mms200MILstItmByItmBean.setWAPC(javaMI.mvxGetField("WAPC"));
                mms200MILstItmByItmBean.setQACD(javaMI.mvxGetField("QACD"));
                mms200MILstItmByItmBean.setEPCD(javaMI.mvxGetField("EPCD"));
                mms200MILstItmByItmBean.setPOCY(javaMI.mvxGetField("POCY"));
                mms200MILstItmByItmBean.setACTI(javaMI.mvxGetField("ACTI"));
                mms200MILstItmByItmBean.setHIE1(javaMI.mvxGetField("HIE1"));
                mms200MILstItmByItmBean.setHIE2(javaMI.mvxGetField("HIE2"));
                mms200MILstItmByItmBean.setHIE3(javaMI.mvxGetField("HIE3"));
                mms200MILstItmByItmBean.setHIE4(javaMI.mvxGetField("HIE4"));
                mms200MILstItmByItmBean.setHIE5(javaMI.mvxGetField("HIE5"));
                mms200MILstItmByItmBean.setGRP1(javaMI.mvxGetField("GRP1"));
                mms200MILstItmByItmBean.setGRP2(javaMI.mvxGetField("GRP2"));
                mms200MILstItmByItmBean.setGRP3(javaMI.mvxGetField("GRP3"));
                mms200MILstItmByItmBean.setGRP4(javaMI.mvxGetField("GRP4"));
                mms200MILstItmByItmBean.setGRP5(javaMI.mvxGetField("GRP5"));
                mms200MILstItmByItmBean.setCFI1(javaMI.mvxGetField("CFI1"));
                mms200MILstItmByItmBean.setCFI2(javaMI.mvxGetField("CFI2"));
                mms200MILstItmByItmBean.setCFI3(javaMI.mvxGetField("CFI3"));
                mms200MILstItmByItmBean.setCFI4(javaMI.mvxGetField("CFI4"));
                mms200MILstItmByItmBean.setCFI5(javaMI.mvxGetField("CFI5"));
                mms200MILstItmByItmBean.setTXID(javaMI.mvxGetField("TXID"));
                mms200MILstItmByItmBean.setECMA(javaMI.mvxGetField("ECMA"));
                mms200MILstItmByItmBean.setPRGP(javaMI.mvxGetField("PRGP"));
                mms200MILstItmByItmBean.setDS07(javaMI.mvxGetField("DS07"));
                mms200MILstItmByItmBean.setINDI(javaMI.mvxGetField("INDI"));
                mms200MILstItmByItmBean.setPUUN(javaMI.mvxGetField("PUUN"));
                mms200MILstItmByItmBean.setDS08(javaMI.mvxGetField("DS08"));
                mms200MILstItmByItmBean.setALUC(javaMI.mvxGetField("ALUC"));
                mms200MILstItmByItmBean.setIEAA(javaMI.mvxGetField("IEAA"));
                mms200MILstItmByItmBean.setEXPD(javaMI.mvxGetField("EXPD"));
                mms200MILstItmByItmBean.setGRMT(javaMI.mvxGetField("GRMT"));
                mms200MILstItmByItmBean.setHAZI(javaMI.mvxGetField("HAZI"));
                mms200MILstItmByItmBean.setSALE(javaMI.mvxGetField("SALE"));
                mms200MILstItmByItmBean.setTAXC(javaMI.mvxGetField("TAXC"));
                mms200MILstItmByItmBean.setDS09(javaMI.mvxGetField("DS09"));
                mms200MILstItmByItmBean.setATMO(javaMI.mvxGetField("ATMO"));
                mms200MILstItmByItmBean.setATMN(javaMI.mvxGetField("ATMN"));
                mms200MILstItmByItmBean.setTPLI(javaMI.mvxGetField("TPLI"));
                mms200MILstItmByItmBean.setFCU1(javaMI.mvxGetField("FCU1"));
                mms200MILstItmByItmBean.setALUN(javaMI.mvxGetField("ALUN"));
                mms200MILstItmByItmBean.setIACP(javaMI.mvxGetField("IACP"));
                mms200MILstItmByItmBean.setHDPR(javaMI.mvxGetField("HDPR"));
                mms200MILstItmByItmBean.setAAD0(javaMI.mvxGetField("AAD0"));
                mms200MILstItmByItmBean.setAAD1(javaMI.mvxGetField("AAD1"));
                mms200MILstItmByItmBean.setCHCL(javaMI.mvxGetField("CHCL"));
                mms200MILstItmByItmBean.setITRC(javaMI.mvxGetField("ITRC"));
                mms200MILstItmByItmBean.setVTCP(javaMI.mvxGetField("VTCP"));
                mms200MILstItmByItmBean.setDS10(javaMI.mvxGetField("DS10"));
                mms200MILstItmByItmBean.setVTCS(javaMI.mvxGetField("VTCS"));
                mms200MILstItmByItmBean.setDS11(javaMI.mvxGetField("DS11"));
                mms200MILstItmByItmBean.setLMDT(javaMI.mvxGetField("LMDT"));
                mms200MILstItmByItmBean.setDCCD(javaMI.mvxGetField("DCCD"));
                mms200MILstItmByItmBean.setPDCC(javaMI.mvxGetField("PDCC"));
                mms200MILstItmByItmBean.setSPUN(javaMI.mvxGetField("SPUN"));
                mms200MILstItmByItmBean.setGRTI(javaMI.mvxGetField("GRTI"));
                mms200MILstItmByItmBean.setGRTS(javaMI.mvxGetField("GRTS"));
                mms200MILstItmByItmBean.setCAWP(javaMI.mvxGetField("CAWP"));
                mms200MILstItmByItmBean.setCWUN(javaMI.mvxGetField("CWUN"));
                mms200MILstItmByItmBean.setCPUN(javaMI.mvxGetField("CPUN"));
                mms200MILstItmByItmBean.setITRU(javaMI.mvxGetField("ITRU"));
                mms200MILstItmByItmBean.setTECR(javaMI.mvxGetField("TECR"));
                mms200MILstItmByItmBean.setEXCA(javaMI.mvxGetField("EXCA"));
                mms200MILstItmByItmBean.setACCG(javaMI.mvxGetField("ACCG"));
                mms200MILstItmByItmBean.setCCCM(javaMI.mvxGetField("CCCM"));
                mms200MILstItmByItmBean.setCCI1(javaMI.mvxGetField("CCI1"));
                mms200MILstItmByItmBean.setCRI1(javaMI.mvxGetField("CRI1"));
                mms200MILstItmByItmBean.setHVMT(javaMI.mvxGetField("HVMT"));
                mms200MILstItmByItmBean.setITNE(javaMI.mvxGetField("ITNE"));
                mms200MILstItmByItmBean.setAUTC(javaMI.mvxGetField("AUTC"));
                mms200MILstItmByItmBean.setRIDE(javaMI.mvxGetField("RIDE"));
                mms200MILstItmByItmBean.setRIDC(javaMI.mvxGetField("RIDC"));
                mms200MILstItmByItmBean.setRNRI(javaMI.mvxGetField("RNRI"));
                mms200MILstItmByItmBean.setSAFC(javaMI.mvxGetField("SAFC"));
                mms200MILstItmByItmBean.setRMSG(javaMI.mvxGetField("RMSG"));
                try {
                    MMS200miGetItmFacBean bean = mms200mi.GetItmFacility(HelperConstantsFnl.COMPANY, javaMI.mvxGetField("ITNO"), "100");
                    mms200MILstItmByItmBean.setVAMT(bean.getVAMT());
                    mms200MILstItmByItmBean.setUCOS(bean.getUCOS());
                    mms200MILstItmByItmBean.setACRF(bean.getACRF());
                    mms200MILstItmByItmBean.setAPPR(bean.getAPPR());
                    mms200MILstItmByItmBean.setPUPR(mms200mi.getItemPricePUPR(CONO, javaMI.mvxGetField("ITNO")));
                } catch (Exception e) {
                }
                mms200MILstItmByItmBeans.add(mms200MILstItmByItmBean);
                javaMI.mvxAccess(null);
            }
            mms200mi.destroyMI();
            mms200mi = null;
        }
        return mms200MILstItmByItmBeans;
    }

    public String getItemPricePUPR(String CONO, String ITNO) {
        int recFlag;
        String result;
        String identity = "GetItmPrice";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ITNO", ITNO);
        recFlag = javaMI.mvxAccess("GetItmPrice");
        if (recFlag > 0) {
            System.out.println(identity + javaMI.mvxGetLastError());
            result = javaMI.mvxGetLastError();
        } else {
            result = javaMI.mvxGetField("PUPR");
        }
        return result;
    }

    public MMS200miGetItmFacBean GetItmFacility(String CONO, String ITNO, String FACI) {
        MMS200miGetItmFacBean mms200GetItemFac = new MMS200miGetItmFacBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetItmFac";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("FACI", "100");
        recFlag = javaMI.mvxAccess("GetItmFac");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mms200GetItemFac.setCONO(javaMI.mvxGetField("CONO"));   //Company
            mms200GetItemFac.setFACI(javaMI.mvxGetField("FACI"));   //Facility
            mms200GetItemFac.setFACN(javaMI.mvxGetField("FACN"));   //Name
            mms200GetItemFac.setITNO(javaMI.mvxGetField("ITNO"));   //Item number
            mms200GetItemFac.setITDS(javaMI.mvxGetField("ITDS"));   //Name
            mms200GetItemFac.setLEA4(javaMI.mvxGetField("LEA4"));   //Administrative lead time
            mms200GetItemFac.setCSNO(javaMI.mvxGetField("CSNO"));   //Customs statistical number
            mms200GetItemFac.setORCO(javaMI.mvxGetField("ORCO"));   //Country of origin
            mms200GetItemFac.setAPPR(javaMI.mvxGetField("APPR"));   //Average cost
            mms200GetItemFac.setUCOS(javaMI.mvxGetField("UCOS"));   //Cost price
            mms200GetItemFac.setSOCO(javaMI.mvxGetField("SOCO"));   //Ordering cost
            mms200GetItemFac.setBQTM(javaMI.mvxGetField("BQTM"));   //Batch quantity method
            mms200GetItemFac.setDLET(javaMI.mvxGetField("DLET"));   //MO lead time method
            mms200GetItemFac.setDLEF(javaMI.mvxGetField("DLEF"));   //Dynamic lead time fence - MO
            mms200GetItemFac.setDIDY(javaMI.mvxGetField("DIDY"));   //Flow order planning
            mms200GetItemFac.setDIDF(javaMI.mvxGetField("DIDF"));   //Flow order fence
            mms200GetItemFac.setPRRA(javaMI.mvxGetField("PRRA"));   //Production rate
            mms200GetItemFac.setMARC(javaMI.mvxGetField("MARC"));   //MO reservations
            mms200GetItemFac.setJITF(javaMI.mvxGetField("JITF"));   //Orderless production
            mms200GetItemFac.setREWH(javaMI.mvxGetField("REWH"));   //Main warehouse
            mms200GetItemFac.setOPFQ(javaMI.mvxGetField("OPFQ"));   //Optimal on-hand balance - facility
            mms200GetItemFac.setFANO(javaMI.mvxGetField("FANO"));   //On-hand balance - facility
            mms200GetItemFac.setFANQ(javaMI.mvxGetField("FANQ"));   //On-hand balance for inspection -facility
            mms200GetItemFac.setFANR(javaMI.mvxGetField("FANR"));   //Rejected on-hand balance - facility
            mms200GetItemFac.setFATM(javaMI.mvxGetField("FATM"));   //On-hand balance method - facility
            mms200GetItemFac.setWCLN(javaMI.mvxGetField("WCLN"));   //Production line
            mms200GetItemFac.setPLNM(javaMI.mvxGetField("PLNM"));   //Name
            mms200GetItemFac.setAUGE(javaMI.mvxGetField("AUGE"));   //Automatic creation of MO
            mms200GetItemFac.setECCC(javaMI.mvxGetField("ECCC"));   //Consumption code - trade stat (TST)
            mms200GetItemFac.setECAR(javaMI.mvxGetField("ECAR"));   //Area/state
            mms200GetItemFac.setCPRI(javaMI.mvxGetField("CPRI"));   //Customs procedure - import
            mms200GetItemFac.setCPRE(javaMI.mvxGetField("CPRE"));   //Customs procedure - export
            mms200GetItemFac.setWSCA(javaMI.mvxGetField("WSCA"));   //Costing model - purchasing
            mms200GetItemFac.setPRCM(javaMI.mvxGetField("PRCM"));   //Costing model - product costing
            mms200GetItemFac.setPLAP(javaMI.mvxGetField("PLAP"));   //Planning process
            mms200GetItemFac.setPLUP(javaMI.mvxGetField("PLUP"));   //Push process
            mms200GetItemFac.setSCMO(javaMI.mvxGetField("SCMO"));   //Costing model - sales price
            mms200GetItemFac.setCPL0(javaMI.mvxGetField("CPL0"));   //Calculate price for line type 0
            mms200GetItemFac.setCPL1(javaMI.mvxGetField("CPL1"));   //Calculate price for line type 1
            mms200GetItemFac.setCPL2(javaMI.mvxGetField("CPL2"));   //Calculate price for line type 2
            mms200GetItemFac.setPPL0(javaMI.mvxGetField("PPL0"));   //Preliminary price for line type 0
            mms200GetItemFac.setPPL1(javaMI.mvxGetField("PPL1"));   //Preliminary price for line type 1
            mms200GetItemFac.setPPL2(javaMI.mvxGetField("PPL2"));   //Preliminary price for line type 2
            mms200GetItemFac.setCPDC(javaMI.mvxGetField("CPDC"));   //Costing decimal places
            mms200GetItemFac.setCOCD(javaMI.mvxGetField("COCD"));   //Item cost quantity
            mms200GetItemFac.setEVGR(javaMI.mvxGetField("EVGR"));   //Environment group
            mms200GetItemFac.setVAMT(javaMI.mvxGetField("VAMT"));   //Inventory accounting method
            mms200GetItemFac.setLAMA(javaMI.mvxGetField("LAMA"));   //Minimum accepted contributn margin ratio
            mms200GetItemFac.setGRTI(javaMI.mvxGetField("GRTI"));   //Group technology class
            mms200GetItemFac.setMOLL(javaMI.mvxGetField("MOLL"));   //MO leadtime limit
            mms200GetItemFac.setLMDT(javaMI.mvxGetField("LMDT"));   //Change date
            mms200GetItemFac.setCRTM(javaMI.mvxGetField("CRTM"));   //Critical material
            mms200GetItemFac.setDICM(javaMI.mvxGetField("DICM"));   //Costing model - distribution
            mms200GetItemFac.setACRF(javaMI.mvxGetField("ACRF"));   //User-defined accounting control object
            mms200GetItemFac.setSTCW(javaMI.mvxGetField("STCW"));   //Catch weight approved on hand balance
            mms200GetItemFac.setRJCW(javaMI.mvxGetField("RJCW"));   //Catch weight rejected on hand balance
            mms200GetItemFac.setQUCW(javaMI.mvxGetField("QUCW"));   //Catch weight for inspection on hand bal
            mms200GetItemFac.setCAWC(javaMI.mvxGetField("CAWC"));   //Catch weight cost
            mms200GetItemFac.setCPUN(javaMI.mvxGetField("CPUN"));   //Standard cost price unit of measure
            mms200GetItemFac.setEXPC(javaMI.mvxGetField("EXPC"));   //Yield percentage
            mms200GetItemFac.setBQTY(javaMI.mvxGetField("BQTY"));   //Batch quantity
            mms200GetItemFac.setLLCM(javaMI.mvxGetField("LLCM"));   //Lowest level - BoM
            mms200GetItemFac.setTRHC(javaMI.mvxGetField("TRHC"));   //Throughput rate of capacity/day
            mms200GetItemFac.setEDEC(javaMI.mvxGetField("EDEC"));   //Method for expiry date excess control
            mms200GetItemFac.setTXID(javaMI.mvxGetField("TXID"));   //Text identity
            mms200GetItemFac.setDTID(javaMI.mvxGetField("DTID"));   //Data identity
            mms200GetItemFac.setCOFA(javaMI.mvxGetField("COFA"));   //Conversion factor


            javaMI.mvxAccess(null);
        }
        return mms200GetItemFac;
    }

    public MMS200MIGetBean getGetItem(String ITNO, String LNCD) {
        MMS200MIGetBean mms200miGetBean = new MMS200MIGetBean();
        int recFlag;
        String ERROR = null;
        String identity = "Get";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("LNCD", LNCD);
        recFlag = javaMI.mvxAccess("Get");
        if (recFlag > 0) {
            System.out.println("recFlag" + recFlag);
            ERROR = javaMI.mvxGetLastError();

        } else {
            //System.out.println("ITNO"+ITNO+"::::"+javaMI.mvxGetField("ITNO"));
            mms200miGetBean.setSTAT(javaMI.mvxGetField("STAT"));   //Status
            mms200miGetBean.setITNO(javaMI.mvxGetField("ITNO"));   //Item number
            mms200miGetBean.setITDS(javaMI.mvxGetField("ITDS"));   //Name
            mms200miGetBean.setFUDS(javaMI.mvxGetField("FUDS"));   //Description 2
            mms200miGetBean.setDWNO(javaMI.mvxGetField("DWNO"));   //Drawing number
            mms200miGetBean.setRESP(javaMI.mvxGetField("RESP"));   //Responsible
            mms200miGetBean.setUNMS(javaMI.mvxGetField("UNMS"));   //Basic unit of measure
            mms200miGetBean.setITGR(javaMI.mvxGetField("ITGR"));   //Item group
            mms200miGetBean.setITCL(javaMI.mvxGetField("ITCL"));   //Product group
            mms200miGetBean.setBUAR(javaMI.mvxGetField("BUAR"));   //Business area
            mms200miGetBean.setITTY(javaMI.mvxGetField("ITTY"));   //Item type
            mms200miGetBean.setTPCD(javaMI.mvxGetField("TPCD"));   //Item category
            mms200miGetBean.setMABU(javaMI.mvxGetField("MABU"));   //Make/buy code
            mms200miGetBean.setCHCD(javaMI.mvxGetField("CHCD"));   //Configuration code
            mms200miGetBean.setSTCD(javaMI.mvxGetField("STCD"));   //Inventory accounting
            mms200miGetBean.setBACD(javaMI.mvxGetField("BACD"));   //Lot numbering method
            mms200miGetBean.setVOL3(javaMI.mvxGetField("VOL3"));   //Volume
            mms200miGetBean.setNEWE(javaMI.mvxGetField("NEWE"));   //Net weight
            mms200miGetBean.setGRWE(javaMI.mvxGetField("GRWE"));   //Gross weight
            mms200miGetBean.setPPUN(javaMI.mvxGetField("PPUN"));   //Purchase price U/M
            mms200miGetBean.setBYPR(javaMI.mvxGetField("BYPR"));   //By/co-product code
            mms200miGetBean.setWAPC(javaMI.mvxGetField("WAPC"));   //Normal waste percentage
            mms200miGetBean.setQACD(javaMI.mvxGetField("QACD"));   //Inspection code
            mms200miGetBean.setEPCD(javaMI.mvxGetField("EPCD"));   //Yield calculation
            mms200miGetBean.setPOCY(javaMI.mvxGetField("POCY"));   //Normal potency
            mms200miGetBean.setACTI(javaMI.mvxGetField("ACTI"));   //Active or catch weight item
            mms200miGetBean.setHIE1(javaMI.mvxGetField("HIE1"));   //Hierachy level 1
            mms200miGetBean.setHIE2(javaMI.mvxGetField("HIE2"));   //Hierachy level 2
            mms200miGetBean.setHIE3(javaMI.mvxGetField("HIE3"));   //Hierachy level 3
            mms200miGetBean.setHIE4(javaMI.mvxGetField("HIE4"));   //Hierachy level 4
            mms200miGetBean.setHIE5(javaMI.mvxGetField("HIE5"));   //Hierachy level 5
            mms200miGetBean.setGRP1(javaMI.mvxGetField("GRP1"));   //Search group 1
            mms200miGetBean.setGRP2(javaMI.mvxGetField("GRP2"));   //Search group 2
            mms200miGetBean.setGRP3(javaMI.mvxGetField("GRP3"));   //Search group 3
            mms200miGetBean.setGRP4(javaMI.mvxGetField("GRP4"));   //Search group 4
            mms200miGetBean.setGRP5(javaMI.mvxGetField("GRP5"));   //Search group 5
            mms200miGetBean.setCFI1(javaMI.mvxGetField("CFI1"));   //User-defined field 1 - item
            mms200miGetBean.setCFI2(javaMI.mvxGetField("CFI2"));   //User-defined field 2 - item
            mms200miGetBean.setCFI3(javaMI.mvxGetField("CFI3"));   //User-defined field 3 - item
            mms200miGetBean.setCFI4(javaMI.mvxGetField("CFI4"));   //User-defined field 4 - item
            mms200miGetBean.setCFI5(javaMI.mvxGetField("CFI5"));   //User-defined field 5 - item
            mms200miGetBean.setTXID(javaMI.mvxGetField("TXID"));   //Text identity
            mms200miGetBean.setECMA(javaMI.mvxGetField("ECMA"));   //ECO managed
            mms200miGetBean.setPRGP(javaMI.mvxGetField("PRGP"));   //Procurement group
            mms200miGetBean.setINDI(javaMI.mvxGetField("INDI"));   //Lot control method
            mms200miGetBean.setPUUN(javaMI.mvxGetField("PUUN"));   //Purchase order U/M
            mms200miGetBean.setALUC(javaMI.mvxGetField("ALUC"));   //Alternate U/M in use
            mms200miGetBean.setIEAA(javaMI.mvxGetField("IEAA"));   //Item exists as alias identity
            mms200miGetBean.setEXPD(javaMI.mvxGetField("EXPD"));   //Expiration date method
            mms200miGetBean.setGRMT(javaMI.mvxGetField("GRMT"));   //Goods receiving method
            mms200miGetBean.setHAZI(javaMI.mvxGetField("HAZI"));   //Danger indicator
            mms200miGetBean.setSALE(javaMI.mvxGetField("SALE"));   //Sales item
            mms200miGetBean.setTAXC(javaMI.mvxGetField("TAXC"));   //Tax code customer/address
            mms200miGetBean.setATMO(javaMI.mvxGetField("ATMO"));   //Attribute model
            mms200miGetBean.setATMN(javaMI.mvxGetField("ATMN"));   //Attribute managed
            mms200miGetBean.setTPLI(javaMI.mvxGetField("TPLI"));   //Template item number
            mms200miGetBean.setFCU1(javaMI.mvxGetField("FCU1"));   //Estimated free unit of assigned goods
            mms200miGetBean.setALUN(javaMI.mvxGetField("ALUN"));   //Alternate U/M
            mms200miGetBean.setIACP(javaMI.mvxGetField("IACP"));   //Attribute-controlled item
            mms200miGetBean.setHDPR(javaMI.mvxGetField("HDPR"));   //Main product
            mms200miGetBean.setAAD0(javaMI.mvxGetField("AAD0"));   //Extended QA used
            mms200miGetBean.setAAD1(javaMI.mvxGetField("AAD1"));   //Generate txt from text template
            mms200miGetBean.setCHCL(javaMI.mvxGetField("CHCL"));   //Charge calculation
            mms200miGetBean.setITRC(javaMI.mvxGetField("ITRC"));   //Individual item tracing
            mms200miGetBean.setVTCP(javaMI.mvxGetField("VTCP"));   //VAT code - purchase
            mms200miGetBean.setVTCS(javaMI.mvxGetField("VTCS"));   //VAT code - sales
            mms200miGetBean.setLMDT(javaMI.mvxGetField("LMDT"));   //Change date
            mms200miGetBean.setDCCD(javaMI.mvxGetField("DCCD"));   //Number of decimal places
            mms200miGetBean.setPDCC(javaMI.mvxGetField("PDCC"));   //Number of price decimal places
            mms200miGetBean.setSPUN(javaMI.mvxGetField("SPUN"));   //Sales price unit of measure
            mms200miGetBean.setGRTI(javaMI.mvxGetField("GRTI"));   //Group Technology Class
            mms200miGetBean.setGRTS(javaMI.mvxGetField("GRTS"));   //Distribution Group Technology
            mms200miGetBean.setACHK(javaMI.mvxGetField("ACHK"));   //Assortment check
            mms200miGetBean.setCAWP(javaMI.mvxGetField("CAWP"));   //Catch weight mode
            mms200miGetBean.setCWUN(javaMI.mvxGetField("CWUN"));   //Catch weight unit of measure
            mms200miGetBean.setCPUN(javaMI.mvxGetField("CPUN"));   //Standard cost price unit of measure
            mms200miGetBean.setATNR(javaMI.mvxGetField("ATNR"));   //Attribute number
            mms200miGetBean.setNMIT(javaMI.mvxGetField("NMIT"));   //Non-material type
            mms200miGetBean.setTECR(javaMI.mvxGetField("TECR"));   //Core terms
            mms200miGetBean.setEXCA(javaMI.mvxGetField("EXCA"));   //Exchangeable
            mms200miGetBean.setMOTP(javaMI.mvxGetField("MOTP"));   //Model/site
            mms200miGetBean.setACMC(javaMI.mvxGetField("ACMC"));   //Compatibility code
            mms200miGetBean.setITRU(javaMI.mvxGetField("ITRU"));   //Included in territorial roll-up
            mms200miGetBean.setACCG(javaMI.mvxGetField("ACCG"));   //Acceptance group
            mms200miGetBean.setCCCM(javaMI.mvxGetField("CCCM"));   //Costing model - core charge
            mms200miGetBean.setCCI1(javaMI.mvxGetField("CCI1"));   //Core charge item number
            mms200miGetBean.setCRI1(javaMI.mvxGetField("CRI1"));   //Core remain item number
            mms200miGetBean.setHVMT(javaMI.mvxGetField("HVMT"));   //Harvest Method
            mms200miGetBean.setITNE(javaMI.mvxGetField("ITNE"));   //Extended item number
            mms200miGetBean.setSPGV(javaMI.mvxGetField("SPGV"));   //Specific gravity

            /*
             MMS015MI mms015mi = new MMS015MI();
             mms015mi.connect();
             String CONV=mms015mi.getCovfactorList(HelperConstantsFnl.COMPANY, ITNO);
             mms015mi.destroyMI();
               
             mms200miGetBean.setCONV_FACT(CONV);*/

            javaMI.mvxAccess(null);
        }
        return mms200miGetBean;
    }
}
