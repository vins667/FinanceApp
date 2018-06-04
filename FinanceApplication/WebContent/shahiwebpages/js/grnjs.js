

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

function searchhomedtl(){

if(document.hris.RECEIPT_NO.value!=""){
    document.hris.action='grninsdetailAction.action';
    document.hris.submit();
}

}

function searchhomedtlNew(){

if(document.hris.RECEIPT_NO.value!=""){
    document.hris.action='grninsdetailActionNew.action';
    document.hris.submit();
    document.getElementById('prepage').style.visibility='';
}

}



function validatenumber(a)
{
 if (!a.value=="" &&!a.value.match(/^\d+$/))
  {
    alert('Invalid Input, Only Numbers Allowed');
    a.value="";
    a.focus();
    return false;
  }
}



function aaa()
{
document.hris.action='grninspectionMovexDetail.jsp';
document.hris.submit();
}

function saverec()
{
if(validate()){
if(confirm('Do You Want to Save/Update Record(s) ?'))
  {
  document.getElementById('prepage').style.visibility='';
  document.hris.action='grninsdetailUpdateAction.action?updateflg=1';
  document.hris.submit();
  }
  else{
  return;
  }
}
}

function saverecNew()
{
//if(validate()){
if(confirm('Do You Want to Save/Update Record(s) ?'))
  {
  document.getElementById('prepage').style.visibility='';
  document.hris.action='grninsdetailUpdateActionNew.action?updateflg=1';
  document.hris.submit();
  }
  else{
  return;
  }
//}
}

function validate()
 {

 INS_STATUS=document.hris.INS_STATUS
 REJ_CODE=document.hris.REJ_CODE
 STOCK_LOCATION=document.hris.STOCK_LOCATION
 INS_LOCATION=document.hris.INS_LOCATION
 

 ROLL_NO=document.hris.ROLL_NO

 if(ROLL_NO.length)
   {
   for(i=0; i<INS_STATUS.length; i++)
    {
        if(INS_STATUS[i].value=="")
        {
        alert("please Select Inspection Status");
        INS_STATUS[i].focus()
        return false;
        }

        if(INS_STATUS[i].value==4)
        {
           if(REJ_CODE[i].value=="")
          {
          alert("please Select Rejection Reason");
          REJ_CODE[i].focus()
          return false;
          }
        }
       
        if(INS_LOCATION[i].value=="")
       {
                alert("Please Select Inspection Location1");
               INS_LOCATION[i].focus()
               return false;

       }
       a=INS_LOCATION[i].value;
      
        if(INS_STATUS[i].value==4 && a.indexOf("INS")>0)
        {
           alert("please Select Rejection  Location");
            INS_LOCATION[i].focus()
            return false;

        }
       
        if(INS_STATUS[i].value==1 && a.indexOf("INS")==-1)
        {
           alert("Please Select Inspection Location2");
            INS_LOCATION[i].focus()
            return false;

        }

         if(INS_STATUS[i].value!=4 ){
          if(STOCK_LOCATION[i].value=="")
          {
           alert("please Select Stock Location");
           STOCK_LOCATION[i].focus()
           return false;
          }
          a=STOCK_LOCATION[i].value
          if(a.indexOf("PUT")==-1)
        {
           //alert("please Select Put Stock Location");
           // INS_LOCATION[i].focus()
           // return false;

        }

         }

    }
 }else{
     if(INS_STATUS.value=="")
        {
        alert("please Select Inspection Status");
        INS_STATUS.focus()
        return false;
        }
         if(INS_STATUS.value==4)
        {
           if(REJ_CODE.value=="")
          {
          alert("please Select Rejection Reason");
          REJ_CODE.focus()
          return false;
          }
        }

        if(INS_LOCATION.value=="")
       {
         alert("please Select Inspection Location3");
        INS_LOCATION.focus()
        return false;

       }
        a=INS_LOCATION.value;

        if(INS_STATUS.value==4 && a.indexOf("INS")>0)
        {
           alert("please Select Rejection  Location");
            INS_LOCATION.focus()
            return false;

        }

        if(INS_STATUS.value==1 && a.indexOf("INS")==-1)
        {
           alert("Please Select Inspection Location4");
            INS_LOCATION.focus()
            return false;

        }
   if(INS_STATUS.value!=4 ){
          if(STOCK_LOCATION.value=="")
          {
           alert("please Select Stock Location");
           STOCK_LOCATION.focus()
           return false;
          }
          a=STOCK_LOCATION.value
          if(a.indexOf("PUT")==-1)
        {
           //alert("please Select Put Stock Location");
            //INS_LOCATION.focus()
            //return false;

        }

         }

 }
 return true;
 }



function uploadtomvx()
{
  if(confirm('Do You Want to Upload in Movex ?'))
  {
  document.getElementById('prepage').style.visibility='';
  document.hris.action='grninsdetailUpdateAction.action?mvxupload=1';
  document.hris.submit();
  }
  else{
  return;
  }

}

function uploadtomvxNew()
{
  
  if(confirm('Do You Want to Upload in Movex ???'))
  {
  document.getElementById('prepage').style.visibility='';
  document.hris.action='grninsdetailUpdateActionNew.action?mvxupload=1';
  document.hris.submit();
  }
 
}

function uploadtomvxNewError()
{
    
 alert("Inspection Qty(%) is less than 10%... Can not upload!!!");

}

function changevalue(aa,a,b){
 STOCK_LOCATION=b;
 STOCK_LOCATION_CP=a;
SHADE_GRP_CP=document.hris.SHADE_GRP_CP.value;
SHADE_LOT_CP=document.hris.SHADE_LOT_CP.value;

     for(i=0; i<STOCK_LOCATION.length; i++)
     {
          if(SHADE_LOT_CP=="" && SHADE_GRP_CP=="")
         {
                 for(j=0; j<STOCK_LOCATION[i].options.length; j++)
                 {
                         if(STOCK_LOCATION[i].options[j].value ==STOCK_LOCATION_CP)
                         {
                         STOCK_LOCATION[i].options[j].selected=true;

                         }
                         }

               


}else{
               strid=STOCK_LOCATION[i].id;
           if(SHADE_LOT_CP!="" && SHADE_GRP_CP=="")
           {
              if(strid.substring(0,strid.indexOf(":"))==SHADE_LOT_CP)
              {
               for(j=0; j<STOCK_LOCATION[i].options.length; j++)
                     {

                             if(STOCK_LOCATION[i].options[j].value ==STOCK_LOCATION_CP)
                             {
                             STOCK_LOCATION[i].options[j].selected=true;

                             }
                    }

              }
          }

           if(SHADE_GRP_CP!="" && SHADE_LOT_CP=="")
           {

              if(strid.substring(strid.indexOf("::")+2,strid.length)==SHADE_GRP_CP)
              {
               for(j=0; j<STOCK_LOCATION[i].options.length; j++)
                     {
                     //alert(STOCK_LOCATION[i].value);
                     //SHADE_GRP_CP=="" &&

                             if(STOCK_LOCATION[i].options[j].value ==STOCK_LOCATION_CP)
                             {
                             STOCK_LOCATION[i].options[j].selected=true;

                             }
                    }

              }
          }




         if(SHADE_GRP_CP!="" && SHADE_LOT_CP!="")
           {

              if(strid.substring(0,strid.indexOf(":"))==SHADE_LOT_CP && strid.substring(strid.indexOf("::")+2,strid.length)==SHADE_GRP_CP)
              {
               for(j=0; j<STOCK_LOCATION[i].options.length; j++)
                     {
                     //alert(STOCK_LOCATION[i].value);
                     //SHADE_GRP_CP=="" &&

                             if(STOCK_LOCATION[i].options[j].value ==STOCK_LOCATION_CP)
                             {
                             STOCK_LOCATION[i].options[j].selected=true;

                             }
                    }

              }
          }




         }


     }
aa.value="";
}


 function blinkColor()
  {
  var inputs = document.getElementsByName( 'ab');
  for(i=0; i< inputs.length; i++)
  {
  document.getElementById("blink"+i).style.background="red"
  }
  setTimeout("setblinkColor()",500)
  }

function setblinkColor()
{
 var inputs = document.getElementsByName( 'ab');
  for(i=0; i< inputs.length; i++)
  {
  document.getElementById("blink"+i).style.background="#5e9dc8"
  }
  setTimeout("blinkColor()",500)
}

function blinkColortxt()
  {

  document.getElementById("ODOURtest").style.backgroundColor="White"

  setTimeout("setblinkColortxt()",500)
  }

function setblinkColortxt()
{

  document.getElementById("ODOURtest").style.backgroundColor="red"

  setTimeout("blinkColortxt()",500)
}


var ns4=document.layers
var ie4=document.all
var ns6=document.getElementById&&!document.all

function hidebox1(){
crossobj=ns6? document.getElementById("showimage") : document.all.showimage
if (ie4||ns6)
crossobj.style.visibility="hidden"
else if (ns4)
document.showimage.visibility="hide"
}

function showbox1(){
crossobj=ns6? document.getElementById("showimage") : document.all.showimage
if (ie4||ns6)
crossobj.style.visibility="visible"
else if (ns4)
document.showimage.visibility="show"
}

function showbox2(a){
var td = document.getElementById('dragbar1');
td.innerHTML ='Remarks     &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         (' +a+')';

crossobj=ns6? document.getElementById("showimage2") : document.all.showimage2
if (ie4||ns6)
crossobj.style.visibility="visible"
else if (ns4)
document.showimage2.visibility="show"
}

function hidebox2(){
crossobj=ns6? document.getElementById("showimage2") : document.all.showimage2
if (ie4||ns6)
crossobj.style.visibility="hidden"
else if (ns4)
document.showimage2.visibility="hide"
}

function showboxdebit2(){
crossobj=ns6? document.getElementById("showimagedebit2") : document.all.showimagedebit2
if (ie4||ns6)
crossobj.style.visibility="visible"
else if (ns4)
document.showimage2.visibility="show"
}

function hideboxdebit2(){
crossobj=ns6? document.getElementById("showimage2debit") : document.all.showimagedebit2
if (ie4||ns6)
crossobj.style.visibility="hidden"
else if (ns4)
document.showimage2.visibility="hide"
}


function abc()
{
   var obj1=document.hris.MSDEST;
   var ins_status=document.hris.INS_STATUS;
   var obj2=document.hris.INS_LOCATION;

        for(m=obj2.length-1; m>=1; m--)
        {
            obj2.remove(m) ;
        }
    alert(obj1.length);
    var m=parseInt(obj2.options.length);
   for(p=0;p<obj1.length;p++)
   {
       insloc=obj1[p].value;
       locins=insloc.split("-");
       status=locins[0];
       loc=locins[1];
       if(ins_status.value==4)
       {
          if(status==40)
          {
             obj2.options[m-1] = new Option(loc,loc);
             m++;
          }
       }
       if(ins_status.value==1)
       {
          if(status==20)
          {
             alert(loc);
             alert(m);
             obj2.options[m-1] = new Option(loc,loc);
             m++;
          }
       }
       
   }
    
}

function remove()
{
    
var select = document.hris.INS_LOCATION;
var length = select.options.length;
for (i = 0;i<length; i++) {
  select.remove(i)
}

}

function callnew(idins,id1,id2,id3,id4,insid)
{
    idinsv=idins.value;
    var selobj=document.getElementById(id1);
     var selobj1=document.getElementById(id2);
     var insloc=document.getElementById(id3);
      var insloclist=document.getElementById(id4);
      var insloclisttemp=document.hris.REJ;
     var insidnew=document.getElementById(insid);
      var insloctallwithrej=document.hris.MSDEST;
    //abc();
   if(idinsv==4)
    {
       for(k=selobj.length-1; k>=1; k--)
       {
            selobj.remove(k) ;
       }
        
        for(k=insloc.length-1; k>=1; k--)
        {
            insloc.remove(k) ;
        }
         for(k=insidnew.length-1; k>=1; k--)
        {
            insidnew.remove(k) ;
        }
         if(insloctallwithrej.length>0)
                {
                   for(p=0; p<insloctallwithrej.length; p++)
                  {
                    insloc=insloctallwithrej[p].value;
                    locins=insloc.split("-");
                    status=locins[0];
                    loc=locins[1];
                    if(status==40)
                    {
                       insidnew.options[insidnew.options.length] = new Option(loc,loc);
                     
                    }
                
                }
                }else{
                    insloc=insloctallwithrej.value;
                    locins=insloc.split("-");
                    status=locins[0];
                    loc=locins[1];
                    if(status==40)
                    {
                        insidnew.options[insidnew.options.length] = new Option(loc,loc);
                       
                    }
                    
                }
           
           
           
          if(insloclisttemp.length){
             for(k=0; k<insloclisttemp.length; k++)
             {
                insloc.options[insloc.options.length] = new Option(insloclisttemp[k].value,insloclisttemp[k].value);
              }
          
        }else{
               
            // insloc.options[insloc.options.length] = new Option(document.hris.REJ.value,document.hris.REJ.value);
           alert('okk');
           
            
        }

    }
    else{
        for(k=selobj.length-1; k>=1; k--)
        {
            selobj.remove(k) ;
        }

                 for(k=1; k<selobj1.options.length; k++)
             {
               
               selobj.options[selobj.options.length] = new Option(selobj1.options[k].text,selobj1.options[k].value);

             }
             
               for (k = insidnew.length - 1; k >= 1; k--)
        {
            insidnew.remove(k);
        }
             if(insloctallwithrej.length>0)
                {
                 for(p=0;p<insloctallwithrej.length;p++)
                {
                    insloc=insloctallwithrej[p].value;
                    locins=insloc.split("-");
                    status=locins[0];
                    loc=locins[1];
                    if(status==20)
                    {
                       insidnew.options[insidnew.options.length] = new Option(loc,loc);
                     
                    }
                
                }
                }else{
                    insloc=insloctallwithrej.value;
                    locins=insloc.split("-");
                    status=locins[0];
                    loc=locins[1];
                    if(status==20)
                    {
                        insidnew.options[insidnew.options.length] = new Option(loc,loc);
                       
                    }
                    
                }
              /*  for(k=insloc.length-1; k>=1; k--)
                    {
                        insloc.remove(k) ;
                    }
             for(k=1; k<insloclist.options.length; k++)
             {
               a=insloclist.options[k].value;
               if(a.indexOf("INS")>0)
               {
                  alert("bbbb")
                  insloc.options[insloc.options.length] = new Option(insloclist.options[k].text,insloclist.options[k].value);
               }
             }*/

    }

}



function fillin(mytextid,selectid)
{
var mytext =document.getElementById(mytextid).value;
sellength = document.getElementById(selectid).length; //Mainform.wholetext.length;
objselect=document.getElementById(selectid);
var m;

for(m = 0; m<sellength; m++)
{
   if (objselect.options[m].text.toLowerCase().indexOf(mytext.toLowerCase(),0) == 0)
     { objselect.options[m].selected = true;
     break;}
}

}

function filltext(mytextid,selectid)
{
objselect=document.getElementById(selectid);

var selectedItem = objselect.selectedIndex;
var selectedText = objselect.options[selectedItem].text;

document.getElementById(mytextid).value = selectedText;
}