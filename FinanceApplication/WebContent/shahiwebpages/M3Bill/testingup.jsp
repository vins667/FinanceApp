<%-- 
    Document   : invmast
    Created on : Sep 28, 2013, 4:23:30 PM
    Author     : RANJEET
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="css/style.css"> 
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>  
<SCRIPT TYPE="text/javascript" SRC="js/filterlist.js"></SCRIPT>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sepl</title>
        
         <style>
            
           .textreadonly{
        font-family: Arial, Sans-Serif;
        font-size: 11px;
        border: solid 1px #677788;
        background-color:#e6e6e6;
        text-transform: uppercase;
    }
.texts{
        font-family: Arial, Sans-Serif;
        font-size: 11px;
        border: solid 1px #677788;
        text-transform: uppercase;
        
    }
    
  
    .selecttext{
        font-family: Arial, Sans-Serif;
        font-size: 10px;
        border: solid 1px #677788;
        text-transform: uppercase;
    }
    
    
        </style>
         <style type="text/css">
         
      tbody {
        height: 500px;
        overflow-y: auto;
        overflow-x: hidden;

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
            padding-top: 23px ;
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
            
    function onsave()
    {
        if(validateinput()==true){
        if(confirm('Do you want to Save?')){
     document.getElementById('formIdinv').action="testingupmbillentAction.action?savetest=Yes"  ;
     document.getElementById('formIdinv').submit(); 
        }else{
            return;
        }
        }
    }
    
    
    function validateinput()
    {
     
    if(document.getElementById('formIdinv').INV_REPORT)
        {}else{
            alert("Record Not Found For Save...");
            return false;
        }
        
        
           var BREAK_CODE=document.frmname.INV_REPORT;
            for(i=0; i<BREAK_CODE.length; i++)
                  { 
                    for(j=i+1; j<BREAK_CODE.length; j++)  
                    {    if(BREAK_CODE[j].value!=""){
                           
                         if(BREAK_CODE[i].value==BREAK_CODE[j].value)
                             {
                               alert("Duplicate  Entry Found")  ;
                              
                               BREAK_CODE[j].focus();
                               
                               return false;
                             }}
                    }
                      
                  }


          
        
        return true;
    } 
          
 function selectbuyer()
{
    
var index = document.getElementById('buyermyselect').selectedIndex;

document.getElementById('BUYERCODETEST').value=document.getElementById('buyermyselect').options[index].value;
document.getElementById('BUYERNAMETEST').value=document.getElementById('buyermyselect').options[index].text;
document.getElementById("BUYERDIV").style.display = "none";

}
 
 function AddRowTable(tableID)
    {   var table = document.getElementById(tableID);
       
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var colCount = table.rows[1].cells.length;
      
        for(var i=0; i<colCount; i++)
        {   var newcell = row.insertCell(i);
            newcell.innerHTML = table.rows[1].cells[i].innerHTML;
          
             switch(newcell.childNodes[0].type) {
                 case "text":
                     newcell.childNodes[0].value = "";
                     break;
                  case "checkbox":
                      newcell.childNodes[0].checked = false;
                      break;
                  case "select-one":
                      newcell.childNodes[0].selectedIndex = 0;
                      break;
              }
          }
      }
   
            
            
            
            </script>
    </head>
    <body style="background-color:#f2f2f2;padding: 0px;margin: 0px">
        <form method="POST" id="formIdinv" name="frmname" action="" style="margin: 0px;padding: 0px"> 
             <s:hidden name="MAST_SL_NO" value="%{MAST_SL_NO}"/>
            <table width="100%" cellpadding="0" cellspacing="0"><tr><td style="border-width:1pt;border-color:black;border-style:solid;">                           
          
          <table cellpadding='1' width="100%"  cellspacing='1'>
                                        <tr><td>
                                           <s:textfield name="BUYERCODETEST" id="BUYERCODETEST" readonly="true" theme="simple" maxlength="10" cssClass="textreadonly" cssStyle="width:100px;text-transform: uppercase;"  />
                                           <s:textfield name="BUYERNAMETEST" id="BUYERNAMETEST" readonly="true" theme="simple"  cssClass="textreadonly" cssStyle="width:355px;text-transform: uppercase;" />
                                           
                                           <a href="#" class="search"  onclick='document.getElementById("BUYERDIV").style.display="block";' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                            
                                          </td></tr>
                                      <tr class="hd" style="height:15pt">
                                          <th class="label-1">Report No</th>
                                      </tr>
                                        <tr>
                                        <td>
                                         <div style="width:220px;overflow: auto;height:250px;">
                                         <table id="tableaddid">
                                              <s:iterator value="invsavelist" status="invsavelistst">
                                            <tr style='background-color: #ffffce'>
                                                <td><s:textfield name="INV_REPORT" id="INV_REPORT" theme="simple"  maxlength="20" cssClass="texts" cssStyle="width:200px;text-transform: uppercase;" value="%{EAAITM}"/>

                                                </td>

                                            </tr> 
                                         </s:iterator>
                                         <s:if test="%{FORWARD_DATE==null}">
                                        <s:iterator begin="%{invsavelist.size()}" end="9" status="stbegin">
                                          <tr><td>
                                           <s:textfield name="INV_REPORT" id="INV_REPORT" theme="simple" maxlength="20" cssClass="texts" cssStyle="width:200px;text-transform: uppercase;"  value=""/>
                                           </td>
                                              </tr>
                                              
                                              </s:iterator>
                                              </s:if>
                                          </table>
                                            </div> 
                                      </td>
                                     
                                       </tr>
                                    </table>
          
         </td></tr>
       
         <s:if test="%{FORWARD_DATE==null}">
        <tr><td align="center">
                <table cellpadding="0" width="100%"><tr><td width="300px">
                           <table>
                             <tr>
                                                <td><div style="width:25px;height:20px;background-color:#ffffce;border-width:1pt;border-color:#ffffce;border-style:solid;"></div></td><td class="label-1">Saved Entry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:#FFFFFF;border-width:1pt;border-color:#FFFFFF;border-style:solid;"></div></td><td class="label-1">New Entry</td>
                                            </tr>
                                            </table> 
                            
                        </td><td>
                <table cellpadding="0" cellspacing="0"><tr>
                        
                        <td>
                           <button class="sexybutton" onclick="AddRowTable('tableaddid')"><span><span><span class="add">Add Row</span></span></span></button> 
                                 &nbsp;
                <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                        
                </td>
                <td style="font-size:12px;color:red"  >  
                   
                </td>
        </tr></table>
                        </td>
                </table>
            </td>
        </tr>
        <tr> <td  align="center" style="font-size:12px;color:red"  >  
                    <s:actionerror theme="simple" />
                    <s:fielderror theme="simple" />
                    <s:actionmessage theme="simple" />
                </td></tr>
        
        
                       
        </s:if>
                </table>
            
        </form>
                                           
                       <div id='BUYERDIV' name='BUYERDIV' style='width: 300px; height: 200x; display:none; position: absolute; top: 20px; left:200px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='320px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='300px'  style="font-size:12px;color:white;font-weight: bold">Buyer Details 
                            </td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("BUYERDIV").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        
                        <tr><td colspan="2" valign='top' >
                               
                                    <table cellpadding='0' width="100%"  cellspacing='1'>
                                        <tr><td class="label-1"> Search 
                    <input type="text" id="regexp" name="regexp" class="texts" onKeyUp="myfilter.set(this.value)"/>
                    
                                            </td>
                                    </tr>
                                        
                                        <tr><td>
                                                <s:select name="buyermyselect" theme="simple" id="buyermyselect" cssClass="texts"
                                                          list="buyerlist"
                                                          listKey="WHLO"
                                                          listValue="WHNM"
                                                          ondblclick="selectbuyer()"
                                                          size="15"
                                                          cssStyle="width:300px"
                                                          />
                                          </td></tr>
                                   
                                    </tr></table>
                                
                                                 
                       </td>
                    </tr>
                  
                       
                       </table>
                                                   
                     </div>
                          <SCRIPT TYPE="text/javascript">

var myfilter = new filterlist(document.getElementById('buyermyselect'));

</SCRIPT>
    </body>
</html>
