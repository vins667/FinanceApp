
<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
    <head>	
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />         
    <title>SEPL</title>
<link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>
<script src="js/fixedheader/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="js/fixedheader/table-fixed-header.css">
<script src="js/fixedheader/jquery-ui-1.7.2.custom.min.js"></script> 
<script src="js/fixedheader/jquery.chromatable.js"></script>
    <script>
       
        $(document).ready(function() {
            // make the header fixed on scroll
            $("#mytable").chromatable({
                width: "100%", // specify 100%, auto, or a fixed pixel amount
                height: "450px",
                scrolling: "yes" // must have the jquery-1.3.2.min.js script installed to use
            });
        });
        function ongosearch() {
            $('#formId').attr('action', 'mccenttempAction.action');
            $("#formId").submit();
        }
        function ongodelete() {
            if (confirm('Do you want to delete??')) {
                $('#formId').attr('action', 'mbillAction.action?delflg=Yes');
                $("#formId").submit();
            }
        }
        function ongonew() {
            $('#formId').attr('action', 'newmstmccenttempAction.action');
            $("#formId").submit();
        }
        function CheckAllDelete()
        {
            a = document.searchsubs.chkdel;
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    if (document.searchsubs.dchk.checked)
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = true;
                        }
                    }
                    else
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = false;
                        }
                    }
                }
            }
            else
            {
                if (document.searchsubs.dchk.checked)
                {
                    if (!a.disabled)
                    {
                        a.checked = true;
                    }
                }
                else
                {
                    if (!a.disabled)
                    {
                        a.checked = false;
                    }
                }
            }
        }
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height: 510px;
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 28px ;
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
<body style="text-align:center;margin:0px;">

    <form method="POST" id="formId" name="searchsubs" action=""> 
        <table style="background-color: #f2f2f2;width: 100%;" cellpadding="0" cellspacing="0"> 
            <tr><td colspan="10" class="hd" style="text-align: center">Department  Query (TEMP)</td></tr>
            <tr style="background-color: #b2cecf;height:35px;">
               <%-- <td class="label-1" style="width:10%;">Department Code</td>
                <td style="width:100px;"><s:textfield name="S_DEPT_CODE" id="S_DEPT_CODE" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" style="width:100%;text-transform:uppercase" /></td>
               --%>
                <td class="label-1" style="text-align: left;">Department <s:textfield  name="S_DEPT_DESC" id="S_DEPT_DESC" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" style="width:300px;text-transform:uppercase" /></td>
                <td style="text-align: right;">
                    &nbsp;&nbsp;&nbsp;<button onclick="ongosearch();" class="sexybutton"><span><span><span class="search">Search</span></span></span></button>
                    &nbsp;&nbsp;&nbsp;<button onclick="ongodelete()" class="sexybutton"><span><span><span class="cancel">Delete</span></span></span></button>
                    &nbsp;&nbsp;&nbsp;<button onclick="ongonew();" class="sexybutton"><span><span><span class="edit">New</span></span></span></button>
                    &nbsp;&nbsp;&nbsp;<button onclick="self.close();" class="sexybutton"><span><span><img src="css/ShahiButtons/images/icons/silk/bin_closed.png" alt="" />Exit</span></span></button>
                </td> 
            </tr>
            <tr>
                <td style="width:90%" colspan="10">                    
                       <div  class="div1" style="width:100%;overflow:auto ;height:500px;">
                                    <table border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                                        <thead>
                                      <tr  class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);height:20pt" >

                                          <th class="label-1">Sl No</th>
                                   
                                     <th class="label-1">Department</th>
                                     
                                     <th class="label-1">Action</th>
                                    <th class="label-1" style="width:70px;"><input name="dchk" type="checkbox" onclick="CheckAllDelete()">&nbsp;Delete</th>
                                </tr>
                            </thead>
                            <tbody>	
                                
                                <s:iterator value="%{deptmastlist}" status="st">
                                    <s:if test="%{FLAG=='EXIST'}">
                                       <tr style="background-color: #D8BFD8;text-align: left"> 
                                    </s:if>
                                    <s:else>
                                         <tr style="background-color: #FFFFFF;text-align: left">
                                    </s:else>
                                        <td style="height:20px;"><s:property value="%{#st.index+1}"/></td>			
                                        
                                        <td><s:property value="%{SUB_TYPE_CODE}"/></td>	
                                        <td><s:property value="%{DELFLAG}"/></td>	
                                       
                                        <td style="width:70px;">
                                            <a href="newmstmccenttempAction.action?DEPT_CODE=<s:property value="%{SL_NO}"/>" ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail"/></a></td>
                                        <td>
                                       <s:if test="%{FLAG=='EXIST'}">
                                      <input type="checkbox" name="chkdel" value='<s:property value="%{SL_NO}"/>' disabled="disabled"/>
                                    </s:if>
                                    <s:else>
                                        <input type="checkbox" name="chkdel" value='<s:property value="%{SL_NO}"/>'/> 
                                    </s:else>
                            
                                 </tr>						
                            </s:iterator>	
                            </tbody>
                        </table>  
                       </div>
                </td>
            </tr>
            <tr>
                <td style="text-align: center;color:red;font-size: 15px;width:90%;background-color: #FFFFFF;" colspan="10">  <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage /></td>
            </tr>
        </table>
           
    </form>
    </div>
    </section>
</body>
</html>