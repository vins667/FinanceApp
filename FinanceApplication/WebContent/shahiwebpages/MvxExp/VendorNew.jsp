

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="../css/main.css"/>" rel="stylesheet"
          type="text/css"/>
<html>
    <head>
          <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
<style type="text/css">
           .datelabel{font-size:8pt;height:13pt;width:100pt;text-transform: uppercase}

          th {
        font-size:9pt;
        font-weight:bold;
        color:white;
        background-image:url('../image/style13.jpg');
    }
      tbody {
        height: 500px;
        overflow-y: auto;
        overflow-x: hidden;

     }

     .Btn {

	background-color: #BDC7CE;
	background-image: URL(../image/glossyback.gif);
	background-repeat:repeat-x;
	border: 1px solid #677788;
	padding-top:2px;
	padding-bottom:4px;
	padding-left:10px;
	padding-right:10px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #223546;
	cursor: hand;
        width: 130px;
        height:10pt;
        text-align:center;



}

 .stylelabel {

     left: 0px;
     top: 15px;
     margin: 0px;
     padding: 0px;
     cursor: pointer;
     height: 11px;
     font-weight:bold;
     text-align: center;
     color: #223546;
      font-size: 12px;
      font-family: Verdana, Arial, Helvetica, sans-serif;
     }

     a {
	color: #000000;

	text-decoration: none;
}

        </style>
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
 <script language="javascript">

function validateinput()
{
   return true;
}

      function vendlist()
        { window.open("VendAcListaction.action","vendlist","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=600m,height=400, top=192,left=367");

        }

             function recsave()
            {

                if(confirm('You Want to save Record ? '))
                { if(validateinput()){
                    document.getElementById("saveid").disabled=true;
                    document.frm.action="VendorNew.action?saveFlag=Yes";
                    document.frm.submit()
                }
                }else{
                    document.getElementById("saveid").disabled=false;
                    return ;}

            }
     function updsave()
            {
        if(confirm('You Want to Modify Record ? '))
                { if(validateinput()){
                    document.getElementById("updid").disabled=true;
                    document.frm.action="VendorNew.action?updFlag=Yes";
                    document.frm.submit()
                }
                }else{
                    document.getElementById("updid").disabled=false;
                    return ;}

            }




 </script>

    </head>

    <body class="body1" >

<form action=""  method="post" name="frm" >
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr ><td  class="hd" style="text-align:center">Vendor/Company Master</td></tr>
                  <tr><td>
                  <table align="center">
                  <tr><td>
               <table align="center">

               <tr><td >

                    </td></tr><tr><td height="300pt" valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
              <div  class="div1" style="width:100%;overflow:auto ;height:300pt;">
                <table align="center" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >

                    <tr><td>
                  <table align="center" border="0" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                    <thead >
                      <tr class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);height:20pt" >
                          <th class="label-1"  >Code</th>
                          <th class="label-1" >Name</th>
                          <th class="label-1" >Type</th>
                      </tr>
                      </thead>
                             <tr>
                             <s:if  test="%{vend_code!=null}">
                                 <td><s:textfield name="vend_code" readonly="true" cssStyle="width:70pt;text-transform:uppercase" theme="simple" value="%{vend_code}" maxlength="10" /></td>
                                 <td><s:textfield name="vend_name" readonly="true" cssStyle="width:250pt;text-transform:uppercase" theme="simple" value="%{vend_name}" maxlength="100" /></td>
                              </s:if>
                             <s:else>
                                 <td><s:textfield name="vend_code" cssStyle="width:50pt;text-transform:uppercase" theme="simple" value="%{vend_code}" maxlength="10" /></td>
                                 <td><s:textfield name="vend_name" cssStyle="width:250pt;text-transform:uppercase" theme="simple" value="%{vend_name}" maxlength="100" /></td>

                             </s:else>
                                <td><s:select name="vend_type" cssStyle="width:120pt;text-transform:uppercase" headerKey="CD" headerValue="COURIER PARTIES"  theme="simple" listKey="VEND_TYPECODE" listValue="VEND_TYPEDESC"  list="%{VendTypeList}" value="%{vend_type}" maxlength="2" /></td>
                              
                             </tr>
                         </table>
                       </td></tr> 
                       <tr><td>
                            <table align="center" border="`0" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >

                                 <tr>
                                     <td>Address :</td><td><s:textfield name="vend_addr" cssStyle="width:350pt;text-transform:uppercase" theme="simple" value="%{vend_addr}" maxlength="150" /></td>
                             
                                </tr>
                               </table>
                           </td></tr>
                       <tr><td>
                             <table align="center" border="`0" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
 
                                <tr>
                                <td>City </td><td><s:textfield name="city" cssStyle="text-transform:uppercase" theme="simple" value="%{city}" maxlength="50" /></td>
                                <td></td>
                                <td>State </td><td><s:textfield name="state" cssStyle="text-transform:uppercase" theme="simple" value="%{state}" maxlength="50" /></td>
                                </tr>
                               <tr>
                                <td>Pin code </td><td><s:textfield name="pin_code" cssStyle="text-transform:uppercase" theme="simple" value="%{pin_code}" maxlength="6" /></td>
                                 <td></td>
                                <td>Phones </td><td><s:textfield name="phones" cssStyle="text-transform:uppercase" theme="simple" value="%{phones}" maxlength="50" /></td>
                                </tr>
                                <tr>
                                <td>Fax </td><td><s:textfield name="fax" cssStyle="text-transform:uppercase" theme="simple" value="%{fax}" maxlength="50" /></td>
                                 <td></td>
                                <td>Email </td><td><s:textfield name="email" cssStyle="text-transform:uppercase" theme="simple" value="%{email}" maxlength="40" /></td>
                                </tr>
                                <tr>
                                <td>CST No </td><td><s:textfield name="cst_no" cssStyle="text-transform:uppercase" theme="simple" value="%{cst_no}" maxlength="50" /></td>
                               <td></td>
                                <td>CST Date </td><td><sx:datetimepicker name="cst_date1"   cssClass="txtd" value="%{cst_date}" displayFormat="dd/MM/yyyy"   /></td>
                                </tr>
                                 <tr>
                                <td>LST No </td><td><s:textfield name="lst_no" cssStyle="text-transform:uppercase" theme="simple" value="%{lst_no}" maxlength="50" /></td>
                                <td></td>
                                <td>LST Date </td><td><sx:datetimepicker name="lst_date1"   cssClass="txtd" value="%{lst_date}" displayFormat="dd/MM/yyyy"   /></td>
                                </tr>
                                <tr>
                                <td>TIN No </td><td><s:textfield name="tin_no" cssStyle="text-transform:uppercase" theme="simple" value="%{tin_no}" maxlength="50" /></td>
                                <td></td>
                                <td>TIN Date </td><td><sx:datetimepicker name="tin_date1"   cssClass="txtd" value="%{tin_date}" displayFormat="dd/MM/yyyy"   /></td>
                                </tr>
                                <tr>
                                <td>IEC No </td><td><s:textfield name="iec_no" cssStyle="text-transform:uppercase" theme="simple" value="%{iec_no}" maxlength="50" /></td>
                                <td></td>
                                <td>IEC Date </td><td><sx:datetimepicker name="iec_date1"   cssClass="txtd" value="%{iec_date}" displayFormat="dd/MM/yyyy"   /></td>
                                 </tr>

                             </table>
                       </td></tr>
                       <tr><td>
                        <table align="center" border="`0" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                            <tr>
                           <td>Fin.Comp ID.</td>
                             <td><s:select name="fin_comp_id" cssStyle="width:330pt;text-transform:uppercase" theme="simple" headerKey="" headerValue="" listKey="VEND_TYPECODE" listValue="VEND_TYPEDESC"  list="%{FinCompList}" value="%{fin_comp_id}" maxlength="3" />
                            </td>
                            </tr>
                            <tr>
                          <td>Account Code</td><td><s:textfield name="account_code" theme="simple" value="%{account_code}" cssStyle="width:80pt" />
                              <input type="button" name="vbtn" id="vid" value="List"  onclick="vendlist()" readonly="true" onkeypress="return tabE(this,event)" cssStyle="width:40pt">
                           <s:textfield name="account_name" theme="simple" readonly="true" value="%{account_name}" cssStyle="width:220pt" />
                          </td>
                            </tr>

                        </table>
                </td></tr>

             
                </table>
                 </div>
            </td></tr>
           <tr>
            <td height="1pt"  align="center" style="color:red;font-weight:bold">
                <div style="height:5pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />
                    <table cellpadding="0" bgcolor="gray" cellspacing="1"><tr bgcolor="#f2f2f2">
                            <td><table>
                    <s:iterator value="errorList" id="id">
                        <tr><td>
                         <s:property value="VEND_TYPECODE"/>
                         <s:set id="setid" name="setid" value="%{'Yes'}"/>
                        </td></tr>
                    </s:iterator>
                                </table>
                        </td>
                        <td><s:if test="%{#setid=='Yes'}">
                            Code Already Exist
                        </s:if>
                        </td>
                        </tr></table>
                </div>


            </td>
        </tr>
                  </table>

              </td></tr>
        <tr>
             <td colspan="2" >
                 <table width="100%" cellpadding="0" cellspacing="0"><tr><td>
                               <input type="button" name="backbnt" onclick="window.location.href='VendorMaster.action'" value="Back" class="submitbutton1">

                               </td><td align="right">
                                   <s:if  test="%{vend_code!=null}">
                                       <input type="button" name="updbtn" id="updid" value="Update" onclick="updsave()"  class="submitbutton1">
                                   </s:if>
                                   <s:else>
                                       <input type="button" name="savbtn" id="saveid" value="Save" onclick="recsave()"  class="submitbutton1">

                                   </s:else>
                               </td></tr></table>

              </td>

       </tr>
  </table>

                  </td></tr></table>
         </td></tr></table>
  </form>


    </body>

</html>


