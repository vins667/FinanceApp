
package shahi.Action.MvxExp;
   
import java.sql.*; 
import java.util.*;
import java.io.*; 
import com.opensymphony.xwork2.ActionContext;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.text.SimpleDateFormat;
import java.util.Date; 
import javax.servlet.ServletContext;
import org.apache.struts2.StrutsStatics;
import org.apache.commons.io.FileUtils;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.MvxExp.Beans.IOBdbkBean; 
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.Beans.DbkChqUpdBean;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class DbkChqInsertAction extends ActionSupport {

    private List FileList = new ArrayList();
    private String aausrid ;
    private String saveflag;
    private String currentdate;
    private List errorlist;
   
    private String sbank;
    private String trdate;
    private String date1;
    private String showFlag;
    private String dbkbank;
    private String[] saverec;
    private List BankList = new ArrayList();
    private List ChqList = new ArrayList();
     private List DateList = new ArrayList();
    
    ResourceBundle aResourcBundle = null;

   private String getValue(String key) {
        return aResourcBundle.getString(key);
    }


   public String execute()
   {
       try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }
       
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       int flag = 0;
        if(usrid==null)
        {
           session.put("sessUserId",aausrid); 
           usrid=aausrid;
        }

        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
      
        try { 
            Connection conn = null,Odbcon=null;
            try {
                aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
               conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return INPUT;
            } // end catch

        try {
                Odbcon = new connection().getConnection();
                Odbcon.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("IBM Database Connection Not Valid !!");
                return INPUT;
            } // end catch


            PreparedStatement stat1=null,stat=null;
            ResultSet RTaccess = null,result1 =null,result=null;
         try
           { 
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
           
             stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
             stat1.setString(1,aausrid);
             result1=stat1.executeQuery();
             while (result1.next())
             {LOCATION_CODE=result1.getString("location_code");
             } 
               
             stat1 = conn.prepareStatement("select distinct a.bank_code,bank_desc||'-'||a.bank_code BANK_DESC from eis_dbk_bank a,eis_dbkchq_upload b where a.bank_code=b.bank_code and upd_date is null order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 BankList.add(new GetListBean(result1.getString("bank_code"),result1.getString("bank_desc")));      
              }
              if (sbank!=null )  ///&& showFlag==null
                 {
                 stat1 = conn.prepareStatement("select distinct to_char(tr_date,'dd-Mon-yyyy') tdate2 from ei_dbk_mast a,eis_dbkchq_upload b where a.shp_bill_no=TRIM(b.sb_no) and upd_date is null and b.bank_code=? order by 1" );
                 stat1.setString(1,sbank);
                 result1 = stat1.executeQuery();
                 while(result1.next())
                {   
                     DateList.add(new GetListBean(result1.getString("tdate2"),result1.getString("tdate2")));      
                 }   
                 }
             
             
              double strrecv=0; double dbkwoff=0; double balamt=0;   
              if (showFlag!=null && showFlag.length()>0)
             {  
                 trdate = date1; 
                 sbank=  dbkbank;
                 
              if (sbank.equals("IOBT105") || sbank.substring(0,3).equals("PNB"))
              {
                stat1 = conn.prepareStatement(" select to_char(tr_date,'dd-Mon-yyyy') tr_date,a.shp_bill_no,to_char(a.shp_bill_date,'dd-Mon-yyyy') sbdate,claim_port,a.dbk_admisable,a.str_due,a.str_woff,b.amt_cr,str_not_claim from ei_dbk_mast a,eis_dbkchq_upload b where a.shp_bill_date>='01-jan-2012' and a.shp_bill_no=TRIM(b.sb_no) and upd_date is null and b.bank_code=? and tr_date=? order by 1,2,3 " );
              }else
              {
                stat1 = conn.prepareStatement(" select to_char(tr_date,'dd-Mon-yyyy') tr_date,a.shp_bill_no,to_char(a.shp_bill_date,'dd-Mon-yyyy') sbdate,claim_port,a.dbk_admisable,a.str_due,a.str_woff,b.amt_cr,str_not_claim from ei_dbk_mast a,eis_dbkchq_upload b where a.shp_bill_no=TRIM(b.sb_no) and (to_char(a.shp_bill_date,'dd/mm/yyyy')=to_char(b.sb_date,'dd/mm/yyyy') or to_char(a.shp_bill_date,'dd/mm/yyyy')=to_char(b.sb_date,'mm/dd/yyyy')) and upd_date is null and b.bank_code=? and tr_date=? order by 1,2,3 " );
              }
                stat1.setString(1,sbank);
                stat1.setString(2,trdate);
                result1 = stat1.executeQuery();
               while(result1.next())
               {    
                  double bankcr=result1.getDouble("amt_cr");
                  double strdue=result1.getDouble("str_due")-result1.getDouble("str_woff");;
                     if (result1.getString("str_not_claim")==null && strdue>0)
                     {
                       strrecv=strdue;
                     }
                    double dbkrecv=bankcr-strrecv;
                    if (result1.getDouble("dbk_admisable")-dbkrecv<=500 && result1.getDouble("dbk_admisable")-dbkrecv>5)
                    {
                      dbkwoff=result1.getDouble("dbk_admisable")-dbkrecv;
                    }
                      balamt=bankcr-strrecv-dbkrecv-dbkwoff;
                       
                    ChqList.add(new DbkChqUpdBean(result1.getString("TR_DATE"),result1.getString("shp_bill_no"),result1.getString("sbdate"),result1.getString("claim_port"),result1.getDouble("dbk_admisable"),result1.getDouble("str_due"),result1.getDouble("str_woff"),result1.getDouble("amt_cr"),dbkrecv,strrecv,dbkwoff,balamt));
                    balamt=0; dbkwoff=0;
               } // end while

             }
                 String va1=null;          String va3=null;
                 String va2=null;          String ChqPreFix="A";   String Chqdt =null;
                 double dbkrecv = 0;       double bankcr =0; 
                  if  (saverec!=null && saveflag!=null && saveflag.length()>0 )
                 {
                            stat1 = conn.prepareStatement(" select chr(count(*)+65) max_char from exports.ei_dbk_chq_mast where  bank=? and chq_date=? " );
                            stat1.setString(1,dbkbank);
                            stat1.setString(2,trdate);
                            result1 = stat1.executeQuery();
                           if (result1.next()==true)
                            {   
                                ChqPreFix= result1.getString("max_char"); 
                             } 
                 
                     
                  for(int i=0; i<saverec.length; i++)
                   {
                      if(saverec[i].length()!=0)
                       { int in1=saverec[i].indexOf("$");
                         va1=saverec[i].substring(0,in1);
                         int in2=saverec[i].indexOf("~");
                          va2=saverec[i].substring(in1+1,in2);
                          va3=saverec[i].substring(in2+1);
                          
                           if (dbkbank.equals("IOBT105") || dbkbank.substring(0,3).equals("PNB") )
                               {
                                  stat=conn.prepareStatement("select count(*) v_cnt from ei_dbk_mast where shp_bill_date>='01-jan-2012' and shp_bill_no=? ");
                                  stat.setString(1,va1);
                               }else
                             {
                                stat=conn.prepareStatement("select count(*) v_cnt from ei_dbk_mast where shp_bill_no=?  and shp_bill_date=?");
                                  stat.setString(1,va1);
                                   stat.setString(2,va2);
                                 }
                                  result=stat.executeQuery();
                                   while(result.next())
                                   { if (result.getInt("v_cnt")>1) 
                                          {
                                           addActionMessage("Please check More then one S/B :"+va1 +" Date: "+va2);
                                           return ERROR;
                                          }
                                    
                                    }    
                                            stat1 = conn.prepareStatement(" select a.dbk_admisable,a.str_due,a.str_woff,b.amt_cr,str_not_claim,to_char(b.tr_date,'ddmmyy') chdt from ei_dbk_mast a,eis_dbkchq_upload b where  a.shp_bill_no=trim(b.sb_no) and upd_date is null and a.shp_bill_no=? and a.shp_bill_date=?  " );
                                            stat1.setString(1,va1);
                                            stat1.setString(2,va2);
                                            result1 = stat1.executeQuery();
                                             while(result1.next())
                                           {    
                                              bankcr=result1.getDouble("amt_cr");
                                              Chqdt=result1.getString("chdt");
                                              double strdue=result1.getDouble("str_due")-result1.getDouble("str_woff");;
                                                 if (result1.getString("str_not_claim")==null && strdue>0)
                                                 {
                                                   strrecv=strdue;
                                                 }
                                                dbkrecv=bankcr-strrecv;
                                                if (result1.getDouble("dbk_admisable")-dbkrecv<=500 && result1.getDouble("dbk_admisable")-dbkrecv>5)
                                                {
                                                  dbkwoff=result1.getDouble("dbk_admisable")-dbkrecv;
                                                }
                                                
                                            }   
                                         stat = conn.prepareStatement("INSERT INTO exports.ei_dbk_chq_dtls (year,chq_no,shp_bill_no,shp_bill_date,amount,bank_cr,str_amt,w_off,seh_user,pay_type,chqdate,chqbank,location,tdate) VALUES (?,?,?,?,?,?,?,?,?,'D',?,?,?,sysdate)  "  );
                                        stat.setString(1,va3.substring(7,11)); 
                                        stat.setString(2,(dbkbank+Chqdt+ChqPreFix));
                                        stat.setString(3,va1); 
                                        stat.setString(4,va2); 
                                        stat.setDouble(5,dbkrecv);
                                        stat.setDouble(6,bankcr);
                                        stat.setDouble(7,strrecv);
                                        stat.setDouble(8,dbkwoff);
                                        stat.setString(9,usrid); 
                                        stat.setString(10,va3);
                                        stat.setString(11,dbkbank);
                                        stat.setString(12,LOCATION_CODE);
                                        stat.executeUpdate();  
                  
                                         stat1 = conn.prepareStatement("update eis_dbkchq_upload set upd_date=sysdate where trim(sb_no)=? and trim(bank_code)=? and upd_date is null   " );
                                         stat1.setString(1,va1.trim());
                                         stat1.setString(2,dbkbank.trim());
                                         stat1.executeUpdate();
                                        
                                        dbkwoff=0; strrecv=0;
                                          flag=1;
                       }
                   }
                 }
              
               
           }catch (Exception e) {
            System.out.println(e.toString());
            addActionMessage(e+" - Error In  Statement !!");
            try{   conn.rollback();
            }catch(Exception ee) {System.out.println(ee.toString());}

            }finally {
                try
                {
                    if (stat1 != null) { stat1.close(); }
                    if (stat != null) { stat.close(); }

                    if (RTaccess != null) { RTaccess.close(); }
                    if (result != null) { result.close(); }
                    if (result1 != null) { result1.close(); }

                    if (conn != null) { conn.close(); }
                    if  (Odbcon!=null) {Odbcon.close();}
                    RTaccess = null;
                    conn = null; Odbcon=null;
                } catch (Exception e) {
                    System.out.print("File Name : DbkChqInsertAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  DbkChqInsertAction.java ");
                }
            } /// end Finally Block
         
         
        } 
        
         
        catch (Exception e) {
            addActionError(e.getMessage());
        }
            
           if (flag == 1) {
                 addActionMessage("Records Save(s) !!");
               return INPUT;
                }
            else {return ERROR;
           }
    }  // end excute

    

    public List getFileList() {
        return FileList;
    }

    public void setFileList(List FileList) {
        this.FileList = FileList;
    }

   
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String[] getSaverec() {
        return saverec;
    }

    public void setSaverec(String[] saverec) {
        this.saverec = saverec;
    }

        

    public String getSaveflag() {
        return saveflag;
    }

    public void setSaveflag(String saveflag) {
        this.saveflag = saveflag;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public ResourceBundle getaResourcBundle() {
        return aResourcBundle;
    }

    public void setaResourcBundle(ResourceBundle aResourcBundle) {
        this.aResourcBundle = aResourcBundle;
    }

   
    public List getErrorlist() {
        return errorlist;
    }

    public void setErrorlist(List errorlist) {
        this.errorlist = errorlist;
    }

  
    public List getBankList() {
        return BankList;
    }

    public void setBankList(List BankList) {
        this.BankList = BankList;
    }

    public String getSbank() {
        return sbank;
    }

    public void setSbank(String sbank) {
        this.sbank = sbank;
    }

    public String getDbkbank() {
        return dbkbank;
    }

    public void setDbkbank(String dbkbank) {
        this.dbkbank = dbkbank;
    }

    public List getDateList() {
        return DateList;
    }

    public void setDateList(List DateList) {
        this.DateList = DateList;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public List getChqList() {
        return ChqList;
    }

    public void setChqList(List ChqList) {
        this.ChqList = ChqList;
    }

    public String getTrdate() {
        return trdate;
    }

    public void setTrdate(String trdate) {
        this.trdate = trdate;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    
  
     

  
   

    

}
