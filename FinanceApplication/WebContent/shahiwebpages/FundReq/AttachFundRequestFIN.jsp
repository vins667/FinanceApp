<%-- 
    Document   : AttachFundRequest
    Created on : Oct 11, 2012, 3:37:59 PM
    Author     : Vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page language="java" contentType="text/html" import="java.util.*"%>

<link href="../<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<script language="javascript" type="text/javascript" src="../shahiwebpages/js/shahijs/commonjs.js"/>
<html>
    <head>
        <sx:head/>
        <title>PO No-<s:property value="%{chkpoatt}"/></title>
        <script type="text/javascript">
            function uploadfile(){
                if(confirm('Do you want to upload file??')){
                    document.uploadfrm.action="FundReqFileUploadFIN.action?recupld=save";
                    document.uploadfrm.submit();
                }
            }
        </script>
    </head>
    <body>        
      <form action="" id="uploadfrm"   method="post" name="uploadfrm" enctype="multipart/form-data">
        <table style="width: 100%;height:100%;">
            <tr>
                <td>
                    <s:actionerror/>
                    <s:if test="%{FILE_NAME!=null && FILE_NAME.length()>1}">
                        <a href="<s:property value="%{FILE_NAME}"/>" target="new">View File</a>
                    </s:if>
                    &nbsp;
                </td>
            </tr>
            <tr style="vertical-align: middle;">
                <td class="label-1" style="border-width: 1px;border-style: solid;border-color: #6699CC;height: 30px;">Upload File
                    <s:hidden name="chkpoatt" id="chkpoatt"/>
                    <s:hidden name="reqno" id="reqno"/>
                    <s:hidden name="RQCHID" id="RQCHID"/>
                    <s:hidden name="reqsts" id="reqsts"/>                    
                    <s:file name="sketchPdf" theme="simple" cssClass="texts"  cssStyle="width:300px;height:28px;font-size:15px;"/>
                    <input type="button" name="submit12" value="Upload" class="texts" style="height: 28px;font-size:15px;width:65px;" onclick="uploadfile();"/>
                </td>
            </tr>            
        </table>
        </form>
        
    </body>
</html>
