function xmlhttpreqbuyer(objsrc,objtrg)
         {
	
            var xmlHttpReq = false; 
            var self = this;
            if (window.XMLHttpRequest) { 
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {  
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            }
            self.xmlHttpReq.open('POST', 'getajxbuyeraddBOSAjaxAction', false); 
            self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            self.xmlHttpReq.onreadystatechange = function() {
               	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
                { 
                      var a = self.xmlHttpReq.responseText;
                      var b= new Array();
                      b=a.split('#');
                      if(b.length>4)
                      {
                    	  //alert(b);
                          document.getElementById('handlefrm').src="getbuyeraddbillofsalesAction.action?S_BUYER_CODE="+objsrc.value;
                          openpop('root');
                          objsrc.value='';
                          document.getElementById(objtrg).value='';
                      }
                      else
                      {
                        if(b[0]=='Data Not Found')
                        {   
                          objsrc.value='';
                          document.getElementById(objtrg).value='';
                          alert(b[0]);
                          document.getElementById('handlefrm').src="getbuyeraddbillofsalesAction.action";
                          openpop('root');
                        }
                        else
                        {
                            objsrc.value=b[2];
                            document.getElementById(objtrg).value=b[0];
                            document.getElementById('BUYER_ADDR').value=b[3];
                            document.getElementById('address').value=b[2];
                            document.getElementById('addressno').value=b[3];
                        }
                      }
            	}
            }
            param=objsrc.value;
            self.xmlHttpReq.send("S_BUYER_CODE="+encodeURIComponent(param)+"&S_BUYER_ADD="+document.getElementById('addressno').value+"&time="+(new Date()).getTime());
         }

function xmlhttpreqagent(objsrc,objtrg)
{
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'getajaxagentBOSAjaxAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>4)
             {
           	  //alert(b);
                 document.getElementById('handlefrm').src="agentViewbillofsalesAction?unitparam="+objsrc.value+"&PARAA=PLACE&PARAB=PLACE_DESC";
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="agentsearch.jsp";
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[2];
                   document.getElementById(objtrg).value=b[0];
                   
                   if(objtrg=='CHA_DESC')
                   {
                	   document.getElementById('CHA_ADDR').value=b[3];
                	   document.getElementById('cha_address').value=b[1];
                	   document.getElementById('cha_addressno').value=b[3];
                   }
                   //DISPATCH_VIA
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=PLACE&PARAB=PLACE_DESC"+"&time="+(new Date()).getTime());
}


function xmlhttpreqtpt(objsrc,objtrg)
{
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'getajaxagentBOSAjaxAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>4)
             {
           	  //alert(b);
                 document.getElementById('handlefrm').src="agentViewbillofsalesAction?unitparam="+objsrc.value+"&PARAA=TRANSPORTER&PARAB=DISPATCH_VIA";
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="agentViewbillofsalesAction.action?PARAA=TRANSPORTER&PARAB=DISPATCH_VIA";
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[2];
                   document.getElementById(objtrg).value=b[0];
                   
                   if(objtrg=='CHA_DESC')
                   {
                	   document.getElementById('CHA_ADDR').value=b[1];
                	   document.getElementById('cha_address').value=b[1];
                	   document.getElementById('cha_addressno').value=b[3];
                   }
                   //DISPATCH_VIA
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=TRANSPORTER&PARAB=DISPATCH_VIA"+"&time="+(new Date()).getTime());
}


function xmlhttprequnit(objsrc,objtrg)
{
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   if(objtrg=='UNIT_DESC')
	   {
	      paramter="PARAA=UNIT&PARAB=UNIT_DESC";
	   }
   else
	   {
	   	  paramter="PARAA=UNIT_TO&PARAB=UNIT_TO_DESC";
	   }
   
   
   
   self.xmlHttpReq.open('POST', 'getajaxunitBOSAjaxAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>3)
             {
           	  //alert(b);
                 document.getElementById('handlefrm').src="unitViewbillofsalesAction.action?unitparam="+objsrc.value+"&"+paramter;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="unitViewbillofsalesAction.action?"+paramter;
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[0];
                   document.getElementById(objtrg).value=b[1];
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&"+paramter+"&time="+(new Date()).getTime());
}

function xmlhttpreqport(objsrc,objtrg)
{
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   if(objtrg=='DESTINATION_DESC')
   {
      paramter="PARAA=DESTINATION&PARAB=DESTINATION_DESC&TYPE_CODE=CSCD";
   }
else
   {
   	  paramter="PARAA=PORT&PARAB=PORT_DESC&TYPE_CODE=HAFE";
   }

     
   self.xmlHttpReq.open('POST', 'getajaxportBOSAjaxAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {
           	//  alert(b);
                 document.getElementById('handlefrm').src="portViewbillofsalesAction.action?unitparam="+objsrc.value+"&"+paramter;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="portViewbillofsalesAction.action?"+paramter;
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[0];
                   document.getElementById(objtrg).value=b[1];
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&"+paramter+"&time="+(new Date()).getTime());
}

function xmlhttpreqshipmode(objsrc)
{
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'getajaxshipmodeBOSAjaxAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {
                 document.getElementById('handlefrm').src="ShipmodebillofsalesAction.action?unitparam="+objsrc.value+"&PARAA=SHIP_MODE&PARAB=SHIP_MODE&TYPE_CODE=MODL";
                 openpop('root');
                 objsrc.value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="ShipmodebillofsalesAction.action?PARAA=SHIP_MODE&PARAB=SHIP_MODE&TYPE_CODE=MODL";
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[0];
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=SHIP_MODE&PARAB=SHIP_MODE&TYPE_CODE=MODL"+"&time="+(new Date()).getTime());
}

function xmlhttpreqcfs(objsrc,objtrg)
{
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   if(objtrg=='CFS_DESC')
	   {
	      paramter="PARAA=CFS_CODE&PARAB=CFS_DESC&TYPE_CODE=CFS";
	   }
   else
	   {
	   	  paramter="PARAA=VEHICLE_TYPE&PARAB=VEHICLE_TYPE_DESC&TYPE_CODE=BOS";
	   }
	   
	   //PARAA=CFS_CODE&PARAB=CFS_DESC&TYPE_CODE=CFS
   
   self.xmlHttpReq.open('POST', 'getajaxcfsBOSAjaxAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {
           	  //alert(b);
                 document.getElementById('handlefrm').src="cfsViewbillofsalesAction.action?unitparam="+objsrc.value+"&"+paramter;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="cfsViewbillofsalesAction.action?"+paramter;
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[0];
                   document.getElementById(objtrg).value=b[1];
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&"+paramter+"&time="+(new Date()).getTime());
}


function xmlhttpreqinv(objsrc,objtrg,index)
{ 
     
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'getajaxInvBOSAjaxAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
            
             if(b.length>20)
             {   
           	  
                 document.getElementById('handlefrm').src="viewInvbillofsalesAction.action?unitparam="+objsrc.value+"&PARAA="+index;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             { 
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="viewInvbillofsalesAction.action?PARAA="+index;
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[0];
                   document.getElementById(objtrg).value=b[7];
                    
                   document.getElementById('INV_NO'+index).value=b[2];
                   document.getElementById('INV_DESC'+index).value=b[7];
                   document.getElementById('QNTY'+index).value=b[3];
                   document.getElementById('AVG_RATE'+index).value=b[4];
                   document.getElementById('CRNCY'+index).value=b[5];
                   document.getElementById('INR_CONV'+index).value=b[6];
                   document.getElementById('FOB'+index).value=b[9];
                   document.getElementById('UOM'+index).value=b[8];
                   document.getElementById('YEAR'+index).value=b[0];
                   document.getElementById('COMPANY'+index).value=b[1];
                   document.getElementById('EXCS_INV_NO'+index).value=b[11];
                   document.getElementById('i_cha'+index).value=b[13];
                   document.getElementById('i_port'+index).value=b[12];
                   document.getElementById('i_agent'+index).value=b[14];
                   document.getElementById('i_buyer'+index).value=b[15];
                   document.getElementById('i_address'+index).value=b[16];
                   document.getElementById('BUYER').value=b[15];
                   document.getElementById('CHA').value=b[14];
                   document.getElementById('PORT').value=b[12];
                   document.getElementById('BUYER_ADDR').value=b[16];
                   document.getElementById('addressno').value=b[16];
                   document.getElementById('DESTINATION').value=b[13];
                   document.getElementById('INR'+index).value=eval(b[6])*eval(b[9]);
                   document.getElementById('SHIP_MODE').value=b[19];
                  
                   
                  totalpalnqty();
                  totalfobqty();
                  totalinvqty();
                   
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA="+index+"&time="+(new Date()).getTime());
}





