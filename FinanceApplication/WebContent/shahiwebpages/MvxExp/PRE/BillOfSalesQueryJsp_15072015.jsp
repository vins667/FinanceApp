<%-- 
    Document   : BillOfSalesQueryJsp
    Created on : Mar 2, 2015, 11:46:45 AM
    Author     : Ranjeet
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<script type="text/javascript" src="js/dom-drag.js"></script>
<script type="text/javascript" src="js/jquery.min-1.5.2.js"></script>
<html>
    <head>
    <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>        
		<style type="text/css">
            th {
                font-size:11px;
                font-weight:bold;
                color:#006699;
                background-image:url('image/table-gradient.jpg');
            } 
             tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;

            }     
            .root
			{
				position:absolute;
				height:200px;
				width:900px;
				background-color:#F4F4F4;
				border:1px solid #2a6afe;
			    padding: 0px;
			    margin: 0px
			} 
			.root1
			{
				position:absolute;
				height:200px;
				width:900px;
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
        </style>
        <script type="text/javascript">
            
            
if (typeof window.event != 'undefined') // IE
    document.onkeydown = function() // IE
    {

       if(event.keyCode==13)
          {event.keyCode=9}


        var t=event.srcElement.type;
        var kc=event.keyCode;


        return ((kc != 8 && kc != 13) || ( t == 'text' &&  kc != 13 ) ||
            (t == 'textarea') || ( t == 'submit' &&  kc == 13))






    }
else
    document.onkeypress = function(e)  // FireFox/Others
    {
        return tabOnEnter(e.target,e)

        aaaa=e.keyCode;
       if(aaaa==13 )
          {

        
      }
        return true;
  
    }
    
	        function ongosearch(){
	        	$("#searchheadId").attr("disabled","disabled");
	        	$("#searchId").text("Please wait....");
	            $("#newheadId").attr("disabled","disabled");
	            $("#newId").text("Please wait....");
	            $("#clearheadId").attr("disabled","disabled");
	            $("#clearId").text("Please wait....");
	            
	        	document.packquery.action="billofsalesAction.action?SEARCH_SUBMIT=YES";
	        	document.packquery.submit();
	        	document.getElementById('prepage').style.visibility = '';
	        }
	        
	        function ongodelete(){
	        	ctr=0;
	        	a=document.packquery.chkdel;
	        	if(a.length>0)
	            {
	        		 for (var i=0;i<a.length;i++)
	                  {
	        			 if(a[i].checked){
	        				 ++ctr;
	        			 }
	                  }
	            }
	        	else{
	        		if(document.packquery.chkdel.checked){
	        			++ctr;
	        		}
	        	}
	        	if(ctr==0){
	        		alert('You must choose atleast 1 entry for deletion!!!');
	        		return false;
	        	}
	        	if(confirm('Do you want to delete?')){
	        		document.packquery.action="deletebillofsalesAction.action";
	        		document.packquery.submit();
	        		document.getElementById('prepage').style.visibility = '';
	        	}
	        }
	        
	        function CheckAllDelete()
            {
              a=document.packquery.chkdel;
              if(a.length>0)
              {
                  for (var i=0;i<a.length;i++)
                  {
                      if(document.packquery.dchk.checked)
                      {
                          e=a[i];
                          if(!e.disabled )
                          {e.checked=true;  }
                      }
                      else
                      {
                          e=a[i];
                          if (!e.disabled )
                          {e.checked=false;  }
                      }
                  }
              }
              else
              {
                if(document.packquery.dchk.checked)
                 {
                      if (!a.disabled )
                      {a.checked=true;  }
                 }
                 else
                 {
                      if (!a.disabled )
                      {a.checked=false;  }
                  }
              }
            }
	          
        </script>
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
		            padding-top: 0px ;
		        }        
		         thead tr {
		             position: relative;
		             top: expression(this.offsetParent.scrollTop);
		        }
		        
		        tbody {
		            height: auto;
		        }
		         
		    </style>
		<![endif]-->
    </head>
    <body style="background-color:#f2f2f2">
    	<DIV align="center" id="prepage" class="lodingdiv" style="right:400px;position:absolute;z-index: 1;visibility: hidden;background: transparent;top:50px;" >
			<img src="image/pleaseWaitOverlay.gif" />			
		</DIV>
   		<form action="" onsubmit=""  method="post" name="packquery" >
      		<table align="center" width="100%">
            	<tr>
            		<td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
		           		<table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
		    	           	<tr><td width="100%"   class="hd" style="text-align:center">Bill Of Sale Query</td></tr>
		                  	<tr>
		                  		<td width="100%">
		                  			<table style="width:100%;" cellpadding="1" cellspacing="1">
		                          		<tr>
			                          	<td class="label-1">Location
				                          		<s:select name="SEARCH_WAREHOUSE" 
					                            	id="SEARCH_WAREHOUSE" 
					                            	list="warehouselist"
					                            	 cssStyle="width:130px"					                                
					                                value="%{SEARCH_WAREHOUSE}"
                                                    cssClass="texts"
					                                theme="simple"/>
			                          		</td>
                                                                <td class="label-1">BOS Number
				                          		<s:textfield name="SERACH_PLAN_NUMB" 
					                            	id="SERACH_PLAN_NUMB" 
					                                cssStyle="width:130px"
					                                value="%{SERACH_PLAN_NUMB}" 
					                                cssClass="texts"                                            
					                                theme="simple"/>
			                          		</td>
			                          		
		                          		    <td class="label-1">BOS Date from
			                           	    <sx:datetimepicker name="SEARCH_PLAN_DATE" 
			                                   		id="SEARCH_PLAN_DATE" 
			                                       
			                                        displayFormat="dd/MM/yyyy"
			                                        cssClass="texts"/>
		                             		</td>
		                             		<td class="label-1">To
			                           	    <sx:datetimepicker name="SEARCH_TOPLAN_DATE" 
			                                   		id="SEARCH_TOPLAN_DATE" 
			                                       
			                                        displayFormat="dd/MM/yyyy"
			                                        cssClass="texts"/>
		                             		</td>
		                             			                             		
		                          			
			                          		<td style="text-align: right;">	                          		
			                          			<button  id="searchheadId" class="sexybutton" onclick="ongosearch()"><span><span><span class="search" id="searchId">Search</span></span></span></button> &nbsp;
												<button  id="newheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('newplanbillofsalesAction.action');"><span><span><span class="add" id="newId">New</span></span></span></button> &nbsp;
												<button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('billofsalesAction.action');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
											</td>											
										</tr>
		                 			</table>
		              			</td>
		              		</tr>
		           		</table>
					</td>
				</tr>
				<tr>
					<td style="width:100%">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
					<td valign="top" style="border-width:1px;border-color:black;border-style:solid;" >
	                                <div  class="div1" style="width:100%;overflow:auto ;height:560px;">
	                                    <table   id="myTableID" border="0" bgcolor="#f2f2f2" cellpadding="1" cellspacing="1"  align="center" width="100%" >
	                                    	<thead class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);">
                                                    <tr>
                                                        <th>Type</th>
                                                        <th  style="height:25px">Location</th>
                                                        <th>BOS Number</th>
                                                        <th>BOS Date</th>
                                                        <th>Buyer</th>
                                                        <th>CHA</th>
                                                        <th>From Unit</th>
                                                        <th>To Unit</th>
                                                        <th>Transporter</th>
                                                        <th style="width:50px;" align="center">Edit</th>
                                                    </tr>
	                                        </thead>
	                                        <tbody>	   
                                                    <s:set id="SEARCH_WAREHOUSE" name="SEARCH_WAREHOUSE" value="%{SEARCH_WAREHOUSE}"/>
                                                    <s:set id="SERACH_PLAN_NUMB" name="SERACH_PLAN_NUMB" value="%{SERACH_PLAN_NUMB}"/>
                                                    <s:set id="SEARCH_PLAN_DATE" name="SEARCH_PLAN_DATE" value="%{SEARCH_PLAN_DATE}"/>
                                                    <s:set id="SEARCH_TOPLAN_DATE" name="SEARCH_TOPLAN_DATE" value="%{SEARCH_TOPLAN_DATE}"/>
                                                    
	                                        	<s:iterator value="bosmastlist" status="status">
                                                            <tr style="background-color: #fff;font-size: 9px">
                                                                       <td><s:property value="INTERUNIT"/></td>
	                                        			<td><s:property value="LOCATION_CODE"/></td>
	                                        			<td><s:property value="BOS_NO"/></td>
	                                        			<td><s:property value="BOS_DATE"/></td>
	                                        			<td><s:property value="BUYER"/></td>
	                                        			<td><s:property value="CHA"/></td>
                                                                        <td><s:property value="UNIT"/></td>
	                                        			<td><s:property value="UNIT_TO"/></td>
	                                        			<td><s:property value="TRANSPORTER"/>
                                                                          
                                                                        </td>
	                                        			<td style="text-align:center;"><a href="editbillofsalesAction.action?BOS_NO=<s:property value="BOS_NO"/>&LOCATION_CODE=<s:property value="LOCATION_CODE"/>&SEARCH_WAREHOUSE=<s:property value="%{#SEARCH_WAREHOUSE}"/>&SERACH_PLAN_NUMB=<s:property value="%{#SERACH_PLAN_NUMB}"/>&SEARCH_PLAN_DATE=<s:property value="%{#SEARCH_PLAN_DATE}"/>&SEARCH_TOPLAN_DATE=<s:property value="%{#SEARCH_TOPLAN_DATE}"/>&SEARCH_SUBMIT=YES" onclick="javascript:document.getElementById('prepage').style.visibility = '';"><img src="image/editicon.png" style="border-width:0px;"/></a></td>
	                                        			<%--<td>
	                                        				<s:if test="%{CONTROL_NUMB!=null}">
	                                        					<s:checkbox name="chkdel" id="chkdel_id%{#status.index}" fieldValue="%{LOCATION_CODE+'#'+PLAN_NUMB}" theme="simple" disabled="true"/>
	                                        				</s:if>
	                                        				<s:else>
	                                        					<s:checkbox name="chkdel" id="chkdel_id%{#status.index}" fieldValue="%{LOCATION_CODE+'#'+PLAN_NUMB}" theme="simple"/>
	                                        				</s:else>
	                                        				
	                                        			</td> --%>
	                                        		</tr>
	                                        	</s:iterator>                                     	
	                                        </tbody>
	                                 	</table>
	                                 </div>
                                 </td>
                        	</tr>                                   
				</table>
					</td>
				</tr>
                                 <tr>
                                        <td height="40px"  align="center" style="color:red;font-weight:bold;">

                                            <s:actionerror theme="simple" cssStyle="font-size:12px" />
                                            <s:fielderror theme="simple" cssStyle="font-size:12px"  />
                                            <s:actionmessage theme="simple" cssStyle="font-size:12px"  />



                                        </td>
                                    </tr>
			</table>
			<input type="hidden" name="rowcount" id="rowcount" value="2"/>
		</form>		
    </body>
</html>

