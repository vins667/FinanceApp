<%-- 
    Document   : GnrtrefnoBlrFbadNM
    Created on : May 16, 2015, 10:36:21 AM
    Author     : Shilpa
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/sexybuttons.css" rel="stylesheet"/>
<link href="<s:url value="css/main_gn.css"/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/dom-drag.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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
.root1 {
	position: absolute;

	width: 380px;
	background-color: #F4F4F4;
	border: 1px solid #2a6afe;
	padding: 0px;
	margin: 0px
}

.root2 {
	position: absolute;

	width: 680px;
	background-color: #F4F4F4;
	border: 1px solid #2a6afe;
	padding: 0px;
	margin: 0px
}

.handle1 {
	margin: 0px;
	padding: 0px;
	width: 100%;
	color: white;
	font-weight: bold;
	font-size: 12px;
}
            </style>
 <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #F8F8FF;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 22px ;
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);
                  }
                tbody {
                    height: auto;
                }
            </style>
        <![endif]-->

<script type="text/javascript">
	    function loadtotal(){
				$("#detailstot").load('GnrtreftotNM.jsp');
			}
            function loadallpages(){
            	for(i=0;i<15;i++){
            		$("#details"+i).load('searchRefNoAction.action?textid='+i);
            	}
            }
            function loadsinglerow(a,b,c,d){
            	var d1 = new Date();
            	$("#details"+a).load('searchRefNoAction.action?textid='+a+'&XYEAR='+b+'&XBANK='+c+'&XCHQ_NO='+d);
            	
            }
            function save()
			{
				if(validateSave()==true)
				{
					if(confirm("Do you want to save!!!")){
						document.frm.action="saveRefNoAction";
					    document.frm.submit();
					}
				}
			}
			
			function validateSave()
			{
				YEAR_LST=document.frm.YEAR;
				BANK_LST=document.frm.BANK;
				CHQ_NO_LST=document.frm.CHQ_NO;
				BOA_LST=document.frm.BOA;
				SELECT_TYPE_LST=document.frm.SELECT_TYPE;
				ctr=0;
				for(i=0;i<YEAR_LST.length;i++)
				{
					YEAR_1=YEAR_LST[i].value;
					BANK_1=BANK_LST[i].value;
					CHQ_NO_1=CHQ_NO_LST[i].value;
					BOA_1=BOA_LST[i].value;
					SELECT_TYP=SELECT_TYPE_LST[i].value;
					if(YEAR_1!="")
							{
								if(BANK_1=="")
								{
									alert("Please Enter Bank");
									document.frm.BANK[i].focus();
									return false;
								}
								if(CHQ_NO_1=="")
								{
									alert("Please Enter Cheque No");
									document.frm.CHQ_NO[i].focus();
									return false;
								}
								if(BOA_1=="")
								{
									alert("Please Fill the record");
									return false;
								}
								if(SELECT_TYP=="")
								{
									alert("Please Select Payment Mode");
									document.frm.SELECT_TYPE[i].focus();
									return false;
								}

								//id="attachfile_ID"+i;
								//if(document.getElementById(id).value=="")
								//{
								//alert("Please Attach File");
								//return false;
								//}
								++ctr;
								
							}	
					}
				if(ctr==0)
					{
					alert("Please Enter Year");
					YEAR_LST[0].focus();
					return false;
					}
				return true;	
			}
			
		
			function search_details(a)
			{
				id="YEAR_ID"+a;
				id1="BANK_ID"+a;
				id2="CHQ_NO_ID"+a;
				id3="details"+a;
				id4="details_temp"+a;
				YEAR=document.getElementById(id);
				 BANK=document.getElementById(id1);
				 CHQ_NO=document.getElementById(id2);
				 if(YEAR.value!='' && YEAR.value.length>0 && BANK.value!='' && BANK.value.length>0 && CHQ_NO.value!='' && CHQ_NO.value.length>0){
	            	 abc=YEAR.value
	            	 if(duplicateCheck() && validateYear(a) && validateChequeNo(a)){
	            		 loadsinglerow(a,abc,BANK.value,CHQ_NO.value);
	            	}	            	 
	             }
				 else
        		 {
        		 search_CLEAR(a);
        		 }
	             addAmount();     	 
	       	}
			
			function search_CLEAR(a)
			{
				$("#details"+a).load('searchRefNoAction.action?textid='+a);
	            
	       	}
			
			function duplicateCheck()
			{
				YEAR=document.frm.YEAR;
				
				for(i=0;i<YEAR.length;i++)
					{
						YEARa=document.frm.YEAR[i].value;
						BANKa=document.frm.BANK[i].value;
						CHQ_NOa=document.frm.CHQ_NO[i].value;
						if(YEARa!='' && YEARa.length>0 && BANKa!='' && BANKa.length>0 && CHQ_NOa!='' && CHQ_NOa.length>0)
						{
							for(j=i+1;j<YEAR.length;j++)
							{
							YEARa1=document.frm.YEAR[j].value;
							BANKa1=document.frm.BANK[j].value;
							CHQ_NOa1=document.frm.CHQ_NO[j].value;
							if(YEARa+BANKa+CHQ_NOa==YEARa1+BANKa1+CHQ_NOa1)
								{
								alert("Duplicate Cheque enter..  Please enter different Cheque No ..");
								document.frm.CHQ_NO[j].value="";
								document.frm.CHQ_NO[j].focus();
								return false;
								}
							}
						}
					}
				return true;
			}
		
			function validateYear(a)
			{
				
				id="YEAR_ID"+a;
				YEARa=document.getElementById(id);
				if(YEARa.value!="")
					{
					if(!YEARa.value.match( /^[0]*(\d+)$/) )
				    {
				    alert("Please Enter Numbers Only");
				    YEARa.value="";
				    YEARa.focus();
				    return false;
		    		}
					
					if(YEARa.value.length!=4)
				   	{
				    alert("Invalid Year");
				    YEARa.focus();
				    YEARa.value="";
				    return false;
				   	}
					
				   if(YEARa.value=="" || parseInt(YEARa.value)<2009)
				    {   alert("Invalid Year,Please Enter Year above 2008 .....!");
				    YEARa.value="";
				    YEARa.focus();
				   	return false;
				    }
					
					}
				
				
				return true;
			}
			
			function validateBankId(a)
			{
				id1="BANK_ID"+a;
				BANKa=document.getElementById(id1);
				if(BANKa.value=="")
				{
					alert("Please Enter Bank Id");
					BANKa.focus();
					return false;
				}
				
				return true;
			}
			
			function validateChequeNo(a)
			{
				id2="CHQ_NO_ID"+a;
				CHQ_NOa=document.getElementById(id2);
				
						if(CHQ_NOa.value!="" && CHQ_NOa.value.length<15)
							{
							    alert("Check No must be of 15 digits");
							    CHQ_NOa.value="";
							    CHQ_NOa.focus();
							    return false;
							}
					
				return true;
			}
			   				
			
		function openpop(a) {

			document.getElementById(a).style.display = '';
		}
		function closediv(a) {
			document.getElementById(a).style.display = 'none';
		}
		function openpopdtl(a,b){
			document.getElementById(a).style.display = '';
			document.getElementById('searchdetail').src="searchdtRefNoAction.action?TINDEX="+b;
		}
		
		
		function refno()
		{
			document.frm.action="refnoRefNoAction";
		    document.frm.submit();
		}
		
		 function tabE(obj,e){
			    var e=(typeof event!='undefined')?window.event:e;// IE : Moz
			    if(e.keyCode==13){
			    var ele = document.forms[0].elements;
			    for(var i=0;i<ele.length;i++){
			    var q=(i==ele.length-1)?0:i+1;// if last element : if any other
			    if(obj==ele[i]){ele[q].focus();break;}
			    }
			    return false;
			    }
			    }
		 
		 function tabECHQ(obj,e,a){
			    var e=(typeof event!='undefined')?window.event:e;// IE : Moz
			    if(e.keyCode==13){
			    var ele = document.forms[0].elements;
			    for(var i=0;i<ele.length;i++){
			    var q=(i==ele.length-1)?0:i+1;// if last element : if any other
			    if(obj==ele[i])
			    {
				id="SELECT_TYPE_ID"+a;
				document.getElementById(id).focus();

				break;
			    	}
			    }
			    return false;
			    }
			    }
		 
		function deleteLine(a,b)
		{
			id="YEAR_ID"+a;
			id1="BANK_ID"+a;
			id2="CHQ_NO_ID"+a;
			 
			YEARa=document.getElementById(id);
			 BANKa=document.getElementById(id1);
			 CHQ_NOa=document.getElementById(id2);
			 YEARa.value="";
			 BANKa.value="";
			 CHQ_NOa.value="";
			 YEARa.focus();
			 b.checked=false;
			 search_CLEAR(a);
			 addAmount();
		}
	
		
		function validatecpumt(a,b,c)
		{
		   // k=document.frm.CHQ_AMT[a].value;
		   // pfsct=document.frm.PFSCT[a].value;
		   // creftp=document.frm.LETTER_TYPE.value;
                    k=document.getElementById("CHQ_AMT_ID"+a).value;
		    pfsct=document.getElementById("PFSCT_ID"+a).value;
		    creftp=document.getElementById("LETTER_TYPE").value;

		if (creftp=="R")
		{
//alert("1-"+pfsct+b+k);
		if (pfsct!=null && pfsct!="")
		  {
		//alert("2"+b);
		     if(k!="" && parseInt(k)<200000 && pfsct!="CNRB" )
		        {
		           if (b=="NE" )
		         {
		         
		         }else{
		         alert("Can be NEFT Only.");
		         c.value="";
		         document.frm.SELECT_TYPE[b].focus();
		         }
		        }else

		         if(k!="" && parseInt(k)>200000 && b=="NE")
		           {
		            alert("Can not be NEFT.");
		            c.value="";
		            document.frm.SELECT_TYPE[b].focus();
		            }else
		            if(k!="" && pfsct=="CNRB" && b!="FT")
		           {
		            alert("Can only be Bank TO Bank.");
		            c.value="";
		            document.frm.SELECT_TYPE[b].focus();
		            }
		            else
		              if (b=="" )  {
		                 alert("Invalid Payment Method. ");
		                 c.value="";
		                 document.frm.SELECT_TYPE[b].focus();
		                }

		            if (b=="FT"){
		                    if (pfsct!="CNRB" )
		                {
		                    alert("Can not be Bank TO Bank");
		                    c.value="";  
		                    document.frm.SELECT_TYPE[b].focus();
		                }
		            }

		}else{
		   alert("IFSC No. Blank...");
		    c.value="";
		    
		}
		}
		}
		
		
		function addAmount()
		{
			
			document.frm.TOTAL_AMT.value="0";
			var flag=0;
			for(i=0; i<20; i++)
			{
				
				id="CHQ_AMT_ID"+i;
				if(document.getElementById(id).value=="")
				{}
				else{
					  flag=1;
					  a=document.getElementById(id).value;
					  var aa=parseFloat(document.frm.TOTAL_AMT.value) +parseFloat(a);
					  document.frm.TOTAL_AMT.value=aa.toFixed(2);
					 if(document.frm.TOTAL_AMT.value>40010000)
					  
					 {
					   alert("Please Check, Total Amount Above 4.00 Crore and 10 Thousand...");
					  
					   return false;
					  }
					}
		
			}
			if(flag==0)
			{
				document.frm.TOTAL_AMT.value="0";
			}

		}	
		
</script>
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Shahi Exports Pvt Ltd</title>
</head>
<body style="background-color: #377cb1; text-align: center;" onload="loadallpages();loadtotal()">
<form action="" name="frm" id="frm" method="post" enctype="multipart/form-data">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td class="hd" valign="middle" style="text-align: center; height: 25px">Payment Ref. No. Creation(CANARA)
			</td>
		</tr>
	</table>
	<table align="center"  style="margin-top: 0px; background-color: #e3eefb;text-align:right">
		<tr>
			<td valign="middle">
				<table border="0" cellpadding="0" align="center" width="100%">
					<tr>
						<td style="height: 20px;">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr height="40px">
									<td class="label-1" style=" vertical-align: middle;text-align: left">Location&nbsp;<s:textfield name="LOC" id="LOC" theme="simple" cssStyle="text-transform: uppercase;" cssClass="textreadonly" readonly="true"></s:textfield>
									</td>
									<td>&nbsp;</td>
									<td class="label-1" style="vertical-align: middle;"> Letter&nbsp;Type&nbsp;
										<s:select list="#{'R':'RTGS','I':'ILC'}" name="LETTER_TYPE" id="LETTER_TYPE" theme="simple"></s:select>
									</td>
									<td>&nbsp;</td>
									<td class="label-1" style=" vertical-align: middle;">Enter&nbsp;Ref&nbsp;No.&nbsp;
										<s:textfield name="REF_NO" id="REF_NO" theme="simple" cssStyle="text-transform: uppercase;" readonly="true" cssClass="textreadonly"></s:textfield>
									</td>
									<td style=" vertical-align: middle;text-align: right;">&nbsp;
									</td>
									<td style="text-align: right; vertical-align: middle;">
										<button class="sexybutton" type="button" onclick="save();"><span><span><span class="save">Save</span></span></span></button>&nbsp;&nbsp;
										<button class="sexybutton" type="button" onclick="window.location.href='clearRefNoAction'"><span><span><span class="undo">Clear</span></span></span></button>&nbsp;&nbsp;
										<button class="sexybutton" type="button" onclick="self.close();"><span><span><span class="cancel">Exit</span></span></span></button>&nbsp;&nbsp;&nbsp;
										</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
				<table id="tablea"  cellspacing="0" cellpadding="0" style="width:100%;background-color: gray">
                                <thead>
                                    <tr class="tr1">
	                            	<tr style="height:20px;">
	                                	<th valign="middle" style="text-align: left;width:140px" class="label-1">Year</th>
	                                    <th valign="middle" style="text-align: left;width:140px" class="label-1">Bank</th>
										<th valign="middle" style="text-align: left;width:140px" class="label-1">Cheque No.</th>
										<th valign="middle" style="text-align: left;width:20px" class="label-1">&nbsp;</th>
										<th valign="middle" style="text-align: left;width:130px;padding-left: 5px" class="label-1">BOA</th>
										<th valign="middle" style="text-align: left;width:130px;padding-left: 5px" class="label-1">Vch. No.</th>
										<th valign="middle" style="text-align: left;width:130px;padding-left: 5px" class="label-1">Status</th>
										<th valign="middle" style="text-align: left;width:130px;padding-left:5px" class="label-1">Supplier</th>
										<th valign="middle" style="text-align: right;width:130px;padding-left: 5px" class="label-1">Cheque&nbsp;Amount</th>
										<th valign="middle" style="text-align: left;width:130px" class="label-1">Select</th>	
										<th valign="middle" style="text-align: left;;width:20px" class="label-1">&nbsp;</th>					
										<th valign="middle" style="text-align: left;width:100px" class="label-1">
											 <input type="checkbox" name="dchk" id="CHK" onclick="checkAllDelete();">Delete&nbsp;&nbsp;
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator begin="0" end="14" status="st">
										<tr bgcolor="" style="font-size: 8pt;background-color: #f0f0f0;color:#A8A8A8;">
											<td style="text-align: left;;width:140px">
												<s:textfield name="YEAR" id="YEAR_ID%{#st.index}" value="%{YEAR[#st.index]}"  theme="simple"  cssClass="texts" onblur="search_details('%{#st.index}');" onkeypress="return tabE(this,event)"  cssStyle="text-transform:uppercase;width:136px"></s:textfield>&nbsp;
											</td>
											<td style="text-align: left;width:140px">
												<s:textfield name="BANK" id="BANK_ID%{#st.index}" value="%{BANK[#st.index]}"  theme="simple"  cssClass="texts" onblur="search_details('%{#st.index}');" onkeypress="return tabE(this,event)"  cssStyle="text-transform:uppercase;width:136px"></s:textfield>&nbsp;
											</td>
											<td style="text-align: left;width:140px">
												<s:textfield name="CHQ_NO" id="CHQ_NO_ID%{#st.index}" value="%{CHQ_NO[#st.index]}" cssStyle="width:136px" theme="simple" onblur="search_details('%{#st.index}');" onkeypress="return tabECHQ(this,event,'%{#st.index}')"  cssClass="texts"></s:textfield>&nbsp;
											</td>
											<td style="text-align: left;">
												&nbsp;<a href="#" onclick="search_details('<s:property value="%{#st.index}"/>')"><img border="0" width="18px" height="18px" src="image/Search-icon-big.png" /></a>
											</td>
											<td style="text-align: left" colspan="7">												
                                                <div id="details<s:property value='%{#st.index}'/>"></div>
                                            </td>
											<td style="text-align: left;">
												<s:checkbox name="CHK" id="CHK" theme="simple" onclick="deleteLine('%{#st.index}',this);"></s:checkbox>
											</td>
										</tr>
										</s:iterator>
										<tr>
											<td colspan="9" style="background-color:#fff;text-align:right;padding-right:25px;" >
											<div id="detailstot"></div>
											</td>
											<td colspan="3" style="background-color:#fff;" >&nbsp;
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;color:red;background-color: #fff;font-size: 14px">&nbsp;<s:actionerror/></td>
			</tr>
		</table>	
	</form>
	
</body>
</html>