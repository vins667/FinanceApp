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


public class CntryMasterAction extends ActionSupport {

    private List CONTENT_CODE;
    private List CONTENT_DESC;
    private List errorList=null;
    private List CONTENT_GRPCODE;
    private String searchcode;
    private String searchdesc;
    private List chkdel;
    private String saveFlag;
    private String delFlag;
    private List qa_codeList;
    private String aausrid;

    @Override
      
    public String execute() {
          errorList = new ArrayList();
          qa_codeList=new ArrayList();
         int falg = 0;
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
                String sqlstr="";
               if(chkdel!=null && chkdel.size()>0 &&delFlag!=null)
                {
                    for(int i=0; i<chkdel.size(); i++)
                    {
                        stat1 = conn.prepareStatement("delete from ei_country_mast where country=?");
                        stat1.setString(1,chkdel.get(i).toString());
                        stat1.executeUpdate();
                        falg = 1;
                    }
                  }


             if (saveFlag!=null)
             {
             if(searchcode!=null && searchcode.length()>0 )
                {
                sqlstr="  and country like '"+searchcode.toUpperCase()+"%'";
                }
                if(searchdesc!=null && searchdesc.length()>0)
                {
                sqlstr=sqlstr +"  and  country_desc like '"+searchdesc.toUpperCase()+"%'";
                }
                         
                          stat1 = conn.prepareStatement("select country,country_desc,country_grp_code from ei_country_mast where country is not null " +sqlstr +"order by 1,2" );
                          result1 = stat1.executeQuery();
                          while(result1.next())
                          {

                             stat = conn.prepareStatement("select nvl(count(*),0) v_cnt  from ei_endors_mast where (desti_cntry=? or CNTRY_ORIGIN=?)");
                             stat.setString(1,result1.getString("country"));
                             stat.setString(2,result1.getString("country"));
                             result = stat.executeQuery();
                             String statusflag="NO";
                                while(result.next())
                                {statusflag=result.getString("v_cnt");
                                }

                               errorList.add(new CntryMasterBean(result1.getString("country"),result1.getString("country_desc"),statusflag,result1.getString("country_grp_code")));
                          }
                           
                
             }
            }
             catch (Exception e) {

                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : CntryMaster.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : CntryMaster.java" + e);

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
                    falg = 0;
                    System.out.print("File Name : CntryMaster.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (falg == 1) {
            CONTENT_CODE=null;
            CONTENT_DESC=null;
            CONTENT_GRPCODE=null;
           addActionMessage("Records Save(s) !!");
            return SUCCESS;
        }

        else {

           // addActionMessage("Records Not Save(s) !!");
            return ERROR;
        }


    }
public String Cntryshow() {
          errorList = new ArrayList();
          qa_codeList=new ArrayList();
         int falg = 0;
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
            ResultSet result = null;

            try {
                if(saveFlag!=null && saveFlag.length()>0)
                {
                 if(CONTENT_CODE!=null && CONTENT_DESC!=null && CONTENT_CODE.size()>0 && CONTENT_DESC.size()>0)
                 {

                     for(int i=0; i<CONTENT_CODE.size(); i++)
                     {
                         if(CONTENT_CODE.get(i).toString()!=null && CONTENT_CODE.get(i).toString().length()>0)
                         {
                                stat1 = conn.prepareStatement("select * from ei_country_mast where country=? ");
                                 stat1.setString(1,CONTENT_CODE.get(i).toString().toUpperCase());
                                 result1 = stat1.executeQuery();
                                if(result1.next())
                                {
                                  errorList.add(new CntryMasterBean(CONTENT_CODE.get(i).toString().toUpperCase(), CONTENT_DESC.get(i).toString().toUpperCase(),CONTENT_GRPCODE.get(i).toString().toUpperCase(),""));
                                }else{
                                  stat1 = conn.prepareStatement("insert into ei_country_mast(country,country_desc,country_grp_code,SEH_USER,TDATE) values(?,?,?,?,sysdate)");
                                  stat1.setString(1, CONTENT_CODE.get(i).toString().toUpperCase());
                                     stat1.setString(2, CONTENT_DESC.get(i).toString().toUpperCase());
                                  stat1.setString(3, CONTENT_GRPCODE.get(i).toString().toUpperCase());
                                  stat1.setString(4,usrid);
                                  stat1.executeUpdate();
                                  falg = 1;

                                }
                         }
                     }
                 }
                 }




            } catch (Exception e) {

                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : CntryMaster.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : CntryMaster.java" + e);

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
                    falg = 0;
                    System.out.print("File Name : CntryMaster.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (falg == 1) {
            CONTENT_CODE=null;
            CONTENT_DESC=null;
            CONTENT_GRPCODE=null;
           addActionMessage("Records Save(s) !!");
            return SUCCESS;
        }

        else {

            addActionMessage("Records Not Save(s) !!");
            return ERROR;
        }


    }

    


    public List getQa_codeList() {
        return qa_codeList;
    }

    public void setQa_codeList(List qa_codeList) {
        this.qa_codeList = qa_codeList;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }



}
