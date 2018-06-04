<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Collection"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.JRParameter"%> 
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="shahi.Action.database.connection"%>
<%@page import="java.sql.Connection"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shahi Exports Pvt Ltd</title>
</head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Shahi Exports Pvt Ltd.</title>
      
        <link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>

 
		<script language="javascript" type="text/javascript" src = "script/function.js" ></script>
   		<script type="text/javascript" src="script/jquery.1.4.2.js"></script>
		<script type="text/javascript" src="script/jquery_blockUI.js"></script>
		<script type="text/javascript" src="script/jquery_cookie.js"></script>
		<script type="text/javascript" src="script/hidediv.js"></script>     
       
        <link rel="stylesheet" type="text/css" media="all" href="style/jsDatePick_ltr.min.css" />
        <link href="style/style.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="script/jsDatePick.min.1.3.js"></script>
			        
   <script type="text/javascript" language="javascript" >     
        window.onload = function()
	  {
        new JsDatePick({ 
            useMode:2,
            target:"date1",
            dateFormat:"%d/%m/%Y",
            imgPath:"/img/"
        });
        new JsDatePick({
            useMode:2,
            target:"date2",
            dateFormat:"%d/%m/%Y",
            imgPath:"/img/"
        });
    };
    
   
    function search()
    {
    	if(validate()==true)
    	{
    	document.create_pdf_form.action="SalesReportAction";
    	document.create_pdf_form.submit();
    	}
    	return true;
    }
    
		function validate() 
		{
			DATE_FROM=document.getElementById("date1");
		    DATE_TO=document.getElementById("date2");
		    	
		    	if(DATE_FROM.value==''){
		    		alert("'From' Date cannot be empty");
		    		return false;
		    	}
		    	
		    	if(DATE_TO.value==''){
		    		alert("'To' Date cannot be empty");
		    		return false;
		    	}
		    	insertPrcGrp();
		    	insertCountry();
		    	insertCurrency();
		    	insertMode();
		    	insertBuyer();
                        insertPCH();
		    	return true;
			
		} 
    
    function addPrcGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PRC_GRPLIST.options.length;
        while(document.create_pdf_form.PRC_GRP.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PRC_GRP.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PRC_GRPLIST.options[i].value ==document.create_pdf_form.PRC_GRP.options[index].value)
                {
                    alert(document.create_pdf_form.PRC_GRP.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PRC_GRPLIST.options[toSelect_Length] = new Option(document.create_pdf_form.PRC_GRP.options[index].text);
            document.create_pdf_form.PRC_GRPLIST.options[toSelect_Length].value =document.create_pdf_form.PRC_GRP.options[index].value;    
            document.create_pdf_form.PRC_GRP.options[index] = null;
            toSelect_Length++;    
        } 
    }
    

    function removePrcGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PRC_GRP.options.length;
        while(document.create_pdf_form.PRC_GRPLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PRC_GRPLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PRC_GRP.options[i].value ==document.create_pdf_form.PRC_GRPLIST.options[index].value)
                {
                    alert(document.create_pdf_form.PRC_GRPLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PRC_GRP.options[toSelect_Length] = new Option(document.create_pdf_form.PRC_GRPLIST.options[index].text);
            document.create_pdf_form.PRC_GRP.options[toSelect_Length].value =document.create_pdf_form.PRC_GRPLIST.options[index].value;    
            document.create_pdf_form.PRC_GRPLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertPrcGrp(){  
        var tnl = document.getElementById("PRC_GRPLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
    
    
    function addCountry()
    {
    	var toSelect_Length = document.create_pdf_form.COUNTRYLIST.options.length;
        while(document.create_pdf_form.COUNTRY.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.COUNTRY.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.COUNTRYLIST.options[i].value ==document.create_pdf_form.COUNTRY.options[index].value)
                {
                    alert(document.create_pdf_form.COUNTRY.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.COUNTRYLIST.options[toSelect_Length] = new Option(document.create_pdf_form.COUNTRY.options[index].text);
            document.create_pdf_form.COUNTRYLIST.options[toSelect_Length].value =document.create_pdf_form.COUNTRY.options[index].value;    
            document.create_pdf_form.COUNTRY.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removeCountry()
    {
    	var toSelect_Length = document.create_pdf_form.COUNTRY.options.length;
        while(document.create_pdf_form.COUNTRYLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.COUNTRYLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.COUNTRY.options[i].value ==document.create_pdf_form.COUNTRYLIST.options[index].value)
                {
                    alert(document.create_pdf_form.COUNTRYLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.COUNTRY.options[toSelect_Length] = new Option(document.create_pdf_form.COUNTRYLIST.options[index].text);
            document.create_pdf_form.COUNTRY.options[toSelect_Length].value =document.create_pdf_form.COUNTRYLIST.options[index].value;    
            document.create_pdf_form.COUNTRYLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }


    function insertCountry(){  
        var tnl = document.getElementById("COUNTRYLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
    
    function addCurrency()
    {
    	var toSelect_Length = document.create_pdf_form.CURRENCYLIST.options.length;
        while(document.create_pdf_form.CURRENCY.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.CURRENCY.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.CURRENCYLIST.options[i].value ==document.create_pdf_form.CURRENCY.options[index].value)
                {
                    alert(document.create_pdf_form.CURRENCY.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.CURRENCYLIST.options[toSelect_Length] = new Option(document.create_pdf_form.CURRENCY.options[index].text);
            document.create_pdf_form.CURRENCYLIST.options[toSelect_Length].value =document.create_pdf_form.CURRENCY.options[index].value;    
            document.create_pdf_form.CURRENCY.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    
    function removeCurrency()
    {
    	var toSelect_Length = document.create_pdf_form.CURRENCY.options.length;
        while(document.create_pdf_form.CURRENCYLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.CURRENCYLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.CURRENCY.options[i].value ==document.create_pdf_form.CURRENCYLIST.options[index].value)
                {
                    alert(document.create_pdf_form.CURRENCYLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.CURRENCY.options[toSelect_Length] = new Option(document.create_pdf_form.CURRENCYLIST.options[index].text);
            document.create_pdf_form.CURRENCY.options[toSelect_Length].value =document.create_pdf_form.CURRENCYLIST.options[index].value;    
            document.create_pdf_form.CURRENCYLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertCurrency(){  
        var tnl = document.getElementById("CURRENCYLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
  
    function addMode()
    {
    	var toSelect_Length = document.create_pdf_form.MODELIST.options.length;
        while(document.create_pdf_form.MODE.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.MODE.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.MODELIST.options[i].value ==document.create_pdf_form.MODE.options[index].value)
                {
                    alert(document.create_pdf_form.MODE.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.MODELIST.options[toSelect_Length] = new Option(document.create_pdf_form.MODE.options[index].text);
            document.create_pdf_form.MODELIST.options[toSelect_Length].value =document.create_pdf_form.MODE.options[index].value;    
            document.create_pdf_form.MODE.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removeMode()
    {
    	var toSelect_Length = document.create_pdf_form.MODE.options.length;
        while(document.create_pdf_form.MODELIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.MODE.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.MODE.options[i].value ==document.create_pdf_form.MODELIST.options[index].value)
                {
                    alert(document.create_pdf_form.MODELIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.MODE.options[toSelect_Length] = new Option(document.create_pdf_form.MODELIST.options[index].text);
            document.create_pdf_form.MODE.options[toSelect_Length].value =document.create_pdf_form.MODELIST.options[index].value;    
            document.create_pdf_form.MODELIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertMode(){  
        var tnl = document.getElementById("MODELIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
    
    function addBuyer()
    {
    	var toSelect_Length = document.create_pdf_form.BUYERLIST.options.length;
        while(document.create_pdf_form.BUYER.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.BUYER.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.BUYERLIST.options[i].value ==document.create_pdf_form.BUYER.options[index].value)
                {
                    alert(document.create_pdf_form.BUYER.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.BUYERLIST.options[toSelect_Length] = new Option(document.create_pdf_form.BUYER.options[index].text);
            document.create_pdf_form.BUYERLIST.options[toSelect_Length].value =document.create_pdf_form.BUYER.options[index].value;    
            document.create_pdf_form.BUYER.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removeBuyer()
    {
    	var toSelect_Length = document.create_pdf_form.BUYER.options.length;
        while(document.create_pdf_form.BUYERLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.BUYERLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.BUYER.options[i].value ==document.create_pdf_form.BUYERLIST.options[index].value)
                {
                    alert(document.create_pdf_form.BUYERLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.BUYER.options[toSelect_Length] = new Option(document.create_pdf_form.BUYERLIST.options[index].text);
            document.create_pdf_form.BUYER.options[toSelect_Length].value =document.create_pdf_form.BUYERLIST.options[index].value;    
            document.create_pdf_form.BUYERLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertBuyer(){  
        var tnl = document.getElementById("BUYERLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
  
    function addPCH()
    {
    	var toSelect_Length = document.create_pdf_form.PCHLIST.options.length;
        while(document.create_pdf_form.PCH.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PCH.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PCHLIST.options[i].value ==document.create_pdf_form.PCH.options[index].value)
                {
                    alert(document.create_pdf_form.PCH.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PCHLIST.options[toSelect_Length] = new Option(document.create_pdf_form.PCH.options[index].text);
            document.create_pdf_form.PCHLIST.options[toSelect_Length].value =document.create_pdf_form.PCH.options[index].value;    
            document.create_pdf_form.PCH.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removePCH()
    {
    	var toSelect_Length = document.create_pdf_form.PCH.options.length;
        while(document.create_pdf_form.PCHLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PCHLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PCH.options[i].value ==document.create_pdf_form.PCHLIST.options[index].value)
                {
                    alert(document.create_pdf_form.PCHLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PCH.options[toSelect_Length] = new Option(document.create_pdf_form.PCHLIST.options[index].text);
            document.create_pdf_form.PCH.options[toSelect_Length].value =document.create_pdf_form.PCHLIST.options[index].value;    
            document.create_pdf_form.PCHLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertPCH(){  
        var tnl = document.getElementById("PCHLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
  
   
   
</script>
 
<s:if test="%{abc==null}">
<%
	
	if(request.getMethod()=="POST")
	{
		 Connection con = null;
                 con = new connection().getConnection();

		try
		{ 
			String output=request.getParameter("filetype");
			byte[] bytes = null;
            String Qry = " ";
            Map parameters = new HashMap();
            
            if (request.getParameter("filetype").equals("XLS") || request.getParameter("filetype").equals("FITEM")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }                    
            JasperReport report = null;
            String filename="Report";
            
            String Prclist[] = request.getParameterValues("PRC_GRPLIST");
            String saleorderstr = "";
            String saleorderflag="";
            if(Prclist!=null && Prclist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(A.EXP_TYPE) in(";
            	 for(int i=0; i<Prclist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Prclist[i]+"'" ;                            	
                            saleorderflag=""+Prclist[i];
                        }else{
                        	cntr = cntr+",'"+Prclist[i]+"'" ;
                        	saleorderflag = saleorderflag+", "+Prclist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            String Countrylist[] = request.getParameterValues("COUNTRYLIST");
            String str = "";
            String flag="";
            if(Countrylist!=null && Countrylist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(A.CNTRY_ORIGIN) in(";
            	 for(int i=0; i<Countrylist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Countrylist[i]+"'" ;                            	
                        	flag=""+Countrylist[i];
                        }else{
                        	cntr = cntr+",'"+Countrylist[i]+"'" ;
                        	flag = flag+", "+Countrylist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            String Currencylist[] = request.getParameterValues("CURRENCYLIST");
            String Str = "";
            String flag1="";
            if(Currencylist!=null && Currencylist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(A.CRNCY_CODE) in(";
            	 for(int i=0; i<Currencylist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Currencylist[i]+"'" ;                            	
                        	flag1=""+Currencylist[i];
                        }else{
                        	cntr = cntr+",'"+Currencylist[i]+"'" ;
                        	flag1 = flag1+", "+Currencylist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            
            
            String Modelist[] = request.getParameterValues("MODELIST");
            String Str1 = "";
            String flag2="";
            if(Modelist!=null && Modelist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(A.MODE_OF_SHIP) in(";
            	 for(int i=0; i<Modelist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Modelist[i]+"'" ;                            	
                        	flag2=""+Modelist[i];
                        }else{
                        	cntr = cntr+",'"+Modelist[i]+"'" ;
                        	flag2 = flag2+", "+Modelist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            String Buyerlist[] = request.getParameterValues("BUYERLIST");
            String Str2 = "";
            String flag3="";
            if(Buyerlist!=null && Buyerlist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(A.BUYER) in(";
            	 for(int i=0; i<Buyerlist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Buyerlist[i]+"'" ;                            	
                        	flag3=""+Buyerlist[i];
                        }else{
                        	cntr = cntr+",'"+Buyerlist[i]+"'" ;
                        	flag3 = flag3+", "+Buyerlist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            String pchlist[] = request.getParameterValues("PCHLIST");
              String flag4="";
            if(pchlist!=null && pchlist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(A.cost_centre) in(";
            	 for(int i=0; i<pchlist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+pchlist[i]+"'" ;                            	
                        	flag4=""+pchlist[i];
                        }else{
                        	cntr = cntr+",'"+pchlist[i]+"'" ;
                        	flag4 = flag4+", "+pchlist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
              String gitm[] = request.getParameterValues("garmentItemList");
              String flag5="";
            if(gitm!=null && gitm.length>0)
            {
            	String cntr=null;
            	Qry+=" and substr(b.item_no,1,4) in(";
            	 for(int i=0; i<gitm.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+gitm[i]+"'" ;                            	
                        	flag5=""+gitm[i];
                        }else{
                        	cntr = cntr+",'"+gitm[i]+"'" ;
                        	flag5 = flag5+", "+gitm[i]+"" ;
                        }
                 }
               
            	 Qry+=cntr+" )";
            }
            
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/PRE/";
            if (output.equals("PDF")) { 
             report = (JasperReport) JRLoader.loadObject(path +"salesreport.jasper");
             filename="SalesReport";}
             else if (output.equals("FITEM"))
             {
               report = (JasperReport) JRLoader.loadObject(path +"itemshipcm.jasper");
               filename="FS_ITEM";
            }else{
               report = (JasperReport) JRLoader.loadObject(path +"salesdetail.jasper");
               filename="SalesDetails";
            }
               
             
             String date1 = request.getParameter("date1");
             String date2 = request.getParameter("date2");
             Collection list=null;
             
            parameters.put("SUBREPORT_DIR",path); 
            parameters.put("REPORT_CONNECTION", con);  
            
            SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
            SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yyyy"); 
            parameters.put("date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
            parameters.put("date_to", dateformat1.format(dateformat.parse(request.getParameter("date2")))); 
            parameters.put("p_query",Qry);
        
            parameters.put("p_loct",request.getParameter("LOCT_CODE"));
            parameters.put("p_selftp",request.getParameter("self_tp"));
            parameters.put("p_basedon",request.getParameter("basedon"));
            parameters.put("p_summary",request.getParameter("SUMCH"));
           	//response.getWriter().write(Qry);
              
          	 
            JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
            response.reset();
            ServletOutputStream out1 = response.getOutputStream();
            //response.reset();
            response.addCookie(new Cookie("fileDownloadToken", request.getParameter("download_token_value_id")));
       		if (output.equals("PDF")) {
                response.setHeader("Content-Disposition", "attachment; filename="+filename+".pdf");
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-Modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
                out1.flush();
                out1.close();
            } else {
                JRXlsExporter exporter = new JRXlsExporter();
                ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, "C:\\JSP\\");
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filename+".xls");
                exporter.exportReport();
                bytes = xlsReport.toByteArray();
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename="+filename+".xls;");
                response.setContentLength(bytes.length);
                xlsReport.close();
                OutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			response.getWriter().write(e.getMessage());
		}
	}
%> 
 </s:if>  
<body>
	<form name="create_pdf_form" id="create_pdf_form" action="SalesReport.jsp" method="POST">
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                       Sales Report [M4]
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 500px; background-color: #6699CC;">
                            <tr>
                                 <td class="label-1" style="background-color: white;">Location </td>
                                 <td class="label-1" style="background-color: white;" colspan="3">
                                     <s:select  headerValue="" list="#{'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:220px;font-weight:bold" theme="simple" name="LOCT_CODE" value="%{LOCT_CODE}" /> 
                           
                                 </td> 
                                <td class="label-1" style="background-color: white;" >Invoice Type</td>
                                <td align="left" class="label-1" style="background-color: white;"colspan="3">
                                 <s:select name="self_tp" label="Invoice Type" cssStyle="font-size:10pt;width:200px;font-size:9pt;" theme="simple"   list="#{'%':'ALL','N':'Normal','S':'Trade Sample','F':'Free Sample'}" value="%{self_tp}" />
                                </td>
                                 
                          
                              
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:220px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:150px">Date To </td>
                                <td class="label-1" style="background-color: white;" colspan="2">
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:220px" ></s:textfield>
                                </td>
                                <td class="label-1" style="background-color: white;">
                                   <button  id="searchheadId" class="sexybutton" onclick="search()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                </td>
                            </tr>
                            <tr>
								<td valign="top" class="label-1" style="background-color: white;text-align:left">Proc Grp</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" name="PRC_GRP" id="PRC_GRP" multiple="true" list="%{PROC_LIST}" ondblclick="addPrcGrp();" cssClass="texts" cssStyle="width:100px;height:80px">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addPrcGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removePrcGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="PRC_GRPLIST" multiple="multiple" name="PRC_GRPLIST" class="texts" style="width:100px;height:80px;text-transform: uppercase" ondblclick="removePrcGrp();"> 
                                	</select>
                               </td>
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">Country&nbsp;Of&nbsp;Origin</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" list="%{COUNTRY_LIST}" name="COUNTRY" id="COUNTRY" multiple="true" cssClass="texts" cssStyle="width:100px;height: 80px" ondblclick="addCountry();">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addCountry();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeCountry();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="COUNTRYLIST" multiple="multiple" ondblclick="removeCountry();" name="COUNTRYLIST" class="texts" style="width:100px;height: 80px;text-transform: uppercase"> 
                                	</select>
                               </td>
                            </tr>
                            <tr>
								<td valign="top" class="label-1" style="background-color: white;text-align:left">Currency</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" list="%{CURRENCY_LIST}" name="CURRENCY" id="CURRENCY" multiple="true" cssClass="texts" cssStyle="width:100px;height: 80px" ondblclick="addCurrency();">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addCurrency();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeCurrency();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="CURRENCYLIST" multiple="multiple" name="CURRENCYLIST" class="texts" style="width:100px;height: 100px;text-transform: uppercase" ondblclick="removeCurrency();"> 
                                	</select>
                               </td>
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">Mode&nbsp;of&nbsp;Shipment</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" list="%{MODE_LIST}" name="MODE" id="MODE" multiple="true" cssClass="texts" cssStyle="width:100px;height: 80px" ondblclick="addMode();">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" onclick="addMode();" id="addButton" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeMode();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="MODELIST" multiple="multiple" name="MODELIST" ondblclick="removeMode();" class="texts" style="width:100px;height: 80px;text-transform: uppercase"> 
                                	</select>
                               </td>
                            </tr>
                            <tr>
				<td valign="top" class="label-1" style="background-color: white;text-align:left">Buyer&nbsp;Code</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" list="%{BUYER_LIST}" ondblclick="addBuyer();" name="BUYER" id="BUYER" multiple="true" cssClass="texts" cssStyle="width:100px;height: 80px">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addBuyer();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeBuyer();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;" >                                   
                                    <select id="BUYERLIST" multiple="multiple" ondblclick="removeBuyer();" name="BUYERLIST" class="texts" style="width:100px;height: 80px;text-transform: uppercase"> 
                                	</select>
                               </td>
                                
                               	<td valign="top" class="label-1" style="background-color: white;text-align:left">PCH&nbsp</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" list="%{PCH_LIST}" ondblclick="addPCH();" name="PCH" id="PCH" multiple="true" cssClass="texts" cssStyle="width:100px;height:80px">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addPCH();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removePCH();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;" colspan="5">                                   
                                    <select id="PCHLIST" multiple="multiple" ondblclick="removePCH();" name="PCHLIST" class="texts" style="width:100px;height: 80px;text-transform: uppercase"> 
                                	</select>
                               </td>
                               </tr>
                               <tr> 
                                        <td  valign="top" class="label-1" style="background-color: white;">Item#</td>
                                        <td valign="top" class="label-1" style="background-color: white;width:50px"><input type="text" id="garmentItem" class="texts" style="width:100px;text-transform: uppercase;" onkeypress="return tabEgarmentItem(this,event)"/></td>
                                        <td valign="top" style="background-color: white;width:85px">
                                            <a href="#" id="addgarmentItemButton" onclick="addgarmentItem();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;<img src="../images/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                            <a href="#"  id="removeButtongarmentItem" onclick="removegarmentItem();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;<img src="../images/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                        </td>
                                        <td valign="top" class="label-1" style="background-color: white; width :100px;"><select id="garmentItemList" multiple="multiple" name="garmentItemList" ondblclick="removegarmentItem();" class="texts" style="width:100px;height: 80px;text-transform: uppercase;"> </select> </td>

                                        <td  valign="top" class="label-1" style="background-color: white;"></td>
                                        <td valign="top" class="label-1" style="background-color: white;width:50px"></td>
                                        <td valign="top" style="background-color: white;width:85px"></td>
                                        <td valign="top" class="label-1" style="background-color: white; width : 100px;"></td>                   
                                     
                               </tr>
                               
                             <tr>
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Output&nbsp;Format
                             	</td>
                             	<td colspan="3" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="#{'PDF':'PDF','XLS':'Excel','FITEM':'FS Item'}" value="%{'XLS'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top">PCH Summary 
                             	</td>
                                <td  style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="#{'YES':'YES','NO':'NO'}" value="%{'NO'}" name="SUMCH" id="SUMCH" theme="simple" ></s:radio>
                             	</td>
                                 <td style="background-color: white;" class="label-1">Date Based</td>
                                   <td align="left"  align="center" style="background-color: white;" class="label-2">
                                    <input type="radio" name="basedon" value="TO" CHECKED>TO
                                    <input type="radio" name="basedon" value="AWB">AWB
                                   
                                    </td>
                             </tr> 
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick="window.location.href='SalesReportAction.action'" >&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="submit" id="submitbtn" value="Finish" class="whitesubmitbutton">
                             	</td>
                             </tr>	
                        </table>
                     </td>
                 </tr>
             </table>  
 </form>
       <script type="text/javascript" language="javascript" src="js/CalendarControl.js"></script>
        <script type="text/javascript">
            function addgarmentItem(){
                var garmentItemInput=document.getElementById("garmentItem");
		
                if(garmentItemInput.value.length==0)
                {
                    alert("You have not entered any value");
                    return;
                }		
                var garmentItemList=document.getElementById("garmentItemList");
                addValue(garmentItemInput,garmentItemList);
            }
            function tabEgarmentItem(obj, e) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for ( var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other
                        if (obj == ele[i]) {
                            addgarmentItem();
                            break
                        }
                    }
                    return false;
                }
            }            
            function removegarmentItem(){
                var garmentItemList=document.getElementById("garmentItemList");
                removeValue(garmentItemList);
            }   
            
       </script>
        
  </body> 
</html> 