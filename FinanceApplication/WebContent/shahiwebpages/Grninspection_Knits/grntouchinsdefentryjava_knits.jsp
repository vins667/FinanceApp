<%@page import="shahi.Action.database.ConnectionSeplWeb"%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page  import="com.ibm.as400.access.*"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
     <LINK href="css/style.css" rel="stylesheet"	type="text/css">

    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
  </head>
  <%
        PreparedStatement        stat2 = null;
        PreparedStatement        stat1 = null;
        PreparedStatement        stat=null;
         ResultSet result=null;
         ResultSet result1=null;
         ResultSet result2=null;
        String grnno=request.getParameter("grnno");
        String ROLL_NO=request.getParameter("ROLL_NO");
        String emp_code=request.getParameter("emp_code");
        Connection conn = null;
        ResourceBundle aResourcBundle = null;
        String myusrid = (String)session.getValue("myusrid");
        String location = (String)session.getValue("LOCATION_CODE");
         //location="100";
         conn=new ConnectionSeplWeb().getConnection();
        conn.setAutoCommit(false);
  
   try
    {
        int sl_no=0;
        stat=conn.prepareStatement("select grninsdft_s.nextval sl_no from dual");
        result=stat.executeQuery();
        if(result.next())
        {
         sl_no=result.getInt(1);
        }
        stat=conn.prepareStatement("insert into grndftdt(LOCATION_CODE,RECEIPT_NO,ROLL_NO,DEFECT_CODE,DEFECT_VALUE,TDATE,TTIME,USER_ID,SL_NO) values(?,?,?,?,?,trunc(sysdate),sysdate,?,?)");
        stat.setString(1,location);
        stat.setString(2,grnno);
        stat.setString(3,ROLL_NO);
        stat.setString(4,request.getParameter("DEFECT_CODE"));
        stat.setString(5,request.getParameter("DEFECT_VALUE"));
        stat.setString(6,emp_code);
        stat.setInt(7,sl_no);
        stat.executeUpdate();
    conn.commit();
}catch (Exception e)
       {
       try{
       conn.rollback();
       }catch(Exception ee){
        System.out.print("1 File Name : grntouchinsdefentryjava_knits.jsp"+ee);
         System.out.println(ee.toString());
       }
       System.out.print("2 File Name : grntouchinsdefentryjava_knits.jsp"+e);
        System.out.println(e.toString());
       }finally {
                try {                   
                        if(result!=null)
                        {
                        result.close();
                        }
                          if(result1!=null)
                        {
                        result1.close();
                        }
                          if(result2!=null)
                        {
                        result2.close();
                        }
                         if(stat!=null)
                        {
                        stat.close();
                        }
                         if(stat1!=null)
                        {
                        stat1.close();
                        }
                         if(stat2!=null)
                        {
                        stat2.close();
                        }
                    if(conn != null){                        
                        conn.close();}                     
                    result=null;
                    stat=null;
                    conn = null;                   
                } catch(Exception e) {
                    System.out.print("File Name : grntouchinsdefentryjava_knits.jsp Exception in finally block");
                    e.printStackTrace();
                }
            }  
            response.sendRedirect("grntouchinsdefentry1_knits.jsp?grnno="+request.getParameter("grnno")+"&ROLL_NO="+request.getParameter("ROLL_NO")+"&emp_code="+request.getParameter("emp_code")+"&DC="+request.getParameter("DEFECT_CODE")+"&DV="+request.getParameter("DEFECT_VALUE"));
  %>
  <body>
  </body>
</html>
