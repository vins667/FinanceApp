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
import shahi.Action.database.ConnectionMovexBi;
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.Transport.Beans.FrtRateBean;
import shahi.Action.MvxExp.Transport.Beans.TrLaneBean;
 
  

public class TrLaneMastAction extends ActionSupport {
    private String currentdate;
    private String aausrid;
    private String searchtr;
    private String searchorg;
    private String searchdest;
    private String searchsize;
    private String viewFlag;
    private List showList;
   
    private List ShowDetail=new ArrayList();
    private List TRLIST = new ArrayList();
    private List ORGLIST = new ArrayList();
    private List DESTLIST = new ArrayList();
    private List SIZELIST = new ArrayList();
    private List FRTLIST = new ArrayList();
    
    @Override
    public String execute() {
        showList = new ArrayList();
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
            Connection connBI = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            try {
                connBI = new ConnectionMovexBi().getConnection();
                connBI.setAutoCommit(false);
                
            } catch (Exception e) { 
                System.out.println(e.toString());
            } // end catch
            PreparedStatement stat = null;
            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            PreparedStatement stat3 = null;
            
            ResultSet result = null;
            ResultSet result1 = null;
            ResultSet result2 = null;
            ResultSet result3 = null;
            
            try {
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
            
               stat=conn.prepareStatement(" select distinct idsunm||'-'||tr_code tr_name,tr_code from tr_lane_master a , cidmas_view_67 b where b.idcono=111 and a.tr_code=b.idsuno order by 1 ");
               result=stat.executeQuery();
               while (result.next())
               {  
                   TRLIST.add(new GetListBean(result.getString("tr_code"),result.getString("tr_name")));   
               }
               stat=conn.prepareStatement(" select distinct oaconm,trim(oaadk1) origin_code FROM CIADDR_VIEW_67 a , tr_lane_master b    WHERE OACONO=111 AND OAADTH=4 and trim(a.oaadk1)=b.origin_code order by 1 ");
               result=stat.executeQuery();
               while (result.next())
               { 
                   ORGLIST.add(new GetListBean(result.getString("origin_code"),result.getString("oaconm")));   
               }
               stat=conn.prepareStatement(" select distinct destn_code from tr_lane_master order by 1");
               result=stat.executeQuery();
               while (result.next())
               { 
                   DESTLIST.add(new GetListBean(result.getString("destn_code"),result.getString("destn_code")));   
               }
               stat=conn.prepareStatement(" select distinct TRK_SIZE from tr_lane_master order by 1");
               result=stat.executeQuery();
               while (result.next())
               {  
                   SIZELIST.add(new GetListBean(result.getString("TRK_SIZE"),result.getString("TRK_SIZE")));   
               } 
           
                if (viewFlag.equals("Yes")) 
                {  
                    stat = conn.prepareStatement("select ctrl_no,tr_code,origin_code,destn_code,trk_size,auction_yn,to_char(eff_date,'dd/mm/yyyy') eff_date,to_char(end_date,'dd/mm/yyyy') end_date from  tr_lane_master where tr_code like ? and origin_code like ? and destn_code like ? and trk_size like ? order by 2,3,4  ");
                    stat.setString(1,searchtr);
                    stat.setString(2,searchorg);
                    stat.setString(3,searchdest); 
                    stat.setString(4,searchsize);
                    result = stat.executeQuery(); 
                   String mparty = null;
                   String morigin=null;
                    while (result.next()) 
                    {  
                       
                        stat1=conn.prepareStatement("select oaadK1,oaconm FROM CIADDR_VIEW_67 WHERE OACONO=111 AND OAADTH=4 AND OAADK1=? ");
                        stat1.setString(1,result.getString("origin_code"));
                        result1=stat1.executeQuery();
                        if (result1.next())
                        {
                        morigin=result1.getString("oaconm");
                        }
                          
                        stat1=conn.prepareStatement("select idsunm FROM cidmas_view_67 WHERE IDCONO=111 AND IDSUNO=?");
                        stat1.setString(1,result.getString("tr_code"));
                        result1=stat1.executeQuery();
                        if (result1.next())
                        {
                        mparty=result1.getString("idsunm");
                        } 
                        stat1=conn.prepareStatement("select pros_date,to_char(pros_date,'dd-Mon-yyyy') prmnth,rate,hd_per,ctrl_no from tr_frtrate_dtls  WHERE  ctrl_no=? order by 1");
                        stat1.setString(1,result.getString("ctrl_no"));
                        result1=stat1.executeQuery();
                        while (result1.next())
                        {
                           FRTLIST.add(new FrtRateBean(result1.getString("ctrl_no"), result1.getString("prmnth"), result1.getString("rate"), result1.getString("hd_per")));
                         }     
                        
                        ShowDetail.add(new TrLaneBean(result.getString("ctrl_no"), result.getString("tr_code"), result.getString("origin_code"), result.getString("destn_code"), result.getString("trk_size"),result.getString("auction_yn"),mparty,morigin,result.getString("eff_date"),result.getString("end_date")));
                       
                    } 
                     
                     
                
                
                } // View Flg Close      
 

          
                      
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : TrLaneMastAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : TrLaneMastAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                     


                    if (stat1 != null) {
                        stat1.close();
                    }
                     
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                     
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : TrLaneMastAction.java Exception in finally block");
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

    public String getSearchtr() {
        return searchtr;
    }

    public void setSearchtr(String searchtr) {
        this.searchtr = searchtr;
    }

    public String getSearchorg() {
        return searchorg;
    }

    public void setSearchorg(String searchorg) {
        this.searchorg = searchorg;
    }

    public String getSearchdest() {
        return searchdest;
    }

    public void setSearchdest(String searchdest) {
        this.searchdest = searchdest;
    }

    public String getSearchsize() {
        return searchsize;
    }

    public void setSearchsize(String searchsize) {
        this.searchsize = searchsize;
    }

    public List getTRLIST() {
        return TRLIST;
    }

    public void setTRLIST(List TRLIST) {
        this.TRLIST = TRLIST;
    }

    public List getORGLIST() {
        return ORGLIST;
    }

    public void setORGLIST(List ORGLIST) {
        this.ORGLIST = ORGLIST;
    }

    public List getDESTLIST() {
        return DESTLIST;
    }

    public void setDESTLIST(List DESTLIST) {
        this.DESTLIST = DESTLIST;
    }

    public List getSIZELIST() {
        return SIZELIST;
    }

    public void setSIZELIST(List SIZELIST) {
        this.SIZELIST = SIZELIST;
    }

    public List getFRTLIST() {
        return FRTLIST;
    }

    public void setFRTLIST(List FRTLIST) {
        this.FRTLIST = FRTLIST;
    }
 
       
      
}
