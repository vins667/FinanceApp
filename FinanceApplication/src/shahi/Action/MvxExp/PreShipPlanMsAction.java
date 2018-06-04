package shahi.Action.MvxExp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import shahi.Action.database.connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import shahi.Action.MvxExp.Beans.PreShipPlanMsBeans;
/**
 *
 * @author Vivek
 */
public class PreShipPlanMsAction extends ActionSupport {
    private String searchfrom;
    private String searchto;
    private String searchplan;
    private String searchinv;
    private List showList;
    private String aausrid;
    public PreShipPlanMsAction() {

    }
    @Override
    public String execute() throws Exception {
        SimpleDateFormat ts= new SimpleDateFormat("MM/dd/yyyy");
       showList = new ArrayList<PreShipPlanMsBeans>();
        int falg = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
        usrid = "227350";
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
            String extendedQuery="";
            Date frmDate = new java.sql.Date(ts.parse(searchfrom).getTime());
            Date toDate = new java.sql.Date(ts.parse(searchto).getTime());
            try {                
                if((searchplan!=null && searchplan.length()>0) || (searchinv!=null && searchinv.length()>0)){
                }
                else{
                    extendedQuery=" and PLAN_DATE between ? and ?";
                }
                //System.out.println("extendedQuery "+ extendedQuery);
                if(searchplan!=null && searchplan.length()>0){
                }
                else{
                     searchplan="%";
                }
                //System.out.println("searchplan " + searchplan);
                if(searchinv!=null && searchinv.length()>0){
                }
                else{
                    searchinv="%";
                }
                //System.out.println("searchinv "+ searchinv);
                
               stat = conn.prepareStatement("select PLAN_NUMB, to_char(PLAN_DATE,'dd/MM/yyyy')PLAN_DATE, INV_QNTY, EXCS_INV_NO from pr_ship_plan_master where PLAN_NUMB like ? and EXCS_INV_NO like ? "+extendedQuery);
               stat.setString(1,searchplan);
               stat.setString(2,searchinv);
               if((searchplan!=null && searchplan.equals("%")) && (searchinv!=null && searchinv.equals("%"))){
                    stat.setDate(3,frmDate);
                    stat.setDate(4,toDate);
                }
               result=stat.executeQuery();
               while(result.next()){
                   showList.add(new PreShipPlanMsBeans(result.getString("PLAN_NUMB"), result.getString("PLAN_DATE"), result.getString("INV_QNTY"), result.getString("EXCS_INV_NO")));
               }
            }
            catch (Exception e) {

                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : PreShipPlanMaster.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : PreShipPlanMaster.java" + e);

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
                    if(searchplan.equals("%")){
                    searchplan="";}
                    if(searchinv.equals("%")){
                    searchinv="";}

                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : PreShipPlanMaster.java Exception in finally block");
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

    public String getSearchplan() {
        return searchplan;
    }

    public void setSearchplan(String searchplan) {
        this.searchplan = searchplan;
    }

    public String getSearchto() {
        return searchto;
    }

    public void setSearchto(String searchto) {
        this.searchto = searchto;
    }

    public List getShowList() {
        return showList;
    }

    public void setShowList(List showList) {
        this.showList = showList;
    }
}