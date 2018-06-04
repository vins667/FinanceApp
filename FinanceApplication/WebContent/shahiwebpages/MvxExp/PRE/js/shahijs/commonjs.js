function addnewoption(id,newid){

    var a = id.value;
    var b = document.getElementsByName(a);
    var selobj=document.getElementById(newid);
    for(i=selobj.length-1; i>=1; i--)
    {
     selobj.remove(i) ;
    }
    for(i=0; i<b.length; i++)
    {
         var op=document.createElement('option');
         op.text=b[i].value;
         op.value=b[i].value;
         selobj.options[selobj.options.length] = new Option(b[i].value, b[i].value);

    }




}


function showKeyCode(e)
{

var keycode =(window.event) ? event.keyCode : e.keyCode;

if(keycode == 116)
{
event.keyCode = 0;
event.returnValue = false;
return false;
}
}