/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.dao;
import static com.opensymphony.xwork2.Action.ERROR; 
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap; 
import java.util.List;

import shahi.Action.MvxExp.PRE.Beans.AgentBean;
import shahi.Action.MvxExp.PRE.Beans.BillOfSalesBean;
import shahi.Action.MvxExp.PRE.Beans.BillOfSalesMastBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.database.connection;

/**
 *
 * @author Ranjeet
 */
public class BillOfSalesDao {

    private Connection con;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement st = null;
    HashMap<String, String> typeL= new HashMap<String, String>();
    
    
    void gettypelist()
    {
      
    typeL.put("N","INTER STATE");
    typeL.put("L","LOCAL");
    typeL.put("Y","INTER UNIT");
    typeL.put("C","CONTAINER");
    
    }

    public String getTypeDesc(String typecode)
    {
    	return typeL.get(typecode);
    }
    
    String getMovexdate(String date) {

        if (date != null && date.length() > 5) {
            return date.substring(6, 8) + "/" + date.substring(4, 6) + "/" + date.substring(0, 4);

        } else {
            return "";
        }
    }

    public String roundToDecimals(double d, int c) {
        double temp = (double) ((d * Math.pow(10, c)));
        temp = Math.round(temp);

        double aa = (((double) temp) / Math.pow(10, c));

        return String.format("%.2f", aa);
    }
    
    
    /*public List getInv(String str) throws SQLException {
        List ls = new ArrayList();
        if (str != null && str.length() > 0) {
            try {
                if (str != null && str.length() > 0) {
                    str="'"+str+"%'";
                    //System.out.println(str);
                    con = new connection().getConnection();
                    st = con.prepareStatement("select a.year year,a.company company,a.inv_no inv_no,excs_inv_no,a.currency crncy,min(unit) uom,exp_rate inr_conv,min(description) inv_desc,sum(qty_endors) qnty, "
                            + " round(sum(qty_endors*(price_fc+nvl(price_misc,0)))/sum(qty_endors),2) avg_rate "
                            + " from ei_endors_dtls a,ei_endors_mast b,ei_exchange_Rate_mast c "
                            + " where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no "
                            + " and b.excs_inv_no like "+str
                            + " and nvl(qty_endors,0)>0 "
                           // + " and (select distinct warehouse from PRODUCTION.user_para_mast where user_id=?)"
                            + " and a.currency=c.currency and b.inv_date between begin_date and end_date  "
                            + " group by a.year,a.company,a.inv_no,excs_inv_no,a.currency,exp_rate");
                   // st.setString(1, str + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                         String FOB=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty"),2);
                         String INR=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty")* rs.getDouble("INR_CONV"),2);
                        ls.add(new BillOfSalesBean(rs.getString("YEAR"), rs.getString("COMPANY"), rs.getString("INV_NO"), rs.getString("QNTY"), rs.getString("AVG_RATE"), rs.getString("CRNCY"), rs.getString("INR_CONV"), rs.getString("INV_DESC"), rs.getString("EXCS_INV_NO"), rs.getString("UOM"),FOB,INR));

                    }
                }
            } catch (Exception ee) { 
                System.out.println(ee.toString());

            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            }
        }
        return ls;
    }
    * */
    
    public List getInv(String str) throws SQLException {
        List ls = new ArrayList();
        if (str != null && str.length() > 0) {
            try {
                if (str != null && str.length() > 0) {
                    str="'"+str+"%'";
                    //System.out.println(str);
                    con = new connection().getConnection();
                    st = con.prepareStatement("select a.year year,a.company company,a.inv_no inv_no,b.excs_inv_no ,a.currency crncy,min(unit) uom,exp_rate inr_conv,min(description) inv_desc,sum(qty_endors) qnty, "
                            + " round(sum(qty_endors*(price_fc+nvl(price_misc,0)))/sum(qty_endors),2) avg_rate ,loading i_port,desti_cntry i_cha ,agent i_agent,buyer i_buyer,buyer_addr i_buyer_addr,nvl(first_sale,'N') i_first_sale,doc_send i_doc_send,mode_of_ship i_mos "
                            + " from ei_endors_dtls a,ei_endors_mast b,ei_exchange_Rate_mast c "
                            + " where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no "
                            + " and b.excs_inv_no like "+str
                            + " and nvl(qty_endors,0)>0 "
                           // + " and (select distinct warehouse from PRODUCTION.user_para_mast where user_id=?)"
                            + " and a.currency=c.currency and b.inv_date between begin_date and end_date  "
                            + " group by a.year,a.company,a.inv_no,a.currency,exp_rate,excs_INV_NO,loading ,desti_cntry ,agent,buyer,buyer_addr, first_sale,doc_send,mode_of_ship");
                   // st.setString(1, str + "%");
                    rs = st.executeQuery();
                    while (rs.next()) { 
                        String FOB=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty"),2);
                        String INR=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty") * rs.getDouble("INR_CONV"),2); 
                 ls.add(new BillOfSalesBean(rs.getString("YEAR"), rs.getString("COMPANY"), rs.getString("INV_NO"), rs.getString("QNTY"), rs.getString("AVG_RATE"), rs.getString("CRNCY"), rs.getString("INR_CONV"), rs.getString("INV_DESC"), rs.getString("UOM"),FOB,INR,rs.getString("excs_INV_NO"),rs.getString("i_port"),rs.getString("i_cha"),rs.getString("i_agent"),rs.getString("i_buyer"),rs.getString("i_buyer_addr"),rs.getString("i_first_sale"),rs.getString("i_doc_send"),rs.getString("i_mos"),"","","",""));
   
                    }    
                } 
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
 
            }
        }
        return ls;
    }
     
    public String getInvAjax(String str) throws SQLException {
        String ls = "";
        if (str != null && str.length() > 0) {
            try {
                if (str != null && str.length() > 0) {
                    str="'"+str+"%'";
                    //System.out.println(str);
                    con = new connection().getConnection();
                    st = con.prepareStatement("select a.year year,a.company company,a.inv_no inv_no,b.excs_inv_no ,a.currency crncy,min(unit) uom,exp_rate inr_conv,min(description) inv_desc,sum(qty_endors) qnty, "
                            + " round(sum(qty_endors*(price_fc+nvl(price_misc,0)))/sum(qty_endors),2) avg_rate ,loading i_port,desti_cntry i_cha ,agent i_agent,buyer i_buyer,buyer_addr i_buyer_addr,nvl(first_sale,'N') i_first_sale,doc_send i_doc_send,mode_of_ship i_mos "
                            + " from ei_endors_dtls a,ei_endors_mast b,ei_exchange_Rate_mast c "
                            + " where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no "
                            + " and b.excs_inv_no like "+str
                            + " and nvl(qty_endors,0)>0 "
                           // + " and (select distinct warehouse from PRODUCTION.user_para_mast where user_id=?)"
                            + " and a.currency=c.currency and b.inv_date between begin_date and end_date  "
                            + " group by a.year,a.company,a.inv_no,a.currency,exp_rate,excs_INV_NO,loading ,desti_cntry ,agent,buyer,buyer_addr, first_sale,doc_send,mode_of_ship");
                   // st.setString(1, str + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                        String FOB=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty"),2);
                        String INR=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty") * rs.getDouble("INR_CONV"),2);
                        ls+=rs.getString("YEAR")+"#"+rs.getString("COMPANY")+"#"+rs.getString("INV_NO")+"#"+rs.getString("QNTY")+"#"+rs.getString("AVG_RATE")+"#"+rs.getString("CRNCY")+"#"+rs.getString("INR_CONV")+"#"+rs.getString("INV_DESC")+"#"+rs.getString("UOM")+"#"+FOB+"#"+INR+"#"+rs.getString("excs_INV_NO")+"#"+rs.getString("i_port")+"#"+rs.getString("i_cha")+"#"+rs.getString("i_agent")+"#"+rs.getString("i_buyer")+"#"+rs.getString("i_buyer_addr")+"#"+rs.getString("i_first_sale")+"#"+rs.getString("i_doc_send")+"#"+rs.getString("i_mos");
 
                    } 
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
 
            }
        }
        return ls;
    }
    
    
     public List getquery(String usrid,String str) throws SQLException
    {
      List ls=new ArrayList();
      
      if(usrid!=null && usrid.length()>0){
     try
		{
                 if(str!=null && str.length()>2){
                 
	         con=new connection().getConnection();
                 st=con.prepareStatement("select distinct A.bos_no,to_char(bos_date,'dd-MON-yyyy') bos_date,bos_loct,buyer,buyer_addr,cha,unit,unit_to,interunit,TRANSPORTER from ei_bos_mast A,EI_BOS_DTLS B where  A.BOS_NO=B.BOS_NO AND A.BOS_LOCT=B.BOS_LOCATION AND bos_loct in (select distinct warehouse from PRODUCTION.user_para_mast where user_id=?) "+str +" order by a.bos_no");
                 st.setString(1, usrid);
                 rs=st.executeQuery();
                 gettypelist();
                 while(rs.next())
                 {
                       
                  ls.add(new BillOfSalesMastBean(typeL.get(rs.getString("INTERUNIT")), rs.getString("bos_loct"), rs.getString("BOS_NO"),rs.getString("BOS_DATE"), rs.getString("BUYER"), rs.getString("CHA"), rs.getString("UNIT"), rs.getString("UNIT_TO"), rs.getString("TRANSPORTER")));
                 
                 }
                    }
     }catch(Exception ee)
                {
                System.out.println(ee.toString());
                
                }finally
		{      
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }
			
		}
      }
      return ls;
    }
    
     
    
    
    public int save(shahi.Action.MvxExp.PRE.Beans.BillOfSalesMastBean billOfSalesMast,String usrid,String LOCATION_CODE) throws SQLException {
       int k=0;
        try {
 
             con = new connection().getConnection();
             con.setAutoCommit(false);
             if( billOfSalesMast.getBOS_NO()==null || (billOfSalesMast.getBOS_NO()!=null && billOfSalesMast.getBOS_NO().length()==0))
             {    String newbos=""; String newno="";  String loct="";
                 //st=con.prepareStatement("select nvl(max(bos_no),0)+1 bos_no from  ei_bos_mast where bos_loct=?");
                 // st.setString(1, billOfSalesMast.getLOCATION_CODE());
                  st=con.prepareStatement("select LOCATION_CODE,vou_numb+1 vno ,lpad(nvl(vou_numb,0)+1,5,'0') newex5,SUBSTR(FIN_YEAR,3,2) FIN_YEAR from ei_vou_numb_mast where location_code=substr(?,1,1) and fin_year=pay_fin_year(?) and  VOU_TYPE='EI' AND SUB_TYPE='BOS' for update NOWAIT");
                  st.setString(1, billOfSalesMast.getLOCATION_CODE());
                  st.setString(2,billOfSalesMast.getBOS_DATE())	;
                rs=st.executeQuery();
                if(rs.next())
               {
                 newbos=rs.getString("FIN_YEAR")+rs.getString("LOCATION_CODE")+rs.getString("newex5");
                 newno=rs.getString("vno");   
                 loct=rs.getString("LOCATION_CODE");
            	// billOfSalesMast.setBOS_NO(rs.getString(1));
            	// billOfSalesMast.setBOSNO(rs.getString(1));
                   billOfSalesMast.setBOS_NO(newbos);
            	   billOfSalesMast.setBOSNO(newbos);
              }
                  st=con.prepareStatement(" update ei_vou_numb_mast set vou_numb=?  where location_code=? and fin_year=pay_fin_year(?) and  VOU_TYPE='EI' AND SUB_TYPE='BOS' ");
                                     st.setString(1,newno);
                                     st.setString(2,loct);
                                     st.setString(3,billOfSalesMast.getBOS_DATE());
                                     st.executeUpdate();
             }else{
              st=con.prepareStatement("delete from  ei_bos_mast where BOS_NO=? and BOS_LOCT=?");
              st.setString(1,billOfSalesMast.getBOS_NO())	;
              st.setString(2,billOfSalesMast.getLOCATION_CODE())	;
              st.executeUpdate();
             }
           
             double GR_WT=0;
             double PLAN_CFT=0;
             double ACTUAL_CFT=0;
              if(billOfSalesMast.getINV_NO()!=null && billOfSalesMast.getINV_NO().size()>0)
                {
                   for(int i=0; i<billOfSalesMast.getINV_NO().size(); i++){
                       if(billOfSalesMast.getINV_NO().get(i).toString()!=null && billOfSalesMast.getINV_NO().get(i).toString().length()>0)
                       {
                          if(billOfSalesMast.getGRWT().get(i).toString()!=null && billOfSalesMast.getGRWT().get(i).toString().length()>0) 
                          {
                              GR_WT=GR_WT+(Double.parseDouble(billOfSalesMast.getGRWT().get(i).toString()));
                               }
                           if(billOfSalesMast.getCFT_PLAN().get(i).toString()!=null && billOfSalesMast.getCFT_PLAN().get(i).toString().length()>0) 
                          {
                              PLAN_CFT=PLAN_CFT+(Double.parseDouble(billOfSalesMast.getCFT_PLAN().get(i).toString()));
                               }
                            if(billOfSalesMast.getCFT_ACTUAL().get(i).toString()!=null && billOfSalesMast.getCFT_ACTUAL().get(i).toString().length()>0) 
                          {
                              ACTUAL_CFT=ACTUAL_CFT+(Double.parseDouble(billOfSalesMast.getCFT_ACTUAL().get(i).toString()));
                               }
                       
                       }}} 
             
           if(billOfSalesMast.getBOS_NO()!=null && billOfSalesMast.getBOS_NO().length()>0){
            st=con.prepareStatement("insert into ei_bos_mast(BOS_NO,BOS_DATE,INTERUNIT,DRIVER_NAME,BOS_LOCT,SHIP_MODE,DRIVER_MOBILE,LR_NO,DL_NO,LR_DATE,VEHICLE_TYPE,BUYER,BUYER_ADDR,VEHICLE_NO,TO_DATE,CHA,CHA_ADDR,VCH_ARV_DATE,HALT_HRS,UNIT,VCH_DEP_DATE,SEAL_NO,UNIT_TO,VCH_REP_DATE,TRANSPORTER,DISPATCH_VIA,DAMAGE,PORT,PLAN_SIZE,DESTINATION,ACTUAL_SIZE,REMARKS,CFS_CODE,CUTOFF_DATE,CANCEL_DATE,TDATE,SEH_USER,FY_HALT,FY_CANCEL,CONTAINER_TYPE,CONTAINER_NO,LOCT_CODE,GPRS_YN,LEASE_TRK,RETURN_CARGO,RFID_SEAL_NO) values(?,?,?,upper(?),?,?,?,?,?,to_date(?,'dd/mm/yyyy hh24:mi'),?,?,?,upper(?),to_date(?,'dd/mm/yyyy'),?,?,to_date(?,'dd/mm/yyyy hh24:mi'),?,?,to_date(?,'dd/mm/yyyy hh24:mi'),?,?,to_date(?,'dd/mm/yyyy hh24:mi'),?,?,?,?,?,?,?,?,?,to_date(?,'dd/mm/yyyy hh24:mi'),decode(?,'Y',sysdate,null),sysdate,?,?,?,?,?,?,?,?,decode(?,'Y',sysdate,null),upper(?))");
            st.setString(1,billOfSalesMast.getBOS_NO())	;
            st.setString(2,billOfSalesMast.getBOS_DATE())	;
            st.setString(3,billOfSalesMast.getINTERUNIT())	; 
            st.setString(4,billOfSalesMast.getDRIVER_NAME())	;
            st.setString(5,billOfSalesMast.getLOCATION_CODE())	;
            st.setString(6,billOfSalesMast.getSHIP_MODE().trim())	;
            st.setString(7,billOfSalesMast.getDRIVER_MOBILE())	;
            st.setString(8,billOfSalesMast.getLR_NO())	; 
            st.setString(9,billOfSalesMast.getDL_NO())	; 
            st.setString(10,billOfSalesMast.getLR_DATE())	;
            st.setString(11,billOfSalesMast.getVEHICLE_TYPE())	;
            st.setString(12,billOfSalesMast.getBUYER().trim())	;
            st.setString(13,billOfSalesMast.getBUYER_ADDR().trim())	; 
            st.setString(14,billOfSalesMast.getVEHICLE_NO())	;
            st.setString(15,billOfSalesMast.getTO_DATE())	;
            st.setString(16,billOfSalesMast.getCHA())	;
            st.setString(17,billOfSalesMast.getCHA_ADDR())	;
            st.setString(18,billOfSalesMast.getVCH_ARV_DATE())	;
            st.setString(19,billOfSalesMast.getHALT_HRS())	;
            st.setString(20,billOfSalesMast.getUNIT().trim())	;
            st.setString(21,billOfSalesMast.getVCH_DEP_DATE())	;
            st.setString(22,billOfSalesMast.getSEAL_NO())	;
            st.setString(23,billOfSalesMast.getUNIT_TO())	;
            st.setString(24,billOfSalesMast.getVCH_REP_DATE())	;
           // st.setString(25,GR_WT+"")	;
            st.setString(25,billOfSalesMast.getTRANSPORTER())	;
            st.setString(26,billOfSalesMast.getDISPATCH_VIA())	;
            st.setString(27,billOfSalesMast.getDAMAGE())	;
            st.setString(28,billOfSalesMast.getPORT().trim())	;
          //  st.setString(30,PLAN_CFT+"")	;
            st.setString(29,billOfSalesMast.getPLAN_SIZE())	;
            st.setString(30,billOfSalesMast.getDESTINATION())	;
         //   st.setString(33,ACTUAL_CFT+"")	;
            st.setString(31,billOfSalesMast.getACTUAL_SIZE())	;
            st.setString(32,billOfSalesMast.getREMARKS())	;
            st.setString(33,billOfSalesMast.getCFS_CODE())	;
            st.setString(34,billOfSalesMast.getCUTOFF_DATE())	;
            st.setString(35,billOfSalesMast.getCANCEL_DATE())	;
            st.setString(36, usrid);
            st.setString(37, billOfSalesMast.getFY_HALT());
            st.setString(38, billOfSalesMast.getFY_CANCEL());
            st.setString(39, billOfSalesMast.getCONTAINER_TYPE());
            st.setString(40, billOfSalesMast.getCONTAINER_NO());
            st.setString(41,LOCATION_CODE);
               
               st.setString(42,billOfSalesMast.getGPRS_YN());
               st.setString(43,billOfSalesMast.getLEASE_YN());
               st.setString(44,billOfSalesMast.getRETURN_YN());
               st.setString(45,billOfSalesMast.getRFID_SEAL_NO());
               
            k=st.executeUpdate();
           // System.out.println(k);
            //System.out.println(k);
            if(k>0)  
            {    
               //EXCS_INV_NO, 
                 st=con.prepareStatement("delete from  ei_bos_dtls where BOS_NO=? and BOS_LOCATION=?");
                 st.setString(1,billOfSalesMast.getBOS_NO())	;
                 st.setString(2,billOfSalesMast.getLOCATION_CODE())	;
                 st.executeUpdate();
                 
                if(billOfSalesMast.getINV_NO()!=null && billOfSalesMast.getINV_NO().size()>0)
                { 
                   for(int i=0; i<billOfSalesMast.getINV_NO().size(); i++){
                       if(billOfSalesMast.getINV_NO().get(i).toString()!=null && billOfSalesMast.getINV_NO().get(i).toString().length()>0)
                       { 
                           String PRINT_DATE=null;
                         
                           if(billOfSalesMast.getPRINT_DATE_FLAG().get(i).toString()!=null && billOfSalesMast.getPRINT_DATE_FLAG().get(i).toString().equals("Y"))
                           {
                                if(billOfSalesMast.getPRINT_DATE().get(i).toString()==null || (billOfSalesMast.getPRINT_DATE().get(i).toString()!=null && billOfSalesMast.getPRINT_DATE().get(i).toString().length()==0))
                                {
                                    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
                                    PRINT_DATE = formatter.format(new Date());
                                }else{
                                PRINT_DATE=billOfSalesMast.getPRINT_DATE().get(i).toString();
                                }
                           }
                           String vol=null;
                          
                           String cbm=null;
                           if(billOfSalesMast.getCFT_ACTUAL().get(i).toString()!=null && billOfSalesMast.getCFT_ACTUAL().get(i).toString().length()>0)
                           {
                           cbm=roundToDecimals((Double.parseDouble(billOfSalesMast.getCFT_ACTUAL().get(i).toString())/35.31),4);
                           }
                           if(cbm!=null && cbm.length()>0)
                           {
                            vol=roundToDecimals((Double.parseDouble(cbm)/166.66),2);
                           }
                            st=con.prepareStatement("insert into ei_bos_dtls(BOS_NO,YEAR,COMPANY,INV_NO,PKGS,QNTY,AVG_RATE,CRNCY,INR_CONV,INV_DESC,BOS_LOCATION,DISPATCH_YN,CFT_ACTUAL,UOM,CFT_PLAN,GRWT,PRINT_DATE,TDATE,SEH_USER,CBM,VOL,excs_inv_no,FY_PKGS,FY_QNTY,FY_USER,FY_TDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'dd/mm/yyyy'),sysdate,?,?,?,?,?,?,trim(?),to_date(?,'dd/mm/yyyy hh24:mi'))");
                            st.setString(1,billOfSalesMast.getBOS_NO())	;
                            st.setString(2,billOfSalesMast.getYEAR().get(i).toString())	;
                            st.setString(3,billOfSalesMast.getCOMPANY().get(i).toString())	;
                            st.setString(4,billOfSalesMast.getINV_NO().get(i).toString())	;
                            st.setString(5,billOfSalesMast.getPKGS().get(i).toString())	;
                            st.setString(6,billOfSalesMast.getQNTY().get(i).toString())	;
                            st.setString(7,billOfSalesMast.getAVG_RATE().get(i).toString())	;
                            st.setString(8,billOfSalesMast.getCRNCY().get(i).toString())	;
                            st.setString(9,billOfSalesMast.getINR_CONV().get(i).toString())	;
                            st.setString(10,billOfSalesMast.getINV_DESC().get(i).toString())	;
                            st.setString(11,billOfSalesMast.getLOCATION_CODE())	;
                            st.setString(12,billOfSalesMast.getDISPATCH_YN().get(i).toString())	;
                            st.setString(13,billOfSalesMast.getCFT_ACTUAL().get(i).toString())	;
                            st.setString(14,billOfSalesMast.getUOM().get(i).toString())	;
                            st.setString(15,billOfSalesMast.getCFT_PLAN().get(i).toString())	;
                            st.setString(16,billOfSalesMast.getGRWT().get(i).toString())	;
                            st.setString(17,PRINT_DATE)	;
                            st.setString(18,usrid);
                            st.setString(19,cbm);
                            st.setString(20,vol); 
                            st.setString(21,billOfSalesMast.getEXCS_INV_NO().get(i).toString())	;
                            st.setString(22,billOfSalesMast.getFY_PKGS().get(i).toString())	;
                            st.setString(23,billOfSalesMast.getFY_QNTY().get(i).toString())	;
                            st.setString(24,billOfSalesMast.getFY_USER().get(i).toString())	;
                            st.setString(25,billOfSalesMast.getFY_TDATE().get(i).toString())	;
                             st.executeUpdate();
                       }
                    
                   }
                }
               
            }
            
           }     
          
       con.commit();
        }catch (SQLException ee1) {
            System.out.println(ee1.toString());
            con.rollback();

        }
        
        catch (Exception ee) {
            System.out.println(ee.toString());
            con.rollback();

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return k;
    }
    public int update(shahi.Action.MvxExp.PRE.Beans.BillOfSalesMastBean billOfSalesMast,String usrid,String LOCATION_CODE) throws SQLException {
       int k=0;
        try {
 
             con = new connection().getConnection();
             con.setAutoCommit(false);

                      
           if(billOfSalesMast.getBOS_NO()!=null && billOfSalesMast.getBOS_NO().length()>0)
           {
        
                if(billOfSalesMast.getEXCS_INV_NO()!=null && billOfSalesMast.getEXCS_INV_NO().size()>0)
                { 
                   for(int i=0; i<billOfSalesMast.getINV_NO().size(); i++){
                       if(billOfSalesMast.getEXCS_INV_NO().get(i).toString()!=null && billOfSalesMast.getEXCS_INV_NO().get(i).toString().length()>0)
                       { 
                            st=con.prepareStatement("select excs_inv_no,to_char(FY_TDATE,'dd/mm/yy hh24:mi') fy_tdate,(SYSDATE-fy_tdate)*24 aa from ei_bos_dtls where  excs_inv_no=? and bos_no=?");
                            st.setString(1,billOfSalesMast.getEXCS_INV_NO().get(i).toString())	;
                            st.setString(2,billOfSalesMast.getBOS_NO())	;
                            rs=st.executeQuery();
                            while (rs.next()) 
                            { double aa=rs.getDouble("aa");
                               if (aa>6)
                               {
                                 k=0;
                                 return k;
                               }  
                            }
                          String vdate=  billOfSalesMast.getFY_TDATE().get(i).toString(); 
                         if(vdate!=null && vdate.length()>6 ) 
                         {
                            st=con.prepareStatement("update ei_bos_dtls set fy_pkgs=?,fy_qnty=?,fy_user=? where bos_no=? and excs_inv_no=? ");
                            st.setString(1,billOfSalesMast.getFY_PKGS().get(i).toString())	;
                            st.setString(2,billOfSalesMast.getFY_QNTY().get(i).toString())	;
                            st.setString(3,usrid);
                            st.setString(4,billOfSalesMast.getBOS_NO())	;
                            st.setString(5,billOfSalesMast.getEXCS_INV_NO().get(i).toString())	;
                            k=st.executeUpdate();
                             
                         }else{
                         
                            st=con.prepareStatement("update ei_bos_dtls set fy_pkgs=?,fy_qnty=?,fy_user=?,FY_TDATE=sysdate where bos_no=? and excs_inv_no=? ");
                            st.setString(1,billOfSalesMast.getFY_PKGS().get(i).toString())	;
                            st.setString(2,billOfSalesMast.getFY_QNTY().get(i).toString())	;
                            st.setString(3,usrid);
                            st.setString(4,billOfSalesMast.getBOS_NO())	;
                            st.setString(5,billOfSalesMast.getEXCS_INV_NO().get(i).toString())	;
                            k=st.executeUpdate();
                          
                         }
                       }
                   
                   }
                } 
               
                            st=con.prepareStatement("update ei_bos_mast set DRIVER_NAME=upper(?),DRIVER_MOBILE=?,DL_NO=?,SEAL_NO=?,LR_NO=?,LR_DATE=to_date(?,'dd/mm/yyyy hh24:mi') ,VEHICLE_NO=upper(?),VCH_ARV_DATE=to_date(?,'dd/mm/yyyy hh24:mi'),VCH_DEP_DATE=to_date(?,'dd/mm/yyyy hh24:mi') where bos_no=? and bos_loct=? ");
                            st.setString(1,billOfSalesMast.getDRIVER_NAME());
                            st.setString(2,billOfSalesMast.getDRIVER_MOBILE()); 
                             st.setString(3,billOfSalesMast.getDL_NO());
                            st.setString(4,billOfSalesMast.getSEAL_NO());
                            st.setString(5,billOfSalesMast.getLR_NO());
                            st.setString(6,billOfSalesMast.getLR_DATE());
                            st.setString(7,billOfSalesMast.getVEHICLE_NO())	;
                             st.setString(8,billOfSalesMast.getVCH_ARV_DATE())	;
                            st.setString(9,billOfSalesMast.getVCH_DEP_DATE())	;
                            st.setString(10,billOfSalesMast.getBOS_NO())	;
                            st.setString(11,billOfSalesMast.getLOCATION_CODE())	;
                            k=st.executeUpdate();
                
                  
              }      
          
       con.commit();
        }catch (SQLException ee1) {
            System.out.println(ee1.toString());
            con.rollback();

        }
        
        catch (Exception ee) {
            System.out.println(ee.toString());
            con.rollback();

        } finally {
            if (rs != null) {
                rs.close(); 
            }
            if (st != null) {
                st.close(); 
            }
            if (con != null) {
                con.close();
            }

        }
 
        return k;
    }
      
    public BillOfSalesMastBean getmast(shahi.Action.MvxExp.PRE.Beans.BillOfSalesMastBean billOfSalesMast,String usrid,String LOCATION_CODE) throws SQLException {
       int k=0;

        try {
 
             con = new connection().getConnection();
             gettypelist();
           if(billOfSalesMast.getBOS_NO()!=null && billOfSalesMast.getBOS_NO().length()>0){
              
               st=con.prepareStatement("select * from ei_endors_mast a,ei_bos_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and doc_send is not null and b.bos_no=? and b.bos_location=? ");
               st.setString(1,billOfSalesMast.getBOS_NO());
               st.setString(2,billOfSalesMast.getLOCATION_CODE());
               rs=st.executeQuery();
                while (rs.next()) 
                { 
                    billOfSalesMast.setDOC_FWD("YES");
                    
                } 
                  
              rs.close();st.close();
              
            st=con.prepareStatement("select BOS_NO,to_char(BOS_DATE,'dd-Mon-yyyy') BOS_DATE,INTERUNIT,DRIVER_NAME,BOS_LOCT,trim(SHIP_MODE) SHIP_MODE,DRIVER_MOBILE,LR_NO,DL_NO,to_char(LR_DATE,'dd/mm/yyyy') LR_DATE,VEHICLE_TYPE,BUYER,BUYER_ADDR,VEHICLE_NO,to_char(TO_DATE,'dd/mm/yyyy') TO_DATE,CHA,CHA_ADDR,to_char(VCH_ARV_DATE,'dd/mm/yyyy hh24:mi') VCH_ARV_DATE,HALT_HRS,UNIT,to_char(VCH_DEP_DATE,'dd/mm/yyyy hh24:mi') VCH_DEP_DATE,SEAL_NO,UNIT_TO,to_char(VCH_REP_DATE,'dd/mm/yyyy hh24:mi') VCH_REP_DATE,GR_WT,TRANSPORTER,DISPATCH_VIA,DAMAGE,PORT,PLAN_CFT,PLAN_SIZE,DESTINATION,ACTUAL_CFT,ACTUAL_SIZE,REMARKS,CFS_CODE,to_char(CUTOFF_DATE,'dd/mm/yyyy hh24:mi') CUTOFF_DATE,CANCEL_DATE,TDATE,SEH_USER,FY_HALT,FY_CANCEL,CONTAINER_TYPE,CONTAINER_NO,GPRS_YN,LEASE_TRK,RETURN_CARGO,RFID_SEAL_NO from ei_bos_mast where bos_no=? and BOS_LOCT=?");
            st.setString(1,billOfSalesMast.getBOS_NO());
            st.setString(2,billOfSalesMast.getLOCATION_CODE());
            rs=st.executeQuery();
               while (rs.next()) {

                   billOfSalesMast.setBOS_NO(rs.getString("BOS_NO"));
                   billOfSalesMast.setBOS_DATE(rs.getString("BOS_DATE"));
                   billOfSalesMast.setINTERUNIT(rs.getString("INTERUNIT"));
                   billOfSalesMast.setINTERUNIT_DIS(typeL.get(rs.getString("INTERUNIT")));
                   billOfSalesMast.setDRIVER_NAME(rs.getString("DRIVER_NAME"));
                   billOfSalesMast.setLOCATION_CODE(rs.getString("BOS_LOCT"));
                   billOfSalesMast.setSHIP_MODE(rs.getString("SHIP_MODE"));
                   billOfSalesMast.setDRIVER_MOBILE(rs.getString("DRIVER_MOBILE"));
                   billOfSalesMast.setLR_NO(rs.getString("LR_NO"));
                   billOfSalesMast.setDL_NO(rs.getString("DL_NO"));
                   billOfSalesMast.setLR_DATE(rs.getString("LR_DATE"));
                   billOfSalesMast.setVEHICLE_TYPE(rs.getString("VEHICLE_TYPE"));

                   if (rs.getString("VEHICLE_TYPE") != null && rs.getString("VEHICLE_TYPE").length() > 0) {
                       UnitBean ub = new BillOfSalesMasterDao().getCFSBean(rs.getString("VEHICLE_TYPE"), "BOS");
                       if (ub != null && ub.getUNIT_DESC() != null) {
                           billOfSalesMast.setVEHICLE_TYPE_DESC(ub.getUNIT_DESC());

                       }
                   }
                   billOfSalesMast.setBUYER(rs.getString("BUYER"));
                   billOfSalesMast.setBUYER_ADDR(rs.getString("BUYER_ADDR"));
                   if (rs.getString("BUYER") != null && rs.getString("BUYER").length() > 0) {
                       UnitBean ub = new BillOfSalesMasterDao().getBuyeraddress(rs.getString("BUYER"), rs.getString("BUYER_ADDR"));
                       if (ub != null && ub.getUNIT_DESC() != null) {
                           billOfSalesMast.setBUYER_DESC(ub.getUNIT_DESC());
                           billOfSalesMast.setBUYER_ADDRESS_NAME(ub.getUNIT_ADDRESS());
                       }
                   }
                   billOfSalesMast.setVEHICLE_NO(rs.getString("VEHICLE_NO"));
                   billOfSalesMast.setTO_DATE(rs.getString("TO_DATE"));
                   billOfSalesMast.setCHA(rs.getString("CHA"));
                   billOfSalesMast.setCHA_ADDR(rs.getString("CHA_ADDR"));
                   if (rs.getString("CHA") != null && rs.getString("CHA").length() > 0) {
                       AgentBean ab = new BillOfSalesMasterDao().getAgentName(rs.getString("CHA"), rs.getString("CHA_ADDR"));
                       if (ab != null && ab.getAgentName() != null) {
                           billOfSalesMast.setCHA_DESC(ab.getAgentName());
                           billOfSalesMast.setCHA_ADDRESS_NAME(ab.getAgentAdd());
                       }
                   } 

                   billOfSalesMast.setVCH_ARV_DATE(rs.getString("VCH_ARV_DATE"));
                   billOfSalesMast.setHALT_HRS(rs.getString("HALT_HRS"));
                   billOfSalesMast.setUNIT(rs.getString("UNIT"));
                   if (rs.getString("UNIT") != null && rs.getString("UNIT").length() > 0) {
                       UnitBean ab1 = new BillOfSalesMasterDao().getUnitByName(rs.getString("UNIT"));
                       if (ab1 != null && ab1.getUNIT_DESC() != null) {
                           billOfSalesMast.setUNIT_DESC(ab1.getUNIT_DESC());
                       }
                   }

                   billOfSalesMast.setVCH_DEP_DATE(rs.getString("VCH_DEP_DATE"));
                   billOfSalesMast.setSEAL_NO(rs.getString("SEAL_NO"));

                   billOfSalesMast.setUNIT_TO(rs.getString("UNIT_TO"));

                   if (rs.getString("UNIT_TO") != null && rs.getString("UNIT_TO").length() > 0) {
                       UnitBean ab1 = new BillOfSalesMasterDao().getUnitByName(rs.getString("UNIT_TO"));
                       if (ab1 != null && ab1.getUNIT_DESC() != null) {
                           billOfSalesMast.setUNIT_TO_DESC(ab1.getUNIT_DESC());
                       }
                   }
                   billOfSalesMast.setVCH_REP_DATE(rs.getString("VCH_REP_DATE"));
                   billOfSalesMast.setGR_WT(rs.getString("GR_WT"));
                   billOfSalesMast.setTRANSPORTER(rs.getString("TRANSPORTER"));
                   billOfSalesMast.setDISPATCH_VIA(rs.getString("DISPATCH_VIA"));
                   billOfSalesMast.setDAMAGE(rs.getString("DAMAGE"));

                   billOfSalesMast.setPORT(rs.getString("PORT"));
                   if (rs.getString("PORT") != null && rs.getString("PORT").length() > 0) {
                       UnitBean ab2 = new BillOfSalesMasterDao().getCsytabBeanByName(rs.getString("PORT"), "HAFE");
                       if (ab2 != null && ab2.getUNIT_DESC() != null) {
                           billOfSalesMast.setPORT_DESC(ab2.getUNIT_DESC());
                       }
                   }

                   billOfSalesMast.setPLAN_CFT(rs.getString("PLAN_CFT"));
                   billOfSalesMast.setPLAN_SIZE(rs.getString("PLAN_SIZE"));
                   billOfSalesMast.setDESTINATION(rs.getString("DESTINATION"));
                   if (rs.getString("DESTINATION") != null && rs.getString("DESTINATION").length() > 0) {
                       UnitBean ab2 = new BillOfSalesMasterDao().getCsytabBeanByName(rs.getString("DESTINATION"), "CSCD");
                       if (ab2 != null && ab2.getUNIT_DESC() != null) {
                           billOfSalesMast.setDESTINATION_DESC(ab2.getUNIT_DESC());
                       }
                   }
                   billOfSalesMast.setACTUAL_CFT(rs.getString("ACTUAL_CFT"));
                   billOfSalesMast.setACTUAL_SIZE(rs.getString("ACTUAL_SIZE"));
                   billOfSalesMast.setREMARKS(rs.getString("REMARKS"));
                   billOfSalesMast.setCFS_CODE(rs.getString("CFS_CODE"));
                   if (rs.getString("CFS_CODE") != null && rs.getString("CFS_CODE").length() > 0) {
                       UnitBean ub = new BillOfSalesMasterDao().getCFSBean(rs.getString("CFS_CODE"), "CFS");
                       if (ub != null && ub.getUNIT_DESC() != null) {
                           billOfSalesMast.setCFS_DESC(ub.getUNIT_DESC());

                       }
                   }

                    
                   billOfSalesMast.setCUTOFF_DATE(rs.getString("CUTOFF_DATE"));
                   billOfSalesMast.setCANCEL_DATE(rs.getString("CANCEL_DATE"));
                   billOfSalesMast.setFY_HALT(rs.getString("FY_HALT"));
                   billOfSalesMast.setFY_CANCEL(rs.getString("FY_CANCEL"));
                   billOfSalesMast.setCONTAINER_TYPE(rs.getString("CONTAINER_TYPE"));
                   billOfSalesMast.setCONTAINER_NO(rs.getString("CONTAINER_NO"));
                   
                   billOfSalesMast.setGPRS_YN(rs.getString("GPRS_YN"));
                   billOfSalesMast.setLEASE_YN(rs.getString("GPRS_YN"));
                   billOfSalesMast.setRETURN_CARGO(rs.getString("RETURN_CARGO"));
                   System.out.println("sanjeev rfd1 "+rs.getString("RFID_SEAL_NO"));   
                   billOfSalesMast.setRFID_SEAL_NO(rs.getString("RFID_SEAL_NO"));
            
            }
           
                  
                
                          List ls=new ArrayList();
                           st=con.prepareStatement("select BOS_NO,YEAR,COMPANY,INV_NO,EXCS_INV_NO,PKGS,QNTY,AVG_RATE,CRNCY,INR_CONV,INV_DESC,BOS_LOCATION,DISPATCH_YN,CFT_ACTUAL,UOM,CFT_PLAN,GRWT,to_char(PRINT_DATE,'dd/mm/yyyy') PRINT_DATE,TDATE,SEH_USER,CBM,VOL,FY_USER,FY_PKGS,FY_QNTY,to_char(FY_TDATE,'dd/mm/yy hh24:mi') fy_tdate,(SYSDATE-fy_tdate)*24 aa from ei_bos_dtls where BOS_NO=? and BOS_LOCATION=?");
                           st.setString(1,billOfSalesMast.getBOS_NO())	;
                           st.setString(2,billOfSalesMast.getLOCATION_CODE());
                           rs=st.executeQuery();
                           int QNTY_TOTAL=0;
                           int CTN_TOTAL=0;
                           int FYQNTY_TOTAL=0;
                           int FYCTN_TOTAL=0;
                           double FOB_TOTAL=0;
                           double INR_CONV_TOTAL=0;
                           double GRWT_TOTAL=0.0;
                           double CFTPLAN_TOTAL=0.0;
                           double CFTACT_TOTAL=0.0;
                           double VOL_TOTAL=0.0;
                           double CBM_TOTAL=0.0;
                           
                           
                           while(rs.next()){ 
                               String FOB=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty"),2);
                               String INR=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty") * rs.getDouble("INR_CONV"),2);
                               QNTY_TOTAL=QNTY_TOTAL+rs.getInt("QNTY");
                               CTN_TOTAL=CTN_TOTAL+rs.getInt("PKGS");
                               FYQNTY_TOTAL=FYQNTY_TOTAL+rs.getInt("FY_QNTY");
                               FYCTN_TOTAL=FYCTN_TOTAL+rs.getInt("FY_PKGS");
                               FOB_TOTAL=FOB_TOTAL+(rs.getDouble("avg_rate") * rs.getDouble("qnty"));
                               GRWT_TOTAL=GRWT_TOTAL+(rs.getDouble("GRWT"));
                               CBM_TOTAL=CBM_TOTAL+(rs.getDouble("CBM"));
                               VOL_TOTAL=VOL_TOTAL+(rs.getDouble("VOL"));
                               CFTPLAN_TOTAL=CFTPLAN_TOTAL+(rs.getDouble("CFT_PLAN"));
                               CFTACT_TOTAL=CFTACT_TOTAL+(rs.getDouble("CFT_ACTUAL"));
                               double aa=rs.getDouble("aa");
                               if (aa>6)
                               {
                                  billOfSalesMast.setUPDATE_ALLOW("NO");
                               }
                               INR_CONV_TOTAL=INR_CONV_TOTAL+(rs.getDouble("avg_rate") * rs.getDouble("qnty") * rs.getDouble("INR_CONV"));
                               
                         ls.add(new BillOfSalesBean(rs.getString("YEAR"), rs.getString("COMPANY"), rs.getString("INV_NO"), rs.getString("QNTY"), rs.getString("AVG_RATE"),rs.getString("CRNCY"),rs.getString("INR_CONV"), rs.getString("INV_DESC"),rs.getString("UOM"),FOB,INR,rs.getString("DISPATCH_YN"),rs.getString("CFT_PLAN"),rs.getString("CFT_ACTUAL"),rs.getString("GRWT"),rs.getString("PRINT_DATE"),rs.getString("PKGS"),rs.getString("CBM"),rs.getString("VOL"),rs.getString("EXCS_INV_NO"),"","","","","","","","",rs.getString("FY_USER"),rs.getString("FY_PKGS"),rs.getString("FY_QNTY"),rs.getString("FY_TDATE")));
                                 
                               
                           }
                             
                           billOfSalesMast.setQNTY_TOTAL(QNTY_TOTAL+"");
                           billOfSalesMast.setCTN_TOTAL(CTN_TOTAL+""); 
                           billOfSalesMast.setFYQNTY_TOTAL(FYQNTY_TOTAL+""); 
                           billOfSalesMast.setFYCTN_TOTAL(FYCTN_TOTAL+""); 
                           billOfSalesMast.setGRWT_TOTAL(GRWT_TOTAL+""); 
                           billOfSalesMast.setCBM_TOTAL(CBM_TOTAL+""); 
                           billOfSalesMast.setVOL_TOTAL(VOL_TOTAL+"");
                           billOfSalesMast.setCFTPLAN_TOTAL(CFTPLAN_TOTAL+""); 
                           billOfSalesMast.setCFTACT_TOTAL(CFTACT_TOTAL+"");  
                           billOfSalesMast.setFOB_TOTAL(roundToDecimals(FOB_TOTAL,2)+"");
                           billOfSalesMast.setINR_CONV_TOTAL(roundToDecimals(INR_CONV_TOTAL,2)+"");
                           billOfSalesMast.setDETAILLIST(ls);
                }
            
           
           
              
      
        } catch (Exception ee) {
            System.out.println(ee.toString());
           

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return billOfSalesMast;
    }
    public BillOfSalesMastBean getNewBOS(shahi.Action.MvxExp.PRE.Beans.BillOfSalesMastBean billOfSalesMast,String usrid,String LOCATION_CODE) throws SQLException {
       int k=0;
 
        try {
 
             con = new connection().getConnection();
             gettypelist();
           if(billOfSalesMast.getBOS_NO()!=null && billOfSalesMast.getBOS_NO().length()>0){
            
               st=con.prepareStatement("select * from ei_endors_mast a,ei_bos_detail b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and doc_send is not null and b.bos_no=? and b.bos_location=? ");
               st.setString(1,billOfSalesMast.getBOS_NO());
               st.setString(2,billOfSalesMast.getLOCATION_CODE());
               rs=st.executeQuery();
                while (rs.next()) 
                {          
                    billOfSalesMast.setDOC_FWD("YES");
                    
                } 
                  
              rs.close();st.close();
              
            st=con.prepareStatement("select BOS_NO,to_char(BOS_DATE,'dd-Mon-yyyy') BOS_DATE,INTERUNIT,DRIVER_NAME,BOS_LOCT,trim(SHIP_MODE) SHIP_MODE,DRIVER_MOBILE,LR_NO,DL_NO,to_char(LR_DATE,'dd/mm/yyyy') LR_DATE,VEHICLE_TYPE,BUYER,BUYER_ADDR,VEHICLE_NO,to_char(TO_DATE,'dd/mm/yyyy') TO_DATE,CHA,CHA_ADDR,to_char(VCH_ARV_DATE,'dd/mm/yyyy hh24:mi') VCH_ARV_DATE,HALT_HRS,UNIT,to_char(VCH_DEP_DATE,'dd/mm/yyyy hh24:mi') VCH_DEP_DATE,SEAL_NO,UNIT_TO,to_char(VCH_REP_DATE,'dd/mm/yyyy hh24:mi') VCH_REP_DATE,GR_WT,TRANSPORTER,DISPATCH_VIA,DAMAGE,PORT,PLAN_CFT,PLAN_SIZE,DESTINATION,ACTUAL_CFT,ACTUAL_SIZE,REMARKS,CFS_CODE,to_char(CUTOFF_DATE,'dd/mm/yyyy hh24:mi') CUTOFF_DATE,CANCEL_DATE,TDATE,SEH_USER,FY_HALT,FY_CANCEL,CONTAINER_TYPE,CONTAINER_NO,GPRS_YN,LEASE_TRK,RETURN_CARGO,RFID_SEAL_NO from ei_bos_mast where bos_no=? and BOS_LOCT=?");
            st.setString(1,billOfSalesMast.getBOS_NO());
            st.setString(2,billOfSalesMast.getLOCATION_CODE());
            rs=st.executeQuery();
               while (rs.next()) {
        
                   billOfSalesMast.setBOS_NO(rs.getString("BOS_NO"));
                   billOfSalesMast.setBOS_DATE(rs.getString("BOS_DATE"));
                   billOfSalesMast.setINTERUNIT(rs.getString("INTERUNIT"));
                   billOfSalesMast.setINTERUNIT_DIS(typeL.get(rs.getString("INTERUNIT")));
                   billOfSalesMast.setDRIVER_NAME(rs.getString("DRIVER_NAME"));
                   billOfSalesMast.setLOCATION_CODE(rs.getString("BOS_LOCT"));
                   billOfSalesMast.setSHIP_MODE(rs.getString("SHIP_MODE"));
                   billOfSalesMast.setDRIVER_MOBILE(rs.getString("DRIVER_MOBILE"));
                   billOfSalesMast.setLR_NO(rs.getString("LR_NO"));
                   billOfSalesMast.setDL_NO(rs.getString("DL_NO"));
                   billOfSalesMast.setLR_DATE(rs.getString("LR_DATE"));
                   billOfSalesMast.setVEHICLE_TYPE(rs.getString("VEHICLE_TYPE"));

                   if (rs.getString("VEHICLE_TYPE") != null && rs.getString("VEHICLE_TYPE").length() > 0) {
                       UnitBean ub = new BillOfSalesMasterDao().getCFSBean(rs.getString("VEHICLE_TYPE"), "BOS");
                       if (ub != null && ub.getUNIT_DESC() != null) {
                           billOfSalesMast.setVEHICLE_TYPE_DESC(ub.getUNIT_DESC());

                       }
                   }
                   billOfSalesMast.setBUYER(rs.getString("BUYER"));
                   billOfSalesMast.setBUYER_ADDR(rs.getString("BUYER_ADDR"));
                   if (rs.getString("BUYER") != null && rs.getString("BUYER").length() > 0) {
                       UnitBean ub = new BillOfSalesMasterDao().getBuyeraddress(rs.getString("BUYER"), rs.getString("BUYER_ADDR"));
                       if (ub != null && ub.getUNIT_DESC() != null) {
                           billOfSalesMast.setBUYER_DESC(ub.getUNIT_DESC());
                           billOfSalesMast.setBUYER_ADDRESS_NAME(ub.getUNIT_ADDRESS());
                       }
                   }
                   billOfSalesMast.setVEHICLE_NO(rs.getString("VEHICLE_NO"));
                   billOfSalesMast.setTO_DATE(rs.getString("TO_DATE"));
                   billOfSalesMast.setCHA(rs.getString("CHA"));
                   billOfSalesMast.setCHA_ADDR(rs.getString("CHA_ADDR"));
                   if (rs.getString("CHA") != null && rs.getString("CHA").length() > 0) {
                       AgentBean ab = new BillOfSalesMasterDao().getAgentName(rs.getString("CHA"), rs.getString("CHA_ADDR"));
                       if (ab != null && ab.getAgentName() != null) {
                           billOfSalesMast.setCHA_DESC(ab.getAgentName());
                           billOfSalesMast.setCHA_ADDRESS_NAME(ab.getAgentAdd());
                       }
                   } 

                   billOfSalesMast.setVCH_ARV_DATE(rs.getString("VCH_ARV_DATE"));
                   billOfSalesMast.setHALT_HRS(rs.getString("HALT_HRS"));
                   billOfSalesMast.setUNIT(rs.getString("UNIT"));
                   if (rs.getString("UNIT") != null && rs.getString("UNIT").length() > 0) {
                       UnitBean ab1 = new BillOfSalesMasterDao().getUnitByName(rs.getString("UNIT"));
                       if (ab1 != null && ab1.getUNIT_DESC() != null) {
                           billOfSalesMast.setUNIT_DESC(ab1.getUNIT_DESC());
                       }
                   }

                   billOfSalesMast.setVCH_DEP_DATE(rs.getString("VCH_DEP_DATE"));
                   billOfSalesMast.setSEAL_NO(rs.getString("SEAL_NO"));

                   billOfSalesMast.setUNIT_TO(rs.getString("UNIT_TO"));

                   if (rs.getString("UNIT_TO") != null && rs.getString("UNIT_TO").length() > 0) {
                       UnitBean ab1 = new BillOfSalesMasterDao().getUnitByName(rs.getString("UNIT_TO"));
                       if (ab1 != null && ab1.getUNIT_DESC() != null) {
                           billOfSalesMast.setUNIT_TO_DESC(ab1.getUNIT_DESC());
                       }
                   }
                   billOfSalesMast.setVCH_REP_DATE(rs.getString("VCH_REP_DATE"));
                   billOfSalesMast.setGR_WT(rs.getString("GR_WT"));
                   billOfSalesMast.setTRANSPORTER(rs.getString("TRANSPORTER"));
                   billOfSalesMast.setDISPATCH_VIA(rs.getString("DISPATCH_VIA"));
                   billOfSalesMast.setDAMAGE(rs.getString("DAMAGE"));

                   billOfSalesMast.setPORT(rs.getString("PORT"));
                   if (rs.getString("PORT") != null && rs.getString("PORT").length() > 0) {
                       UnitBean ab2 = new BillOfSalesMasterDao().getCsytabBeanByName(rs.getString("PORT"), "HAFE");
                       if (ab2 != null && ab2.getUNIT_DESC() != null) {
                           billOfSalesMast.setPORT_DESC(ab2.getUNIT_DESC());
                       }
                   }

                   billOfSalesMast.setPLAN_CFT(rs.getString("PLAN_CFT"));
                   billOfSalesMast.setPLAN_SIZE(rs.getString("PLAN_SIZE"));
                   billOfSalesMast.setDESTINATION(rs.getString("DESTINATION"));
                   if (rs.getString("DESTINATION") != null && rs.getString("DESTINATION").length() > 0) {
                       UnitBean ab2 = new BillOfSalesMasterDao().getCsytabBeanByName(rs.getString("DESTINATION"), "CSCD");
                       if (ab2 != null && ab2.getUNIT_DESC() != null) {
                           billOfSalesMast.setDESTINATION_DESC(ab2.getUNIT_DESC());
                       }
                   }
                   billOfSalesMast.setACTUAL_CFT(rs.getString("ACTUAL_CFT"));
                   billOfSalesMast.setACTUAL_SIZE(rs.getString("ACTUAL_SIZE"));
                   billOfSalesMast.setREMARKS(rs.getString("REMARKS"));
                   billOfSalesMast.setCFS_CODE(rs.getString("CFS_CODE"));
                   if (rs.getString("CFS_CODE") != null && rs.getString("CFS_CODE").length() > 0) {
                       UnitBean ub = new BillOfSalesMasterDao().getCFSBean(rs.getString("CFS_CODE"), "CFS");
                       if (ub != null && ub.getUNIT_DESC() != null) {
                           billOfSalesMast.setCFS_DESC(ub.getUNIT_DESC());

                       }
                   }

                    
                   billOfSalesMast.setCUTOFF_DATE(rs.getString("CUTOFF_DATE"));
                   billOfSalesMast.setCANCEL_DATE(rs.getString("CANCEL_DATE"));
                   billOfSalesMast.setFY_HALT(rs.getString("FY_HALT"));
                   billOfSalesMast.setFY_CANCEL(rs.getString("FY_CANCEL"));
                   billOfSalesMast.setCONTAINER_TYPE(rs.getString("CONTAINER_TYPE"));
                   billOfSalesMast.setCONTAINER_NO(rs.getString("CONTAINER_NO"));
                   
                   billOfSalesMast.setGPRS_YN(rs.getString("GPRS_YN"));
                   billOfSalesMast.setLEASE_YN(rs.getString("GPRS_YN"));
                   billOfSalesMast.setRETURN_CARGO(rs.getString("RETURN_CARGO"));
                  billOfSalesMast.setRFID_SEAL_NO(rs.getString("RFID_SEAL_NO"));
            
            }
           
                  
                       
                          List ls=new ArrayList();
                           st=con.prepareStatement("select BOS_NO,YEAR,COMPANY,INV_NO,EXCS_INV_NO,PKGS,QNTY,AVG_RATE,CRNCY,INR_CONV,INV_DESC,BOS_LOCATION,DISPATCH_YN,CFT_ACTUAL,UOM,CFT_PLAN,GRWT,to_char(PRINT_DATE,'dd/mm/yyyy') PRINT_DATE,TDATE,SEH_USER,CBM,VOL,FY_USER,FY_PKGS,FY_QNTY,to_char(FY_TDATE,'dd/mm/yy hh24:mi') fy_tdate,(SYSDATE-fy_tdate)*24 aa from exports.ei_bos_detail where BOS_NO=? and BOS_LOCATION=?");
                           st.setString(1,billOfSalesMast.getBOS_NO())	;
                           st.setString(2,billOfSalesMast.getLOCATION_CODE());
                            rs=st.executeQuery();
                           int QNTY_TOTAL=0;
                           int CTN_TOTAL=0;
                           int FYQNTY_TOTAL=0;
                           int FYCTN_TOTAL=0;
                           double FOB_TOTAL=0;
                           double INR_CONV_TOTAL=0;
                           double GRWT_TOTAL=0.0;
                           double CFTPLAN_TOTAL=0.0;
                           double CFTACT_TOTAL=0.0;
                           double VOL_TOTAL=0.0;
                           double CBM_TOTAL=0.0;
                           
                           
                           while(rs.next()){         
                               
                               String FOB=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty"),2);
                               String INR=roundToDecimals(rs.getDouble("avg_rate") * rs.getDouble("qnty") * rs.getDouble("INR_CONV"),2);
                               QNTY_TOTAL=QNTY_TOTAL+rs.getInt("QNTY");
                               CTN_TOTAL=CTN_TOTAL+rs.getInt("PKGS");
                               FYQNTY_TOTAL=FYQNTY_TOTAL+rs.getInt("FY_QNTY");
                               FYCTN_TOTAL=FYCTN_TOTAL+rs.getInt("FY_PKGS");
                               FOB_TOTAL=FOB_TOTAL+(rs.getDouble("avg_rate") * rs.getDouble("qnty"));
                               GRWT_TOTAL=GRWT_TOTAL+(rs.getDouble("GRWT"));
                               CBM_TOTAL=CBM_TOTAL+(rs.getDouble("CBM"));
                               VOL_TOTAL=VOL_TOTAL+(rs.getDouble("VOL"));
                               CFTPLAN_TOTAL=CFTPLAN_TOTAL+(rs.getDouble("CFT_PLAN"));
                               CFTACT_TOTAL=CFTACT_TOTAL+(rs.getDouble("CFT_ACTUAL"));
                               double aa=rs.getDouble("aa");
                               if (aa>6)
                               {
                                  billOfSalesMast.setUPDATE_ALLOW("NO");
                               }
                               INR_CONV_TOTAL=INR_CONV_TOTAL+(rs.getDouble("avg_rate") * rs.getDouble("qnty") * rs.getDouble("INR_CONV"));
                               
                         ls.add(new BillOfSalesBean(rs.getString("YEAR"), rs.getString("COMPANY"), rs.getString("INV_NO"), rs.getString("QNTY"), rs.getString("AVG_RATE"),rs.getString("CRNCY"),rs.getString("INR_CONV"), rs.getString("INV_DESC"),rs.getString("UOM"),FOB,INR,rs.getString("DISPATCH_YN"),rs.getString("CFT_PLAN"),rs.getString("CFT_ACTUAL"),rs.getString("GRWT"),rs.getString("PRINT_DATE"),rs.getString("PKGS"),rs.getString("CBM"),rs.getString("VOL"),rs.getString("EXCS_INV_NO"),"","","","","","","","",rs.getString("FY_USER"),rs.getString("FY_PKGS"),rs.getString("FY_QNTY"),rs.getString("FY_TDATE")));
                                 
                               
                           }
                             
                           billOfSalesMast.setQNTY_TOTAL(QNTY_TOTAL+"");
                           billOfSalesMast.setCTN_TOTAL(CTN_TOTAL+""); 
                           billOfSalesMast.setFYQNTY_TOTAL(FYQNTY_TOTAL+""); 
                           billOfSalesMast.setFYCTN_TOTAL(FYCTN_TOTAL+""); 
                           billOfSalesMast.setGRWT_TOTAL(GRWT_TOTAL+""); 
                           billOfSalesMast.setCBM_TOTAL(CBM_TOTAL+""); 
                           billOfSalesMast.setVOL_TOTAL(VOL_TOTAL+"");
                           billOfSalesMast.setCFTPLAN_TOTAL(CFTPLAN_TOTAL+""); 
                           billOfSalesMast.setCFTACT_TOTAL(CFTACT_TOTAL+"");  
                           billOfSalesMast.setFOB_TOTAL(roundToDecimals(FOB_TOTAL,2)+"");
                           billOfSalesMast.setINR_CONV_TOTAL(roundToDecimals(INR_CONV_TOTAL,2)+"");
                           billOfSalesMast.setDETAILLIST(ls);
                }
             
           
           
              
      
        } catch (Exception ee) {
            System.out.println(ee.toString());
           

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }

        } 

        return billOfSalesMast;
    }

  
}
   