/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function waitPreloadPage() { //DOM
    if (document.getElementById) {
        document.getElementById('prepage').style.visibility = 'hidden';
    }
    else {
        if (document.layers) { //NS4
            document.prepage.visibility = 'hidden';
        }
        else { //IE4
            document.all.prepage.style.visibility = 'hidden';
        }
    }
}

function onSearch(url)
{
    document.frm.action = url;
    document.frm.submit();
    document.getElementById('prepage').style.visibility = '';
}

function onvalidateSearch(url)
{
    if (validate()) {
        document.frm.action = url;
        document.frm.submit();
        document.getElementById('prepage').style.visibility = '';
    }
}

function onDelete(url)
{
    document.frm.action = url;
    document.frm.submit();
    document.getElementById('prepage').style.visibility = '';
}

function onNew(url) 
{
    window.location.href = url;
    document.getElementById('prepage').style.visibility = '';
}

function onsave(url) {
    if (validate()) {
        if (confirm('Do you want to save!!!')) {
            document.frm.action = url;
            document.frm.submit();
            document.getElementById('prepage').style.visibility = '';
        }
    }
}
function onsavesign(url) {
    if (validate()) {
            document.frm.action = url;
            document.frm.submit();
            document.getElementById('prepage').style.visibility = '';
     }
}

function onback(url) {
    window.location.href = url;
    document.getElementById('prepage').style.visibility = '';
}

function openpop(frame, url, a, id, b)
{
    document.getElementById(frame).src = url;
    document.getElementById(a).style.display = '';
    document.getElementById(id).innerHTML = b;
}

function closediv(a)
{
    document.getElementById(a).style.display = 'none';
}

function onsubload(url) {
    document.frm.action = url;
    document.frm.submit();
    document.getElementById('prepage').style.visibility = '';
}

function validateNonumber(a)
{
    k = a.value;
    if (k != "" && !k.match(/^\d+$|^\d+\$/))
    {
        alert('Invalid Input, Only Numbers Allowed');
        a.value = "";
        a.focus();
        return false;
    }
    return true;
}

function validateTwonumber(a)
{
    k = a.value;
    if (k != "" && !k.match(/^\d+$|^\d+\.\d{1,2}$/))
    {
        alert('Invalid Input, Only Numbers Allowed');
        a.value = "";
        a.focus();
        return false;
    }
    return true;
}
function onvalsavebtn(url) {
    if (validatesave()) {
        if (confirm('Do you want to save!!!')) {
            document.frm.action = url;
            document.frm.submit();
            document.getElementById('prepage').style.visibility = '';
        }
    }
}