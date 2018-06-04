
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
   <head>
        <title>Shahi Exports Pvt Ltd</title>
        </head>
 <script language="javascript">

    function refcheck(){
    	document.compliance.action="downloadHDFCFileBankLog.action";
    	document.compliance.method="post";
        document.compliance.submit();
    }

    function refblank()
    {
   if (document.compliance.BSUNO.value=="" )
   {
   alert("Ref. No. Can Not Blank. ....!");
   document.compliance.BSUNO.focus();
   return false;
   }
    return true
   }
  </script>

   
    <body>
       <s:form name="compliance">
           <BR>
         
        <center style="outline-color:rgb(153,0,51);"><h6 style="font-size:20.0pt; font-family:Monotype Corsiva;">HDFC Bank LOG (FBAD)</h6>
            <BR> <BR>
            <table cellspacing="0" cellpadding="0">
        <tr><td>


        <table  cellspacing="1" cellpadding="12"  align="center">

       <tr bgcolor="White">
      <td> Enter Log Date (DDMMYYYY) </td>
      <td>
      <s:textfield name="fileDate" class="txtField" onkeypress="return tabE(this,event)" theme="simple" /> </td>
      </tr>
        </table>
       </table>

      
 <br>
  <table border="0" cellpadding="10" cellspacing="20">
   <tr>
    <td>

    <input type="Button" class="title" value="Show" onclick="refcheck();">
    </td>
    <td><input type="reset" class="title" onclick="window.location.href='YesLogDel.jsp'" value="Clear" ></td>
    <td><input type="Button" class="title" value="Exit" onclick="self.close();" ></td>
   </tr>
   </table>

  </center>

  </s:form>

    </body>
</html>
