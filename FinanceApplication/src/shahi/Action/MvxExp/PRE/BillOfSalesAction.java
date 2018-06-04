/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE;

import static com.opensymphony.xwork2.Action.ERROR;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;  
import com.opensymphony.xwork2.ModelDriven; 

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import shahi.Action.MvxExp.PRE.Beans.AgentBean;
import shahi.Action.MvxExp.PRE.Beans.BillOfSalesMastBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.BillOfSalesDao;
import shahi.Action.MvxExp.PRE.dao.BillOfSalesMasterDao;
import shahi.Action.database.connection;

/**
 *
 * @author Ranjeet
 */
public class BillOfSalesAction extends ActionSupport implements ModelDriven  {

    private List warehouselist = new ArrayList();
    private List buyeraddlist = new ArrayList();
    private List unitList=new ArrayList<UnitBean>();
    private List agentList=new ArrayList<AgentBean>();
    private List invList=new ArrayList<AgentBean>();
    private List bosmastlist=new ArrayList();
    
   
    private String S_BUYER_CODE;
    private String unitparam;
    private String PARAA;
    private String PARAB;
    private String TYPE_CODE;
    private String S_BUYER_ADD;
    private String FYBOS="NO";
    private InputStream inputStream; 
    
    private BillOfSalesMastBean BillOfSalesMast = new BillOfSalesMastBean();

    public BillOfSalesAction() {
    }

    public Object getModel() {
        return BillOfSalesMast;
    }

    public String execute() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String LOCATION_CODE = (String) session.get("sessLocationCode");
         Connection conn=null;
         System.out.println("usrid"+usrid);
        if (usrid == null) {
            addActionError("Session is not Available");
            return ERROR;
        }    
         try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            PreparedStatement stat = null;
            ResultSet result = null;
            try{
            stat=conn.prepareStatement("Select * from pa_auth_mast where prog_name='FYBOS' and user_id=?  ");
            stat.setString(1, usrid.trim());
             result = stat.executeQuery();
            if(result.next())
            {
              FYBOS="YES";
            }else{ FYBOS="NO";}
        String str=""; 
        if(BillOfSalesMast.getSEARCH_PLAN_DATE()==null)
        	BillOfSalesMast.setSEARCH_PLAN_DATE(new Date());
        if(BillOfSalesMast.getSEARCH_TOPLAN_DATE()==null)
        	BillOfSalesMast.setSEARCH_TOPLAN_DATE(new Date());
        if(BillOfSalesMast.getSEARCH_WAREHOUSE()!=null && BillOfSalesMast.getSEARCH_WAREHOUSE().length()>0)
        {
        str=str +" and bos_loct='"+BillOfSalesMast.getSEARCH_WAREHOUSE()+"'";
        }
        if(BillOfSalesMast.getSERACH_PLAN_NUMB()!=null && BillOfSalesMast.getSERACH_PLAN_NUMB().length()>0)
        {
        str=str +" and a.BOS_NO='"+BillOfSalesMast.getSERACH_PLAN_NUMB()+"'";
        }
        
        if(BillOfSalesMast.getSEARCH_INVOICE()!=null && BillOfSalesMast.getSEARCH_INVOICE().length()>0)
        {
        str=str +" and EXCS_INV_NO='"+BillOfSalesMast.getSEARCH_INVOICE()+"'";
        }
        if((BillOfSalesMast.getSSEARCH_PLAN_DATE()!=null && BillOfSalesMast.getSSEARCH_PLAN_DATE().length()>0) && (BillOfSalesMast.getSSEARCH_TOPLAN_DATE()!=null && BillOfSalesMast.getSSEARCH_TOPLAN_DATE().length()>0)){
        	SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");
        	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy");
        	String arr[] = BillOfSalesMast.getSSEARCH_PLAN_DATE().split("/");
        	if(arr[0].length()==2){
        		BillOfSalesMast.setSEARCH_PLAN_DATE(format1.parse(BillOfSalesMast.getSSEARCH_PLAN_DATE()));
        	}
        	else{
        		BillOfSalesMast.setSEARCH_PLAN_DATE(format.parse(BillOfSalesMast.getSSEARCH_PLAN_DATE()));
        	}
        	
        	String arr1[] = BillOfSalesMast.getSSEARCH_TOPLAN_DATE().split("/");
        	if(arr1[0].length()==2){
        		BillOfSalesMast.setSEARCH_TOPLAN_DATE(format1.parse(BillOfSalesMast.getSSEARCH_TOPLAN_DATE()));
        	}
        	else{
        		BillOfSalesMast.setSEARCH_TOPLAN_DATE(format.parse(BillOfSalesMast.getSSEARCH_TOPLAN_DATE()));
        	}
        } 
        if(BillOfSalesMast.getSERACH_PLAN_NUMB()!=null && BillOfSalesMast.getSERACH_PLAN_NUMB().length()==0 && BillOfSalesMast.getSEARCH_INVOICE()!=null && BillOfSalesMast.getSEARCH_INVOICE().length()==0 && BillOfSalesMast.getSEARCH_PLAN_DATE()!=null && BillOfSalesMast.getSEARCH_TOPLAN_DATE()!=null)
        {
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String fdate=simpleDateFormat.format(BillOfSalesMast.getSEARCH_PLAN_DATE());
        	String tdate=simpleDateFormat.format(BillOfSalesMast.getSEARCH_TOPLAN_DATE());
        	str=str +" and BOS_DATE BETWEEN to_date('"+fdate +"','yyyy-mm-dd') AND to_date('"+tdate +"','yyyy-mm-dd')";
        }
       
        if(str!=null && str.trim().length()>0 && BillOfSalesMast.getSEARCH_SUBMIT()!=null && BillOfSalesMast.getSEARCH_SUBMIT().equals("YES")){
            
        bosmastlist=new BillOfSalesDao().getquery(usrid, str) ;
       
        }
         warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
         } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            finally{
            if (conn!=null){conn.close();}}
        return SUCCESS;
    }
  
    public String save() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
        int saveflag = 0;
        saveflag=new BillOfSalesDao().save(BillOfSalesMast, usrid,LOCATION_CODE);
        if (saveflag > 0) {
        	
        	if(BillOfSalesMast.getBOSNO()!=null)
        	{
        			addActionMessage("Record Saved !!"+BillOfSalesMast.getBOS_NO());
        			setNullForeNewEntry();
        	}
        	else
        	{
        		 addActionMessage("Record Updated !!");
        	}
        } else { 
            addActionMessage("Record Not Save !!");
        }
         
        if(BillOfSalesMast.getBOSNO()!=null)
        	return newplan();
        else
        	return edit();
        	
    }
        public String update() throws Exception
    {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        } 
        int saveflag = 0;
        saveflag=new BillOfSalesDao().update(BillOfSalesMast, usrid,LOCATION_CODE);
      
        if (saveflag > 0) {
        	    System.out.println("saveflag Bos "+BillOfSalesMast.getBOSNO());   
        	 
        		 addActionMessage("Record Updated !!");
        	 
        } else { 
                   addActionMessage("Record Not Updated !!");
        }
        
        if(BillOfSalesMast.getBOSNO()!=null)
        	return newplan();
        else
        	return fy();
    }
    public String clearplan() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
         
        setNullForeNewEntry();
         
        Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Format formattertm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
         
        BillOfSalesMast.setBOS_DATE (formatter.format(new Date()));
         
        warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
        
        BillOfSalesMast.setINTERUNIT(BillOfSalesMast.getINTERUNIT());
        
        switch (BillOfSalesMast.getINTERUNIT().charAt(0)) {
        case 'N':
        	BillOfSalesMast.setINTERUNIT_DIS("INTER STATE");
              break;
        case 'L': 
        	BillOfSalesMast.setINTERUNIT_DIS("LOCAL");
              break;
        case 'Y':        
        	BillOfSalesMast.setINTERUNIT_DIS("INTER UNIT");
              break;
        case 'C':        
        	BillOfSalesMast.setINTERUNIT_DIS("CONTAINER");
              break;
      }
       
        return "newpage";
    }
    
    
    
    public String newplan() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
        Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Format formattertm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
         
        BillOfSalesMast.setBOS_DATE (formatter.format(new Date()));
     //   BillOfSalesMast.setVCH_ARV_DATE (formattertm.format(new Date()));
        warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
        
        BillOfSalesMast.setINTERUNIT(BillOfSalesMast.getINTERUNIT());
       
        switch (BillOfSalesMast.getINTERUNIT().charAt(0)) {
        case 'N':
        	BillOfSalesMast.setINTERUNIT_DIS("INTER STATE");
              break;
        case 'L': 
        	BillOfSalesMast.setINTERUNIT_DIS("LOCAL");
              break;
        case 'Y':        
        	BillOfSalesMast.setINTERUNIT_DIS("INTER UNIT");
              break;
        case 'C':        
        	BillOfSalesMast.setINTERUNIT_DIS("CONTAINER");
              break;
      }
        
        return "newpage";
    }
     
    public String edit() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
        Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
      
        warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
        BillOfSalesMast=new BillOfSalesDao().getmast(BillOfSalesMast, usrid,LOCATION_CODE);
        return "newpage";
    }
    public String NewEdit() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
        Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
      
        warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
        BillOfSalesMast=new BillOfSalesDao().getNewBOS(BillOfSalesMast, usrid,LOCATION_CODE);
        return "challanpage";
    }
   
           public String fy() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
        Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
      
        warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
        BillOfSalesMast=new BillOfSalesDao().getmast(BillOfSalesMast, usrid,LOCATION_CODE);
        return "fybos";
    }
      public String getbuyeradd() throws Exception {
         Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
       
        if(usrid==null){
            addActionError("Session is not Available");
           
            return ERROR;
        }
        
        if(S_BUYER_CODE!=null && S_BUYER_CODE.length()>0){
        	buyeraddlist=new BillOfSalesMasterDao().getBuyeraddListbyName(S_BUYER_CODE);
        	}
        	return "byr";
        }
      
      public String getajxbuyeradd()  throws SQLException, ParseException, UnsupportedEncodingException
  		{
  	       /*	Map session = ActionContext.getContext().getSession();
  	    	String usrid = ((String)session.get("sessUserId"));
  	    	if (usrid == null)
  	    	{
  	    		addActionError("Session is not Available");
  	    		return "error";
  	    	}*/
  	    	
  	    	//System.out.println("ssssssssssssss"+BillOfSalesMast.getBUYER_ADDR().toString());
  	    	
  	        String ajxstring = new BillOfSalesMasterDao().getBuyeraddListbyNameAjax(S_BUYER_CODE,S_BUYER_ADD);
  	        //System.out.println(ajxstring);
  	    if ((ajxstring!= null) && (!ajxstring.equals(""))) {
  	      this.inputStream = new ByteArrayInputStream(ajxstring.getBytes("UTF-8"));
  	    } else {
  	      this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
  	    }
  	    return "success";
  	}
      
       public String agentView() throws SQLException {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
        if(unitparam!=null && unitparam.length()>0){
        agentList = new BillOfSalesMasterDao().getAgentList(unitparam);
        }
        return "agentview";
    }
       
       public String getajaxagent()  throws SQLException, ParseException, UnsupportedEncodingException
 		{
 	    	Map session = ActionContext.getContext().getSession();
 	    	String usrid = ((String)session.get("sessUserId"));
 	    	if (usrid == null)
 	    	{
 	    		addActionError("Session is not Available");
 	    		return "error";
 	    	}
 	        String ajxstring = new BillOfSalesMasterDao().getAgentListAjax(unitparam);
 	    //System.out.println(ajxstring);
 	    if ((ajxstring!= null) && (!ajxstring.equals(""))) {
 	      this.inputStream = new ByteArrayInputStream(ajxstring.getBytes("UTF-8"));
 	    } else {
 	      this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
 	    }
 	    return "success";
 	}
       
   public String unitView() throws SQLException{
       
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
            addActionError("Session is not Available");
           
            return ERROR;
        }
        if(unitparam!=null && unitparam.length()>0){
            unitList = new BillOfSalesMasterDao().getUnitListByName(unitparam);
        }
            return "unitview";
    }    
   
   public String getajaxunit()  throws SQLException, ParseException, UnsupportedEncodingException
	{
    	Map session = ActionContext.getContext().getSession();
    	String usrid = ((String)session.get("sessUserId"));
    	if (usrid == null)
    	{
    		addActionError("Session is not Available");
    		return "error";
    	}
        String ajxstring = new BillOfSalesMasterDao().getUnitListByNameAjax(unitparam);
    //System.out.println(ajxstring);
    if ((ajxstring!= null) && (!ajxstring.equals(""))) {
      this.inputStream = new ByteArrayInputStream(ajxstring.getBytes("UTF-8"));
    } else {
      this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
    }
    return "success";
    }

   public String portView() throws SQLException{
       
            //List unitList = new ArrayList<UnitBean>();
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
            addActionError("Session is not Available");
           
            return ERROR;
        }
        if(unitparam!=null && unitparam.length()>0 && TYPE_CODE!=null && TYPE_CODE.length()>0){
            unitList = new BillOfSalesMasterDao().getCsytabByName(unitparam,TYPE_CODE);
        }
            return "port";
    }  
   
   public String getajaxport()  throws SQLException, ParseException, UnsupportedEncodingException
	{
   	Map session = ActionContext.getContext().getSession();
   	String usrid = ((String)session.get("sessUserId"));
   	if (usrid == null)
   	{
   		addActionError("Session is not Available");
   		return "error";
   	}
       String ajxstring = new BillOfSalesMasterDao().getCsytabByNameAjax(unitparam,TYPE_CODE);
   //System.out.println(ajxstring);
   if ((ajxstring!= null) && (!ajxstring.equals(""))) {
     this.inputStream = new ByteArrayInputStream(ajxstring.getBytes("UTF-8"));
   } else {
     this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
   }
   return "success";
   }
   
   public String Shipmode() throws SQLException{
       
            //List unitList = new ArrayList<UnitBean>();
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
            addActionError("Session is not Available");
           
            return ERROR;
        }
        if(unitparam!=null && unitparam.length()>0 && TYPE_CODE!=null && TYPE_CODE.length()>0){
            unitList = new BillOfSalesMasterDao().getCsytabByCode(unitparam,TYPE_CODE);
        }
            return "shipmode";
    }   
   
   public String getajaxshipmode()  throws SQLException, ParseException, UnsupportedEncodingException
	{
  	Map session = ActionContext.getContext().getSession();
  	String usrid = ((String)session.get("sessUserId"));
  	if (usrid == null)
  	{
  		addActionError("Session is not Available");
  		return "error";
  	}
      String ajxstring = new BillOfSalesMasterDao().getCsytabByCodeAjax(unitparam,TYPE_CODE);
  //System.out.println(ajxstring);
  if ((ajxstring!= null) && (!ajxstring.equals(""))) {
    this.inputStream = new ByteArrayInputStream(ajxstring.getBytes("UTF-8"));
  } else {
    this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
  }
  return "success";
  }
  
   
    public String cfsView() throws SQLException{
       
            //List unitList = new ArrayList<UnitBean>();
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
            addActionError("Session is not Available");
           
            return ERROR;
        }
        if(unitparam!=null && unitparam.length()>0 && TYPE_CODE!=null && TYPE_CODE.length()>0){
            unitList =new BillOfSalesMasterDao().getCFS(unitparam,TYPE_CODE);
        }
            return "cfs";
    }
    
    public String getajaxcfs()  throws SQLException, ParseException, UnsupportedEncodingException
	{
  	Map session = ActionContext.getContext().getSession();
  	String usrid = ((String)session.get("sessUserId"));
  	if (usrid == null)
  	{
  		addActionError("Session is not Available");
  		return "error";
  	}
      String ajxstring = new BillOfSalesMasterDao().getCFSAjax(unitparam,TYPE_CODE);
  //System.out.println(ajxstring);
  if ((ajxstring!= null) && (!ajxstring.equals(""))) {
    this.inputStream = new ByteArrayInputStream(ajxstring.getBytes("UTF-8"));
  } else {
    this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
  }
  return "success";
  }
    
   public String viewInv() throws SQLException{
       
        
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
            addActionError("Session is not Available");
           
            return ERROR;
        }
        if(unitparam!=null && unitparam.length()>0){
            invList = new BillOfSalesDao().getInv(unitparam);
            
        }
     return "inv";
    }  
   
   public String getajaxInv()  throws SQLException, ParseException, UnsupportedEncodingException
  	{
    	Map session = ActionContext.getContext().getSession();
    	String usrid = ((String)session.get("sessUserId"));
    	if (usrid == null)
    	{
    		addActionError("Session is not Available");
    		return "error";
    	}
        String ajxstring = new BillOfSalesDao().getInvAjax(unitparam);
    //System.out.println(ajxstring);
    if ((ajxstring!= null) && (!ajxstring.equals(""))) {
      this.inputStream = new ByteArrayInputStream(ajxstring.getBytes("UTF-8"));
    } else {
      this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
    }
    return "success";
    }
    
   public void setNullForeNewEntry()
   {
	   BillOfSalesMast.setBOS_NO(null);
       BillOfSalesMast.setDRIVER_NAME("");
       BillOfSalesMast.setLOCATION_CODE("");
       BillOfSalesMast.setSHIP_MODE("");
       BillOfSalesMast.setDRIVER_MOBILE("");
       BillOfSalesMast.setLR_NO("");
       BillOfSalesMast.setDL_NO("");
       BillOfSalesMast.setLR_DATE("");
       BillOfSalesMast.setVEHICLE_TYPE("");
       BillOfSalesMast.setVEHICLE_TYPE_DESC("");
       BillOfSalesMast.setBUYER("");
       BillOfSalesMast.setCHA("");
       BillOfSalesMast.setPORT("");
       BillOfSalesMast.setBUYER_ADDR("");
       BillOfSalesMast.setBUYER_DESC("");
       BillOfSalesMast.setBUYER_ADDRESS_NAME("");
       BillOfSalesMast.setCHA_DESC("");
       BillOfSalesMast.setCHA_ADDRESS_NAME("");
       BillOfSalesMast.setUNIT_DESC("");
       BillOfSalesMast.setVCH_DEP_DATE("");
       BillOfSalesMast.setSEAL_NO("");
       BillOfSalesMast.setUNIT_TO("");
       BillOfSalesMast.setUNIT_TO_DESC("");
       BillOfSalesMast.setVCH_REP_DATE("");
       BillOfSalesMast.setGR_WT("");
       BillOfSalesMast.setTRANSPORTER("");
       BillOfSalesMast.setDISPATCH_VIA("");
       BillOfSalesMast.setDAMAGE("");
       BillOfSalesMast.setPORT_DESC("");
       BillOfSalesMast.setPLAN_CFT("");
       BillOfSalesMast.setPLAN_SIZE("");
       BillOfSalesMast.setDESTINATION("");
       BillOfSalesMast.setDESTINATION_DESC("");
       BillOfSalesMast.setACTUAL_CFT("");
       BillOfSalesMast.setACTUAL_SIZE("");
       BillOfSalesMast.setREMARKS("");
       BillOfSalesMast.setCFS_CODE("");
       BillOfSalesMast.setCFS_DESC("");
       BillOfSalesMast.setCUTOFF_DATE("");
       BillOfSalesMast.setCANCEL_DATE("");
       BillOfSalesMast.setFY_HALT("");
       BillOfSalesMast.setFY_CANCEL("");
       BillOfSalesMast.setCONTAINER_TYPE("");
       BillOfSalesMast.setCONTAINER_NO("");
       BillOfSalesMast.setGPRS_YN("");
       BillOfSalesMast.setLEASE_YN("");
       BillOfSalesMast.setRETURN_CARGO("");
       BillOfSalesMast.setQNTY_TOTAL("");
       BillOfSalesMast.setFOB_TOTAL("");
       BillOfSalesMast.setINR_CONV_TOTAL("");
   }
    

    public List getAgentList() {
        return agentList;
    }

    public void setAgentList(List agentList) {
        this.agentList = agentList;
    }

    public String getUnitparam() {
        return unitparam;
    }

    public void setUnitparam(String unitparam) {
        this.unitparam = unitparam;
    }

   

    public List getWarehouselist() {
        return warehouselist;
    }

    public void setWarehouselist(List warehouselist) {
        this.warehouselist = warehouselist;
    }

    public String getS_BUYER_CODE() {
        return S_BUYER_CODE;
    }

    public void setS_BUYER_CODE(String S_BUYER_CODE) {
        this.S_BUYER_CODE = S_BUYER_CODE;
    }

    public List getBuyeraddlist() {
        return buyeraddlist;
    }

    public void setBuyeraddlist(List buyeraddlist) {
        this.buyeraddlist = buyeraddlist;
    }

    public List getUnitList() {
        return unitList;
    }

    public void setUnitList(List unitList) {
        this.unitList = unitList;
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

    public String getTYPE_CODE() {
        return TYPE_CODE;
    }

    public void setTYPE_CODE(String TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public List getInvList() {
        return invList;
    }

    public void setInvList(List invList) {
        this.invList = invList;
    }

    public BillOfSalesMastBean getBillOfSalesMast() {
        return BillOfSalesMast;
    }

    public void setBillOfSalesMast(BillOfSalesMastBean BillOfSalesMast) {
        this.BillOfSalesMast = BillOfSalesMast;
    }

    public List getBosmastlist() {
        return bosmastlist;
    }

    public void setBosmastlist(List bosmastlist) {
        this.bosmastlist = bosmastlist;
    }

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getS_BUYER_ADD() {
		return S_BUYER_ADD;
	}

	public void setS_BUYER_ADD(String s_BUYER_ADD) {
		S_BUYER_ADD = s_BUYER_ADD;
	}

    public String getFYBOS() {
        return FYBOS;
    }

    public void setFYBOS(String FYBOS) {
        this.FYBOS = FYBOS;
    }
    
    
    
    
}
