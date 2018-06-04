function xmlhttpreqplace(objsrc,objtrg)
         {
             /*if(objsrc.value=='')
             {
                 alert('Please enter place ');
                 objsrc.focus();
                 return false;
             }*/
            var xmlHttpReq = false; 
            var self = this;  
            if (window.XMLHttpRequest) { 
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {   
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } 
                                           
            self.xmlHttpReq.open('POST', 'ajxplaceAJXPREAction.action', false); 
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
                          document.getElementById('handlefrm').src="CsytabViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=PLACE&PARAB=PLACE_DESC&TYPE_CODE=EDES";
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
                          document.getElementById('handlefrm').src="CsytabSearch.jsp";
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
            self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=PLACE&PARAB=PLACE_DESC&TYPE_CODE=EDES"+"&time="+(new Date()).getTime());
         }



function xmlhttpreqclearingport(objsrc,objtrg)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxplaceAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="CsytabViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=CLR_PORT&PARAB=CLR_PORT_DESC&TYPE_CODE=HAFE";
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
                 document.getElementById('handlefrm').src="CsytabSearch.jsp";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=CLR_PORT&PARAB=CLR_PORT_DESC&TYPE_CODE=HAFE"+"&time="+(new Date()).getTime());
}



function xmlhttpreqloadingport(objsrc,objtrg)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxplaceAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="CsytabViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=LOADING_PORT&PARAB=LOADING_PORT_DESC&TYPE_CODE=HAFE";
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
                 document.getElementById('handlefrm').src="CsytabSearch.jsp";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=LOADING_PORT&PARAB=LOADING_PORT_DESC&TYPE_CODE=HAFE"+"&time="+(new Date()).getTime());
}
    


function xmlhttpreqdischarge(objsrc,objtrg)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxplaceAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="CsytabViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=DISCHARGE&PARAB=DISCHARGE_DESC&TYPE_CODE=SDST";
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
                 document.getElementById('handlefrm').src="CsytabSearch.jsp";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=DISCHARGE&PARAB=DISCHARGE_DESC&TYPE_CODE=SDST"+"&time="+(new Date()).getTime());
}
         

function xmlhttpreqdiscntry(objsrc,objtrg)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxplaceAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="CsytabViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=DIS_CNTRY&PARAB=DIS_CNTRY_DESC&TYPE_CODE=CSCD";
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
                 document.getElementById('handlefrm').src="CsytabSearch.jsp";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=DIS_CNTRY&PARAB=DIS_CNTRY_DESC&TYPE_CODE=CSCD"+"&time="+(new Date()).getTime());
}
             


function xmlhttpreqdiscntryoriginal(objsrc,objtrg)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxplaceAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="CsytabViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=CNTRY_ORIGIN&PARAB=CNTRY_ORIGIN_DESC&TYPE_CODE=CSCD";
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
                 document.getElementById('handlefrm').src="CsytabSearch.jsp";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=CNTRY_ORIGIN&PARAB=CNTRY_ORIGIN_DESC&TYPE_CODE=CSCD"+"&time="+(new Date()).getTime());
}
      



function xmlhttpreqshipmode(objsrc)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxplaceAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="CsytabViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=mode_of_ship&PARAB=mode_of_ship&TYPE_CODE=MODL";
                 openpop('root');
                 objsrc.value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="CsytabSearch.jsp";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=mode_of_ship&PARAB=mode_of_ship&TYPE_CODE=MODL"+"&time="+(new Date()).getTime());
}
      

function xmlhttpreqacholder(objsrc)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxgrpviewAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="GruptypeViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=ac_holder&PARAB=ac_holder&TYPE_CODE=AHN";
                 openpop('root');
                 objsrc.value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="GruptypeSearch.jsp";
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[1];
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=ac_holder&PARAB=ac_holder&TYPE_CODE=AHN"+"&time="+(new Date()).getTime());
}




function xmlhttpreqmerchent(objsrc)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxgrpviewAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="GruptypeViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=merchant&PARAB=merchant&TYPE_CODE=MER";
                 openpop('root');
                 objsrc.value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="GruptypeSearch.jsp";
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[1];
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=merchant&PARAB=merchant&TYPE_CODE=MER"+"&time="+(new Date()).getTime());
}


function xmlhttpreqlcview(objsrc)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxglcviewAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="lcViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=lcno&PARAB=lcno&TYPE_CODE=lcno";
                 openpop('root');
                 objsrc.value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="lcsearch.jsp";
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[1];
               }
             }
   	}
   }
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=lcno&PARAB=lcno&TYPE_CODE=lcno"+"&time="+(new Date()).getTime());
}



function notifysearch(objsrc,objtrg)
{
    /*if(objsrc.value=='')
    { 
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxglchaiewAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="chaViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=notify&PARAB=NOTIFY_NAME";
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
                 document.getElementById('handlefrm').src="chaViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=agen&PARAB=CHA_NAME";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=notify&PARAB=NOTIFY_NAME"+"&time="+(new Date()).getTime());
}



function search_cha(objsrc,objtrg)
{
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxglchaiewAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="chaViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=agent&PARAB=CHA_NAME";
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
                 
                 document.getElementById('handlefrm').src="chaViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=agen&PARAB=CHA_NAME";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=agent&PARAB=CHA_NAME"+"&time="+(new Date()).getTime());
}      
 
function xmlhttpreqfwd(objsrc,objtrg)
{
   
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxglchaiewAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="chaViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=fwd_code&PARAB=FWD_NAME";
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                
                 alert(b[0]);
                 document.getElementById('handlefrm').src="chaViewPREINVMVX.action?&PARAA=fwd_code&PARAB=FWD_NAME";
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=fwd_code&PARAB=FWD_NAME"+"&time="+(new Date()).getTime());
}


function xmlhttpreqmanuf(objsrc,objtrg)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false; 
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxunitviewAJXPREAction.action', false); 
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
                 document.getElementById('handlefrm').src="unitViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=MANUF_CODE&PARAB=MANUF_DESC";
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
                 document.getElementById('handlefrm').src="Manufsearch.jsp";
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=MANUF_CODE&PARAB=MANUF_DESC"+"&time="+(new Date()).getTime());
}
 

function xmlhttpreqtax(objsrc,objtrg,objtrg1)
{
    /*if(objsrc.value=='')
    {
        alert('Please enter place ');
        objsrc.focus();
        return false;
    }*/
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxtaxviewAJXPREAction.action', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       { 
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             if(b.length>3)
             {
            	 alert(b);
                 document.getElementById('handlefrm').src="taxcodeViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=TAX_CODE&PARAB=TAX_TYPE&TYPE_CODE=TAX_PERCENT";
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 document.getElementById(objtrg1).value='';
             }
             else
             {
               if(b[0]=='Data Not Found')
               {   
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 document.getElementById(objtrg1).value='';
                 alert(b[0]);
                 document.getElementById('handlefrm').src="taxcodesearch.jsp";
                 openpop('root');
              }
               else
               {
                   objsrc.value=b[0];
                   document.getElementById(objtrg).value=b[1];
                   document.getElementById(objtrg1).value=b[2];
               }
             }
   	}
   }   
   param=objsrc.value;
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=TAX_CODE&PARAB=TAX_TYPE&TYPE_CODE=TAX_PERCENT"+"&time="+(new Date()).getTime());
}

function xmlhttpreqsb(objsrc,objtrg)
         {
                 
            var xmlHttpReq = false; 
            var self = this;   
            if (window.XMLHttpRequest) { 
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {   
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } 
                                       
            self.xmlHttpReq.open('POST', 'sbAJAXEPTRACKAJAX.action', false); 
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
                          document.getElementById('handlefrm').src="sbViewEPTRACK.action?unitparam="+objsrc.value+"&PARAA=SB_NO&PARAB=SB_DATE";
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
                          document.getElementById('handlefrm').src="sbsearch.jsp";
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
            self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=SB_NO&PARAB=SB_DATE"+"&time="+(new Date()).getTime());
         }

           
function xmlhttpreqcatg(objsrc,objtrg,destination)
{
   alert('destination'+destination);
   
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxcatgViewAJXPREAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
            
             if(b.length>2)
             {
           	 
                 document.getElementById('handlefrm').src="catgViewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=CATG_CODE_COPY&PARAB=CATG_DESC_COPY&TYPE_CODE="+document.getElementById("DESTI_CNTRY").value;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else 
             {
               if(b[0]=='Data Not Found')
               {   
                
                 alert(b[0]);
                 /*document.getElementById('handlefrm').src="precatgesearch.jsp?TYPE_CODE="+objsrc.value+"&DESTI_CNTRY="+document.getElementById("DESTI_CNTRY").value;
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 openpop('root');*/
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
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=CATG_CODE_COPY&PARAB=CATG_DESC_COPY&TYPE_CODE="+document.getElementById("DESTI_CNTRY").value+"&time="+(new Date()).getTime());
}
function xmlhttpreqdbkslno(objsrc,objtrg)
{
   
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxdbkslnoViewAJXPREAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {  
           	 
                 document.getElementById('handlefrm').src="predbkslviewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=DBK_SLNO_COPY&INVDATE="+document.getElementById("inv_date").value;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             { 
               if(b[0]=='Data Not Found')
               {   
                   
                 alert(b[0]);
                 
                  document.getElementById('handlefrm').src="predbkslviewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=DBK_SLNO_COPY&INVDATE="+document.getElementById("inv_date").value;
                  objsrc.value='';
                 document.getElementById(objtrg).value='';
                 openpop('root'); 
              }   
               else
               {
                   objsrc.value=b[0];
                   document.getElementById(objtrg).value=b[1];
                   
                   //alert("value "+b[1].substring(0,b[1].length()-1)+"E");
                   //system.out.println("value "+b[1].substring(0,b[1].length()-1)+"E");
                 //  document.getElementById("ROSL_SLNO_COPY").value=b[1].substring(0,b[1].length()-1)+"E";
               }
             }
   	} 
   }  
   
   param=objsrc.value;
    /*self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=DBK_SLNO_COPY&INVDATE="+document.getElementById("inv_date").value+"&time="+(new Date()).getTime());*/
   self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=DBK_SLNO_COPY&INVDATE="+document.getElementById("inv_date").value+"&time="+(new Date()).getTime());
}

function xmlhttpreqstrslno(objsrc,objtrg)
{
	
    
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxstrslnoViewAJXPREAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
          
             if(b.length>2)
             {  
           	 document.getElementById('handlefrm').src="prestrslviewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=STR_SLNO_COPY&PARAB=STR&dbkslnocopy="+document.getElementById('DBK_SLNO_COPY').value+"&INVDATE="+document.getElementById("inv_date").value;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             {  
               if(b[0]=='Data Not Found')
               {   
                   
                 alert(b[0]);
                 
                  document.getElementById('handlefrm').src="prestrslviewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=STR_SLNO_COPY&PARAB=STR&dbkslnocopy="+document.getElementById('DBK_SLNO_COPY').value+"&INVDATE="+document.getElementById("inv_date").value;
               //   document.getElementById('handlefrm').src="PreinvStrSearch.jsp";
                   objsrc.value='';
                 document.getElementById(objtrg).value='';
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
    self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=STR_SLNO_COPY&PARAB=STR&dbkslnocopy="+document.getElementById('DBK_SLNO_COPY').value+"&INVDATE="+document.getElementById("inv_date").value+"&time="+(new Date()).getTime());
 }

function xmlhttpreqstrmisc(objsrc,objtrg)
{
    
   var xmlHttpReq = false; 
   var self = this;
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   self.xmlHttpReq.open('POST', 'ajxstrslnoViewAJXPREAction', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
          
             if(b.length>2)
             {  
           	 
                 document.getElementById('handlefrm').src="prestrslviewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=STR_MISC_COPY&PARAB=STRMISC&INVDATE="+document.getElementById("inv_date").value;
                 openpop('root');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             { 
               if(b[0]=='Data Not Found')
               {   
                   
                 alert(b[0]);
                 
                 document.getElementById('handlefrm').src="prestrslviewPREINVMVX.action?unitparam="+objsrc.value+"&PARAA=STR_MISC_COPY&PARAB=STRMISC&INVDATE="+document.getElementById("inv_date").value;
             //    document.getElementById('handlefrm').src="PreInvStrSearch.jsp";
                   objsrc.value='';
                 document.getElementById(objtrg).value='';
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
    self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=STR_SLNO_COPY&PARAB=STRMISC&dbkslnocopy="+document.getElementById('DBK_SLNO_COPY').value+"&INVDATE="+document.getElementById("inv_date").value+"&time="+(new Date()).getTime());
 }	  

function rosltest(objsrc, objtrg) {
	
	var xmlHttpReq = false;
	var self = this;
	if (window.XMLHttpRequest) {
		self.xmlHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	}
	self.xmlHttpReq.open('POST', 'ajxroslslnoViewAJXPREAction', false);
	self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	self.xmlHttpReq.onreadystatechange = function() {
		if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200) {
			var a = self.xmlHttpReq.responseText;

			var b = new Array();
			b = a.split('#');

			if (b.length > 2) {
				document.getElementById('handlefrm').src = "preroslslviewPREINVMVX.action?unitparam=" + objsrc.value + "&PARAA=ROSL_SLNO_COPY&INVDATE=" + document.getElementById("inv_date").value;
				openpop('root');
				objsrc.value = '';
				document.getElementById(objtrg).value = '';
			} else {
				if (b[0] == 'Data Not Found') {

					alert(b[0]);

					document.getElementById('handlefrm').src = "preroslslviewPREINVMVX.action?unitparam=" + objsrc.value + "&PARAA=ROSL_SLNO_COPY&INVDATE=" + document.getElementById("inv_date").value;
					objsrc.value = '';
					document.getElementById(objtrg).value = '';
					openpop('root');
				} else {
					objsrc.value = b[0];
					document.getElementById(objtrg).value = b[1];
				}
			}
		}
	}
	param = objsrc.value;
	self.xmlHttpReq.send("unitparam=" + encodeURIComponent(param) + "&PARAA=ROSL_SLNO_COPY&INVDATE=" + document.getElementById("inv_date").value + "&time=" + (new Date()).getTime());
}
       