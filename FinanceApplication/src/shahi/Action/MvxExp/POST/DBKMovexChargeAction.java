package shahi.Action.MvxExp.POST;

 
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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.POST.Beans.DBKSBBEAN;
import shahi.Action.MvxExp.POST.Beans.DBKMOVEXBEAN;
import shahi.Action.database.connectiondb2;
  

public class DBKMovexChargeAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private String FIN_DATE;
    private String unitparam;
    private List showList;
    private List unitList=new ArrayList<UnitBean>();
    private String PARAA;
    private String PARAB;
    private String SB_NO;
    private String SB_DATE;
    private String aausrid;
    private String stype;
    private String srem;
    private String location;
    private String year;
    private String company;
    private String inv_no;
    private String saveFlag;
    private List INVOICE_NO;
    private List SR_NO;
    private List DBK_SLNO;
    private List STR_SLNO;
    private List STR_MISC;
    private List ROSL_SLNO;
    private List  QTY_KGS;
    private List MISC_CODE;
    private List MISC_AMOUNT;
    private List INV_NO;
    private List YEAR;
    private List COMPANY;
    private List ShowDetail=new ArrayList();
    private List sbDetail=new ArrayList();
    private List TypeList=new ArrayList();
    private InputStream inputStream;
     
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
        int newdbk=0;
        int newstr=0;
        int newrosl=0;
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
            Connection conndb2=null;
            try {
                conn = new connection().getConnection();
                conndb2 = new connectiondb2().getConnection();
                conn.setAutoCommit(false);
               
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
               stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                stat1.setString(1,usrid);
                result1=stat1.executeQuery();
                while (result1.next())
                {LOCATION_CODE=result1.getString("location_code");
                } 
           
                  
                     
              
                if (viewFlag != null  && viewFlag.equals("Yes")) 
                {
                    
                       stat1=conn.prepareStatement("select shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') sb_date,claim_port,dbk_admisable,dbk_received,DBK_SUPL_RECV,STR_DUE,STR_RECV,ROSL_DUE,ROSL_RECV,WOFF_AMT,STR_WOFF from ei_dbk_mast a where a.shp_bill_no=? and a.shp_bill_date=? order by 1");
                       stat1.setString(1, SB_NO);
                       stat1.setString(2, SB_DATE);
                       result1 = stat1.executeQuery();
                      while (result1.next()) 
                      {   
                          DBKSBBEAN bean = new DBKSBBEAN();
                          bean.setSB_NO(result1.getString("shp_bill_no")); 
                          bean.setSB_DATE(result1.getString("sb_date")); 
                          bean.setCLAIM_PORT(result1.getString("claim_port")); 
                          bean.setDBK_DUE(result1.getString("dbk_admisable")); 
                          bean.setDBK_RECV(result1.getString("dbk_received")); 
                          bean.setSUPL_RECV(result1.getString("DBK_SUPL_RECV")); 
                          bean.setDBK_WOFF(result1.getString("WOFF_AMT")); 
                          bean.setSTR_DUE(result1.getString("STR_DUE")); 
                          bean.setSTR_RECV(result1.getString("STR_RECV")); 
                          bean.setSTR_WOFF(result1.getString("STR_WOFF")); 
                          bean.setROSL_DUE(result1.getString("ROSL_DUE")); 
                          bean.setROSL_RECV(result1.getString("ROSL_RECV")); 
                           sbDetail.add(bean);
                      }
                     if (result1 != null) {result1.close();}
                     if (stat1 != null) {stat1.close();}
                    
                        stat1 = conn.prepareStatement("select  type_desc ,type_code from  EI_GRUP_TYPE_DTLS WHERE GRUP_TYPE_CODE='DM' and close_date is null order by 1" );
                       result1 = stat1.executeQuery();
                         while(result1.next())
                         {  
                            TypeList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
                         }
                        if (result1 != null) {result1.close();}
                        if (stat1 != null) {stat1.close();} 
                    
                     stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.all_no,a.co_no,a.co_line,substr(a.item_no,1,4) item,a.sr_no,a.qty_endors,nvl(a.Qty_kgs,0) qty_kgs,a.unit,a.price_fc,a.price_misc,a.currency,a.gr_decl_amt,a.dbk_slno,a.str_slno,a.str_misc,a.rosl_slno,a.scheme_code,c.misc_code,c.misc_amount,b.dbk_conv,a1.fin_date from ei_endors_mast a1, ei_endors_dtls a, ei_shipment_dtls b,ei_dbk_misc_dtls c where a1.year=a.year and a1.company=a.company and a1.inv_no=a.inv_no and "+
                                                   " a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.year=c.year(+) and a.company=c.company(+) and a.inv_no=c.inv_no(+) and a.sr_no=c.sr_no(+) and b.shp_bill_no=? and b.shp_bill_date=? order by  A.all_no,co_no,to_number(co_line)");
                    
                    //  stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.all_no,a.co_no,a.co_line,substr(a.item_no,1,4) item,a.sr_no,a.qty_endors,nvl(a.Qty_kgs,0) qty_kgs,a.unit,a.price_fc,a.price_misc,a.currency,a.gr_decl_amt,a.dbk_slno,a.str_slno,a.str_misc,a.rosl_slno,a.scheme_code,b.dbk_conv,a1.fin_date from ei_endors_mast a1, ei_endors_dtls a, ei_shipment_dtls b where a1.year=a.year and a1.company=a.company and a1.inv_no=a.inv_no and "+
                    //                              " a.year=b.year and a.company=b.company and a.inv_no=b.inv_no  and b.shp_bill_no=? and b.shp_bill_date=? order by  A.all_no,co_no,to_number(co_line)");
                       
                     stat1.setString(1, SB_NO);
                     stat1.setString(2,SB_DATE);
                     result1 = stat1.executeQuery(); 
                    while (result1.next())  
                    { 
                          
                        
                        DBKMOVEXBEAN bean = new DBKMOVEXBEAN();
                         bean.setINVOICE_NO(result1.getString("all_no"));
                         bean.setCO_NO(result1.getString("co_no"));
                         bean.setCO_LINE(result1.getString("CO_LINE"));
                         bean.setITEM_NO(result1.getString("ITEM"));
                         bean.setSR_NO(result1.getInt("SR_NO"));
                         bean.setQTY_ENDORS(result1.getString("QTY_ENDORS"));
                         bean.setQTY_KGS(result1.getString("QTY_KGS"));
                         bean.setUOM(result1.getString("Unit"));                       
                         bean.setCRNCY_CODE(result1.getString("CURRENCY"));
                         bean.setPRICE_FC(result1.getString("PRICE_FC"));
                         bean.setPRICE_MISC(result1.getString("PRICE_MISC"));
                         bean.setGR_DECL(result1.getString("GR_DECL_AMT"));
                         bean.setDBK_SLNO(result1.getString("DBK_SLNO"));
                         bean.setSTR_SLNO(result1.getString("STR_SLNO"));
                         bean.setSTR_MISC(result1.getString("STR_MISC"));
                         bean.setROSL_SLNO(result1.getString("rosl_slno"));
                         bean.setSCHEME_CODE(result1.getString("SCHEME_CODE"));
                         bean.setDBK_CONV(result1.getString("DBK_CONV"));
                         bean.setYEAR(result1.getInt("YEAR"));
                         bean.setINV_NO(result1.getInt("INV_NO"));
                         bean.setCOMPANY(result1.getString("COMPANY"));
                         bean.setMISC_CODE(result1.getString("MISC_CODE"));
                         bean.setMISC_AMOUNT(result1.getString("misc_amount"));
                         FIN_DATE=result1.getString("FIN_DATE");
                         
                          ShowDetail.add(bean);
                          
                          
                    }
                        if (result1 != null) {result1.close();}
                        if (stat1 != null) {stat1.close();}     
                      
                          
                } // View Flg Close    
                    
                if (saveFlag!=null && saveFlag.equals("YES"))
                {   String p_error=""; 
                   double qty=0.0;   int srno=0; int YR; int INV;  double miscamt=0.0;
                   if ((DBK_SLNO != null) && (DBK_SLNO.size() > 0))
                  {  
                    for (int i = 0; i < DBK_SLNO.size(); i++)
                     {  
                          if (DBK_SLNO.get(i).toString().trim()!=null && DBK_SLNO.get(i).toString().length()>0)
                                {
                             stat1 = conn.prepareStatement("select dbk_slno,dbk_type,dbk_rate,upper(dbk_unit) dbk_unit from EI_DBK_rate_MAST WHERE trim(dbk_slno)=? and  ? BETWEEN dbk_begin_date and dbk_end_date");
                             stat1.setString(1, DBK_SLNO.get(i).toString().trim());
                             stat1.setString(2,SB_DATE);
                             result1 = stat1.executeQuery();
                             if (result1.next())
                             { 
                                 if (result1.getString("dbk_unit").equals("KGS") && QTY_KGS.get(i).toString().equals("0"))
                                 {
                                      addActionMessage("Please update KGS Qnty for DBK Sl Unit KGS");
                                      return  ERROR;
                                 }
                             }else{ 
                                 addActionMessage("Check DBK Sl No Not Found at Master... !!!");
                                return  ERROR;
                             }
                            }
                           if (result1 != null) {result1.close();}
                           if (stat1 != null) {stat1.close();} 
                      }
                   }           
                  if ((STR_SLNO != null) && (STR_SLNO.size() > 0))
                  {  
                    for (int i = 0; i < STR_SLNO.size(); i++)
                     {   
                         if (STR_MISC.get(i).toString().trim()!=null && STR_MISC.get(i).toString().length()>0)
                       {
                             stat1 = conn.prepareStatement("select STR_SLNO from EI_STR_rate_MAST WHERE trim(STR_slno)=? and  ? BETWEEN begin_date and end_date");
                             stat1.setString(1, STR_SLNO.get(i).toString().trim());
                             stat1.setString(2,SB_DATE);
                             result1 = stat1.executeQuery();
                             if (result1.next()==false)
                             { 
                               addActionMessage("Check STR Sl No Not Found at Master... !!!");
                                return  ERROR;
                             }  
                         }
                          if (result1 != null) {result1.close();}
                          if (stat1 != null) {stat1.close();} 
                      }
                   }     
                    if ((STR_MISC != null) && (STR_MISC.size() > 0))
                  {  
                    for (int i = 0; i < STR_MISC.size(); i++)
                     {  
                          String vstrmisc=STR_MISC.get(i).toString().trim();
                         if (vstrmisc!=null && vstrmisc.length()>0 )
                         {
                             stat1 = conn.prepareStatement("select STR_SLNO from EI_STR_rate_MAST WHERE trim(STR_slno)=? and  ? BETWEEN begin_date and end_date");
                             stat1.setString(1, vstrmisc);
                             stat1.setString(2,SB_DATE);
                             result1 = stat1.executeQuery();
                             if (result1.next()==false)
                             { 
                               addActionMessage("Check STR MISC  Not Found at Master... !!!");
                                return  ERROR;
                             }
                              if (result1 != null) {result1.close();}
                              if (stat1 != null) {stat1.close();} 
                      }
                     }
                   }   
                  if ((ROSL_SLNO != null) && (ROSL_SLNO.size() > 0))
                  {  
                    for (int i = 0; i < ROSL_SLNO.size(); i++)
                     {  
                         String vrosl=ROSL_SLNO.get(i).toString().trim();
                         if (vrosl!=null && vrosl.length()>0 )
                         {
                             stat1 = conn.prepareStatement("select ROSL_SLNO from EI_ROSL_RATE_MAST WHERE trim(ROSL_slno)=? and  ? BETWEEN begin_date and end_date");
                             stat1.setString(1, vrosl);
                             stat1.setString(2,SB_DATE);
                             result1 = stat1.executeQuery();
                             if (result1.next()==false)
                             { 
                               addActionMessage("Check ROSL SlNo Not Found at Master... !!!");
                                return  ERROR;
                             }
                              if (result1 != null) {result1.close();}
                              if (stat1 != null) {stat1.close();} 
                         } 
                     }
                   }   
                   if ((MISC_AMOUNT != null) && (MISC_AMOUNT.size() > 0))
                  {  
                    for (int i = 0; i < MISC_AMOUNT.size(); i++  )
                     {  
                      if(MISC_AMOUNT.get(i)!=null && MISC_AMOUNT.get(i).toString()!=null && MISC_AMOUNT.get(i).toString().length()>0 )
                      {
                          miscamt=Double.parseDouble(MISC_AMOUNT.get(i).toString());
                          String misccode=MISC_CODE.get(i).toString();
                          if (miscamt>0 && (misccode==null || misccode.length()==0) )
                         {
                             
                               addActionMessage("Please enter Misc Code for Misc Amount... !!!");
                                return  ERROR;
                       
                         } 
                      }
                     }
                   }   
                    if ((INVOICE_NO != null) && (INVOICE_NO.size() > 0))
                     {  
                            for (int i = 0; i < INVOICE_NO.size(); i++) 
                             {   
                                     qty=Double.parseDouble(QTY_KGS.get(i).toString());
                                   
                                     srno=Integer.parseInt(SR_NO.get(i).toString()) ;
                                     YR=Integer.parseInt(YEAR.get(i).toString()) ;
                                     INV=Integer.parseInt(INV_NO.get(i).toString()) ;
                                     stat1 = conn.prepareStatement("UPDATE EI_ENDORS_DTLS SET QTY_KGS=?,DBK_SLNO=?,STR_SLNO=?,STR_MISC=?,ROSL_SLNO=?  WHERE YEAR=? and company='111' and inv_no=? and type='E' and  SR_NO=?");
                                     stat1.setDouble(1,qty);
                                     stat1.setString(2, DBK_SLNO.get(i).toString().trim());
                                     stat1.setString(3, STR_SLNO.get(i).toString().trim());
                                     stat1.setString(4, STR_MISC.get(i).toString().trim());
                                     stat1.setString(5, ROSL_SLNO.get(i).toString().trim());
                                     stat1.setInt(6, YR);
                                     stat1.setInt(7,INV);
                                     stat1.setInt(8,srno);
                                     stat1.executeUpdate();
                                 
                                     stat1.close();
                                 }
                       } 
                            
                             
                               if ((MISC_AMOUNT != null) && (MISC_AMOUNT.size() > 0))
                               {  
                                for (int j = 0; j < MISC_AMOUNT.size(); j++  )
                                 {  
                                  if(MISC_AMOUNT.get(j)!=null && MISC_AMOUNT.get(j).toString()!=null && MISC_AMOUNT.get(j).toString().length()>0 )
                                  {
                                         srno=Integer.parseInt(SR_NO.get(j).toString()) ;
                                        YR=Integer.parseInt(YEAR.get(j).toString()) ;
                                        INV=Integer.parseInt(INV_NO.get(j).toString()) ;
                                      
                                        stat2=conn.prepareStatement("delete from ei_dbk_misc_dtls where year=? and company=? and inv_no=? and sr_no=?");
                                        stat2.setInt(1,YR);
                                        stat2.setString(2,COMPANY.get(j).toString().trim());
                                        stat2.setInt(3,INV);
                                        stat2.setInt(4,srno);
                                        stat2.executeUpdate(); 
                                        stat2.close();
                                      
                                        
                                     stat3=conn.prepareStatement("insert  into ei_dbk_misc_dtls (year,company,inv_no,dbk_slno,misc_code,misc_amount,sr_no,str_slno,rosl_slno,seh_user,tdate) values (?,?,?,?,?,?,?,?,?,?,sysdate) ");
                                     stat3.setInt(1,YR);
                                     stat3.setString(2,COMPANY.get(j).toString().trim());
                                     stat3.setInt(3,INV);
                                     stat3.setString(4,DBK_SLNO.get(j).toString().trim());
                                     stat3.setString(5,MISC_CODE.get(j).toString().trim());
                                     stat3.setString(6,MISC_AMOUNT.get(j).toString().trim());
                                     stat3.setInt(7,srno);
                                     stat3.setString(8,STR_SLNO.get(j).toString().trim());
                                     stat3.setString(9,ROSL_SLNO.get(j).toString().trim());
                                     stat3.setString(10,usrid);
                                     stat3.executeUpdate(); 
                                     stat3.close();
                                     
                                  }
                                 }
                               }
                               conn.commit();
                                
                               if (result1 != null) {result1.close();}
                               if (stat1 != null){stat1.close();}
                               if (result2 != null) {result2.close();}
                               if (stat2 != null){stat2.close();}
                               if (result3 != null) {result3.close();}
                               if (stat3 != null){stat3.close();}
                               
                                 stat1=conn.prepareStatement("update ei_shipment_dtls set dbk_slno=null where trim(shp_bill_no)=? and shp_bill_date=?");
                                 stat1.setString(1,SB_NO.trim());
                                 stat1.setString(2,SB_DATE);
                                 stat1.executeUpdate();
                                 stat1.close();
                                 
                                 stat1=conn.prepareStatement("update ei_dbk_mast set dbk_admisable=0,dbk_recal_date=sysdate,seh_user=? where trim(shp_bill_no)=? and shp_bill_date=? ");
                                 stat1.setString(1,usrid);
                                 stat1.setString(2, SB_NO.trim());
                                 stat1.setString(3,SB_DATE);
                                 stat1.executeUpdate();
                                 stat1.close();
                                 
                                 stat1=conn.prepareStatement("update ei_shipment_dtls set dbk_slno='Y' where trim(shp_bill_no)=? and shp_bill_date=?");
                                 stat1.setString(1,SB_NO.trim());
                                 stat1.setString(2,SB_DATE);
                                 stat1.executeUpdate();
                                 stat1.close();
                                 
                                 conn.commit();
                                   
                                  stat1=conn.prepareStatement("update ei_dbk_mast set str_due=0,rosl_due=0 where trim(shp_bill_no)=? and shp_bill_date=?");
                                  stat1.setString(1,SB_NO.trim());
                                  stat1.setString(2,SB_DATE);
                                  stat1.executeUpdate();
                                  stat1.close();
                                    
                                  stat1=conn.prepareStatement("update ei_shipment_dtls set ship_qnty=ship_qnty,frt_type=frt_type where trim(shp_bill_no)=? and shp_bill_date=?");
                                  stat1.setString(1,SB_NO.trim());
                                  stat1.setString(2,SB_DATE);
                                  stat1.executeUpdate();
                                  stat1.close();
                                    
                                    
                                  stat1=conn.prepareStatement("update ei_dbk_mast set str_woff=str_due where str_due<50 and trim(shp_bill_no)=? and shp_bill_date=?");
                                  stat1.setString(1,SB_NO.trim());
                                  stat1.setString(2,SB_DATE);
                                  stat1.executeUpdate();
                                  stat1.close();
                                  
                                  conn.commit();
                                    
                             //////////////  Insert into EI_SHIP_DATA FOR MOVEX POSTING.....
                               
                                
                                  p_error = DataCheck(SB_NO,SB_DATE,usrid,conn,conndb2);
                                  
                                
                                     if (p_error.length()>0)
                                       {    addActionMessage(" Error: "+p_error);
                                            flag=0;
                                            return  ERROR;
                                       }else{
                                            flag=1;
                                            conn.commit();
                                            qty=0;
                                            srno=0;
                                            miscamt=0;
                                     }
                              newdbk=0;newstr=0;newrosl=0;       
                             stat1 = conn.prepareStatement("select dbk_admisable,str_due,rosl_due from EI_dbk_mast  WHERE  shp_bill_no=? and shp_bill_date=? ");
                             stat1.setString(1,SB_NO.trim());
                             stat1.setString(2,SB_DATE);
                             result1 = stat1.executeQuery();
                             if (result1.next())
                             { 
                               newdbk=result1.getInt("dbk_admisable");
                               newstr=result1.getInt("str_due");
                               newrosl=result1.getInt("rosl_due");
                             }
                            //// MOVEX POSTING CLOSE
                              if (result1 != null) {result1.close();}
                              if (stat1 != null){stat1.close();} 
                            
                   
                    
                 } // Save Flag Closed
                           
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();
                  addActionError("ERROR :"+e);
                } catch (Exception ee) {
                    System.out.print("1 file name : DBKMovexChargeAction.java" + ee);

                    System.out.println(ee.toString());
                      addActionError("ERROR :"+ee);
                }
                System.out.print("1 file name : DBKMovexChargeAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                    
                    if (stat1 != null) {
                        stat1.close();
                    }
                    if (stat2 != null) {
                        stat2.close();
                    }
                       if (stat3 != null) {
                        stat3.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    
                    result1 = null;
                    result2=null;
                    result3=null;
                    stat1 = null;
                    conn = null;
                     
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : DBKMovexChargeAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
  
            SB_NO=null;
            SB_DATE=null;
            addActionMessage("Records Updated !!  New Dbk Due : "+newdbk+"  New Str Due : "+newstr+" New Rosl Due : "+newrosl);
             newdbk=0;
            newstr=0;
            newrosl=0;
            return SUCCESS;
           
        } else {

            // addActionMessage("Records Not Save(s) !!");
             
            return ERROR;
        }
    } 
     public String DataCheck(String P_SBNO,String P_SBDATE,String P_USER,Connection conn,Connection conndb2)
    {
                        PreparedStatement pst2=null;  
                        PreparedStatement pst3=null; 
                        PreparedStatement pst1=null;
                        PreparedStatement pst4=null; 
                        PreparedStatement stat1=null;
                        ResultSet result1=null;
                        ResultSet rs1=null;
                        ResultSet rs2=null; 
                        ResultSet rs3=null;
                        ResultSet rs4=null;
                        String p_error=""; 
                        
                            
                          try{
                            
                            Double SBQTY=0.0;
                            String Sql1=" select sum(ship_qNty) iqty from ei_shipment_dtls a  where trim(shp_bill_no)=? and shp_bill_date=?  "; 
                            pst1=conn.prepareStatement(Sql1);
                            pst1.setString(1, SB_NO.trim());
                            pst1.setString(2,SB_DATE);
                            rs1=pst1.executeQuery();
                            if(rs1.next())
                            {  
                              SBQTY=rs1.getDouble("iqty");
                            }
                               if (rs1 != null) {rs1.close();}
                               if (pst1 != null){pst1.close();}
                               
                          Double ndbk=0.0; Double nstr=0.0; Double nrosl=0.0; int vfound=0; 
                           Sql1=" select  nvl(dbk_admisable,0) dbk_due,dbk_type,nvl(str_due,0)-nvl(str_woff,0) strdue,nvl(rosl_due,0) rosldue  from ei_dbk_mast a  where trim(shp_bill_no)=? and shp_bill_date=?  "; 
                            pst1=conn.prepareStatement(Sql1);
                            pst1.setString(1, SB_NO.trim());
                            pst1.setString(2,SB_DATE);
                            rs1=pst1.executeQuery();
                            if(rs1.next())
                            {  
                                ndbk=rs1.getDouble("dbk_due")/SBQTY;
                                nstr=rs1.getDouble("strdue")/SBQTY;
                                nrosl=rs1.getDouble("rosldue")/SBQTY;
                            }
                             
                               if (rs1 != null) {rs1.close();}
                               if (pst1 != null){pst1.close();}
                               
                            Double dbk_d=0.0; Double str_d=0.0; Double rosl_d=0.0; 
                            ////////// Cursor Start-------
                                String Sql2="select a.year,a.company,a.inv_no,c.excs_inv_no,c.loading,c.plan_no,a.ship_qnty iqty" 
                                           +"  from ei_shipment_dtls a, ei_endors_mast c  where a.year=c.year and a.company=c.company and a.inv_no=c.inv_no  and "
                                           +" trim(A.shp_bill_no)=? and A.shp_bill_date=? ";
                                  pst2=conn.prepareStatement(Sql2);
                                  pst2.setString(1, SB_NO.trim());
                                  pst2.setString(2,SB_DATE);
                                  rs2=pst2.executeQuery();
                                 while(rs2.next()) 
                                 { 
                                       stat1=conn.prepareStatement("select * from seplweb.pr_ship_plan_detail where del_numb<>888888 and plan_numb=? ");
                                       stat1.setString(1,rs2.getString("plan_no"));
                                       result1=stat1.executeQuery();
                                       if (result1.next())
                                               {
                                                  p_error="Delivery <>888888 book thru oracle...  ";  
                                               }
                                        if (result1 != null) {result1.close();}
                                        if (stat1 != null){stat1.close();} 
                                       
                                        stat1=conn.prepareStatement("delete from ei_ship_data where type_code in ('DBK','DEPB','SVTAX','ROSL') and year=? and company=? and inv_no=? ");
                                        stat1.setString(1,rs2.getString("year"));
                                        stat1.setString(2,rs2.getString("company"));
                                        stat1.setString(3,rs2.getString("inv_no"));
                                        stat1.executeUpdate();
                                        stat1.close();
                                   
                                   
                                        
                                       String Sql3=" select co_numb,m3_del_numb,sum(nvl(pack_qty,0)/nvl(set_pcs,1)) pqty from seplweb.pr_pack_box_ir_detail a  " 
                                            +" where  a.pack_numb=? group by co_numb,m3_del_numb";
                                        pst3=conn.prepareStatement(Sql3);
                                        pst3.setString(1,rs2.getString("plan_no"));
                                   
                                        rs3=pst3.executeQuery();
                                        while(rs3.next())
                                        { 
                                              String Sql4=" select uafaci,uawhlo,uaorst from m3fdbprd.odhead where uacono=111  and uadlix=? ";
                                              pst4=conndb2.prepareStatement(Sql4);
                                              pst4.setString(1,rs3.getString("m3_del_numb"));
                                              rs4=pst4.executeQuery();
                                              while (rs4.next())
                                              { 
                                                 vfound=1;
                                                 if (rs4.getInt("uaorst")>60)
                                                 {
                                                    p_error="Check Movex ...Delv No  "+rs3.getString("m3_del_numb")+" Status "+rs4.getInt("uaorst");
                                                    return p_error;
                                                 }
                                                
                                                  
                                                   
                                                  dbk_d=ndbk*rs3.getDouble("pqty");
                                      
                                                 str_d=nstr*rs3.getDouble("pqty");
                                                 rosl_d=nrosl*rs3.getDouble("pqty");
                                                 
                                                 if (dbk_d>0)
                                                 {
                                                    // dbk_d=dbk_d/rs2.getDouble("iqty")*rs3.getDouble("pqty");
                                                    // System.out.println("excs "+rs2.getString("excs_inv_no")); 
                                                        stat1=conn.prepareStatement("insert into ei_ship_data (year,company,inv_no,ex_track_no,del_numb,co_numb,type_code,amt,type_desc,type_date,rem1,tdate,seh_user,facility,warehouse,crncy,t_mod) values (?,?,?,?,?,?,'DBK',round(?),?,?,?,sysdate,?,?,?,'INR','LGM4') ");
                                                        stat1.setString(1,rs2.getString("year"));
                                                        stat1.setString(2,rs2.getString("company"));
                                                        stat1.setString(3,rs2.getString("inv_no"));
                                                        stat1.setString(4,rs2.getString("excs_inv_no"));
                                                        stat1.setString(5,rs3.getString("m3_del_numb"));
                                                        stat1.setString(6,rs3.getString("co_numb"));
                                                        stat1.setDouble(7,dbk_d);
                                                        stat1.setString(8,SB_NO);
                                                        stat1.setString(9,SB_DATE);
                                                        stat1.setString(10,rs2.getString("loading"));
                                                        stat1.setString(11,P_USER);
                                                        stat1.setString(12,rs4.getString("uafaci"));
                                                        stat1.setString(13,rs4.getString("uawhlo"));
                                                        stat1.executeUpdate();
                                                        stat1.close();
                                                   }
                                                 if (str_d>0)
                                                 {
                                                    // str_d=str_d/rs2.getDouble("iqty")*rs3.getDouble("pqty");
                                                     
                                                        stat1=conn.prepareStatement("insert into ei_ship_data (year,company,inv_no,ex_track_no,del_numb,co_numb,type_code,amt,type_desc,type_date,rem1,tdate,seh_user,facility,warehouse,crncy,t_mod) values (?,?,?,?,?,?,'SVTAX',round(?),?,?,?,sysdate,?,?,?,'INR','LGM4') ");
                                                        stat1.setString(1,rs2.getString("year"));
                                                        stat1.setString(2,rs2.getString("company"));
                                                        stat1.setString(3,rs2.getString("inv_no"));
                                                        stat1.setString(4,rs2.getString("excs_inv_no"));
                                                        stat1.setString(5,rs3.getString("m3_del_numb"));
                                                        stat1.setString(6,rs3.getString("co_numb"));
                                                        stat1.setDouble(7,str_d);
                                                        stat1.setString(8,SB_NO);
                                                        stat1.setString(9,SB_DATE);
                                                        stat1.setString(10,rs2.getString("loading"));
                                                        stat1.setString(11,P_USER);
                                                        stat1.setString(12,rs4.getString("uafaci"));
                                                        stat1.setString(13,rs4.getString("uawhlo"));
                                                        stat1.executeUpdate();
                                                        stat1.close();
                                                   }
                                                 if (rosl_d>0) 
                                                 {
                                                   //  rosl_d=rosl_d/rs2.getDouble("iqty")*rs3.getDouble("pqty");
                                                     
                                                        stat1=conn.prepareStatement("insert into ei_ship_data (year,company,inv_no,ex_track_no,del_numb,co_numb,type_code,amt,type_desc,type_date,rem1,tdate,seh_user,facility,warehouse,crncy,t_mod) values (?,?,?,?,?,?,'ROSL',round(?),?,?,?,sysdate,?,?,?,'INR','LGM4') ");
                                                        stat1.setString(1,rs2.getString("year"));
                                                        stat1.setString(2,rs2.getString("company"));
                                                        stat1.setString(3,rs2.getString("inv_no"));
                                                        stat1.setString(4,rs2.getString("excs_inv_no"));
                                                        stat1.setString(5,rs3.getString("m3_del_numb"));
                                                        stat1.setString(6,rs3.getString("co_numb"));
                                                        stat1.setDouble(7,rosl_d);
                                                        stat1.setString(8,SB_NO);
                                                        stat1.setString(9,SB_DATE);
                                                        stat1.setString(10,rs2.getString("loading"));
                                                        stat1.setString(11,P_USER);
                                                        stat1.setString(12,rs4.getString("uafaci"));
                                                        stat1.setString(13,rs4.getString("uawhlo"));
                                                        stat1.executeUpdate();
                                                        stat1.close();
                                                   }
                                              }
                                               if (rs4 != null) {rs4.close();}
                                                   if (pst4 != null){pst4.close();}
                                              if (vfound==0)
                                              {
                                               p_error="Check Delivery Warehouse ...  ";
                                              }
                                        
                                        
                                        }  /// While rs3 closed
                                 } /////////  while  rs2 closed
                          }
                           
                          catch(Exception e)
                          {
                              e.printStackTrace();
                              System.out.println(e.toString());
                          }
                          finally
                          { 
                              try{
                              if(rs2!=null)rs2.close(); if(rs1!=null)rs1.close();  
                              if(pst2!=null)pst2.close();  if(pst1!=null)pst1.close();   
                              
                             
                              }catch(Exception ee){ee.printStackTrace();}
                          }
                                    
                 return p_error;
    
    }
     
        public String sbView() throws SQLException {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
          
        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
      
       if(unitparam!=null && unitparam.length()>0 ){
        shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
         unitList = dao.getSBList(unitparam);
        }
        return "sbview";
    } 
        
     public String sbAJAX()  throws SQLException, ParseException, UnsupportedEncodingException
    	{
             
    	    Map session = ActionContext.getContext().getSession();
    	    String usrid = ((String)session.get("sessUserId"));
    	    if (usrid == null)
    	    {
    	      addActionError("Session is not Available");
    	      return "error";
    	    }
           
    	    shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
            
            
    	    String placedesc = dao.getSBByNameAjax(unitparam);
    	    
    	    if ((placedesc!= null) && (!placedesc.equals(""))) {
    	      this.inputStream = new ByteArrayInputStream(placedesc.getBytes("UTF-8"));
    	    } else {
    	      this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
    	    }
    	    return "success";
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

    public String getSB_NO() {
        return SB_NO;
    }

    public void setSB_NO(String SB_NO) {
        this.SB_NO = SB_NO;
    }

    public String getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(String SB_DATE) {
        this.SB_DATE = SB_DATE;
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
    
 
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    
    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInv_no() {
        return inv_no;
    }

    public void setInv_no(String inv_no) {
        this.inv_no = inv_no;
    }

    
    public String getUnitparam() {
        return unitparam;
    }

    public void setUnitparam(String unitparam) {
        this.unitparam = unitparam;
    }

    public List getUnitList() {
        return unitList;
    }

    public void setUnitList(List unitList) {
        this.unitList = unitList;
    }

    public List getSbDetail() {
        return sbDetail;
    }

    public void setSbDetail(List sbDetail) {
        this.sbDetail = sbDetail;
    }

    public String getPARAA() {
        return PARAA;
    }

    public void setPARAA(String PARAA) { 
        this.PARAA = PARAA;
    }

    public String getPARAB() {
        return PARAB;
    }

    public void setPARAB(String PARAB) {
        this.PARAB = PARAB;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List getTypeList() {
        return TypeList;
    }

    public void setTypeList(List TypeList) {
        this.TypeList = TypeList;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSrem() {
        return srem;
    }

    public void setSrem(String srem) {
        this.srem = srem;
    }

    public List getINVOICE_NO() {
        return INVOICE_NO;
    }

    public void setINVOICE_NO(List INVOICE_NO) {
        this.INVOICE_NO = INVOICE_NO;
    }

    public List getDBK_SLNO() {
        return DBK_SLNO;
    }

    public void setDBK_SLNO(List DBK_SLNO) {
        this.DBK_SLNO = DBK_SLNO;
    }

    public List getSTR_SLNO() {
        return STR_SLNO;
    }

    public void setSTR_SLNO(List STR_SLNO) {
        this.STR_SLNO = STR_SLNO;
    }

    public List getSTR_MISC() {
        return STR_MISC;
    }

    public void setSTR_MISC(List STR_MISC) {
        this.STR_MISC = STR_MISC;
    }

    public List getROSL_SLNO() {
        return ROSL_SLNO;
    }

    public void setROSL_SLNO(List ROSL_SLNO) {
        this.ROSL_SLNO = ROSL_SLNO;
    }

    public List getQTY_KGS() {
        return QTY_KGS;
    }

    public void setQTY_KGS(List QTY_KGS) {
        this.QTY_KGS = QTY_KGS;
    }

    public List getMISC_CODE() {
        return MISC_CODE;
    }

    public void setMISC_CODE(List MISC_CODE) {
        this.MISC_CODE = MISC_CODE;
    }

    public List getMISC_AMOUNT() {
        return MISC_AMOUNT;
    }

    public void setMISC_AMOUNT(List MISC_AMOUNT) {
        this.MISC_AMOUNT = MISC_AMOUNT;
    }

    public List getSR_NO() {
        return SR_NO;
    }

    public void setSR_NO(List SR_NO) {
        this.SR_NO = SR_NO;
    }

    public List getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(List INV_NO) {
        this.INV_NO = INV_NO;
    }

    public List getYEAR() {
        return YEAR;
    }

    public void setYEAR(List YEAR) {
        this.YEAR = YEAR;
    }

    public List getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(List COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getFIN_DATE() {
        return FIN_DATE;
    }

    public void setFIN_DATE(String FIN_DATE) {
        this.FIN_DATE = FIN_DATE;
    }
 
    
        
}
