/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// Trap Backspace(8) and Enter(13) -
// Except bksp on text/textareas, enter on textarea/submit
//*****************************************************************
cDivs = new Array();
function disableDivs()
{
    d = document.getElementsByTagName("BODY")[0];
    for(x=0;x<arguments.length;x++)
    {
        if (document.getElementById(arguments[x]))
        {
            xPos = document.getElementById(arguments[x]).offsetLeft;
            yPos = document.getElementById(arguments[x]).offsetTop;
            oWidth = document.getElementById(arguments[x]).offsetWidth;
            oHeight = document.getElementById(arguments[x]).offsetHeight;
            cDivs[cDivs.length] = document.createElement("DIV");
            cDivs[cDivs.length-1].style.width = oWidth+"px";
            cDivs[cDivs.length-1].style.height = oHeight+"px";
            cDivs[cDivs.length-1].style.position = "absolute";
            cDivs[cDivs.length-1].style.left = xPos+"px";
            cDivs[cDivs.length-1].style.top = yPos+"px";
            cDivs[cDivs.length-1].style.backgroundColor = "#F0F0FF";
            cDivs[cDivs.length-1].style.opacity = .6;
            cDivs[cDivs.length-1].style.filter = "alpha(opacity=60)";
            d.appendChild(cDivs[cDivs.length-1]);
        }
    }
}
function hideCDivs()
{
    for (hippopotamus=0;hippopotamus<cDivs.length;hippopotamus++)
    {
        document.getElementsByTagName("BODY")[0].removeChild(cDivs[hippopotamus]);
    }
    cDivs = [];
}

function checkallchkbox(a,b,chkval)
{

chk=document.getElementById(a);
kkk=document.getElementsByName(a)
allchk=document.getElementsByName('chkb');
k=document.getElementById(a).value;


         if(chk.checked==true)
         {

             for (i=0;i<allchk.length;i++)
          {

                 if(!allchk[i].disabled){
                      s=allchk[i].id;
                      if(b=='c')
                          {
                             strid=s.substr(s.indexOf(":")+1, s.length)
                          }else{
                              strid=s.substr(0,s.indexOf(":"))

                          }

               if(a==strid)
                  {

                       allchk[i].checked=true;
                  }

                  }

          }

          }else{
           for (i=0;i<allchk.length;i++)
          {

                 if(!allchk[i].disabled){
                      s=allchk[i].id;
                       if(b=='c')
                          {
                             strid=s.substr(s.indexOf(":")+1, s.length)
                          }else{
                              strid=s.substr(0,s.indexOf(":"))

                          }
               if(a==strid)
                  {

                       allchk[i].checked=false;
                  }

                  }

          }

          }
    }


//**************************************************************************************

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

           //   getNextElement(e.target).focus();



           //return true;
      }
        return true;
     // return tabE(e.target,e)
      /*aaaa=e.keyCode;


        if(aaaa==13 && e.target.tagName == "INPUT")
          {    es=window.event
              alert('okkkk');
           e.keyCode = 9;
              alert('ok11');

        }
       //return true
        var t=e.target.type;
        var kc=e.keyCode;
        if ((kc != 8 && kc != 13) || ( t == 'text' &&  kc != 13 ) ||
            (t == 'textarea') || ( t == 'submit' &&  kc == 13))
            return true
        else {
          // alert('Sorry Backspace/Enter is not allowed here'); // Demo code
            return true

        }
        */
    }

function tabOnEnter (field, evt) {
       var keyCode = document.layers ? evt.which : document.all ?
     evt.keyCode : evt.keyCode;
       if (keyCode != 13)
         return true;
       else {
         var el=getNextElement(field);
         if (el.type!='hidden')
            el.focus();
         else
            while (el.type=='hidden')
               el=getNextElement(el);
            el.focus();
         return false;
       }
     }
function getNextElement (field) {
       var form = field.form;
       for (var e = 0; e < form.elements.length; e++) {
         if (field == form.elements[e])
             break;
       }
       return form.elements[++e % form.elements.length];
     }

 function tabE(obj,e){
    var e=(typeof event!='undefined')?window.event:e;// IE : Moz
    if(e.keyCode==13){

    var ele = document.forms[0].elements;
     alert(ele.length);
    alert(ele.Name);
    for(var i=0;i<ele.length;i++){
    var q=(i==ele.length-1)?0:i+1;// if last element : if any other
    if(obj==ele[i]){ele[q].focus();break}
    }
    return false;
    }
    }


function validateqty(a,b,c)
{

    if(a.value=='' || parseInt(a.value)==0){
        alert('Qty can not be zero,please delete line through View Lines option');
        a.value=c;
           return false
    }
    if(parseInt(a.value)<parseInt(b))
        {alert("Order Qty can not be less than " +b+ " Qty.")
           a.value=b;
       a.focus();
    return false
    }
return true;
}



function callnew(id,k){
    var a = id.value;
    var b = document.getElementsByName(a);
     var bname = document.getElementsByName('name'+a);
    var c='ordtype'+id.id;
    var selobj=document.getElementById(c);
    //alert(selobj.length);

      if(k=='y')
          {
    for(i=selobj.length-1; i>=1; i--)
    {
        //       alert(selobj[i].value);
        selobj.remove(i) ;
    }
          }else{
          for(i=selobj.length-1; i>=0; i--)
    {
        //       alert(selobj[i].value);
        selobj.remove(i) ;
    }


          }
    for(i=0; i<b.length; i++)
    {
        var op=document.createElement('option');
        //op.text=b[i].value;
        op.text=bname[i].value;
        op.value=b[i].value;
        selobj.options[selobj.options.length] = new Option(b[i].value+"-"+bname[i].value, b[i].value);


    // selobj.add(op);
    //alert(b[i].value)
    }

}
function valStyleNumber(id){
    if(document.getElementById(id) != null){
        if(document.getElementById(id).value == ''){
            alert('Enter Style Number');
            return false;
        }else if(document.getElementById(id).value.length < 4){
            alert('Incorrect Style Number');
            return false;
        }
        return true;
    }else{
        return true;
    }
}

function valStyleNumbercopy(id,id1){
    if(document.getElementById(id) != null){
        if(document.getElementById(id).value == ''){
            alert('Enter Style Number');
            return false;
        }else if(document.getElementById(id).value.length < 4){
            alert('Incorrect Style Number');
            return false;
        }

         if(document.getElementById(id1).value == ''){
            alert('Enter Copy From (Item No.)');
            return false;
        }else if(document.getElementById(id).value.length < 4){
            alert('Incorrect Item Number');
            return false;
        }

        return true;
    }else{
        return true;
    }
}

function copynormalwastage(a,b)
{
         if(parseFloat(document.getElementById(a).value)<=0)
             {
               document.getElementById(a).value=document.getElementById('normalwastage').value
             }
             if(parseFloat(document.getElementById(b).value)<=0)
                 {
                     document.getElementById(a).value="0.00"

                 }


}




function CopyStyle(styl){

   if(document.getElementById('stylenumberH')){
    document.getElementById('stylenumberH').value=styl;
   }
   if(document.getElementById('styleid2H')){
    document.getElementById('styleid2H').value=styl;
     }
     if(document.getElementById('styleid21H')){
    document.getElementById('styleid21H').value=styl;
     }
     if(document.getElementById('styleid4H')){
        document.getElementById('styleid4H').value=styl;
      }
     
    if(document.getElementById('styleid3H')){
    document.getElementById('styleid3H').value=styl;
    }
     
     
      



}


function calculate(){
   // -(document.getElementById('MarkDown').value)
    var num = (document.getElementById('hidden1').value)*((document.getElementById('fob').value));
    var result = num.toFixed(2);
    document.getElementById('costinr1').value = result;
    
    if(document.getElementById('MarkDown').value!=""){
     var num1 = (document.getElementById('hidden1').value)*((document.getElementById('fob').value- (document.getElementById('fob').value * (document.getElementById('MarkDown').value)/100)));
     var result1 = num1.toFixed(2);
    document.getElementById('Fcostinr1').value = result1;
    }
}

function valStyleSave(){
    var text = document.getElementById('savestyle').innerHTML;
    document.getElementById('savestyle').innerHTML="";
    disableDivs('subStyle');
    if(document.getElementById('0').value==''){
        alert('Enter Destination');
        hideCDivs();
        document.getElementById('savestyle').innerHTML=text;
        return false;
    }
    
    if(getduplicateclr()==true){
    
    if(confirm('Do you want to add colors and generate SKU?')){
        dojo.widget.byId('StyleSaveID').href='StyleItemSKU.action';
        hideCDivs();
        return true;
    }else{
        document.getElementById('savestyle').innerHTML=text;
        hideCDivs();
        return false;
    }
    }else{
         document.getElementById('savestyle').innerHTML=text;
        hideCDivs();
        return false;
    }
}




function valStyleUpd(){
    var text = document.getElementById('updstyle').innerHTML;
    document.getElementById('updstyle').innerHTML="";
    disableDivs('subStyle');
    var buaset=document.getElementById('buaset').value;
     var PCH=document.getElementById('PCH').value;
     buaset=buaset.substring(0,3);
     
    if(buaset.toString()!=PCH.toString())
        {

              alert('You are not authorised to Change!!');
              hideCDivs();
              document.getElementById('updstyle').innerHTML=text;

              return false;

        }else{
     if(getduplicateclr()==true){
    if(confirm('Do you want to add colors and generate SKU?')){
        dojo.widget.byId('StyleSaveID').href='StyleEditItemSKU.action';
        hideCDivs();
        return true;
    }else{
         hideCDivs();
        document.getElementById('updstyle').innerHTML=text;

        return false;
    }}else{
     hideCDivs();
        document.getElementById('updstyle').innerHTML=text;

        return false;
    }
        }

}

function getduplicateclr()
{
    
    
  var colors=document.getElementById('form1').colors;
  
  var sizes=document.getElementById('form1').sizes;
  var destinations=document.getElementById('form1').destinations;
  
  for(i=0; i<colors.length; i++)
      { 
         for(j=i+1; j<colors.length; j++)
      {  
        if(colors[i].value!="" && colors[i].value==colors[j].value)
            {
                alert("Duplicate Entry Found.."+colors[j].value);
                colors[j].focus();
                return false;
            }
          
      }  
          
      }
  
  for(i=0; i<sizes.length; i++)
      {
         for(j=i+1; j<sizes.length; j++)
      {
        if(sizes[i].value!="" && sizes[i].value==sizes[j].value)
            {
                alert("Duplicate Entry Found.."+sizes[j].value);
                sizes[j].focus();
                return false;
            }
          
      }  
          
      }
    for(i=0; i<destinations.length; i++)
      {
         for(j=i+1; j<destinations.length; j++)
      {
        if(destinations[i].value!="" && destinations[i].value==destinations[j].value)
            {
                alert("Duplicate Entry Found.."+destinations[j].value);
                destinations[j].focus();
                return false;
                
            }
          
      }  
          
      }
    return true;
}


function valStyleCreate(url) {
    var text = document.getElementById('createstyle').innerHTML;
    document.getElementById('createstyle').innerHTML="";
    disableDivs('subStyle');



    if(validateStyleCreate() && pchinr1() && validateinputuge()){
        hideCDivs();
        dojo.widget.byId('StyleCreateID').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('createstyle').innerHTML=text;
        return false;
    }


}

function checkcurrflag(aid)
{
   
 var indexval = document.getElementById(aid).value;   
   var indexvalobj=document.getElementById(aid).options[indexval].text;
 

var CloseCurrobj= document.getElementsByName('CloseCurr');   
   
    if(CloseCurrobj.length)
        {
           for(i=0; i<CloseCurrobj.length; i++) 
               {    
                   if(CloseCurrobj[i].value==indexvalobj)
                       {
                          alert("'"+CloseCurrobj[i].value+"' Currency Not in Existence");
                          document.getElementById(aid).focus();
                          return false;
                           break;
                       }
               }
        }else{
            if(CloseCurrobj.value==indexvalobj)
                       {
                           alert("'"+CloseCurrobj.value+"' Currency Not in Existence");
                           document.getElementById(aid).focus();
                           return false;
                          
                       }
        }

return true;
}


function ordcheckcurrflag(aid)
{
   
 var indexval = document.getElementById(aid).value;   
 
var indexvalobj=indexval;
var CloseCurrobj= document.getElementsByName('CloseCurr');   
   
    if(CloseCurrobj.length)
        {
           for(i=0; i<CloseCurrobj.length; i++) 
               {    
                   if(CloseCurrobj[i].value==indexvalobj)
                       {
                          alert("'"+CloseCurrobj[i].value+"' Currency Not in Existence");
                          document.getElementById(aid).focus();
                          return false;
                           break;
                       }
               }
        }else{
            if(CloseCurrobj.value==indexvalobj)
                       {
                           alert("'"+CloseCurrobj.value+"' Currency Not in Existence");
                           document.getElementById(aid).focus();
                           return false;
                          
                       }
        }

return true;
}





function pchinr1()
{
    /*
var index = document.getElementById('currname').selectedIndex;
 var buaset=document.getElementById('buaset').value;
       buaset=buaset.substring(0,3);

    if(document.getElementById('buaset') && (buaset.toString()=='LFE' || buaset.toString()=='LFX' || buaset.toString()=='LSE' || buaset.toString()=='MNE' || buaset.toString()=='KNE' || buaset.toString()=='MNE' || buaset.toString()=='LSX' || buaset.toString()=='MNX'
        || buaset.toString()=='KNX' || buaset.toString()=='MNX' || buaset.toString()=='SMA' ) )
        {
          if(document.getElementById('currname').options[index].text!='INR')
              {
                  alert(" only 'INR' Currency is allowed for selected PCH !!!")
                  return false;
              }
              
              if(document.getElementById('excisest').value=="")
              {
                  alert("Please Select Excise Duty !!!");
                  document.getElementById('excisest').focus();
                  return false;
              }
        
    
}else{

            //if(document.getElementById('currname').options[index].text=='INR')
             // {
                 // alert("'INR' Currency is not allowed for selected PCH !!!")
                  //return false;
             // }
               if(document.getElementById('excisest').value!="")
              {
                  alert("Excise Duty Not Allowed ");
                  document.getElementById('excisest').focus();
                  return false;
              }
        }

*/

var buaset=document.getElementById('buaset').value;
buaset=buaset.substring(0,3);
if(document.getElementById('buaset') && buaset.toString()=='LSD')
    {
        if(document.getElementById('appuserpd').value=="0")
        {
            alert("Select Pd Merchant.");
            return false;
        }
        
         if(document.getElementById('appuserpm').value=="0")
        {
            alert("Select Production Merchant.");
            return false;
        }
        
        
    }


    return true;

}



function valStyleCreatecopy(url) {
    var text = document.getElementById('createstylecopy').innerHTML;
    document.getElementById('createstylecopy').innerHTML="";
    disableDivs('subStyle');
    if(validateStyleCreate() &&  pchinr1()){
        hideCDivs();
        dojo.widget.byId('StyleCreateIDcopy').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('createstylecopy').innerHTML=text;
        return false;
    }
}

function valStyleUpdate(url) {
    var text = document.getElementById('createstyle').innerHTML;
    document.getElementById('createstyle').innerHTML="";
    disableDivs('subStyle');
    if(validateStyleUpdate() && pchinr2() && validateinputuge() ){
        hideCDivs();

        dojo.widget.byId('StyleCreateID').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('createstyle').innerHTML=text;
        return false;
    }
}



function pchinr2()
{
    /*
var index = document.getElementById('currID').selectedIndex;
 var buaset=document.getElementById('buaset').value;
       buaset=buaset.substring(0,3);

    if(document.getElementById('buaset') && (buaset.toString()=='LFE' || buaset.toString()=='LFX' || buaset.toString()=='LSE' || buaset.toString()=='MNE' || buaset.toString()=='KNE' || buaset.toString()=='MNE' || buaset.toString()=='LSX' || buaset.toString()=='MNX'
        || buaset.toString()=='KNX' || buaset.toString()=='MNX' || buaset.toString()=='SMA' ) )
        {
          if(document.getElementById('currID').options[index].text!='INR')
              {
                  alert(" only 'INR' Currency is allowed for selected PCH !!!")
                  return false;
              }
              
              if(document.getElementById('excisest').value=="")
              {
                  alert("Please Select Excise Duty !!!");
                  document.getElementById('excisest').focus();
                  return false;
              }
        }else{

            if(document.getElementById('currID').options[index].text=='INR')
              {
                  alert("'INR' Currency is not allowed for selected PCH !!!")
                  return false;
              }
              
              if(document.getElementById('excisest').value!="")
              {
                  alert("Excise Duty Not Allowed");
                  document.getElementById('excisest').focus();
                  return false;
              }
        }
*/

var buaset=document.getElementById('buaset').value;
buaset=buaset.substring(0,3);
if(document.getElementById('buaset') && buaset.toString()=='LSD')
    {
        if(document.getElementById('appuserpd').value=="0")
        {
            alert("Select Pd Merchant.");
            return false;
        }
        
         if(document.getElementById('appuserpm').value=="0")
        {
            alert("Select Production Merchant.");
            return false;
        }
        
        
    }

    return true;

}


function ValLinkst(id,url,aid,styleid) {

    var text = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML="";
    disableDivs('mainContainer');
    if(aid=='StyleNewID' || aid =='RefreshDataID'|| aid =='UpdateDataID'){

        dojo.widget.byId(aid).href=url;
        hideCDivs();
        return true;
    }

}






function ValLink(id,url,aid,styleid) {

   if(settingValidation()==true && forcastfind() )
{
    var text = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML="";
    disableDivs('mainContainer');
    if(aid=='StyleNewID' || aid =='RefreshDataID'|| aid =='UpdateDataID'){

        dojo.widget.byId(aid).href=url;
        hideCDivs();
        return true;
    }
    else if(valStyleNumber(styleid)){
       
           if(aid =='StyleSKUID' ||aid =='updSKUFabric' ||aid =='addSKUFabric' ||aid =='createsubmit' || aid =='UpdateLineID' || aid =='DelOrderID'
               || aid =='DelLinesID' || aid =='saveCompOp' || aid =='DelLinesIDclosed' )
           {
              if((aid =='updSKUFabric' || aid=='addSKUFabric') && !getduplicatefab()) 
                  {
                     hideCDivs();
                    document.getElementById(id).innerHTML=text;
                    return false; 
                  }
               
               
       var PCH="";
         var buaset=document.getElementById('buaset').value;
       if(aid =='StyleSKUID' && document.getElementById('PCH')){
        PCH=document.getElementById('PCH').value;
       }
        if(document.getElementById('PCHM') && (aid =='updSKUFabric'  || aid =='addSKUFabric' || aid =='ViewFab1' || aid =='createsubmit')){
         PCH=document.getElementById('PCHM').value;
       }

        if(document.getElementById('PCHO') && (aid =='UpdateLineID' || aid =='DelOrderID' || aid =='DelLinesID' || aid =='DelLinesIDclosed')){
         PCH=document.getElementById('PCHO').value;

       }
       if(document.getElementById('PCHP') && (aid =='saveCompOp')){
         PCH=document.getElementById('PCHP').value;

       }



        buaset=buaset.substring(0,3);

       if( PCH!="" && buaset.toString()!=PCH.toString())
        {

              alert('Your PCH and selected item PCH are not same,so you are not authorised for this option!!');
              hideCDivs();

              document.getElementById(id).innerHTML=text;

              return true;

        }else{
         if(aid =='DelOrderID' || aid =='DelLinesID' || aid =='DelLinesIDclosed'){
                        if(aid =='DelLinesIDclosed')
                            {
                                var st='Do You Want To Close Order Line(s),Bcoz Once Closed Order Line(s) Cannot be Reinstated...';
                            }else{
                                var st='Do You Want To Delete Record(s)?';
                            }
                        if(confirm(st)){
                            dojo.widget.byId(aid).href=url;
                            hideCDivs();
                            return true;
                        }else{
                            hideCDivs();
                            document.getElementById(id).innerHTML=text;
                            return false;

                        }
                        }else{
                        dojo.widget.byId(aid).href=url;
                        hideCDivs();
                        return true;

                    }


}

        }else{
            
          
        dojo.widget.byId(aid).href=url;
        hideCDivs();
        return true;
        }
    }else{

        hideCDivs();
        document.getElementById(id).innerHTML=text;
        return false;
    }

}else{

     var tabContainer = dojo.widget.byId("mainContainer");
     tabContainer.selectTab('subSettings');


}
}

function getduplicatefab()
{
    if(document.getElementById('formmat1')){
    var flg=0;
    var flg1=0;
  var colors=document.getElementById('formmat1').colors;
  var sizes=document.getElementById('formmat1').sizes;
 for(i=0; i<colors.length; i++)
      {
         if(colors[i].value!="" ) 
             {
                 flg=1;
             }
          
      }
      
    for(i=0; i<sizes.length; i++)
      {
         if(sizes[i].value!="" ) 
             {
                 flg1=1;
             } 
          
      }  
      
    if(flg==0) 
    {
               alert("Please Enter Color");
                colors[0].focus();
                return false;    
        
    }
    
     if(flg1==0) 
    {
               alert("Please Enter Width");
                sizes[0].focus();
                return false;    
        
    }
  
  for(i=0; i<colors.length; i++)
      { 
         for(j=i+1; j<colors.length; j++)
      {  
        if(colors[i].value!="" && (colors[i].value).toUpperCase()==(colors[j].value).toUpperCase())
            {
                alert("Duplicate Entry Found.."+colors[j].value);
                colors[j].focus();
                return false;
            }
          
      }  
          
      }
  
  for(i=0; i<sizes.length; i++)
      {
         for(j=i+1; j<sizes.length; j++)
      {
        if(sizes[i].value!="" && sizes[i].value==sizes[j].value)
            {
                alert("Duplicate Entry Found.."+sizes[j].value);
                sizes[j].focus();
                return false;
            }
          
      }  
          
      }
    }
    
    
    if(document.getElementById('formtrim1')){
    
  var colors=document.getElementById('formtrim1').colors;
  var sizes=document.getElementById('formtrim1').sizes;
   var destinations=document.getElementById('formtrim1').destinations;
 
  
  for(i=0; i<colors.length; i++)
      { 
         for(j=i+1; j<colors.length; j++)
      {  
        if(colors[i].value!="" && (colors[i].value).toUpperCase()==(colors[j].value).toUpperCase())
            {
                alert("Duplicate Entry Found.."+colors[j].value);
                colors[j].focus();
                return false;
            }
          
      }  
          
      }
   
  for(i=0; i<sizes.length; i++)
      {
         for(j=i+1; j<sizes.length; j++)
      {
        if(sizes[i].value!="" && sizes[i].value==sizes[j].value)
            {
                alert("Duplicate Entry Found.."+sizes[j].value);
                sizes[j].focus();
                return false;
            }
          
      }  
          
      }
      if(destinations.length)
          {
       for(i=0; i<destinations.length; i++)
      {
         for(j=i+1; j<destinations.length; j++)
      {
        if(destinations[i].value!="" && destinations[i].value==destinations[j].value)
            {
                alert("Duplicate Entry Found.."+destinations[j].value);
                destinations[j].focus();
                return false;
            }
          
      }  
          
      }}
    }
    
    
    
    
    
    return true;
}




function ValLinkCopy(id,url,aid,styleid,styleid1) {

   if(settingValidation()==true)
{
    var text = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML="";
    disableDivs('mainContainer');

    if(valStyleNumbercopy(styleid,styleid1))
        {
        dojo.widget.byId(aid).href=url;
        hideCDivs();
        return true;
    }else{

        hideCDivs();
        document.getElementById(id).innerHTML=text;
        return false;
        }

}else{

     var tabContainer = dojo.widget.byId("mainContainer");
     tabContainer.selectTab('subSettings');


}
}




function ValOrderLink(id,url,aid,styleid) {
    var text = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML="";
    disableDivs('subCOntainer');
    if(validateOrder()){
        hideCDivs();
        dojo.widget.byId(aid).href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById(id).innerHTML=text;
        return false;
    }
}

function valOrderCreate(url) {
    var text = document.getElementById('createorder').innerHTML;
    document.getElementById('createorder').innerHTML="";
    disableDivs('subCOntainer');

     if(!validatePCHO() || !pchinr())
        {
               hideCDivs();
               document.getElementById('createorder').innerHTML=text;
               return false;
        }

    if(validateOrderCreate() && forcastfind()){
        hideCDivs();
        dojo.widget.byId('OrderCreateID').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('createorder').innerHTML=text;
        return false;
    }
}


function valOrderCreateM(url) {
    var text = document.getElementById('Matcreateorder').innerHTML;
    document.getElementById('Matcreateorder').innerHTML="";
    disableDivs('MatsubCOntainer');

     if(!validatePCHOM() || !pchinr())
        {
               hideCDivs();
               document.getElementById('Matcreateorder').innerHTML=text;
               return false;
        }

    if(validateOrderCreateM() && forcastfind()){
        hideCDivs();
        dojo.widget.byId('MatOrderCreateID').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('Matcreateorder').innerHTML=text;
        return false;
    }
}

function valOrderCreateMM(url) {
    var text = document.getElementById('Matcreateorder').innerHTML;
    document.getElementById('Matcreateorder').innerHTML="";
    disableDivs('MatsubCOntainer');

     if(!validatePCHOM() || !pchinr())
        {
               hideCDivs();
               document.getElementById('Matcreateorder').innerHTML=text;
               return false;
        }


    if(validateOrderCreateNewMat() && forcastfind()){
        hideCDivs();
        dojo.widget.byId('MatOrderCreateID').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('Matcreateorder').innerHTML=text;
        return false;
    }
}



function valOrderCreateNew1(url) {
   
    var text = document.getElementById('updlinesNew').innerHTML;
    document.getElementById('updlinesNew').innerHTML="";
    disableDivs('subCOntainer');

     if(!validatePCHO())
        {
               hideCDivs();
               document.getElementById('updlinesNew').innerHTML=text;
               return false;
        }

    if(validateOrderCreateNewItem()){
        hideCDivs();
        dojo.widget.byId('UpdateLineIDNew').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('updlinesNew').innerHTML=text;
        return false;
    }
}



function valOrderCreateNew(url) {
   
    var text = document.getElementById('updlinesNew').innerHTML;
    document.getElementById('updlinesNew').innerHTML="";
    disableDivs('subCOntainer');

     if(!validatePCHO() || !pchinr())
        {
               hideCDivs();
               document.getElementById('updlinesNew').innerHTML=text;
               return false;
        }

    if(validateOrderCreateNewItem()){
        hideCDivs();
        dojo.widget.byId('UpdateLineIDNew').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('updlinesNew').innerHTML=text;
        return false;
    }
}


function valOrderCreateNewMat(url) {
    var text = document.getElementById('MatupdlinesNew').innerHTML;
    document.getElementById('MatupdlinesNew').innerHTML="";
    disableDivs('MatsubCOntainer');

     if(!validatePCHOM())
        {
               hideCDivs();
               document.getElementById('MatupdlinesNew').innerHTML=text;
               return false;
        }

    if(validateOrderCreateNew()){
        hideCDivs();
        dojo.widget.byId('MatUpdateLineIDNew').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('MatupdlinesNew').innerHTML=text;
        return false;
    }
}



function pchinr()
{
/*
    if(document.getElementById('ctp') && (document.getElementById('ctp').value=='LFX' || document.getElementById('ctp').value=='LFE' || document.getElementById('ctp').value=='LSE' || document.getElementById('ctp').value=='MNE' || document.getElementById('ctp').value=='KNE' || document.getElementById('ctp').value=='MNE' || document.getElementById('ctp').value=='LSX' || document.getElementById('ctp').value=='MNX'
        || document.getElementById('ctp').value=='KNX' || document.getElementById('ctp').value=='MNX' || document.getElementById('ctp').value=='SMA'  ) )
        {
          if(document.getElementById('cur').value!='INR')
              {
                  alert("only 'INR' Currency is allowed for selected order type !!!")
                  return false;
              }
        }else{

            if(document.getElementById('cur').value=='INR')
              {
                 alert("'INR' Currency is not allowed for selected order type !!!")
                  return false;
              }
        }

*/
    return true;

}

function forcastfind()
{

    if(document.getElementById('forecast') && document.getElementById('forecast1') && document.getElementById('forecast').value!=0 && document.getElementById('forecast').value==document.getElementById('forecast1').value)
        {
          alert("Both Forecast Nos Can't be Same !!! ");
           return false;
        }
   return true;
}


function valAddOperation(url) {
    var text = document.getElementById('AddOperationLabel').innerHTML;
    document.getElementById('AddOperationLabel').innerHTML="";
    disableDivs('subOperation');
    if(ValidateAddOperation()){
        hideCDivs();
        dojo.widget.byId('AddOperation').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('AddOperationLabel').innerHTML=text;
        return false;
    }
}

function valMetMatCreate(url) {
    var text = document.getElementById('createmat').innerHTML;
    document.getElementById('createmat').innerHTML="";
    disableDivs('subMaterial');
    if(!validatePCHM())
        {
               hideCDivs();
               document.getElementById('createsub').innerHTML=text;
               return false;
        }
    if(valMetMatrixCreate()){
        hideCDivs();
        dojo.widget.byId('createsubmit').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('createmat').innerHTML=text;
        return false;
    }
}
function valMetSubsCreate(url) {
    var text = document.getElementById('createsub').innerHTML;
    document.getElementById('createsub').innerHTML="";
    disableDivs('subMaterial');
     if(!validatePCHM())
        {
               hideCDivs();
               document.getElementById('createsub').innerHTML=text;
               return false;
        }
    if(valMetSubsitutionCreate()){
        hideCDivs();
        dojo.widget.byId('createsubmit').href=url;
        return true;
    }else{
        hideCDivs();
        document.getElementById('createsub').innerHTML=text;
        return false;
    }
}
function valFabricCreate(url) {
    var text = document.getElementById('createfab').innerHTML;
    document.getElementById('createfab').innerHTML="";
    disableDivs('subMaterial');
    if(!validatePCHM())
        {
               hideCDivs();
               document.getElementById('createfab').innerHTML=text;
              return false;
        }

    if(validateFabricCreate(url)){
        
        hideCDivs();
        return true;
    }else{
        hideCDivs();
        document.getElementById('createfab').innerHTML=text;
        return false;
    }
}

function valFabricCreate2(url) {
    var text = document.getElementById('createfab').innerHTML;
    document.getElementById('createfab').innerHTML="";
    disableDivs('subMaterial');
     if(!validatePCHM()){
         hideCDivs();
        document.getElementById('createfab').innerHTML=text;
        return false;
     }

    if(validateFabricCreate2(url)){
        hideCDivs();
        return true;
    }else{
        hideCDivs();
        document.getElementById('createfab').innerHTML=text;
        return false;
    }
}

function copyBom(url) {
    var text = document.getElementById('createfab').innerHTML;
    document.getElementById('createfab').innerHTML="";
    disableDivs('subMaterial');
     if(!validatePCHM()){
         hideCDivs();
        document.getElementById('createfab').innerHTML=text;
        return false;
     }
    dojo.widget.byId('createsubmit').href=url;
    hideCDivs();
    return true;
}

function stycopyBom(url) {
    var text = document.getElementById('stcreatefab').innerHTML;
    document.getElementById('stcreatefab').innerHTML="";
    disableDivs('subStyle');
     if(!validatePCHM()){
         hideCDivs();
        document.getElementById('stcreatefab').innerHTML=text;
        return false;
     }
    dojo.widget.byId('stcreatesubmit').href=url;
    hideCDivs();
    return true;
}

function valTrimCreate(url) {
    var text = document.getElementById('createfab').innerHTML;
    document.getElementById('createfab').innerHTML="";
    disableDivs('subMaterial');

     if(!validatePCHM())
        {
               hideCDivs();
               document.getElementById('createfab').innerHTML=text;
              return false;
        }
    if(validateTrimCreate(url)){
        hideCDivs();
        return true;
    }else{
        hideCDivs();
        document.getElementById('createfab').innerHTML=text;
        return false;
    }
}

function valTrimCreate2(url) {
    var text = document.getElementById('createfab').innerHTML;
    document.getElementById('createfab').innerHTML="";
    disableDivs('subMaterial');
if(!validatePCHM())
        {
               hideCDivs();
               document.getElementById('createfab').innerHTML=text;
              return false;
        }
    if(validateTrimCreate2(url)){
        hideCDivs();
        return true;
    }else{
        hideCDivs();
        document.getElementById('createfab').innerHTML=text;
        return false;
    }
}

function valMetMatUpdate() {
    disableDivs('subMaterial');
    if(valMetMatrixUpdate()){
        dojo.widget.byId('createsubmit').href='UpdMercMatrix.action';
        hideCDivs();
        return true;
    }else{
        hideCDivs();
        return false;
    }
}

function valMetMatrixCreate(){

    if(document.getElementById('matdesc').value==''){
        alert('Enter Description');return false;
    }
    if(document.getElementById('mattype').value=='0'){
        alert('Enter Type');return false;
    }
    if(document.getElementById('feature').value=='0'){
        alert('Please Select Garment Feature');return false;
    }
    return true;
}

function valMetSubsitutionCreate(){
    var ovjtemp=document.getElementsByName("temmfabitm");
   
    flag=0;
    if(ovjtemp && ovjtemp.length>0)
        {
            for(i=0; i<ovjtemp.length; i++)
                {
                    
                 var ovjtemp1= document.getElementById(ovjtemp[i].value).value;
                
                 if(ovjtemp1!="0")
                     {
                         flag=1;
                     }
                }
        }else{
            if(ovjtemp.value!="0")
            {
                         flag=1;
            }
        }
  
    if(flag==0)
        {
           alert("Please Select Options") 
           return false;
        }
    return true;
}


function valMetMatrixUpdate(){
    if(document.getElementById('matdesc').value=='0'){
        alert('Enter Description');return false;
    }
    if(document.getElementById('mattype').value=='0'){
        alert('Enter Type');return false;
    }
    if(document.getElementById('feature').value=='0'){
        alert('Please Select Garment Feature');return false;
    }
    return true;
}

function valFetchData(){
    disableDivs('subMaterial');
    if(document.getElementById('mattype').value=='0'){
        alert('Enter Type');
        hideCDivs();
        return false;
    }
    if(document.getElementById('feature').value=='0'){
        alert('Please Select Garment Feature');
        hideCDivs();
        return false;
    }
    dojo.widget.byId('MaterialItemID').href='FetchData.action';
    hideCDivs();
    return true;
}

function valFetchSubsData(){
    disableDivs('subMaterial');
    if(document.getElementById('feature').value=='0'){
        alert('Please Select Garment Feature');
        hideCDivs();
        return false;
    }
    if(document.getElementById('fabfeature').value=='0'){
        alert('Please Select Fabric Feature');
        hideCDivs();
        return false;
    }
    dojo.widget.byId('MaterialItemID').href='FetchSubsData.action';
    hideCDivs();
    return true;
}



function validateStyleCreate(){

    if(document.getElementById('desc').value==''){
        alert('Enter Name');
        document.getElementById('desc').focus();
        return false;
    }
    
    if(document.getElementById('descnew').value==''){
        alert('Enter Desc');return false;
    }
    if(document.getElementById('catagorynameID').value=='0'){
        alert('Please Select Catagory');return false;
    }
    if(document.getElementById('productnameID').value=='0'){
      //  alert('Please Select Product Group');return false;
    }
    if(document.getElementById('license').value=='0'){
        alert('Please Select License');return false;
    }
    if(document.getElementById('resp').value=='0'){
        alert('Please Select Resp');return false;
    }
    
    if(document.getElementById('customGrp').value=='0'){
        alert('Please Select Customs Grp');return false;
    }
    
    
    
    if(document.getElementById('itemGrp').value=='0'){
        alert('Please Select Item Grp');return false;
    }
    if(document.getElementById('currname').value=='0'){
        alert('Please Select Currency');return false;
    }
    
  
    if(!checkcurrflag('currname'))
        {
            return false;
        }
    
   
    if(document.getElementById('fob').value=='0')
        {
           alert('FOB/Sales Price should be > 0');return false;
        }
    if(!/^\d+.?\d*$/.test(document.getElementById('fob').value)){
        alert('Please Enter Decimal Numeric Format for FOB/Sales Price');return false;
    }
   
  /*  if(document.getElementById('FirstSale').checked){
    
    if(document.getElementById('MarkDown').value=='')
        {
           alert('Mark Down Must be Enter');return false;
        }
    if(!/^\d+.?\d*$/.test(document.getElementById('MarkDown').value)){
        alert('Please Enter Decimal Numeric Format for Mark Down');return false;
    }
    }
    */
    
    if(!/^\d+.?\d*$/.test(document.getElementById('wapc').value)){
        alert('Please Enter Numeric Format for Normal Wastage');return false;
    }
   
     if(document.getElementById('appuser').value==''){
        alert('Please Select Approver');return false;
    }
    
 
   /* if(document.getElementById('buaset')){
      var buaset1=document.getElementById('buaset').value;
       buaset1=buaset1.substring(0,3);
       if(buaset1.toString()=='MNB' || buaset1.toString()=='KNT' || buaset1.toString()=='MND'){
     if(document.getElementById('appuserfab').value=='0'){
      alert('Please Select Fabric Responsible');return false;
    }}
    }
    */
  
    if(document.getElementById('styleBasicUOM').value=='0'){
        //alert('Please Select Basic Unit of Measurement');return false;
    }
 
    if(!/^\d+.?\d*$/.test(document.getElementById('conversion').value)){
        alert('Please Enter Numeric Format for Normal Conversion');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('styleadjf').value)){
        alert('Please Enter Numeric Format for Adj Fac');return false;
    }
   
    
    ORDCOMDATEobj=dojo.widget.byId('ORDCOMDATE')
     if(ORDCOMDATEobj.getValue()=="")
         {
            // alert("Please Enter Order Confirmation Date");  
            
         }
         
         PCDDATEobj=dojo.widget.byId('PCDDATE')
     if(PCDDATEobj.getValue()=="")
         {
            // alert("Please Enter PCD Date");  
            
         }
    //alert(PCDDATEobj.getValue());
    //alert(ORDCOMDATEobj.getValue());
    
   
     if(PCDDATEobj.getValue()!="" && ORDCOMDATEobj.getValue()=="")
        {
              alert("Please Enter Order Confirmation Date");  
              return false;
            
        }
       
    if(PCDDATEobj.getValue()!="" && ORDCOMDATEobj.getValue()!="")
        {
            
              var PCDDATE=PCDDATEobj.getValue().replace("-", "");
                  PCDDATE=PCDDATE.replace("-", "");
                  PCDDATE = PCDDATE.substring(0, 8);
                  
              var ORDCOMDATE=ORDCOMDATEobj.getValue().replace("-", "");
                  ORDCOMDATE=ORDCOMDATE.replace("-", "");
                  ORDCOMDATE = ORDCOMDATE.substring(0, 8);  
                 
                  
                  if(parseInt(PCDDATE) < parseInt(ORDCOMDATE))
                      {
                          
                        alert("PCD can not be a date prior to Order Confirmation Date");  
                        return false
                          
                      }
                
            
            
        }
        
   
    return true;
}
function validateStyleUpdate(){

     var buaset=document.getElementById('buaset').value;
     var PCH=document.getElementById('PCH').value;
     buaset=buaset.substring(0,3);
    if(buaset.toString()!=PCH.toString())
        {

               alert('Your PCH and selected item PCH are not same,so you are not authorised for this option!!');

              return false;

        }

    if(document.getElementById('desc').value==''){
        alert('Enter Desc');return false;
    }
    if(document.getElementById('productnameid').value=='0'){
       // alert('Please Select Product Group');return false;
    }
    if(document.getElementById('licenseID').value=='0'){
        alert('Please Select License');return false;
    }
    if(document.getElementById('respid').value=='0'){
        alert('Please Select Resp');return false;
    }
    if(document.getElementById('customGrp').value=='0'){
        alert('Please Select Customs Grp');return false;
    }
    if(document.getElementById('appuser').value==''){
        alert('Please Select Approver');return false;
    }
    /* if(document.getElementById('PCH') && (document.getElementById('PCH').value=='MNB' || document.getElementById('PCH').value=='KNT' || document.getElementById('PCH').value=='MND')){
       if(document.getElementById('appuserfab').value=='0'){
        alert('Please Select Fabric Responsible');return false;
    }
     }*/
    if(document.getElementById('itemgrpid').value=='0'){
        alert('Please Select Item Grp');return false;
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency');return false;
        
    }
    if(!checkcurrflag('currID'))
        {
            return false;
        }
    
   
    
    if(!/^\d+.?\d*$/.test(document.getElementById('fob').value)){
        alert('Enter Decimal Numeric Format for FOB/Sales Price');return false;
    }
    
  /*  if(document.getElementById('FirstSale').checked){
    
    if(document.getElementById('MarkDown').value=='')
        {
           alert('Mark Down Must be Enter');return false;
        }
    if(!/^\d+.?\d*$/.test(document.getElementById('MarkDown').value)){
        alert('Please Enter Decimal Numeric Format for Mark Down');return false;
    }
    }
    */
  /*  if(!/^\d+.?\d*$/.test(document.getElementById('wapc').value)){
        alert('Please Enter Numeric Format for Normal Wastage');return false;
    }*/
    if(document.getElementById('styleBasicUOM').value=='0'){
       // alert('Please Select Basic Unit of Measurement');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('conversion').value)){
        alert('Please Enter Numeric Format for Normal Conversion');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('styleadjf').value)){
        alert('Please Enter Numeric Format for Adj Fac');return false;
    }
    
     var ORDCOMDATEvalue=0;
      var PCDDATEvalue=0;
    if(document.getElementById('ORDCOMDATEH'))
        {
           
           ORDCOMDATEobj=document.getElementById('ORDCOMDATEH') ;
           ORDCOMDATEvalue=ORDCOMDATEobj.value;
        }else{
            
            ORDCOMDATEobj=dojo.widget.byId('ORDCOMDATE');
            ORDCOMDATEvalue=ORDCOMDATEobj.getValue();
            
        }
        
        if(document.getElementById('PCDDATEH'))
        {
           PCDDATETEMPobj=document.getElementById('PCDDATEH') ;
           PCDDATEvalue=PCDDATETEMPobj.value;
        }else{
            
            PCDDATETEMPobj=dojo.widget.byId('PCDDATE');
            PCDDATEvalue=PCDDATETEMPobj.getValue();
        } 
        
   
   
   
   
     if(PCDDATEvalue!="" && ORDCOMDATEvalue=="")
        {
              alert("Please Enter Order Confirmation Date");  
              return false;
            
        }
       
       
    if(ORDCOMDATEvalue!="" && PCDDATEvalue!="")
        {
            
              var PCDDATE=PCDDATEvalue.replace("-", "");
                  PCDDATE=PCDDATE.replace("-", "");
                  PCDDATE = PCDDATE.substring(0, 8);
                  
              var ORDCOMDATE=ORDCOMDATEvalue.replace("-", "");
                  ORDCOMDATE=ORDCOMDATE.replace("-", "");
                  ORDCOMDATE = ORDCOMDATE.substring(0, 8);  
                 
                  
                  if(parseInt(PCDDATE) < parseInt(ORDCOMDATE))
                      {
                          
                        alert("PCD can not be a date prior to Order Confirmation Date");  
                        return false
                          
                      }
                
            
            
        }
    
    
    
    return true;
}


function validateOrderCreateNew(){
   
    if(document.getElementById('itemnon').value=='00'){
        alert('Please Select Item No'); 
        document.getElementById('itemnon').focus();
        return false;
    }
    if(document.getElementById('orqtn').value=='' || document.getElementById('orqtn').value=='0' ){
        alert('Please Enter Qty'); 
        document.getElementById('orqtn').focus();
        return false;
    }else{
        if(!/^\d+.?\d*$/.test(document.getElementById('orqtn').value)){
        alert('Enter Decimal Numeric Format for Qty');
         document.getElementById('orqtn').focus();
        return false;
    }
    }
    if(document.getElementById('saprn').value=="" || document.getElementById('saprn').value=="0")
        {alert('Please Enter  Sales Price'); 
            document.getElementById('saprn').focus();
            return false;
        }else{
     
    if(!/^\d+.?\d*$/.test(document.getElementById('saprn').value)){
        alert('Enter Decimal Numeric Format for FOB/Sales Price');
        document.getElementById('saprn').focus();
        return false;
    }
        }
    
     if(document.getElementById('discn').value=="" || document.getElementById('discn').value=="0"){}else{
    if(!/^\d+.?\d*$/.test(document.getElementById('discn').value)){
        alert('Enter Decimal Numeric Format Discount');
        document.getElementById('discn').focus();
        return false;
    }
}
  
     a=dojo.widget.byId('delydtn')
     if(a.getValue()=="")
         {
             alert("please Enter Delivery Date");  
              return false;
         }
   

    return true;

}



function validateOrderCreateNewItem(){
  
    if(document.getElementById('itemnonI').value=='00'){
        alert('Please Select Item No'); 
        document.getElementById('itemnonI').focus();
        return false;
    }
    if(document.getElementById('orqtnI').value=='' || document.getElementById('orqtnI').value=='0' ){
        alert('Please Enter Qty'); 
        document.getElementById('orqtnI').focus();
        return false;
    }else{
        if(!/^\d+.?\d*$/.test(document.getElementById('orqtnI').value)){
        alert('Enter Decimal Numeric Format for Qty');
         document.getElementById('orqtnI').focus();
        return false;
    }
    }
    if(document.getElementById('saprnI').value=="" || document.getElementById('saprnI').value=="0")
        {alert('Please Enter  Sales Price'); 
            document.getElementById('saprnI').focus();
            return false;
        }else{
     
    if(!/^\d+.?\d*$/.test(document.getElementById('saprnI').value)){
        alert('Enter Decimal Numeric Format for FOB/Sales Price');
        document.getElementById('saprnI').focus();
        return false;
    }
        }
    
     if(document.getElementById('discnI').value=="" || document.getElementById('discnI').value=="0"){}else{
    if(!/^\d+.?\d*$/.test(document.getElementById('discnI').value)){
        alert('Enter Decimal Numeric Format Discount');
        document.getElementById('discnI').focus();
        return false;
    }
}
  
     a=dojo.widget.byId('delydtnI')
     if(a.getValue()=="")
         {
             alert("please Enter Delivery Date");  
              return false;
         }
   

    return true;

}





function validateOrderCreateNewMat(){
   
    if(document.getElementById('itemnon').value=='00'){
        alert('Please Select Item No'); 
        document.getElementById('itemnon').focus();
        return false;
    }
    if(document.getElementById('orqtn').value=='' || document.getElementById('orqtn').value=='0' ){
        alert('Please Enter Qty'); 
        document.getElementById('orqtn').focus();
        return false;
    }else{
        if(!/^\d+.?\d*$/.test(document.getElementById('orqtn').value)){
        alert('Enter Decimal Numeric Format for Qty');
         document.getElementById('orqtn').focus();
        return false;
    }
    }
    if(document.getElementById('saprn').value=="" || document.getElementById('saprn').value=="0")
        {alert('Please Enter  Sales Price'); 
            document.getElementById('saprn').focus();
            return false;
        }else{
     
    if(!/^\d+.?\d*$/.test(document.getElementById('saprn').value)){
        alert('Enter Decimal Numeric Format for FOB/Sales Price');
        document.getElementById('saprn').focus();
        return false;
    }
        }
    
     if(document.getElementById('sqtyn').value=="" || document.getElementById('sqtyn').value=="0"){}else{
    if(!/^\d+.?\d*$/.test(document.getElementById('sqtyn').value)){
        alert('Enter Decimal Numeric Format Sale Qty');
        document.getElementById('sqtyn').focus();
        return false;
    }
}
    
     if(document.getElementById('discn').value=="" || document.getElementById('discn').value=="0"){
     }else{
     
    if(!/^\d+.?\d*$/.test(document.getElementById('discn').value)){
        alert('Enter Decimal Numeric Format Discount');
        document.getElementById('discn').focus();
        return false;
    }
}
  
     
   

    return true;

}


function validateOrderCreateM(){

    if(document.getElementById('ctp').value=='0'){
        alert('Please Select Order Type');return false;
    }
    if(document.getElementById('whs').value=='0'){
        alert('Please Select Warehouse');return false;
    }
    if(document.getElementById('faci').value=='0'){
        alert('Please Select Facility');return false;
    }
    if(document.getElementById('pmt').value=='0'){
        alert('Please Select Payment Method');return false;
    }
    if(document.getElementById('ptr').value=='0'){
        alert('Please Select Payment Term');return false;
    }
    if(document.getElementById('dmt').value=='0'){
        alert('Please Select Delivery Method');return false;
    }
    if(document.getElementById('dtr').value=='0'){
        alert('Please Select Delivery Terms');return false;
    }
    if(document.getElementById('cur').value=='0'){
        alert('Please Select Currency');return false;
    }
    
    if(!ordcheckcurrflag('cur'))
        {
            return false;
        }
    
    if(document.getElementById('byd').value=='0'){
        alert('Enter Buyer Address');return false;
    }
    
    
  
    return true;

}






function validateOrderCreate(){

    if(document.getElementById('ctp').value=='0'){
        alert('Please Select Order Type');return false;
    }
   
   if(document.getElementById('whs').value=='0'){
        alert('Please Select Warehouse');return false;
    }
    
   var exdateobj= dojo.widget.byId('EXFACTORYDATE');
     if(exdateobj.getValue()=="")
       {
           alert('Please Enter Planned Ex-Factory Date');
           return false; 
        }
    
      var ORDCOMDATEvalue=exdateobj.getValue();
      var PCDDATEvalue=document.getElementById('PCDDATEH').value;
    if(ORDCOMDATEvalue!="" && PCDDATEvalue!="")
        {
            
              var PCDDATE=PCDDATEvalue.replace("-", "");
                  PCDDATE=PCDDATE.replace("-", "");
                  PCDDATE = PCDDATE.substring(0, 8);
                  
              var ORDCOMDATE=ORDCOMDATEvalue.replace("-", "");
                  ORDCOMDATE=ORDCOMDATE.replace("-", "");
                  ORDCOMDATE = ORDCOMDATE.substring(0, 8);  
                 
                  
                  if(parseInt(PCDDATE) > parseInt(ORDCOMDATE))
                      {
                          
                        alert("Planned Ex-factory Date can not be a date prior to PCD");  
                        return false
                          
                      }
                
            
            
        }
    
    if(document.getElementById('faci').value=='0'){
        alert('Please Select Facility');return false;
    }
    if(document.getElementById('pmt').value=='0'){
        alert('Please Select Payment Method');return false;
    }
    if(document.getElementById('ptr').value=='0'){
        alert('Please Select Payment Term');return false;
    }
    if(document.getElementById('dmt').value=='0'){
        alert('Please Select Delivery Method');return false;
    }
    if(document.getElementById('dtr').value=='0'){
        alert('Please Select Delivery Terms');return false;
    }
    if(document.getElementById('cur').value=='0'){
        alert('Please Select Currency');return false;
    }
    
    if(!ordcheckcurrflag('cur'))
        {
            return false;
        }
    
     if(document.getElementById('bpo').value==''){
        alert('Please Enter Buyer PO');
        document.getElementById('bpo').value.focus();
        return false;
    }
     if(document.getElementById('bus').value==''){
        alert('Please Enter Buyer Style');
         document.getElementById('bus').value.focus();
        return false;
    }
    
    if(document.getElementById('byd').value=='0'){
        alert('Enter Buyer Address');return false;
    }
    if(document.getElementById('price').value=="")
        {}else{
    if(!/^\d+.?\d*$/.test(document.getElementById('price').value)){
        alert('Enter Decimal Numeric Format for FOB/Sales Price');return false;
    }
        }
    if(!/^\d+.?\d*$/.test(document.getElementById('disc').value)){
        alert('Enter Decimal Numeric Format Discount');return false;
    }
    return true;

}

function ValidateAddOperation(){
    if(document.getElementById('styleid3').value==''){
        alert('Enter Style Number');return false;
    }
    if(document.getElementById('OpNo').value==''){
        alert('Enter Operation Number');return false;
    }
    if(!/^-?\d+$/.test(document.getElementById('leadoffset').value)){
        alert('Enter Integer Value for PCD/PSD');return false;
    }
    if(!/^-?\d+$/.test(document.getElementById('SAM').value)){
        alert('Enter Integer Value for PCD/PSD');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('efficiency').value)){
        alert('Enter Decimal Numeric Format for Efficiency');return false;
    }
    if(!/^-?\d+$/.test(document.getElementById('nol').value)){
        alert('Enter Integer Value for PCD/PSD');return false;
    }
    if(!/^-?\d+$/.test(document.getElementById('nom').value)){
        alert('Enter Integer Value for PCD/PSD');return false;
    }
    return true;
}
function validateFabricCreate(url){
     if(document.getElementById('fabcode').value==''){
        alert('Enter Fabric Code');return false;
    }
     if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Type');return false;
    }
     if(document.getElementById('FRE3').value==''){
        alert('Please Select Structure');return false;
    }
     if(document.getElementById('FRE4').value==''){
        alert('Please Select Finish/Value');return false;
    }
    if(document.getElementById('fabdesc').value==''){
        alert('Enter Fabric Desc');return false;
    }
   
    if(document.getElementById('resp').value==''){
        alert('Please Select Responsible');return false;
    }
    
   
    //if(document.getElementById('productgrpID').value=='0'){
      //  alert('Please Select Product Group'); return false;
   // }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');return false;
    }
    if(document.getElementById('prqty').value!=''){
        if(!/^\d+.?\d*$/.test(document.getElementById('prqty').value)){
            alert('Enter Decimal Numeric Format for Purchase Price Qty');return false;
        }
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');return false;
    }
    
    if(!checkcurrflag('currID'))
        {
            return false;
        }
     
     
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');return false;
    }
    if(document.getElementById('excise').value=='0'){
        alert('Please Select Excise');return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('adjf').value)){
        alert('Enter Decimal Numeric Format for Prc Adj Factor');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('consumption').value)){
        alert('Enter Decimal Numeric Format for consumption');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('wastage').value)){
        alert('Enter Decimal Numeric Format for wastage');return false;
    }
    if(document.getElementById('wastage').value=='' || document.getElementById('wastage').value=='0.0'){
        if(confirm('Wastage Percentage is Zero, Do you wish to continue?'))
            dojo.widget.byId('createsubmit').href=url;
    }else{
        dojo.widget.byId('createsubmit').href=url;
        return true;
    }
}

function validateFabricCreate2(url){
    if(document.getElementById('fabdesc').value==''){
        alert('Enter Fabric Desc');return false;
    }
    if(document.getElementById('fabcode').value==''){
        alert('Enter Fabric Code');return false;
    }
     if(document.getElementById('resp').value==''){
        alert('Please Select Responsible');return false;
    }
    
    if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Item Type');return false;
    }
    if(document.getElementById('productgrpID').value=='0'){
       // alert('Please Select Product Group');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');return false;
    }
    if(document.getElementById('prqty').value!=''){
        if(!/^\d+.?\d*$/.test(document.getElementById('prqty').value)){
            alert('Enter Decimal Numeric Format for Purchase Price Qty');return false;
        }
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');return false;
    }
    
    if(!checkcurrflag('currID'))
        {
            return false;
        }
    
    
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');return false;
    }
    if(document.getElementById('excise').value=='0'){
        alert('Please Select Excise');return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('adjf').value)){
        alert('Enter Decimal Numeric Format for Prc Adj Factor');return false;
    }
    //    if(!/^\d+.?\d*$/.test(document.getElementById('consumption').value)){
    //        alert('Enter Decimal Numeric Format for consumption');return false;
    //    }
    //    if(!/^\d+.?\d*$/.test(document.getElementById('wastage').value)){
    //        alert('Enter Decimal Numeric Format for wastage');return false;
    //    }
    //    if(document.getElementById('wastage').value=='' || document.getElementById('wastage').value=='0.0'){
    //        if(confirm('Wastage Percentage is Zero, Do you wish to continue?'))
    //            dojo.widget.byId('createsubmit').href=url;
    //    }else{
    dojo.widget.byId('createsubmit').href=url;
    return true;
//    }
}

function validateFabricUpdate(){
    disableDivs('subMaterial');
    if(document.getElementById('fabdesc').value==''){
        alert('Enter Fabric Desc');
        hideCDivs();
        return false;
    }
    if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Item Type');
        hideCDivs();
        return false;
    }
    if(document.getElementById('productgrpID').value=='0'){
       // alert('Please Select Product Group');
        hideCDivs()
        return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');
        hideCDivs();
        return false;
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');
        hideCDivs();
        return false;
    }
    if(!checkcurrflag('currID'))
        {hideCDivs();
            return false;
        }
    
    
    
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');
        hideCDivs();
        return false;
    }
    if(document.getElementById('excise').value=='0'){
        alert('Please Select Excise');
        hideCDivs();
        return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');
        hideCDivs();
        return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');
        hideCDivs();
        return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('consumption').value)){
        alert('Enter Decimal Numeric Format for consumption');
        hideCDivs();
        return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('wastage').value)){
        alert('Enter Decimal Numeric Format for wastage');
        hideCDivs();
        return false;
    }
    if(document.getElementById('wastage').value=='' || document.getElementById('wastage').value=='0.0'){
        if(confirm('Wastage Percentage is Zero, Do you wish to continue?'))
            dojo.widget.byId('MaterialItemID').href ='MeterialUpdate.action';
        hideCDivs();
    }else{
        dojo.widget.byId('MaterialItemID').href='MeterialUpdate.action';
        hideCDivs();
        return true;
    }
}
function validateView(){
    disableDivs('subStyle');
    if(valStyleNumber('stylenumber')){
        alert('Enter Style Number');
        hideCDivs();
        return false;
    }
    dojo.widget.byId('StyleItemID').href='StyleView.action';
    hideCDivs();
    return true;
}

function validateOrder(){
    if(valStyleNumber('styleid2')){
        alert('Enter Style Number');return false;
    }
    if(document.getElementById('byr').value==''){
        alert('Select Buyer');return false;
    }
    return true;
}

function validateTrimCreate1(){
    alert('inside');
}

function validateTrimCreate(url){
    if(document.getElementById('fabcode').value==''){
        alert('Enter Code');return false;
    }
    if(document.getElementById('fabdesc').value==''){
        alert('Enter Trim Desc');return false;
    }
    if(document.getElementById('resp').value==''){
        alert('Please Select Responsible');return false;
    }
    
    if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Item Type');return false;
    }
    //    if(document.getElementById('productgrpID').value=='0'){
    //        alert('Please Select Product Group'); return false;
    //    }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');return false;
    }
    if(document.getElementById('prqty').value!=''){
        if(!/^\d+.?\d*$/.test(document.getElementById('prqty').value)){
            alert('Enter Decimal Numeric Format for Purchase Price Qty');return false;
        }
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');return false;
    }
    
    if(!checkcurrflag('currID'))
        {
            return false;
        }
   
    
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');return false;
    }
    if(document.getElementById('excise').value=='0'){
        alert('Please Select Excise');return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('adjf').value)){
        alert('Enter Decimal Numeric Format for Prc Adj Factor');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('consumption').value)){
        alert('Enter Decimal Numeric Format for consumption');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('wastage').value)){
        alert('Enter Decimal Numeric Format for wastage');return false;
    }
    if(document.getElementById('wastage').value=='' || document.getElementById('wastage').value=='0.0'){
        if(confirm('Wastage Percentage is Zero, Do you wish to continue?'))
            dojo.widget.byId('createsubmit').href=url;
    }else{
        dojo.widget.byId('createsubmit').href=url;
        return true;
    }
}

function validateTrimCreate2(url){
    if(document.getElementById('fabcode').value==''){
        alert('Enter Code');return false;
    }
  /*  if(document.getElementById('fabdesc').value==''){
        alert('Enter Trim Desc');return false;
    }*/
     if(document.getElementById('resp').value==''){
        alert('Please Select Responsible');return false;
    }
    
    if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Item Type');return false;
    }
    //    if(document.getElementById('productgrpID').value=='0'){
    //        alert('Please Select Product Group'); return false;
    //    }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');return false;
    }
    if(document.getElementById('prqty').value!=''){
        if(!/^\d+.?\d*$/.test(document.getElementById('prqty').value)){
            alert('Enter Decimal Numeric Format for Purchase Price Qty');return false;
        }
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');return false;
    }
    
      if(!checkcurrflag('currID'))
        {
            return false;
        }
   
    
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');return false;
    }
    if(document.getElementById('excise').value=='0'){
        alert('Please Select Excise');return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('adjf').value)){
        alert('Enter Decimal Numeric Format for Prc Adj Factor');return false;
    }

    dojo.widget.byId('createsubmit').href=url;
    return true;
}

function validateTrimUpdate(){
    disableDivs('subMaterial');
    if(document.getElementById('fabdesc').value==''){
        alert('Enter Fabric Desc');
        hideCDivs();
        return false;
    }
    if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Item Type');
        hideCDivs();
        return false;
    }
    if(document.getElementById('productgrpID').value=='0'){
       // alert('Please Select Product Group');
        hideCDivs();
        return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');
        hideCDivs();
        return false;
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');
        hideCDivs();
        return false;
    }
    
    if(!checkcurrflag('currID'))
        {hideCDivs();
            return false;
        }
    
    
    
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');
        hideCDivs();
        return false;
    }
    if(document.getElementById('alunID').value=='0'){
        alert('Please Select Alternative UOM');
        hideCDivs();
        return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');
        hideCDivs();
        return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');
        hideCDivs();
        return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('consumption').value)){
        alert('Enter Decimal Numeric Format for consumption');
        hideCDivs();
        return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('wastage').value)){
        alert('Enter Decimal Numeric Format for wastage');
        hideCDivs();
        return false;
    }
    if(document.getElementById('wastage').value=='' || document.getElementById('wastage').value=='0.0'){
        if(confirm('Wastage Percentage is Zero, Do you wish to continue?'))
            dojo.widget.byId('MaterialTrimID').href ='MercTrimUpdate.action';
        hideCDivs();
    }else{
        dojo.widget.byId('MaterialTrimID').href='MercTrimUpdate.action';
        hideCDivs();
        return true;
    }
}

function limitText(limitField, limitCount, limitNum) {
    if (limitField.value.length > limitNum) {
        limitField.value = limitField.value.substring(0, limitNum);
    } else {
        limitCount.value = limitNum - limitField.value.length;
    }
}
function submitCO(){
    alert("called1");
    document.getElementById('form1').action='TabbedPane.action?target=subCOntainer';
    document.getElementById('subCOntainer').href='OrderNew.action';
    //document.getElementById('form1').targets='subCOntainer';
    //dojo.widget.byId('form1').action='OrderNew.action';
    document.getElementById('form1').submit();
}

function addRow()
{
    var oTable = document.getElementById('item_add_table');
    var lastRow = oTable.rows.length;
    var newRow = document.all("item_add_table").insertRow(lastRow);
    newRow.setAttribute("bgColor", "#ffffff");
    oCell = newRow.insertCell(0);
    oCell = newRow.insertCell(1);
    oCell.innerHTML='<input name="colors" class="text1" type="text" maxlength="15" onblur="javascript:this.value=this.value.toUpperCase();">';
    oCell = newRow.insertCell(2);
    oCell = newRow.insertCell(3);
    oCell.innerHTML='<input name="sizes" class="text1" type="text" maxlength="15" onblur="javascript:this.value=this.value.toUpperCase();">';
    oCell = newRow.insertCell(4);
    oCell = newRow.insertCell(5);
    oCell.innerHTML='<input name="destinations" class="text1" maxlength="15" type="text" onfocus="addRow()" onblur="javascript:this.value=this.value.toUpperCase();">';
}
function addRowStyle()
{
    var oTable = document.getElementById('styleitem_add_table');
    var lastRow = oTable.rows.length;
    var newRow = document.all("styleitem_add_table").insertRow(lastRow);
     newRow.setAttribute("bgColor", "#ffffff");
    oCell = newRow.insertCell(0);
    oCell.innerHTML='<input name="colors" class="text1" maxlength="15" type="text" onblur="javascript:this.value=this.value.toUpperCase();">';
    oCell = newRow.insertCell(1);
    oCell.innerHTML='<input name="sizes" class="text1" maxlength="15" type="text" onblur="javascript:this.value=this.value.toUpperCase();">';
    oCell = newRow.insertCell(2);
    oCell.innerHTML='<input name="destinations" class="text1" maxlength="15" type="text" onfocus="addRowStyle()" onblur="javascript:this.value=this.value.toUpperCase();">';
}

function addNewRow()
{
    var oTable = document.getElementById('item_add_table');
    var lastRow = oTable.rows.length;

    var newRow = document.all("item_add_table").insertRow(lastRow);
    newRow.setAttribute("bgColor", "#ffffff");


    oCell = newRow.insertCell(0);
    oCell = newRow.insertCell(1);
    oCell.innerHTML='<input name="colors" class="text1" maxlength="15"  type="text" onblur="javascript:this.value=this.value.toUpperCase();">';
    oCell = newRow.insertCell(2);
    oCell.innerHTML='<input name="width" class="text1" maxlength="15" type="text" onfocus="addNewRow()" onblur="javascript:this.value=this.value.toUpperCase();">';
}


function settingValidation()
{
    var altsms="Setting Parameters are not updated Properly";
    if(document.getElementById("faciset").value==0)
        {
             alert(altsms);
             return false;
        }
        if(document.getElementById("whsset").value==0)
        {
             alert(altsms);
             return false;
        }
         if(document.getElementById("rspset").value==0)
        {
             alert(altsms);
             return false;
        }
         if(document.getElementById("frspset").value==0)
        {
             alert(altsms);
             return false;
        }
          if(document.getElementById("trspset").value==0)
        {
             alert(altsms);
             return false;
        }

           if(document.getElementById("bgpset").value==0)
        {
             alert(altsms);
             return false;
        }

            if(document.getElementById("byrset").value==0)
        {
             alert(altsms);
             return false;
        }

             if(document.getElementById("bydset").value==0)
        {
             alert(altsms);
             return false;
        }
             if(document.getElementById("buaset").value==0)
        {
             alert(altsms);
             return false;
        }


              if(document.getElementById("prgset").value==0)
        {
             alert(altsms);
             return false;
        }
              if(document.getElementById("curset").value==0)
        {
             alert(altsms);
             return false;
        }

              if(document.getElementById("sapset").value==0)
        {
             alert(altsms);
             return false;
        }
              if(document.getElementById("dmtset").value==0)
        {
             alert(altsms);
             return false;
        }
               if(document.getElementById("dtrset").value==0)
        {
             alert(altsms);
             return false;
        }
               if(document.getElementById("pmtset").value==0)
        {
             alert(altsms);
             return false;
        }
                if(document.getElementById("ptrset").value==0)
        {
             alert(altsms);
             return false;
        }
         if(document.getElementById("ltpset").value==0)
        {
             alert(altsms);
             return false;
        }
          if(document.getElementById("pktset").value==0)
        {
             alert(altsms);
             return false;
        }

           if(document.getElementById("ctpset").value==0)
        {
             alert(altsms);
             return false;
        }





    return true;

}


function ValLinkset(id,url,aid,styleid) {


    var text = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML="";
    disableDivs('mainContainer');
    if(aid=='StyleNewID' || aid =='RefreshDataID'|| aid =='UpdateDataID'){
        dojo.widget.byId(aid).href=url;
        hideCDivs();
        return true;
    }
    else if(valStyleNumber(styleid)){
        dojo.widget.byId(aid).href=url;
        hideCDivs();
        return true;
    }else{
        hideCDivs();
        document.getElementById(id).innerHTML=text;
        return false;
    }


}


function validatePCHM()
{

        if(document.getElementById('PCHM')){
         var buaset=document.getElementById('buaset').value;
        var PCH=document.getElementById('PCHM').value;
        buaset=buaset.substring(0,3);

       if(buaset.toString()!=PCH.toString())
        {

              alert('Your PCH and selected item PCH are not same,so you are not authorised for this option!!');
              return false;

        }

        }

        return true;

}

function validatePCHO()
{

        if(document.getElementById('PCHO')){
         var buaset=document.getElementById('buaset').value;
        var PCH=document.getElementById('PCHO').value;
        buaset=buaset.substring(0,3);

       if(buaset.toString()!=PCH.toString())
        {


                alert('Your PCH and selected item PCH are not same,so you are not authorised for this option!!');

              return false;

        }

        }

        return true;

}


function validatePCHOM()
{

        if(document.getElementById('PCHOM')){
         var buaset=document.getElementById('buaset').value;
        var PCH=document.getElementById('PCHOM').value;
        buaset=buaset.substring(0,3);

       if(buaset.toString()!=PCH.toString())
        {


                alert('Your PCH and selected item PCH are not same,so you are not authorised for this option!!');

              return false;

        }

        }

        return true;

}

function getaddressby(tempaddview,addressdesc,addbtnshow,byd)
{

 toggle(tempaddview,addbtnshow);
  document.getElementById(addressdesc).value=document.getElementById(document.getElementById(byd).value).value

}

function toggle(tempaddview,addbtnshow) {

 var state = document.getElementById(tempaddview).style.display;

if (state == 'block') {

document.getElementById(tempaddview).style.display = 'none';
//document.getElementById(addbtnshow).value = 'Address';


} else {

document.getElementById(tempaddview).style.display = 'block';
//document.getElementById(addbtnshow).value = 'Hide';

}

}

    function validatenumber(a)
{

       k=0+a.value;
                   if (!k.match(/^\d+$|^\d+\.\d{1,6}$/ ) )
                        {
                          alert('Invalid Input, Only Numbers Allowed');
                          a.value="0";
                          a.focus();
                          return false;

                        }
return true;
}

   function validatenumber(a)
{

       k=0+a.value;
                   if (!k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
                        {
                          alert('Invalid Input, Only Numbers Allowed');
                          a.value="0";
                          a.focus();
                          return false;

                        }
return true;
}

  function validatenumbersty(a)
{

       k=0+a.value;
                   if (!k.match(/^\d+$|^\d+\.\d{1,6}$/ ) )
                        {
                          alert('Invalid Input, Only Numbers Allowed');
                          a.value="0";
                          a.focus();
                          return false;

                        }
return true;
}


   function validatenumbern(a)
{

       k=0+a.value;
                   if (!k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
                        {
                          alert('Invalid Input, Only Numbers Allowed');
                          a.value="0";
                          a.focus();
                          return false;

                        }
return true;
}


   function validatenumberwidth(a)
{

       k=0+a.value;
                   if (!k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
                        {
                          alert('Invalid Input, Only Numbers Allowed');
                          a.value="";
                          a.focus();
                          return false;

                        }
return true;
}

function copyvalue()
{

    if(validatenumber(document.frm.tempprices))
        {
   if(document.frm.tempprices.value!="")
       {
        objtempprices=document.frm.LISTPUPR ;

        for(i=0; i<objtempprices.length; i++)
            {
                if(!objtempprices[i].disabled){
              objtempprices[i].value=document.frm.tempprices.value;

                }
          }

       }
        }
}



function tbc(obj)
{
    var index = obj.selectedIndex;
     var buaset=obj.options[index].text;
     buaset=buaset.substring(0,3);
       //buaset=buaset.substring(0,3);
       if(buaset=='TBC')
        {
           document.getElementById('fabcode').value="TBC" 
           document.getElementById('fabcode').readOnly="true" 
        }else{ 
            
            document.getElementById('fabcode').readOnly="" 
        }
       
    
}

function getdesc(a,b)
{
   
    if(document.getElementById(a))
        {
         document.getElementById(b).value= document.getElementById(a).value;  
        }
}


function ValLinksettest(url,aid) {

         
        dojo.widget.byId(aid).href=url;
    
        return true;
    

}



function show_detailsBGP() {
dojo.event.topic.publish("show_detail");
}
function show_detailsBGP1() {
dojo.event.topic.publish("show_detail1");
}

function show_detailsapprovar() {
dojo.event.topic.publish("show_detailapp");
}
function show_detailsapprovarfab() {
dojo.event.topic.publish("show_detailappfab");
}
function show_detailsapprovarpd() {
dojo.event.topic.publish("show_detailapppd");
}
function show_detailsapprovarpm() {
dojo.event.topic.publish("show_detailapppm");
}
function show_detailsapprovarfm() {
dojo.event.topic.publish("show_detailappfm");
}

function show_detailsFAB() {

dojo.event.topic.publish("show_detail_frm");
}

function show_detailsTRM() {
   

dojo.event.topic.publish("show_detail_trm");
}

function show_detailsMKT() {
  
dojo.event.topic.publish("show_detail_mkt");
}


function checkdate(date1,date2){
var validformat=/^\d{4}\-\d{2}\-\d{2}$/ 
var returnval=false
if(!validformat.test(date1)){
alert("Invalid Todate");

}
else if(!validformat.test(date2.value)){
alert("Invalid Delivery Date");
date2.value="";
}
else{
var start = date1;
var end = date2.value;
start =start.substring(0, 4)+start.substring(5, 7)+start.substring(8, 10);
end =end.substring(0, 4)+end.substring(5, 7)+end.substring(8, 10);




//var stDate = new Date(start);
//var enDate = new Date(end);
var compDate = parseInt(end) - parseInt(start);

if(parseInt(compDate) >= 0)
    {
return true;
    }
else
{
alert("Delivery Date should be greater than Todate.");
date2.value=date1;
return false;
} 
}
}

function validatedateinput(date1)
{
  var validformat=/^\d{4}\-\d{2}\-\d{2}$/ 

if(date1.value!="" && !validformat.test(date1.value)){
alert("Invalid Date");
date1.focus();
return false;
}  

validateexpdate(date1);

   return true; 
}


function validateexpdate(date1)
{
  var PCDDATEvalue=document.getElementById('PCDDATE').value ;
  var ORDCOMDATEvalue=date1.value;
     
    if(ORDCOMDATEvalue!="" && PCDDATEvalue!="")
        {
            
              var PCDDATE=PCDDATEvalue.replace("-", "");
                  PCDDATE=PCDDATE.replace("-", "");
                  PCDDATE = PCDDATE.substring(0, 8);
                  
              var ORDCOMDATE=ORDCOMDATEvalue.replace("-", "");
                  ORDCOMDATE=ORDCOMDATE.replace("-", "");
                  ORDCOMDATE = ORDCOMDATE.substring(0, 8);  
                 
                  
                  if(parseInt(PCDDATE) > parseInt(ORDCOMDATE))
                      {
                          
                        alert("Planned Ex-factory Date can not be a date prior to PCD");  
                        date1.value=PCDDATEvalue;
                        return false
                          
                      }
                
            
            
        }
    
}

//fabric 





function FabValLink(id,url,aid,styleid) {

  
    var text = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML="";
      //disableDivs('subFabric');
    if((aid =='updSKUFabric' || aid=='addSKUFabric') && !fabgetduplicatefab()) 
      {
                     hideCDivs();
                    document.getElementById(id).innerHTML=text;
                    return false;
      }else if(aid=='viewFabric2' && document.getElementById('paropno').value=="")
          {
             alert("Please Enter Item No") ;
              document.getElementById(id).innerHTML=text;
             document.getElementById('paropno').focus();
              return false;
              
          }
      
     dojo.widget.byId(aid).href=url;
     return true;
        
   


}

function fabricvalFabricCreate(url,id,aid) {
    var text = document.getElementById(id).innerHTML;
    document.getElementById(id).innerHTML="";
    
    //alert(validateFabricCreatefabric());
    disableDivs('subFabric');
                 
    if(validateFabricCreatefabric()){
        
             dojo.widget.byId(aid).href=url;
               hideCDivs();
        return true;
    }else{
         hideCDivs();
        document.getElementById(id).innerHTML=text;
        return false;
    }
}



function validateFabricCreatefabric(){
     if(document.getElementById('fabcode').value==''){
        alert('Enter Item Code');return false;
    }
    if(document.getElementById('stylenumber').value==''){
        alert('Enter Style No');return false;
    }
    if((document.getElementById('stylenumber').value).length==4)
    {
      
    }else{
        
        alert('Not a Valid Style No');
        return false;
    }
    
    if(document.getElementById('fabdesc').value==''){
        alert('Enter  Desc');return false;
    }
    if(document.getElementById('fabcode').value==''){
        alert('Enter  Code');return false;
    }
    if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Item Type');return false;
    }
    //if(document.getElementById('productgrpID').value=='0'){
      //  alert('Please Select Product Group'); return false;
   // }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');return false;
    }
    if(document.getElementById('prqty').value!=''){
        if(!/^\d+.?\d*$/.test(document.getElementById('prqty').value)){
            alert('Enter Decimal Numeric Format for Purchase Price Qty');return false;
        }
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');return false;
    }
    
    if(!checkcurrflag('currID'))
        {
            return false;
        }
     
     
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');return false;
    }
    if(document.getElementById('excise').value=='0'){
        alert('Please Select Excise');return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');return false;
    }
    
    if(!/^\d+.?\d*$/.test(document.getElementById('adjf').value)){
        alert('Enter Decimal Numeric Format for Prc Adj Factor');return false;
    }
     
  /*  if(!/^\d+.?\d*$/.test(document.getElementById('consumption').value)){
        alert('Enter Decimal Numeric Format for consumption');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('wastage').value)){
        alert('Enter Decimal Numeric Format for wastage');return false;
    }
    if(document.getElementById('wastage').value=='' || document.getElementById('wastage').value=='0.0'){
        if(confirm('Wastage Percentage is Zero, Do you wish to continue?'))
            dojo.widget.byId('createsubmit').href=url;
    }else{
       // dojo.widget.byId('createsubmit').href=url;
        return true;
    }*/
    
    return true;
}




function FabvalFabricCreate2(url) {
    var text = document.getElementById('createfab').innerHTML;
    document.getElementById('createfab').innerHTML="";
    disableDivs('subFabric');
     

    if(FabvalidateFabricCreate2(url)){
        hideCDivs();
        return true;
    }else{
        hideCDivs();
        document.getElementById('createfab').innerHTML=text;
        return false;
    }
}



function FabvalidateFabricCreate2(url){
    if(document.getElementById('fabdesc').value==''){
        alert('Enter Desc');return false;
    }
    if(document.getElementById('fabcode').value==''){
        alert('Enter Item Code');return false;
    }
    if(document.getElementById('itemtypeID').value=='0'){
        alert('Please Select Item Type');return false;
    }
    if(document.getElementById('productgrpID').value=='0'){
        alert('Please Select Product Group');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('purchasepr').value)){
        alert('Enter Decimal Numeric Format for Purchase Price');return false;
    }
    if(document.getElementById('prqty').value!=''){
        if(!/^\d+.?\d*$/.test(document.getElementById('prqty').value)){
            alert('Enter Decimal Numeric Format for Purchase Price Qty');return false;
        }
    }
    if(document.getElementById('currID').value=='0'){
        alert('Please Select Currency Code');return false;
    }
    
    if(!checkcurrflag('currID'))
        {
            return false;
        }
    
    
    if(document.getElementById('basicUOM').value=='0'){
        alert('Please Select Basic UOM');return false;
    }
    if(document.getElementById('excise').value=='0'){
        alert('Please Select Excise');return false;
    }
    if(document.getElementById('salestaxgrp').value=='0'){
        alert('Please Select Sales Tax Group');return false;
    }
    if(document.getElementById('itemgrpID').value=='0'){
        alert('Please Select Item Group');return false;
    }
    if(!/^\d+.?\d*$/.test(document.getElementById('adjf').value)){
        alert('Enter Decimal Numeric Format for Prc Adj Factor');return false;
    }
    //    if(!/^\d+.?\d*$/.test(document.getElementById('consumption').value)){
    //        alert('Enter Decimal Numeric Format for consumption');return false;
    //    }
    //    if(!/^\d+.?\d*$/.test(document.getElementById('wastage').value)){
    //        alert('Enter Decimal Numeric Format for wastage');return false;
    //    }
    //    if(document.getElementById('wastage').value=='' || document.getElementById('wastage').value=='0.0'){
    //        if(confirm('Wastage Percentage is Zero, Do you wish to continue?'))
    //            dojo.widget.byId('createsubmit').href=url;
    //    }else{
    dojo.widget.byId('createsubmit').href=url;
    return true;
//    }
}



function FabcopyBom(url) {
    var text = document.getElementById('createfab').innerHTML;
    document.getElementById('createfab').innerHTML="";
    disableDivs('subFabric');
    
    dojo.widget.byId('createsubmit').href=url;
    hideCDivs();
    return true;
}


function fabgetduplicatefab()
{
    if(document.getElementById('formmat1')){
    var flg=0;
    var flg1=0;
  var colors=document.getElementById('formmat1').colors;
  var sizes=document.getElementById('formmat1').sizes;
 for(i=0; i<colors.length; i++)
      {
         if(colors[i].value!="" ) 
             {
                 flg=1;
             }
          
      }
      
    for(i=0; i<sizes.length; i++)
      {
         if(sizes[i].value!="" ) 
             {
                 flg1=1;
             } 
          
      }  
      
    if(flg==0) 
    {
               alert("Please Enter Color");
                colors[0].focus();
                return false;    
        
    }
    
     if(flg1==0) 
    {
               alert("Please Enter Width");
                sizes[0].focus();
                return false;    
        
    }
  
  for(i=0; i<colors.length; i++)
      { 
         for(j=i+1; j<colors.length; j++)
      {  
        if(colors[i].value!="" && (colors[i].value).toUpperCase()==(colors[j].value).toUpperCase())
            {
                alert("Duplicate Entry Found.."+colors[j].value);
                colors[j].focus();
                return false;
            }
          
      }  
          
      }
  
  for(i=0; i<sizes.length; i++)
      {
         for(j=i+1; j<sizes.length; j++)
      {
        if(sizes[i].value!="" && sizes[i].value==sizes[j].value)
            {
                alert("Duplicate Entry Found.."+sizes[j].value);
                sizes[j].focus();
                return false;
            }
          
      }  
          
      }
    }
    
    
    if(document.getElementById('formtrim1')){
    
  var colors=document.getElementById('formtrim1').colors;
  var sizes=document.getElementById('formtrim1').sizes;
   var destinations=document.getElementById('formtrim1').destinations;
 
  
  for(i=0; i<colors.length; i++)
      { 
         for(j=i+1; j<colors.length; j++)
      {  
        if(colors[i].value!="" && (colors[i].value).toUpperCase()==(colors[j].value).toUpperCase())
            {
                alert("Duplicate Entry Found.."+colors[j].value);
                colors[j].focus();
                return false;
            }
          
      }  
          
      }
   
  for(i=0; i<sizes.length; i++)
      {
         for(j=i+1; j<sizes.length; j++)
      {
        if(sizes[i].value!="" && sizes[i].value==sizes[j].value)
            {
                alert("Duplicate Entry Found.."+sizes[j].value);
                sizes[j].focus();
                return false;
            }
          
      }  
          
      }
      if(destinations.length)
          {
       for(i=0; i<destinations.length; i++)
      {
         for(j=i+1; j<destinations.length; j++)
      {
        if(destinations[i].value!="" && destinations[i].value==destinations[j].value)
            {
                alert("Duplicate Entry Found.."+destinations[j].value);
                destinations[j].focus();
                return false;
            }
          
      }  
          
      }}
    }
    
    
    
    
    
    return true;
}

function aaaatest(){
alert('okkk');
}


function orderlinesc(a)
{
   
  $("#nameDiv3"+a).scroll(function() {
    $("#percentageDiv1"+a).scrollTop($("#nameDiv3"+a).scrollTop());
});
$("#percentageDiv1"+a).scroll(function() {
    $("#nameDiv3"+a).scrollTop($("#percentageDiv1"+a).scrollTop());
});


$("#nameDiv1"+a).scroll(function() {
    $("#percentageDiv1"+a).scrollLeft($("#nameDiv1"+a).scrollLeft());
});
$("#percentageDiv1"+a).scroll(function() {
    $("#nameDiv1"+a).scrollLeft($("#percentageDiv1"+a).scrollLeft());
});
}



function closeooderjs(a,url,b)
{
  
     var text = document.getElementById(b).innerHTML;
    document.getElementById(b).innerHTML="";
   if(confirm("Do You Want To Close Order ,Bcoz Once Closed Order Cannot be Reinstated...'")) 
       { 
          dojo.widget.byId(a).href=url;  
           return true;
       }else{
           document.getElementById(b).innerHTML=text;
           return ;
       }
    
}


function validateinputUsa() 
 { 
  // var ustype=document.frm.USAGE_TYPE;
  var ustype=document.getElementsByName("USAGE_TYPE");
   var ustypeval=0;
  
   var j=0;
   for(i=0; i<ustype.length; i++)
       {
         if(ustype[i].checked)  
             {
                 if(ustype[i].value=='BD')
                     {
                         ustypeval='BD';
                        
                   }else{
                       ustypeval='AA';
                   }
              }
       }
       
    for(i=0; i<ustype.length; i++)
       {
         
                 if(ustypeval=='BD')
                     { if(ustype[i].value!='BD')
                        {
                         ustype[i].checked=false;
                         ustype[i].disabled=true; 
                        }
                     
                   }else{
                      
                       if(ustype[i].value=='BD' && ustypeval=='AA')
                        {
                         ustype[i].checked=false;
                         ustype[i].disabled=true;
                        }else{
                         ustype[i].disabled=false;  
                        }
                   }
              
       }   
   
   
 }
 
 
 function openpurchasepop(a)
 {
     if(a.value=="2-Purchased"){
    document.getElementById("styled_popup1").style.display="block" ;
     }else{
        document.getElementById("styled_popup1").style.display="none" ; 
     }
 }
 
 
 
 function validateinputuge()
   {
       
       if(document.getElementById('stylecode').value==""){
           alert("Please Select Style");
            document.getElementById('stylecode').focus();
            return false;
           
       }
       
        var ustype=document.getElementsByName("USAGE_TYPE");
   var ustypeval="CC";
  
   
   for(i=0; i<ustype.length; i++)
       {
         if(ustype[i].checked)  
             {
                 if(ustype[i].value=='BD')
                     {
                         ustypeval='BD';
                        
                   }else{
                       ustypeval='AA';
                   }
              }
       }
       
       if(ustypeval=="CC")
           {
              alert("Please Select Design Usages Type.") ;
               document.getElementById("styled_popup1U").style.display="block" ;
               return false;
               
           }
       if(ustypeval=='AA')
           {   if(document.getElementById('productdesigner').value==""){
               alert("Please Select Product Designer.");
               document.getElementById('productdesigner').focus();
               return false;
}
           }else{

           if(ustypeval!="CC" && document.getElementById('productdesigner').value!="")
           {
               alert("Product Designer Not Required for Buyer Design ");
               document.getElementById('productdesigner').value="";
               return false;
              }


}
       return true;
   }
   
   
   
function addstructure(id,s_file)  
{
  document.getElementById(id).innerHTML='<img src="http://172.17.3.57:8080/ShahiApplication/shahiwebpages/CRMAPP/FileImages/'+s_file+'" width="200px" height="100px"/>';  
    
}

