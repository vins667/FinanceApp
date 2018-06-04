/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.compcal;

import com.opensymphony.xwork2.ActionContext;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.StrutsStatics;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.compcal.bean.CompCalBean;
import shahi.Action.compcal.bean.ComplianceCalenderDetailBean;
import shahi.Action.compcal.bean.ComplianceCalenderDtBean;
import shahi.Action.compcal.bean.ReportBean;
import shahi.Action.database.connection;

/**
 *
 * @author Ranjeet
 */
public class ActivityMailFunction {
     ResourceBundle aResourcBundle = null;
     public ActivityMailFunction()
    {
        
             aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");

    }
   public void sendmail()
    {
    Connection conn = null;


            try {
                conn = new connection().getConnection();
                //conn.setAutoCommit(false);

            } catch (Exception e) {
                System.out.println(e.toString());
            }
            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            ResultSet result2 = null;
            ResultSet result3 = null;
            ResultSet result4 = null;
            String emplist=null;
            List tempemplist=new ArrayList();
            List tempactivitylist=new ArrayList();
            String actidlist=null;
            List ccidlist=new ArrayList();

      try{
          stat1=conn.prepareStatement("SELECT min(CC_COMPLIANCE_CALENDER.ID) ID,CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_YEAR.ACTIVITY_YEAR,CALENDER_TYPE,"+
        " min(CALENDER_TYPE_CODE) CALENDER_TYPE_CODE,DUE_DAYS,CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID,"+
        " CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID,CC_COMPLIANCE_CALENDER.ACTIVITY_ID"+
        " FROM CC_COMPLIANCE_CALENDER,CC_ACTIVITY_MASTER,CC_ACTIVITY_YEAR "+
        " WHERE CC_COMPLIANCE_CALENDER.ACTIVITY_ID=CC_ACTIVITY_MASTER.ID AND CC_ACTIVITY_MASTER.ID=CC_ACTIVITY_YEAR.ACTIVITY_ID and ACT_FLAG is null AND CC_ACTIVITY_YEAR.FLAG='Y' "+
        " GROUP BY CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_YEAR.ACTIVITY_YEAR,CALENDER_TYPE,DUE_DAYS,"+
        " CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID, CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID,CC_COMPLIANCE_CALENDER.ACTIVITY_ID") ;     
        result1=stat1.executeQuery();
        while(result1.next())
        {
       Date dt=duedate(result1.getString("CALENDER_TYPE"),result1.getInt("ACTIVITY_YEAR"),result1.getInt("CALENDER_TYPE_CODE"),result1.getInt("DUE_DAYS"));
       String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dt);
        

        
        stat1=conn.prepareStatement("select distinct id,trunc(to_date('"+formattedDate+"','YYYY-mm-dd')-ALERT_TYPE1) - trunc(sysdate) t1, "+
                " trunc(to_date('"+formattedDate+"','YYYY-mm-dd')-ALERT_TYPE2) - trunc(sysdate) t2, "+
                " trunc(to_date('"+formattedDate+"','YYYY-mm-dd')-ALERT_TYPE3) - trunc(sysdate) t3,"+
                "trunc(to_date('"+formattedDate+"','YYYY-mm-dd')) - trunc(sysdate) dt1 "+
                "  from cc_activity_master  where "+
               "id=? and flag='Y'");
        stat1.setString(1,result1.getString("ACTIVITY_ID"));
         result=stat1.executeQuery();
         String mailtype=null;
        while(result.next())
        {
          
            if(result.getInt("t1")<=0 && result.getInt("dt1")>=0)
            {
             if(mailtype==null)
             {
                 mailtype="'T1'";
             }else{
                  mailtype=mailtype+",'T1'";
             }
            }
            if(result.getInt("t2")<=0 && result.getInt("dt1")>=0)
            {
             if(mailtype==null)
             {
                 mailtype="'T2'";
             }else{
                  mailtype=mailtype+",'T2'";
             }
            }
            if(result.getInt("t3")<=0 && result.getInt("dt1")>=0)
            {
             if(mailtype==null)
             {
                 mailtype="'T3'";
             }else{
                  mailtype=mailtype+",'T3'";
             }
            }
             // System.out.println(mailtype);
         
       
        }
        
                     if(mailtype!=null)
                     {
                                              
    		  	                     
    		  	                    stat1=conn.prepareStatement("select link_user,E_MAIL,initcap(USER_NAME) USER_NAME,b.user_id from cc_user_link a,seh_web_users b where a.link_user=b.user_id "+
                                                "and ACT_ID=? and ALERT_TYPE in("+mailtype+")");
                                            stat1.setString(1,result1.getString("ACTIVITY_ID"));
                                            result2=stat1.executeQuery();
                                            while(result2.next())
                                            {
                                                 
                                            if(!tempemplist.contains(result2.getString("user_id"))){
                                            if(emplist==null)
                                            {
                                            emplist=result2.getString("user_id");
                                           
                                            }else{
                                            emplist=emplist +","+result2.getString("user_id");
                                            }
                                            tempemplist.add(result2.getString("user_id"));
                                            
                                            }
                                           
                                            ccidlist.add(new CompCalBean(result2.getString("user_id"),result1.getString("ID")));
                                            
                                            
                                            if(!tempactivitylist.contains(result1.getString("ACTIVITY_ID"))){
                                             if(actidlist==null)
                                            {
                                            actidlist=result1.getString("ACTIVITY_ID");
                                           
                                            }else{
                                            actidlist=actidlist +","+result1.getString("ACTIVITY_ID");
                                            }
                                               tempactivitylist.add(result1.getString("ACTIVITY_ID"));
                                            }
                                            
                                            }
                                            
           }
        }
      }catch(Exception ee)
      {
      System.out.println(ee.toString());
      }
      finally{
      try {

                    if (result1 != null) {
                        result1.close();
                    }
                    if (result != null) {
                        result.close();
                    }
                    if (result2 != null) {
                        result2.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    result2=null;
                    result=null;
                    stat1 = null;
                    conn = null;
                   

                } catch (Exception e) {

                    System.out.print("File Name : ActivityMailAction Exception in finally block");
                    e.printStackTrace();
                }
      
      }
     // System.out.println(tempemplist +"::"+actidlist +":::"+ccidlist);
      
      
      if(tempemplist!=null && tempemplist.size()>0){
      // sendmailemp(tempemplist,actidlist);
          sendmailemp(tempemplist,ccidlist);
      }
       //tempemplist=new ArrayList();
      // tempemplist.add("228570");
       //tempemplist.add("220001");
      // System.out.println(tempemplist);
       //sendmailemp(tempemplist);
    }
    private String getValue(String key) {
    return aResourcBundle.getString(key);
  }
     private int getIntValue(String key) {
    return Integer.parseInt(getValue(key));
  }
   void sendmailemp(List emplist, List actidlist){
       try{
            
            String path =getValue("path");
            path=path+"/shahiwebpages/compcal/xml/";
                   
              Map parameters = new HashMap();
        for(int i=0; i<emplist.size(); i++)
        {
            String idstring=null;
            if(actidlist!=null && actidlist.size()>0){
                Iterator itr = actidlist.iterator();
                while (itr.hasNext()) {
                    CompCalBean bn = (CompCalBean) itr.next();
                    if (emplist.get(i).toString().equals(bn.getID())) {
                        if(idstring==null)
                        {
                           idstring=bn.getNAME();
                        }else{
                           idstring=idstring+","+bn.getNAME();
                        }
                    }

                }
            }
            
            
            List list=pendingact(emplist.get(i).toString(),idstring);
           if(idstring!=null){
           InputStream input = new FileInputStream(new File(path + "complianceduereport.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(list));
           // ByteArrayOutputStream baos = new ByteArrayOutputStream();
           // JasperExportManager.exportReportToPdfStream(print, baos);
            //DataSource aAttachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
            OutputStream output = new FileOutputStream(new File(path + "pdf/DueCompliance.pdf"));
            JasperExportManager.exportReportToPdfStream(print, output);
            output.close();
             File tempfile=new File(path + "pdf/DueCompliance.pdf");
                
          if(tempfile.exists() && tempfile.isFile() && (tempfile.length())/1024>1)
          {
            MailProvider mailProvider = new MailProvider();
            String subjecttitle = "";
            String messageBodyText = " ";
            String frommail = "";
            String fromname = "";
            String[] tomail = new String[1];
            String toname = "";
            Connection conn=null;
            PreparedStatement stat1 = null;
            ResultSet result2 = null;
            conn=new connection().getConnection();
            stat1 = conn.prepareStatement("select E_MAIL,initcap(USER_NAME) USER_NAME from  seh_web_users where user_id='"+emplist.get(i).toString()+"'");
            result2 = stat1.executeQuery();
            if (result2.next()) {
                tomail[0] =result2.getString(1) ;
                
                toname = result2.getString("USER_NAME");
               }
            
           
            
             if(result2!=null)
             {
             result2.close();
             } if(stat1!=null)
             {
             stat1.close();
             }
             if(conn!=null)
             {
               conn.close();
             }

            frommail = "movex@shahi.co.in";
            fromname = "Shahi IT";
            /// DataSource[] filenames= new DataSource[1];
            // filenames[0]=aAttachment;
             String[] filenames= new String[1];
               filenames[0]=path + "pdf/DueCompliance.pdf";
               
            subjecttitle = "Report: Due Compliance (HR)";
            messageBodyText = "<html>";
            messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
            messageBodyText += "<body>";
            messageBodyText += "Dear " + toname + ",<br/>&nbsp;<br/>";
            messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:black'>";
            messageBodyText += "Pls find enclosed report of compliance due as on date.";
            messageBodyText += "</br></br>";
            messageBodyText += "<table cellpadding='4' width='600'>";
            messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
            messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
            messageBodyText += "</table>";
            messageBodyText += "</body>";
            messageBodyText += "</html>";
          
             mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, filenames);
          
                }
           }
        }
       }catch(Exception e)  
       {
       System.out.println(e.toString());
       }
   }
  
 List pendingact(String empcode,String actidlist) throws SQLException{
	
       List a=new ArrayList();
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet resultSet=null;
        PreparedStatement stat1=null;
        ResultSet resultSet1=null;
        try {
		    conn = new connection().getConnection();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
        try{
        	a=new ArrayList<ComplianceCalenderDtBean>();
        	
        	
        	stat=conn.prepareStatement("SELECT min(CC_COMPLIANCE_CALENDER.ID) ID,CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_YEAR.ACTIVITY_YEAR,CALENDER_TYPE,"
					+" min(CALENDER_TYPE_CODE) CALENDER_TYPE_CODE,DUE_DAYS,CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID,"
					+" CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID,"
                                        +" CC_COMPANY_MASTER.NAME compname,CC_DEPT_MASTER.NAME deptuser, CC_SUB_DEPT_MASTER.NAME subdeptuser,CC_ACTIVITY_MASTER.ID actid"
                                        +" FROM CC_COMPLIANCE_CALENDER,CC_ACTIVITY_MASTER,CC_ACTIVITY_YEAR,CC_COMPANY_MASTER,CC_DEPT_MASTER,CC_SUB_DEPT_MASTER "
					+" WHERE CC_COMPLIANCE_CALENDER.ACTIVITY_ID=CC_ACTIVITY_MASTER.ID AND CC_ACTIVITY_MASTER.ID=CC_ACTIVITY_YEAR.ACTIVITY_ID  AND CC_ACTIVITY_YEAR.FLAG='Y' and act_flag is null "
					+" and CC_ACTIVITY_MASTER.ID in(select ACT_ID from CC_ACTIVITY_ACCESS_MASTER where EMP_CODE="+empcode+" and FLAG='Y')"
                                       // +" and CC_COMPLIANCE_CALENDER.ACTIVITY_ID in ("+actidlist+")"
                                        +" and CC_COMPLIANCE_CALENDER.ID in ("+actidlist+")"
                                        +" and CC_COMPLIANCE_CALENDER.COMPANY_ID=CC_COMPANY_MASTER.ID and CC_ACTIVITY_MASTER.DEPT_ID=CC_DEPT_MASTER.ID and CC_ACTIVITY_MASTER.SUBDEPT_ID=CC_SUB_DEPT_MASTER.ID "
                                        +" GROUP BY CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_YEAR.ACTIVITY_YEAR,CALENDER_TYPE,DUE_DAYS,"
					+" CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID, CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID,CC_COMPANY_MASTER.NAME,CC_DEPT_MASTER.NAME,CC_SUB_DEPT_MASTER.NAME,CC_ACTIVITY_MASTER.ID  order by compname,ACTIVITY_NAME,LOCATION_CODE,deptuser,subdeptuser");
        	
                resultSet=stat.executeQuery();
        	while(resultSet.next()){
        		
        		Date duedate=duedate(resultSet.getString("CALENDER_TYPE"),resultSet.getInt("ACTIVITY_YEAR"),resultSet.getInt("CALENDER_TYPE_CODE"),resultSet.getInt("DUE_DAYS"));
        		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        		     //  System.out.println(resultSet.getString("ACTIVITY_NAME") +"::"+formatter.format(duedate))       ;   
                        
                        a.add(new ComplianceCalenderDtBean(resultSet.getLong("ID"),resultSet.getString("ACTIVITY_NAME"),formatter.format(duedate),resultSet.getString("compname"),resultSet.getString("LOCATION_CODE"),resultSet.getString("deptuser"),resultSet.getString("subdeptuser"),resultSet.getString("CALENDER_TYPE"),""));
        	
        	
        	}
        }
        catch(Exception e){
       	 System.out.println("UserMasterAction " + e);
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	        if(stat!=null){
	        	stat.close();
	        }
	        if(resultSet!=null){
	        	resultSet.close();
	        }
	    }
        return a;
	} 
   
    public Date duedate(String type,int year,int code,int duedate){
        int bb=0;
        if(type!=null && type.equals("M")){
            switch(code){
            case 1 : bb=3;
            break;
            case 2 : bb=4;
             break;
            case 3 : bb=5;
             break;
            case 4 : bb=6;
             break;
            case 5 : bb=7;
             break;
            case 6 : bb=8;
             break;
            case 7 : bb=9;
             break;
            case 8 : bb=10;
             break;
            case 9 : bb=11;
             break;
            case 10 : bb=12;
             break;
            case 11 : bb=1;
             break;
            case 12 : bb=2;
             break;             
            }
        }
        else if(type!=null && type.equals("Q")){
            switch(code){
            case 1 : bb=5;
            break;
            case 2 : bb=8;
             break;
            case 3 : bb=11;
             break;
            case 4 : bb=2;
             break;
            }
        }
        else if(type!=null && type.equals("H")){
            switch(code){
            case 1 : bb=8;
            break;
            case 2 : bb=2;
             break;
            }
        }
        else if(type!=null && type.equals("Y")){
            bb=2;
        }
        if(bb<3){
            year=year+1;
        }
        Date dt=getLastDateOfMonth(year,bb);
        Calendar cal=Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, duedate);
        dt=cal.getTime();
        return dt;
    }
    
    public static Date getLastDateOfMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    

}
