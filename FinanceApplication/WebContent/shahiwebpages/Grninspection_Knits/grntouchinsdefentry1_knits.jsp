<%-- 
    Document   : grntouchinsdefentry1_knits
    Created on : Oct 18, 2011, 4:55:25 PM
    Author     : Shyamal
--%>

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
<%@ page import="java.lang.Math"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
     <LINK href="css/style.css" rel="stylesheet"	type="text/css">

    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
   <%
    String  DC=request.getParameter("DC");
    String  DV=request.getParameter("DV");


   %>
 <script language="javascript">
 function saverecord(a,b)
 {

 if(validaterec()==true)
 {
 document.hris.action="grntouchinsdefentryjava_knits.jsp?DEFECT_CODE="+a+"&DEFECT_VALUE="+b;
 document.hris.submit();
 }
 }

 function saverecord1(a,b)
 {
  if(confirm('Do You Want to Delete Record(s) ?'))
  {
  b=-b;

  document.hris.action="grntouchinsdefentryjava_knits.jsp?DEFECT_CODE="+a+"&DEFECT_VALUE="+b;
  document.hris.submit();

 }else{
  return;
 }

 }

function validaterec()
{



return true;

}

function changefun(a,b,c)
{

document.getElementById(c).style.backgroundColor="#d6ff8a";
document.getElementById(c).style.borderWidth="3pt";
document.getElementById(c).style.borderStyle="outset";


aa=document.getElementsByName('raj');
for(i=0; i<aa.length; i++)
{
bb=aa[i].id;
if(bb==c)
{}else{
aa[i].style.backgroundColor="#c7dbf7"
aa[i].style.borderWidth="0pt";
}
}

var el = document.getElementById('addrec');

el.onclick =function(){
saverecord(a,b)
}

ack=document.forms['hris'].elements[a+b+'a'].value;

var e2 = document.getElementById('delrec');
if(parseInt(ack)>0)
{
e2.onclick =function(){
saverecord1(a,b)
}
}else{
e2.onclick =function(){alert('Not Allowed')}
}

}

function changeselectcolor(c)
{
document.getElementById(c).style.backgroundColor="#99a3ff";
document.getElementById(c).style.borderWidth="3pt";
document.getElementById(c).style.borderStyle="outset";

}
 </script>
    <style>
    text{
    font-size:18pt;
    width:200pt;
    }
    td{
   font-size:15pt;
    }

   a.button {

color:Black;
background-color: #c7dbf7;
background-repeat: no-repeat;
width:115px;
height:85pt;
font-size:20pt;
text-align: center;


font-weight: bold;
text-decoration: none;
}



    </style>
  </head>
  <%
        PreparedStatement        stat2,stat1     = null;
        PreparedStatement        stat=null;
        ResultSet result=null;
        ResultSet result2,result1=null;
        String grnno=request.getParameter("grnno");
        String ROLL_NO=request.getParameter("ROLL_NO");
        String emp_code=request.getParameter("emp_code");
        Connection conn = null;
        ResourceBundle aResourcBundle = null;
        String myusrid = (String)session.getValue("myusrid");
        String location = (String)session.getValue("LOCATION_CODE");
        String EMPNAME= (String)session.getValue("EMPNAME");

        //location="100";
       conn=new ConnectionSeplWeb().getConnection();

  if(DC!=null && DV!=null)
  {

  %>
   <body onload="changeselectcolor('<%=DC+Math.abs(Integer.parseInt(DV))%>')" >
  <%}else{
  %>

  <body >
  <%}%>
  <form name="hris" method="post" action="">
  <table bgcolor="#006699" border="0" cellpadding="4"   cellspacing="1"  width="100%">
<tr >
 <td class="label" style="font-size:15pt;font-family:Arial Black;font-weight:'normal'"><%=EMPNAME%> - Shahi Exports Pvt. Ltd.</td>

<td align="right">

           <!--<input type="BUTTON"  onclick="saverecord();" style="font-size:20pt;width:100pt" name="btn" value="Save">-->
           <input type="BUTTON"  style="font-size:20pt;width:100pt"  onclick="window.location.href='grntouchgrndtls_knits.jsp?grnno=<%=request.getParameter("grnno")%>&emp_code=<%=emp_code%>&srollno=<%=ROLL_NO%>'" name="btn" value="Back">

</td>
    </tr>
  </table>
  <table cellpadding="5" cellspacing="1" bgcolor="#007fbf" border="" width="100%">

 <%
       stat=conn.prepareStatement("select nvl(sum(DEFECT_VALUE),0) total from grndftdt where LOCATION_CODE=? and RECEIPT_NO=? and ROLL_NO=?");
       stat.setString(1,location);
       stat.setString(2,grnno);
       stat.setString(3,ROLL_NO);
       result=stat.executeQuery();
       String tpoint=null;
       while(result.next())
       {
       tpoint=result.getString(1);
       }


  stat=conn.prepareStatement("select * from grninsdt where LOCATION_CODE=? and RECEIPT_NO=? and ROLL_NO=?");
  stat.setString(1,location);
  stat.setString(2,grnno);
  stat.setString(3,ROLL_NO);
  result=stat.executeQuery();
  String actuallength=null;
  int closeflag=0;
  int MIN_WIDTHint=1;
  int TOLERANCE=1;
  double gsm=0;
  String INS_UOM=null;
  String BUOM=null;
  String AUOM=null;
  double CONV_FACT=0;
  if(result.next())
  {
   actuallength=result.getString("ACTUAL_QTY");
   MIN_WIDTHint=result.getInt("MIN_WIDTH");
   TOLERANCE=result.getInt("TOLERANCE");
   INS_UOM=result.getString("INS_UOM");
   BUOM=result.getString("BUOM");
   AUOM=result.getString("AUOM");
   CONV_FACT=result.getDouble("CONV_FACT");
   gsm=result.getDouble("GSM_BW");
   
      if(result.getString("CLOSE_DATE")!=null || result.getString("End_DATE")!=null)
      {
      closeflag=1;
      }
  }
  if(actuallength==null)
  {
  actuallength="0";
  }
   if(MIN_WIDTHint==0)
  {
  MIN_WIDTHint=1;
  }
  
 %>


  <tr bgcolor="#F5F5F5"><td class="label-1" style="font-size:15pt;" align="right" >Grn#</td><td><input type="TEXT" style="font-size:18pt;width:100pt" value="<%=grnno%>" readonly="readonly" name="grnno"></td>
  <td class="label-1"style="font-size:15pt;" align="right" >Roll No</td><td> <input type="TEXT" style="font-size:18pt;width:100pt" value="<%=ROLL_NO%>" readonly="readonly" name="ROLL_NO"></td>
  <td class="label-1"style="font-size:15pt;" align="right" >Actual Roll Qty</td>
  <td class="label-1"style="font-size:15pt;"><%=actuallength%></td>
  </tr>
  <tr bgcolor="#F5F5F5"><td class="label-1" style="font-size:15pt;" align="right"  >Total Points</td><td class="label-1"style="font-size:15pt;color:Red">
 <%=tpoint%>
  </td>
  <td class="label-1" style="font-size:15pt;" align="right">Points(100 <%=INS_UOM%><sup style="font-weight:bold;font-size:15pt">2</sup>)</td>
  <td  class="label-1" style="font-size:15pt;color:Red">

  <%
  double pointtemp=0;

  if(Float.parseFloat(tpoint)>0 && Float.parseFloat(actuallength)>0){
  if(INS_UOM!=null && INS_UOM.equals("YRD") && AUOM!=null && AUOM.equals("MTR")){%>
   <%=Math.round((Float.parseFloat(tpoint)*3300)/(Float.parseFloat(actuallength)*MIN_WIDTHint))%>
  <%
  pointtemp=Math.round((Float.parseFloat(tpoint)*3300)/(Float.parseFloat(actuallength)*MIN_WIDTHint));
  }
  else if(INS_UOM!=null && INS_UOM.equals("MTR")  && AUOM!=null && AUOM.equals("MTR")){
  %>
  <%=Math.round(((((Float.parseFloat(tpoint)*100)/Float.parseFloat(actuallength))*40)/MIN_WIDTHint) *100.0)/100.0%>
  <%
  pointtemp=Math.round(((((Float.parseFloat(tpoint)*100)/Float.parseFloat(actuallength))*40)/MIN_WIDTHint) *100.0)/100.0;
  }
  else if(INS_UOM!=null && INS_UOM.equals("YRD")  && AUOM!=null && AUOM.equals("YRD")){
  %>
   <%=Math.round((Float.parseFloat(tpoint)*3600)/(Float.parseFloat(actuallength)*MIN_WIDTHint))%>
<%
pointtemp=Math.round((Float.parseFloat(tpoint)*3600)/(Float.parseFloat(actuallength)*MIN_WIDTHint));
} else if(INS_UOM!=null && INS_UOM.equals("MTR")  && AUOM!=null && AUOM.equals("KGS")  && BUOM!=null && BUOM.equals("MTR")){
  %>
  <%=Math.round(((((Float.parseFloat(tpoint)*100)/(Float.parseFloat(actuallength)*CONV_FACT))*40)/MIN_WIDTHint) *100.0)/100.0%>
 <%
 pointtemp=Math.round(((((Float.parseFloat(tpoint)*100)/(Float.parseFloat(actuallength)*CONV_FACT))*40)/MIN_WIDTHint) *100.0)/100.0;
 }
 else if(INS_UOM!=null && INS_UOM.equals("YRD")  && AUOM!=null && AUOM.equals("KGS") && BUOM!=null && BUOM.equals("MTR")){
  %>
   <%=Math.round((Float.parseFloat(tpoint)*3300)/((Float.parseFloat(actuallength)*CONV_FACT)*MIN_WIDTHint))%>
<%
pointtemp=Math.round((Float.parseFloat(tpoint)*3300)/((Float.parseFloat(actuallength)*CONV_FACT)*MIN_WIDTHint));
}
else if(INS_UOM!=null && INS_UOM.equals("YRD")  && AUOM!=null && AUOM.equals("KGS") && BUOM!=null && BUOM.equals("YRD")){
  %>
   <%=Math.round((Float.parseFloat(tpoint)*3600)/((Float.parseFloat(actuallength)*CONV_FACT)*MIN_WIDTHint))%>
<%
pointtemp=Math.round((Float.parseFloat(tpoint)*3600)/((Float.parseFloat(actuallength)*CONV_FACT)*MIN_WIDTHint));
}

 else if(INS_UOM!=null && INS_UOM.equals("YRD")  && AUOM!=null && AUOM.equals("KGS") && BUOM!=null && BUOM.equals("KGS")){
  %>
      <%=Math.round( (Float.parseFloat(tpoint)* gsm*0.081 )/( (Float.parseFloat(actuallength)*CONV_FACT) ) )%>
<%
pointtemp=Math.round((Float.parseFloat(tpoint)* gsm*0.081 )/((Float.parseFloat(actuallength)*CONV_FACT)));
}

//(gsm*0.081*tpoint)/roll wt kg

  }else{%>0<%}%>


   </td>
   <td class="label-1" style="font-size:15pt;" align="right">Status</td>
  <td  class="label-1" style="font-size:15pt;color:Red">
  <%if(Float.parseFloat(tpoint)>0 && Float.parseFloat(actuallength)>0){
  %>
  <%if(pointtemp <=TOLERANCE)
  {%>
  P1
  <%}else
  {%>
  R1
  <%}

  }else{%>Pending<%}%>
  </td>


  </tr>


 </table>


  <%
  stat1=conn.prepareStatement("select distinct DEFECT_CODE,DEFECT_DESC from grndftms_knits where FLAG='A'");
  result1=stat1.executeQuery();
  %>
  <table><tr>
  <td width="100%">
  <div style="width:100%;height:580px;overflow:auto">

  <table width="100%" border="1" cellpadding="0" cellspacing="0" >
  <%while(result1.next()){%>

  <tr><td class="label-1" style="font-size:15pt" width="100%">
  <table><tr><td>
  <%=result1.getString("DEFECT_DESC")%>
  </td></tr><tr><td class="label-1" style="font-size:15pt">
  Total Points:
      <%
        stat=conn.prepareStatement("select nvl(sum(DEFECT_VALUE),0) total from grndftdt where LOCATION_CODE=? and RECEIPT_NO=? and ROLL_NO=? and DEFECT_CODE=?");
       stat.setString(1,location);
       stat.setString(2,grnno);
       stat.setString(3,ROLL_NO);
       stat.setString(4,result1.getString("DEFECT_CODE"));
       result=stat.executeQuery();
       while(result.next())
       {%>
       <%=result.getInt(1)%>
       <%}
       %>

  </td></tr></table>
  </td>
  <% stat1=conn.prepareStatement("select * from grndftms_knits where FLAG='A' and DEFECT_CODE=?");
     stat1.setString(1,result1.getString("DEFECT_CODE"));
     result2=stat1.executeQuery();

    %>
  <td>
  <table cellpadding="0"  bgcolor="white" border="1"   style="border-left:0pt;border-top:0pt;border-bottom:0pt;"  cellspacing="0">

  <tr>
  <%while(result2.next()){
       stat=conn.prepareStatement("select nvl(sum(DEFECT_VALUE)/?,0) ctn from grndftdt where LOCATION_CODE=? and RECEIPT_NO=? and ROLL_NO=? and DEFECT_CODE=?  and abs(DEFECT_VALUE)=? ");
       stat.setString(1,result2.getString("DEFECT_VALUE"));
       stat.setString(2,location);
       stat.setString(3,grnno);
       stat.setString(4,ROLL_NO);
       stat.setString(5,result2.getString("DEFECT_CODE"));
       stat.setString(6,result2.getString("DEFECT_VALUE"));
       result=stat.executeQuery();
       int k=0;
  %>
  <td >
  <%while(result.next()){
  k=result.getInt(1);
  }%>
 <input type="HIDDEN" name="<%=result2.getString("DEFECT_CODE")+result2.getString("DEFECT_VALUE")%>a" value="<%=k%>">
  <%
  String btnvalue=result2.getString("DEFECT_VALUE")+ " X "+ k+" ="+result2.getInt("DEFECT_VALUE")*k;
  String btnvalue1=result2.getString("DEFECT_VALUE");
   String btnvalue2=" X "+ k+" ="+result2.getInt("DEFECT_VALUE")*k;
  %>
  <%--<a href="#" class="button" id="<%=result2.getString("DEFECT_CODE")+result2.getString("DEFECT_VALUE")%>" name="raj"  onclick="changefun('<%=result2.getString("DEFECT_CODE")%>','<%=result2.getString("DEFECT_VALUE")%>','<%=result2.getString("DEFECT_CODE")+result2.getString("DEFECT_VALUE")%>')" >
  <table style="height:80pt;" width="150"   ><tr><td  valign="middle" style="font-size:30pt;color:black" class="label-1"><%=btnvalue1%></td><td  class="label-1" style="font-size:17pt;color:black"> <%=btnvalue2%></td></tr></table>
  </a>--%>
    <input type="BUTTON"  style="width:115pt;height:80pt;font-size:20pt;background-color:#c7dbf7;border-width:0pt;cursor:hand" id="<%=result2.getString("DEFECT_CODE")+result2.getString("DEFECT_VALUE")%>" name="raj"  onclick="changefun('<%=result2.getString("DEFECT_CODE")%>','<%=result2.getString("DEFECT_VALUE")%>','<%=result2.getString("DEFECT_CODE")+result2.getString("DEFECT_VALUE")%>')"  value="<%=btnvalue%>">
  </td>
  <%}%>
 </table>
  </td>
 <%}%>
 </table>
 </div>

 </td><td valign="top">
 <%if(closeflag==1){%>
  <input type="BUTTON" name="btn" disabled="disabled" id="addrec" onclick="alert('Please Select Point')"  style="width:130pt;height:213pt;font-size:80pt;background-color:#72cc85;color:white"  value="+">
 <br>
  <input type="BUTTON" name="btn" disabled="disabled" id="delrec"  onclick="alert('Please Select Point')" style="width:130pt;height:211pt;font-size:80pt;background-color:#bf6900;color:white"  value="-">
 <%}else{%>
  <input type="BUTTON" name="btn" id="addrec" onclick="alert('Please Select Point')"   style="width:130pt;height:213pt;font-size:80pt;background-color:#72cc85;color:white"  value="+">
 <br>
  <input type="BUTTON" name="btn" id="delrec"  onclick="alert('Please Select Point')" style="width:130pt;height:211pt;font-size:80pt;background-color:#bf6900;color:white"  value="-">
<%}%>
 </td></tr></table>
   <input type="HIDDEN" name="emp_code" value="<%=emp_code%>">
  <%
   try {

                if(result!=null){
                        result.close();}
                     if(result1!=null){
                        result1.close();}

                    if(stat!= null){
                        stat.close();}

                    if(stat1!=null){
                    stat1.close();
                    }

                    if(conn != null){
                        conn.close();
                        }
                    stat= null;
                    stat1= null;
                    result = null;
                    result1=null;
                    conn = null;

                } catch(Exception e) {

                    e.printStackTrace();

                }
                %>
  </form>
  </body>
</html>
