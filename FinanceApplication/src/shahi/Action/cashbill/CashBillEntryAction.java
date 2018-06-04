/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.cashbill;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import shahi.Action.M4bill.Beans.GetTaxRateBean;
import shahi.Action.M4bill.Beans.M3BILLBean;
import shahi.Action.MI.Beans.MDBREADMIGetCSYTAB20Bean;
import shahi.Action.MI.Beans.MDBREADMILstCSYCSN00Bean;
import shahi.Action.MI.Beans.TXZ050MIGetTaxRateBean;
import shahi.Action.MIM4.MDBREADMI;
import shahi.Action.MIM4.TXZ050MI;
import shahi.Action.Mis.ShahiInformationList;
import shahi.Action.cashbill.bean.CashBillDetailBean;
import shahi.Action.cashbill.bean.CashBillQueryBean;
import shahi.Action.cashbill.bean.SupplierAddressBean;
import shahi.Action.cashbill.bean.SupplierBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connection;
import shahi.Action.database.connectionShahiHris;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author Vivek
 */
public class CashBillEntryAction extends ActionSupport {

    private String aausrid;
    private String WAREHOUSE;
    private String BILL_TYPE;
    private String BILL_SUBTYPE;
    private String HSN_CODE;
    private String T_INDEX;
    private String DIVISION;
    private String STATE;
    private String GEOC;
    private String BREAK_AMOUNT;   
    private String FIN_STATUS;
    private String REPORT_NO;
    private String VCH_DATE;
    private String PAY_TYPE;
    private String PAY_TYPE_QUERY;
    
    private List<String> WAREHOUSE_LIST;
    private List<M3BILLBean> BILLTYPE_LIST;
    private List<M3BILLBean> BILLSUBTYPE_LIST;
    private List<M3BILLBean> BILLCC_LIST;
    private List<M3BILLBean> UOM_LIST;
    private List<String> PCH_LIST;
    private List<M3BILLBean> BILLPRODUCT_TYPE_LIST;
    private List<SupplierBean> SUPPLIER_LIST;
    private List<GetTaxRateBean> GST_LIST;
    private List HSN_LIST;
    private List<CashBillDetailBean> DETAIL_LIST;
    
    private String UPD_MAST;
    private String MAST_SL_NO;
    private String BILL_NO;
    private String BILL_YEAR;
    private String BILL_DATE;
    private String SUPPLIER;
    private String BILL_AMOUNT;
    private String MAST_REMARKS;
    private String SUPPLIER_DESC;
    private String ADR1;
    private String ADR2;
    private String ADR3;
    private String ADR4;
    private String TOWN;
    private String CSCD;
    private String ECAR;
    private String PONO;
    private String CONM;
    private String ADID;
    private String RGDT;
    private String REVERSE_SRVTAX;
    private String USER_ID;
    
    private String WAREHOUSE_QUERY;
    private String CONTROL_QUERY;
    private String BILL_FROM_DATE;
    private String BILL_TO_DATE;
    private String SUPPLIER_QUERY;
    private String STATUS;
    private String USER_QUERY;
    private String ACT_FLAG;
    private List<CashBillQueryBean> QUERY_LIST; 
    
    private List<String> BILL_TYPE_SLNO;
    private List<String> BILL_SUBTYPE_SLNO;
    private List<String> PCH;
    private List<String> COST_CENTER;
    private List<String> PRODUCT_GROUP;    
    private List<String> BILL_AMOUNT_BREAKUP;    
    private List<String> BILL_AMOUNT_QUANTITY;  
    private List<String> BILL_AMOUNT_UOM;
    private List<String> BILL_AMOUNT_NON_BREAKUP;
    private List<String> REMARKS;
    private List<String> BILL_AMOUNT_HSN;
    private List<String> BREAKUP_COUNT;
    private List<String> COST_ELEMENT;
    private List<String> COST_BREAKUP_AMT;
    private List<String> COST_BREAKUP_TAX;
    private List<String> COST_BREAKUP_TAX_CODE;
    private List<String> VOUCHER_DESC;
    private List<String> PARTNER_DESC;
    
    private List<String> CONTROL_NO_LIST;
    private List<String> DEL_CONTROL_NO_LIST;
    private List<String> DELETE_BILL_LIST;
    private List<String> FORWARD_USER_LIST;    
    private List<String> APPROVE_BILL_LIST;
    private List<String> UNAPPROVE_BILL_LIST;
    
    

    public String mksess() throws SQLException, Exception {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        if (usrid != null) {
            session.remove("sessUserName");
            session.remove("sessUserId");
            session.remove("sessLocationCode");
            session.remove("sessUnitCode");
            session.remove("sessDeptCode");
            try {
                conn = new connection().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
                return SUCCESS;
            }
            try {
                stat = conn.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE,DEPT_CODE from EMPLOYEE_VIEW where emp_code=?");
                stat.setString(1, aausrid);
                result = stat.executeQuery();
                if (result.next()) {
                    session.put("sessUserName", result.getString("EMP_NAME"));
                    session.put("sessUserId", result.getString("emp_code"));
                    session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                    session.put("sessUnitCode", result.getString("UNIT_CODE"));
                    session.put("sessDeptCode", result.getString("DEPT_CODE"));
                }
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }
                result = null;
                stat = null;
            } catch (Exception e) {
                System.out.println("CashBillEntryAction + mksess() " + e);
                return SUCCESS;
            } finally {
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
                result = null;
                stat = null;
                conn = null;
            }
        } else {
            try {
                conn = new connection().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
                return SUCCESS;
            }
            try {
                stat = conn.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE,DEPT_CODE from EMPLOYEE_VIEW where emp_code=?");
                stat.setString(1, aausrid);
                result = stat.executeQuery();
                if (result.next()) {
                    session.put("sessUserName", result.getString("EMP_NAME"));
                    session.put("sessUserId", result.getString("emp_code"));
                    session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                    session.put("sessUnitCode", result.getString("UNIT_CODE"));
                    session.put("sessDeptCode", result.getString("DEPT_CODE"));
                }
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }
                result = null;
                stat = null;
            } catch (Exception e) {
                System.out.println("CashBillEntryAction  + mksess() " + e);
                return SUCCESS;
            } finally {
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
                result = null;
                stat = null;
                conn = null;
            }
        }
        execute();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        WAREHOUSE_LIST = new ArrayList<String>();
        QUERY_LIST = new ArrayList<CashBillQueryBean>();
        if (usrid == null) {
            addActionError("Session not valid!!!!");
            return SUCCESS;
        }
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try {
            conn = new ConnectionSeplWeb().getConnection();
            WAREHOUSE_LIST = getWarehouse(conn, usrid);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if(VCH_DATE!=null && VCH_DATE.length()>0){}else{
                VCH_DATE = dateFormat.format(new Date());
            }
            if(ACT_FLAG!=null && ACT_FLAG.equalsIgnoreCase("CONTROL") && CONTROL_NO_LIST!=null && CONTROL_NO_LIST.size()>0 && !CONTROL_NO_LIST.get(0).equalsIgnoreCase("false")){
                String ctno = "0";
                stat = conn.prepareStatement("SELECT M4_BILL_CASH_MASTER_CTN_SQ.NEXTVAL FROM DUAL");
                resultSet = stat.executeQuery();
                if (resultSet.next()) {
                    ctno = resultSet.getString(1);
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stat != null) {
                    stat.close();
                }
                resultSet = null;
                stat = null;

                for (int i = 0; i < CONTROL_NO_LIST.size(); i++) {
                    stat = conn.prepareStatement("update  m4_bill_master set REPORT_NO=?  where SL_NO=?");
                    stat.setString(1, ctno);
                    stat.setString(2, CONTROL_NO_LIST.get(i).toString());
                    stat.executeUpdate();
                    if(stat!=null) stat.close();
                }
                addActionError("Control Generated Successfully!!! Control No: "+ctno);
            }        
            if(ACT_FLAG!=null && ACT_FLAG.equalsIgnoreCase("CONTROL") && DEL_CONTROL_NO_LIST!=null && DEL_CONTROL_NO_LIST.size()>0 && !DEL_CONTROL_NO_LIST.get(0).equalsIgnoreCase("false")){
                for (int i = 0; i < DEL_CONTROL_NO_LIST.size(); i++) {
                    stat = conn.prepareStatement("update  m4_bill_master set REPORT_NO=null  where SL_NO=?");
                    stat.setString(1, DEL_CONTROL_NO_LIST.get(i).toString());
                    stat.executeUpdate();
                    if(stat!=null) stat.close();
                }
                addActionError("Control Deleted Successfully!!!");
            }
            if(ACT_FLAG!=null && ACT_FLAG.equalsIgnoreCase("FORWARD") && FORWARD_USER_LIST!=null && FORWARD_USER_LIST.size()>0 && !FORWARD_USER_LIST.get(0).equalsIgnoreCase("false")){
                for (int i = 0; i < FORWARD_USER_LIST.size(); i++) {
                    stat = conn.prepareStatement("update  M4_BILL_MASTER set FORWARD_USER=? ,FORWARD_DATE=sysdate  where SL_NO=?");
                    stat.setString(1, usrid);
                    stat.setString(2, FORWARD_USER_LIST.get(i).toString());
                    stat.executeUpdate();
                    if(stat!=null) stat.close();
                }
                addActionError("Forwarded Successfully!!!");
            }
            if(ACT_FLAG!=null && ACT_FLAG.equalsIgnoreCase("APPROVE") && UNAPPROVE_BILL_LIST!=null && UNAPPROVE_BILL_LIST.size()>0 && !UNAPPROVE_BILL_LIST.get(0).equalsIgnoreCase("false")){
                for (int i = 0; i < UNAPPROVE_BILL_LIST.size(); i++) {
                    stat = conn.prepareStatement("update  M4_BILL_MASTER set FORWARD_USER=null ,FORWARD_DATE=null  where SL_NO=?");
                    stat.setString(1, UNAPPROVE_BILL_LIST.get(i).toString());
                    stat.executeUpdate();
                    if(stat!=null) stat.close();
                }
                addActionError("Un-Approve Successfully!!!");
            }
            if(ACT_FLAG!=null && ACT_FLAG.equalsIgnoreCase("APPROVE") && APPROVE_BILL_LIST!=null && APPROVE_BILL_LIST.size()>0 && !APPROVE_BILL_LIST.get(0).equalsIgnoreCase("false")){
                for (int i = 0; i < APPROVE_BILL_LIST.size(); i++) {
                    stat = conn.prepareStatement("update  M4_BILL_MASTER set ACCOUNT_USER=? ,ACCOUNT_DATE=SYSDATE,VCH_DATE=to_date(?,'dd/MM/yyyy')  where SL_NO=?");
                    stat.setString(1, usrid);
                    stat.setString(2, VCH_DATE);
                    stat.setString(3, APPROVE_BILL_LIST.get(i).toString());                    
                    stat.executeUpdate();
                    if(stat!=null) stat.close();
                }
                new CashBillTxtGenerator().txtGenerator(APPROVE_BILL_LIST,usrid);
                addActionError("Approve Successfully!!!");
            }
            if(ACT_FLAG!=null && ACT_FLAG.equalsIgnoreCase("DELETE") && DELETE_BILL_LIST!=null && DELETE_BILL_LIST.size()>0 && !DELETE_BILL_LIST.get(0).equalsIgnoreCase("false")){
                for (int i = 0; i < DELETE_BILL_LIST.size(); i++) {
                    stat = conn.prepareStatement("delete from m4_bill_detail where BILL_SL_NO=?");
                    stat.setString(1, DELETE_BILL_LIST.get(i).toString());
                    stat.executeUpdate();

                    stat = conn.prepareStatement("delete from M4_BILL_BREAKUP_DETAIL where BILL_SL_NO=?");
                    stat.setString(1, DELETE_BILL_LIST.get(i).toString());
                    stat.executeUpdate();

                    stat = conn.prepareStatement("delete from m4_bill_master where SL_NO=?");
                    stat.setString(1, DELETE_BILL_LIST.get(i).toString());
                    stat.executeUpdate();
                }
                addActionError("Deleted Successfully!!!");
            }        
            if (FIN_STATUS != null && FIN_STATUS.equalsIgnoreCase("YES")) {
                String para = "";
                if (WAREHOUSE_QUERY != null && WAREHOUSE_QUERY.length() > 0) {
                    para += " AND A.BILL_WHLO='" + WAREHOUSE_QUERY + "'";
                }
                if (CONTROL_QUERY != null && CONTROL_QUERY.length() > 0) {
                    para += " AND A.REPORT_NO='" + CONTROL_QUERY + "'";
                }
                if (BILL_FROM_DATE != null && BILL_FROM_DATE.length() > 0) {
                    para += " AND A.BILL_DATE>TO_DATE('" + BILL_FROM_DATE + "','dd/MM/yyyy')";
                }
                if (BILL_TO_DATE != null && BILL_TO_DATE.length() > 0) {
                    para += " AND A.BILL_DATE<TO_DATE('" + BILL_TO_DATE + "','dd/MM/yyyy')";
                }
                if (SUPPLIER_QUERY != null && SUPPLIER_QUERY.length() > 0) {
                    para += " AND A.SUPPLIER_CODE='" + SUPPLIER_QUERY + "'";
                }
                if (STATUS != null && STATUS.length() > 0 && STATUS.equals("P")) {
                        para += " AND A.FORWARD_DATE IS NOT NULL AND A.ACCOUNT_DATE IS NULL";
                }else{
                    para += " AND A.FORWARD_DATE IS NOT NULL";
                }
                
                    stat = conn.prepareStatement("SELECT A.SL_NO,DEPT_SL_NO,BILL_NO,TO_CHAR(BILL_DATE,'dd/mm/yyyy') BILL_DATE,SUPPLIER_CODE,BILL_AMOUNT,GROSS_AMOUNT,b.DEPT_DESC DEPT_DESC ,to_char(a.FORWARD_DATE,'dd/mm/yyyy') FORWARD_DATE,A.SACONM IDSUNM, FORWARD_USER,BILL_WHLO,REPORT_NO ,REVERSE_SRVTAX,SRVTAX_GL_CODE,BILL_YEAR,A.ACCOUNT_USER, TO_CHAR(A.ACCOUNT_DATE,'dd/mm/yyyy') ACCOUNT_DATE FROM M4_BILL_MASTER a,M4_BILL_DEPT_MASTER b where a.DEPT_SL_NO=b.SL_NO AND DEPT_SL_NO in(select SL_NO from M4_BILL_DEPT_MASTER  WHERE  DEPT_CODE in (select DEPT_SL_NO from  m4_bill_user_master where emp_code=? and flag='Y')) AND DEPT_SL_NO='93' and a.PAY_TYPE=? " + para + " order by SL_NO");
                    stat.setString(1, USER_QUERY);
                    stat.setString(2, PAY_TYPE_QUERY);
                    resultSet = stat.executeQuery();
                    while (resultSet.next()) {
                        CashBillQueryBean bean = new CashBillQueryBean();
                        bean.setSL_NO(resultSet.getString("SL_NO"));
                        bean.setDEPT_SL_NO(resultSet.getString("DEPT_SL_NO"));
                        bean.setBILL_NO(resultSet.getString("BILL_NO"));
                        bean.setBILL_DATE(resultSet.getString("BILL_DATE"));
                        bean.setSUPPLIER_CODE(resultSet.getString("SUPPLIER_CODE"));
                        bean.setSUPPLIER_DESC(resultSet.getString("IDSUNM"));
                        bean.setBILL_AMOUNT(resultSet.getString("BILL_AMOUNT"));
                        bean.setGROSS_AMOUNT(resultSet.getString("GROSS_AMOUNT"));
                        bean.setDEPT_DESC(resultSet.getString("DEPT_DESC"));
                        bean.setFORWARD_DATE(resultSet.getString("FORWARD_DATE"));
                        bean.setFORWARD_USER(resultSet.getString("FORWARD_USER"));
                        bean.setBILL_WHLO(resultSet.getString("BILL_WHLO"));
                        bean.setREPORT_NO(resultSet.getString("REPORT_NO"));
                        bean.setREVERSE_SRVTAX(resultSet.getString("REVERSE_SRVTAX"));
                        bean.setSRVTAX_GL_CODE(resultSet.getString("SRVTAX_GL_CODE"));
                        bean.setBILL_YEAR(resultSet.getString("BILL_YEAR"));
                        bean.setACCOUNT_DATE(resultSet.getString("ACCOUNT_DATE"));
                        bean.setACCOUNT_USER(resultSet.getString("ACCOUNT_USER"));
                        
                        bean.setERROR_MSG(null);
                        if(resultSet.getString("REVERSE_SRVTAX")!=null && resultSet.getString("REVERSE_SRVTAX").equals("1")){}
                        else {
                            PreparedStatement stat1 = conn.prepareStatement(" SELECT SUM(AMOUNT) AMOUNT FROM("
                                    + " SELECT A.SL_NO,B.BILL_SL_NO,MAX(A.PRODUCT_AMOUNT)+NVL(SUM(B.AMT),0) AMOUNT FROM M4_BILL_DETAIL A,M4_BILL_BREAKUP_DETAIL B"
                                    + " WHERE A.BILL_SL_NO=B.BILL_SL_NO(+) and A.SL_NO=B.LINE_SL_NO(+) AND A.BILL_SL_NO=?"
                                    + " GROUP BY A.SL_NO,B.BILL_SL_NO)");
                            stat1.setString(1, resultSet.getString("SL_NO"));
                            ResultSet resultSet1 = stat1.executeQuery();
                            if (resultSet1.next()) {
                                if (resultSet.getDouble("BILL_AMOUNT") == resultSet1.getDouble("AMOUNT")) {
                                } else {
                                    bean.setERROR_MSG("Bill Amount and Breakup mismated!!!");
                                }
                            }
                            if (resultSet1 != null) {
                                resultSet1.close();
                            }
                            if (stat1 != null) {
                                stat1.close();
                            }
                        }
                        
                        QUERY_LIST.add(bean);
                    }
            }
            else {
                if (USER_QUERY != null && USER_QUERY.length() > 0) {
                    String para = "";
                    if (WAREHOUSE_QUERY != null && WAREHOUSE_QUERY.length() > 0) {
                        para += " AND A.BILL_WHLO='" + WAREHOUSE_QUERY + "'";
                    }
                    if (CONTROL_QUERY != null && CONTROL_QUERY.length() > 0) {
                        para += " AND A.REPORT_NO='" + CONTROL_QUERY + "'";
                    }
                    if (BILL_FROM_DATE != null && BILL_FROM_DATE.length() > 0) {
                        para += " AND A.BILL_DATE>TO_DATE('" + BILL_FROM_DATE + "','dd/MM/yyyy')";
                    }
                    if (BILL_TO_DATE != null && BILL_TO_DATE.length() > 0) {
                        para += " AND A.BILL_DATE<TO_DATE('" + BILL_TO_DATE + "','dd/MM/yyyy')";
                    }
                    if (SUPPLIER_QUERY != null && SUPPLIER_QUERY.length() > 0) {
                        para += " AND A.SUPPLIER_CODE='" + SUPPLIER_QUERY + "'";
                    }
                    if (STATUS != null && STATUS.length() > 0 && STATUS.equals("P")) {
                        para += " AND A.FORWARD_DATE IS NULL";
                    } else if (STATUS != null && STATUS.length() > 0 && STATUS.equals("F")) {
                        para += " AND A.FORWARD_DATE IS NOT NULL";
                    }
                    if (USER_QUERY != null && USER_QUERY.length() > 0) {
                        para += " AND A.USER_ID='" + USER_QUERY + "'";
                    }

                    stat = conn.prepareStatement("SELECT A.SL_NO,DEPT_SL_NO,BILL_NO,TO_CHAR(BILL_DATE,'dd/mm/yyyy') BILL_DATE,SUPPLIER_CODE,BILL_AMOUNT,GROSS_AMOUNT,b.DEPT_DESC DEPT_DESC ,to_char(a.FORWARD_DATE,'dd/mm/yyyy') FORWARD_DATE,A.SACONM IDSUNM, FORWARD_USER,BILL_WHLO,REPORT_NO ,REVERSE_SRVTAX,SRVTAX_GL_CODE,BILL_YEAR,A.ACCOUNT_USER, TO_CHAR(A.ACCOUNT_DATE,'dd/mm/yyyy') ACCOUNT_DATE FROM M4_BILL_MASTER a,M4_BILL_DEPT_MASTER b where a.DEPT_SL_NO=b.SL_NO AND DEPT_SL_NO in(select SL_NO from M4_BILL_DEPT_MASTER  WHERE  DEPT_CODE in (select DEPT_SL_NO from  m4_bill_user_master where emp_code=? and flag='Y')) AND DEPT_SL_NO='93' and a.PAY_TYPE=? " + para + " order by SL_NO");
                    stat.setString(1, USER_QUERY);
                    stat.setString(2, PAY_TYPE_QUERY);
                    resultSet = stat.executeQuery();
                    while (resultSet.next()) {
                        CashBillQueryBean bean = new CashBillQueryBean();
                        bean.setSL_NO(resultSet.getString("SL_NO"));
                        bean.setDEPT_SL_NO(resultSet.getString("DEPT_SL_NO"));
                        bean.setBILL_NO(resultSet.getString("BILL_NO"));
                        bean.setBILL_DATE(resultSet.getString("BILL_DATE"));
                        bean.setSUPPLIER_CODE(resultSet.getString("SUPPLIER_CODE"));
                        bean.setSUPPLIER_DESC(resultSet.getString("IDSUNM"));
                        bean.setBILL_AMOUNT(resultSet.getString("BILL_AMOUNT"));
                        bean.setGROSS_AMOUNT(resultSet.getString("GROSS_AMOUNT"));
                        bean.setDEPT_DESC(resultSet.getString("DEPT_DESC"));
                        bean.setFORWARD_DATE(resultSet.getString("FORWARD_DATE"));
                        bean.setFORWARD_USER(resultSet.getString("FORWARD_USER"));
                        bean.setBILL_WHLO(resultSet.getString("BILL_WHLO"));
                        bean.setREPORT_NO(resultSet.getString("REPORT_NO"));
                        bean.setREVERSE_SRVTAX(resultSet.getString("REVERSE_SRVTAX"));
                        bean.setSRVTAX_GL_CODE(resultSet.getString("SRVTAX_GL_CODE"));
                        bean.setBILL_YEAR(resultSet.getString("BILL_YEAR"));
                        bean.setACCOUNT_DATE(resultSet.getString("ACCOUNT_DATE"));
                        bean.setACCOUNT_USER(resultSet.getString("ACCOUNT_USER"));
                        bean.setERROR_MSG(null);
                        if(resultSet.getString("REVERSE_SRVTAX")!=null && resultSet.getString("REVERSE_SRVTAX").equals("1")){}
                        else {
                            PreparedStatement stat1 = conn.prepareStatement(" SELECT SUM(AMOUNT) AMOUNT FROM("
                                    + " SELECT A.SL_NO,B.BILL_SL_NO,MAX(A.PRODUCT_AMOUNT)+NVL(SUM(B.AMT),0) AMOUNT FROM M4_BILL_DETAIL A,M4_BILL_BREAKUP_DETAIL B"
                                    + " WHERE A.BILL_SL_NO=B.BILL_SL_NO(+) and A.SL_NO=B.LINE_SL_NO(+) AND A.BILL_SL_NO=?"
                                    + " GROUP BY A.SL_NO,B.BILL_SL_NO)");
                            stat1.setString(1, resultSet.getString("SL_NO"));
                            ResultSet resultSet1 = stat1.executeQuery();
                            if (resultSet1.next()) {
                                if (resultSet.getDouble("BILL_AMOUNT") == resultSet1.getDouble("AMOUNT")) {
                                } else {
                                    bean.setERROR_MSG("Bill Amount and Breakup mismated!!!");
                                }
                            }
                            if (resultSet1 != null) {
                                resultSet1.close();
                            }
                            if (stat1 != null) {
                                stat1.close();
                            }
                        }
                        
                        QUERY_LIST.add(bean);
                    }

                }
            }
            CONTROL_NO_LIST=null;
            DEL_CONTROL_NO_LIST=null;
            FORWARD_USER_LIST=null;
            UNAPPROVE_BILL_LIST=null;
            DELETE_BILL_LIST=null;
            APPROVE_BILL_LIST=null;
        } catch (Exception se) {
            System.out.println("CashBillEntryAction " + se);
            addActionError("CashBillEntryAction " + se);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return SUCCESS;
    }

    public String newpage() {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if (usrid == null) {
            addActionError("Session not valid!!!!");
            return "newpage";
        }
        Connection conn = null;
        BILLTYPE_LIST = new ArrayList<M3BILLBean>();
        BILLSUBTYPE_LIST = new ArrayList<M3BILLBean>();
        PCH_LIST = new ArrayList<String>();
        BILLCC_LIST = new ArrayList<M3BILLBean>();
        BILLPRODUCT_TYPE_LIST = new ArrayList<M3BILLBean>();
        UOM_LIST = new ArrayList<M3BILLBean>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            conn = new ConnectionSeplWeb().getConnection();
            WAREHOUSE_LIST=getWarehouse(conn,usrid);
            PCH_LIST = new ShahiInformationList().pchListWithBill();
            UOM_LIST = getUOMS(conn);
            if(WAREHOUSE!=null && WAREHOUSE.length()>0){
                BILLTYPE_LIST = getBillType(conn, usrid);
            }
            BILL_DATE = dateFormat.format(new Date());
        } catch(Exception se){
            System.out.println("CashBillEntryAction "+ se);
        }finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        BILL_TYPE_SLNO = null;
        BILL_SUBTYPE_SLNO = null;
        PCH = null;
        COST_CENTER = null;
        PRODUCT_GROUP = null;
        BILL_AMOUNT_BREAKUP = null;
        BILL_AMOUNT_NON_BREAKUP = null;
        BILL_AMOUNT_QUANTITY = null;
        BILL_AMOUNT_UOM = null;
        BILL_AMOUNT_HSN = null;
        VOUCHER_DESC=null;
        PARTNER_DESC=null;
        return "newpage";
    }
    
    public String sup(){
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if (usrid == null) {
            addActionError("Session not valid!!!!");
            return "sup";
        }
        SUPPLIER_LIST = new ArrayList<SupplierBean>();
        if(SUPPLIER_QUERY!=null && SUPPLIER_QUERY.length()>0){
            try {
                SUPPLIER_LIST = getSupplier("111", SUPPLIER_QUERY);
            } catch (Exception se) {
                System.out.println("CashBillEntryAction " + se);
            }
        }
        return "sup";
    }
    
    public String ajaxsubtype() {
        BILLSUBTYPE_LIST = new ArrayList<M3BILLBean>();
        BILLCC_LIST = new ArrayList<M3BILLBean>();
        if (BILL_TYPE != null && BILL_TYPE.length() > 0) {
            Connection conn = null;
            Connection connhris = null;
            try {
                conn = new ConnectionSeplWeb().getConnection();
                connhris = new connectionShahiHris().getConnection();
                BILLSUBTYPE_LIST=getBillSubType(conn, BILL_TYPE);
                BILLCC_LIST = getBillCC(connhris, BILL_TYPE);
            } catch (Exception se) {
                System.out.println("CashBillEntryAction " + se);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connhris != null) {
                    try {
                        connhris.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return SUCCESS;
    }
    
    public String ajaxprodtype(){
        BILLPRODUCT_TYPE_LIST = new ArrayList<M3BILLBean>();
        if (BILL_SUBTYPE != null && BILL_SUBTYPE.length() > 0) {
            Connection conn = null;
            Connection connhris = null;
            try {
                conn = new ConnectionSeplWeb().getConnection();
                BILLPRODUCT_TYPE_LIST=getProdType(conn, BILL_SUBTYPE);
            } catch (Exception se) {
                System.out.println("CashBillEntryAction " + se);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connhris != null) {
                    try {
                        connhris.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return SUCCESS;
    }
    
    public List getWarehouse(Connection conn, String USER_ID){
        List<String> whlolist = new ArrayList<String>();
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        try{
            stat = conn.prepareStatement("SELECT WAREHOUSE FROM M4_BILL_USER_MASTER WHERE EMP_CODE=? AND DEPT_SL_NO='70'");
            stat.setString(1, USER_ID);
            result = stat.executeQuery();
            if(result.next()){
                stat1 = conn.prepareStatement("SELECT M4WHLO FROM M4_WHLO_MASTER WHERE M4WHLO IN("+result.getString("WAREHOUSE")+") ORDER BY 1");
                result1 = stat1.executeQuery();
                while (result1.next()) {
                    whlolist.add(result1.getString("M4WHLO"));
                }
            }
        } catch(SQLException se){
            System.out.println("CashBillEntryAction "+ se);
        } finally{
          if(result!=null){
              try {
                  result.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          if(stat!=null){
              try {
                  stat.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
        return whlolist;
    }
    
    public List getUOMS(Connection conn){
        List<M3BILLBean> whlolist = new ArrayList<M3BILLBean>();
        PreparedStatement stat = null;
        ResultSet result = null;
        try{
            stat = conn.prepareStatement("SELECT CTSTKY,INITCAP(CTTX15) DESCR FROM PRODBI.CSYTAB@AMS WHERE CTSTCO='UNIT' AND CTCONO='111' ORDER BY INITCAP(CTTX15)");
            result = stat.executeQuery();
            while(result.next()){
                whlolist.add(new M3BILLBean(result.getString("CTSTKY"), result.getString("DESCR")));
            }
        } catch(SQLException se){
            System.out.println("CashBillEntryAction "+ se);
        } finally{
          if(result!=null){
              try {
                  result.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          if(stat!=null){
              try {
                  stat.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
        return whlolist;
    }
    
    public List getBillType(Connection conn, String USER_ID){
        List<M3BILLBean> whlolist = new ArrayList<M3BILLBean>();
        PreparedStatement stat = null;
        ResultSet result = null;
        try{
            stat = conn.prepareStatement("SELECT SL_NO,DEPT_SL_NO,TYPE_CODE,TYPE_DESC,FLAG from M4_BILL_TYPE_MASTER where DEPT_SL_NO='93' and FLAG='Y' AND TYPE_CODE in(SELECT BILL_TYPE FROM M4_BILL_USER_MASTER WHERE EMP_CODE=?)  ORDER BY  TYPE_DESC");
            stat.setString(1, USER_ID);
            result = stat.executeQuery();
            while(result.next()){
                whlolist.add(new M3BILLBean(result.getString("SL_NO"), result.getString("DEPT_SL_NO"), result.getString("TYPE_CODE"), result.getString("TYPE_DESC"), result.getString("FLAG"),""));
            }
        } catch(SQLException se){
            System.out.println("CashBillEntryAction "+ se);
        } finally{
          if(result!=null){
              try {
                  result.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          if(stat!=null){
              try {
                  stat.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
        return whlolist;
    }
    
    public List getBillSubType(Connection conn, String srNo){
        List<M3BILLBean> whlolist = new ArrayList<M3BILLBean>();
        PreparedStatement stat = null;
        ResultSet result = null;
        try{
            stat = conn.prepareStatement("SELECT SL_NO,TYPE_SL_NO,SUB_TYPE_CODE,SUB_TYPE_DESC,FLAG FROM M4_BILL_SUB_TYPE_MASTER WHERE FLAG='Y' AND TYPE_SL_NO=? AND SUB_TYPE_CODE IS NOT NULL ORDER BY  SL_NO");
            stat.setString(1, srNo);
            result = stat.executeQuery();
            while(result.next()){
                whlolist.add(new M3BILLBean(result.getString("SL_NO"), result.getString("TYPE_SL_NO"), result.getString("SUB_TYPE_CODE"), result.getString("SUB_TYPE_DESC"), result.getString("FLAG"),"",""));
            }
        } catch(SQLException se){
            System.out.println("CashBillEntryAction "+ se);
        } finally{
          if(result!=null){
              try {
                  result.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          if(stat!=null){
              try {
                  stat.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
        return whlolist;
    }
    
    public List getBillCC(Connection conn, String srNo){
        List<M3BILLBean> whlolist = new ArrayList<M3BILLBean>();
        PreparedStatement stat = null;
        ResultSet result = null;
        try{
            stat = conn.prepareStatement("SELECT SL_NO,EAAITM,EATX40 FROM SEPLWEB.M4_CC_MASTER@IBM.WORLD@IBM A,PRODBI.FCHACC B WHERE EACONO=111 AND A.CC_CODE=B.EAAITM AND EAAITP=3 AND DEPT_SL_NO='93' AND TYPE_SL_NO=? AND EAAT12=0 ORDER BY 2");
            stat.setString(1, srNo);
            result = stat.executeQuery();
            while(result.next()){
                whlolist.add(new M3BILLBean(result.getString(1), result.getString(2)+"-"+ result.getString(3).toUpperCase()));
            }
        } catch(SQLException se){
            System.out.println("CashBillEntryAction "+ se);
        } finally{
          if(result!=null){
              try {
                  result.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          if(stat!=null){
              try {
                  stat.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
        return whlolist;
    }
    
    public List getProdType(Connection conn, String srNo){
        List<M3BILLBean> whlolist = new ArrayList<M3BILLBean>();
        PreparedStatement stat = null;
        ResultSet result = null;
        try{
            stat = conn.prepareStatement("select SL_NO,SUB_TYPE_SL_NO,PRODUCT_CODE,PRODUCT_DESC,FLAG FROM M4_BILL_PRODUCT_MASTER WHERE SUB_TYPE_SL_NO=?  ORDER BY  PRODUCT_DESC");
            stat.setString(1, srNo);
            result = stat.executeQuery();
            while(result.next()){
                whlolist.add(new M3BILLBean(result.getString("SL_NO"), result.getString("SUB_TYPE_SL_NO"), result.getString("PRODUCT_CODE"), result.getString("PRODUCT_DESC"), result.getString("FLAG"),""));
            }
        } catch(SQLException se){
            System.out.println("CashBillEntryAction "+ se);
        } finally{
          if(result!=null){
              try {
                  result.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          if(stat!=null){
              try {
                  stat.close();
              } catch (SQLException ex) {
                  Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
        return whlolist;
    }
    
    public static List<SupplierBean> getSupplier(String CONO, String query) throws SQLException {
        List<SupplierBean> supplierBeans = new ArrayList<SupplierBean>();
        if (query != null && query.length() > 0) {
            Connection conn = null;
            PreparedStatement stat = null;
            ResultSet resultSet = null;
            try {
                conn = new connectiondb2().getConnection();
                stat = conn.prepareStatement("SELECT IDSUNO,IDSUNM,IDCFI3 FROM CIDMAS WHERE IDCONO=? AND (IDSUNO LIKE ? OR IDSUNM LIKE ?) AND IDSTAT='20' AND IDSUTY='8'");
                stat.setString(1, CONO);
                stat.setString(2, query.toUpperCase() + "%");
                stat.setString(3, "%" + query.toUpperCase() + "%");
                resultSet = stat.executeQuery();
                MDBREADMI mdbreadmi = new MDBREADMI();
                mdbreadmi.connect();
                while (resultSet.next()) {
                    MDBREADMIGetCSYTAB20Bean bean = mdbreadmi.GetCSYTAB20("CFS3", resultSet.getString("IDCFI3"), "", "");
                    String GSTF = resultSet.getString("IDCFI3");
                    String GSTD = bean.getTX15();
                    List<SupplierAddressBean> supplierAddressMasters = SupplierAddressMaster.getSupplierAddressWithoutConnection(conn, CONO, resultSet.getString("IDSUNO"), "1",mdbreadmi);
                    for (SupplierAddressBean supplierAddressBean : supplierAddressMasters) {
                        supplierBeans.add(new SupplierBean(resultSet.getString("IDSUNO"), resultSet.getString("IDSUNM"), GSTF, GSTD, supplierAddressBean));
                    }
                }
                mdbreadmi.destroyMI();
                mdbreadmi = null;

            } catch (SQLException se) {
                System.out.println("com.shahi.SupplierMaster " + se);
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return supplierBeans;
    }
    
    public String searchhsn() throws SQLException {
        HSN_LIST = new ArrayList();
        List HSN_LIST_TEMP = new ArrayList();
        if (HSN_CODE != null && HSN_CODE.length() > 0) {
            Connection conn = null;
            PreparedStatement stat = null;
            ResultSet resultSet = null;
            try {
                conn = new connectiondb2().getConnection();
                stat = conn.prepareStatement("SELECT CKCONO, CKCSNO, CKINYN, CKSUPL, CKTX40, CKTX15, CKSPFA, CKTXID, CKRGDT, CKRGTM, CKLMDT, CKCHNO, CKCHID FROM CSYCSN WHERE CKCONO=? AND CKCSNO LIKE ? AND CKTX40 NOT LIKE '(X)%'");
                stat.setString(1, "111");
                stat.setString(2, HSN_CODE + "%");
                resultSet = stat.executeQuery();
                while (resultSet.next()) {
                    MDBREADMILstCSYCSN00Bean bean = new MDBREADMILstCSYCSN00Bean();
                    bean.setCSNO(resultSet.getString("CKCSNO"));
                    bean.setINYN(resultSet.getString("CKINYN"));
                    bean.setSUPL(resultSet.getString("CKSUPL"));
                    bean.setTX40(resultSet.getString("CKTX40"));
                    bean.setTX15(resultSet.getString("CKTX15"));
                    bean.setSPFA(resultSet.getString("CKSPFA"));
                    bean.setTXID(resultSet.getString("CKTXID"));
                    bean.setRGDT(resultSet.getString("CKRGDT"));
                    bean.setRGTM(resultSet.getString("CKRGTM"));
                    bean.setLMDT(resultSet.getString("CKLMDT"));
                    bean.setCHNO(resultSet.getString("CKCHNO"));
                    bean.setCHID(resultSet.getString("CKCHID"));
                    HSN_LIST.add(bean);
                }
            } catch (Exception e) {
                addActionError("HSN Search Error " + e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return "hsnsearch";
    }
    
    public String gstcalc() throws SQLException{
        GST_LIST = new ArrayList<GetTaxRateBean>();
        if(WAREHOUSE!=null && WAREHOUSE.length()>0){
            Connection conn = null;
            PreparedStatement stat = null;
            ResultSet resultSet = null;
            try {
                conn = new ConnectionSeplWeb().getConnection();
                Map<String, String> mpgl = new HashMap<String, String>();
                stat = conn.prepareStatement("select HSN_CODE,GL_CODE from M4_BILL_HSN_GL where flag='Y' and REV_GL_CODE is null ");
                resultSet= stat.executeQuery();
                while (resultSet.next()) {
                    mpgl.put(resultSet.getString("HSN_CODE"), resultSet.getString("GL_CODE"));
                }
                if(resultSet!=null) resultSet.close();
                if(stat!=null) stat.close();
                
                stat = conn.prepareStatement("select M4DIVI,M4STAT,M4GEOC from M4_WHLO_MASTER where M4CONO='111' and M4WHLO=? and M4GEOC is not null");
                stat.setString(1, WAREHOUSE);
                resultSet = stat.executeQuery();
                while (resultSet.next()) {
                    DIVISION = resultSet.getString("M4DIVI");
                    STATE = resultSet.getString("M4STAT");
                    GEOC = resultSet.getString("M4GEOC");

                }
                if(resultSet!=null) resultSet.close();
                if(stat!=null) stat.close();
                SimpleDateFormat fn = new SimpleDateFormat("yyyyMMdd");
                if(STATE!=null && STATE.length()>0){
                    TXZ050MI MI = new TXZ050MI();
                    MI.connect();//2/3
                    TXZ050MIGetTaxRateBean bn = new TXZ050MIGetTaxRateBean();
                    if (CSCD != null && CSCD.trim().equals("IN")) {
                        bn = MI.GetTaxRate("111", DIVISION, GEOC, "2", "5", ECAR, STATE, HSN_CODE.toUpperCase(), "", fn.format(new Date()));
                    } else {
                        bn = MI.GetTaxRate("111", DIVISION, GEOC, "2", "3", "IMP", HSN_CODE.toUpperCase(), "", "", fn.format(new Date()));

                    }
                    MI.destroyMI();
                    MI = null;
                    if (bn != null && bn.getTAXC() != null && bn.getTAXC().length() > 0) {
                        if (bn.getTAX1() != null && bn.getTAX1().length() > 0 && Double.parseDouble(bn.getTAX1()) > 0) {
                            GetTaxRateBean bn1 = new GetTaxRateBean();
                            bn1.setTAX_CODE(bn.getTAXC());
                            bn1.setTAXC("SGST");
                            bn1.setTAX1(bn.getTAX1());
                            bn1.setTAX2(mpgl.get("SGST"));
                            if (BREAK_AMOUNT != null && BREAK_AMOUNT.length()>0) {
                                double dt = Double.parseDouble(bn.getTAX1()) * 0.01 * Double.parseDouble(BREAK_AMOUNT);
                                bn1.setCALVAL(Math.round(dt * 100.0) / 100.0 + "");
                            }
                            GST_LIST.add(bn1);
                        }
                        if (bn.getTAX2() != null && bn.getTAX2().length() > 0 && Double.parseDouble(bn.getTAX2()) > 0) {
                            GetTaxRateBean bn1 = new GetTaxRateBean();
                            bn1.setTAX_CODE(bn.getTAXC());
                            bn1.setTAXC("CGST");
                            bn1.setTAX2(mpgl.get("CGST"));
                            bn1.setTAX1(bn.getTAX2());
                            if (BREAK_AMOUNT != null && BREAK_AMOUNT.length()>0) {
                                double dt = Double.parseDouble(bn.getTAX2()) * 0.01 * Double.parseDouble(BREAK_AMOUNT);
                                bn1.setCALVAL(Math.round(dt * 100.0) / 100.0 + "");
                            }
                            GST_LIST.add(bn1);
                        }
                        if (bn.getTAX3() != null && bn.getTAX3().length() > 0 && Double.parseDouble(bn.getTAX3()) > 0) {
                            GetTaxRateBean bn1 = new GetTaxRateBean();
                            bn1.setTAX_CODE(bn.getTAXC());
                            bn1.setTAXC("IGST");
                            bn1.setTAX2(mpgl.get("IGST"));
                            bn1.setTAX1(bn.getTAX3());
                            if (BREAK_AMOUNT != null && BREAK_AMOUNT.length()>0) {
                                double dt = Double.parseDouble(bn.getTAX3()) * 0.01 * Double.parseDouble(BREAK_AMOUNT);
                                bn1.setCALVAL(Math.round(dt * 100.0) / 100.0 + "");

                            }
                            GST_LIST.add(bn1);
                        }
                    }
                }
            } catch (Exception e) {
                addActionError("HSN Search Error " + e.getMessage());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return SUCCESS;
    }
    
    public String save() {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if (usrid == null) {
            addActionError("Session not valid!!!!");
            return "newpage";
        }
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try {
            conn = new ConnectionSeplWeb().getConnection();
            conn.setAutoCommit(false);
            int flag = 0;
            if (BILL_NO != null && BILL_NO.length() > 0 && BILL_AMOUNT_BREAKUP != null && BILL_AMOUNT_BREAKUP.size() > 0) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formattermonth = new SimpleDateFormat("MM");
                SimpleDateFormat formatteryear = new SimpleDateFormat("yyyy");
                int month = Integer.parseInt(formattermonth.format(formatter.parse(BILL_DATE)));
                int year = Integer.parseInt(formatteryear.format(formatter.parse(BILL_DATE)));
                if (month <= 3) {
                    BILL_YEAR = (year - 1) + "";
                } else {
                    BILL_YEAR = year + "";
                }
                
                if (UPD_MAST != null && UPD_MAST.length() > 0 && MAST_SL_NO != null && MAST_SL_NO.length() > 0) {
                    stat = conn.prepareStatement("UPDATE M4_BILL_MASTER SET BILL_AMOUNT=?,REVERSE_SRVTAX=? WHERE SL_NO=?");
                    stat.setString(1, BILL_AMOUNT);
                    stat.setString(2, REVERSE_SRVTAX == null ? "0" : REVERSE_SRVTAX);//REVERSE_SRVTAX
                    stat.setString(3, MAST_SL_NO);
                    flag = stat.executeUpdate();
                    if (stat != null) {
                        stat.close();
                    }
                    int breakuprunning = 0;
                    stat = conn.prepareStatement("DELETE FROM M4_BILL_DETAIL WHERE BILL_SL_NO=?");
                    stat.setString(1, MAST_SL_NO);
                    stat.executeUpdate();
                    if (stat != null) {
                        stat.close();
                    }
                    stat = conn.prepareStatement("DELETE FROM M4_BILL_BREAKUP_DETAIL WHERE BILL_SL_NO=?");
                    stat.setString(1, MAST_SL_NO);
                    stat.executeUpdate();
                    if (stat != null) {
                        stat.close();
                    }
                    for (int i = 0; i < BILL_TYPE_SLNO.size(); i++) {
                        if (BILL_TYPE_SLNO.get(i).toString() != null && BILL_TYPE_SLNO.get(i).toString().length() > 0 && BILL_AMOUNT_BREAKUP.get(i).toString() != null && BILL_AMOUNT_BREAKUP.get(i).toString().length() > 0 && Double.parseDouble(BILL_AMOUNT_BREAKUP.get(i).toString()) > 0) {
                            String SL_NO = "";                           
                            
                            stat = conn.prepareStatement("select m4_bill_detail_Sq.nextval from dual");
                            resultSet = stat.executeQuery();
                            if (resultSet.next()) {
                                SL_NO = resultSet.getString(1);
                            }
                            if (resultSet != null) {
                                resultSet.close();
                            }
                            if (stat != null) {
                                stat.close();
                            }

                            stat = conn.prepareStatement("INSERT INTO M4_BILL_DETAIL(SL_NO,BILL_SL_NO,CC_CODE,TYPE_SL_NO,SUB_TYPE_SL_NO,PRODUCT_SL_NO,PRODUCT_AMOUNT,NON_GST_AMOUNT,TDATE,USER_ID,PCH,BILL_DATE1,BILL_DATE2,REMARKS,TAXABLE,HSN_CODE,VOUCHER_DESC,PRODUCT_QUANTITY,UOM,PARTNER_DESC) values(?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?)");
                            stat.setString(1, SL_NO);
                            stat.setString(2, MAST_SL_NO);
                            stat.setString(3, COST_CENTER.get(i));
                            stat.setString(4, BILL_TYPE_SLNO.get(i));
                            stat.setString(5, BILL_SUBTYPE_SLNO.get(i));
                            stat.setString(6, PRODUCT_GROUP.get(i));
                            stat.setString(7, BILL_AMOUNT_BREAKUP.get(i));
                            stat.setString(8, BILL_AMOUNT_NON_BREAKUP.get(i));
                            stat.setString(9, usrid);
                            stat.setString(10, PCH.get(i));
                            stat.setString(11, "");//BILL_DATE1
                            stat.setString(12, "");//BILL_DATE2
                            stat.setString(13, "");//REMARK
                            stat.setString(14, "");//CHKINPUT.get(i).toString()
                            stat.setString(15, BILL_AMOUNT_HSN.get(i));
                            stat.setString(16, VOUCHER_DESC.get(i).toUpperCase().trim());
                            stat.setString(17, BILL_AMOUNT_QUANTITY.get(i));
                            stat.setString(18, BILL_AMOUNT_UOM.get(i));
                            stat.setString(19, PARTNER_DESC.get(i).toUpperCase().trim());
                            flag = stat.executeUpdate();
                            if (stat != null) {
                                stat.close();
                            }
                            if (BREAKUP_COUNT.get(i) != null && Integer.parseInt(BREAKUP_COUNT.get(i)) > 0) {
                                for (int j = 0; j < Integer.parseInt(BREAKUP_COUNT.get(i)); j++) {
                                    String SUB_SL_NO = "";
                                    stat = conn.prepareStatement("SELECT M4_BILL_BREAKUP_DETAIL_SQ.NEXTVAL FROM DUAL");
                                    resultSet = stat.executeQuery();
                                    if (resultSet.next()) {
                                        SUB_SL_NO = resultSet.getString(1);
                                    }
                                    if (resultSet != null) {
                                        resultSet.close();
                                    }
                                    if (stat != null) {
                                        stat.close();
                                    }

                                    PreparedStatement statb = conn.prepareStatement("INSERT INTO M4_BILL_BREAKUP_DETAIL(SL_NO, COST_ELEMENT, AMT, TDATE, USER_ID, BILL_SL_NO, GST_PER, TAX_CODE,LINE_SL_NO) VALUES(?,?,?,SYSDATE,?,?,?,?,?)");
                                    statb.setString(1, SUB_SL_NO);
                                    statb.setString(2, COST_ELEMENT.get(breakuprunning));
                                    statb.setString(3, COST_BREAKUP_AMT.get(breakuprunning));
                                    statb.setString(4, usrid);
                                    statb.setString(5, MAST_SL_NO);
                                    statb.setString(6, COST_BREAKUP_TAX.get(breakuprunning));
                                    statb.setString(7, COST_BREAKUP_TAX_CODE.get(breakuprunning));
                                    statb.setString(8, SL_NO);
                                    statb.executeUpdate();
                                    ++breakuprunning;
                                }
                            }
                        }
                    }
                } else {
                    PreparedStatement stats = conn.prepareStatement("SELECT * FROM M4_BILL_MASTER WHERE BILL_NO=? AND SUPPLIER_CODE=? AND BILL_YEAR=?");
                    stats.setString(1, BILL_NO.trim().toUpperCase());
                    stats.setString(2, SUPPLIER.trim());
                    stats.setString(3, BILL_YEAR);
                    ResultSet resultSets = stats.executeQuery();
                    if (resultSets.next()) {// && (SUPPLIER!=null && !SUPPLIER.trim().equalsIgnoreCase("CSF00054") removed check by Deo Chandra(Finance)
                        addActionError("Same Bill No " + BILL_NO.trim() + " exist in Financial Year " + BILL_YEAR);
                        return newpage();
                    } else {
                        if (MAST_SL_NO == null || (MAST_SL_NO!=null && MAST_SL_NO.length()==0)) {
                            stat = conn.prepareStatement("select m4_bill_master_Sq.nextval from dual");
                            resultSet = stat.executeQuery();
                            if (resultSet.next()) {
                                MAST_SL_NO = resultSet.getString(1);
                            }
                            if (resultSet != null) {
                                resultSet.close();
                            }
                            if (stat != null) {
                                stat.close();
                            }
                        }

                        stat = conn.prepareStatement("INSERT INTO M4_BILL_MASTER(SL_NO,DEPT_SL_NO,BILL_NO,BILL_DATE,SUPPLIER_CODE,BILL_AMOUNT,TDATE,USER_ID,GROSS_AMOUNT,BILL_WHLO,BILL_YEAR,remarks,REVERSE_SRVTAX,REVERSE_SRVTAX_RATE,SRVTAX_GL_CODE,REVERSE_SRVTAX_CODE,NON_SRVTAX_AMOUNT,SASUNM,SAADR1,SAADR2,SAADR3,SAADR4,SATOWN,SAECAR,SAPONO,SACSCD,HSN_CODE,NON_GST_AMOUNT,SACONM,SAADID,SASTDT,PAY_TYPE) values(?,?,?,to_date(?,'dd/mm/yyyy'),?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,upper(?),?,?,?,?,?)");
                        stat.setString(1, MAST_SL_NO);
                        stat.setString(2, "93");//DEPT_SL_NO//cash sr no
                        stat.setString(3, BILL_NO.toUpperCase());
                        stat.setString(4, BILL_DATE);
                        stat.setString(5, SUPPLIER.trim());
                        stat.setString(6, BILL_AMOUNT);
                        stat.setString(7, usrid);
                        stat.setString(8, "");//GROSS_AMOUNT
                        stat.setString(9, WAREHOUSE);
                        stat.setString(10, BILL_YEAR);
                        stat.setString(11, MAST_REMARKS);
                        stat.setString(12, REVERSE_SRVTAX == null ? "0" : REVERSE_SRVTAX);//REVERSE_SRVTAX
                        stat.setString(13, "");//REVERSE_SRVTAX_RATE
                        stat.setString(14, "");//SRVTAX_GL_CODE
                        stat.setString(15, "");//REVERSE_SRVTAX_CODE
                        stat.setString(16, "");//
                        stat.setString(17, SUPPLIER_DESC);
                        stat.setString(18, ADR1);
                        stat.setString(19, ADR2);
                        stat.setString(20, ADR3);
                        stat.setString(21, ADR4);
                        stat.setString(22, TOWN);
                        stat.setString(23, ECAR);
                        stat.setString(24, PONO);
                        stat.setString(25, CSCD);
                        stat.setString(26, "");//HSN_CODE
                        stat.setString(27, "");//BREAK_AMOUNT_NON_GST                    
                        stat.setString(28, CONM);
                        stat.setString(29, ADID.trim());
                        stat.setString(30, RGDT);
                        stat.setString(31, PAY_TYPE);
                        flag = stat.executeUpdate();
                        if (stat != null) {
                            stat.close();
                        }
                        int breakuprunning = 0;
                        for (int i = 0; i < BILL_TYPE_SLNO.size(); i++) {
                            if (BILL_TYPE_SLNO.get(i).toString() != null && BILL_TYPE_SLNO.get(i).toString().length() > 0 && BILL_AMOUNT_BREAKUP.get(i).toString() != null && BILL_AMOUNT_BREAKUP.get(i).toString().length() > 0 && Double.parseDouble(BILL_AMOUNT_BREAKUP.get(i).toString()) > 0) {
                                String SL_NO = "";

                                stat = conn.prepareStatement("select m4_bill_detail_Sq.nextval from dual");
                                resultSet = stat.executeQuery();
                                if (resultSet.next()) {
                                    SL_NO = resultSet.getString(1);
                                }
                                if (resultSet != null) {
                                    resultSet.close();
                                }
                                if (stat != null) {
                                    stat.close();
                                }

                                stat = conn.prepareStatement("INSERT INTO M4_BILL_DETAIL(SL_NO,BILL_SL_NO,CC_CODE,TYPE_SL_NO,SUB_TYPE_SL_NO,PRODUCT_SL_NO,PRODUCT_AMOUNT,NON_GST_AMOUNT,TDATE,USER_ID,PCH,BILL_DATE1,BILL_DATE2,REMARKS,TAXABLE,HSN_CODE,VOUCHER_DESC,PRODUCT_QUANTITY,UOM,PARTNER_DESC) values(?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?)");
                                stat.setString(1, SL_NO);
                                stat.setString(2, MAST_SL_NO);
                                stat.setString(3, COST_CENTER.get(i));
                                stat.setString(4, BILL_TYPE_SLNO.get(i));
                                stat.setString(5, BILL_SUBTYPE_SLNO.get(i));
                                stat.setString(6, PRODUCT_GROUP.get(i));
                                stat.setString(7, BILL_AMOUNT_BREAKUP.get(i));
                                stat.setString(8, BILL_AMOUNT_NON_BREAKUP.get(i));
                                stat.setString(9, usrid);
                                stat.setString(10, PCH.get(i));
                                stat.setString(11, "");//BILL_DATE1
                                stat.setString(12, "");//BILL_DATE2
                                stat.setString(13, REMARKS.get(i).toString());
                                stat.setString(14, "");//CHKINPUT.get(i).toString()
                                stat.setString(15, BILL_AMOUNT_HSN.get(i));
                                stat.setString(16, VOUCHER_DESC.get(i).toUpperCase().trim());
                                stat.setString(17, BILL_AMOUNT_QUANTITY.get(i));
                                stat.setString(18, BILL_AMOUNT_UOM.get(i));
                                stat.setString(19, PARTNER_DESC.get(i).toUpperCase().trim());
                                flag = stat.executeUpdate();
                                if (stat != null) {
                                    stat.close();
                                }
                                if (BREAKUP_COUNT.get(i) != null && Integer.parseInt(BREAKUP_COUNT.get(i)) > 0) {
                                    for (int j = 0; j < Integer.parseInt(BREAKUP_COUNT.get(i)); j++) {
                                        String SUB_SL_NO = "";
                                        stat = conn.prepareStatement("SELECT M4_BILL_BREAKUP_DETAIL_SQ.NEXTVAL FROM DUAL");
                                        resultSet = stat.executeQuery();
                                        if (resultSet.next()) {
                                            SUB_SL_NO = resultSet.getString(1);
                                        }
                                        if (resultSet != null) {
                                            resultSet.close();
                                        }
                                        if (stat != null) {
                                            stat.close();
                                        }

                                        PreparedStatement statb = conn.prepareStatement("INSERT INTO M4_BILL_BREAKUP_DETAIL(SL_NO, COST_ELEMENT, AMT, TDATE, USER_ID, BILL_SL_NO, GST_PER, TAX_CODE,LINE_SL_NO) VALUES(?,?,?,SYSDATE,?,?,?,?,?)");
                                        statb.setString(1, SUB_SL_NO);
                                        statb.setString(2, COST_ELEMENT.get(breakuprunning));
                                        statb.setString(3, COST_BREAKUP_AMT.get(breakuprunning));
                                        statb.setString(4, usrid);
                                        statb.setString(5, MAST_SL_NO);
                                        statb.setString(6, COST_BREAKUP_TAX.get(breakuprunning));
                                        statb.setString(7, COST_BREAKUP_TAX_CODE.get(breakuprunning));
                                        statb.setString(8, SL_NO);
                                        statb.executeUpdate();
                                        ++breakuprunning;
                                    }
                                }
                            }
                        }
                    }
                    if (resultSets != null) {
                        resultSets.close();
                    }
                    if (stats != null) {
                        stats.close();
                    }
                }
            }
            conn.commit();
            addActionError("Bill Save Successfully!!! Cash Id "+MAST_SL_NO);
        } catch (Exception e) {
            System.out.println("CashBillEntryAction " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
        BILL_TYPE_SLNO = null;
        BILL_SUBTYPE_SLNO = null;
        PCH = null;
        COST_CENTER = null;
        PRODUCT_GROUP = null;
        VOUCHER_DESC = null;
        PARTNER_DESC = null;
        BILL_AMOUNT_BREAKUP = null;
        BILL_AMOUNT_NON_BREAKUP = null;
        BILL_AMOUNT_QUANTITY = null;
        BILL_AMOUNT_UOM = null;
        BILL_AMOUNT_HSN = null;
        edit();
        return "newpage";
    }
    
    public String edit() {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        
        WAREHOUSE_LIST = new ArrayList<String>();
        BILLTYPE_LIST = new ArrayList<M3BILLBean>();
        BILLSUBTYPE_LIST = new ArrayList<M3BILLBean>();
        PCH_LIST = new ArrayList<String>();
        BILLCC_LIST = new ArrayList<M3BILLBean>();
        BILLPRODUCT_TYPE_LIST = new ArrayList<M3BILLBean>();
        UOM_LIST = new ArrayList<M3BILLBean>();
        
        if (usrid == null) {
            addActionError("Session not valid!!!!");
            return "newpage";
        }
        Connection conn = null;
        Connection connhris = null;
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        PreparedStatement stat1 = null;
        ResultSet resultSet1 = null;
        try {
            DETAIL_LIST = new ArrayList<CashBillDetailBean>();
            if (MAST_SL_NO != null && MAST_SL_NO.length() > 0) {                
                conn = new ConnectionSeplWeb().getConnection();
                connhris = new connectionShahiHris().getConnection();
                
                WAREHOUSE_LIST = getWarehouse(conn, usrid);
                PCH_LIST = new ShahiInformationList().pchListWithBill();
                UOM_LIST = getUOMS(conn);
                
                stat = conn.prepareStatement("SELECT SL_NO, DEPT_SL_NO, BILL_NO, to_char(BILL_DATE,'dd/MM/yyyy') BILL_DATE, SUPPLIER_CODE, BILL_AMOUNT, TDATE, USER_ID, GROSS_AMOUNT, BILL_WHLO, BILL_YEAR, FORWARD_USER, FORWARD_DATE, ACCOUNT_USER, ACCOUNT_DATE, REMARKS, REPORT_NO, REC_ACC_USER, REC_ACC_DATE, REVERSE_SRVTAX, REVERSE_SRVTAX_RATE, REJ_USER, REJ_DATE, OLD_REPORT_NO, SRVTAX_GL_CODE, REVERSE_SRVTAX_CODE, NON_SRVTAX_AMOUNT, SASUNM, SAADR1, SAADR2, SAADR3, SAADR4, SATOWN, SAECAR, SAPONO, SACSCD, HSN_CODE, NON_GST_AMOUNT,SACONM,SAADID,SASTDT,REMARKS,PAY_TYPE FROM M4_BILL_MASTER WHERE SL_NO=?");
                stat.setString(1, MAST_SL_NO);
                resultSet = stat.executeQuery();
                if(resultSet.next()){
                    BILL_NO = resultSet.getString("BILL_NO");
                    BILL_DATE = resultSet.getString("BILL_DATE");
                    SUPPLIER = resultSet.getString("SUPPLIER_CODE");
                    BILL_AMOUNT = resultSet.getString("BILL_AMOUNT");
                    REVERSE_SRVTAX = resultSet.getString("REVERSE_SRVTAX");
                    WAREHOUSE = resultSet.getString("BILL_WHLO");
                    SUPPLIER_DESC = resultSet.getString("SASUNM");
                    ADID = resultSet.getString("SAADID");
                    RGDT = resultSet.getString("SASTDT");
                    CONM = resultSet.getString("SACONM");
                    ADR1 = resultSet.getString("SAADR1");
                    ADR2 = resultSet.getString("SAADR2");
                    ADR3 = resultSet.getString("SAADR3");
                    ADR4 = resultSet.getString("SAADR4");
                    TOWN = resultSet.getString("SATOWN");
                    ECAR = resultSet.getString("SAECAR");
                    PONO = resultSet.getString("SAPONO");
                    CSCD = resultSet.getString("SACSCD");
                    STATE = resultSet.getString("SAECAR");
                    REPORT_NO = resultSet.getString("REPORT_NO");
                    USER_ID = resultSet.getString("USER_ID");
                    MAST_REMARKS = resultSet.getString("REMARKS");
                    PAY_TYPE = resultSet.getString("PAY_TYPE");
                }
                if(resultSet!=null) resultSet.close();
                if(stat!=null) stat.close();
                
                stat = conn.prepareStatement("SELECT A.SL_NO, A.BILL_SL_NO, A.CC_CODE, A.TYPE_SL_NO, A.SUB_TYPE_SL_NO, A.PRODUCT_SL_NO, A.PRODUCT_AMOUNT, A.TDATE, A.USER_ID,"
                            +" A.PCH, A.BILL_DATE1, A.BILL_DATE2, A.REMARKS, A.TAXABLE, A.NON_GST_AMOUNT, A.HSN_CODE,B.TYPE_DESC||'-'||TYPE_CODE TYPE_SL_NO_DESC,"
                            +" C.SUB_TYPE_DESC||'-'||SUB_TYPE_CODE SUB_TYPE_SL_NO_DESC,E.EAAITM ||'-'|| E.EATX40 CC_CODE_DESC,PRODUCT_DESC||'-'||PRODUCT_CODE PRODUCT_SL_NO_DESC,A.VOUCHER_DESC,"
                            +" A.PRODUCT_QUANTITY,A.UOM,A.PARTNER_DESC"
                            +" FROM M4_BILL_DETAIL A, M4_BILL_TYPE_MASTER B,M4_BILL_SUB_TYPE_MASTER C,M4_CC_MASTER D,PRODBI.FCHACC@AMS E,M4_BILL_PRODUCT_MASTER F"
                            +" WHERE B.DEPT_SL_NO='93' AND A.TYPE_SL_NO=B.SL_NO AND A.SUB_TYPE_SL_NO=C.SL_NO AND E.EACONO=111"
                            +" AND D.CC_CODE=E.EAAITM AND E.EAAITP=3 AND D.DEPT_SL_NO='93' AND A.CC_CODE=D.SL_NO AND A.PRODUCT_SL_NO=F.SL_NO AND"
                            +" A.BILL_SL_NO=? ORDER BY A.SL_NO");
                stat.setString(1, MAST_SL_NO);
                resultSet = stat.executeQuery();
                while(resultSet.next()){
                    CashBillDetailBean bean = new CashBillDetailBean();
                    bean.setSL_NO(resultSet.getString("SL_NO"));
                    bean.setBILL_SL_NO(resultSet.getString("BILL_SL_NO"));
                    bean.setCC_CODE(resultSet.getString("CC_CODE"));
                    bean.setCC_CODE_DESC(resultSet.getString("CC_CODE_DESC"));
                    bean.setTYPE_SL_NO(resultSet.getString("TYPE_SL_NO"));
                    bean.setTYPE_SL_NO_DESC(resultSet.getString("TYPE_SL_NO_DESC"));
                    bean.setSUB_TYPE_SL_NO(resultSet.getString("SUB_TYPE_SL_NO"));
                    bean.setSUB_TYPE_SL_NO_DESC(resultSet.getString("SUB_TYPE_SL_NO_DESC"));
                    bean.setPRODUCT_SL_NO(resultSet.getString("PRODUCT_SL_NO"));
                    bean.setPRODUCT_SL_NO_DESC(resultSet.getString("PRODUCT_SL_NO_DESC"));
                    bean.setPRODUCT_AMOUNT(resultSet.getString("PRODUCT_AMOUNT"));
                    bean.setTDATE(resultSet.getString("TDATE"));
                    bean.setUSER_ID(resultSet.getString("USER_ID"));
                    bean.setPCH(resultSet.getString("PCH"));
                    bean.setBILL_DATE1(resultSet.getString("BILL_DATE1"));
                    bean.setBILL_DATE2(resultSet.getString("BILL_DATE2"));
                    bean.setREMARKS(resultSet.getString("REMARKS"));
                    bean.setTAXABLE(resultSet.getString("TAXABLE"));
                    bean.setNON_GST_AMOUNT(resultSet.getString("NON_GST_AMOUNT"));
                    bean.setHSN_CODE(resultSet.getString("HSN_CODE"));
                    bean.setVOUCHER_DESC(resultSet.getString("VOUCHER_DESC"));
                    bean.setPRODUCT_QUANTITY(resultSet.getString("PRODUCT_QUANTITY"));
                    bean.setUOM(resultSet.getString("UOM"));
                    bean.setPARTNER_DESC(resultSet.getString("PARTNER_DESC"));
                    bean.setBILLSUBTYPE_LIST(getBillSubType(conn, resultSet.getString("TYPE_SL_NO")));
                    bean.setBILLCC_LIST(getBillCC(connhris, resultSet.getString("TYPE_SL_NO")));
                    bean.setBILLPRODUCT_TYPE_LIST(getProdType(conn, resultSet.getString("SUB_TYPE_SL_NO")));
                    List<GetTaxRateBean> GST_LIST_TEMP =new ArrayList<GetTaxRateBean>();
                    stat1 = conn.prepareStatement("SELECT A.SL_NO, A.COST_ELEMENT, A.AMT, A.FORM_TYPE, A.TDATE, A.USER_ID, A.BILL_SL_NO, A.FORM_WHLO, A.GST_PER, A.TAX_CODE, A.LINE_SL_NO,B.HSN_CODE"
                            +" FROM M4_BILL_BREAKUP_DETAIL A,M4_BILL_HSN_GL B WHERE A.COST_ELEMENT=B.GL_CODE AND A.BILL_SL_NO=? AND A.LINE_SL_NO=? AND  B.flag='Y' and B.REV_GL_CODE is null");
                    stat1.setString(1, resultSet.getString("BILL_SL_NO"));
                    stat1.setString(2, resultSet.getString("SL_NO"));
                    resultSet1 = stat1.executeQuery();
                    while(resultSet1.next()){
                        GetTaxRateBean bean1 =new GetTaxRateBean();
                        bean1.setTAX1(resultSet1.getString("GST_PER"));
                        bean1.setTAX2(resultSet1.getString("COST_ELEMENT"));
                        bean1.setTAXC(resultSet1.getString("HSN_CODE"));
                        bean1.setTAX_CODE(resultSet1.getString("TAX_CODE"));
                        bean1.setCALVAL(resultSet1.getString("AMT"));
                        GST_LIST_TEMP.add(bean1);
                    }
                    if(resultSet1!=null) resultSet1.close();
                    if(stat1!=null) stat1.close();
                    bean.setGST_LIST(GST_LIST_TEMP);
                    DETAIL_LIST.add(bean);
                }
                if(resultSet!=null) resultSet.close();
                if(stat!=null) stat.close();
            }
            if (WAREHOUSE != null && WAREHOUSE.length() > 0) {
                BILLTYPE_LIST = getBillType(conn, usrid);
            }
        } catch (Exception e) {
            System.out.println("CashBillEntryAction " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connhris!=null){
                try {
                    connhris.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "newpage";
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getWAREHOUSE() {
        return WAREHOUSE;
    }

    public void setWAREHOUSE(String WAREHOUSE) {
        this.WAREHOUSE = WAREHOUSE;
    }

    public List<String> getWAREHOUSE_LIST() {
        return WAREHOUSE_LIST;
    }

    public void setWAREHOUSE_LIST(List<String> WAREHOUSE_LIST) {
        this.WAREHOUSE_LIST = WAREHOUSE_LIST;
    }

    public List<M3BILLBean> getBILLTYPE_LIST() {
        return BILLTYPE_LIST;
    }

    public void setBILLTYPE_LIST(List<M3BILLBean> BILLTYPE_LIST) {
        this.BILLTYPE_LIST = BILLTYPE_LIST;
    }

    public List<M3BILLBean> getBILLSUBTYPE_LIST() {
        return BILLSUBTYPE_LIST;
    }

    public void setBILLSUBTYPE_LIST(List<M3BILLBean> BILLSUBTYPE_LIST) {
        this.BILLSUBTYPE_LIST = BILLSUBTYPE_LIST;
    }

    public List<String> getPCH_LIST() {
        return PCH_LIST;
    }

    public void setPCH_LIST(List<String> PCH_LIST) {
        this.PCH_LIST = PCH_LIST;
    }

    public List<M3BILLBean> getBILLCC_LIST() {
        return BILLCC_LIST;
    }

    public void setBILLCC_LIST(List<M3BILLBean> BILLCC_LIST) {
        this.BILLCC_LIST = BILLCC_LIST;
    }

    public String getBILL_TYPE() {
        return BILL_TYPE;
    }

    public void setBILL_TYPE(String BILL_TYPE) {
        this.BILL_TYPE = BILL_TYPE;
    }

    public String getBILL_SUBTYPE() {
        return BILL_SUBTYPE;
    }

    public void setBILL_SUBTYPE(String BILL_SUBTYPE) {
        this.BILL_SUBTYPE = BILL_SUBTYPE;
    }

    public List<M3BILLBean> getBILLPRODUCT_TYPE_LIST() {
        return BILLPRODUCT_TYPE_LIST;
    }

    public void setBILLPRODUCT_TYPE_LIST(List<M3BILLBean> BILLPRODUCT_TYPE_LIST) {
        this.BILLPRODUCT_TYPE_LIST = BILLPRODUCT_TYPE_LIST;
    }

    public List<SupplierBean> getSUPPLIER_LIST() {
        return SUPPLIER_LIST;
    }

    public void setSUPPLIER_LIST(List<SupplierBean> SUPPLIER_LIST) {
        this.SUPPLIER_LIST = SUPPLIER_LIST;
    }

    public String getSUPPLIER_QUERY() {
        return SUPPLIER_QUERY;
    }

    public void setSUPPLIER_QUERY(String SUPPLIER_QUERY) {
        this.SUPPLIER_QUERY = SUPPLIER_QUERY;
    }

    public String getBILL_DATE() {
        return BILL_DATE;
    }

    public void setBILL_DATE(String BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
    }

    public String getHSN_CODE() {
        return HSN_CODE;
    }

    public void setHSN_CODE(String HSN_CODE) {
        this.HSN_CODE = HSN_CODE;
    }

    public List getHSN_LIST() {
        return HSN_LIST;
    }

    public void setHSN_LIST(List HSN_LIST) {
        this.HSN_LIST = HSN_LIST;
    }

    public String getT_INDEX() {
        return T_INDEX;
    }

    public void setT_INDEX(String T_INDEX) {
        this.T_INDEX = T_INDEX;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getGEOC() {
        return GEOC;
    }

    public void setGEOC(String GEOC) {
        this.GEOC = GEOC;
    }

    public String getCSCD() {
        return CSCD;
    }

    public void setCSCD(String CSCD) {
        this.CSCD = CSCD;
    }

    public String getECAR() {
        return ECAR;
    }

    public void setECAR(String ECAR) {
        this.ECAR = ECAR;
    }

    public String getBREAK_AMOUNT() {
        return BREAK_AMOUNT;
    }

    public void setBREAK_AMOUNT(String BREAK_AMOUNT) {
        this.BREAK_AMOUNT = BREAK_AMOUNT;
    }

    public List<GetTaxRateBean> getGST_LIST() {
        return GST_LIST;
    }

    public void setGST_LIST(List<GetTaxRateBean> GST_LIST) {
        this.GST_LIST = GST_LIST;
    }

    public String getMAST_SL_NO() {
        return MAST_SL_NO;
    }

    public void setMAST_SL_NO(String MAST_SL_NO) {
        this.MAST_SL_NO = MAST_SL_NO;
    }

    public String getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    public String getSUPPLIER() {
        return SUPPLIER;
    }

    public void setSUPPLIER(String SUPPLIER) {
        this.SUPPLIER = SUPPLIER;
    }

    public String getBILL_AMOUNT() {
        return BILL_AMOUNT;
    }

    public void setBILL_AMOUNT(String BILL_AMOUNT) {
        this.BILL_AMOUNT = BILL_AMOUNT;
    }

    public String getMAST_REMARKS() {
        return MAST_REMARKS;
    }

    public void setMAST_REMARKS(String MAST_REMARKS) {
        this.MAST_REMARKS = MAST_REMARKS;
    }

    public String getSUPPLIER_DESC() {
        return SUPPLIER_DESC;
    }

    public void setSUPPLIER_DESC(String SUPPLIER_DESC) {
        this.SUPPLIER_DESC = SUPPLIER_DESC;
    }

    public String getADR1() {
        return ADR1;
    }

    public void setADR1(String ADR1) {
        this.ADR1 = ADR1;
    }

    public String getADR2() {
        return ADR2;
    }

    public void setADR2(String ADR2) {
        this.ADR2 = ADR2;
    }

    public String getADR3() {
        return ADR3;
    }

    public void setADR3(String ADR3) {
        this.ADR3 = ADR3;
    }

    public String getADR4() {
        return ADR4;
    }

    public void setADR4(String ADR4) {
        this.ADR4 = ADR4;
    }

    public String getTOWN() {
        return TOWN;
    }

    public void setTOWN(String TOWN) {
        this.TOWN = TOWN;
    }

    public String getPONO() {
        return PONO;
    }

    public void setPONO(String PONO) {
        this.PONO = PONO;
    }

    public String getREVERSE_SRVTAX() {
        return REVERSE_SRVTAX;
    }

    public void setREVERSE_SRVTAX(String REVERSE_SRVTAX) {
        this.REVERSE_SRVTAX = REVERSE_SRVTAX;
    }

    public String getBILL_YEAR() {
        return BILL_YEAR;
    }

    public void setBILL_YEAR(String BILL_YEAR) {
        this.BILL_YEAR = BILL_YEAR;
    }

    public List<String> getBILL_TYPE_SLNO() {
        return BILL_TYPE_SLNO;
    }

    public void setBILL_TYPE_SLNO(List<String> BILL_TYPE_SLNO) {
        this.BILL_TYPE_SLNO = BILL_TYPE_SLNO;
    }

    public List<String> getBILL_SUBTYPE_SLNO() {
        return BILL_SUBTYPE_SLNO;
    }

    public void setBILL_SUBTYPE_SLNO(List<String> BILL_SUBTYPE_SLNO) {
        this.BILL_SUBTYPE_SLNO = BILL_SUBTYPE_SLNO;
    }

    public List<String> getPCH() {
        return PCH;
    }

    public void setPCH(List<String> PCH) {
        this.PCH = PCH;
    }

    public List<String> getCOST_CENTER() {
        return COST_CENTER;
    }

    public void setCOST_CENTER(List<String> COST_CENTER) {
        this.COST_CENTER = COST_CENTER;
    }

    public List<String> getPRODUCT_GROUP() {
        return PRODUCT_GROUP;
    }

    public void setPRODUCT_GROUP(List<String> PRODUCT_GROUP) {
        this.PRODUCT_GROUP = PRODUCT_GROUP;
    }

    public List<String> getBILL_AMOUNT_NON_BREAKUP() {
        return BILL_AMOUNT_NON_BREAKUP;
    }

    public void setBILL_AMOUNT_NON_BREAKUP(List<String> BILL_AMOUNT_NON_BREAKUP) {
        this.BILL_AMOUNT_NON_BREAKUP = BILL_AMOUNT_NON_BREAKUP;
    }

    public List<String> getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(List<String> REMARKS) {
        this.REMARKS = REMARKS;
    }

    public List<String> getBILL_AMOUNT_HSN() {
        return BILL_AMOUNT_HSN;
    }

    public void setBILL_AMOUNT_HSN(List<String> BILL_AMOUNT_HSN) {
        this.BILL_AMOUNT_HSN = BILL_AMOUNT_HSN;
    }

    public List<String> getCOST_ELEMENT() {
        return COST_ELEMENT;
    }

    public void setCOST_ELEMENT(List<String> COST_ELEMENT) {
        this.COST_ELEMENT = COST_ELEMENT;
    }

    public List<String> getCOST_BREAKUP_AMT() {
        return COST_BREAKUP_AMT;
    }

    public void setCOST_BREAKUP_AMT(List<String> COST_BREAKUP_AMT) {
        this.COST_BREAKUP_AMT = COST_BREAKUP_AMT;
    }

    public List<String> getBILL_AMOUNT_BREAKUP() {
        return BILL_AMOUNT_BREAKUP;
    }

    public void setBILL_AMOUNT_BREAKUP(List<String> BILL_AMOUNT_BREAKUP) {
        this.BILL_AMOUNT_BREAKUP = BILL_AMOUNT_BREAKUP;
    }

    public List<String> getCOST_BREAKUP_TAX() {
        return COST_BREAKUP_TAX;
    }

    public void setCOST_BREAKUP_TAX(List<String> COST_BREAKUP_TAX) {
        this.COST_BREAKUP_TAX = COST_BREAKUP_TAX;
    }

    public List<String> getCOST_BREAKUP_TAX_CODE() {
        return COST_BREAKUP_TAX_CODE;
    }

    public void setCOST_BREAKUP_TAX_CODE(List<String> COST_BREAKUP_TAX_CODE) {
        this.COST_BREAKUP_TAX_CODE = COST_BREAKUP_TAX_CODE;
    }

    public List<String> getBREAKUP_COUNT() {
        return BREAKUP_COUNT;
    }

    public void setBREAKUP_COUNT(List<String> BREAKUP_COUNT) {
        this.BREAKUP_COUNT = BREAKUP_COUNT;
    }

    public String getCONM() {
        return CONM;
    }

    public void setCONM(String CONM) {
        this.CONM = CONM;
    }

    public String getADID() {
        return ADID;
    }

    public void setADID(String ADID) {
        this.ADID = ADID;
    }

    public String getRGDT() {
        return RGDT;
    }

    public void setRGDT(String RGDT) {
        this.RGDT = RGDT;
    }

    public String getUPD_MAST() {
        return UPD_MAST;
    }

    public void setUPD_MAST(String UPD_MAST) {
        this.UPD_MAST = UPD_MAST;
    }

    public List<CashBillDetailBean> getDETAIL_LIST() {
        return DETAIL_LIST;
    }

    public void setDETAIL_LIST(List<CashBillDetailBean> DETAIL_LIST) {
        this.DETAIL_LIST = DETAIL_LIST;
    }     

    public String getWAREHOUSE_QUERY() {
        return WAREHOUSE_QUERY;
    }

    public void setWAREHOUSE_QUERY(String WAREHOUSE_QUERY) {
        this.WAREHOUSE_QUERY = WAREHOUSE_QUERY;
    }

    public String getCONTROL_QUERY() {
        return CONTROL_QUERY;
    }

    public void setCONTROL_QUERY(String CONTROL_QUERY) {
        this.CONTROL_QUERY = CONTROL_QUERY;
    }

    public String getBILL_FROM_DATE() {
        return BILL_FROM_DATE;
    }

    public void setBILL_FROM_DATE(String BILL_FROM_DATE) {
        this.BILL_FROM_DATE = BILL_FROM_DATE;
    }

    public String getBILL_TO_DATE() {
        return BILL_TO_DATE;
    }

    public void setBILL_TO_DATE(String BILL_TO_DATE) {
        this.BILL_TO_DATE = BILL_TO_DATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getUSER_QUERY() {
        return USER_QUERY;
    }

    public void setUSER_QUERY(String USER_QUERY) {
        this.USER_QUERY = USER_QUERY;
    }

    public List<CashBillQueryBean> getQUERY_LIST() {
        return QUERY_LIST;
    }

    public void setQUERY_LIST(List<CashBillQueryBean> QUERY_LIST) {
        this.QUERY_LIST = QUERY_LIST;
    }

    public List<String> getCONTROL_NO_LIST() {
        return CONTROL_NO_LIST;
    }

    public void setCONTROL_NO_LIST(List<String> CONTROL_NO_LIST) {
        this.CONTROL_NO_LIST = CONTROL_NO_LIST;
    }

    public List<String> getFORWARD_USER_LIST() {
        return FORWARD_USER_LIST;
    }

    public void setFORWARD_USER_LIST(List<String> FORWARD_USER_LIST) {
        this.FORWARD_USER_LIST = FORWARD_USER_LIST;
    }

    public String getFIN_STATUS() {
        return FIN_STATUS;
    }

    public void setFIN_STATUS(String FIN_STATUS) {
        this.FIN_STATUS = FIN_STATUS;
    }

    public List<String> getDEL_CONTROL_NO_LIST() {
        return DEL_CONTROL_NO_LIST;
    }

    public void setDEL_CONTROL_NO_LIST(List<String> DEL_CONTROL_NO_LIST) {
        this.DEL_CONTROL_NO_LIST = DEL_CONTROL_NO_LIST;
    }

    public List<String> getDELETE_BILL_LIST() {
        return DELETE_BILL_LIST;
    }

    public void setDELETE_BILL_LIST(List<String> DELETE_BILL_LIST) {
        this.DELETE_BILL_LIST = DELETE_BILL_LIST;
    }

    public List<String> getUNAPPROVE_BILL_LIST() {
        return UNAPPROVE_BILL_LIST;
    }

    public void setUNAPPROVE_BILL_LIST(List<String> UNAPPROVE_BILL_LIST) {
        this.UNAPPROVE_BILL_LIST = UNAPPROVE_BILL_LIST;
    }

    public String getACT_FLAG() {
        return ACT_FLAG;
    }

    public void setACT_FLAG(String ACT_FLAG) {
        this.ACT_FLAG = ACT_FLAG;
    }

    public List<String> getAPPROVE_BILL_LIST() {
        return APPROVE_BILL_LIST;
    }

    public void setAPPROVE_BILL_LIST(List<String> APPROVE_BILL_LIST) {
        this.APPROVE_BILL_LIST = APPROVE_BILL_LIST;
    }

    public String getREPORT_NO() {
        return REPORT_NO;
    }

    public void setREPORT_NO(String REPORT_NO) {
        this.REPORT_NO = REPORT_NO;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public List<String> getVOUCHER_DESC() {
        return VOUCHER_DESC;
    }

    public void setVOUCHER_DESC(List<String> VOUCHER_DESC) {
        this.VOUCHER_DESC = VOUCHER_DESC;
    }

    public String getVCH_DATE() {
        return VCH_DATE;
    }

    public void setVCH_DATE(String VCH_DATE) {
        this.VCH_DATE = VCH_DATE;
    }

    public List<String> getBILL_AMOUNT_QUANTITY() {
        return BILL_AMOUNT_QUANTITY;
    }

    public void setBILL_AMOUNT_QUANTITY(List<String> BILL_AMOUNT_QUANTITY) {
        this.BILL_AMOUNT_QUANTITY = BILL_AMOUNT_QUANTITY;
    }

    public List<String> getBILL_AMOUNT_UOM() {
        return BILL_AMOUNT_UOM;
    }

    public void setBILL_AMOUNT_UOM(List<String> BILL_AMOUNT_UOM) {
        this.BILL_AMOUNT_UOM = BILL_AMOUNT_UOM;
    }

    public List<M3BILLBean> getUOM_LIST() {
        return UOM_LIST;
    }

    public void setUOM_LIST(List<M3BILLBean> UOM_LIST) {
        this.UOM_LIST = UOM_LIST;
    }

    public List<String> getPARTNER_DESC() {
        return PARTNER_DESC;
    }

    public void setPARTNER_DESC(List<String> PARTNER_DESC) {
        this.PARTNER_DESC = PARTNER_DESC;
    }

    public String getPAY_TYPE() {
        return PAY_TYPE;
    }

    public void setPAY_TYPE(String PAY_TYPE) {
        this.PAY_TYPE = PAY_TYPE;
    }

    public String getPAY_TYPE_QUERY() {
        return PAY_TYPE_QUERY;
    }

    public void setPAY_TYPE_QUERY(String PAY_TYPE_QUERY) {
        this.PAY_TYPE_QUERY = PAY_TYPE_QUERY;
    }
}
