
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
        function searchcodemaster()
            {

              document.frm.action="CrncyMaster.action?saveFlag=Yes";
                  document.frm.submit()
            }
            function delmaster()
            {

              document.frm.action="CrncyMaster.action?delFlag=Yes";
                  document.frm.submit()
            }
       </script>

    </head>

    <body class="body1" >

<form action=""   method="post" name="frm" >
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr><td  class="hd" style="text-align:center">Currency Master List</td></tr>
                  <tr><td>
                  <table align="center">
                  <tr><td>
                  <table align="center">
                     <tr><td >
                            <table width="100%" cellpadding="5">
                            <tr><td class="label-1" style="border-width:1pt;border-color:#2680b5;border-style:solid;">
                             </td>
                            </tr>
                           </table>
                            <table width="100%" cellpadding="3">
                            <tr><td>
                                <td class="label-1">Code <s:textfield name="searchcode" cssStyle="text-transform:uppercase;width:50pt" theme="simple" maxLength="10"/></td>
                                 <td class="label-1">Description <s:textfield name="searchdesc" cssStyle="text-transform:uppercase" theme="simple" maxLength="100"/></td>
                                
                                 <td> <input  type="button" name="bnt" style="width:50pt" onclick="searchcodemaster()" value="Go" class="submitbutton1"></td>
                                 <td> <input  type="button" name="bnt" style="width:50pt" onclick="window.location.href='CrncyNew.jsp'" value="New" class="submitbutton1"></td>
                                 <td> <input  type="button" name="bnt" style="width:50pt" onclick="delmaster()" value="Delete" class="submitbutton1"></td>

                                 <td><input type="button" name="bnt" style="width:50pt" onclick="self.close();" value="Exit" class="submitbutton1"></td>

                            </tr></table>
                    </td></tr>
                    <tr><td height="280pt" valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >

                 <div  class="div1" style="width:100%;overflow:auto ;height:320pt;">
                     <table align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                    <thead >
                      <tr class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);height:20pt" >
                          <th class="label-1" style="width:10%">Code</th>
                          <th class="label-1" >Description</th>
                           <th class="label-1" style="width:20%">Decimal Print</th>
                           <th class="label-1" style="width:10%">Delete</th>
                      </tr>
                      </thead>
                       <tbody>
                         <s:if test="%{errorList!=null}">
                           <s:iterator value="errorList" id="itr">
                               <tr bgcolor="white">
                                <td style="font-size:8pt"><s:property value="CONTENT_CODE"/></td>
                               <td style="font-size:8pt"><s:property value="CONTENT_DESC"/></td>
                                <td style="font-size:8pt"><s:property value="CONTENT_GRPCODE"/></td>

                                <td><s:if test="%{STATUS==0}">
                                   <input type="checkbox" name="chkdel" value="<s:property value="CONTENT_CODE"/>">
                                   </s:if>
                               </td>
                               </s:iterator>
                           </tr>
                          </s:if>
                      </tbody>
                </table>
                 </div>
            </td></tr>
           <tr>
            <td height="1pt"  align="center" style="color:red;font-weight:bold">
                <div style="height:3pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />

                </div>


            </td>
        </tr>
                  </table>

              </td></tr>

  </table>

                  </td></tr></table>
         </td></tr></table>
  </form>


    </body>

</html>

