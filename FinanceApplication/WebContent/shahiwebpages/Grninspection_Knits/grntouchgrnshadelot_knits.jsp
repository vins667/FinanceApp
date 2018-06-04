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
  <%
    String CATG_CODE=(String)session.getValue("CATG_CODE");
     if(CATG_CODE==null)
         {
         CATG_CODE=request.getParameter("Category").trim();
         session.putValue("CATG_CODE",CATG_CODE);         
         }
  %>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
     <LINK href="css/style.css" rel="stylesheet"	type="text/css">
 
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd</title>
  
 <script language="javascript">
 
 function printstickerroll(k)
{

a=document.hris.rollstic

document.hris.action="Printsticker.jsp?reporttype="+k+"&pageback=1";
document.hris.submit();

}

function CheckCheckAll1() {
  a=document.hris.rollstic;
  for (var i=0;i<a.length;i++) 
  {
  if(document.hris.s.checked)
  {
  e=a[i]
  if (!e.disabled ) 
  {
    e.checked=true;
  }
   
  }else{
  
   e=a[i]
  if (!e.disabled ) 
  {
    e.checked=false;
  }  
  }
  } 
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
     
      for(i=0; i<11; i++)
     {
     document.getElementById(inputobg+i).value=document.forms['hris'].elements[inputobg].value;
     }     
    }

function clear1()
{
 inputobg=document.hris.inputobg.value
 document.forms['hris'].elements[inputobg].value="";
     for(i=0; i<11; i++)
     {
     document.getElementById(inputobg+i).value='';
     }  
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
  
 function validatenumber(a)
{
                   if (!a.value=="" &&!a.value.match(/^\d+$|^\d+\.\d{1,2}$/ ) ) 
                        {
                          alert('Invalid Input, Only Numbers Allowed'); 
                          a.value="";
                          a.focus();
                          return false;                        
                        }
} 
 
 function invoke(btn,a,b,c)
{
    if(btn == 0) 
    {
    document.hris.action="grntouchgrnshadelot_knits.jsp?newpage="+a+"&firstpageno="+b+"&maxpage="+c;
    }                       
      document.hris.submit();
}  
 
 function saverecord()
 {
     if(validaterec()==true)
     {
         document.hris.action="grntouchgrnshadelotjava_knits.jsp"
         document.hris.submit();
     }
 }
 
function validaterec()
{return true;
}

function copyshadegrp(a,b)
{
 for(i=0; i<11; i++)
     {
     k=document.getElementById(b+i).options.length;
     for(j=0; j<k; j++)
     {
     if(a==document.getElementById(b+i).options[j].value)
     {
     document.getElementById(b+i).options[j].selected=true
     break;
     }
     }      
     }
}

function searchrecord()
 {
 document.hris.action="grntouchgrnshadelot_knits.jsp"
 document.hris.submit();
}

 </script>
    <style>
    text{
    font-size:18pt;
    width:200pt;
   text-transform: uppercase
    }
    td{
   font-size:12pt;
    }
      .btn
    {
    font-size:21.0pt;
    font-family:Arial Black;
    width:50pt;
    background-color: #f3f3ee;
   
   
    }
    .newst{
   font-size:18pt;
   width:60pt
    }
    
     .newst5{
   font-size:18pt;
   width:35pt
    }
    
     .newst1{
   font-size:17pt;
   width:62pt;
   font-weight:bold;
   background-color:#ece9d8;
    border: solid 1px #677788;
    }
    
     .newst2{
   font-size:15pt;
   width:30pt;
   font-weight:bold;
    }
    
    </style>
  </head>
  <%
        PreparedStatement        stat    = null;
        PreparedStatement        stat2    = null;
         ResultSet result,result2,result1=null;
        String grnno=request.getParameter("grnno");
        String emp_code=request.getParameter("emp_code");
        String SEARCHQTY=request.getParameter("SEARCHQTY");
        String SEARCHROLL=request.getParameter("SEARCHROLL");
        String SEARCHString=" ";
        if(SEARCHQTY!=null && SEARCHQTY.length()>0)
        {
        
        SEARCHString ="and roll_qty='"+SEARCHQTY+"'";
        }
        
        if(SEARCHROLL!=null && SEARCHROLL.length()>0)
        {
        
        SEARCHString = SEARCHString + "and roll_no='"+SEARCHROLL+"'";
        }
        
        Connection conn = null;
        ResourceBundle aResourcBundle = null;
        String myusrid = (String)session.getValue("myusrid");
        String location = (String)session.getValue("LOCATION_CODE");
        String EMPNAME= (String)session.getValue("EMPNAME");
        //location="100";
        conn=new ConnectionSeplWeb().getConnection();
      
         int reportflag=0;
        stat=conn.prepareStatement("select RECEIPT_NO,REPORT_NO from grninsdt where LOCATION_CODE=? and  RECEIPT_NO=? and REPORT_NO is not null and nvl(inspection_type,'W')='K'");
        stat.setString(1,location);
        stat.setString(2,grnno);
        result=stat.executeQuery();
        if(result.next())
        {
        reportflag=1;
        }

reportflag=0;
       stat=conn.prepareStatement("select distinct location_code,RECEIPT_NO,SUPPLIER_CODE,SUPPLIER_NAME,nvl(BUYER_NAME,' ') BUYER_NAME,ITEM_CODE,"
               + "ITEM_DESC,PO_NO,grn_qty,COLOR,COUNT_CONST,SHRK_PRCT,GMT_AVG,BOWING,CF_WASH,DRY_CROCK,WASH_CROCK,nvl(INVOICE_NO,'')  INVOICE_NO,"
               + "nvl(width,'') width,FABRIC_FORM from grninsdt where location_code=? and RECEIPT_NO=? "+SEARCHString+" and nvl(inspection_type,'W')='K'");
        stat.setString(1,location);
        stat.setString(2,grnno);
       ResultSet resultdtls=stat.executeQuery();
   
    String sqlQuery=null;
    
    Statement  stat1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    sqlQuery ="select * from grninsdt where location_code="+location+" and RECEIPT_NO="+grnno+" "+SEARCHString+" and nvl(inspection_type,'W')='K' order by sl_no";
    
    PreparedStatement cnt=conn.prepareStatement("select count(*) cnt from grninsdt where"
            + " location_code="+location+" and RECEIPT_NO="+grnno +SEARCHString+" and nvl(inspection_type,'W')='K'");
    result = stat1.executeQuery(sqlQuery);
    ResultSet result_cnt = cnt.executeQuery();  
    
     stat2=conn.prepareStatement("select * from grninsdt where close_date is null and location_code="+location+" and RECEIPT_NO="+grnno  + SEARCHString+" and nvl(inspection_type,'W')='K'");
     result2=stat2.executeQuery();
     
     int pageno=0;
     int k=11;//no of  records
    int p=0; 
    int q=0; 
     int i=1;
   
    String  pagevalue=null;
    
    if(result_cnt.next()==true)
    {p=Integer.parseInt(result_cnt.getString(1)); }
    pageno=p/k;
    if (p%k>0)
    {pageno++;}
       String group[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","NA"};
      
  %>
  <body >
  <form name="hris" method="post" action="">
  
  <table bgcolor="#006699" border="0"   cellspacing="0"  cellpadding="0" width="100%"><tr>
  <td width="100%">
        <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
        <td class="label"  align="center"><%=EMPNAME.trim()%> - Shahi Exports Pvt. Ltd.
             </td></tr>
       <tr>
       <td width="100%" style="margin:0pt" bgcolor="#f2f9fb">
       <table width="100%" cellspacing="1" bgcolor="#006699" ><tr>
      <%
       String  readonly1=null;
      String suliersarla=null;
      
      if(resultdtls.next()){
      suliersarla=resultdtls.getString("SUPPLIER_NAME");
      
  if(resultdtls.getString("FABRIC_FORM")!=null && resultdtls.getString("FABRIC_FORM").equals("Roll"))
            {
            readonly1="disabled=true";
            }
      %>
      <tr bgcolor="#f2f9fb">
      <td class="label-1" align="right">Supplier</td><td class="label-1" style="color:Green;font-size:9pt"><%=resultdtls.getString("SUPPLIER_NAME")%>  (<%=resultdtls.getString("SUPPLIER_CODE")%>)</td>
      <td class="label-1" align="right">Buyer</td><td class="label-1" style="color:Green;font-size:9pt"><%=resultdtls.getString("BUYER_NAME")%></td>
      </tr>
      <tr bgcolor="#f2f9fb">
      <td class="label-1" align="right">Item</td><td class="label-1" style="color:Green;font-size:9pt"><%=resultdtls.getString("ITEM_DESC")%>  (<%=resultdtls.getString("ITEM_CODE")%>)</td>
      <td class="label-1" align="right">Grn</td><td class="label-1" style="color:Green;font-size:9pt"><%=grnno%></td>
     
      </tr>
      <%}%>
      </tr></table>
       </td>
       
       </tr>
       </table>
  </td>
  <td>
  <%if(reportflag==0){%>
  <input type="BUTTON"  onclick="saverecord();" style="font-size:20pt;width:75pt" name="btn" value="Save">
  <%}else{%>
   <input type="BUTTON" disabled="disabled"  onclick="saverecord();" style="font-size:20pt;width:75pt" name="btn" value="Save">
  
  <%}%>
  </td><td>
  
  <input type="BUTTON"  style="font-size:20pt;width:75pt"  onclick="searchrecord()" name="btn" value="Search">
   </td><td>
  <input type="BUTTON"  style="font-size:20pt;width:75pt"  onclick="window.location.href='Grnno_knits.jsp?emp_code=<%=emp_code%>'" name="btn" value="Back">
         
  </td>
  </tr></table>
  

  <table cellpadding="0" cellspacing="0">
      <tr>
          <td valign="top" style="height:410pt">
      <table border="0" align="center" cellpadding="0" cellspacing="0" >
    <tr><td>   
 <%
    int maxflag=0;
    int maxpage=10;
    int firstpageno=0;   
    
    if(request.getParameter("addmorepage")==null)
    {
      firstpageno=1;     
      pagevalue=Integer.toString(firstpageno);    
    }else
    {if(request.getParameter("newpage")!=null)
     {if(Integer.parseInt(request.getParameter("newpage"))==2)
      {firstpageno= Integer.parseInt(request.getParameter("addmorepage"));
       pagevalue=Integer.toString(firstpageno);
       maxpage=(maxpage+firstpageno)-1;
       if(maxpage>=pageno)
       {maxpage=pageno;
        pagevalue=Integer.toString(firstpageno);
       }
      }else if(Integer.parseInt(request.getParameter("newpage"))==3)
      {maxpage=Integer.parseInt(request.getParameter("firstpageno"))-1;
       firstpageno=maxpage-9;
       if(firstpageno<=0)
       {firstpageno=1;      }      
       pagevalue=Integer.toString(firstpageno);
      }
      else
      {firstpageno=Integer.parseInt(request.getParameter("firstpageno"));
       pagevalue=Integer.toString(firstpageno);
       maxpage=Integer.parseInt(request.getParameter("maxpage"));      
      }
     }else
     {firstpageno=1;
      pagevalue=Integer.toString(firstpageno);   
     }
    }
    if(request.getParameter("pagebreak")!=null)
    {pagevalue=request.getParameter("pagebreak").toString().trim();    }
    if(maxpage >10)
    {%>
   
    <td><input type="IMAGE" src="image/Left_Arrow1.gif" style="width:50pt;font-size:30pt;font-weight:bold;text-align:center" onclick="invoke(0,3,<%=firstpageno%>,<%=maxpage%>);"  name="Pretpage" value="Prev"></td>
    <%}
    String tttt=request.getParameter("pagebreak");
    if(tttt==null)
    {
    tttt=Integer.toString(firstpageno);
    }
    for(q=firstpageno; q<=pageno ;q++)
    { 
    if(q<=maxpage){
   
    %>
    <td>
    <%if(Integer.parseInt(tttt)==q){%>
    <input type="SUBMIT" value="<%=q%>" name="pagebreak" onclick="invoke(0,1,<%=firstpageno%>,<%=maxpage%>);"   style="color:red;width:60pt;font-size:30pt;font-weight:bold;text-align:center">
  
    
    <%}else{%>
     <input type="SUBMIT" value="<%=q%>" name="pagebreak" onclick="invoke(0,1,<%=firstpageno%>,<%=maxpage%>);"   style="width:60pt;font-size:30pt;font-weight:bold;text-align:center">
   
    <%}%>
    </td>
  <%}else
    {maxflag=1;  }
    }
    if(maxflag==1)
    {%>    
  
    <td><input type="IMAGE" src="image/Right_Arrow1.gif"  onclick="invoke(0,2);"  style="width:50pt;font-size:30pt;font-weight:bold;text-align:center" name="nextpage" value="NEXT"></td>
   
  <%}%>
   <input type="hidden" name="pagebreak12" value="<%=firstpageno%>">
    <input type="hidden" name="addmorepage" value="<%=maxpage+1%>">
    <%if(request.getParameter("pagebreak")!=null){%>
    
    <input type="hidden" name="aaaaaaa" value="<%=request.getParameter("pagebreak")%>">
    <%}else{%>
    <input type="hidden" name="aaaaaaa" value="<%=firstpageno%>">
    <%}%>
    </tr>
    </table>
  
  <table cellpadding="1" width="100%" cellspacing="1">
  <input style="font-size:20pt;width:175pt"  readonly="readonly" type="hidden" name="grnno" value="<%=grnno%>" >
  <input style="font-size:20pt;width:175pt"  readonly="readonly" type="hidden" name="RECEIPT_NO" value="<%=grnno%>" >
  
  <tr >
  <!--
  <td >
  <%
  if(suliersarla!=null && suliersarla.trim().contains("SARLA FAB"))
         {%>
          <input type="BUTTON"  onclick="window.location.href='sarlagrntouchgrnshadelot_knits.jsp?grnno=<%=grnno%>&emp_code=<%=emp_code%>'" style="font-size:20pt;width:75pt" name="btn" value="Edit">
 
         <%}%>
  </td>-->
  
  <td>
  <%if(SEARCHROLL!=null && SEARCHROLL.length()>0){%>
  <input  type="TEXT" class="newst1"  value="<%=SEARCHROLL%>" maxlength="10"  name="SEARCHROLL"  onfocus="selectobj(this.name)">
  <%}else{%>
   <input  type="TEXT" class="newst1" maxlength="10"  name="SEARCHROLL"  onfocus="selectobj(this.name)">

  <%}%>
  
  </td>
  
  <td>
  <%if(SEARCHQTY!=null && SEARCHQTY.length()>0){%>
  <input  type="TEXT" class="newst1" value="<%=SEARCHQTY%>" maxlength="10"  name="SEARCHQTY"  onfocus="selectobj(this.name)">
  <%}else{%>
   <input  type="TEXT" class="newst1" maxlength="10"  name="SEARCHQTY"  onfocus="selectobj(this.name)">

  <%}%>
  
  </td>
  <td>
      <input  type="TEXT" class="newst" maxlength="10"  name="SHADE_LOT_ID"  onfocus="selectobj(this.name)">
  </td>
<td><select name="SHADE_GRP_ID" onchange="copyshadegrp(this.value,this.name)"  style="font-size:18pt;width:56pt">
  <option value="">GRP</option>
 <%for(int mmm=0; mmm<group.length; mmm++ ){%>

 <option value="<%=group[mmm]%>"><%=group[mmm]%></option>
 <%}%>
 </select></td>
 <td><input  type="TEXT" class="newst" maxlength="10"    name="MIN_WIDTH_ID"  onfocus="selectobj(this.name);"></td>
 <td><input  type="TEXT" class="newst" maxlength="10"   name="MAX_WIDTH_ID"  onfocus="selectobj(this.name);"></td>
<td>
<select name="CS_ID" onchange="copyshadegrp(this.value,this.name)"  style="font-size:19pt;width:80pt">
 <option value="No CS">No CS</option>
 <option value="Critical">Critical</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 </select>
 </td>
 <td ><select name="LWV_ID" onchange="copyshadegrp(this.value,this.name)"  style="font-size:19pt;width:90pt" >
 <option value="No LWV">No LWV</option>
 <option value="Critical">Critical</option>
 <option value="Major">Major</option>
 <option value="Minor">Minor</option>
 </select>
 </td>
 
 
 
 <!--td-->
  <input  type="hidden" class="newst5" maxlength="10"    name="CUT_PCS_ID"  onfocus="selectobj(this.name);">
  <!--/td-->
 
 <%if(readonly1==null){%>
 <!--td-->
     <input  type="hidden" class="newst5" maxlength="10"   name="ROLL_BOWING_ID"  onfocus="selectobj(this.name);">
 <!--/td-->
  <!--td-->
      <input  type="hidden" class="newst5" maxlength="10"   name="FOLD_LENGTH_ID"  onfocus="selectobj(this.name);">
  <!--/td-->
  
  <%}%>
 <!--td-->
     <input  type="hidden" class="newst5" maxlength="10"   name="ROLL_SHRK_PRCT_ID"  onfocus="selectobj(this.name);">
 <!--/td-->
 
 <td >
 <input type="CHECKBOX" name="s"  style="width:18;height:18;" onclick="CheckCheckAll1()">
 </td>
 </tr>
  
  <tr class="table-header">
      <td>Roll No</td>
      <td>Roll Len.</td>
      <td>Lot No</td>
      <td>Group</td>
      <td>Dia B/Wash</td>
      <td>Dia A/Wash</td>
      <td>CS</td>
      <td >LWV</td>
  <!--td>Ct Pcs</td-->
  <%if(readonly1==null){%>
  <!--td>Bow.</td-->
  <!--td>F.Len.</td-->
  <%}%>
  
  <!--td>Shri(%)</td-->
  <td align="left">Sticker</td>
  </tr>
  <%
  String r=null;
  int idcount=0;
  int flag=0;
  int ps=0;
  int ps2=0;
  int imgno=0;
  if (p!=0) 
  {if(Integer.parseInt(pagevalue)==1)
   {ps2=1;   }
  else
  {
   if (pageno!=0)
   {ps2=(Integer.parseInt(pagevalue)-1)*k+1;}
  }
  result.absolute(ps2);
  result.relative(-1);
  ps = result.getRow();
  if (Integer.parseInt(pagevalue)!=pageno)
  {
  while(i<=k)
  {
      result.next();    
   flag=1;
  
 
  if(r==null)
  {
  r=result.getString("roll_no");
  }
   String  readonly=null;
  if(result.getString("FABRIC_FORM")!=null && result.getString("FABRIC_FORM").equals("Roll"))
            {
            readonly="disabled=true";
            }
            
  
%>
 
 <tr>
 <td class="label-1" style="font-size:15pt;" >
  <input  type="TEXT" class="newst1" readonly="readonly" value="<%=result.getString("roll_no")%>" maxlength="10"  name="ROLL_NO" >
 
 </td>
  <td class="label-1" style="font-size:15pt;" >
  <input  type="TEXT" class="newst1" readonly="readonly" value="<%=result.getString("ROLL_QTY")%>" maxlength="10"  name="ROLL_NOlength" >
 
 </td>
 <td class="label-1" style="font-size:15pt;" >
 
 <%

 if(result.getString("SHADE_LOT")==null){
 %>
 <input  type="TEXT" class="newst" id="SHADE_LOT_ID<%=idcount%>" maxlength="10" value="1" name="SHADE_LOT<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)">
<%
 }else{%>
 <input  type="TEXT" class="newst" id="SHADE_LOT_ID<%=idcount%>" maxlength="10" value="<%=result.getString("SHADE_LOT")%>" name="SHADE_LOT<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)">
<%}%>
</td>
<td>
 <select  name="SHADE_GRP<%=result.getString("roll_no")%>" id="SHADE_GRP_ID<%=idcount%>"    style="font-size:18pt;width:56pt">
  <option value="">GRP</option>
 <%for(int mmm=0; mmm<group.length; mmm++ ){
 if(group[mmm].equals(result.getString("SHADE_GRP")))
 {%>
 <option selected="selected" value="<%=group[mmm]%>"><%=group[mmm]%></option>
 <%}else{
 %>
 <option value="<%=group[mmm]%>"><%=group[mmm]%></option>
 <%}}%>
 </select>
 </td>
<%if(result.getString("MIN_WIDTH")==null){%>
  <td><input  type="TEXT" class="newst" id="MIN_WIDTH_ID<%=idcount%>" maxlength="10" value="0"   name="MIN_WIDTH<%=result.getString("roll_no")%>" onblur="if(this.value==''){this.value='0'}"  onfocus="selectobj(this.name);this.value=''"></td>
 <td><input  type="TEXT" class="newst" id="MAX_WIDTH_ID<%=idcount%>" maxlength="10" value="0"  name="MAX_WIDTH<%=result.getString("roll_no")%>" onblur="if(this.value==''){this.value='0'}"  onfocus="selectobj(this.name);this.value=''"></td>

 <%}else{%>
 <td> <input  type="TEXT" class="newst" id="MIN_WIDTH_ID<%=idcount%>" maxlength="10" value="<%=result.getString("MIN_WIDTH")%>" onblur="if(this.value==''){this.value='0'}" name="MIN_WIDTH<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)"></td>
 <td><input  type="TEXT" class="newst" id="MAX_WIDTH_ID<%=idcount%>" maxlength="10" value="<%=result.getString("MAX_WIDTH")%>" onblur="if(this.value==''){this.value='0'}" name="MAX_WIDTH<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)"></td>

 <%}%>
<td>
 <select name="CS<%=result.getString("roll_no")%>" id="CS_ID<%=idcount%>" style="font-size:19pt;width:80pt">>
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
 
 <td>
 <select name="LWV<%=result.getString("roll_no")%>" id="LWV_ID<%=idcount%>" style="font-size:19pt;width:90pt"> >
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
  <option value="No LWV">No LWV</option>
   <option value="Minor">Minor</option>
 <option value="Major">Major</option>
 <option value="Critical">Critical</option>

 
 <%}%>

 </select>
 
 </td>
 
 <!--td-->
 <%if(result.getString("CUT_PCS")==null){%>
 <input type="hidden" class="newst5" id="CUT_PCS_ID<%=idcount%>"  name="CUT_PCS<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" class="newst5" id="CUT_PCS_ID<%=idcount%>"    value="<%=result.getString("CUT_PCS")%>"  name="CUT_PCS<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}%>
 <!--/td-->
 <%if(readonly==null)
 {%> 
<!--td-->
 <%if(result.getString("ROLL_BOWING")==null){%>
 <input type="hidden" class="newst5" id="ROLL_BOWING_ID<%=idcount%>" <%=readonly%>   name="ROLL_BOWING<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" class="newst5" id="ROLL_BOWING_ID<%=idcount%>"  <%=readonly%> value="<%=result.getString("ROLL_BOWING")%>"  name="ROLL_BOWING<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}%>
 <!--/td-->
 <!--td-->
 <%if(result.getString("FOLD_LENGTH")==null){%>
 <input type="hidden" class="newst5" id="FOLD_LENGTH_ID<%=idcount%>" onblur="validatenumber(this)" <%=readonly%>   name="FOLD_LENGTH<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" class="newst5" id="FOLD_LENGTH_ID<%=idcount%>" onblur="validatenumber(this)" <%=readonly%> value="<%=result.getString("FOLD_LENGTH")%>"  name="FOLD_LENGTH<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}%>
 <!--/td-->
 
 <%}%>
 <!--td-->
 <%if(result.getString("ROLL_SHRK_PRCT")==null){%>
 <input type="hidden" class="newst5" id="ROLL_SHRK_PRCT_ID<%=idcount%>" onblur="validatenumber(this)" value=""  name="ROLL_SHRK_PRCT<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 
 <%}else{%>
 <input type="hidden" class="newst5" id="ROLL_SHRK_PRCT_ID<%=idcount%>" onblur="validatenumber(this)" value="<%=result.getString("ROLL_SHRK_PRCT")%>"  name="ROLL_SHRK_PRCT<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 
 <%}%>
 <!--/td-->
 <td>
  <input type="checkbox" style="width:18px;height:18px" value="<%=result.getString("roll_no")%>" name="rollstic">
 </td>
 
 </tr>
 <%
  i++ ;
  idcount++;
  }
  }
  else
  {
  idcount=0;
  if (p!=0) 
   {
  while(result.next())
  {
  flag=1;
  String  readonly=null;
  if(result.getString("FABRIC_FORM")!=null && result.getString("FABRIC_FORM").equals("Roll"))
            {
            readonly="readonly";
            }
 %>
  <tr>
 <td class="label-1" style="font-size:15pt;" >
  <input  type="TEXT" class="newst1" readonly="readonly" value="<%=result.getString("roll_no")%>" maxlength="10"  name="ROLL_NO" >
 
 </td>
  <td class="label-1" style="font-size:15pt;" >
  <input  type="TEXT" class="newst1" readonly="readonly" value="<%=result.getString("ROLL_QTY")%>" maxlength="10"  name="ROLL_NOlength" >
 
 </td>
 <td class="label-1" style="font-size:15pt;" >
 
 <%if(result.getString("SHADE_LOT")==null){
 %>
 <input  type="TEXT" class="newst" maxlength="10"  id="SHADE_LOT_ID<%=idcount%>"  value="1" name="SHADE_LOT<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)">
<%
 }else{%>
 <input  type="TEXT" class="newst" maxlength="10" id="SHADE_LOT_ID<%=idcount%>" value="<%=result.getString("SHADE_LOT")%>" name="SHADE_LOT<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)">
<%}%>
</td>
<td>
 <select name="SHADE_GRP<%=result.getString("roll_no")%>" id="SHADE_GRP_ID<%=idcount%>"   style="font-size:18pt;width:56pt">
  <option value="">GRP</option>
 <%for(int mmm=0; mmm<group.length; mmm++ ){
 if(group[mmm].equals(result.getString("SHADE_GRP")))
 {%>
 <option selected="selected" value="<%=group[mmm]%>"><%=group[mmm]%></option>
 <%}else{
 %>
 <option value="<%=group[mmm]%>"><%=group[mmm]%></option>
 <%}}%>
 </select>
 </td>

 <%if(result.getString("MIN_WIDTH")==null){%>
  <td><input  type="TEXT" class="newst" id="MIN_WIDTH_ID<%=idcount%>" maxlength="10" value="0"  name="MIN_WIDTH<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)"></td>
 <td><input  type="TEXT" class="newst" id="MAX_WIDTH_ID<%=idcount%>" maxlength="10" value="0"  name="MAX_WIDTH<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)"></td>

 <%}else{%>
 <td> <input  type="TEXT" class="newst" id="MIN_WIDTH_ID<%=idcount%>" maxlength="10" value="<%=result.getString("MIN_WIDTH")%>" name="MIN_WIDTH<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)"></td>
 <td><input  type="TEXT" class="newst" id="MAX_WIDTH_ID<%=idcount%>" maxlength="10" value="<%=result.getString("MAX_WIDTH")%>" name="MAX_WIDTH<%=result.getString("roll_no")%>"  onfocus="selectobj(this.name)"></td>

 <%}%>
 
<td>
 <select name="CS<%=result.getString("roll_no")%>" id="CS_ID<%=idcount%>" style="font-size:19pt;width:80pt">>
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
 <td>
 <select name="LWV<%=result.getString("roll_no")%>" id="LWV_ID<%=idcount%>" style="font-size:19pt;width:90pt"> >
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
 <option value="No LWV">No LWV</option>
   <option value="Minor">Minor</option>
 <option value="Major">Major</option>
 <option value="Critical">Critical</option>
 
 
 <%}%>

 </select>
 
 </td>
 
  <!--td-->
 <%if(result.getString("CUT_PCS")==null){%>
 <input type="hidden" class="newst5" id="CUT_PCS_ID<%=idcount%>"  name="CUT_PCS<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" class="newst5" id="CUT_PCS_ID<%=idcount%>"    value="<%=result.getString("CUT_PCS")%>"  name="CUT_PCS<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}%>
 <!--/td-->
  <%if(readonly==null)
 {%>
 
<!--td-->
 <%if(result.getString("ROLL_BOWING")==null){%>
 <input type="hidden" class="newst5" id="ROLL_BOWING_ID<%=idcount%>" <%=readonly%>   name="ROLL_BOWING<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" class="newst5" id="ROLL_BOWING_ID<%=idcount%>"  <%=readonly%> value="<%=result.getString("ROLL_BOWING")%>"  name="ROLL_BOWING<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}%>
 <!--/td>
 <td-->
 <%if(result.getString("FOLD_LENGTH")==null){%>
 <input type="hidden" class="newst5" id="FOLD_LENGTH_ID<%=idcount%>" onblur="validatenumber(this)" <%=readonly%>   name="FOLD_LENGTH<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}else{%>
 <input type="hidden" class="newst5" id="FOLD_LENGTH_ID<%=idcount%>" onblur="validatenumber(this)" <%=readonly%> value="<%=result.getString("FOLD_LENGTH")%>"  name="FOLD_LENGTH<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 <%}%>
 <!--/td-->
 <%}%>
 
 <!--td-->
 <%if(result.getString("ROLL_SHRK_PRCT")==null){%>
 <input type="hidden" class="newst5" id="ROLL_SHRK_PRCT_ID<%=idcount%>" onblur="validatenumber(this)" value=""  name="ROLL_SHRK_PRCT<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 
 <%}else{%>
 <input type="hidden" class="newst5" id="ROLL_SHRK_PRCT_ID<%=idcount%>" onblur="validatenumber(this)" value="<%=result.getString("ROLL_SHRK_PRCT")%>"  name="ROLL_SHRK_PRCT<%=result.getString("roll_no")%>" onfocus="selectobj(this.name)">
 
 <%}%>
 <!--/td-->
 <td>
  <input type="checkbox" style="width:18px;height:18px" value="<%=result.getString("roll_no")%>" name="rollstic">
 </td>
 </tr>
 <%
   i++ ;
   idcount++;
   }
  }
  }
  }
  %>
  </table>
   <table cellpadding="2" cellspacing="1"><tr><td class="label-1">Machine IP</td><td class="label-1"><%=request.getRemoteAddr() %></td></tr></table>
 
  </td>
  </tr><tr>
  <td valign="top" style="padding:5pt;" align="center">
  <table align="center" cellpadding="8"  style="border-color:blue;border-style:solid;">
  <tr><td>
    
    <table>
   <!--<tr>
    <td><input type="BUTTON" name="0" value="." class="btn" onclick="AddDigit('.')"></td>
     <td colspan="2" ><input type="BUTTON" name="backspace"  value="Backspace" class="btn" style="width:200pt" onclick="backclear()" ></td>
    
     </tr>
     -->
    <tr>
     <td><input type="BUTTON" name="." value="." class="btn" onclick="AddDigit('.')"></td>
    <td><input type="BUTTON" name="0" value="0" class="btn" onclick="AddDigit(0)"></td>
    <td><input type="BUTTON" name="1" value="1" class="btn" onclick="AddDigit(1)"></td>
    <td><input type="BUTTON" name="2" value="2" class="btn" onclick="AddDigit(2)"></td>
     <td><input type="BUTTON" name="3" value="3" class="btn" onclick="AddDigit(3)"></td>
  
    
   
    <td><input type="BUTTON" name="4" value="4" class="btn" onclick="AddDigit(4)"></td>
    <td><input type="BUTTON" name="5" value="5" class="btn" onclick="AddDigit(5)"></td>
    <td><input type="BUTTON" name="6" value="6" class="btn" onclick="AddDigit(6)"></td>
    
   
    
    
    <td><input type="BUTTON" name="7" value="7" class="btn" onclick="AddDigit(7)"></td>
    <td><input type="BUTTON" name="8" value="8" class="btn" onclick="AddDigit(8)"></td>
    <td><input type="BUTTON" name="9" value="9" class="btn" onclick="AddDigit(9)"></td>
    
    
     <td ><input type="BUTTON" name="clear" style="background-color:red;width:65pt" value="Clear" class="btn" onclick="clear1();" ></td>
     
          <td ><input type="BUTTON" name="clear" style="background-color:Green;width:65pt" value="Print" class="btn" onclick="printstickerroll('1')" ></td>
     
          
     
      <!-- <td ><input type="Button" value="Enter" style="background-color:#1e721e" onclick="nextrecfocu()" class="btn"></td>-->
    </tr>
 
        <input type="HIDDEN" name="inputobg" value="SHADE_LOT<%=r%>">
      
    </table>
    </td></tr></table>
    </td></tr></table>
    
   
    
    
 <input type="hidden"  name="emp_code" value="<%=emp_code.trim()%>">
 
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
alert("Record(s) Saved !")
</script>
<%}else{%>
<script language="javascript">
alert("Record(s) Not Saved !")
</script>

<%}
}%>
  </form>
  </body>
</html>
