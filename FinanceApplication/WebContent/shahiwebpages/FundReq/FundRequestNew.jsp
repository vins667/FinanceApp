<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<link href="<s:url value="../css/main.css"/>" rel="stylesheet" type="text/css"/>
<script language="javascript" type="text/javascript" src="js/datetimepicker.js"/>
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
            function chkpono(a){
                alert('Purchase order '+a+' Invalid!!!');
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
                if(document.getElementById("paydivi").value==''){
                    alert('Division cannot be blank');
                    document.getElementById("paydivi").focus();
                    return false;
                }
                if(document.getElementById("reqpototalamt").value==''){
                    alert('Request Amount cannot be blank')
                    return false;
                }
                if(eval(document.getElementById("reqpototalamt").value)==0)
                {
                    alert('Request Amount cannot be less than zero...')
                    return false;
                }
                document.frm.action="saveFundRequestNew.action";
                document.frm.submit()
                document.getElementById("saveid").disabled=true;
            }
            /*function getrec()
            {
                 document.frm.action="FundRequestNew.action?getPO=Yes";
                 document.frm.submit()
            }*/
            
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
                                            <th style="text-align: left;height: 25px;">&nbsp;Pymt Text</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Del.Place</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Status</th>
                                            <th style="text-align: left;height: 25px;">&nbsp;Req TO</th>
                                        </tr>
                                        <tr style="background-color: #e5eaf5;">
                                            <td><s:select label="Type" value="%{paydivi}" theme="simple"  name="paydivi" id="paydivi" list="divilist" listKey="REQ_CODE" listValue="REQ_NAME" headerKey="" headerValue="Select" cssStyle="width:100%;font-size:12px" cssClass="texts"/></td>
                                            <td><s:textfield name="reqno" readonly="true" value="%{reqno}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly"/></td>
                                            <td><s:textfield  name="reqdt" readonly="true"  value="%{reqdt}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly"/></td>
                                            <td><s:select label="Type"  value="%{reqtyp}" theme="simple" id="reqtyp" name="reqtyp" list="reqtyplist" listKey="REQ_CODE" listValue="REQ_NAME" cssStyle="width:100%;font-size:12px" cssClass="texts" onchange="propertychg();"/></td>
                                            <td><s:textfield id="reqsuno"  name="reqsuno"  value="%{reqsuno}" readonly="true" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" onclick="buyersearch();"/></td>
                                            <td style="width: 220px"><s:textfield  name="partyname"  id="" value="%{partyname}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" readonly="true"/></td>                                        
                                            <td><s:textfield  name="reqtxt"  value="%{reqtxt}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts"/></td>
                                            <td><s:textfield  name="dlvplc"  value="%{dlvplc}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts"/></td>
                                            <td><s:textfield name="reqsts" readonly="true" value="%{reqsts}"  theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts"/></td>
                                            <td><s:select label="Type" value="%{payloct}" theme="simple"  name="payloct" list="payloctlist" listKey="REQ_CODE" listValue="REQ_NAME" cssStyle="width:100%;font-size:12px" cssClass="texts"/></td>
                                        </tr>

                                    </table>

                                    <table width="100%" border="1" cellpadding="1" bgcolor="#e5eaf5" cellspacing="0">
                                        <tr>
                                            <td class="label-1">Bank Name</td>
                                            <td style="width:150px;"><s:textfield  name="reqbkno"  value="%{reqbkno}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" readonly="true"/></td>
                                            <td class="label-1">Chq No </td>
                                            <td style="width:150px;"><s:textfield  name="reqchq"  value="%{reqchq}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" readonly="true"/></td>
                                            <td class="label-1">Chq Date</td>
                                            <td style="width:150px;"><s:textfield name="reqchqdt" id="reqchqdt" theme="simple" readonly="true" cssClass="textreadonly" cssStyle="width:100%;font-size:12px"/></td>
                                            <td class="label-1">Chq Delivered</td>
                                            <td style="width:150px;"><s:textfield  name="chqdelv"  value="%{chqdelv}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="textreadonly" readonly="true" /></td>

                                        </tr>
                                        <tr>   
                                            <td class="label-1">PI No. </td><td><s:textfield  name="reqpino" value="%{reqpino}" theme="simple" cssStyle="width:100%;font-size:12px" cssClass="texts" /></td>
                                            <td class="label-1">Emp Code </td><td><s:textfield  name="reqempcd" value="%{reqempcd}" theme="simple" cssStyle="width:100%;font-size:12px" readonly="true" cssClass="textreadonly" /></td>
                                             <td class="label-1">PDC Date</td>
                                             <td style="width:150px;"><sx:datetimepicker name="reqpdcdt" id="reqpdcdt" startDate="MIN_DATE" endDate="MAX_DATE"  cssClass="texts" value="%{reqpdcdt}" displayFormat="dd/MM/yyyy"  cssStyle="width:100px;font-size:12px"/></td>
 
                                            
                                            <s:if test='%{reqdisc=="Y"}'>
                                                <td class="label-1">Discount  <input type="CHECKBOX"  name="reqdisc" checked="true"    value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:if>
                                                <s:else>
                                                <td class="label-1">Discount  <input type="CHECKBOX"  name="reqdisc"   value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:else>
                                                <s:if test='%{mvxpaid=="Y"}'>
                                                <td class="label-1">TDS  <input type="CHECKBOX"  name="mvxpaid" checked="true" readonly="true"  value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:if>
                                                <s:else>
                                                <td class="label-1">TDS  <input type="CHECKBOX" name="mvxpaid" readonly="true"  value="Y" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
                                                </s:else>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr><td align="center"> 
                                    <table><tr><td border="1">
                                                <table  cellpadding="0" bgcolor="#e5eaf5"  cellspacing="1">
                                                    <tr>
                                                        <td class="hd" style=" font-size:18px; text-align:center" colspan="2">PO Detail</td>
                                                    </tr>
                                                    <tr>
                                                    <td height="100pt"  style="border-width:1pt;border-color:silver;border-style:solid;" colspan="2">
                                                        <div  class="div1" style="overflow:auto ;height:250px;width:1100px">
                                                            <table id="tebleidpo" bgcolor="#d9e8fe"   cellpadding="0" cellspacing="1" >
                                                                <thead>
                                                                    <tr style="background-color: #6699cc;position: absolute; top: expression(this.offsetParent.scrollTop);">
                                                                        <th class="label-1" style="height:18pt;width:50px;">PO Block</th>
                                                                        <th align="center" class="label-1" style="width:100px;">PO No</th>
                                                                        <th align="center" class="label-1" style="width:100px" >Remarks</th>
                                                                        <th align="center" class="label-1" style="width:100px" >PO Date</th>
                                                                        <th align="right" class="label-1" style="width:100px" >PO Amt</th>
                                                                        <th align="right" class="label-1" style="width:100px" >Adv Paid</th>
                                                                        <th align="right" class="label-1" style="width:100px" >Req Amt</th>
                                                                        <th align="right" class="label-1" style="width:100px" >Srv Tax</th>
                                                                        <th  class="label-1">&nbsp;</th>
                                                                    </tr></thead>
                                                                </thead>
                                                                <tbody >

                                                                    <s:if test="%{showReqdtl!=null}">
                                                                        <s:iterator value="showReqdtl" id="itr" status="st">
                                                                            <tr>
                                                                                <s:if test='%{reqpost=="1"}'>
                                                                                    <td style="width:50px;"><input type="CHECKBOX"  name="mvxpaid" checked="true"  name="reqpost" readonly="true"  value="%{reqpost}" theme="simple" cssStyle="width:30pt;font-size:9pt"  /></td>
                                                                                    </s:if>
                                                                                    <s:else>
                                                                                    <td><input type="CHECKBOX"  name="mvxpaid" value="1"  name="reqpost" readonly="true"  value="%{reqpost}" theme="simple" cssStyle="width:30pt;font-size:9pt"  /></td>
                                                                                    </s:else>
                                                                                <td style="width:100px;"><s:textfield  name="reqpono"  value="%{reqpono}" readonly="true"   theme="simple" cssStyle="width:80pt;font-size:9pt"/></td>
                                                                                <td style="width:100px;"><s:textfield  name="reqpodt"  readonly="true"  value="%{reqpodt}"  theme="simple" cssStyle="width:70pt;font-size:9pt"/></td>
                                                                                <td align="right" style="width:100px;"><s:textfield  name="rpoamt"  value="%{rpoamt}" readonly="true" theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                                                                                <td align="right" style="width:100px;" ><s:textfield  name="advamt"  value="%{advamt}" readonly="true" theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                                                                                <td align="right" style="width:100px;"><s:textfield name="reqpoamt"  value="%{reqpoamt}"  theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                                                                                <td align="right" style="width:100px;"><s:textfield name="srvtax"  value="%{srvtax}"  theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                                                                            </tr>

                                                                        </s:iterator>
                                                                    </s:if>
                                                                    <s:if test="%{newinsert=='YES'}">
                                                                        <s:set id="newslno" name="newslno" value="0"/>
                                                                        <s:iterator status="st1" id="aaa"  begin="0" end="0">
                                                                            <tr>
                                                                                <td style="width:50px;"><input type="CHECKBOX"  name="mvxpaid" value="1"  name="reqpost"   readonly="true"  value="%{reqpost}" theme="simple" cssStyle="width:30pt;font-size:9pt"  /></td>
                                                                                <td style="width:100px;"><s:textfield  name="reqpono" id="reqpono%{#st1.index}" value="%{reqpono[#st1.index]}" onblur="show_details('%{#st1.index}');addReqAmt();addpono(this,'%{#newslno}')"  theme="simple" cssClass="texts" cssStyle="width:80pt;font-size:9pt"/></td>
                                                                                <td colspan="6" style="width:600px;">
                                                                                   <s:url id="d_url" action="getpodtBuyerFundRequest.action" >
                                                                                        <s:param name="textid" value="%{#st1.index}"/>
                                                                                    </s:url>
                                                                                    <sx:div id="details%{#st1.index}"  href="%{d_url}" listenTopics="show_detail%{#st1.index}" formId="frm" executeScripts="true" showLoadingText="Loading ......"></sx:div>

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
                                                                                                                            <s:iterator status="st" begin="0" end="9">
                                                                                                                                <tr>
                                                                                                                                    <td style="width:100px"><s:textfield  name="dlrqpo" cssClass="textreadonly"  id="delrqpo%{#newslno}id%{#st.index+1}" theme="simple" cssStyle="width:100%;font-size:9pt" readonly="true"/></td>
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
                                                                                            </tr></table>
                                                                                    </div>

                                                                                </td>
                                                                            </tr>


                                                                        </s:iterator>                             
                                                                    </s:if>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                        <tr>
                                            <td colspan="2">
                                                <table id="tebleidpo" bgcolor="#d9e8fe"   cellpadding="0" cellspacing="1" >
                                                                    <tr>
                                                                        <td class="label-1" style="height:18pt;width:650px;" colspan="6"><b>Total Req Amount:</b></td>
                                                
                                                                        <td><s:textfield name="reqpototalamt" id="reqpototalamt" value="" theme="simple" cssClass="textreadonly" readonly="true" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                                                                    </tr>
                                                </table>
                                            </td>


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
            <td>
                <table width="100%" cellpadding="1" cellspacing="0"><tr><td>
                        <td align="left"><input type="button" name="exitbnt" style="width:60pt" onclick="window.location.href='backFundRequestAction.action'" value="Back" class="submitbutton1"></td>
                        <td align="right">
                            <input type="button" name="btn" id="saveid" style="width:60pt" value="Save" onclick="saverec()"  class="submitbutton1">                            
                        </td>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

