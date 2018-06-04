/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.CbnacBean;
import shahi.Action.ReportFolder.EPM.beans.PayrollPaymentsBeans;
import shahi.Action.ReportFolder.EPM.service.PayrollPaymentService;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connectiondb2;
import shahi.pagination.Page;



public class PayrollPaymentsBActionNew extends ActionSupport {

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
    private int pageCount;
	public String view() throws Exception {

		/*  Map session = ActionContext.getContext().getSession();
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
        }*/
		disble = "true";

		getAllList();

		return SUCCESS;
	}

	private void getAllList() throws SQLException{
		Connection conn=null,db2=null;
		try{
			conn = new ConnectionShahiHrisNew().getConnection();
			conn.setAutoCommit(false);
			db2=new connectiondb2().getConnection();
			//setDetaillist(service.getAllBanks(getDIVI()));
			setBankcodelist(getBankCodes(db2));
			setComplist(getCompanyList(conn));
			setDeptlist(getDepartmentList(conn));
			setLOC_LIST(getLocationList(conn));
		}catch(SQLException sql){
			addActionError(sql.getLocalizedMessage());
		}finally{
			if(conn!=null){
				conn.close();
			}
			if(db2!=null){
				db2.close();
			}
		}
	}
	private List<CbnacBean>  getCompanyList(Connection conn) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<CbnacBean>compList=new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select code company_code,LONGDESCRIPTION comp_desc from AMSNOW.DIVISIONVIEW where code<>'999' order by code");
			result = stmt.executeQuery();
			while (result.next()) {
				compList.add(new CbnacBean(result.getString("company_code"), result.getString("comp_desc")));
			}

		}catch (Exception e) {
			System.out.println("2.PayrollPaymentBAction " + e);
			addActionError(e.getMessage());
		}
		finally {

			if (stmt != null) {
				stmt.close();
			}
			if (result != null) {
				result.close();
			}
		}
		return compList;

	}

	private List<CbnacBean> getDepartmentList(Connection conn) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<CbnacBean>deptList=new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select LONGDESCRIPTION dept_desc, CODE dept_Code from AMSNOW.DEPARTMENTVIEW where COMPANYCODE is not null  order by 1");
			result = stmt.executeQuery();
			while (result.next()) {
				deptList.add(new CbnacBean(result.getString("dept_desc"), result.getString("dept_Code")));
			}

		}catch (Exception e) {
			System.out.println("2.PayrollPaymentBAction " + e);
			addActionError(e.getMessage());
		}
		finally {

			if (stmt != null) {
				stmt.close();
			}
			if (result != null) {
				result.close();
			}
		}
		return deptList;

	}

	private List<CbnacBean>  getLocationList(Connection conn) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<CbnacBean>locList=new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select CODE location_code,LONGDESCRIPTION location_desc from AMSNOW.FACTORYVIEW where COMPANYCODE is not null order by code");
			result = stmt.executeQuery();
			while (result.next()) {
				locList.add(new CbnacBean(result.getString("location_code"), result.getString("location_desc")));
			}

		}catch (Exception e) {
			System.out.println("2.PayrollPaymentBAction " + e);
			addActionError(e.getMessage());
		}
		finally {

			if (stmt != null) {
				stmt.close();
			}
			if (result != null) {
				result.close();
			}
		}
		return locList;

	}

	private List<CbnacBean>  getBankCodes(Connection conn) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<CbnacBean>bankcodeList=new ArrayList<>();
		try {
			stmt = conn.prepareStatement("select distinct BCAIT1 BCBKID,BCBANA from m3fdbprd.CBANAC where BCCONO=111 and BCDIVI=? and BCBKTP=1 and bcstat='20' and bccucd='INR' AND BCBKIN='SHAB' order by 1");
			stmt.setString(1, getDIVI());
			result = stmt.executeQuery();
			while (result.next()) {
				bankcodeList.add(new CbnacBean(result.getString("BCBKID"), result.getString("BCBANA")));
			}

		}catch (Exception e) {
			System.out.println("2.PayrollPaymentBAction " + e);
			addActionError(e.getMessage());
		}
		finally {

			if (stmt != null) {
				stmt.close();
			}
			if (result != null) {
				result.close();
			}
		}
		return bankcodeList;

	}
	

	@Override
	public String execute() throws Exception {

		Map session = ActionContext.getContext().getSession();
		String USER_ID = (String) session.get("sessUserId");
		String LOCATION = (String) session.get("sessLocationCode");
		/*if (USER_ID == null) {
			addActionError("Session Not Valid!!!");
			return ERROR;
		}*/
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		int  pageNo=Integer.parseInt(request.getParameter("pageNo"));
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
			if (!TMDEPT.equals("ALL")) {
				TMDEPTALL = " and deptcode = '" + TMDEPT + "' ";
			} else {
				TMDEPTALL = " ";
			}
			if (!LOCATION_CODE.equals("ALL")) {
				TMDEPTALL += " and location_code = '" + LOCATION_CODE + "' ";
			} else if (TMDEPTALL == null) {
				TMDEPTALL += " ";
			}
			if (!VTYPE.equals("ALL")) {
				TMDEPTALL += " and pymt_type = '" + VTYPE + "' ";
			} else {
				TMDEPTALL += " ";
			}
			if (!VSTATE.equals("ALL")) {
				TMDEPTALL += " and state_code = '" + VSTATE.trim() + "' ";
			} else {
				TMDEPTALL += " ";
			}
			if (!TMLOCT.equals("ALL")) {
				TMDEPTALL += "and company_code= '" + TMLOCT + "' ";
			} else {
				TMDEPTALL += " ";
			}
			if (!ETYPE.equals("ALL")) {
				TMDEPTALL += "and nvl(cat_code,'S')= '" + ETYPE + "' ";
			} else {
				TMDEPTALL += " ";
			}
			
			
			WebApplicationContext context =
					WebApplicationContextUtils.getRequiredWebApplicationContext(
		                                    ServletActionContext.getServletContext());
	    	PayrollPaymentService service=(PayrollPaymentService)context.getBean(PayrollPaymentService.class);
	    	Map<String,Object>map=service.loadAllCheques(TMDEPTALL,DIVI,pageNo,TMPRID);
	    	Page<PayrollPaymentsBeans>page=(Page<PayrollPaymentsBeans>) map.get(DIVI);
	    	setDetaillist(page.getPageItems());
	    	setCHQ_AMT((double)map.get("CHQ_AMT"));
	        setPageCount(page.getPagesAvailable());
			//   System.out.println(TMDEPTALL+" " + DIVI+"-"+TMPRID+VSTATE);
			statCHK = conn.prepareStatement("select a.pay_id,a.emp_code,acc_data_desc,chq_amount,b.acc_amt from AMSNOW.fa_payroll_chq a,"
					+ " (select c.pay_id,sum(acc_amt) acc_amt from AMSNOW.fa_payroll_chq_Dtls c,AMSNOW.fa_payroll_chq d where c.pay_id=d.pay_id and pymt_date is null and d.chk_upd is null  and  acc_code<>1111"
					+ " and cono=111 and divi=? " + TMDEPTALL + " and to_char(d.pros_date,'MM/YYYY')=? group by c.pay_id) b "
					+ " where cono=111 and divi=? " + TMDEPTALL + " and to_char(a.pros_date,'MM/YYYY')=?"
					+ " and a.pay_id=b.pay_id"
					+ " and chq_amount-acc_amt<>0");

			


			statCHK.setString(1, DIVI);
			statCHK.setString(2, TMPRID);
			
			statCHK.setString(3, DIVI);
			statCHK.setString(4, TMPRID);
			
			resultCHK = statCHK.executeQuery();
			if (resultCHK.next()) {
				disble = "true";
				addActionError(resultCHK.getString("pay_id") + "-" +resultCHK.getString("emp_code") + "-" + resultCHK.getString("acc_data_desc") + " Invalid record.");
				getAllList();
				return SUCCESS;
			}

			statBAL = conn.prepareStatement("select c.pay_id,d.emp_code,acc_data_Desc,sum(acc_amt) acc_amt from AMSNOW.fa_payroll_chq_Dtls c,AMSNOW.fa_payroll_chq d where c.pay_id=d.pay_id and pymt_date is null and d.chk_upd is null "
					+ " and cono=111 and divi=? " + TMDEPTALL + " and to_char(d.pros_date,'MM/YYYY')=? "
					+ " group by c.pay_id,d.emp_code, acc_data_Desc having sum(acc_amt)<>0");
			statBAL.setString(1, DIVI);
			
			statBAL.setString(2, TMPRID);
			resultBAL = statBAL.executeQuery();
			if (resultBAL.next()) {
				disble = "true";
				addActionError(resultBAL.getString("emp_code") + "-" + resultBAL.getString("acc_data_desc") + " Unbalance record found.");
				
				return SUCCESS;
			}

			/*
			stat = conn.prepareStatement("select pay_id,company_code,divi,chq_no,chq_date,acc_data_Desc,year,vser,vono,emp_code,acc_data_Desc,pros_date,fin_upd,chq_amount,bank_name from AMSNOW.fa_payroll_chq where CONO=111 AND divi=? " + TMDEPTALL + " AND to_char(pros_date,'MM/YYYY')=? AND PYMT_DATE is null and nvl(chq_amount,0)>0  order by BANK_CODE,COMPANY_CODE,PYMT_TYPE,pay_id");
			stat.setString(1, DIVI);
			stat.setString(2, TMPRID);
			result = stat.executeQuery();
			CHQ_AMT = 0;
			while (result.next()) {
				CHQ_AMT += result.getDouble("chq_amount");
				detaillist.add(new PayrollPaymentsBeans(result.getString("divi"), result.getString("year"), result.getString("emp_code"), result.getString("acc_data_Desc"), result.getString("VSER"), result.getString("VONO"), result.getString("chq_date"), result.getString("chq_no"), result.getString("pros_date"), result.getString("pay_id"), result.getDouble("chq_amount"), result.getString("bank_name"), result.getString("company_code")));
			}
			conn.commit();*/
		} catch (Exception e) {
			System.out.println("3.PayrollPaymentBAction " + e);
			addActionError(e.getMessage());
			//conn.rollback();
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
		}
		//getAllBankCode();
		getAllList();
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
				}

				conn.commit();
				PayrollPmtBTxt pmt = new PayrollPmtBTxt();
				DONE = pmt.UpdateTxt(USER_ID, LOCATION, DIVI, TMLOCT.toUpperCase(), ETYPE, BATCH_ID + "", TMPRID,VSTATE.trim());
				addActionError(DONE);

			} catch (Exception e) {
				System.out.println("PayrollPaymentBAction " + e);
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
			//getAllBankCode();
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

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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
