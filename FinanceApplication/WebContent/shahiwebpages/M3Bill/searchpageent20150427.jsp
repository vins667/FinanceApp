<%-- 
    Document   : searchpage
    Created on : Aug 6, 2013, 10:53:37 AM
    Author     : RANJEET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="css/style.css">  

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sepl</title>
 <link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>
<script src="js/fixedheader/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="js/fixedheader/table-fixed-header.css">
<script src="js/fixedheader/jquery-ui-1.7.2.custom.min.js"></script> 
<script src="js/fixedheader/jquery.chromatable.js"></script>
         <script language="javascript">
            function ent_key(e)
 {if(!e)
  {e = window.event;
  }
   if(e.keyCode == 13)
   {
       document.getElementById('prepage').style.visibility='';
            document.frmbuy.action="searchpagembillentAction.action";
   
    document.frmbuy.submit();
   }
 }
 
 function callme()
  {
   var index=document.frmbuy.listname.selectedIndex;
    <s:if test="%{SEARCH_TYPE==10}">
    var CODE   = window.parent.document.frmname.DEPT_SL_NO;
    var NAME   = window.parent.document.frmname.DEPT_DESC;
    CODE.value=document.frmbuy.listname.options[index].value;
    NAME.value=document.frmbuy.listname.options[index].text;
   
    window.parent.document.getElementById('formId').action='newmstbillmbillentAction.action';
    window.parent.document.getElementById('formId').submit();
    
    //window.parent.show_details();
    //window.parent.show_details3();
    </s:if>
    <s:if test="%{SEARCH_TYPE==133333 }">
        var CODE   = window.parent.document.frmname.<s:property value="TXTID"/>;
       
    CODE.value=document.frmbuy.listname.options[index].value;
    </s:if>
    <s:if test="%{SEARCH_TYPE==3 }">
    var CODE   = window.parent.document.frmname.CC_CODE<s:property value="TXTID"/>;
     var DESC   = window.parent.document.frmname.CC_DESC<s:property value="TXTID"/>;
      
    CODE.value=document.frmbuy.listname.options[index].value;
    DESC.value=document.frmbuy.listname.options[index].text
    </s:if>
    window.parent.approveraddidClose();
  }
  
  function addvalue(a)
  {
    window.parent.document.frmname.BILL_NO.value=document.getElementById('APSINO'+a).value;
    window.parent.document.frmname.BILL_DATE.value=document.getElementById('APIVDT'+a).value;
    var fromstr=document.getElementById('APIVDT'+a).value;
     window.parent.document.frmname.BILL_DATE1.value=fromstr.substring(3, fromstr.length);
      window.parent.document.frmname.BILL_DATE2.value=fromstr.substring(3, fromstr.length);
    
    window.parent.document.frmname.SUPPLIER_CODE.value=document.getElementById('APSUNO'+a).value;
    window.parent.document.frmname.SUPPLIER_DESC.value=document.getElementById('IDSUNM'+a).value;
    var amt=eval(document.getElementById('APCUAM'+a).value)
      window.parent.document.frmname.BILL_AMOUNT.value=amt.toFixed(2);
      
      window.parent.document.frmname.BREAK_AMOUNT[0].value=amt.toFixed(2);
      window.parent.document.frmname.BRKTOTAL.value=amt.toFixed(2);
      window.parent.document.frmname.GROSS_AMOUNT.value=amt.toFixed(2);
      
      window.parent.document.frmname.BILL_WHLO.value=document.getElementById('BILL_WHLO'+a).value;
      window.parent.document.frmname.BILL_YEAR.value=document.getElementById('BILL_YEAR'+a).value;
      if(document.getElementById('REVERSE_SRVTAX'+a).value==1)
          {
              window.parent.document.frmname.REVERSE_SRVTAXTEMP.disabled=false;
              window.parent.document.frmname.REVERSE_SRVTAXTEMP.checked=true;
              window.parent.document.frmname.REVERSE_SRVTAXTEMP.disabled=true;
              window.parent.document.frmname.REVERSE_SRVTAX_RATE.disabled=false;
              window.parent.document.frmname.REVERSE_SRVTAX_RATE.value=document.getElementById('REVERSE_SRVTAX_RATE'+a).value;
      
          }else{
                window.parent.document.frmname.REVERSE_SRVTAX_RATE.value="";
              window.parent.document.frmname.REVERSE_SRVTAX_RATE.disabled=true;
      
              window.parent.document.frmname.REVERSE_SRVTAXTEMP.disabled=false;
              window.parent.document.frmname.REVERSE_SRVTAXTEMP.checked=false;
              window.parent.document.frmname.REVERSE_SRVTAXTEMP.disabled=true;
              window.parent.document.frmname.REVERSE_SRVTAX_RATE.disabled=true;
              
          }
           window.parent.document.frmname.REVERSE_SRVTAX.value=document.getElementById('REVERSE_SRVTAX'+a).value;
             
         window.parent.getsrvtxtfun();
         
   window.parent.approveraddidClose();   
      
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
  
  
  
  $(document).ready(function(){
    // make the header fixed on scroll
    $("#searchenttable").chromatable({
        width: "100%", // specify 100%, auto, or a fixed pixel amount
        height: "300px",
        scrolling: "yes" // must have the jquery-1.3.2.min.js script installed to use
    });
});
       </script>
       
    </head>
    <body class="body1" onload="waitPreloadPage();document.getElementById('SEARCH_CODE').focus()" style="background-color: #f3f3f3" >
   
     <DIV align="center" id="prepage" class="lodingdiv"  style="position:absolute;left:150px;padding-top: 150px" >
<img src="../css/image/progress.gif" >
<br>
<B >Loading ... ... Please wait ...</B>
</DIV>
<form action=""  method="post" name="frmbuy" >
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
       <table width="100%">
           <tr><td class="label-1">Search:</td><td><s:textfield name="SEARCH_CODE" id="SEARCH_CODE" theme="simple" onkeypress="ent_key()" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/></td>
    </tr><tr>
        <td colspan="2">
            <s:if test="%{SEARCH_TYPE==11}">
                <table id="searchenttable" style="font-size: 10px" class="table table-bordered table-striped table-fixed-header">
				<thead>
					<tr style="background-color: #dfdfdf;font-size: 9px">
						<th style="text-align: left;" class="label-1" >Whlo</th>	
                                                <th style="text-align: left;" class="label-1">Bill No</th>
                                                <th style="text-align: left;" class="label-1">Bill Date</th>
                                                <th style="text-align: left;" class="label-1">Supplier</th>
                                                 <th style="text-align: left;" class="label-1">Amount</th>
					         <th style="text-align: left;" class="label-1">Action</th>
									
					</tr>
				</thead>
                                <tbody> 
                <s:iterator value="suppamtlist" status="st">
                    <tr style="background-color: #FFFFFF;">
                        <td>&nbsp;<s:property value="%{WAREHOUSE}"/></td>
                        <td>
                            <s:hidden name="APSUNO" value="%{APSUNO}" id="APSUNO%{#st.index}"/>
                            <s:hidden name="IDSUNM" value="%{IDSUNM}" id="IDSUNM%{#st.index}"/>
                            <s:hidden name="APSINO" value="%{APSINO}" id="APSINO%{#st.index}"/>
                            <s:hidden name="APIVDT" value="%{APIVDT}" id="APIVDT%{#st.index}"/>
                             <s:hidden name="APCUAM" value="%{APCUAM}" id="APCUAM%{#st.index}"/>
                             
                              <s:hidden name="BILL_WHLO" value="%{WAREHOUSE}" id="BILL_WHLO%{#st.index}"/>
                               <s:hidden name="BILL_YEAR" value="%{CYEAR}" id="BILL_YEAR%{#st.index}"/>
                               <s:hidden name="REVERSE_SRVTAX" value="%{REVERSE_SRVTAX}" id="REVERSE_SRVTAX%{#st.index}"/>
                               <s:hidden name="REVERSE_SRVTAX_RATE" value="%{REVERSE_SRVTAX_RATE}" id="REVERSE_SRVTAX_RATE%{#st.index}"/>
                               
                        &nbsp;<s:property value="%{APSINO}"/>    
                        </td>
                        
                         <td>&nbsp;<s:property value="%{APIVDT}"/></td>
                         <td>&nbsp;<s:property value="%{APSUNO}"/>-<s:property value="%{IDSUNM}"/></td>
                         <td>&nbsp;
                              <s:text name="product.req">
                               <s:param name="value" value="%{APCUAM}"/> 
                               </s:text>
                             </td>
                         <td>&nbsp;
                             <input type="radio" name="rd" onclick="addvalue(<s:property value="%{#st.index}"/>)"/>
                             </td>
                    </tr>   
                </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
            <s:select id="listid" size="20" ondblclick="callme();" onkeypress="callme();" cssStyle="width:570px" name="listname" value="" list="mastlist" listKey="EAAITM" listValue="EATX40"  theme="simple" />
             </s:else>
            <s:hidden name="SEARCH_TYPE" id="SEARCH_TYPE" value="%{SEARCH_TYPE}"/>
            <s:hidden name="TXTID" id="TXTID" value="%{TXTID}"/>
            <s:hidden name="SDPT" id="SDPT" value="%{SDPT}"/>
            <s:hidden name="DEPT_SL_NO" value="%{DEPT_SL_NO}"/>
            
        </td>
   </tr>

                        
                        
                        </table>
            </td></tr>
           <tr>
            <td height="1pt"  align="center" style="color:red;font-weight:bold">
                <div style="height:5pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />
                   
                </div>


            </td>
        </tr>
                  </table>

      

                  </td></tr></table>
                    

  </form>


      
    </body>
</html>
