/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.ReportFolder.EPM.beans.CbnacBean;
import shahi.Action.ReportFolder.EPM.beans.PayrollPaymentsBeans;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;
import shahi.Action.database.ConnectionShahiHrisNew;


/**
 *
 * @author Vivek
 */
public class PayrollPaymentsAction extends ActionSupport {

    private List detaillist;
    private List bankcodelist;
    private List complist;
    private List deptlist;
    private String aausrid;
    private String disble;
    private String DIVI;
    private String TMLOCT;
    private String TMDEPT;
    private String TMDEPTALL;
    private String ETYPE;
    private String VTYPE;
    private String VSTATE;
    private String TMPRID;
    private String CKBKID;
    private String CKCHKNF;
    private String CKCHKNT;
    private String CKDTPRF;
    private String LOCATION_CODE;
    private List chkdel;
    private List CHK_DATE;
    private List CHEQUE_NEW;
    private List REMARKS_NEW;
    private List LOC_LIST;
    private String DONE;
    private double CHQ_AMT;
    private String[] CHK_BK_CODE;

    public String view() throws Exception {

        Map session = ActionContext.getContext().getSession();
        Connection conn1 = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn1 = new connection().getConnection();
            conn1.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
            stat = conn1.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE,DEPT_CODE from EMPLOYEE_VIEW where emp_code=?");
            stat.setString(1, aausrid);
            result = stat.executeQuery();
            if (result.next()) {
                session.put("sessUserName", result.getString("EMP_NAME"));
                session.put("sessUserId", result.getString("emp_code"));
                session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                session.put("sessUnitCode", result.getString("UNIT_CODE"));
                session.put("sessDeptCode", result.getString("DEPT_CODE"));
            }
            conn1.commit();
        } catch (Exception e) {
            System.out.println("1.PayrollPaymentAction " + e);
            addActionError(e.getMessage());
            conn1.rollback();
        } finally {
            if (conn1 != null) {
                conn1.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }
        disble = "true";
        getAllBankCode();
        return SUCCESS;
    }

    public void getAllBankCode() throws Exception {
        bankcodelist = new ArrayList();
        complist = new ArrayList();
        deptlist = new ArrayList();
        LOC_LIST = new ArrayList();
        if (DIVI != null && DIVI.length() > 0) {
            Connection connbi = null;

            PreparedStatement stat = null;
            ResultSet result = null;

            try {
                connbi = new connectiondb2().getConnection();
                connbi.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionError(e.getMessage());
            }
            try {
                stat = connbi.prepareStatement("select distinct BCAIT1 BCBKID,BCBANA from m3fdbprd.CBANAC where BCCONO=111 and BCDIVI=? and BCBKTP=1 and bcstat='20' and bccucd='INR' AND BCBKIN='SHAB' order by 1");
                stat.setString(1, DIVI);
                result = stat.executeQuery();
                while (result.next()) {
                    bankcodelist.add(new CbnacBean(result.getString("BCBKID"), result.getString("BCBANA")));
                }
                connbi.commit();
            } catch (Exception e) {
                System.out.println("2.PayrollPaymentAction " + e);
                addActionError(e.getMessage());
                connbi.rollback();
            } finally {
                if (connbi != null) {
                    connbi.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (result != null) {
                    result.close();
                }
            }
        }
        Connection connora = null;
        try {
            connora = new ConnectionShahiHrisNew().getConnection();
            connora.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        PreparedStatement stat3 = null;
        ResultSet result3 = null;

        try {
            stat1 = connora.prepareStatement("select code company_code,LONGDESCRIPTION comp_desc from AMSNOW.DIVISIONVIEW where code<>'999' order by code");
            result1 = stat1.executeQuery();
            while (result1.next()) {
                complist.add(new CbnacBean(result1.getString("company_code"), result1.getString("comp_desc")));
            }
            stat2 = connora.prepareStatement("select LONGDESCRIPTION dept_desc, CODE dept_Code from AMSNOW.DEPARTMENTVIEW where COMPANYCODE is not null  order by 1");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                deptlist.add(new CbnacBean(result2.getString("dept_Code"), result2.getString("dept_desc")));
            }
            stat3 = connora.prepareStatement("select CODE location_code,LONGDESCRIPTION location_desc from AMSNOW.FACTORYVIEW where COMPANYCODE is not null order by code");
            result3 = stat3.executeQuery();
            while (result3.next()) {
                LOC_LIST.add(new CbnacBean(result3.getString("location_code"), result3.getString("location_desc")));
            }
            connora.commit();
        } catch (Exception e) {
            System.out.println("2.PayrollPaymentAction " + e);
            addActionError(e.getMessage());
            connora.rollback();
        } finally {
            if (connora != null) {
                connora.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (result1 != null) {
                result1.close();
            }
        }
    }

    @Override
    public String execute() throws Exception {
        CHEQUE_NEW = null;
        REMARKS_NEW = null;
        CHK_DATE = null;
        chkdel = null;
        detaillist = new ArrayList();
        Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        String LOCATION = (String) session.get("sessLocationCode");
        if (USER_ID == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement statCHK = null;
        PreparedStatement statBAL = null;
        ResultSet resultCHK = null;
        ResultSet result = null;
        ResultSet resultBAL = null;
        try {
            conn = new ConnectionShahiHrisNew().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
            if (!TMDEPT.equals("ALL")) {
                TMDEPTALL = " and deptcode = '" + TMDEPT + "' ";
            } else {
                TMDEPTALL = " ";
            }
            if (!LOCATION_CODE.equals("ALL")) {
                TMDEPTALL += " and trim(location_code) = '" + LOCATION_CODE + "' ";
            } else if (TMDEPTALL == null) {
                TMDEPTALL += " ";
            }
            if (!VTYPE.equals("ALL")) {
                TMDEPTALL += " and trim(pymt_type) = '" + VTYPE.trim() + "' ";
            } else {
                TMDEPTALL += " ";
            }
            if (!VSTATE.equals("ALL")) {
                TMDEPTALL += " and trim(state_code) = '" + VSTATE.trim() + "' ";
            } else {
                TMDEPTALL += " ";
            }
            if (!TMLOCT.equals("ALL")) {
                TMDEPTALL += "and trim(company_code)= '" + TMLOCT.trim() + "' ";
            } else {
                TMDEPTALL += " ";
            }
            if (!ETYPE.equals("ALL")) {
                TMDEPTALL += "and nvl(trim(cat_code),'S')= '" + ETYPE.trim() + "' ";
            } else {
                TMDEPTALL += " ";
            }
           // System.out.println(TMDEPTALL+" " + DIVI+"-"+TMPRID+VSTATE);
            statCHK = conn.prepareStatement("select a.pay_id,a.emp_code,acc_data_desc,chq_amount,b.acc_amt from AMSNOW.fa_payroll_chq a,"
                    + " (select c.pay_id,sum(acc_amt) acc_amt from AMSNOW.fa_payroll_chq_Dtls c,AMSNOW.fa_payroll_chq d where c.pay_id=d.pay_id and pymt_date is null and d.chk_upd is null and  acc_code<>1111"
                    + " and cono=111 and divi=? " + TMDEPTALL + " and to_char(d.pros_date,'MM/YYYY')=? group by c.pay_id) b "
                    + " where cono=111 and divi=? " + TMDEPTALL + " and to_char(a.pros_date,'MM/YYYY')=?"
                    + " and a.pay_id=b.pay_id(+) and chk_upd is null"
                    + " and chq_amount-acc_amt<>0");
            
            /*("select a.pay_id,a.emp_code,acc_data_desc,chq_amount,b.acc_amt from finac.fa_payroll_chq a,"
                    + " (select c.pay_id,c.emp_code,sum(acc_amt) acc_amt from finac.fa_payroll_chq_Dtls c,finac.fa_payroll_chq d where c.pay_id=d.pay_id and pymt_date is null and d.chk_upd is null and  acc_code<>1111"
                    + " and cono=111 and divi=? " + TMDEPTALL + " and to_char(d.pros_date,'MM/YYYY')=? group by c.pay_id,c.emp_code) b "
                    + " where cono=111 and divi=? " + TMDEPTALL + " and to_char(a.pros_date,'MM/YYYY')=?"
                    + " and a.pay_id=b.pay_id and a.emp_code=b.emp_code"
                    + " and chq_amount-acc_amt<>0"); - Changed on 12/08/2013 */


            statCHK.setString(1, DIVI);
            statCHK.setString(2, TMPRID);
            //statCHK.setString(3, TMDEPT);
            //   statCHK.setString(3, ETYPE);
            //statCHK.setString(4, VTYPE);
            statCHK.setString(3, DIVI);
            statCHK.setString(4, TMPRID);
            //statCHK.setString(5, TMLOCT);
            //statCHK.setString(3, TMDEPT);
            //statCHK.setString(5, ETYPE);
            //statCHK.setString(8, VTYPE);
            resultCHK = statCHK.executeQuery();
            if (resultCHK.next()) {
                disble = "true";
                addActionError(resultCHK.getString("pay_id") + "-" +resultCHK.getString("emp_code") + "-" + resultCHK.getString("acc_data_desc") + " Invalid record.");
                getAllBankCode();
                return SUCCESS;
            }

            statBAL = conn.prepareStatement("select c.pay_id,d.emp_code,acc_data_Desc,sum(acc_amt) acc_amt from AMSNOW.fa_payroll_chq_Dtls c,AMSNOW.fa_payroll_chq d where c.pay_id=d.pay_id and pymt_date is null and d.chk_upd is null"
                    + " and cono=111 and divi=? " + TMDEPTALL + " and to_char(d.pros_date,'MM/YYYY')=? "
                    + " group by c.pay_id,d.emp_code, acc_data_Desc having sum(acc_amt)<>0");
            statBAL.setString(1, DIVI);
            //statBAL.setString(2, TMLOCT.toUpperCase());
            //statBAL.setString(3, ETYPE);
            statBAL.setString(2, TMPRID);
            resultBAL = statBAL.executeQuery();
            if (resultBAL.next()) {
                disble = "true";
                addActionError(resultBAL.getString("emp_code") + "-" + resultBAL.getString("acc_data_desc") + " Unbalance record found.");
                getAllBankCode();
                return SUCCESS;
            }


            stat = conn.prepareStatement("select pay_id,company_code,divi,chq_no,chq_date,acc_data_Desc,year,vser,vono,emp_code,acc_data_Desc,pros_date,fin_upd,chq_amount,bank_name from AMSNOW.fa_payroll_chq where CONO=111 AND divi=? " + TMDEPTALL + " AND to_char(pros_date,'MM/YYYY')=? AND PYMT_DATE is null and nvl(chq_amount,0)>0 order by BANK_CODE,COMPANY_CODE,PYMT_TYPE,pay_id");
            stat.setString(1, DIVI);
            //stat.setString(2, TMLOCT.toUpperCase());
            //stat.setString(3, TMDEPT);
            //stat.setString(3, ETYPE);
            stat.setString(2, TMPRID);
            result = stat.executeQuery();
            CHQ_AMT = 0;
            while (result.next()) {
                CHQ_AMT += result.getDouble("chq_amount");
                detaillist.add(new PayrollPaymentsBeans(result.getString("divi"), result.getString("year"), result.getString("emp_code"), result.getString("acc_data_Desc"), result.getString("VSER"), result.getString("VONO"), result.getString("chq_date"), result.getString("chq_no"), result.getString("pros_date"), result.getString("pay_id"), result.getDouble("chq_amount"), result.getString("bank_name"), result.getString("company_code")));
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println("3.PayrollPaymentAction " + e);
            addActionError(e.getMessage());
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stat != null) {
                stat.close();
            }
            
            if (result != null) {
                result.close();
            }
            if (statBAL != null) {
                statBAL.close();
            }        
            if (resultBAL != null) {
                resultBAL.close();
            }
            
            if (statCHK != null) {
                statCHK.close();
            }                     
            if (resultCHK != null) {
                resultCHK.close();
            }
        }
        getAllBankCode();
        return SUCCESS;
    }

    public String save() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        String LOCATION = (String) session.get("sessLocationCode");
        int BATCH_ID = 1;
        if (USER_ID == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        if ((CHEQUE_NEW != null && CHEQUE_NEW.size() > 0) && (CHK_DATE != null && CHK_DATE.size() > 0)) {
            if (!TMDEPT.equals("ALL")) {
                TMDEPTALL = " and deptcode = '" + TMDEPT + "' ";
            } else {
                TMDEPTALL = " ";
            }
            if (!TMLOCT.equals("ALL")) {
                TMDEPTALL += "and  company_code= '" + TMLOCT + "' ";
            } else {
                TMDEPTALL += " ";
            }


            Connection conn = null;

            PreparedStatement stat = null;
            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            ResultSet result = null;
            ResultSet result1 = null;
            try {
                conn = new ConnectionShahiHrisNew().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionError(e.getMessage());
            }
            try {
                int batch_id = 1;

                stat1 = conn.prepareStatement("select max(nvl(batch_no,0)) batch_no from AMSNOW.fa_payroll_chq");
                result1 = stat1.executeQuery();
                if (result1.next()) {
                    batch_id = result1.getInt("batch_no") + 1;
                }
                BATCH_ID = batch_id;
                for (int i = 0; i < CHEQUE_NEW.size(); i++) {
                    stat = conn.prepareStatement("update AMSNOW.fa_payroll_chq set CHQ_NO=?,CHQ_DATE=to_date(?,'dd/MM/yyyy'),BANK_CODE=?,PYMT_DATE=to_date(?,'dd/MM/yyyy'),BATCH_NO=? where pay_id=?");
                    stat.setString(1, CHEQUE_NEW.get(i).toString());
                    stat.setString(2, CHK_DATE.get(i).toString());
                    stat.setString(3, CHK_BK_CODE[i].trim());
                    stat.setString(4, CHK_DATE.get(i).toString());
                    stat.setInt(5, batch_id);
                    stat.setString(6, chkdel.get(i).toString());
                    stat.executeUpdate();
                    stat.close();
                }
                for (int i = 0; i < CHK_BK_CODE.length; i++) {
                    //  System.out.println(CHK_BK_CODE[i].trim()+DIVI+TMLOCT+ETYPE+VTYPE+batch_id+chkdel.get(i).toString()+TMDEPTALL);                 

                    stat1 = conn.prepareStatement("update AMSNOW.fa_payroll_chq_Dtls set acc_Code=? where acc_code=1111 and pay_id in (select pay_id from AMSNOW.fa_payroll_chq where  cono=111 and divi=? " + TMDEPTALL + " and pay_id=?)");
                    stat1.setString(1, CHK_BK_CODE[i].trim());
                    stat1.setString(2, DIVI);
                    //stat1.setString(3, TMLOCT);
                    //stat1.setString(4, TMDEPT);
                    // stat1.setString(4, ETYPE);
                    //   stat1.setString(5, VTYPE);
                    //   stat1.setInt(6, batch_id);
                    stat1.setString(3, chkdel.get(i).toString());
                    stat1.executeUpdate();
                    stat1.close();
                }

                conn.commit();
                PayrollPmtTxt pmt = new PayrollPmtTxt();
                DONE = pmt.UpdateTxt(USER_ID, LOCATION, DIVI, TMLOCT.toUpperCase(), ETYPE, BATCH_ID + "", TMPRID,VSTATE.trim());
                addActionError(DONE);

            } catch (Exception e) {
                System.out.println("PayrollPaymentAction " + e);
                conn.rollback();
                addActionError(e.getMessage());
            } finally {
                if (conn != null) {
                    conn.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (result != null) {
                    result.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (result1 != null) {
                    result1.close();
                }
            }
            getAllBankCode();
        } else {
            addActionError("Error: Data Not Valid.");
        }
        return SUCCESS;

    }

    public List getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(List detaillist) {
        this.detaillist = detaillist;
    }

    public List getBankcodelist() {
        return bankcodelist;
    }

    public void setBankcodelist(List bankcodelist) {
        this.bankcodelist = bankcodelist;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getDisble() {
        return disble;
    }

    public void setDisble(String disble) {
        this.disble = disble;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getTMLOCT() {
        return TMLOCT;
    }

    public void setTMLOCT(String TMLOCT) {
        this.TMLOCT = TMLOCT;
    }

    public String getETYPE() {
        return ETYPE;
    }

    public void setETYPE(String ETYPE) {
        this.ETYPE = ETYPE;
    }

    public String getVTYPE() {
        return VTYPE;
    }

    public void setVTYPE(String VTYPE) {
        this.VTYPE = VTYPE;
    }
    public String getVSTATE() {
        return VSTATE;
    }

    public void setVSTATE(String VSTATE) {
        this.VSTATE = VSTATE;
    }
    public String getTMPRID() {
        return TMPRID;
    }

    public void setTMPRID(String TMPRID) {
        this.TMPRID = TMPRID;
    }

    public String getCKBKID() {
        return CKBKID;
    }

    public void setCKBKID(String CKBKID) {
        this.CKBKID = CKBKID;
    }

    public String getCKCHKNF() {
        return CKCHKNF;
    }

    public void setCKCHKNF(String CKCHKNF) {
        this.CKCHKNF = CKCHKNF;
    }

    public String getCKCHKNT() {
        return CKCHKNT;
    }

    public void setCKCHKNT(String CKCHKNT) {
        this.CKCHKNT = CKCHKNT;
    }

    public String getCKDTPRF() {
        return CKDTPRF;
    }

    public void setCKDTPRF(String CKDTPRF) {
        this.CKDTPRF = CKDTPRF;
    }

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
    }

    public List getCHK_DATE() {
        return CHK_DATE;
    }

    public void setCHK_DATE(List CHK_DATE) {
        this.CHK_DATE = CHK_DATE;
    }

    public List getCHEQUE_NEW() {
        return CHEQUE_NEW;
    }

    public void setCHEQUE_NEW(List CHEQUE_NEW) {
        this.CHEQUE_NEW = CHEQUE_NEW;
    }

    public List getREMARKS_NEW() {
        return REMARKS_NEW;
    }

    public void setREMARKS_NEW(List REMARKS_NEW) {
        this.REMARKS_NEW = REMARKS_NEW;
    }

    public List getComplist() {
        return complist;
    }

    public void setComplist(List complist) {
        this.complist = complist;
    }

    public List getDeptlist() {
        return deptlist;
    }

    public void setDeptlist(List deptlist) {
        this.deptlist = deptlist;
    }

    public String getTMDEPT() {
        return TMDEPT;
    }

    public void setTMDEPT(String TMDEPT) {
        this.TMDEPT = TMDEPT;
    }

    public String getTMDEPTALL() {
        return TMDEPTALL;
    }

    public void setTMDEPTALL(String TMDEPTALL) {
        this.TMDEPTALL = TMDEPTALL;
    }

    public double getCHQ_AMT() {
        return CHQ_AMT;
    }

    public void setCHQ_AMT(double CHQ_AMT) {
        this.CHQ_AMT = CHQ_AMT;
    }

    public List getLOC_LIST() {
        return LOC_LIST;
    }

    public void setLOC_LIST(List LOC_LIST) {
        this.LOC_LIST = LOC_LIST;
    }

    public String getLOCATION_CODE() {
        return LOCATION_CODE;
    }

    public void setLOCATION_CODE(String LOCATION_CODE) {
        this.LOCATION_CODE = LOCATION_CODE;
    }

    public String[] getCHK_BK_CODE() {
        return CHK_BK_CODE;
    }

    public void setCHK_BK_CODE(String[] CHK_BK_CODE) {
        this.CHK_BK_CODE = CHK_BK_CODE;
    }
}
