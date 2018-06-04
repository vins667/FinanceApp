/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import shahi.Action.HelperConstantsFnl;
import shahi.Action.MI.BaseMI;
import shahi.Action.MI.Beans.OrderViewBean;
import shahi.Action.PcdEntry.Beans.OrderLineBean;
import shahi.Action.PcdEntry.Beans.OrderPcdBean;

/**
 *
 * @author Ranjeet
 */
public class OIZ100MI extends BaseMI {
    public OIZ100MI() {
        setProgram("OIZ100MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public List<OrderViewBean> LstHeadByStyle(String ITNO) {
        OrderViewBean ovb = null;
        List list1 = new ArrayList();
        int recFlag;

        if (ITNO != null && ITNO.trim().length() > 0 && ITNO.trim().length() > 3) {
            javaMI.mvxClearFields();
            String identity = "LstHeadByStyle";
            javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
            javaMI.mvxSetField("ITNO", ITNO);
            recFlag = javaMI.mvxAccess("LstHeadByStyle");
            if (recFlag > 0) {
            } else {
                while (javaMI.mvxMore()) {
                    ovb = new OrderViewBean();
                    ovb.setFacility(javaMI.mvxGetField("FACI"));
                    ovb.setOrderDate(javaMI.mvxGetField("ORDT"));
                    ovb.setDelyDate(javaMI.mvxGetField("RLDZ"));
                    ovb.setOrderNo(javaMI.mvxGetField("ORNO"));
                    ovb.setDelyMtd(javaMI.mvxGetField("MODL"));
                    ovb.setDelyTerms(javaMI.mvxGetField("TEDL"));
                    ovb.setBuyerPO(javaMI.mvxGetField("CUOR"));
                    ovb.setTempOrderNo(javaMI.mvxGetField("ORNO"));
                    ovb.setLowerStatus(javaMI.mvxGetField("ORSL"));
                    ovb.setHigherStatus(javaMI.mvxGetField("ORST"));
                    ovb.setBuyer(javaMI.mvxGetField("CUNO"));
                    ovb.setOrderType(javaMI.mvxGetField("ORTP"));
                    ovb.setBuyerStyle(javaMI.mvxGetField("OREF"));
                    ovb.setYREF(javaMI.mvxGetField("YREF"));


                    list1.add(ovb);
                    javaMI.mvxAccess(null);
                }
            }
        }
        return list1;
    }

    public List<shahi.Action.PcdEntry.Beans.OrderViewBean> LstHeadByStyle(String ITNO, List<shahi.Action.PcdEntry.Beans.OrderViewBean> list1) {
        shahi.Action.PcdEntry.Beans.OrderViewBean ovb = null;
        int recFlag;

        if (ITNO != null && ITNO.trim().length() > 0 && ITNO.trim().length() > 3) {
            javaMI.mvxClearFields();
            String identity = "LstHeadByStyle";
            javaMI.mvxSetField("CONO", "111");
            javaMI.mvxSetField("ITNO", ITNO);

            recFlag = javaMI.mvxAccess("LstHeadByStyle");
            if (recFlag > 0) {
                //  CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            } else {
                while (javaMI.mvxMore()) {
                    ovb = new shahi.Action.PcdEntry.Beans.OrderViewBean();
                    ovb.setFacility(javaMI.mvxGetField("FACI"));
                    ovb.setOrderDate(javaMI.mvxGetField("ORDT"));
                    ovb.setDelyDate(javaMI.mvxGetField("RLDZ"));
                    ovb.setOrderNo(javaMI.mvxGetField("ORNO"));
                    ovb.setDelyMtd(javaMI.mvxGetField("MODL"));
                    ovb.setDelyTerms(javaMI.mvxGetField("TEDL"));
                    ovb.setBuyerPO(javaMI.mvxGetField("CUOR"));
                    ovb.setTempOrderNo(javaMI.mvxGetField("ORNO"));
                    ovb.setLowerStatus(javaMI.mvxGetField("ORSL"));
                    ovb.setHigherStatus(javaMI.mvxGetField("ORST"));
                    ovb.setBuyer(javaMI.mvxGetField("CUNO"));
                    ovb.setOrderType(javaMI.mvxGetField("ORTP"));
                    ovb.setBuyerStyle(javaMI.mvxGetField("OREF"));

                    list1.add(ovb);
                    javaMI.mvxAccess(null);
                }
            }
        }
        return list1;
    }

    public List<OrderViewBean> LstHeadByStyle1(String ITNO, List<OrderViewBean> list1) {
        OrderViewBean ovb = null;
        int recFlag;
        if (ITNO != null && ITNO.trim().length() > 0 && ITNO.trim().length() > 3) {
            javaMI.mvxClearFields();
            String identity = "LstHeadByStyle";
            javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
            javaMI.mvxSetField("ITNO", ITNO);

            recFlag = javaMI.mvxAccess("LstHeadByStyle");
            if (recFlag > 0) {
            } else {
                while (javaMI.mvxMore()) {
                    ovb = new OrderViewBean();
                    ovb.setFacility(javaMI.mvxGetField("FACI"));
                    ovb.setOrderDate(javaMI.mvxGetField("ORDT"));
                    ovb.setDelyDate(javaMI.mvxGetField("RLDZ"));
                    ovb.setOrderNo(javaMI.mvxGetField("ORNO"));
                    ovb.setDelyMtd(javaMI.mvxGetField("MODL"));
                    ovb.setDelyTerms(javaMI.mvxGetField("TEDL"));
                    ovb.setBuyerPO(javaMI.mvxGetField("CUOR"));
                    ovb.setTempOrderNo(javaMI.mvxGetField("ORNO"));
                    ovb.setLowerStatus(javaMI.mvxGetField("ORSL"));
                    ovb.setHigherStatus(javaMI.mvxGetField("ORST"));
                    ovb.setBuyer(javaMI.mvxGetField("CUNO"));
                    ovb.setOrderType(javaMI.mvxGetField("ORTP"));
                    ovb.setBuyerStyle(javaMI.mvxGetField("OREF"));
                    ovb.setYREF(javaMI.mvxGetField("YREF"));


                    list1.add(ovb);
                    javaMI.mvxAccess(null);
                }
            }
        }
        return list1;
    }

    public List<OrderPcdBean> ListFreeFieldIndataApproval(String ORNO, String HDBR) {
        List<OrderPcdBean> list1 = new ArrayList<OrderPcdBean>();
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("HDPR", HDBR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                String date = javaMI.mvxGetField("CFD1");
                String date1 = javaMI.mvxGetField("CFD2");
                String date2 = javaMI.mvxGetField("CFD3");
                String ponr1 = javaMI.mvxGetField("PONR");
                String exfacdate = javaMI.mvxGetField("CFD4");

                if (date != null && date.length() > 0 && !date.equals("0")) {
                    date = date.substring(6, 8) + "-" + date.substring(4, 6) + "-" + date.substring(0, 4);
                } else {
                    date = "";
                }
                if (date1 != null && date1.length() > 0 && !date1.equals("0")) {
                    date1 = date1.substring(6, 8) + "-" + date1.substring(4, 6) + "-" + date1.substring(0, 4);
                } else {
                    date1 = "";
                }
                if (date2 != null && date2.length() > 0 && !date2.equals("0")) {
                    date2 = date2.substring(6, 8) + "-" + date2.substring(4, 6) + "-" + date2.substring(0, 4);
                } else {
                    date2 = "";
                }

                if (exfacdate != null && exfacdate.length() > 0 && !exfacdate.equals("0")) {
                } else {
                    exfacdate = "";
                }

                list1.add(new OrderPcdBean(ponr1, date, date1, ORNO, date2, exfacdate));

                javaMI.mvxAccess(null);


            }
        }

        return list1;
    }

    public String GetLinedeldate(String ORNO, String PONR) {
        String DWDT = "";
        String identity = "GetLine";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        recFlag = javaMI.mvxAccess("GetLine");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {

            DWDT = javaMI.mvxGetField("DWDT");
            javaMI.mvxAccess(null);

        }
        if (DWDT == null) {
            DWDT = "";
        }
        return DWDT;
    }

    public List<OrderLineBean> LstLine(String ORNO) {
        List<OrderLineBean> list1 = new ArrayList<OrderLineBean>();
        int recFlag;
        String identity = "LstLine";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);

        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {

            while (javaMI.mvxMore()) {
                String PONR = javaMI.mvxGetField("PONR");
                String ITNO = javaMI.mvxGetField("ITNO");
                String ORQT = javaMI.mvxGetField("ORQA");
//                String ORQT = javaMI.mvxGetField("ORQT");
                String WHLO = javaMI.mvxGetField("WHLO");
                String SAPR = javaMI.mvxGetField("SAPR");
                String DIP1 = javaMI.mvxGetField("DIP1");
                String DWDT = javaMI.mvxGetField("DWDT");
                String ITDS = javaMI.mvxGetField("ITDS");
                String ORST = javaMI.mvxGetField("ORST");
                String ADID = javaMI.mvxGetField("ADID");
                String TEDS = javaMI.mvxGetField("TEDS");
                String CUOR = javaMI.mvxGetField("CUOR");
                String ALUN = javaMI.mvxGetField("ALUN");
                String ALQT = javaMI.mvxGetField("ALQT");
                String DLQT = javaMI.mvxGetField("DLQT");
                String IVQT = javaMI.mvxGetField("IVQT");
                String NEPR = javaMI.mvxGetField("NEPR");
                String LNAM = javaMI.mvxGetField("NLAM");
                String UCOS = javaMI.mvxGetField("UCOS");
                String MODL = javaMI.mvxGetField("MODL");
                String TEDL = javaMI.mvxGetField("TEDL");
                String SACD = javaMI.mvxGetField("SACD");
                String CUCD = javaMI.mvxGetField("CUCD");


                //ALQT=Integer.parseInt(ALQT) + Integer.parseInt(DLQT) + Integer.parseInt(IVQT)+"";
                //ORQT =  Integer.parseInt(ORQT) + "";

                OrderLineBean olb = new OrderLineBean(PONR, ORNO, ITNO, ORQT, WHLO, SAPR, DIP1, DWDT, ITDS, ADID, ORST, TEDS, ALQT, NEPR, LNAM, UCOS, MODL, "", "", "No", "", TEDL, SACD, CUCD);

                olb.setAlun(ALUN);
                olb.setBpo(CUOR);
                list1.add(olb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public Map<String, String> LstLineMap(String ORNO) {
        Map<String, String> listlinemap = new HashMap<String, String>();

        int recFlag;
        String identity = "LstLine";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);

        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {

            while (javaMI.mvxMore()) {
                String RIDN = javaMI.mvxGetField("RIDN");
                String PONR = javaMI.mvxGetField("PONR");
                String DWDT = javaMI.mvxGetField("DWDT");

                listlinemap.put(RIDN + "-" + PONR, DWDT);

                javaMI.mvxAccess(null);
            }
        }
        return listlinemap;
    }

    public List<OrderPcdBean> ListFreeFieldIndata(String ORNO, String HDBR) {
        List<OrderPcdBean> list1 = new ArrayList<OrderPcdBean>();
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("HDPR", HDBR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                String date = javaMI.mvxGetField("CF11");
                String date1 = javaMI.mvxGetField("CF12");

                if (date != null && date.length() > 0) {
                    date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                }
                if (date1 != null && date1.length() > 0) {
                    date1 = date1.substring(0, 4) + "-" + date1.substring(4, 6) + "-" + date1.substring(6, 8);
                }
                String ponr1 = javaMI.mvxGetField("PONR");
                list1.add(new OrderPcdBean(ponr1, date, date1));

                javaMI.mvxAccess(null);


            }
        }

        return list1;
    }

    public String UpdateFreeFieldACT(String ORNO, String PONR, String CF11, String USID, String CF12) {
        String identity = "UpdateFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        //javaMI.mvxSetField("CF11", CF11);
        // javaMI.mvxSetField("USID", USID);
        javaMI.mvxSetField("CF12", CF12);

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

    public String UpdateFreeFieldPRO(String ORNO, String PONR, String CF11, String USID, String CF12) {

        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("CF11", CF11);
        //javaMI.mvxSetField("USID", USID);
        //javaMI.mvxSetField("CF12", CF12);

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

    public String AddFreeFieldACT(String ORNO, String PONR, String HDBR, String CF11, String USID, String CF12) {
        String identity = "AddFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("HDPR", HDBR);
        // javaMI.mvxSetField("CF11", CF11);
        javaMI.mvxSetField("USID", USID);
        javaMI.mvxSetField("CF12", CF12);




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

    public String AddFreeFieldPRO(String ORNO, String PONR, String HDBR, String CF11, String USID, String CF12) {
        String identity = "AddFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("HDPR", HDBR);
        javaMI.mvxSetField("CF11", CF11);
        javaMI.mvxSetField("USID", USID);
        // javaMI.mvxSetField("CF12", CF12);




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

    public String UpdateFreeFieldApproval(String ORNO, String PONR, String CFI1, String USID, String CFI2, String CFD3) {
        String identity = "UpdateFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("CFD1", CFI1);
        javaMI.mvxSetField("CFD2", CFI2);
        javaMI.mvxSetField("CFD3", CFD3);
        // javaMI.mvxSetField("USID", USID);
        javaMI.mvxSetField("CFS1", USID);
        recFlag = javaMI.mvxAccess("UpdateFreeField");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String AddFreeFieldApproval(String ORNO, String PONR, String HDBR, String CFI1, String USID) {
        String identity = "AddFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("HDPR", HDBR);
        javaMI.mvxSetField("CFI1", CFI1);
        javaMI.mvxSetField("USID", USID);
        recFlag = javaMI.mvxAccess("AddFreeField");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String UpdateFreeFieldFinalApproval(String ORNO, String PONR, String CFD3, String USID) {
        String identity = "UpdateFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("CFD3", CFD3);
        // javaMI.mvxSetField("USID", USID);
        javaMI.mvxSetField("CFS1", USID);
        recFlag = javaMI.mvxAccess("UpdateFreeField");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public shahi.Action.PcdEntry.Beans.OrderViewBean GetHeadWithTXT(String ORNO) {
        String identity = "GetHead";
        shahi.Action.PcdEntry.Beans.OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);

        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            ovb = new shahi.Action.PcdEntry.Beans.OrderViewBean();
            ovb.setBuyer(javaMI.mvxGetField("CUNO"));
            ovb.setOrderType(javaMI.mvxGetField("ORTP"));
            ovb.setWhs(javaMI.mvxGetField("WHLO"));
            ovb.setFacility(javaMI.mvxGetField("FACI"));
            ovb.setOrderDate(javaMI.mvxGetField("ORDT"));
            ovb.setDelyDate(javaMI.mvxGetField("RLDZ"));
            ovb.setOrderNo(javaMI.mvxGetField("ORNO"));
            ovb.setDelyMtd(javaMI.mvxGetField("MODL"));
            ovb.setDelyTerms(javaMI.mvxGetField("TEDL"));
            ovb.setPymtTerms(javaMI.mvxGetField("TEPY") + "-" + javaMI.mvxGetField("TXPY"));
            ovb.setBuyerPO(javaMI.mvxGetField("CUOR"));
            ovb.setBuyerStyle(javaMI.mvxGetField("OREF"));
            ovb.setBuyerAddr(javaMI.mvxGetField("ADID"));
            ovb.setCUR(javaMI.mvxGetField("CUCD"));
            ovb.setSalesPerson(javaMI.mvxGetField("SMCD"));
            ovb.setPrice(javaMI.mvxGetField("NTAM"));
            ovb.setFOB(javaMI.mvxGetField("ONET"));
            ovb.setPymntMtd(javaMI.mvxGetField("PYCD"));
            ovb.setDest(javaMI.mvxGetField("OTDP"));
            ovb.setPackagingTerms(javaMI.mvxGetField("TEPA"));
            ovb.setHigherStatus(javaMI.mvxGetField("ORST"));
            ovb.setLowerStatus(javaMI.mvxGetField("ORSL"));
            ovb.setFre1(javaMI.mvxGetField("FRE1"));
            javaMI.mvxAccess(null);
        }
        return ovb;
    }

    public String AddBatchHead(String CUNO, String ORTP, String FACI, String SMCD,
            String MODL, String TEDL, String TEPY,
            String PYNO, String ADID, String CUCD, String OREF, String ORDT,
            String RLDZ, String OTDP, String WHLO, String PYCD, String CUOR,
            String PACT, String FRE1, String YREF, String CRTP, String DLSP) {
        String identity = "AddBatchHead";
        int recFlag;
        String ORNO = null;
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("CUNO", CUNO);
        javaMI.mvxSetField("ORTP", ORTP);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("SMCD", SMCD);
        javaMI.mvxSetField("MODL", MODL);
        javaMI.mvxSetField("TEDL", TEDL);
        javaMI.mvxSetField("TEPY", TEPY);
        javaMI.mvxSetField("PYNO", PYNO);
        javaMI.mvxSetField("ADID", ADID);
        javaMI.mvxSetField("CUCD", CUCD);
        javaMI.mvxSetField("OREF", OREF);
        javaMI.mvxSetField("CUOR", CUOR);
        javaMI.mvxSetField("ORDT", ORDT);
        javaMI.mvxSetField("RLDT", RLDZ);
        javaMI.mvxSetField("RLDZ", RLDZ);
        javaMI.mvxSetField("OTDP", "0");
        //javaMI.mvxSetField("CRTP", "1");
        javaMI.mvxSetField("CRTP", CRTP);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("PYCD", PYCD);
        javaMI.mvxSetField("TEPA", PACT);
        javaMI.mvxSetField("WCON", "NA");
        javaMI.mvxSetField("FRE1", FRE1);
        javaMI.mvxSetField("YREF", YREF);
        javaMI.mvxSetField("DLSP", DLSP);
        String status;
        recFlag = javaMI.mvxAccess("AddBatchHead");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();

        } else {
            int i = 1;
            ORNO = javaMI.mvxGetField("ORNO");
            while (javaMI.mvxMore()) {
                ORNO = javaMI.mvxGetField("ORNO");
                javaMI.mvxAccess(null);
                i++;
            }
        }
        return ORNO;
    }

    public OrderViewBean GetHead(String ORNO) {
        String identity = "GetHead";
        OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);
        String status;
        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            ovb = new OrderViewBean();
            ovb.setBuyer(javaMI.mvxGetField("CUNO"));
            ovb.setOrderType(javaMI.mvxGetField("ORTP"));
            ovb.setWhs(javaMI.mvxGetField("WHLO"));
            ovb.setFacility(javaMI.mvxGetField("FACI"));
            ovb.setOrderDate(javaMI.mvxGetField("ORDT"));
            ovb.setDelyDate(javaMI.mvxGetField("RLDZ"));
            ovb.setOrderNo(javaMI.mvxGetField("ORNO"));
            ovb.setDelyMtd(javaMI.mvxGetField("MODL"));
            ovb.setDelyTerms(javaMI.mvxGetField("TEDL"));
            ovb.setPymtTerms(javaMI.mvxGetField("TEPY"));
            ovb.setBuyerPO(javaMI.mvxGetField("CUOR"));
            ovb.setBuyerStyle(javaMI.mvxGetField("OREF"));
            ovb.setBuyerAddr(javaMI.mvxGetField("ADID"));
            ovb.setCUR(javaMI.mvxGetField("CUCD"));
            ovb.setSalesPerson(javaMI.mvxGetField("SMCD"));
            ovb.setPrice(javaMI.mvxGetField("NTAM"));
            ovb.setFOB(javaMI.mvxGetField("ONET"));
            ovb.setPymntMtd(javaMI.mvxGetField("PYCD"));
            ovb.setDest(javaMI.mvxGetField("OTDP"));
            ovb.setPackagingTerms(javaMI.mvxGetField("TEPA"));
            ovb.setHigherStatus(javaMI.mvxGetField("ORST"));
            ovb.setLowerStatus(javaMI.mvxGetField("ORSL"));
            ovb.setFre1(javaMI.mvxGetField("FRE1"));
            ovb.setYREF(javaMI.mvxGetField("YREF"));
            ovb.setDLSP(javaMI.mvxGetField("DLSP"));
            ovb.setDELIVERYSPECDESC(javaMI.mvxGetField("DSTX"));
            javaMI.mvxAccess(null);
        }
        return ovb;
    }

    public static double roundToDecimals(double d, int c) {
        double temp = (double) ((d * Math.pow(10, c)));
        temp = Math.round(temp);
        return (((double) temp) / Math.pow(10, c));
    }

    public static String roundToDecimals(String s, int c) {
        double d = Double.parseDouble(s);
        return roundToDecimals(d, c) + "";
    }

    public String AddLineBatchEntMat(String ORNO, String ITNO, String ORQT, String WHLO, String SAPR,
            String DIP1, String DWDT, String ADID, String SACD, String CUOR) {
        String DIA1 = "";
        String status = null;
        int recFlag;
        String identity = "AddLineBatchEnt";
        javaMI.mvxClearFields();
        try {
            if (DIP1 != null) {
                DIA1 = ((Double.parseDouble(SAPR) * Double.parseDouble(DIP1)) / 100) + "";
            }
        } catch (Exception e) {
        }
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("ORQT", ORQT);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("SAPR", roundToDecimals(SAPR, 2));
        javaMI.mvxSetField("DIP1", roundToDecimals(DIP1, 2));
        javaMI.mvxSetField("DIA1", roundToDecimals(DIA1, 2));
        javaMI.mvxSetField("DWDT", DWDT);
        javaMI.mvxSetField("DIC1", "8");
        javaMI.mvxSetField("ADID", ADID);
        javaMI.mvxSetField("PACT", "BAG");
        javaMI.mvxSetField("SACD", SACD);
        javaMI.mvxSetField("CUOR", CUOR);

        recFlag = javaMI.mvxAccess("AddLineBatchEnt");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError() + "-" + "Rej";
        } else {
            status = javaMI.mvxGetField("PONR") + "-" + "Success";

        }
        return status;
    }

    public String AddFreeFieldMarkDown(String ORNO, String PONR, String HDBR, String CFI6, String USID, String EXFACTORYDATE, String CFS3) {
        String identity = "AddFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("DIVI", "100");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("HDPR", HDBR);
        javaMI.mvxSetField("CFI6", CFI6);
        javaMI.mvxSetField("USID", USID);
        javaMI.mvxSetField("CFD4", EXFACTORYDATE);
        javaMI.mvxSetField("CFS3", CFS3);
        //  System.out.println(ORNO +"::"+PONR +":::"+HDBR+":::"+CFI6+":::"+USID+":::"+EXFACTORYDATE+":::"+CFS3);
        recFlag = javaMI.mvxAccess("AddFreeField");
        String status11;
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError() + " ::: " + EXFACTORYDATE);
            status11 = javaMI.mvxGetLastError();
        } else {
            status11 = "Yes";
        }
        return status11;
    }

    public String AddBatchLineMat(String ORNO, String ITNO, String ORQT, String WHLO, String SAPR,
            String DIP1, String DWDT, String ADID, String SACD, String CUOR) {
        int recFlag;
        String identity = "AddBatchLine";
        String result = "";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO.trim());
        javaMI.mvxSetField("ITNO", ITNO.trim());
        javaMI.mvxSetField("ORQT", ORQT.trim());
        javaMI.mvxSetField("WHLO", WHLO.trim());
        javaMI.mvxSetField("SAPR", roundToDecimals(SAPR.trim(), 2));
        javaMI.mvxSetField("DIP1", roundToDecimals(DIP1.trim(), 2));
        javaMI.mvxSetField("DWDT", DWDT.trim());
        javaMI.mvxSetField("ADID", ADID.trim());
        javaMI.mvxSetField("DIC1", "8");
        javaMI.mvxSetField("SACD", SACD.trim());
        javaMI.mvxSetField("CUOR", CUOR.trim());

        recFlag = javaMI.mvxAccess("AddBatchLine");
        if (recFlag > 0) {
            result = javaMI.mvxGetLastError();
        } else {
            result = "Success";
        }
        return result;
    }

    public String Confirm(String ORNO) {
        String identity = "Confirm";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);

        recFlag = javaMI.mvxAccess("Confirm");
        if (recFlag > 0) {
            return javaMI.mvxGetLastError();
        } else {
            ORNO = javaMI.mvxGetField("ORNO");
            return ORNO;
        }
    }

    public String DeleteFreeField(String ORNO, String PONR, String HDPR) {
        String identity = "DeleteFreeField";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        //javaMI.mvxSetField("HDPR", HDPR);
        //javaMI.mvxSetField("USID", USID);

        recFlag = javaMI.mvxAccess("DeleteFreeField");
        String status11;
        if (recFlag > 0) {
            status11 = javaMI.mvxGetLastError();
        } else {
            status11 = "Yes";
        }
        return status11;
    }

    public String DelLine(String ORNO, String PONR) {
        int recFlag;
        String identity = "DelLine";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);

        recFlag = javaMI.mvxAccess("DelLine");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {

            status = "Success";
        }
        return status;
    }

    public String ChgLinedmt(String ORNO, String ITNO, String ORQT, String WHLO, String SAPR,
            String DIP1, String DWDT, String ADID, String PONR, String CUOR, String MODL, String TEDL, String SACD) {
        double saprDouble = 0.00;
        int recFlag;
        String identity = "ChgLine";
        javaMI.mvxClearFields();
        try {
            if (SAPR != null && !SAPR.equals("")) {
                saprDouble = Double.parseDouble(SAPR);
            }
        } catch (Exception e) {
        }
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("ORQA", ORQT);
        javaMI.mvxSetField("MODL", MODL);
        SAPR = saprDouble + "";
        javaMI.mvxSetField("SAPR", roundToDecimals(SAPR, 2));
        javaMI.mvxSetField("DIP1", roundToDecimals(DIP1, 2));
        javaMI.mvxSetField("DWDZ", DWDT);
        javaMI.mvxSetField("ADID", ADID);
        javaMI.mvxSetField("SACD", SACD);
        if (!CUOR.trim().equals("")) {
            javaMI.mvxSetField("CUOR", CUOR);
        }
        javaMI.mvxSetField("TEDL", TEDL);

        recFlag = javaMI.mvxAccess("ChgLineBatchEnt");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Success";
        }
        return status;
    }

    public String ChgLineQty(String ORNO, String ORQT, String PONR) {
        int recFlag;
        String identity = "ChgLine";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("ORQA", ORQT);
        recFlag = javaMI.mvxAccess("ChgLineBatchEnt");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();

        } else {
            status = "Success";
        }
        return status;
    }

    public String AddLineBatchEnt(String ORNO, String ITNO, String ORQT, String WHLO, String SAPR,
            String DIP1, String DWDT, String ADID, String CUOR, String SACD) {
        String DIA1 = "";
        String status = null;
        int recFlag;
        String identity = "AddLineBatchEnt";
        javaMI.mvxClearFields();
        try {
            if (DIP1 != null) {
                DIA1 = ((Double.parseDouble(SAPR) * Double.parseDouble(DIP1)) / 100) + "";
            }
        } catch (Exception e) {
        }
        javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("ORQT", ORQT);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("SAPR", roundToDecimals(SAPR, 2));
        javaMI.mvxSetField("DIP1", roundToDecimals(DIP1, 2));
        javaMI.mvxSetField("DIA1", roundToDecimals(DIA1, 2));
        javaMI.mvxSetField("DWDT", DWDT);
        javaMI.mvxSetField("DIC1", "8");
        javaMI.mvxSetField("ADID", ADID);
        javaMI.mvxSetField("PACT", "BAG");
        javaMI.mvxSetField("CUOR", CUOR);
        javaMI.mvxSetField("SACD", SACD);


        recFlag = javaMI.mvxAccess("AddLineBatchEnt");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = javaMI.mvxGetField("PONR") + "-" + "Success";
        }
        return status;
    }

    public List<OrderPcdBean> ListFreeFieldIndataApprovalNew(String HDPR) {
        List<OrderPcdBean> list1 = new ArrayList<OrderPcdBean>();
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("HDPR", HDPR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                String date = javaMI.mvxGetField("CFD1");
                String date1 = javaMI.mvxGetField("CFD2");
                String date2 = javaMI.mvxGetField("CFD3");
                String ponr1 = javaMI.mvxGetField("PONR");
                String exfacdate = javaMI.mvxGetField("CFD4");

                if (date != null && date.length() > 0 && !date.equals("0")) {
                    date = date.substring(6, 8) + "-" + date.substring(4, 6) + "-" + date.substring(0, 4);
                } else {
                    date = "";
                }
                if (date1 != null && date1.length() > 0 && !date1.equals("0")) {
                    date1 = date1.substring(6, 8) + "-" + date1.substring(4, 6) + "-" + date1.substring(0, 4);
                } else {
                    date1 = "";
                }
                if (date2 != null && date2.length() > 0 && !date2.equals("0")) {
                    date2 = date2.substring(6, 8) + "-" + date2.substring(4, 6) + "-" + date2.substring(0, 4);
                } else {
                    date2 = "";
                }

                if (exfacdate != null && exfacdate.length() > 0 && !exfacdate.equals("0")) {
                } else {
                    exfacdate = "";
                }

                list1.add(new OrderPcdBean(ponr1, date, date1, javaMI.mvxGetField("ORNO"), date2, exfacdate));

                javaMI.mvxAccess(null);


            }
        }

        return list1;
    }
    public String[] getexfactorydate(String ORNO, String HDBR, String LINE) {
        String date[] = new String[2];
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("PONR", LINE);
        javaMI.mvxSetField("HDPR", HDBR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {

                String exfacdate = javaMI.mvxGetField("CFD4");
                String approveddate = javaMI.mvxGetField("CFD3");

                if (exfacdate != null && exfacdate.length() > 0 && !exfacdate.equals("0")) {
                } else {
                    exfacdate = "NA";
                }
                if (approveddate != null && approveddate.length() > 0 && !approveddate.equals("0")) {
                    approveddate = "Approved";
                } else {
                    approveddate = "Un-Approved";
                }
                date[0] = exfacdate;
                date[1] = approveddate;

                javaMI.mvxAccess(null);


            }
        }

        return date;
    }
    public List<shahi.Action.UnitEmbForecast.Beans.OrderViewBean> LstHeadByStyleForEmbPlanning(String ITNO, List<shahi.Action.UnitEmbForecast.Beans.OrderViewBean> list1) {
        shahi.Action.UnitEmbForecast.Beans.OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        String identity = "LstHeadByStyle";
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("LstHeadByStyle");
        if (recFlag > 0) {
            //  CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                ovb = new shahi.Action.UnitEmbForecast.Beans.OrderViewBean();
                ovb.setFacility(javaMI.mvxGetField("FACI"));
                ovb.setOrderDate(javaMI.mvxGetField("ORDT"));
                ovb.setDelyDate(javaMI.mvxGetField("RLDZ"));
                ovb.setOrderNo(javaMI.mvxGetField("ORNO"));
                ovb.setDelyMtd(javaMI.mvxGetField("MODL"));
                ovb.setDelyTerms(javaMI.mvxGetField("TEDL"));
                ovb.setBuyerPO(javaMI.mvxGetField("CUOR"));
                ovb.setTempOrderNo(javaMI.mvxGetField("ORNO"));
                ovb.setLowerStatus(javaMI.mvxGetField("ORSL"));
                ovb.setHigherStatus(javaMI.mvxGetField("ORST"));
                ovb.setBuyer(javaMI.mvxGetField("CUNO"));
                ovb.setOrderType(javaMI.mvxGetField("ORTP"));
                ovb.setBuyerStyle(javaMI.mvxGetField("OREF"));


                list1.add(ovb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }
    public List<shahi.Action.UnitEmbForecast.Beans.OrderLineBean> LstLineForEmbPlanning(String ORNO) {
        List<shahi.Action.UnitEmbForecast.Beans.OrderLineBean> list1 = new ArrayList<shahi.Action.UnitEmbForecast.Beans.OrderLineBean>();
        shahi.Action.UnitEmbForecast.Beans.OrderLineBean olb = null;
        int recFlag;
        String identity = "LstLine";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);


        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {

            while (javaMI.mvxMore()) {
                String PONR = javaMI.mvxGetField("PONR");
                String ITNO = javaMI.mvxGetField("ITNO");
                String ORQT = javaMI.mvxGetField("ORQA");
//              String ORQT = javaMI.mvxGetField("ORQT");
                String WHLO = javaMI.mvxGetField("WHLO");
                String SAPR = javaMI.mvxGetField("SAPR");
                String DIP1 = javaMI.mvxGetField("DIP1");
                String DWDT = javaMI.mvxGetField("DWDT");
                String ITDS = javaMI.mvxGetField("ITDS");
                String ORST = javaMI.mvxGetField("ORST");
                String ADID = javaMI.mvxGetField("ADID");
                String TEDS = javaMI.mvxGetField("TEDS");
                String CUOR = javaMI.mvxGetField("CUOR");
                String ALUN = javaMI.mvxGetField("ALUN");
                String ALQT = javaMI.mvxGetField("ALQT");
                String DLQT = javaMI.mvxGetField("DLQT");
                String IVQT = javaMI.mvxGetField("IVQT");
                String NEPR = javaMI.mvxGetField("NEPR");
                String LNAM = javaMI.mvxGetField("NLAM");
                String UCOS = javaMI.mvxGetField("UCOS");
                String MODL = javaMI.mvxGetField("MODL");


                //ALQT=Integer.parseInt(ALQT) + Integer.parseInt(DLQT) + Integer.parseInt(IVQT)+"";
                //ORQT =  Integer.parseInt(ORQT) + "";
                olb = new shahi.Action.UnitEmbForecast.Beans.OrderLineBean(PONR, ORNO, ITNO, ORQT, WHLO, SAPR, DIP1, DWDT, ITDS, ADID, ORST, TEDS, ALQT, NEPR, LNAM, UCOS, MODL, "", "", "No", "");
                olb.setBpo(CUOR);
                olb.setAlun(ALUN);
                list1.add(olb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }
    public List<OrderViewBean> GetHead(List<OrderViewBean> list1) {
        OrderViewBean ovb = null;
        String identity = "getHead";
        Iterator itr = list1.iterator();
        int i = 0;
        while (itr.hasNext()) {
            int recFlag;
            javaMI.mvxClearFields();

            ovb = (OrderViewBean) itr.next();
            javaMI.mvxSetField("CONO", "111");
            javaMI.mvxSetField("ORNO", ovb.getOrderNo());

            recFlag = javaMI.mvxAccess("GetHead");
            if (recFlag > 0) {
                //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            } else {
                list1.remove(i);
                ovb.setPymtTerms(javaMI.mvxGetField("TXPY"));
                ovb.setCUR(javaMI.mvxGetField("CUCD"));
                ovb.setAddrNo(javaMI.mvxGetField("ADID"));

                list1.add(i, ovb);
                i++;
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }
    
    public List<shahi.Action.UnitEmbForecast.Beans.OrderPcdBean> ListFreeFieldIndataForEmbPlanning(String ORNO, String HDBR) {
        List<shahi.Action.UnitEmbForecast.Beans.OrderPcdBean> list1 = new ArrayList<shahi.Action.UnitEmbForecast.Beans.OrderPcdBean>();
        String identity = "ListFreeFieldIndata";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("HDPR", HDBR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                String date = javaMI.mvxGetField("CF11");
                String date1 = javaMI.mvxGetField("CF12");

                if (date != null && date.length() > 0) {
                    date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                }
                if (date1 != null && date1.length() > 0) {
                    date1 = date1.substring(0, 4) + "-" + date1.substring(4, 6) + "-" + date1.substring(6, 8);
                }
                String ponr1 = javaMI.mvxGetField("PONR");
                list1.add(new shahi.Action.UnitEmbForecast.Beans.OrderPcdBean(ponr1, date, date1));

                javaMI.mvxAccess(null);


            }
        }

        return list1;
    }
    public boolean GetFreeField(String ORNO, String HDBR) {
        int recFlag;
        String identity = "ListFreeFieldIndata";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("HDPR", HDBR);
        recFlag = javaMI.mvxAccess("ListFreeFieldIn");
        if (recFlag > 0) {
            return false;
        } else {
            return true;
        }
    }
}
