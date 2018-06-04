

function removeValue(listbox)
{
	var x = listbox.selectedIndex;
	 if(x>= null)
	  {
	 for(i=listbox.options.length-1;i>=0;i--)
	  {
	   if (listbox.options[i].selected)
	     {
	       listbox.remove(i);
	      }
	  }
	}
	else
	alert("Select Any Value To Remove");
	}
function addValue(textbox,listbox)
{
	var optiontag = document.createElement("option")
	optiontag.text = textbox.value;
	optiontag.value = textbox.value;
	listbox.options.add(optiontag);
	textbox.value="";
	optiontag.selected=true;
	
} 
