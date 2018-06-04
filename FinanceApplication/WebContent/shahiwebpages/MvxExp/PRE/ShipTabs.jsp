<%-- 
    Document   : InvLineDetails
    Created on : Mar 19, 2015, 10:50:59 AM
    Author     : Sanjeev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" 	href="<%=request.getContextPath() %>/css/bootstrap.min.css">
	<link rel="stylesheet" 	href="<%=request.getContextPath() %>/jquery-ui/jquery-ui.css">
	<link rel="stylesheet" 	href="<%=request.getContextPath() %>/shahicss/common.css">

	<script src="<%=request.getContextPath() %>/js/jquery-3.2.0.min.js"></script>
	<script src="<%=request.getContextPath() %>/jquery-ui/jquery-ui.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>	
	<script>
  $( function() {
    $( "#tabs" ).tabs();
  } );
  </script>
<title>JSP Page</title>

</head>
<body>
   <div id="tabs">
  <ul>
    <li><a href="#tabs-1">Header</a></li>
    <li><a href="#tabs-2">Billing Details</a></li>
    <li><a href="#tabs-3">Accessories Details</a></li>
    <li><a href="#tabs-4">Licence Details</a></li>
    <li><a href="#tabs-5">COLine/GST</a></li>
  </ul>
  <div id="tabs-1">
  <iframe src="PREINVMVX.action?aausrid=227350" width="100%" height="100%"></iframe>
  </div>
  <div id="tabs-2">
  <iframe src="prelinepricePREINVMVX.action" width="100%" height="100%"></iframe>
  </div>
  <div id="tabs-3">
  <iframe src="PreInvAccr.jsp" width="100%" height="100%" height="100%"></iframe>
   </div>
  <div id="tabs-4">
  <iframe src="PreInvLicence.jsp" width="100%" height="100%" height="100%"></iframe>
  </div>
   <div id="tabs-5">
   <iframe src="PreInvLineDetails.jsp" width="100%" height="100%" height="100%"></iframe>
  </div>
</div>

</body>
</html>
