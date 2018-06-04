/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author Ranjeet
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import shahi.Action.PcdEntry.Beans.OrderLineBean;
import shahi.Action.PcdEntry.Beans.OrderPcdBean;
import shahi.Action.PcdEntry.Beans.OrderViewBean;

/**
 *
 * @author user
 */
public class OIS100MI extends BaseMI {

    public OIS100MI() {
        setProgram("OIS100MI");
    }
    String identity = null;

    public OrderViewBean GetHead(String ORNO) {
        identity = "GetHead";
        OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);

        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
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
            javaMI.mvxAccess(null);
        }
        return ovb;
    }

    public OrderViewBean GetHeadWithTXT(String ORNO) {
        identity = "GetHead";
        OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);

        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
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

    public List<OrderViewBean> LstHeadByStyle(String ITNO, List<OrderViewBean> list1) {
        OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        identity = "LstHeadByStyle";
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("LstHeadByStyle");
        if (recFlag > 0) {
            //  CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
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


                list1.add(ovb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<shahi.Action.UnitEmbForecast.Beans.OrderViewBean> LstHeadByStyleForEmbPlanning(String ITNO, List<shahi.Action.UnitEmbForecast.Beans.OrderViewBean> list1) {
        shahi.Action.UnitEmbForecast.Beans.OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        identity = "LstHeadByStyle";
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

    public List<OrderViewBean> LstHead(String CUNO) {
        List<OrderViewBean> list1 = new ArrayList<OrderViewBean>();
        identity = "LstHead";
        OrderViewBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("CUNO", CUNO);
        javaMI.mvxSetField("ORSL", "22");
        javaMI.mvxSetField("ORST", "22");

        recFlag = javaMI.mvxAccess("LstHead");
        if (recFlag > 0) {
            // CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                ovb = new OrderViewBean();
                ovb.setFacility(javaMI.mvxGetField("FACI"));
                ovb.setOrderDate(javaMI.mvxGetField("ORDT"));
                ovb.setDelyDate(javaMI.mvxGetField("RLDT"));
                ovb.setOrderNo(javaMI.mvxGetField("ORNO"));
                ovb.setDelyMtd(javaMI.mvxGetField("MODL"));
                ovb.setDelyTerms(javaMI.mvxGetField("TEDL"));
                ovb.setBuyerPO(javaMI.mvxGetField("CUOR"));
                ovb.setTempOrderNo(javaMI.mvxGetField("ORNO"));
                ovb.setLowerStatus(javaMI.mvxGetField("ORSL"));
                ovb.setHigherStatus(javaMI.mvxGetField("ORST"));
                list1.add(ovb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<OrderLineBean> LstLine(String ORNO) {
        List<OrderLineBean> list1 = new ArrayList<OrderLineBean>();
        OrderLineBean olb = null;
        int recFlag;
        identity = "LstLine";
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


                //ALQT=Integer.parseInt(ALQT) + Integer.parseInt(DLQT) + Integer.parseInt(IVQT)+"";
                //ORQT =  Integer.parseInt(ORQT) + "";

                olb = new OrderLineBean(PONR, ORNO, ITNO, ORQT, WHLO, SAPR, DIP1, DWDT, ITDS, ADID, ORST, TEDS, ALQT, NEPR, LNAM, UCOS, MODL, "", "", "No", "");
                olb.setBpo(CUOR);
                olb.setAlun(ALUN);
                list1.add(olb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<shahi.Action.UnitEmbForecast.Beans.OrderLineBean> LstLineForEmbPlanning(String ORNO) {
        List<shahi.Action.UnitEmbForecast.Beans.OrderLineBean> list1 = new ArrayList<shahi.Action.UnitEmbForecast.Beans.OrderLineBean>();
        shahi.Action.UnitEmbForecast.Beans.OrderLineBean olb = null;
        int recFlag;
        identity = "LstLine";
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
        identity = "getHead";
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

    public String GetLine(String ORNO) {
        String ITNO = "";
        identity = "GetLine";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ORNO", ORNO);
        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError() + " for order no->" + ORNO, CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            while (javaMI.mvxMore()) {
                ITNO = javaMI.mvxGetField("ITNO");
                javaMI.mvxAccess(null);
                break;
            }
        }
        if (ITNO == null) {
            ITNO = "";
        }
        return ITNO;
    }

    public String GetLinedeldate(String ORNO, String PONR) {
        String DWDT = "";
        identity = "GetLine";
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

    public String AddFreeFieldACT(String ORNO, String PONR, String HDBR, String CF11, String USID, String CF12) {
        identity = "AddFreeField";
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
        identity = "AddFreeField";
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

    public String UpdateFreeFieldACT(String ORNO, String PONR, String CF11, String USID, String CF12) {
        identity = "UpdateFreeField";
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
        identity = "UpdateFreeField";
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

    public String UpdateFreeFieldApproval(String ORNO, String PONR, String CFI1, String USID, String CFI2, String CFD3) {
        identity = "UpdateFreeField";
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
        identity = "AddFreeField";
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
        identity = "UpdateFreeField";
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

    public List<OrderPcdBean> ListFreeFieldIndata(String ORNO, String HDBR) {
        List<OrderPcdBean> list1 = new ArrayList<OrderPcdBean>();
        identity = "ListFreeFieldIndata";
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

    public List<shahi.Action.UnitEmbForecast.Beans.OrderPcdBean> ListFreeFieldIndataForEmbPlanning(String ORNO, String HDBR) {
        List<shahi.Action.UnitEmbForecast.Beans.OrderPcdBean> list1 = new ArrayList<shahi.Action.UnitEmbForecast.Beans.OrderPcdBean>();
        identity = "ListFreeFieldIndata";
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

    public List<OrderPcdBean> ListFreeFieldIndataApproval(String ORNO, String HDBR) {
        List<OrderPcdBean> list1 = new ArrayList<OrderPcdBean>();
        identity = "ListFreeFieldIndata";
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

    public String[] getexfactorydate(String ORNO, String HDBR, String LINE) {
        String date[] = new String[2];
        identity = "ListFreeFieldIndata";
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

    public boolean GetFreeField(String ORNO, String HDBR) {
        int recFlag;
        identity = "ListFreeFieldIndata";
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
