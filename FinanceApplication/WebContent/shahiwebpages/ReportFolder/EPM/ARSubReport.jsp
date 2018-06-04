<%--
    Document   : SubGeneralLedgerReport
    Created on : July 5, 2012, 5:37 PM
    Author     : Vivek
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="oracle.jdbc.driver.OracleDriver"%>
<%@ page import="shahi.Action.ReportFolder.EPM.service.PopulateListService"%>
<%@ page import="shahi.Action.ReportFolder.EPM.beans.Code"%>
<html>
  <head>
    <title>Shahi Export Pvt Ltd</title>
  <link href="../../style/style.css" rel="stylesheet" type="text/css"/>
  <script language="javascript">
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



  function submitfrm()
  {
  document.SubGeneralLedgerReport.action='ARSubReport.jsp'
  document.SubGeneralLedgerReport.submit();

  }
 function addOptions()
{
var toSelect_Length = parent.GeneralLedgerReport.existglcode.options.length;
 while(document.SubGeneralLedgerReport.availglcode.selectedIndex > -1 )
 {
    var index = document.SubGeneralLedgerReport.availglcode.selectedIndex;
    for(i=0;i<toSelect_Length; i++)
    {
    if(parent.GeneralLedgerReport.existglcode.options[i].value ==document.SubGeneralLedgerReport.availglcode.options[index].value)
    {
    alert(document.SubGeneralLedgerReport.availglcode.options[index].text +", " + " Already Exist !")
    return false;
    }
    }

    parent.GeneralLedgerReport.existglcode.options[toSelect_Length] = new Option(document.SubGeneralLedgerReport.availglcode.options[index].text);
    parent.GeneralLedgerReport.existglcode.options[toSelect_Length].value =document.SubGeneralLedgerReport.availglcode.options[index].value;
    document.SubGeneralLedgerReport.availglcode.options[index] = null;
    toSelect_Length++;
 }
}

  </script>
  </head>
  <body style="margin: 0px;background-color: white;">
  <form name="SubGeneralLedgerReport" method="POST" action="">

    <table >
        <tr>
      <td>
          <font style="font-size: 11px"><b>Keywords: </b><br/>
      Type one or more keywords separated by spaces.</font><br/>
      <INPUT NAME=regexp class="texts" style="text-transform: uppercase;margin-top:7pt;" onblur="submitfrm();" onkeypress="return tabE(this,event)"  >&nbsp;&nbsp;<input type="BUTTON" name="search" value="Search" class="submitbutton" onclick="submitfrm();"><br/>
      </td>
  </tr>
  <tr>
      <td align="right">
       <select name="availglcode"  multiple="multiple" style="width:320px;height:175px;margin-top:7pt;" class="textsselect" size="7" ondblclick="addOptions()">
        <%
          if(request.getParameter("regexp")!=null && request.getParameter("regexp").length()>0){
        	  PopulateListService epmutil = new PopulateListService();
            List<Code> glcoderesult = epmutil.getSupplier(request.getParameter("regexp").toUpperCase()+"%");
            for(Code code:glcoderesult){
        %>
            <option value="<%=code.getCode()%>"><%=code.getCode()%></option>
        <%
            }

          }
        %>
    </select>
        <a href="#" id="addButtonitemGrp" onclick="addOptions();" style="text-decoration:none;"><div style="width:80px; height:23px" class="whitesubmiticon">Insert&nbsp;<img src="../../images/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a>
   </td></tr>
   </table>
  </form>
  </body>  
</html>
