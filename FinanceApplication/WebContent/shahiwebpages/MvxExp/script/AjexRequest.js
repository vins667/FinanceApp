function xmlhttpPost(objectName, objectNext, param) {
        	//alert("1--->"+objectName.name +", "+ objectNext.name +", "+ param);
        	//var param=createParam(objectName);
        	//alert(param);
            var xmlHttpReq = false; 
            var self = this;
            if (window.XMLHttpRequest) { 
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {  
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            }
            self.xmlHttpReq.open('POST', 'AjaxResponse.jsp', false); 
            self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            self.xmlHttpReq.onreadystatechange = function() {
            	removeOptions(objectNext);
            	//alert(self.xmlHttpReq.readyState);
            	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200) { 
            		    	updatePage(objectNext, self.xmlHttpReq.responseXML); 
                }
            }
           //alert("2--->"+"req="+objectName.name+ "&objectNext="+objectNext.name+"&param="+param+"&time="+(new Date()).getTime());
           var reporttype=document.getElementById('reporttype').value;
           //alert(reporttype);
        	self.xmlHttpReq.send("req="+objectName.name+ "&objectNext="+objectNext.name+"&param="+encodeURIComponent(param)+"&time="+(new Date()).getTime()+"&reporttype="+reporttype);
        }
        
        
        function updatePage(objectName, xmlDoc) {
        	try{
        		//alert("objectName: "+objectName.name);
        		var responseXML = xmlDoc.getElementsByTagName("responseXML")[0];
        		//var showElements = responseXML.getElementsByTagName("show")[0];
        		//alert("showElements.length: "+responseXML.childNodes.length);
        		for (var x=0; x<responseXML.childNodes.length; x++) {
        			var showElements=responseXML.childNodes[x];
        			var text = showElements.getElementsByTagName('text')[0].firstChild.data;
        			var value = showElements.getElementsByTagName('type')[0].firstChild.data;
        			//alert(text+" : "+value);
        			addOption(objectName,text,value );
        		}
        	}catch(e){alert('Error: adding option.'+e.description);}
        }
        function addOption(selectbox,text,value ){
        	var optn = document.createElement("OPTION");
        	optn.text = text;
        	optn.value = value;
         	selectbox.options.add(optn);
        	
        }
        function removeOptions(selectbox)
        {
        	var i;
        	for(i=selectbox.options.length-1;i>=0;i--){
        		selectbox.remove(i);
        	}
        }
        function createParam(selectbox)
        {
        	var i;
        	var param='';
        	for(i=selectbox.options.length-1;i>=0;i--){
        		alert(selectbox.options[i].value);
        		 if(param=='')
                 {
        			 param = "'"+selectbox.options[i].value+"'" ;
                 }else{
                	 param = param+",'"+selectbox.options[i].value+"'" ;
                 }
        	}
        	return param;
        }
        
        
      