<%-- 
    Document   : YESBankFileUpload
    Created on : May 3, 2017, 2:24:17 PM
    Author     : Amit
--%>


<!DOCTYPE html>
<html lang="en" ng-app="myApp">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <head>
        <meta charset="utf-8" />
        <title>Shahi Exports Pvt Ltd - HRIS</title>

        <!-- Sexy Buttons CSS -->
        <link href="nowcss/css/ShahiButtons/shahibuttons.css" rel="stylesheet">

        <!-- Bootstrap Core CSS -->
        <link href="nowcss/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="nowcss/css/modern-business.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/nowcss/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!--custom css of calendar  -->
        <link id="themes" href="nowcss/css/StyleCalender.css" rel="stylesheet">

        <!-- Main CSS -->
        <link href="nowcss/css/main.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="nowcss/js/html5shiv.js"></script>
            <script src="nowcss/js/respond.min.js"></script>
        <![endif]-->
        <!--[if lt IE 10]>
         <style type="text/css">
                .div1 {
                    position: relative;
                    height: 510px;
                    width: 700px;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                }
                thead tr {

                }
                tbody {
                    overflow-y: auto;
                    overflow-x: hidden; 
                }
            </style>
    <![endif]--> 
    </head>
    <body onload="waitPreloadPage();">
        <div class="navbar navbar-inverse nav-header">
            <span class="headtitle">                  
               YES BANK TXT FILE UPLOAD
            </span>
        </div>
        <DIV align="center" id="prepage" class="centered" style="visibility:  hidden;">
            <img src="images/loading.gif"/><br/><span style="font-weight: bold;color:#4D4D4D;font-size: 28px;">&nbsp;&nbsp;Please Wait...</span>			
        </DIV>
        <div class="container body-container">
            <!-- row -->
            <div class="row">
                <form name="frm" method="post" enctype="multipart/form-data">
                    <table border="0" style="width:100%;">
                        <tr>
                            <td valign="top">
                                <table align="center"  width="100%" cellpadding="8">
                                    <tr>
                                      <td valign="top" align="center"  >
                                         <table width="100%" cellpadding="0" class="seaechheader">
                                           <tr>
                                            <td style="width:100px;font-weight: bold;" class="tdpadding">Unit Code<span style="color:red;font-weight:bold;">*</span></td>
                                            <td style="width:120px;" class="tdpadding">
                                               <s:select name="UNIT_CODE" id="UNIT_CODE" list="# {'100':'SEPL','540':'AHPH','520':'AHPA','530':'SHA','210':'TRP'}" theme="simple"  cssStyle="width:150px;" headerKey="" headerValue="------------Select-----------"/>                                              
                                            </td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td style="width:130px;font-weight: bold;" class="tdpadding">Bank Code<span style="color:red;font-weight:bold;">*</span></td>
                                            <td style="width:120px;" class="tdpadding">
                                                <s:textfield name="BANK_CODE" id="BANK_CODE" value="18059" theme="simple" cssClass="textreadonly" cssStyle="width:100px;"/>                                             
                                            </td>
                                            <td style="vertical-align: top;text-align: right;">
                                               <a href="#" onclick="onSave();" class="sexybutton"><span><span><span class="save">Save</span></span></span></a>
                                               <a href="#" onclick="self.close();" class="sexybutton"><span><span><span class="cancel">Exit</span></span></span></a>   
                                           </td>
                                        </tr>    
                                     </table>
                                      </td></tr>

                                    <tr><td>
                                            <!--[if lt IE 10]>
                                                 <div  class="div1" style="width:100%;overflow-y: scroll;overflow-x: hidden; ;height:530px;">
                                             <![endif]--> 
                                            <s:if test="%{YESFILE_LIST!=null && YESFILE_LIST.size()>0}">
                                                <!--[if lt IE 10]>
                                                    <table class="table table-bordered table-striped" style='width:100%;'>
                                                <![endif]--> 
                                                <!--[if gt IE 9]>
                                                    <table class="table table-bordered table-striped" fixed-header style='height:530px;'>
                                                <![endif]--> 
                                                <![if !IE]>
                                                <table class="table table-bordered table-striped" fixed-header style='height:480px;'>
                                                    <![endif]>
                                                </s:if>
                                                <s:else>
                                                    <!--[if lt IE 10]>
                                                    <table class="table table-bordered table-striped" style='width:100%;'>
                                                    <![endif]-->
                                                    <!--[if gt IE 9]>
                                                        <table class="table table-bordered table-striped" fixed-header style='height:25px;'>
                                                    <![endif]--> 
                                                    <![if !IE]>
                                                    <table class="table table-bordered table-striped" fixed-header style='height:25px;'>
                                                        <![endif]>
                                                    </s:else>                                    
                                                    <thead>
                                                        <!--[if lt IE 10]>
                                                            <tr style="background-color: #9Aef64;position: relative; top: expression(this.offsetParent.scrollTop);height:35px">
                                                        <![endif]-->   
                                                        <!--[if gt IE 9]>
                                                            <tr style="background-color: #9Aef64;">
                                                        <![endif]--> 
                                                        <![if !IE]>
                                                        <tr style="background-color: #9Aef64;">
                                                            <![endif]>
                                                            <th>Transaction Date</th>
                                                            <th>VSER</th>
                                                            <th>VONO</th>
                                                            <th>Amount</th>
                                                            <th>C.D.Flag</th>
                                                            <th>Reference No</th>
                                                            <th>Value Date</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <s:if test="%{YESFILE_LIST!=null}">
                                                            <s:iterator value="YESFILE_LIST" status="itr">
                                                                <tr>
                                                                    <td>
                                                                        <s:hidden name="TRANS_DATE" id="TRANS_DATE%{#stat.index+1}" theme="simple" value="%{TRANS_DATE}"/>
                                                                        <s:property value="%{TRANS_DATE}"/>
                                                                    </td>
                                                                    <td>
                                                                        <s:hidden name="DESC" id="DESC%{#stat.index+1}" theme="simple" value="%{DESC}"/>
                                                                        <s:property value="%{DESC}"/>
                                                                    </td>
                                                                    <td>
                                                                        <s:hidden name="BRANCH_NAME" id="BRANCH_NAME%{#stat.index+1}" theme="simple" value="%{BRANCH_NAME}"/>
                                                                        <s:property value="%{BRANCH_NAME}"/>
                                                                    </td>
                                                                    <td>
                                                                        <s:hidden name="AMOUNT" id="AMOUNT%{#stat.index+1}" theme="simple" value="%{AMOUNT}"/>
                                                                        <s:property value="%{AMOUNT}"/>
                                                                    </td>
                                                                    <td>
                                                                        <s:hidden name="CD_FLAG" id="CD_FLAG%{#stat.index+1}" theme="simple" value="%{CD_FLAG}"/>
                                                                        <s:property value="%{CD_FLAG}"/>
                                                                    </td>
                                                                    <td>
                                                                        <s:hidden name="REF_NO" id="REF_NO%{#stat.index+1}" theme="simple" value="%{REF_NO}"/>
                                                                        <s:property value="%{REF_NO}"/>
                                                                    </td>
                                                                    <td>
                                                                        <s:hidden name="VAL_DATE" id="VAL_DATE%{#stat.index+1}" theme="simple" value="%{VAL_DATE}"/>
                                                                        <s:property value="%{VAL_DATE}"/>
                                                                    </td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:if>
                                                    </tbody>                                                       
                                                  </table>
                                                <!--[if lt IE 10]>
                                                 </div>
                                             <![endif]--> 
                                        </td> </tr>
                                    <tr><td>
                                        <table width="100%">
                                              <tr style="width:100%">
                                                  <td style="width:40%">&nbsp;</td>
                                                  <td style="text-align:left;width:18%">
                                                      <s:file type="file" name="unFile" theme="simple"/>&nbsp;
                                                  </td>  
                                                  <td style="vertical-align: top;">
                                                      <a href="#" class="sexybutton" onclick="uploadfile();"><span><span><span class="uploadd">Upload</span></span></span></a>
                                                  </td>
                                              </tr>
                                          </table>
                                      </td></tr>
                                </table>
                            </td></tr>
                        <tr style="height:20px;color:red;font-weight: bold;text-align: center;">
                            <td style="text-align: center;">
                                <s:actionerror />
                                <s:fielderror />
                                <s:actionmessage />
                            </td>
                        </tr>
                    </table>
                     <input type="hidden" name="myusrid" value="<s:property value="%{#session.sessUserId}"/>" >
                    <input type="hidden" name="PAMenu" value="<s:property value="%{PAMenu}"/>" >
                </form>
            </div>
        </div>
    </body>
    <script src="nowcss/js/angular.js"></script>
    <script src="nowcss/js/angu-fixed-header-table.js"></script>
    <script src="nowcss/js/js.js"></script>
    <script src="nowcss/js/demo.js"></script>
    <script language="javascript">
       function validatetxt()
            {
                if (document.frm.unFile.value == "")
                {
                    alert("Please Upload .txt File!!!");
                    document.frm.unFile.focus();
                    return false;
                }
                else
                {
                    var imagePath = document.frm.unFile.value;
                    var pathLength = imagePath.length;
                    var lastDot = imagePath.lastIndexOf(".");
                    var fileType = imagePath.substring(lastDot, pathLength);
                    if ((fileType == ".TXT") || (fileType == ".txt"))
                    {
                        i = true;
                    }
                    else
                    {
                        alert("Please Select txt file. Your file-type is " + fileType + "");
                        document.frm.unFile.focus();
                        return false;
                    }
                }
                return true;
            }
            
    //------------Validation---Befor save---------------------------
          function validatedel()
          {
            if(document.getElementById("UNIT_CODE").value=='')
             {
               alert("Please select Unit Code !!!"); 
               document.getElementById("UNIT_CODE").focus();
               return false;
             }
             else if(document.getElementById("BANK_CODE").value=='')
             {
                 alert("Please enter bank Code !!!"); 
                 document.getElementById("BANK_CODE").focus();
                 return false;     
             }
            
            return true;
        }

       //----------save-----------------------------------------
            function onSave()
            {
               if(validatedel()){
               if(confirm('Do you want to save?')){ 
                document.frm.action = "saveYESBankFileUploadAction?SAVE_FLAG=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
               }
               }
            }
          
      //--------------Upload File--------------------------------
            function uploadfile()
            {
                if (validatetxt() == true)
                {
                    document.frm.action = "uploadFileYESBankFileUploadAction";
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility = '';
                }
            }
    //--------wait and execute--------------------------------------          
            function waitPreloadPage() { //DOM
                if (document.getElementById) {
                    document.getElementById('prepage').style.visibility = 'hidden';
                } else {
                    if (document.layers) { //NS4
                        document.prepage.visibility = 'hidden';
                    }
                    else { //IE4
                        document.all.prepage.style.visibility = 'hidden';
                    }
                }
            }// end of function
    </script>
</html>
