<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.ibm.as400.access.*"%>
<%@ page import="com.shahi.misreports.util.ConnectionMovexBi"%>
<html>
    <head>
        <title>Shahi Export Pvt Ltd</title>
        <link href="../style/style.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">
            body {
                font: 12px verdana, arial, helvetica, sans-serif;
                background-color:#FFFFFF;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;
                margin-left: 0px;

            }
        </style>
        <script language="javascript">
            function tabE(obj,e){ 
                var e=(typeof event!='undefined')?window.event:e;// IE : Moz 
                if(e.keyCode==13){ 
                    var ele = document.forms[0].elements; 
                    for(var i=0;i<ele.length;i++){ 
                        var q=(i==ele.length-1)?0:i+1;// if last element : if any other 
                        if(obj==ele[i]){ele[q].focus();break} 
                    } 
                    return false; 
                } 
            } 
    
  
  
            function submitfrm()
            {
                document.hris.action='BuyerList.jsp'
                document.hris.submit();
  
            }
            function selectbuyerdtlist()
            {
                aa=document.getElementById("buyerdtlist");
                bb=parent.BuyerPerformance.buyerList;
                SelectMoveRows(aa,bb);
            }
            
            function removebuyerdtlist()
            {
                aa=document.getElementById("buyerdtlist");
                bb=parent.BuyerPerformance.buyerList;
                SelectMoveRows(bb,aa);
            }
            
            function SelectMoveRows(SS1,SS2)
            {
                var SelID='';
                var SelText='';
                // Move rows from SS1 to SS2 from bottom to top
                for (i=SS1.options.length - 1; i>=0; i--)
                {
                    if (SS1.options[i].selected == true)
                    {
                        SelID=SS1.options[i].value;
                        SelText=SS1.options[i].text;
                        var newRow = new Option(SelText,SelID);
                        SS2.options[SS2.length]=newRow;
                        SS1.options[i]=null;
                    }
                }
                SelectSort(SS2);
            }
            function SelectSort(SelList)
            {
                var ID='';
                var Text='';
                for (x=0; x < SelList.length - 1; x++)
                {
                    for (y=x + 1; y < SelList.length; y++)
                    {
                        if (SelList[x].text > SelList[y].text)
                        {
                            // Swap rows
                            ID=SelList[x].value;
                            Text=SelList[x].text;
                            SelList[x].value=SelList[y].value;
                            SelList[x].text=SelList[y].text;
                            SelList[y].value=ID;
                            SelList[y].text=Text;
                        }
                    }
                }
            }
  
        </script>
    </head>  
    <body>
        <form name="hris" method="POST" action="">
            <table>
                <tr>
                    <td>                        
                        <INPUT NAME=regexp class="texts" onblur="submitfrm();" onkeypress="return tabE(this,event)" style="width:150px">&nbsp;&nbsp;<input type="BUTTON" name="search" value="Search" class="whitesubmitbutton" onclick="submitfrm();" style="width:80px">
                    </td>
                </tr>
                <tr>
                    <td>
                        <select name="buyerdtlist" id="buyerdtlist" style="width:250px;height:100px" size="12" ondblclick="selectbuyerdtlist()" class="texts" >
                            <%
                                Connection con = null;
                                //ResourceBundle aResourcBundle = null;
                                //aResourcBundle = ResourceBundle.getBundle("com.shahi.misreports.util.app");
                                con = new ConnectionMovexBi().getConnection();
                                //String location = (String)session.getValue("LOCATION_CODE");
                                PreparedStatement emp_stat = null;
                                String aa = null;
                                ResultSet emp_result = null;
                                if (request.getParameter("regexp") != null && request.getParameter("regexp").length() > 0) {
                                    aa = "%" + request.getParameter("regexp").toUpperCase() + "%";
                                    emp_stat = con.prepareStatement("select okcuno,okcunm from prodbi.ocusma where OKCONO='111' and okcunm like ? and rownum<21 order by okcunm ");
                                    emp_stat.setString(1, aa);
                                    emp_result = emp_stat.executeQuery();
                                    while (emp_result.next()) {
                            %>
                            <option value="<%=emp_result.getString("okcuno")%>" ><%=emp_result.getString("okcunm")%>-<%=emp_result.getString("okcuno")%></option>
                            <%}

                                }

                            %> 




                        </select>
                    </td>
                    <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButtonStyle" onclick="selectbuyerdtlist();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;<img src="../images/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButtonStyle" onclick="removebuyerdtlist();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;<img src="../images/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                    </td>
                </tr>
            </table>
        </form>
    </body>
    <%con.close();%>
</html>
