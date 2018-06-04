/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.HDFTextFileUploadBean;
import shahi.Action.ReportFolder.EPM.util.DateUtil;
import shahi.Action.database.ConnectionDB2HR;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author Amit
 */
public class HDFTextFileUploadAction extends ActionSupport{
    
    private String aausrid;
    private File unFile;
    private String unFileFileName;
    private String unFileContentType;
    private List  HDFCFILE_LIST;
    private List  NEWHD_LIST;
    private String SAVE_FLAG;
    private String SAVE_NEW;
    private List TRANS_DATE;
    private List DESC;
    private List AMOUNT;
    private List CD_FLAG;
    private List REF_NO;
    private List VAL_DATE;
    private List BRANCH_NAME;
    private List FIN_YEAR;
    
    private String CLOSING_BALANCE;
    private String OPENNING_BLANCE;
    private String TODATE;
    
    private String FROM_DATE;
    private String TO_DATE;
    private String BANK_CODE;
    private String UNIT_CODE;
    private String fileName;
    public String mksess() throws SQLException, Exception
    {
          System.out.println("Amit"+aausrid);
        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid!=null){
            session.remove("sessUserName");
            session.remove("sessUserId");
            session.remove("sessUnitCode");
            session.remove("sessShiftDesc");
            session.remove("sessShiftSTARTTIME");
            session.remove("sessShiftENDTIME");
        }
        try {
            conndb2 = new ConnectionDB2HR().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
            execute();
            return ERROR;
        }
        try{
            stat = conndb2.prepareStatement("SELECT distinct EMPLOYEE.CODE EMP_CODE,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') EMP_NAME,"
                    + "EMPLOYEE.FACTORYCODE UNIT_CODE,SHIFT.LONGDESCRIPTION SHIFTDESC,STARTTIME, ENDTIME FROM EMPLOYEE,SHIFT WHERE "
                    + "EMPLOYEE.COMPANYCODE=SHIFT.COMPANYCODE "
                    + "AND EMPLOYEE.WORKSHIFTNOCODE=SHIFT.CODE AND nvl(EMPLOYEE.COLUMN1,employee.cardno)=?");// ---hrempview where emp_code=?
            stat.setString(1,aausrid.trim());
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
                session.put("sessUnitCode", result.getString("UNIT_CODE"));
                session.put("sessShiftDesc", result.getString("SHIFTDESC"));
                session.put("sessShiftSTARTTIME", STARTTIME);
                session.put("sessShiftENDTIME", ENDTIME);
                session.put("sessShiftMIDSHIFT", MIDSHIFT);
            }
        }
        catch(SQLException se){
            addActionError("com.shahi.ams.UNFileUploadAction.java mksess()" + se);
            execute();
            return ERROR;
        }
        catch(Exception e){
            addActionError("com.shahi.ams.UNFileUploadAction.java mksess()" + e);
            execute();
            return ERROR;
        }
        finally{
            if(conndb2!=null) conndb2.close();
            if(result!=null) result.close();
            if(stat!=null) stat.close();
        }      
        execute();
        return SUCCESS;
    }
    
//----------------execute()-----------------------------------------------------
    @Override
    public String execute()
    {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        return "success";
    }
    
//----------TXT FILE UPLOAD FUNCTION--------------------------------------------
    public String uploadFile() throws SQLException
    {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");

        int count=0;
        Connection conn=null;
        Connection conndb2=null;
        PreparedStatement stat=null;
        ResultSet rs=null, rs2=null;
        HDFCFILE_LIST=new ArrayList();
        NEWHD_LIST=new ArrayList();
        try {
              conn = new ConnectionShahiHrisNew().getConnection();
              conndb2 = new connectiondb2().getConnection();
            ActionContext ac= ActionContext.getContext();
            ServletContext sc=(ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
           // File uploadDir=new File("D:/NetBeans/NOW/web/bankpay/esicfile/");
            //File uploadDir=new File("D:");
            //File uploadDir = new File(sc.getRealPath("/Hris/esicfile/"));
            /*if(uploadDir.exists()==false)
            {
                uploadDir.mkdirs();
            }*/
           
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            SimpleDateFormat format = new SimpleDateFormat( "yyyymmdd" );        

            String exe=unFileFileName.substring(unFileFileName.lastIndexOf(".")+1,unFileFileName.length());
            if(exe!=null && exe.toUpperCase().equalsIgnoreCase("TXT"))
            {
                String strdt="";
                ArrayList<String> list =new ArrayList<String> ();
                String currentLine;
                fileName=unFileFileName.substring(0,10);
                
                FileInputStream file = new FileInputStream(unFile); 
                BufferedReader br=new BufferedReader(new InputStreamReader(file));
                String clsamt[] = null;
                String opnamt[]=null;
                String todat[] = null;
               //-----Reading file and put into List----------------------------
                while((currentLine = br.readLine())!= null)
                {
                    if(currentLine.contains("~"))
                    {
                         String arr[]=currentLine.trim().split("~",-1);
                         HDFTextFileUploadBean bean=new HDFTextFileUploadBean();
                         if(arr[10].equals("E") || arr[10].equals("R") || arr[10].equals("D"))
                         {
                                bean.setTRANS_DATE(arr[0]);
                                if(arr[4].length()==10)
                                {
                                  bean.setYEAR("20"+arr[4].substring(0,2));
                                  bean.setDESC(arr[4].substring(2, 5));
                                  bean.setBRANCH_NAME(arr[4].substring(5, arr[4].length())); 
                                }
                               else{
                                  bean.setDESC(arr[4].substring(0, 3));
                                  bean.setBRANCH_NAME(arr[4].substring(3, arr[4].length()));
                                }
                                bean.setAMOUNT(arr[9]);
                                bean.setCD_FLAG(arr[10]);
 
                               if(arr.length==12)
                               {
                                 bean.setREF_NO(arr[11].substring(8, arr[11].length()));
                               }
                               else if(arr[6].startsWith("N")){
                                bean.setREF_NO(arr[6].substring(1,arr[6].length()));
                               }
                               else{
                                 bean.setREF_NO(arr[6]);  
                               }
                                bean.setVAL_DATE(arr[5]);
                                NEWHD_LIST.add(bean);
                         }
                         else{
                                bean.setTRANS_DATE(arr[0]);
                                bean.setYEAR("201"+arr[3].substring(0,1));
                                bean.setDESC(arr[3].substring(1,4));
                                bean.setBRANCH_NAME(arr[3].substring(4, arr[3].length()));
                                bean.setAMOUNT(arr[8]);
                                bean.setCD_FLAG(arr[9]);
                               if(arr[5].startsWith("N")){
                                bean.setREF_NO(arr[5].substring(1,arr[5].length()));
                               }
                               else{
                                 bean.setREF_NO(arr[5]);  
                               }
                                bean.setVAL_DATE(arr[4]);
                                NEWHD_LIST.add(bean);
                         }
                    }
                    else{
                        NEWHD_LIST=null;
                        if(currentLine.startsWith("Opening Balance"))
                        {
                           opnamt=currentLine.split(":"); 
                        }
                        if(currentLine.startsWith("Closing Balance"))
                        {
                          clsamt=currentLine.split(":");
                        }
                        if(currentLine.startsWith("To Date"))
                        {
                           todat=currentLine.split(":"); 
                        }
                        list.add(currentLine);
                    }
                } br.close() ;

               
              //------Get data from List and put into bean----------------------
                if(list!=null && list.size()>0)
                {   
                    //String strdt="";
                    for(int i=18;i<list.size();i++)
                    {
                     //-------validate if Record Already Uploaded----------------
                       if(list.get(i).length()>100)
                       {
                        
                          stat=conndb2.prepareStatement("select * from  mvxcdtshah.bankstmn where CHQ_DATE= ? and COMP_ID= ?");
                          strdt=(String)list.get(i).subSequence(126,140);
                          stat.setString(1, format.format(dateFormat.parse(strdt.trim())));
                          stat.setString(2, "111");
                          rs=stat.executeQuery();
                          if(rs.next())
                          {
                            count++;
                          }
                         else{
                                HDFTextFileUploadBean bean=new HDFTextFileUploadBean();
                                bean.setTRANS_DATE((String) list.get(i).subSequence(0, 10));
                                bean.setDESC((String) list.get(i).subSequence(20, 70));
                                bean.setAMOUNT((String) list.get(i).subSequence(70, 88));
                                bean.setCD_FLAG((String) list.get(i).subSequence(88, 94));
                                bean.setREF_NO((String) list.get(i).subSequence(94,126));
                                bean.setVAL_DATE((String) list.get(i).subSequence(126,140));
                                bean.setBRANCH_NAME((String) list.get(i).toString().subSequence(140,151));
                                OPENNING_BLANCE=opnamt[1].trim();
                                CLOSING_BALANCE=clsamt[1].trim();
                                TODATE=todat[1].trim();
                                HDFCFILE_LIST.add(bean);
                             }
                          if(stat!=null){stat.close();}
                       }// end of if
                     }
                     if (count>0){addActionError(count+"  "+"File uploaded already for date "+"  "+ strdt);}
               } // end of if----
              //if (count>0){addActionError(count+"  "+"File uploaded already for date "+"  "+ strdt);}
           }
           else
           {
             addActionMessage("Please select .txt file only !!!!!");
           }
        }
        catch(Exception e)
        {
          e.printStackTrace();
          //System.out.println(e.getMessage());
        }
        finally{
            if(conn!=null){conn.close();}
            if(conndb2!=null){conndb2.close();}
        }
      return SUCCESS;
    }
//------------------save data-------------------------------------------------
    public String save() throws SQLException
    {
        int flag=0;
        Connection conndb2 = null;
        Connection conn = null;
        PreparedStatement stat=null,stat1=null;
        String division=null;
        ResultSet rs=null;
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
         System.out.println("usridold"+usrid);
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return INPUT;
        }
        
        try {
            conn = new ConnectionShahiHrisNew().getConnection();
            conndb2 = new connectiondb2().getConnection();
            conn.setAutoCommit(false);
            stat1=conn.prepareStatement("select division from movex.fa_bank_code where  BANKNM=? and division=?");
            stat1.setString(1, fileName);
            stat1.setString(2, UNIT_CODE);
            rs=stat1.executeQuery();
            if(rs.next()){
            	division=rs.getString(1);
            }
            if(stat1!=null){
            	stat1.close();
            }
            if(rs!=null){
            	rs.close();
            }
            if(division==null){
            	addActionError("Bank id is not found in FA_BANK_CODE");
               if(conn!=null){
            	   conn.close();
               }
               if(conndb2!=null){
            	   conndb2.close();
               }
               return SUCCESS;
            }
          
           if(SAVE_FLAG!=null && SAVE_FLAG.equals("YES") && TRANS_DATE!=null)
           {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat( "yyyymmdd" );        

                int SN_ID=0;
                int MAX_ID=1;
                double TOTAL=Double.parseDouble(OPENNING_BLANCE.trim().replace(",",""));
                SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                SimpleDateFormat formatmm = new SimpleDateFormat("MM");
                String finyear=null;
                String mon=null;
                finyear=format.format(formatreq.parse(TODATE));  
                mon=formatmm.format(formatreq.parse(TODATE));   
                if (mon.equals("01") || mon.equals("02") || mon.equals("03") )
                {
                  finyear=(Integer.parseInt(finyear)-1)+"";
                }
                //------
                stat1=conn.prepareStatement("select BANKCODE from movex.fa_bank_code where division=? and BANKNM=?");
                stat1.setString(1, UNIT_CODE);
                stat1.setString(2, fileName);
                rs=stat1.executeQuery();
                if(rs.next()){
                	BANK_CODE=rs.getString(1);
                }
                if(stat1!=null){
                	stat1.close();
                }
                if(rs!=null){
                	rs.close();
                }
               //------Getting Max SR_NO----------------------
                stat1=conndb2.prepareStatement("select ifnull(max(SR_NO),0)SR_NO from  mvxcdtshah.bankstmn where COMP_ID= ? and YEAR= ? and BANK_CODE= ? and DIVISION= ?");
                stat1.setInt(1,111);
                stat1.setString(2,finyear);
                stat1.setString(3,BANK_CODE.trim());
                stat1.setString(4,UNIT_CODE);
                rs=stat1.executeQuery();
                if(rs.next())
                {
                   MAX_ID=rs.getInt("SR_NO");
                }
                if(stat1!=null){stat1.close();}
                if(rs!=null){rs.close();}
                
               for(int i=0;i<TRANS_DATE.size();i++)
               {
                    double VAL=0;
                    //SN_ID=i+1;
                    /*SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    SimpleDateFormat formatmm = new SimpleDateFormat("MM");
                    String mon=null;
                    String finyear=null;
                    finyear=format.format(formatreq.parse(VAL_DATE.get(i).toString().trim()));  
                    mon=formatmm.format(formatreq.parse(VAL_DATE.get(i).toString().trim()));   
                    if (mon.equals("01") || mon.equals("02") || mon.equals("03") )
                    {
                      finyear=(Integer.parseInt(finyear)-1)+"";
                    }*/
                   
                    stat=conndb2.prepareStatement("insert into mvxcdtshah.bankstmn(COMP_ID,YEAR,BANK_CODE,CHQ_DATE,CHQ_NO,CHQ_AMOUNT,COMM_CHQ_AMOUNT,DR_CR,CHQ_DESC,SR_NO,CHQ_AMOUNT_CR,LINE_DESC,DIVISION,SEH_USER,TDATE)"
                                              + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,current timestamp)");  
                    stat.setInt(1,111);
                    stat.setString(2,finyear);
                    stat.setString(3,BANK_CODE);
                    stat.setString(4,format1.format(dateFormat.parse(VAL_DATE.get(i).toString().trim())));
                    if(REF_NO.get(i).toString().trim().startsWith("HDFCR"))
                    {
                      stat.setString(5,(String) REF_NO.get(i).toString().trim().subSequence(8, REF_NO.get(i).toString().trim().length()));
                    }
                    else if(REF_NO.get(i).toString().trim().startsWith("CNRBR"))
                    {
                      stat.setString(5,(String) REF_NO.get(i).toString().trim().subSequence(8, REF_NO.get(i).toString().trim().length()));
                    }
                    else if(REF_NO.get(i).toString().trim().startsWith("4440U"))
                    {
                      stat.setString(5,(String) REF_NO.get(i).toString().trim().subSequence(6, REF_NO.get(i).toString().trim().length()));
                    }
                    else
                    {
                      stat.setString(5, REF_NO.get(i).toString().trim());
                    }
                   
                   if(CD_FLAG.get(i).toString().trim().equalsIgnoreCase("C"))
                   {
                     stat.setDouble(6, Double.parseDouble("-0"+AMOUNT.get(i).toString().trim()));
                     VAL=Double.parseDouble("-0"+AMOUNT.get(i).toString().trim());
                   }
                   else{
                     stat.setDouble(6, Double.parseDouble(AMOUNT.get(i).toString().trim()));
                     VAL=Double.parseDouble(AMOUNT.get(i).toString().trim());
                   }
                    if(i==0)
                    {
                       TOTAL=TOTAL+VAL;
                       stat.setDouble(7, TOTAL);
                    }
                    else{
                      
                        TOTAL=TOTAL+VAL; 
                        stat.setDouble(7, TOTAL);
                    }
                   
                    stat.setString(8, CD_FLAG.get(i).toString().trim()); 
                    stat.setString(9, DESC.get(i).toString().substring(0, 50));
                    //stat.setInt(10, i+1);
                    stat.setInt(10, MAX_ID+i);
                    stat.setDouble(11, 0.0);
                    stat.setString(12, DESC.get(i).toString().trim());
                    stat.setString(13, UNIT_CODE);
                    stat.setString(14, usrid);
                    flag= stat.executeUpdate();
                    SN_ID=MAX_ID+i;
                    if(stat!=null)stat.close();
               }
               
               if(flag>0){conn.commit();addActionMessage("Record saved !!!!!!!!!");}
              //------------Here saving Closing Balance and Todate only---------
               //if(flag>0 && CLOSING_BALANCE!=null && CLOSING_BALANCE.length()>0 && TODATE!=null && TODATE.length()>0)
              // {
                   /* SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    SimpleDateFormat formatmm = new SimpleDateFormat("MM");
                    String mon=null;
                    String finyear=null;
                    finyear=format.format(formatreq.parse(TODATE));  
                    mon=formatmm.format(formatreq.parse(TODATE));   
                    if (mon.equals("01") || mon.equals("02") || mon.equals("03") )
                    {
                      finyear=(Integer.parseInt(finyear)-1)+"";
                    }*/
                  
                 /* stat1=conn.prepareStatement("insert into movex.fa_bank_statement_dummy(COMP_ID,YEAR,BANK_CODE,CHQ_DATE,CHQ_DESC,SR_NO,COMM_CHQ_AMOUNT,SEH_USER,TDATE)values(?,?,?,to_date(?,'dd/mm/yyyy'),?,?,?,?,sysdate)");
                  stat1.setInt(1,111);
                  stat1.setString(2,finyear);
                  stat1.setString(3,BANK_CODE.trim());
                  stat1.setString(4,TODATE);
                  stat1.setString(5,"CLOSING BALANCE");
                  stat1.setInt(6,SN_ID+1);
                  stat1.setBigDecimal(7,new BigDecimal(CLOSING_BALANCE.replace(",", "")));
                  stat1.setString(8, usrid);
                  int flag1=stat1.executeUpdate();
                  
                  if(flag1>0){conn.commit();addActionMessage("Record saved !!!!!!!!!");}
                  if(stat!=null)stat.close();
                  if(stat1!=null)stat1.close();
               }*/
           }
            
        }
        catch(Exception e)
        {
          conn.rollback();
          e.printStackTrace();
        }
        finally
        {
            if(stat!=null)stat.close();
            if(stat1!=null)stat1.close();
            if(rs!=null){rs.close();}
            if(conn!=null) conn.close();
            if(conndb2!=null) conn.close();
        }
        return SUCCESS;
    }

    public String saveNew() throws SQLException
    {
        int flag=0;
        Connection conndb2 = null;
        Connection conn = null;
        PreparedStatement stat=null,stat1=null;
        ResultSet rs=null;
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String division=null;
       System.out.println("usridnew"+usrid);
        if (usrid == null) {
            addActionMessage("Session Not Valid 123!!");
            return INPUT;
        }
        
        try {
            conn = new ConnectionShahiHrisNew().getConnection();
            conndb2 = new ConnectionDB2HR().getConnection();
            conn.setAutoCommit(false);
            
            stat1=conn.prepareStatement("select division from movex.fa_bank_code where  BANKNM=? and division=?");
            stat1.setString(1, fileName);
            stat1.setString(2, UNIT_CODE);
            rs=stat1.executeQuery();
            if(rs.next()){
            	division=rs.getString(1);
            }
            if(stat1!=null){
            	stat1.close();
            }
            if(rs!=null){
            	rs.close();
            }
            if(division==null){
            	addActionError("Bank id is not found in FA_BANK_CODE");
               if(conn!=null){
            	   conn.close();
               }
               if(conndb2!=null){
            	   conndb2.close();
               }
               return SUCCESS;
            }
           if(SAVE_NEW!=null && SAVE_NEW.equals("YES") && TRANS_DATE!=null)
           {
               for(int i=0;i<TRANS_DATE.size();i++)
               {
                    SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                    SimpleDateFormat formatmm = new SimpleDateFormat("MM");
                    String mon=null;
                    String finyear=null;
                   if(FIN_YEAR!=null && FIN_YEAR.get(i).toString().length()>0)
                   {
                     finyear=FIN_YEAR.get(i).toString().trim();
                   }
                 else{
                	   finyear=VAL_DATE.get(i).toString().trim();
                   }
                   stat1=conn.prepareStatement("select BANKCODE from movex.fa_bank_code where division=? and BANKNM=?");
                   stat1.setString(1, UNIT_CODE);
                   stat1.setString(2, fileName);
                   rs=stat1.executeQuery();
                   if(rs.next()){
                   	BANK_CODE=rs.getString(1);
                   }
                   if(stat1!=null){
                   	stat1.close();
                   }
                   if(rs!=null){
                   	rs.close();
                   }
                   
                   stat1=conn.prepareStatement("select VSER,VONO from  movex.fa_bank_statement_dummy where YEAR= ? and VSER= ? and VONO= ? and DIVISION= ?");
                   stat1.setString(1, finyear);
                   stat1.setString(2, DESC.get(i).toString().trim());
                   stat1.setString(3, BRANCH_NAME.get(i).toString());
                   stat1.setString(4, UNIT_CODE);
                   rs=stat1.executeQuery();
                   if(rs.next()==false)
                   {
                        stat=conn.prepareStatement("insert into movex.fa_bank_statement_dummy(COMP_ID,YEAR,BANK_CODE,CHQ_DATE,CHQ_NO,COMM_CHQ_AMOUNT,CHQ_AMOUNT,VSER,VONO,SR_NO,DIVISION,SEH_USER,TDATE)"
                                                  + " values(?,?,?,to_date(?,'dd/mm/yyyy'),?,?,?,?,?,?,?,?,sysdate)");  
                        stat.setInt(1,222);
                        stat.setString(2,finyear);
                        stat.setString(3,BANK_CODE.trim());
                        stat.setString(4,VAL_DATE.get(i).toString().trim());
                        if(REF_NO.get(i).toString().trim().startsWith("4440U"))
                        {
                          stat.setString(5,(String) REF_NO.get(i).toString().trim().subSequence(6, REF_NO.get(i).toString().trim().length()));
                        }
                        else if(REF_NO.get(i).toString().trim().startsWith("N1"))
                        {
                          stat.setString(5,(String) REF_NO.get(i).toString().trim().subSequence(3, REF_NO.get(i).toString().trim().length()));
                        }
                        else
                        {
                          stat.setString(5, REF_NO.get(i).toString().trim());
                        }
                        stat.setDouble(6, 0.0);
                        stat.setDouble(7, Double.parseDouble(AMOUNT.get(i).toString().trim()));
                        stat.setString(8, DESC.get(i).toString().trim()); 
                        stat.setString(9, BRANCH_NAME.get(i).toString());
                        stat.setInt(10, i+1);
                        stat.setString(11,UNIT_CODE);
                        stat.setString(12, usrid);
                        flag= stat.executeUpdate();
                        if(stat!=null)stat.close();
                   }
                   if(stat1!=null)stat1.close();
               }
               if(flag>0){conn.commit();addActionMessage("Record saved !!!!!!!!!");}
               else{addActionError("Record not saved !!!!!!!");}
               //if(stat!=null)stat.close();
           }
            
        }
        catch(Exception e)
        {
          conn.rollback();
          e.printStackTrace();
        }
        finally
        {
            if(stat!=null)stat.close();
            if(stat1!=null)stat1.close();
            if(rs!=null){rs.close();}
            if(conn!=null) conn.close();
            if(conndb2!=null) conn.close();
        }
        return SUCCESS;
    }

 //--------------------setter and getter method--------------------------------
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public File getUnFile() {
        return unFile;
    }

    public void setUnFile(File unFile) {
        this.unFile = unFile;
    }

    public String getUnFileFileName() {
        return unFileFileName;
    }

    public void setUnFileFileName(String unFileFileName) {
        this.unFileFileName = unFileFileName;
    }

    public String getUnFileContentType() {
        return unFileContentType;
    }

    public void setUnFileContentType(String unFileContentType) {
        this.unFileContentType = unFileContentType;
    }

    public List getHDFCFILE_LIST() {
        return HDFCFILE_LIST;
    }

    public void setHDFCFILE_LIST(List HDFCFILE_LIST) {
        this.HDFCFILE_LIST = HDFCFILE_LIST;
    }

    public String getSAVE_FLAG() {
        return SAVE_FLAG;
    }

    public void setSAVE_FLAG(String SAVE_FLAG) {
        this.SAVE_FLAG = SAVE_FLAG;
    }

    public List getTRANS_DATE() {
        return TRANS_DATE;
    }

    public void setTRANS_DATE(List TRANS_DATE) {
        this.TRANS_DATE = TRANS_DATE;
    }

    public List getDESC() {
        return DESC;
    }

    public void setDESC(List DESC) {
        this.DESC = DESC;
    }

    public List getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(List AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public List getCD_FLAG() {
        return CD_FLAG;
    }

    public void setCD_FLAG(List CD_FLAG) {
        this.CD_FLAG = CD_FLAG;
    }

    public List getREF_NO() {
        return REF_NO;
    }

    public void setREF_NO(List REF_NO) {
        this.REF_NO = REF_NO;
    }

    public List getVAL_DATE() {
        return VAL_DATE;
    }

    public void setVAL_DATE(List VAL_DATE) {
        this.VAL_DATE = VAL_DATE;
    }

    public List getNEWHD_LIST() {
        return NEWHD_LIST;
    }

    public void setNEWHD_LIST(List NEWHD_LIST) {
        this.NEWHD_LIST = NEWHD_LIST;
    }

    public String getSAVE_NEW() {
        return SAVE_NEW;
    }

    public void setSAVE_NEW(String SAVE_NEW) {
        this.SAVE_NEW = SAVE_NEW;
    }

    public List getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public void setBRANCH_NAME(List BRANCH_NAME) {
        this.BRANCH_NAME = BRANCH_NAME;
    }

   
    public String getCLOSING_BALANCE() {
        return CLOSING_BALANCE;
    }

    public void setCLOSING_BALANCE(String CLOSING_BALANCE) {
        this.CLOSING_BALANCE = CLOSING_BALANCE;
    }

    public String getTODATE() {
        return TODATE;
    }

    public void setTODATE(String TODATE) {
        this.TODATE = TODATE;
    }

    public String getFROM_DATE() {
        return FROM_DATE;
    }

    public void setFROM_DATE(String FROM_DATE) {
        this.FROM_DATE = FROM_DATE;
    }

    public String getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(String TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public String getBANK_CODE() {
        return BANK_CODE;
    }

    public void setBANK_CODE(String BANK_CODE) {
        this.BANK_CODE = BANK_CODE;
    }

    public String getUNIT_CODE() {
        return UNIT_CODE;
    }

    public void setUNIT_CODE(String UNIT_CODE) {
        this.UNIT_CODE = UNIT_CODE;
    }

    public String getOPENNING_BLANCE() {
        return OPENNING_BLANCE;
    }

    public void setOPENNING_BLANCE(String OPENNING_BLANCE) {
        this.OPENNING_BLANCE = OPENNING_BLANCE;
    }

    public List getFIN_YEAR() {
        return FIN_YEAR;
    }

    public void setFIN_YEAR(List FIN_YEAR) {
        this.FIN_YEAR = FIN_YEAR;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
    
    
}
