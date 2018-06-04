<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<script type="text/javascript" src="js/dom-drag.js"></script>
<script type="text/javascript" src="js/jquery.min-1.5.2.js"></script>
<html>
    <head>
       <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
 
<style type="text/css">
    #container {
      position: relative;
      width: 1200px;
      height: 25px;
      overflow: hidden;
      border: 0px solid #EFEFEF;
            font-family: sans-serif;
            font-size: 12px;
            font-weight: bold;
    }

    #scroller {
      width: 1200px; /* = width + margin left + margin right + padding*2 + border*2  from the content rule */
    }

    .content {
      height: 25px;
      float: left;
      padding: 5px;
    }

    a
    {
      text-decoration: none;
      color:black;
    }

     th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('../../image/table-gradient.jpg');
            }
            tbody {
                height: 300px;
                overflow-y: auto;
                overflow-x: hidden;
            }
            .root
            {
                position:absolute;
                height:200px;
                width:800px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .handle
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px
                    
            }  
    .scrollNav, .scrollNav a:link, .scrollNav a:visited  {
      color:#00009C;
      font-weight: bold;
    }
    
</style>
  <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 30px ;
        
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);                     
                  }
                tbody {
                    height: auto;
                }
                tfoot{
                    background:#3383bb;
                    font-weight:bold;
                }
                .tr2 {
                     position:absolute;
                     bottom:expression(this.offsetParent.scrollTop);
                  }
            </style>
        <![endif]-->
<script type="text/javascript">
    function changeclassid(a)
    {
        var cc=document.getElementById(a)
        for(i=1; i<20; i++)
        {
            if(i==a)
            {
            cc.style.color= "blue";
            }else{
            document.getElementById(i).style.color='black';
            }
        }
    }
            function searchdetail()
            {   if((document.frm.searchinv.value=="" && document.frm.searchplan.value=="") )
                   {
                      alert("Please Enter Plan No or Invoice No ")
                      document.frm.searchplan.focus();
                      return false;

                  }
                document.frm.action="PREINVMVX.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }

</script>

</head>

<body class="body1"  oncontextmenu="return false;" >
 <form action=""  method="post" name="frm" id="frmid">
     <table height="86%" bgcolor="#f2f2f2"  align="center" width="100%">
                         <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Pre Shipment Invoice</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                     <table border="0" align="center" bgcolor="#cccccc" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                     <tr >
                                            <td class="label-1" width="20%">Facility : <s:textfield name="searchloct"   readonly="true" cssStyle="text-transform:uppercase;width:40pt" value="%{searchloct}" theme="simple" maxLength="10"/></td>
                                            <td class="label-1" width="20%">Invoice No : <s:textfield name="searchinv" cssStyle="text-transform:uppercase;width:70pt" theme="simple" maxLength="10"/></td>
                                            <td class="label-1" width="20%">Plan No : <s:textfield name="searchplan" cssStyle="text-transform:uppercase;width:70pt" theme="simple" maxLength="10"/>  
                                            <td style="text-align:left;">	 
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button> &nbsp;
                                                <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PREINVMVX.action?aausrid=<s:property value="%{usrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>&nbsp;
                                                <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                           </td>				
                                             
                                        </tr>
                                        
                                    </table> 
                                </td>
                            </tr>
                        </table>
                    </td></tr>
         <tr><td height="20pt" >
        <table  width="100%" cellpadding="0" cellspacing="0" >
        <tr style="background-image:url('../image/untitled.bmp');">
            <td align="left" style="padding-left:15pt" >
        <div  id="container">
          <div id="scroller">
              <div class="content" style="margin:0pt;"><a  style="color:blue;" id="1" href="PREINVMVX.action" onclick="changeclassid('1')" target="framecontent1"  title="Invoice Header"><b> Header</b></a> </div>
              <div class="content" style="margin:0pt;" ><a  id="2"  href="PREINVMVX.action" onclick="changeclassid('2')" target="framecontent1" title="Invoice Line Detail"  ><b>| Line Detail</b>   </a></div>
                        </div>
          <br style="clear:both">
        </div>
        </td>
        <td  align="right" style="padding-right:3.0pt;" >
        <input type="BUTTON" align="right" name="back" value="Back" class="submitbutton" onclick="window.location.href='EmpMaster.action?PAMenu=<s:property value="%{PAMenu}"/>'">
        </td>
        </tr>
        </table>
           </td></tr>
         <tr><td >
              <iframe name="framecontent1"  id="left"  width="100%"  height="100%"   frameborder="0"  src="PREINVMVX.action"/>"   scrolling="no"></iframe>

     </td></tr>
     </table>
        <script language="javascript">
            var theHandle = document.getElementById("handle");
            var theRoot   = document.getElementById("root");
            Drag.init(theHandle, theRoot);
           </script>
 </form>

</body>
</html>

