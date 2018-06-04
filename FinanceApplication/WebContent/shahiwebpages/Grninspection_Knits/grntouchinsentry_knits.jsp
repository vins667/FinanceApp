<%-- 
    Document   : grntouchinsentry_knits
    Created on : Oct 18, 2011, 4:52:35 PM
    Author     : Shyamal
--%>
<%@ page import="shahi.Action.database.ConnectionSeplWeb"%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>
<html>
  <head>
  <%
  String CATG_CODE=(String)session.getValue("CATG_CODE");
  %>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
     <LINK href="css/style.css" rel="stylesheet"	type="text/css">

 <script language="javascript">

     var MAXLENGTH=10;
    var Current="";
    function AddDigit(dig)
      {
     inputobg=document.hris.inputobg.value
     Current=document.forms['hris'].elements[inputobg].value;

     if(dig==".")
     {
     if(Current.indexOf(".")==-1)
     {
     Current=Current+dig;
     }
     }else{
     Current=Current+dig;
     }

     document.forms['hris'].elements[inputobg].value=Current;
     addactqty();
    }

    function addactqty(){

    if(document.hris.NO_OF_FOLD && document.hris.NO_OF_FOLD.value!="" ){
    aval=document.hris.NO_OF_FOLD.value
    if(parseInt(aval)>0){
    document.hris.ACTUAL_QTY.value=document.hris.NO_OF_FOLD.value*document.hris.FOLD_LENGTH.value
    }
    }
    }

function clear1()
{
 inputobg=document.hris.inputobg.value
 document.forms['hris'].elements[inputobg].value="";

}

function selectobj(a)
{
//alert(a);
k=document.forms['hris'].elements[a].value;
if(parseInt(k)==0)
{
  if(a!="MAX_WIDTH" || a!="GSM_AW"){}else{
  document.forms['hris'].elements[a].value="";
  }
}
document.hris.inputobg.value=a;
}


function backclear()
{
    inputobg=document.hris.inputobg.value
    val=document.forms['hris'].elements[inputobg].value;
    var min=(val.charCodeAt(val.length - 1) == 10) ? 2 : 1;
    document.forms['hris'].elements[inputobg].value = val.substr(0, val.length - min);

}

function nextrecfocu()
{
    var ele = document.forms[0].elements;
    inputobg=document.hris.inputobg.value;
    obj=document.forms['hris'].elements[inputobg];
//alert(inputobg);    
    if(inputobg=='FABRIC_PH'){}else{
    for(var i=0;i<ele.length;i++)
    {
      var q=(i==ele.length-1)?0:i+1;// if last element : if any other
      if(obj==ele[i])
      {
        ele[q].focus();
        break
      }
    }
    }
}

    function tabE(obj,e){
//alert("backspace "+e.keyCode);
      var e=(typeof event!='undefined')?window.event:e;// IE : Moz
      if(e.keyCode==13)
      {
        var ele = document.forms[0].elements;
        for(var i=0;i<ele.length;i++)
        {
          var q=(i==ele.length-1)?0:i+1;// if last element : if any other
          if(obj==ele[i])
          {
            ele[q].focus();
            break
          }
        }
        return false;
      }
    }
   


 function validatenumber(a)
 {
   if (!a.value=="" &&!a.value.match(/^\d+$|^\d+\.\d{1,3}$/ ) )
        {
          alert('Invalid Input, Only Numbers Allowed');
          a.value="";
          a.focus();
          return false;

        }
}

 function validatenumber1(a)
{
   if (!a.value=="" &&!a.value.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
    {
      alert('Invalid Input, Only Numbers Allowed');
      a.value="";
      a.focus();
      return false;
    }
}

 function saverecord()
 {
     if(validaterec()==true)
     { 
         document.hris.action="grntouchinsentryjava-Knits.jsp"
         document.hris.submit();
     }
 }

    function validaterec()
    {
     <%if (CATG_CODE.equals("D")) {%>
        if(document.hris.ROLL_QTY.value=="")
        {
            alert("Please Enter Roll Qty !!");
            document.hris.ROLL_QTY.focus()
            return false;
        }
        if(document.hris.ACTUAL_QTY.value=="" || parseInt(document.hris.ACTUAL_QTY.value)==0)
        {
            document.hris.ACTUAL_QTY.value==""
            alert("Please Enter Actual Roll Qty !!");
            document.hris.ACTUAL_QTY.focus()
            return false;
        }
        if(document.hris.MIN_WIDTH.value==""  || parseInt(document.hris.MIN_WIDTH.value)==0)
        {
            document.hris.MIN_WIDTH.value==""
            alert("Please Enter Dia B/Wash !!");
            document.hris.MIN_WIDTH.focus()
            return false;
        }
      /*  if(document.hris.MAX_WIDTH.value==""  || parseInt(document.hris.MAX_WIDTH.value)==0)
        {
            document.hris.MAX_WIDTH.value
            alert("Please Enter Dia A/Wash !!");
            document.hris.MAX_WIDTH.focus()
            return false;
        }*/
        a=document.hris.MIN_WIDTH.value;
        b=document.hris.MAX_WIDTH.value;
       /* if(parseInt(a)>parseInt(b))
        {
            alert("Invalid Dia B & A/Wash !!");
            document.hris.MAX_WIDTH.focus()
            return false;
        }*/
     <%} else {%>
        if(document.hris.SHADE_LOT.value=="")
        {
            alert("Please Enter Shade Lot !!");
            document.hris.SHADE_LOT.focus()
            return false;
        }
        if(document.hris.SHADE_GRP.value=="")
        {
            alert("Please Select Shade Group !!");
            document.hris.SHADE_GRP.focus()
            return false;
        }
     <%}%>
        return true;
    }   
 

 </script>
    <style>
    text{
    font-size:18pt;
    width:200pt;
   text-transform: uppercase
    }
    td{
   font-size:15pt;
    }
      .btn
    {
    font-size:25.0pt;
    font-family:Arial Black;
    width:100pt;
    background-color: #f3f3ee;
    }

    </style>
     
  </head>
  <%
        PreparedStatement        stat,stat2,stat1     = null;
         ResultSet result,result2,result1=null;
        String grnno=request.getParameter("grnno");
        String ROLL_NO=request.getParameter("ROLL_NO");
        String emp_code=request.getParameter("emp_code");
        Connection conn = null;
        ResourceBundle aResourcBundle = null;
        String myusrid = (String)session.getValue("myusrid");
        String location = (String)session.getValue("LOCATION_CODE");
        String EMPNAME= (String)session.getValue("EMPNAME");
         conn=new ConnectionSeplWeb().getConnection();
       stat=conn.prepareStatement("select  LOCATION_CODE,RECEIPT_NO,SUPPLIER_CODE,SUPPLIER_NAME,BUYER_NAME,ITEM_CODE,ITEM_DESC,PO_NO,GRN_QTY,ROLL_NO,nvl(ROLL_QTY,0) ROLL_QTY,nvl(WIDTH,' ') WIDTH,nvl(ACTUAL_WIDTH,0) ACTUAL_WIDTH,REPORT_NO,COLOR,SHADE_LOT,BOWING,COUNT_CONST,GMT_AVG,INSPECTOR,INSPECTION_DATE,CS,LWV,TDATE,TTIME,USER_ID,SL_NO,CF_WASH,DRY_CROCK,WASH_CROCK ,SHADE_GRP,nvl(MIN_WIDTH,0) MIN_WIDTH,nvl(MAX_WIDTH,0) MAX_WIDTH,nvl(ACTUAL_QTY,0) ACTUAL_QTY,SHRK_PRCT , "+
       " to_char(nvl(end_date,CLOSE_DATE),'dd/mm/yyyy') CLOSE_DATE,INSPECTOR_S ,nvl(INVOICE_NO,'')  INVOICE_NO,nvl(to_char(START_DATE,'dd/mm/yyyy'),' ') START_DATE, "+
       " nvl(to_char(END_DATE,'dd/mm/yyyy'),' ') END_DATE,START_TIME,END_TIME,CUT_PCS,FOLD_LENGTH,ROLL_BOWING,FABRIC_FORM,nvl(EPI,0) EPI,nvl(PPI,0) PPI,round(NO_OF_FOLD,2) NO_OF_FOLD,LAY_TO_LAY,BODY_TO_TRIMS,HAND_FEEL,MATAMARISAM,FABRIC_PH,nvl(GSM_BW,0) GSM_BW,nvl(GSM_AW,0) GSM_AW,nvl(PROCESS_ROUTE,' ') PROCESS_ROUTE "+
       " from grninsdt where location_code=? and RECEIPT_NO=? and ROLL_NO=? and (ROLL_QTY is not null or SHADE_LOT is not null and nvl(inspection_type,'W')='K' )");
       stat.setString(1,location);
       stat.setString(2,grnno);
       stat.setString(3,ROLL_NO);
       result=stat.executeQuery();
       String group[] = {"A","B","C","D","E","F","G","H","I","J"};
       String disvalue="";
       String disvalue1="";
       if(CATG_CODE.equals("D"))
       {
       disvalue1="disabled='disabled'";
       disvalue="";
       }else{
       disvalue1="";
       disvalue="disabled='disabled'";
       }
  %>
  <body >
  <form name="hris" method="post" action="">
  <table bgcolor="#006699" border="0" cellpadding="0"   cellspacing="1"  width="100%">
<tr >
<!--<td class="label" style="font-size:15pt;font-family:Arial Black;font-weight:'normal'">Roll No:<%=ROLL_NO%></td>-->
 <td class="label" style="font-size:15pt;font-family:Arial Black;font-weight:'normal'"><%=EMPNAME%> - Shahi Exports Pvt. Ltd.</td>

<td align="right">
            <%
    int closeflag=0;
    if(result.next()){
    if(result.getString("CLOSE_DATE")!=null)
    {
    closeflag=1;
    }
    }
    if(closeflag==1){%>
     <input type="BUTTON"  disabled="disabled" style="font-size:20pt;width:100pt" name="btn" value="Save">
    <%}else{%>
   <input type="BUTTON"  onclick="saverecord();" style="font-size:20pt;width:100pt" name="btn" value="Save">
  <%}%>
   <input type="BUTTON"  style="font-size:20pt;width:100pt"  onclick="window.location.href='grntouchgrndtls_knits.jsp?grnno=<%=request.getParameter("grnno")%>&emp_code=<%=emp_code%>&srollno=<%=ROLL_NO%>'" name="btn" value="Back">

</td>
    </tr>
  </table>
  <table border="0" cellpadding="0" cellspacing="0">
    <tr>
     <td valign="top">
  <table cellpadding="0">
  <%
  String ltl=""; String btt=""; String hf=""; String fp=""; String mmrsm="";
  result=stat.executeQuery();
  String readonly1="disabled=''";
  if(result.next())
  {
      if(result.getString("LAY_TO_LAY")!=null)
      {ltl=result.getString("LAY_TO_LAY"); }
      if(result.getString("BODY_TO_TRIMS")!=null)
      {btt=result.getString("BODY_TO_TRIMS"); }
      if(result.getString("HAND_FEEL")!=null)
      {hf=result.getString("HAND_FEEL"); }
      if(result.getString("FABRIC_PH")!=null)
      {fp=result.getString("FABRIC_PH"); }
      if(result.getString("MATAMARISAM")!=null)
      {mmrsm=result.getString("MATAMARISAM"); }

    if(result.getString("FABRIC_FORM")!=null && result.getString("FABRIC_FORM").equals("Roll"))
    {
    readonly1="disabled='disabled'";
    }        
    String rdonly=""; String clr="black"; 
    if(result.getString("PROCESS_ROUTE").trim()!=null && result.getString("PROCESS_ROUTE").trim().equals("Direct Cut"))
    { rdonly="readonly='readonly'"; 
      clr="#aca899"; 
    } 
     
  if(result.getString("ROLL_QTY")==null)
  { %>
  <tr><td class="label-1" style="font-size:15pt;" align="right">Roll No</td>
      <td><input type="TEXT" style="font-size:20pt;width:177pt" value="<%=ROLL_NO%>" readonly="readonly" name="ROLL_NO1" <%=disvalue%>></td>
  </tr>
   <tr><td class="label-1" style="font-size:15pt;" align="right">Po Roll Qty</td>
       <td><input type="TEXT" style="font-size:20pt;width:177pt" readonly="readonly" onblur="validatenumber(this)"   name="ROLL_QTY" <%=disvalue%>></td>
   </tr>
  <tr><td class="label-1"style="font-size:15pt;" align="right" >Po Roll Width</td>
      <td><input type="TEXT" style="font-size:20pt;width:177pt" readonly="readonly" onblur="validatenumber1(this)" name="ROWIDTH"  <%=disvalue%>></td>
  </tr>
  <tr>
      <td class="label-1" style="font-size:15pt;"  colspan="2"><hr class="label-1" style="margin:0pt;padding:0pt"></td>
  </tr>

  <%if(result.getString("FABRIC_FORM")!=null && result.getString("FABRIC_FORM").equals("Than")){%>
  <tr><td class="label-1" style="font-size:15pt;" align="right" >N0 Of Fold</td>
      <td><input  type="TEXT" style="font-size:20pt;width:177pt" onkeyup="document.hris.ACTUAL_QTY.value=this.value*document.hris.FOLD_LENGTH.value" onblur="validatenumber(this)" onfocus="selectobj(this.name)" name="NO_OF_FOLD"></td>
  </tr>
    <%}%>
  <tr><td class="label-1" style="font-size:15pt;" align="right" >Actual Roll Qty</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt"  onblur="validatenumber(this)" onfocus="selectobj(this.name)" name="ACTUAL_QTY"></td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right">Dia B/Wash</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt"  onblur="validatenumber1(this)" name="MIN_WIDTH" onfocus="selectobj(this.name)"></td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right">Dia A/Wash</td>
     <td> 
      <input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt;color:<%=clr%>" onblur="validatenumber1(this)" <%=rdonly%> name="MAX_WIDTH" onfocus="selectobj(this.name)" >
     </td>
  </tr>
  
  <tr><td class="label-1" style="font-size:15pt;" align="right">GSM B/Wash</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt"  onblur="validatenumber1(this)" name="GSM_BW" onfocus="selectobj(this.name)"></td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right">GSM A/Wash</td>
     <td>
     <input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt;color:<%=clr%>" onblur="validatenumber1(this)" <%=rdonly%> name="GSM_AW" onfocus="selectobj(this.name)">
     
     </td>
  </tr>
  
<!--tr><td class="label-1" style="font-size:15pt;"  align="right">Cut Pcs</td-->
     <input type="hidden" style="font-size:20pt;width:177pt"  <%=readonly1%>    name="CUT_PCS" onfocus="selectobj(this.name)">
<!--/tr-->
  <!--tr><td class="label-1"  style="font-size:15pt;" align="right">Bowing</td-->
      <input type="hidden" style="font-size:20pt;width:177pt"  <%=readonly1%>    name="ROLL_BOWING" onfocus="selectobj(this.name)">
  <!--/tr-->
  <!--tr><td class="label-1" style="font-size:15pt;"  align="right">Fold Length</td-->
      <input type="hidden" style="font-size:20pt;width:177pt" <%=readonly1%>  onblur="validatenumber(this)"    name="FOLD_LENGTH" onfocus="selectobj(this.name)">
  <!--/tr-->
<!--tr><td class="label-1" style="font-size:15pt;"  align="right">EPI</td-->
    <input type="hidden" style="font-size:20pt;width:177pt"  onblur="validatenumber(this)"    name="EPI" onfocus="selectobj(this.name)">
<!--/tr-->
<!--tr><td class="label-1" style="font-size:15pt;"  align="right">PPI</td-->
    <input type="hidden" style="font-size:20pt;width:177pt"  onblur="validatenumber(this)"    name="PPI" onfocus="selectobj(this.name)">
<!--/tr-->
<%}else{
  %>
  <tr><td class="label-1" style="font-size:15pt;" align="right"  >Roll No</td>
      <td><input   type="TEXT" style="font-size:20pt;width:177pt" value="<%=ROLL_NO%>" readonly="readonly"     name="ROLL_NO1" <%=disvalue%>></td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right"  >Po Roll Qty</td>
      <td><input   type="TEXT" style="font-size:20pt;width:177pt" readonly="readonly" value="<%=result.getString("ROLL_QTY")%>"    name="ROLL_QTY" <%=disvalue%>></td>
  </tr>
  <tr><td class="label-1"style="font-size:15pt;" align="right" >Po Roll Width</td>
      <td><input  type="TEXT" style="font-size:20pt;width:177pt" readonly="readonly" value="<%=result.getString("WIDTH").trim()%>"  name="ROWIDTH"  <%=disvalue%>></td>
  </tr>
  <tr>
      <td class="label-1" style="font-size:15pt;"  colspan="2"><hr class="label-1" style="margin:0pt;padding:0pt"></td>
  </tr>
 <%if(result.getString("FABRIC_FORM")!=null && result.getString("FABRIC_FORM").equals("Than")){
 if(result.getString("NO_OF_FOLD")!=null){
 %>
  <tr><td class="label-1" style="font-size:15pt;" align="right" >N0 Of Fold</td>
      <td><input  type="TEXT" style="font-size:20pt;width:177pt" onkeyup="document.hris.ACTUAL_QTY.value=this.value*document.hris.FOLD_LENGTH.value"  value="<%=result.getString("NO_OF_FOLD")%>" onblur="validatenumber(this)" onfocus="selectobj(this.name)" name="NO_OF_FOLD"></td>
  </tr>
    <%}else{%>
    <tr><td class="label-1" style="font-size:15pt;" align="right" >N0 Of Fold</td>
        <td><input  type="TEXT" style="font-size:20pt;width:177pt"  onkeyup="document.hris.ACTUAL_QTY.value=this.value*document.hris.FOLD_LENGTH.value" value="" onblur="validatenumber(this)" onfocus="selectobj(this.name)" name="NO_OF_FOLD"></td>
    </tr>
    <%}}%>
  <tr><td class="label-1" style="font-size:15pt;" align="right" >Actual Roll Qty</td>
      <td>
<% if (result.getString("ACTUAL_QTY")!=null && result.getString("ACTUAL_QTY").length()>0 && !result.getString("ACTUAL_QTY").equals("0")){%>

       <input disabled='true' type="TEXT" style="font-size:20pt;width:177pt"  value="<%=result.getString("ACTUAL_QTY")%>" onblur="validatenumber(this)"   name="ACTUAL_QTY">
	<input  type="hidden" style="font-size:20pt;width:177pt"  value="<%=result.getString("ACTUAL_QTY")%>" onblur="validatenumber(this)"   name="ACTUAL_QTY">
 <%}else{%>
 <input disabled='true' type="TEXT" style="font-size:20pt;width:177pt"  value="<%=result.getString("ROLL_QTY")%>" onblur="validatenumber(this)"   name="ACTUAL_QTY">
	<input  type="hidden" style="font-size:20pt;width:177pt"  value="<%=result.getString("ROLL_QTY")%>" onblur="validatenumber(this)"   name="ACTUAL_QTY">

<%}%>
</td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right">Dia B/Wash</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" value="<%=result.getString("MIN_WIDTH")%>" onblur="validatenumber1(this)" name="MIN_WIDTH" onfocus="selectobj(this.name)"></td>
  </tr>
 <tr><td class="label-1" style="font-size:15pt;" align="right">Dia A/Wash</td>
     <td>     
     <input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt;color:<%=clr%>" value="<%=result.getString("MAX_WIDTH")%>" <%=rdonly%>  onblur="validatenumber1(this)" name="MAX_WIDTH" onfocus="selectobj(this.name)">
        
     </td>
 </tr> 
 
  <tr><td class="label-1" style="font-size:15pt;" align="right">GSM B/Wash</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" value="<%=result.getString("GSM_BW")%>"  onblur="validatenumber1(this)" name="GSM_BW" onfocus="selectobj(this.name)"></td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right">GSM A/Wash</td>
     <td> 
     <input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt;color:<%=clr%>" value="<%=result.getString("GSM_AW")%>" <%=rdonly%> onblur="validatenumber1(this)" name="GSM_AW" onfocus="selectobj(this.name)">
    
     </td>
  </tr>
  
 <%if(result.getString("CUT_PCS")==null){%>
 <input type="hidden" style="font-size:20pt;width:177pt" name="CUT_PCS" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" style="font-size:20pt;width:177pt;" value="<%=result.getString("CUT_PCS")%>"  name="CUT_PCS" onfocus="selectobj(this.name)">
 <%}%>
</tr>
  <!--tr><td class="label-1" style="font-size:15pt;"  align="right">Bowing</td>
      <td-->
 <%
 if(result.getString("ROLL_BOWING")==null){%>
 <input type="hidden" style="font-size:20pt;width:177pt" <%=readonly1%>   name="ROLL_BOWING" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" style="font-size:20pt;width:177pt"  <%=readonly1%> value="<%=result.getString("ROLL_BOWING")%>"  name="ROLL_BOWING" onfocus="selectobj(this.name)">
 <%}%>
 <!--/td>
  </tr-->

 <%if(result.getString("FOLD_LENGTH")==null){%>
 <input type="hidden" style="font-size:20pt;width:177pt" onblur="validatenumber(this)"    name="FOLD_LENGTH" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" style="font-size:20pt;width:177pt" onblur="validatenumber(this)"  value="<%=result.getString("FOLD_LENGTH")%>"  name="FOLD_LENGTH" onfocus="selectobj(this.name)">
 <%}%>
 <input type="hidden" value="<%=result.getString("EPI")%>" style="font-size:20pt;width:177pt"  onblur="validatenumber(this)"    name="EPI" onfocus="selectobj(this.name)">
 <input type="hidden" value="<%=result.getString("PPI")%>" style="font-size:20pt;width:177pt"  onblur="validatenumber(this)"    name="PPI" onfocus="selectobj(this.name)">
   <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Lay to Lay</td>
      <td>
          <select name="LAY_TO_LAY" style="font-size:20pt;width:177pt">
           <%if(ltl!=null && ltl.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(ltl!=null && ltl.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
   </tr>
   <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Body to Trims</td>
      <td>
          <select name="BODY_TO_TRIMS" style="font-size:20pt;width:177pt">
           <%if(btt!=null && btt.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(btt!=null && btt.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
     </tr>
     <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Hand feel</td>
      <td>
          <select name="HAND_FEEL" style="font-size:20pt;width:177pt">
           <%if(hf!=null && hf.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(hf!=null && hf.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
     </tr>
     <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Matamarisam</td>
      <td> 
          <select name="MATAMARISAM" style="font-size:20pt;width:177pt">
           <%if(mmrsm!=null && mmrsm.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(mmrsm!=null && mmrsm.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
     </tr>
     <!--tr>
     <td class="label-1" style="font-size:15pt;" align="right">Fabric pH</td>
     <td-->
     <input type="hidden" style="font-size:20pt;width:177pt" name="FABRIC_PH" value="<%=fp%>" readonly="readonly">
     <!--/td>
     </tr-->


<%
}
%>
<tr><td class="label-1" style="font-size:15pt;"  colspan="2"><hr class="label-1" style="margin:0pt;padding:0pt"></td>
</tr>
 <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Shade Lot</td>
 <td class="label-1" style="font-size:15pt;" valign="top">
 <table cellpadding="0" cellspacing="0">
     <tr><td>
 <%if(result.getString("SHADE_LOT")==null){
 %>
 <input <%=disvalue1%> type="TEXT" style="font-size:20pt;width:70pt" maxlength="10"  name="SHADE_LOT"  onfocus="selectobj(this.name)">
<%
 }else{%>
 <input <%=disvalue1%> type="TEXT" style="font-size:20pt;width:70pt" maxlength="10" value="<%=result.getString("SHADE_LOT")%>" name="SHADE_LOT"  onfocus="selectobj(this.name)">
<%}%>
</td><td class="label-1" style="font-size:15pt;">Group
 </td>
 <td>
     <select name="SHADE_GRP" <%=disvalue1%> style="font-size:20pt;width:62pt">
  <option value="">GRP</option>
 <%for(int i=0; i<group.length; i++ ){
 if(group[i].equals(result.getString("SHADE_GRP")))
 {%>
 <option selected="selected" value="<%=group[i]%>"><%=group[i]%></option>
 <%}else{
 %>
 <option value="<%=group[i]%>"><%=group[i]%></option>
 <%}}%>
 </select>
 </td></tr></table>
 </td>
 </tr>
 <tr>
     <td class="label-1" style="font-size:15pt;" align="right">CS</td>
     <td><select name="CS" style="font-size:20pt;width:177pt" <%=disvalue1%>>
 <%if(result.getString("CS")!=null && result.getString("CS").equals("No CS")){%>
 <option value="No CS">No CS</option>
 <option value="Critical">Critical</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 <%}else if(result.getString("CS")!=null && result.getString("CS").equals("Critical")){%>
 <option value="Critical">Critical</option>
 <option value="No CS">No CS</option>
  <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 <%}else if(result.getString("CS")!=null && result.getString("CS").equals("Major")){%>
 <option value="Major">Major</option>
 <option value="Critical">Critical</option>
 <option value="No CS">No CS</option>
  <option value="Minor">Minor</option>
 <%}else if(result.getString("CS")!=null && result.getString("CS").equals("Minor")){%>
 <option value="Minor">Minor</option>
 <option value="Major">Major</option>
 <option value="Critical">Critical</option>
 <option value="No CS">No CS</option>
 <%}else{%>
  <option value="No CS">No CS</option>
 <option value="Critical">Critical</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 <%}%>
 </select>
 </td>
 </tr>
 <tr>
     <td class="label-1" style="font-size:15pt;" align="right">LWV</td>
     <td>
 <select name="LWV" style="font-size:20pt;width:177pt" <%=disvalue1%>>
  <%if(result.getString("LWV")!=null && result.getString("LWV").equals("No LWV")){%>
 <option value="No LWV">No LWV</option>
 <option value="Critical">Critical</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 <%}else if(result.getString("LWV")!=null && result.getString("LWV").equals("Critical")){%>
  <option value="Critical">Critical</option>
 <option value="No LWV">No LWV</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 <%}else if(result.getString("LWV")!=null && result.getString("LWV").equals("Major")){%>
 <option value="Major">Major</option>
 <option value="Critical">Critical</option>
 <option value="No LWV">No LWV</option>
  <option value="Minor">Minor</option>
 <%}else if(result.getString("LWV")!=null && result.getString("LWV").equals("Minor")){%>
   <option value="Minor">Minor</option>
 <option value="Major">Major</option>
 <option value="Critical">Critical</option>
 <option value="No LWV">No LWV</option>
 <%}else{%>
   <option value="Minor">Minor</option>
 <option value="Major">Major</option>
 <option value="Critical">Critical</option>
 <option value="No LWV">No LWV</option>
 <%}%>
 </select>
 </td>
 </tr>

  <%}

  else
   {%>
   <tr>
       <td class="label-1" style="font-size:15pt;" align="right"  >Roll No</td>
       <td><input   type="TEXT" style="font-size:20pt;width:177pt" value="<%=ROLL_NO%>" readonly="readonly"     name="ROLL_NO1" <%=disvalue%>></td>
   </tr>
   <tr>
       <td class="label-1" style="font-size:15pt;" align="right" >Po Roll Qty</td>
       <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" readonly="readonly"  name="ROLL_QTY"  ></td>
   </tr>
  <tr><td class="label-1"style="font-size:15pt;" align="right" >Po Roll Width</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" readonly="readonly"  name="ROWIDTH" onblur="validatenumber1(this)" ></td>
  </tr>
 <tr><td class="label-1" style="font-size:15pt;"  colspan="2"><hr class="label-1" style="margin:0pt;padding:0pt"></td>
 </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right" >Actual ROll Qty</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" onblur="validatenumber(this)"  name="ACTUAL_QTY" onfocus="selectobj(this.name)"></td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right">Dia B/Wash</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" onblur="validatenumber1(this)"  name="MIN_WIDTH" onfocus="selectobj(this.name)"></td>
  </tr>
 <tr><td class="label-1" style="font-size:15pt;" align="right">Dia A/Wash</td>
     <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" value="0" onblur="validatenumber1(this)"  name="MAX_WIDTH" onfocus="selectobj(this.name)"></td>
 </tr>
 
  <tr><td class="label-1" style="font-size:15pt;" align="right">GSM B/Wash</td>
      <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt" onblur="validatenumber1(this)" name="GSM_BW" onfocus="selectobj(this.name)"></td>
  </tr>
  <tr><td class="label-1" style="font-size:15pt;" align="right">GSM A/Wash</td>
     <td><input <%=disvalue%> type="TEXT" style="font-size:20pt;width:177pt"  value="0"  onblur="validatenumber1(this)" name="GSM_AW" onfocus="selectobj(this.name)"></td>
  </tr>
  
   <input type="hidden" style="font-size:20pt;width:177pt" name="CUT_PCS" onfocus="selectobj(this.name)"></td>

  <!--tr><td class="label-1"  style="font-size:15pt;" align="right">Bowing</td-->
      <input type="hidden" style="font-size:20pt;width:177pt"    name="ROLL_BOWING" onfocus="selectobj(this.name)">
  <!--/tr-->
   <input type="hidden" style="font-size:20pt;width:177pt" onblur="validatenumber(this)" name="FOLD_LENGTH" onfocus="selectobj(this.name)">   
    <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Lay to Lay</td>
      <td>
          <select name="LAY_TO_LAY" style="font-size:20pt;width:177pt">
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
          </select>
      </td>
   </tr>
   <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Body to Trims</td>
      <td>
          <select name="BODY_TO_TRIMS" style="font-size:20pt;width:177pt">
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
          </select>
      </td>
     </tr>
     <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Hand feel</td>
      <td>
          <select name="HAND_FEEL" style="font-size:20pt;width:177pt">
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
          </select>
      </td>
     </tr>     
     <td class="label-1" style="font-size:15pt;" align="right">Matamarisam</td>
      <td> 
          <select name="MATAMARISAM" style="font-size:20pt;width:177pt"> 
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option> 
          </select>
      </td>
     </tr>
     <!--tr>
     <td class="label-1" style="font-size:15pt;" align="right">Fabric pH</td>
     <td-->
     <input  type="hidden" style="font-size:20pt;width:177pt" name="FABRIC_PH" readonly="readonly"> 
     <!--/table></tr-->


  <tr><td class="label-1" style="font-size:15pt;"  colspan="2"><hr class="label-1" style="margin:0pt;padding:0pt"></td>
  </tr>
 <tr><td class="label-1" style="font-size:15pt;" align="right">Shade Lot</td>
 <td class="label-1" style="font-size:15pt;">
 <table cellpadding="0" cellspacing="0"><tr><td>
 <input  type="TEXT" maxlength="10" <%=disvalue1%> style="font-size:20pt;width:70pt"   name="SHADE_LOT" onfocus="selectobj(this.name)">
</td><td class="label-1" style="font-size:15pt;">
 &nbsp;Group
 </td><td>
&nbsp;
 <select name="SHADE_GRP" style="font-size:20pt;width:62pt" <%=disvalue1%>>
 <option value="">GRP</option>
 <%for(int i=0; i<group.length; i++ ){ %>
 <option value="<%=group[i]%>"><%=group[i]%></option>
 <%}%>
 </select>
 </td></tr>
 </table>
 </td>
 </tr>
 <tr><td class="label-1" style="font-size:15pt;" align="right">CS</td><td>
 <select name="CS" style="font-size:20pt;width:177pt" <%=disvalue1%>>
 <option value="No CS">No CS</option>
 <option value="Critical">Critical</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 </select>
 </td>
 </tr>
 <tr><td class="label-1" style="font-size:15pt;" align="right">LWV</td><td>
 <select name="LWV"  style="font-size:20pt;width:177pt" <%=disvalue1%>>
 <option value="No LWV">No LWV</option>
 <option value="Critical">Critical</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 </select>
 </td>
 </tr>
  <%}

  if(grnno!=null){%>
  <input type="HIDDEN" name="grnno" value="<%=grnno%>">
  <%}
  if(ROLL_NO!=null)
  {%>
   <input type="HIDDEN" name="ROLL_NO" value="<%=ROLL_NO%>">
   <input type="HIDDEN" name="emp_code" value="<%=emp_code%>">
  <%}%>
  </table>

  <%
 /* String ltl=""; String btt=""; String hf=""; String fp=""; String mmrsm="";
  result=stat.executeQuery();
  if(result.next())
  {
      if(result.getString("LAY_TO_LAY")!=null)
      {ltl=result.getString("LAY_TO_LAY"); }
      if(result.getString("BODY_TO_TRIMS")!=null)
      {btt=result.getString("BODY_TO_TRIMS"); }
      if(result.getString("HAND_FEEL")!=null)
      {hf=result.getString("HAND_FEEL"); }
      if(result.getString("FABRIC_PH")!=null)
      {fp=result.getString("FABRIC_PH"); }
      if(result.getString("MATAMARISAM")!=null)
      {mmrsm=result.getString("MATAMARISAM"); }
      
  }
  */
%>
  <!--td valign="top">
    <table>
        <tr><td height="123pt"></td></tr>
        <tr><td class="label-1" style="font-size:15pt;" colspan="2">
                <hr class="label-1" style="margin:0pt;padding:0pt">
            </td>
        </tr>
   <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Lay to Lay</td>
      <td>
          <select name="LAY_TO_LAY" style="font-size:20pt;width:90pt">
           <%if(ltl!=null && ltl.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(ltl!=null && ltl.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
   </tr>
   <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Body to Trims</td>
      <td>
          <select name="BODY_TO_TRIMS" style="font-size:20pt;width:90pt">
           <%if(btt!=null && btt.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(btt!=null && btt.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
     </tr>
     <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Hand feel</td>
      <td>
          <select name="HAND_FEEL" style="font-size:20pt;width:90pt">
           <%if(hf!=null && hf.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(hf!=null && hf.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
     </tr>     
     <tr>
     <td class="label-1" style="font-size:15pt;" align="right">Matamarisam</td>
      <td> 
          <select name="MATAMARISAM" style="font-size:20pt;width:177pt">
           <%if(mmrsm!=null && mmrsm.equals("Accept")){%>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <option value="">Select</option>
             <%}else if(mmrsm!=null && mmrsm.equals("Reject")){%>
             <option value="Reject">Reject</option>
             <option value="Accept">Accept</option>
             <option value="">Select</option>
             <%}else{%>
             <option value="">Select</option>
             <option value="Accept">Accept</option>
             <option value="Reject">Reject</option>
             <%}%>
          </select>
      </td>
     </tr>
     
     <td class="label-1" style="font-size:15pt;" align="right">Fabric pH</td>
     <td><input  type="TEXT" style="font-size:20pt;width:90pt" name="FABRIC_PH" value="<%=fp%>" onfocus="selectobj(this.name)"></td>
     </tr>
        <tr><td height="165pt"></td></tr>
        <tr><td class="label-1" style="font-size:15pt;" colspan="2">
                <hr class="label-1" style="margin:0pt;padding:0pt">
            </td>
        </tr>
    </table>
  </td-->

  
  
  <td valign="top" style="padding:10pt;">
    <table>
   <tr>
    <td><input type="BUTTON" name="0" value="." class="btn" onclick="AddDigit('.')"></td>
     <td colspan="2" ><input type="BUTTON" name="backspace"  value="Backspace" class="btn" style="width:203pt" onclick="backclear()" ></td>
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
       <td ><input type="Button" value="Enter" style="background-color:#1e721e" onclick="nextrecfocu()" class="btn"></td>
    </tr>
   <% if(CATG_CODE.equals("D"))
       {%>
       <input type="HIDDEN" name="inputobg" value="ACTUAL_QTY">
       <%}else{%>
        <input type="HIDDEN" name="inputobg" value="SHADE_LOT">
       <%}%>
    </table>
    </td></tr></table>
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

                if(request.getParameter("savemsg")!=null)
{
String msg=null;
if(Integer.parseInt(request.getParameter("savemsg"))>0)
{%>
<script language="javascript">
alert("Record Saved Successfully!!!")
</script>
<%}else{%>
<script language="javascript">
alert("Record Not Saved!!!")
</script>

<%}
}%>
  </form>
  </body>
</html>
