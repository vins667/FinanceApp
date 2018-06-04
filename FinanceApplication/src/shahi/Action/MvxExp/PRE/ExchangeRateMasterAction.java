//guddu
package shahi.Action.MvxExp.PRE;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Beans.CntryMasterBean;
import shahi.Action.MvxExp.Beans.ExrateShowBean;


public class ExchangeRateMasterAction extends ActionSupport {

    private List CONTENT_CODE;
    private List CONTENT_DESC;
    private List exp_rate;
    private List imp_rate;
    private String curr_code;
    private List errorList=null;
    private List errorList1=null;
    private List ShowDetail=null;
    private String saveFlag;
    private String start_date1;
    private String end_date1;
    private List curr_code1;
    private List start_date2;
    private List end_date2;
    private String aausrid;
    private List curr_code_L;
    private List end_date_L;
    private List begin_date_L; 
    private List chqdate;
    private String close_date;
    private String chq_date1; 


 

    public String execute() {
          errorList = new ArrayList();
          ShowDetail = new ArrayList();
         int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
         if(usrid==null)
        {
           session.put("sessUserId",aausrid);
           usrid=aausrid;
        }
        usrid="227350";
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }


        try {

            Connection conn = null;

            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            ResultSet result1 = null;
            ResultSet result2 = null;

            try {
                   stat1 = conn.prepareStatement("select currency_desc||'  ('||currency||')' curr_desc,currency from ei_currency_mast order by 1,2"  );
                   result1 = stat1.executeQuery();
                   while(result1.next())
                     {
                       errorList.add(new CntryMasterBean(result1.getString("currency"),result1.getString("curr_desc"),"",""));
                     }
                    if (saveFlag!=null)
                    {
                     if (start_date1.length()==0)
                     {
                      stat2 = conn.prepareStatement("select b.currency_desc,to_char(begin_date,'yyyymmdd') bdate,a.currency,to_char(a.exp_rate,'999.99') exp_rate,to_char(a.imp_rate,'999.99') imp_rate,to_char(a.begin_date,'dd/mm/yyyy') begin_date,to_char(a.end_date,'dd/mm/yyyy') end_date from ei_exchange_rate_mast a,ei_currency_mast b where a.currency=b.currency and a.currency like ? order by 1,2"  );
                      stat2.setString(1,curr_code);
                      result2 = stat2.executeQuery();
                     }
                     else
                     {
                         String query="";
                         if(start_date1!=null &&  start_date1.length()>0){
                         start_date1 = start_date1.substring(0, 10);
                          query+=" and begin_date>=to_date('"+start_date1+"','yyyy-mm-dd')";
                         }
                         if(end_date1!=null && end_date1.length()>0){
                         end_date1 = end_date1.substring(0, 10);
                         query+=" and end_date<=to_date('"+end_date1+"','yyyy-mm-dd')";
                         }
                         System.out.println("guddu"+query);
                      stat2 = conn.prepareStatement("select b.currency_desc,to_char(begin_date,'yyyymmdd') bdate,a.currency,to_char(a.exp_rate,'999.99') exp_rate,to_char(a.imp_rate,'999.99') imp_rate,to_char(a.begin_date,'dd/mm/yyyy') begin_date,to_char(a.end_date,'dd/mm/yyyy') end_date from ei_exchange_rate_mast a,ei_currency_mast b where a.currency=b.currency and a.currency like ?  "+query+"  order by 1,2"  );
                      stat2.setString(1,curr_code);
                      result2 = stat2.executeQuery();

                     }
                     while(result2.next())
                     {
                       ShowDetail.add(new ExrateShowBean(result2.getString("currency_desc"),result2.getString("currency"),result2.getString("exp_rate"),result2.getString("imp_rate"),result2.getString("begin_date"),result2.getString("end_date"))); 
                     }
                    }
            }
             catch (Exception e) {
                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : ExchangeRateMaster.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : ExchangeRateMaster.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result2 != null) {
                        result2.close();
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
                    flag = 0;
                    System.out.print("File Name : ExchangeRateMaster.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
            CONTENT_CODE=null;
            CONTENT_DESC=null;

           addActionMessage("Records Save(s) !!");
            return SUCCESS;
        }

        else {

           // addActionMessage("Records Not Save(s) !!");
            return ERROR;
        }

 
    }
public String newentry() {
          errorList = new ArrayList();
         errorList1 = new ArrayList();
         int flag = 0;
         int flag2 = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
      if(usrid==null)
        {
           session.put("sessUserId",aausrid);
           usrid=aausrid;
        }
        
        // usrid="227350";
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }


        try {

            Connection conn = null;

            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                stat1 = conn.prepareStatement("select currency_desc||'  ('||currency||')' curr_desc,currency from ei_currency_mast order by 1,2"  );
                   result1 = stat1.executeQuery();
                 errorList.add("new");
                   while(result1.next())
                     {
                        errorList.add(new CntryMasterBean(result1.getString("currency"),result1.getString("curr_desc"),"",""));
                     }

                  if(saveFlag!=null && saveFlag.length()>0)
                {
                 if(curr_code1!=null && curr_code1.size()>0)
                 {
                     for(int i=0; i<curr_code1.size(); i++)
                     {
                         if(curr_code1.get(i).toString()!=null && curr_code1.get(i).toString().length()>0)
                         {       start_date1 = start_date2.get(i).toString().substring(0, 10);
                                 end_date1 = end_date2.get(i).toString().substring(0, 10);

                                 stat1 = conn.prepareStatement("select * from ei_exchange_rate_mast where currency=? and (to_date(?,'yyyy-mm-dd') between begin_date and end_date or  to_date(?,'yyyy-mm-dd')  between begin_date and end_date ) ");
                                 stat1.setString(1,curr_code1.get(i).toString().toUpperCase());
                                 stat1.setString(2, start_date1);
                                 stat1.setString(3, end_date1);
                                 result1 = stat1.executeQuery();

                                 if(result1.next())
                                { 
                                  errorList1.add(new CntryMasterBean(curr_code1.get(i).toString().toUpperCase(),start_date1,end_date1,""));
                                  flag2=1;
                                }else{

                                  stat1 = conn.prepareStatement("insert into ei_exchange_rate_mast (currency,begin_date,end_date,exp_rate,imp_rate,SEH_USER,TDATE) values(?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,sysdate)");
                                  stat1.setString(1, curr_code1.get(i).toString().toUpperCase());
                                  stat1.setString(2, start_date1);
                                  stat1.setString(3, end_date1);
                                  stat1.setString(4, exp_rate.get(i).toString());
                                  stat1.setString(5, imp_rate.get(i).toString());
                                  stat1.setString(6,usrid);
                                  stat1.executeUpdate();
                                  flag = 1;

                                }
                         }
                     }
                 }
                 if(flag2>0){
                     curr_code1=null;
                     exp_rate=null;
                     imp_rate=null;
                 }
                 if(flag>0){
                     addActionMessage("Data Inserted");
                     curr_code1=null;
                     exp_rate=null;
                     imp_rate=null;
                 }
                 }

     } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : ExchangeRateMaster.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : ExchangeRateMaster.java" + e);

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
                    System.out.print("File Name : ExchangeRateMaster.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

       
        return "new";
}

    public String update1() {
          errorList = new ArrayList();
         errorList1 = new ArrayList();
         int flag = 0;
         int flag2 = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
      if(usrid==null)
        {
           session.put("sessUserId",aausrid);
           usrid=aausrid;
        }
        
         //usrid="227350";
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }


        try {

            Connection conn = null;

            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                
                
                 if(curr_code_L!=null && curr_code_L.size()>0)
                 {
                     String q="";
                     for(int i=0; i<curr_code_L.size(); i++)
                     {
                         String[] x=chqdate.get(i).toString().split(",");
                         String p=x[0];
                         if(p!=null && p.length()>0){
                          q=x[1];
                         }
                         
                         
                         if(p!=null && p.length()>0)
                         {       
                             SimpleDateFormat formt = new SimpleDateFormat("dd/MM/yyyy");
                             SimpleDateFormat formte = new SimpleDateFormat("yyyy-MM-dd");
                             SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
                             
                                 chq_date1 = format1.format(formt.parse(p));
                                 String begin_date_L1 = format1.format(formt.parse(q));
                                 String close_date1 = format1.format(formte.parse(close_date));
                                 
                                Date date1=format1.parse(close_date1);
                                Date date2=format1.parse(begin_date_L1);
                                                                       
                                 if(format1.format(formt.parse(p))!=null){
                                 if(date1.after(date2)){
                                    stat1 = conn.prepareStatement("update ei_exchange_rate_mast set END_DATE=?,SEH_USER=?,TDATE=sysdate where CURRENCY=? and END_DATE=?");
                                    stat1.setString(1,close_date1);
                                    stat1.setString(2,usrid);
                                    stat1.setString(3,curr_code_L.get(i).toString());
                                    stat1.setString(4,format1.format(formt.parse(chqdate.get(i).toString())));

                                      stat1.executeUpdate();
                                        ++flag;
                                    }
                                 else{
                                     ++flag2;
                                    
                                 }
                                 }
                                }
                         }
                     }
                 
                
                 if(flag>0){
                     addActionMessage("Data Updated");
                     curr_code1=null;
                     exp_rate=null;
                     imp_rate=null;
                 }
                 if(flag2>0){
                    addActionMessage("Close date should be greater than Start Date");
                    
                 }
                 

     } catch (Exception e) {
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
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }
        saveFlag="Yes";
        
        execute();
       
            return SUCCESS;
}

    

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

  





  public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }



    public List getCONTENT_CODE() {
        return CONTENT_CODE;
    }

    public void setCONTENT_CODE(List CONTENT_CODE) {
        this.CONTENT_CODE = CONTENT_CODE;
    }

    public List getCONTENT_DESC() {
        return CONTENT_DESC;
    }

    public void setCONTENT_DESC(List CONTENT_DESC) {
        this.CONTENT_DESC = CONTENT_DESC;
    }

    public List getErrorList() {
        return errorList;
    }

    public void setErrorList(List errorList) {
        this.errorList = errorList;
    }

    public List getShowDetail() {
        return ShowDetail;
    }

    public void setShowDetail(List ShowDetail) {
        this.ShowDetail = ShowDetail;
    }

    public String getCurr_code() {
        return curr_code;
    }

    public void setCurr_code(String curr_code) {
        this.curr_code = curr_code;
    }

    public String getEnd_date1() {
        return end_date1;
    }

    public void setEnd_date1(String end_date1) {
        this.end_date1 = end_date1;
    }

    public String getStart_date1() {
        return start_date1;
    }

    public void setStart_date1(String start_date1) {
        this.start_date1 = start_date1;
    }

    public List getErrorList1() {
        return errorList1;
    }

    public void setErrorList1(List errorList1) {
        this.errorList1 = errorList1;
    }

    public List getCurr_code1() {
        return curr_code1;
    }

    public void setCurr_code1(List curr_code1) {
        this.curr_code1 = curr_code1;
    }

    public List getEnd_date2() {
        return end_date2;
    }

    public void setEnd_date2(List end_date2) {
        this.end_date2 = end_date2;
    }

    public List getExp_rate() {
        return exp_rate;
    }

    public void setExp_rate(List exp_rate) {
        this.exp_rate = exp_rate;
    }

    public List getImp_rate() {
        return imp_rate;
    }

    public void setImp_rate(List imp_rate) {
        this.imp_rate = imp_rate;
    }

    public List getStart_date2() {
        return start_date2;
    }

    public void setStart_date2(List start_date2) {
        this.start_date2 = start_date2;
    }

    public List getCurr_code_L() {
        return curr_code_L;
    }

    public void setCurr_code_L(List curr_code_L) {
        this.curr_code_L = curr_code_L;
    }

    public List getChqdate() {
        return chqdate;
    }

    public void setChqdate(List chqdate) {
        this.chqdate = chqdate;
    }

   

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public List getEnd_date_L() {
        return end_date_L;
    }

    public void setEnd_date_L(List end_date_L) {
        this.end_date_L = end_date_L;
    }

    public String getChq_date1() {
        return chq_date1;
    }

    public void setChq_date1(String chq_date1) {
        this.chq_date1 = chq_date1;
    }

    public List getBegin_date_L() {
        return begin_date_L;
    }

    public void setBegin_date_L(List begin_date_L) {
        this.begin_date_L = begin_date_L;
    }

   





}
