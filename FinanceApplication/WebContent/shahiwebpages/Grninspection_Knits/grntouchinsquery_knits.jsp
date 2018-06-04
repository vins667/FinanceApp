<%-- 
    Document   : grntouchinsquery_knits
    Created on : Oct 18, 2011, 2:58:22 PM
    Author     : Shyamal
--%>

<%@page import="shahi.Action.database.ConnectionShahiHris"%>
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
      <LINK href="css/style1.css" rel="stylesheet"	type="text/css">
       <script language="javascript">

top.window.moveTo(0,0);
if (document.all) {
top.window.resizeTo(screen.availWidth,screen.availHeight);
}
else if (document.layers||document.getElementById) {
if (top.window.outerHeight<screen.availHeight||top.window.outerWidth<screen.availWidth){
top.window.outerHeight = screen.availHeight;
top.window.outerWidth = screen.availWidth;
}
}
</script>
    <style>

    </style>
    <script language="javascript">
    var MAXLENGTH=10;
    var Current="";
    function AddDigit(dig)
      {
     inputobg=document.welcome.inputobg.value
     Current=Current+dig;
     document.forms['welcome'].elements[inputobg].value=Current;
    }

function clear1()
{
 inputobg=document.welcome.inputobg.value
 document.forms['welcome'].elements[inputobg].value="";
 Current="";
}

function selectobj(a)
{
Current="";
document.welcome.inputobg.value=a;

}

   function getempname()
    {
         acc=document.welcome.emp_code.value;
        if(document.welcome.emp_code.value!="")
        {

        if(!document.getElementById(acc))
        {
         alert("Employee Code Is Not Valid");
         document.welcome.emp_code.value="";
         document.welcome.emp_code.focus();
          return false;
        }else{


          document.welcome.emp_name.value=document.getElementById(acc+'N').value

        }


        }
  }

    function blk()
    {
         acc=document.welcome.emp_code.value;
         if(document.welcome.emp_code.value=="")
        {
        alert("Please  Enter Employee Code")

        document.welcome.emp_code.focus();
        return false;
        }

        if(!document.getElementById(acc))
        {
         alert("Employee Code Is Not Valid");
         document.welcome.emp_code.value="";
         document.welcome.emp_code.focus();
          return false;
        }
         if(document.welcome.emp_pass.value=="")
        {
        alert("Please  Enter Password")
         document.welcome.emp_pass.value="";
        return false;
        }else{



        if(document.getElementById(acc).value!=document.welcome.emp_pass.value)
       {
       alert("Password Is Not Valid");
       document.welcome.emp_pass.value="";
       document.welcome.emp_pass.focus();
       return false;

       }

        }



    return true;
    }

 function submitform()
 {
 if(blk()==true)
 {
document.welcome.action="Grnno_knits.jsp";
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


function appcmd()
 {

  var wshShell;

  wshShell = new ActiveXObject("WScript.Shell");
  aa="c:\\windows\\system32\\Shutdown.exe -s -t 0";
  wshShell.Run(aa);
  aa="c:\\windows\\system32\\Shutdown.exe -L -t 0";
  wshShell.Run(aa);


 }


   function closewin()
   {
   if(confirm('Do You Want to Shut Down Machine ?'))
  {
  appcmd();
  }else{
  return;
 }
   }

 </script>

 <%
    Connection conn = null;
    ResultSet result=null;
    PreparedStatement stat=null;
    conn = new ConnectionShahiHris().getConnection();
    int falg=0;

    stat=conn.prepareStatement("select distinct a.LOCATION_CODE,a.UNIT_CODE,a.EMP_CODE,a.PASSWORD,c.full_name from "
            + " seplweb.grninspms@ibm.world@ibm a ,hrempmst c,hrempdtl d  where  a.emp_code=c.emp_code and c.emp_code=d.emp_code "
            + " and a.FLAG='A' and end_date is null");
    result=stat.executeQuery();
 %>
  </head>

  <body onload="document.welcome.emp_code.focus()" >
    <form name="welcome" method="POST"  onSubmit="return (blk()) ;" action="" >
    <%--<embed src="http://172.17.5.68/IntraNet/Grninspection/xml/printstk.pdf" width="500" height="600" />--%>
      <table bgcolor="#006699" border="0" cellpadding="4"   cellspacing="1"  width="100%">
    <tr >  <td class="label" style="font-size:15pt;font-weight:'normal'">Shahi Exports Pvt. Ltd.</td>
<td align="right">
           <input type="BUTTON"  style="font-size:20pt;width:100pt"  onclick="closewin()" name="btn" value="Close">
</td>
    </tr>
  </table>

   <table cellpadding="10">
     <tr><td class="label-1" style="font-size:20pt" align="right" >Enter Employee Code</td><td>
    <input class="label-2" type="TEXT" name="emp_code" style="font-size:25pt;width:350pt" onblur="getempname()"  onfocus="selectobj(this.name)"  onkeypress="return tabE(this,event)" >
     </td></tr>
     <tr>
     <td class="label-1" style="font-size:20pt" align="right" >Name</td>
     <td>
      <input class="label-2" type="TEXT" name="emp_name" readonly="readonly" style="font-size:25pt;width:350pt">
              </td></tr>
     <tr><td class="label-1" style="font-size:20pt" align="right">Password</td><td>
     <input class="label-2"  type="PASSWORD" name="emp_pass" onfocus="selectobj(this.name);getempname()"  style="font-size:25pt;width:350pt">
     </td></tr>
   <tr><td></td><td>

     <table align="center">

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

   <input type="HIDDEN" name="inputobg" value="emp_pass">
    </table>


   <%
    result=stat.executeQuery();
      while(result.next())
      {


     %>
    <input type="HIDDEN" id="<%=result.getString("EMP_CODE")%>" name="<%=result.getString("EMP_CODE")%>" value="<%=result.getString("PASSWORD")%>">
     <input type="HIDDEN" id="<%=result.getString("EMP_CODE")%>N" name="<%=result.getString("EMP_CODE")%>N" value="<%=result.getString("full_name")%>">

     <%}
      if (result != null) {
     result.close(); }
     if (stat != null) {
     stat.close();  }
     result = null;
     stat = null;
     conn.close();
    %>


   </td></tr>

    </table>

        <table cellpadding="5" cellspacing="1"><tr><td class="label-1">Machine IP</td><td class="label-1"><%=request.getRemoteAddr() %></td></tr></table>
    </form>
  </body>
</html>
