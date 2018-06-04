package shahi.Action.MvxExp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.MvxExp.Beans.PreShipPlanDtBeans;
/**
 *
 * @author Vivek
 */
public class PreShipPlanDtAction  extends ActionSupport {
    private String searchfrom;
    private String searchto;
    private String searchinv;
    private String searchplan;
    private String PLANNUMB;
    private List showList;
    private String aausrid;
     @Override
    public String execute() throws Exception {        
       showList = new ArrayList<PreShipPlanDtBeans>();
        int falg = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
        usrid = "237519";
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
                stat = conn.prepareStatement("select PLAN_NUMB, to_char(PLAN_DATE,'dd/MM/yyyy')PLAN_DATE,CO_NUMB, to_char(CO_DATE,'dd/MM/yyyy')CO_DATE, CO_LINE, ITEM_NUMB, ITEM_DESC, PLAN_QNTY, PCH, WEIGHT, UOM, CONTROL_NUMB, INV_NO, BUYER, EXTRA_QNTY, MODE_OF_SHIP, DEL_NUMB, CONV_FACTR from pr_ship_plan_detail where PLAN_NUMB=?");
                stat.setString(1,PLANNUMB);

                result=stat.executeQuery();
                while(result.next()){
                   showList.add(new PreShipPlanDtBeans(result.getString("PLAN_NUMB"),result.getString("PLAN_DATE"),result.getString("CO_NUMB"),result.getString("CO_DATE"),result.getString("CO_LINE"),result.getString("ITEM_NUMB"),result.getString("ITEM_DESC"),result.getString("PLAN_QNTY"),result.getString("PCH"),result.getString("WEIGHT"),result.getString("UOM"),result.getString("CONTROL_NUMB"),result.getString("INV_NO"),result.getString("BUYER"),result.getString("EXTRA_QNTY"),result.getString("MODE_OF_SHIP"),result.getString("DEL_NUMB"),result.getString("CONV_FACTR")));
               }
            }
            catch (Exception e) {

                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : PreShipPlanDtAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : PreShipPlanDtAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
                    if (result != null) {
                        result.close();
                    }
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    conn = null;
                    //searchplan="";

                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : PreShipPlanDtAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }
        return SUCCESS;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getSearchplan() {
        return searchplan;
    }

    public void setSearchplan(String searchplan) {
        this.searchplan = searchplan;
    }

    public List getShowList() {
        return showList;
    }

    public void setShowList(List showList) {
        this.showList = showList;
    }

    public String getPLANNUMB() {
        return PLANNUMB;
    }

    public void setPLANNUMB(String PLANNUMB) {
        this.PLANNUMB = PLANNUMB;
    }

    public String getSearchfrom() {
        return searchfrom;
    }

    public void setSearchfrom(String searchfrom) {
        this.searchfrom = searchfrom;
    }

    public String getSearchinv() {
        return searchinv;
    }

    public void setSearchinv(String searchinv) {
        this.searchinv = searchinv;
    }

    public String getSearchto() {
        return searchto;
    }

    public void setSearchto(String searchto) {
        this.searchto = searchto;
    }
     
}
