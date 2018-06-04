 
package shahi.Action.MvxExp.Transport; 

  

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
 
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Transport.Beans.TrLaneBean;
  

public class TrFrtProsAction extends ActionSupport {
    private String currentdate;
    private String aausrid;
    private String searchstate;
    private String searchtype;
    private String start_date;
    private String viewFlag;
    private String saveFlag;
    private String lockFlag;
    private List showList;
    private String STATE_CODE;
    private String TYPE_CODE;
    private String[] saverec;
    private String LAST_PROS;
    private String PR_DATE;
    private List ShowDetail=new ArrayList();
    private List stateList = new ArrayList();
    private List typeList  = new ArrayList();
    
    
    @Override
    public String execute() {
        showList = new ArrayList();
        typeList = new ArrayList();
        stateList= new ArrayList();
     try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }
        
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
     //   aausrid = "227350";
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
       
            PreparedStatement stat = null;
            
            
            ResultSet result = null;
            
            
            try {
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
            
              typeList.add("DIESEL");
              typeList.add("PETROL");
              typeList.add("CNG"); 
              
              
              stateList.add("KA");
              stateList.add("DL"); 
              stateList.add("UP");
              stateList.add("HR");
           
                if (viewFlag!=null && viewFlag.length()>0) 
                {  
                    
                    stat = conn.prepareStatement("select pros_date prdate,STATE_CODE,FUEL_TYPE,to_char(pros_DATE,'dd-Mon-yyyy') pros_date,prv_price,cr_price,price_var,hd_given,hike_per,LOCK_YN,FIRST_HIKE_PER from tr_frt_pros  where state_code like ? AND fuel_type like ? order by 1,2,3");
                    stat.setString(1,searchstate);
                    stat.setString(2,searchtype);
                    result = stat.executeQuery(); 
                   
                     while (result.next()) 
                    {  
                           
                        ShowDetail.add(new TrLaneBean(result.getString("STATE_CODE"), result.getString("FUEL_TYPE"), result.getString("pros_date"),  result.getString("prv_price"),result.getString("cr_price"), result.getString("price_var"),result.getString("hike_per"),result.getString("hd_given"),result.getString("LOCK_YN"),result.getString("first_hike_per")));
                    } 
                  } // View Flg Close      
          
                 stat = conn.prepareStatement("select TO_CHAR(MAX(pros_date),'DD-MON-YYYY') prdate,to_char(last_day(max(pros_date)+1),'dd-Mon-yyyy') pr_month from tr_frt_pros where lock_yn='YES' ");
                 result=stat.executeQuery();
                 while (result.next())
                 {
                   LAST_PROS=(result.getString("prdate"));
                   PR_DATE=result.getString("pr_month");
                 }
                if (saveFlag!=null && saveFlag.length()>0) 
                {  
                 
                        CallableStatement insertRecordStat =conn.prepareCall("{call  Exports.tr_frthike(?,?,?,?)}");
                           insertRecordStat.setString(1,STATE_CODE);
                           insertRecordStat.setString(2,TYPE_CODE);
                           insertRecordStat.setString(3,PR_DATE);
                           insertRecordStat.setString(4,usrid);
                           insertRecordStat.executeUpdate();
                    
                           if (insertRecordStat!=null) { insertRecordStat.close();  insertRecordStat=null; }
               
                }  
                if (lockFlag!=null && lockFlag.length()>0) 
                {
                    System.out.println("lockflag "+lockFlag);
                for(int i=0; i<saverec.length; i++)
                   { 
                      if(saverec[i].length()!=0)
                       {
                         int in1= saverec[i].indexOf("!");
                          String va1=saverec[i].substring(0,in1);
                         int in2=saverec[i].indexOf("~");
                          String va2=saverec[i].substring(in1+1,in2);
                          String va3=saverec[i].substring(in2+1);
                             
                             stat = conn.prepareStatement("update exports.tr_frt_pros set lock_yn='YES' where STATE_CODE=? AND FUEL_TYPE=? AND PROS_DATE=? " );
                             stat.setString(1,va1);
                             stat.setString(2,va2);
                             stat.setString(3,va3);
                             stat.executeUpdate();
                             
                             
                            CallableStatement insertRecordStat =conn.prepareCall("{call  Exports.tr_frtrate_pros(?,?,?,?)}");
                            insertRecordStat.setString(1,va1);
                            insertRecordStat.setString(2,va2);
                            insertRecordStat.setString(3,va3);
                            insertRecordStat.setString(4,usrid);
                            insertRecordStat.executeUpdate();

                            if (insertRecordStat!=null) { insertRecordStat.close();  insertRecordStat=null; }

                             
                             
                             conn.commit();
                           
                       }
                   }
                
                }
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : TrfrtProsAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : TrfrtProsAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                   
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                      stat =null;
                  } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : TrfrtProsAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
            
            addActionMessage("Records Save(s) !!");
            
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");
             
            return ERROR;
        }
    }

    double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
}

    public String getAausrid() {
        return aausrid;
    } 

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    
    public List getShowList() {
        return showList;
    }

    public void setShowList(List showList) {
        this.showList = showList;
    }

    public String getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(String viewFlag) {
        this.viewFlag = viewFlag;
    }

  
 
    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public List getShowDetail() {
        return ShowDetail;
    }

      
    public void setShowDetail(List ShowDetail) {
        this.ShowDetail = ShowDetail;
    }

    

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getSearchstate() {
        return searchstate;
    }

    public void setSearchstate(String searchstate) {
        this.searchstate = searchstate;
    }

    public List getStateList() {
        return stateList;
    }

    public void setStateList(List stateList) {
        this.stateList = stateList;
    }

    public List getTypeList() {
        return typeList;
    }

    public void setTypeList(List typeList) {
        this.typeList = typeList;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getSTATE_CODE() {
        return STATE_CODE;
    }

    public void setSTATE_CODE(String STATE_CODE) {
        this.STATE_CODE = STATE_CODE;
    }

    public String getTYPE_CODE() {
        return TYPE_CODE;
    }

    public void setTYPE_CODE(String TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public String getLAST_PROS() {
        return LAST_PROS;
    }

    public void setLAST_PROS(String LAST_PROS) {
        this.LAST_PROS = LAST_PROS;
    }

    public String getPR_DATE() {
        return PR_DATE;
    }

    public void setPR_DATE(String PR_DATE) {
        this.PR_DATE = PR_DATE;
    }

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String[] getSaverec() {
        return saverec;
    }

    public void setSaverec(String[] saverec) {
        this.saverec = saverec;
    }

    
      
}
