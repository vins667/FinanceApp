<%-- 
    Document   : EditFundRequest
    Created on : Oct 9, 2012, 9:40:17 AM
    Author     : Vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<link href="<s:url value="../css/main.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="css/style.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/stylishbuttons.css" type="text/css" />
<script language="javascript" type="text/javascript" src="js/datetimepicker.js"/>
<html>
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            select,textarea {
                /* -- CSS3 Shadow - create a shadow around each input element -- */ 
                -webkit-box-shadow: 0px 0px 4px #aaa;
                -moz-box-shadow: 0px 0px 4px #aaa; 
                box-shadow: 0px 0px 4px #aaa;

                /* -- CSS3 Transition - define what the transition will be applied to (i.e. the background) -- */		
                -webkit-transition: background 0.3s linear;					
            }

            .datelabel{font-size:8pt;height:13pt;width:100pt;text-transform: uppercase}

            th {
                font-size:9pt;
                font-weight:bold;
                color:#006699;
                background-image:url('image/table-gradient.jpg');
            }
            tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }

            .Btn
            {

                background-color: #BDC7CE;
                background-image: URL(image/glossyback.gif);
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
            height: 550px;
            width: 600px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 28px ;
        }

        thead tr {

        }
        tbody {
            height: auto;
        }
        body{
        scrollbar-face-color: #191970;
        scrollbar-shadow-color: #2D2C4D;
        scrollbar-highlight-color:#7D7E94;
        scrollbar-3dlight-color: #7D7E94;
        scrollbar-darkshadow-color: #2D2C4D;
        scrollbar-track-color: #7D7E94;
        scrollbar-arrow-color: #C1C1D1;
        } 
    </style>
<![endif]-->
        <script language="javascript">
            function allowone(a){
                if(a=="C"){
                    if(document.getElementById('FORWARDTO').value.length > 0){
                        if(document.getElementById('REJECTSTATUS')){
                            if(document.getElementById('REJECTSTATUS').checked==true){
                                alert('Forword/Reject only one allowed\n You already choose Forword.');
                                document.getElementById('REJECTSTATUS').checked=false;
                                return false;
                            }
                        }
                    }
                }
                else{                    
                    if(document.getElementById('REJECTSTATUS')){
                        if(document.getElementById('REJECTSTATUS').checked==true){
                            alert('Forword/Reject only one allowed\n You already choose Reject.');
                            document.getElementById('FORWARDTO').value='';
                            return false;
                        }
                    }
                }
            }
            function countmaxlen1()
            {
                var maxlimit=500;
                if (document.frm.REMARKS.value.length > maxlimit)
                {
                    document.frm.REMARKS.value = document.frm.REMARKS.value.substring(0, maxlimit);
                }
                else
                {
                    document.frm.maxjobcont.value = maxlimit - document.frm.REMARKS.value.length;
                }
            }
            function propertychg(){
                reqtype = document.getElementById("reqtype");
                if(reqtype.value=="M"){                       
                    document.getElementById("reqsuno").value='';
                    document.getElementById("partyname").value='';
                    document.getElementById("reqsuno").onclick=function() {buyersearch();};                    
                    document.getElementById("reqsuno").readOnly = true;
                    document.getElementById("partyname").readOnly = true;                    
                }
                else{                                                            
                    document.getElementById("reqsuno").value='';
                    document.getElementById("partyname").value='';
                    document.getElementById("reqsuno").onclick=function() {b();};
                    document.getElementById("reqsuno").readOnly = false;                    
                    document.getElementById("partyname").readOnly = false;
                }
            }
            function addpono(a,b){            
                for(y=1;y<11;y++){
                    x='delrqpo'+b+'id'+y;                    
                    document.getElementById(x).value=a.value;
                    b=eval(b)+1;
                }
            
            }
            function buyersearch()
            {
                window.open("buyerlstBuyerFundRequest.action","byrlist","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=600m,height=400, top=192,left=367");
            }

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
            function saverec()
            {
                if(document.getElementById("reqpototalamt").value==''){
                    alert('Request Amount cannot be blank')
                    return false;
                }
                if(eval(document.getElementById("reqpototalamt").value)==0)
                {
                    alert('Request Amount cannot be less than zero...')
                    return false;
                }
                    if(confirm('Do you want to update/forword request??')){
                        document.frm.action="updateFundRequestNew.action";
                        document.frm.submit()
                    }
                
            }
            function savests3(){                
               if(validate()==true){
                    if(confirm('Do you want to update/forword request??')){
                        document.frm.action="updateFundRequestNew.action";
                        document.frm.submit()
                    }
                } 
            }
            
            function validate(){
                
                    if(document.getElementById("reqbkno").value==''){
                        alert('Bank Name cannot be empty.')
                        document.getElementById("reqbkno").focus();
                        return false;
                    }
                    if(document.getElementById("reqchq").value==''){
                        alert('Chq No cannot be empty.')
                        document.getElementById("reqchq").focus();
                        return false;
                    }
                    if(document.getElementById("chqdelv").value=='')
                    {
                        alert('Chq Delivered cannot be empty.')
                        document.getElementById("chqdelv").focus();
                        return false;
                    }
                    var aa=dojo.widget.byId("reqchqdt");
                    var stringValue = aa.getValue()
                    //alert(stringValue)
                    if(stringValue=='')
                    {
                        alert('Chq date cannot be empty.')                        
                        return false;
                    }
                    return true;
                
            }
            
            function getrec(a)
            {
                b=document.getElementById("reqsuno").value;
                alert("getpoBuyerFundRequest.action?locindex="+a+"&SUP_CODE="+b)
                window.open("getpoBuyerFundRequest.action?locindex="+a+"&SUP_CODE="+b,"byrlist12","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=700m,height=400, top=192,left=367");
            }


            function show_details(a) {
                if(document.getElementById("reqsuno").value=="")
                {alert("Please Enter Supplier");
                    document.getElementById("reqsuno").focus();
                    return false;
                }else{
                    dojo.event.topic.publish("show_detail"+a);
                }
               
            }
            function addReqAmt()
            {
                reqpodtobj=document.getElementsByName("reqpodt");  
                rpoamtobj=document.getElementsByName("rpoamt");  
                advamtobj=document.getElementsByName("advamt");  
                reqpoamtobj=document.getElementsByName("reqpoamt");  
                reqpototalamt=document.frm.reqpototalamt; 
                var totalam=0;
                if(reqpoamtobj.length>1){
                    for(i=0; i<reqpoamtobj.length; i++)
                    {
                        if(reqpoamtobj[i].value!="")
                        {
                            totalam=totalam+eval(reqpoamtobj[i].value) ;
                        }
                    }
                }
                else{
                     if(document.frm.reqpoamt.value!="")
                        {
                            totalam=totalam+eval(document.frm.reqpoamt.value) ;
                        }
                }
    
                reqpototalamt.value=totalam;
            }
            function checkbalamt(a,b)
            {            	
                if(document.getElementById("reqtyp").value=="M"){
                    reqpoamtobj=document.getElementById("rpoamt"+b); 
                    advamtobj=document.getElementById("advamt"+b);
                    //alert(eval(a.value) +eval(advamtobj.value));
                    var bobj=eval(a.value) +eval(advamtobj.value);
                    if(eval(bobj) > eval(reqpoamtobj.value))
                    {
                        alert("Request Amount is Greater Than PO Amount");
                        a.value="0";
                        return false;
                    }
                    else{
                        //calculateservice(document.getElementById("srvtaxapp"+b),b);
                    }
                }
  
            }   
            function calculateservice(a,b){
                reqamtobj=document.getElementById("rpoamt"+b);
                srvtaxobj=document.getElementById("srvtax"+b);
                CHGPCTobj=document.getElementById("CHGPCT"+b);
                reqpoamtobj=document.getElementById("reqpoamt"+b);
                advamtobj=document.getElementById("advamt"+b);
                if(reqpoamtobj.value!='' && eval(reqpoamtobj.value)>0){
                    if(a.checked==true){
                        if(CHGPCTobj.value!='' && eval(CHGPCTobj.value)>0){
                            srvtaxobj.value=(eval(reqpoamtobj.value)*eval(CHGPCTobj.value))/100;
                            var bobj=eval(reqpoamtobj.value)+eval(srvtaxobj.value)+eval(advamtobj.value);
                            if(eval(bobj) > eval(reqamtobj.value))
                            {
                                alert("Request Amount is Greater Than PO Amount");
                                a.checked=false;
                                srvtaxobj.value='0';
                                return false;
                            }
                        }
                    }
                    else{
                        srvtaxobj.value='0';
                    }
                }
            }         
        </script>

        <style type='text/css'> 
            .dragme { cursor: move }
        </style>
        <script type='text/javascript'> 
            var ie = document.all;
            var nn6 = document.getElementById &&! document.all;
 
            var isdrag = false;
            var x, y;
            var dobj;
 
            function movemouse( e ) {
                if( isdrag ) {
                    dobj.style.left = nn6 ? tx + e.clientX - x : tx + event.clientX - x;
                    dobj.style.top  = nn6 ? ty + e.clientY - y : ty + event.clientY - y;
                    return false;
                }
            }
 
            function selectmouse( e ) {
                var fobj       = nn6 ? e.target : event.srcElement;
                var topelement = nn6 ? "HTML" : "BODY";
 
                while (fobj.tagName != topelement && fobj.className != "dragme") {
                    fobj = nn6 ? fobj.parentNode : fobj.parentElement;
                }
 
                if (fobj.className=="dragme") {
      
                    isdrag = true;
   
                    dobj = document.getElementById(fobj.id+"_popup");
                    tx = parseInt(dobj.style.left+0);
                    ty = parseInt(dobj.style.top+0);
                    x = nn6 ? e.clientX : event.clientX;
                    y = nn6 ? e.clientY : event.clientY;
                    document.onmousemove=movemouse;
                    return false;
                }
            }
            function styledPopupCloseeditline(a) {
   
                document.getElementById(a).style.display = "none";
            }

            document.onmousedown=selectmouse;
            document.onmouseup=new Function("isdrag=false");            
            
        </script>
    </head>

    <body class="body1">
        <form action="" id="frm"   method="post" name="frm" >
            <table align="center" width="100%">
                <tr>
                    <td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >
                        <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                            <tr>
                                <td class="hd" style=" font-size:18px; text-align:center">Fund Request New</td>
                            </tr>
                            <tr>
                                <td valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
                                    <table width="100%"  cellpadding="1" bgcolor="#2680b5" cellspacing="1">                  
                                        <tr>
                                            <th style="text-align: left;height: 25px;">&nbsp;Divi</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Req No</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Req Date</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Req Type</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Party CD</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Party Name</th>                                        
                                            <th style="text-align: left;height: 25px;">&nbsp;Req Text</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Del.Place</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Status</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Req TO</th>
                                        </tr>
                                        <tr style="background-color: #e5eaf5;">
                                            <td><s:textfield label="Type" value="%{paydivi}" theme="simple"  name="paydivi" id="paydivi" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" readonly="true"/></td>
                                            <td><s:textfield name="reqno" readonly="true" value="%{reqno}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly"/></td>
                                            <td><s:textfield  name="reqdt" readonly="true"  value="%{reqdt}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly"/></td>
                                            <td><s:select label="Type"  value="%{reqtyp}" theme="simple" id="reqtyp" name="reqtyp" list="# {'M':'Movex PO','G':'General PO','B':'BillWise','E':'Employee'}" cssStyle="width:100%;font-size:12px" cssClass="texts"/><!-- onchange="propertychg();"--></td>
                                            <td><s:textfield id="reqsuno"  name="reqsuno"  value="%{reqsuno}" readonly="true" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts"/><!-- onclick="buyersearch();"--></td>
                                            <td style="width: 220px"><s:textfield  name="partyname"  id="" value="%{partyname}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" readonly="true"/></td>                                        
                                            <td>
                                            <s:if test="%{#session.sessUserCode==RQCHID && reqsts==1}">
                                            	<s:textfield  name="reqtxt"  value="%{reqtxt}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts"/>
                                            </s:if>
                                            <s:else>
                                            	<s:textfield  name="reqtxt"  value="%{reqtxt}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" readonly="true"/>
                                            </s:else>
                                            </td>
                                            <td>
                                            <s:if test="%{#session.sessUserCode==RQCHID && reqsts==1}">
                                            <s:textfield  name="dlvplc"  value="%{dlvplc}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts"/>
                                            </s:if>
                                            <s:else>
                                            	<s:textfield  name="dlvplc"  value="%{dlvplc}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" readonly="true"/>
                                            </s:else>
                                            </td>
                                            <td><s:textfield name="reqsts" readonly="true" value="%{reqsts}"  theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts"/></td>
                                            <td><s:select label="Type" value="%{payloct}" theme="simple"  name="payloct" list="# {'100':'Fbad A/C','200':'Blr A/C','205':'Shmg A/C','101':'HO A/C'}" cssStyle="width:100%;font-size:12px" cssClass="texts"/></td>
                                        </tr>
                                    </table>

                                    <table width="100%" border="1" cellpadding="1" bgcolor="#e5eaf5" cellspacing="0">
                                        <tr>
                                            <td class="label-1">Bank Name</td><td style="width:150px;">
                                                <s:if test="%{reqsts==5 && #session.sessUserCode==CHQCHID}">
                                                    <s:textfield  name="reqbkno"  value="%{reqbkno}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" />
                                                </s:if>
                                                <s:else>
                                                    <s:textfield  name="reqbkno"  value="%{reqbkno}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" readonly="true"/>
                                                </s:else>
                                            </td>
                                            <td class="label-1">Chq No </td>
                                            <td style="width:150px;">
                                                <s:if test="%{reqsts==5 && #session.sessUserCode==CHQCHID}">
                                                    <s:textfield  name="reqchq"  value="%{reqchq}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" />
                                                </s:if>
                                                <s:else>
                                                    <s:textfield  name="reqchq"  value="%{reqchq}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" readonly="true"/>
                                                </s:else>
                                            </td>
                                            <td class="label-1">Chq Date</td>
                                            <td style="width:150px;">
                                                <s:if test="%{reqsts==5 && #session.sessUserCode==CHQCHID}">
                                                    <sx:datetimepicker name="reqchqdt" id="reqchqdt"  value="%{reqchqdt}" cssClass="texts" displayFormat="dd/MM/yyyy"  cssStyle="width:100px;font-size:12px"/>
                                                </s:if>
                                                <s:elseif test="%{reqsts==6}">
                                                    <sx:datetimepicker name="reqchqdt" id="reqchqdt"  cssClass="texts" displayFormat="dd/MM/yyyy"  cssStyle="width:100px;font-size:12px"/>
                                                </s:elseif>
                                                <s:else>
                                                    <s:textfield name="reqchqdt" id="reqchqdt" theme="simple" readonly="true" cssClass="textreadonly" cssStyle="width:100%;font-size:12px"/>
                                                </s:else>
                                            </td>
                                            <td class="label-1">Chq Delivered</td>
                                            <td style="width:150px;">
                                                <s:if test="%{reqsts==5 && #session.sessUserCode==CHQCHID}">
                                                    <s:textfield  name="chqdelv"  value="%{chqdelv}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" />
                                                </s:if>
                                                <s:else>
                                                    <s:textfield  name="chqdelv"  value="%{chqdelv}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" readonly="true" />
                                                </s:else>
                                            </td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>   
                                            <td class="label-1">PI No.</td>
                                            <td>
                                                <s:if test="%{#session.sessUserCode==RQCHID && reqsts==1}">
                                                    <s:textfield  name="reqpino" value="%{reqpino}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" /> 
                                                </s:if>
                                                <s:else>
                                                    <s:textfield  name="reqpino" readonly="true"  value="%{reqpino}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" />
                                                </s:else>
                                            </td>
                                            <td class="label-1">Emp Code </td>
                                            <td>
                                                <s:if test="%{#session.sessUserCode==RQCHID && reqsts==1}">
                                                    <s:textfield  name="reqempcd" value="%{reqempcd}" theme="simple" cssStyle="width:100%;font-size:12px" readonly="true" cssClass="textreadonly" />
                                                </s:if>
                                                <s:else>
                                                    <s:textfield  name="reqempcd" readonly="true"  value="%{reqempcd}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" />
                                                </s:else>
                                            </td>
                                            <td class="label-1">PDC Date</td>
                                            <td style="width:150px;">
                                                <s:if test="%{reqsts<3}">                                                    
                                                    <sx:datetimepicker name="reqpdcdt" id="reqpdcdt"  cssClass="texts" displayFormat="dd/MM/yyyy"  cssStyle="width:100px;font-size:12px"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield name="reqpdcdt" id="reqpdcdt" theme="simple"  readonly="true" cssClass="textreadonly" cssStyle="width:100%;font-size:12px"/>
                                                </s:else>
                                            </td>
                                            <s:if test='%{reqdisc=="Y"}'>
                                                <td class="label-1">Discount <input type="CHECKBOX"  name="reqdisc" checked="true"    value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:if>
                                                <s:else>
                                                <td class="label-1">Discount <input type="CHECKBOX"  name="reqdisc"   value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:else>
                                                <s:if test="%{reqsts==1}">
                                                <td class="label-1">Cancel Req.&nbsp;
                                                    <s:if test='%{CANFLAG!=null && CANFLAG=="Y"}'>
                                                        <s:checkbox fieldValue="YES" name="cancelreq" id="cancelreq" theme="simple" disabled="true" checked="true"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:checkbox fieldValue="YES" name="cancelreq" id="cancelreq" theme="simple"/>
                                                    </s:else>
                                                </td>
                                                </s:if>
                                                <s:else>
                                                    <td class="label-1">Cancel Req.&nbsp;<s:checkbox fieldValue="YES" name="cancelreq" id="cancelreq" disabled="true" theme="simple"/></td>
                                                </s:else>
                                                <s:if test='%{mvxpaid=="Y"}'>
                                                <td class="label-1">TDS <input type="CHECKBOX"  name="mvxpaid" checked="true" readonly="true"  value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:if>                                                
                                                <s:else>
                                                <td class="label-1">TDS <input type="CHECKBOX" name="mvxpaid" readonly="true"  value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:else>                                                
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr><td align="center"> 
                                    <table><tr><td>
                                                <table  cellpadding="0" bgcolor="#e5eaf5"  cellspacing="1">
                                                    <tr><td class="hd" style=" font-size:18px; text-align:center">PO Detail<s:if test="%{reqsts>1 && reqbal>0}">&nbsp;&nbsp;&nbsp;(<span style="color: red; ">Balance: <s:property value="%{reqbal}"/></span>)</s:if></td></tr>
                                                    <tr>
                                                        <td height="100pt"  style="border-width:1pt;border-color:silver;border-style:solid;" >
                                                            <div  class="div1" style="overflow:auto ;height:200px;width:1100px">
                                                                <table id="tebleidpo" bgcolor="#d9e8fe"   cellpadding="0" cellspacing="1" >
                                                                    <thead>
                                                                        <tr style="background-color: #6699cc;position: absolute; top: expression(this.offsetParent.scrollTop);">
                                                                            <th align="center" class="label-1" style="width:100px;">PO No</th>
                                                                            <th align="center" class="label-1" style="width:100px">Remark</th>
                                                                            <th align="center" class="label-1" style="width:100px">PO Date</th>
                                                                            <th align="right" class="label-1" style="width:100px" >PO Amt</th>
                                                                            <th align="right" class="label-1" style="width:100px" >Adv Paid</th>
                                                                            <th align="right" class="label-1" style="width:100px" >Req Amt</th>
                                                                            <th align="right" class="label-1" style="width:100px" >Srv Tax</th>
                                                                            <th class="label-1" style="width:150px;">&nbsp;</th>  
                                                                            <th class="label-1" style="width:50px">PO Upd</th>
                                                                            <th class="label-1" style="width:50px">PI Upd</th>
                                                                            <th class="label-1" style="width:50px">FI Upd</th>
                                                                        </tr>
                                                                    </thead>
                                                                    </thead>
                                                                    <tbody >                                                                        
                                                                        <s:set id="newslno" name="newslno" value="0"/>
                                                                        <s:iterator status="st1" begin="0" end="0">
                                                                            <tr>
                                                                                <td style="width:100px;">
                                                                                    <s:set id="reqponotempdt" name="reqponotempdt" value=""/>
                                                                                    <s:if test="%{reqpono[#st1.index].length()>0}">
                                                                                        <s:textfield  name="reqpono" id="reqpono%{#st1.index}" value="%{reqpono[#st1.index]}" onblur="show_details('%{#st1.index}');addReqAmt();"  theme="simple" cssClass="textreadonly" cssStyle="width:100px;;font-size:9pt" readonly="true"/>
                                                                                        <s:set id="reqponotempdt" name="reqponotempdt" value="%{reqpono[#st1.index]}"/>
                                                                                    </s:if>
                                                                                    <s:else>
                                                                                        <s:textfield  name="reqpono" id="reqpono%{#st1.index}" value="%{reqpono[#st1.index]}" onblur="show_details('%{#st1.index}');addReqAmt();addpono(this,'%{#newslno}')"  theme="simple" cssClass="texts" cssStyle="width:100px;font-size:9pt"/>
                                                                                    </s:else>
                                                                                </td>
                                                                                <td colspan="6" style="width:500px;">
                                                                                    <%--s:url id="d_url" action="getpodtBuyerFundRequest.action?textid=%{#st1.index}&chkedit=%{reqpono[#st1.index]}" /--%>
                                                                                    <s:url id="d_url" action="getpodtBuyerFundRequest.action" >
                                                                                        <s:param name="textid" value="%{#st1.index}"/>
                                                                                    </s:url>
                                                                                    <sx:div id="details%{#st1.index}"  href="%{d_url}" listenTopics="show_detail%{#st1.index}" formId="frm" showLoadingText="Loading ......"></sx:div>

                                                                                    </td> 
                                                                                    <td style="width:150px;">
                                                                                        <input type='button' class="submitbutton1"  onClick='document.getElementById("<s:property value="%{#st1.index}"/>td_popup").style.display="block"' value='Delivery Schedule' style="width: 120px"/>

                                                                                    <div id='<s:property value="%{#st1.index}"/>td_popup' name='<s:property value="%{#st1.index}"/>td_popup' style='background-color: #f2f2f2; width: 482px; height: 250px; display:none; position: absolute; top: 5px; left: 20px;border-width: 1px;border-style: solid;border-color: black;overflow: auto'>
                                                                                        <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
                                                                                            <tr bgcolor="#f2f2f2">
                                                                                                <td height='23' width='356' align="center" id="<s:property value="%{#st1.index}"/>td" class='dragme' style="font-size:12px;font-weight: bold;background-image:url(image/table-gradient.jpg);">Delivery Schedule</td>
                                                                                                <td><a href='javascript:styledPopupCloseeditline("<s:property value="%{#st1.index}"/>td_popup")'><img height='23' width='24' src='image/divclose.gif' border='0'/></a></td>
                                                                                            </tr>
                                                                                            <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("..css/images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                                                                                                    <table   width="20%" align="center" bgcolor="#e5eaf5"  cellpadding="0"  cellspacing="0"  >

                                                                                                        <td height="100pt"  style="border-width:1pt;border-color:silver;border-style:solid;" >

                                                                                                            <div  class="div1" style="overflow:auto ;height:200px;width:475px">
                                                                                                                <table id="tebleidpo11" bgcolor="#d9e8fe" width="100%"  cellpadding="0" cellspacing="1" >
                                                                                                                    <thead >
                                                                                                                        <tr style="background-color: #6699CC;position: absolute; top: expression(this.offsetParent.scrollTop);" >
                                                                                                                            <th style="height:20pt;text-align: left;">PO No</th>
                                                                                                                            <th style="height:20pt;text-align: left;">PO Qnty</th>
                                                                                                                            <th style="height:20pt;text-align: left;">Delv Date</th>
                                                                                                                            <th style="height:20pt;text-align: left;">PCD Date</th>
                                                                                                                        </tr>
                                                                                                                    </thead>
                                                                                                                    <tbody >                                                                                                                        
                                                                                                                        <s:set id="delslno" name="delslno" value="0"/>
                                                                                                                        <s:set id="delslnop" name="delslnop" value="0"/>
                                                                                                                        <s:iterator value="REQDELV" status="stt">
                                                                                                                            <s:if test="%{DLRQPO==#reqponotempdt}">
                                                                                                                                <tr>                                                                                                                                
                                                                                                                                    <td style="width:100px">
                                                                                                                                        <s:textfield  name="dlrqpo" cssClass="textreadonly"  id="delrqpo%{#newslno}id%{#stt.index+1}" value="%{#reqponotempdt}" theme="simple" cssStyle="width:100%;font-size:9pt" readonly="true"/>    
                                                                                                                                    </td>
                                                                                                                                    <td style="width:100px" align="right"><s:textfield  name="delqty" cssClass="texts"  id="delqty%{#stt.index+1}"   theme="simple" cssStyle="width:100%;text-align:right;font-size:9pt" value="%{DELQTY}"/></td>
                                                                                                                                    <td style="width:137px">
                                                                                                                                        <input type="Text" name="delvdt" id="delvdt<s:property value="%{#newslno}"/>id<s:property value="%{#stt.index+1}"/>" class="texts" style="width:80%;font-size:9pt" maxlength="25" size="25" readonly="true" value="<s:property value="%{DELVDT}"/>"/>
                                                                                                                                        <a href="javascript:NewCal('delvdt<s:property value="%{#newslno}"/>id<s:property value="%{#stt.index+1}"/>','ddmmyyyy')"><img src="js/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
                                                                                                                                    </td>
                                                                                                                                    <td style="width:138px">
                                                                                                                                        <input type="Text" name="pcddt" id="pcddt<s:property value="%{#newslno}"/>id<s:property value="%{#st.index+1}"/>" class="texts" style="width:80%;font-size:9pt" maxlength="25" size="25" readonly="true" value="<s:property value="%{PCDDT}"/>"/>                                                                                                                                        
                                                                                                                                        <a href="javascript:NewCal('pcddt<s:property value="%{#newslno}"/>id<s:property value="%{#stt.index+1}"/>','ddmmyyyy')"><img src="js/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>                                                                                                                                        
                                                                                                                                    </td>                                                                                                                                    
                                                                                                                                </tr>
                                                                                                                                <s:set id="delslnop" name="delslnop" value="%{#delslnop+1}"/>
                                                                                                                            </s:if>                                                                                                                                    
                                                                                                                        </s:iterator>
                                                                                                                        <s:iterator status="st" begin="%{#delslnop}" end="9">
                                                                                                                            <tr>
                                                                                                                                <td style="width:100px">
                                                                                                                                    <s:if test="%{#reqponotempdt.length()>0}">
                                                                                                                                        <s:textfield  name="dlrqpo" cssClass="textreadonly"  id="delrqpo%{#newslno}id%{#st.index+1}" value="%{#reqponotempdt}" theme="simple" cssStyle="width:100%;font-size:9pt" readonly="true"/>    
                                                                                                                                    </s:if>
                                                                                                                                    <s:else>
                                                                                                                                        <s:textfield  name="dlrqpo" cssClass="textreadonly"  id="delrqpo%{#newslno}id%{#st.index+1}" theme="simple" cssStyle="width:100%;font-size:9pt" readonly="true"/>
                                                                                                                                    </s:else>

                                                                                                                                </td>
                                                                                                                                <td style="width:100px" align="right"><s:textfield  name="delqty" cssClass="texts"  id="delqty%{#st.index+1}"   theme="simple" cssStyle="width:100%;text-align:right;font-size:9pt"/></td>
                                                                                                                                <td style="width:137px">
                                                                                                                                    <input type="Text" name="delvdt" id="delvdt<s:property value="%{#newslno}"/>id<s:property value="%{#st.index+1}"/>" class="texts" style="width:80%;font-size:9pt" maxlength="25" size="25" readonly="true"/>                                                                                                                                        
                                                                                                                                    <a href="javascript:NewCal('delvdt<s:property value="%{#newslno}"/>id<s:property value="%{#st.index+1}"/>','ddmmyyyy')"><img src="js/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
                                                                                                                                </td>
                                                                                                                                <td style="width:138px">
                                                                                                                                    <input type="Text" name="pcddt" id="pcddt<s:property value="%{#newslno}"/>id<s:property value="%{#st.index+1}"/>" class="texts" style="width:80%;font-size:9pt" maxlength="25" size="25" readonly="true"/>                                                                                                                                        
                                                                                                                                    <a href="javascript:NewCal('pcddt<s:property value="%{#newslno}"/>id<s:property value="%{#st.index+1}"/>','ddmmyyyy')"><img src="js/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>                                                                                                                                        
                                                                                                                                </td>
                                                                                                                            </tr>
                                                                                                                            <s:set id="delslno" name="delslno" value="%{#st.index+2}"/>
                                                                                                                            <s:set id="newslno" name="newslno" value="%{#newslno+1}"/>
                                                                                                                        </s:iterator>                                                                                                                        
                                                                                                                    </tbody>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </table>
                                                                                                </td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </div>                                                                                                                        
                                                                                </td>
                                                                                <td style="text-align: center;width:50px;">
                                                                                    <s:if test="%{reqpono[#st1.index].length()>0}">
                                                                                        <a href="#" onclick='javascript:window.open("attachFundRequestNew.action?chkpoatt=<s:property value="%{reqpono[#st1.index]}"/>&reqno=<s:property value="%{reqno}"/>&RQCHID=<s:property value="%{RQCHID}"/>&reqsts=<s:property value="%{reqsts}"/>","attachlstw","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=470m,height=150, top=192,left=367");'><img src="image/attachment-PO.jpg" style="border-width:0px;"/></a>
                                                                                    </s:if>
                                                                                </td>
                                                                                <td style="text-align: center;width:50px;">
                                                                                    <s:if test="%{reqpono[#st1.index].length()>0}">
                                                                                        <a href="#" onclick='javascript:window.open("attach1FundRequestNew.action?chkpoatt=<s:property value="%{reqpono[#st1.index]}"/>&reqno=<s:property value="%{reqno}"/>&RQCHID=<s:property value="%{RQCHID}"/>&reqsts=<s:property value="%{reqsts}"/>","attachlstw","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=470m,height=150, top=192,left=367");'><img src="image/attachment-PI.jpg" style="border-width:0px;"/></a>
                                                                                    </s:if>
                                                                                </td>
                                                                                <td style="text-align: center;width:50px;">
                                                                                    <s:if test="%{reqpono[#st1.index].length()>0}">
                                                                                        <a href="#" onclick='javascript:window.open("attach2FundRequestNew.action?chkpoatt=<s:property value="%{reqpono[#st1.index]}"/>&reqno=<s:property value="%{reqno}"/>&RQCHID=<s:property value="%{RQCHID}"/>&reqsts=<s:property value="%{reqsts}"/>","attachlstw","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=470m,height=150, top=192,left=367");'><img src="image/attachment-FN.jpg" style="border-width:0px;"/></a>
                                                                                    </s:if>
                                                                                </td>
                                                                            </tr>
                                                                        </s:iterator>                                                                                                     
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" style="padding-right:330px;text-align: right;"><s:textfield name="reqpototalamt" id="reqpototalamt" theme="simple" cssClass="textreadonly" readonly="true"  cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                                                    </tr>

                                                </table>
                                            </td>
                                            <td valign="top">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
                        <div  style="width:100%;overflow:auto;margin-top: 0px;height: 200px">
                            <table    width="100%"  cellpadding="1" cellspacing="1"  >
                                <tr>
                                    <td>
                                        <s:set id="colorassign" name="colorassign" value=""/>
                                        <s:iterator value="REMARKS_LIST" status="detailst">                                    
                                            <s:if test="%{#detailst.index%2==0}">
                                                <div class="rounded-box" style="width:98%;background-color:#E6E6FA;">
                                                    <div class="top-left-corner" ><div class="top-left-inside" style="color:#E6E6FA;" >&bull;</div></div>
                                                    <div class="bottom-left-corner" ><div class="bottom-left-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                    <div class="top-right-corner" ><div class="top-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                    <div class="bottom-right-corner" ><div class="bottom-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                </s:if>
                                                <s:else>
                                                    <div class="rounded-box" style="width:98%;background-color:#DCDCDC;">
                                                        <div class="top-left-corner" ><div class="top-left-inside" style="color:#DCDCDC;" >&bull;</div></div>
                                                        <div class="bottom-left-corner" ><div class="bottom-left-inside" style="color:#DCDCDC;">&bull;</div></div>
                                                        <div class="top-right-corner" ><div class="top-right-inside" style="color:#DCDCDC;">&bull;</div></div>
                                                        <div class="bottom-right-corner" ><div class="bottom-right-inside" style="color:#DCDCDC;">&bull;</div></div>
                                                    </s:else>
                                                    <div class="box-contents" >
                                                        <table><tr>                                                                
                                                                <td align="left" width="170px" style="height: 12px;font-size: 10px;">
                                                                    <s:property value="USER_NAME"/><%--(<s:property value="USER_ID"/>)--%>
                                                                </td>
                                                                <td rowspan="2" style="vertical-align: top;text-align: left;height: 12px;font-size: 10px;">: <s:property value="REMARK"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="left" width="170px" style="height: 12px;font-size: 10px;">
                                                                    <s:property value="FORWORD_DATE"/><s:property value="%{#colorassign}"/>                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>                                       
                                            </s:iterator>
                                    </td>
                                </tr>
                                <s:if test="%{REMARKS_LIST!=null && REMARKS_LIST.size()==0}">
                                    <tr>
                                        <td>
                                            <div class="rounded-box" style="width:98%;background-color:#E6E6FA;">
                                                <div class="top-left-corner" ><div class="top-left-inside" style="color:#E6E6FA;" >&bull;</div></div>
                                                <div class="bottom-left-corner" ><div class="bottom-left-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="top-right-corner" ><div class="top-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="bottom-right-corner" ><div class="bottom-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="box-contents" >
                                                    <table>
                                                        <tr>
                                                            <td width="170px" class="label-11" style="vertical-align: top;">
                                                                Forwarded to
                                                            </td>
                                                            <td align="left" style="height: 12px;font-size: 10px;">
                                                                <table>
                                                                    <tr style="vertical-align: top;">
                                                                        <td><textarea cols="" rows="" class="txtField"  name="REMARKS"   style="width:570px;height:30pt" onkeyup="countmaxlen1()"  onkeydown="countmaxlen1()"></textarea></td>
                                                                        <td style="width: 210px">
                                                                            <s:select name="FORWARDTO" id="FORWARDTO" cssClass="textbox" cssStyle="width:200px" onchange="allowone('F');" list="forwardlist" listKey="EMP_CODE" listValue="EMP_NAME" headerKey="" headerValue="Select" theme="simple"/>
                                                                            <br/>
                                                                            <label class="label-1">
                                                                                <input type="TEXT" value="500" name="maxjobcont" style="width:30pt" readonly="readonly">
                                                                                Char Left
                                                                            </label>
                                                                        </td>                                                     

                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </s:if>
                                <s:elseif test="%{#session.sessUserCode==ENTRYID && reqsts!=6}">
                                    <tr>
                                        <td>
                                            <div class="rounded-box" style="width:98%;background-color:#E6E6FA;">
                                                <div class="top-left-corner" ><div class="top-left-inside" style="color:#E6E6FA;" >&bull;</div></div>
                                                <div class="bottom-left-corner" ><div class="bottom-left-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="top-right-corner" ><div class="top-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="bottom-right-corner" ><div class="bottom-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="box-contents" >
                                                    <table>
                                                        <tr>
                                                            <s:hidden name="ENTRYSRNO" id="ENTRYSRNO"/>
                                                            <td width="170px" class="label-11" style="vertical-align: top;">
                                                                Forwarded to
                                                            </td>
                                                            <td align="left" style="height: 12px;font-size: 10px;">
                                                                <table>
                                                                    <tr style="vertical-align: top;">
                                                                        <td><textarea cols="" rows="" class="txtField"  name="REMARKS"   style="width:570px;height:30pt" onkeyup="countmaxlen1()"  onkeydown="countmaxlen1()"></textarea></td>
                                                                        <td style="width: 210px">
                                                                            <s:select name="FORWARDTO" id="FORWARDTO" cssClass="textbox" cssStyle="width:200px" onchange="allowone('F');" list="forwardlist" listKey="EMP_CODE" listValue="EMP_NAME" headerKey="" headerValue="Select" theme="simple"/>
                                                                            <br/>
                                                                            <label class="label-1">
                                                                                <input type="TEXT" value="500" name="maxjobcont" style="width:30pt" readonly="readonly">
                                                                                Char Left
                                                                            </label>
                                                                        </td>
                                                                        <s:if test="%{reqsts>=2}">
                                                                            <s:hidden name="RQCHID" id="RQCHID"/>
                                                                            <td class="label-1">Reject Req.&nbsp;<s:checkbox name="REJECTSTATUS" id="REJECTSTATUS" fieldValue="Y" theme="simple" onclick="allowone('C');"/></td>
                                                                        </s:if>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </s:elseif>
                                <s:elseif test="%{ENTRYID!=null && reqsts!=6}">
                                    <tr>
                                        <td>
                                            <div class="rounded-box" style="width:98%;background-color:#E6E6FA;">
                                                <div class="top-left-corner" ><div class="top-left-inside" style="color:#E6E6FA;" >&bull;</div></div>
                                                <div class="bottom-left-corner" ><div class="bottom-left-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="top-right-corner" ><div class="top-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="bottom-right-corner" ><div class="bottom-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="box-contents" >
                                                    <table>
                                                        <tr>                                                            
                                                            <td class="label-11" style="vertical-align: top;height: 30px;text-align: center">
                                                                Already Forworded to <s:property value="%{ENTRYUSER}"/>(<s:property value="%{ENTRYID}"/>)
                                                            </td>                                                            
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </s:elseif>
                                <s:else>
                                    <tr>
                                        <td>
                                            <div class="rounded-box" style="width:98%;background-color:#E6E6FA;">
                                                <div class="top-left-corner" ><div class="top-left-inside" style="color:#E6E6FA;" >&bull;</div></div>
                                                <div class="bottom-left-corner" ><div class="bottom-left-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="top-right-corner" ><div class="top-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="bottom-right-corner" ><div class="bottom-right-inside" style="color:#E6E6FA;">&bull;</div></div>
                                                <div class="box-contents" >
                                                    <table>
                                                        <tr>                                                            
                                                            <td class="label-11" style="vertical-align: top;height: 30px;text-align: center">
                                                                Cheque Made by <s:property value="%{ENTRYUSER}"/>(<s:property value="%{ENTRYID}"/>)
                                                            </td>                                                            
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </s:else>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>
                        <table width="100%" cellpadding="1" cellspacing="0">
                            <tr>                                
                                <td class="sexybutton" style="width:70px">
                                    <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="window.location.href='backFundRequestAction.action'">
                                        <span><span><img src="css/images/icons/silk/Back.png"  alt="" style="border-width: 0pt;"/>Back</span></span>
                                            </s:a>
                                </td>
                                <td style="text-align: center;color:red;font-size: 20px;font-weight: bold;">&nbsp;<s:if test='%{CANFLAG!=null && CANFLAG=="Y"}'>Request Cancelled</s:if></td>
                                <s:if test='%{CANFLAG!=null && CANFLAG=="Y"}'>
                                    <td class="disabled sexybutton" style="width:70px">                                    
                                        <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="">
                                            <span><span><img src="css/images/icons/silk/save.png"  alt="" style="border-width: 0pt;"/><font color="red">Update</font></span></span>
                                                </s:a>
                                    </td>
                                </s:if>
                                <s:else>
                                    <s:if test="%{#session.sessUserCode==RQCHID && reqsts==1}">
                                        <td class="sexybutton" style="width:70px">                                    
                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="saverec();">
                                                <span><span><img src="css/images/icons/silk/save.png"  alt="" style="border-width: 0pt;"/>Update</span></span>
                                                    </s:a>
                                        </td>
                                    </s:if>
                                    <s:elseif test="%{#session.sessUserCode==ENTRYID && reqsts==2}">
                                        <td class="sexybutton" style="width:70px">                                    
                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="saverec();">
                                                <span><span><img src="css/images/icons/silk/save.png"  alt="" style="border-width: 0pt;"/>Update</span></span>
                                                    </s:a>
                                        </td>    
                                    </s:elseif>
                                        <s:elseif test="%{#session.sessUserCode==ENTRYID && reqsts==3}">
                                        <td class="sexybutton" style="width:70px">                                    
                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="saverec();">
                                                <span><span><img src="css/images/icons/silk/save.png"  alt="" style="border-width: 0pt;"/>Update</span></span>
                                                    </s:a>
                                        </td>    
                                    </s:elseif>
                                    <s:elseif test="%{#session.sessUserCode==ENTRYID && reqsts==4}">
                                        <td class="sexybutton" style="width:70px">                                    
                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="saverec();">
                                                <span><span><img src="css/images/icons/silk/save.png"  alt="" style="border-width: 0pt;"/>Update</span></span>
                                                    </s:a>
                                        </td>    
                                    </s:elseif>
                                    <s:elseif test="%{#session.sessUserCode==CHQCHID && reqsts==5}">
                                        <td class="sexybutton" style="width:70px">                                    
                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="savests3();">
                                                <span><span><img src="css/images/icons/silk/save.png"  alt="" style="border-width: 0pt;"/>Update</span></span>
                                                    </s:a>
                                        </td>    
                                    </s:elseif>

                                    <s:else>
                                        <td class="disabled sexybutton" style="width:70px">                                    
                                            <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="">
                                                <span><span><img src="css/images/icons/silk/save.png"  alt="" style="border-width: 0pt;"/><font color="red">Update</font></span></span>
                                            </s:a>
                                        </td>
                                    </s:else>
                                </s:else>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td  style="text-align: center;color:red;font-size: 20px;font-weight: bold;"><s:property value="%{MESSAGE}"/></td>

                </tr>
            </table>
        </form>
    </body>
</html>
