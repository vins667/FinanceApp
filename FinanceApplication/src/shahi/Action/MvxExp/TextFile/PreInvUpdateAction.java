
 

 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
  


public class PreInvUpdateAction extends ActionSupport {

    
    private List empList = new ArrayList();
     private String Eview;
    private String empview;
    
    
    private String aausrid;
    
 
    @Override
      
    public String execute() {
          
           
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
            ResultSet result1 = null;
        
            try {
                
             stat1=conn.prepareStatement("select location_code from seh_web_users where user_id=?");
             stat1.setString(1,aausrid);
             result1=stat1.executeQuery();
             while (result1.next())
             {
              LOCATION_CODE=result1.getString("LOCATION_CODE");
             }
                
               String sqlstr="";
                   if(empview!=null && empview.length()>0)
                    { sqlstr="  and  excs_inv_no like '"+empview.toUpperCase()+"%'";
                          stat1 = conn.prepareStatement(" select excs_inv_no from ei_endors_mast  where nvl(surrender_yn,'N')='N'  and location=? "+sqlstr+" order by 1 "  );
                          stat1.setString(1,LOCATION_CODE);
                          result1 = stat1.executeQuery();
                        while(result1.next())
                        {   empList.add(new GetListBean(result1.getString("excs_inv_no"),result1.getString("excs_inv_no")));
                      
                         } 
                      } 
               
                 if (Eview!=null  && Eview.length()>0)
                        { stat1 = conn.prepareStatement(" select excs_inv_no from EI_ENDORS_MAST where nvl(surrender_yn,'N')='N' AND location=? order by 1 "  );
                          stat1.setString(1,LOCATION_CODE);
                        result1 = stat1.executeQuery();
                        while(result1.next())
                        {
                         empList.add(new GetListBean(result1.getString("excs_inv_no"),result1.getString("excs_inv_no")));
                         }
                       }



            }
             catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : POHELPAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : POHELPAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
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
                    System.out.print("File Name : POHELPAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

           return INPUT;  

        }

        if (flag == 1) {
              return INPUT;
        }
 
        else {

           // addActionMessage("Records Not Save(s) !!");
            return INPUT;
        }


    }

 
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public List getEmpList() {
        return empList;
    }

    public void setEmpList(List empList) {
        this.empList = empList;
    }

   

  
    public String getEview() {
        return Eview;
    }

    public void setEview(String Eview) {
        this.Eview = Eview;
    }

    public String getEmpview() {
        return empview;
    }

    public void setEmpview(String empview) {
        this.empview = empview;
    }

    
  
}
