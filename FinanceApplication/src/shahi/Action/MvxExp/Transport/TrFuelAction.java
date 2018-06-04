/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
  

public class TrFuelAction extends ActionSupport {
    private String currentdate;
    private String aausrid;
    private String searchstate;
    private String searchtype;
    private String start_date;
    private String viewFlag;
    private String saveFlag;
    private List showList;
    private String STATE_CODE;
    private String TYPE_CODE;
    private String PRICE;
    private String EFF_DATE;
   
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
                    stat = conn.prepareStatement("select eff_date efd,STATE_CODE,FUEL_TYPE,to_char(EFF_DATE,'dd/mm/yyyy') eff_date,to_char(end_date,'dd/mm/yyyy') end_date,price  from TR_FUEL_PRICE  where state_code like ? AND fuel_type like ? order by 1,2,3");
                
                    stat.setString(1,searchstate);
                    stat.setString(2,searchtype);
                     result = stat.executeQuery(); 
                   
                     while (result.next()) 
                    {  
                          
                        ShowDetail.add(new TrLaneBean(result.getString("FUEL_TYPE"), result.getString("STATE_CODE"), result.getString("price"), result.getString("EFF_DATE"), result.getString("END_DATE"),"","","","",""));
                    } 
                  } // View Flg Close      
          
                if (saveFlag!=null && saveFlag.length()>0) 
                {  
                     stat=conn.prepareStatement("select * from tr_fuel_price where state_code=? and fuel_type=? and end_date is null");
                     stat.setString(1,STATE_CODE);
                     stat.setString(2,TYPE_CODE);
                     result=stat.executeQuery();
                     if (result.next())
                             { 
                                  stat=conn.prepareStatement(" update tr_fuel_price set end_date=to_date(?,'yyyy-mm-dd')-1 where state_code=? and fuel_type=? and end_date is null");
                                  stat.setString(1,EFF_DATE.substring(0,10));
                                  stat.setString(2,STATE_CODE);
                                  stat.setString(3,TYPE_CODE);
                                  stat.executeUpdate();
                           
                                  stat=conn.prepareStatement("insert into tr_fuel_price (STATE_CODE,FUEL_TYPE,price,EFF_DATE,tdate,user_id) values (?,?,?,to_date(?,'yyyy-mm-dd'),sysdate,?)");
                                  stat.setString(1,STATE_CODE);
                                  stat.setString(2,TYPE_CODE);
                                  stat.setString(3,PRICE);
                                   stat.setString(4,EFF_DATE.substring(0,10));
                                  stat.setString(5,usrid);
                                  stat.executeUpdate();
                                  flag=1;
                             }else{ 
                                  stat=conn.prepareStatement("insert into tr_fuel_price (STATE_CODE,FUEL_TYPE,price,EFF_DATE,tdate,user_id) values (?,?,?,to_date(?,'yyyy-mm-dd'),sysdate,?)");
                                  stat.setString(1,STATE_CODE);
                                  stat.setString(2,TYPE_CODE);
                                  stat.setString(3,PRICE);
                                  stat.setString(4,EFF_DATE.substring(0,10));
                                  stat.setString(5,usrid);
                                  stat.executeUpdate();
                                  flag=1;
                                 }
                }  
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : TrFuelAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : TrFuelAction.java" + e);

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
                    System.out.print("File Name : TrFuelAction.java Exception in finally block");
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
            PRICE=null;
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

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getEFF_DATE() {
        return EFF_DATE;
    }

    public void setEFF_DATE(String EFF_DATE) {
        this.EFF_DATE = EFF_DATE;
    }

 
    
    
      
}
