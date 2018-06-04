


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>

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
             function searchrec()
            {
                 document.frm.action="ExchangeRate.action?saveFlag=Yes";
                document.frm.submit()
            }

       </script>
</head>

</head>
    </head>

    <body class="body1" >

<form action=""   method="post" name="frm" >
 <table align="center" width="100%">
     <tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >
              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr><td  class="hd" style="text-align:center">Custom Exchange Rate Master</td></tr>
                       <tr><td>
                  <table align="center">
                  <tr><td>
                  <table align="center">
                    <tr><td>
                   <table width="100%" align="center">
                     <tr><td height="50pt" width="100%" valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
                           <table  align="center" width="100%" cellpadding="6" border="0" >
                            <tr>
                               <td class="label-1">Currency  <s:select name="curr_code" theme="simple" headerKey="%" headerValue="ALL" listKey="CONTENT_CODE" listValue="CONTENT_DESC" list="errorList" cssStyle="width:150pt"/> </td>
                                <td class="label-1">Start Date</td><td><sx:datetimepicker name="start_date1"  cssClass="txtd" displayFormat="dd/MM/yyyy"   /></td>
                                <td class="label-1">End Date</td><td><sx:datetimepicker name="end_date1"  cssClass="txtd" displayFormat="dd/MM/yyyy"   /></td>
                                <td> <input  type="button" name="bnt" style="width:30pt" onclick="searchrec()" value="Go" class="submitbutton1"></td>
                                <td> <input  type="button" name="bnt" style="width:30pt" onclick="window.location.href='ExrateNew.action'" value="New" class="submitbutton1"></td>
                                 <td><input type="button" name="bnt" style="width:30pt" onclick="self.close();" value="Exit" class="submitbutton1"></td>

                            </tr>
                              </table>
                     </td></tr>
                  </table>
                      </td></tr>
             <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >

                    <div  class="div1" style="width:100%;overflow:auto ;">
                     <table  width="100%"  cellpadding="1" bgcolor="#d0d7e5" cellspacing="0"  >
                      <thead>
                      <tr class="hd"  style="position: absolute; top: expression(this.offsetParent.scrollTop);height:20pt" >
                           <th class="label-1" >Currency Desc</th>
                           <th class="label-1" >Crncy Code</th>
                           <th class="label-1" >Export Rate</th>
                           <th class="label-1" >Import Rate</th>
                            <th class="label-1 ">Start Date</th>
                           <th class="label-1" >End Date </th>
 
                       </tr>
                      </thead>
                        <s:if test="%{ShowDetail!=null}">
                             <s:iterator value="ShowDetail" id="itr">
                                 <tr bgcolor="white">
                                 <td style="font-size:10pt"><s:textfield cssClass="inputtxt" name="curr_desc"   readonly="true"  value="%{curr_desc}"  theme="simple" cssStyle="width:150pt"/></td>
                                 <td style="font-size:10pt"><s:textfield  cssClass="inputtxt" name="curr_code"   readonly="true"  value="%{curr_code}"  theme="simple" cssStyle="width:70pt"/></td>
                                 <td style="font-size:10pt"><s:textfield  cssClass="inputtxt" name="exp_rate"   readonly="true"  value="%{exp_rate}"  theme="simple" cssStyle="width:70pt"/></td>
                                 <td style="font-size:10pt"><s:textfield  cssClass="inputtxt" name="imp_rate"   readonly="true"  value="%{imp_rate}"  theme="simple" cssStyle="width:70pt"/></td>
                                 <td style="font-size:10pt" ><s:textfield  cssClass="inputtxt" name="begin_date" readonly="true"  value="%{begin_date}"  theme="simple" cssStyle="width:70pt" /></td>
                                 <td style="font-size:10pt"><s:textfield  cssClass="inputtxt" name="end_date" readonly="true"  value="%{end_date}"  theme="simple" cssStyle="width:70pt" /></td>
                                 </tr>
                            </s:iterator>
                       </s:if>
                 </table>
                 </div>
            </td></tr>
              </table>
               </td></tr>
              </table>
              </td></tr>
              </table>
    </td></tr>
   </table>
  </form>


    </body>

</html>

