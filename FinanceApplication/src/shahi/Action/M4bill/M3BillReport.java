/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill;

/**
 *
 * @author RANJEET
 */

 
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shahi.Action.database.connection;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.catalina.core.ApplicationContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import shahi.Action.M4bill.Beans.M3BILLBean;
import shahi.Action.Mis.ShahiInformationList;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionShahiHris;

 
public class M3BillReport extends ActionSupport implements ServletRequestAware,ServletResponseAware{
 
        private HttpServletRequest servletRequest;
        private HttpServletResponse response;
	private List<M3BILLBean> m3billdeptlist=new ArrayList<M3BILLBean>();
	private List<M3BILLBean> m3billtypelist=new ArrayList<M3BILLBean>();
        private List<M3BILLBean> m3billtypelistgrp=new ArrayList<M3BILLBean>();
        
	private List<M3BILLBean> m3billsubtypelist=new ArrayList<M3BILLBean>();
        private List<M3BILLBean> m3billsubtypelistgrp=new ArrayList<M3BILLBean>();
        
	private List<M3BILLBean> m3pchtypelist=new ArrayList<M3BILLBean>();
	private List<M3BILLBean> m3costcenterlist=new ArrayList<M3BILLBean>();
        private List<M3BILLBean> m3costcenterlistgrp=new ArrayList<M3BILLBean>();
        
	private List<M3BILLBean> m3productlist=new ArrayList<M3BILLBean>();
        private List<M3BILLBean> m3productlistgrp=new ArrayList<M3BILLBean>();
        private List warehouselist=new ArrayList();
	private String monthtype;
	
	private List rightBill;
	
	private List rightPCH;
	
	private List rightDepartment;
		
	private List rightcost;
		
	private List rightproduct;
	
        private List rightsubbill;
        
        private String BILL_DATE1;
        
        private String BILL_DATE2;
        
        private List warehouse;
        private String REPORTTYPE;

	public String execute() throws Exception{
		
            warehouselist=new ShahiInformationList().warehouseListBYLoct("");
            
	        Connection conn =new ConnectionSeplWeb().getConnection();
                Connection conn1=null;
	    
		m3billdeptlist = new ArrayList<M3BILLBean>();
		PreparedStatement stat=null;
		ResultSet resultSet = null;				
		try{			
			conn1 = new connection().getConnection();
		}
		catch(Exception e){
			addActionError("M3BillRepoert execute()"+e);
			System.out.println("M3BillRepoert execute()"+e);
			return ERROR;
		}
		try
		{
			stat = conn.prepareStatement("SELECT SL_NO,DEPT_NAME FROM M4_Dept_Master order by DEPT_NAME");
			
			resultSet = stat.executeQuery();
			while(resultSet.next()){
				
				m3billdeptlist.add(new M3BILLBean(resultSet.getString("SL_NO"),resultSet.getString("DEPT_NAME")));
				
			 }
			
                        stat = conn1.prepareStatement("select  PCH from bill_pch_master where  BILL_FLAG='A' order by 1");
			resultSet = stat.executeQuery();
			while(resultSet.next()){
				
				m3pchtypelist.add(new M3BILLBean(resultSet.getString("PCH"),resultSet.getString("PCH")));
				
			
		}
		}
		catch(SQLException se){
			addActionError("M3BillReport execute() "+se);
			System.out.println("M3BillReport execute() "+se);
			return ERROR;
		}
		
		finally{
			if(conn!=null){
				conn.close();
			}
			conn = null;
			
			if(stat!=null){
				stat.close();
			}
			stat=null;
			
			if(resultSet!=null){
				resultSet.close();
			}
			resultSet = null;
		}
		
		return SUCCESS;
		}


	public String getbilltype() throws Exception{
		Connection conn =new ConnectionSeplWeb().getConnection();
		m3billtypelist = new ArrayList<M3BILLBean>();
		
		PreparedStatement stat=null;
		ResultSet resultSet = null;	
                ResultSet resultSet1 = null;	
		try{			
			conn = new ConnectionSeplWeb().getConnection();
		}
		catch(Exception e){
			addActionError("M3BillRepoert execute()"+e);
			System.out.println("M3BillRepoert execute()"+e);
			return ERROR;
		}
		try
		{
		
			if(rightDepartment!=null && rightDepartment.size()>0){
			for(int i=0; i<rightDepartment.size();i++)
			{
                        stat = conn.prepareStatement("select SL_NO,DEPT_DESC from m4_bill_dept_master where DEPT_CODE=? order by DEPT_DESC");
			stat.setString(1,rightDepartment.get(i).toString());
			resultSet1 = stat.executeQuery();
                        if(resultSet1.next())
                        {  
                            m3billtypelistgrp.add(new M3BILLBean(resultSet1.getString(1),resultSet1.getString(2)));
                            
                            stat = conn.prepareStatement("select SL_NO,TYPE_CODE,TYPE_DESC,DEPT_SL_NO from m4_bill_type_master where DEPT_SL_NO"
                                    + " in (select sl_no from m4_bill_dept_master where DEPT_CODE=? and FLAG='Y') and FLAG='Y' order by TYPE_DESC");
                            stat.setString(1,rightDepartment.get(i).toString());
                            resultSet = stat.executeQuery();
                            while(resultSet.next()){

                                    m3billtypelist.add(new M3BILLBean(resultSet.getString("SL_NO"),resultSet.getString("TYPE_DESC"),resultSet.getString("DEPT_SL_NO")));

                             }
                        }
			}
		}
	}
		catch(SQLException se){
			addActionError("M3BillReport execute() "+se);
			System.out.println("M3BillReport execute() "+se);
			return ERROR;
		}
		
		finally{
			if(conn!=null){
				conn.close();
			}
			conn = null;
			
			if(stat!=null){
				stat.close();
			}
			stat=null;
			
			if(resultSet!=null){
				resultSet.close();
			}
			resultSet = null;
		}
		return "billtype";
		}
	
	
	
	public String getbillsubtype() throws Exception{
		Connection conn =null;
		m3billsubtypelist = new ArrayList<M3BILLBean>();
		
		PreparedStatement stat=null;
		ResultSet resultSet = null;
                ResultSet resultSet1 = null;	
		try{			
			conn = new ConnectionSeplWeb().getConnection();
		}
		catch(Exception e){
			addActionError("M3BillRepoert execute()"+e);
			System.out.println("M3BillRepoert execute()"+e);
			return ERROR;
		}
		try
		{
		
			if(rightBill!=null && rightBill.size()>0){
			for(int i=0; i<rightBill.size();i++)
			{
                         stat = conn.prepareStatement("select SL_NO,TYPE_CODE,TYPE_DESC from m4_bill_type_master where SL_NO=? "
                                 + "and FLAG='Y'  order by TYPE_DESC");
                            stat.setString(1,rightBill.get(i).toString());
                            resultSet1 = stat.executeQuery();
                            if(resultSet1.next()){   
                                
                                        m3billsubtypelistgrp.add(new M3BILLBean(resultSet1.getString("sl_no"),resultSet1.getString("TYPE_DESC")));

                                stat = conn.prepareStatement("select sl_no,sub_type_code,sub_type_desc from m4_bill_sub_type_master "
                                        + "where type_sl_no=? and  FLAG='Y' order by sub_type_desc");
                                stat.setString(1,rightBill.get(i).toString());
                                resultSet = stat.executeQuery();
                                while(resultSet.next()){

                                        m3billsubtypelist.add(new M3BILLBean(resultSet.getString("sl_no"),resultSet.getString("sub_type_desc")+"-"+resultSet.getString("sub_type_code"),rightBill.get(i).toString()));

                                 }
                            }
			}
			
			}
			
	}
		catch(SQLException se){
			addActionError("M3BillReport execute() "+se);
			System.out.println("M3BillReport execute() "+se);
			return ERROR;
		}
		
		finally{
			if(conn!=null){
				conn.close();
			}
			conn = null;
			
			if(stat!=null){
				stat.close();
			}
			stat=null;
			
			if(resultSet!=null){
				resultSet.close();
			}
			resultSet = null;
		}
		return "subbilltype";
		}
	
	
	public String getpchtype() throws Exception{
		Connection conn =new ConnectionSeplWeb().getConnection();
		m3pchtypelist = new ArrayList<M3BILLBean>();
		
		PreparedStatement stat=null;
		ResultSet resultSet = null;				
		try{			
			conn = new ConnectionSeplWeb().getConnection();
		}
		catch(Exception e){
			addActionError("M3BillRepoert execute()"+e);
			System.out.println("M3BillRepoert execute()"+e);
			return ERROR;
		}
		try
		{
		
			
			stat = conn.prepareStatement("select  PCH from bill_pch_master where  BILL_FLAG='A' order by 1");
			
			resultSet = stat.executeQuery();
			while(resultSet.next()){
				
				m3pchtypelist.add(new M3BILLBean(resultSet.getString("PCH"),resultSet.getString("PCH")));
				
			
		}
	}
		catch(SQLException se){
			addActionError("M3BillReport execute() "+se);
			System.out.println("M3BillReport execute() "+se);
			return ERROR;
		}
		
		finally{
			if(conn!=null){
				conn.close();
			}
			conn = null;
			
			if(stat!=null){
				stat.close();
			}
			stat=null;
			
			if(resultSet!=null){
				resultSet.close();
			}
			resultSet = null;
		}
		return SUCCESS;
		}
	
	public String getcostcentertype() throws Exception{
		Connection conn =null;
                Connection connbi =null;
		m3costcenterlist = new ArrayList<M3BILLBean>();
		
		PreparedStatement stat=null;
		ResultSet resultSet = null;
                ResultSet resultSet1 = null;
                ResultSet resultSet2 = null;
		try{			
			conn = new ConnectionSeplWeb().getConnection();
                        connbi = new ConnectionShahiHris().getConnection();
		}
		catch(Exception e){
			addActionError("M3BillRepoert execute()"+e);
			System.out.println("M3BillRepoert execute()"+e);
			return ERROR;
		}
		try
		{
		
			if(rightBill!=null && rightBill.size()>0){
			for(int i=0; i<rightBill.size();i++)
			{
                             stat = conn.prepareStatement("select SL_NO,TYPE_CODE,TYPE_DESC from m4_bill_type_master "
                                     + "where SL_NO=? and   FLAG='Y'  order by TYPE_DESC");
                            stat.setString(1,rightBill.get(i).toString());
                            resultSet1 = stat.executeQuery();
                            if(resultSet1.next()){   
                                
                                        m3costcenterlistgrp.add(new M3BILLBean(resultSet1.getString("sl_no"),resultSet1.getString("TYPE_DESC")));
                            
                                    stat = conn.prepareStatement("select SL_NO,CC_CODE from M4_CC_MASTER where  TYPE_SL_NO=? and"
                                            + "  FLAG='Y' order by CC_CODE");
                                    stat.setString(1,rightBill.get(i).toString());

                                    resultSet = stat.executeQuery();
                                    while(resultSet.next()){
                                        String CC_DESC="";
                                                stat=conn.prepareStatement("select EAAITM,EATX40 from fchacc_M4"
                                                        + "  where EACONO=111 and  EAAITP=3 and eaat12=0 and EAAITM=? ");
                                                stat.setString(1, resultSet.getString("CC_CODE"));
                                                resultSet2=stat.executeQuery();
                                                if(resultSet2.next())
                                                {
                                                    CC_DESC=resultSet2.getString(1)+"-"+resultSet2.getString(2);

                                                }
                                                if(resultSet2!=null)
                                                {
                                                resultSet2.close();
                                                }
                                                if(stat!=null)
                                                {
                                                stat.close();
                                                }
                                                
                                            m3costcenterlist.add(new M3BILLBean(resultSet.getString("SL_NO"),CC_DESC,rightBill.get(i).toString()));

                                     }
                            }
			}
		}
	}
		catch(SQLException se){
			addActionError("M3BillReport execute() "+se);
			System.out.println("M3BillReport execute() "+se);
			return ERROR;
		}
		
		finally{
			if(conn!=null){
				conn.close();
			}
                        if(connbi!=null){
				connbi.close();
			}
			conn = null;
			
			if(stat!=null){
				stat.close();
			}
			stat=null;
			
			if(resultSet!=null){
				resultSet.close();
			}
			resultSet = null;
		}
		return "billcc";
		}
	
	
	public String getproducttype() throws Exception{
		Connection conn =new ConnectionSeplWeb().getConnection();
		 m3productlist = new ArrayList<M3BILLBean>();
		
		PreparedStatement stat=null;
		ResultSet resultSet = null;	
                ResultSet resultSet1 = null;
		try{			
			conn = new ConnectionSeplWeb().getConnection();
		}
		catch(Exception e){
			addActionError("M3BillRepoert execute()"+e);
			System.out.println("M3BillRepoert execute()"+e);
			return ERROR;
		}
		try
		{
		
			if( rightsubbill!=null && rightsubbill.size()>0){
			for(int i=0; i< rightsubbill.size();i++)
			{
                                stat = conn.prepareStatement("select sl_no,sub_type_code,sub_type_desc from m4_bill_sub_type_master "
                                        + "where sl_no=? and FLAG='Y'");
                                stat.setString(1,rightsubbill.get(i).toString());
                                resultSet1 = stat.executeQuery();
                                if(resultSet1.next()){

                                        m3productlistgrp.add(new M3BILLBean(resultSet1.getString("sl_no"),resultSet1.getString("sub_type_desc")+"-"+resultSet1.getString("sub_type_code")));

                                 
                                stat = conn.prepareStatement("select SL_NO ,PRODUCT_CODE,PRODUCT_DESC from M4_BILL_PRODUCT_MASTER"
                                        + " where SUB_TYPE_SL_NO=?  and FLAG='Y' order by PRODUCT_DESC");
                                stat.setString(1, rightsubbill.get(i).toString());
                                resultSet = stat.executeQuery();
                                while(resultSet.next()){

                                         m3productlist.add(new M3BILLBean(resultSet.getString("SL_NO"),resultSet.getString("PRODUCT_DESC")+"-"+resultSet.getString("PRODUCT_CODE"),rightsubbill.get(i).toString()));

                                 }
                                }
			}
		}
	}
		catch(SQLException se){
			addActionError("M3BillReport execute() "+se);
			System.out.println("M3BillReport execute() "+se);
			return ERROR;
		}
		
		finally{
			if(conn!=null){
				conn.close();
			}
			conn = null;
			
			if(stat!=null){
				stat.close();
			}
			stat=null;
			
			if(resultSet!=null){
				resultSet.close();
			}
			resultSet = null;
		}
		return "billpro";
		}
	
	
	public String printpdf() throws Exception {
            
     //  System.out.println(warehouse +" ::: "+BILL_DATE1+"  ::: "+BILL_DATE2);
            
            
        Connection con = null;
        int a=0;
        try
        {
           con = new ConnectionSeplWeb().getConnection();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result=null; 
      
        
         try
        {
          Map session = ActionContext.getContext().getSession();
          String usrid = (String) session.get("sessUserId");
          String qry="";
          String deptin=null;
          String rightBillsql=null;
          String rightsubbillsql=null;
          String rightpchsql=null;
          String rightcostsql=null;
          String rightproductsql=null;
          String wareshouesql=null;
          String datesql=" ";
          String wareshouepa=null;
          
          if(warehouse!=null && warehouse.size()>0)
          {
              for(int i=0; i<warehouse.size(); i++)
           {
             if(wareshouesql==null)
             {
             wareshouesql="'"+warehouse.get(i).toString()+"'";
             }else{
              wareshouesql=wareshouesql+",'"+warehouse.get(i).toString()+"'";
             }
             
             if(wareshouepa==null)
             {
             wareshouepa=warehouse.get(i).toString();
             }else{
              wareshouepa=wareshouepa+","+warehouse.get(i).toString();
             }
          }
          }
          
          if(wareshouepa==null)
          {
          wareshouepa="All";
          }
          
          if(wareshouesql!=null && wareshouesql.length()>0)
          {
             
          wareshouesql=" and BILL_WHLO in("+wareshouesql+")";  
          }else{
          wareshouesql=" ";
          }
          
        
          
          if(rightDepartment!=null && rightDepartment.size()>0)
          {
           for(int i=0; i<rightDepartment.size(); i++)
           {
             if(deptin==null)
             {
             deptin=rightDepartment.get(i).toString();
             }else{
              deptin=deptin+","+rightDepartment.get(i).toString();
             }
           }
          }
           if(rightBill!=null && rightBill.size()>0)
          {
           for(int i=0; i<rightBill.size(); i++)
           {
             if(rightBillsql==null)
             {
             rightBillsql=" and b.type_sl_no in ("+rightBill.get(i).toString();
             }else{
              rightBillsql=rightBillsql+","+rightBill.get(i).toString();
             }
           }
          }
          if(rightBillsql==null)
          {
          rightBillsql=" ";
          }else{
              
              rightBillsql=rightBillsql+")";
          }
          
          
            if(rightsubbill!=null && rightsubbill.size()>0)
          {
           for(int i=0; i<rightsubbill.size(); i++)
           {
             if(rightsubbillsql==null)
             {
             rightsubbillsql=" and b.SUB_TYPE_SL_NO in ("+rightsubbill.get(i).toString();
             }else{
              rightsubbillsql=rightsubbillsql+","+rightsubbill.get(i).toString();
             }
           }
          }
          if(rightsubbillsql==null)
          {
          rightsubbillsql=" ";
          }else{
              
              rightsubbillsql=rightsubbillsql+")";
          }
          
          
          
            if(rightPCH!=null && rightPCH.size()>0)
          {
           for(int i=0; i<rightPCH.size(); i++)
           {
             if(rightpchsql==null)
             {
             rightpchsql=" and b.pch in ('"+rightPCH.get(i).toString()+"'";
             }else{
              rightpchsql=rightpchsql+",'"+rightPCH.get(i).toString()+"'";
             }
           }
          }
          if(rightpchsql==null)
          {
          rightpchsql=" ";
          }else{
              
              rightpchsql=rightpchsql+")";
          }
          
           if(rightcost!=null && rightcost.size()>0)
          {
           for(int i=0; i<rightcost.size(); i++)
           {
             if(rightcostsql==null)
             {
             rightcostsql=" and b.CC_CODE in ('"+rightcost.get(i).toString()+"'";
             }else{
              rightcostsql=rightcostsql+",'"+rightcost.get(i).toString()+"'";
             }
           }
          }
          if(rightcostsql==null)
          {
          rightcostsql=" ";
          }else{
              
              rightcostsql=rightcostsql+")";
          }
          
           if(rightproduct!=null && rightproduct.size()>0)
          {
           for(int i=0; i<rightproduct.size(); i++)
           {
             if(rightproductsql==null)
             {
             rightproductsql=" and b.PRODUCT_SL_NO in ('"+rightproduct.get(i).toString()+"'";
             }else{
              rightproductsql=rightproductsql+",'"+rightproduct.get(i).toString()+"'";
             }
           }
          }
          if(rightproductsql==null)
          {
          rightproductsql=" ";
          }else{
              
              rightproductsql=rightproductsql+")";
          }
         
         
          if(BILL_DATE1!=null && BILL_DATE1.length()>0 && BILL_DATE2!=null && BILL_DATE2.length()>0)
          {
              if(monthtype!=null && monthtype.equals("M")){
               datesql ="and (a.sl_no) in (select bill_sl_no from M4_BILL_month_DETAIL where to_date(bill_month,'dd/yyyy')"
                       + "  between to_date('"+BILL_DATE1+"','dd/yyyy') and to_date('"+BILL_DATE2+"','dd/yyyy')) ";
              }else{
               // datesql =" and to_char(a.bill_date,'mm/yyyy')  between '"+BILL_DATE1+"' and '"+BILL_DATE2+"'";
             datesql =" and to_date(to_char(a.bill_date,'mm/yyyy'),'mm/yyyy')  between to_date('"+BILL_DATE1+"','mm/yyyy')"
                     + " and to_date('"+BILL_DATE2+"','mm/yyyy')";
            
              }
          }
          if(rightBill==null || (rightBill!=null && rightBill.size()==0))
          {
            qry+=" select  a.DEPT_SL_NO,c.DEPT_DESC,0 type_sl_no,'All' TYPE_DESC, " +
            " 0 SUB_TYPE_SL_NO,'All' SUB_TYPE_DESC,";
            if(rightpchsql.trim().length()==0)
           {
            qry+=" 'All' PCH, ";
           }else{
            qry+=" b.PCH, ";
          }
           qry+="'All' CC_CODE_DESC, " +
            " 0 PRODUCT_SL_NO,'All' PRODUCT_DESC, " +
            " sum(round(b.product_amount/(months_between(to_date(bill_date2,'mm/yyyy'),to_date(bill_date1,'mm/yyyy'))+1),2) ) product_amount " +
            " from m4_bill_master a,m4_bill_detail b,m4_bill_dept_master c,M4_BILL_type_MASTER d,M4_BILL_SUB_TYPE_MASTER e, " +
            " m4_cc_master f,fchacc_M4 g,M4_BILL_PRODUCT_MASTER h " +
            " where a.sl_no=b.bill_sl_no and a.DEPT_SL_NO=c.SL_NO and b.type_sl_no=d.SL_NO AND b.SUB_TYPE_SL_NO=e.SL_NO " +
            " and b.CC_CODE=f.sl_no and f.cc_code=g.EAAITM and g.EAAITP=3 and g.eaat12=0 and b.PRODUCT_SL_NO=h.SL_NO " +
            " and a.DEPT_SL_NO in(select  SL_NO from m3_bill_dept_master where DEPT_CODE in("+deptin+")) "+wareshouesql+" "+rightpchsql+" "+datesql+"  group by a.DEPT_SL_NO,c.DEPT_DESC ";
              if(rightpchsql.trim().length()==0)
                {
                 qry+=" ";
                }else{
                 qry+=" ,b.PCH";
               }   
            qry+=" order by c.DEPT_DESC ";
          }else if (rightBill!=null && rightBill.size()>0 && (rightsubbill==null ||(rightsubbill!=null && rightsubbill.size()==0))
                  )
                  {
                   qry+=" select  a.DEPT_SL_NO,c.DEPT_DESC,b.type_sl_no,d.TYPE_DESC ||'-'|| d.TYPE_CODE TYPE_DESC, " +
            " 0 SUB_TYPE_SL_NO,'All' SUB_TYPE_DESC, ";
               if(rightpchsql.trim().length()==0)
                    {
                     qry+=" 'All' PCH, ";
                    }else{
                     qry+=" b.PCH, ";
                   }
               
               if(rightcostsql.trim().length()==0)
                    {
                     qry+=" 'All'  CC_CODE_DESC, ";
                    }else{
                     qry+=" f.CC_CODE ||'-'|| g.EATX40 ||'-'|| b.CC_CODE CC_CODE_DESC , ";
                   }
               
             qry+=" 0 PRODUCT_SL_NO,'All' PRODUCT_DESC, " +
            " sum(round(b.product_amount/(months_between(to_date(bill_date2,'mm/yyyy'),to_date(bill_date1,'mm/yyyy'))+1),2)) product_amount " +
            " from m4_bill_master a,m4_bill_detail b,m4_bill_dept_master c,M4_BILL_type_MASTER d,M4_BILL_SUB_TYPE_MASTER e, " +
            " m4_cc_master f,fchacc_m4 g,M4_BILL_PRODUCT_MASTER h " +
            " where a.sl_no=b.bill_sl_no and a.DEPT_SL_NO=c.SL_NO and b.type_sl_no=d.SL_NO AND b.SUB_TYPE_SL_NO=e.SL_NO " +
            " and b.CC_CODE=f.sl_no and f.cc_code=g.EAAITM and g.EAAITP=3 and g.eaat12=0 and b.PRODUCT_SL_NO=h.SL_NO " +
            " and a.DEPT_SL_NO in(select  SL_NO from m4_bill_dept_master where DEPT_CODE in("+deptin+")) "+wareshouesql+" "+rightBillsql+
            " "+rightpchsql+" "+rightcostsql+" "+datesql+" group by a.DEPT_SL_NO,c.DEPT_DESC ,b.type_sl_no,d.TYPE_DESC ||'-'|| d.TYPE_CODE " ;
              if(rightpchsql.trim().length()==0)
                {
                 qry+=" ";
                }else{
                 qry+=" ,b.PCH";
               } 
              
              if(rightcostsql.trim().length()==0)
                    {
                     qry+="  ";
                    }else{
                     qry+=", f.CC_CODE ||'-'|| g.EATX40 ||'-'|| b.CC_CODE  ";
                   } 
            qry+=" order by c.DEPT_DESC ";
            }else if (rightBill!=null && rightBill.size()>0 && rightsubbill!=null && rightsubbill.size()>0)
              {
                      qry += " select  a.DEPT_SL_NO,c.DEPT_DESC,b.type_sl_no,d.TYPE_DESC ||'-'|| d.TYPE_CODE TYPE_DESC, "
                              + " b.SUB_TYPE_SL_NO,e.SUB_TYPE_DESC  ||'-'|| e.SUB_TYPE_CODE SUB_TYPE_DESC, ";
                      if (rightpchsql.trim().length() == 0) {
                          qry += " 'All' PCH, ";
                      } else {
                          qry += " b.PCH, ";
                      }

                      if (rightcostsql.trim().length() == 0) {
                          qry += " 'All'  CC_CODE_DESC, ";
                      } else {
                          qry += " f.CC_CODE ||'-'|| g.EATX40 ||'-'|| b.CC_CODE CC_CODE_DESC , ";
                      }
                      
                       if (rightproductsql.trim().length() == 0) {
                          qry += " 0 PRODUCT_SL_NO,'All' PRODUCT_DESC, ";
                      } else {
                          qry += " b.PRODUCT_SL_NO,h.PRODUCT_DESC ||'-'|| h.PRODUCT_CODE PRODUCT_DESC , ";
                      }
                      
                      
                      
                      qry += " sum(round(b.product_amount/(months_between(to_date(bill_date2,'mm/yyyy'),to_date(bill_date1,'mm/yyyy'))+1),2)) "
                              + "product_amount "
                              + " from m4_bill_master a,m4_bill_detail b,m4_bill_dept_master c,M4_BILL_type_MASTER d,M4_BILL_SUB_TYPE_MASTER e, "
                              + " m4_cc_master f,fchacc_m4 g,M4_BILL_PRODUCT_MASTER h "
                              + " where a.sl_no=b.bill_sl_no and a.DEPT_SL_NO=c.SL_NO and b.type_sl_no=d.SL_NO AND b.SUB_TYPE_SL_NO=e.SL_NO "
                              + " and b.CC_CODE=f.sl_no and f.cc_code=g.EAAITM and g.EAAITP=3 and g.eaat12=0 and b.PRODUCT_SL_NO=h.SL_NO "
                              + " and a.DEPT_SL_NO in(select  SL_NO from m4_bill_dept_master "
                              + "where DEPT_CODE in(" + deptin + ")) "+wareshouesql+" " + rightBillsql
                              + " " + rightpchsql + " "+rightcostsql+" "+rightproductsql+" "+datesql+" group "
                              + "by a.DEPT_SL_NO,c.DEPT_DESC ,b.type_sl_no,d.TYPE_DESC ||'-'|| d.TYPE_CODE,b.SUB_TYPE_SL_NO,"
                              + "e.SUB_TYPE_DESC  ||'-'|| e.SUB_TYPE_CODE";
                      if (rightpchsql.trim().length() == 0) {
                          qry += " ";
                      } else {
                          qry += " ,b.PCH";
                      }

                      if (rightcostsql.trim().length() == 0) {
                          qry += "  ";
                      } else {
                          qry += ", f.CC_CODE ||'-'|| g.EATX40 ||'-'|| b.CC_CODE  ";
                      }
                      
                        if (rightproductsql.trim().length() == 0) {
                          qry += "  ";
                      } else {
                          qry += " ,b.PRODUCT_SL_NO,h.PRODUCT_DESC ||'-'|| h.PRODUCT_CODE  ";
                      }
                      
                      qry += " order by c.DEPT_DESC ";
            }
          
          System.out.println(qry);
/*
          select distinct a.DEPT_SL_NO,c.DEPT_DESC,b.type_sl_no,d.TYPE_DESC ||'-'|| d.TYPE_CODE TYPE_DESC,
b.SUB_TYPE_SL_NO,e.SUB_TYPE_DESC  ||'-'|| e.SUB_TYPE_CODE SUB_TYPE_DESC,b.PCH,f.CC_CODE ||'-'|| g.EATX40 ||'-'|| b.CC_CODE CC_CODE_DESC,
b.PRODUCT_SL_NO,h.PRODUCT_DESC ||'-'|| h.PRODUCT_CODE PRODUCT_DESC,b.PRODUCT_AMOUNT
from m3_bill_master a,m3_bill_detail b,m3_bill_dept_master c,M3_BILL_type_MASTER d,M3_BILL_SUB_TYPE_MASTER e,
m3_cc_master f,prodbi.fchacc@movex.world@movex g,M3_BILL_PRODUCT_MASTER h
 where a.sl_no=b.bill_sl_no and a.DEPT_SL_NO=c.SL_NO and b.type_sl_no=d.SL_NO AND b.SUB_TYPE_SL_NO=e.SL_NO
 and b.CC_CODE=f.sl_no and f.cc_code=g.EAAITM and g.EAAITP=3 and g.eaat12=0 and b.PRODUCT_SL_NO=h.SL_NO
  and a.DEPT_SL_NO=25
  order by c.DEPT_DESC,d.TYPE_DESC ||'-'|| d.TYPE_CODE,e.SUB_TYPE_DESC  ||'-'|| e.SUB_TYPE_CODE,b.PCH,
  f.CC_CODE ||'-'|| g.EATX40 ||'-'|| b.CC_CODE,h.PRODUCT_DESC ||'-'|| h.PRODUCT_CODE
          */
  
              Map parameters = new HashMap();
           ActionContext ac = ActionContext.getContext();
            ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
            String path=sc.getRealPath("/shahiwebpages/M3Bill/report/");

           // System.out.println(path1);

            InputStream input = new FileInputStream(new File(path + "/M3BillReport.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            parameters.put("qry", qry);
            
            if(BILL_DATE1!=null && BILL_DATE1!=null)
            {
            parameters.put("monthp", "Month : "+BILL_DATE1 +" - "+BILL_DATE2);
            }else{
              parameters.put("monthp", "Month :  - ");
            }
             parameters.put("wareshouepa", "Warehouse : "+wareshouepa);
            
            PreparedStatement stat = con.prepareStatement(qry);
            ResultSet result5 = stat.executeQuery();
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(result5);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, resultSetDataSource);
           
            byte[] bytes = null;

            String reportType = REPORTTYPE;
            ServletOutputStream out1 = response.getOutputStream();
            response.reset();
            if (reportType != null && reportType.equals("PDF")) {
              /*  bytes = JasperRunManager.runReportToPdf(report, parameters, con);
                 response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment;filename=m3bill.pdf;");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                * */
                
               response.setHeader("Content-Disposition","inline; filename=m3bill.pdf");
               response.setHeader("cache-control", "no-cache");
               response.setDateHeader("Last-Modified", 123);
               response.setContentType("application/pdf");
               JasperExportManager.exportReportToPdfStream(print, out1);
              

                
            }
              else if (reportType.equals("XLS")) {
                      
                        JRXlsExporter exporter = new JRXlsExporter();
                        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, "C:\\JSP\\");
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "m3bill.xls");
                        exporter.exportReport();
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "inline; filename=m3bill.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
                    } else {
                        JRXlsxExporter exporterXLSX = new JRXlsxExporter();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        exporterXLSX.setParameter(JRExporterParameter.JASPER_PRINT, print);
                        exporterXLSX.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
                        exporterXLSX.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                        exporterXLSX.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                        exporterXLSX.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                        exporterXLSX.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                        exporterXLSX.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "m3bill.xlsx");
                        exporterXLSX.exportReport();
                        bytes = byteArrayOutputStream.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "inline;filename=m3bill.xlsx;");
                        response.setContentLength(bytes.length);
                        byteArrayOutputStream.close();
                        out1.write(bytes, 0, bytes.length);

                    }
                    out1.flush();
                    out1.close();

         
                                           
        }
        catch (Exception e)
        {
           
            System.out.print("2 File Name : printmethod m3billreport.java" + e);

            System.out.println(e.toString());
        }
        finally
        {
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
               
                if (con != null) {
                    con.close();
                }
                result1 = null;
                stat1 = null;
              
               con = null;

            } catch (Exception e) {
                System.out.print("File Name : printmethod m3billreport.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
          
   
          return  SUCCESS;
        
    }
	





	public List<M3BILLBean> getM3productlist() {
		return m3productlist;
	}


	public void setM3productlist(List<M3BILLBean> m3productlist) {
		this.m3productlist = m3productlist;
	}


	public List getRightproduct() {
		return rightproduct;
	}


	public void setRightproduct(List rightproduct) {
		this.rightproduct = rightproduct;
	}


	public List getRightsubbill() {
		return rightsubbill;
	}


	public void setRightsubbill(List rightsubbill) {
		this.rightsubbill = rightsubbill;
	}



public List<M3BILLBean> getM3costcenterlist() {
		return m3costcenterlist;
	}


	public void setM3costcenterlist(List<M3BILLBean> m3costcenterlist) {
		this.m3costcenterlist = m3costcenterlist;
	}


	public List getRightcost() {
		return rightcost;
	}


	public void setRightcost(List rightcost) {
		this.rightcost = rightcost;
	}


	public List<M3BILLBean> getM3pchtypelist() {
		return m3pchtypelist;
	}


	public void setM3pchtypelist(List<M3BILLBean> m3pchtypelist) {
		this.m3pchtypelist = m3pchtypelist;
	}

    public List getRightPCH() {
        return rightPCH;
    }

    public void setRightPCH(List rightPCH) {
        this.rightPCH = rightPCH;
    }


	


	


	


	

	public List getRightBill() {
		return rightBill;
	}


	public void setRightBill(List rightBill) {
		this.rightBill = rightBill;
	}


	public List<M3BILLBean> getM3billtypelist() {
		return m3billtypelist;
	}



	public void setM3billtypelist(List<M3BILLBean> m3billtypelist) {
		this.m3billtypelist = m3billtypelist;
	}



	public List<M3BILLBean> getM3billsubtypelist() {
		return m3billsubtypelist;
	}


	public void setM3billsubtypelist(List<M3BILLBean> m3billsubtypelist) {
		this.m3billsubtypelist = m3billsubtypelist;
	}


	public List<M3BILLBean> getM3billdeptlist() {
		return m3billdeptlist;
	}



	public void setM3billdeptlist(List<M3BILLBean> m3billdeptlist) {
		this.m3billdeptlist = m3billdeptlist;
	}



	public List getRightDepartment() {
		return rightDepartment;
	}



	public void setRightDepartment(List rightDepartment) {
		this.rightDepartment = rightDepartment;
	}

    public List<M3BILLBean> getM3billtypelistgrp() {
        return m3billtypelistgrp;
    }

    public void setM3billtypelistgrp(List<M3BILLBean> m3billtypelistgrp) {
        this.m3billtypelistgrp = m3billtypelistgrp;
    }

    public List<M3BILLBean> getM3billsubtypelistgrp() {
        return m3billsubtypelistgrp;
    }

    public void setM3billsubtypelistgrp(List<M3BILLBean> m3billsubtypelistgrp) {
        this.m3billsubtypelistgrp = m3billsubtypelistgrp;
    }

    public List<M3BILLBean> getM3costcenterlistgrp() {
        return m3costcenterlistgrp;
    }

    public void setM3costcenterlistgrp(List<M3BILLBean> m3costcenterlistgrp) {
        this.m3costcenterlistgrp = m3costcenterlistgrp;
    }

    public List<M3BILLBean> getM3productlistgrp() {
        return m3productlistgrp;
    }

    public void setM3productlistgrp(List<M3BILLBean> m3productlistgrp) {
        this.m3productlistgrp = m3productlistgrp;
    }
	
	public void setServletRequest(HttpServletRequest servletRequest) {
 
       this.servletRequest = servletRequest;
 
   }
         public void setServletResponse(HttpServletResponse response) {
           this.response = response;
    }

    public List getWarehouselist() {
        return warehouselist;
    }

    public void setWarehouselist(List warehouselist) {
        this.warehouselist = warehouselist;
    }

    public String getBILL_DATE1() {
        return BILL_DATE1;
    }

    public void setBILL_DATE1(String BILL_DATE1) {
        this.BILL_DATE1 = BILL_DATE1;
    }

    public String getBILL_DATE2() {
        return BILL_DATE2;
    }

    public void setBILL_DATE2(String BILL_DATE2) {
        this.BILL_DATE2 = BILL_DATE2;
    }

    public List getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(List warehouse) {
        this.warehouse = warehouse;
    }

    public String getREPORTTYPE() {
        return REPORTTYPE;
    }

    public void setREPORTTYPE(String REPORTTYPE) {
        this.REPORTTYPE = REPORTTYPE;
    }

    public String getMonthtype() {
        return monthtype;
    }

    public void setMonthtype(String monthtype) {
        this.monthtype = monthtype;
    }

   
	
         
         
         
}