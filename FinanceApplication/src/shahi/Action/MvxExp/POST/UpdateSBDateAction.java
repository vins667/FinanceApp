package shahi.Action.MvxExp.POST;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*; 
import java.sql.Date;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import shahi.Action.MvxExp.BuyerInv.Beans.SearchListBean;
import shahi.Action.MvxExp.POST.Beans.UpdateSBHistryListBean;
import shahi.Action.MvxExp.POST.Beans.UpdateSBListBean;
import shahi.Action.database.connectiondb2;
 

public class UpdateSBDateAction extends ActionSupport {

    private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private String INVOICE_S; 
    private String YEAR;
    private String COMPANY;
    private String INVOICENO;
    private String INVOICEDATE;
    private String BUYER;
    private String BUYER_DESC;
    private String BUYER_ADDR;
    private String FLAG1;
    private String FLAG2;
    private String aausrid;
    private String CBM;
    private String ACCESSTYPE;
    private ByteArrayInputStream inputStream;
    private List listdata = new ArrayList();
    private List updatedatalist= new ArrayList();
    private List updatehistoryList= new ArrayList();
    private String SEARCH_CODE;
    private String NEWSB_DATE;
    private String CLMDATE;
    private String PORT1;
    private String ADMISABLE;
    private String RECECD;
    private String SUPPLYRCVD;
    private String ADJSTD;
    private String MISCAMT;
    private String DBKWOFF;
    private String STRDUE;
    private String STRRECV;
    private String WOFF;
    
   
    private List SearchList=new ArrayList();
    
    
    private String FLG1;
    private String FLG2;
    private String FLG3;
    private String FLG4;
    
    private String SHIPBILNO;
    private String CLRANCEPORT;
    private String LODINGPORT;
    private String MODESHIP;
    private String FRWRD;
    private String CHA;
    
    
   
     private List INV_L;
     private List CRNCY_L;
     private List FOBAMT_L;
     private List QNTY_L;
     private List DBK_L;
     private List INVCONV_L;
     private List DBKCONV_L;
     private List LEEDATE_L;
     private List AWBDATE_L;
     private List FINDATE_L;
     private List MODE_L;
     private List CHA_L;
     private List FWD_L;
     private List CLRPORT_L;
     private List LOADPORT_L;
    
    
     private String ITMIDL;
     private double QTYID;
     private String invid;
     
     private List ITEM_NO;
     private List ITEM_ID;
     private List ITEM_RATIO;
     private List ITEM_PCKGS;
     private List ITEM_QTY;
     private double TOTITMQTY;
     
     private List ModeList=new ArrayList();
     private String SHIPBILDATE;
     
     
     private String FRWRDCODE;
     private String CHACODE;
     private String LODINGCODE;
     private String CLRANCODE;
    
     
     
    @Override
    public String execute() throws SQLException {
        Connection conn = null;
        PreparedStatement stat2 = null;
                ResultSet result2 = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch  
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        //LOCATION_CODE = "100";
         if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        

        try {
            
            SimpleDateFormat fromUser  = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat myFormat  = new SimpleDateFormat("dd-MMM-yyyy");
                //String date2=myFormat.format(fromUser.parse(SHIPBILDATE));
            
           
                stat2 = conn.prepareStatement("select * from pa_auth_mast where prog_name='SBUPDATE' and user_id=?");
                stat2.setString(1,aausrid);
                result2 = stat2.executeQuery();
                if(result2.next()) {
                    FLAG2="YES";
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
            System.out.println("Flag "+FLAG2); 
               
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
                
            
            
            if (FLAG1.equals("Yes") && FLAG1.length() > 0) {
                stat2 = conn.prepareStatement("select to_char(LAST_CLAIM_DATE,'dd-MM-yyyy') LAST_CLAIM_DATE,CLAIM_PORT,DBK_ADMISABLE,DBK_RECEIVED,DBK_SUPL_RECV,DBK_ADJUSTED,DBK_MISC_AMOUNT,WOFF_AMT,STR_DUE,STR_RECV,STR_WOFF from ei_dbk_mast where SHP_BILL_NO=? and SHP_BILL_DATE=?");
                stat2.setString(1, INVOICE_S.trim());
                stat2.setString(2, SHIPBILDATE);
                result2 = stat2.executeQuery(); 
                if (result2.next()) {

                    CLMDATE = result2.getString("LAST_CLAIM_DATE");
                    PORT1 = result2.getString("CLAIM_PORT");
                    ADMISABLE = result2.getString("DBK_ADMISABLE");
                    RECECD = result2.getString("DBK_RECEIVED");
                    SUPPLYRCVD = result2.getString("DBK_SUPL_RECV");
                    ADJSTD = result2.getString("DBK_ADJUSTED");
                    MISCAMT = result2.getString("DBK_MISC_AMOUNT");
                    DBKWOFF = result2.getString("WOFF_AMT");
                    STRDUE = result2.getString("STR_DUE");
                    STRRECV = result2.getString("STR_RECV");
                    WOFF = result2.getString("STR_WOFF");
                    
                    if (result2.getDouble("DBK_RECEIVED")>0)
                    {
                       FLAG2=null;
                    }
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }

                
                

                stat2 = conn.prepareStatement("select c.excs_inv_no,b.CURRENCY,b.FOB_AMT,b.ship_qnty,b.inr_conv,b.dbk_conv,to_char(b.LET_EXP_DATE,'dd-MM-yyyy') LET_EXP_DATE,to_char(c.AWBDATE,'dd-MM-yyyy') AWBDATE,"
                        + "to_char(c.FIN_DATE,'dd-MM-yyyy') FIN_DATE,c.MODE_OF_SHIP,c.AGENT,c.FWD_CODE,c.LOADING_PORT load_port,c.LOADING clr_port from ei_shipment_dtls  b,ei_endors_mast c where b.year=c.year and b.company=c.company  and b.inv_no=c.inv_no and c.t_mod='LGM4' AND  b.SHP_BILL_NO=? and b.SHP_BILL_DATE=? order by 1,2");
                stat2.setString(1, INVOICE_S.trim());
                stat2.setString(2,SHIPBILDATE);
                result2 = stat2.executeQuery();
                int p=0;
                while (result2.next()) {
                   ++p;
                    updatedatalist.add(new UpdateSBListBean(result2.getString("excs_inv_no"), result2.getString("CURRENCY"), result2.getString("FOB_AMT"),
                            result2.getString("ship_qnty"), result2.getString("inr_conv"),
                            result2.getString("dbk_conv"), result2.getString("LET_EXP_DATE"),result2.getString("AWBDATE"), result2.getString("FIN_DATE"),
                            result2.getString("MODE_OF_SHIP"),result2.getString("AGENT"),result2.getString("FWD_CODE"),result2.getString("clr_port"),
                            result2.getString("load_port")));  
                }
                   if(p==0) {
                    INV_L = null;
                    CRNCY_L = null;
                    FOBAMT_L = null;
                    QNTY_L = null;
                    DBK_L = null;
                    INVCONV_L = null;
                    DBKCONV_L = null;
                    LEEDATE_L = null;
                    AWBDATE_L = null;
                    FINDATE_L = null;
                    MODE_L = null;
                    CHA_L = null;
                    FWD_L = null;
                    CLRPORT_L = null;
                    LOADPORT_L = null;
                   }
            
                
                   
                
                    INV_L = null;
                    CRNCY_L = null;
                    FOBAMT_L = null;
                    QNTY_L = null;
                    DBK_L = null;
                    INVCONV_L = null;
                    DBKCONV_L = null;
                    LEEDATE_L = null;
                    AWBDATE_L = null;
                    FINDATE_L = null;
                    MODE_L = null;
                    CHA_L = null;
                    FWD_L = null;
                    CLRPORT_L = null;
                    LOADPORT_L=null;
                   
                
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
                   
                stat2 = conn.prepareStatement("select EXCS_INV,OLD_SB,OLD_PORT,OLD_PORT_LOAD,OLD_CHA,OLD_FWD,OLD_MOS,to_char(old_sbdate,'dd/mm/yyyy') old_sbdate,SEH_USER,to_char(TDATE,'dd-MM-yyyy hh24:mi') TDATE,NEW_SB,NEW_PORT_LOAD,NEW_CHA,NEW_FWD,NEW_MOS,new_port,to_char(new_sbdate,'dd/mm/yyyy') new_sbdate from EI_SHPBILL_HIST where  (OLD_SB=? or NEW_SB=?) and (OLD_SBDATE=? or NEW_SBDATE=?)");
                stat2.setString(1, INVOICE_S.trim());
                stat2.setString(2, INVOICE_S.trim());
                stat2.setString(3, SHIPBILDATE);
                stat2.setString(4, SHIPBILDATE);
                result2 = stat2.executeQuery();
                int s=0;
                while (result2.next()) {
                    ++s;
                  updatehistoryList.add(new UpdateSBHistryListBean(result2.getString("EXCS_INV"),result2.getString("OLD_SB"),result2.getString("OLD_PORT"),result2.getString("OLD_PORT_LOAD"),result2.getString("OLD_CHA"),result2.getString("OLD_FWD"),result2.getString("OLD_MOS"),result2.getString("SEH_USER"),result2.getString("TDATE"),result2.getString("NEW_SB"),result2.getString("NEW_PORT"),result2.getString("NEW_PORT_LOAD"),result2.getString("NEW_CHA"),result2.getString("NEW_FWD"),result2.getString("NEW_MOS"),result2.getString("OLD_SBDATE"),result2.getString("NEW_SBDATE")));  
                }
                 
                    
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
                    
                }
                    INV_L = null;
                    CRNCY_L = null;
                    FOBAMT_L = null;
                    QNTY_L = null;
                    DBK_L = null;
                    INVCONV_L = null;
                    DBKCONV_L = null;
                    LEEDATE_L = null;
                    AWBDATE_L = null;
                    FINDATE_L = null;
                    MODE_L = null;
                    CHA_L = null;
                    FWD_L = null;
                    CLRPORT_L = null;
                    LOADPORT_L = null;
                    
                     
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
              conn.close();
            } 
        }


        return SUCCESS;
    }
    
    public String mod() throws SQLException {
        Connection conn = null;
       
        PreparedStatement stat4 = null;
        ResultSet result4 = null;
       
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch   


        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       // LOCATION_CODE = "100";
       // usrid="227350";
        
        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
                SimpleDateFormat fromUser  = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat myFormat  = new SimpleDateFormat("dd-MMM-yyyy");
                
                
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0, z = 0;
           
            String oldloadingport = "";
            String oldclrport="";
            String oldcha = "";
            String oldfwd = "";
            String oldmode = "";
            String excsinvno = "";
            
            
                        
                 if((SHIPBILNO!=null && SHIPBILNO.length()>0) ||  (CLRANCODE!=null  && CLRANCODE.length()>0)  || (CHACODE!=null && CHACODE.length()>0 ) || (FRWRDCODE!=null && FRWRDCODE.length()>0)  || (LODINGCODE!=null && LODINGCODE.length()>0))
                 {
                        stat = conn.prepareStatement("select excs_inv_no,loading,loading_port,mode_of_ship,agent,fwd_code from ei_endors_mast where (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls where  shp_bill_no=? and shp_bill_date=? )");
                        stat.setString(1, INVOICE_S.trim());
                        stat.setString(2, SHIPBILDATE);
                        result = stat.executeQuery();
                        if (result.next()) {
                           excsinvno=result.getString("excs_inv_no");
                           oldloadingport=result.getString("loading_port");
                           oldclrport=result.getString("loading");
                           oldcha=result.getString("agent");
                           oldfwd=result.getString("fwd_code");
                           oldmode=result.getString("mode_of_ship");
                        }
                        if (stat != null) {
                        stat.close();
                        }
                        if (result != null) {
                            result.close();
                        }
 
             
                        
                        
                   if(SHIPBILNO!=null && SHIPBILNO.length()>0){
                       
                            stat4 = conn.prepareStatement("update ei_shipment_dtls set shp_bill_no=? where T_MOD='LGM4' AND shp_bill_no=? and shp_bill_date=?");
                            stat4.setString(1, SHIPBILNO.trim());
                            stat4.setString(2, INVOICE_S.trim() );
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                            if (stat4 != null) {
                         stat4.close();
                        }
                            stat4 = conn.prepareStatement("update ei_dbk_chq_dtls set shp_bill_no=? where shp_bill_no=? and shp_bill_date=?");
                            stat4.setString(1, SHIPBILNO.trim());
                            stat4.setString(2, INVOICE_S.trim());
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                            if (stat4 != null) {
                         stat4.close();
                        }
                            stat4 = conn.prepareStatement("update ei_sbill_track set shp_bill_no=? where shp_bill_no=? and shp_bill_date=?");
                            stat4.setString(1, SHIPBILNO.trim());
                            stat4.setString(2, INVOICE_S);
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                       if (stat4 != null) {
                         stat4.close();
                        }
              }
                
           if(CLRANCODE!=null && CLRANCODE.length()>0){
                       
                            stat4 = conn.prepareStatement("update ei_dbk_mast set claim_port=? where shp_bill_no=? and shp_bill_date=?");
                            stat4.setString(1, CLRANCODE.trim());
                            stat4.setString(2, INVOICE_S.trim());
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                            if (stat4 != null) {
                         stat4.close();
                        }
                            stat4 = conn.prepareStatement("update ei_endors_mast set loading=? where T_MOD='LGM4' AND (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls where shp_bill_no=? and shp_bill_date=?)");
                            stat4.setString(1, CLRANCODE.trim());
                            stat4.setString(2, INVOICE_S.trim());
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                            if (stat4 != null) {
                         stat4.close();
                        }
             
                 }  
               
           
           if(LODINGCODE!=null && LODINGCODE.length()>0){
                       
                            stat4 = conn.prepareStatement("update ei_endors_mast set loading_port=? where T_MOD='LGM4' AND (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls where shp_bill_no=? and shp_bill_date=?)");
                            stat4.setString(1, LODINGCODE.trim());
                            stat4.setString(2, INVOICE_S.trim());
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                            if (stat4 != null) {
                         stat4.close();
                        }
                            
                
          }  
                
            
           if(FRWRDCODE!=null && FRWRDCODE.length()>0){
                       
                            stat4 = conn.prepareStatement("update ei_endors_mast set fwd_code=? where T_MOD='LGM4' AND (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls where shp_bill_no=? and shp_bill_date=?)");
                            stat4.setString(1, FRWRDCODE.trim());
                            stat4.setString(2, INVOICE_S.trim());
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                            if (stat4 != null) {
                         stat4.close();
                        }
                            
                
          } 
           
           if(CHACODE!=null && CHACODE.length()>0){
                       
                            stat4 = conn.prepareStatement(" update ei_endors_mast set agent=? where T_MOD='LGM4' AND (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls where shp_bill_no=? and shp_bill_date=?)");
                            stat4.setString(1, CHACODE.trim());
                            stat4.setString(2, INVOICE_S.trim());
                            stat4.setString(3, SHIPBILDATE);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                            }
                            if (stat4 != null) {
                         stat4.close();
                        }
                            
               
             } 
           
           stat4 = conn.prepareStatement("insert into  EI_SHPBILL_HIST(EXCS_INV,OLD_SB,OLD_SBDATE,OLD_PORT,old_port_load,OLD_CHA,OLD_FWD,OLD_MOS,NEW_SB,NEW_SBDATE,NEW_PORT_LOAD,NEW_CHA,NEW_FWD,NEW_MOS,new_port,TDATE,SEH_USER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?)");
                            stat4.setString(1, excsinvno.trim());
                            stat4.setString(2, INVOICE_S.trim());
                            stat4.setString(3, SHIPBILDATE);
                            stat4.setString(4, oldclrport);
                            stat4.setString(5, oldloadingport);
                            stat4.setString(6, oldcha );
                            stat4.setString(7, oldfwd );
                            stat4.setString(8, oldmode);
                            stat4.setString(9, SHIPBILNO.trim());
                            stat4.setString(10, SHIPBILDATE);
                            stat4.setString(11, LODINGCODE);
                            stat4.setString(12, CHACODE);
                            stat4.setString(13, FRWRDCODE);
                            stat4.setString(14, MODESHIP);
                            stat4.setString(15,CLRANCODE);
                            stat4.setString(16, usrid);
                            z = stat4.executeUpdate();
                              if (stat4 != null) {
                                stat4.close();
                              }
           
               if (z > 0) {
                            conn.commit();
                            addActionMessage("Record Updated succcessfully ");
                    
                }
          }   
          
          if (NEWSB_DATE!=null && NEWSB_DATE.length()>0)
            {
                     System.out.println("sbdate "+NEWSB_DATE+" Sb no "+SHIPBILNO);
                           Double dbkconv=0.0;
                           stat4=conn.prepareStatement("select exp_rate from ei_exchange_rate_mast a,ei_shipment_dtls b where a.currency=b.currency and ? between begin_date and end_date and shp_bill_no=? and shp_bill_date=?");        
                           stat4.setString(1,NEWSB_DATE);
                           stat4.setString(2,INVOICE_S.trim());
                           stat4.setString(3, SHIPBILDATE);
                           result4=stat4.executeQuery();
                           if (result4.next())
                           {
                              dbkconv=result4.getDouble("exp_rate");
                           }
                           if (dbkconv<1){
                               addActionMessage("Check DBK Conv  "+dbkconv);
                               return ERROR;
                           }
                             
                                stat4 = conn.prepareStatement("insert into  EI_SHPBILL_HIST(OLD_SB,OLD_SBDATE,NEW_SB,NEW_SBDATE,TDATE,SEH_USER) values(?,?,?,?,sysdate,?)");
                                stat4.setString(1, INVOICE_S.trim());
                                stat4.setString(2, SHIPBILDATE);
                                stat4.setString(3,INVOICE_S.trim());
                                stat4.setString(4, NEWSB_DATE);
                                stat4.setString(5, usrid);
                                z = stat4.executeUpdate();
                                if (stat4 != null) {stat4.close();}
                                  
                                  
                                 stat=conn.prepareStatement("update ei_shipment_dtls set shp_bill_date=?,dbk_conv=? where trim(shp_bill_no)=? and shp_bill_date=?");
                                 stat.setString(1,NEWSB_DATE);
                                 stat.setDouble(2,dbkconv);
                                 stat.setString(3,INVOICE_S.trim());
                                 stat.setString(4, SHIPBILDATE);
                                 z=stat.executeUpdate();
                                 if (stat!= null) {stat.close();
                                 
                                 }
                                   
                                 stat=conn.prepareStatement("update ei_shipment_dtls set dbk_slno=null where trim(shp_bill_no)=? and shp_bill_date=?");
                                 stat.setString(1,INVOICE_S.trim());
                                 stat.setString(2,NEWSB_DATE);
                                 z=stat.executeUpdate();
                                 if (stat!= null) {stat.close();}
                                 conn.commit();
                                 
                                 
                                 stat=conn.prepareStatement("update ei_dbk_mast set dbk_admisable=0,dbk_recal_date=sysdate,seh_user=? where trim(shp_bill_no)=? and shp_bill_date=? ");
                                 stat.setString(1,usrid);
                                 stat.setString(2, INVOICE_S.trim());
                                 stat.setString(3,NEWSB_DATE);
                                 z=stat.executeUpdate();
                                 if (stat!= null) {stat.close();}
                                 
                                 stat=conn.prepareStatement("update ei_shipment_dtls set dbk_slno='Y' where trim(shp_bill_no)=? and shp_bill_date=?");
                                 stat.setString(1,INVOICE_S.trim());
                                 stat.setString(2,NEWSB_DATE);
                                 z=stat.executeUpdate();
                                 if (stat!= null) {stat.close();}
                                 
                                 conn.commit();
                                   
                                  stat=conn.prepareStatement("update ei_dbk_mast set str_due=0,rosl_due=0 where trim(shp_bill_no)=? and shp_bill_date=?");
                                  stat.setString(1,INVOICE_S.trim());
                                  stat.setString(2,NEWSB_DATE);
                                  z=stat.executeUpdate();
                                  if (stat!= null) {stat.close();}
                                    
                                  stat=conn.prepareStatement("update ei_shipment_dtls set ship_qnty=ship_qnty,frt_type=frt_type where trim(shp_bill_no)=? and shp_bill_date=?");
                                  stat.setString(1,INVOICE_S.trim());
                                  stat.setString(2,NEWSB_DATE);
                                  z=stat.executeUpdate();
                                  if (stat!= null) {stat.close();}
                                    
                                    
                                  stat=conn.prepareStatement("update ei_dbk_mast set str_woff=str_due where str_due<50 and trim(shp_bill_no)=? and shp_bill_date=?");
                                  stat.setString(1,INVOICE_S.trim());
                                  stat.setString(2,NEWSB_DATE);
                                  z=stat.executeUpdate();
                                  if (stat!= null) {stat.close();}
                                  
                                  
                                  
           
                                    if (z > 0) {
                                                 conn.commit();
                                                 addActionMessage("Record Updated succcessfully ");

                                     }
                     
                     
           }
                  
             
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
              conn.close();
            }
        }

       
         FLAG1="Yes";
         execute();

        return SUCCESS;
    }
     
    public String shipbillcodedesc() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connection().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn.prepareStatement("Select distinct shp_bill_no,to_char(shp_bill_date,'dd-Mon-yyyy') shp_bill_date from ei_shipment_dtls  where  t_mod='LGM4' AND shp_bill_no like ?  order by 1");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("shp_bill_no"),result.getString("shp_bill_date")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "sbill";
    }
    
    public String searchcodedesc() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        
 
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and (idsuno like ? or idsunm like ?)");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("CHA_CODE"),result.getString("CHA_NAME")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "srch";
    }
    public String searchcodedesc2() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and (idsuno like ? or idsunm like ?)");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("CHA_CODE"),result.getString("CHA_NAME")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "srch2";
    }
    
    public String searchcodedesc1() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                
                stat = conn.prepareStatement("select cttx40,cttx15,ctstky from m3fdbprd.csytab where ctcono=111 and ctstco='HAFE' and (ctstky like ? or cttx40 like ?)");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase().trim() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase().trim() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("ctstky"),result.getString("cttx40")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "srch1";
    }
    public String searchcodedesc3() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn.prepareStatement("select cttx40,cttx15,ctstky from m3fdbprd.csytab where ctcono=111 and ctstco='HAFE' and (ctstky like ? or cttx40 like ?)");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("ctstky"),result.getString("cttx40")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "srch3";
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


    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getCBM() {
        return CBM;
    }

    public void setCBM(String CBM) {
        this.CBM = CBM;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List getListdata() {
        return listdata;
    }

    public void setListdata(List listdata) {
        this.listdata = listdata;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public String getINVOICE_S() {
        return INVOICE_S;
    }

    public void setINVOICE_S(String INVOICE_S) {
        this.INVOICE_S = INVOICE_S;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getINVOICENO() {
        return INVOICENO;
    }

    public void setINVOICENO(String INVOICENO) {
        this.INVOICENO = INVOICENO;
    }

    public String getINVOICEDATE() {
        return INVOICEDATE;
    }

    public void setINVOICEDATE(String INVOICEDATE) {
        this.INVOICEDATE = INVOICEDATE;
    }

    public String getFLAG1() {
        return FLAG1;
    }

    public void setFLAG1(String FLAG1) {
        this.FLAG1 = FLAG1;
    }

    public String getFLAG2() {
        return FLAG2;
    }

    public void setFLAG2(String FLAG2) {
        this.FLAG2 = FLAG2;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {

        this.servletRequest = servletRequest;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getBUYER_DESC() {
        return BUYER_DESC;
    }

    public void setBUYER_DESC(String BUYER_DESC) {
        this.BUYER_DESC = BUYER_DESC;
    }

    public String getBUYER_ADDR() {
        return BUYER_ADDR;
    }

    public void setBUYER_ADDR(String BUYER_ADDR) {
        this.BUYER_ADDR = BUYER_ADDR;
    }

    

    public List getSearchList() {
        return SearchList;
    }

    public void setSearchList(List SearchList) {
        this.SearchList = SearchList;
    }

    

    public String getFLG1() {
        return FLG1;
    }

    public void setFLG1(String FLG1) {
        this.FLG1 = FLG1;
    }

    public String getFLG2() {
        return FLG2;
    }

    public void setFLG2(String FLG2) {
        this.FLG2 = FLG2;
    }

    public String getFLG3() {
        return FLG3;
    }

    public void setFLG3(String FLG3) {
        this.FLG3 = FLG3;
    }

    public String getFLG4() {
        return FLG4;
    }

    public void setFLG4(String FLG4) {
        this.FLG4 = FLG4;
    }

   

    public String getITMIDL() {
        return ITMIDL;
    }

    public void setITMIDL(String ITMIDL) {
        this.ITMIDL = ITMIDL;
    }

    public double getTOTITMQTY() {
        return TOTITMQTY;
    }

    public void setTOTITMQTY(double TOTITMQTY) {
        this.TOTITMQTY = TOTITMQTY;
    }

    public List getITEM_NO() {
        return ITEM_NO;
    }

    public void setITEM_NO(List ITEM_NO) {
        this.ITEM_NO = ITEM_NO;
    }

    public List getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(List ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public List getITEM_RATIO() {
        return ITEM_RATIO;
    }

    public void setITEM_RATIO(List ITEM_RATIO) {
        this.ITEM_RATIO = ITEM_RATIO;
    }

    public List getITEM_PCKGS() {
        return ITEM_PCKGS;
    }

    public void setITEM_PCKGS(List ITEM_PCKGS) {
        this.ITEM_PCKGS = ITEM_PCKGS;
    }

    public double getQTYID() {
        return QTYID;
    }

    public void setQTYID(double QTYID) {
        this.QTYID = QTYID;
    }

    public String getInvid() {
        return invid;
    }

    public void setInvid(String invid) {
        this.invid = invid;
    }

    public List getITEM_QTY() {
        return ITEM_QTY;
    }

    public void setITEM_QTY(List ITEM_QTY) {
        this.ITEM_QTY = ITEM_QTY;
    }

    public String getCLMDATE() {
        return CLMDATE;
    }

    public void setCLMDATE(String CLMDATE) {
        this.CLMDATE = CLMDATE;
    }

    public String getPORT1() {
        return PORT1;
    }

    public void setPORT1(String PORT1) {
        this.PORT1 = PORT1;
    }

    public String getADMISABLE() {
        return ADMISABLE;
    }

    public void setADMISABLE(String ADMISABLE) {
        this.ADMISABLE = ADMISABLE;
    }

    public String getRECECD() {
        return RECECD;
    }

    public void setRECECD(String RECECD) {
        this.RECECD = RECECD;
    }

    public String getSUPPLYRCVD() {
        return SUPPLYRCVD;
    }

    public void setSUPPLYRCVD(String SUPPLYRCVD) {
        this.SUPPLYRCVD = SUPPLYRCVD;
    }

    public String getADJSTD() {
        return ADJSTD;
    }

    public void setADJSTD(String ADJSTD) {
        this.ADJSTD = ADJSTD;
    }

    public String getMISCAMT() {
        return MISCAMT;
    }

    public void setMISCAMT(String MISCAMT) {
        this.MISCAMT = MISCAMT;
    }

    public String getDBKWOFF() {
        return DBKWOFF;
    }

    public void setDBKWOFF(String DBKWOFF) {
        this.DBKWOFF = DBKWOFF;
    }

    public String getSTRDUE() {
        return STRDUE;
    }

    public void setSTRDUE(String STRDUE) {
        this.STRDUE = STRDUE;
    }

    public String getSTRRECV() {
        return STRRECV;
    }

    public void setSTRRECV(String STRRECV) {
        this.STRRECV = STRRECV;
    }

    public String getWOFF() {
        return WOFF;
    }

    public void setWOFF(String WOFF) {
        this.WOFF = WOFF;
    }

    public String getSHIPBILNO() {
        return SHIPBILNO;
    }

    public void setSHIPBILNO(String SHIPBILNO) {
        this.SHIPBILNO = SHIPBILNO;
    }

    public String getCLRANCEPORT() {
        return CLRANCEPORT;
    }

    public void setCLRANCEPORT(String CLRANCEPORT) {
        this.CLRANCEPORT = CLRANCEPORT;
    }

    public String getLODINGPORT() {
        return LODINGPORT;
    }

    public void setLODINGPORT(String LODINGPORT) {
        this.LODINGPORT = LODINGPORT;
    }

    public String getMODESHIP() {
        return MODESHIP;
    }

    public void setMODESHIP(String MODESHIP) {
        this.MODESHIP = MODESHIP;
    }

    public String getFRWRD() {
        return FRWRD;
    }

    public void setFRWRD(String FRWRD) {
        this.FRWRD = FRWRD;
    }

    public List getINV_L() {
        return INV_L;
    }

    public void setINV_L(List INV_L) {
        this.INV_L = INV_L;
    }

    public List getCRNCY_L() {
        return CRNCY_L;
    }

    public void setCRNCY_L(List CRNCY_L) {
        this.CRNCY_L = CRNCY_L;
    }

    public List getFOBAMT_L() {
        return FOBAMT_L;
    }

    public void setFOBAMT_L(List FOBAMT_L) {
        this.FOBAMT_L = FOBAMT_L;
    }

    public List getQNTY_L() {
        return QNTY_L;
    }

    public void setQNTY_L(List QNTY_L) {
        this.QNTY_L = QNTY_L;
    }

    public List getDBK_L() {
        return DBK_L;
    }

    public void setDBK_L(List DBK_L) {
        this.DBK_L = DBK_L;
    }

    public List getINVCONV_L() {
        return INVCONV_L;
    }

    public void setINVCONV_L(List INVCONV_L) {
        this.INVCONV_L = INVCONV_L;
    }

    public List getDBKCONV_L() {
        return DBKCONV_L;
    }

    public void setDBKCONV_L(List DBKCONV_L) {
        this.DBKCONV_L = DBKCONV_L;
    }

    public List getLEEDATE_L() {
        return LEEDATE_L;
    }

    public void setLEEDATE_L(List LEEDATE_L) {
        this.LEEDATE_L = LEEDATE_L;
    }

    public List getAWBDATE_L() {
        return AWBDATE_L;
    }

    public void setAWBDATE_L(List AWBDATE_L) {
        this.AWBDATE_L = AWBDATE_L;
    }

    public List getFINDATE_L() {
        return FINDATE_L;
    }

    public void setFINDATE_L(List FINDATE_L) {
        this.FINDATE_L = FINDATE_L;
    }

    public List getMODE_L() {
        return MODE_L;
    }

    public void setMODE_L(List MODE_L) {
        this.MODE_L = MODE_L;
    }

    public List getCHA_L() {
        return CHA_L;
    }

    public void setCHA_L(List CHA_L) {
        this.CHA_L = CHA_L;
    }

    public List getFWD_L() {
        return FWD_L;
    }

    public void setFWD_L(List FWD_L) {
        this.FWD_L = FWD_L;
    }

   

    public List getCLRPORT_L() {
        return CLRPORT_L;
    }

    public void setCLRPORT_L(List CLRPORT_L) {
        this.CLRPORT_L = CLRPORT_L;
    }

    public List getLOADPORT_L() {
        return LOADPORT_L;
    }

    public void setLOADPORT_L(List LOADPORT_L) {
        this.LOADPORT_L = LOADPORT_L;
    }
    

    public List getUpdatedatalist() {
        return updatedatalist;
    }

    public void setUpdatedatalist(List updatedatalist) {
        this.updatedatalist = updatedatalist;
    }

    public List getUpdatehistoryList() {
        return updatehistoryList;
    }

    public void setUpdatehistoryList(List updatehistoryList) {
        this.updatehistoryList = updatehistoryList;
    }

    public List getModeList() {
        return ModeList;
    }

    public void setModeList(List ModeList) {
        this.ModeList = ModeList;
    }

    public String getCHA() {
        return CHA;
    }

    public void setCHA(String CHA) {
        this.CHA = CHA;
    }

    public String getSHIPBILDATE() {
        return SHIPBILDATE;
    }

    public void setSHIPBILDATE(String SHIPBILDATE) {
        this.SHIPBILDATE = SHIPBILDATE;
    }

    

    public String getFRWRDCODE() {
        return FRWRDCODE;
    }

    public void setFRWRDCODE(String FRWRDCODE) {
        this.FRWRDCODE = FRWRDCODE;
    }

    public String getCHACODE() {
        return CHACODE;
    }

    public void setCHACODE(String CHACODE) {
        this.CHACODE = CHACODE;
    }

    public String getLODINGCODE() {
        return LODINGCODE;
    }

    public void setLODINGCODE(String LODINGCODE) {
        this.LODINGCODE = LODINGCODE;
    }

    public String getCLRANCODE() {
        return CLRANCODE;
    }

    public void setCLRANCODE(String CLRANCODE) {
        this.CLRANCODE = CLRANCODE;
    }

    public String getACCESSTYPE() {
        return ACCESSTYPE;
    }

    public void setACCESSTYPE(String ACCESSTYPE) {
        this.ACCESSTYPE = ACCESSTYPE;
    }

    public String getNEWSB_DATE() {
        return NEWSB_DATE;
    }

    public void setNEWSB_DATE(String NEWSB_DATE) {
        this.NEWSB_DATE = NEWSB_DATE;
    }

        

   
}