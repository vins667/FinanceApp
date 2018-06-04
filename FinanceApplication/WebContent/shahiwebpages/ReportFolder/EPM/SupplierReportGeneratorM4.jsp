<%-- 
    Document   : SupplierReportGeneratorM4
    Created on : May 16, 2012, 4:26:18 PM
    Author     : Vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Shahi Exports Pvt Ltd.</title>
        <LINK href="css/style.css" rel="stylesheet"	type="text/css">
        <script language="javascript">

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



            function HandleKeyup2(e)
            {
                if(!e)
                {
                    e = window.event;
                }
                if(e.keyCode == 13)
                {
                    addvalue();
                    //document.production.Add.focus();
                }
            }


            function subfrm()
            {
                if(confirm('Do You Want to generate the Supplier Payment Report ? '))
                {
                    document.production.action="SupplierReportGeneratorM4.action";
                    document.production.submit();
                }
                else
                {
                    return;
                }
            }

            function tabE(obj,e)
            {var e=(typeof event!='undefined')?window.event:e;// IE : Moz
                if(e.keyCode==13)
                {var ele = document.forms[0].elements;
                    for(var i=0;i<ele.length;i++)
                    {var q=(i==ele.length-1)?0:i+1;// if last element : if any other
                        if(obj==ele[i]){ele[q].focus();break}
                    }
                    return false;
                }
            }


        </script>

    </head>

    <body onLoad="waitPreloadPage();">
        <form name="production" action="" method="POST" onsubmit="return(checkblank());">
            <DIV align="center" id="prepage" class="lodingdiv" >
                <img src="image/progress.gif" >
                <br>
                <B >Loading ... ... Please wait ...</B>
            </DIV>
            <h1 align="center" class="label-1">Supplier Payment Upload Reports-M4</h1>

            <table cellpadding="10" align="center">
                <tr>
                    <td style="border-width:1pt;border-style:solid;border-color:Green">
                        <table cellpadding="40" cellspacing="1" bgcolor="#006699">
                            <tr bgcolor="#f2f9fb">
                                <td colspan="2">
                                    <table width="100%">
                                        <tr>
                                            <td><input type="BUTTON" name="btn" class="submitbutton" value="Process" onclick="subfrm()"></td>
                                            <td align="right"><input type="BUTTON" class="submitbutton" onclick="self.close()"  name="close" value="Exit"></td>
                                        </tr>
                                        <tr>
                                            <td class="label-1" align="center"><font color="red"><s:actionerror/></font></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
