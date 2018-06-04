<%-- 
    Document   : Grnno
    Created on : Oct 18, 2011, 2:54:59 PM
    Author     : Shyamal
--%>

<%@page import="shahi.Action.database.ConnectionShahiHris"%>
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
  <%
  Connection conn = null;
   ResultSet result=null;
    String CATG_CODE=null;
    PreparedStatement stat=null;
    conn = new ConnectionShahiHris().getConnection();
    stat=conn.prepareStatement(" select distinct a.LOCATION_CODE,a.UNIT_CODE,a.EMP_CODE,a.PASSWORD,a.CATG_CODE,decode"
            + " (a.CATG_CODE, 'D', 'Defect Checker', 'S', 'Shade/Lot Checker') "
            + " CATG_DESC ,c.full_name from seplweb.grninspms@ibm.world@ibm a , hrempmst c  where a.emp_code=?  and  a.emp_code=c.emp_code");
    stat.setString(1,request.getParameter("emp_code"));
    result=stat.executeQuery();
    String EMPNAME1=null;
    if(result.next())
    {
    session.removeValue("CATG_CODE");
     session.putValue("LOCATION_CODE",result.getString("LOCATION_CODE"));
     //session.putValue("CATG_CODE",result.getString("CATG_CODE"));
     CATG_CODE=result.getString("CATG_CODE");
     session.putValue("EMPNAME",result.getString("full_name")+"("+request.getParameter("emp_code")+")");
     EMPNAME1=result.getString("full_name")+"("+request.getParameter("emp_code")+")";
    }
   result=stat.executeQuery();

 %>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
     <LINK href="css/style.css" rel="stylesheet"	type="text/css">
    <style>
     .btn
    {
    font-size:20.0pt;
    font-family:Arial Black;
    width:75pt;
    height:40pt;
    }
      .btn1
    {
    font-size:20.0pt;
    font-family:Times New Roman;
    width:112pt;
    height:30pt;
    font-weight:bold;
    text-align:center;
    }

       .btn
    {
    font-size:25.0pt;
    font-family:Arial Black;
    width:100pt;
    background-color: #f3f3ee;


    }

    .clr
    {
    font-size:20.0pt;
    font-family:Arial Black;
    width:104pt;
    height:40pt;
    }
    td{
     font-size:15.0pt;
    font-family:Arial Black;

    }
    .txt{

    font-size:12.0pt;
    font-family:Times New Roman;
    }


    </style>
    <script language="javascript">
    var MAXLENGTH=10;
    var Current="";
    function AddDigit(dig)
      {
    inputobg=document.welcome.inputobg.value
     Current=document.forms['welcome'].elements[inputobg].value;

     if(dig==".")
     {
     if(Current.indexOf(".")==-1)
     {
     Current=Current+dig;
     }
     }else{
     Current=Current+dig;
     }

     document.forms['welcome'].elements[inputobg].value=Current;
    }

function clear1()
{
 inputobg=document.welcome.inputobg.value
 document.forms['welcome'].elements[inputobg].value="";
}

function selectobj(a)
{
document.welcome.inputobg.value=a;
}
function backclear()
{
    inputobg=document.welcome.inputobg.value
    val=document.forms['welcome'].elements[inputobg].value;
    var min=(val.charCodeAt(val.length - 1) == 10) ? 2 : 1;
    document.forms['welcome'].elements[inputobg].value = val.substr(0, val.length - min);
}

    function blk()
    {
        if(document.welcome.grnno.value=="")
        {
        alert("Please  Enter GRN No")
        return false;
        }
    return true;
    }

 function submitform()
 {
 if(blk()==true)
 {
 a=document.welcome.Category.value;
 if(a=='D')
 {
document.welcome.action="grntouchgrndtls_knits.jsp";
}else{

document.welcome.action="grntouchgrnshadelot_knits.jsp";
}
document.welcome.submit();
 }
 }

 function tabE(obj,e){
    var e=(typeof event!='undefined')?window.event:e;// IE : Moz
    if(e.keyCode==13){
    var ele = document.forms[0].elements;
    for(var i=0;i<ele.length;i++){
    var q=(i==ele.length-1)?0:i+1;// if last element : if any other
    if(obj==ele[i]){ele[q].focus();break}
    }
    return false;
    }
    }
 </script>

  </head>

  <body onload="document.welcome.grnno.focus()" >

    <form name="welcome" method="POST"  onSubmit="return (blk()) ;" action="" >
    <table bgcolor="#006699" border="0" cellpadding="4"   cellspacing="1"  width="100%">
    <tr >
    <td class="label" style="font-size:15pt;font-weight:'normal'"><%=EMPNAME1%> - Shahi Exports Pvt. Ltd.</td>
    <td align="right">
            <input type="BUTTON"  style="font-size:20pt;width:100pt"  onclick="window.location.href='grntouchinsquery_knits.jsp'" name="btn" value="Back">
</td>
    </tr>
  </table>

   <table cellpadding="10">
    <tr><td class="label-1" style="font-size:20pt" align="right">Enter GRN #</td><td>

    <input type="TEXT" name="grnno" onfocus="selectobj(this.name)" onkeypress="return tabE(this,event)"  style="font-size:20pt;width:305pt" maxlength="10">

    </td></tr>
      <tr><td class="label-1" style="font-size:20pt" align="right">Category</td><td>
      <select name="Category" onfocus="selectobj(this.name)" onkeypress="return tabE(this,event)"  style="font-size:20pt;width:305pt">
      <%while(result.next()){%>
      <option value="<%=result.getString("CATG_CODE")%>"><%=result.getString("CATG_DESC")%></option>
      <%}%>

      </select>

    </td></tr>

   <tr><td></td><td>

     <table align="center">
   <tr>
    <!--<td><input type="BUTTON" name="0" value="." class="btn" onclick="AddDigit('.')"></td>-->
     <td colspan="3" ><input type="BUTTON" name="backspace"  value="Backspace" class="btn" style="width:305pt" onclick="backclear()" ></td>

     </tr>

    <tr>

    <td><input type="BUTTON" name="1" value="1" class="btn" onclick="AddDigit(1)"></td>
    <td><input type="BUTTON" name="2" value="2" class="btn" onclick="AddDigit(2)"></td>
     <td><input type="BUTTON" name="3" value="3" class="btn" onclick="AddDigit(3)"></td>

    </tr>
    <tr>


    <td><input type="BUTTON" name="4" value="4" class="btn" onclick="AddDigit(4)"></td>
    <td><input type="BUTTON" name="5" value="5" class="btn" onclick="AddDigit(5)"></td>
    <td><input type="BUTTON" name="6" value="6" class="btn" onclick="AddDigit(6)"></td>

    </tr>
    <tr>


    <td><input type="BUTTON" name="7" value="7" class="btn" onclick="AddDigit(7)"></td>
    <td><input type="BUTTON" name="8" value="8" class="btn" onclick="AddDigit(8)"></td>
    <td><input type="BUTTON" name="9" value="9" class="btn" onclick="AddDigit(9)"></td>

    </tr>

    <tr>
     <td ><input type="BUTTON" name="clear" style="background-color:red;" value="Clear" class="btn" onclick="clear1();" ></td>
     <td><input type="BUTTON" name="0" value="0" class="btn" onclick="AddDigit(0)"></td>

    <td ><input type="Button" value="Ok" style="background-color:#1e721e" onclick="submitform()" class="btn"></td>
    </tr>

   <input type="HIDDEN" name="inputobg" value="grnno">
    </table>



 <input type="HIDDEN" name="emp_code" value="<%=request.getParameter("emp_code")%>">


   </td></tr>

    </table>
      <table cellpadding="5" cellspacing="1"><tr><td class="label-1">Machine IP</td><td class="label-1"><%=request.getRemoteAddr() %></td></tr></table>

   <%
            try {

                if(result!=null){
                        result.close();}

                    if(stat!= null){
                        stat.close();}


                    if(conn != null){
                        conn.close();
                        }
                    stat= null;

                    result = null;

                    conn = null;

                } catch(Exception e) {

                    e.printStackTrace();

                }



%>
    </form>
  </body>
</html>

