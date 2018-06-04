<%-- 
    Document   : SupplierInwordReport
    Created on : 25 Aug, 2017, 11:35:24 AM
    Author     : Vivek
--%>

<%@page import="shahi.Action.ReportFolder.EPM.tax.bean.UtilBean"%>
<%@page import="shahi.Action.ReportFolder.EPM.tax.TAXUtil"%>
<%@page import="java.util.Arrays"%>
<%@page import="net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter"%>
<%@page import="shahi.Action.ReportFolder.EPM.tax.SupplierInwordReportAction"%>
<%@page import="java.util.Date"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="java.util.List"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JRParameter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html ng-app="myApp">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=9">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <sj:head jqueryui="true"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Performance Management System</title>
    <!-- styles -->
    <link href="../../../bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../../bootstrap/css/ShahiButtons/shahibuttons.css" rel="stylesheet">
    <link href="../../../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">   
    <!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="../css/ie/ie7.css" />
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" type="text/css" href="../css/ie/ie8.css" />
    <![endif]-->
    <!--[if IE 9]>
    <link rel="stylesheet" type="text/css" href="../css/ie/ie9.css" />
    <![endif]-->
    <style type="text/css">
        .divtext{
            width: 100%;
            height: 100%;
            position: absolute;
            vertical-align: middle;
            top:200px;
        }
        .ptext{
            font-size: 40px; 
            font-weight: bolder;
            color: rgba(105,105,105,.5);  
            text-align: center;
        }
    </style>
</head>
<body style="padding: 0px;">
    <% 
        String COMPANY = request.getParameter("COMPANY");
        String DIVISION = request.getParameter("DIVISION");
        if (request.getMethod() == "POST" &&
                (request.getParameter("SUBMIT")!=null && request.getParameter("SUBMIT").equalsIgnoreCase("YES")) &&
                (COMPANY!=null && COMPANY.length()>0) &&
                (DIVISION!=null && DIVISION.length()>0)) {
            try {
                String output = request.getParameter("outformat");
                byte[] bytes = null;

                Map parameters = new HashMap();
                if (request.getParameter("outformat").equals("XLS")) {
                    parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                }
                JasperReport report = null;
                JasperPrint print = null;

                String FROM_DATE = request.getParameter("FROM_DATE");
                String TO_DATE = request.getParameter("TO_DATE");
                String REPORT_TYPE = request.getParameter("REPORT_TYPE");
                String filename = "";
                parameters.put("FROM_DATE", FROM_DATE);
                parameters.put("TO_DATE", TO_DATE);
                parameters.put("REPORT_TYPE", REPORT_TYPE);
                String [] GEOCODEARR = request.getParameterValues("GEOCODE");
                List<String> GEOCODE = Arrays.asList(GEOCODEARR);
                filename = "SupplierInwordReport";
                report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/ReportFolder/EPM/tax/GoodsSupplier.jasper"));
                Collection list = null;
                if(REPORT_TYPE!=null && REPORT_TYPE.equals("ImportGL")){
                    list = new SupplierInwordReportAction().generateReportF(COMPANY,DIVISION,FROM_DATE, TO_DATE,GEOCODE,REPORT_TYPE);
                } else{
                    list = new SupplierInwordReportAction().generateReport(COMPANY,DIVISION,FROM_DATE, TO_DATE,GEOCODE,REPORT_TYPE);
                }
                print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(list));
                
                ServletOutputStream out1 = response.getOutputStream();
                response.reset();
                response.addCookie(new Cookie("fileDownloadToken", request.getParameter("download_token_value_id")));
                if (output.equals("PDF")) {
                    response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".pdf");
                    response.setHeader("cache-control", "no-cache");
                    response.setDateHeader("Last-Modified", 1234);
                    response.setContentType("application/pdf");
                    bytes = JasperExportManager.exportReportToPdf(print);
                    response.setContentLength(bytes.length);
                    ServletOutputStream outStream = response.getOutputStream();
                    outStream.write(bytes, 0, bytes.length);
                    outStream.flush();
                    outStream.close();
                } else {
                        JRXlsxExporter exporter = new JRXlsxExporter();
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);

                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                        //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, "C:\\JSP\\");
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filename + ".xlsx");
                        exporter.exportReport();
                        bytes = os.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".xlsx;");
                        response.setContentLength(bytes.length);
                        os.close();
                        OutputStream ouputStream = response.getOutputStream();
                        ouputStream.write(bytes, 0, bytes.length);
                        ouputStream.flush();
                        ouputStream.close();
                    }
            } catch (Exception t) {
                System.out.println("Exception " + t);
            }
        }
        TAXUtil util = new TAXUtil();
        List<UtilBean> COMPANYLIST = util.getCompany();
        List<UtilBean> DIVISIONLIST = null;
        if(COMPANY!=null && COMPANY.length()>0){
            DIVISIONLIST = util.getDivision(COMPANY);
        }
        List<UtilBean> GEOLIST = new ArrayList<UtilBean>();
        if(COMPANY!=null && COMPANY.length()>0 && DIVISION!=null && DIVISION.length()>0){
            GEOLIST = util.getGSTIN(COMPANY, DIVISION);
        }
        util.close();
    %>
    <div id="main-content">
        <div class="container-fluid">			
            <div class="row-fluid">				
                <div class="span12" style="text-align: center;">
                    <h1>Inward Supply Details</h1>
                </div>
            </div>
            <div class="row-fluid">				
                <div class="span12" style="text-align: center;">  
                    <form name="create_pdf_form" id="create_pdf_form" action="" target="" method="POST">
                        <input type="hidden" name="download_token_value_id" id="download_token_value_id"/>
                        <table class="table table-bordered" style="width:600px;" align='center'>
                            <tr>
                                <td style="width:100px;font-weight: bold;">
                                    Report Type
                                </td>
                                <td colspan="3">
                                    <select name="REPORT_TYPE" id="REPORT_TYPE">
                                        <% if(request.getParameter("REPORT_TYPE")!=null && request.getParameter("REPORT_TYPE").equals("Import")){ %>
                                        <option value="All">All</option>
                                        <option value="Import" selected="true">Import</option>
                                        <option value="ImportGL">Import with GL</option>
                                        <%}else if(request.getParameter("REPORT_TYPE")!=null && request.getParameter("REPORT_TYPE").equals("ImportGL")){ %>
                                        <option value="All">All</option>
                                        <option value="Import">Import</option>
                                        <option value="ImportGL" selected="true">Import with GL</option>
                                        <%}else{%>
                                        <option value="All">All</option>
                                        <option value="Import">Import</option>
                                        <option value="ImportGL">Import with GL</option>
                                        <%}%>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:100px;font-weight: bold;">
                                    Company
                                </td>
                                <td colspan="3">
                                    <select name="COMPANY" id="COMPANY" style="width:300px;" onchange="fetchdivi();">
                                        <option value="">Select Company</option>
                                        <%if(COMPANYLIST!=null && COMPANYLIST.size()>0){%>
                                            <%for(UtilBean bean : COMPANYLIST){
                                                if(COMPANY!=null && COMPANY.length()>0 && COMPANY.equalsIgnoreCase(bean.getCODE())){
                                            %> 
                                            <option value="<%=bean.getCODE()%>" selected="selected"><%=bean.getDESC()%></option>
                                            <%}else{
                                            %>
                                            <option value="<%=bean.getCODE()%>"><%=bean.getDESC()%></option>
                                            <%
                                                }
                                            }
                                        }%>
                                    </select>                                        
                                </td>
                            </tr>
                            <tr>
                                <td style="width:100px;font-weight: bold;">
                                    Division
                                </td>
                                <td colspan="3">
                                    <select name="DIVISION" id="DIVISION" style="width:300px;" onchange="fetchdivi();">
                                        <option value="">Select Division</option>
                                        <%if(DIVISIONLIST!=null && DIVISIONLIST.size()>0){%>
                                            <%for(UtilBean bean : DIVISIONLIST){
                                                if(DIVISION!=null && DIVISION.length()>0 && DIVISION.equalsIgnoreCase(bean.getCODE())){
                                            %> 
                                            <option value="<%=bean.getCODE()%>" selected="selected"><%=bean.getDESC()%></option>
                                            <%}else{
                                            %>
                                            <option value="<%=bean.getCODE()%>"><%=bean.getDESC()%></option>
                                            <%
                                                }
                                            }
                                        }%>
                                    </select>                                        
                                </td>
                            </tr>
                            <tr>
                                
                                <td style="width:100px;font-weight: bold;">
                                    GSTIN
                                </td>
                                <td colspan="3">
                                    <div style="width:300px;height:200px;border-width: 1px;border-color: darkgray;border-style: solid;overflow-x: auto;">
                                        <table class="table table-bordered table-striped">
                                            <tbody>
                                            <%if(GEOLIST!=null && GEOLIST.size()>0){
                                                for(UtilBean bean : GEOLIST){
                                            %>
                                            <tr><td style="font-size: 11px;color: #555555;"><input type="checkbox" name="GEOCODE" id="GEOCODE" class="GEOCODECLASS" value="<%=bean.getCODE()%>">&nbsp;&nbsp;<%=bean.getDESC()%></td></tr>
                                            <%
                                                }
                                            }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:100px;font-weight: bold;">
                                    Date Form
                                </td>
                                <td>
                                    <sj:datepicker name="FROM_DATE" id="FROM_DATE" theme="simple" displayFormat="dd/mm/yy" cssStyle="width:100px;"/>
                                </td>
                                <td style="width:100px;font-weight: bold;">
                                    Date To
                                </td>
                                <td>
                                    <sj:datepicker name="TO_DATE" id="TO_DATE" theme="simple" displayFormat="dd/mm/yy" cssStyle="width:100px;"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:100px;font-weight: bold;">
                                    Out Format
                                </td>
                                <td colspan="3">
                                    <input type='radio' name='outformat' id='outformat' value="PDF" checked="true"/> PDF
                                    <input type='radio' name='outformat' id='outformat' value="XLS" /> XLS
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" style="text-align: center;"><a href='#' onclick='submitfrm();' class='sexybutton'><span><span><span class="save">Submit</span></span></span></a></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="../../../bootstrap/js/jquery_blockUI.js"></script>
    <script type="text/javascript" src="../../../bootstrap/js/jquery_cookie.js"></script>
    <script type='text/javascript'>
        var fileDownloadCheckTimer;
        function blockUIForDownload() {
          var token = new Date().getTime(); //use the current timestamp as the token value
          $('#download_token_value_id').val(token);
          $.blockUI();
          fileDownloadCheckTimer = window.setInterval(function () {
          var cookieValue = $.cookie('fileDownloadToken');
            if (cookieValue == token)
             finishDownload();
          }, 1000);
        }

        function finishDownload() {
                window.clearInterval(fileDownloadCheckTimer);
                $.removeCookie('fileDownloadToken'); //clears this cookie value
                $.unblockUI();
                //alert('after download');
               }
            function submitfrm(){
                if(validate()){
                    blockUIForDownload();
                    $('#create_pdf_form').attr('action','SupplierInwordReport.jsp?SUBMIT=YES');
                    $('form').submit();
                }
                else{
                    return false;
                }
            }
            function onblursubmit(){
                if($('#EMP_CODE').val()==''){
                    $('#EMP_NAME').val('');  
                    $('#SCORE_CARD').html('');
                }else{
                    $('#create_pdf_form').attr('action','SupplierInwordReport.jsp');
                    $('form').submit();
                }
            }
            function  fetchdivi(){
                $('#create_pdf_form').attr('action','SupplierInwordReport.jsp');
                $('form').submit();
            }
            
        function validate(){
                if($('#COMPANY').val()==''){
                    alert('Please choose Company!!!');  
                    $('#COMPANY').focus();
                    return false;
                }if($('#DIVISION').val()==''){
                    alert('Please choose Division!!!');  
                    $('#DIVISION').focus();
                    return false;
                }
                ctr=0;
                $('.GEOCODECLASS').each(function (index, value){
                    if(this.checked==true){
                        ++ctr;
                    }
                });
                if(ctr==0){
                    alert('Please choose atleast 1 GSTIN!!!');
                    return false;
                }
                if($('#FROM_DATE').val()==''){
                    alert('Please choose From Date!!!');
                    $('#FROM_DATE').focus();
                    return false;
                }
                if($('#TO_DATE').val()==''){
                    alert('Please choose To Date!!!');
                    $('#TO_DATE').focus();
                    return false;
                }
                return true;
            }
    </script>
</body>