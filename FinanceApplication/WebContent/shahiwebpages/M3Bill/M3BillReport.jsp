<%-- 
    Document   : M3BillReport
    Created on : Jan 2, 2014, 2:36:50 PM
    Author     : RANJEET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<link rel="stylesheet" type="text/css" href="js/monthyearpicker/css/StyleCalender.css"> 
<script type="text/javascript" language="javascript" src="js/monthyearpicker/js/CalendarControl.js"></script>

<html >
    <head>
        <s:head/>
        <sx:head/>
         <script src="js/jquery-1.5.2.min.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sepl</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>
        <script language="javascript">
        $(document).ready(function() {
    $('#btnRight').click(function(e) {
        var selectedOpts = $('#leftDepartment option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#rightDepartment').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
         gettype('rightDepartment','show_detail');
    });

    $('#btnLeft').click(function(e) {
        var selectedOpts = $('#rightDepartment option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#leftDepartment').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
        
        removeallpro('#rightBill');
        removeallpro('#rightsubbill');
        removeallpro('#rightcost');
        removeallpro('#rightproduct');
         gettype('rightDepartment','show_detail');
    });

$('#btnRightPCH').click(function(e) {
        var selectedOpts = $('#leftPCH option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#rightPCH').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
         
    });

    $('#btnLeftPCH').click(function(e) {
        var selectedOpts = $('#rightPCH option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#leftPCH').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
        
    });


$('#add').click(function() {
     var $leftBill = $('#leftBill');
       $rightBill = $('#rightBill');
    
   $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
}); 
    var $el = $('#leftBill option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $rightBill.find('optgroup[label="' + groupName + '"]');
    
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($rightBill);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
    gettype('rightBill','show_detailsub');
    gettype('rightcost','show_detailcc');
    return false;
    
     
});
$('#remove').click(function() {
     var $leftBill = $('#leftBill');
       $rightBill = $('#rightBill');
      $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
});
    var $el = $('#rightBill option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $leftBill.find('optgroup[label="' + groupName + '"]');
   
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($leftBill);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
    
    removeallpro('#rightsubbill');
    removeallpro('#rightcost');
    removeallpro('#rightproduct');
    gettype('rightBill','show_detailsub');
    gettype('rightsubbill','show_detailpro');
    gettype('rightcost','show_detailcc');
    
    return false;
    
     
});

$('#addsub').click(function() {
     var $leftsubbill = $('#leftsubbill');
       $rightsubbill = $('#rightsubbill');
    
   $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
}); 
    var $el = $('#leftsubbill option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $rightsubbill.find('optgroup[label="' + groupName + '"]');
    
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($rightsubbill);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
    gettype('rightsubbill','show_detailpro');
    return false;
    
     
});
$('#removesub').click(function() {
     var $leftsubbill = $('#leftsubbill');
       $rightsubbill = $('#rightsubbill');
      $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
});
    var $el = $('#rightsubbill option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $leftsubbill.find('optgroup[label="' + groupName + '"]');
    
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($leftsubbill);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
    removeallpro('#rightproduct');
    gettype('rightsubbill','show_detailpro');
    return false;
    
     
});



$('#addcc').click(function() {
     var $leftcost = $('#leftcost');
       $rightcost = $('#rightcost');
    
   $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
}); 
    var $el = $('#leftcost option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $rightcost.find('optgroup[label="' + groupName + '"]');
    
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($rightcost);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
   
    return false;
    
     
});
$('#removecc').click(function() {
     var $leftcost = $('#leftcost');
       $rightcost = $('#rightcost');
      $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
});
    var $el = $('#rightcost option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $leftcost.find('optgroup[label="' + groupName + '"]');
    
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($leftcost);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
   
    return false;
    
     
});




$('#addpro').click(function() {
     var $leftproduct = $('#leftproduct');
       $rightproduct = $('#rightproduct');
    
   $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
}); 
    var $el = $('#leftproduct option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $rightproduct.find('optgroup[label="' + groupName + '"]');
    
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($rightproduct);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
   
    return false;
    
     
});
$('#removepro').click(function() {
     var $leftproduct = $('#leftproduct');
       $rightproduct = $('#rightproduct');
      $('option').each(function(){
    $(this).data('optgroup', $(this).parent().attr('label'));
});
    var $el = $('#rightproduct option:selected'),
        groupName = $el.data('optgroup'),
        $parent = $el.parent(),
        $optgroup = $leftproduct.find('optgroup[label="' + groupName + '"]');
    
    if ( ! $el.length ) return false;
    
    if ( ! $optgroup.length ) $optgroup = $('<optgroup label="' + $el.data('optgroup') + '" />').appendTo($leftproduct);
    
    $el.appendTo( $optgroup );
    
    if ( ! $parent.children().length ) $parent.remove();
    
   
    return false;
    
     
});


$('#addware').click(function(e) {
        var selectedOpts = $('#leftwarehouse option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#warehouse').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
         
    });

    $('#removeware').click(function(e) {
        var selectedOpts = $('#warehouse option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#leftwarehouse').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        e.preventDefault();
        
    });

});

function gettype(a,b)
{
    
	allselect(a);
	dojo.event.topic.publish(b);
	
}
function allselect(item){
                		
                var toSelect_Length = document.getElementById(item).options.length;
               for(i=0; i<toSelect_Length; i++)
                   {
                       document.getElementById(item).options[i].selected=true;
                       
                   }
}


function removeallpro(a)
{
     $(a).empty();

}

function ongoprint()
{               
                 var a = document.getElementById('rightDepartment').options.length;
               if(a>0)
                   {
                        allselect('rightPCH');
                        allselect('warehouse');
                        allselect('rightDepartment');
                        allselect('rightcost');
                        allselect('rightproduct');
                        allselect('rightBill');
                        allselect('rightsubbill');
                        document.getElementById('formId').action='printpdfmbillreportAction.action';
                        document.getElementById('formId').submit();
                   }else{
                       alert("Please Select Department");
                       return false;
                   }
}

</script>
    </head>
    <body style="margin:0px;background-color: #f2f2f2;">
        <form method="POST" id="formId" name="frmname" action=""> 
           
         <table style="background-color: #f2f2f2;width: 100%;" cellpadding="0" cellspacing="0">    
            <tr>
              <td style="width:100%;height: 25px;text-align: center" colspan="10" class="hd" > M4 Bill Report</td></tr>
             
            
            <tr>
                  
              <table>
                  <tr >
                      <td colspan="5"> <table><tr><td class="label-1">Month </td><td>
                                 <s:textfield  theme="simple" name="BILL_DATE1"   readonly="true" cssClass="textreadonly" id="BILL_DATE1" value="%{BILL_DATE1}" cssStyle="width:98px;" />
                                 </td> <td>
                                       <img alt="Month/Year Picker" onclick="showCalendarControl('BILL_DATE1');" 
				src="js/monthyearpicker/images/datepicker.gif" />
                                 </td><td>&nbsp;-&nbsp;
                                  </td><td>
                                  <s:textfield  id="BILL_DATE2" name="BILL_DATE2" theme="simple" readonly="true" cssClass="textreadonly"  value="%{BILL_DATE2}" cssStyle="width:98px;" />
                                   </td><td>
                                       <img alt="Month/Year Picker" onclick="showCalendarControl('BILL_DATE2');" 
				src="js/monthyearpicker/images/datepicker.gif" />
                                               </td>
                                               
                                               <td width="400px" class="label-1">&nbsp;&nbsp;&nbsp;&nbsp;
                                                    MIS Month
                                                    <input type="radio" checked="true" name="monthtype" value="M"/>
                                                   Bill Month
                                                   <input type="radio" name="monthtype" value="B"/>
                                               </td>
                              
                              </tr></table>
                     </td>
                  </tr>
                  <tr><td class="label-1">Available Department</td><td></td><td class="label-1">Assign Department</td>
                  
                   <td class="label-1">Available Bill Type</td><td></td><td class="label-1">Assign Bill Type</td>
                  </tr> 
                  <tr><td><s:select name="leftDepartment"
                            id="leftDepartment"
                            list="m3billdeptlist"
                            listKey="EAAITM"
                            listValue="EATX40"
                            theme="simple"
                            cssClass="selecttext"
                            cssStyle="width:300px"
                            size="8"
                            multiple="true"
                            />
                            </td>
                            <td  style="width:50px" align="center">
                                    <a href="#" id="btnRight" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="btnLeft"  style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                            <td>
                                <select id="rightDepartment"
                                        size="8"  name="rightDepartment"
                                        style="width:300px"
                                        class="selecttext"
                                         multiple="true"
                            </select>
                            </td>
                            
                 <td>
                         <s:url id="url"  action="getbilltypembillreportAction.action" />
                         <sx:div id="details"  href="%{url}"  cssStyle="width:300px" listenTopics="show_detail" formId="formId" showLoadingText="Loading ......"></sx:div>
   
                         
                     </td><td  style="width:50px" align="center">
                                    <a href="#" id="add" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="remove"  style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                     <td>
                                <select id="rightBill"
                                        size="8" 
                                        name="rightBill"
                                        style="width:300px"
                                        class="selecttext"
                                        multiple="true">
                            </select>
                            </td>
                 </tr>
                  
                    <tr>
                        <td class="label-1">Available Bill Sub Type</td><td></td><td class="label-1">Assign Bill Sub Type</td>
                        <td class="label-1">Available PCH</td><td></td><td class="label-1">Assign PCH</td></tr> 
               
                     <tr>
                         <td>
                         <s:url id="urlsub"  action="getbillsubtypembillreportAction.action" />
                         <sx:div id="detailssub"  href="%{urlsub}"  cssStyle="width:300px" listenTopics="show_detailsub" formId="formId" showLoadingText="Loading ......"></sx:div>
   
                         
                     </td><td  style="width:50px" align="center">
                                    <a href="#" id="addsub" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removesub"  style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                     <td>
                                <select id="rightsubbill"
                                        size="8" 
                                        name="rightsubbill"
                                        style="width:300px"
                                        class="selecttext"
                                        multiple="true">
                            </select>
                            </td>
                         
                         
                         <td><s:select name="leftPCH"
                            id="leftPCH"
                            list="m3pchtypelist"
                            listKey="EAAITM"
                            listValue="EATX40"
                            theme="simple"
                            cssClass="selecttext"
                            cssStyle="width:300px"
                            size="8"
                            multiple="true"
                            />
                            </td>
                            <td  style="width:50px" align="center">
                                    <a href="#" id="btnRightPCH" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="btnLeftPCH"  style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                            <td>
                                <select id="rightPCH"
                                        size="8"  name="rightPCH"
                                        style="width:300px"
                                        class="selecttext"
                                         multiple="true"
                            </select>
                            </td>
                            
                  </tr>
                  
                
              
                  <tr><td class="label-1">Available Cost Center</td><td></td><td class="label-1">Assign Cost Center</td>
                  <td class="label-1">Available Product</td><td></td><td class="label-1">Assign Product</td>
                  </tr> 
                 <tr><td>
                         <s:url id="urlcc"  action="getcostcentertypembillreportAction.action" />
                         <sx:div id="detailscc"  href="%{urlcc}"  cssStyle="width:300px" listenTopics="show_detailcc" formId="formId" showLoadingText="Loading ......"></sx:div>
   
                         
                     </td><td  style="width:50px" align="center">
                                    <a href="#" id="addcc" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removecc"  style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                     <td>
                                <select id="rightcost"
                                        size="8" 
                                        name="rightcost"
                                        style="width:300px"
                                        class="selecttext"
                                        multiple="true">
                            </select>
                            </td>
                 <td>
                         <s:url id="urlpro"  action="getproducttypembillreportAction.action" />
                         <sx:div id="detailspro"  href="%{urlpro}"  cssStyle="width:300px" listenTopics="show_detailpro" formId="formId" showLoadingText="Loading ......"></sx:div>
   
                         
                     </td><td  style="width:50px" align="center">
                                    <a href="#" id="addpro" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removepro"  style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                     <td>
                                <select id="rightproduct"
                                        size="8" 
                                        name="rightproduct"
                                        style="width:300px"
                                        class="selecttext"
                                        multiple="true">
                            </select>
                            </td>
                 </tr>
                 <tr>
                     
                     <td class="label-1">Available Warehouse</td><td></td><td class="label-1">Assign Warehouse</td>
                 </tr><tr>
                     <td class="label-1">
                           <s:select 
                             name="leftwarehouse" 
                             id="leftwarehouse"
                             list="warehouselist"
                             listKey="WHLO"
                             listValue="WHNM"
                             size="8"
                             theme="simple"
                              cssClass="selecttext"
                              cssStyle="width:300px"
                              multiple="true"
                             />
                     </td>
                     <td  style="width:50px" align="center">
                                    <a href="#" id="addware" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeware"  style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                     <td>
                                <select id="warehouse"
                                        size="8" 
                                        name="warehouse"
                                        style="width:300px"
                                        class="selecttext"
                                        multiple="true">
                            </select>
                            </td>
                            
                             
                            <td class="label-1" colspan="2 " align="right">Output&nbsp;Format</td>
                                <td  align="center"  class="label-1" >
                                    <input type="radio" name="REPORTTYPE" value="PDF" CHECKED>
                                    &nbsp;Pdf&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="REPORTTYPE" value="XLS">&nbsp;Excel 2003 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="REPORTTYPE" value="XLSX">&nbsp;Excel 2007
                                </td>
                            </tr>

                 </tr>
              </table>
              <br>
              <table align="center"><tr>
                      <td>
                           <button onclick="self.close()" class="sexybutton"><span><span><span class="cancel">Cancel</span></span></span></button>
                         
                           &nbsp;
                          <button onclick="window.location.href='mbillreportAction.action'" class="sexybutton"><span><span><span class="reload">Reset</span></span></span></button>
                            &nbsp;
                            <button onclick="ongoprint()" class="sexybutton"><span><span><span class="download">Finish</span></span></span></button>
                         
                      </td></tr></table>
        </form>
    </body>
</html>
