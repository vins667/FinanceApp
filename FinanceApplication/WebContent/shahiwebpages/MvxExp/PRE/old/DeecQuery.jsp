<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
<html>
    <head 
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('image/table-gradient.jpg');
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
            
      function validateinput(){
           
        if(document.frm.searchitem.value=="")
        {
           alert("Please Enter Item No ")
            
           document.frm.searchitem.focus();
           return false;
        }
            
           return true;
      }
     

            
            function searchdetail()
            {   if(validateinput())
                {
                    document.frm.action="DeecQuery.action?viewFlag=Yes";
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility='';
                }
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
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Deec Query</td></tr>
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
                                            <td class="label-1" style="width:200px">Item Code : <s:textfield name="searchitem" value="%{searchitem}" cssStyle="text-transform:uppercase;width:60pt" theme="simple" maxLength="10"/>
                                            </td> 
                                            <td class="label-1" style="width:150px">Item Type : <s:select label="Type" theme="simple"  name="searchtype" value="%{searchtype}" list="#{'F':'Fabric ','T':'Trim '}" />
                                            </td>
                                            <td align="left">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                            </td>
                                            <td align="right">
                                              <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                           </td>
                                        </tr>
                                          
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        
                        <div  align="center" style="width:100.0%;">
                            <table width="100%" cellpadding="0" cellspacing="0"  border="1">
                                <tr><td>
                                        <div style="width:100%; height:300pt; overflow:auto; position:relative; "  >
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                            <tr>
                                               <th align="left">Sl #</th>
                                                <th align="left">PO No</th>
                                                <th align="left">Faci</th>
                                                <th align="left">Whlo</th>
                                                <th align="left">Type</th>
                                                <th align="left">Type Description</th>
                                                <th align="left">Crncy</th>
                                                <th align="left">Supplier</th>
                                                <th align="left">Supplier Name</th>
                                                 
                                            </tr>
                                           
                                                <s:iterator value="ShowDetail" status="st" >
                                                    
                                                    <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st.index+1}" /></td>
                                                            <td style="font-size:8pt">
                                                             <a href="#"  onclick="showhide('<s:property value="#st.index+1"/>','A<s:property value="#st.index+1" />'); return(false);"><img  id="A<s:property value="#st.index+1" />" style="border:0px" src="../../image/plus.jpg"></a>
                                                             <s:property value="IAPUNO" />
                                                           </td>
                                                            <td style="font-size:8pt"><s:property value="IAFACI"/></td>
                                                            <td style="font-size:8pt"><s:property value="IAWHLO"/></td>
                                                            <td style="font-size:8pt"><s:property value="IAORTY"/></td>
                                                            <td style="font-size:8pt"><s:property value="ORDESC"/></td>
                                                            <td style="font-size:8pt"><s:property value="IACUCD"/></td>
                                                            <td style="font-size:8pt"><s:property value="IASUNO"/></td>
                                                            <td style="font-size:8pt"  ><s:property value="SUPNAME"/></td>
                                                   </tr> 
 
                                                    <s:set name="Xpono" value="%{IAPUNO}"/>
                                                   <tr  style="display:none"   id="<s:property value="#st.index+1"/>" >  
                                                    <td colspan="9">
                                                        <table align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                        <tr bgcolor="#f2f2f2">    
                                                         <td valign="top" width="50%"  style="font-size:10pt; font-weight: bold;color: blue">Indent Details :-
                                                        
                                                               <table style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                                   <th align="left"> Sl # </th><th align="left">Indent No </th><th align="left">Indent General Description </th>
                                                                   <s:iterator value="indentList.{?#this.IAPUNO==#Xpono}"  status="st3" >
                                                                        <tr bgcolor="#f2f9fb">

                                                                               <td style="font-size:8pt"><s:property value="%{#st3.index+1}" /></td>
                                                                               <td style="font-size:8pt"><s:property value="IAFACI"/></td>
                                                                               <td style="font-size:8pt"><s:property value="IAWHLO"/></td>
                                                                        </tr> 
                                                                        <tr>
                                                                            <td colspan="3" valign="top" width="40%"  style="font-size:10pt; font-weight: bold;color:blue">AWBL Details :-
                                                                                    <table style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                                                        <s:set name="Xindno" value="%{IAFACI}"/>
                                                                                           <th align="left"> Sl # </th><th align="left">Type </th><th align="left"> Ref No</th><th align="left"> B/E No</th><th align="left"> B/E Date</th><th align="left"> Type</th>
                                                               
                                                                                        <s:iterator value="awblList.{?#this.IAPUNO==#Xindno}"  status="st4" >
                                                                                             <tr bgcolor="#f2f9fb">

                                                                                                    <td style="font-size:8pt"><s:property value="%{#st4.index+1}" /></td>
                                                                                                     <td style="font-size:8pt"><s:property value="IAFACI"/></td>
                                                                                                    <td style="font-size:8pt"><s:property value="IAORTY"/></td>
                                                                                                     <td style="font-size:8pt"><s:property value="ORDESC"/></td>
                                                                                                     <td style="font-size:8pt"><s:property value="IACUCD"/></td>
                                                                                                       <td style="font-size:8pt"><s:property value="IAWHLO"/></td>
                                                                                             </tr> 
                                                                                            <tr>
                                                                                                <td colspan="6" valign="top" width="40%"  style="font-size:10pt; font-weight: bold;color:blue">License Details :-
                                                                                                        <table style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                                                                            <s:set name="Xrefno" value="%{IAORTY}"/>
                                                                                                              <th align="left"> Sl # </th><th align="left">Type </th><th align="left"> Licence No</th><th align="right"> Qty SQM</th><th align="left"> B/E Description</th>
                                                               
                                                                                                            <s:iterator value="implicList.{?#this.IAFACI==#Xrefno && #this.IASUNO==#Xindno}"  status="st5" >
                                                                                                                 <tr bgcolor="#f2f9fb">

                                                                                                                        <td style="font-size:8pt"><s:property value="%{#st5.index+1}" /></td>
                                                                                                                        <td style="font-size:8pt"><s:property value="IAWHLO"/></td>
                                                                                                                        <td style="font-size:8pt"><s:property value="IAORTY"/></td>
                                                                                                                        <td style="font-size:8pt" align="right"><s:property value="ORDESC"/></td>
                                                                                                                        <td style="font-size:8pt"><s:property value="IACUCD"/></td>
                                                                                                                       

                                                                                                                 </tr> 
                                                                                                              </s:iterator>
                                                                                                         </table>
                                                                                                    </td>                                                                           
                                                                                            </tr>
                                                                                         
                                                                                         
                                                                                         </s:iterator>
                                                                                     </table>
                                                                                </td>                                                                           
                                                                        </tr>
                                                                     </s:iterator>
                                                                </table>
                                                           </td>
                                                           <td valign="top" width="10%" > </td>
                                                           <td valign="top"   style="font-size:10pt; font-weight: bold;color:blue ">Item Details :-
                                                               <table style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                                     <th align="left"> Sl # </th><th align="left">Item Code</th><th align="left">Item Description</th><th align="left">Type</th>
                                                               
                                                                   <s:iterator value="ItemList.{?#this.IAPUNO==#Xpono}"  status="st2" >
                                                                        <tr bgcolor="#f2f9fb">

                                                                               <td style="font-size:8pt"><s:property value="%{#st2.index+1}" /></td>
                                                                                <td style="font-size:8pt"><s:property value="IAFACI"/></td>
                                                                               <td style="font-size:8pt"><s:property value="IAWHLO"/></td>
                                                                               <td style="font-size:8pt"><s:property value="IAORTY"/></td>
                                                                        </tr> 
                                                                     </s:iterator>
                                                                </table>
                                                             </td>                                                           
                                                     </tr> 
                                                  </table>
                                                </td>      
                                             </tr>  
                                                   </s:iterator>
                                               </table>
                                        </div>
                                    </td></tr> 
                                  
                                <tr><td valign="top" >     
                              
                               <table bgcolor="#f0f0f0" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                               <tr>     
                                 <td style="font-size:10pt; font-weight: bold">Export Invoice Details :-
                                    <div style="width:100%; height:100pt; overflow:auto; position:relative; "  >   
                                       <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                               <th align="left">Sl #</th>
                                                <th align="left">Invoice No.</th>
                                                <th align="left">Buyer</th>
                                                <th align="left">Destn.</th>
                                                <th align="left">T/O Date</th>
                                                <th align="left">Awb Date</th>
                                                <th align="left">InvQty</th>
                                                <th align="left">Ship Type</th>
                                                <th align="left">Dbk/Deec</th>
                                                 
                                            </tr>
                                           
                                                <s:iterator value="InvList" status="st" >
                                                     <tr bgcolor="#f2f9fb">
                                                       
                                                            <td style="font-size:8pt"><s:property value="%{#st.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="IAPUNO"/></td>
                                                            <td style="font-size:8pt"><s:property value="IAFACI"/></td>
                                                            <td style="font-size:8pt"><s:property value="IAWHLO"/></td>
                                                            <td style="font-size:8pt"><s:property value="IAORTY"/></td>
                                                            <td style="font-size:8pt"><s:property value="ORDESC"/></td>
                                                            <td style="font-size:8pt"><s:property value="IACUCD"/></td>
                                                            <td style="font-size:8pt"><s:property value="IASUNO"/></td>
                                                            <td style="font-size:8pt"><s:property value="SUPNAME"/></td>
                                                     </tr> 
                                                </s:iterator>
                                                
                                               </table>
                                        </div>
                                    </td>
                             
                                   <td style="font-size:10pt; font-weight: bold">Customer Details :-
                                       <div style="width:100%; height:100pt; overflow:auto; position:relative; "  > 
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                               <th align="left">Sl #</th>
                                                <th align="left">Order No.</th>
                                                 <th align="left">Order Qty</th>
                                                <th align="left">Qty>= Status 66</th>
                                            </tr>
                                           
                                                <s:iterator value="OrdQtyList" id="itr1" status="st1">
                                                       <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="IAPUNO"/></td>
                                                             <td style="font-size:8pt"><s:property value="IAWHLO"/></td>
                                                            <td style="font-size:8pt"><s:property value="IAORTY"/></td>
                                                       </tr> 
                                                </s:iterator>
                                                
                                               </table>
                                       </div>
                                    </td>
                                  </tr>
                               </table>
                               
                              </td>
                             </tr>
                    
                             </table>
                        </div>
                    </td></tr>
            </table>

   
   
                                                     
        </form>                    


    </body>
 
</html>

