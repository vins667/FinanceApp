<%-- 
    Document   : HDFTextUploadNew
    Created on : Apr 14, 2017, 9:46:05 AM
    Author     : Amit
--%>

<html lang="en" ng-app="myApp">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
    <%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <head>
        <sj:head jqueryui="true"/>
        <meta charset="utf-8" />
        <title>Shahi Exports Pvt Ltd - HRIS</title>

        <!-- Sexy Buttons CSS -->
        <link href="nowcss/css/ShahiButtons/shahibuttons.css" rel="stylesheet">

        <!-- Bootstrap Core CSS -->
        <link href="nowcss/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="nowcss/css/modern-business.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!--custom css of calendar  -->
        <link href="nowcss/css/responsive-calendar.css" rel="stylesheet">

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
        <title>Shahi Exports Pvt Ltd - HRIS</title>
        <script type="text/javascript">
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
            /*else if(document.getElementById("FROM_DATE").value=='')
            {
                alert("Please Choose From Date!!!");
                document.getElementById("FROM_DATE").focus();
                return false;
             }
             else if(document.getElementById("TO_DATE").value=='')
             {
                alert("Please Choose To Date!!!");
                document.getElementById("TO_DATE").focus();
                return false;
             }*/
            return true;
        }

       //----------save-----------------------------------------
            function onSave()
            {
               if(validatedel()){
               if(confirm('Do you want to save?')){ 
                document.frm.action = "saveHDFTextFileUploadAction?SAVE_FLAG=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
               }
               }
            }

           //----------save-----------------------------------------
            function onUpdate()
            {
               if(validatedel()){
               if(confirm('Do you want to save?')){ 
                document.frm.action = "saveNewHDFTextFileUploadAction?SAVE_NEW=YES";
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
                    document.frm.action = "uploadFileHDFTextFileUploadAction";
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
  </head>
  
    <body>
        <form name="frm" method="post" enctype="multipart/form-data">
            <DIV align="center" id="prepage" class="centered" style="visibility:hidden;">
                <img src="images/loading.gif"/><br/><span style="font-weight: bold;color:#4D4D4D;font-size: 28px;">&nbsp;&nbsp;Please Wait...</span>			
            </DIV>
            <div class="navbar navbar-inverse nav-header">
                <span class="headtitle">Upload HDF File</span>
            </div>
            <table border="0" bgcolor="#f2f2f2" style="width:100%;">
                <tr>
                    <td valign="top">
                        <table align="center"  width="100%" cellpadding="1">
                            <tr>
                                <td valign="top" align="center">
                                    <table width="100%" cellpadding="1" class="seaechheader">

                                        <tr>
                                            <td style="width:100px;font-weight: bold;" class="tdpadding">Unit Code<span style="color:red;font-weight:bold;">*</span></td>
                                            <td style="width:120px;" class="tdpadding">
                                               <s:select name="UNIT_CODE" id="UNIT_CODE" list="#{'100':'SEPL','540':'AHPH','520':'AHPA','530':'SHA','210':'TRP'}" theme="simple"  cssStyle="width:150px;" headerKey="" headerValue="------------Select-----------"/>                                              
                                            </td>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td style="width:130px;font-weight: bold;" class="tdpadding">Bank Code<span style="color:red;font-weight:bold;">*</span></td>
                                            <td style="width:120px;" class="tdpadding">
                                                <s:textfield name="BANK_CODE" id="BANK_CODE" value="18104" theme="simple" cssClass="text" cssStyle="width:100px;"/>                                             
                                            </td>
                                            <td style="width:130px;font-weight: bold;" class="tdpadding">From Date<span style="color:red;font-weight:bold;">*</span></td>
                                            <td style="width:150px;" class="tdpadding">
                                                <sj:datepicker name="FROM_DATE" id="FROM_DATE" value="%{FROM_DATE}" cssStyle="width:120px;" readonly="true" cssClass="textreadonly" displayFormat="dd.M.yy" buttonImageOnly="true"/>                                                 
                                            </td>
                                            <td style="width:120px;font-weight: bold;" class="tdpadding">TO Date<span style="color:red;font-weight:bold;">*</span></td>
                                            <td style="width:150px;" class="tdpadding">
                                                <sj:datepicker name="TO_DATE" id="TO_DATE" cssStyle="width:120px;" readonly="true" cssClass="textreadonly" displayFormat="dd.M.yy" buttonImageOnly="true"/>                                                 
                                            </td>
                                            <td style="vertical-align: top;text-align: right;">
                                               <s:if test="%{NEWHD_LIST!=null}">
                                                   <a href="#" onclick="onUpdate();" class="sexybutton" style="color:blue"><span><span><span class="save">SAVE</span></span></span></a>
                                               </s:if>
                                               <s:else>      
                                                 <a href="#" onclick="onSave();" class="sexybutton"><span><span><span class="save">Save</span></span></span></a>
                                               </s:else> 
                                               <a href="#" onclick="self.close();" class="sexybutton"><span><span><span class="cancel">Exit</span></span></span></a>   
                                           </td>
                                        </tr>                                        
                                    </table>
                                </td>
                            </tr>
                              <tr style="vertical-align: top;"> <td>
                                    <div style="width:100%; max-height:530px; overflow:auto">
                                        <s:if test="%{HDFCFILE_LIST!=null && HDFCFILE_LIST.size()>0}">
                                            <!--[if lt IE 10]>
                                                <table class="table table-bordered table-striped" style='width:100%;'>
                                            <![endif]--> 
                                            <!--[if gt IE 9]>
                                                <table class="table table-bordered table-striped" fixed-header style='height:470px;'>
                                            <![endif]--> 
                                            <![if !IE]>
                                            <table class="table table-bordered table-striped" fixed-header style='height:450px'>
                                                <![endif]>
                                        </s:if>
                                            <s:if test="%{NEWHD_LIST!=null && NEWHD_LIST.size()>0}">
                                                <!--[if lt IE 10]>
                                                    <table class="table table-bordered table-striped" style='width:100%;'>
                                                <![endif]--> 
                                                <!--[if gt IE 9]>
                                                    <table class="table table-bordered table-striped" fixed-header style='height:470px;'>
                                                <![endif]--> 
                                                <![if !IE]>
                                                <table class="table table-bordered table-striped" fixed-header style='height:25px'>
                                                    <![endif]>
                                                </s:if>   

                                                  <s:else>
                                                    <!--[if lt IE 10]>
                                                    <table class="table" style='width:100%;'>
                                                    <![endif]-->
                                                    <!--[if gt IE 9]>
                                                        <table class="table" fixed-header style='height:470px;'>
                                                    <![endif]--> 
                                                    <![if !IE]>
                                                    <table class="table" fixed-header style='height:25px'>
                                                        <![endif]>
                                                    </s:else>                                    
                                                    <thead>
                                                        <s:if test="%{NEWHD_LIST==null}">
                                                            <tr style="background-color: #9Aef64;">
                                                                <th>Transaction Date</th>
                                                                <th>Description</th>
                                                                <th>Amount</th>
                                                                <th>C.D.Flag</th>
                                                                <th>Reference No</th>
                                                                <th>Value Date</th>
                                                                <th>Branch Name</th>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <tr style="background-color: #9Aef64;">
                                                                <th>Transaction Date</th>
                                                                <th>VSER</th>
                                                                <th>VONO</th>
                                                                <th>Amount</th>
                                                                <th>C.D.Flag</th>
                                                                <th>Reference No</th>
                                                                <th>Value Date</th>
                                                            </tr> 
                                                        </s:else>
                                                    </thead>
                                                    <tbody> 
                                                        <s:if test="%{HDFCFILE_LIST!=null && HDFCFILE_LIST.size()>0}">
                                                            <s:hidden name="OPENNING_BLANCE" value="%{OPENNING_BLANCE}"/>
                                                            <s:hidden name="CLOSING_BALANCE" value="%{CLOSING_BALANCE}"/>
                                                            <s:hidden name="TODATE" value="%{TODATE}"/>
                                                            <s:iterator status="stat" value="%{HDFCFILE_LIST}">
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
                                                                    <td>
                                                                        <s:hidden name="BRANCH_NAME" id="BRANCH_NAME%{#stat.index+1}" theme="simple" value="%{BRANCH_NAME}"/>
                                                                        <s:property value="%{BRANCH_NAME}"/>
                                                                    </td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:if>
                                                        <s:if test="%{NEWHD_LIST!=null && NEWHD_LIST.size()>0}">
                                                            <s:iterator status="stat" value="%{NEWHD_LIST}">
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
                                          </div>
                                           </td></tr>
                                                <tr><td>
                                                      <table width="100%">
                                                            <tr style="width:100%">
                                                                <td style="width:40%">&nbsp;</td>
                                                                <td style="text-align:left;width:18%">
                                                                    <s:file type="file" name="unFile" theme="simple"/>&nbsp;
                                                                </td>  
                                                                <td style="vertical-align: top;">
                                                                    <a href="#" class="sexybutton" onclick="uploadfile()"><span><span><span class="uploadd">Upload</span></span></span></a>
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
         </form>
        </body>
         <script src="nowcss/js/angular.js"></script>
         <script src="nowcss/js/angu-fixed-header-table.js"></script>
         <script src="nowcss/js/js.js"></script>
         <script src="nowcss/js/demo.js"></script>
</html>

