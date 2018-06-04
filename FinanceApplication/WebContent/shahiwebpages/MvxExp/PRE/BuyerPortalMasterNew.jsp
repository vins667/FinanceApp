<%-- 
    Document   : ExchangeRateMasterNew
    Created on : May 03, 2017, 10:00:54 PM
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
            .root1
            {
                position:absolute;
                height:100px;
                width:300px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            }

            .handle1
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
            
              function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
            		return false;
            	}
                totalctnqty();
            	return true;
                
            }
            
            function recsave()
            {
                myFunction1();
                myFunction2();
                myFunction3();
                if(confirm('You Want to save Records ? '))
                { 
                    if(validateinput()){
                    document.frm.action="saveBuyerPortalMasterA.action";
                    document.frm.submit();
                }
                }

            }
             function validateinput(){
                 
                if(document.getElementById("BUYR_NEW").value==''){
                   alert("Buyer can't be empty!!!");
                  return false;  
                }
                 
               
                return true; 
            }
     
            function myFunction1() { 
                chk=document.getElementById("EDIAPP").checked; 
                if (chk){
                   document.getElementById("EDIAPP").value="Y";
                } else { 
                 document.getElementById("EDIAPP").value="N"; 
                  
                } 
            } 
            function myFunction2() { 
            chk=document.getElementById("PORTLAPP").checked;   
                if (chk){
                    document.getElementById("PORTLAPP").value="Y";
                } else { 
                   document.getElementById("PORTLAPP").value="N"; 
                } 
            } 
            function myFunction3() { 
            chk=document.getElementById("ICAPP").checked;   
                if (chk){
                    document.getElementById("ICAPP").value="Y";
                } else { 
                    document.getElementById("ICAPP").value="N"; 
                   
                } 
            } 
            
            function mychk() { 
              BEDI_APP=document.getElementById("BEDI_APP1").value;
              BPORTAL_APP=document.getElementById("BPORTAL_APP1").value;
              BIC_APP=document.getElementById("BIC_APP1").value;
              if(BEDI_APP=='Y'){
                  document.getElementById("EDIAPP").checked=true;
              }
              if(BPORTAL_APP=='Y'){
                  document.getElementById("PORTLAPP").checked=true;
              }
              if(BIC_APP=='Y'){
                  document.getElementById("ICAPP").checked=true;
              }
            } 
            
            
        </script>

    </head>

    <body style="width:100%;height:100%;overflow: hidden;" onload="mychk();">
<!--        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>-->
        <form action=""   method="post" name="frm" >
             <s:hidden id="BEDI_APP1" name="BEDI_APP1" value="%{BEDI_APP1}" />
             <s:hidden id="BPORTAL_APP1" name="BPORTAL_APP1" value="%{BPORTAL_APP1}" />
             <s:hidden id="BIC_APP1" name="BIC_APP1" value="%{BIC_APP1}" />
            
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr>
                    <td>
                        <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr ><td  class="hd" style="text-align:center">Buyer Portal Master</td></tr>
                  <tr><td>
                          <table   width="55%" border="0" align="center">
                  <tr><td>
                           <table width="100%" align="center">
  
               <tr><td >

                    </td></tr><tr><td height="200pt" width="100%" valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >

                <div  class="div1" style="width:100%;overflow:auto ;height:200pt;">
                    <table  width="100%" align="left" cellpadding="1" bgcolor="#d0d7e5" border="0" cellspacing="1" >
                        <tr>
                            <td class="label-1"  align="center">
                                Buyer &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield name="BUYR_NEW" id="BUYR_NEW" value="%{BUYR_NEW}" cssStyle="text-transform:uppercase;width:200pt" theme="simple"  /> 
                            </td>
                        </tr>
                        <tr>
                            <td class="label-1"  align="center">
                              Dues Days Cal. <s:textfield name="DUE_DAYS" id="DUE_DAYS" value="%{DUE_DAYS}" cssStyle="text-transform:uppercase;width:100pt" theme="simple"  onblur="validatenumber(this)" /> 
                            </td>
                        </tr>
                        <tr>
                            <td class="label-1" align="center">
                             EDI App<s:checkbox id="EDIAPP" fieldValue="myFunction1();" name="EDIAPP" value="EDIAPP" theme="simple"/>
                             <input type="hidden" id="EDIAPP1" name="EDIAPP1" value="N" />
                             Portal App<s:checkbox id="PORTLAPP" fieldValue="myFunction2();" name="PORTLAPP" value="PORTLAPP" theme="simple"/>
                             <input type="hidden" id="PORTLAPP1" name="PORTLAPP1" value="N" />
                             IC App<s:checkbox id="ICAPP" fieldValue="myFunction3();" name="ICAPP" value="ICAPP" theme="simple"/>
                             <input type="hidden" id="ICAPP1" name="ICAPP1" value="N" />
                            </td>
                           
                        </tr>
                        <tr>
                            <td class="label-1">
                                
                            </td>
                            <td class="label-1">
                                
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                
                            </td>
                            <td class="label">
                                
                            </td>
                        </tr>
                        <tr>
                         <td align="center">  
                           <button  id="searchheadId" class="sexybutton" onclick="window.location.href='BuyerPortalMasterA.action'"><span><span><span class="undo" id="undo">Back</span></span></span></button>
                           <button  id="saveheadId"  class="sexybutton" onclick="recsave();"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                           <button  id="saveheadId"  class="sexybutton" onclick="clr();"><span><span>Clear</span></span></button> 
                           <button  id="exitheadId" class="sexybutton" onclick="javascript:window.close();"><span><span><span class="cancel" id="exitId" >Exit</span></span></span></button>
                        </td>
                       </tr>
                </table>
                 </div>
            </td></tr>
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
                      </TD></TR>
    

                  </td></tr></table>
                    </td></tr> 
            </table> 
                                               

<script type="text/javascript">
    var approveraddid1 = document.getElementById("approveraddid");
    var report = document.getElementById('Report');
    Drag.init(report, approveraddid1);
</script>
        </form> 
    </body>
</html>
