
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<html> 
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('../../image/table-gradient.jpg');
            }
            tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }

            .toolTip {
                background-color: white;

                border: solid 1px;
                font-family: Arial;
                font-size: 12px;
                font-style: normal;
                font-variant: normal;
                font-weight: normal;
                left: 0;
                padding: 0px;
                position: absolute;
                text-align: left;
                top: 0;
                visibility: hidden;
                z-index: 2;
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
        <script language="javascript">

             
             function searchdetail()
            {      
                 
                document.frm.action="TRLANEMAST.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
                
            }
        function showhide(id,id1)
        {  
          if (document.getElementById){
          obj = document.getElementById(id);
          obj1 = document.getElementById(id1);
          if (obj.style.display == "none")
          { 
          obj.style.display = "";
          obj1.src="image/minus.jpg"; 
          }
          else {
          obj.style.display = "none";
          obj1.src="image/plus.jpg";
          }
          }
        }
            
            function waitPreloadPage() { //DOM
                if (document.getElementById){
                    document.getElementById('prepage').style.visibility='hidden';                    
                }else{
                    if (document.layers){ //NS4
                        document.prepage.visibility = 'hidden';                        
                    }
                    else { //IE4
                        document.all.prepage.style.visibility = 'hidden';                        
                    }
                }
            }
        </script>

    </head>

    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    OutBound Transporter Lane/Route Master</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                            <td class="label-1" >Transporter :<s:select name="searchtr" value="%{searchtr}" cssStyle="text-transform:uppercase;width:200pt" theme="simple" list="%{TRLIST}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  /></td> 
                                            <td class="label-1" >Origin : <s:select name="searchorg" value="%{searchtr}" cssStyle="text-transform:uppercase;width:140pt" theme="simple" list="%{ORGLIST}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  /></td> 
                                            <td class="label-1" >Destination :  <s:select name="searchdest" value="%{searchdest}" cssStyle="text-transform:uppercase;width:60pt" theme="simple" list="%{DESTLIST}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  /></td> 
                                            <td class="label-1" >Size :  <s:select name="searchsize" value="%{searchsize}" cssStyle="text-transform:uppercase;width:40pt" theme="simple" list="%{SIZELIST}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  /></td> 
                                         
                                                
                                            <td align="right">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                                 <button  id="newheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('TRLaneNew.jsp');"><span><span><span class="add" id="newId">New</span></span></span></button>  
						    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                    	   					      
                                            </td>
                                        </tr>
                                         
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        <div style="width:200pt;" class="toolTip" id="toolTipDiv"></div>
                        <div align="center" style="width:100.0%;">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr><td>
                                         <div  class="div1" style="width:100%;overflow:auto ;height:450pt;">
              
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                                <th align="left">Sl#</th>
                                                <th align="left">Transporter Code</th>
                                                <th align="left">Transporter Name </th>
                                                <th align="left">Origin Code</th>
                                                <th align="left">Origin Desc</th>
                                                <th align="left">Destination</th>
                                                <th align="left">Size </th>
                                                <th align="left">Auction </th> 
                                                <th align="left">Eff Date </th> 
                                                <th align="left">End Date </th> 
                                           </tr> 
                                           <s:iterator value="ShowDetail" id="itr" status="st">
                                            <tr bgcolor="#f2f9fb">
                                                <s:hidden  property="%{CTRL_NO}" />
                                                  <td style="font-size:8pt"><s:property value="%{#st.index+1}" /></td>
                                                   <td style="font-size:8pt">
                                                             <a href="#"  onclick="showhide('<s:property value="#st.index+1"/>','A<s:property value="#st.index+1" />'); return(false);"><img  id="A<s:property value="#st.index+1" />" style="border:0px" src="../../image/plus.jpg"></a>
                                                             <s:property value="TR_CODE" />
                                                         
                                                  </td>
                                                  <td style="font-size:8pt"><s:property value="TR_DESC"/></td>
                                                  <td style="font-size:8pt"><s:property value="ORGIN_CODE"/></td>
                                                  <td style="font-size:8pt"><s:property value="ORIGIN_DESC"/></td>
                                                  <td style="font-size:8pt"><s:property value="DESTN_CODE"/></td>
                                                   <td style="font-size:8pt"><s:property value="TRK_SIZE"/></td>
                                                   <td style="font-size:8pt"><s:property value="AUCTION_YN"/></td> 
                                                   <td style="font-size:8pt"><s:property value="EFF_DATE"/></td> 
                                                   <td style="font-size:8pt"><s:property value="END_DATE"/></td> 
                                             </tr> 
                                             <s:set name="Xpono" value="%{CTRL_NO}"/>
                                                   <tr  style="display:none"   id="<s:property value="#st.index+1"/>" >  
                                                       <td colspan="2">
                                                           <table style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                                   <th align="right"> Sl # </th><th align="center">Month </th><th align="right">Rate</th><th align="right">H/D %</th>
                                                                   <s:iterator value="FRTLIST.{?#this.CTRL_NO==#Xpono}"  status="st3" >
                                                                        <tr bgcolor="#f2f2f2">
                                                                               <td style="font-size:8pt;text-align: right" ><s:property value="%{#st3.index+1}" /></td>
                                                                               <td style="font-size:8pt;text-align: center"><s:property value="PROS_DATE"/></td>
                                                                               <td style="font-size:8pt;text-align: right"><s:property value="FRT_RATE"/></td>
                                                                               <td style="font-size:8pt;text-align: right"><s:property value="HD_PER"/></td>
                                                                        </tr>  
                                                                   </s:iterator>
                                                                  </table> 
                                                       </td>  
                                                   </tr>
                                                                 
                                                   
                                           </s:iterator>
                                          
                                             
                                        </table>
                                              </div>
                                    </td></tr> 

                                
                             
                              <tr>
                                <td height="1pt"  align="center" style="color:red;font-weight:bold">
                                    <div style="height:5pt">
                                        <s:actionerror />
                                        <s:fielderror />
                                        <s:actionmessage />
                                      </div>


                                 </td>
                                </tr>                               
                                                              
                                                              
                             </table>
                        </div>
                    </td></tr>
            </table>

   
   
                                                     
        </form>                    


    </body>
 
</html>

