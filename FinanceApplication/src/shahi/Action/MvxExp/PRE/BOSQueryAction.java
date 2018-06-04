/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;

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


public class BOSQueryAction extends ActionSupport implements ModelDriven{

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
    private Double CHALLAN_CTN;
    private String aausrid;
    private InputStream inputStream; 
    
    private BillOfSalesMastBean BOSQuery = new BillOfSalesMastBean();
    public BOSQueryAction() {
    }

    public Object getModel() {
        return BOSQuery;
    }


    @Override
    public String execute() throws Exception {
       
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String LOCATION_CODE = (String) session.get("sessLocationCode");
         System.out.println("sir"+usrid);
         Connection conn=null;
       //  usrid="227350";
           if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
        if (usrid == null){
            addActionError("Session is not Available");
            return ERROR;
        }
         try {
                conn = new connection().getConnection();
                //conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            PreparedStatement stat = null;
            ResultSet result = null;
     try{
            
            
        String str=""; 
        if(BOSQuery.getSEARCH_PLAN_DATE()==null)
        	BOSQuery.setSEARCH_PLAN_DATE(new Date());
        if(BOSQuery.getSEARCH_TOPLAN_DATE()==null)
        	BOSQuery.setSEARCH_TOPLAN_DATE(new Date());
        if(BOSQuery.getSEARCH_WAREHOUSE()!=null && BOSQuery.getSEARCH_WAREHOUSE().length()>0)
        {
        str=str +" and bos_loct='"+BOSQuery.getSEARCH_WAREHOUSE()+"'";
        }
        if(BOSQuery.getSERACH_PLAN_NUMB()!=null && BOSQuery.getSERACH_PLAN_NUMB().length()>0)
        {
        str=str +" and a.BOS_NO='"+BOSQuery.getSERACH_PLAN_NUMB()+"'";
        }
        
        if(BOSQuery.getSEARCH_INVOICE()!=null && BOSQuery.getSEARCH_INVOICE().length()>0)
        {
        str=str +" and EXCS_INV_NO='"+BOSQuery.getSEARCH_INVOICE()+"'";
        }
        if((BOSQuery.getSSEARCH_PLAN_DATE()!=null && BOSQuery.getSSEARCH_PLAN_DATE().length()>0) && (BOSQuery.getSSEARCH_TOPLAN_DATE()!=null && BOSQuery.getSSEARCH_TOPLAN_DATE().length()>0)){
        	SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");
        	SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy");
        	String arr[] = BOSQuery.getSSEARCH_PLAN_DATE().split("/");
        	if(arr[0].length()==2){
        		BOSQuery.setSEARCH_PLAN_DATE(format1.parse(BOSQuery.getSSEARCH_PLAN_DATE()));
        	}
        	else{
        		BOSQuery.setSEARCH_PLAN_DATE(format.parse(BOSQuery.getSSEARCH_PLAN_DATE()));
        	}
        	
        	String arr1[] = BOSQuery.getSSEARCH_TOPLAN_DATE().split("/");
        	if(arr1[0].length()==2){
        		BOSQuery.setSEARCH_TOPLAN_DATE(format1.parse(BOSQuery.getSSEARCH_TOPLAN_DATE()));
        	}
        	else{
        		BOSQuery.setSEARCH_TOPLAN_DATE(format.parse(BOSQuery.getSSEARCH_TOPLAN_DATE()));
        	}
        } 
        if(BOSQuery.getSERACH_PLAN_NUMB()!=null && BOSQuery.getSERACH_PLAN_NUMB().length()==0 && BOSQuery.getSEARCH_INVOICE()!=null && BOSQuery.getSEARCH_INVOICE().length()==0 && BOSQuery.getSEARCH_PLAN_DATE()!=null && BOSQuery.getSEARCH_TOPLAN_DATE()!=null)
        {
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String fdate=simpleDateFormat.format(BOSQuery.getSEARCH_PLAN_DATE());
        	String tdate=simpleDateFormat.format(BOSQuery.getSEARCH_TOPLAN_DATE());
        	str=str +" and BOS_DATE BETWEEN to_date('"+fdate +"','yyyy-mm-dd') AND to_date('"+tdate +"','yyyy-mm-dd')";
        }
       
        if(str!=null && str.trim().length()>0 && BOSQuery.getSEARCH_SUBMIT()!=null && BOSQuery.getSEARCH_SUBMIT().equals("YES")){
            
        bosmastlist=new BillOfSalesDao().getquery(usrid, str) ;
       
        }
         warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
         } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            finally{
            if (conn!=null){conn.close();}}
        return "success";
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
         
        BOSQuery.setBOS_DATE (formatter.format(new Date()));
         
        warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
        
        BOSQuery.setINTERUNIT(BOSQuery.getINTERUNIT());
        
        switch (BOSQuery.getINTERUNIT().charAt(0)) {
        case 'N':
        	BOSQuery.setINTERUNIT_DIS("INTER STATE");
              break;
        case 'L': 
        	BOSQuery.setINTERUNIT_DIS("LOCAL");
              break;
        case 'Y':        
        	BOSQuery.setINTERUNIT_DIS("INTER UNIT");
              break;
        case 'C':        
        	BOSQuery.setINTERUNIT_DIS("CONTAINER");
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
        BOSQuery=new BillOfSalesDao().getmast(BOSQuery, usrid,LOCATION_CODE);
        return "newpage";
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
        BOSQuery=new BillOfSalesDao().getmast(BOSQuery, usrid,LOCATION_CODE);
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
  	    	
  	    	//System.out.println("ssssssssssssss"+BOSQuery.getBUYER_ADDR().toString());
  	    	
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
	   BOSQuery.setBOS_NO(null);
       BOSQuery.setDRIVER_NAME("");
       BOSQuery.setLOCATION_CODE("");
       BOSQuery.setSHIP_MODE("");
       BOSQuery.setDRIVER_MOBILE("");
       BOSQuery.setLR_NO("");
       BOSQuery.setDL_NO("");
       BOSQuery.setLR_DATE("");
       BOSQuery.setVEHICLE_TYPE("");
       BOSQuery.setVEHICLE_TYPE_DESC("");
       BOSQuery.setBUYER("");
       BOSQuery.setCHA("");
       BOSQuery.setPORT("");
       BOSQuery.setBUYER_ADDR("");
       BOSQuery.setBUYER_DESC("");
       BOSQuery.setBUYER_ADDRESS_NAME("");
       BOSQuery.setCHA_DESC("");
       BOSQuery.setCHA_ADDRESS_NAME("");
       BOSQuery.setUNIT_DESC("");
       BOSQuery.setVCH_DEP_DATE("");
       BOSQuery.setSEAL_NO("");
       BOSQuery.setUNIT_TO("");
       BOSQuery.setUNIT_TO_DESC("");
       BOSQuery.setVCH_REP_DATE("");
       BOSQuery.setGR_WT("");
       BOSQuery.setTRANSPORTER("");
       BOSQuery.setDISPATCH_VIA("");
       BOSQuery.setDAMAGE("");
       BOSQuery.setPORT_DESC("");
       BOSQuery.setPLAN_CFT("");
       BOSQuery.setPLAN_SIZE("");
       BOSQuery.setDESTINATION("");
       BOSQuery.setDESTINATION_DESC("");
       BOSQuery.setACTUAL_CFT("");
       BOSQuery.setACTUAL_SIZE("");
       BOSQuery.setREMARKS("");
       BOSQuery.setCFS_CODE("");
       BOSQuery.setCFS_DESC("");
       BOSQuery.setCUTOFF_DATE("");
       BOSQuery.setCANCEL_DATE("");
       BOSQuery.setFY_HALT("");
       BOSQuery.setFY_CANCEL("");
       BOSQuery.setCONTAINER_TYPE("");
       BOSQuery.setCONTAINER_NO("");
       BOSQuery.setGPRS_YN("");
       BOSQuery.setLEASE_YN("");
       BOSQuery.setRETURN_CARGO("");
       BOSQuery.setQNTY_TOTAL("");
       BOSQuery.setFOB_TOTAL("");
       BOSQuery.setINR_CONV_TOTAL("");
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

  

    public BillOfSalesMastBean getBOSQuery() {
        return BOSQuery;
    }

    public void setBOSQuery(BillOfSalesMastBean BOSQuery) {
        this.BOSQuery = BOSQuery;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public Double getCHALLAN_CTN() {
        return CHALLAN_CTN;
    }

    public void setCHALLAN_CTN(Double CHALLAN_CTN) {
        this.CHALLAN_CTN = CHALLAN_CTN;
    }
    
    
    
    
}
