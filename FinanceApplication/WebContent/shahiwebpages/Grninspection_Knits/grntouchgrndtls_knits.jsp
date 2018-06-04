<%-- 
    Document   : grntouchgrndtls_knits
    Created on : Oct 18, 2011, 2:53:25 PM
    Author     : Shyamal
--%>

<%@ page import="shahi.Action.database.ConnectionSeplWeb"%>
<%@ page import="shahi.Action.database.ConnectionShahiHris"%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.ibm.as400.access.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Shahi Export Pvt Ltd</title>
 <LINK href="css/style.css" rel="stylesheet"	type="text/css">
 
     
     
 <style type="text/css">


     .lodingdiv{
position:absolute;
font-family:arial;
font-size:25;
left:372px;
top:374px;
background:#e6e6e6; layer-background-color:blue; height:80pt; width:300pt;
}
.btn1{
 font-size:25.0pt;

    width:150pt;
    background-color: #f3f3ee;
    }
     .btn
    {
    font-size:20.0pt;
    font-family:Arial Black;
    width:50pt;
    background-color: #f3f3ee;
    height:60px
    }

</style>

<SCRIPT TYPE="text/javascript" LANGUAGE="javascript">
   function numbfield_check(field,fname)
        {
            var NumberField = field;
            var NumberField2 = '0'+field.value;
            if(NumberField.value==0){}else{
                if( NumberField2.match( /^\d+$|^\d+\.\d{1,2}$/) )
                {return true;}
                else
                {
                    alert("Please enter "+fname+" numbers & upto 2 decimal only");
                    NumberField.focus();
                    NumberField.value="";
                    return false;
                }
            }
        }
  function printstickerroll(k)
    {
    a=document.hris.rollstic
    document.hris.action="Printsticker.jsp?reporttype="+k;
    document.hris.submit();
    }
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
    }
 function objfocus(x)
 {
  document.hris.inputobg.value=x;
 }
 
function clear1()
{
 inputobg=document.hris.inputobg.value
 document.forms['hris'].elements[inputobg].value="";
}

function selectobj(a)
{
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

   function tabE(obj,e){
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

function closerec(a,b,c)
{
 if(confirm('Do You Want to Close Roll Entry ?'))
  {
    window.location.href="grntouchgrndtls_knits.jsp?grnno="+a+"&ROLL_NO="+b+"&srollno="+b+"&emp_code="+c+"&closeflag=a";

 }else{
  return;
 }
}

function closerec1(a,b,c,d,e)
{
 if(confirm('Do You Want to Split Roll Qty ?'))
  {
    window.location.href="grntouchgrndtls_knits.jsp?grnno="+a+"&ROLL_NO="+b+"&srollno="+b+"&emp_code="+c+"&splitroll=a&tempactulaqty="+d+"&temprollqty="+e;

 }else{
  return;
 }
}

function waitPreloadPage() { //DOM
if (document.getElementById){
document.getElementById('prepage').style.visibility='hidden';
}else{
if (document.layers){ //NS4
document.prepage.visibility = 'hidden';
}
else { //IE4
document.all.prepage.style.visibility = 'hidden';
}
}
}

function showhide(id,id1){
if (document.getElementById){
obj = document.getElementById(id);
obj1 = document.getElementById(id1);
if (obj.style.display == "none")
{
obj.style.display = "";
obj1.src="../Recruitment/image/minus.jpg";
}
else {
obj.style.display = "none";
obj1.src="../Recruitment/image/plus.jpg";
}
}
}

function changevaluerollno()
{
document.hris.srollno.value=document.hris.searchrollno.value;
}

function callselectedroll()
{
a=document.hris.searchrollno.value;
b=document.hris.srollno.value;
if(b=="")
{
alert("Please Select/Enter Roll No !!");
return false;
}else{
var objoption=document.hris.searchrollno.options.length;
flag=0;
        for(i=0; i<objoption; i++)
        {
        if(document.hris.searchrollno[i].value==b)
        {
        flag=1;
        }
        }
  if(flag==0)
  {
   alert("Not Valid Roll No !!");
    return false;

  }else{
  document.hris.action="grntouchgrndtls_knits.jsp"
  document.hris.submit();
  }
}
}

function addodour(a,b)
{
    if(document.hris.ODOUR.value=="")
    {
        alert("Please Select Fabric Odour !!");
        document.hris.ODOUR.focus();
        return false;
    }else{
     d=document.hris.ODOUR.value;
     gr=document.hris.GSM_REQD.value;
     ga=document.hris.GSM_AVG.value;
     gd=document.hris.GSM_DEVIATION.value;
     wr=document.hris.WIDTH_REQD.value;
     wa=document.hris.WIDTH_AVG.value;
     wd=document.hris.WIDTH_DEVIATION.value;
     window.location.href="grntouchgrndtls_knits.jsp?addodourflag=1&emp_code="+a+"&grnno="+b+"&ODOUR="+d+"&GSM_REQD="+gr+"&GSM_AVG="+ga+"&GSM_DEVIATION="+gd+"&WIDTH_REQD="+wr+"&WIDTH_AVG="+wa+"&WIDTH_DEVIATION="+wd;
    }
}
</SCRIPT>
 <%
         PreparedStatement stat,stat2,stat1     = null;
         ResultSet result,result2,result1=null;
         String RECEIPT_NO=request.getParameter("grnno");
         Connection conn = null;
         ResourceBundle aResourcBundle = null;
         String emp_code = request.getParameter("emp_code");
         String location = (String)session.getValue("LOCATION_CODE");
         String EMPNAME= (String)session.getValue("EMPNAME");
         String CATG_CODE=(String)session.getValue("CATG_CODE");
         if(CATG_CODE==null)
         {
          CATG_CODE=request.getParameter("Category").trim();
          session.putValue("CATG_CODE",CATG_CODE);
         }
         String srollno= request.getParameter("srollno");

         //location="100";

        conn=new ConnectionSeplWeb().getConnection();

        if(request.getParameter("closeflag")!=null)
        {
            stat=conn.prepareStatement("update grninsdt set end_date=trunc(sysdate),end_time=sysdate, USER_ID=? where location_code=? "
                    + "and RECEIPT_NO=? and ROLL_NO=?");
            stat.setString(1,emp_code);
            stat.setString(2,location);
            stat.setString(3,RECEIPT_NO);
            stat.setString(4,request.getParameter("ROLL_NO").trim());
            stat.executeUpdate();
        }

         if(request.getParameter("splitroll")!=null)
         {           
            stat=conn.prepareStatement("update grninsdt set ROLL_QTY=?, USER_ID=? where location_code=? and RECEIPT_NO=? and ROLL_NO=?");
            //stat=conn.prepareStatement("update grninsdt set end_date=current date,end_time=current time, USER_ID=? where location_code=? and RECEIPT_NO=? and ROLL_NO=?");
            stat.setString(1,request.getParameter("tempactulaqty"));
            stat.setString(2,emp_code);
            stat.setString(3,location);
            stat.setString(4,RECEIPT_NO);
            stat.setString(5,request.getParameter("ROLL_NO").trim());
           int saverec=stat.executeUpdate();
           if(saverec>0)
           {
             String sln="";
             stat=conn.prepareStatement("select max(sl_no)+1 sln from grninsdt where location_code=? and RECEIPT_NO=?");
             stat.setString(1,location);
             stat.setString(2,RECEIPT_NO);
             result=stat.executeQuery();
             if(result.next())
             {
               sln=result.getString("sln");
             }
               double aaaa=Double.parseDouble(request.getParameter("temprollqty"));
               double bbbb=Double.parseDouble(request.getParameter("tempactulaqty"));
               double cccc=aaaa-bbbb;
 // TEST_METHOD,WASH_CARE,PL_SOURCE,REQD_SHRK_LENGTH,REQD_SHRK_WIDTH,REQD_SHRK_SPRLT,ACT_SHRK_LENGTH,ACT_SHRK_WIDTH,
//ACT_SHRK_SPRLT,REQD_CFHL,REQD_CFWASH,REQD_CFWATER,REQD_DRYCROCK,REQD_WETCROCK,ACT_CFHL,ACT_CFWASH,ACT_CFWATER,
//ACT_DRYCROCK,ACT_WETCROCK,SHRK_LENGTH_BW,SHRK_WIDTH_BW,SHRK_SPRLT_BW,SHRK_LENGTH_AW,SHRK_WIDTH_AW,SHRK_SPRLT_AW,PROCESS_ROUTE

               stat=conn.prepareStatement("insert into grninsdt(LOCATION_CODE,RECEIPT_NO,SUPPLIER_CODE,SUPPLIER_NAME,BUYER_NAME,ITEM_CODE,ITEM_DESC,PO_NO,GRN_QTY,"
                       + "ROLL_NO,COLOR,SHRK_PRCT,GMT_AVG,TDATE,TTIME,USER_ID,COUNT_CONST,CF_WASH,DRY_CROCK,WASH_CROCK,BOWING,SL_NO,INVOICE_NO,WIDTH,ROLL_QTY,"
                       + "GRN_DATE,ODOUR,FABRIC_FORM,TOLERANCE,BUYER_CODE,WHLO,INS_UOM,CONV_FACT,FABRIC_PH,BUOM,AUOM,ROLL_QTY_B,inspection_type) "+
               " select LOCATION_CODE,RECEIPT_NO,SUPPLIER_CODE,SUPPLIER_NAME,BUYER_NAME,ITEM_CODE,ITEM_DESC,PO_NO,GRN_QTY,'"+request.getParameter("ROLL_NO").trim()+
                       "S"+"',COLOR,SHRK_PRCT,GMT_AVG,trunc(sysdate),sysdate,'"+emp_code+"',COUNT_CONST,CF_WASH,DRY_CROCK,WASH_CROCK,BOWING,"+sln+",INVOICE_NO,"
                       + "WIDTH,'"+cccc+"',GRN_DATE,ODOUR,FABRIC_FORM,TOLERANCE,BUYER_CODE,WHLO,INS_UOM,CONV_FACT,FABRIC_PH,BUOM,AUOM,CONV_FACT*"+cccc+",'K'"
                       + " from  grninsdt where  location_code=? and RECEIPT_NO=? and ROLL_NO=? ");
               //stat.setString(1,request.getParameter("ROLL_NO").trim()+"S");
              // stat.setString(2,emp_code);
               stat.setString(1,location);
               stat.setString(2,RECEIPT_NO);
               stat.setString(3,request.getParameter("ROLL_NO").trim());
               saverec=stat.executeUpdate();
               if(saverec>0)
               {
                srollno=request.getParameter("ROLL_NO").trim()+"S";
               }
           }
      }
        if(request.getParameter("addodourflag")!=null)
        {
           // stat=conn.prepareStatement("update grninsdt set ODOUR=?, USER_ID=? where location_code=? and RECEIPT_NO=?");
           // stat.setString(1,request.getParameter("ODOUR"));
           // stat.setString(2,emp_code);
           // stat.setString(3,location);
          //  stat.setString(4,RECEIPT_NO);
          //  stat.executeUpdate();
            stat=conn.prepareStatement("update grninsdt set ODOUR=?,GSM_REQD=?,GSM_AVG=?,GSM_DEVIATION=?,WIDTH_REQD=?,WIDTH_AVG=?,WIDTH_DEVIATION=?,USER_ID=?,FABRIC_PH=? where location_code=? and RECEIPT_NO=?");
            stat.setString(1,request.getParameter("ODOUR"));
            stat.setString(2,request.getParameter("GSM_REQD"));
            stat.setString(3,request.getParameter("GSM_AVG"));
            stat.setString(4,request.getParameter("GSM_DEVIATION"));
            stat.setString(5,request.getParameter("WIDTH_REQD"));
            stat.setString(6,request.getParameter("WIDTH_AVG"));
            stat.setString(7,request.getParameter("WIDTH_DEVIATION"));
            stat.setString(8,emp_code);
            stat.setString(9,request.getParameter("FABRIC_PH"));
            stat.setString(10,location);
            stat.setString(11,RECEIPT_NO);
            stat.executeUpdate();
        }
        
       // location="100";
        stat=conn.prepareStatement("select distinct location_code,RECEIPT_NO,SUPPLIER_CODE,SUPPLIER_NAME,BUYER_NAME,ITEM_CODE,ITEM_DESC,PO_NO,grn_qty,"
                + "nvl(COLOR,' ') COLOR,nvl(COUNT_CONST,' ') COUNT_CONST,nvl(SHRK_PRCT,' ') SHRK_PRCT,GMT_AVG,BOWING,nvl(CF_WASH,' ') CF_WASH,"
                + "nvl(DRY_CROCK,' ') DRY_CROCK,nvl(WASH_CROCK,' ') WASH_CROCK,nvl(INVOICE_NO,' ')  INVOICE_NO,nvl(width,' ') width,nvl(ODOUR,' ') "
                + "ODOUR,FABRIC_FORM,TOLERANCE,"
                + " nvl(whlo,' ') whlo,INS_UOM,nvl(CONV_FACT,0) CONV_FACT,nvl(BUOM,' ') BUOM,nvl(AUOM,' ') AUOM,GSM_REQD,GSM_AVG,GSM_DEVIATION,WIDTH_REQD,"
                + "WIDTH_AVG,WIDTH_DEVIATION,nvl(PROCESS_ROUTE,' ') PROCESS_ROUTE,FABRIC_PH from grninsdt where location_code=? and RECEIPT_NO=? "
                + "and nvl(inspection_type,'W')='K'");
        stat.setString(1,location);
        stat.setString(2,RECEIPT_NO);
        result=stat.executeQuery();

        String ODOURtemp=null;

  %> 
   
</head>
<body  onLoad="waitPreloadPage();" oncontextmenu="return false;">
<form name="hris" method="POST"   action="" >

<DIV align="center" id="prepage" class="lodingdiv" >
<img src="image/progress.gif" >
<br>
<B >Loading ... ... Please wait ...</B>
</DIV>

 <table bgcolor="#006699" border="0" cellpadding="4"   cellspacing="1"  width="98%">
<tr>
 <td class="label" style="font-size:15pt;font-family:Arial Black;font-weight:'normal'"><%=EMPNAME%> - Shahi Exports Pvt. ..d.</td>
<td align="right">
  <%if(srollno==null)
 {%>
    <input type="BUTTON"  style="font-size:20pt;width:100pt"   onclick="addodour('<%=emp_code%>',<%=RECEIPT_NO%>)" name="btn" value="Save">
   <input type="BUTTON"  style="font-size:20pt;width:100pt"   onclick="window.location.href='Grnno_knits.jsp?emp_code=<%=emp_code%>'" name="btn" value="Back">
 <%}else{%>
  <input type="BUTTON"  style="font-size:20pt;width:100pt"   onclick="window.location.href='grntouchgrndtls_knits.jsp?emp_code=<%=emp_code%>&grnno=<%=RECEIPT_NO%>'" name="btn" value="Back ">
 <%}%>

</td>
    </tr>
  </table>
 <table width="98%"><tr><td>
 <%

int flag=0;
if(result.next())
{
flag=1;
%>
<input type="hidden" name="grnno" readonly="readonly"  value="<%=result.getString("RECEIPT_NO")%>" maxlength="10" onKeyPress="return tabE(this,event)" style="width:100pt">
<input type="hidden" name="emp_code" readonly="readonly"  value="<%=request.getParameter("emp_code")%>" maxlength="10" onKeyPress="return tabE(this,event)" style="width:100pt">

  <table cellpadding="0" cellspacing="1" border="0">
      <tr>
      <td class="label-1" align="right">GRN #</td><td><input type="TEXT" name="RECEIPT_NO" readonly="readonly"  value="<%=result.getString("RECEIPT_NO")%>" maxlength="10" onKeyPress="return tabE(this,event)" style="width:100pt"></td>
      <td class="label-1" align="right" >Buyer</td>
      <td><input type="TEXT" name="BUYER_NAME" readonly="readonly" value="<%=result.getString("BUYER_NAME")%>"  onkeypress="return tabE(this,event)" style="width:552pt"> </td>
     </tr>
     <tr>
      <td class="label-1" align="right">Supplier # </td><td><input type="TEXT" name="SUPPLIER_CODE" value="<%=result.getString("SUPPLIER_CODE")%>"  readonly="readonly" onKeyPress="return tabE(this,event)" style="width:100pt"></td>
      <td class="label-1" align="right">Supplier Name</td>
      <td><input type="TEXT" name="SUPPLIER_NAME" value="<%=result.getString("SUPPLIER_NAME")%>" readonly="readonly"  onkeypress="return tabE(this,event)" style="width:552pt"></td>
     </tr>
     <tr>
      <td class="label-1" align="right">Item # </td>
      <td><input type="TEXT" name="ITEM_CODE" value="<%=result.getString("ITEM_CODE")%>"  onkeypress="return tabE(this,event)" readonly="readonly" style="width:100pt"></td>
     <td class="label-1" align="right">Item Description</td>
     <td><input type="TEXT" name="ITEM_DESC"  value="<%=result.getString("ITEM_DESC")%>" onKeyPress="return tabE(this,event)" readonly="readonly" style="width:552pt"></td>
     </tr>
     <tr>
      <td class="label-1" align="right">Po# </td>
      <td><input type="TEXT" name="PO_NO"  value="<%=result.getString("PO_NO")%>" onKeyPress="return tabE(this,event)" readonly="readonly" style="width:100pt"></td>
      <td class="label-1" align="right">Quantity  </td>
      <td>
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tr>
                  <td><input type="TEXT" value="<%=result.getDouble("GRN_QTY")%>" name="GRN_QTY"  onkeypress="return tabE(this,event)" readonly="readonly" style="width:120pt"></td>
                  <td width="12.0%"></td>
                  <td class="label-1" align="right">Color <input type="TEXT" name="COLOR" value="<%=result.getString("COLOR")%>" onkeypress="return tabE(this,event)" readonly="readonly" style="width:120pt"></td>
              </tr>
          </table>
      </td>
    </tr>
    <tr>
      <td class="label-1" align="right">Invoice# </td>
      <td><input type="TEXT" name="INVOICE_NO" value="<%=result.getString("INVOICE_NO")%>" maxlength="20"  onkeypress="return tabE(this,event)"  style="width:100pt"></td>
      <td class="label-1" align="right">Po Width</td>
      <td>
          <table cellpadding="0" border="0" cellspacing="0" width="100%">
              <tr class="label-1">
                  <td width="29.0%">
                      <input type="TEXT" name="powidth" style="width:120pt" readonly="readonly" value="<%=result.getString("WIDTH")%>">
                  </td>
                  <td width="12.0%" align="right" class="label-1">
                  </td>
                  <%ODOURtemp=result.getString("ODOUR");    %>
                  <td align="right" class="label-1" >Fabric Odour <input type="TEXT" name="ODOURtest"  style="width:120pt" readonly="readonly" value="<%=result.getString("ODOUR")%>"></td>
              </tr>
          </table>
      </td>
 </tr>
 <tr>
     <td class="label-1" align="right">Fabric Form </td>
     <td>
        <input type="TEXT" name="FABRIC_FORM"  style="width:100pt" readonly="readonly" value="<%=result.getString("FABRIC_FORM").trim()%>">
     </td>
     <td class="label-1" align="right">Points UOM</td><td>
       <table width="100%" cellpadding="0" cellspacing="0">
          <tr>
             <td width="29.0%">
                 <input type="TEXT" name="INS_UOM"  style="width:120pt" readonly="readonly" value="<%=result.getString("INS_UOM").trim()%>">
             </td>
             <td width="12.0%" align="right" class="label-1"></td>
             <td class="label-1" align="right">Basic UOM
             <%if(result.getString("BUOM")!=null ){%>
                 <input type="TEXT" name="basicuom" readonly="readonly" value="<%=result.getString("BUOM").trim()%>" style="width:120pt">
             <%}%>
	    </td>
          </tr>
       </table>
     </td>         
 </tr>
 <tr>

 <td class="label-1" align="right">Warehouse</td>
 <td><input type="text" name="WHLO" readonly="true" style="width:100pt" value="<%=result.getString("WHLO")%>"></td>
  <td class="label-1" align="right">Tolerance</td>
  <td>
   <table cellpadding="0" cellspacing="0" width="100%">
       <tr>
           <td>
               <%String tlrnc="";
                 if(result.getString("TOLERANCE")!=null ){
                  tlrnc=result.getString("TOLERANCE");
               }%>
               <input type="TEXT" name="TOLERANCE"  style="width:120pt" readonly="readonly" value="<%=tlrnc%>">
           </td>
           <td class="label-1">PO UOM
           <%if(result.getString("AUOM")!=null ){%>
              <input type="TEXT" name="altuom" readonly="readonly" value="<%=result.getString("AUOM").trim()%>" style="width:60pt">
             <%}%>
           </td>
           <td  align="right" class="label-1">Conv Factor
           <%if(result.getString("CONV_FACT")!=null ){%>
              <input type="TEXT" name="convfalt"  style="width:60pt" readonly="readonly" value="<%=result.getString("CONV_FACT").trim()%>">
              <%}%>
              &nbsp;Process route
              <input type="TEXT" name="pros_rout" style="width:120pt" readonly="readonly" value="<%=result.getString("PROCESS_ROUTE").trim()%>">	
              <input type="hidden" name="FABRIC_PH" style="width:120pt" readonly="readonly" value="<%=result.getString("FABRIC_PH")%>">	
    </td>
	</tr>
     </table>
  </td>
 </tr>
  </table>
  <table align="left" cellpadding="0" cellspacing="1" border="0">  
       <tr class="table-header">
           <td width="78.0pt">Fabric</td>
           <td width="125.0pt">GSM Required</td>
           <td width="125.0pt">Dia Required</td> 
       </tr>
       <%String gr=""; String ga=""; String gd="";
          if(result.getString("GSM_REQD")!=null){gr=result.getString("GSM_REQD");}
          if(result.getString("GSM_AVG")!=null){ga=result.getString("GSM_AVG");}
          if(result.getString("GSM_DEVIATION")!=null){gd=result.getString("GSM_DEVIATION");}
          
          String wr=""; String wa=""; String wd="";
          if(result.getString("WIDTH_REQD")!=null){wr=result.getString("WIDTH_REQD");}
          if(result.getString("WIDTH_AVG")!=null){wa=result.getString("WIDTH_AVG");}
          if(result.getString("WIDTH_DEVIATION")!=null){wd=result.getString("WIDTH_DEVIATION");}
        %>
       <tr>        
           <td></td>
           <td><input type="TEXT" name="GSM_REQD" value="<%=gr%>" style="width:100%" onclick="objfocus('GSM_REQD');" onblur="numbfield_check(this,'GSM Required')" onkeypress="return tabE(this,event)"></td>
           
           <input type="hidden" name="GSM_AVG" value="<%=ga%>" style="width:100%" onblur="numbfield_check(this,'GSM Average')" onkeypress="return tabE(this,event)">
           <input type="hidden" name="GSM_DEVIATION" value="<%=gd%>" style="width:100%" onblur="numbfield_check(this,'% of Deviation')" onkeypress="return tabE(this,event)">
       
           <td><input type="TEXT" name="WIDTH_REQD" onclick="objfocus('WIDTH_REQD');" value="<%=wr%>" style="width:100%" onblur="numbfield_check(this,'Dia Required')" onkeypress="return tabE(this,event)"></td>
           <input type="hidden" name="WIDTH_AVG" value="<%=wa%>" style="width:100%" onblur="numbfield_check(this,'Width Average')" onkeypress="return tabE(this,event)">
           <input type="hidden" name="WIDTH_DEVIATION" value="<%=wd%>" style="width:100%" onblur="numbfield_check(this,'% of Deviation')" onkeypress="return tabE(this,event)">
       </tr>
   </table>
     
  <%}%>
 </td>
     </tr>
 <tr>
     <td  class="label-1" style="font-size:13pt" align="center">
 <%if(flag!=0){%>
 <table border="0" width="100%" align="center" style="margin-top:-2pt;margin-bottom:-3pt;"  >
<tr><td valign="top" >
<%
     stat=conn.prepareStatement("select * from grninsdt where location_code=? and RECEIPT_NO=? and nvl(inspection_type,'W')='K' order by SL_NO");
     stat.setString(1,location);
     stat.setString(2,RECEIPT_NO);
     result=stat.executeQuery();
%>
<table align="center" cellpadding="1"  style="border-color:blue;border-style:solid;" width="75%">
<%if(srollno==null)
 {%>

 <tr><td class="label-1" style="font-size:18pt" align="center">Fabric Odour</td></tr>
<tr><td class="label-1" style="font-size:18pt" align="center">
<select name="ODOUR"     style="width:200pt;font-size:16pt;">
<%if(ODOURtemp!=null && ODOURtemp.equals("Excessive")){%>
<option value="Excessive">Excessive</option>
<option value="Mild">Mild</option>
<option value="Nil">Nil</option>
<option value="">Select Fabric Odour</option>
<%}else if(ODOURtemp!=null && ODOURtemp.equals("Mild")){%>
<option value="Mild">Mild</option>
<option value="Excessive">Excessive</option>
<option value="Nil">Nil</option>
<option value="">Select Fabric Odour</option>
<%}else if(ODOURtemp!=null && ODOURtemp.equals("Nil")){%>
<option value="Nil">Nil</option>
<option value="Mild">Mild</option>
<option value="Excessive">Excessive</option>
<option value="">Select Fabric Odour</option>
<%}else{%>
<option value="">Select Fabric Odour</option>
<option value="Excessive">Excessive</option>
<option value="Mild">Mild</option>
<option value="Nil">Nil</option>
<%}%>
</select>
</td></tr>
<tr><td class="label-1" style="font-size:18pt" align="center">&nbsp;</td></tr>
<tr><td class="label-1" style="font-size:18pt" align="center">Select Roll No</td>
</tr><tr>
<td align="center">
<select name="searchrollno" onChange="changevaluerollno()"    style="width:200pt;font-size:16pt;">
<option value="">Select Roll No</option>
<%while(result.next()){
if(result.getString("actual_qty")!=null)
{%>
<option style="color:Red;" value="<%=result.getString("roll_no")%>"><%=result.getString("roll_no")%> (<%=result.getString("ROLL_QTY")%>)</option>
<%}else{
%>
<option  value="<%=result.getString("roll_no")%>"><%=result.getString("roll_no")%> (<%=result.getString("ROLL_QTY")%>)</option>
<%}}%>
</select>
</td>
</tr>
<tr><td class="label-1" style="font-size:18pt" align="center">&nbsp;</td>
<tr><td class="label-1" style="font-size:18pt" align="center">Enter Roll No</td>
</tr>
<tr>
    <td align="center">
<input type="TEXT" name="srollno" style="width:200pt;font-size:25pt" onclick="objfocus('srollno');">
</td></tr>
 <%}%>
 <%
 if(srollno!=null)
 {
 stat=conn.prepareStatement("update  grninsdt set START_DATE=trunc(sysdate),START_TIME=sysdate where location_code=? and RECEIPT_NO=? and upper(roll_no)=? and START_TIME is null and close_date is null");
 stat.setString(1,location);
 stat.setString(2,RECEIPT_NO);
 stat.setString(3,srollno);
 stat.executeUpdate();

     stat=conn.prepareStatement("select ROLL_QTY,Actual_QTY,ROLL_NO,to_char(START_DATE,'dd/mm/yyyy') START_DATE,to_char(end_DATE,'dd/mm/yyyy') end_DATE,to_char(START_TIME,'hh24:mi') START_TIME ,to_char(END_TIME,'hh24:mi') END_TIME,close_date from grninsdt where location_code=? and RECEIPT_NO=? and upper(roll_no)=?");
     stat.setString(1,location);
     stat.setString(2,RECEIPT_NO);
     stat.setString(3,srollno);
     result1=stat.executeQuery();
 }
 if(result1!=null)
 {
 if(result1.next())
 {

 %>
 <tr>
 <td class="label-1" style="font-size:20pt" align="center">Roll No: <%=srollno%></td>

</tr>
<tr>
 <td class="label-1" style="font-size:20pt" align="center">
 <table><tr class="label-1" style="font-size:20pt"><td>Start Date-</td><td style="color:Red"><%=result1.getString("START_DATE")%></td><td>&nbsp;&nbsp;Time-</td><td  style="color:Red"><%=result1.getString("START_TIME")%></td></tr>
<%if(result1.getString("end_DATE")!=null){%>

 <tr class="label-1" style="font-size:20pt"><td>End Date-</td><td style="color:Red"><%=result1.getString("end_DATE")%></td><td>&nbsp;&nbsp;Time-</td><td  style="color:Red"><%=result1.getString("end_TIME")%></td></tr>
<%}%>
</table>
 </td>

</tr>

<tr>
 <td align="center">

 <input type="BUTTON"  class="btn1" style="font-size:25pt;width:250pt;font-weight:bold" name="btn"  onclick="window.location.href='grntouchinsentry_knits.jsp?grnno=<%=RECEIPT_NO%>&ROLL_NO=<%=result1.getString("ROLL_NO")%>&emp_code=<%=emp_code%>'" value="Inspection Entry"></td></tr>
<tr>
<tr><td align="center">
<%if(CATG_CODE.equals("D")){%>
<input type="BUTTON" class="btn1" style="font-size:25pt;width:250pt;font-weight:bold" name="btn"  onclick="window.location.href='grntouchinsdefentry1_knits.jsp?grnno=<%=RECEIPT_NO%>&ROLL_NO=<%=result1.getString("ROLL_NO")%>&emp_code=<%=emp_code%>'" value="Defect Entry">
<%}else{%>
<input type="BUTTON" disabled="disabled" class="btn1" style="font-size:25pt;width:250pt;font-weight:bold" name="btn"  onclick="window.location.href='grntouchinsdefentry1_knits.jsp?grnno=<%=RECEIPT_NO%>&ROLL_NO=<%=result1.getString("ROLL_NO")%>&emp_code=<%=emp_code%>'" value="Defect Entry">

<%}%>
</td></tr>

<tr><td class="label-1" style="font-size:15pt" align="center" title="">
<%if(result1.getString("Actual_QTY")!=null && result1.getString("End_DATE")==null && CATG_CODE.equals("D")){%>
<input type="BUTTON"  style="font-size:25pt;width:250pt;font-weight:bold;background-color:#8f2e00;color:white" name="btn" class="btn1" onClick="closerec('<%=RECEIPT_NO%>','<%=result1.getString("ROLL_NO")%>','<%=emp_code%>')"  value="Close">
 <%}else{
 if(result1.getString("End_DATE")!=null)
 {
 %>
 Status : Closed
 <br>
 <input type="HIDDEN" name="rollstic" value="<%=result1.getString("ROLL_NO")%>">
 <input type="BUTTON"  style="font-size:25pt;width:250pt;font-weight:bold;background-color:Green;color:white" name="btn" class="btn1" onClick="printstickerroll('2')"  value="Print">

 <%
 if(result1.getInt("ROLL_QTY")-result1.getInt("Actual_QTY")>1)
 {%>
 <input type="BUTTON"  style="font-size:25pt;width:250pt;font-weight:bold;background-color:#8f2e00;color:white" name="btn" class="btn1" onClick="closerec1('<%=RECEIPT_NO%>','<%=result1.getString("ROLL_NO")%>','<%=emp_code%>','<%=result1.getString("Actual_QTY")%>','<%=result1.getString("ROLL_QTY")%>')"  value="Split Roll">
 <%}
 }else{
 if(result1.getString("close_date")==null){%>
 Status : Pending
 <%}else{%>
  Status : Closed
  <%}}
 }%>
   </td></tr>
   <%}}%>
</table>
</td>
<%if(srollno==null)
 {%>
<td width="100%" align="right">
<!--keypade++++++++++++++++++++++++++++++++++++++++++++ -->
   <table cellpadding="0" cellspacing="0"><tr><td>
   <table >
   <tr>
    <td><input type="BUTTON" name="A" value="A" class="btn" onClick="AddDigit('A')"></td>
    <td><input type="BUTTON" name="B" value="B" class="btn" onClick="AddDigit('B')"></td>
    <td><input type="BUTTON" name="C" value="C" class="btn" onClick="AddDigit('C')"></td>
    <td><input type="BUTTON" name="D" value="D" class="btn" onClick="AddDigit('D')"></td>
    <td><input type="BUTTON" name="E" value="E" class="btn" onClick="AddDigit('E')"></td>
     </tr>
    <tr>
    <td><input type="BUTTON" name="F" value="F" class="btn" onClick="AddDigit('F')"></td>
    <td><input type="BUTTON" name="G" value="G" class="btn" onClick="AddDigit('G')"></td>
    <td><input type="BUTTON" name="H" value="H" class="btn" onClick="AddDigit('H')"></td>
    <td><input type="BUTTON" name="I" value="I" class="btn" onClick="AddDigit('I')"></td>
    <td><input type="BUTTON" name="J" value="J" class="btn" onClick="AddDigit('J')"></td>
    </tr>
    <tr>
    <td><input type="BUTTON" name="K" value="K" class="btn" onClick="AddDigit('K')"></td>
    <td><input type="BUTTON" name="L" value="L" class="btn" onClick="AddDigit('L')"></td>
    <td><input type="BUTTON" name="M" value="M" class="btn" onClick="AddDigit('M')"></td>
    <td><input type="BUTTON" name="N" value="N" class="btn" onClick="AddDigit('N')"></td>
    <td><input type="BUTTON" name="O" value="O" class="btn" onClick="AddDigit('O')"></td>
    </tr>
    <tr>
    <td><input type="BUTTON" name="P" value="P" class="btn" onClick="AddDigit('P')"></td>
    <td><input type="BUTTON" name="Q" value="Q" class="btn" onClick="AddDigit('Q')"></td>
    <td><input type="BUTTON" name="R" value="R" class="btn" onClick="AddDigit('R')"></td>
    <td><input type="BUTTON" name="S" value="S" class="btn" onClick="AddDigit('S')"></td>
    <td><input type="BUTTON" name="T" value="T" class="btn" onClick="AddDigit('T')"></td>
    </tr>
    <tr>
    <td><input type="BUTTON" name="U" value="U" class="btn" onClick="AddDigit('U')"></td>
    <td><input type="BUTTON" name="V" value="V" class="btn" onClick="AddDigit('V')"></td>
    <td><input type="BUTTON" name="W" value="W" class="btn" onClick="AddDigit('W')"></td>
    <td><input type="BUTTON" name="X" value="X" class="btn" onClick="AddDigit('X')"></td>
    <td><input type="BUTTON" name="Y" value="Y" class="btn" onClick="AddDigit('Y')"></td>
  </tr>
   <tr>
    <td><input type="BUTTON" name="Z" value="Z" class="btn" onClick="AddDigit('Z')"></td>
    <td><input type="BUTTON" name="@" value="@" class="btn" onClick="AddDigit('@')"></td>
    <td><input type="BUTTON" name="#" value="#" class="btn" onClick="AddDigit('#')"></td>
    <td><input type="BUTTON" name="-" value="-" class="btn" onClick="AddDigit('-')"></td>
    <td><input type="BUTTON" name="/" value="/" class="btn" onClick="AddDigit('/')"></td>
  </tr>
       <input type="HIDDEN" name="inputobg" value="srollno">
    </table>
 </td><td>
    <table>
   <tr>
    <td><input type="BUTTON" name="0" value="." class="btn" onClick="AddDigit('.')"></td>
     <td colspan="2" ><input type="BUTTON" name="backspace"  value="Backspace" class="btn" style="width:140px;font-size:19px;height:60px" onClick="backclear()" ></td>
     </tr>
    <tr>
    <td><input type="BUTTON" name="1" value="1" class="btn" onClick="AddDigit(1)"></td>
    <td><input type="BUTTON" name="2" value="2" class="btn" onClick="AddDigit(2)"></td>
     <td><input type="BUTTON" name="3" value="3" class="btn" onClick="AddDigit(3)"></td>
    </tr>
    <tr>
    <td><input type="BUTTON" name="4" value="4" class="btn" onClick="AddDigit(4)"></td>
    <td><input type="BUTTON" name="5" value="5" class="btn" onClick="AddDigit(5)"></td>
    <td><input type="BUTTON" name="6" value="6" class="btn" onClick="AddDigit(6)"></td>
    </tr>
    <tr>
    <td><input type="BUTTON" name="7" value="7" class="btn" onClick="AddDigit(7)"></td>
    <td><input type="BUTTON" name="8" value="8" class="btn" onClick="AddDigit(8)"></td>
    <td><input type="BUTTON" name="9" value="9" class="btn" onClick="AddDigit(9)"></td>
    </tr>
    <tr>
     <td colspan="2"><input type="BUTTON" name="clear" style="width:106pt;background-color:red;" value="Clear" class="btn" onClick="clear1();" ></td>
     <td><input type="BUTTON" name="0" value="0" class="btn" onClick="AddDigit(0)"></td>
     </tr>
     <tr><td colspan="3">
       <input type="BUTTON" name="OK" value="Enter" style="width:212;background-color:Green;" class="btn" onClick="callselectedroll()">
       </td>
     </tr>
    </table>
 </td>
 </tr></table>
<!--close keypade++++++++++++++++++++++++++++++++++++++++++++ -->
</td>
<%}%>
</tr>
</table>
<%}%>
</td></tr></table>
<%if(flag==0){%>
<h1 class="label-1" align="center" style="font-size:25pt;padding-top:150pt">Record Not Found !!</h1>
<%}%>
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
     <table cellpadding="0" cellspacing="0">
         <tr>
             <td class="label-1">Machine IP</td><td class="label-1"><%=request.getRemoteAddr() %></td>
         </tr>
     </table>
</form>
  </body>
</html>

