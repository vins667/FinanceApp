package shahi.Action.MvxExp.PRE;

 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.PRE.Beans.DeecQueryBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
  

public class DeecQueryAction extends ActionSupport {
    private String currentdate;
    private String aausrid;
    private String searchitem;
    private String searchtype;
    private String viewFlag;
    private List showList;
   
    private List ShowDetail=new ArrayList();
    private List InvList = new ArrayList();
    private List OrdQtyList = new ArrayList();
    private List ItemList = new ArrayList();
    private List indentList = new ArrayList();
    private List awblList = new ArrayList();
    private List implicList = new ArrayList();
    private List ReclassList= new ArrayList();
    
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
                connBI=new ConnectionShahiHrisNew().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            try {
                 connBI.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            PreparedStatement stat = null;
            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            PreparedStatement stat3 = null;
            PreparedStatement stat4 = null;
            ResultSet result = null;
            ResultSet result1 = null;
            ResultSet result2 = null;
            ResultSet result3 = null;
            ResultSet result4 = null;
            try {
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
           
              
               
                if (viewFlag.equals("Yes")) 
                {  
                    stat = connBI.prepareStatement("select iapuno,iafaci,iawhlo,IAORTY,IACUCD,IASUNO from prodbi.mphead   where  (IACONO,IAPUNO) IN (SELECT IBCONO, IBPUNO FROM prodbi.MPLINE, prodbi.MITMAS WHERE IBCONO = MMCONO AND IBITNO = MMITNO AND IBITNO LIKE ?  AND MMPRGP like ? ) order by 1");
                 //   stat.setString(1,searchitem+"%".toUpperCase().trim());
                  String  newitem=searchitem+"%";
                   searchtype=searchtype+"%"; 
                    stat.setString(1,newitem.toUpperCase().trim());
                    stat.setString(2,searchtype.trim());
                     result = stat.executeQuery(); 
                    while (result.next()) 
                    { String ORDESC=""; String SUPNAME="";
                          
                          stat1=connBI.prepareStatement("select OTTX40 from prodbi.mpordt where OTCONO=111 and otorty=?");
                          stat1.setString(1,result.getString("IAORTY"));
                          result1=stat1.executeQuery();
                          while (result1.next()){
                             ORDESC=result1.getString("OTTX40");   
                          }
                          
                         // UnitBean bn=new  PreInvoiceDao().getCsytabBeanByName(result.getString("IAORTY"),"SUCL");
                         // ORDESC=bn.getUNIT_DESC();  
                           
                         UnitBean bn=new PreInvoiceDao().getCHAName(result.getString("IASUNO"));
                          SUPNAME=bn.getUNIT_DESC();
                          
                            ShowDetail.add(new DeecQueryBean(result.getString("iapuno"), result.getString("iafaci"), result.getString("iawhlo"), result.getString("IAORTY"),ORDESC,result.getString("IACUCD"),result.getString("IASUNO"),SUPNAME));
                       
                            stat1 = connBI.prepareStatement("select distinct ibpuno, ibitno, ibpitd, MMPRGP from prodbi.MPLINE,prodbi.MITMAS where ibcono = mmcono and ibitno = mmitno and IBPUNO=? ");
                            stat1.setString(1,result.getString("IAPUNO")); 
                            result1 = stat1.executeQuery(); 
                            while (result1.next()) 
                             {   
                                 ItemList.add(new DeecQueryBean(result1.getString("ibpuno"), result1.getString("ibitno"),result1.getString("ibpitd"), result1.getString("MMPRGP"),"","","",""));
                             }
                            stat2 = conn.prepareStatement("select distinct b.po_numb, a.pi_Control_no, a.pi_Gen_desc from ei_pi_master a, ei_pi_po_adjustment b where a.pi_control_no = b.pi_Control_no and B.po_numb=? UNION ALL SELECT DISTINCT MVX_PO, TO_NUMBER(A.IND_NO), A.GEN_DESC FROM PI_INDENT_MAST A, PI_INDENT_DTLS B WHERE A.IND_NO = B.IND_NO  and MVX_PO =?   ");
                            stat2.setString(1,result.getString("IAPUNO")); 
                            stat2.setString(2,result.getString("IAPUNO"));
                            result2 = stat2.executeQuery(); 
                            while (result2.next()) 
                            {
                              indentList.add(new DeecQueryBean(result2.getString("po_numb"), result2.getString("pi_Control_no"),result2.getString("pi_Gen_desc"),"","","","",""));
                   
                              stat3 = conn.prepareStatement("select distinct b.pi_control_No, a.pi_Type, decode(a.LIC_TYPE,'LIC','Licence','WLC','Without Licence','EPC','EPCG Licence','DTY','Duty','LAD','Licence/Duty','EPL','EPC Licence',a.LIC_TYPE) lic_type, a.pi_Ref_no,a.pi_be_no, to_char(a.pi_be_date,'dd/mm/yyyy') pi_be_date from ei_pi_awbl_mast a, ei_pi_awbl_Dtls b where b.pi_Type = a.pi_type and b.pi_ref_No = a.pi_Ref_no and b.pi_control_No = ? "+
                                                              "union all select distinct TO_NUMBER(b.ind_no), a.ref_Type, e.sub_desc cler_mode, a.Ref_no,c.be_no, to_char(d.be_date,'dd/mm/yyyy') be_date from pi_imp_awbl_mast a, pi_imp_awbl_cinv b , pi_imp_boe_Dtls c, pi_imp_boe_mast d, pi_imp_sys_type e where b.ref_No = a.Ref_no and c.ref_no(+) = b.ref_no and d.be_no(+) = c.be_no and e.grp_code= 'CLRMD' and e.sub_Code= a.cler_mode and b.ind_no=? ");
                                stat3.setString(1,result2.getString("pi_Control_no")); 
                                stat3.setString(2,result2.getString("pi_Control_no")); 
                                 result3 = stat3.executeQuery(); 
                                while (result3.next())  
                                { 
                                  awblList.add(new DeecQueryBean(result3.getString("pi_control_No"), result3.getString("pi_Type"),result3.getString("lic_type"),result3.getString("pi_Ref_no"),result3.getString("pi_be_no"),result3.getString("pi_be_date"),"",""));

                                   stat4 = conn.prepareStatement("select distinct pi_type, pi_ref_no, PI_LC_TYPE, pi_lc_no, PI_QTY_SQM, BE_DESC from ei_pi_awbl_lc_Dtls where  pi_type= ? and pi_ref_no=? union all select distinct c.ref_type, c.ref_no, a.LIC_type , a.LIC_no , a.LIC_qty ,a.BE_DESC from PI_IMP_CINV_LIC_DTLS a, pi_imp_awbl_cinv b, pi_imp_awbl_mast c where a.ind_no= b.ind_no and a.cinv_no = b.cinv_no and b.ref_no = c.ref_no and c.ref_type= ? and c.ref_no=?");
                                   stat4.setString(1,result3.getString("pi_Type")); 
                                   stat4.setString(2,result3.getString("pi_Ref_no"));
                                   stat4.setString(3,result3.getString("pi_Type")); 
                                   stat4.setString(4,result3.getString("pi_Ref_no"));
                                    result4 = stat4.executeQuery(); 
                                   while (result4.next()) 
                                   {     implicList.add(new DeecQueryBean(result4.getString("pi_type"), result4.getString("pi_ref_no"),result4.getString("PI_LC_TYPE"),result4.getString("pi_lc_no"),result4.getString("PI_QTY_SQM"),result4.getString("BE_DESC"), result2.getString("pi_Control_no"),""));
                                 
                                   }
                                   }
                                  }
                                 
                          } 
                       
                      
                       stat=conn.prepareStatement("select a.excs_inv_no,buyer,desti_cntry,to_char(t_o_date,'dd/mm/yyyy') t_o_date,to_char(awbdate,'dd/mm/yyyy') awbdate,made_for,decode(dbk_slno,null,'DEEC','DBK') invtype,sum(qty_endors) invqty from ei_endors_mast a, ei_endors_Dtls b where a.t_mod='LGM4' AND a.company = b.company and a.year = b.year and a.inv_no = b.inv_no and a.type = b.type and a.type = 'E' and substr(item_no,1,4)=? "+
                                                  " group by a.excs_inv_no,buyer,desti_cntry,to_char(t_o_date,'dd/mm/yyyy'),to_char(awbdate,'dd/mm/yyyy'),made_for,decode(dbk_slno,null,'DEEC','DBK') order by 1 ");
                       stat.setString(1,searchitem.toUpperCase().trim());
                        result = stat.executeQuery(); 
                       while (result.next()) 
                        {
                         InvList.add(new DeecQueryBean(result.getString("excs_inv_no"), result.getString("buyer"), result.getString("desti_cntry"), result.getString("t_o_date"),result.getString("awbdate"),result.getString("invqty"),result.getString("made_for"),result.getString("invtype")));
                        }
                       
                       stat=connBI.prepareStatement("select OBORNO, OBHDPR, SUM(OBORQT) QTY, SUM(OBDLQT+OBIVQT) SHIP from prodbi.ooline WHERE OBCONO = 111 and OBHDPR=? GROUP BY OBORNO, OBHDPR order by 1 ");
                       stat.setString(1,searchitem.toUpperCase().trim());
                        result = stat.executeQuery(); 
                       while (result.next()) 
                       {
                         OrdQtyList.add(new DeecQueryBean(result.getString("OBORNO"), result.getString("OBHDPR"), result.getString("QTY"), result.getString("SHIP"),"","","",""));
                      }
               
                        stat=connBI.prepareStatement("select mtitno,substr(mtrftx,1,15) toitem,  sum(mttrqt) qty , sum(mttrpr) amt from prodbi.MITTRA ,prodbi.mitmas  WHERE mtcono = 111 and  mtttid = 'REC' and  mmcono = 111 and mmitno = mtitno and ( mtitno like ? or mtrftx like ? ) and MMPRGP like ? GROUP BY   mtitno, substr(mtrftx,1,15) order by 1 ");
                       stat.setString(1,searchitem.toUpperCase().trim()+"%");
                        stat.setString(2,searchitem.toUpperCase().trim()+"%");
                       stat.setString(3,searchtype.trim());
                        result = stat.executeQuery(); 
                       while (result.next()) 
                       {
                            
                         ReclassList.add(new DeecQueryBean(result.getString("mtitno"), "", result.getString("toitem"), result.getString("qty"),result.getString("amt"),"","",""));
                      }
                       
                
                
                } // View Flg Close      
 

           
                     
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : DeecQueryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : DeecQueryAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                      if (result2 != null) {
                        result2.close();
                    }
                    if (result3 != null) {
                        result3.close();
                    }

                    if (stat1 != null) {
                        stat1.close();
                    }
                     
                    if (stat != null) {
                        stat.close();
                    }
                     if (stat2 != null) {
                        stat2.close();
                    } if (stat3 != null) {
                        stat3.close();
                    }
                    if (conn != null) {
                        conn.close(); 
                    }
                      if (connBI != null) {
                        connBI.close(); 
                    }
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    stat3=null;
                     
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : DeecQueryAction.java Exception in finally block");
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

    public List getInvList() {
        return InvList;
    }

    public void setInvList(List InvList) {
        this.InvList = InvList;
    }

    public void setShowDetail(List ShowDetail) {
        this.ShowDetail = ShowDetail;
    }

    public String getSearchitem() {
        return searchitem;
    }

    public void setSearchitem(String searchitem) {
        this.searchitem = searchitem;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public List getOrdQtyList() {
        return OrdQtyList;
    }

    public void setOrdQtyList(List OrdQtyList) {
        this.OrdQtyList = OrdQtyList;
    }

    public List getItemList() {
        return ItemList;
    }

    public void setItemList(List ItemList) {
        this.ItemList = ItemList;
    }

    public List getIndentList() {
        return indentList;
    }

    public void setIndentList(List indentList) {
        this.indentList = indentList;
    }

    public List getAwblList() {
        return awblList;
    }

    public void setAwblList(List awblList) {
        this.awblList = awblList;
    }

    public List getImplicList() {
        return implicList;
    }

    public void setImplicList(List implicList) {
        this.implicList = implicList;
    }

    public List getReclassList() {
        return ReclassList;
    }

    public void setReclassList(List ReclassList) {
        this.ReclassList = ReclassList;
    }
     
       
} 
 