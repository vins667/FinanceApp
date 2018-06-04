<%-- 
    Document   : grntouchinsentryjava-Knits
    Created on : Jan 2, 2016, 12:56:29 PM
    Author     : Shilpa
--%>

<%@page import="shahi.Action.database.ConnectionSeplWeb"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Connection conn = null;
        PreparedStatement save_stat = null;
        PreparedStatement pr_stat = null;
        ResultSet pr_result = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;

        ResultSet result = null;
        ResultSet result1 = null;
        ResultSet result2 = null;

        String myusrid = (String) session.getValue("myusrid");
        String location = (String) session.getValue("LOCATION_CODE");
        String EMPNAME = (String) session.getValue("EMPNAME");
        String CATG_CODE = (String) session.getValue("CATG_CODE");
        //location="100";
        
        int a = 0;
        int k = 0;
        String NO_OF_FOLD = request.getParameter("NO_OF_FOLD");
        String EPI = request.getParameter("EPI");
        String PPI = request.getParameter("PPI");
        if (NO_OF_FOLD == null || (NO_OF_FOLD != null && NO_OF_FOLD.length() == 0)) {
            NO_OF_FOLD = "0";
        }
        if (EPI == null || (EPI != null && EPI.length() == 0)) {
            EPI = "0";
        }
        if (PPI == null || (PPI != null && PPI.length() == 0)) {
            PPI = "0";
        }
        try {

            conn = new ConnectionSeplWeb().getConnection();
            conn.setAutoCommit(false);

            if (request.getParameter("grnno") != null && request.getParameter("grnno").length() > 0) {
                if (CATG_CODE.equals("D")) {
                    save_stat = conn.prepareStatement("update grninsdt set ROLL_QTY=?,WIDTH=?,MIN_WIDTH=?,INSPECTOR=?,INSPECTION_DATE=trunc(sysdate),TDATE=trunc(sysdate),TTIME=sysdate,USER_ID=?,MAX_WIDTH=?,ACTUAL_QTY=?,CUT_PCS=?,FOLD_LENGTH=?,ROLL_BOWING=?,NO_OF_FOLD=?,EPI=?,PPI=?,LAY_TO_LAY=?,BODY_TO_TRIMS=?,HAND_FEEL=?,FABRIC_PH=?,GSM_BW=?,GSM_AW=?,MATAMARISAM=? where RECEIPT_NO=? and location_code=? and ROLL_NO=?");
                    save_stat.setString(1, request.getParameter("ROLL_QTY"));
                    save_stat.setString(2, request.getParameter("ROWIDTH"));
                    save_stat.setString(3, request.getParameter("MIN_WIDTH"));
                    save_stat.setString(4, request.getParameter("emp_code"));
                    save_stat.setString(5, request.getParameter("emp_code"));
                    save_stat.setString(6, request.getParameter("MAX_WIDTH"));
                    save_stat.setString(7, request.getParameter("ACTUAL_QTY"));
                    save_stat.setString(8, request.getParameter("CUT_PCS"));
                    save_stat.setString(9, request.getParameter("FOLD_LENGTH"));
                    save_stat.setString(10, request.getParameter("ROLL_BOWING"));
                    save_stat.setString(11, NO_OF_FOLD);
                    save_stat.setString(12, EPI);
                    save_stat.setString(13, PPI);
                    save_stat.setString(14, request.getParameter("LAY_TO_LAY"));
                    save_stat.setString(15, request.getParameter("BODY_TO_TRIMS"));
                    save_stat.setString(16, request.getParameter("HAND_FEEL"));
                    save_stat.setString(17, request.getParameter("FABRIC_PH"));
                    save_stat.setString(18, request.getParameter("GSM_BW"));
                    save_stat.setString(19, request.getParameter("GSM_AW"));
                    save_stat.setString(20, request.getParameter("MATAMARISAM"));

                    save_stat.setString(21, request.getParameter("grnno"));
                    save_stat.setString(22, location);
                    save_stat.setString(23, request.getParameter("ROLL_NO"));
                    a = save_stat.executeUpdate();
                } else {
                    save_stat = conn.prepareStatement("update grninsdt set SHADE_LOT=?,CS=?,LWV=?,INSPECTOR_S=?,TDATE=trunc(sysdate),TTIME=sysdate,USER_ID=?,SHADE_GRP=?,CUT_PCS=?,FOLD_LENGTH=?,ROLL_BOWING=?,NO_OF_FOLD=?,EPI=?,PPI=?,LAY_TO_LAY=?,BODY_TO_TRIMS=?,HAND_FEEL=?,FABRIC_PH=?,GSM_BW=?,GSM_AW=?,MATAMARISAM=? where RECEIPT_NO=? and location_code=? and ROLL_NO=?");
                    save_stat.setString(1, request.getParameter("SHADE_LOT"));
                    save_stat.setString(2, request.getParameter("CS"));
                    save_stat.setString(3, request.getParameter("LWV"));
                    save_stat.setString(4, request.getParameter("emp_code"));
                    save_stat.setString(5, request.getParameter("emp_code"));
                    save_stat.setString(6, request.getParameter("SHADE_GRP"));
                    save_stat.setString(7, request.getParameter("CUT_PCS"));
                    save_stat.setString(8, request.getParameter("FOLD_LENGTH"));
                    save_stat.setString(9, request.getParameter("ROLL_BOWING"));
                    save_stat.setString(10, NO_OF_FOLD);
                    save_stat.setString(11, EPI);
                    save_stat.setString(12, PPI);
                    save_stat.setString(13, request.getParameter("LAY_TO_LAY"));
                    save_stat.setString(14, request.getParameter("BODY_TO_TRIMS"));
                    save_stat.setString(15, request.getParameter("HAND_FEEL"));
                    save_stat.setString(16, request.getParameter("FABRIC_PH"));
                    save_stat.setString(17, request.getParameter("GSM_BW"));
                    save_stat.setString(18, request.getParameter("GSM_AW"));
                    save_stat.setString(19, request.getParameter("MATAMARISAM"));

                    save_stat.setString(20, request.getParameter("grnno"));
                    save_stat.setString(21, location);
                    save_stat.setString(22, request.getParameter("ROLL_NO"));
                    a = save_stat.executeUpdate();
                }
                conn.commit();
            }
   
        } catch (Exception e) {
            System.out.println("1 File Name : grntouchinsentry_knits.java" + e.getMessage());
        } finally {
            try {
                if (pr_result != null) {
                    pr_result.close();
                }
                if (pr_stat != null) {
                    pr_stat.close();
                }
                if (result != null) {
                    result.close();
                }
                if (result1 != null) {
                    result1.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (conn != null) {
                    conn.close();
                }
                pr_stat = null;
                pr_result = null;
                result = null;
                stat = null;
                conn = null;
            } catch (Exception e) {
                System.out.print("File Name : grntouchinsentry_knits.java Exception in finally block");
                e.printStackTrace();
            }
        }
        response.sendRedirect("grntouchinsentry_knits.jsp?savemsg=" + a + "&grnno=" + request.getParameter("grnno") + "&ROLL_NO=" + request.getParameter("ROLL_NO") + "&emp_code=" + request.getParameter("emp_code"));

    %>
</html>
