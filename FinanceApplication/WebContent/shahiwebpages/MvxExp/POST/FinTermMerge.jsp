<%-- 
    Document   : FinTermMerge
    Created on : Jun 2, 2016, 9:35:17 AM
    Author     : Guddu Kumar
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
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
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }

            .toolTip {
                background-color: white;

                border: solid 1px;
                font-family: Arial;
                font-size: 12px;
                font-style: normal;
                font-variant: normal;
                font-weight: normal;
                left: 0;
                padding: 0px;
                position: absolute;
                text-align: left;
                top: 0;
                visibility: hidden;
                z-index: 2;
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
            
           function validateRec()
         {    
            
               var x = document.frm.CRNCY;
               var x1= document.frm.PTERM;
               var x2=document.frm.PLC;
               var y=null; var y1=null; var y2=null;
                for (var i = 0; i < x.length; i++) {
                         if (x[i].value!=null ) {
                          if (y!=null)   
                          {  
                            if (x[i].value != y) {
                                alert(" Multiple Crncy Not Allowed..");
                                 return false;
                            }
                          }else{
                                y=x[i].value;
                          }
                        }
                     }
                         for (var i = 0; i < x1.length; i++) {
                         if (x1[i].value!=null ) {
                          if (y1!=null)   
                          {  
                            if (x1[i].value != y1) {
                                alert(" Multiple PayTerm Not Allowed..");
                                 return false;
                            }
                          }else{
                                y1=x1[i].value;
                          }
                        }
                         }
                        for (var i = 0; i < x2.length; i++) {
                         if (x2[i].value!=null ) {
                          if (y2!=null)   
                          {  
                            if (x2[i].value != y2) {
                                alert(" Multiple LC Not Allowed..");
                                 return false;
                            }
                          }else{
                                y2=x2[i].value;
                          }
                        }
                  }
                return true;
            }

            
            function searchdetail()
            {                
                document.frm.action="FINTERMUPD.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
            function searchdetail2()
            {                
                document.frm.action="FINTERMUPD.action?viewFlag=Yes&viewFlag2=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverecNew()
            {   
                document.frm.action="update1FINTERMUPD.action?NewFlag=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
             
            }
              function saverec()
            {     
           if(validateRec()){
             var a=dojo.widget.byId('SEARCH_DATE2').getValue();
             var b=document.getElementById('TERM_NO2').value;
               if(a=='' || b==''){ 
                    alert("Please Enter Merge Date and Term_No");
                }
                else{
                document.frm.action="update1FINTERMUPD.action?MergeFlag=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
               }
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
        </script>

    </head>

    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Finance Term Change</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#A9CCE3" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                            <td class="label-1" style="width:300px">Finance Date : <sx:datetimepicker id="SEARCH_DATE" name="SEARCH_DATE" value="%{SEARCH_DATE}" displayFormat="dd/MM/yyyy"/>
                                            </td>  
                                            <td class="label-1" style="width:200px">Term No. <s:textfield name="TERM_NO" value="%{TERM_NO}" cssStyle="text-transform:uppercase;width:60pt" theme="simple" maxLength="15"/>
                                            </td> 
                                
                                            <td align="left">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                            </td>
                                             <td align="right" ><button  id="saveheadId"  class="sexybutton" onclick="saverecNew();"><span><span><span class="save" id="saveId" >New Term#</span></span></span></button> 
                                             <td align="right" ><button  id="saveheadId1"  class="sexybutton" onclick="saverec();"><span><span><span class="save" id="saveId" >Merge Term#</span></span></span></button> 
                                           
                                             <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                           </td>
                                        </tr>
                                          
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                
                         <tr><td>
                        <div style="width:200pt;" class="toolTip" id="toolTipDiv"></div>
                        <div align="center" style="width:100.0%;">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr  style="background-color: whitesmoke;height: 150pt;">
                                    <td>
                                <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div class="div1"  style="width:100%; overflow:auto; height:150.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                                <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                 
                                                   <tr  style="position: absolute;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                    <th style="font-size:8pt" align="left">Select</th>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left"  >Awb #</th>
                                                    <th style="font-size:8pt" align="left">H/Awb #</th>
                                                    <th style="font-size:8pt" align="left">Awb Dt.</th>
                                                    <th style="font-size:8pt" align="left">Pay Term</th>
                                                    <th style="font-size:8pt" align="left">Inv #</th>
                                                    <th style="font-size:8pt" align="left">Buyer</th>
                                                    <th style="font-size:8pt" align="right">Cntry</th>
                                                    <th style="font-size:8pt" align="left">Crncy</th>
                                                    <th style="font-size:8pt" align="right">Fob[Fc]</th>
                                                    <th style="font-size:8pt" align="right">Fob[Inr]</th>
                                                    <th style="font-size:8pt" align="right">Loct</th>
                                                    <th style="font-size:8pt" align="right">LC No</th>
                                                    
                                                  </tr>                                                
                                                  <s:hidden name="MDATE1" value="%{SEARCH_DATE}" />
                                                  <tbody> 
                                                    <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td height="20px" width="40px">
                                                              <s:checkbox id="SEL_CHECK" fieldValue="%{YEAR+'-'+LINK_NO}" value=""  name="SEL_CHECK" theme="simple"  /></td>
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="AWB_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="H_AWB_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="AWB_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="PAYTERM"/></td>
                                                            <td style="font-size:8pt"><s:property value="INVOICE_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="DESTI_CNTRY"/></td>
                                                            <td style="font-size:8pt"><s:property value="CRNCY_CODE"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="FOB_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="FOB_INR"/></td>
                                                           <td style="font-size:8pt;text-align: left"><s:property value="LOCT"/></td>
                                                           <td style="font-size:8pt;text-align: left"><s:property value="LC_NO"/></td>
                                                           <s:hidden name="CRNCY" value="%{CRNCY_CODE}"/>
                                                           <s:hidden name="PTERM" value="%{PAYTERM}"/>
                                                           <s:hidden name="PLC" value="%{LC_NO}"/>
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                                            </table>
                                        </div>  
                                      </td>
                                 </tr>
                                 
                                 
                   <tr>
                    <td>
                        <table style="text-align:center" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#A9CCE3" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                          
                                            
                                            <td class="label-1" style="width:300px">Merge with Date : <sx:datetimepicker id="SEARCH_DATE2" name="SEARCH_DATE2" value="%{SEARCH_DATE2}" displayFormat="dd/MM/yyyy" />
                                            </td>  
                                            <td class="label-1" style="width:200px">Term No. <s:textfield name="TERM_NO2" value="%{TERM_NO2}" cssStyle="text-transform:uppercase;width:60pt" theme="simple" maxLength="15"/>
                                            </td> 
                                            <td align="left">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail2();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                            </td>
                                            <td></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                 </tr>
                                 
                                 
                                 <tr>
                                       <td valign="top" >
                                           <div class="div1"  style="width:100%; overflow:auto; height:180.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                                <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                 
                                                   <tr  style="position: absolute;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                       <th></th>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left">Awb #</th>
                                                    <th style="font-size:8pt" align="left">H/Awb #</th>
                                                    <th style="font-size:8pt" align="left">Awb Dt.</th>
                                                    <th style="font-size:8pt" align="left">Pay Term</th>
                                                    <th style="font-size:8pt" align="left">Inv #</th>
                                                    <th style="font-size:8pt" align="left">Buyer</th>
                                                    
                                                    <th style="font-size:8pt" align="right">Cntry</th>
                                                    <th style="font-size:8pt" align="left">Crncy</th>
                                                    <th style="font-size:8pt" align="right">Fob[Fc]</th>
                                                    <th style="font-size:8pt" align="right">Fob[Inr]</th>
                                                    <th style="font-size:8pt" align="right">Loct</th>
                                                    <th style="font-size:8pt" align="right">LC No</th>
                                                    
                                                  </tr>                                                
                                                
                                                  <tbody> 
                                                      <s:set id="cnt" name="cnt" value="%{0}"/>
                                                      <s:hidden name="MDATE2" value="%{SEARCH_DATE2}" />
                                                       <s:hidden name="MTERM2" value="%{TERM_NO2}" />
                                                       
                                                    <s:iterator value="ShowDetail2" status="st2">
                                                        <tr bgcolor="#D9E3E7">
                                                            <td height="20px" width="40px"></td>
                                                            <td style="font-size:8pt"><s:property value="%{#st2.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="AWB_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="H_AWB_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="AWB_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="PAYTERM"/></td>
                                                            <td style="font-size:8pt"><s:property value="INVOICE_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="DESTI_CNTRY"/></td>
                                                            <td style="font-size:8pt"><s:property value="CRNCY_CODE"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="FOB_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="FOB_INR"/></td>
                                                           <td style="font-size:8pt;text-align: left"><s:property value="LOCT"/></td>
                                                           <td style="font-size:8pt;text-align: left"><s:property value="LC_NO"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>
                                                            <s:hidden name="CRNCY" value="%{CRNCY_CODE}"/>
                                                            <s:hidden name="PTERM" value="%{PAYTERM}"/>
                                                            <s:hidden name="PLC" value="%{LC_NO}"/>
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                                            </table>
                                        </div>  
                                      </td>
                                 </tr>
                                 
                                 
                                 
                              </table>  
                             </td>
                            </tr>
                            </table>
                        </div>
                             </TD>
                         </TR>
                            
                            
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

                           </form>                    


    </body>
 
</html>