/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.ReportFolder.EPM.beans.HDFTextFileUploadBean;
import shahi.Action.database.ConnectionDB2HR;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connectiondb2;
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

/**
 *
 * @author Amit
 */
public class YESBankFileUploadAction extends ActionSupport{
    
    private String aausrid;
    private File unFile;
    private String unFileFileName;
    private String unFileContentType;
    
    private List YESFILE_LIST;
    private List TRANS_DATE;
    private List DESC;
    private List AMOUNT;
    private List CD_FLAG;
    private List REF_NO;
    private List VAL_DATE;
    private List BRANCH_NAME;
    
    private String CLOSING_BALANCE;
    private String OPENNING_BLANCE;
    private String TODATE;
    
    private String FROM_DATE;
    private String TO_DATE;
    private String BANK_CODE;
    private String UNIT_CODE;
    
    private String SAVE_FLAG;
    
    public String mksess() throws SQLException, Exception
    {
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
            stat.setString(1,aausrid);
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
        return "success";
    }
//----------TXT FILE UPLOAD FUNCTION--------------------------------------------
    public String uploadFile() throws SQLException
    {
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs=null, rs2=null;
        YESFILE_LIST=new ArrayList();
        try {
              conn = new ConnectionShahiHrisNew().getConnection();
         } catch (Exception e) {
            System.out.println(e.toString());
            execute();
            return ERROR;
         }
        
        try
        {
            ActionContext ac= ActionContext.getContext();
            ServletContext sc=(ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
            String exe=unFileFileName.substring(unFileFileName.lastIndexOf(".")+1,unFileFileName.length());
            if(exe!=null && exe.toUpperCase().equalsIgnoreCase("TXT"))
            {
                ArrayList<String> list =new ArrayList<String> ();
                String currentLine;
                FileInputStream file = new FileInputStream(unFile); 
                BufferedReader br=new BufferedReader(new InputStreamReader(file));
               //-----Reading file and put into List----------------------------
                while((currentLine = br.readLine())!= null)
                {
                    String arr[]=currentLine.split(",");
                    HDFTextFileUploadBean bean=new HDFTextFileUploadBean();                   
                    long data=Long.parseLong(arr[7].trim());
                    bean.setTRANS_DATE(arr[8].trim());
                    bean.setDESC(String.valueOf(data).substring(4,7).trim());
                    bean.setBRANCH_NAME(String.valueOf(data).substring(7,String.valueOf(data).trim().length()).trim());
                    bean.setAMOUNT(arr[9].trim());
                    bean.setCD_FLAG(arr[0].trim());
                   if(arr[13].startsWith("YESBR520"))
                   {
                     bean.setREF_NO(arr[13].substring(8, arr[13].length()).trim());  
                   }
                   else if(arr[13].startsWith("N"))
                    {
                      bean.setREF_NO(arr[13].substring(4, arr[13].length()).trim());    
                    }
                   /* else if(arr[13].startsWith("N2"))
                    {
                      bean.setREF_NO(arr[13].substring(4, arr[13].length()).trim());    
                    }*/
                    else{
                      bean.setREF_NO(arr[13].trim());
                    }
                    bean.setVAL_DATE(String.valueOf(data).substring(0,4).trim());

                    YESFILE_LIST.add(bean);
               } br.close() ;
            }
            else
            {
              addActionMessage("Please select .txt file only !!!!!");
            }
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        finally{
            if(conn!=null){conn.close();}
        }
      return "success";
    }
    
    public String save() throws SQLException
    {
        int flag=0;
        Connection conn = null;
        PreparedStatement stat=null,stat1=null;
        ResultSet rs=null;
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return INPUT;
        }
        
        try {
            conn = new ConnectionShahiHrisNew().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            execute();
            return ERROR;
        }
        
        try
        {
           if(SAVE_FLAG!=null && SAVE_FLAG.equals("YES") && TRANS_DATE!=null)
           {
               for(int i=0;i<TRANS_DATE.size();i++)
               {
                   //String finyear=null;
                   //finyear=(Integer.parseInt(VAL_DATE.get(i).toString().trim())-1)+"";
                   stat1=conn.prepareStatement("select VSER,VONO from  movex.fa_bank_statement_dummy where COMP_ID=222 AND YEAR= ? and VSER= ? and VONO= ? and DIVISION= ?");
                   stat1.setString(1, VAL_DATE.get(i).toString().trim());
                   stat1.setString(2, DESC.get(i).toString().trim());
                   stat1.setString(3, BRANCH_NAME.get(i).toString());
                   stat1.setString(4, UNIT_CODE);
                   rs=stat1.executeQuery();
                   if(rs.next()==false)
                   {
                        stat=conn.prepareStatement("insert into movex.fa_bank_statement_dummy(COMP_ID,YEAR,BANK_CODE,CHQ_DATE,CHQ_NO,COMM_CHQ_AMOUNT,CHQ_AMOUNT,VSER,VONO,SR_NO,DIVISION,SEH_USER,TDATE)"
                                                  + " values(?,?,?,to_date(?,'dd/mm/yyyy'),?,?,?,?,?,?,?,?,sysdate)");  
                        stat.setInt(1,222);
                        stat.setString(2,VAL_DATE.get(i).toString().trim());
                        stat.setString(3,BANK_CODE.trim());
                        stat.setString(4,TRANS_DATE.get(i).toString().trim());
                        if(REF_NO.get(i).toString().trim().startsWith("YESBR"))
                        {
                          stat.setString(5,(String) REF_NO.get(i).toString().trim().subSequence(8, REF_NO.get(i).toString().trim().length()));
                        }
                        else if(REF_NO.get(i).toString().trim().startsWith("N1") || REF_NO.get(i).toString().trim().startsWith("N2"))
                        {
                          stat.setString(5,(String) REF_NO.get(i).toString().trim().subSequence(3, REF_NO.get(i).toString().trim().length()));
                        }
                        else
                        {
                          stat.setString(5,REF_NO.get(i).toString().trim());
                        }
                        stat.setDouble(6, 0.0);
                        stat.setDouble(7, Double.parseDouble(AMOUNT.get(i).toString().trim()));
                        stat.setString(8, DESC.get(i).toString().trim()); 
                        stat.setString(9, BRANCH_NAME.get(i).toString().trim());
                        stat.setInt(10,i+1);
                        stat.setString(11,UNIT_CODE);
                        stat.setString(12, usrid);
                        flag= stat.executeUpdate();
                       if(stat!=null)stat.close();
                   }
               }
               if(flag>0){conn.commit();addActionMessage("Record saved !!!!!!!!!");}
               else{addActionError("Record not saved !!!!!!!");}
               if(stat!=null)stat.close();
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
        }
        return SUCCESS;
    }
 //--------------setter and getter method----------------------------------------
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

    public List getYESFILE_LIST() {
        return YESFILE_LIST;
    }

    public void setYESFILE_LIST(List YESFILE_LIST) {
        this.YESFILE_LIST = YESFILE_LIST;
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

    public List getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public void setBRANCH_NAME(List BRANCH_NAME) {
        this.BRANCH_NAME = BRANCH_NAME;
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

    public String getSAVE_FLAG() {
        return SAVE_FLAG;
    }

    public void setSAVE_FLAG(String SAVE_FLAG) {
        this.SAVE_FLAG = SAVE_FLAG;
    }
    
    
    
}
