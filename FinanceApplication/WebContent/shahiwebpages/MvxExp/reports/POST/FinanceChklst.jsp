
 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>

<link type="text/css" rel="stylesheet" href="../../../css/main.css"/>
<link type="text/css" rel="stylesheet" href="../../../css/ShahiButtons/shahibuttons.css"/>

<script language="javascript" type="text/javascript" src = "../../script/function.js" ></script>
<script type="text/javascript" src="../../script/jquery.1.4.2.js"></script>
<script type="text/javascript" src="../../script/jquery_blockUI.js"></script>
<script type="text/javascript" src="../../script/jquery_cookie.js"></script>
<script type="text/javascript" src="../../script/hidediv.js"></script>  
 
<html>
    <head>
        <s:head/>
        <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Export Pvt Ltd.</title>
        <style type="text/css">


        </style>
        <script type="text/javascript" lang="javascript">
            
            function onnSubmit(x)
            {
               // INVNO = document.financereport.INVNO.value;
                document.getElementById("REPORTTYPE").value=x;
                document.create_pdf_form.action = "FinanceChklstA.action";
                document.create_pdf_form.submit();
                document.getElementById('prepage').style.visibility = '';
            }
           
            function tabE(obj, e) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for (var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other

                        if (obj == ele[i])
                        {
                            ele[q].focus();
                            break
                        }
                    }
                    //onfinish();
                    return false;
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
 
        <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height: 510px;
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
        
           border:  solid #006699; 
            border-width: 0px 0px 0px 0px;
             padding-top: 0px ;
        }

         }
        thead tr {

        }
        tbody {
            height: auto;
        }
          }
    </style>
<![endif]-->

    </head>
    <body style="text-align: center;padding:1px;background-color: #f2f9fb;" >
        <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="../../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form name="create_pdf_form" id="create_pdf_form" action="" method=""   >
       
            <input type="hidden" name="REPORTTYPE" id="REPORTTYPE"/>
            <input type="hidden" name="aausrid" id="aausrid" value="%{aausrid}"/>
            <table id="table1" style="text-align:center;width:100%;background-color: #6699CC;margin:0px;padding-top: 0px" cellspacing="1" cellpadding="0">
                <tr>
                    <td style="width: 100%;height:25px;text-align: center" class="hd">
                        Finance CheckList Report
                    </td>
                </tr>
            </table>

            <table style="width:46%; text-align: center;background-color: #4D4D4D;" cellspacing="1" cellpadding="20">
                   <tr>
                                <td  align="left" valign="top" class="label-1" style="background-color: white; ">Loct</td>
                                <td colspan="3" align="left" class="label-1" style="background-color: white;">
                                       <s:select  headerValue="" list="#{'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:70pt;font-weight:bold" theme="simple" name="LOCT" value="%{LOCT}" /> 
                                </td>  
                               
                            </tr>
                <tr class="label-1" style="background-color: #FFFFFF;">
                    <td style="text-align: left" class="label-1">Date</td><td style="padding-left:20px"> <sx:datetimepicker id="date1" name="date1" displayFormat="dd-MM-yyyy" value="%{EDATE}" /></td>
                     <td style="text-align: left" class="label-1">Term</td><td style="padding-left:20px"><s:textfield value="%{TERM}" theme="simple" name="TERM" cssStyle="text-transform:uppercase;width:80pt" id="TERM" onkeypress="return tabE(this,event);" /> </td>
               </tr>
               <tr class="label-1" style="background-color: #FFFFFF;">
                   <td style="text-align: left" class="label-1">Remark</td><td style="padding-left:20px" colspan="4"> <s:textfield id="REMRK" name="REMRK"  cssStyle="width:320pt" theme="simple" /></td>
               </tr>
                <tr class="label-1" style="background-color: #FFFFFF;">
                   
                    <td style="text-align: center" class="label-1">Report&nbsp;Type</td>
                       <td style="padding-left:20px;text-align:left" colspan="4">
                                <s:radio label="Report Type" name="ReportT"  id="ReportT" list="# {'FC':'Finance Copy&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;','BC':'Bill of Exchange&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;','DD':'DA+DP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;','OC':'LC Covering&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;','BL':'Bank&nbsp;Letter&nbsp;(Movex)&nbsp;','FF':'Finance &nbsp;&nbsp; CheckList'}" value="'FC'" theme="simple" cssStyle="texts"/>
                        </td>
                </tr>
                     
                <tr class="label-1" style="background-color: #FFFFFF;">
                    <td colspan="4" style="text-align: center">
                        <table><tr><td>
                                    <a href="#" class="sexybutton" id="printhd" onclick="onnSubmit('PDF');"><span><span><img src="../../images/pdf1.png"/>Pdf</span></span></a>
                        </td>
                         
                                    
                            <td style="width:20px"> <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('FinanceChklstA.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                          </td>
                        <td style="width:20px"><a href="#" class="sexybutton" onclick="self.close();"><span><span><span class="cancel">Exit</span></span></span></a>
                        </td>
                        </tr>
                        </table>

                </tr>
            </table>

        </form>

    </body>
</html>

