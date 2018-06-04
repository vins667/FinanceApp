package shahi.Action.FundReq;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.database.ConnectionDB2HR;

import java.sql.*;
import java.util.Date;
import java.util.*;

import com.opensymphony.xwork2.ActionContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import shahi.Action.FundReq.Beans.FaPayrollTypeMastBean;

import shahi.Action.FundReq.Beans.FundReqBean;
import shahi.Action.database.ConnectionShahiHrisNew;

public class FundRequestAction extends ActionSupport {

    private String aausrid;
    private String vreq;
    private Date start_date1;
    private Date end_date1;
    private String vparty;
    private String vpono;
    private String reqtype;
    private String reqstatus;
    private String payloct;
    private String reqbyid;
    private List detaillst; 
    private List<FaPayrollTypeMastBean> reqtyplist;
    private List<FaPayrollTypeMastBean> payloctlist;

    public FundRequestAction() {
        super();
    }
    
    public String back() throws SQLException{
        reqtyplist = new ArrayList<FaPayrollTypeMastBean>();
        payloctlist = new ArrayList<FaPayrollTypeMastBean>();
        PreparedStatement stat2=null;
        ResultSet result2=null;
        Connection connbi = null;
         try {
            connbi = new ConnectionShahiHrisNew().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
            return ERROR;
        }
         try{
             stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTP' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while(result2.next()){
                reqtyplist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if(result2!=null) result2.close();
            if(stat2!=null) stat2.close();
            
            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTO' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while(result2.next()){
                payloctlist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if(result2!=null) result2.close();
            if(stat2!=null) stat2.close();
        }
        catch(SQLException se){
            addActionError("com.shahi.ams.AMSEmpLeaveEntryAction.java mksess()" + se);
            return ERROR;
        }
        catch(Exception e){
            addActionError("com.shahi.ams.AMSEmpLeaveEntryAction.java mksess()" + e);
            return ERROR;
        }
        finally{
            if(connbi!=null) connbi.close();
            if(result2!=null) result2.close();
            if(stat2!=null) stat2.close();
        }   
         return SUCCESS;
    }

    public String view() throws SQLException, Exception {
        reqtyplist = new ArrayList<FaPayrollTypeMastBean>();
        payloctlist = new ArrayList<FaPayrollTypeMastBean>();
        Connection conndb2 = null;
        Connection connbi = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserCode");
        if(usrid!=null){
            session.remove("sessUserName");
            session.remove("sessUserId");
            session.remove("sessUserCode");
            session.remove("sessUnitCode");
            session.remove("sessShiftDesc");
            session.remove("sessShiftSTARTTIME");
            session.remove("sessShiftENDTIME");
            session.remove("sessMEMEBERSHIPFLAG");
        }
        try {
            conndb2 = new ConnectionDB2HR().getConnection();
            connbi = new ConnectionShahiHrisNew().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
            return ERROR;
        }
        try{
           // stat = conndb2.prepareStatement("SELECT distinct EMPLOYEE.COLUMN1,EMPLOYEE.CODE EMP_CODE,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') EMP_NAME,EMPLOYEE.FACTORYCODE UNIT_CODE,SHIFT.LONGDESCRIPTION SHIFTDESC,STARTTIME, ENDTIME,DIVISIONCODE,MEMEBERSHIPFLAG FROM EMPLOYEE,SHIFT WHERE EMPLOYEE.COMPANYCODE=SHIFT.COMPANYCODE AND EMPLOYEE.WORKSHIFTNOCODE=SHIFT.CODE AND EMPLOYEE.COLUMN1=?"
           //+" UNION select 998819 COLUMN1,998819 EMP_CODE,'JAY PANDEY','F01' UNIT_CODE,'General' SHIFTDESC,'09:30:00' STARTTIME,'18:00:00' ENDTIME,'100' DIVISIONCODE,0 MEMEBERSHIPFLAG FROM sysibm.sysdummy1");// ---hrempview where emp_code=?
           
           stat = conndb2.prepareStatement("SELECT  COLUMN1,EMP_CODE, EMP_NAME,UNIT_CODE,SHIFTDESC,STARTTIME, ENDTIME,DIVISIONCODE,MEMEBERSHIPFLAG from "
                                +"(SELECT distinct EMPLOYEE.COLUMN1,EMPLOYEE.CODE EMP_CODE,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') EMP_NAME,EMPLOYEE.FACTORYCODE UNIT_CODE,SHIFT.LONGDESCRIPTION SHIFTDESC,STARTTIME, ENDTIME,DIVISIONCODE,MEMEBERSHIPFLAG"
                                +" FROM EMPLOYEE,SHIFT WHERE EMPLOYEE.COMPANYCODE=SHIFT.COMPANYCODE AND EMPLOYEE.WORKSHIFTNOCODE=SHIFT.CODE AND EMPLOYEE.COLUMN1=? "
                                +"union "
                                +"select 998819 COLUMN1,998819 EMP_CODE,'JAY PANDEY','F01' UNIT_CODE,'General' SHIFTDESC,'09:30:00' STARTTIME,'18:00:00' ENDTIME,'100' DIVISIONCODE,0 MEMEBERSHIPFLAG FROM sysibm.sysdummy1) where COLUMN1=?");
          
            stat.setString(1,aausrid);
            stat.setString(2,aausrid);
            result=stat.executeQuery();
            if(result.next()){
                String STARTTIME=result.getString("STARTTIME");
                STARTTIME=STARTTIME.substring(0,STARTTIME.lastIndexOf(":"));
                String ENDTIME = result.getString("ENDTIME");
                ENDTIME=ENDTIME.substring(0,ENDTIME.lastIndexOf(":"));
                
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date d = df.parse(STARTTIME);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                cal.add(Calendar.MINUTE, 240);
                String MIDSHIFT = df.format(cal.getTime());
                
                session.put("sessUserName", result.getString("emp_name"));
                session.put("sessUserId", result.getString("emp_code"));
                session.put("sessUserCode", result.getString("COLUMN1").trim());
                session.put("sessUnitCode", result.getString("UNIT_CODE"));
                session.put("sessShiftDesc", result.getString("SHIFTDESC"));
                session.put("sessShiftSTARTTIME", STARTTIME);
                session.put("sessShiftENDTIME", ENDTIME);
                session.put("sessShiftMIDSHIFT", MIDSHIFT);
                session.put("sessDivisionCode",result.getString("DIVISIONCODE"));
                session.put("sessMEMEBERSHIPFLAG",result.getString("MEMEBERSHIPFLAG"));
            }
            else {
             //  stat1 = conndb2.prepareStatement("SELECT distinct EMPLOYEE.CARDNO,EMPLOYEE.CODE EMP_CODE,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') EMP_NAME,EMPLOYEE.FACTORYCODE UNIT_CODE,SHIFT.LONGDESCRIPTION SHIFTDESC,STARTTIME, ENDTIME,DIVISIONCODE,MEMEBERSHIPFLAG FROM EMPLOYEE,SHIFT WHERE EMPLOYEE.COMPANYCODE=SHIFT.COMPANYCODE AND EMPLOYEE.WORKSHIFTNOCODE=SHIFT.CODE AND EMPLOYEE.CARDNO=?"
             //            +" UNION select 998819 COLUMN1,998819 EMP_CODE,'JAY PANDEY','F01' UNIT_CODE,'General' SHIFTDESC,'09:30:00' STARTTIME,'18:00:00' ENDTIME,'100' DIVISIONCODE,0 MEMEBERSHIPFLAG FROM sysibm.sysdummy1");// ---hrempview where emp_code=?);// ---hrempview where emp_code=?
              
              stat1 = conndb2.prepareStatement("SELECT  CARDNO,EMP_CODE, EMP_NAME,UNIT_CODE,SHIFTDESC,STARTTIME, ENDTIME,DIVISIONCODE,MEMEBERSHIPFLAG from "
                                +"(SELECT distinct CARDNO,EMPLOYEE.CODE EMP_CODE,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') EMP_NAME,EMPLOYEE.FACTORYCODE UNIT_CODE,SHIFT.LONGDESCRIPTION SHIFTDESC,STARTTIME, ENDTIME,DIVISIONCODE,MEMEBERSHIPFLAG"
                                +" FROM EMPLOYEE,SHIFT WHERE EMPLOYEE.COMPANYCODE=SHIFT.COMPANYCODE AND EMPLOYEE.WORKSHIFTNOCODE=SHIFT.CODE AND CARDNO=? "
                                +"union "
                                +"select 998819 CARDNO,998819 EMP_CODE,'JAY PANDEY','F01' UNIT_CODE,'General' SHIFTDESC,'09:30:00' STARTTIME,'18:00:00' ENDTIME,'100' DIVISIONCODE,0 MEMEBERSHIPFLAG FROM sysibm.sysdummy1) where CARDNO=?");
          
              stat1.setString(1, aausrid);
              stat1.setString(2, aausrid);
                result1 = stat1.executeQuery();
                if (result1.next()) {
                    String STARTTIME = result1.getString("STARTTIME");
                    STARTTIME = STARTTIME.substring(0, STARTTIME.lastIndexOf(":"));
                    String ENDTIME = result1.getString("ENDTIME");
                    ENDTIME = ENDTIME.substring(0, ENDTIME.lastIndexOf(":"));

                    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                    Date d = df.parse(STARTTIME);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    cal.add(Calendar.MINUTE, 240);
                    String MIDSHIFT = df.format(cal.getTime());

                    session.put("sessUserName", result1.getString("emp_name"));
                    session.put("sessUserId", result1.getString("emp_code"));
                    session.put("sessUserCode", result1.getString("CARDNO").trim());
                    session.put("sessUnitCode", result1.getString("UNIT_CODE"));
                    session.put("sessShiftDesc", result1.getString("SHIFTDESC"));
                    session.put("sessShiftSTARTTIME", STARTTIME);
                    session.put("sessShiftENDTIME", ENDTIME);
                    session.put("sessShiftMIDSHIFT", MIDSHIFT);
                    session.put("sessDivisionCode", result1.getString("DIVISIONCODE"));
                    session.put("sessMEMEBERSHIPFLAG",result.getString("MEMEBERSHIPFLAG"));
                }
            }
            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTP' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while(result2.next()){
                reqtyplist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if(result2!=null) result2.close();
            if(stat2!=null) stat2.close();
            
            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTO' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while(result2.next()){
                payloctlist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if(result2!=null) result2.close();
            if(stat2!=null) stat2.close();
        }
        catch(SQLException se){
            addActionError("com.shahi.ams.AMSEmpLeaveEntryAction.java mksess()" + se);
            return ERROR;
        }
        catch(Exception e){
            addActionError("com.shahi.ams.AMSEmpLeaveEntryAction.java mksess()" + e);
            return ERROR;
        }
        finally{
            if(conndb2!=null) conndb2.close();
            if(connbi!=null) connbi.close();
            if(result!=null) result.close();
            if(stat!=null) stat.close();
            if(result1!=null) result1.close();
            if(stat1!=null) stat1.close();
        }      
        return SUCCESS;
    }
   
    @Override
    public String execute() throws ParseException {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        //usrid = "237519";
        //LOCATION_CODE = "100";
        int flag = 0;
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        Connection connbi = null;
        PreparedStatement stat2 = null;
        ResultSet result2=null;
        reqtyplist = new ArrayList<FaPayrollTypeMastBean>();
        payloctlist = new ArrayList<FaPayrollTypeMastBean>();
        try {
            connbi = new ConnectionShahiHrisNew().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch
        String reqno="";
        
        if(vreq!=null && vreq.length()>0){
            reqno=vreq+"%";
        }
        else{reqno="%";}
        String dateSearch="";
        
        if(start_date1 !=null  && end_date1 != null ){
            dateSearch=" and to_char(REQDT,'yyyy-MM-dd') between '"+new java.sql.Date(start_date1.getTime())+"' and '"+ new java.sql.Date(end_date1.getTime()) +"'";
        }
        if(vparty !=null && vparty.length()>0){
            dateSearch+=" and REQSUNO='"+vparty+"'";
        }
        if(reqtype !=null && reqtype.length()>0){
            dateSearch+=" and REQTYP='"+reqtype+"'";            
        }
        if(reqstatus!=null && reqstatus.length()>0){
            dateSearch+=" and REQSTS='"+reqstatus+"'"; 
        }
        if(payloct!=null && payloct.length()>0){
            dateSearch+=" and PAYLOCT='"+payloct+"'"; 
            
        }
        if(reqbyid!=null && reqbyid.length()>0){
        	 dateSearch+=" and RQCHID='"+reqbyid+"'"; 
        }
        PreparedStatement stat = null;
        ResultSet result = null;

        detaillst = new ArrayList();

        try {  
            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTP' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while(result2.next()){
                reqtyplist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if(result2!=null) result2.close();
            if(stat2!=null) stat2.close();
            
            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTO' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while(result2.next()){
                payloctlist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if(result2!=null) result2.close();
            if(stat2!=null) stat2.close();
        	if(vpono!=null && vpono.length()>0){
        		String pquery=" and b.REQPONO='"+vpono+"'";
        		stat=connbi.prepareStatement("select a.REQNO,to_char(a.REQDT,'dd/mm/yyyy') REQDT,a.REQSUNO,case when a.REQTYP='M' THEN 'Movex PO' when a.REQTYP='G' then 'General PO' when a.REQTYP='B' then 'BillWise' ELSE 'Employee' END REQTYP,"
                        +" a.REQTXT,a.DLVPLC,a.REQSTS,a.PAYLOCT"
                        +" from finacbi.REQMST a,finacbi.REQDTL b"
                        +" where a.REQNO=b.REQNO and  a.REQNO like ? "+dateSearch+pquery+" order by a.REQSTS DESC,a.REQNO DESC");
        	}
        	else{
            stat=connbi.prepareStatement("select REQNO,to_char(REQDT,'dd/mm/yyyy') REQDT,REQSUNO,case when REQTYP='M' THEN 'Movex PO' when REQTYP='G' then 'General PO' when REQTYP='B' then 'BillWise' ELSE 'Employee' END REQTYP,"
                +" REQTXT,DLVPLC,REQSTS,PAYLOCT"
                +" from finacbi.REQMST"
                +" where  REQNO like ? "+dateSearch+" order by REQSTS DESC,REQNO DESC");
        	}
            stat.setString(1,reqno.trim());
            result = stat.executeQuery();
            while(result.next()){
                detaillst.add(new FundReqBean(result.getString("REQNO"), result.getString("REQDT"), result.getString("REQSUNO"), result.getString("REQTYP"), result.getString("REQTXT"), result.getString("DLVPLC"), result.getString("REQSTS"), result.getString("PAYLOCT")));
            }
        } catch (Exception e) {

            flag = 0;
            try {
                flag = 0;                
                connbi.rollback();
            } catch (Exception ee) {
                System.out.print("1 file name : FundRequestAction.java" + ee);

                System.out.println(ee.toString());
            }
            System.out.print("1 file name : FundRequestAction.java" + e);

            System.out.println(e.toString());
        } finally {

            try {

                if (result != null) {
                    result.close();
                }

                if (stat != null) {
                    stat.close();
                }
                
                if (connbi != null) {
                    connbi.close();
                }
                result = null;
                result = null;                
                connbi = null;

            } catch (Exception e) {
                flag = 0;
                System.out.print("File Name : FundRequestAction.java Exception in finally block");
                e.printStackTrace();
            }
        }





        if (flag == 1) {
            addActionMessage("Records Save(s) !!");
            return SUCCESS;

        } else {

            //   addActionMessage("Records Not Save(s) !!");
            return ERROR;
        }


    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getVreq() {
        return vreq;
    }

    public void setVreq(String vreq) {
        this.vreq = vreq;
    }

   public String getVparty() {
        return vparty;
    }

    public Date getStart_date1() {
        return start_date1;
    }

    public void setStart_date1(Date start_date1) {
        this.start_date1 = start_date1;
    }

    public Date getEnd_date1() {
        return end_date1;
    }

    public void setEnd_date1(Date end_date1) {
        this.end_date1 = end_date1;
    }

    public void setVparty(String vparty) {
        this.vparty = vparty;
    }

    public String getReqtype() {
        return reqtype;
    }

    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
    }

    public String getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(String reqstatus) {
        this.reqstatus = reqstatus;
    }

    public String getPayloct() {
        return payloct;
    }

    public void setPayloct(String payloct) {
        this.payloct = payloct;
    }

    public List getDetaillst() {
        return detaillst;
    }

    public void setDetaillst(List detaillst) {
        this.detaillst = detaillst;
    }

	/**
	 * @return the reqbyid
	 */
	public String getReqbyid() {
		return reqbyid;
	}

	/**
	 * @param reqbyid the reqbyid to set
	 */
	public void setReqbyid(String reqbyid) {
		this.reqbyid = reqbyid;
	}

	/**
	 * @return the vpono
	 */
	public String getVpono() {
		return vpono;
	}

	/**
	 * @param vpono the vpono to set
	 */
	public void setVpono(String vpono) {
		this.vpono = vpono;
	}

    public List<FaPayrollTypeMastBean> getReqtyplist() {
        return reqtyplist;
    }

    public void setReqtyplist(List<FaPayrollTypeMastBean> reqtyplist) {
        this.reqtyplist = reqtyplist;
    }

    public List<FaPayrollTypeMastBean> getPayloctlist() {
        return payloctlist;
    }

    public void setPayloctlist(List<FaPayrollTypeMastBean> payloctlist) {
        this.payloctlist = payloctlist;
    }
}
