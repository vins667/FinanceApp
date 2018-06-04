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
        String ROLL_NO[]=request.getParameterValues("ROLL_NO");
        
        
        
        String emp_code=request.getParameter("emp_code");
        Connection conn = null;
        ResourceBundle aResourcBundle = null;
        String myusrid = (String)session.getValue("myusrid");
        String location = (String)session.getValue("LOCATION_CODE");
        // location="100";
         conn=new ConnectionSeplWeb().getConnection();
        conn.setAutoCommit(false);
  int pagflg=0;
   try
{ 

        int sl_no=0;
     if(emp_code!=null && !emp_code.equals("") && !emp_code.equals("null"))
     {
  for(int i=0; i<ROLL_NO.length; i++)   
  {
  
 stat=conn.prepareStatement("update grninsdt set SHADE_LOT=?,CS=?,LWV=?,TDATE=trunc(sysdate),TTIME=sysdate,USER_ID=?,SHADE_GRP=?,INSPECTOR_S=?,MIN_WIDTH=?,MAX_WIDTH=?,SHADE_LOT_DATE=trunc(sysdate),CUT_PCS=?,ROLL_BOWING=?,FOLD_LENGTH=?,ROLL_SHRK_PRCT=? where RECEIPT_NO=? and location_code=? and roll_no=? and INSPECTOR_S=?");
stat.setString(1,request.getParameter("SHADE_LOT"+ROLL_NO[i]));
stat.setString(2,request.getParameter("CS"+ROLL_NO[i]));
stat.setString(3,request.getParameter("LWV"+ROLL_NO[i]));
stat.setString(4,emp_code);
stat.setString(5,request.getParameter("SHADE_GRP"+ROLL_NO[i]));
stat.setString(6,emp_code);
stat.setString(7,request.getParameter("MIN_WIDTH"+ROLL_NO[i]));
stat.setString(8,request.getParameter("MAX_WIDTH"+ROLL_NO[i]));

stat.setString(9,request.getParameter("CUT_PCS"+ROLL_NO[i]));
stat.setString(10,request.getParameter("ROLL_BOWING"+ROLL_NO[i]));
stat.setString(11,request.getParameter("FOLD_LENGTH"+ROLL_NO[i]));
stat.setString(12,request.getParameter("ROLL_SHRK_PRCT"+ROLL_NO[i]));



stat.setString(13,grnno);
stat.setString(14,location);
stat.setString(15,ROLL_NO[i]);
stat.setString(16,emp_code);
stat.executeUpdate();

stat=conn.prepareStatement("update grninsdt set SHADE_LOT=?,CS=?,LWV=?,TDATE=trunc(sysdate),TTIME=sysdate,USER_ID=?,SHADE_GRP=?,INSPECTOR_S=?,MIN_WIDTH=?,MAX_WIDTH=?,SHADE_LOT_DATE=trunc(sysdate),CUT_PCS=?,ROLL_BOWING=?,FOLD_LENGTH=?,ROLL_SHRK_PRCT=? where RECEIPT_NO=? and location_code=? and roll_no=? and (INSPECTOR_S is null or SHADE_LOT is null)");
stat.setString(1,request.getParameter("SHADE_LOT"+ROLL_NO[i]));
stat.setString(2,request.getParameter("CS"+ROLL_NO[i]));
stat.setString(3,request.getParameter("LWV"+ROLL_NO[i]));
stat.setString(4,emp_code);
stat.setString(5,request.getParameter("SHADE_GRP"+ROLL_NO[i]));
stat.setString(6,emp_code);
stat.setString(7,request.getParameter("MIN_WIDTH"+ROLL_NO[i]));
stat.setString(8,request.getParameter("MAX_WIDTH"+ROLL_NO[i]));

stat.setString(9,request.getParameter("CUT_PCS"+ROLL_NO[i]));
stat.setString(10,request.getParameter("ROLL_BOWING"+ROLL_NO[i]));
stat.setString(11,request.getParameter("FOLD_LENGTH"+ROLL_NO[i]));
stat.setString(12,request.getParameter("ROLL_SHRK_PRCT"+ROLL_NO[i]));


stat.setString(13,grnno);
stat.setString(14,location);
stat.setString(15,ROLL_NO[i]);
stat.executeUpdate();


}
}else{

pagflg=1;
}
conn.commit();
}catch (Exception e)
       {
      
       try{
       
       conn.rollback();
        
       }catch(Exception ee){
        System.out.print("1 File Name : grotouchgrnshadelotjava.java"+ee);
                 
         System.out.println(ee.toString());
       }
       System.out.print("1 File Name : grotouchgrnshadelotjava.java"+e);
        
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
                    System.out.print("File Name : grotouchgrnshadelotjava.java Exception in finally block");
                    e.printStackTrace();
                }
            }
         
         int kkk=Integer.parseInt(request.getParameter("addmorepage"))-1;
         String SEARCHQTYstring=" ";
         if(request.getParameter("SEARCHQTY")!=null && request.getParameter("SEARCHQTY").length()>0)
         {
         SEARCHQTYstring="&SEARCHQTY="+request.getParameter("SEARCHQTY");
         }
         
         if(pagflg==0)
         {
         response.sendRedirect("grntouchgrnshadelot_knits.jsp?grnno="+request.getParameter("grnno")+"&emp_code="+request.getParameter("emp_code")+"&firstpageno="+request.getParameter("pagebreak12")+"&pagebreak="+request.getParameter("aaaaaaa")+"&aaaaaaa="+request.getParameter("aaaaaaa")+"&newpage=0&maxpage="+kkk+"&addmorepage="+request.getParameter("addmorepage")+SEARCHQTYstring);
         }else{
         response.sendRedirect("TouchIndex_knits.jsp");
         }
  %>
  <body>
  </body>
</html>
