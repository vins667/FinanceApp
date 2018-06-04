<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<script type="text/javascript" src="js/dom-drag.js"></script>

<!-- <script src="js/jquery-1.9.1.min.js"></script> -->
 <script src="js/jquery.js"></script>
 <script src="js/jquery.table.addrow.js"></script>
  <script src="js/mvxexp.js"></script>
<html>  
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('../../image/table-gradient.jpg');
            }
            tbody {
                height: 300px;
                overflow-y: auto;
                overflow-x: hidden;
            }
            .root
            {
                position:absolute;
                height:200px;
                width:800px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .handle
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px
                    
            }  
        </style>
        <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 30px ;
        
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);                     
                  }
                tbody {
                    height: auto;
                }
                tfoot{
                    background:#3383bb;
                    font-weight:bold;
                }
                .tr2 {
                     position:absolute;
                     bottom:expression(this.offsetParent.scrollTop);
                  }
            </style>
        <![endif]-->
        
        
        <script language="javascript">
            
            
if (typeof window.event != 'undefined') // IE
    document.onkeydown = function() // IE
    {

       if(event.keyCode==13)
          {event.keyCode=9}


        var t=event.srcElement.type;
        var kc=event.keyCode;


        return ((kc != 8 && kc != 13) || ( t == 'text' &&  kc != 13 ) ||
            (t == 'textarea') || ( t == 'submit' &&  kc == 13))
    }
else
    document.onkeypress = function(e)  // FireFox/Others
    {
        return tabOnEnter(e.target,e)

        aaaa=e.keyCode;
       if(aaaa==13 )
          {

        
      }
        return true;
  
    }
            function searchdetail()
            {   if((document.frm.searchinv.value=="" && document.frm.searchplan.value=="") )
                   {
                      alert("Please Enter Plan No or Invoice No ")
                      document.frm.searchplan.focus();
                      return false;

                  }
                document.frm.action="PREINVMVX.action?viewFlag=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
             }
            function GetMovexData()
            {
                document.frm.action="PREINVMVX.action?viewFlag=YES&GETREFRESH=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function COAPRV()
            { 
                document.frm.action="PREINVMVX.action?CHECK_APRV=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
       function validaterec()
        { if(document.frm.cost_centre.value=="") 
            {
             alert("Please Enter PCH !!");
              document.frm.cost_centre.focus();
              return false;
            }
             if(document.frm.mode_of_ship.value=="") 
            {
             alert("Please Enter Mode of Ship !!");
              document.frm.mode_of_ship.focus();
              return false;
            }
             if(document.frm.ac_holder.value=="") 
            {
             alert("Please Enter AC Holder !!");
              document.frm.ac_holder.focus();
              return false;
            }
            
             if(document.frm.mode_of_ship.value=="") 
            {
             alert("Please Enter Mode of Ship !!");
              document.frm.mode_of_ship.focus();
              return false;
            }
           if(document.frm.PLACE.value=="") 
          {
             alert("Please Enter Place !!");
              document.frm.PLACE.focus();
              return false;
            }
          if(document.frm.CLR_PORT.value=="") 
          {
             alert("Please Enter Clearing Port !!");
              document.frm.CLR_PORT.focus();
              return false;
            }
            if(document.frm.LOADING_PORT.value=="") 
          {
             alert("Please Enter LOADING_PORT !!");
              document.frm.LOADING_PORT.focus();
              return false;
            }
            if(document.frm.DISCHARGE.value=="") 
          {
             alert("Please Enter DISCHARGE !!");
              document.frm.DISCHARGE.focus();
              return false;
            }
            if(document.frm.DIS_CNTRY.value=="") 
           {
             alert("Please Enter Discharge Cntry !!");
              document.frm.DIS_CNTRY.focus();
              return false;
            }
             if(document.frm.DISCHARGE.value=="") 
           {
             alert("Please Enter Discharge  !!");
              document.frm.DISCHARGE.focus();
              return false;
            }
             if(document.frm.CNTRY_ORIGIN.value=="") 
            {
             alert("Please Enter Origin Cntry !!");
              document.frm.CNTRY_ORIGIN.focus();
              return false;
            }
           return true; 
            
       }
         function copyHS()
         {
              if((document.frm.HSCODE_COPY.value=="" ) )
                   {
                      alert("Please Enter HSCODE TO COPY ")
                      document.frm.HSCODE_COPY.focus();
                      return false;
                   }  
                     var HSCODE=document.frm.HSCODE;
                  if(document.getElementById("HSCODE_COPY").value!="")
                    { 
                    for(i=0; i<HSCODE.length; i++) 
                    {
                      HSCODE[i].value=  document.getElementById("HSCODE_COPY").value;
                    }
                   }
               }   
               function copySelectedHS(z) 
                {     
                if(document.getElementById("HSCODE_COPY").value!="")
                 { 
                     document.getElementById('HSCODE'+z).value=document.getElementById("HSCODE_COPY").value;
                 } 
                 
                } 
                 
             function copyMRP()
            {  
              if((document.frm.MRPRATE_COPY.value=="" ) )
                   {
                      alert("Please Enter MRP RATE TO COPY ")
                      document.frm.MRPRATE_COPY.focus();
                      return false;
                   }  
                     var MRP_RATE=document.frm.MRP_RATE;
                  if(document.getElementById("MRPRATE_COPY").value!="")
                    { 
                    for(i=0; i<MRP_RATE.length; i++) 
                    {
                      MRP_RATE[i].value=  document.getElementById("MRPRATE_COPY").value;
                    }
                   }
               }    
               function copySelectedMRP(z) 
                {     
                if(document.getElementById("MRPRATE_COPY").value!="")
                 { 
                     document.getElementById('MRP_RATE'+z).value=document.getElementById("MRPRATE_COPY").value;
                 } 
                 
                }
                
            function copyACCR()
            {   if((document.frm.ACCR_RATE_COPY.value=="" ) )
                   {
                      alert("Please Enter ACCR Price ")
                      document.frm.ACCR_RATE_COPY.focus();
                      return false;

                  }
                document.frm.action="PREINVMVX.action?accrFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function openpop(a)
            {
                
                document.getElementById(a).style.display='';
            }
             function closediv(a)
            {
                document.getElementById(a).style.display='none';
            }
            
        function callcatg(a1,a,b)
        {
            
          document.getElementById(a1).href="catgViewPREINVMVX?PARAA="+a+"&PARAB="+b+"&DESTI_CNTRY="+document.getElementById('DESTI_CNTRY').value; 
            
        }
        function calllic(a1,a)
        {
            document.getElementById(a1).href="prelicviewPREINVMVX?PARAA="+a;  
        }
         function calldbk(a1,a)
        {   
            document.getElementById(a1).href="predbkslviewPREINVMVX?PARAA="+a+"&INVDATE="+document.getElementById('inv_date').value; 
        }
         function callrosl(a1,a)
        {   
            document.getElementById(a1).href="preroslslviewPREINVMVX?PARAA="+a+"&INVDATE="+document.getElementById('inv_date').value; 
        }
         function callstr(a1,a,b)
        {   
            document.getElementById(a1).href="prestrslviewPREINVMVX?PARAA="+a+"&PARAB=STR"+"&dbkslnocopy="+document.getElementById('DBK_SLNO_COPY').value+"&INVDATE="+document.getElementById('inv_date').value; 
        } 
         function callstrmisc(a1,a,b)
        {      
            document.getElementById(a1).href="prestrslviewPREINVMVX?PARAA="+a+"&PARAB=STRMISC"+"&INVDATE="+document.getElementById('inv_date').value; 
        }  
        function callBE(a1,a)
        { 
            document.getElementById(a1).href="prebeviewPREINVMVX?PARAA="+a+"&LIC_TYPE="+document.getElementById('REF_TYPE'+a).value+"&LIC_NO="+document.getElementById('REF_NO'+a).value; 
        }
    function copyCatg() 
    {    var PRICE_FC_MOVEX=document.frm.PRICE_FC_MOVEX;
         var CATG_CODE=document.frm.CATG_CODE;
         var INV_DESC=document.frm.INV_DESC; 
         var DBK_SLNO=document.frm.DBK_SLNO;
         var ROSL_SLNO=document.frm.ROSL_SLNO;
         var STR_SLNO=document.frm.STR_SLNO;
         var STR_MISC=document.frm.STR_MISC;
         var PRICE_MISC=document.frm.PRICE_MISC;
         var PRICE_FC_E=document.frm.PRICE_FC_E;
         var MADE_FOR=document.frm.MADE_FOR;
         var ADJUST_FC=document.frm.ADJUST_FC;
         if(CATG_CODE.length>0)
             {  
                 if(document.getElementById("CATG_CODE_COPY").value!="")
                 { 
                for(i=0; i<CATG_CODE.length; i++) 
                    {
                      CATG_CODE[i].value=  document.getElementById("CATG_CODE_COPY").value;
                      DBK_SLNO[i].value=  document.getElementById("DBK_SLNO_COPY").value;
                      ROSL_SLNO[i].value=  document.getElementById("ROSL_SLNO_COPY").value;
                      STR_SLNO[i].value=  document.getElementById("STR_SLNO_COPY").value;
                      STR_MISC[i].value=  document.getElementById("STR_MISC_COPY").value;
                     
                      PRICE_MISC[i].value=  document.getElementById("PRICE_MISC_COPY").value;
                      
                  //    PRICE_FC_E[i].value=parseFloat(eval(document.getElementById("PRICE_FC_E"+i).value)) - eval(document.getElementById("PRICE_MISC_COPY").value);
                      PRICE_FC_E[i].value=parseFloat(eval(document.getElementById("PRICE_FC_MOVEX"+i).value)) - eval(document.getElementById("PRICE_MISC_COPY").value);
                      MADE_FOR[i].value=  document.getElementById("SHIP_TYPE_COPY").value;
                      ADJUST_FC[i].value=  document.getElementById("ADJUST_FC_COPY").value;
                       
                    } 
                 }  
                 
                  if(document.getElementById("CATG_DESC_COPY").value!="")
                 {
                 for(i=0; i<CATG_CODE.length; i++) 
                    {
                      INV_DESC[i].value=  document.getElementById("CATG_DESC_COPY").value;
                     
                    }
                 }
                 
             }else{
                 
                  if(document.getElementById("CATG_CODE_COPY").value!="")
                 {
                     CATG_CODE.value=  document.getElementById("CATG_CODE_COPY").value;
                     DBK_SLNO.value=  document.getElementById("DBK_SLNO_COPY").value;
                     ROSL_SLNO.value=  document.getElementById("ROSL_SLNO_COPY").value;
                     STR_SLNO.value=  document.getElementById("STR_SLNO_COPY").value;
                     STR_MISC.value=  document.getElementById("STR_MISC_COPY").value;
                     PRICE_MISC.value=  document.getElementById("PRICE_MISC_COPY").value;
                    }
                 
                  if(document.getElementById("CATG_DESC_COPY").value!="")
                 {
                 
                      INV_DESC.value=  document.getElementById("CATG_DESC_COPY").value;
                 }
             }
    } 
     function copySelected(x) 
     {     
                if(document.getElementById("CATG_DESC_COPY").value!="")
                 {
                     document.getElementById('INV_DESC'+x).value=document.getElementById("CATG_DESC_COPY").value;
                 } 
                 if(document.getElementById("CATG_CODE_COPY").value!="")
                 {
                     document.getElementById('CATG_CODE'+x).value=document.getElementById("CATG_CODE_COPY").value;
                 } 
                 if(document.getElementById("DBK_SLNO_COPY").value!="")
                 {
                     document.getElementById('DBK_SLNO'+x).value=document.getElementById("DBK_SLNO_COPY").value;
                 } 
                 if(document.getElementById("ROSL_SLNO_COPY").value!="")
                 {
                     document.getElementById('ROSL_SLNO'+x).value=document.getElementById("ROSL_SLNO_COPY").value;
                 } 
                 if(document.getElementById("STR_SLNO_COPY").value!="")
                 {
                     document.getElementById('STR_SLNO'+x).value=document.getElementById("STR_SLNO_COPY").value;
                 } 
                 if(document.getElementById("STR_MISC_COPY").value!="")
                 {
                     document.getElementById('STR_MISC'+x).value=document.getElementById("STR_MISC_COPY").value;
                 } 
                 if(document.getElementById("PRICE_MISC_COPY").value!="0.0")
                 { 
                    document.getElementById('PRICE_MISC'+x).value=document.getElementById("PRICE_MISC_COPY").value;
                 }
      } 
    function validateSTR()
    {  
       var PRICE_FC_MOVEX=document.frm.PRICE_FC_MOVEX;
       var STR_SLNO=document.frm.STR_SLNO; 
       var STR_MISC=document.frm.STR_MISC; 
       var PRICE_MISC=document.frm.PRICE_MISC; 
       var CATG_CODE=document.frm.CATG_CODE; 
       var PRICE_FC_E=document.frm.PRICE_FC_E; 
       var PRICE_MISC=document.frm.PRICE_MISC; 
       var ADJUST_FC=document.frm.ADJUST_FC;
       if(PRICE_FC_MOVEX.length>0)
            {    t1=0;  h1=0;
                 t2=0;
                for(i=0; i<PRICE_FC_MOVEX.length; i++)
                    { 
                       if(document.frm.CNTRY_ORIGIN.value=="") 
                        {
                         alert("Please Enter Origin Cntry !!");
                          document.frm.CNTRY_ORIGIN.focus();
                          return false;
                        }
                      if(CATG_CODE[i].value=="")
                      {
                       alert("Please Enter Category ....");
                        return false;
                      }
                      if(STR_SLNO[i].value=="")
                      {
                       alert("Please Enter STR SLNO....");
                        return false;
                      }   
                    
                      h1=parseFloat(PRICE_MISC[i].value) ;
                     
                      if (ADJUST_FC[i].value>=.01) 
                      { 
                          alert("Check Adjust FC ");
                           return false;
                      }
                    
                          
                      if (STR_MISC[i].value=="" && h1!=0)
                      { 
                          alert("STR Misc required for Price Misc ");
                           return false; 
                      } 
                     
                      if (STR_MISC[i].value!="" &&  h1==0)
                      {
                          alert("Price Misc required for STR Misc. ");
                           return false;  
                      } 
                        
                              t1=parseFloat(PRICE_FC_E[i].value) ;
                              t2=parseFloat(PRICE_MISC[i].value);
                            
                            var t=parseFloat(eval(PRICE_FC_E[i].value)+eval(PRICE_MISC[i].value)).toFixed(4);
                            
                              if(t>parseFloat(PRICE_FC_MOVEX[i].value).toFixed(4))
                              { 
                                  alert("Please Check Price FC");
                                  PRICE_FC_E[i].value=PRICE_FC_MOVEX[i].value;
                                  PRICE_MISC[i].value="0.0";
                                  return false;
                              }
                    } 
                }  
                 
                return true;
             }
     
    function validateLIC()
    {   
       var REF_NO=document.frm.REF_NO;
       var REF_TYPE=document.frm.REF_TYPE;
       var IO_NORMS=document.frm.IO_NORMS; 
       var QTY_SQM=document.frm.QTY_SQM; 
       var BE_NO=document.frm.BE_NO; 
       var ADJS_QTY=document.frm.ADJS_QTY; 
       var FOB_AMT=document.frm.FOB_AMT; 
       var LIC_AMT_OB=document.frm.LIC_AMT_OB; 
       var LIC_AMT_ISSUE=document.frm.LIC_AMT_ISSUE; 
       var LIC_QTY_OB=document.frm.LIC_QTY_OB; 
       var LIC_QTY_ISSUE=document.frm.LIC_QTY_ISSUE; 
       
       if(REF_NO!=null)
            {    t1=0;
                 t2=0;
                for(i=0; i<REF_NO.length; i++)
                    {     
                    if(REF_NO[i].value!="") 
                    {
                     
                      if(IO_NORMS[i].value=="" && REF_TYPE[i].value!="EPCG")
                      {
                       alert("Please Enter Licence IO Norms ....");
                        return false;
                      } 
                      if(QTY_SQM[i].value=="" && REF_TYPE[i].value!="EPCG")
                      {
                       alert("Please Enter SQM QTY....");
                        return false;
                      }
                        if ((BE_NO[i].value=="" && REF_TYPE[i].value!="EPCG")  )
                      {
                          alert("Please Select BE No.... ");
                           return false;
                      } 
                           t1=eval(eval(LIC_AMT_OB[i].value));
                           t2=eval(FOB_AMT[i].value)+eval(LIC_AMT_ISSUE[i].value) ;
                           
                           if (t1>t2)
                            { alert('Check Licence Export Obligation Value --> '+t1+'  Utilize Value -->'+t2)}   
                  }
                } 
              }
             
                 return true;
         }
         
    function validateExcise()
    {    
       var MRP_RATE=document.frm.MRP_RATE;
       var EXCISE_UNIT=document.frm.EXCISE_UNIT;
       
        
        if(MRP_RATE!=null || MRP_RATE!=0)
            {    t1=0;  h1=0;
                 t2=0;
                for(i=0; i<MRP_RATE.length; i++)
                    { 
                          t1=parseFloat(MRP_RATE[i].value) ;
                         if(t1[i]>0) 
                           {
                             if(t1<1000 && EXCISE_UNIT!="")
                              { 
                                  alert("Exicse unit not Required for MRP Rate<1000");
                                  return false;
                              } 
                              if(t1>=1000 && EXCISE_UNIT=="")
                              { 
                                  alert("Exicse unit  Required for MRP Rate>=1000");
                                  return false;
                              }
                    } }
                } 
                
                return true; 
             }
     function isNumber(evt) {
                    evt = (evt) ? evt : window.event;
                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                     alert('Invalid Input, Only Numbers Allowed');
                    return false;
                    }
                    return true;
                }
                
         function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,4}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
                    
            		return false;
            	}
             
            	return true;
            }
            
      function deletetablerow(x){ 
                
        document.getElementById('REF_NO'+x).value="";
        document.getElementById('REF_TYPE'+x).value="";
        document.getElementById('QTY'+x).value="";
        document.getElementById('ADJS_QTY'+x).value="";
        document.getElementById('FOB_AMT'+x).value="";
        document.getElementById('IO_NORMS'+x).value="";
        document.getElementById('QTY_SQM'+x).value="";
        document.getElementById('BE_NO'+x).value="";
        document.getElementById('BE_DESC'+x).value="";
      
    } 
     function validatenumber1(a,b)
            {
            	k=a.value; 
            //	if (k!="" && !k.match(/^\d+$|^\d+\$/ ) )
                if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,4}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="0";
                        LicSqmCal(b);
                        a.focus();
            		return false;
            	}
              LicSqmCal(b);
            	return true;
            }
             
         function copyFcTotal(id)  
         { var t=0;
              
             document.getElementById('FOB_AMT'+id).value=document.getElementById('FCTOTAL').value ;
             document.getElementById('QTY'+id).value=document.getElementById('QTYTOTAL').value ;
             document.getElementById('ADJS_QTY'+id).value=document.getElementById('QTYTOTAL').value ;
             
       }
            
             
    function LicSqmCal(a)
{
     var t=0;  
      if(document.getElementById('QTY'+a).value!="")
        {
            document.getElementById('ADJS_QTY'+a).value=document.getElementById('QTY'+a).value;
            t=eval(document.getElementById('QTY'+a).value)*eval(document.getElementById('IO_NORMS'+a).value);
          }
         
        document.getElementById('QTY_SQM'+a).value=t.toFixed(2);
         
        
  }
         function saverec()
         {//if(validaterec()   
            //    {
              if (validaterec() && validateSTR() && validateLIC() && validateExcise()){
                    document.frm.action="saveinvPREINVMVX.action?saveFlag=YES";
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility='';   
                   }
            //   }
          }
          function generateExcise()
          { if(document.frm.EXCISE_UNIT.value=="") 
            {
              alert("Please Select Excise Unit !!");
              document.frm.EXCISE_UNIT.focus();
              return false;
            }
          
                    document.frm.action="exciseinvPREINVMVX.action?exciseFlag=YES";
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility=''; 
           
          }
           
        function deletetablerowaccr(x){
                //var id1=document.getElementById(x);   
                //alert('aa'+x);
                 //document.getElementById('tableaaccr').deleteRow(x);
                 $('#' + x).remove();
         }  
        function addsubtitleq1(a)
            {
                  
               var CO_NO_E=document.frm.CO_NO_E;
               var CO_LINE_E=document.frm.CO_LINE_E;
               var ITEM_NO_E=document.frm.ITEM_NO_E;
               var QTY_ENDORS_E=document.frm.QTY_ENDORS_E;
               var ACCRTYPE=document.getElementById("ACCRTYPE").value;
               var DBK_ACCR_COPY=document.getElementById("DBK_ACCR_COPY").value;
               var STR_ACCR_COPY=document.getElementById("STR_ACCR_COPY").value;
               var ACCR_RATE_COPY=document.getElementById("ACCR_RATE_COPY").value;
               
               if (typeof CO_NO_E.length=="undefined")
               {   
                   
                    var k=eval(document.getElementById('rownum').value)+eval(document.getElementById('accrctn').value);
                    var str1 = '<tr bgcolor="#FFFFFF" id='+k+'><td><s:textfield name="ACCR_CO_NO" readonly="true"  value="'+CO_NO_E.value+'" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_CO_LINE" readonly="true" value="'+CO_LINE_E.value+'" theme="simple" cssClass="texts" cssStyle="width:60px"/> </td>';
                     str1 += '<td><s:textfield name="ACCR_ITEM_NO" readonly="true" value="'+ITEM_NO_E.value+'"  theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_TYPE" readonly="true" value="'+ACCRTYPE+'" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_DBKSLNO" readonly="true" value="'+DBK_ACCR_COPY+'" theme="simple" cssClass="texts" cssStyle="width:80px"/> </td>';
                     str1 += '<td><s:textfield name="ACCR_STRSLNO" readonly="true" value="'+STR_ACCR_COPY+'" theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_QTY" readonly="true" value="'+QTY_ENDORS_E.value+'"  theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
                     str1 += '<td><s:textfield name="ACCR_PRICE"   value="'+ACCR_RATE_COPY+'" theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
                     str1 += '<td> <input type="button" name="btn" id="'+k+'" value="X" onclick="deletetablerowaccr(this.id)" class="texts"   theme="simple"  cssStyle="width:30px;text-align:right"/></td>';
                   
                     str1 += '</tr>';	
                    // alert(str1);
                    $('#'+a+' tr').last().after(str1);
                    k=eval(k)+1;
                    document.getElementById('rownum').value=eval(document.getElementById('rownum').value)+eval(CO_NO_E.length);
               }
               else
               {
                   
                   var k=eval(document.getElementById('rownum').value)+eval(document.getElementById('accrctn').value);
                    for(i=0; i<CO_NO_E.length; i++){ 
                    var str1 = '<tr bgcolor="#FFFFFF" id='+k+'><td><s:textfield name="ACCR_CO_NO" readonly="true"  value="'+CO_NO_E[i].value+'" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_CO_LINE" readonly="true" value="'+CO_LINE_E[i].value+'" theme="simple" cssClass="texts" cssStyle="width:60px"/> </td>';
                     str1 += '<td><s:textfield name="ACCR_ITEM_NO" readonly="true" value="'+ITEM_NO_E[i].value+'"  theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_TYPE" readonly="true" value="'+ACCRTYPE+'" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_DBKSLNO" readonly="true" value="'+DBK_ACCR_COPY+'" theme="simple" cssClass="texts" cssStyle="width:80px"/> </td>';
                     str1 += '<td><s:textfield name="ACCR_STRSLNO" readonly="true" value="'+STR_ACCR_COPY+'" theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
                     str1 += '<td><s:textfield name="ACCR_QTY" readonly="true" value="'+QTY_ENDORS_E[i].value+'"  theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
                     str1 += '<td><s:textfield name="ACCR_PRICE"   value="'+ACCR_RATE_COPY+'" theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
                     
                    str1 += '<td><input type="button" name="btn" id="'+k+'" value="X" onclick="deletetablerowaccr(this.id)" class="texts"  theme="simple"   cssStyle="width:30px;text-align:right"/></td>';
                      str1 += '</tr>';	
                    // alert(str1);
                    $('#'+a+' tr').last().after(str1);
                    k=eval(k)+1;
                  }
                  document.getElementById('rownum').value=eval(document.getElementById('rownum').value)+eval(CO_NO_E.length);
               }
        
            }    
          function xmlhttpreqCOAPRV(YEAR,COMPANY,INV_NO)
         {
             
            var xmlHttpReq = false; 
            var self = this;
            if (window.XMLHttpRequest) {  
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {  
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            }
            self.xmlHttpReq.open('POST', 'coapprAJXPREAction', false); 
            self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            self.xmlHttpReq.onreadystatechange = function() {
               	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
                { 
                      var a = self.xmlHttpReq.responseText;
                      var b= new Array();
                      b=a.split('#');
                      
                      
                        if(b[0]=='Data Not Found')
                        {   
                          
                       }
                        else
                        {
                            document.getElementById('fwd_custom').checked=false;
                           alert(b[0]);
                        }
                      
            	}
            }
           
            self.xmlHttpReq.send("YEAR="+encodeURIComponent(YEAR)+"&COMPANY="+encodeURIComponent(COMPANY)+"&INV_NO="+encodeURIComponent(INV_NO)+"&time="+(new Date()).getTime());
         }
         
      function tabE(obj,e)
          {var e=(typeof event!='undefined')?window.event:e;// IE : Moz
              if(e.keyCode==13)
              {var ele = document.forms[0].elements;
                  for(var i=0;i<ele.length;i++)
                  {var q=(i==ele.length-1)?0:i+1;// if last element : if any other
                      if(obj==ele[i]){
                      	alert(document.getElementById("pre_carriage").value());
                      	
                      	if(obj==document.getElementById("mode_of_ship")){
                      		document.getElementById("pre_carriage").focus();
                      		break;
                      	}
                      	
                      	else if(obj==document.getElementById("pre_carriage")){
                      		document.getElementById("ac_holder").focus();
                      		break;
                      	}
                      	
                      
                      	
                      	}
                  }
                  return false;
              }
          }
          
        </script>
  
    </head> 
  
    <body style="width:100%;height:86%;overflow: hidden;">
        	<DIV align="center" id="prepage" class="lodingdiv" style="right:400px;position:absolute;z-index: 1;visibility: hidden;background: transparent;top:50px;" >
			<img src="image/pleaseWaitOverlay.gif" />			
		</DIV>
        <form action=""   method="post" name="frm"  >
         <input type="hidden" name="rownum" id="rownum" value="0"/>
        <table  width="100%"  cellpadding="0" cellspacing="0">
            <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Pre Shipment Invoice [M4]</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                     <table border="1" align="center" bgcolor="#cccccc" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                     <tr>
                                            <td class="label-1" >Loct : <s:textfield name="searchloct"   readonly="true" cssStyle="text-transform:uppercase;width:40pt" value="%{searchloct}" theme="simple" maxLength="10"/></td>
                                            <td class="label-1" >Invoice No : <s:textfield name="searchinv" cssStyle="text-transform:uppercase;width:70pt" theme="simple" maxLength="10"/></td>
                                            <td class="label-1">Plan No : <s:textfield name="searchplan" cssStyle="text-transform:uppercase;width:70pt" theme="simple" maxLength="10"/>  
                                            <td align="right" style="width: 400px">	 
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                                <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PREINVMVX.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                                <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                             
                                                <s:if test='%{ship_cancel.equals("YES") || fwd_date!=null }'>    
                                                       <button  id="saveheadId" disabled="true" class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                </s:if>
                                                <s:else> 
                                                         <button  id="saveheadId"  class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                </s:else>
                                               <s:if test='%{CRNCY_CODE!=null }'>             
                                                   <button id="printheadId" class="sexybutton" onclick="javascript:window.location.href('printPreInv.jsp?P_INV=<s:property value="%{searchinv}"/>&CRNCY=<s:property value="%{CRNCY_CODE}"/>');"><span><span><span class="print" id="printId">Pdf</span></span></span></button>
                                                </s:if>
                                            
                                            </td>				
                                              
                                        </tr>
                                        
                                    </table> 
                                </td>
                            </tr>
                        </table>
                    </td></tr>
        </table>
            <table   height="86%" border=0 cellpadding="0" width="100%" cellspacing="0">
               <tr><td>
                          <sx:tabbedpanel id="COContainer"  cssClass="style4"  doLayout="true">
                               <sx:div  label="Header"   id="style" closable="false"  errorText="Some Error Occured While Transaction" showErrorTransportText="true"  loadingText="Loading ..." showLoadingText="true">
                                   
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr><td>
                                        <table bgcolor="#f2f2f2" style="margin-top:0pt;" align="center" width="100%" cellpadding="0" cellspacing="0" >
                                            <tr bgcolor="#f2f9fb">  
                                            <tr> 
                                                   <td style="font-size:8pt" align="left" class="label-1">Plan No</td>
                                                        <td  style="font-size:8pt" align="left"><s:textfield name="plan_no" readonly="true"  cssClass="textreadonly"  cssStyle="width:60pt;text-align:right; font-size:9pt;" theme="simple" value="%{plan_no}"/>
                                                               <s:if test='%{GetDataFlag.equals("NO") && fwd_custom==null}'> 
                                                                  <input type="button" name="bnt" style="width:50pt" onclick="GetMovexData()" value="Refresh" class="submitbutton">
                                                              </s:if>
                                                        </td> 
                                                     <td style="font-size:8pt" align="left"  class="label-1">AC Holder</td>
                                                                <td  class="label-1" style="font-size:8pt" align="left">                     
                                                                <s:textfield id="ac_holder" name="ac_holder"  cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;" theme="simple" value="%{ac_holder}" onblur="if(this.value!='') return xmlhttpreqacholder(this)" tabindex="3"/>
                                                                                   <a href="GruptypeViewPREINVMVX.action?PARAA=ac_holder&PARAB=ac_holder&TYPE_CODE=AHN" target="handlefrm" onclick="openpop('root')">
                                                                                  <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                </td> 
                                                     <td style="font-size:8pt" align="left"  class="label-1">PPRQ Date</td>
                                                                             <td  class="label-1" style="font-size:9pt" align="left">
                                                                                 <s:if test='%{pprq_date==null}'>
                                                                                       <input type="checkbox" value="Y" name="pprq_date"  />
                                                                                 </s:if>
                                                                                 <s:else>
                                                                                    <s:checkbox name="pprq1" disabled="true" checked="true" theme="simple" /><s:property value="pprq_date"/>      </s:else> 
                                                                              </td>
                                                                              <td align="left" class="label-1">Ship Cnxl  </td> 
                                                     <td style="font-size:8pt" align="left"> 
                                                                <s:if test='%{ship_cancel.equals("YES") }'> 
                                                                    <input type="checkbox" name="ship_cancel" disabled="true" checked="true" style="height: 9pt"/>
                                                                </s:if>
                                                                <s:elseif test='%{customfwd_auth.equals("YES")}'>
                                                                            <input type="checkbox" value="Y"  name="ship_cancel" id="ship_cancel"/>
                                                                </s:elseif>
                                                                <s:else>          
                                                                            <input type="checkbox" name="ship_cancel" disabled="true"  style="height: 9pt"/>
                                                                 </s:else>
                                                     </td>  
                                                      <td style="font-size:8pt" class="label-1" align="left">Place</td>
                                                                 <td class="label-1" style="font-size:8pt">
                                                                        <s:textfield name="PLACE" id="PLACE" cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{PLACE}" onblur="if(this.value!='') return xmlhttpreqplace(this,'PLACE_DESC')" tabindex="19" />
                                                                                  <a href="CsytabViewPREINVMVX.action?PARAA=PLACE&PARAB=PLACE_DESC&TYPE_CODE=EDES" target="handlefrm" onclick="openpop('root')">
                                                                          
                                                                                      <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                        <s:textfield id="PLACE_DESC" name="PLACE_DESC"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{PLACE_DESC}"/>
                                                                     
                                                                 </td> 
                                              </tr>                    
                                                                                  
                                             <tr>
                                                   <td style="font-size:8pt" class="label-1" align="left">Invoice No</td><td class="label-1" style="font-size:8pt"><s:textfield name="excs_inv_no" readonly="true"  cssClass="textreadonly"  cssStyle="width:60pt;font-size:9pt;text-align:right;" theme="simple" value="%{excs_inv_no}"/></td>
                                                   <td style="font-size:8pt" class="label-1" align="left">Merchant</td> 
                                                         <td  class="label-1" style="font-size:8pt"><s:textfield name="merchant"  cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;" theme="simple" value="%{merchant}" onblur="if(this.value!='') return xmlhttpreqmerchent(this)" tabindex="4" />
                                                              <a href="GruptypeViewPREINVMVX.action?PARAA=merchant&PARAB=merchant&TYPE_CODE=MER" target="handlefrm" onclick="openpop('root')">
                                                              <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/></a>
                                                         </td>
                                                   
                                                        
                                                   <td style="font-size:8pt" class="label-1" align="left">Fwd Custom</td>
                                                    <td  class="label-1" style="font-size:8pt" align="left">
                                                        <s:if test='%{fwd_custom==null}'> 
                                                                <input type="checkbox" value="Y" name="fwd_custom" id="fwd_custom"  onclick="return xmlhttpreqCOAPRV('<s:property value="%{YEAR}"/>','<s:property value="%{COMPANY}"/>','<s:property value="%{INV_NO}"/>')" id="fwd_custom"/>
                                                         </s:if>
                                                         <s:elseif test='%{customfwd_auth.equals("YES")}'>
                                                             <input type="checkbox" checked="true" disabled="true" name="fwd_custom" id="fwd_custom" /><s:property value="fwd_custom"/>& Removed :<input type="checkbox"  value="NN" name="fwd_custom" id="fwd_custom" />
                                                        </s:elseif>         
                                                         <s:else>
                                                              <s:checkbox name="fwd1" disabled="true" checked="true" theme="simple" /><s:property value="fwd_custom"/>
                                                         </s:else> 
                                                           
                                                    </td>              
                                                              <td align="left" class="label-1">Out House</td>
                                                  <td style="font-size:8pt" align="left">
                                                      <s:if test='%{outhouse!=null}'> 
                                                           
                                                                 <input type="checkbox" checked="true" value="Y" disabled="true"  style="height: 9pt"/>
                                                                 <s:hidden name="outhouse" value="%{outhouse}" /> 
                                                      </s:if>
                                                      <s:else> 
                                                                  <input type="checkbox" value="Y" name="outhouse" style="height: 9pt"/>
                                                       </s:else>
                                                  </td>   
                                                  <td style="font-size:8pt" class="label-1" align="left">Clearing Port</td> 
                                                  <td class="label-1" style="font-size:8pt">
                                                               <s:textfield id="CLR_PORT" name="CLR_PORT"   cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{CLR_PORT}"  onblur="if(this.value!='') return xmlhttpreqclearingport(this,'CLR_PORT_DESC')" tabindex="20" />
                                                                    <a href="CsytabViewPREINVMVX.action?PARAA=CLR_PORT&PARAB=CLR_PORT_DESC&TYPE_CODE=HAFE" target="handlefrm" onclick="openpop('root')">
                                                                    <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                               <s:textfield id="CLR_PORT_DESC" name="CLR_PORT_DESC"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{CLR_PORT_DESC}"/>
                                                                     
                                                  </td> 
                                             </tr>  
                                             <tr>
                                                   <td style="font-size:8pt" class="label-1" align="left">Invoice Date</td><td class="label-1" style="font-size:9pt; "><s:textfield name="inv_date" readonly="true"  id="inv_date" cssClass="textreadonly"  cssStyle="width:60pt;font-size:9pt;" theme="simple" value="%{inv_date}"/></td>
                                                   <td style="font-size:8pt" class="label-1" align="left">LC No</td> 
                                                    <td  class="label-1" style="font-size:8pt"><s:textfield name="lcno"  cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;" theme="simple" value="%{lcno}" onblur="if(this.value!='') return xmlhttpreqlcview(this)" tabindex="5"/>
                                                              <a href="lcViewPREINVMVX.action?PARAA=lcno&PARAB=lcno&TYPE_CODE=lcno" target="handlefrm" onclick="openpop('root')">
                                                              <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                    </td> 
                                                   <td style="font-size:8pt" class="label-1" align="left">TTO Date</td>
                                                          <td  class="label-1" style="font-size:9pt" align="left">
                                                               <s:if test='%{tto_date==null}'> 
                                                                    <input type="checkbox" value="Y" name="tto_date" id="tto_date" />
                                                                    
                                                               </s:if><s:else>
                                                                   <s:checkbox name="tt1" disabled="true" checked="true" theme="simple" /><s:property value="tto_date"/>
                                                               </s:else>    
                                                           </td>
                                                   <td style="font-size:8pt" class="label-1" align="left">Upcharge %</td> <td  class="label-1" style="font-size:9pt"><s:textfield name="upcharge_per"   cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;" theme="simple" value="%{upcharge_per}" tabindex="15" /></td>
                                                   <td style="font-size:8pt" class="label-1" align="left">Loading Port</td> 
                                                   <td class="label-1" style="font-size:8pt"> 
                                                               <s:textfield id="LOADING_PORT" name="LOADING_PORT" cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{LOADING_PORT}"  onblur="if(this.value!='')  return xmlhttpreqloadingport(this,'LOADING_PORT_DESC')" tabindex="22" />
                                                                    <a href="CsytabViewPREINVMVX.action?PARAA=LOADING_PORT&PARAB=LOADING_PORT_DESC&TYPE_CODE=HAFE" target="handlefrm" onclick="openpop('root')">
                                                                    <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                               <s:textfield id="LOADING_PORT_DESC" name="LOADING_PORT_DESC"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{LOADING_PORT_DESC}"/>
                                                   </td> 
                                             </tr>  
                                             <tr>
                                                    <td style="font-size:8pt" class="label-1" align="left">Invoice Qnty</td><td class="label-1" style="font-size:9pt"><s:textfield name="inv_qty" readonly="true"  cssClass="textreadonly" cssStyle="width:60pt;text-align:right;font-size:9pt;" theme="simple" value="%{inv_qty}"/></td>
                                                    <td style="font-size:8pt" class="label-1" align="left">Notify</td>
                                                    <td class="label-1" style="font-size:8pt"> 
                                                               <s:textfield id="notify" name="notify"  cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{notify}" onblur="if(this.value!='') return notifysearch(this,'NOTIFY_NAME')" tabindex="6" />
                                                               <s:textfield id="NOTIFY_NAME" name="NOTIFY_NAME"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{NOTIFY_NAME}"/>
                                                                   <a href="notifyViewPREINVMVX.action?PARAA=notify&PARAB=NOTIFY_NAME" target="handlefrm" onclick="openpop('root')">
                                                                   <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                     
                                                    </td> 
                                   
                                                    <td style="font-size:8pt" class="label-1" align="left">TO Date</td><td  class="label-1" style="font-size:8pt" align="left"><sx:datetimepicker name="to_date"  cssClass="txtd;font-size:9pt;" displayFormat="dd/MM/yyyy" value="%{to_date}" tabindex="11" /></td>
                                                    <td style="font-size:8pt" class="label-1" align="left">Commision %</td> <td  class="label-1" style="font-size:8pt"><s:textfield name="comm_per"   cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;" theme="simple" value="%{comm_per}" tabindex="16" /></td>
                                                    <td style="font-size:8pt" class="label-1" align="left">Discharge</td> 
                                                     <td class="label-1" style="font-size:8pt">
                                                               <s:textfield id="DISCHARGE" name="DISCHARGE"  cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{DISCHARGE}" onblur="if(this.value!='') return xmlhttpreqdischarge(this,'DISCHARGE_DESC')"  tabindex="22"/>
                                                                    <a href="CsytabViewPREINVMVX.action?PARAA=DISCHARGE&PARAB=DISCHARGE_DESC&TYPE_CODE=SDST" target="handlefrm" onclick="openpop('root')">
                                                                    <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                               <s:textfield id="DISCHARGE_DESC" name="DISCHARGE_DESC"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{DISCHARGE_DESC}"/>
                                                                     
                                                     </td> 
                                             </tr>
                                              <tr>  
                                                  <td style="font-size:8pt" class="label-1" align="left">Invoice Type</td><td class="label-1" style="font-size:9pt"><s:select name="self_tp" label="Invoice Type" cssStyle="font-size:10pt;width=60pt;font-size:9pt;" theme="simple"  value="%{self_tp}"  list="#{'N':'Normal','S':'Trade Sample','F':'Free Sample'}" /></td>
                                                  <td style="font-size:8pt" class="label-1" align="left">CHA</td>
                                                   <td class="label-1" style="font-size:8pt">
                                                            <s:textfield id="agent" name="agent" cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{agent}" onblur="if(this.value!='') return xmlhttpreqcha(this,'CHA_NAME')" tabindex="7" />
                                                              	<s:textfield id="CHA_NAME" name="CHA_NAME"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:8pt;" theme="simple" value="%{CHA_NAME}"/>
                                                               		 <a href="chaViewPREINVMVX.action?PARAA=agent&PARAB=CHA_NAME" target="handlefrm" onclick="openpop('root')">
                                                                		 <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                          
                                                    </td> 
                                                  <td style="font-size:8pt" class="label-1" align="left">ETD Date</td><td  class="label-1" style="font-size:9pt" align="left"><sx:datetimepicker name="etd_date"  cssClass="txtd;font-size:9pt;" displayFormat="dd/MM/yyyy" value="%{etd_date}" tabindex="12" /></td>
                                                 
                                                   <td style="font-size:8pt" class="label-1" align="left">Transport Rate</td> <td  class="label-1" style="font-size:8pt"><s:textfield name="transport_cost"   cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;" theme="simple" value="%{transport_cost}" tabindex="16" /></td>
                                                  
                                                  <td style="font-size:8pt" class="label-1" align="left">Dischr Cntry</td> 
                                                  <td  class="label-1" style="font-size:8pt">
                                                      <s:textfield id="DIS_CNTRY" name="DIS_CNTRY"  cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{DIS_CNTRY}" onblur="if(this.value!='') return xmlhttpreqdiscntry(this,'DIS_CNTRY_DESC')" tabindex="23" />
                                                                    <a href="CsytabViewPREINVMVX.action?PARAA=DIS_CNTRY&PARAB=DIS_CNTRY_DESC&TYPE_CODE=CSCD" target="handlefrm" onclick="openpop('root')">
                                                                    <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                       <s:textfield id="DIS_CNTRY_DESC" name="DIS_CNTRY_DESC"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{DIS_CNTRY_DESC}"/>
                                                  </td>
                                              </tr> 
                                               <tr>
                                                  <td style="font-size:8pt" class="label-1" align="left">Ship Mode</td>
                                                            <td class="label-1" style="font-size:8pt"  >
                                                              <s:select name="mode_of_ship"   cssStyle="text-transform:uppercase;width:60pt;font-size:9pt;" tabindex="1" value="%{mode_of_ship}" theme="simple" list="%{modlList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />  
                                                  </td>
                                           
                                                   <td style="font-size:8pt" class="label-1" align="left">Forwarder</td>
                                                    <td class="label-1" style="font-size:8pt">
                                                               <s:textfield id="fwd_code" name="fwd_code"  cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{fwd_code}" onblur="if(this.value!='') return xmlhttpreqfwd(this,'FWD_NAME')" tabindex="8" />
                                                               <s:textfield id="FWD_NAME" name="FWD_NAME"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:8pt;" theme="simple" value="%{FWD_NAME}"/>
                                                              <a href="chaViewPREINVMVX.action?PARAA=fwd_code&PARAB=FWD_NAME" target="handlefrm" onclick="openpop('root')">
                                                              	<img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/>
                                                              </a>
                                                            
                                                    </td>
                                                     <td style="font-size:8pt" class="label-1" align="left">Tax Code </td>
                                                     <td class="label-1" style="font-size:9pt"  >
                                                         <s:textfield name="TAX_CODE" id="TAX_CODE" cssStyle="text-transform:uppercase;width:70pt;" theme="simple" value="%{TAX_CODE}" onblur="if(this.value!='') return xmlhttpreqtax(this,'TAX_TYPE','TAX_PERCENT')" tabindex="13"/>
                                                                 <a href="taxcodeViewPREINVMVX.action?PARAA=TAX_CODE&PARAB=TAX_TYPE&TYPE_CODE=TAX_PERCENT" target="handlefrm" onclick="openpop('root')">
                                                                 <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                     </td>
                                                       <td></td><td></td>
                                                        
                                                     <td style="font-size:8pt" class="label-1" align="left">Origin Cntry</td> 
                                                     <td  class="label-1" style="font-size:8pt">   
                                                           <s:textfield id="CNTRY_ORIGIN" name="CNTRY_ORIGIN"  cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{CNTRY_ORIGIN}" onblur="if(this.value!='') return xmlhttpreqdiscntryoriginal(this,'CNTRY_ORIGIN_DESC')" tabindex="23" />
                                                                    <a href="CsytabViewPREINVMVX.action?PARAA=CNTRY_ORIGIN&PARAB=CNTRY_ORIGIN_DESC&TYPE_CODE=CSCD" target="handlefrm" onclick="openpop('root')">
                                                                    <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                            <s:textfield id="CNTRY_ORIGIN_DESC" name="CNTRY_ORIGIN_DESC"  readonly="true"  cssClass="textreadonly" cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{CNTRY_ORIGIN_DESC}"/>
                                                     </td>
                                               </tr>
                                                <tr>  
                                                <td style="font-size:8pt" class="label-1" align="left">Pre Carriage</td><td class="label-1" style="font-size:8pt"  ><s:textfield id="pre_carriage" name="pre_carriage" cssStyle="text-transform:uppercase;width:60pt;font-size:9pt;" theme="simple" value="%{pre_carriage}" tabindex="2"  /></td>
                                                  
                                                    
                                                    <td style="font-size:8pt" class="label-1" align="left">Manufacturer</td>
                                                     <td class="label-1" style="font-size:8pt"  >
                                                              <s:textfield id="MANUF_CODE" name="MANUF_CODE"  cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" theme="simple" value="%{MANUF_CODE}" onblur=" if(this.value!='') return xmlhttpreqmanuf(this,'MANUF_DESC')" tabindex="9" /> 
                                                              <s:textfield id="MANUF_DESC" name="MANUF_DESC" readonly="true"  cssClass="textreadonly"  cssStyle="width:100pt;font-size:9pt;" theme="simple" value="%{MANUF_DESC}"/>
                                                               <a href="unitViewPREINVMVX.action?PARAA=MANUF_CODE&PARAB=MANUF_DESC" target="handlefrm" onclick="openpop('root')">
                                                                  <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/>
                                                               </a>
                                                     </td> 
                                                     <td style="font-size:8pt" class="label-1" align="left">Tax Desc </td><td class="label-1" style="font-size:9pt"  >
                                                     <s:textfield id="TAX_TYPE" name="TAX_TYPE" readonly="true" cssClass="textreadonly"  cssStyle="width:70pt;font-size:9pt;" theme="simple" value="%{TAX_TYPE}"/>
                                                     <s:textfield id="TAX_PERCENT"  name="TAX_PERCENT"  readonly="true" cssClass="textreadonly"  cssStyle="text-transform:uppercase;width:20pt;font-size:9pt;" theme="simple" value="%{TAX_PERCENT}"/></td>
                                                     <td style="font-size:8pt" class="label-1" align="left">Tax Calc Fob% </td><td class="label-1" style="font-size:9pt">
                                                     <s:textfield id="TAX_CAL_PER" name="TAX_CAL_PER"   cssStyle="width:30pt;font-size:9pt;" theme="simple" value="%{TAX_CAL_PER}"/></td>
                                                     <td style="font-size:8pt" class="label-1" align="left">Destination</td>
                                                     <td  class="label-1" style="font-size:8pt"> 
                                                           <s:textfield name="DESTI_CODE"  readonly="true"   cssStyle="width:50pt;font-size:9pt;background-color:#fea" theme="simple" value="%{DESTI_CODE}" />
                                                                     <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/>
                                                           <s:textfield name="DESTI_CODE_DESC"  readonly="true"   cssStyle="width:100pt;font-size:9pt;background-color:#fea;" theme="simple" value="%{DESTI_CODE_DESC}"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                      <td style="font-size:8pt" class="label-1" align="left">PCH</td><td class="label-1" style="font-size:9pt"  ><s:textfield name="cost_centre" readonly="true"  cssStyle="width:60pt;font-size:9pt;background-color:#fea" theme="simple" value="%{cost_centre}"/></td>
                                              
                                                     <td style="font-size:8pt" class="label-1" align="left">CO PayTerm</td>
                                                     <td class="label-1" style="font-size:8pt"  >
                                                              <s:textfield name="pay_term" readonly="true"  cssStyle="width:50pt;font-size:9pt;background-color:#fea" theme="simple" value="%{pay_term}"/> 
                                                              <s:textfield name="pay_term_desc" readonly="true"  cssStyle="width:100pt;font-size:9pt;background-color:#fea" theme="simple" value="%{pay_term_desc}"/>
                                                     </td>
                                                      <td style="font-size:8pt" class="label-1" align="left">HS Code</td> <td  class="label-1" style="font-size:9pt"><s:textfield name="hs_code"   cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" theme="simple" value="%{hs_code}" tabindex="14"/></td>
                                                         
                                                     <td style="font-size:8pt" class="label-1" align="left">Total Cartons </td><td class="label-1" style="font-size:8pt"  ><s:textfield name="CTNS"  cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;" theme="simple" value="%{CTNS}" tabindex="17"/></td>
                                                     <td style="font-size:8pt" class="label-1" align="left">Destn Cntry</td> 
                                                      <td  class="label-1" style="font-size:8pt">
                                                           <s:textfield name="DESTI_CNTRY"  id="DESTI_CNTRY" readonly="true"   cssStyle="width:50pt;font-size:9pt;background-color:#fea" theme="simple" value="%{DESTI_CNTRY}" />
                                                                     <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/>
                                                           <s:textfield name="DESTI_CNTRY_DESC"  readonly="true"   cssStyle="width:100pt;font-size:9pt;background-color:#fea;" theme="simple" value="%{DESTI_CNTRY_DESC}"/>
                                                    </td>
                                          
                                                </tr> 
                                                <tr>
                                                            <td style="font-size:8pt" class="label-1" align="left">Currency</td> <td  class="label-1" style="font-size:9pt"><s:textfield name="CRNCY_CODE"  readonly="true"  cssStyle="width:60pt;font-size:9pt;background-color:#fea" theme="simple" value="%{CRNCY_CODE}"/></td>
                                                            <td style="font-size:8pt" class="label-1" align="left">Delivery Term</td> 
                                                            <td  class="label-1" style="font-size:9pt" >
                                                                <s:select name="ship_term"      cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;background-color:#fea" tabindex="11" value="%{ship_term}" theme="simple" list="%{tedlList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />  

                                                             </td>
                                                           <td style="font-size:8pt" class="label-1" align="left">Fwd to Post</td><td class="label-1" style="font-size:8pt"><s:textfield name="fwd_date"  readonly="true"  cssClass="textreadonly" cssStyle="width:70pt;font-size:10pt;font-weight:bold;color:blue" theme="simple" value="%{fwd_date}"/> </td>
                                                           <td style="font-size:8pt" class="label-1" align="left">Total GrWt. </td><td class="label-1" style="font-size:8pt"  ><s:textfield name="GRWT"  cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;" theme="simple" value="%{GRWT}" tabindex="18"/></td>
                                                           <td style="font-size:8pt" class="label-1" align="left">Fob FC [<s:property value="%{CRNCY_CODE}" />]</td> <td  class="label-1" style="font-size:9pt"><s:textfield   readonly="true"  cssStyle="width:50pt;font-size:9pt;text-align:right;background-color:yellow;color:red;font-weight:bold" theme="simple" value="%{TOT_FOB}"/>
                                                               &nbsp; &nbsp;&nbsp;&nbsp;GR Decl:&nbsp; <s:textfield   readonly="true"  cssStyle="width:49pt;font-size:9pt;align:right;background-color:yellow;text-align:right" theme="simple" value="%{TOT_GR}"/></td>
                                                </tr> 
                                                   
                                            <tr>  
                                        </tr> 
                                        <tr>        <td style="font-size:8pt" class="label-1" align="left">Exp Type</td><td class="label-1" style="font-size:9pt"  ><s:textfield name="exp_type"  readonly="true" cssStyle="width:60pt;font-size:9pt;background-color:#fea" theme="simple" value="%{exp_type}"/></td>
                                                    <td style="font-size:8pt" class="label-1" align="left">Payment Term</td><td class="label-1" style="font-size:8pt"  ><s:select name="payment_term"   cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;" tabindex="11" value="%{payment_term}" theme="simple" list="%{PaymenttermList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" /> </td>
                                                      
                                                    <td style="font-size:8pt" class="label-1" align="left">Remarks</th>
                                                    <td style="font-size:8pt" class="label-1" colspan="3" class="label-1"><s:textfield name="SHIP_DESC"  cssStyle="text-transform:uppercase;width:230pt;font-size:9pt;" theme="simple" value="%{SHIP_DESC}" tabindex="23"/></td>
                                                    <td style="font-size:8pt" class="label-1" align="left">Net Fob [<s:property value="%{CRNCY_CODE}" />]</td> <td  class="label-1" style="font-size:9pt"><s:textfield   readonly="true"  cssStyle="width:50pt;font-size:9pt;align:right;background-color:yellow;text-align:right;font-weight:bold;color:green" theme="simple" value="%{NET_FOB}"/></td>
                         
                                        </tr>
                                                  
                                               
                                      </table>
                                    </td></tr>
                                <tr style="background-color: #f2f9fb;">
                                    <td>
                                        <table bgcolor="#7b97e0" style="margin-top: -2pt" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                                <th align="left">Type</th>
                                                <th align="left">Code</th>
                                                <th align="left">Add No.</th>
                                                <th align="left">Name</th>
                                                <th align="left">Address</th>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt">Buyer</td>
                                                <td style="font-size:8pt"><s:property value="buyer"/></td>
                                                <td style="font-size:8pt"><s:property value="buyer_addr"/></td>
                                                <td style="font-size:8pt"><s:property value="buyer_name"/></td>
                                                <td style="font-size:8pt"><s:property value="buyer_address"/></td>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt">Consignee</td>
                                                <td style="font-size:8pt"><s:property value="buyer"/></td>
                                                <td style="font-size:8pt"><s:property value="cons_addr"/></td>
                                                <td style="font-size:8pt"><s:property value="cons_name"/></td>
                                                <td style="font-size:8pt"><s:property value="cons_address"/></td>
                                            </tr>
                                            <s:hidden name="buyer" value="%{buyer}"/>
                                            <s:hidden name="buyer_addr" value="%{buyer_addr}"/>
                                            <s:hidden name="cons_addr" value="%{cons_addr}"/>
                                            <s:hidden name="facility" value="%{facility}"/>
                                            <s:hidden name="to_inr_conv" value="%{to_inr_conv}"/>
                                            
                                        </table>
                                    </td>
                                </tr> 
                            </table>
                               </sx:div>
                            <s:url id="lineurl" action="prelinePREINVMVX">
                                <s:param name="plan_no" value="%{plan_no}"/>
                                 <s:param name="GetDataFlag" value="%{GetDataFlag}"/>
                                 <s:param name="YEAR" value="%{YEAR}"/>
                                 <s:param name="COMPANY" value="%{COMPANY}"/>
                                 <s:param name="INV_NO" value="%{INV_NO}"/>
                             </s:url>
                             
                                 <sx:div cssStyle="overflow:auto;" label="CO Line Details" href="%{#lineurl}" refreshOnShow="false" id="Linedetails" closable="false"  errorText="Some Error Occured While Transaction" showErrorTransportText="true"  loadingText="Loading ..." showLoadingText="true">
                                  </sx:div>
                               
                                <s:url id="billinurl" action="prelinepricePREINVMVX">
                                    <s:param name="plan_no" value="%{plan_no}"/>
                                    <s:param name="GetDataFlag" value="%{GetDataFlag}"/>
                                    <s:param name="YEAR" value="%{YEAR}"/>
                                    <s:param name="COMPANY" value="%{COMPANY}"/>
                                    <s:param name="INV_NO" value="%{INV_NO}"/>
                                  
                                  </s:url>  
                                 <sx:div cssStyle="overflow:auto;" label="Billing Details" href="%{#billinurl}" refreshOnShow="false" id="billingdetails" closable="false"  errorText="Some Error Occured While Transaction" showErrorTransportText="true"  loadingText="Loading ..." showLoadingText="true">
                                 </sx:div>
                                 <s:url id="accrurl" action="preinvaccrPREINVMVX">
                                    <s:param name="plan_no" value="%{plan_no}"/>
                                    <s:param name="GetDataFlag" value="%{GetDataFlag}"/>
                                    <s:param name="YEAR" value="%{YEAR}"/>
                                    <s:param name="COMPANY" value="%{COMPANY}"/>
                                    <s:param name="INV_NO" value="%{INV_NO}"/>
                                  </s:url>  
                                 <sx:div cssStyle="overflow:auto;" label="Accessories Details" href="%{#accrurl}" refreshOnShow="false" id="accrdetails" closable="false"  errorText="Some Error Occured While Transaction" showErrorTransportText="true"  loadingText="Loading ..." showLoadingText="true">
                                 </sx:div> 
                               <s:url id="licenceurl" action="prelicencePREINVMVX">
                                    <s:param name="plan_no" value="%{plan_no}"/>
                                    <s:param name="GetDataFlag" value="%{GetDataFlag}"/>
                                    <s:param name="YEAR" value="%{YEAR}"/>
                                    <s:param name="COMPANY" value="%{COMPANY}"/>
                                    <s:param name="INV_NO" value="%{INV_NO}"/>
                                  </s:url>  
                                 <sx:div cssStyle="overflow:auto;" label="Licence Details" href="%{#licenceurl}" refreshOnShow="false" id="licencedetails" closable="false"  errorText="Some Error Occured While Transaction" showErrorTransportText="true"  loadingText="Loading ..." showLoadingText="true">
                                 </sx:div>
                                 <s:url id="hscodeurl" action="prehscodePREINVMVX">
                                    <s:param name="plan_no" value="%{plan_no}"/>
                                    <s:param name="GetDataFlag" value="%{GetDataFlag}"/>
                                    <s:param name="YEAR" value="%{YEAR}"/>
                                    <s:param name="COMPANY" value="%{COMPANY}"/>
                                    <s:param name="INV_NO" value="%{INV_NO}"/>
                                  </s:url>  
                                 <sx:div cssStyle="overflow:auto;" label="HS Code" href="%{#hscodeurl}" refreshOnShow="false" id="hsdetails" closable="false"  errorText="Some Error Occured While Transaction" showErrorTransportText="true"  loadingText="Loading ..." showLoadingText="true">
                                 </sx:div>
                                  <s:url id="exciseurl" action="preexcisePREINVMVX">
                                    <s:param name="plan_no" value="%{plan_no}"/>
                                    <s:param name="GetDataFlag" value="%{GetDataFlag}"/>
                                    <s:param name="YEAR" value="%{YEAR}"/>
                                    <s:param name="COMPANY" value="%{COMPANY}"/>
                                    <s:param name="INV_NO" value="%{INV_NO}"/>
                                     <s:param name="searchloct" value="%{searchloct}"/>
                                    
                                  </s:url>  
                                 <sx:div cssStyle="overflow:auto;" label="Excise" href="%{#exciseurl}" refreshOnShow="false" id="excisedetails" closable="false"  errorText="Some Error Occured While Transaction" showErrorTransportText="true"  loadingText="Loading ..." showLoadingText="true">
                                 </sx:div>
                             </sx:tabbedpanel>
                        
                    </td></tr>
                       <tr style="height:5%" bgcolor="#f2f2f2" >
                        <td style="border-width:1pt;border-color:black;border-style:solid;" >
                            <table   align="center" width="100%" >
                                <tr><td align="left" > Date : <s:property value="%{currentdate}" /> </td>
                              <td valign="bootom" align="center"  style="color:red;font-weight:bold">
                              <div style="height:3pt">
                                  <s:actionerror />
                                  <s:fielderror />
                                  <s:actionmessage />
                              </div>
                              </td>
                              <td align="right" > User : <s:property value="%{aausrid}"/> </td></tr>
                             <input type="hidden" name="aausrid" value="<s:property value="%{aausrid}"/>" >
                           </table>
                         </td>
                       </tr> 
                
                 
            </table>
            <div id="root" class="root" style="left:50px; top:200px;display:none;width:900px;z-index: 10000">
               <table cellpadding="0" cellspacing="0">
                <tr bgcolor="#6699FF">
                    <td width="100%">
                        <div id="handle" class="handle"  style="cursor: move">Details</div>
                    </td>
                    <td style="10px"><a href="#" onclick="closediv('root')" ><img border="0" width="18px" height="18px" src="image/chrome_close_button.png"/></a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <iframe name="handlefrm" id="handlefrm" src="" scrolling="no" frameborder="0"  width="100%" height="300px"></iframe>
                    </td>
                 </tr>
             </table>
        </div>
                                                    
                                                    
                                                    
     <script language="javascript">
            var theHandle = document.getElementById("handle");
            var theRoot   = document.getElementById("root");
            Drag.init(theHandle, theRoot);
           </script>
        </form>                    


    </body>
    
</html>

