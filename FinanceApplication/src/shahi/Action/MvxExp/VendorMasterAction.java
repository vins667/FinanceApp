/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MvxExp;


/**
 *
 * @author Ranjeet
 */
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import shahi.Action.MvxExp.Beans.CntryMasterBean;
import shahi.Action.MvxExp.Beans.VendMasterBean;
import java.util.Date;


public class VendorMasterAction extends ActionSupport {

    private List CONTENT_CODE;
    private List CONTENT_DESC;
    private List VEND_TYPECODE;
    private List VEND_TYPEDESC;
    private List errorList=null;
    private List VendTypeList=null;
    private List FinCompList=null;
    private List FinAcList=null;
    private List CONTENT_GRPCODE;
    private String searchcode;
    private String searchdesc;
    private String searchtype;
    private String saveFlag;
    private String updFlag;
    private String vend_code;
    private String vend_name;
    private String vend_type;
    private String vend_addr;
    private String city;
    private String state;
    private String pin_code;
    private String phones;
    private String fax;
    private String email;
    private String cst_no;
    private String lst_no;
    private String tin_no;
    private String iec_no;
    private String comp_grp_code;
    private String account_code;
    private String account_name;
    private Date iec_date;
    private Date cst_date;
    private Date lst_date;
    private Date tin_date;
    private String cst_date1;
    private String lst_date1;
    private String tin_date1;
    private String iec_date1;
    private String aausrid;
    private String fin_comp_id;
    private String vendview;
    @Override

    public String execute() {
          errorList = new ArrayList();
          VendTypeList = new ArrayList();

         int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        if(usrid==null)
        {
           session.put("sessUserId",aausrid);
           usrid=aausrid;
        }
      //usrid="227350";
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


            PreparedStatement stat1 = null;
            PreparedStatement stat = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {

                  stat1 = conn.prepareStatement("select type_desc,type_code from ei_grup_type_dtls where grup_type_code='VT' order  by 1,2"  );
                  result1 = stat1.executeQuery();
                 while(result1.next())
                  {
                     VendTypeList.add(new VendMasterBean(result1.getString("type_code"),result1.getString("type_desc")));
                  }
 
                String sqlstr="";
             if (saveFlag!=null)
             {
             if(searchcode!=null && searchcode.length()>0 )
                {
                sqlstr="  and vend_code like '"+searchcode.toUpperCase()+"%'";
                }
                if(searchdesc!=null && searchdesc.length()>0)
                {
                sqlstr=sqlstr +"  and  vend_name like '"+searchdesc.toUpperCase()+"%'";
                }
               if(searchtype!=null && searchtype.length()>0)
                {
                sqlstr=sqlstr +"  and  nvl(vend_type,'NA') like '"+searchtype.toUpperCase()+"%'";
               
               }
                 String statusflag="NO";

                          stat1 = conn.prepareStatement("select vend_code,vend_name,trim(vend_addr)||' '||trim(city)||' '||trim(state) vend_address from pr_vend_mast where vend_name is not null "+sqlstr+"order by 1,2"  );
                          result1 = stat1.executeQuery();

                                while(result1.next())
                                {
                                 errorList.add(new CntryMasterBean(result1.getString("vend_code"),result1.getString("vend_name"),result1.getString("vend_address"),""));
                                 }


                            }
             }
             catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : VendorMaster.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : VendorMaster.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : VendorMaster.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
            CONTENT_CODE=null;
            CONTENT_DESC=null;
            CONTENT_GRPCODE=null;
            VEND_TYPECODE=null;
            VEND_TYPEDESC=null;
           addActionMessage("Records Save(s) !!");
            return SUCCESS;
        }

        else {

           // addActionMessage("Records Not Save(s) !!");
            return ERROR;
        }


    }
public String VendorInsert() {
          errorList = new ArrayList();
          VendTypeList = new ArrayList();
          FinAcList=new ArrayList();
          FinCompList=new ArrayList();
         int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       if(usrid==null)
        {
           session.put("sessUserId",aausrid);
           usrid=aausrid;
        }
     // usrid="227350";
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


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            PreparedStatement stat=null;
            ResultSet result = null;

            try {

                   stat1 = conn.prepareStatement("select type_desc,type_code from ei_grup_type_dtls where grup_type_code='VT' order  by 1,2"  );
                   result1 = stat1.executeQuery();
                   while(result1.next())
                   {
                     VendTypeList.add(new VendMasterBean(result1.getString("type_code"),result1.getString("type_desc")));
                   } 
                   stat1 = conn.prepareStatement("select comp_name||' ('||comp_id||')' comp_name,comp_id from fa_company order  by 1,2"  );
                   result1 = stat1.executeQuery();
                   while(result1.next())
                   {
                     FinCompList.add(new VendMasterBean(result1.getString("comp_id"),result1.getString("comp_name")));
                   }
 
                   if (vendview!=null && vendview.length()>0 )
                   {
                       vendview = vendview+"%";
                   stat1 = conn.prepareStatement("select account_desc||' ('||account_code||')' acc_name,account_code from fa_account_master where account_desc like upper(?) order  by 1,2"  );
                   stat1.setString(1,vendview);
                   result1 = stat1.executeQuery();
                   while(result1.next())
                   {
                     FinAcList.add(new VendMasterBean(result1.getString("account_code"),result1.getString("acc_name")));
                   }
                   }

                   if(saveFlag!=null && saveFlag.length()>0)
                             {
                                 stat1 = conn.prepareStatement("select * from pr_vend_mast where vend_code=? ");
                                 stat1.setString(1,vend_code.toUpperCase());
                                 result1 = stat1.executeQuery();
                                 if(result1.next())
                                {
                                 errorList.add(new VendMasterBean(result1.getString("vend_code"),""));
                                }else{
                                  stat1 = conn.prepareStatement("insert into pr_Vend_mast(vend_code,vend_name,vend_type,vend_addr,city,state,pin_code,email,phones,fax,cst_no,lst_no,tin_no,cst_date,lst_date,tin_date,iec_no,iec_date,fin_comp_id,account_code,SEH_USER,TDATE) " +
                                                                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,to_date(?,'yyyy-mm-dd'),?,?,?,sysdate)");
                                  stat1.setString(1, vend_code.toUpperCase());
                                  stat1.setString(2, vend_name.toUpperCase());
                                  stat1.setString(3,vend_type.toUpperCase()); 
                                  stat1.setString(4,vend_addr.toUpperCase());
                                  stat1.setString(5,city.toUpperCase());
                                  stat1.setString(6,state.toUpperCase());
                                  stat1.setString(7,pin_code.toUpperCase());
                                  stat1.setString(8,email.toUpperCase());
                                  stat1.setString(9,phones.toUpperCase());
                                  stat1.setString(10,fax.toUpperCase());
                                  stat1.setString(11,cst_no.toUpperCase());
                                  stat1.setString(12,lst_no.toUpperCase());
                                  stat1.setString(13,tin_no.toUpperCase());
                          
                                 if (cst_date1!=null && cst_date1.length()>0)
                                    {
                                     cst_date1 = cst_date1.toString().substring(0, 10);
                                    }

                                   if (lst_date1!=null && lst_date1.length()>0)
                                    {
                                     lst_date1 = lst_date1.toString().substring(0, 10);
                                    }
                                     if (tin_date1!=null && tin_date1.length()>0)
                                    {
                                     tin_date1 = tin_date1.toString().substring(0, 10);
                                    }
                                   if (iec_date1!=null && iec_date1.length()>0)
                                    {
                                     iec_date1 = iec_date1.toString().substring(0, 10);
                                    }
                                  stat1.setString(14,cst_date1);
                                  stat1.setString(15,lst_date1);
                                  stat1.setString(16,tin_date1);
                                  stat1.setString(17,iec_no.toUpperCase());
                                  stat1.setString(18,iec_date1);
                                  stat1.setString(19,fin_comp_id);
                                  stat1.setString(20,account_code);
                                  stat1.setString(21,usrid);
                                  stat1.executeUpdate();
                                  flag = 1;

                                }

                             }
                          if(updFlag!=null && updFlag.length()>0)
                          {     
                            stat1=conn.prepareStatement("update pr_vend_mast set vend_type=upper(?),vend_addr=upper(?),city=upper(?),state=upper(?),pin_code=upper(?),email=upper(?),phones=upper(?),fax=upper(?),cst_no=upper(?),lst_no=upper(?),tin_no=upper(?),cst_date=to_date(?,'yyyy-mm-dd'),lst_date=to_date(?,'yyyy-mm-dd'),tin_date=to_date(?,'yyyy-mm-dd'),fin_comp_id=?,account_code=?  where vend_code=?");
                             stat1.setString(1,vend_type);
                            stat1.setString(2,vend_addr);
                            stat1.setString(3,city);
                            stat1.setString(4,state);
                            stat1.setString(5,pin_code);
                            stat1.setString(6,email);
                            stat1.setString(7,phones);
                            stat1.setString(8,fax);
                            stat1.setString(9,cst_no);
                            stat1.setString(10,lst_no);
                            stat1.setString(11,tin_no);
                        if (cst_date1!=null && cst_date1.length()>0)
                        {
                         cst_date1 = cst_date1.toString().substring(0, 10);
                        }

                       if (lst_date1!=null && lst_date1.length()>0)
                        {
                         lst_date1 = lst_date1.toString().substring(0, 10);
                        }
                         if (tin_date1!=null && tin_date1.length()>0)
                        {
                         tin_date1 = tin_date1.toString().substring(0, 10);
                        }
                            stat1.setString(12,cst_date1);
                             stat1.setString(13,lst_date1);
                            stat1.setString(14,tin_date1);
                            stat1.setString(15,fin_comp_id);
                            stat1.setString(16,account_code);
                         System.out.println("AC "+account_code);
                            stat1.setString(17,vend_code.toUpperCase());
                            stat1.executeUpdate();
                            flag = 1;
                          }

                 stat1=conn.prepareStatement("select vend_name,vend_addr,vend_type,city,state,pin_code,phones,fax,email,cst_no,lst_no,tin_no,iec_no,comp_grp_code,account_code,iec_date,cst_date,lst_date, tin_date,fin_comp_id from pr_Vend_mast where vend_code=? ");
                   stat1.setString(1,vend_code.toUpperCase());
                   result1=stat1.executeQuery();
                   if (result1.next())
                   {  stat=conn.prepareStatement("select account_desc from fa_account_master where account_code=?");
                      stat.setString(1,result1.getString("account_code"));
                      result=stat.executeQuery();
                      if (result.next())
                      {
                         account_name=result.getString("account_desc");
                      }

                    vend_name=result1.getString("vend_name");   vend_addr=result1.getString("vend_addr");
                    vend_type=result1.getString("vend_type");   city=result1.getString("city");
                    state=result1.getString("state");           pin_code=result1.getString("pin_code");
                    phones=result1.getString("phones");         fax=result1.getString("fax");
                    email=result1.getString("email");           cst_no=result1.getString("cst_no");
                    lst_no=result1.getString("lst_no");          tin_no=result1.getString("tin_no");
                    iec_no=result1.getString("iec_no");           comp_grp_code=result1.getString("comp_grp_code");
                    account_code=result1.getString("account_code");
                    iec_date=result1.getDate("iec_date");      
                    cst_date=result1.getDate("cst_date");       lst_date=result1.getDate("lst_date");
                    tin_date=result1.getDate("tin_date");       fin_comp_id=result1.getString("fin_comp_id");

             
                   }

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : VendorMaster.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : VendorMaster.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : VendorMaster.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }
 
        if (flag == 1) {
            CONTENT_CODE=null;
            CONTENT_DESC=null;
            CONTENT_GRPCODE=null;
            VEND_TYPECODE=null;
            VEND_TYPEDESC=null;
            vend_code=null;
            vend_name=null;
            vend_type=null;
            city=null;state=null;pin_code=null;cst_no=null;lst_no=null;tin_no=null;
            cst_date1=null;lst_date1=null;tin_date1=null;iec_date1=null;iec_no=null;
            vend_type=null;fin_comp_id=null;comp_grp_code=null;account_code=null;
            account_name=null;
           addActionMessage("Records Save(s) !!");
            return SUCCESS;
        }

        else {

           // addActionMessage("Records Not Save(s) !!");
            return ERROR;
        }


    }



  public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }



    public List getCONTENT_CODE() {
        return CONTENT_CODE;
    }

    public void setCONTENT_CODE(List CONTENT_CODE) {
        this.CONTENT_CODE = CONTENT_CODE;
    }

    public List getCONTENT_DESC() {
        return CONTENT_DESC;
    }

    public void setCONTENT_DESC(List CONTENT_DESC) {
        this.CONTENT_DESC = CONTENT_DESC;
    }

    public List getErrorList() {
        return errorList;
    }

    public void setErrorList(List errorList) {
        this.errorList = errorList;
    }

    public List getCONTENT_GRPCODE() {
        return CONTENT_GRPCODE;
    }

    public void setCONTENT_GRPCODE(List CONTENT_GRPCODE) {
        this.CONTENT_GRPCODE = CONTENT_GRPCODE;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public List getVEND_TYPECODE() {
        return VEND_TYPECODE;
    }

    public void setVEND_TYPECODE(List VEND_TYPECODE) {
        this.VEND_TYPECODE = VEND_TYPECODE;
    }

    public List getVEND_TYPEDESC() {
        return VEND_TYPEDESC;
    }

    public void setVEND_TYPEDESC(List VEND_TYPEDESC) {
        this.VEND_TYPEDESC = VEND_TYPEDESC;
    }

    public List getVendTypeList() {
        return VendTypeList;
    }

    public void setVendTypeList(List VendTypeList) {
        this.VendTypeList = VendTypeList;
    }

    public String getSearchcode() {
        return searchcode;
    }

    public void setSearchcode(String searchcode) {
        this.searchcode = searchcode;
    }

    public String getSearchdesc() {
        return searchdesc;
    }

    public void setSearchdesc(String searchdesc) {
        this.searchdesc = searchdesc;
    }

    public String getVend_code() {
        return vend_code;
    }

    public void setVend_code(String vend_code) {
        this.vend_code = vend_code;
    }

    public String getVend_name() {
        return vend_name;
    }

    public void setVend_name(String vend_name) {
        this.vend_name = vend_name;
    }

    public String getVend_type() {
        return vend_type;
    }

    public String getFin_comp_id() {
        return fin_comp_id;
    }

    public void setFin_comp_id(String fin_comp_id) {
        this.fin_comp_id = fin_comp_id;
    }

    public void setVend_type(String vend_type) {
        this.vend_type = vend_type;
    }

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getVendview() {
        return vendview;
    }

    public void setVendview(String vendview) {
        this.vendview = vendview;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }


    public String getAccount_code() {
        return account_code;
    }

    public void setAccount_code(String account_code) {
        this.account_code = account_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComp_grp_code() {
        return comp_grp_code;
    }

    public void setComp_grp_code(String comp_grp_code) {
        this.comp_grp_code = comp_grp_code;
    }

    public String getCst_no() {
        return cst_no;
    }

    public void setCst_no(String cst_no) {
        this.cst_no = cst_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getIec_no() {
        return iec_no;
    }

    public void setIec_no(String iec_no) {
        this.iec_no = iec_no;
    }

  
    public String getLst_no() {
        return lst_no;
    }

    public void setLst_no(String lst_no) {
        this.lst_no = lst_no;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTin_no() {
        return tin_no;
    }

    public void setTin_no(String tin_no) {
        this.tin_no = tin_no;
    }

    public String getVend_addr() {
        return vend_addr;
    }

    public void setVend_addr(String vend_addr) {
        this.vend_addr = vend_addr;
    }

    public Date getCst_date() {
        return cst_date;
    }

    public void setCst_date(Date cst_date) {
        this.cst_date = cst_date;
    }

    public Date getIec_date() {
        return iec_date;
    }

    public void setIec_date(Date iec_date) {
        this.iec_date = iec_date;
    }
 
  
    public Date getLst_date() {
        return lst_date;
    }

    public void setLst_date(Date lst_date) {
        this.lst_date = lst_date;
    }

    public Date getTin_date() {
        return tin_date;
    }

    public void setTin_date(Date tin_date) {
        this.tin_date = tin_date;
    }

    public String getCst_date1() {
        return cst_date1;
    }

    public void setCst_date1(String cst_date1) {
        this.cst_date1 = cst_date1;
    }

    public String getLst_date1() {
        return lst_date1;
    }

    public void setLst_date1(String lst_date1) {
        this.lst_date1 = lst_date1;
    }

    public String getTin_date1() {
        return tin_date1;
    }

    public void setTin_date1(String tin_date1) {
        this.tin_date1 = tin_date1;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getIec_date1() {
        return iec_date1;
    }

    public void setIec_date1(String iec_date1) {
        this.iec_date1 = iec_date1;
    }

    public List getFinAcList() {
        return FinAcList;
    }

    public void setFinAcList(List FinAcList) {
        this.FinAcList = FinAcList;
    }

    public List getFinCompList() {
        return FinCompList;
    }

    public void setFinCompList(List FinCompList) {
        this.FinCompList = FinCompList;
    }

   

}
