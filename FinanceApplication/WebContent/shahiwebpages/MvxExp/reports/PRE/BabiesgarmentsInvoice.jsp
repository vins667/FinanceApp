<%-- 
    Document   : ExcisePrePeriod
    Created on : Jul 05, 2016, 11:24:39 AM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>

<link type="text/css" rel="stylesheet" href="../../css/main.css"/>
<link type="text/css" rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>

<html>
    <head>
        <s:head/>
        <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Export Pvt Ltd.</title>
        <style type="text/css">


        </style>
        <script type="text/javascript" lang="javascript">
            
            function cln()
            {
               
                document.knitinspreport.action = "clearBabiesgarmentsInvoiceA.action";
                document.knitinspreport.submit();
                document.getElementById('prepage').style.visibility = '';
            }
            
            function onnSubmit(x)
            {
               // INVNO = document.knitinspreport.INVNO.value;
                document.getElementById("REPORTTYPE").value=x;
                document.knitinspreport.action = "BabiesgarmentsInvoiceA.action";
                document.knitinspreport.submit();
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
         <DIV align="center" id="prepage"  style="visibility: hidden;padding-top: 300px;position: absolute;">
            <img src="../../css/img/loading.gif"/><br><span style="font-weight: bold;color:#4D4D4D;font-size: 25px;">&nbsp;&nbsp;Please Wait...</span> 
        </DIV>
        <form action="" method="" id="knitinspreport" name="knitinspreport">
       
            <input type="hidden" name="REPORTTYPE" id="REPORTTYPE"/>
            <table id="table1" style="text-align:center;width:100%;background-color: #6699CC;margin:0px;padding-top: 0px" cellspacing="1" cellpadding="0">
                <tr>
                    <td style="width: 100%;height:25px;text-align: center" class="hd">
                        Babies Garments Invoice
                    </td>
                </tr>
            </table>

            <table style="width:30%; text-align: center;background-color: #4D4D4D;" cellspacing="1" cellpadding="20">
                <tr class="label-1" style="background-color: #FFFFFF;">
                    <td style="text-align:left" class="label-1">Invoice No.</td><td style="padding-left:10px"><s:textfield value="%{INVNO}" theme="simple" name="INVNO" onkeypress="return tabE(this,event);" cssStyle="texts"></s:textfield></td>
                </tr>
                <tr class="label-1" style="background-color: #FFFFFF;">
                   
                       <td style="text-align:left" class="label-1">Report Type</td>
                                <td style="padding-left:10px">
                                <s:radio label="Report Type" name="ReportT"  id="ReportT" list="# {'1':'Babies Garments'}" value="1" theme="simple" cssStyle="texts"/>
                        </td>
                        

                </tr>
                   
                <tr class="label-1" style="background-color: #FFFFFF;">
                    <td colspan="2" style="text-align: center">
                        <table><tr><td>
                                    <a href="#" class="sexybutton" onclick="onnSubmit('PDF');"><span><span><img src="../reports/css/ShahiButtons/images/icons/silk/pdf.png"/>Pdf</span></span></a>
                        </td>
                        
                        
                        <td style="width:20px"></td>
                        <td><a href="#" class="sexybutton" onclick="cln();"><span><span><span class="reload">Clear</span></span></span></a>
                        </td>
                        <td style="width:20px"></td>
                        <td><a href="#" class="sexybutton" onclick="self.close();"><span><span><span class="cancel">Exit</span></span></span></a>
                        </td>
                        </tr>
                        </table>

                </tr>
            </table>

        </form>

    </body>
</html>

