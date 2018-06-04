<%--
    Document   : UtrNoUploadM4
    Created on : Mar 28, 2012, 9:55:59 AM
    Author     : Vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page language="java" contentType="text/html" import="java.util.*"%>

<link href="css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/stylishbuttons.css" type="text/css" />
<html>
    <head>
        <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            .realupload {
                -moz-opacity:50;
                filter:alpha(opacity: 50);
                z-index: 5;
                border-width:1px;
                border-color: #007fbf;
                border-style: outset;
            }
            th {
                font-size:9pt;
                text-align: left;
                font-weight:bold;
                color:#0066aa;
                background-image:url('image/table-gradient.jpg');
            }
        </style>
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
            function recsave(){
                if(validate()==true){
                    if(confirm("Do you want to Upload file??")){
                        document.frm.action="UtrNoUploadM4Action.action";
                        document.frm.submit();
                    }
                    else{
                        return false;
                    }
                }
            }
            function validate(){
                if(document.frm.sketchFile.value==""){
                    alert("Please Select the file");
                    document.frm.sketchFile.focus();
                    return false;
                }
                return true;
            }
        </script>
    </head>

    <body  class="body1"  onload="waitPreloadPage();">
        <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form name="frm" id="frmid" action="#" method="post" enctype="multipart/form-data">
            <input type="hidden" name="aausrid"  value="<%=request.getParameter("aausrid")%>"/>
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr>
                    <td>
                       <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#60C8F2">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:12.0pt; font-weight:bold; font-family:Garamond; color:#2633A8;">
                                    UTR&nbsp;&nbsp;&nbsp;NUMBER&nbsp;&nbsp;&nbsp;UPLOAD
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <br/><br/>
                        <br/><br/>
                        <br/>
                        <table width="900px" style="" cellpadding="0" cellspacing="0" align="center">
                            <br/>
                            <tr>
                                <td valign="middle">
                                    <table border="0"  cellpadding="0" align="center" width="100%"  style="border-width:5.0px; border-color:rgb(204,204,204); border-style:solid; width:600.0px; height:300.0px; background-color:#E7E4D3;" >
                                        <tr height="50px">
                                            <td class="label-1">File UPLOAD (PATH)</td>
                                            <td>
                                                <s:file name="sketchFile" theme="simple" cssClass="realupload"  cssStyle="width:367px;height:26px;font-size:18px;" onchange="return setimage(this);"/>
                                                <br/>
                                                <font size="2px" color="red"><b>format&rarr;</b> txt</font>
                                            </td>
                                        </tr>
                                        <tr align="center" height="84px">
                                            <td colspan="2">
                                                <table align="center">
                                                    <tr>
                                                        <td class="sexybutton">
                                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="recsave();">
                                                                <span><span><img src="css/images/icons/silk/Silk_upload.png"  alt="" style="border-width: 0pt;"/>Upload</span></span>
                                                            </s:a>
                                                        </td>
                                                        <td>&nbsp;&nbsp;&nbsp;</td>
                                                        <td class="sexybutton">
                                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="self.close();">
                                                                <span><span><img src="css/images/icons/silk/exit.png"  alt="" style="border-width: 0pt;"/>Close</span></span>
                                                            </s:a>
                                                        </td>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table border="0" align="center" width="100%" >
                                        <tr>
                                            <td height="1pt"  align="center" style="color:red;font-weight:bold">
                                                <div style="height:5pt">                                                    
                                                    <%--s:if test="errorlist!=null && errorlist.size()>0">
                                                        <font color="red">Vono Already Exist :
                                                            <s:iterator value="errorlist" status="st123">
                                                                <s:if test="%{#st123.index]==errorlist.size()-1}">
                                                                   <s:property value="%{errorlist[#st123.index]}"/>.
                                                                </s:if>
                                                                <s:else>
                                                                    <s:property value="%{errorlist[#st123.index]}"/>,&nbsp;
                                                                </s:else>
                                                            </s:iterator>
                                                        </font>
                                                    </s:if--%>
                                                    <s:actionerror />
                                                    <s:fielderror />
                                                    <s:actionmessage />
                                                </div>
                                            </td>
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
