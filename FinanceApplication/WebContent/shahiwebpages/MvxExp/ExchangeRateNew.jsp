

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


    CONTENT_CODE_obj=document.frm.curr_code1;
    CONTENT_DESC_obj=document.frm.exp_rate;
    CONTENT_GRPCODE_obj=document.frm.imp_rate;
    for(i=0; i<CONTENT_CODE_obj.length; i++)
        {

           if(i==0)
        {
        if(CONTENT_CODE_obj[0].value=="" || CONTENT_DESC_obj[0].value=="" || CONTENT_GRPCODE_obj[0].value=="" )
            {
               alert("Please Eneter All Detail")
               return false;
            }
        }else{
            if(CONTENT_CODE_obj[i].value!="")
            { if(CONTENT_DESC_obj[i].value=="")
                    {
                       alert("Please Eneter Export Rate")
                       CONTENT_DESC_obj[i].focus();
                       return false;
                    }
            }
             if(CONTENT_CODE_obj[i].value!="")
            { if(CONTENT_GRPCODE_obj[i].value=="")
                    {
                       alert("Please Eneter Import Rate")
                       CONTENT_GRPCODE_obj[i].focus();
                       return false;
                    }
            }  
            
        }

        }

   return true;
}



             function recsave()
            {

                if(confirm('You Want to save Records ? '))
                { if(validateinput()){
                    document.getElementById("saveid").disabled=true;
                    document.frm.action="ExrateNew.action?saveFlag=Yes";
                    document.frm.submit()
                }
                }else{
                    document.getElementById("saveid").disabled=false;
                    return ;}

            }




 </script>

    </head>

    <body class="body1" >

<form action=""  method="post" name="frm" >
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr ><td  class="hd" style="text-align:center">Custom Exchange Rate Master Entry</td></tr>
                  <tr><td>
                          <table   width="55%" border="0" align="center">
                  <tr><td>
                           <table width="100%" align="center">
  
               <tr><td >

                    </td></tr><tr><td height="300pt" width="100%" valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >

                <div  class="div1" style="width:100%;overflow:auto ;height:300pt;">
                    <table  width="100%" align="left" cellpadding="1" bgcolor="#d0d7e5" border="0" cellspacing="1" >
                    <thead >
                      <tr class="hd"  style="position: absolute; top: expression(this.offsetParent.scrollTop);height:20pt" >
                           <th class="label-1" >Currency Desc</th>
                           <th class="label-1 ">Start Date</th>
                           <th class="label-1" >End Date </th>
                           <th class="label-1" >Export Rate</th>
                           <th class="label-1" >Import Rate</th>
                      </tr>
                      </thead>
                       <tbody>
                            <tr>   
                                 <td class="label-1"><s:select name="curr_code1" theme="simple"  listKey="CONTENT_CODE" listValue="CONTENT_DESC" list="errorList"  cssStyle="width:200pt"  /> </td>
                                 <td class="label-1"><sx:datetimepicker name="start_date2"  cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><sx:datetimepicker name="end_date2"    cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="exp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="imp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                            </tr>
                            <tr>
                                 <td class="label-1"><s:select name="curr_code1" theme="simple"   listKey="CONTENT_CODE" listValue="CONTENT_DESC" list="errorList" cssStyle="width:200pt"/> </td>
                                <td class="label-1"><sx:datetimepicker name="start_date2"  cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><sx:datetimepicker name="end_date2"   cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt" /></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="exp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="imp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                            </tr>
                             <tr>
                                  <td class="label-1"><s:select name="curr_code1" theme="simple"   listKey="CONTENT_CODE" listValue="CONTENT_DESC" list="errorList" cssStyle="width:200pt"/> </td>
                                 <td class="label-1"><sx:datetimepicker name="start_date2"  cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><sx:datetimepicker name="end_date2" cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="exp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="imp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                            </tr>
                            <tr>
                                <td class="label-1"><s:select name="curr_code1" theme="simple"   listKey="CONTENT_CODE" listValue="CONTENT_DESC" list="errorList" cssStyle="width:200pt"/> </td>
                                <td class="label-1"><sx:datetimepicker name="start_date2" cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><sx:datetimepicker name="end_date2"  cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="exp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="imp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                            </tr>
                              <tr>
                                <td class="label-1"><s:select name="curr_code" theme="simple"   listKey="CONTENT_CODE" listValue="CONTENT_DESC" list="errorList" cssStyle="width:200pt"/> </td>
                                <td class="label-1"><sx:datetimepicker name="start_date2"  cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><sx:datetimepicker name="end_date2"   cssClass="txtd" displayFormat="dd/MM/yyyy" cssStyle="width:60pt"  /></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="exp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="imp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                            </tr>
                             <tr>
                                <td class="label-1"><s:select name="curr_code1" theme="simple"   listKey="CONTENT_CODE" listValue="CONTENT_DESC" list="errorList" cssStyle="width:200pt"/> </td>
                                <td class="label-1"><sx:datetimepicker name="start_date2" cssClass="txtd" displayFormat="dd/MM/yyyy"cssStyle="width:60pt"   /></td>
                                 <td class="label-1"><sx:datetimepicker name="end_date2"  cssClass="txtd" displayFormat="dd/MM/yyyy"  cssStyle="width:60pt" /></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="exp_rate"  theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                                 <td class="label-1"><s:textfield cssClass="inputtxt" name="imp_rate" theme="simple" cssStyle="width:60pt"  maxLength="6"/></td>
                            </tr>
                      </tbody>
                </table>
                 </div>
            </td></tr>
           </table>

              </td></tr>
        <tr>
             <td colspan="2" >
                 <table width="100%" cellpadding="0" cellspacing="0"><tr><td>
                               <input type="button" name="bnt" onclick="window.location.href='ExchangeRate.action'" value="Back" class="submitbutton1">

                               </td><td align="right">
                               <input type="button" name="btn" id="saveid" value="Save" onclick="recsave()"  class="submitbutton1">
                               </td></tr></table>

              </td>

       </tr>

  </table>
    <tr>
            <td height="1pt"  align="center" style="color:red;font-weight:bold">
                <div style="height:5pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />
                    <table cellpadding="0" bgcolor="grey" cellspacing="1"><tr bgcolor="red">
                            <td><table>
                    <s:iterator value="errorList1" id="id">
                        <tr><td>Start Date :<s:property value="CONTENT_DESC"/> End Date:<s:property value="STATUS"/>
                         <s:set id="setid" name="setid" value="%{'Yes'}"/>
                        </td></tr>
                    </s:iterator> 
                                </table>
                        </td>
                        <td><s:if test="%{#setid=='Yes'}">
                            Record Already Exist with Date Range
                        </s:if>
                        </td>
                        </tr></table>
                </div>


            </td>
        </tr>

                  </td></tr></table>
         </td></tr></table>
  </form>


    </body>

</html>

