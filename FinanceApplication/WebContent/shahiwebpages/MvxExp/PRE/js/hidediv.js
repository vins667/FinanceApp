$(document).ready(function () {
    $('#create_pdf_form').submit(function () {
       document.getElementById('pag').value='A';
       if(validate())
    		blockUIForDownload();
    	else
    	  return false;
    });    
  });





  var fileDownloadCheckTimer;
  function blockUIForDownload() {
    var token = new Date().getTime(); //use the current timestamp as the token value
    $('#download_token_value_id').val(token);
    $.blockUI({ message: '<img src="images/please_wait.gif" /> Just a moment...' });
    fileDownloadCheckTimer = window.setInterval(function () {
    var cookieValue = $.cookie('fileDownloadToken');
    //alert(cookieValue);
      if (cookieValue == token)
       finishDownload();
    }, 1000);
  }
  
  function finishDownload() {
	  window.clearInterval(fileDownloadCheckTimer);
	  document.getElementById('pag').value='P';
	  $.removeCookie('fileDownloadToken'); //clears this cookie value
	  $.unblockUI();
	  //alert('after download');
	 }
  