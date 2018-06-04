/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import shahi.Action.M4bill.Beans.GetTaxRateBean;

import shahi.Action.M4bill.Beans.M3BILLBean;
import shahi.Action.M4bill.Beans.M3BillEntryBean;
import shahi.Action.M4bill.Beans.M3lrbean;
import shahi.Action.M4bill.Beans.Supinv;
import shahi.Action.MI.Beans.TXZ050MIGetTaxRateBean;
import shahi.Action.MI.CRS610MI;
import shahi.Action.MIM4.TXZ050MI;
import shahi.Action.Mis.ShahiInformationList;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionShahiHris;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author RANJEET
 */

public class M3BillEntryAction20170810 extends ActionSupport{
     private List<M3BillEntryBean> deptmastlist=new ArrayList<M3BillEntryBean>();
     private List<M3BillEntryBean> accgrplist=new ArrayList<M3BillEntryBean>();
     private List<M3BILLBean> mastlist=new ArrayList<M3BILLBean>();
     private List<Supinv> suppamtlist=new ArrayList<Supinv>();
     private List<M3BILLBean> typelist=new ArrayList<M3BILLBean>();
     private List<M3BILLBean> subtypelist=new ArrayList<M3BILLBean>();
     private List<M3BILLBean> cclist=new ArrayList<M3BILLBean>();
     
     private List<M3BILLBean> productlist=new ArrayList<M3BILLBean>();
     private List<M3BillEntryBean> billdetail=new ArrayList<M3BillEntryBean>();
     private List mastlisttemp=new ArrayList();
     private List<M3BillEntryBean> billdetailgrp=new ArrayList<M3BillEntryBean>();
     private List<M3BillEntryBean> saveprod=new ArrayList<M3BillEntryBean>();
     private String delflg;
     private List chkdel;
     private List chkdelmaster;
     private String SEARCH_TYPE;
     private String SEARCH_CODE;
     private String TXTID;
      
     
      private String CC_CODE;
      private String CC_DESC;
      private String TYPE_SL_NO="";
      private String SUB_TYPE_SL_NO; 
      private List PRODUCT_SL_NO; 
      private List PRODUCT_AMOUNT;
      private List pchlist=new ArrayList();
      private String PCH;
      
        private String DEPT_SL_NO;
        private String DEPT_DESC;
        private String BILL_NO;        
        private String BILL_DATE;      
        private String SUPPLIER_CODE;
        private String SUPPLIER_DESC;
        private String BILL_AMOUNT ; 
        private String MAST_SL_NO;
        private String SL_NO;
        
        
        private String TYPE_DESC;
        private String SUB_TYPE_DESC;
        private String PRODUCT_DESC;
        private String SAVETYPE;
      
        
        //update
        private List PRODUCT_MAST_SL_NO;
        private List UP_PRODUCT_SL_NO;
        private List UP_PRODUCT_AMOUNT;
        
        private String S_DEPT_DESC;
        private String S_Bill_FROM;
        private String S_Bill_TO;
        private String S_SUPPLIER;
        private String S_Bill_NO;
        private String S_CONTROL;
        private String BILL_DATE1;
        private String BILL_DATE2;
        private String DIS;
        private String SDPT;
        private List<M3BILLBean> costelement=new ArrayList<M3BILLBean>();
        private List BREAK_CODE;
        private List BREAK_AMOUNT;
        private List FORM_TYPE;
        private String GROSS_AMOUNT;
        private List<M3BILLBean> costelementtype=new ArrayList<M3BILLBean>();
        private List<M3BILLBean> grosselemlist=new ArrayList<M3BILLBean>();
        private List<M3BillEntryBean> savecostelement=new ArrayList<M3BillEntryBean>();
        
        private String tempbillamount=null;
        private String tempentgrossamt=null;
        private String BILL_WHLO;
        private String BILL_YEAR;
         
         
        private String SBILLSHIPDATE;
        private List shipbilldatelist=new ArrayList();
        
        private String SSHIPBILL;
        private String SSHIPDATE;
        private String BOS;
        private String SINV;
        
        private List invlist=new ArrayList();
        private List invsavelist=new ArrayList();
        private String INVSLTXT;
        
        private String BILL_SL_NO;
        private String BILL_DT_SL_NO;
        private List INV_NO;
        private List INV_WEIGHT;
        private List INV_SAVE;
        
        private String INV_CC_CODE;
        private String INV_TYPE_SL_NO;
        private String INV_SUB_TYPE_SL_NO;
        private String INV_PRODUCT_SL_NO;
        
        
        //new code
        private List SSHIPBILLNEW;
        private List BOSNEW;
        private List SINVNEW;
        private List INVSLTXTPAGE;
        private List INVSLTXTCTN;
        private List INV_NONEW;
        private List INV_WEIGHTNEW;
        private List INV_SAVENEW;
        private List BILL_DT_SL_NONEW;
        
        private List accchkmaster;
        private String actflg;
        private String FORWARD_DATE;
        private String ACCOUNTDATE;
        private List REMARKS;
        private List UP_REMARKS;
        private String ACC_FLAG;
        private String STATUS;
        private String LOGISSTATUS;
        
        
        private String SSHIPBILLNEWMASTER;
        private String BOSNEWMASTER;
        private String SINVNEWMASTER;
        private List INV_NONEWMASTER;
        private List INV_WEIGHTNEWMASTER;
        private List INV_SAVENEWMASTER;
        
        private String MAST_REMARKS;
        
        private String rejflg;
        private List accchkmasterR;
        private int ctninv=0;
        
        private List REPORT_NO;
        private String ctnflg;
        private List UP_REPORT_NO;
        
        private List CHKINPUT;
        private List UP_CHKINPUT;
        private String recflg;
        private List accrec;
        private List INV_BUYERMASTER;
        private List INV_BUYER;
        private List INV_PCHMASTER;
        private List INV_PCH;
        
        private List acremarks=new ArrayList();
        private String lcert_recv_no;
        
        private String REVERSE_SRVTAX;
        private String REVERSE_SRVTAX_RATE;
        
        private String S_WHLO;
        
        private List buyerlist=new ArrayList();
        private String BUYERCODETEST;
        private String BUYERNAMETEST;
        private List INV_REPORT;
        private String savetest;
        private String SHOWFLAG;
        private String DEBIT_AMOUNT;
        private String DEBIT_REASON;
        private String S_EMP;
        private int ctndebit=0;
        private String ACCESS_FLAG="No";
        private String FIELD_NAME;
        private String SEARCH_TABLE;
        private String DISPLAY_NAME;
        private String SEARCH_FIELD;
        private List BOS_DATE;
        
        private List<M3BILLBean> srvtaxmasterlist=new ArrayList<M3BILLBean>();
        private String SRVTAX_GL_CODE;
        private String REFFLG;
        private String EMPTYPE;
        
        private String ACC_DEBIT_AMOUNT;
        private String ACC_DEBIT_REASON;
        private String ACC_USER_ID;
        private String ENT_USER_ID;
        
        
    
        //LR Details
        private List<M3lrbean> fablrlist;
        private List fablruomlist;
        private List<String> LR_NO;
        private List<String> LR_WEIGHT;
        private List<String> LR_WEIGHT_UOM;
        private List<String> LR_QTY;
        private List<String> LR_MATERIAL_TYPE;
        private List<String> lrdtchk;
        private String SHP_BILL_DATE_S;
        private String REVERSE_SRVTAX_CODE;
        
        private List BOS_LOCT;
        private String NON_SRVTAX_AMOUNT;
        private List supplierAddreessList=new ArrayList();
        private String HSN_CODE;
     
        private List gstlist=new ArrayList();
        private String SUNM;
        private String ADR1;
        private String ADR2;
        private String ADR3;
        private String ADR4;
        private String TOWN;
        private String ECAR;
        private String PONO;
        private String CSCD;
        private List GST_PER;
        private List TAX_CODE;
        private Map<String,String> HSNDESC=new HashMap<String,String>();
        private String SAVEDATA;
        double setbrktotal=0;
        private String DIFF_AMT;
        private String HSNCODEFLAG;
        private String BREAK_AMOUNT_NON_GST;
        
        
       
        public String execute() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        try {

            Connection conn = null;
            Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
                 connbi = new ConnectionShahiHris().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            ResultSet result3 = null;
            ResultSet result2 = null;
            ResultSet result4 = null;
            
            ResultSet result5 = null;

            try {
                
                if(delflg!=null && delflg.equals("Yes"))
                {
                    
                     if(UP_REPORT_NO!=null && UP_REPORT_NO.size()>0)
                {
                    
                    for(int i=0; i<UP_REPORT_NO.size(); i++)
                    {
                        stat1 = conn.prepareStatement("update  m4_bill_master set REPORT_NO=null  where SL_NO=?");
                        stat1.setString(1, UP_REPORT_NO.get(i).toString());
                        stat1.executeUpdate();

                    }
                }   
                    
                if(chkdelmaster!=null && chkdelmaster.size()>0)
                {
                    for(int i=0; i<chkdelmaster.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("delete from m4_bill_detail where BILL_SL_NO=?");
                        stat1.setString(1, chkdelmaster.get(i).toString());
                        int a = stat1.executeUpdate();
                        // stat1 = conn.prepareStatement("delete from m4_bill_add_detail where BILL_SL_NO=?");
                         stat1 = conn.prepareStatement("delete from m4_bill_add_master where BILL_SL_NO=?");
                        stat1.setString(1, chkdelmaster.get(i).toString());
                        a = stat1.executeUpdate();
                        
                           stat1 = conn.prepareStatement("delete from M4_BILL_ADD_BREAKUP where BILL_SL_NO=?");
                           stat1.setString(1, chkdelmaster.get(i).toString());
                           stat1.executeUpdate();
                           
                           stat1 = conn.prepareStatement("delete from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                           stat1.setString(1, chkdelmaster.get(i).toString());
                           stat1.executeUpdate();
                        
                        stat1 = conn.prepareStatement("delete from m4_bill_master where SL_NO=?");
                        stat1.setString(1, chkdelmaster.get(i).toString());
                        a = stat1.executeUpdate();
                         
                          
                            
                      
                }
                }
                }
                
                 if(ctnflg!=null && ctnflg.equals("Yes"))
                {
                
                    
                if(REPORT_NO!=null && REPORT_NO.size()>0)
                {
                    String ctno="0";
                    stat1=conn.prepareStatement("select m4_bill_master_ctn_Sq.nextval from dual");
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                      ctno=result1.getString(1);
                    }
                    if(result1!=null)
                    {
                    result1.close();
                    }
                     if(stat1!=null)
                    {
                    stat1.close();
                    }
                     result1=null;
                     stat1=null;
                     
                    for(int i=0; i<REPORT_NO.size(); i++)
                    {
                        stat1 = conn.prepareStatement("update  m4_bill_master set REPORT_NO=?  where SL_NO=?");
                        stat1.setString(1, ctno);
                        stat1.setString(2, REPORT_NO.get(i).toString());
                        stat1.executeUpdate();

                      
                }
                }
                }
                
                
                
                if(actflg!=null && actflg.equals("Yes"))
                {
                if(accchkmaster!=null && accchkmaster.size()>0)
                {
                    for(int i=0; i<accchkmaster.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("update  m4_bill_master set FORWARD_USER=? ,FORWARD_DATE=sysdate where SL_NO=?");
                        stat1.setString(1, usrid);
                        stat1.setString(2, accchkmaster.get(i).toString());
                        stat1.executeUpdate();

                      
                }
                }
                }
                
               
                stat1 = conn.prepareStatement("select distinct ACCESS_FLAG from M4_BILL_USER_MASTER where EMP_CODE=? and ACCESS_FLAG='Y'");
                stat1.setString(1, usrid);
                result3=stat1.executeQuery();
                if(result3.next())
                {
                ACCESS_FLAG="Yes";
                }
                if(result3!=null)
                {
                result3.close();
                }
                
                if(stat1!=null)
                {
                stat1.close();
                }
                String sqlstr=" ";
                
                
                if(S_DEPT_DESC!=null && S_DEPT_DESC.length()>0)
                {
                 
                  sqlstr=sqlstr+" and upper(b.DEPT_DESC) like '"+S_DEPT_DESC.toUpperCase()+"%'";
               
                }
                if(S_SUPPLIER!=null && S_SUPPLIER.length()>0)
                {
                 
                  sqlstr=sqlstr+" and SUPPLIER_CODE ='"+S_SUPPLIER.toUpperCase()+"'";
               
                }
                if(S_Bill_NO!=null && S_Bill_NO.length()>0)
                {
                 
                  sqlstr=sqlstr+" and Bill_NO  like '"+S_Bill_NO.toUpperCase()+"%'";
               
                }
                 if(S_CONTROL!=null && S_CONTROL.length()>0)
                {
                 
                  sqlstr=sqlstr+" and REPORT_NO ='"+S_CONTROL+"'";
               
                }
                
                 if(S_WHLO!=null && S_WHLO.length()>0)
                 {
                
                          sqlstr=sqlstr+" and BILL_WHLO ='"+S_WHLO+"'";
                 }
                  if(S_EMP!=null && S_EMP.length()>0)
                 {
                
                          sqlstr=sqlstr+" and a.USER_ID ='"+S_EMP+"'";
                 }
                
                 
                 String tempfrom=null;
                 String tempto=null;
                if(S_Bill_FROM!=null && S_Bill_FROM.length()>0)
                {
                 tempfrom=S_Bill_FROM.substring(0,10);
                  
               
                }
                if(S_Bill_TO!=null && S_Bill_TO.length()>0)
                {
                  tempto=S_Bill_TO.substring(0,10);
                  
               
                }
                
                
                if(tempfrom!=null && tempto!=null )
                {
                sqlstr=sqlstr +" and BILL_DATE between to_date('"+tempfrom+"','yyyy-mm-dd') and to_date('"+tempto+"','yyyy-mm-dd')";
                }
                
               
                       
                
                if(sqlstr!=null && sqlstr.trim().length()>0)
                {
                    
                   if(STATUS!=null && STATUS.equals("P"))
                {
                 sqlstr=sqlstr + " and  FORWARD_DATE is null";
                }
               if(STATUS!=null && STATUS.equals("F"))
                {
                 sqlstr=sqlstr + " and  FORWARD_DATE is not null";
                }  
                    
               stat1=conn.prepareStatement("select WAREHOUSE from m4_bill_user_master where EMP_CODE=? and flag='Y'");
               stat1.setString(1,usrid);
               result3=stat1.executeQuery();
               if(result3.next())
               {
                     
                stat1=conn.prepareStatement("select a.SL_NO,DEPT_SL_NO,BILL_NO,to_char(BILL_DATE,'dd/mm/yyyy') BILL_DATE,SUPPLIER_CODE,BILL_AMOUNT,GROSS_AMOUNT,b.DEPT_DESC dept_desc ,to_char(a.FORWARD_DATE,'dd/mm/yyyy') FORWARD_DATE " +
                 " , FORWARD_USER,BILL_WHLO,REPORT_NO ,REVERSE_SRVTAX,SRVTAX_GL_CODE,BILL_YEAR,HSN_CODE from m4_bill_master a,M4_BILL_DEPT_MASTER b where a.DEPT_SL_NO=b.SL_NO and BILL_WHLO in("+result3.getString(1)+") and  DEPT_SL_NO in(select SL_NO from M4_BILL_DEPT_MASTER  where  DEPT_CODE in (select DEPT_SL_NO from  m4_bill_user_master where emp_code=? and flag='Y'))  and trunc(BILL_DATE) > to_date('30-06-2017','dd-mm-yyyy')  "+sqlstr+" order by SL_NO");
               stat1.setString(1, usrid);
                result1=stat1.executeQuery();
                while(result1.next())
                {
                    
                    
                    acremarks=new ArrayList();
                    
                     stat1=conn.prepareStatement("select nvl(sum(PRODUCT_AMOUNT),0) PRODUCT_AMOUNT from m4_bill_detail where BILL_SL_NO=?");
                     stat1.setString(1, result1.getString("SL_NO"));
                     result=stat1.executeQuery();
                     double prdamt=0;
                      
                     if(result.next())
                     {
                     prdamt=result.getDouble(1);
                     }
                      if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      String IDSUNM="";
                       stat1=connbi.prepareStatement("select IDSUNM from   prodbi.cidmas where  IDCONO=111 and IDSUNO=?");
                       stat1.setString(1, result1.getString("SUPPLIER_CODE"));
                       result=stat1.executeQuery();
                    
                        if(result.next())
                         {
                           IDSUNM=result.getString(1);
                         }
                       if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      
                       String full_name="";
                       
                     if(result1.getString("FORWARD_DATE")==null)
                     {
                     stat1=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                     stat1.setString(1, result1.getString("FORWARD_USER"));
                     result=stat1.executeQuery();
                    
                     if(result.next())
                     {
                     full_name=result.getString(1);
                     }
                     
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                    // EXPORTS VALIDATION
                  
                      stat1=conn.prepareStatement("select distinct GL_CODE  from  m4_bill_amt_detail a,m4_cost_element_master b ,m4_bill_master c " +
                                    "      where a.bill_sl_no=? and b.sl_no<>1 and a.COST_ELEMENT=b.SL_NO " +
                                   "  and c.sl_no=a.bill_sl_no and b.dept_sl_no in(select DEPT_CODE from  M4_BILL_DEPT_MASTER where SL_NO= ?)  and gl_code='9999'");
                      stat1.setString(1,result1.getString("SL_NO"));
                      stat1.setString(2,result1.getString("DEPT_SL_NO"));
                    result=stat1.executeQuery();
                    int a=0;
                     if(result.next())
                     {
                         a=1;
                     // 
                     }
                     
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      if(a>0)
                      {
                      stat1=conn.prepareStatement("select distinct TAXABLE from m4_bill_detail where BILL_SL_NO=? and TAXABLE='Y'");
                      stat1.setString(1,result1.getString("SL_NO"));
                      result=stat1.executeQuery();
                      if(result.next())
                     {
                     }else{
                      acremarks.add("Chargeable Amount is Not Selected");
                      }
                      
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      
                      }
                      /*
                      stat1=conn.prepareStatement("select DEPT_CODE from M4_BILL_DEPT_MASTER where SL_NO=? and DEPT_CODE in(1,2,6,8)");
                      stat1.setString(1,result1.getString("DEPT_SL_NO"));
                      result=stat1.executeQuery();
                      int b=0;
                      String tempcode="";
                     if(result.next())
                     {   
                         if(result.getString(1)!=null && result.getString(1).equals("6"))
                         {
                             
                         
                        PreparedStatement stat1dept6=conn.prepareStatement("select  TYPE_SL_NO from  m4_bill_detail where  BILL_SL_NO=? and TYPE_SL_NO in (38,60)");
                         stat1dept6.setString(1,result1.getString("SL_NO"));
                    
                         ResultSet deptresult=stat1dept6.executeQuery();
                         
                         if(deptresult.next())
                         {
                            b=1;
                         tempcode=result.getString(1);
                         }else{
                         b=0;
                         }
                         
                         if(deptresult!=null)
                         {
                         deptresult.close();
                         }
                       if(stat1dept6!=null)
                         {
                         stat1dept6.close();
                         }
                       
                        deptresult=null;
                        stat1dept6=null;
                         }
                         else{
                        
                           b=1;
                            tempcode=result.getString(1);
                         }
                     // 
                     }
                      
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      if(b>0)
                      {
                      stat1=conn.prepareStatement("select distinct INV_NO from   m4_bill_add_master  a,m4_bill_master b   where a.bill_sl_no=b.sl_no and a.bill_sl_no=? ");
                      stat1.setString(1,result1.getString("SL_NO"));
                      result=stat1.executeQuery();
                      if(result.next())
                     {
                         
                     }else{
                          if(tempcode!=null && tempcode.equals("1")){
                          acremarks.add("Invoice Detail is Not Entered");
                          }else if(tempcode!=null && tempcode.equals("8"))
                          {
                          acremarks.add("Report Detail is Not Entered");
                          }
                          else{
                          acremarks.add("AWB Detail is Not Entered");
                          }
                      }
                       
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      
                      }   */
                      
                      
                         PreparedStatement stat1dept6=conn.prepareStatement("select  TYPE_SL_NO from  m4_bill_detail where  BILL_SL_NO=? and TYPE_SL_NO in (32,33,34,35,38,56,60,67,68,69,73,74,75,76,77,78,79,428,1638,1824)");
                         stat1dept6.setString(1,result1.getString("SL_NO"));
                         ResultSet deptresult=stat1dept6.executeQuery();
                         
                         if(deptresult.next())
                         {
                             stat1 = conn.prepareStatement("select distinct INV_NO from   m4_bill_add_master  a,m4_bill_master b   where a.bill_sl_no=b.sl_no and a.bill_sl_no=? ");
                             stat1.setString(1, result1.getString("SL_NO"));
                             result = stat1.executeQuery();
                             if (result.next()) {
                             } else {

                                 acremarks.add("Additional Detail is Not Entered");

                             }
                       
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                         }
                      
                      if(deptresult!=null)
                         {
                         deptresult.close();
                         }
                       if(stat1dept6!=null)
                         {
                         stat1dept6.close();
                         }
                       
                        deptresult=null;
                        stat1dept6=null;
                      
                        if(result1.getString("HSN_CODE")==null)
                      {
                       acremarks.add("HSN Code is not Enter...");
                      }
                       
                       
                      if(result1.getString("HSN_CODE")!=null && (result1.getString("HSN_CODE").equals("GST EXEMPTED-") || result1.getString("HSN_CODE").indexOf("-0")>0) )
                      {
                         
                          
                      }else{
                          stat1dept6=conn.prepareStatement("select * from M4_BILL_AMT_DETAIL where bill_sl_no=? and TAX_CODE is not null");
                          stat1dept6.setString(1,result1.getString("SL_NO"));
                          deptresult=stat1dept6.executeQuery();
                         
                         if(deptresult.next())
                         {
                            
                         }else{
                         acremarks.add("GST not calculated...");
                         }
                      
                      if(deptresult!=null)
                         {
                         deptresult.close();
                         }
                       if(stat1dept6!=null)
                         {
                         stat1dept6.close();
                         }
                       
                        deptresult=null;
                        stat1dept6=null;
                      }
                        
                        
                      
                      stat1=conn.prepareStatement("select DEPT_CODE from M4_BILL_DEPT_MASTER where SL_NO=? and DEPT_CODE=1");
                      stat1.setString(1,result1.getString("DEPT_SL_NO"));
                      result5=stat1.executeQuery();
                      if(result5.next()){
                      stat1=conn.prepareStatement("select distinct INV_NO from  m4_bill_add_master a,m4_bill_master b   where a.bill_sl_no=b.sl_no and a.bill_sl_no=? ");
                      stat1.setString(1,result1.getString("SL_NO"));
                      result=stat1.executeQuery();
                      while(result.next())
                     {
                         stat1=conn.prepareStatement("select buyer from   m4_bill_add_master  where  bill_sl_no=? and INV_NO=? and buyer is null");
                         stat1.setString(1,result1.getString("SL_NO"));
                         stat1.setString(2,result.getString("INV_NO"));
                         result2=stat1.executeQuery();
                         if(result2.next())
                         {
                          acremarks.add("Buyer is Blank - "+result.getString("INV_NO"));
                          if(result2!=null)
                     {
                     result2.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result2=null;
                      stat1=null;
                         break;
                         }
                           
                      if(result2!=null)
                     {
                     result2.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result2=null;
                      stat1=null;
                         
                     }
                        if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      
                      
                      
                       List tempdtpch=new ArrayList();
                      List tempaddpch=new ArrayList();
                      stat1=conn.prepareStatement("select distinct pch from  m4_bill_detail where BILL_SL_NO=? and pch is not null");
                      stat1.setString(1,result1.getString("SL_NO"));
                      result=stat1.executeQuery();
                      while(result.next())
                     {
                     //
                      
                     tempdtpch.add(result.getString(1));
                     }
                        
                      if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      
                      stat1=conn.prepareStatement("select distinct pch from m4_bill_add_master  where BILL_SL_NO=? and pch is not null ");
                      stat1.setString(1,result1.getString("SL_NO"));
                      result2=stat1.executeQuery();
                      while(result2.next())
                     {
                          tempaddpch.add(result2.getString(1));
                     }  
                       
                      if(result2!=null)
                     {
                     result2.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result2=null;
                      stat1=null;
                      
                      if(tempdtpch!=null && tempdtpch.size()>0 && tempaddpch!=null && tempaddpch.size()>0)
                      {
                       for(int i=0; i<tempdtpch.size(); i++)
                       {
                           int aa=0;
                            for(int j=0; j<tempaddpch.size(); j++)
                            {
                                if(tempdtpch.get(i).toString()!=null && tempdtpch.get(i).toString().equals(tempaddpch.get(j).toString()))
                                {
                                   aa=1; 
                                 break;
                                }
                               
                            }
                            
                             if(aa==0){
                                acremarks.add("Bill PCH "+tempdtpch+ " and Invoice PCH "+tempaddpch+" Mismatch");
                                }
                       
                       }
                      }
                      
                       if(tempdtpch!=null && tempdtpch.size()>0 && tempaddpch!=null && tempaddpch.size()>0)
                      {
                       for(int i=0; i<tempaddpch.size(); i++)
                       {
                           int aa=0;
                            for(int j=0; j<tempdtpch.size(); j++)
                            {
                                if(tempaddpch.get(i).toString()!=null && tempaddpch.get(i).toString().equals(tempdtpch.get(j).toString()))
                                {
                                   aa=1; 
                                 break;
                                }
                               
                            }
                            
                             if(aa==0){
                                acremarks.add("Bill PCH "+tempdtpch+ " and Invoice PCH "+tempaddpch+" Mismatch");
                                }
                       
                       }
                      }
                      
                      
                      
                      
                      
                          }
                        
                      if(result5!=null)
                      {
                      result5.close();
                      }
                      if(stat1!=null)
                      {
                      stat1.close();
                      }
                      result5=null;
                       stat1=null;   
                      
                       if(result!=null)
                       {
                       result.close();
                       }
                        if(stat1!=null)
                       {
                       stat1.close();
                       }
                         result=null;    
                         stat1=null;
                       stat1=conn.prepareStatement("select SUB_TYPE_SL_NO,PRODUCT_SL_NO,CC_CODE,pch from  m4_bill_detail where BILL_SL_NO=?");
                      stat1.setString(1,result1.getString("SL_NO"));
                      result=stat1.executeQuery();
                      
                      while(result.next())
                     {
                         
                         if(result2!=null)
                         {                         
                         result2.close();
                         }
                         
                         
                         result2=null;
                         stat1=null;
                         //FINANCE
                        PreparedStatement  stat211=conn.prepareStatement("select shp_bill_no from m4_bill_add_master  where  BILL_SL_NO=? and chq_no is not null");
                         stat211.setString(1,result1.getString("SL_NO"));
                        
                         result2=stat211.executeQuery();
                          
                         while(result2.next())
                         {
                        	PreparedStatement    stat3=conn.prepareStatement("select distinct bill_no from m4_bill_master a,M4_BILL_ADD_MASTER c " +
                                     " where  a.sl_no=c.bill_sl_no and" +
                                    " c.shp_bill_no =?  and a.sl_no<>? and c.chq_no is not null");
                           stat3.setString(1,result2.getString("shp_bill_no"));
                           stat3.setString(2,result1.getString("SL_NO"));
                              
                           result4=stat3.executeQuery();
                              
                           while(result4.next())
                           {
                            	  acremarks.add("S/Bill No - "+result2.getString("shp_bill_no")+" Already Attached in Bill No - "+result4.getString("BILL_NO"));
                           }
                                
                           if(result4!=null)
                           {
                        	   result4.close();
                           }
                           if(stat3!=null)
                           {
                        	   stat3.close();
                           }
                           result4=null;
                           stat3=null;
                         }
                         if(stat211!=null){
                        	 stat211.close();
                         }
                         if(result2!=null){
                        	 result2.close();
                         }
                         //To check duplicate BOS
                         
                            PreparedStatement  statreport=conn.prepareStatement("select bos_no,bos_loct from M4_BILL_ADD_MASTER  where BILL_SL_NO=?");
                           statreport.setString(1,result1.getString("SL_NO"));
                           result2=statreport.executeQuery();
                          
                         while(result2.next())
                         {
                             if(result2.getString("bos_no")!=null && result2.getString("bos_no").length()>0)
                             {
                         PreparedStatement    stat4=conn.prepareStatement("select distinct bill_no from m4_bill_master a,m4_bill_detail b,M4_BILL_ADD_MASTER c " +
                                " where a.sl_no=b.bill_sl_no and a.sl_no=c.bill_sl_no and b.SUB_TYPE_SL_NO=? "+
                               " and c.bos_no=? and a.sl_no<>? and c.bos_loct=?");
                         stat4.setString(1,result.getString("SUB_TYPE_SL_NO"));
                         stat4.setString(2,result2.getString("bos_no"));
                         stat4.setString(3,result1.getString("SL_NO"));
                         stat4.setString(4,result2.getString("bos_loct"));
                         result4=stat4.executeQuery();
                         while(result4.next())
                         {
                              acremarks.add("BOS No - "+result2.getString("bos_no")+" Already Attached in Bill No - "+result4.getString("BILL_NO"));
                          }
                           
                          if(result4!=null)
                     {
                     result4.close();
                     }
                     
                      result4=null;
                      
                             
                             }
                         
                         
                         }
                         
                     if(result2!=null)
                     {
                     result2.close();
                     }
                     
                      if(statreport!=null)
                      {
                      statreport.close();
                      }
                      result2=null;
                      
                         
                         
                            //To check duplicate BOE
                         
                          PreparedStatement  stat211BE=conn.prepareStatement("select boe_no bos_no,LR_NO from m4_bill_add_master  where  BILL_SL_NO=? and boe_no is not null");
                         stat211BE.setString(1,result1.getString("SL_NO"));
                        
                         result2=stat211BE.executeQuery();
                          
                         while(result2.next())
                         {
                              if(result2.getString("bos_no")!=null && result2.getString("bos_no").length()>0)
                             {
                        	 PreparedStatement    stat4=conn.prepareStatement("select distinct bill_no from m4_bill_master a,m4_bill_detail b,M4_BILL_ADD_MASTER c " +
                                " where a.sl_no=b.bill_sl_no and a.sl_no=c.bill_sl_no and b.SUB_TYPE_SL_NO=? "+
                               " and c.boe_no=? and a.sl_no<>? and c.LR_NO=?");
                         stat4.setString(1,result.getString("SUB_TYPE_SL_NO"));
                         stat4.setString(2,result2.getString("bos_no"));
                         stat4.setString(3,result1.getString("SL_NO"));
                         stat4.setString(4,result2.getString("LR_NO"));
                              
                           result4=stat4.executeQuery();
                              
                           while(result4.next())
                           {
                            	  acremarks.add("BOE No - "+result2.getString("bos_no")+" Already Attached in Bill No - "+result4.getString("BILL_NO"));
                           }
                                
                           if(result4!=null)
                           {
                        	   result4.close();
                           }
                           if(stat4!=null)
                           {
                        	   stat4.close();
                           }
                           result4=null;
                           stat4=null;
                         }}
                         if(stat211BE!=null){
                        	 stat211BE.close();
                         }
                         if(result2!=null){
                        	 result2.close();
                         }
                       
                         
                         
                         PreparedStatement  stat21=conn.prepareStatement("select LR_NO from m4_bill_add_master  where  BILL_SL_NO=?");
                         stat21.setString(1,result1.getString("SL_NO"));
                        
                         result2=stat21.executeQuery();
                          
                         while(result2.next())
                         {
                        	PreparedStatement    stat3=conn.prepareStatement("select distinct bill_no from m4_bill_master a,M4_BILL_ADD_MASTER c " +
                                     " where  a.sl_no=c.bill_sl_no and" +
                                    " c.LR_NO =? and a.supplier_code=? and a.sl_no<>? and a.BILL_YEAR=?");
                           stat3.setString(1,result2.getString("LR_NO"));
                           stat3.setString(2,result1.getString("SUPPLIER_CODE"));
                           stat3.setString(3,result1.getString("SL_NO"));
                           stat3.setString(4,result1.getString("BILL_YEAR"));
                           
                              
                           result4=stat3.executeQuery();
                              
                           while(result4.next())
                           {
                            	  acremarks.add("LR No - "+result2.getString("LR_NO")+" Already Attached in Bill No - "+result4.getString("BILL_NO"));
                           }
                                
                           if(result4!=null)
                           {
                        	   result4.close();
                           }
                           if(stat3!=null)
                           {
                        	   stat3.close();
                           }
                           result4=null;
                           stat3=null;
                         }
                         if(stat21!=null){
                        	 stat21.close();
                         }
                         if(result2!=null){
                        	 result2.close();
                         }
                         
                        
                         PreparedStatement  stat2=conn.prepareStatement("select INV_NO,AWB_NO from m4_bill_add_master  where  BILL_SL_NO=?");
                         stat2.setString(1,result1.getString("SL_NO"));
                        
                         result2=stat2.executeQuery();
                          
                         while(result2.next())
                         {
                             if(result2.getString("INV_NO")!=null && result2.getString("INV_NO").length()>0)
                             {
                       PreparedStatement    stat3=conn.prepareStatement("select distinct bill_no from m4_bill_master a,m4_bill_detail b,M4_BILL_ADD_MASTER c " +
                                " where a.sl_no=b.bill_sl_no and a.sl_no=c.bill_sl_no and b.SUB_TYPE_SL_NO=? and b.PRODUCT_SL_NO=? and " +
                                    "b.CC_CODE=? and " +
                                    "b.pch=? and" +
                               " c.inv_no =? and a.sl_no<>?");
                         stat3.setString(1,result.getString("SUB_TYPE_SL_NO"));
                         stat3.setString(2,result.getString("PRODUCT_SL_NO"));
                         stat3.setString(3,result.getString("CC_CODE"));
                         stat3.setString(4,result.getString("pch"));
                         stat3.setString(5,result2.getString("INV_NO"));
                         stat3.setString(6,result1.getString("SL_NO"));
                         
                         result4=stat3.executeQuery();
                         
                         while(result4.next())
                         {
                            acremarks.add("Invoice - "+result2.getString("INV_NO")+" Already Attached in Bill No - "+result4.getString("BILL_NO"));
                         }
                           
                          if(result4!=null)
                     {
                     result4.close();
                     }
                      if(stat3!=null)
                     {
                     stat3.close();
                     }
                      result4=null;
                      stat3=null;
                             
                             }
                           if(result2.getString("AWB_NO")!=null && result2.getString("AWB_NO").length()>0){
                       PreparedStatement    stat4=conn.prepareStatement("select distinct bill_no from m4_bill_master a,m4_bill_detail b,M4_BILL_ADD_MASTER c " +
                                " where a.sl_no=b.bill_sl_no and a.sl_no=c.bill_sl_no and b.SUB_TYPE_SL_NO=? and b.PRODUCT_SL_NO=? and " +
                                    "b.CC_CODE=? and " +
                                    "b.pch=? and" +
                               " c.AWB_NO =? and a.sl_no<>?");
                         stat4.setString(1,result.getString("SUB_TYPE_SL_NO"));
                         stat4.setString(2,result.getString("PRODUCT_SL_NO"));
                         stat4.setString(3,result.getString("CC_CODE"));
                         stat4.setString(4,result.getString("pch"));
                         stat4.setString(5,result2.getString("AWB_NO"));
                         stat4.setString(6,result1.getString("SL_NO"));
                         result4=stat4.executeQuery();
                         while(result4.next())
                         {
                             acremarks.add("AWB No - "+result2.getString("AWB_NO")+" Already Attached in Bill No - "+result4.getString("BILL_NO"));
                         }
                           
                        if(result4!=null)
                     {
                     result4.close();
                     }
                      if(stat4!=null)
                     {
                     stat4.close();
                     }
                      result4=null;
                      stat1=null;
                             }  
                         if(result4!=null)
                         {
                         result4.close();
                         }
                         if(stat1!=null)
                         {
                         stat1.close();
                         }
                         result4=null;
                         stat1=null;
                         
                         
                         }
                         
                         if(result2!=null)
                     {
                     result2.close();
                     }
                      if(stat2!=null)
                     {
                     stat2.close();
                     }
                       result2=null;
                       stat2=null;
                       
                          statreport=conn.prepareStatement("select a.BUYER,b.REPORT_NO from M4_BILL_ADD_MASTER a,M4_BILL_ADD_BREAKUP b where a.bill_sl_no=b.bill_sl_no and a.BILL_SL_NO=?");
                         statreport.setString(1,result1.getString("SL_NO"));
                        
                         result2=statreport.executeQuery();
                          
                         while(result2.next())
                         {
                             if(result2.getString("REPORT_NO")!=null && result2.getString("REPORT_NO").length()>0)
                             {
                       PreparedStatement    stat3=conn.prepareStatement("select distinct bill_no from m4_bill_master a,m4_bill_detail b,M4_BILL_ADD_BREAKUP c,M4_BILL_ADD_MASTER d " +
                                " where a.sl_no=b.bill_sl_no and a.sl_no=c.bill_sl_no and a.sl_no=d.bill_sl_no and b.SUB_TYPE_SL_NO=? and b.PRODUCT_SL_NO=? and " +
                                    "b.CC_CODE=? and " +
                                    "b.pch=? and" +
                               " c.REPORT_NO =?  and a.sl_no<>? and d.BUYER=?");
                         stat3.setString(1,result.getString("SUB_TYPE_SL_NO"));
                         stat3.setString(2,result.getString("PRODUCT_SL_NO"));
                         stat3.setString(3,result.getString("CC_CODE"));
                         stat3.setString(4,result.getString("pch"));
                         stat3.setString(5,result2.getString("REPORT_NO"));
                         stat3.setString(6,result1.getString("SL_NO"));
                         stat3.setString(7,result2.getString("BUYER"));
                         
                         result4=stat3.executeQuery();
                         
                         while(result4.next())
                         {
                             acremarks.add("Report - "+result2.getString("REPORT_NO")+" Already Attached in Bill No - "+result4.getString("BILL_NO"));
                         }
                           
                          if(result4!=null)
                     {
                     result4.close();
                     }
                      if(stat3!=null)
                     {
                     stat3.close();
                     }
                      result4=null;
                      stat3=null;
                             
                             }
                         
                         
                         }
                         
                     if(result2!=null)
                     {
                     result2.close();
                     }
                      if(stat2!=null)
                     {
                     stat2.close();
                     }
                      if(statreport!=null)
                      {
                      statreport.close();
                      }
                      result2=null;
                      stat2=null;
                         
                     }
                        
                        if(result!=null)
                     {
                     result.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result=null;
                      stat1=null;
                      
                    
                     if(result1.getString("REVERSE_SRVTAX")!=null && result1.getString("REVERSE_SRVTAX").equals("1") && result1.getString("SRVTAX_GL_CODE")==null)
                     {
                     //acremarks.add("Reverse Service Tax  GL Code   is Not Entered");
                     }
                      
                      
                     }
                       String DEBITAMOUNT="";
                       PreparedStatement  stat1debit=conn.prepareStatement("select DEBIT_AMOUNT,DEBIT_REASON from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                        stat1debit.setString(1, result1.getString("SL_NO"));
                        ResultSet resultdebit=stat1debit.executeQuery();
                        if(resultdebit.next())
                        {
                         DEBITAMOUNT=resultdebit.getString(1);
                        // DEBIT_REASON=resultdebit.getString(2);

                        }
                       if(resultdebit!=null)
                       {
                       resultdebit.close();
                       }
                       if(stat1debit!=null)
                       {
                       stat1debit.close();
                       }
                     
                      //CLOSE EXPORTS VALIDATION
                    deptmastlist.add(new M3BillEntryBean(result1.getString("SL_NO"),result1.getString("dept_desc"),result1.getString("BILL_NO"),result1.getString("BILL_DATE"),result1.getString("SUPPLIER_CODE")+"-"+IDSUNM,result1.getDouble("BILL_AMOUNT"),
                            prdamt,result1.getDouble("GROSS_AMOUNT"),result1.getString("FORWARD_DATE"),full_name,result1.getString("BILL_WHLO"),result1.getString("REPORT_NO"),acremarks,DEBITAMOUNT));
                    flag=1;
                }
                 if(result1!=null)
                     {
                     result1.close();
                     }
                      if(stat1!=null)
                     {
                     stat1.close();
                     }
                      result1=null;
                      stat1=null;
            }
            }

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M3BillEntryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M3BillEntryAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }
                      if (result2 != null) {
                        result2.close();
                    }
                       if (result3 != null) {
                        result3.close();
                    }
                        if (result4 != null) {
                        result4.close();
                    }

                          if (result5 != null) {
                        result5.close();
                    }
                           
                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                     result2 = null;
                      result3 = null;
                       result4 = null;
                        result5 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
           
           
            return SUCCESS;
        } else {

            addActionMessage("Records Not Found(s) !!");
            return ERROR;
        }
     
     }
     
     
     public String exeac() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return "accpage";
        }

        try {

            Connection conn = null;
            Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
                 connbi = new ConnectionShahiHris().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            ResultSet result3=null;

            try {
                
                  if(recflg!=null && recflg.equals("Yes"))
                {
                if(accrec!=null && accrec.size()>0)
                {
                    for(int i=0; i<accrec.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("update  m4_bill_master set REC_ACC_DATE=sysdate ,REC_ACC_USER=? where SL_NO=?");
                        stat1.setString(1, usrid);
                        stat1.setString(2, accrec.get(i).toString());
                        stat1.executeUpdate();

                      
                }
                }
                }
                
                
                
                
                
                 if(rejflg!=null && rejflg.equals("Yes"))
                {
                if(accchkmasterR!=null && accchkmasterR.size()>0)
                {
                    for(int i=0; i<accchkmasterR.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("update  m4_bill_master set FORWARD_USER=null ,REC_ACC_DATE=null ,REC_ACC_USER=null ,FORWARD_DATE=null,REPORT_NO=null,REJ_USER=?,REJ_DATE=sysdate,OLD_REPORT_NO=REPORT_NO where SL_NO=?");
                        stat1.setString(1, usrid);
                        stat1.setString(2, accchkmasterR.get(i).toString());
                        stat1.executeUpdate();

                      
                }
                }
                }
                
                if(actflg!=null && actflg.equals("Yes"))
                {
                if(accchkmaster!=null && accchkmaster.size()>0)
                {
                    for(int i=0; i<accchkmaster.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("update  m4_bill_master set ACCOUNT_USER=? ,ACCOUNT_DATE=sysdate where SL_NO=?");
                        stat1.setString(1, usrid);
                        stat1.setString(2, accchkmaster.get(i).toString());
                        stat1.executeUpdate();

                      
                }
                }
                }
                
                
                
                String sqlstr="";
                if(STATUS!=null && STATUS.equals("F"))
                {
                sqlstr=sqlstr+" and FORWARD_DATE is not null and REPORT_NO is not null  and REC_ACC_DATE is  null";
                }
                if(STATUS!=null && STATUS.equals("P"))
                {
                sqlstr=sqlstr+" and ACCOUNT_DATE is not null";
                }
                
                if(STATUS!=null && STATUS.equals("L"))
                {
                sqlstr=sqlstr+" and REC_ACC_DATE is not null and ACCOUNT_DATE is  null";
                }
                
                
                if(STATUS!=null && STATUS.equals("R"))
                {
                sqlstr=sqlstr+" and REC_ACC_DATE is not null";
                }
                if(STATUS!=null && STATUS.equals("A"))
                {
                sqlstr=sqlstr+"and FORWARD_DATE is not null ";
                }
                if(S_DEPT_DESC!=null && S_DEPT_DESC.length()>0)
                {
                 
                  sqlstr=sqlstr+" and upper(b.DEPT_DESC) like '"+S_DEPT_DESC.toUpperCase()+"%'";
               
                }
                 if(S_SUPPLIER!=null && S_SUPPLIER.length()>0)
                {
                 
                  sqlstr=sqlstr+" and SUPPLIER_CODE ='"+S_SUPPLIER.toUpperCase()+"'";
               
                }
                if(S_Bill_NO!=null && S_Bill_NO.length()>0)
                {
                 
                  sqlstr=sqlstr+" and Bill_NO  like '"+S_Bill_NO.toUpperCase()+"%'";
               
                }
                
                 if(S_CONTROL!=null && S_CONTROL.length()>0)
                {
                 
                  sqlstr=sqlstr+" and REPORT_NO ='"+S_CONTROL+"'";
               
                }
                 
                
                 String tempfrom=null;
                 String tempto=null;
                if(S_Bill_FROM!=null && S_Bill_FROM.length()>0)
                {
                 tempfrom=S_Bill_FROM.substring(0,10);
                  
               
                }
                if(S_Bill_TO!=null && S_Bill_TO.length()>0)
                {
                  tempto=S_Bill_TO.substring(0,10);
                  
               
                }
                
                if(tempfrom!=null && tempto!=null )
                {
                sqlstr=sqlstr +" and BILL_DATE between to_date('"+tempfrom+"','yyyy-mm-dd') and to_date('"+tempto+"','yyyy-mm-dd')";
                }
                
                ArrayList temparr=new ArrayList();
                 if(sqlstr!=null && sqlstr.trim().length()>0)
                {
               stat1=conn.prepareStatement("select WAREHOUSE from m4_bill_user_master where EMP_CODE=? and flag='Y'");
               stat1.setString(1,usrid);
               result3=stat1.executeQuery();
               if(result3.next())
               {
               
                stat1=conn.prepareStatement("select a.SL_NO,DEPT_SL_NO,BILL_NO,to_char(BILL_DATE,'dd/mm/yyyy') BILL_DATE,SUPPLIER_CODE,BILL_AMOUNT,GROSS_AMOUNT,b.DEPT_DESC dept_desc,IDSUNM ,to_char(a.FORWARD_DATE,'dd/mm/yyyy') FORWARD_DATE " +
                 " , FORWARD_USER,BILL_WHLO,to_char(ACCOUNT_DATE,'dd/mm/yyyy') ACCOUNT_DATE,ACCOUNT_USER,REPORT_NO,REC_ACC_USER,to_char(REC_ACC_DATE,'dd/mm/yyyy') REC_ACC_DATE,a.OLD_REPORT_NO,REVERSE_SRVTAX,SRVTAX_GL_CODE,REVERSE_SRVTAX_RATE,REVERSE_SRVTAX_CODE from m4_bill_master a,M4_BILL_DEPT_MASTER b,prodbi.cidmas@ams c  where a.DEPT_SL_NO=b.SL_NO and IDCONO=111 and SUPPLIER_CODE=IDSUNO and BILL_WHLO in ("+result3.getString(1)+") and  DEPT_SL_NO in(select SL_NO from M4_BILL_DEPT_MASTER  where  DEPT_CODE in (select DEPT_SL_NO from  m4_bill_user_master where emp_code=? and flag='Y'))  and trunc(BILL_DATE) > to_date('30-06-2017','dd-mm-yyyy')   "+sqlstr+" order by REPORT_NO desc, BILL_NO asc");
                stat1.setString(1,usrid);
                result1=stat1.executeQuery();
                while(result1.next())
                {
                   PreparedStatement  stat2=conn.prepareStatement("select nvl(sum(PRODUCT_AMOUNT),0) PRODUCT_AMOUNT from m4_bill_detail where BILL_SL_NO=?");
                     stat2.setString(1, result1.getString("SL_NO"));
                     result=stat2.executeQuery();
                     double prdamt=0;
                     if(result.next())
                     {
                     prdamt=result.getDouble(1);
                     }
                     
                     stat2=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                     stat2.setString(1, result1.getString("FORWARD_USER"));
                     result=stat2.executeQuery();
                     String full_name="";
                     if(result.next())
                     {
                     full_name=result.getString(1);
                     }
                     stat2=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                     stat2.setString(1, result1.getString("ACCOUNT_USER"));
                     result=stat2.executeQuery();
                     String full_name1="";
                     if(result.next())
                     {
                     full_name1=result.getString(1);
                     }
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat2!=null)
                     {
                     stat2.close();
                     }
                      result=null;
                      stat2=null;
                      
                       String DEBITAMOUNT="";
                       PreparedStatement  stat1debit=conn.prepareStatement("select DEBIT_AMOUNT,DEBIT_REASON from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                        stat1debit.setString(1, result1.getString("SL_NO"));
                        ResultSet resultdebit=stat1debit.executeQuery();
                        if(resultdebit.next())
                        {
                         DEBITAMOUNT=resultdebit.getString(1);
                        // DEBIT_REASON=resultdebit.getString(2);

                        }
                       if(resultdebit!=null)
                       {
                       resultdebit.close();
                       }
                       if(stat1debit!=null)
                       {
                       stat1debit.close();
                       }
                      
                     if(!temparr.contains(result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO"))) 
                     {
                         temparr.add(result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO"));
                      accgrplist.add(new M3BillEntryBean(result1.getString("dept_desc"), result1.getString("SUPPLIER_CODE")+"-"+result1.getString("IDSUNM"), result1.getString("REPORT_NO")
                             ,"" ,result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO")));
                     }
                    
                     String glflag=null;
                     
                     if(result1.getString("REVERSE_SRVTAX")!=null && result1.getString("REVERSE_SRVTAX").equals("1") && result1.getString("SRVTAX_GL_CODE")==null)
                     {
                         if(result1.getString("REVERSE_SRVTAX_CODE")!=null && result1.getString("REVERSE_SRVTAX_CODE").equals("0"))
                         {}else{
                        
                             
                             glflag="No";
                         }
                     }
                     
                    deptmastlist.add(new M3BillEntryBean(result1.getString("SL_NO"),result1.getString("dept_desc"),result1.getString("BILL_NO"),result1.getString("BILL_DATE"),result1.getString("SUPPLIER_CODE")+"-"+result1.getString("IDSUNM"),result1.getDouble("BILL_AMOUNT"),
                            prdamt,result1.getDouble("GROSS_AMOUNT"),result1.getString("FORWARD_DATE"),full_name,result1.getString("ACCOUNT_DATE"),full_name1,result1.getString("BILL_WHLO"),result1.getString("REPORT_NO"),result1.getString("REC_ACC_DATE"),result1.getString("REC_ACC_USER"),
                            result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO"),DEBITAMOUNT,result1.getString("OLD_REPORT_NO"),glflag));
                    flag=1;
                }
            }
            }

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M3BillEntryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M3BillEntryAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return "accpage";

        }

        if (flag == 1) {
           
           
            return "accpage";
        } else {

            addActionMessage("Records Not Found(s) !!");
            return "accpage";
        }
     
     }
     
     
   public String save() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       // HttpServletRequest request = ServletActionContext.getRequest();
       
        EMPTYPE="ENT";
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try { 
                
               
                if(MAST_SL_NO==null){
                   stat1=conn.prepareStatement("select m4_bill_master_Sq.nextval from dual");
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                      MAST_SL_NO=result1.getString(1);
                    }
                    
                  if(BREAK_AMOUNT!=null && BREAK_AMOUNT.size()>0)  
                  {
                   for(int i=0; i<BREAK_AMOUNT.size(); i++)
                   {
                    if(BREAK_CODE!=null && BREAK_CODE.get(i).toString()!=null && BREAK_CODE.get(i).toString().length()>0  &&   BREAK_AMOUNT!=null && BREAK_AMOUNT.get(i).toString()!=null && BREAK_AMOUNT.get(i).toString().length()>0 
                            && Double.parseDouble(BREAK_AMOUNT.get(i).toString())>0)
                    {
                        stat1=conn.prepareStatement("select M4_BILL_AMT_DETAIL_sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         String formtypetemp=null;
                         String formwhltemp=null;
                      /*  if(FORM_TYPE.get(i).toString()!=null && FORM_TYPE.get(i).toString().length()>0)
                        {
                        formwhltemp=FORM_TYPE.get(i).toString().trim().substring(0,FORM_TYPE.get(i).toString().indexOf("-"));
                        formtypetemp=FORM_TYPE.get(i).toString().trim().substring(FORM_TYPE.get(i).toString().indexOf("-")+1,FORM_TYPE.get(i).toString().length());
                        
                        
                        
                        }*/
                         
                       
                         String GSTPER=null;
                         if(GST_PER.get(i).toString()!=null && GST_PER.get(i).toString().length()>0)
                         {
                         GSTPER=GST_PER.get(i).toString();
                         }
                          String TAXCODE=null;
                         if(TAX_CODE.get(i).toString()!=null && TAX_CODE.get(i).toString().length()>0)
                         {
                         TAXCODE=TAX_CODE.get(i).toString();
                         }
                         
                         
                      stat1=conn.prepareStatement("insert into M4_BILL_AMT_DETAIL(SL_NO,BILL_SL_NO,COST_ELEMENT,AMT,FORM_TYPE,TDATE,USER_ID,FORM_WHLO,GST_PER,TAX_CODE) values(?,?,?,?,?,sysdate,?,?,?,?)");
                      stat1.setString(1, brkslno);
                      stat1.setString(2, MAST_SL_NO);
                      stat1.setString(3, BREAK_CODE.get(i).toString());
                      stat1.setString(4, BREAK_AMOUNT.get(i).toString());
                      stat1.setString(5, formtypetemp);
                      stat1.setString(6, usrid);
                      stat1.setString(7, formwhltemp);
                      stat1.setString(8, GSTPER);
                      stat1.setString(9, TAXCODE);
                      
                      stat1.executeUpdate();
                    }
                   
                   }
                  
                  }
                  
                  /* not working
                   if(INV_SAVENEWMASTER!=null && INV_SAVENEWMASTER.size()>0)  
                  {
                   for(int i=0; i<INV_SAVENEWMASTER.size(); i++)
                   {
                    if(INV_SAVENEWMASTER!=null && INV_SAVENEWMASTER.get(i).toString()!=null && INV_SAVENEWMASTER.get(i).toString().length()>0  &&   
                            INV_SAVENEWMASTER.get(i).toString().equals("Yes"))
                    {
                        stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("26notworking"))
                         {
                            stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,INV_NO,INV_WEIGHT,TDATE,USER_ID,BUYER,pch) values(?,?,?,?,sysdate,?,?,?)");
                            stat1.setString(1, brkslno);
                            stat1.setString(2, MAST_SL_NO);
                            stat1.setString(3, INV_NONEWMASTER.get(i).toString());
                            stat1.setString(4, INV_WEIGHTNEWMASTER.get(i).toString());
                            stat1.setString(5, usrid);
                            stat1.setString(6, INV_BUYERMASTER.get(i).toString());
                            stat1.setString(7, INV_PCHMASTER.get(i).toString());
                            stat1.executeUpdate();
                         }
                          if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("27"))
                         {
                            stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,AWB_NO,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                            stat1.setString(1, brkslno);
                            stat1.setString(2, MAST_SL_NO);
                            stat1.setString(3, INV_NONEWMASTER.get(i).toString().toUpperCase());
                            stat1.setString(4, usrid);
                            stat1.executeUpdate();
                         }
                    }
                   
                   }
                  
                  }  */
                    if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("29111") && INV_NONEWMASTER!=null && INV_NONEWMASTER.size()>0)
                         {
                   for(int i=0; i<INV_NONEWMASTER.size(); i++)
                   {
                    if(INV_NONEWMASTER!=null && INV_NONEWMASTER.get(i).toString()!=null && INV_NONEWMASTER.get(i).toString().length()>0 )
                    {  
                       stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                        
                            stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,AWB_NO,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                            stat1.setString(1, brkslno);
                            stat1.setString(2, MAST_SL_NO);
                            stat1.setString(3, INV_NONEWMASTER.get(i).toString().toUpperCase());
                            stat1.setString(4, usrid);
                            stat1.executeUpdate();
                         } 
                   }}
                    
                   
                    //testing 
                    /*
                    if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("35") && BUYERCODETEST!=null && BUYERCODETEST.length()>0 && INV_REPORT!=null && INV_REPORT.size()>0)
                         {
                   for(int i=0; i<INV_REPORT.size(); i++)
                   {
                    if(INV_REPORT!=null && INV_REPORT.get(i).toString()!=null && INV_REPORT.get(i).toString().length()>0 )
                    {  
                       
                           if(i==0){
                               stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                                result1=stat1.executeQuery();
                                String brkslno="0";
                                if(result1.next())
                                {
                                  brkslno=result1.getString(1);
                                }
                            stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,BUYER,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                            stat1.setString(1, brkslno);
                            stat1.setString(2, MAST_SL_NO);
                            stat1.setString(3, BUYERCODETEST);
                            stat1.setString(4, usrid);
                            stat1.executeUpdate();
                           }
                                stat1=conn.prepareStatement("select M4_BILL_ADD_BREAKUP_sq.nextval from dual");
                                result1=stat1.executeQuery();
                                String brkslno="0";
                                if(result1.next())
                                {
                                  brkslno=result1.getString(1);
                                }
                                
                                stat1=conn.prepareStatement("insert into M4_BILL_ADD_BREAKUP(SL_NO,BILL_SL_NO,REPORT_NO,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                                stat1.setString(1, brkslno);
                                stat1.setString(2, MAST_SL_NO);
                                stat1.setString(3, INV_REPORT.get(i).toString().toUpperCase());
                                stat1.setString(4, usrid);
                                stat1.executeUpdate();
                           
                           
                         } 
                   }}
                    */
                    //close testing
                    
                    
                    
                stat1=conn.prepareStatement("insert into m4_bill_master(SL_NO,DEPT_SL_NO,BILL_NO,BILL_DATE,SUPPLIER_CODE,BILL_AMOUNT,TDATE,USER_ID,GROSS_AMOUNT,BILL_WHLO,BILL_YEAR,remarks,REVERSE_SRVTAX,REVERSE_SRVTAX_RATE,SRVTAX_GL_CODE,REVERSE_SRVTAX_CODE,NON_SRVTAX_AMOUNT,SASUNM,SAADR1,SAADR2,SAADR3,SAADR4,SATOWN,SAECAR,SAPONO,SACSCD,HSN_CODE,NON_GST_AMOUNT) values(?,?,?,to_date(?,'dd/mm/yyyy'),?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,upper(?),?)");
                stat1.setString(1, MAST_SL_NO);
                stat1.setString(2, DEPT_SL_NO);
                stat1.setString(3, BILL_NO);
                stat1.setString(4, BILL_DATE);
                stat1.setString(5, SUPPLIER_CODE);
                stat1.setString(6, BILL_AMOUNT);
                stat1.setString(7, usrid);
                stat1.setString(8, GROSS_AMOUNT);
                stat1.setString(9, BILL_WHLO);
                stat1.setString(10, BILL_YEAR);
                stat1.setString(11, MAST_REMARKS);
                stat1.setString(12, REVERSE_SRVTAX);
                stat1.setString(13, REVERSE_SRVTAX_RATE);
                stat1.setString(14, SRVTAX_GL_CODE);
                stat1.setString(15, REVERSE_SRVTAX_CODE);
                stat1.setString(16, NON_SRVTAX_AMOUNT);
                stat1.setString(17, SUPPLIER_DESC);
                stat1.setString(18, ADR1);
                stat1.setString(19, ADR2);
                stat1.setString(20, ADR3);
                stat1.setString(21, ADR4);
                stat1.setString(22, TOWN);
                stat1.setString(23, ECAR);
                stat1.setString(24, PONO);
                stat1.setString(25, CSCD);
                stat1.setString(26, HSN_CODE);
                stat1.setString(27, BREAK_AMOUNT_NON_GST);
                
                        
                flag=stat1.executeUpdate();
                
                if(DEBIT_AMOUNT!=null && DEBIT_AMOUNT.length()>0 && Double.parseDouble(DEBIT_AMOUNT)>0)
                {
                  stat1=conn.prepareStatement("insert into M4_BILL_DEBIT_DETAIL(BILL_SL_NO,DEBIT_AMOUNT,DEBIT_REASON,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                  stat1.setString(1, MAST_SL_NO);
                  stat1.setString(2, DEBIT_AMOUNT);
                  stat1.setString(3, DEBIT_REASON);
                  stat1.setString(4, usrid);
                  stat1.executeUpdate();
                }
                
                }else{
                flag=1;
                   // stat1 = conn.prepareStatement("update   m4_bill_detail set bill_date1=? , bill_date2=? where BILL_SL_NO=?");
                   // stat1.setString(1, BILL_DATE1);
                    //stat1.setString(2, BILL_DATE2);
                   // stat1.setString(3, MAST_SL_NO);
                   // stat1.executeUpdate();
                }
                if(flag>0)
                {  
                    
                     
                    
                    
                    int ctn=0;
                    if(UP_PRODUCT_SL_NO!=null && UP_PRODUCT_SL_NO.size()>0)
                {
                     for(int i=0; i<UP_PRODUCT_SL_NO.size(); i++){
                     if(UP_PRODUCT_SL_NO.get(i).toString()!=null && UP_PRODUCT_SL_NO.get(i).toString().length()>0 &&
                            UP_PRODUCT_AMOUNT.get(i).toString()!=null && UP_PRODUCT_AMOUNT.get(i).toString().length()>0 && Double.parseDouble(UP_PRODUCT_AMOUNT.get(i).toString())>0){
                     
                    stat1=conn.prepareStatement("update  m4_bill_detail set PRODUCT_AMOUNT=?,TDATE=sysdate,USER_ID=?,PRODUCT_SL_NO=?,PCH=?,REMARKS=?,TAXABLE=?  where SL_NO=?");
                    stat1.setString(1, UP_PRODUCT_AMOUNT.get(i).toString());
                    stat1.setString(2, usrid);
                    stat1.setString(3, UP_PRODUCT_SL_NO.get(i).toString());
                    stat1.setString(4, PCH);
                    stat1.setString(5, UP_REMARKS.get(i).toString());
                    stat1.setString(6, UP_CHKINPUT.get(i).toString());
                    stat1.setString(7, PRODUCT_MAST_SL_NO.get(i).toString());
                    
                    flag=stat1.executeUpdate();
                     if(flag>0)
                    {
                      //  stat1=conn.prepareStatement("delete from  m4_bill_add_detail where BILL_DT_SL_NO=?");
                       // stat1.setString(1,PRODUCT_MAST_SL_NO.get(i).toString());
                       // stat1.executeUpdate();
                        /*
                        if(INVSLTXTCTN.get(i).toString()!=null && INVSLTXTCTN.get(i).toString().length()>0)
                        {
                            for(int m=0; m<Integer.parseInt(INVSLTXTCTN.get(i).toString()); m++)
                            {
                            
                                    if(INV_SAVENEW.get(ctn).toString()!=null && INV_SAVENEW.get(ctn).toString().length()>0 &&  INV_SAVENEW.get(ctn).toString().equals("Yes") )
                                    {
                                     String slno=null;
                                    stat1=conn.prepareStatement("select m4_bill_add_detail_Sq.nextval from dual");
                                    result1=stat1.executeQuery();
                                    if(result1.next())
                                    {
                                      slno=result1.getString(1);
                                    }
                                     stat1=conn.prepareStatement("insert into m4_bill_add_detail(SL_NO,BILL_SL_NO,BILL_DT_SL_NO,INV_NO,INV_WEIGHT,TDATE,USER_ID) values(?,?,?,?,?,sysdate,?)");
                                     stat1.setString(1,slno); 
                                     stat1.setString(2,MAST_SL_NO);
                                     stat1.setString(3,PRODUCT_MAST_SL_NO.get(i).toString());
                                     stat1.setString(4,INV_NONEW.get(ctn).toString());
                                     stat1.setString(5,INV_WEIGHTNEW.get(ctn).toString());
                                     stat1.setString(6,usrid);
                                     flag=stat1.executeUpdate();


                                    }
                                    ++ctn;
                                

                            }
                     
                            } */
                    
                    }
                         
                     }else{
                     
                    /*  if(INVSLTXTCTN.get(i).toString()!=null && INVSLTXTCTN.get(i).toString().length()>0)
                        {
                            for(int m=0; m<Integer.parseInt(INVSLTXTCTN.get(i).toString()); m++)
                            {
                            ++ctn;
                            }} */
                     
                     }
                     
                     if(UP_PRODUCT_SL_NO.get(i).toString()!=null && UP_PRODUCT_SL_NO.get(i).toString().length()>0 &&
                            (UP_PRODUCT_AMOUNT.get(i).toString()==null ||( UP_PRODUCT_AMOUNT.get(i).toString()!=null && UP_PRODUCT_AMOUNT.get(i).toString().length()==0) || ( UP_PRODUCT_AMOUNT.get(i).toString()!=null && UP_PRODUCT_AMOUNT.get(i).toString().length()>0 && Double.parseDouble(UP_PRODUCT_AMOUNT.get(i).toString())==0))){
                     
                    stat1=conn.prepareStatement("delete from   m4_bill_detail  where SL_NO=?");
                    stat1.setString(1, PRODUCT_MAST_SL_NO.get(i).toString());
                    flag=stat1.executeUpdate();
                    if(flag>0){
                   // stat1=conn.prepareStatement("delete from   m4_bill_add_detail  where BILL_DT_SL_NO=?");
                    //stat1.setString(1, PRODUCT_MAST_SL_NO.get(i).toString());
                   // flag=stat1.executeUpdate();
                    }
                         
                     }
                     
                     }
                     
                  
                   
                     
                }
                int ctr=0;
                    if(PRODUCT_SL_NO!=null && PRODUCT_SL_NO.size()>0)
                    {
                    for(int i=0; i<PRODUCT_SL_NO.size(); i++){
                        
                    if(PRODUCT_SL_NO.get(i).toString()!=null && PRODUCT_SL_NO.get(i).toString().length()>0 &&
                            PRODUCT_AMOUNT.get(i).toString()!=null && PRODUCT_AMOUNT.get(i).toString().length()>0 && Double.parseDouble( PRODUCT_AMOUNT.get(i).toString())>0){
                        stat1=conn.prepareStatement("select m4_bill_detail_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                          SL_NO=result1.getString(1);
                        }
                    stat1=conn.prepareStatement("insert into m4_bill_detail(SL_NO,BILL_SL_NO,CC_CODE,TYPE_SL_NO,SUB_TYPE_SL_NO,PRODUCT_SL_NO,PRODUCT_AMOUNT,TDATE,USER_ID,PCH,BILL_DATE1,BILL_DATE2,REMARKS,TAXABLE) values(?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?)");
                    stat1.setString(1, SL_NO);
                    stat1.setString(2, MAST_SL_NO);
                    stat1.setString(3, CC_CODE);
                    stat1.setString(4, TYPE_SL_NO);
                    stat1.setString(5, SUB_TYPE_SL_NO);
                    stat1.setString(6, PRODUCT_SL_NO.get(i).toString());
                    stat1.setString(7, PRODUCT_AMOUNT.get(i).toString());
                    stat1.setString(8, usrid);
                    stat1.setString(9, PCH);
                    stat1.setString(10, BILL_DATE1);
                    stat1.setString(11, BILL_DATE2);
                    stat1.setString(12, REMARKS.get(i).toString());
                    stat1.setString(13, CHKINPUT.get(i).toString());
                    
                    flag=stat1.executeUpdate();
                    
                    if(flag>0)
                    {
                        /*
                        if(INVSLTXTCTN.get(i).toString()!=null && INVSLTXTCTN.get(i).toString().length()>0)
                        {
                            for(int m=0; m<Integer.parseInt(INVSLTXTCTN.get(i).toString()); m++)
                            {
                             
                                    if(INV_SAVENEW.get(ctr).toString()!=null && INV_SAVENEW.get(ctr).toString().length()>0 &&  INV_SAVENEW.get(ctr).toString().equals("Yes") )
                                    {
                                     String slno=null;
                                    stat1=conn.prepareStatement("select m4_bill_add_detail_Sq.nextval from dual");
                                    result1=stat1.executeQuery();
                                    if(result1.next())
                                    {
                                      slno=result1.getString(1);
                                    }
                                     stat1=conn.prepareStatement("insert into m4_bill_add_detail(SL_NO,BILL_SL_NO,BILL_DT_SL_NO,INV_NO,INV_WEIGHT,TDATE,USER_ID) values(?,?,?,?,?,sysdate,?)");
                                     stat1.setString(1,slno); 
                                     stat1.setString(2,MAST_SL_NO);
                                     stat1.setString(3,SL_NO);
                                     stat1.setString(4,INV_NONEW.get(ctr).toString());
                                     stat1.setString(5,INV_WEIGHTNEW.get(ctr).toString());
                                     stat1.setString(6,usrid);
                                     flag=stat1.executeUpdate();


                                    }
                                    ++ctr;
                                

                            }
                    
                            }
                    */
                    }
                    
                    }else{
                        /*
                        if(INVSLTXTCTN.get(i).toString()!=null && INVSLTXTCTN.get(i).toString().length()>0)
                        {
                            for(int m=0; m<Integer.parseInt(INVSLTXTCTN.get(i).toString()); m++)
                            {
                            ++ctr;
                            }} 
                        */
                    }
                    
                    }
                    }
                
                }

               
                
                
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M3BillEntryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M3BillEntryAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }
 getdetail(MAST_SL_NO);
 costelement=getcostlement();
 
        if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
            return "savepage";
        } else {

            addActionMessage("Records Not save(s) !!");
            return "savepage";
        }
    
     
   } 
   
   
   public void getdetail(String slno)
   {
    

            Connection conn = null;
            Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                connbi = new ConnectionShahiHris().getConnection();
              
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                //delete by sl no
                 if(delflg!=null && delflg.equals("Yes"))
                {
                if(chkdel!=null && chkdel.size()>0)
                {
                    for(int i=0; i<chkdel.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("delete from m4_bill_detail where SL_NO=?");
                        stat1.setString(1, chkdel.get(i).toString());
                        int a = stat1.executeUpdate();
                        if(a>0)
                        {
                        stat1 = conn.prepareStatement("delete from m4_bill_add_detail where BILL_DT_SL_NO=?");
                        stat1.setString(1, chkdel.get(i).toString());
                        a = stat1.executeUpdate();
                        }

                      
                }
                }}
                
                
                stat1=conn.prepareStatement("select SL_NO,DEPT_SL_NO,BILL_NO,to_char(BILL_DATE,'dd/mm/yyyy')BILL_DATE ,SUPPLIER_CODE,BILL_AMOUNT,GROSS_AMOUNT,BILL_WHLO,BILL_YEAR,to_char(FORWARD_DATE,'dd/mm/yyyy') FORWARD_DATE ,REMARKS,SASUNM,SAADR1,SAADR2,SAADR3,SAADR4,SATOWN,SAECAR,SAPONO,SACSCD from m4_bill_master where SL_NO  =?");
                stat1.setString(1, slno);
                result=stat1.executeQuery();
                if(result.next())
                {
                MAST_SL_NO=result.getString("SL_NO");
                DEPT_SL_NO=result.getString("DEPT_SL_NO");
                BILL_NO=result.getString("BILL_NO");
                BILL_DATE=result.getString("BILL_DATE");
                SUPPLIER_CODE=result.getString("SUPPLIER_CODE");
                BILL_AMOUNT=result.getString("BILL_AMOUNT");
                GROSS_AMOUNT=result.getString("GROSS_AMOUNT");
                BILL_WHLO=result.getString("BILL_WHLO");
                BILL_YEAR=result.getString("BILL_YEAR");
                FORWARD_DATE=result.getString("FORWARD_DATE");
                MAST_REMARKS=result.getString("REMARKS");
                SUNM=result.getString("SASUNM");
                ADR1=result.getString("SAADR1");
                ADR2=result.getString("SAADR2");
                ADR3=result.getString("SAADR3");
                ADR4=result.getString("SAADR4");
                TOWN=result.getString("SATOWN");
                ECAR=result.getString("SAECAR");
                PONO=result.getString("SAPONO");
                CSCD=result.getString("SACSCD");
                
                }
                
                stat1=conn.prepareStatement("select DEBIT_AMOUNT,DEBIT_REASON from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                {
                 //DEBIT_AMOUNT=result.getString(1);
                 //DEBIT_REASON=result.getString(2);
                 ctndebit=1;
                
                }
                
                
                stat1=conn.prepareStatement("select count(*) from m4_bill_add_master where BILL_SL_NO=?");
                stat1.setString(1,MAST_SL_NO);
                result=stat1.executeQuery();
                 if(result.next())
                    {
                        ctninv=result.getInt(1);
                    }
                
                
                
                    stat1=conn.prepareStatement("select SL_NO,DEPT_DESC from M4_BILL_DEPT_MASTER  where SL_NO=?");
                    stat1.setString(1, DEPT_SL_NO);
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                    
                        DEPT_DESC=result.getString(2);
                    }
                    
                    stat1=connbi.prepareStatement("select IDSUNM from prodbi.cidmas  where IDCONO=111 and IDSUNO=?");
                    stat1.setString(1, SUPPLIER_CODE);
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                    
                        SUPPLIER_DESC=result.getString(1);
                    }
                
                stat1=conn.prepareStatement("select SL_NO,BILL_SL_NO,CC_CODE,TYPE_SL_NO,SUB_TYPE_SL_NO,PRODUCT_SL_NO,PRODUCT_AMOUNT,TDATE,USER_ID,pch,BILL_DATE1,BILL_DATE2,REMARKS,TAXABLE from m4_bill_detail where BILL_SL_NO=? order by SL_NO");
                stat1.setString(1, slno);
                result=stat1.executeQuery();
                while(result.next())
                {
                    BILL_DATE1=result.getString("BILL_DATE1");
                    BILL_DATE2=result.getString("BILL_DATE2");
                 
                    stat1=conn.prepareStatement("select TYPE_DESC,FLAG,nvl(TYPE_CODE,' ') TYPE_CODE from M4_BILL_type_MASTER where SL_NO=?");
                    stat1.setString(1, result.getString("TYPE_SL_NO"));
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                    TYPE_DESC=result1.getString(1)+"-"+result1.getString("TYPE_CODE");
                    }
                    
                    stat1=conn.prepareStatement("select SUB_TYPE_DESC,nvl(SUB_TYPE_CODE,' ') SUB_TYPE_CODE from M4_BILL_SUB_TYPE_MASTER where SL_NO=?");
                    stat1.setString(1, result.getString("SUB_TYPE_SL_NO"));
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                    SUB_TYPE_DESC=result1.getString(1)+"-"+result1.getString("SUB_TYPE_CODE");
                    }
                    
                    stat1=conn.prepareStatement("select PRODUCT_DESC,nvl(PRODUCT_CODE,' ') PRODUCT_CODE from M4_BILL_PRODUCT_MASTER where SL_NO  =?");
                    stat1.setString(1, result.getString("PRODUCT_SL_NO"));
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                     PRODUCT_DESC=result1.getString(1)+"-"+result1.getString("PRODUCT_CODE");;
                    }
                    
                  /*  stat1=connbi.prepareStatement("select EAAITM,EATX40 from prodbi.fchacc where EACONO=111 and EAAITP=3 and eaat12=0 and EAAITM=?");
                    stat1.setString(1, result.getString("CC_CODE"));
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                        CC_CODE=result1.getString(1);
                        CC_DESC=result1.getString(2);
                    
                    }*/
                    stat1=connbi.prepareStatement("select SL_NO,EAAITM,EATX40 from seplweb.m4_cc_master@ibm.world@ibm a,prodbi.fchacc b where EACONO=111 and a.cc_code=b.EAAITM and EAAITP=3 and eaat12=0 and SL_NO=? order by 2");
                    stat1.setString(1, result.getString("CC_CODE"));
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                        CC_CODE=result1.getString(1);
                        CC_DESC=result1.getString(2)+"-"+result1.getString(3);
                    
                    }
                    
                    
                    
                    if(result1!=null)
                    {
                    result1.close();
                    }
                    if(stat1!=null)
                    {
                    stat1.close();
                    }
                    String TEMPTAXABLE="No";
                    if(result.getString("TAXABLE")!=null && result.getString("TAXABLE").equals("Y"))
                    {
                    TEMPTAXABLE="Yes";
                    }
                    
                    if(!mastlisttemp.contains(result.getString("CC_CODE")+result.getString("TYPE_SL_NO")+result.getString("SUB_TYPE_SL_NO")+result.getString("pch")))
                    {
                    mastlisttemp.add(result.getString("CC_CODE")+result.getString("TYPE_SL_NO")+result.getString("SUB_TYPE_SL_NO")+result.getString("pch"));
                    
                  //  billdetailgrp.add(new M3BillEntryBean(result.getString("CC_CODE")+"-"+CC_DESC,result.getString("TYPE_SL_NO")+"-"+TYPE_DESC,result.getString("SUB_TYPE_SL_NO")+"-"+SUB_TYPE_DESC,result.getString("CC_CODE")+result.getString("TYPE_SL_NO")+result.getString("SUB_TYPE_SL_NO")));
                    billdetailgrp.add(new M3BillEntryBean(result.getString("SL_NO"),result.getString("BILL_SL_NO"), result.getString("CC_CODE"),result.getString("TYPE_SL_NO"),result.getString("SUB_TYPE_SL_NO"),result.getString("PRODUCT_SL_NO"), result.getDouble("PRODUCT_AMOUNT"),
                         CC_DESC,TYPE_DESC,SUB_TYPE_DESC,PRODUCT_DESC,result.getString("CC_CODE")+result.getString("TYPE_SL_NO")+result.getString("SUB_TYPE_SL_NO")+result.getString("pch"),result.getString("pch"),result.getString("REMARKS"),result.getString("TAXABLE"),TEMPTAXABLE));
               
                    
                    }
                    //TESTING WITH PRODUCTION
                    if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("32") &&  ((result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("56")) || (result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("1638"))))
                    {
                    SHOWFLAG="Yes";
                    
                    } //TESTING 
                    
                    //COURIER WITH ADMIN
                    if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("30") &&  ((result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("38"))|| (result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("60"))))
                    {
                    SHOWFLAG="Yes";
                    
                    }
                    //ADMIN 
                    
                    //Export 26 
                     if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("26"))
                    {
                       
                        if(result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("67"))
                        {
                            
                         if(SHOWFLAG==null)
                         {
                         SHOWFLAG="SB";
                         DISPLAY_NAME="S/Bill";
                         
                         }
                        }
                        
                        if(result.getString("TYPE_SL_NO")!=null && (result.getString("TYPE_SL_NO").equals("68") || result.getString("TYPE_SL_NO").equals("77") || result.getString("TYPE_SL_NO").equals("32") || result.getString("TYPE_SL_NO").equals("33")))
                        {
                          
                         if(SHOWFLAG==null)
                         {
                         SHOWFLAG="INV";
                         }
                        }
                        
                    if(result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("69"))
                        {
                         if(SHOWFLAG==null)
                         {
                          SHOWFLAG="LCERT";
                          DISPLAY_NAME="Landing Certificate";
                         }
                        }
                    
                     if(result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("76"))
                        {
                            
                         if(SHOWFLAG==null)
                         {
                         SHOWFLAG="BOS";
                         DISPLAY_NAME="BOS";
                       
                         }
                        }
                     
                      if(result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("428"))
                        {
                            
                         if(SHOWFLAG==null)
                         {
                         SHOWFLAG="FABTRI";
                         DISPLAY_NAME="Fabric & Trims Inv.";
                       
                         }
                        }
                     
                    }
                   
                    //close Export
                    
                     
                   //IMPORTS 27 
                     if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("27"))
                    {
                        if(result.getString("TYPE_SL_NO")!=null && (result.getString("TYPE_SL_NO").equals("78") || result.getString("TYPE_SL_NO").equals("75")))
                        {
                            //BILL OF ENTRY WISE 
                         if(SHOWFLAG==null)
                         {
                         SHOWFLAG="BOE";
                         DISPLAY_NAME="BOE";
                         
                         }
                        }
                        
                       
                    if(result.getString("TYPE_SL_NO")!=null && (result.getString("TYPE_SL_NO").equals("79") || result.getString("TYPE_SL_NO").equals("34") || result.getString("TYPE_SL_NO").equals("35")))
                        {
                         if(SHOWFLAG==null)
                         {
                          SHOWFLAG="AWBNO";
                          DISPLAY_NAME="HAWB/MAWB/BL";
                         }
                        }
                    
                     if(result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("73"))
                        {
                            
                         if(SHOWFLAG==null)
                         {
                         SHOWFLAG="EXINV";
                         DISPLAY_NAME="Invoice No";
                       
                         }
                        }
                     
                     if(result.getString("TYPE_SL_NO")!=null && (result.getString("TYPE_SL_NO").equals("74") || result.getString("TYPE_SL_NO").equals("1813")))
                        {
                            
                         if(SHOWFLAG==null)
                         {
                         SHOWFLAG="EXLIC";
                         DISPLAY_NAME="Licence No";
                       
                         }
                        }
                    }
                     
                     if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("29") && result.getString("TYPE_SL_NO")!=null 
                             && (result.getString("TYPE_SL_NO").equals("1708") || result.getString("TYPE_SL_NO").equals("1819"))){
                    	 LOGISSTATUS="Yes";
                     }
                   
                    //close IMPORTS  
                     
                     //FINANCE
                     if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("89"))
                     {
                     if(result.getString("TYPE_SL_NO")!=null && result.getString("TYPE_SL_NO").equals("1824"))
                     {
                       if(SHOWFLAG==null)
                         {
                         SHOWFLAG="CHK";
                         DISPLAY_NAME="Cheque Details";
                         
                         }
                     }
                     }
                     
                    
                 billdetail.add(new M3BillEntryBean(result.getString("SL_NO"),result.getString("BILL_SL_NO"), result.getString("CC_CODE"),result.getString("TYPE_SL_NO"),result.getString("SUB_TYPE_SL_NO"),result.getString("PRODUCT_SL_NO"), result.getDouble("PRODUCT_AMOUNT"),
                         CC_DESC,TYPE_DESC,SUB_TYPE_DESC,PRODUCT_DESC,result.getString("CC_CODE")+result.getString("TYPE_SL_NO")+result.getString("SUB_TYPE_SL_NO")+result.getString("pch"),result.getString("pch"),result.getString("REMARKS"),result.getString("TAXABLE"),TEMPTAXABLE));
               
                
                }
                
                 stat1=conn.prepareStatement("select a.SL_NO,a.COST_ELEMENT,b.ELEMENT_DESC,a.AMT,a.FORM_TYPE,a.FORM_WHLO,a.BILL_SL_NO from M4_BILL_AMT_DETAIL a,M4_COST_ELEMENT_MASTER b where a.COST_ELEMENT=b.SL_NO and  a.BILL_SL_NO=? and b.DEPT_SL_NO in (select DEPT_CODE from  M4_BILL_DEPT_MASTER where SL_NO=? ) and b.GROSS_FLAG='Y' order by a.SL_NO");
                 stat1.setString(1, slno);
                 stat1.setString(2, DEPT_SL_NO);
                 
                result1=stat1.executeQuery();
                while(result1.next())
                {
                    String desc=null;
                    if(result1.getString("FORM_TYPE")!=null)
                    {
                    desc=result1.getString("FORM_WHLO")+"-"+result1.getString("FORM_TYPE");
                    }
                savecostelement.add(new M3BillEntryBean(result1.getString("SL_NO"), result1.getString("BILL_SL_NO"),result1.getString("ELEMENT_DESC")+"-"+result1.getString("COST_ELEMENT"), result1.getString("AMT"),desc ,result1.getDouble("AMT")));
                }
                
                stat1=conn.prepareStatement("select  a.SL_NO,a.COST_ELEMENT,b.ELEMENT_DESC,a.AMT,a.FORM_TYPE,a.FORM_WHLO,a.BILL_SL_NO from M4_BILL_AMT_DETAIL a,M4_COST_ELEMENT_MASTER b where a.COST_ELEMENT=b.SL_NO and  a.BILL_SL_NO=?  and b.DEPT_SL_NO in (select DEPT_CODE from  M4_BILL_DEPT_MASTER where SL_NO=? )  and b.GROSS_FLAG is null order by a.SL_NO");
                stat1.setString(1, slno);
                 stat1.setString(2, DEPT_SL_NO);
                result1=stat1.executeQuery();
                while(result1.next())
                {
                     String desc=null;
                    if(result1.getString("FORM_TYPE")!=null)
                    {
                    desc=result1.getString("FORM_WHLO")+"-"+result1.getString("FORM_TYPE");
                    }
                savecostelement.add(new M3BillEntryBean(result1.getString("SL_NO"), result1.getString("BILL_SL_NO"),result1.getString("ELEMENT_DESC")+"-"+result1.getString("COST_ELEMENT"), result1.getString("AMT"), desc,result1.getDouble("AMT")));
                }
                 
                
                
                pchlist=new ShahiInformationList().pchListWithBill();
                }catch (Exception e) {
                System.out.println(e.toString());
            }  finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
   
   
   }
   
 public String newmst() throws Exception {
if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
{
     getdetail(MAST_SL_NO);
 }else{
    
mastlist=getdeptlist(SEARCH_CODE);

}
 costelement=getcostlement();
 pchlist=new ShahiInformationList().pchListWithBill();

  return "newpage";
  }
 
  public String newmstbill() throws Exception {
  mastlist=getdeptlist(SEARCH_CODE);
  costelement=getcostlement();
  pchlist=new ShahiInformationList().pchListWithBill();
  if(DEPT_SL_NO!=null && DEPT_SL_NO.equals("35"))
  {
  buyerlist=new ShahiInformationList().buyerList();
  }
 
 BILL_NO="";        
 BILL_DATE="";      
 SUPPLIER_CODE="";
 SUPPLIER_DESC="";
 BILL_AMOUNT="" ; 
 BILL_DATE1="";
 BILL_DATE2="";
 GROSS_AMOUNT="";

  return "newpage";
  }
 
 private List getcostlement() throws Exception {

        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       List listmst=new ArrayList();

        try {

            Connection conn = null;
            Connection connbi = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
                connbi = new connectiondb2().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                
                    stat1=conn.prepareStatement("select SL_NO,COST_ELEMENT,GL_CODE,FORM_FLAG,ELEMENT_DESC from "
                            + "M4_COST_ELEMENT_MASTER where FLAG='Y' and DEPT_SL_NO in (select DEPT_CODE from  M4_BILL_DEPT_MASTER where SL_NO=? )"
                            + " and GROSS_FLAG='Y' order by GL_CODE");
                    stat1.setString(1, DEPT_SL_NO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                      grosselemlist.add(new M3BILLBean(result.getString("SL_NO"), result.getString("GL_CODE"), result.getString("ELEMENT_DESC")+"-"+result.getString("COST_ELEMENT"), result.getString("FORM_FLAG"), "",""));
                    }
                //DEPT_SL_NO in(select SL_NO from M4_BILL_DEPT_MASTER  where  DEPT_CODE in (select DEPT_SL_NO from  m4_bill_user_master where emp_code=? and flag='Y'))
                    stat1=conn.prepareStatement("select SL_NO,COST_ELEMENT,GL_CODE,FORM_FLAG,ELEMENT_DESC from M4_COST_ELEMENT_MASTER"
                            + " where FLAG='Y' and DEPT_SL_NO in (select DEPT_CODE from  M4_BILL_DEPT_MASTER where SL_NO=? ) "
                            + "and GROSS_FLAG is null order by GL_CODE");
                    stat1.setString(1, DEPT_SL_NO);
                    //stat1=conn.prepareStatement("select SL_NO,COST_ELEMENT,GL_CODE,FORM_FLAG,ELEMENT_DESC from M4_COST_ELEMENT_MASTER where FLAG='Y' and GROSS_FLAG is null order by GL_CODE");
                    result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                                            
                      listmst.add(new M3BILLBean(result.getString("SL_NO"), result.getString("GL_CODE"), result.getString("ELEMENT_DESC")+"-"+result.getString("COST_ELEMENT"), result.getString("FORM_FLAG"), "",""));
                    }
                    
                    /*
                    stat1=connbi.prepareStatement("select z4geoc,z4desc,substr(z4geoc,4,3) whlo from cinfdbprd.ZGEOJU where (substr(z4geoc,4,3))=? order by 2") ;
                    stat1.setString(1, LOCATION_CODE);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                       costelementtype.add(new M3BILLBean(result.getString(1)+"-"+result.getString(2), result.getString("whlo")+"-"+result.getString("z4geoc")));
                    }
                   */

            } catch (Exception e) {

                System.out.print("1 file name :getcostlement M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if (connbi != null) {
                        connbi.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :getcostlement M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      return listmst;
  }
 
 
 public String newcc() throws Exception {

 
  return "newccpage";
  }
 
 public String newtype() throws Exception {
 if(DEPT_SL_NO!=null && DEPT_SL_NO.length()>0)
  {
 typelist=gettypemst(DEPT_SL_NO);
  }
  
  return "newtypepage";
  }
 
 public String subtype() throws Exception {
 if(TYPE_SL_NO!=null && TYPE_SL_NO.length()>0)
  {
  subtypelist=getsubtypemst(TYPE_SL_NO);
  }
  
  return "subtypepage";
  }
 
 
public String prodtype() throws Exception {
 if(SUB_TYPE_SL_NO!=null && SUB_TYPE_SL_NO.length()>0)
  {
 productlist=getproductmst(SUB_TYPE_SL_NO);
  }
  
 if(SAVETYPE!=null && SAVETYPE.equals("edit"))
 {
 getproductmstlist(SUB_TYPE_SL_NO);
 }
  return "prodtypepage";
  }
 
 public String cctype() throws Exception {
 
  cclist=getmasterlist();
  
 return "ccpage";
  }
 
 
 public String searchpage() throws Exception {
     
   
     
  if(SEARCH_TYPE!=null && SEARCH_TYPE.equals("10"))
  {
     mastlist=getdeptlist(SEARCH_CODE);
  }else if(SEARCH_TYPE!=null && SEARCH_TYPE.equals("3")){
  
   //mastlist=getmasterlist(SEARCH_CODE,SEARCH_TYPE);
  }else{
    suppamtlist=getsuppamtlist(SEARCH_CODE);
  }
 
  return "searchpage";
  }
 
 
 public String getcostentdt() throws Exception {
 getfrmcost();
 costelement=getcostlement();
 return "costelemt";
 }
 
 public String saveele() throws Exception {
    
     
      int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            
            try{
             if(BREAK_AMOUNT!=null && BREAK_AMOUNT.size()>0)  
                  {
                    
                      stat1=conn.prepareStatement("delete from  M4_BILL_AMT_DETAIL where BILL_SL_NO=?");
                      stat1.setString(1, MAST_SL_NO);
                      stat1.executeUpdate();
                      
                      
                      
                   for(int i=0; i<BREAK_AMOUNT.size(); i++)
                   {
                    if(BREAK_CODE!=null && BREAK_CODE.get(i).toString()!=null && BREAK_CODE.get(i).toString().length()>0  &&   BREAK_AMOUNT!=null && BREAK_AMOUNT.get(i).toString()!=null && BREAK_AMOUNT.get(i).toString().length()>0 
                            && Double.parseDouble(BREAK_AMOUNT.get(i).toString())>0)
                    {
                        stat1=conn.prepareStatement("select M4_BILL_AMT_DETAIL_sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                       
                        if(i==0)
                        {
                        stat1=conn.prepareStatement("update m4_bill_master set GROSS_AMOUNT=?,REVERSE_SRVTAX=? ,REVERSE_SRVTAX_RATE=?,SRVTAX_GL_CODE=?,REVERSE_SRVTAX_CODE=?,NON_SRVTAX_AMOUNT=?,HSN_CODE=?,NON_GST_AMOUNT=? where SL_NO=?");
                        stat1.setString(1, BREAK_AMOUNT.get(0).toString());
                        stat1.setString(2, REVERSE_SRVTAX);
                        stat1.setString(3, REVERSE_SRVTAX_RATE);
                        stat1.setString(4, SRVTAX_GL_CODE);
                        stat1.setString(5, REVERSE_SRVTAX_CODE);
                        stat1.setString(6, NON_SRVTAX_AMOUNT);
                        stat1.setString(7, HSN_CODE);
                        stat1.setString(8, BREAK_AMOUNT_NON_GST);
                        stat1.setString(9, MAST_SL_NO);
                        stat1.executeUpdate();
                        
                        
                        }
                        String formtypetemp=null;
                         String formwhltemp=null;
                       /* if(FORM_TYPE.get(i).toString()!=null && FORM_TYPE.get(i).toString().length()>0)
                        {
                        formwhltemp=FORM_TYPE.get(i).toString().substring(0,FORM_TYPE.get(i).toString().indexOf("-"));
                        formtypetemp=FORM_TYPE.get(i).toString().substring(FORM_TYPE.get(i).toString().indexOf("-")+1,FORM_TYPE.get(i).toString().length());
                        
                        }*/
                           String GSTPER=null;
                         if(GST_PER.get(i).toString()!=null && GST_PER.get(i).toString().length()>0)
                         {
                         GSTPER=GST_PER.get(i).toString();
                         }
                         
                          String TAXCODE=null;
                         if(TAX_CODE.get(i).toString()!=null && TAX_CODE.get(i).toString().length()>0)
                         {
                         TAXCODE=TAX_CODE.get(i).toString();
                         }
                      stat1=conn.prepareStatement("insert into M4_BILL_AMT_DETAIL(SL_NO,BILL_SL_NO,COST_ELEMENT,AMT,FORM_TYPE,TDATE,USER_ID,FORM_WHLO,GST_PER,TAX_CODE) values(?,?,?,?,?,sysdate,?,?,?,?)");
                      stat1.setString(1, brkslno);
                      stat1.setString(2, MAST_SL_NO);
                      stat1.setString(3, BREAK_CODE.get(i).toString());
                      stat1.setString(4, BREAK_AMOUNT.get(i).toString());
                      stat1.setString(5, formtypetemp);
                      stat1.setString(6, usrid);
                      stat1.setString(7, formwhltemp);
                      stat1.setString(8, GSTPER);
                      stat1.setString(9, TAXCODE);
                      stat1.executeUpdate();
                    }
                   
                   }
                  
                  }
           
                 } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M3BillEntryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M3BillEntryAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
     
 getfrmcost();
 costelement=getcostlement();
   if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
          

            addActionMessage("Records Not save(s) !!");
           
        }
 return "costelemt";
 }
 
  public void getfrmcost()
   {
    

            Connection conn = null;
            Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                
              
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                          
                
                 stat1=conn.prepareStatement("select BILL_AMOUNT,REVERSE_SRVTAX,REVERSE_SRVTAX_RATE,SRVTAX_GL_CODE,REVERSE_SRVTAX_CODE,NON_SRVTAX_AMOUNT,HSN_CODE,NON_GST_AMOUNT,SUPPLIER_CODE from m4_bill_master where sl_no=?");
                 stat1.setString(1, MAST_SL_NO);
                 result1=stat1.executeQuery();
                if(result1.next())
                {
                tempbillamount=result1.getString(1);
                REVERSE_SRVTAX=result1.getString(2);
                REVERSE_SRVTAX_RATE=result1.getString(3);
                SRVTAX_GL_CODE=result1.getString(4);
                REVERSE_SRVTAX_CODE=result1.getString("REVERSE_SRVTAX_CODE");
                NON_SRVTAX_AMOUNT=result1.getString("NON_SRVTAX_AMOUNT");
                HSN_CODE=result1.getString("HSN_CODE");
                BREAK_AMOUNT_NON_GST=result1.getString("NON_GST_AMOUNT");
                SUPPLIER_CODE=result1.getString("SUPPLIER_CODE");
                
                }
                 stat1=conn.prepareStatement("select FORWARD_DATE from m4_bill_master where SL_NO=? and FORWARD_DATE is not null");
                 stat1.setString(1, MAST_SL_NO);
                 result1=stat1.executeQuery();
                if(result1.next())
                {
                FORWARD_DATE=result1.getString(1);
                }
                stat1=conn.prepareStatement("select ACCOUNT_DATE from m4_bill_master where SL_NO=? and ACCOUNT_DATE is not null");
                 stat1.setString(1, MAST_SL_NO);
                 result1=stat1.executeQuery();
                if(result1.next())
                {
                ACCOUNTDATE=result1.getString(1);
                }
                
                
                 stat1=conn.prepareStatement("select nvl(sum(PRODUCT_AMOUNT),0) amt from m4_bill_detail where BILL_SL_NO=?");
                 stat1.setString(1, MAST_SL_NO);
                 result1=stat1.executeQuery();
                if(result1.next())
                {
                tempentgrossamt=result1.getString(1);
                }
                
                 stat1=conn.prepareStatement("select a.SL_NO,a.COST_ELEMENT,a.AMT,a.FORM_TYPE,a.FORM_WHLO,a.BILL_SL_NO from M4_BILL_AMT_DETAIL a,M4_COST_ELEMENT_MASTER b where a.COST_ELEMENT=b.SL_NO and  a.BILL_SL_NO=? and b.DEPT_SL_NO in (select DEPT_CODE from  M4_BILL_DEPT_MASTER where SL_NO=? ) and b.GROSS_FLAG='Y' order by a.SL_NO");
                stat1.setString(1, MAST_SL_NO);
                 stat1.setString(2, DEPT_SL_NO);
                
                result1=stat1.executeQuery();
                while(result1.next())
                {
                      String desc=null;
                    if(result1.getString("FORM_TYPE")!=null)
                    {
                    desc=result1.getString("FORM_WHLO")+"-"+result1.getString("FORM_TYPE");
                    }
                savecostelement.add(new M3BillEntryBean(result1.getString("SL_NO"), result1.getString("BILL_SL_NO"),result1.getString("COST_ELEMENT"), result1.getString("AMT"), desc,result1.getDouble("AMT")));
                }
                
                stat1=conn.prepareStatement("select   a.SL_NO,a.COST_ELEMENT,a.AMT,a.FORM_TYPE,a.FORM_WHLO,a.BILL_SL_NO from M4_BILL_AMT_DETAIL a,M4_COST_ELEMENT_MASTER b where a.COST_ELEMENT=b.SL_NO and  a.BILL_SL_NO=? and b.DEPT_SL_NO in (select DEPT_CODE from  M4_BILL_DEPT_MASTER where SL_NO=? ) and b.GROSS_FLAG is null order by a.SL_NO");
                stat1.setString(1, MAST_SL_NO);
                stat1.setString(2, DEPT_SL_NO);
                result1=stat1.executeQuery();
                while(result1.next())
                {
                      String desc=null;
                    if(result1.getString("FORM_TYPE")!=null)
                    {
                    desc=result1.getString("FORM_WHLO")+"-"+result1.getString("FORM_TYPE");
                    }
                savecostelement.add(new M3BillEntryBean(result1.getString("SL_NO"), result1.getString("BILL_SL_NO"),result1.getString("COST_ELEMENT"), result1.getString("AMT"), desc,result1.getDouble("AMT")));
                }
                
               stat1=conn.prepareStatement("select sum(AMT) AMT from M4_BILL_AMT_DETAIL where BILL_SL_NO=?");
               stat1.setString(1, MAST_SL_NO);
               result1=stat1.executeQuery();
               if(result1.next())
                {
                setbrktotal=result1.getDouble("AMT");
                }
               
                
                
                 
                }catch (Exception e) {
                System.out.println(e.toString());
            }  finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
   
   
   }
 
 private List getdeptlist(String desc)
 {
      Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
   List mastlist=new ArrayList();
  

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
            
                if(desc!=null && desc.length()>0)
                {
                   
                    desc="and upper(DEPT_DESC) like '"+desc.toUpperCase()+"%"+"'";
                }
                if(desc==null)
                {
                desc=" ";
                }
                    stat1=conn.prepareStatement("select SL_NO,DEPT_DESC from M4_BILL_DEPT_MASTER  where FLAG='Y' "+desc+"  and DEPT_CODE in(select DEPT_SL_NO from  m4_bill_user_master where emp_code=? and flag='Y')  order by DEPT_DESC");
                    stat1.setString(1, usrid);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    }
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    addActionMessage("Record Not Found(s)");
                    }
               
            }catch (Exception e) {

               
                System.out.print("1 file name :getdeptlist M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                   
                    System.out.print("File Name :getdeptlist M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }
 
 private List getmasterlist()
 {
   List mastlist=new ArrayList();
  

            Connection conn = null;

            try {
                conn = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                    if(DEPT_SL_NO==null)
                    {
                    DEPT_SL_NO="0";
                    }
                String str="";
                if(DIS!=null && DIS.length()>0)
                {
                str=" and SL_NO='"+CC_CODE+"'";
                }  //and TYPE_SL_NO=?
                
                    stat1=conn.prepareStatement("select SL_NO,EAAITM,EATX40 from seplweb.m4_cc_master@ibm.world@ibm a,prodbi.fchacc b where EACONO=111 and a.cc_code=b.EAAITM and EAAITP=3 and DEPT_SL_NO=? and TYPE_SL_NO=?  and eaat12=0 "+str+"  order by 2");
                    stat1.setString(1, DEPT_SL_NO);
                    stat1.setString(2, TYPE_SL_NO);
                    
                   // System.out.println("select SL_NO,EAAITM,EATX40 from seplweb.m4_cc_master@ibm.world@ibm a,prodbi.fchacc b where EACONO=111 and a.cc_code=b.EAAITM and EAAITP=3 and DEPT_SL_NO='"+DEPT_SL_NO+"' and TYPE_SL_NO='"+TYPE_SL_NO+"'  and eaat12=0 "+str+"  order by 2");
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(2)+"-"+ result.getString(3).toUpperCase()));
                    }
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    //addActionMessage("Record Not Found(s)");
                    }
                
            }catch (Exception e) {

               
                System.out.print("1 file name : M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                   
                    System.out.print("File Name : M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }
 
 public   boolean Invoice_Validation(String str) 
{

    if(str.length() <17)
    {
         Pattern letter = Pattern.compile("^[0-9a-zA-Z/ -]+$");
         Matcher hasLetter = letter.matcher(str);
         return hasLetter.find() ;

    }
    else
    {
        return false;
    }
    
   // return true;

}
 /*
 public static void main(String arg[])
 {
     M3BillEntryAction as=new M3BillEntryAction();
     
     System.out.println(as.Invoice_Validation("1d23/323sss-"));
 
 }*/
 
 
 
private List getsuppamtlist(String desc)
 {
   List mastlist=new ArrayList();
   Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

            Connection conn = null;
             Connection conn1 = null;

            try {
                conn = new connectiondb2().getConnection();
                conn1 = new ConnectionSeplWeb().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            ResultSet result3 = null;
            ResultSet result4 = null;

            try {
               String sqlstr=" ";
                if(desc!=null && desc.length()>0)
                {
                    desc=desc.toUpperCase()+"%";
                    //sqlstr=" and (upper(IDSUNM) like '"+desc+"' or  invoicenumber like '"+desc+"')";
                    sqlstr=" and (upper(IDSUNM) like '"+desc+"' or  APSINO like '"+desc+"')";
               
               stat1=conn1.prepareStatement("select WAREHOUSE from m4_bill_user_master where EMP_CODE=? and flag='Y'"
                       + "and DEPT_SL_NO in(select DEPT_CODE from M4_BILL_DEPT_MASTER where SL_NO=?)");
               stat1.setString(1,usrid);
               stat1.setString(2,DEPT_SL_NO);
               result3=stat1.executeQuery();
               if(result3.next()){
                   
                  
                 // stat1=conn.prepareStatement("select mvxsuno APSUNO,invoicenumber APSINO,date1 APIVDT,total APCUAM,IDSUNM,warehouse,cyear from mvxcdtshah.shahi_invoice a,mvxcdtprod.cidmas b where IDCONO=111 and MVXSUNO=IDSUNO and status_flag='A'  "+sqlstr+" and APWHLO in ("+result3.getString(1)+")"+
                        //  " warehouse in ("+result3.getString(1)+")  order by IDSUNM");
                     String list[] = null;
                        String aa = null;
                        String dtatwh=result3.getString(1);
                        if (dtatwh != null && dtatwh.length() > 0) {
                            if (dtatwh.indexOf(",") != -1) {
                                list = dtatwh.split(",");
                                if (list != null && list.length > 0) {
                                    for (int i = 0; i < list.length; i++) {
                                        if (aa == null) {
                                            aa = "'" + list[i] + "'";
                                        } else {
                                            aa = aa + ",'" + list[i] + "'";
                                        }
                                    }
                                }
                            } else {
                                aa = "'" + dtatwh + "'";
                            }
                        }
                   //ranjeet
                   //apacfg=0 

                       // System.out.println("select APSUNO,APSINO, APIVDT, APCUAM,IDSUNM,APWHLO warehouse,apyea4 cyear from cinFDBPRD.supinv a,M3FDBPRD.cidmas b where APCONO=111 and APCONO=IDCONO and APSUNO=IDSUNO and apacfg=1  "+sqlstr+" "
                         //   +"  and APDIVI in ( select distinct MWDIVI  from M3FDBPRD.mitwhl  where MWCONO='111' and MWWHLO   in ("+aa+"))   order by IDSUNM");
                        // and MWWHLO   in ("+aa+")

//and apivdt > '20170630'
                    stat1=conn.prepareStatement("select APSUNO,APSINO, APIVDT, APCUAM,IDSUNM,APWHLO warehouse,apyea4 cyear from cinFDBPRD.supinv a,M3FDBPRD.cidmas b where APCONO=111 and APCONO=IDCONO and APSUNO=IDSUNO  and idstat='20' and apivdt > '20170630'  and apacfg=1  "+sqlstr+" "
                           +"  and APDIVI in (select distinct MWDIVI  from M3FDBPRD.mitwhl  where MWCONO='111')   order by IDSUNM");
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                     
                     stat1=conn1.prepareStatement("select BILL_NO from m4_bill_master where  BILL_NO=? and to_char(BILL_DATE,'yyyymmdd')=? and SUPPLIER_CODE=?");
                     stat1.setString(1, result.getString("APSINO").trim());
                     stat1.setString(2, result.getString("APIVDT").trim());
                     stat1.setString(3, result.getString("APSUNO").trim());
                     result1=stat1.executeQuery();
                     if(result1.next()==false){
                        
                         double rateamt=0;
                         String rvtax=null;
                         // stat1=conn1.prepareStatement("select REVERSE_SRVTAX,REVERSE_SRVTAX_RATE from shahiportal.supplier where IDSUNO=?");
                        //  stat1=conn1.prepareStatement("select REVERSE_SERVICE_TAX REVERSE_SRVTAX,REVERSE_TAX_PERCENT REVERSE_SRVTAX_RATE from seplvportal.vendors where code=?");
                          int subid=0;
                         stat1=conn1.prepareStatement("select ID from seplvportal.vendors where code=?");
                          stat1.setString(1, result.getString("APSUNO").trim());
                          result4=stat1.executeQuery();
                            if(result4.next()){
                                subid=result4.getInt(1);
                               
                            }
                            if(result4!=null)
                            {result4.close();}
                             if(stat1!=null)
                            {stat1.close();}
                             
                            
                         if(subid>0)
                         {
                          stat1=conn1.prepareStatement("select id from seplvportal.VENDOR_TAX_REGISTRATION where VENDOR_ID ='"+subid+"' and  GST_NUMBER is null and  vend_type='2'");
                         // stat1.setInt(1, subid);
                          result4=stat1.executeQuery();
                            if(result4.next()){
                                //rvtax=result4.getString(1);
                                //rateamt=result4.getDouble(2);
                                rvtax="1";
                            }
                            if(result4!=null)
                            {result4.close();}
                             if(stat1!=null)
                            {stat1.close();}
                         }
                         
                       
                         String APIVDT=result.getString("APIVDT").trim();
                          APIVDT= APIVDT.substring(6, 8) + "/" + APIVDT.substring(4, 6) + "/" + APIVDT.substring(0, 4);
                         if(Invoice_Validation(result.getString("APSINO").trim())) 
                         {
                          mastlist.add(new Supinv(result.getString("APSUNO").trim(),result.getString("APSINO").trim(), APIVDT.trim(),result.getDouble("APCUAM"), result.getString("IDSUNM"), result.getString("cyear").trim(), result.getString("warehouse").trim(),rvtax,rateamt,"Yes"));
                         }else{
                             if(rvtax==null)
                                    {
                                 mastlist.add(new Supinv(result.getString("APSUNO").trim(),result.getString("APSINO").trim(), APIVDT.trim(),result.getDouble("APCUAM"), result.getString("IDSUNM"), result.getString("cyear").trim(), result.getString("warehouse").trim(),rvtax,rateamt,"No"));
                                    }else{
                                mastlist.add(new Supinv(result.getString("APSUNO").trim(),result.getString("APSINO").trim(), APIVDT.trim(),result.getDouble("APCUAM"), result.getString("IDSUNM"), result.getString("cyear").trim(), result.getString("warehouse").trim(),rvtax,rateamt,"Yes"));
                                
                             }
                         }
                     }
                    if(result1!=null)
                    {
                        result1.close();
                    }
                    if(stat1!=null)
                    {
                        stat1.close();
                    }
                    result1=null;
                    stat1=null;
                    
                    }
               }
               
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    addActionMessage("Record Not Found(s)");
                    }
                    
                   }  
               
            }catch (Exception e) {

               
                System.out.print("1 file name :getsuppamtlist() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                      if (conn1 != null) {
                        conn1.close();
                    }
                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                   
                    System.out.print("File Name :getsuppamtlist() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }



private List gettypemst(String SLNO) throws Exception {
 Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       List listmst=new ArrayList();

        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                String str="";
                if(DIS!=null && DIS.length()>0)
                {
                str=" and SL_NO='"+TYPE_SL_NO+"'";
                }
                    //
                stat1=conn.prepareStatement("select BILL_TYPE from m4_bill_user_master where EMP_CODE=? and DEPT_SL_NO in(select DEPT_CODE from M4_BILL_DEPT_MASTER where SL_NO=?)");
                stat1.setString(1,usrid); 
                stat1.setString(2, SLNO);
                result1=stat1.executeQuery();
                   
                  if(result1.next())
                  {
                stat1=conn.prepareStatement("select SL_NO,DEPT_SL_NO,TYPE_CODE,TYPE_DESC,FLAG from M4_BILL_type_MASTER where DEPT_SL_NO=? and FLAG='Y' "+str+" and TYPE_CODE in("+result1.getString(1)+")  order by  TYPE_DESC");
                
                stat1.setString(1, SLNO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                         String EXIST=null;
                      /* stat1=conn.prepareStatement("select * from M4_BILL_SUB_TYPE_MASTER where TYPE_SL_NO=?");
                       stat1.setString(1, result.getString("SL_NO"));
                       result1=stat1.executeQuery();
                       String EXIST=null;
                       if(result1.next())
                       {
                       EXIST="EXIST";
                       }
                       if(result1!=null)
                       {
                       result1.close();
                       }
                       if(stat1!=null)
                       {
                       stat1.close();
                       }
                        */
                      listmst.add(new M3BILLBean(result.getString("SL_NO"), result.getString("DEPT_SL_NO"), result.getString("TYPE_CODE"), result.getString("TYPE_DESC"), result.getString("FLAG"),EXIST));
                    }
                       }
                   
                   

            } catch (Exception e) {

                System.out.print("1 file name :gettypemst() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :gettypemst() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      return listmst;
  }


private List getsubtypemst(String SLNO) throws Exception {

       List listmst=new ArrayList();

        try {

            Connection conn = null;
             Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                connbi = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                 String str="";
                if(DIS!=null && DIS.length()>0)
                {
                str=" and SL_NO='"+SUB_TYPE_SL_NO+"'";
                }
                    stat1=conn.prepareStatement("select SL_NO,TYPE_SL_NO,SUB_TYPE_CODE,SUB_TYPE_DESC,FLAG from M4_BILL_SUB_TYPE_MASTER where FLAG='Y' and TYPE_SL_NO=? and SUB_TYPE_CODE is not null "+str+" order by  SL_NO");
                    stat1.setString(1, SLNO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                       String EXIST=null;
                       String DESC=null;
                      
                        
                      listmst.add(new M3BILLBean(result.getString("SL_NO"), result.getString("TYPE_SL_NO"), result.getString("SUB_TYPE_CODE"), result.getString("SUB_TYPE_DESC"), result.getString("FLAG"),EXIST,DESC));
                    }
                    
                   
                   

            } catch (Exception e) {

                System.out.print("1 file name :getsubtypemst() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                     if (connbi != null) {
                        connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;
                    connbi=null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :getsubtypemst()  M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      return listmst;
  }

private List getproductmst(String SLNO) throws Exception {

       List listmst=new ArrayList();

        try {

            Connection conn = null;
             Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                connbi = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                if(MAST_SL_NO==null)
                {
                MAST_SL_NO="0";
                }
                
                    stat1=conn.prepareStatement("select SL_NO,SUB_TYPE_SL_NO,PRODUCT_CODE,PRODUCT_DESC,FLAG from m4_BILL_PRODUCT_MASTER where FLAG='Y' and SUB_TYPE_SL_NO=? and PRODUCT_CODE is not null and sl_no not in(select distinct PRODUCT_SL_NO from m4_bill_detail where BILL_SL_NO=?  and  CC_CODE=? and TYPE_SL_NO=? and SUB_TYPE_SL_NO=? and PCH=?)  order by  PRODUCT_DESC");
                    stat1.setString(1, SLNO);
                    stat1.setString(2, MAST_SL_NO);
                    stat1.setString(3, CC_CODE);
                    stat1.setString(4, TYPE_SL_NO);
                    stat1.setString(5, SUB_TYPE_SL_NO);
                    stat1.setString(6, PCH);
                    
                  
                    
                    
                    result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                         String DESC=null;
                     
                      listmst.add(new M3BILLBean(result.getString("SL_NO"), result.getString("SUB_TYPE_SL_NO"), result.getString("PRODUCT_CODE"), result.getString("PRODUCT_DESC"), result.getString("FLAG"),DESC));
                    }
                    
                   
                   

            } catch (Exception e) {

                System.out.print("1 file name :getproductmst() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :getproductmst() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      return listmst;
  }

private void getproductmstlist(String SLNO) throws Exception {

       List listmst=new ArrayList();

        try {

            Connection conn = null;
             Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                connbi = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                   productlist=new ArrayList();
                    stat1=conn.prepareStatement("select SL_NO,SUB_TYPE_SL_NO,PRODUCT_CODE,PRODUCT_DESC,FLAG from m4_BILL_PRODUCT_MASTER where SUB_TYPE_SL_NO=?  order by  PRODUCT_DESC");
                    stat1.setString(1, SLNO);
                                        
                    result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                         String DESC=null;
                      
                      productlist.add(new M3BILLBean(result.getString("SL_NO"), result.getString("SUB_TYPE_SL_NO"), result.getString("PRODUCT_CODE"), result.getString("PRODUCT_DESC"), result.getString("FLAG"),DESC));
                    }
                    
                   stat1=conn.prepareStatement("select SL_NO,PRODUCT_SL_NO,PRODUCT_AMOUNT,remarks,TAXABLE from  m4_bill_detail where  BILL_SL_NO=? and CC_CODE=? and TYPE_SL_NO=? and SUB_TYPE_SL_NO=? and PCH=? order by SL_NO");
                   stat1.setString(1, MAST_SL_NO);
                   stat1.setString(2, CC_CODE);
                   stat1.setString(3, TYPE_SL_NO);
                   stat1.setString(4, SUB_TYPE_SL_NO);
                   stat1.setString(5, PCH);
                   
                   result=stat1.executeQuery();
                   while(result.next())
                    {
                         String TEMPTAXABLE="No";
                    if(result.getString("TAXABLE")!=null && result.getString("TAXABLE").equals("Y"))
                    {
                    TEMPTAXABLE="Yes";
                    }
                    saveprod.add(new M3BillEntryBean(result.getString("SL_NO"),result.getString("PRODUCT_SL_NO"),result.getDouble("PRODUCT_AMOUNT"),result.getString("remarks"),result.getString("TAXABLE"),TEMPTAXABLE));
                    
                    }

            } catch (Exception e) {

                System.out.print("1 file name :getproductmstlist() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :getproductmstlist() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

     
  }


public String shipbillsearch() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
try {

              Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                
                    shipbilldatelist=new ArrayList();
                   
                    if(SEARCH_CODE!=null && SEARCH_CODE.length()>0){
                    stat1=conn.prepareStatement("select distinct shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date from ei_endors_mast a,ei_shipment_dtls b where a.year=b.year and a.company=b.company  and a.inv_no=b.inv_no and a.location=? and cost_centre=? and SHP_BILL_NO like ? order by 1");
                    
                    stat1.setString(1, LOCATION_CODE);
                    stat1.setString(2, PCH);
                    stat1.setString(3, SEARCH_CODE+"%");
                                       
                    result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                       
                      flag=1;
                      shipbilldatelist.add(result.getString("shp_bill_no")+":"+result.getString("shp_bill_date"));
                    }
                    }
                 

            } catch (Exception e) {

                System.out.print("1 file name : M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

   if (flag==0){

            addActionMessage("Records Not Found(s) !!");
            
        }

return "searchshipbill";
}

public String invnew() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
        getinvdt();
    return "invlistpage";    
}
public String masterinvnewnew() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
       // getinvdt();
    return "invpagemast";    
}

public String importnew() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
       // getinvdt();
    return "importmast";    
}

public String importup() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
       getimport();
    return "importmastup";    
}

public String courierup() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
        getimport();
    return "courierup";    
}




public String masterinvup() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
       getinvdtmast();
    return "invmastup";    
}
public String remhead() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                if(actflg!=null && actflg.equals("Yes"))
                {
                stat1=conn.prepareStatement("update  M4_BILL_MASTER set remarks=? where SL_NO=?");
                stat1.setString(1, MAST_REMARKS);
                stat1.setString(2, MAST_SL_NO);
                flag=stat1.executeUpdate();
                if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
           
        } else {

            addActionMessage("Records Not save(s) !!");
            
        }
                }
                  stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                  stat1.setString(1, MAST_SL_NO);
                  result=stat1.executeQuery();
                 if(result.next())
                {
                FORWARD_DATE=result.getString(1);
                }
                
                stat1=conn.prepareStatement("select remarks from  m4_bill_master where SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                {
                MAST_REMARKS=result.getString(1);
                
                }
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name :remhead() getinvdtmast M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : remhead() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "rempage";    
}

public String debitdt() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
      Connection connbi = null;
            

            try {
                  conn = new ConnectionSeplWeb().getConnection();
                  connbi = new ConnectionShahiHris().getConnection();
                             
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                if(actflg!=null && actflg.equals("Yes"))
                {
                if(EMPTYPE!=null && EMPTYPE.equals("ENT")) 
                {
                
                    stat1=conn.prepareStatement("select *  from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                    stat1.setString(1,MAST_SL_NO) ;
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                       stat1=conn.prepareStatement("update  M4_BILL_DEBIT_DETAIL set DEBIT_AMOUNT=?,DEBIT_REASON=?,TDATE=sysdate,USER_ID=? where BILL_SL_NO=?");
                        stat1.setString(1, DEBIT_AMOUNT);
                        stat1.setString(2, DEBIT_REASON);
                        stat1.setString(3, usrid);
                        stat1.setString(4, MAST_SL_NO);
                        flag=stat1.executeUpdate();
                        
                    }else{
                               if(DEBIT_AMOUNT!=null && DEBIT_AMOUNT.length()>0 && Double.parseDouble(DEBIT_AMOUNT)>0)
                         {
                                stat1=conn.prepareStatement("insert into M4_BILL_DEBIT_DETAIL(BILL_SL_NO,DEBIT_AMOUNT,DEBIT_REASON,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                                stat1.setString(1, MAST_SL_NO);
                                stat1.setString(2, DEBIT_AMOUNT);
                                stat1.setString(3, DEBIT_REASON);
                                stat1.setString(4, usrid);
                                flag=stat1.executeUpdate();
                         }
                    }
                    
                    
                }
                if(EMPTYPE!=null && EMPTYPE.equals("ACC")) 
                {
                
                    stat1=conn.prepareStatement("select *  from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                    stat1.setString(1,MAST_SL_NO) ;
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                        stat1=conn.prepareStatement("update  M4_BILL_DEBIT_DETAIL set ACC_DEBIT_AMOUNT=?,ACC_DEBIT_REASON=?,ACC_TDATE=sysdate,ACC_USER_ID=? where BILL_SL_NO=?");
                        stat1.setString(1, ACC_DEBIT_AMOUNT);
                        stat1.setString(2, ACC_DEBIT_REASON);
                        stat1.setString(3, usrid);
                        stat1.setString(4, MAST_SL_NO);
                        flag=stat1.executeUpdate();
                        
                    }else{
                        if(ACC_DEBIT_AMOUNT!=null && ACC_DEBIT_AMOUNT.length()>0 && Double.parseDouble(ACC_DEBIT_AMOUNT)>0)
                         {
                            stat1=conn.prepareStatement("insert into M4_BILL_DEBIT_DETAIL(BILL_SL_NO,ACC_DEBIT_AMOUNT,ACC_DEBIT_REASON,ACC_TDATE,ACC_USER_ID) values(?,?,?,sysdate,?)");
                            stat1.setString(1, MAST_SL_NO);
                            stat1.setString(2, ACC_DEBIT_AMOUNT);
                            stat1.setString(3, ACC_DEBIT_REASON);
                            stat1.setString(4, usrid);
                            flag=stat1.executeUpdate();
                          }
                    
                    }
                    
                }
                
               
                
                
                if (flag > 0) {
                    addActionMessage("Records  saved(s) !!");
                   } else {
                    addActionMessage("Records Not save(s) !!");
                }
                }
                stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy'),GROSS_AMOUNT from M4_BILL_MASTER where SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                 {
                 FORWARD_DATE=result.getString(1);
                 GROSS_AMOUNT=result.getString(2);
                 }
                  
                 stat1=conn.prepareStatement("select ACCOUNT_DATE from m4_bill_master where SL_NO=? and ACCOUNT_DATE is not null");
                 stat1.setString(1, MAST_SL_NO);
                 result1=stat1.executeQuery();
                if(result1.next())
                {
                ACCOUNTDATE=result1.getString(1);
                } 
                  
                
                stat1=conn.prepareStatement("select DEBIT_AMOUNT,DEBIT_REASON,USER_ID from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                {
                 DEBIT_AMOUNT=result.getString(1);
                 DEBIT_REASON=result.getString(2);
                  
                        stat1=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                         stat1.setString(1, result.getString("USER_ID"));
                         result1=stat1.executeQuery();

                         if(result1.next())
                         {
                         ENT_USER_ID=result1.getString(1);
                         }
                }
                
                stat1=conn.prepareStatement("select ACC_DEBIT_AMOUNT,ACC_DEBIT_REASON,ACC_USER_ID from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                {
                 ACC_DEBIT_AMOUNT=result.getString(1);
                 ACC_DEBIT_REASON=result.getString(2);
                 
                     stat1=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                         stat1.setString(1, result.getString("ACC_USER_ID"));
                         result1=stat1.executeQuery();

                         if(result1.next())
                         {
                         ACC_USER_ID=result1.getString(1);
                         }
                
                }
                
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : debitdt() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if (connbi != null) {
                        connbi.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : debitdt() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "debitpage";    
}


public String testingup() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
      Connection conn1 = null;
            

            try {
                 conn = new ConnectionSeplWeb().getConnection();
                 
                conn1 = new ConnectionShahiHris().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                if(savetest!=null && savetest.equals("Yes"))
                {
                stat1=conn.prepareStatement("delete from   m4_bill_add_master  where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                flag=stat1.executeUpdate();
                stat1=conn.prepareStatement("delete from   M4_BILL_ADD_BREAKUP  where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                flag=stat1.executeUpdate();
                
                 if(BUYERCODETEST!=null && BUYERCODETEST.length()>0 && INV_REPORT!=null && INV_REPORT.size()>0)
                         {
                   for(int i=0; i<INV_REPORT.size(); i++)
                   {
                    if(INV_REPORT!=null && INV_REPORT.get(i).toString()!=null && INV_REPORT.get(i).toString().length()>0 )
                    {  
                       
                           if(i==0){
                               stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                                result1=stat1.executeQuery();
                                String brkslno="0";
                                if(result1.next())
                                {
                                  brkslno=result1.getString(1);
                                }
                            stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,BUYER,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                            stat1.setString(1, brkslno);
                            stat1.setString(2, MAST_SL_NO);
                            stat1.setString(3, BUYERCODETEST);
                            stat1.setString(4, usrid);
                            flag=stat1.executeUpdate();
                           }
                                stat1=conn.prepareStatement("select M4_BILL_ADD_BREAKUP_sq.nextval from dual");
                                result1=stat1.executeQuery();
                                String brkslno="0";
                                if(result1.next())
                                {
                                  brkslno=result1.getString(1);
                                }
                                
                                stat1=conn.prepareStatement("insert into M4_BILL_ADD_BREAKUP(SL_NO,BILL_SL_NO,REPORT_NO,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                                stat1.setString(1, brkslno);
                                stat1.setString(2, MAST_SL_NO);
                                stat1.setString(3, INV_REPORT.get(i).toString().toUpperCase());
                                stat1.setString(4, usrid);
                                flag=stat1.executeUpdate();
                           
                           
                         } 
                   }
                         }
                
                if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
           
        } else {

            addActionMessage("Records Not save(s) !!");
            
        }
         }
                
                
                
                  stat1=conn.prepareStatement("select BUYER from M4_BILL_ADD_master where BILL_SL_NO=?");
                  stat1.setString(1, MAST_SL_NO);
                  result=stat1.executeQuery();
                  if(result.next())
                  {
                        BUYERCODETEST=result.getString(1);
                   
                        stat1=conn1.prepareStatement("select okcunm from prodbi.ocusma where OKCONO=111 and okcusu=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        BUYERNAMETEST=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                   //conn1
                   
                  }
                
                   stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                   stat1.setString(1, MAST_SL_NO);
                   result=stat1.executeQuery();
                    if(result.next())
                   {
                   FORWARD_DATE=result.getString(1);
                   }
                
                    if(FORWARD_DATE==null)
                    {
                    
                    buyerlist=new ShahiInformationList().buyerList();
                    }
                    
                stat1=conn.prepareStatement("select * from  M4_BILL_ADD_BREAKUP where BILL_SL_NO=?  order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
                invsavelist.add(new M3BILLBean(result.getString("REPORT_NO")));
                
                }
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : testingup() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : testingup() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "testup";    
}


public String bos() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
                

            try {
                 conn = new ConnectionSeplWeb().getConnection();
                
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                String fieldname="";
                     
                    
                //Import condation
                       if(SHOWFLAG!=null && SHOWFLAG.equals("BOE"))
                         {
                          DISPLAY_NAME="BOE";
                          fieldname="BOE_NO";
                       
                         }
                     
                      if(SHOWFLAG!=null && SHOWFLAG.equals("AWBNO"))
                         {
                          DISPLAY_NAME="HAWB/MAWB/BL";
                          fieldname="AWB_NO";
                       
                         }
                       if(SHOWFLAG!=null && SHOWFLAG.equals("EXINV"))
                         {
                          DISPLAY_NAME="Invoice No";
                          fieldname="INV_NO";
                       
                         }
                       if(SHOWFLAG!=null && SHOWFLAG.equals("EXLIC"))
                         {
                          DISPLAY_NAME="Licence No";
                          fieldname="LIC_NO";
                       
                         }
                      
                      // close Import condation
                       
                 if(savetest!=null && savetest.equals("Ser"))
                {
                  
                   
                    if (SHOWFLAG != null && SHOWFLAG.equals("AWBNO")) {

                        String list[] = null;
                        String aa = null;
                        if (BOS != null && BOS.length() > 0) {
                            if (BOS.indexOf(",") != -1) {
                                list = BOS.split(",");
                                if (list != null && list.length > 0) {
                                    for (int i = 0; i < list.length; i++) {
                                        if (aa == null) {
                                            aa = "'" + list[i] + "'";
                                        } else {
                                            aa = aa + ",'" + list[i] + "'";
                                        }
                                    }
                                }
                            } else {
                                aa = "'" + BOS + "'";
                            }
                        }
                        stat1 = conn.prepareStatement("select ref_no awbl_no  from pi_imp_awbl_mast  where ref_no in (" + aa + ") order by 1");
                        result = stat1.executeQuery();

                        while (result.next()) {
                            flag = 1;
                            invlist.add(new M3BILLBean(result.getString(1)));
                        }
                    }
                    
                     if (SHOWFLAG != null && SHOWFLAG.equals("EXINV")) {

                        String list[] = null;
                        String aa = null;
                        if (BOS != null && BOS.length() > 0) {
                            if (BOS.indexOf(",") != -1) {
                                list = BOS.split(",");
                                if (list != null && list.length > 0) {
                                    for (int i = 0; i < list.length; i++) {
                                        if (aa == null) {
                                            aa = "'" + list[i] + "'";
                                        } else {
                                            aa = aa + ",'" + list[i] + "'";
                                        }
                                    }
                                }
                            } else {
                                aa = "'" + BOS + "'";
                            }
                        }
                        stat1 = conn.prepareStatement("select distinct inv_no from ted_inv_mast where inv_no in (" + aa + ") order by 1");
                        result = stat1.executeQuery();

                        while (result.next()) {
                            flag = 1;
                            invlist.add(new M3BILLBean(result.getString(1)));
                        }
                    }
                    
                      if (SHOWFLAG != null && SHOWFLAG.equals("EXLIC")) {

                        String list[] = null;
                        String aa = null;
                        if (BOS != null && BOS.length() > 0) {
                            if (BOS.indexOf(",") != -1) {
                                list = BOS.split(",");
                                if (list != null && list.length > 0) {
                                    for (int i = 0; i < list.length; i++) {
                                        if (aa == null) {
                                            aa = "'" + list[i] + "'";
                                        } else {
                                            aa = aa + ",'" + list[i] + "'";
                                        }
                                    }
                                }
                            } else {
                                aa = "'" + BOS + "'";
                            }
                        }
                       
                        stat1 = conn.prepareStatement("select REF_NO  from pi_imp_lic_mast where REF_NO  in (" + aa + ") union select ref_no from ei_lc_lic_mast where REF_NO  in (" + aa + ") union select lic_no ref_no from ei_mlfs_mast where lic_no in (" + aa + ")  order by 1");
                        result = stat1.executeQuery();

                        while (result.next()) {
                            flag = 1;
                            invlist.add(new M3BILLBean(result.getString(1)));
                        }
                    }
                     
                  
                       if (SHOWFLAG != null && SHOWFLAG.equals("BOE")) {

                        String list[] = null;
                        String aa = null;
                        if (BOS != null && BOS.length() > 0) {
                            if (BOS.indexOf(",") != -1) {
                                list = BOS.split(",");
                                if (list != null && list.length > 0) {
                                    for (int i = 0; i < list.length; i++) {
                                        if (aa == null) {
                                            aa = "'" + list[i] + "'";
                                        } else {
                                            aa = aa + ",'" + list[i] + "'";
                                        }
                                    }
                                }
                            } else {
                                aa = "'" + BOS + "'";
                            }
                        }
                       
                        stat1 = conn.prepareStatement("select a.be_no  from pi_imp_boe_mast a, pi_imp_boe_dtls b  where a.be_no = b.be_no and a.be_no in (" + aa.toUpperCase() + ")   order by 1");
                        result = stat1.executeQuery();
                        while (result.next()) {
                            flag = 1;
                            invlist.add(new M3BILLBean(result.getString(1)));
                        }
                    }
                      
                     
                    
                    if (flag == 0) {
                        addActionMessage("Records Not Found(s) !!");
                    }
                
                }
                
                
                
                if(savetest!=null && savetest.equals("Yes"))
                {
                stat1=conn.prepareStatement("delete from   m4_bill_add_master  where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                flag=stat1.executeUpdate();
               
                
                  if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                    
                        stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         if (SHOWFLAG != null && SHOWFLAG.equals("BOE")) {
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,"+fieldname+",TDATE,USER_ID,LR_NO) values(?,?,?,sysdate,?,?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                        stat1.setString(5, LR_NO.get(i).toString());
                        flag=stat1.executeUpdate();
                         
                         }else{
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,"+fieldname+",TDATE,USER_ID) values(?,?,?,sysdate,?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                        flag=stat1.executeUpdate();
                         }
                    
                    }
                
                }
            }
                  if (flag >0) {
               addActionMessage("Records  saved(s) !!");
               } else {
                    addActionMessage("Records Not save(s) !!");
            
                      }
              
         }
                
                
                
                 
                
                   stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                   stat1.setString(1, MAST_SL_NO);
                   result=stat1.executeQuery();
                    if(result.next())
                   {
                   FORWARD_DATE=result.getString(1);
                   }
                
                  
                    
                stat1=conn.prepareStatement("select * from  m4_bill_add_master where BILL_SL_NO=? and "+fieldname+" is not null  order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
                invsavelist.add(new M3BILLBean(result.getString(fieldname),result.getString("LR_NO")));
                
                }
                
             }  catch (Exception e) {

                System.out.print("1 file name :bos() BOS M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :bos() BOS M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "bospage";    
}


public String newinv() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
       Connection conn1 = null;
                

            try {
                 conn = new ConnectionSeplWeb().getConnection();
                  conn1 = new ConnectionShahiHris().getConnection();
                
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                String fieldname="";
                    
                      if(SHOWFLAG!=null && SHOWFLAG.equals("SB"))
                         {
                          DISPLAY_NAME="S/Bill";
                          fieldname="SHP_BILL_NO";
                       
                         }
                      if(SHOWFLAG!=null && SHOWFLAG.equals("LCERT"))
                         {
                          DISPLAY_NAME="Landing Certificate";
                          fieldname="LCERT_RECV_NO";
                       
                         }
                      
                      if(SHOWFLAG!=null && SHOWFLAG.equals("FABTRI"))
                         {
                          DISPLAY_NAME="Fabric & Trims Inv.";
                          fieldname="INV_NO";
                       
                         }
                    
                
                       
                 if(savetest!=null && savetest.equals("Ser"))
                {
                   
                    
                     if(SHOWFLAG!=null && SHOWFLAG.equals("LCERT")){
                     stat1 = conn.prepareStatement(" select distinct lcert_recv_no,a.buyer,a.cost_centre " +
"                     from ei_endors_mast a,ei_shipment_dtls b,ei_sbill_master c,ei_bos_transit d " +
"            where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and " +
"            b.shp_bill_no=c.shp_bill_no and b.shp_bill_date=c.shp_bill_date   " +
"            and a.loading=d.port_code and d.mlfs_loct=?  and    lcert_recv_no in (" + BOS + ") order by 1,2");
                   stat1.setString(1,LOCATION_CODE);
                    result = stat1.executeQuery();

                    while (result.next()) {
                        flag = 1;
                        String okcusu="";
                     
                        stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and OKCUNO=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString("cost_centre");
                         /*
                         stat1=conn.prepareStatement("select PCH from  pch_master where PCH1=? and FLAG='A'");
                         stat1.setString(1,result.getString("cost_centre"));
                         result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        temppch=result1.getString(1);
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         
                         */
                         
                      invlist.add(new M3BILLBean(result.getString(1), temppch,okcusu));
                    }
                    }
                    
                    
                   if(SHOWFLAG!=null && SHOWFLAG.equals("SB")){
                  String list[]=null;
                 String aa=null;
                if (BOS != null && BOS.length() > 0) {
                    if (BOS.indexOf(",") != -1) {
                        list = BOS.split(",");
                         if(list!=null && list.length>0)
                         {  for(int i=0; i<list.length; i++)
                         {
                             if(aa==null)
                             {
                             aa="'"+list[i]+"'";
                             }else{
                              aa=aa + ",'"+list[i]+"'";
                             }
                         }
                         }
                     }else{
                     aa="'"+BOS+"'";
                    }
                }
                
                
                     stat1 = conn.prepareStatement("select distinct shp_bill_no,b.buyer,b.cost_centre  from ei_Shipment_dtls a,ei_endors_mast b  " +
                    "  where a.year=b.year and a.company=b.company " +
                    "  and a.inv_no=b.inv_no and shp_bill_no in   (" + aa + ") order by 1,2");
                     result = stat1.executeQuery();

                    while (result.next()) {
                        flag = 1;
                         String okcusu="";
                     
                        stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and OKCUNO=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString("cost_centre");
                         /*
                         stat1=conn.prepareStatement("select PCH from  pch_master where PCH1=? and FLAG='A'");
                         stat1.setString(1,result.getString("cost_centre"));
                         result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        temppch=result1.getString(1);
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         */
                         
                         
                      invlist.add(new M3BILLBean(result.getString(1), temppch,okcusu));
                    }
                    }
                   
                       if(SHOWFLAG!=null && SHOWFLAG.equals("FABTRI")){
                  String list[]=null;
                 String aa=null;
                if (BOS != null && BOS.length() > 0) {
                    if (BOS.indexOf(",") != -1) {
                        list = BOS.split(",");
                         if(list!=null && list.length>0)
                         {  for(int i=0; i<list.length; i++)
                         {
                             if(aa==null)
                             {
                             aa="'"+list[i]+"'";
                             }else{
                              aa=aa + ",'"+list[i]+"'";
                             }
                         }
                         }
                     }else{
                     aa="'"+BOS+"'";
                    }
                }
                
                
                     stat1 = conn.prepareStatement("select excs_inv_no,cost_centre,buyer  from ei_endors_mast where excs_inv_no in (" + aa + ") and nvl(surrender_yn,'N')='N'  and exp_type not in ('GMT','HME')    order by 1,2");
                     result = stat1.executeQuery();

                    while (result.next()) {
                        flag = 1;
                         String okcusu="";
                     
                        stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and OKCUNO=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString("cost_centre");
                         /*
                         stat1=conn.prepareStatement("select PCH from  pch_master where PCH1=? and FLAG='A'");
                         stat1.setString(1,result.getString("cost_centre"));
                         result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        temppch=result1.getString(1);
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         */
                         
                         
                      invlist.add(new M3BILLBean(result.getString(1), temppch,okcusu));
                    }
                    }
                     
                    
                    if (flag == 0) {
                        addActionMessage("Records Not Found(s) !!");
                    }
                
                }
                
                
                
                if(savetest!=null && savetest.equals("Yes"))
                {
                stat1=conn.prepareStatement("delete from   m4_bill_add_master  where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                flag=stat1.executeUpdate();
               
                
                  if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                    
                        stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,"+fieldname+",TDATE,USER_ID,INV_WEIGHT,BUYER,pch) values(?,?,?,sysdate,?,?,?,?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                        stat1.setString(5, INV_WEIGHT.get(i).toString());
                        stat1.setString(6, INV_BUYER.get(i).toString());
                        stat1.setString(7, INV_PCH.get(i).toString());
                        flag=stat1.executeUpdate();
                     
                      
                    }
                
                }
            }
                  if (flag >0) {
               addActionMessage("Records  saved(s) !!");
               } else {
                    addActionMessage("Records Not save(s) !!");
            
                      }
              
         }
                
                
                
                 
                
                   stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                   stat1.setString(1, MAST_SL_NO);
                   result=stat1.executeQuery();
                    if(result.next())
                   {
                   FORWARD_DATE=result.getString(1);
                   }
                
                  
                    
                stat1=conn.prepareStatement("select * from  m4_bill_add_master where BILL_SL_NO=? and "+fieldname+" is not null  order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
               // invsavelist.add(new M3BILLBean(result.getString(fieldname)));
                 invsavelist.add(new M3BILLBean(result.getString(fieldname), result.getString("INV_WEIGHT"),result.getString("BUYER"),result.getString("PCH"),0));
               
                
                
                }
                
             }  catch (Exception e) {

                System.out.print("1 file name : BOS M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if (conn1 != null) {
                        conn1.close();
                    }
                    
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : BOS M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "newinvpage";    
}

public String newbos() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
       Connection conn1 = null;
                

            try {
                 conn = new ConnectionSeplWeb().getConnection();
                  conn1 = new ConnectionShahiHris().getConnection();
                
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                String fieldname="";
                     if(SHOWFLAG!=null && SHOWFLAG.equals("BOS"))
                         {
                          DISPLAY_NAME="BOS/Shipment Challan";
                          fieldname="BOS_NO";
                       
                         }
                    
                    
                
                       
                 if(savetest!=null && savetest.equals("Ser"))
                {
                    if(SHOWFLAG!=null && SHOWFLAG.equals("BOS")){
                     stat1 = conn.prepareStatement(" select distinct a.bos_no,c.buyer,c.cost_centre,to_char(bos_date,'dd/mm/yyyy') bos_date,a.bos_loct from ei_bos_mast a,ei_bos_dtls b,ei_endors_mast c " +
"                                                 where b.year=c.year and b.company=c.company and b.inv_no=c.inv_no " +
"                                                 and a.bos_no=b.bos_no  " +
"                                                 and a.bos_loct=b.bos_location " +
"                                                 and nvl(b.dispatch_yn,'N')='Y'  and  cancel_date is null and" +
"                                                 a.bos_no in (" + BOS + ") " +
                            " union select distinct a.bos_no,c.buyer,c.cost_centre,to_char(bos_date,'dd/mm/yyyy') bos_date,a.bos_loct from ei_bos_mast a,ei_bos_dtls b,ei_endors_mast c " +
                                            "     where b.year=c.year and b.company=c.company and b.inv_no=c.inv_no " +
                                             "    and a.bos_no=b.bos_no " +
                                              "   and a.bos_loct=b.bos_location " +
                                               "  and CANCEL_DATE is not null and " +
                                                "  a.bos_no in (" + BOS + ")" +
                             " union select distinct to_number(IR_NUMB) bos_no,buyer,' ' cost_centre,to_char(IR_DATE,'dd/mm/yyyy') bos_date,LOCATION_CODE bos_loct from PR_PACK_BOX_IR_master where to_number(IR_NUMB) in (" + BOS + ") order by 1,2");
                     result = stat1.executeQuery();
                     
                     while (result.next()) {
                        flag = 1;
                         String okcusu="";
                           
                        stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and trim(OKCUNO)=trim(?)");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString("cost_centre");
                         if(temppch!=null && temppch.trim().length()==0)
                         {
                         stat1=conn.prepareStatement("select mmbuar from  prodbi.mitmas@ams where mmcono='111' and  mmitno=(select  min(ITEM_NUMB) from PR_PACK_BOX_IR_detail where IR_NUMB=?) ");
                        stat1.setString(1,result.getString("bos_no"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        temppch=result1.getString(1);
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         }
                         /*
                         stat1=conn.prepareStatement("select PCH from  pch_master where PCH1=? and FLAG='A'");
                         stat1.setString(1,result.getString("cost_centre"));
                         result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        temppch=result1.getString(1);
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         */
                         
                         
                      invlist.add(new M3BILLBean(result.getString(1), temppch,okcusu,result.getString("bos_loct"),0,result.getString("bos_date")));
                    }
                    }
                    
                    
                    if (flag == 0) {
                        addActionMessage("Records Not Found(s) !!");
                    }
                
                }
                
                
                
                if(savetest!=null && savetest.equals("Yes"))
                {
                stat1=conn.prepareStatement("delete from   m4_bill_add_master  where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                flag=stat1.executeUpdate();
               
                
                  if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                    
                        stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,"+fieldname+",TDATE,USER_ID,INV_WEIGHT,BUYER,pch,BOS_DATE,BOS_LOCT) values(?,?,?,sysdate,?,?,?,?,to_date(?,'dd/mm/yyyy'),?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                        stat1.setString(5, INV_WEIGHT.get(i).toString());
                        stat1.setString(6, INV_BUYER.get(i).toString());
                        stat1.setString(7, INV_PCH.get(i).toString());
                        stat1.setString(8, BOS_DATE.get(i).toString());
                        stat1.setString(9, BOS_LOCT.get(i).toString());
                        
                        flag=stat1.executeUpdate();
                     
                      
                    }
                
                }
            }
                  if (flag >0) {
               addActionMessage("Records  saved(s) !!");
               } else {
                    addActionMessage("Records Not save(s) !!");
            
                      }
              
         }
                
                
                
                 
                
                   stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                   stat1.setString(1, MAST_SL_NO);
                   result=stat1.executeQuery();
                    if(result.next())
                   {
                   FORWARD_DATE=result.getString(1);
                   }
                
                  
                    
                stat1=conn.prepareStatement("select SL_NO,BILL_SL_NO,BOS_NO,BUYER,PCH,INV_WEIGHT,to_char(BOS_DATE,'dd/mm/yyyy') BOS_DATE,BOS_LOCT from  m4_bill_add_master where BILL_SL_NO=? and "+fieldname+" is not null  order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
               // invsavelist.add(new M3BILLBean(result.getString(fieldname)));
                   
                 invsavelist.add(new M3BILLBean(result.getString(fieldname), result.getString("INV_WEIGHT"),result.getString("BUYER"),result.getString("PCH"),0,result.getString("BOS_DATE"),result.getString("BOS_LOCT")));
              
                
                
                }
                
             }  catch (Exception e) {

                System.out.print("1 file name : BOS M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if (conn1 != null) {
                        conn1.close();
                    }
                    
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : BOS M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "newbosage";    
}



public String newbillofs() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
       Connection conn = null;
       Connection conn1 = null;
                

            try {
                 conn = new ConnectionSeplWeb().getConnection();
                  conn1 = new ConnectionShahiHris().getConnection();
                
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                 String fieldname="";
                    
                      if(SHOWFLAG!=null && SHOWFLAG.equals("SB"))
                         {
                          DISPLAY_NAME="S/Bill";
                          fieldname="SHP_BILL_NO";
                       
                         }
                    
                
                       
                 if(savetest!=null && savetest.equals("Ser"))
                {
                     if(SHOWFLAG!=null && SHOWFLAG.equals("SB")){
                  String list[]=null;
                 String aa=null;
                if (BOS != null && BOS.length() > 0) {
                    if (BOS.indexOf(",") != -1) {
                        list = BOS.split(",");
                         if(list!=null && list.length>0)
                         {  for(int i=0; i<list.length; i++)
                         {
                             if(aa==null)
                             {
                             aa="'"+list[i]+"'";
                             }else{
                              aa=aa + ",'"+list[i]+"'";
                             }
                         }
                         }
                     }else{
                     aa="'"+BOS+"'";
                    }
                }
                
                
                String sqlst="";
                if(SHP_BILL_DATE_S!=null && SHP_BILL_DATE_S.length()>0)
                {
                    SHP_BILL_DATE_S=SHP_BILL_DATE_S.substring(0, 10);
                    
                sqlst=" and shp_bill_date=to_date('"+SHP_BILL_DATE_S+"','yyyy-mm-dd')";
                }
                     stat1 = conn.prepareStatement("select distinct shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date,b.buyer,b.cost_centre  from ei_Shipment_dtls a,ei_endors_mast b  " +
                    "  where a.year=b.year and a.company=b.company " +
                    "  and a.inv_no=b.inv_no and shp_bill_no in   (" + aa + ") "+sqlst+" order by 1,2");
                     result = stat1.executeQuery();

                    while (result.next()) {
                        flag = 1;
                         String okcusu="";
                     
                        stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and OKCUNO=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString("cost_centre");
                    
                         
                         
                       invlist.add(new M3BILLBean(result.getString(1), temppch,okcusu,"",0,result.getString("shp_bill_date")));
                  }
                    }
                    
                    
                    if (flag == 0) {
                        addActionMessage("Records Not Found(s) !!");
                    }
                
                }
                
                
                
                if(savetest!=null && savetest.equals("Yes"))
                {
                stat1=conn.prepareStatement("delete from   m4_bill_add_master  where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                flag=stat1.executeUpdate();
               
                
                  if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                    
                        stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,"+fieldname+",TDATE,USER_ID,INV_WEIGHT,BUYER,pch,shp_bill_date) values(?,?,?,sysdate,?,?,?,?,to_date(?,'dd/mm/yyyy'))");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                        stat1.setString(5, INV_WEIGHT.get(i).toString());
                        stat1.setString(6, INV_BUYER.get(i).toString());
                        stat1.setString(7, INV_PCH.get(i).toString());
                        stat1.setString(8, BOS_DATE.get(i).toString());
                        
                        flag=stat1.executeUpdate();
                     
                      
                    }
                
                }
            }
                  if (flag >0) {
               addActionMessage("Records  saved(s) !!");
               } else {
                    addActionMessage("Records Not save(s) !!");
            
                      }
              
         }
                
                
                
                 
                
                   stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                   stat1.setString(1, MAST_SL_NO);
                   result=stat1.executeQuery();
                    if(result.next())
                   {
                   FORWARD_DATE=result.getString(1);
                   }
                
                  
                    
                stat1=conn.prepareStatement("select SL_NO,BILL_SL_NO,SHP_BILL_NO,BUYER,PCH,INV_WEIGHT,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date from  m4_bill_add_master where BILL_SL_NO=? and "+fieldname+" is not null  order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
               // invsavelist.add(new M3BILLBean(result.getString(fieldname)));
                 invsavelist.add(new M3BILLBean(result.getString(fieldname), result.getString("INV_WEIGHT"),result.getString("BUYER"),result.getString("PCH"),0,result.getString("shp_bill_date")));
               
                
                
                }
                
             }  catch (Exception e) {

                System.out.print("1 file name : newbillofs M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if (conn1 != null) {
                        conn1.close();
                    }
                    
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : newbillofs M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "shpbilldate";    
}

public String chkbillofs() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
       Connection conn = null;
       Connection conn1 = null;
                

            try {
                 conn = new ConnectionSeplWeb().getConnection();
                  conn1 = new ConnectionShahiHris().getConnection();
                
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                 String fieldname="";
                    
                      if(SHOWFLAG!=null && SHOWFLAG.equals("CHK"))
                         {
                          DISPLAY_NAME="Cheque Details";
                          fieldname="SHP_BILL_NO";
                           SHOWFLAG="CHK";
                         DISPLAY_NAME="Cheque Details";
                       
                         }
                    
                
                       
                 if(savetest!=null && savetest.equals("Ser"))
                {
                     if(SHOWFLAG!=null && SHOWFLAG.equals("CHK")){
                  String list[]=null;
                 String aa=null;
                if (BOS != null && BOS.length() > 0) {
                    if (BOS.indexOf(",") != -1) {
                        list = BOS.split(",");
                         if(list!=null && list.length>0)
                         {  for(int i=0; i<list.length; i++)
                         {
                             if(aa==null)
                             {
                             aa="'"+list[i]+"'";
                             }else{
                              aa=aa + ",'"+list[i]+"'";
                             }
                         }
                         }
                     }else{
                     aa="'"+BOS+"'";
                    }
                }
                
                
                String sqlst="";
               // if(SHP_BILL_DATE_S!=null && SHP_BILL_DATE_S.length()>0)
               // {
                   // SHP_BILL_DATE_S=SHP_BILL_DATE_S.substring(0, 10);
                    
               // sqlst=" and shp_bill_date=to_date('"+SHP_BILL_DATE_S+"','yyyy-mm-dd')";
               // }
                     stat1 = conn.prepareStatement("select a.year,a.chq_no,b.shp_bill_no,to_char(b.shp_bill_date,'dd/mm/yyyy') shp_bill_date from ei_dbk_chq_mast a,ei_dbk_chq_dtls b where a.year=b.year and a.chq_no=b.chq_no and a.chq_no in   (" + aa.toUpperCase() + ") "+sqlst+" order by 3,1");
                     result = stat1.executeQuery();

                    while (result.next()) {
                        flag = 1;
                         String okcusu="";
                     
                      /*  stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and OKCUNO=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString("cost_centre");
                       */
                         String temppch="";
                         
                       invlist.add(new M3BILLBean(result.getString("shp_bill_no"), result.getString("chq_no"),okcusu,"",0,result.getString("shp_bill_date")));
                  }
                    }
                    
                    
                    if (flag == 0) {
                        addActionMessage("Records Not Found(s) !!");
                    }
                
                }
                
                
                
                if(savetest!=null && savetest.equals("Yes"))
                {
                stat1=conn.prepareStatement("delete from   m4_bill_add_master  where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                flag=stat1.executeUpdate();
               
                
             if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                    
                        stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,SHP_BILL_NO,TDATE,USER_ID,shp_bill_date,CHQ_NO) values(?,?,?,sysdate,?,to_date(?,'dd/mm/yyyy'),?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                       // stat1.setString(5, INV_WEIGHT.get(i).toString());
                      
                       // stat1.setString(7, INV_PCH.get(i).toString());
                        stat1.setString(5, BOS_DATE.get(i).toString());
                        stat1.setString(6, INV_WEIGHT.get(i).toString());
                        
                        flag=stat1.executeUpdate();
                     
                      
                    }
                
                }
            }
                  if (flag >0) {
               addActionMessage("Records  saved(s) !!");
               } else {
                    addActionMessage("Records Not save(s) !!");
            
                      }
              
         }
                
                
                
                 
                
                   stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                   stat1.setString(1, MAST_SL_NO);
                   result=stat1.executeQuery();
                    if(result.next())
                   {
                   FORWARD_DATE=result.getString(1);
                   }
                
                  
                    
                stat1=conn.prepareStatement("select SL_NO,BILL_SL_NO,SHP_BILL_NO,BUYER,PCH,INV_WEIGHT,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date,chq_no from  m4_bill_add_master where BILL_SL_NO=? and CHQ_NO is not null  order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
               // invsavelist.add(new M3BILLBean(result.getString(fieldname)));
                 invsavelist.add(new M3BILLBean(result.getString("SHP_BILL_NO"), result.getString("chq_no"),result.getString("BUYER"),result.getString("PCH"),0,result.getString("shp_bill_date")));
               
                
                
                }
                
             }  catch (Exception e) {

                System.out.print("1 file name :chkbillofs M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if (conn1 != null) {
                        conn1.close();
                    }
                    
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : chkbillofs M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "chkdt";    
}




public void getinvdtmast()
{
 Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                 stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                 stat1.setString(1, MAST_SL_NO);
                  result=stat1.executeQuery();
                 if(result.next())
                {
                FORWARD_DATE=result.getString(1);
                }
                
                stat1=conn.prepareStatement("select * from  m4_bill_add_master where BILL_SL_NO=? and INV_NO is not null  order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
                invsavelist.add(new M3BILLBean(result.getString("INV_NO"), result.getString("INV_WEIGHT"),result.getString("BUYER"),result.getString("PCH"),0));
                
                }
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : getinvdtmast M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : getinvdtmast M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }

       

}


public void getimport()
{
 Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                 stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                 stat1.setString(1, MAST_SL_NO);
                  result=stat1.executeQuery();
                    if(result.next())
                   {
                   FORWARD_DATE=result.getString(1);
                   }
                
                stat1=conn.prepareStatement("select * from  m4_bill_add_master where BILL_SL_NO=?  and AWB_NO is not null order by 1");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
                invsavelist.add(new M3BILLBean(result.getString("AWB_NO")));
                
                }
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : getimport() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : getimport() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }

       

}

public String importserup() throws Exception {
    
   getimport();
   
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
try {

              Connection conn = null;
              
            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                   
                    
                    stat1=conn.prepareStatement("select ref_no awbl_no  from pi_imp_awbl_mast  where upper(ref_no) like ? and upper(ref_no) not in(select AWB_NO from m4_bill_add_master where BILL_SL_NO=?) "+
                            " union select b.ref_no from pi_imp_boe_mast a, pi_imp_boe_dtls b  where a.be_no = b.be_no and upper(a.be_no) like ? and upper(b.ref_no) not in(select AWB_NO from m4_bill_add_master where BILL_SL_NO=?)");
                    stat1.setString(1, SSHIPBILL.toUpperCase()+"%");
                    stat1.setString(2, MAST_SL_NO);
                    stat1.setString(3, SSHIPBILL.toUpperCase()+"%");
                    stat1.setString(4, MAST_SL_NO);
                    result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                       flag=1;
                       invlist.add(new M3BILLBean(result.getString(1)));
                    }
                    
                    

            } catch (Exception e) {

                System.out.print("1 file name :importserup() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :importserup() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

if (flag==0){

            addActionMessage("Records Not Found(s) !!");
            
        }
 return "importmastup";
}

public String importsave() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;


              Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
               stat1=conn.prepareStatement("delete from  m4_bill_add_master where BILL_SL_NO=?");
               stat1.setString(1,MAST_SL_NO);
               stat1.executeUpdate();
               
                
            if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                    
                     stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,AWB_NO,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                        flag=stat1.executeUpdate();
                     
                    
                    }
                
                }
            }
                
            
            }  catch (Exception e) {

                System.out.print("1 file name :importsave() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :importsave() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
            getimport();
 if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
           
        } else {

            addActionMessage("Records Not save(s) !!");
            
        }
    
return "importmastup";
}


public String couriersave() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;


              Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
               stat1=conn.prepareStatement("delete from  m4_bill_add_master where BILL_SL_NO=?");
               stat1.setString(1,MAST_SL_NO);
               stat1.executeUpdate();
               
                
            if(INV_NO!=null && INV_NO.size()>0)
            {
                for(int i=0; i<INV_NO.size(); i++)
                {
                    if(INV_NO.get(i).toString()!=null && INV_NO.get(i).toString().length()>0 )
                    {
                    
                     stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,AWB_NO,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, usrid);
                        flag=stat1.executeUpdate();
                     
                    
                    }
                
                }
            }
                
            
            }  catch (Exception e) {

                System.out.print("1 file name :couriersave() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :couriersave() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
            getimport();
 if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
           
        } else {

            addActionMessage("Records Not save(s) !!");
            
        }
    
return "courierup";
}

public String invsearchmastup() throws Exception {
    
   getinvdtmast();
   
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
try {

              Connection conn = null;
               Connection conn1 = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn1 = new ConnectionShahiHris().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    invlist=new ArrayList();
                   
                  
                    String SSHIPBILLTEMP=" ";
                   
                    
                    if(SSHIPBILL!=null && SSHIPBILL.length()>0)
                    {
                    SSHIPBILLTEMP=SSHIPBILL;
                    }
                   
                    String notinsql="select  INV_NO from m4_bill_add_master";
                    notinsql="1";
                    
                   String tempsearinv=" ";
                 
                  if(SINV!=null && SINV.length()>0)
                  {
                  tempsearinv=" union select excs_inv_no,cost_centre,BUYER from ei_endors_mast where excs_inv_no in("+SINV+")";
                  }
                     
                 
                  if(BOS!=null && BOS.length()>0)
                  {
                  tempsearinv= tempsearinv + " union select a.excs_inv_no,a.cost_centre,a.BUYER from ei_endors_mast a,ei_bos_dtls b where a.excs_inv_no=b.excs_inv_no  and  bos_no  in("+BOS+")";
                  } 
                    
                 /*   stat1=conn.prepareStatement("select distinct a.excs_inv_no,a.cost_centre,a.BUYER from ei_endors_mast a,ei_shipment_dtls b  where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no   and b.shp_bill_no=? "+
                            "  "+
                            tempsearinv +
                            " union select a.excs_inv_no ,a.cost_centre,a.BUYER  from ei_endors_mast a,ei_shipment_dtls b,ei_sbill_master c where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.shp_bill_no=c.shp_bill_no and b.shp_bill_date=c.shp_bill_date and "+
                            " c.lcert_recv_no=? ");
                            */ 
                    //stat1.setString(1, LOCATION_CODE);
                   // stat1.setString(1, SSHIPBILLTEMP);
                   // stat1.setString(3, LOCATION_CODE);
                  //  stat1.setString(2, BOS);
                    //stat1.setString(5, LOCATION_CODE);
                    //stat1.setString(6, SINV);
                  //  stat1.setString(2, lcert_recv_no);
                   // stat1.setString(6, LOCATION_CODE);
                     stat1=conn.prepareStatement("select excs_inv_no,cost_centre,buyer  from ei_endors_mast where nvl(surrender_yn,'N')='N'  and excs_inv_no in ("+SINV+")");
                     result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                       flag=1;
                      
                       String okcusu="";
                     
                        stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and OKCUNO=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString(2);
                         /*
                         stat1=conn.prepareStatement("select PCH from  pch_master where PCH1=? and FLAG='A'");
                         stat1.setString(1,result.getString(2));
                          result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        temppch=result1.getString(1);
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         */
                         
                         
                      invlist.add(new M3BILLBean(result.getString(1), temppch,okcusu));
                    }
                    
                    

            } catch (Exception e) {

                System.out.print("1 file name :invsearchmastup() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :invsearchmastup() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

if (flag==0){

            addActionMessage("Records Not Found(s) !!");
            
        }
 return "invmastup";
}


public String saveinvmast() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;


              Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
               stat1=conn.prepareStatement("delete from  m4_bill_add_master where BILL_SL_NO=?");
               stat1.setString(1,MAST_SL_NO);
               stat1.executeUpdate();
               
                
            if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                    
                     stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
                        result1=stat1.executeQuery();
                        String brkslno="0";
                        if(result1.next())
                        {
                          brkslno=result1.getString(1);
                        }
                         
                        stat1=conn.prepareStatement("insert into m4_bill_add_master(SL_NO,BILL_SL_NO,INV_NO,INV_WEIGHT,TDATE,USER_ID,BUYER,pch) values(?,?,?,?,sysdate,?,?,?)");
                        stat1.setString(1, brkslno);
                        stat1.setString(2, MAST_SL_NO);
                        stat1.setString(3, INV_NO.get(i).toString());
                        stat1.setString(4, INV_WEIGHT.get(i).toString());
                        stat1.setString(5, usrid);
                        stat1.setString(6, INV_BUYER.get(i).toString());
                        stat1.setString(7, INV_PCH.get(i).toString());
                        flag=stat1.executeUpdate();
                     
                    
                    }
                
                }
            }
                
            
            }  catch (Exception e) {

                System.out.print("1 file name :saveinvmast() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
            getinvdtmast();
 if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
           
        } else {

            addActionMessage("Records Not save(s) !!");
            
        }
    
return "invmastup";
}

public String importser() throws Exception {
    
   // getinvdt();
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
try {

              Connection conn = null;
             

            try {
                conn = new ConnectionSeplWeb().getConnection();
              
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    invlist=new ArrayList();
                   if(SSHIPBILLNEWMASTER!=null && SSHIPBILLNEWMASTER.length()>0){
                    stat1=conn.prepareStatement("select ref_no awbl_no  from pi_imp_awbl_mast  where upper(ref_no) like ? union select b.ref_no from pi_imp_boe_mast a, pi_imp_boe_dtls b where a.be_no = b.be_no and upper(a.be_no) like ?");
                    stat1.setString(1, SSHIPBILLNEWMASTER.toUpperCase()+"%");
                    stat1.setString(2, SSHIPBILLNEWMASTER.toUpperCase()+"%");
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                       flag=1;
                       invlist.add(new M3BILLBean(result.getString(1)));
                    }
                   }
                    

            } catch (Exception e) {

                System.out.print("1 file name : M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :importser() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

if (flag==0){

            addActionMessage("Records Not Found(s) !!");
            
        }
return "importmast";
}

public String invsearchmast() throws Exception {
    
   // getinvdt();
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
try {

              Connection conn = null;
              Connection conn1 = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               conn1 = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    invlist=new ArrayList();
                   
                  
                    String SSHIPBILLTEMP=" ";
                   
                    
                    if(SSHIPBILLNEWMASTER!=null && SSHIPBILLNEWMASTER.length()>0)
                    {
                    SSHIPBILLTEMP=SSHIPBILLNEWMASTER;
                    }
                   
                    //String notinsql="select distinct INV_NO from m4_bill_add_detail a ,m4_bill_detail b where a.BILL_SL_NO=b.BILL_SL_NO and a.BILL_DT_SL_NO  =b.SL_NO and CC_CODE='"+INV_CC_CODE+"' and TYPE_SL_NO='"+INV_TYPE_SL_NO+"' and SUB_TYPE_SL_NO='"+INV_SUB_TYPE_SL_NO+"' and PRODUCT_SL_NO='"+INV_PRODUCT_SL_NO+"' and PCH='"+PCH+"'";
                   String notinsql="select  INV_NO from m4_bill_add_master";
                   notinsql="1";
                  String tempsearinv=" ";
                 
                  if(SINVNEWMASTER!=null && SINVNEWMASTER.length()>0)
                  {
                  tempsearinv=" union select excs_inv_no,cost_centre,BUYER from ei_endors_mast where   excs_inv_no in("+SINVNEWMASTER+")";
                  }
                   if(BOSNEWMASTER!=null && BOSNEWMASTER.length()>0)
                  {
                  tempsearinv=tempsearinv +  " union select a.excs_inv_no,a.cost_centre,a.BUYER from ei_endors_mast a,ei_bos_dtls b where a.excs_inv_no=b.excs_inv_no  and  bos_no in("+BOSNEWMASTER+")";
                  }
                 
                    stat1=conn.prepareStatement("select distinct a.excs_inv_no,a.cost_centre,a.BUYER from ei_endors_mast a,ei_shipment_dtls b  where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and  b.shp_bill_no=? "+
                            " "+
                            tempsearinv +
                           " union select a.excs_inv_no ,a.cost_centre,a.BUYER  from ei_endors_mast a,ei_shipment_dtls b,ei_sbill_master c where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.shp_bill_no=c.shp_bill_no and b.shp_bill_date=c.shp_bill_date and "+
                            " c.lcert_recv_no=? ");
                    //stat1.setString(1, LOCATION_CODE);
                    stat1.setString(1, SSHIPBILLTEMP);
                   // stat1.setString(3, LOCATION_CODE);
                  //  stat1.setString(4, BOSNEWMASTER);
                    //stat1.setString(5, LOCATION_CODE);
                   // stat1.setString(6, SINVNEWMASTER);
                     stat1.setString(2, lcert_recv_no);
                   // stat1.setString(6, LOCATION_CODE);
                    result=stat1.executeQuery();
                    
                    // CRS610MI MI=new CRS610MI();   
                    // MI.connect();
                    while(result.next())
                    {
                       flag=1;
                       String okcusu="";
                     
                        stat1=conn1.prepareStatement("select okcusu from prodbi.ocusma where OKCONO=111 and OKCUNO=?");
                        stat1.setString(1,result.getString("BUYER"));
                        result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        okcusu=result1.getString(1);
                        
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         String temppch=result.getString(2);
                         /*
                         stat1=conn.prepareStatement("select PCH from  bill_pch_master where PCH=?");
                         stat1.setString(1,result.getString(2));
                          result1=stat1.executeQuery();
                        if(result1.next())
                        {
                        temppch=result1.getString(1);
                        }
                        if(result1!=null)
                        {
                        result1.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         */
                      invlist.add(new M3BILLBean(result.getString(1), temppch,okcusu));
                    }
                   // MI.destroyMI();
                    

            } catch (Exception e) {

                System.out.print("1 file name :invsearchmast() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :invsearchmast() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

if (flag==0){

            addActionMessage("Records Not Found(s) !!");
            
        }
return "invpagemast";
}



public String invsearch() throws Exception {
    
    getinvdt();
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
try {

              Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    invlist=new ArrayList();
                   
                    String SSHIPDATETEMP="10/10/1001";
                    String SSHIPBILLTEMP=" ";
                   
                    if(SSHIPDATE!=null && SSHIPDATE.length()>0)
                    {
                    SSHIPDATETEMP=SSHIPDATE;
                    }
                    
                    if(SSHIPBILL!=null && SSHIPBILL.length()>0)
                    {
                    SSHIPBILLTEMP=SSHIPBILL;
                    }
                   
                    String notinsql="select distinct INV_NO from m4_bill_add_detail a ,m4_bill_detail b where a.BILL_SL_NO=b.BILL_SL_NO and a.BILL_DT_SL_NO  =b.SL_NO and CC_CODE='"+INV_CC_CODE+"' and TYPE_SL_NO='"+INV_TYPE_SL_NO+"' and SUB_TYPE_SL_NO='"+INV_SUB_TYPE_SL_NO+"' and PRODUCT_SL_NO='"+INV_PRODUCT_SL_NO+"' and PCH='"+PCH+"'";
                    
                    
                    stat1=conn.prepareStatement("select distinct a.excs_inv_no,a.cost_centre from ei_endors_mast a,ei_shipment_dtls b  where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.location=? and cost_centre=? and b.shp_bill_no=? and a.excs_inv_no not in( "+notinsql+")"+
                            "union select a.excs_inv_no,a.cost_centre from ei_endors_mast a,ei_bos_dtls b where a.excs_inv_no=b.excs_inv_no and location=? and cost_centre=? and bos_no=? and a.excs_inv_no not in( "+notinsql+")"+
                            " union select excs_inv_no,cost_centre from ei_endors_mast where location=? and cost_centre=? and excs_inv_no=? and excs_inv_no not in( "+notinsql+")");
                         
                    
                    stat1.setString(1, LOCATION_CODE);
                    stat1.setString(2, PCH);
                    stat1.setString(3, SSHIPBILLTEMP);
                   // stat1.setString(4, SSHIPDATETEMP);
                    
                    stat1.setString(4, LOCATION_CODE);
                    stat1.setString(5, PCH);
                    stat1.setString(6, BOS);
                    
                    stat1.setString(7, LOCATION_CODE);
                    stat1.setString(8, PCH);
                    stat1.setString(9, SINV);
                                       
                    result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                       flag=1;
                      
                      invlist.add(new M3BILLBean(result.getString(1), result.getString(2)));
                    }
                    
                    

            } catch (Exception e) {

                System.out.print("1 file name :invsearch()  M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :invsearch()  M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

if (flag==0){

            addActionMessage("Records Not Found(s) !!");
            
        }
return "invlistpage";
}


public String fablr(){
	Map session = ActionContext.getContext().getSession();
    String LOCATION_CODE = (String) session.get("sessLocationCode");
    String usrid = (String) session.get("sessUserId");
    int flag=0;
    Connection conn = null;
    fablrlist = new ArrayList<M3lrbean>();
    fablruomlist = new ArrayList();
    try {
        conn = new ConnectionSeplWeb().getConnection();
       
       
    } catch (Exception e) {
        System.out.println(e.toString());
    } // end catch


    PreparedStatement stat = null;
    PreparedStatement stat1 = null;
    PreparedStatement stat2 = null;
    
    ResultSet result = null;
    ResultSet result1 = null;
    ResultSet result2 = null;
    

    try {
    	 stat=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
    	 stat.setString(1, MAST_SL_NO);
         result=stat.executeQuery();
         if(result.next())
         {
        FORWARD_DATE=result.getString(1);
        }
        
        stat1=conn.prepareStatement("select * from  m4_bill_add_master where BILL_SL_NO=? order by 1");
        stat1.setString(1, MAST_SL_NO);
        result1=stat1.executeQuery();
        while(result1.next())
        {
        	M3lrbean m3lrbean = new M3lrbean();
        	m3lrbean.setSL_NO(result1.getString("SL_NO"));
        	m3lrbean.setBILL_SL_NO(result1.getString("BILL_SL_NO"));
        	m3lrbean.setLR_NO(result1.getString("LR_NO"));
        	m3lrbean.setLR_WEIGHT(result1.getString("LR_WEIGHT"));
        	m3lrbean.setLR_WEIGHT_UOM(result1.getString("LR_WEIGHT_UOM"));
        	m3lrbean.setLR_QTY(result1.getString("LR_QTY"));
        	m3lrbean.setLR_MATERIAL_TYPE(result1.getString("LR_MATERIAL_TYPE"));
        	fablrlist.add(m3lrbean);
        
        }
        
        stat2=conn.prepareStatement("select UOM,UOM_DESC from FABSRCUOM order by UOM");
        result2=stat2.executeQuery();
        while(result2.next())
        {
        	fablruomlist.add(result2.getString("UOM"));
        }
     }  catch (Exception e) {

        System.out.print("1 file name : fablr() M3billAction.java" + e);

        System.out.println(e.toString());
    } finally {

        try {
        	if (result2 != null) {
                result2.close();
            }

            if (result1 != null) {
                result1.close();
            }
             if (result != null) {
                result.close();
            }
             
             if (stat != null) {
                 stat.close();
             }

            if (stat1 != null) {
                stat1.close();
            }
            
            if (stat2 != null) {
                stat2.close();
            }

            if (conn != null) {
                conn.close();
            }
           

            result1 = null;
            stat1 = null;
            conn = null;

        } catch (Exception e) {
          
            System.out.print("File Name : fablr() M3billAction.java Exception in finally block");
            e.printStackTrace();
        }
    }
	return "fablr";
}

public String savefablr(){
	Map session = ActionContext.getContext().getSession();
    String LOCATION_CODE = (String) session.get("sessLocationCode");
    String usrid = (String) session.get("sessUserId");
    if(lrdtchk!=null && lrdtchk.size()>0){
    	Connection conn=null;
		try {
	        conn = new ConnectionSeplWeb().getConnection();
	    } catch (Exception e) {
	        System.out.println(e.toString());
	    } // end catch
		PreparedStatement stat = null;
		try{
			for(int i=0;i<lrdtchk.size();i++){
				if(lrdtchk.get(i)!=null && lrdtchk.get(i).toString().length()>0){
					stat = conn.prepareStatement("DELETE FROM m4_bill_add_master where SL_NO=?");
					stat.setString(1, lrdtchk.get(i).toString());
					stat.executeUpdate();
					if(stat!=null){
						stat.close();
					}
				}
			}
		} 
		catch (Exception e) {

	        System.out.print("1 file name : savefablr() M3billAction.java" + e);

	        System.out.println(e.toString());
	    } finally {

	        try {
	        	if (conn != null) {
	                conn.close();
	            }
	        	if (stat != null) {
	                 stat.close();
	             }
	            
	        } catch (Exception e) {
	            
	            System.out.print("File Name :savefablr() M3billAction.java Exception in finally block");
	            e.printStackTrace();
	        }
		
	    }
    }
	if(LR_NO!=null && LR_NO.size()>0){
		Connection conn=null;
		try {
	        conn = new ConnectionSeplWeb().getConnection();
	    } catch (Exception e) {
	        System.out.println(e.toString());
	    } // end catch
		PreparedStatement stat = null;
		ResultSet result = null;
		PreparedStatement stat1 = null;
		ResultSet result1 = null;
		PreparedStatement stat2 = null;
		try{
			for(int i=0;i<LR_NO.size();i++){
				if(LR_NO.get(i)!=null && LR_NO.get(i).toString().length()>0){
					stat = conn.prepareStatement("SELECT * FROM m4_bill_add_master where BILL_SL_NO=? and LR_NO=?");
					stat.setString(1, MAST_SL_NO);
					stat.setString(2, LR_NO.get(i).toUpperCase());
					result = stat.executeQuery();
					if(result.next()){
						addActionError("LR No "+LR_NO.get(i).toUpperCase() +" is already added");
					}					
					else{
						stat1=conn.prepareStatement("select m4_bill_add_master_Sq.nextval from dual");
	                    result1=stat1.executeQuery();
	                    String brkslno="0";
	                    if(result1.next())
	                    {
	                      brkslno=result1.getString(1);
	                    }
	                    if(stat1!=null){
							stat1.close();
						}
						if(result1!=null){
							result1.close();
						}
						
	                    stat2 = conn.prepareStatement("INSERT INTO m4_bill_add_master (SL_NO,BILL_SL_NO,TDATE,USER_ID,LR_NO,LR_WEIGHT,LR_WEIGHT_UOM,LR_QTY,LR_MATERIAL_TYPE) values(?,?,sysdate,?,?,?,?,?,?)");
	                    stat2.setString(1, brkslno);
	                    stat2.setString(2, MAST_SL_NO);
	                    stat2.setString(3, usrid);
	                    stat2.setString(4, LR_NO.get(i).toUpperCase());
	                    stat2.setString(5, LR_WEIGHT.get(i).toUpperCase());
	                    stat2.setString(6, LR_WEIGHT_UOM.get(i).toUpperCase());
	                    stat2.setString(7, LR_QTY.get(i).toUpperCase());
	                    stat2.setString(8, LR_MATERIAL_TYPE.get(i).toUpperCase());
	                    stat2.executeUpdate();
	                    if(stat2!=null){
							stat2.close();
						}
					}
					if(stat!=null){
						stat.close();
					}
					if(result!=null){
						result.close();
					}
				}
			}
		}  catch (Exception e) {

	        System.out.print("1 file name :savefablr() M3billAction.java" + e);

	        System.out.println(e.toString());
	    } finally {

	        try {
	        	if (conn != null) {
	                conn.close();
	            }
	        	if (stat != null) {
	                 stat.close();
	             }

	            if (stat1 != null) {
	                stat1.close();
	            }
	            if (result != null) {
	                result.close();
	            }
	        } catch (Exception e) {
	            
	            System.out.print("File Name :savefablr() M3billAction.java Exception in finally block");
	            e.printStackTrace();
	        }
	    }
	}
	LR_NO=null;
	LR_WEIGHT=null;
	LR_WEIGHT_UOM=null;
	LR_QTY=null;
	LR_MATERIAL_TYPE=null;
	lrdtchk=null;
	fablr();
	return "fablr";
}

public String saveinv() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;


              Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
               stat1=conn.prepareStatement("delete from  m4_bill_add_detail where BILL_SL_NO=? and BILL_DT_SL_NO=?");
               stat1.setString(1,BILL_SL_NO);
               stat1.setString(2,BILL_DT_SL_NO);
               stat1.executeUpdate();
               
                
            if(INV_SAVE!=null && INV_SAVE.size()>0)
            {
                for(int i=0; i<INV_SAVE.size(); i++)
                {
                    if(INV_SAVE.get(i).toString()!=null && INV_SAVE.get(i).toString().length()>0 &&  INV_SAVE.get(i).toString().equals("Yes") )
                    {
                        String slno=null;
                    stat1=conn.prepareStatement("select m4_bill_add_detail_Sq.nextval from dual");
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                      slno=result1.getString(1);
                    }
                     stat1=conn.prepareStatement("insert into m4_bill_add_detail(SL_NO,BILL_SL_NO,BILL_DT_SL_NO,INV_NO,INV_WEIGHT,TDATE,USER_ID) values(?,?,?,?,?,sysdate,?)");
                     stat1.setString(1,slno); 
                     stat1.setString(2,BILL_SL_NO);
                     stat1.setString(3,BILL_DT_SL_NO);
                     stat1.setString(4,INV_NO.get(i).toString());
                     stat1.setString(5,INV_WEIGHT.get(i).toString());
                     stat1.setString(6,usrid);
                     flag=stat1.executeUpdate();
                     
                    
                    }
                
                }
            }
                
            
            }  catch (Exception e) {

                System.out.print("1 file name :saveinv() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :saveinv() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
            getinvdt();
 if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
           
        } else {

            addActionMessage("Records Not save(s) !!");
            
        }
    
return "invlistpage";
}


public void getinvdt()
{
 Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                 stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                 stat1.setString(1, BILL_SL_NO);
                  result=stat1.executeQuery();
                 if(result.next())
                {
                FORWARD_DATE=result.getString(1);
                }
                
                stat1=conn.prepareStatement("select * from  m4_bill_add_detail where BILL_SL_NO=? and BILL_DT_SL_NO=? order by 1");
                stat1.setString(1, BILL_SL_NO);
                stat1.setString(2, BILL_DT_SL_NO);
                result=stat1.executeQuery();
                while(result.next())
                {
                invsavelist.add(new M3BILLBean(result.getString("INV_NO"), result.getString("INV_WEIGHT")));
                
                }
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : getinvdt() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : getinvdt() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }

       

}

public void getinvdtnew()
{
 Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                if(INVSLTXT!=null && INVSLTXT.length()>0 && BILL_DT_SL_NONEW!=null && BILL_DT_SL_NONEW.size()>0)
                {
                
                
                stat1=conn.prepareStatement("select * from  m4_bill_add_detail where BILL_SL_NO=? and BILL_DT_SL_NO=? order by 1");
                stat1.setString(1, MAST_SL_NO);
                stat1.setString(2, BILL_DT_SL_NONEW.get(Integer.parseInt(INVSLTXT)).toString());
                result=stat1.executeQuery();
                while(result.next())
                {
                    
                invsavelist.add(new M3BILLBean(result.getString("INV_NO"), result.getString("INV_WEIGHT")));
                
                }
                }
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name :getinvdtnew() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :getinvdtnew() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }

       

}


public String invnewnew() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
        getinvdtnew();
    return "invlistpagenew";    
}



public String invsearchnew() throws Exception {
    
    getinvdtnew();
       Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        int flag=0;
try {

              Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    invlist=new ArrayList();
                   
                    String SSHIPDATETEMP="10/10/1001";
                    String SSHIPBILLTEMP=" ";
                   
                    if(INVSLTXT!=null && INVSLTXT.length()>0)
                    {
                    if(SSHIPBILLNEW!=null && SSHIPBILLNEW.size()>0)
                    {
                    SSHIPBILLTEMP=SSHIPBILLNEW.get(Integer.parseInt(INVSLTXT)).toString();
                    }
                   String notinsql="1";
                   if(PRODUCT_SL_NO!=null && PRODUCT_SL_NO.size()>0){
                   notinsql="select distinct INV_NO from m4_bill_add_detail a ,m4_bill_detail b where a.BILL_SL_NO=b.BILL_SL_NO and a.BILL_DT_SL_NO  =b.SL_NO and CC_CODE='"+CC_CODE+"' and TYPE_SL_NO='"+TYPE_SL_NO+"' and SUB_TYPE_SL_NO='"+SUB_TYPE_SL_NO+"' and PRODUCT_SL_NO='"+PRODUCT_SL_NO.get(Integer.parseInt(INVSLTXT)).toString()+"' and PCH='"+PCH+"'";
                   }
                   if(UP_PRODUCT_SL_NO!=null && UP_PRODUCT_SL_NO.size()>0){
                   notinsql="select distinct INV_NO from m4_bill_add_detail a ,m4_bill_detail b where a.BILL_SL_NO=b.BILL_SL_NO and a.BILL_DT_SL_NO  =b.SL_NO and CC_CODE='"+CC_CODE+"' and TYPE_SL_NO='"+TYPE_SL_NO+"' and SUB_TYPE_SL_NO='"+SUB_TYPE_SL_NO+"' and PRODUCT_SL_NO='"+UP_PRODUCT_SL_NO.get(Integer.parseInt(INVSLTXT)).toString()+"' and PCH='"+PCH+"'";
                   }
                   // notinsql="11";
                   
                    stat1=conn.prepareStatement("select distinct a.excs_inv_no,a.cost_centre from ei_endors_mast a,ei_shipment_dtls b  where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.location=? and cost_centre=? and b.shp_bill_no=? and a.excs_inv_no not in( "+notinsql+")"+
                            "union select a.excs_inv_no,a.cost_centre from ei_endors_mast a,ei_bos_dtls b where a.excs_inv_no=b.excs_inv_no and location=? and cost_centre=? and bos_no=? and a.excs_inv_no not in( "+notinsql+")"+
                            " union select excs_inv_no,cost_centre from ei_endors_mast where location=? and cost_centre=? and excs_inv_no=? and excs_inv_no not in( "+notinsql+")");
                    
                    stat1.setString(1, LOCATION_CODE);
                    stat1.setString(2, PCH);
                    stat1.setString(3, SSHIPBILLTEMP);
                   // stat1.setString(4, SSHIPDATETEMP);
                    
                    stat1.setString(4, LOCATION_CODE);
                    stat1.setString(5, PCH);
                    stat1.setString(6, BOSNEW.get(Integer.parseInt(INVSLTXT)).toString());
                    
                    stat1.setString(7, LOCATION_CODE);
                    stat1.setString(8, PCH);
                    stat1.setString(9, SINVNEW.get(Integer.parseInt(INVSLTXT)).toString());
                                       
                    result=stat1.executeQuery();
                    
                    while(result.next())
                    {
                       flag=1;
                      
                      invlist.add(new M3BILLBean(result.getString(1), result.getString(2)));
                    }
                    
                  }

            } catch (Exception e) {

                System.out.print("1 file name : invsearchnew()  M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :invsearchnew()  M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

if (flag==0){

            addActionMessage("Records Not Found(s) !!");
            
        }
return "invlistpagenew";
}


public String  getsertexdt()  throws Exception 
{
 Connection conn = null;
             Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
 if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return "srvtexpage";
        }
            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                String sqlstr="";
                
               
                if(REVERSE_SRVTAX_CODE!=null && REVERSE_SRVTAX_CODE.length()>0)
                {
                  
                
                  sqlstr=" and SRV_CODE="+REVERSE_SRVTAX_CODE;
                  if(BILL_DATE!=null && BILL_DATE.length()>8){
                   stat1=conn.prepareStatement("select GL_CODE,GL_DESC,GL_PRCT from M4_BILL_SRV_TAX_MASTER where location=? "+sqlstr+" and  to_date(?,'dd/mm/yyyy') BETWEEN DATE_FROM AND DATE_TO order by 1");
                  stat1.setString(1,LOCATION_CODE);
                  stat1.setString(2,BILL_DATE);
                  result=stat1.executeQuery();
                   while(result.next())
                    {
                    srvtaxmasterlist.add(new M3BILLBean(result.getString("GL_CODE"), result.getString("GL_DESC"), result.getString("GL_PRCT")));
                     REVERSE_SRVTAX_RATE=result.getString("GL_PRCT");
                    }
                  }else{
                  stat1=conn.prepareStatement("select GL_CODE,GL_DESC,GL_PRCT from M4_BILL_SRV_TAX_MASTER where location=? "+sqlstr+" and  (select BILL_DATE  from M4_BILL_MASTER WHERE SL_NO=?) BETWEEN DATE_FROM AND DATE_TO order by 1");
                  stat1.setString(1,LOCATION_CODE);
                  stat1.setString(2,MAST_SL_NO);
                  result=stat1.executeQuery();
                   while(result.next())
                    {
                    srvtaxmasterlist.add(new M3BILLBean(result.getString("GL_CODE"), result.getString("GL_DESC"), result.getString("GL_PRCT")));
                    REVERSE_SRVTAX_RATE=result.getString("GL_PRCT");
                    }
                  }
                    if(srvtaxmasterlist.size()==0)
                    {
                   // stat1=conn.prepareStatement("select GL_CODE,GL_DESC,GL_PRCT from M4_BILL_SRV_TAX_MASTER order by 1");
                    //result=stat1.executeQuery();
                   // while(result.next())
                   // {
                   // srvtaxmasterlist.add(new M3BILLBean(result.getString("GL_CODE"), result.getString("GL_DESC"), result.getString("GL_PRCT")));

                   // }
                    }
                }
                
                
                
                
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : srvtexpage M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : srvtexpage M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }

    return "srvtexpage";   

}



public String  getgstdt()  throws Exception 
{
          Connection conn = null;
           Connection connbi = null;
    Map session = ActionContext.getContext().getSession();
    String LOCATION_CODE = (String) session.get("sessLocationCode");
    String usrid = (String) session.get("sessUserId");
    if (usrid == null) {
        addActionMessage("Session Not Valid !!");
        return "srvtexpage";
    }
    try {
        conn = new ConnectionSeplWeb().getConnection();
        connbi = new connectiondb2().getConnection();


    } catch (Exception e) {
        System.out.println(e.toString());
    } // end catch

 
    PreparedStatement stat1 = null;
    ResultSet result1 = null;
    ResultSet result = null;
    setbrktotal=0;
    try {

        String sqlstr = "";

        gstlist = new ArrayList();
       
       Map<String,String> savemap=new HashMap<String,String>();
       
       if(HSN_CODE!=null && HSN_CODE.length()>0)
       {
       stat1=connbi.prepareStatement("select * from m3fdbprd.csycsn where ckcsno=?");
       stat1.setString(1, HSN_CODE);
       result1=stat1.executeQuery();
       if(result1.next())
                {
                  HSNCODEFLAG="Yes";  
                }
       }
      double  BREAK_AMOUNT_NONGST=0;
       if(BREAK_AMOUNT_NON_GST!=null && BREAK_AMOUNT_NON_GST.length()>0 && Double.parseDouble(BREAK_AMOUNT_NON_GST)>0)
       {
       BREAK_AMOUNT_NONGST=Double.parseDouble(BREAK_AMOUNT_NON_GST);
       }
       
       
        if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
        {
                 stat1=conn.prepareStatement("select BILL_AMOUNT,HSN_CODE,SAECAR,BILL_WHLO,SACSCD from m4_bill_master where sl_no=?");
                 stat1.setString(1, MAST_SL_NO);
                result1=stat1.executeQuery();
                if(result1.next())
                {
                
                 BILL_WHLO=result1.getString("BILL_WHLO");
                 ECAR=result1.getString("SAECAR");
                 CSCD=result1.getString("SACSCD");
                if(SAVEDATA!=null && SAVEDATA.equals("Yes"))
                {
                 HSN_CODE=result1.getString("HSN_CODE");
                 
                }
                
                }
               
             if(SAVEDATA!=null && SAVEDATA.equals("Yes"))
                {
                          stat1 = conn.prepareStatement("select HSN_CODE,GL_CODE from M4_BILL_HSN_GL where flag='Y' and REV_GL_CODE is null");
                           result = stat1.executeQuery();
                           while (result.next()) 
                           {
                              HSNDESC.put(result.getString("GL_CODE"),result.getString("HSN_CODE")) ;
                           }   
                    
                stat1=conn.prepareStatement("select   a.SL_NO,a.COST_ELEMENT,a.AMT,a.FORM_TYPE,a.FORM_WHLO,a.BILL_SL_NO,GST_PER,TAX_CODE from M4_BILL_AMT_DETAIL a,M4_BILL_HSN_GL b where a.COST_ELEMENT=b.GL_CODE and  a.BILL_SL_NO=? and   b.REV_GL_CODE is null  order by a.SL_NO");
                stat1.setString(1, MAST_SL_NO);
               result1=stat1.executeQuery();
                while(result1.next())
                {
                    
                        GetTaxRateBean bn1 = new GetTaxRateBean();
                        bn1.setTAX_CODE(result1.getString("TAX_CODE"));
                        bn1.setTAXC(HSNDESC.get(result1.getString("COST_ELEMENT")));
                        bn1.setTAX1(result1.getString("GST_PER"));
                        bn1.setTAX2(result1.getString("COST_ELEMENT"));
                        bn1.setCALVAL(result1.getString("AMT"));
                        setbrktotal=setbrktotal+result1.getDouble("AMT");
                        gstlist.add(bn1);
                       }
                }
        
        }
       
      
        System.out.println(HSN_CODE+":::"+SAVEDATA +"::CSCD"+CSCD);
        System.out.println(BILL_WHLO);
        System.out.println(ECAR);
        
        
        if (HSN_CODE != null && HSN_CODE.length() > 0 && SAVEDATA==null) {

            Map<String, String> mpgl = new HashMap<String, String>();
            stat1 = conn.prepareStatement("select HSN_CODE,GL_CODE from M4_BILL_HSN_GL where flag='Y'  and REV_GL_CODE is null ");
            result = stat1.executeQuery();
            while (result.next()) {
                mpgl.put(result.getString("HSN_CODE"), result.getString("GL_CODE"));
            }

            
            //stat1=conn.prepareStatement("select * from seplvportal.VENDOR_TAX_REGISTRATION where GST_NUMBER is not null and VENDOR_ID in (select id from seplvportal.vendors where code='111')");
            String M4DIVI = null;
            String M4STAT = null;
            String M4GEOC = null;

            stat1 = conn.prepareStatement("select M4DIVI,M4STAT,M4GEOC from M4_WHLO_MASTER where M4CONO='111' and M4WHLO=? and M4GEOC is not null");
            stat1.setString(1, BILL_WHLO);
            result = stat1.executeQuery();
            while (result.next()) {
                M4DIVI = result.getString("M4DIVI");
                M4STAT = result.getString("M4STAT");
                M4GEOC = result.getString("M4GEOC");

            }

            SimpleDateFormat fn = new SimpleDateFormat("yyyyMMdd");

            if (M4STAT != null && M4STAT.length() > 0) {
                TXZ050MI MI = new TXZ050MI();
                MI.connect();//2/3
                 TXZ050MIGetTaxRateBean bn =new  TXZ050MIGetTaxRateBean();
                if(CSCD!=null && CSCD.trim().equals("IN"))
                {
                 bn = MI.GetTaxRate("111", M4DIVI, M4GEOC, "2", "5", ECAR, M4STAT, HSN_CODE.toUpperCase(), "", fn.format(new Date()));
                }else{
                 bn = MI.GetTaxRate("111", M4DIVI, M4GEOC, "2", "3", "IMP", HSN_CODE.toUpperCase(),"" , "", fn.format(new Date()));
               
                }
                MI.destroyMI();
                MI = null;
                System.out.println(M4DIVI + ":::" + M4GEOC + ":::" + ECAR + ":::" + M4STAT + ":::" + HSN_CODE);
                if (bn != null && bn.getTAXC() != null && bn.getTAXC().length() > 0) {


                    if (bn.getTAX1() != null && bn.getTAX1().length() > 0 && Double.parseDouble(bn.getTAX1()) > 0) {

                        
                        GetTaxRateBean bn1 = new GetTaxRateBean();
                        bn1.setTAX_CODE(bn.getTAXC());
                        bn1.setTAXC("SGST");
                        bn1.setTAX1(bn.getTAX1());
                        bn1.setTAX2(mpgl.get("SGST"));
                        if (BREAK_AMOUNT != null && BREAK_AMOUNT.size() > 0 && BREAK_AMOUNT.get(0).toString() != null) {
                            double dt = Double.parseDouble(bn.getTAX1()) * 0.01 * Double.parseDouble(BREAK_AMOUNT.get(0).toString());
                            bn1.setCALVAL(Math.round(dt * 100.0) / 100.0 + "");
                            setbrktotal=setbrktotal+dt;
                        }
                        gstlist.add(bn1);
                    }
                    if (bn.getTAX2() != null && bn.getTAX2().length() > 0 && Double.parseDouble(bn.getTAX2()) > 0) {
                        GetTaxRateBean bn1 = new GetTaxRateBean();
                        bn1.setTAX_CODE(bn.getTAXC());
                        bn1.setTAXC("CGST");
                        bn1.setTAX2(mpgl.get("CGST"));
                        bn1.setTAX1(bn.getTAX2());
                        if (BREAK_AMOUNT != null && BREAK_AMOUNT.size() > 0 && BREAK_AMOUNT.get(0).toString() != null) {
                           double dt = Double.parseDouble(bn.getTAX2()) * 0.01 * Double.parseDouble(BREAK_AMOUNT.get(0).toString());
                            bn1.setCALVAL(Math.round(dt * 100.0) / 100.0 + "");
                            setbrktotal=setbrktotal+dt;
                        }
                        gstlist.add(bn1);
                    }
                    if (bn.getTAX3() != null && bn.getTAX3().length() > 0 && Double.parseDouble(bn.getTAX3()) > 0) {
                        GetTaxRateBean bn1 = new GetTaxRateBean();
                        bn1.setTAX_CODE(bn.getTAXC());
                        bn1.setTAXC("IGST");
                        bn1.setTAX2(mpgl.get("IGST"));
                        bn1.setTAX1(bn.getTAX3());
                        if (BREAK_AMOUNT != null && BREAK_AMOUNT.size() > 0 && BREAK_AMOUNT.get(0).toString() != null) {
                           double dt = Double.parseDouble(bn.getTAX3()) * 0.01 * Double.parseDouble(BREAK_AMOUNT.get(0).toString());
                           bn1.setCALVAL(Math.round(dt * 100.0) / 100.0 + "");
                        setbrktotal=setbrktotal+dt;
                       
                    }
                         gstlist.add(bn1);
                }
            }
            
                
        }
        }
      
       if(BREAK_AMOUNT!=null && BREAK_AMOUNT.size()>0)
       {
            for(int i=0; i<BREAK_AMOUNT.size(); i++)
            {
                if(BREAK_AMOUNT.get(i).toString()!=null && BREAK_AMOUNT.get(i).toString().length()>0 
                        && Double.parseDouble(BREAK_AMOUNT.get(i).toString())>0)
                {
                setbrktotal=setbrktotal+Double.parseDouble(BREAK_AMOUNT.get(i).toString());
                }
            }
            System.out.println(BILL_AMOUNT);
             System.out.println(setbrktotal);
             if(BILL_AMOUNT!=null && BILL_AMOUNT.length()>0)
             { }else{
             BILL_AMOUNT=tempbillamount;
             }
                 double diffval=Double.parseDouble(BILL_AMOUNT)-Math.round((setbrktotal+BREAK_AMOUNT_NONGST) * 100.0) / 100.0 ;
                if(Double.parseDouble(BILL_AMOUNT)==Math.round(setbrktotal * 100.0) / 100.0)
                {}else{
                DIFF_AMT=Math.round(diffval * 100.0) / 100.0+"";
                }
                
                if(DIFF_AMT==null)
                {
                DIFF_AMT="0";
                }
                
            
       }
               
        //invsavelist
    } catch (Exception e) {

        System.out.print("1 file name : gstlist M3billAction.java" + e);

        System.out.println(e.toString());
    } finally {

        try {

            if (result1 != null) {
                result1.close();
            }
            if (result != null) {
                result.close();
            }


            if (stat1 != null) {
                stat1.close();
            }

            if (conn != null) {
                conn.close();
            }
             if (connbi != null) {
                connbi.close();
            }
            


            result1 = null;
            stat1 = null;
            conn = null;

        } catch (Exception e) {

            System.out.print("File Name : srvtexpage M3billAction.java Exception in finally block");
            e.printStackTrace();
        }
    }

    return "gstpage";   

}

     public String exeview() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return "accpage";
        }

        try {

            Connection conn = null;
            Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
                 connbi = new ConnectionShahiHris().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            ResultSet result3=null;

            try {
                
               
                
                
                
                
                
                String sqlstr="";
                
                 if(STATUS!=null && STATUS.equals("U"))
                {
                sqlstr=sqlstr+" and FORWARD_DATE is  null and REPORT_NO is  null";
                }
                  if(STATUS!=null && STATUS.equals("W"))
                {
                sqlstr=sqlstr+" and FORWARD_DATE is not null ";
                }
                if(STATUS!=null && STATUS.equals("F"))
                {
                sqlstr=sqlstr+" and FORWARD_DATE is not null and REPORT_NO is not null  and REC_ACC_DATE is  null";
                }
                if(STATUS!=null && STATUS.equals("P"))
                {
                sqlstr=sqlstr+" and ACCOUNT_DATE is not null";
                }
                
                if(STATUS!=null && STATUS.equals("L"))
                {
                sqlstr=sqlstr+" and REC_ACC_DATE is not null and ACCOUNT_DATE is  null";
                }
                
                
                if(STATUS!=null && STATUS.equals("R"))
                {
                sqlstr=sqlstr+" and REC_ACC_DATE is not null";
                }
                if(STATUS!=null && STATUS.equals("A"))
                {
                sqlstr=sqlstr+"  ";
                }
                if(S_DEPT_DESC!=null && S_DEPT_DESC.length()>0)
                {
                 
                  sqlstr=sqlstr+" and upper(b.DEPT_DESC) like '"+S_DEPT_DESC.toUpperCase()+"%'";
               
                }
                if(S_SUPPLIER!=null && S_SUPPLIER.length()>0)
                {
                 
                   sqlstr=sqlstr+" and SUPPLIER_CODE ='"+S_SUPPLIER.toUpperCase()+"'";
               
                }
                if(S_Bill_NO!=null && S_Bill_NO.length()>0)
                {
                 
                  sqlstr=sqlstr+" and Bill_NO  like '"+S_Bill_NO.toUpperCase()+"%'";
               
                }
                
                 if(S_CONTROL!=null && S_CONTROL.length()>0)
                {
                 
                  sqlstr=sqlstr+" and REPORT_NO ='"+S_CONTROL+"'";
               
                }
                 
                
                 String tempfrom=null;
                 String tempto=null;
                if(S_Bill_FROM!=null && S_Bill_FROM.length()>0)
                {
                 tempfrom=S_Bill_FROM.substring(0,10);
                  
               
                }
                if(S_Bill_TO!=null && S_Bill_TO.length()>0)
                {
                  tempto=S_Bill_TO.substring(0,10);
                  
               
                }
                
                if(tempfrom!=null && tempto!=null )
                {
                sqlstr=sqlstr +" and BILL_DATE between to_date('"+tempfrom+"','yyyy-mm-dd') and to_date('"+tempto+"','yyyy-mm-dd')";
                }
                
                ArrayList temparr=new ArrayList();
                 if(sqlstr!=null && sqlstr.trim().length()>0)
                {
               stat1=conn.prepareStatement("select WAREHOUSE from m4_bill_user_master where EMP_CODE=? and flag='Y'");
               stat1.setString(1,usrid);
               result3=stat1.executeQuery();
               if(result3.next())
               {
               
                stat1=conn.prepareStatement("select a.SL_NO,DEPT_SL_NO,BILL_NO,to_char(BILL_DATE,'dd/mm/yyyy') BILL_DATE,SUPPLIER_CODE,BILL_AMOUNT,GROSS_AMOUNT,b.DEPT_DESC dept_desc,IDSUNM ,to_char(a.FORWARD_DATE,'dd/mm/yyyy') FORWARD_DATE " +
                 " , FORWARD_USER,BILL_WHLO,to_char(ACCOUNT_DATE,'dd/mm/yyyy') ACCOUNT_DATE,ACCOUNT_USER,REPORT_NO,REC_ACC_USER,to_char(REC_ACC_DATE,'dd/mm/yyyy') REC_ACC_DATE,a.OLD_REPORT_NO,REVERSE_SRVTAX,SRVTAX_GL_CODE,REVERSE_SRVTAX_RATE from m4_bill_master a,M4_BILL_DEPT_MASTER b,prodbi.cidmas@movex.world@movex c  where a.DEPT_SL_NO=b.SL_NO and IDCONO=111 and SUPPLIER_CODE=IDSUNO and BILL_WHLO in ("+result3.getString(1)+") and  DEPT_SL_NO in(select SL_NO from M4_BILL_DEPT_MASTER  where  DEPT_CODE in (select DEPT_SL_NO from  m4_bill_user_master where emp_code=? and flag='Y'))  "+sqlstr+" order by REPORT_NO desc, BILL_NO asc");
                stat1.setString(1,usrid);
                result1=stat1.executeQuery();
                while(result1.next())
                {
                   PreparedStatement  stat2=conn.prepareStatement("select nvl(sum(PRODUCT_AMOUNT),0) PRODUCT_AMOUNT from m4_bill_detail where BILL_SL_NO=?");
                     stat2.setString(1, result1.getString("SL_NO"));
                     result=stat2.executeQuery();
                     double prdamt=0;
                     if(result.next())
                     {
                     prdamt=result.getDouble(1);
                     }
                     
                     stat2=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                     stat2.setString(1, result1.getString("FORWARD_USER"));
                     result=stat2.executeQuery();
                     String full_name="";
                     if(result.next())
                     {
                     full_name=result.getString(1);
                     }
                     stat2=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                     stat2.setString(1, result1.getString("ACCOUNT_USER"));
                     result=stat2.executeQuery();
                     String full_name1="";
                     if(result.next())
                     {
                     full_name1=result.getString(1);
                     }
                     if(result!=null)
                     {
                     result.close();
                     }
                      if(stat2!=null)
                     {
                     stat2.close();
                     }
                      result=null;
                      stat2=null;
                      
                       String DEBITAMOUNT="";
                       PreparedStatement  stat1debit=conn.prepareStatement("select DEBIT_AMOUNT,DEBIT_REASON from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                        stat1debit.setString(1, result1.getString("SL_NO"));
                        ResultSet resultdebit=stat1debit.executeQuery();
                        if(resultdebit.next())
                        {
                         DEBITAMOUNT=resultdebit.getString(1);
                        // DEBIT_REASON=resultdebit.getString(2);

                        }
                       if(resultdebit!=null)
                       {
                       resultdebit.close();
                       }
                       if(stat1debit!=null)
                       {
                       stat1debit.close();
                       }
                      
                     if(!temparr.contains(result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO"))) 
                     {
                         temparr.add(result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO"));
                      accgrplist.add(new M3BillEntryBean(result1.getString("dept_desc"), result1.getString("SUPPLIER_CODE")+"-"+result1.getString("IDSUNM"), result1.getString("REPORT_NO")
                             ,"" ,result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO")));
                     }
                    
                     String glflag=null;
                     
                     if(result1.getString("REVERSE_SRVTAX")!=null && result1.getString("REVERSE_SRVTAX").equals("1") && result1.getString("SRVTAX_GL_CODE")==null)
                     {
                         if(result1.getString("REVERSE_SRVTAX_RATE")!=null && result1.getString("REVERSE_SRVTAX_RATE").equals("0"))
                         {}else{
                        
                             
                             glflag="No";
                         }
                     }
                     
                    deptmastlist.add(new M3BillEntryBean(result1.getString("SL_NO"),result1.getString("dept_desc"),result1.getString("BILL_NO"),result1.getString("BILL_DATE"),result1.getString("SUPPLIER_CODE")+"-"+result1.getString("IDSUNM"),result1.getDouble("BILL_AMOUNT"),
                            prdamt,result1.getDouble("GROSS_AMOUNT"),result1.getString("FORWARD_DATE"),full_name,result1.getString("ACCOUNT_DATE"),full_name1,result1.getString("BILL_WHLO"),result1.getString("REPORT_NO"),result1.getString("REC_ACC_DATE"),result1.getString("REC_ACC_USER"),
                            result1.getString("DEPT_SL_NO")+result1.getString("SUPPLIER_CODE")+result1.getString("REPORT_NO"),DEBITAMOUNT,result1.getString("OLD_REPORT_NO"),glflag));
                    flag=1;
                }
            }
            }

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M3BillEntryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M3BillEntryAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return "viewpage";

        }

        if (flag == 1) {
           
           
            return "viewpage";
        } else {

            addActionMessage("Records Not Found(s) !!");
            return "viewpage";
        }
     
     } 
     
     
  public String newmstview() throws Exception {
if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
{
     getdetail(MAST_SL_NO);
 }else{
    
mastlist=getdeptlist(SEARCH_CODE);

}
 costelement=getcostlement();
 pchlist=new ShahiInformationList().pchListWithBill();

  return "newpageview";
  }   
 
  
  public String getcostentview() throws Exception {
 getfrmcost();
 costelement=getcostlement();
 return "costelemtview";
 }
     
 
public String debitdtview() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
      Connection connbi = null;
            

            try {
                  conn = new ConnectionSeplWeb().getConnection();
                  connbi = new ConnectionShahiHris().getConnection();
                             
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                if(actflg!=null && actflg.equals("Yes"))
                {
                if(EMPTYPE!=null && EMPTYPE.equals("ENT")) 
                {
                
                    stat1=conn.prepareStatement("select *  from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                    stat1.setString(1,MAST_SL_NO) ;
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                       stat1=conn.prepareStatement("update  M4_BILL_DEBIT_DETAIL set DEBIT_AMOUNT=?,DEBIT_REASON=?,TDATE=sysdate,USER_ID=? where BILL_SL_NO=?");
                        stat1.setString(1, DEBIT_AMOUNT);
                        stat1.setString(2, DEBIT_REASON);
                        stat1.setString(3, usrid);
                        stat1.setString(4, MAST_SL_NO);
                        flag=stat1.executeUpdate();
                        
                    }else{
                               if(DEBIT_AMOUNT!=null && DEBIT_AMOUNT.length()>0 && Double.parseDouble(DEBIT_AMOUNT)>0)
                         {
                                stat1=conn.prepareStatement("insert into M4_BILL_DEBIT_DETAIL(BILL_SL_NO,DEBIT_AMOUNT,DEBIT_REASON,TDATE,USER_ID) values(?,?,?,sysdate,?)");
                                stat1.setString(1, MAST_SL_NO);
                                stat1.setString(2, DEBIT_AMOUNT);
                                stat1.setString(3, DEBIT_REASON);
                                stat1.setString(4, usrid);
                                flag=stat1.executeUpdate();
                         }
                    }
                    
                    
                }
                if(EMPTYPE!=null && EMPTYPE.equals("ACC")) 
                {
                
                    stat1=conn.prepareStatement("select *  from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                    stat1.setString(1,MAST_SL_NO) ;
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                        stat1=conn.prepareStatement("update  M4_BILL_DEBIT_DETAIL set ACC_DEBIT_AMOUNT=?,ACC_DEBIT_REASON=?,ACC_TDATE=sysdate,ACC_USER_ID=? where BILL_SL_NO=?");
                        stat1.setString(1, ACC_DEBIT_AMOUNT);
                        stat1.setString(2, ACC_DEBIT_REASON);
                        stat1.setString(3, usrid);
                        stat1.setString(4, MAST_SL_NO);
                        flag=stat1.executeUpdate();
                        
                    }else{
                        if(ACC_DEBIT_AMOUNT!=null && ACC_DEBIT_AMOUNT.length()>0 && Double.parseDouble(ACC_DEBIT_AMOUNT)>0)
                         {
                            stat1=conn.prepareStatement("insert into M4_BILL_DEBIT_DETAIL(BILL_SL_NO,ACC_DEBIT_AMOUNT,ACC_DEBIT_REASON,ACC_TDATE,ACC_USER_ID) values(?,?,?,sysdate,?)");
                            stat1.setString(1, MAST_SL_NO);
                            stat1.setString(2, ACC_DEBIT_AMOUNT);
                            stat1.setString(3, ACC_DEBIT_REASON);
                            stat1.setString(4, usrid);
                            flag=stat1.executeUpdate();
                          }
                    
                    }
                    
                }
                
               
                
                
                if (flag > 0) {
                    addActionMessage("Records  saved(s) !!");
                   } else {
                    addActionMessage("Records Not save(s) !!");
                }
                }
                stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy'),GROSS_AMOUNT from M4_BILL_MASTER where SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                 {
                 FORWARD_DATE=result.getString(1);
                 GROSS_AMOUNT=result.getString(2);
                 }
                  
                 stat1=conn.prepareStatement("select ACCOUNT_DATE from m4_bill_master where SL_NO=? and ACCOUNT_DATE is not null");
                 stat1.setString(1, MAST_SL_NO);
                 result1=stat1.executeQuery();
                if(result1.next())
                {
                ACCOUNTDATE=result1.getString(1);
                } 
                  
                
                stat1=conn.prepareStatement("select DEBIT_AMOUNT,DEBIT_REASON,USER_ID from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                {
                 DEBIT_AMOUNT=result.getString(1);
                 DEBIT_REASON=result.getString(2);
                  
                        stat1=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                         stat1.setString(1, result.getString("USER_ID"));
                         result1=stat1.executeQuery();

                         if(result1.next())
                         {
                         ENT_USER_ID=result1.getString(1);
                         }
                }
                
                stat1=conn.prepareStatement("select ACC_DEBIT_AMOUNT,ACC_DEBIT_REASON,ACC_USER_ID from M4_BILL_DEBIT_DETAIL where BILL_SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                {
                 ACC_DEBIT_AMOUNT=result.getString(1);
                 ACC_DEBIT_REASON=result.getString(2);
                 
                     stat1=connbi.prepareStatement("select full_name from hrempmst where emp_code=?");
                         stat1.setString(1, result.getString("ACC_USER_ID"));
                         result1=stat1.executeQuery();

                         if(result1.next())
                         {
                         ACC_USER_ID=result1.getString(1);
                         }
                
                }
                
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : getinvdtmast M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                    if (connbi != null) {
                        connbi.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : debitpageview M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "debitpageview";    
}    

 public String state() throws Exception {
     if(SUPPLIER_CODE!=null && SUPPLIER_CODE.length()>0)
     {
         SupplierAddressMaster bn=new SupplierAddressMaster();
         supplierAddreessList=bn.getSupplierAddressList("111",SUPPLIER_CODE,"1");
     }
  
 
  return "state";
  }




public String remheadview() throws Exception {
    
       Map session = ActionContext.getContext().getSession();
       String LOCATION_CODE = (String) session.get("sessLocationCode");
       String usrid = (String) session.get("sessUserId");
       int flag=0;
      Connection conn = null;
            

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                if(actflg!=null && actflg.equals("Yes"))
                {
                stat1=conn.prepareStatement("update  M4_BILL_MASTER set remarks=? where SL_NO=?");
                stat1.setString(1, MAST_REMARKS);
                stat1.setString(2, MAST_SL_NO);
                flag=stat1.executeUpdate();
                if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
           
        } else {

            addActionMessage("Records Not save(s) !!");
            
        }
                }
                  stat1=conn.prepareStatement("select to_char(FORWARD_DATE,'dd/mm/yyyy') from M4_BILL_MASTER where SL_NO=?");
                  stat1.setString(1, MAST_SL_NO);
                  result=stat1.executeQuery();
                 if(result.next())
                {
                FORWARD_DATE=result.getString(1);
                }
                
                stat1=conn.prepareStatement("select remarks from  m4_bill_master where SL_NO=?");
                stat1.setString(1, MAST_SL_NO);
                result=stat1.executeQuery();
                if(result.next())
                {
                MAST_REMARKS=result.getString(1);
                
                }
                //invsavelist
             }  catch (Exception e) {

                System.out.print("1 file name : remheadview() M3billAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                   

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : remheadview() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
    return "rempageview";    
}


    public List<M3BillEntryBean> getDeptmastlist() {
        return deptmastlist;
    }

    public void setDeptmastlist(List<M3BillEntryBean> deptmastlist) {
        this.deptmastlist = deptmastlist;
    }

    public List<M3BILLBean> getMastlist() {
        return mastlist;
    }

    public void setMastlist(List<M3BILLBean> mastlist) {
        this.mastlist = mastlist;
    }

    public String getDelflg() {
        return delflg;
    }

    public void setDelflg(String delflg) {
        this.delflg = delflg;
    }

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
    }

    public String getSEARCH_TYPE() {
        return SEARCH_TYPE;
    }

    public void setSEARCH_TYPE(String SEARCH_TYPE) {
        this.SEARCH_TYPE = SEARCH_TYPE;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public List<Supinv> getSuppamtlist() {
        return suppamtlist;
    }

    public void setSuppamtlist(List<Supinv> suppamtlist) {
        this.suppamtlist = suppamtlist;
    }

    public String getTXTID() {
        return TXTID;
    }

    public void setTXTID(String TXTID) {
        this.TXTID = TXTID;
    }

    public List<M3BILLBean> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<M3BILLBean> typelist) {
        this.typelist = typelist;
    }

    public String getDEPT_SL_NO() {
        return DEPT_SL_NO;
    }

    public void setDEPT_SL_NO(String DEPT_SL_NO) {
        this.DEPT_SL_NO = DEPT_SL_NO;
    }

    public List<M3BILLBean> getSubtypelist() {
        return subtypelist;
    }

    public void setSubtypelist(List<M3BILLBean> subtypelist) {
        this.subtypelist = subtypelist;
    }

    public List<M3BILLBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<M3BILLBean> productlist) {
        this.productlist = productlist;
    }

    public String getTYPE_SL_NO() {
        return TYPE_SL_NO;
    }

    public void setTYPE_SL_NO(String TYPE_SL_NO) {
        this.TYPE_SL_NO = TYPE_SL_NO;
    }

    public String getSUB_TYPE_SL_NO() {
        return SUB_TYPE_SL_NO;
    }

    public void setSUB_TYPE_SL_NO(String SUB_TYPE_SL_NO) {
        this.SUB_TYPE_SL_NO = SUB_TYPE_SL_NO;
    }

   

    public String getCC_CODE() {
        return CC_CODE;
    }

    public void setCC_CODE(String CC_CODE) {
        this.CC_CODE = CC_CODE;
    }

   

    public String getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    public String getBILL_DATE() {
        return BILL_DATE;
    }

    public void setBILL_DATE(String BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
    }

    public String getSUPPLIER_CODE() {
        return SUPPLIER_CODE;
    }

    public void setSUPPLIER_CODE(String SUPPLIER_CODE) {
        this.SUPPLIER_CODE = SUPPLIER_CODE;
    }

    public String getBILL_AMOUNT() {
        return BILL_AMOUNT;
    }

    public void setBILL_AMOUNT(String BILL_AMOUNT) {
        this.BILL_AMOUNT = BILL_AMOUNT;
    }

    public String getMAST_SL_NO() {
        return MAST_SL_NO;
    }

    public void setMAST_SL_NO(String MAST_SL_NO) {
        this.MAST_SL_NO = MAST_SL_NO;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getCC_DESC() {
        return CC_DESC;
    }

    public void setCC_DESC(String CC_DESC) {
        this.CC_DESC = CC_DESC;
    }

    public String getDEPT_DESC() {
        return DEPT_DESC;
    }

    public void setDEPT_DESC(String DEPT_DESC) {
        this.DEPT_DESC = DEPT_DESC;
    }

    public String getSUPPLIER_DESC() {
        return SUPPLIER_DESC;
    }

    public void setSUPPLIER_DESC(String SUPPLIER_DESC) {
        this.SUPPLIER_DESC = SUPPLIER_DESC;
    }

    public List<M3BillEntryBean> getBilldetail() {
        return billdetail;
    }

    public void setBilldetail(List<M3BillEntryBean> billdetail) {
        this.billdetail = billdetail;
    }

    public String getTYPE_DESC() {
        return TYPE_DESC;
    }

    public void setTYPE_DESC(String TYPE_DESC) {
        this.TYPE_DESC = TYPE_DESC;
    }

    public String getSUB_TYPE_DESC() {
        return SUB_TYPE_DESC;
    }

    public void setSUB_TYPE_DESC(String SUB_TYPE_DESC) {
        this.SUB_TYPE_DESC = SUB_TYPE_DESC;
    }

    public String getPRODUCT_DESC() {
        return PRODUCT_DESC;
    }

    public void setPRODUCT_DESC(String PRODUCT_DESC) {
        this.PRODUCT_DESC = PRODUCT_DESC;
    }

    public List getPRODUCT_SL_NO() {
        return PRODUCT_SL_NO;
    }

    public void setPRODUCT_SL_NO(List PRODUCT_SL_NO) {
        this.PRODUCT_SL_NO = PRODUCT_SL_NO;
    }

    public List getPRODUCT_AMOUNT() {
        return PRODUCT_AMOUNT;
    }

    public void setPRODUCT_AMOUNT(List PRODUCT_AMOUNT) {
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
    }

    public List getMastlisttemp() {
        return mastlisttemp;
    }

    public void setMastlisttemp(List mastlisttemp) {
        this.mastlisttemp = mastlisttemp;
    }

    public List<M3BillEntryBean> getBilldetailgrp() {
        return billdetailgrp;
    }

    public void setBilldetailgrp(List<M3BillEntryBean> billdetailgrp) {
        this.billdetailgrp = billdetailgrp;
    }

    public List<M3BillEntryBean> getSaveprod() {
        return saveprod;
    }

    public void setSaveprod(List<M3BillEntryBean> saveprod) {
        this.saveprod = saveprod;
    }

    public String getSAVETYPE() {
        return SAVETYPE;
    }

    public void setSAVETYPE(String SAVETYPE) {
        this.SAVETYPE = SAVETYPE;
    }

    public List getPRODUCT_MAST_SL_NO() {
        return PRODUCT_MAST_SL_NO;
    }

    public void setPRODUCT_MAST_SL_NO(List PRODUCT_MAST_SL_NO) {
        this.PRODUCT_MAST_SL_NO = PRODUCT_MAST_SL_NO;
    }

    public List getUP_PRODUCT_SL_NO() {
        return UP_PRODUCT_SL_NO;
    }

    public void setUP_PRODUCT_SL_NO(List UP_PRODUCT_SL_NO) {
        this.UP_PRODUCT_SL_NO = UP_PRODUCT_SL_NO;
    }

    public List getUP_PRODUCT_AMOUNT() {
        return UP_PRODUCT_AMOUNT;
    }

    public void setUP_PRODUCT_AMOUNT(List UP_PRODUCT_AMOUNT) {
        this.UP_PRODUCT_AMOUNT = UP_PRODUCT_AMOUNT;
    }

    public String getS_DEPT_DESC() {
        return S_DEPT_DESC;
    }

    public void setS_DEPT_DESC(String S_DEPT_DESC) {
        this.S_DEPT_DESC = S_DEPT_DESC;
    }

    public List getChkdelmaster() {
        return chkdelmaster;
    }

    public void setChkdelmaster(List chkdelmaster) {
        this.chkdelmaster = chkdelmaster;
    }

    public List getPchlist() {
        return pchlist;
    }

    public void setPchlist(List pchlist) {
        this.pchlist = pchlist;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getS_Bill_FROM() {
        return S_Bill_FROM;
    }

    public void setS_Bill_FROM(String S_Bill_FROM) {
        this.S_Bill_FROM = S_Bill_FROM;
    }

    public String getS_Bill_TO() {
        return S_Bill_TO;
    }

    public void setS_Bill_TO(String S_Bill_TO) {
        this.S_Bill_TO = S_Bill_TO;
    }

    public String getS_SUPPLIER() {
        return S_SUPPLIER;
    }

    public void setS_SUPPLIER(String S_SUPPLIER) {
        this.S_SUPPLIER = S_SUPPLIER;
    }

    public String getS_Bill_NO() {
        return S_Bill_NO;
    }

    public void setS_Bill_NO(String S_Bill_NO) {
        this.S_Bill_NO = S_Bill_NO;
    }

    public String getBILL_DATE1() {
        return BILL_DATE1;
    }

    public void setBILL_DATE1(String BILL_DATE1) {
        this.BILL_DATE1 = BILL_DATE1;
    }

    public String getBILL_DATE2() {
        return BILL_DATE2;
    }

    public void setBILL_DATE2(String BILL_DATE2) {
        this.BILL_DATE2 = BILL_DATE2;
    }

    public String getDIS() {
        return DIS;
    }

    public void setDIS(String DIS) {
        this.DIS = DIS;
    }

    public String getSDPT() {
        return SDPT;
    }

    public void setSDPT(String SDPT) {
        this.SDPT = SDPT;
    }

    public List<M3BILLBean> getCclist() {
        return cclist;
    }

    public void setCclist(List<M3BILLBean> cclist) {
        this.cclist = cclist;
    }

    public List<M3BILLBean> getCostelement() {
        return costelement;
    }

    public void setCostelement(List<M3BILLBean> costelement) {
        this.costelement = costelement;
    }

    public List getBREAK_CODE() {
        return BREAK_CODE;
    }

    public void setBREAK_CODE(List BREAK_CODE) {
        this.BREAK_CODE = BREAK_CODE;
    }

    public List getBREAK_AMOUNT() {
        return BREAK_AMOUNT;
    }

    public void setBREAK_AMOUNT(List BREAK_AMOUNT) {
        this.BREAK_AMOUNT = BREAK_AMOUNT;
    }

    public List<M3BILLBean> getCostelementtype() {
        return costelementtype;
    }

    public void setCostelementtype(List<M3BILLBean> costelementtype) {
        this.costelementtype = costelementtype;
    }

    public List<M3BILLBean> getGrosselemlist() {
        return grosselemlist;
    }

    public void setGrosselemlist(List<M3BILLBean> grosselemlist) {
        this.grosselemlist = grosselemlist;
    }

    public String getGROSS_AMOUNT() {
        return GROSS_AMOUNT;
    }

    public void setGROSS_AMOUNT(String GROSS_AMOUNT) {
        this.GROSS_AMOUNT = GROSS_AMOUNT;
    }

    public List getFORM_TYPE() {
        return FORM_TYPE;
    }

    public void setFORM_TYPE(List FORM_TYPE) {
        this.FORM_TYPE = FORM_TYPE;
    }

    public List<M3BillEntryBean> getSavecostelement() {
        return savecostelement;
    }

    public void setSavecostelement(List<M3BillEntryBean> savecostelement) {
        this.savecostelement = savecostelement;
    }

    public String getTempbillamount() {
        return tempbillamount;
    }

    public void setTempbillamount(String tempbillamount) {
        this.tempbillamount = tempbillamount;
    }

    public String getTempentgrossamt() {
        return tempentgrossamt;
    }

    public void setTempentgrossamt(String tempentgrossamt) {
        this.tempentgrossamt = tempentgrossamt;
    }

    public String getBILL_WHLO() {
        return BILL_WHLO;
    }

    public void setBILL_WHLO(String BILL_WHLO) {
        this.BILL_WHLO = BILL_WHLO;
    }

    public String getBILL_YEAR() {
        return BILL_YEAR;
    }

    public void setBILL_YEAR(String BILL_YEAR) {
        this.BILL_YEAR = BILL_YEAR;
    }

    public String getSBILLSHIPDATE() {
        return SBILLSHIPDATE;
    }

    public void setSBILLSHIPDATE(String SBILLSHIPDATE) {
        this.SBILLSHIPDATE = SBILLSHIPDATE;
    }

    public List getShipbilldatelist() {
        return shipbilldatelist;
    }

    public void setShipbilldatelist(List shipbilldatelist) {
        this.shipbilldatelist = shipbilldatelist;
    }

    public String getSSHIPBILL() {
        return SSHIPBILL;
    }

    public void setSSHIPBILL(String SSHIPBILL) {
        this.SSHIPBILL = SSHIPBILL;
    }

    public String getSSHIPDATE() {
        return SSHIPDATE;
    }

    public void setSSHIPDATE(String SSHIPDATE) {
        this.SSHIPDATE = SSHIPDATE;
    }

    public String getBOS() {
        return BOS;
    }

    public void setBOS(String BOS) {
        this.BOS = BOS;
    }

    public String getSINV() {
        return SINV;
    }

    public void setSINV(String SINV) {
        this.SINV = SINV;
    }

    


    public List getInvlist() {
        return invlist;
    }

    public void setInvlist(List invlist) {
        this.invlist = invlist;
    }

    public String getINVSLTXT() {
        return INVSLTXT;
    }

    public void setINVSLTXT(String INVSLTXT) {
        this.INVSLTXT = INVSLTXT;
    }

    public String getBILL_SL_NO() {
        return BILL_SL_NO;
    }

    public void setBILL_SL_NO(String BILL_SL_NO) {
        this.BILL_SL_NO = BILL_SL_NO;
    }

    public String getBILL_DT_SL_NO() {
        return BILL_DT_SL_NO;
    }

    public void setBILL_DT_SL_NO(String BILL_DT_SL_NO) {
        this.BILL_DT_SL_NO = BILL_DT_SL_NO;
    }

    public List getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(List INV_NO) {
        this.INV_NO = INV_NO;
    }

    public List getINV_WEIGHT() {
        return INV_WEIGHT;
    }

    public void setINV_WEIGHT(List INV_WEIGHT) {
        this.INV_WEIGHT = INV_WEIGHT;
    }

    public List getInvsavelist() {
        return invsavelist;
    }

    public void setInvsavelist(List invsavelist) {
        this.invsavelist = invsavelist;
    }

    public List getINV_SAVE() {
        return INV_SAVE;
    }

    public void setINV_SAVE(List INV_SAVE) {
        this.INV_SAVE = INV_SAVE;
    }

    public String getINV_CC_CODE() {
        return INV_CC_CODE;
    }

    public void setINV_CC_CODE(String INV_CC_CODE) {
        this.INV_CC_CODE = INV_CC_CODE;
    }

    public String getINV_TYPE_SL_NO() {
        return INV_TYPE_SL_NO;
    }

    public void setINV_TYPE_SL_NO(String INV_TYPE_SL_NO) {
        this.INV_TYPE_SL_NO = INV_TYPE_SL_NO;
    }

    public String getINV_SUB_TYPE_SL_NO() {
        return INV_SUB_TYPE_SL_NO;
    }

    public void setINV_SUB_TYPE_SL_NO(String INV_SUB_TYPE_SL_NO) {
        this.INV_SUB_TYPE_SL_NO = INV_SUB_TYPE_SL_NO;
    }

    public String getINV_PRODUCT_SL_NO() {
        return INV_PRODUCT_SL_NO;
    }

    public void setINV_PRODUCT_SL_NO(String INV_PRODUCT_SL_NO) {
        this.INV_PRODUCT_SL_NO = INV_PRODUCT_SL_NO;
    }

    public List getSSHIPBILLNEW() {
        return SSHIPBILLNEW;
    }

    public void setSSHIPBILLNEW(List SSHIPBILLNEW) {
        this.SSHIPBILLNEW = SSHIPBILLNEW;
    }

    public List getBOSNEW() {
        return BOSNEW;
    }

    public void setBOSNEW(List BOSNEW) {
        this.BOSNEW = BOSNEW;
    }

    public List getSINVNEW() {
        return SINVNEW;
    }

    public void setSINVNEW(List SINVNEW) {
        this.SINVNEW = SINVNEW;
    }

    public List getINVSLTXTPAGE() {
        return INVSLTXTPAGE;
    }

    public void setINVSLTXTPAGE(List INVSLTXTPAGE) {
        this.INVSLTXTPAGE = INVSLTXTPAGE;
    }

    public List getINVSLTXTCTN() {
        return INVSLTXTCTN;
    }

    public void setINVSLTXTCTN(List INVSLTXTCTN) {
        this.INVSLTXTCTN = INVSLTXTCTN;
    }

    public List getINV_NONEW() {
        return INV_NONEW;
    }

    public void setINV_NONEW(List INV_NONEW) {
        this.INV_NONEW = INV_NONEW;
    }

    public List getINV_WEIGHTNEW() {
        return INV_WEIGHTNEW;
    }

    public void setINV_WEIGHTNEW(List INV_WEIGHTNEW) {
        this.INV_WEIGHTNEW = INV_WEIGHTNEW;
    }

    public List getINV_SAVENEW() {
        return INV_SAVENEW;
    }

    public void setINV_SAVENEW(List INV_SAVENEW) {
        this.INV_SAVENEW = INV_SAVENEW;
    }

    public List getBILL_DT_SL_NONEW() {
        return BILL_DT_SL_NONEW;
    }

    public void setBILL_DT_SL_NONEW(List BILL_DT_SL_NONEW) {
        this.BILL_DT_SL_NONEW = BILL_DT_SL_NONEW;
    }

    public List getAccchkmaster() {
        return accchkmaster;
    }

    public void setAccchkmaster(List accchkmaster) {
        this.accchkmaster = accchkmaster;
    }

    public String getActflg() {
        return actflg;
    }

    public void setActflg(String actflg) {
        this.actflg = actflg;
    }

    public String getFORWARD_DATE() {
        return FORWARD_DATE;
    }

    public void setFORWARD_DATE(String FORWARD_DATE) {
        this.FORWARD_DATE = FORWARD_DATE;
    }

    public List getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(List REMARKS) {
        this.REMARKS = REMARKS;
    }

    public List getUP_REMARKS() {
        return UP_REMARKS;
    }

    public void setUP_REMARKS(List UP_REMARKS) {
        this.UP_REMARKS = UP_REMARKS;
    }

    public String getACC_FLAG() {
        return ACC_FLAG;
    }

    public void setACC_FLAG(String ACC_FLAG) {
        this.ACC_FLAG = ACC_FLAG;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getACCOUNTDATE() {
        return ACCOUNTDATE;
    }

    public void setACCOUNTDATE(String ACCOUNTDATE) {
        this.ACCOUNTDATE = ACCOUNTDATE;
    }

    public String getSSHIPBILLNEWMASTER() {
        return SSHIPBILLNEWMASTER;
    }

    public void setSSHIPBILLNEWMASTER(String SSHIPBILLNEWMASTER) {
        this.SSHIPBILLNEWMASTER = SSHIPBILLNEWMASTER;
    }

    public String getBOSNEWMASTER() {
        return BOSNEWMASTER;
    }

    public void setBOSNEWMASTER(String BOSNEWMASTER) {
        this.BOSNEWMASTER = BOSNEWMASTER;
    }

    public String getSINVNEWMASTER() {
        return SINVNEWMASTER;
    }

    public void setSINVNEWMASTER(String SINVNEWMASTER) {
        this.SINVNEWMASTER = SINVNEWMASTER;
    }

    public List getINV_NONEWMASTER() {
        return INV_NONEWMASTER;
    }

    public void setINV_NONEWMASTER(List INV_NONEWMASTER) {
        this.INV_NONEWMASTER = INV_NONEWMASTER;
    }

    public List getINV_WEIGHTNEWMASTER() {
        return INV_WEIGHTNEWMASTER;
    }

    public void setINV_WEIGHTNEWMASTER(List INV_WEIGHTNEWMASTER) {
        this.INV_WEIGHTNEWMASTER = INV_WEIGHTNEWMASTER;
    }

    public List getINV_SAVENEWMASTER() {
        return INV_SAVENEWMASTER;
    }

    public void setINV_SAVENEWMASTER(List INV_SAVENEWMASTER) {
        this.INV_SAVENEWMASTER = INV_SAVENEWMASTER;
    }

    public String getMAST_REMARKS() {
        return MAST_REMARKS;
    }

    public void setMAST_REMARKS(String MAST_REMARKS) {
        this.MAST_REMARKS = MAST_REMARKS;
    }

    public String getRejflg() {
        return rejflg;
    }

    public void setRejflg(String rejflg) {
        this.rejflg = rejflg;
    }

    public List getAccchkmasterR() {
        return accchkmasterR;
    }

    public void setAccchkmasterR(List accchkmasterR) {
        this.accchkmasterR = accchkmasterR;
    }

    public int getCtninv() {
        return ctninv;
    }

    public void setCtninv(int ctninv) {
        this.ctninv = ctninv;
    }

    public List getREPORT_NO() {
        return REPORT_NO;
    }

    public void setREPORT_NO(List REPORT_NO) {
        this.REPORT_NO = REPORT_NO;
    }

    public String getCtnflg() {
        return ctnflg;
    }

    public void setCtnflg(String ctnflg) {
        this.ctnflg = ctnflg;
    }

    public List getUP_REPORT_NO() {
        return UP_REPORT_NO;
    }

    public void setUP_REPORT_NO(List UP_REPORT_NO) {
        this.UP_REPORT_NO = UP_REPORT_NO;
    }

    public List getCHKINPUT() {
        return CHKINPUT;
    }

    public void setCHKINPUT(List CHKINPUT) {
        this.CHKINPUT = CHKINPUT;
    }

    public List getUP_CHKINPUT() {
        return UP_CHKINPUT;
    }

    public void setUP_CHKINPUT(List UP_CHKINPUT) {
        this.UP_CHKINPUT = UP_CHKINPUT;
    }

    public String getS_CONTROL() {
        return S_CONTROL;
    }

    public void setS_CONTROL(String S_CONTROL) {
        this.S_CONTROL = S_CONTROL;
    }

    public List<M3BillEntryBean> getAccgrplist() {
        return accgrplist;
    }

    public void setAccgrplist(List<M3BillEntryBean> accgrplist) {
        this.accgrplist = accgrplist;
    }

    public String getRecflg() {
        return recflg;
    }

    public void setRecflg(String recflg) {
        this.recflg = recflg;
    }

    public List getAccrec() {
        return accrec;
    }

    public void setAccrec(List accrec) {
        this.accrec = accrec;
    }

    public List getINV_BUYERMASTER() {
        return INV_BUYERMASTER;
    }

    public void setINV_BUYERMASTER(List INV_BUYERMASTER) {
        this.INV_BUYERMASTER = INV_BUYERMASTER;
    }

    public List getINV_BUYER() {
        return INV_BUYER;
    }

    public void setINV_BUYER(List INV_BUYER) {
        this.INV_BUYER = INV_BUYER;
    }

    public List getAcremarks() {
        return acremarks;
    }

    public void setAcremarks(List acremarks) {
        this.acremarks = acremarks;
    }

    public List getINV_PCHMASTER() {
        return INV_PCHMASTER;
    }

    public void setINV_PCHMASTER(List INV_PCHMASTER) {
        this.INV_PCHMASTER = INV_PCHMASTER;
    }

    public List getINV_PCH() {
        return INV_PCH;
    }

    public void setINV_PCH(List INV_PCH) {
        this.INV_PCH = INV_PCH;
    }

    public String getLcert_recv_no() {
        return lcert_recv_no;
    }

    public void setLcert_recv_no(String lcert_recv_no) {
        this.lcert_recv_no = lcert_recv_no;
    }

    public String getREVERSE_SRVTAX() {
        return REVERSE_SRVTAX;
    }

    public void setREVERSE_SRVTAX(String REVERSE_SRVTAX) {
        this.REVERSE_SRVTAX = REVERSE_SRVTAX;
    }

    public String getREVERSE_SRVTAX_RATE() {
        return REVERSE_SRVTAX_RATE;
    }

    public void setREVERSE_SRVTAX_RATE(String REVERSE_SRVTAX_RATE) {
        this.REVERSE_SRVTAX_RATE = REVERSE_SRVTAX_RATE;
    }

    public String getS_WHLO() {
        return S_WHLO;
    }

    public void setS_WHLO(String S_WHLO) {
        this.S_WHLO = S_WHLO;
    }

    public List getBuyerlist() {
        return buyerlist;
    }

    public void setBuyerlist(List buyerlist) {
        this.buyerlist = buyerlist;
    }

    public String getBUYERCODETEST() {
        return BUYERCODETEST;
    }

    public void setBUYERCODETEST(String BUYERCODETEST) {
        this.BUYERCODETEST = BUYERCODETEST;
    }

    public List getINV_REPORT() {
        return INV_REPORT;
    }

    public void setINV_REPORT(List INV_REPORT) {
        this.INV_REPORT = INV_REPORT;
    }

    public String getBUYERNAMETEST() {
        return BUYERNAMETEST;
    }

    public void setBUYERNAMETEST(String BUYERNAMETEST) {
        this.BUYERNAMETEST = BUYERNAMETEST;
    }

    public String getSavetest() {
        return savetest;
    }

    public void setSavetest(String savetest) {
        this.savetest = savetest;
    }

    public String getSHOWFLAG() {
        return SHOWFLAG;
    }

    public void setSHOWFLAG(String SHOWFLAG) {
        this.SHOWFLAG = SHOWFLAG;
    }

    public String getDEBIT_AMOUNT() {
        return DEBIT_AMOUNT;
    }

    public void setDEBIT_AMOUNT(String DEBIT_AMOUNT) {
        this.DEBIT_AMOUNT = DEBIT_AMOUNT;
    }

    public String getDEBIT_REASON() {
        return DEBIT_REASON;
    }

    public void setDEBIT_REASON(String DEBIT_REASON) {
        this.DEBIT_REASON = DEBIT_REASON;
    }

    public String getS_EMP() {
        return S_EMP;
    }

    public void setS_EMP(String S_EMP) {
        this.S_EMP = S_EMP;
    }

    public int getCtndebit() {
        return ctndebit;
    }

    public void setCtndebit(int ctndebit) {
        this.ctndebit = ctndebit;
    }

    public String getACCESS_FLAG() {
        return ACCESS_FLAG;
    }

    public void setACCESS_FLAG(String ACCESS_FLAG) {
        this.ACCESS_FLAG = ACCESS_FLAG;
    }

    public String getFIELD_NAME() {
        return FIELD_NAME;
    }

    public void setFIELD_NAME(String FIELD_NAME) {
        this.FIELD_NAME = FIELD_NAME;
    }

    public String getSEARCH_TABLE() {
        return SEARCH_TABLE;
    }

    public void setSEARCH_TABLE(String SEARCH_TABLE) {
        this.SEARCH_TABLE = SEARCH_TABLE;
    }

    public String getDISPLAY_NAME() {
        return DISPLAY_NAME;
    }

    public void setDISPLAY_NAME(String DISPLAY_NAME) {
        this.DISPLAY_NAME = DISPLAY_NAME;
    }

    public String getSEARCH_FIELD() {
        return SEARCH_FIELD;
    }

    public void setSEARCH_FIELD(String SEARCH_FIELD) {
        this.SEARCH_FIELD = SEARCH_FIELD;
    }

    public List getBOS_DATE() {
        return BOS_DATE;
    }

    public void setBOS_DATE(List BOS_DATE) {
        this.BOS_DATE = BOS_DATE;
    }

    public List<M3BILLBean> getSrvtaxmasterlist() {
        return srvtaxmasterlist;
    }

    public void setSrvtaxmasterlist(List<M3BILLBean> srvtaxmasterlist) {
        this.srvtaxmasterlist = srvtaxmasterlist;
    }

    public String getSRVTAX_GL_CODE() {
        return SRVTAX_GL_CODE;
    }

    public void setSRVTAX_GL_CODE(String SRVTAX_GL_CODE) {
        this.SRVTAX_GL_CODE = SRVTAX_GL_CODE;
    }

    public String getREFFLG() {
        return REFFLG;
    }

    public void setREFFLG(String REFFLG) {
        this.REFFLG = REFFLG;
    }

    public String getEMPTYPE() {
        return EMPTYPE;
    }

    public void setEMPTYPE(String EMPTYPE) {
        this.EMPTYPE = EMPTYPE;
    }

    public String getACC_DEBIT_AMOUNT() {
        return ACC_DEBIT_AMOUNT;
    }

    public void setACC_DEBIT_AMOUNT(String ACC_DEBIT_AMOUNT) {
        this.ACC_DEBIT_AMOUNT = ACC_DEBIT_AMOUNT;
    }

    public String getACC_DEBIT_REASON() {
        return ACC_DEBIT_REASON;
    }

    public void setACC_DEBIT_REASON(String ACC_DEBIT_REASON) {
        this.ACC_DEBIT_REASON = ACC_DEBIT_REASON;
    }

    public String getACC_USER_ID() {
        return ACC_USER_ID;
    }

    public void setACC_USER_ID(String ACC_USER_ID) {
        this.ACC_USER_ID = ACC_USER_ID;
    }

    public String getENT_USER_ID() {
        return ENT_USER_ID;
    }

    public void setENT_USER_ID(String ENT_USER_ID) {
        this.ENT_USER_ID = ENT_USER_ID;
    }


	public String getLOGISSTATUS() {
		return LOGISSTATUS;
	}


	public void setLOGISSTATUS(String lOGISSTATUS) {
		LOGISSTATUS = lOGISSTATUS;
	}


	public List<M3lrbean> getFablrlist() {
		return fablrlist;
	}


	public void setFablrlist(List<M3lrbean> fablrlist) {
		this.fablrlist = fablrlist;
	}


	public List getFablruomlist() {
		return fablruomlist;
	}


	public void setFablruomlist(List fablruomlist) {
		this.fablruomlist = fablruomlist;
	}


	public List<String> getLR_NO() {
		return LR_NO;
	}


	public void setLR_NO(List<String> lR_NO) {
		LR_NO = lR_NO;
	}


	public List<String> getLR_WEIGHT() {
		return LR_WEIGHT;
	}


	public void setLR_WEIGHT(List<String> lR_WEIGHT) {
		LR_WEIGHT = lR_WEIGHT;
	}


	public List<String> getLR_WEIGHT_UOM() {
		return LR_WEIGHT_UOM;
	}


	public void setLR_WEIGHT_UOM(List<String> lR_WEIGHT_UOM) {
		LR_WEIGHT_UOM = lR_WEIGHT_UOM;
	}


	public List<String> getLR_QTY() {
		return LR_QTY;
	}


	public void setLR_QTY(List<String> lR_QTY) {
		LR_QTY = lR_QTY;
	}


	public List<String> getLR_MATERIAL_TYPE() {
		return LR_MATERIAL_TYPE;
	}


	public void setLR_MATERIAL_TYPE(List<String> lR_MATERIAL_TYPE) {
		LR_MATERIAL_TYPE = lR_MATERIAL_TYPE;
	}


	public List<String> getLrdtchk() {
		return lrdtchk;
	}


	public void setLrdtchk(List<String> lrdtchk) {
		this.lrdtchk = lrdtchk;
	}

    public String getSHP_BILL_DATE_S() {
        return SHP_BILL_DATE_S;
    }

    public void setSHP_BILL_DATE_S(String SHP_BILL_DATE_S) {
        this.SHP_BILL_DATE_S = SHP_BILL_DATE_S;
    }

    public String getREVERSE_SRVTAX_CODE() {
        return REVERSE_SRVTAX_CODE;
    }

    public void setREVERSE_SRVTAX_CODE(String REVERSE_SRVTAX_CODE) {
        this.REVERSE_SRVTAX_CODE = REVERSE_SRVTAX_CODE;
    }

    public List getBOS_LOCT() {
        return BOS_LOCT;
    }

    public void setBOS_LOCT(List BOS_LOCT) {
        this.BOS_LOCT = BOS_LOCT;
    }

    public String getNON_SRVTAX_AMOUNT() {
        return NON_SRVTAX_AMOUNT;
    }

    public void setNON_SRVTAX_AMOUNT(String NON_SRVTAX_AMOUNT) {
        this.NON_SRVTAX_AMOUNT = NON_SRVTAX_AMOUNT;
    }

    public List getSupplierAddreessList() {
        return supplierAddreessList;
    }

    public void setSupplierAddreessList(List supplierAddreessList) {
        this.supplierAddreessList = supplierAddreessList;
    }

    public String getHSN_CODE() {
        return HSN_CODE;
    }

    public void setHSN_CODE(String HSN_CODE) {
        this.HSN_CODE = HSN_CODE;
    }

    public String getECAR() {
        return ECAR;
    }

    public void setECAR(String ECAR) {
        this.ECAR = ECAR;
    }

    public List getGstlist() {
        return gstlist;
    }

    public void setGstlist(List gstlist) {
        this.gstlist = gstlist;
    }

    public String getSUNM() {
        return SUNM;
    }

    public void setSUNM(String SUNM) {
        this.SUNM = SUNM;
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

    public String getCSCD() {
        return CSCD;
    }

    public void setCSCD(String CSCD) {
        this.CSCD = CSCD;
    }

    public List getGST_PER() {
        return GST_PER;
    }

    public void setGST_PER(List GST_PER) {
        this.GST_PER = GST_PER;
    }

    public String getSAVEDATA() {
        return SAVEDATA;
    }

    public void setSAVEDATA(String SAVEDATA) {
        this.SAVEDATA = SAVEDATA;
    }

    public double getSetbrktotal() {
        return setbrktotal;
    }

    public void setSetbrktotal(double setbrktotal) {
        this.setbrktotal = setbrktotal;
    }

    public String getDIFF_AMT() {
        return DIFF_AMT;
    }

    public void setDIFF_AMT(String DIFF_AMT) {
        this.DIFF_AMT = DIFF_AMT;
    }

    public String getHSNCODEFLAG() {
        return HSNCODEFLAG;
    }

    public void setHSNCODEFLAG(String HSNCODEFLAG) {
        this.HSNCODEFLAG = HSNCODEFLAG;
    }

    public List getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(List TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public String getBREAK_AMOUNT_NON_GST() {
        return BREAK_AMOUNT_NON_GST;
    }

    public void setBREAK_AMOUNT_NON_GST(String BREAK_AMOUNT_NON_GST) {
        this.BREAK_AMOUNT_NON_GST = BREAK_AMOUNT_NON_GST;
    }

    
    
 
}
