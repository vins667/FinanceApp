/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM.tax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shahi.Action.ReportFolder.EPM.tax.bean.SupplierInwordReportBean;
import shahi.Action.database.connectionShahiHris;

/**
 *
 * @author Vivek
 */
public class SupplierInwordReportActionFC {
    public List<SupplierInwordReportBean> generateReport(String COMPANY,String DIVISION,String dateFrom, String dateTo,List<String> GEOCODE,String REPORT_TYPE){
        List<SupplierInwordReportBean> beans = new ArrayList<SupplierInwordReportBean>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        PreparedStatement stmt1 = null;
        ResultSet resultSet1 = null;
        if(dateFrom != null && dateTo !=null){
            try{
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
                dateFrom =  format1.format(format.parse(dateFrom));
                dateTo = format1.format(format.parse(dateTo));String query="";
                if(GEOCODE!=null && GEOCODE.size()>0){
                    query+=" AND A.T4GEOT IN(";
                    for(String GEO : GEOCODE){
                        query+="'"+GEO+"',";
                    }
                    query=query.substring(0,query.lastIndexOf(","));
                    query+=")";
                }
              /*  if(REPORT_TYPE!=null && REPORT_TYPE.equals("Import")){
                     query+=" AND SUBSTR(A.T4SUNO,2,1)='I'";
                }
                */
                conn = new connectionShahiHris().getConnection();
                stmt = conn.prepareStatement("SELECT T4CONO, T4DIVI, T4SUNO, T4SINO, T4INYR, T4IVDT, T4GEOT, T4GEOF, T4CSNO, NETVAL, T4TAR1, T4TXA1, T4TAR2, T4TXA2,"
                        +" T4TAR3, T4TXA3, T4TXBA, IDSUNM, IDCFI3, MMITDS, MMUNMS, ARXLCN, SAECAR, EGYEA4, EGVSER, EGVONO, EPSINO, EPIVDT, EGACQT,"
                        +" GSTN, POS, SSTAT, EGACDT, T4ZNO1,"
                        +" b.HSNNO,b.cinv_amt,b.be_no,b.be_date,b.cifvalue,b.custom_duty,b.qty,b.uom,b.delv_port,b.ass_value,b.igst_rate,b.igst_amt,"
                        + "sum(NETVAL+T4TXA1+T4TXA2+T4TXA3) over (partition by"
                        +" T4CONO, T4DIVI, T4SUNO, T4SINO, T4INYR) INVVAL,ROW_NUMBER () OVER (PARTITION BY T4SINO ORDER BY T4SINO) SEQ_NO"
                        +" FROM("
                        +" SELECT  A.T4CONO,A.T4DIVI,A.T4SUNO,A.T4SINO,A.T4INYR,TO_CHAR(TO_DATE(E.EPIVDT,'yyyyMMdd'),'yyyy-MM-dd') T4IVDT,"
                        +" A.T4GEOT,A.T4GEOF,SUBSTR(B.T4CSNO,0,INSTR(B.T4CSNO,'-')-1) T4CSNO,SUM(A.T4TXBA) NETVAL," 
                        +" MAX(A.T4TAR1) T4TAR1,SUM(A.T4TXA1) T4TXA1,MAX(A.T4TAR2) T4TAR2,SUM(A.T4TXA2) T4TXA2,MAX(A.T4TAR3) T4TAR3,SUM(A.T4TXA3) T4TXA3,"
                        +" SUM(A.T4TXBA) T4TXBA,C.IDSUNM,C.IDCFI3,D.MMITDS,D.MMUNMS,G.ARXLCN,G.SAECAR,E.EGYEA4,E.EGVSER,E.EGVONO,E.EPSINO,"
                        +" TO_CHAR(TO_DATE(E.EPIVDT,'yyyyMMdd'),'yyyy-MM-dd') EPIVDT,SUM(E.EGACQT) EGACQT,H.GSTN,SUBSTR(H.GSTN,0,2) POS,"
                        +" SUBSTR(A.T4GEOF,6,2) SSTAT,E.EGACDT,B.T4ZNO1"
                        +" FROM PRODBI.STAXLN A,PRODBI.ZSTAXL B,PRODBI.CIDMAS C,PRODBI.MITMAS D,MOVEX.FGLEDG_FPLEDG_SUMMARY E,"
                        +" (SELECT SACONO,SASUNO,SAGEOC,SAECAR,max(ARXLCN) ARXLCN FROM PRODBI.CIDADR, PRODBI.XINADR "
                        +" where SACONO=ARCONO AND SASUNO=ARSUNO AND SAADTE=ARADTE AND SAADID=ARADID AND SASTDT=ARSTDT AND"
                        +" SACONO='111' GROUP BY SACONO,SASUNO,SAGEOC,SAECAR) G,SEPLWEB.M4_WHLO_MASTER@IBM.WORLD@IBM H"
                        +" WHERE A.T4CONO=B.T4CONO AND"
                        +" A.T4DIVI=B.T4DIVI AND A.T4SUNO=B.T4SUNO AND A.T4SINO=B.T4SINO AND"
                        +" A.T4INYR=B.T4INYR AND A.T4IVSQ=B.T4IVSQ AND A.T4CONO=C.IDCONO AND A.T4SUNO=C.IDSUNO AND"
                        +" B.T4CONO=D.MMCONO(+) AND B.T4ITNO=D.MMITNO(+) AND"
                        +" A.T4CONO=E.EGCONO AND A.T4DIVI=E.EGDIVI AND A.T4INYR=E.EGYEA4 AND A.T4SUNO=E.EPSUNO AND A.T4SINO=E.EPSINO AND"
                        +" A.T4CONO=G.SACONO(+) AND A.T4SUNO=G.SASUNO(+) AND A.T4GEOF=G.SAGEOC(+) AND"
                        +" A.T4CONO=H.M4CONO AND A.T4DIVI=H.M4DIVI AND A.T4GEOT=H.M4GEOC AND"
                        +" E.EGCONO=? AND E.EGDIVI=? AND E.EGACDT BETWEEN ? AND ?"+query+" AND NVL(E.EPCUCD,'INR')!='INR' AND B.T4STAT='01'"
                        +" GROUP BY A.T4CONO,A.T4DIVI,A.T4SUNO,A.T4SINO,A.T4INYR,A.T4IVDT,"
                        +" A.T4GEOT,A.T4GEOF,SUBSTR(B.T4CSNO,0,INSTR(B.T4CSNO,'-')-1),"
                        +" C.IDSUNM,C.IDCFI3,D.MMITDS,D.MMUNMS,G.ARXLCN,G.SAECAR,E.EGYEA4,E.EGVSER,E.EGVONO,E.EPSINO,"
                        +" C.IDSUNM,C.IDCFI3,A.T4SINO,E.EPIVDT,H.GSTN,SUBSTR(H.GSTN,0,2),SUBSTR(A.T4GEOF,6,2),E.EGACDT,B.T4ZNO1" 
                        +" ) a, ( select a.ind_no,cyear,b.sup_code,a.cinv_no,a.cinv_date,SUBSTR(a.hsnno,0,INSTR(a.hsnno,'-')-1) hsnno,a.cinv_amt,a.be_no,a.be_date,a.cifvalue,a.custom_duty,a.qty,a.uom,a.delv_port,a.ass_value,a.igst_rate,a.igst_amt"
                        +" from pi_imp_gst_data@ibm a,pi_indent_mast@ibm b where a.ind_no=b.ind_no ) b" 
                        +" where a.t4inyr=cyear(+) and a.t4suno=b.sup_code(+) and a.t4sino=b.cinv_no(+) and t4csno=b.hsnno(+)"
                        +" ORDER BY T4CONO,T4DIVI,T4INYR,T4IVDT,T4SUNO,T4SINO");    
                stmt.setString(1, COMPANY);
                stmt.setString(2, DIVISION);
                stmt.setString(3,dateFrom);
                stmt.setString(4, dateTo);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("MMyyyy");
                resultSet = stmt.executeQuery();
                while(resultSet.next()){
                    SupplierInwordReportBean bean = new SupplierInwordReportBean();
                    bean.setSOURCE_IDENTIFIER("");//PENDING KULDEEP SIR//1
                    bean.setSOURCE_FILE_NAME("");//PENDING KULDEEP SIR//2
                    bean.setGL_ACCOUNT_CODE("");//PENDING KULDEEP SIR//3
                    bean.setDIVISION_RECEIVER("");//PENDING KULDEEP SIR//4
                    bean.setSUB_DIVISION_RECEIVER("");//PENDING KULDEEP SIR//5
                    bean.setPROFIT_CENTRE_1_RECEIVER("");//PENDING KULDEEP SIR//6
                    bean.setPROFIT_CENTRE_2_RECEIVER("");//PENDING KULDEEP SIR//7
                    bean.setPLANT_CODE_RECEIVER("");//PENDING KULDEEP SIR//8
                    
                    bean.setTAX_RETURN_PERIOD(formatter1.format(formatter.parse(dateTo)));//9
                    bean.setCOMPANY_GSTIN(resultSet.getString("GSTN"));  //PENDING KULDEEP SIR//10
                    //bean.setDOCUMENT_TYPE("INV");//11//Changes Date on 09102017
                    //bean.setSUPPLY_TYPE(resultSet.getString("IDCFI3"));//12//Changes Date on 09102017
                    if(resultSet.getString("IDCFI3")!=null && resultSet.getString("IDCFI3").trim().equalsIgnoreCase("URG")){
                        bean.setDOCUMENT_TYPE("SLF");//11
                    } else{
                        bean.setDOCUMENT_TYPE("INV");//11
                    }
                    bean.setSUPPLY_TYPE("TAX");//12                    
                    bean.setDOCUMENT_NUMBER(resultSet.getString("T4SINO"));//13
                    bean.setDOCUMENT_DATE(resultSet.getString("T4IVDT"));//14
                    
                    String OREFNO = "";
                    
                   OREFNO = resultSet.getString("EGYEA4") + "/" + resultSet.getString("EGVSER") + "/" + resultSet.getString("EGVONO");
                   bean.setQUANTITY(resultSet.getDouble("EGACQT"));//34                       
                   // bean.setORIGINAL_DOCUMENT_NO(resultSet.getString("EPSINO"));//15
                   // bean.setORIGINAL_DOCUMENT_DATE(resultSet.getString("EPIVDT"));//16
                    bean.setCR_DR_PRE_GST("N");//17

                    bean.setLINE_ITEM_NUMBER(resultSet.getString("SEQ_NO"));//18//resultSet.getString("T4IVSQ")
                    
                    if(resultSet.getString("IDCFI3")!=null && resultSet.getString("IDCFI3").trim().equalsIgnoreCase("URG")){
                        if(resultSet.getString("ARXLCN")!=null && resultSet.getString("ARXLCN").length()>0){
                            bean.setSUPPLIER_GSTIN(resultSet.getString("ARXLCN"));//19
                        } else{
                            bean.setSUPPLIER_GSTIN(resultSet.getString("GSTN"));//19
                        }
                    } else{
                        bean.setSUPPLIER_GSTIN(resultSet.getString("ARXLCN"));//19
                    }
                    //bean.setORIGINAL_Supplier_GSTIN(resultSet.getString("ARXLCN"));//20///Changes date on 09102017
                    bean.setORIGINAL_Supplier_GSTIN("");
                    bean.setPLACE_SUPPLY(resultSet.getString("SSTAT"));//23
                    
                    bean.setSUPPLIER_NAME(resultSet.getString("IDSUNM"));//21
                    bean.setSUPPLIER_CODE(resultSet.getString("T4SUNO"));//22             
                    
                    bean.setPORT_CODE(resultSet.getString("DELV_PORT")); //PENDING KULDEEP SIR[Import Bill]//24
                    bean.setBILL_REPORT_NUMBER(resultSet.getString("BE_NO")); //PENDING KULDEEP SIR[Import Bill]//25
                    bean.setBILL_REPORT_DATE(resultSet.getString("BE_DATE")); //PENDING KULDEEP SIR[Import Bill]//26
                    bean.setCIF_VALUE(resultSet.getString("CIFVALUE")); //PENDING KULDEEP SIR[Import Bill]//27
                    bean.setCUSTOMS_DUTY_PAID(resultSet.getString("CUSTOM_DUTY")); //PENDING KULDEEP SIR[Import Bill]//28
                    
                    bean.setHSN_SAC(resultSet.getString("T4CSNO"));//29
                    bean.setITEM_CODE("");//30//resultSet.getString("T4ITNO")
                    if(resultSet.getString("MMITDS")!=null && resultSet.getString("MMITDS").length()>0){
                        bean.setITEM_DESCRIPTION(resultSet.getString("MMITDS"));//31
                    } else{
                        stmt1 = conn.prepareStatement("select egcono,egdivi,egyea4,egvser,egvono,max(egait4) egait4,max(upper(EATX40)) EATX40 from prodbi.fgledg,prodbi.fchacc"
                                 +" where egcono=eacono and egait4=eaaitm and eacono=111 and EAAITP=4"
                                 +" and egcono=111 AND EGYEA4>2016 AND EGAIT1 BETWEEN '40000' AND '60000' and egait1<>'58446' and egreco<>9 "
                                 +" and egcono=? and egdivi=? and egyea4=? and egvser=? and egvono=?"
                                 +" group by egcono,egdivi,egyea4,egvser,egvono");
                                stmt1.setString(1,resultSet.getString("T4CONO"));
                                stmt1.setString(2,resultSet.getString("T4DIVI"));
                                stmt1.setString(3,resultSet.getString("EGYEA4"));
                                stmt1.setString(4,resultSet.getString("EGVSER"));
                                stmt1.setString(5,resultSet.getString("EGVONO"));
                                resultSet1 = stmt1.executeQuery();
                                if(resultSet1.next()){
                                     bean.setITEM_DESCRIPTION(resultSet1.getString("EATX40"));
                                }
                                if(resultSet1!=null) resultSet1.close();
                                if(stmt1!=null) stmt1.close();
                    }
                    bean.setCATEGORY_ITEM("");//32
                    if(resultSet.getString("UOM")!=null && resultSet.getString("UOM").length()>0){
                        bean.setUNIT_OF_MEASUREMENT(resultSet.getString("UOM"));//33 
                    }else{
                        bean.setUNIT_OF_MEASUREMENT("OTH");//33//Changes Date on 09102017 
                    }
                    
                    bean.setTAXABLE_VALUE_GST(resultSet.getDouble("NETVAL"));//35
                    bean.setINTEGRATED_TAX_RATE(resultSet.getDouble("T4TAR3"));//36
                    bean.setINTEGRATED_TAX_AMOUNT(resultSet.getDouble("T4TXA3"));//37
                    bean.setCENTRAL_TAX_RATE(resultSet.getDouble("T4TAR1"));//38
                    bean.setCENTRAL_TAX_AMOUNT(resultSet.getDouble("T4TXA1"));//39
                    bean.setSTATE_UT_TAX_RATE(resultSet.getDouble("T4TAR2"));//40
                    bean.setSTATE_UT_TAX_AMOUNT(resultSet.getDouble("T4TXA2"));//41
                    bean.setCESS_RATE_ADVALOERM(0);//42
                    bean.setCESS_AMOUNT_ADVALEROM(0);//43
                    bean.setCESS_RATE_SPECIFIC(0);//44
                    bean.setCESS_AMOUNT_SPECIFIC(0);//45
                    //bean.setVALUE_INCLUDING_TAX(resultSet.getDouble("T4TXBA"));//46//changes date on 09102017
                    bean.setVALUE_INCLUDING_TAX(resultSet.getDouble("INVVAL"));//46
                    if(resultSet.getDouble("T4ZNO1")!=0){
                        bean.setINDICATE_REVERSE_CHARGE("Y");//47                        
                    }else {
                        bean.setINDICATE_REVERSE_CHARGE("N");//47                        
                    }
                    
                    if(resultSet.getString("T4CSNO")!=null && resultSet.getString("T4CSNO").startsWith("99")){
                        bean.setINELIGIBLE_ITC("IS");//48
                    } else{
                        bean.setINELIGIBLE_ITC("IG");//48
                    }
                    
                    bean.setCOMMON_INWARD_SUPPLIES("");//49
                    bean.setAMOUNT_ITC_IGST(resultSet.getDouble("T4TXA3"));//50
                    bean.setAMOUNT_ITC_CGST(resultSet.getDouble("T4TXA1"));//51
                    bean.setAMOUNT_ITC_SGST(resultSet.getDouble("T4TXA2"));//52
                    bean.setAMOUNT_ITC_CESS(0);//53
                    bean.setITC_REVERSAL_IDENTIFIER("");//54
                    bean.setREASON_ISSUING("");//55
                    bean.setPURCHASE_BILL_VOUCHER_NO("");//56
                    bean.setPURCHASE_BILL_VOUCHER_DATE("");//57
                    bean.setPAYMENT_VOUCHER_NUMBER("");//58
                    bean.setPAYMENT_DATE("");//59
                    bean.setCONTRACT_NUMBER("");//60
                    bean.setCONTRACT_DATE("");//61
                    bean.setCONTRACT_VALUE(0);//62
                    bean.setUSER_DEFINED_FIELD_1(resultSet.getString("T4GEOF"));//63
                    bean.setUSER_DEFINED_FIELD_2(resultSet.getString("EGACDT"));//64
                    bean.setUSER_DEFINED_FIELD_3(OREFNO);//65
                    beans.add(bean);
                }
                if(resultSet!=null){
                    resultSet.close();
                }
                if(stmt!=null){
                    stmt.close();
                }
            } catch (Exception e){
                System.out.println("GoodsSupplierAction.class "+e.getMessage());
            } finally{
                if(conn!=null){
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SupplierInwordReportActionFC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return beans;
    }
    
    public List<SupplierInwordReportBean> generateReportF(String COMPANY,String DIVISION,String dateFrom, String dateTo,List<String> GEOCODE,String REPORT_TYPE){
        List<SupplierInwordReportBean> beans = new ArrayList<SupplierInwordReportBean>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        if(dateFrom != null && dateTo !=null){
            try{
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
                dateFrom =  format1.format(format.parse(dateFrom));
                dateTo = format1.format(format.parse(dateTo));String query="";
                if(GEOCODE!=null && GEOCODE.size()>0){
                    query+=" AND B.M4GEOC IN(";
                    for(String GEO : GEOCODE){
                        query+="'"+GEO+"',";
                    }
                    query=query.substring(0,query.lastIndexOf(","));
                    query+=")";
                }
                /*if(REPORT_TYPE!=null && REPORT_TYPE.equals("Import")){
                     query+=" AND SUBSTR(A.T4SUNO,2,1)='I'";
                }*/
                conn = new connectionShahiHris().getConnection();
                stmt = conn.prepareStatement("SELECT EGCONO,EGDIVI,EGYEA4,TO_CHAR(TO_DATE(EGACDT,'yyyyMMdd'),'yyyy-MM-dd') EGACDT,EGVSER,EGVONO,EGAIT4 BOE,CASE WHEN LENGTH(EGAIT5)=10 THEN TO_CHAR(TO_DATE(EGAIT5,'dd.MM.yyyy'),'yyyy-MM-dd') ELSE null END BOEDATE,EGAIT7 PORT,EGVTXT BILLNO,EGACQT CIFVALUE,EGDCQT QTY,EGUNIT,"
                            +" EGACAM TAXAMT,EGCUCD,ROW_NUMBER () OVER (PARTITION BY EGCONO,EGDIVI,EGYEA4,EGVSER,EGVONO ORDER BY egcono,egdivi,egyea4,egvser,egvono) SEQ_NO,GSTN,SUBSTR(GSTN,1,2) POS"
                            +" FROM PRODBI.FGLEDG A,SEPLWEB.M4_WHLO_MASTER@IBM.WORLD@IBM B  WHERE A.EGVSER=B.WHVSER AND EGCONO=? AND EGDIVI=? AND EGYEA4>=2017 AND EGACDT BETWEEN ? AND ?  AND EGAIT1 IN ('26956','16690') AND EGRECO<>9"+query);  //AND EGVONO=67022                  
                stmt.setString(1, COMPANY);
                stmt.setString(2, DIVISION);
                stmt.setString(3,dateFrom);
                stmt.setString(4, dateTo);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("MMyyyy");
                resultSet = stmt.executeQuery();
                while(resultSet.next()){
                    SupplierInwordReportBean bean = new SupplierInwordReportBean();
                    bean.setSOURCE_IDENTIFIER("");//PENDING KULDEEP SIR//1
                    bean.setSOURCE_FILE_NAME("");//PENDING KULDEEP SIR//2
                    bean.setGL_ACCOUNT_CODE("");//PENDING KULDEEP SIR//3
                    bean.setDIVISION_RECEIVER("");//PENDING KULDEEP SIR//4
                    bean.setSUB_DIVISION_RECEIVER("");//PENDING KULDEEP SIR//5
                    bean.setPROFIT_CENTRE_1_RECEIVER("");//PENDING KULDEEP SIR//6
                    bean.setPROFIT_CENTRE_2_RECEIVER("");//PENDING KULDEEP SIR//7
                    bean.setPLANT_CODE_RECEIVER("");//PENDING KULDEEP SIR//8
                    
                    bean.setTAX_RETURN_PERIOD(formatter1.format(formatter.parse(dateTo)));//9
                    bean.setCOMPANY_GSTIN(resultSet.getString("GSTN"));  //PENDING KULDEEP SIR//10
                    bean.setDOCUMENT_TYPE("INV");//11
                    bean.setSUPPLY_TYPE("TAX");//12                    
                    
                    String aaa = resultSet.getString("BILLNO");
                    if(aaa!=null && aaa.contains(" DT ")){
                        bean.setDOCUMENT_NUMBER(aaa.substring(0,aaa.indexOf(" DT ")));
                        String billdate=aaa.substring(aaa.indexOf(" DT ")+4,aaa.length());
                        try{
                            if(billdate.length()==7){
                                SimpleDateFormat formatter123 = new SimpleDateFormat("dd.M.yy");
                                SimpleDateFormat formatter345 = new SimpleDateFormat("yyyy-MM-dd");
                                bean.setDOCUMENT_DATE(formatter345.format(formatter123.parse(billdate)));
                            } else if(billdate.length()==8){
                                SimpleDateFormat formatter123 = new SimpleDateFormat("dd.MM.yy");
                                SimpleDateFormat formatter345 = new SimpleDateFormat("yyyy-MM-dd");
                                bean.setDOCUMENT_DATE(formatter345.format(formatter123.parse(billdate)));
                            }
                        } catch(Exception e){}
                    }
                    //vivek bean.setDOCUMENT_NUMBER(resultSet.getString("T4SINO"));//13
                    //vivek bean.setDOCUMENT_DATE(resultSet.getString("T4IVDT"));//14
                    
                    String OREFNO = "";
                    
                   OREFNO = resultSet.getString("EGYEA4") + "/" + resultSet.getString("EGVSER") + "/" + resultSet.getString("EGVONO");
                   bean.setQUANTITY(resultSet.getDouble("QTY"));//34                        
                   // bean.setORIGINAL_DOCUMENT_NO(resultSet.getString("EPSINO"));//15
                   // bean.setORIGINAL_DOCUMENT_DATE(resultSet.getString("EPIVDT"));//16
                    bean.setCR_DR_PRE_GST("N");//17

                    bean.setLINE_ITEM_NUMBER(resultSet.getString("SEQ_NO"));//18//resultSet.getString("T4IVSQ")
                    
                    bean.setSUPPLIER_GSTIN("");//19
                    //bean.setORIGINAL_Supplier_GSTIN(resultSet.getString("ARXLCN"));//20///Changes date on 09102017
                    bean.setORIGINAL_Supplier_GSTIN("");
                    bean.setPLACE_SUPPLY(resultSet.getString("POS"));//23
                    
                    bean.setSUPPLIER_NAME("");//21
                    bean.setSUPPLIER_CODE("");//22             
                    
                    bean.setPORT_CODE(resultSet.getString("PORT")); //PENDING KULDEEP SIR[Import Bill]//24
                    bean.setBILL_REPORT_NUMBER(resultSet.getString("BOE")); //PENDING KULDEEP SIR[Import Bill]//25
                    bean.setBILL_REPORT_DATE(resultSet.getString("BOEDATE")); //PENDING KULDEEP SIR[Import Bill]//26
                    bean.setCIF_VALUE(""); //PENDING KULDEEP SIR[Import Bill]//27
                    bean.setCUSTOMS_DUTY_PAID(""); //PENDING KULDEEP SIR[Import Bill]//28
                    
                    bean.setHSN_SAC("");//29
                    bean.setITEM_CODE("");//30//resultSet.getString("T4ITNO")
                    bean.setITEM_DESCRIPTION("");//31
                    bean.setCATEGORY_ITEM("");//32
                    if(resultSet.getString("EGUNIT")!=null && resultSet.getString("EGUNIT").length()>0){
                        bean.setUNIT_OF_MEASUREMENT(resultSet.getString("EGUNIT"));//33//Changes Date on 09102017                     
                    } else{
                        bean.setUNIT_OF_MEASUREMENT("OTH");//33
                    }
                    
                    bean.setTAXABLE_VALUE_GST(resultSet.getDouble("CIFVALUE"));//35
                    bean.setINTEGRATED_TAX_RATE(0.0);//36
                    bean.setINTEGRATED_TAX_AMOUNT(resultSet.getDouble("TAXAMT"));//37
                    bean.setCENTRAL_TAX_RATE(0);//38
                    bean.setCENTRAL_TAX_AMOUNT(0);//39
                    bean.setSTATE_UT_TAX_RATE(0);//40
                    bean.setSTATE_UT_TAX_AMOUNT(0);//41
                    bean.setCESS_RATE_ADVALOERM(0);//42
                    bean.setCESS_AMOUNT_ADVALEROM(0);//43
                    bean.setCESS_RATE_SPECIFIC(0);//44
                    bean.setCESS_AMOUNT_SPECIFIC(0);//45
                    //bean.setVALUE_INCLUDING_TAX(resultSet.getDouble("T4TXBA"));//46//changes date on 09102017
                    bean.setVALUE_INCLUDING_TAX(resultSet.getDouble("CIFVALUE")+resultSet.getDouble("TAXAMT"));//46
                    bean.setINDICATE_REVERSE_CHARGE("N");//47                        
                    
                    bean.setINELIGIBLE_ITC("IG");//48
                    
                    /*if(resultSet.getString("T4CSNO")!=null && resultSet.getString("T4CSNO").startsWith("99")){
                        bean.setINELIGIBLE_ITC("IS");//48
                    } else{
                        bean.setINELIGIBLE_ITC("IG");//48
                    }*/
                    
                    bean.setCOMMON_INWARD_SUPPLIES("");//49
                    bean.setAMOUNT_ITC_IGST(resultSet.getDouble("TAXAMT"));//50
                    bean.setAMOUNT_ITC_CGST(0);//51
                    bean.setAMOUNT_ITC_SGST(0);//52
                    bean.setAMOUNT_ITC_CESS(0);//53
                    bean.setITC_REVERSAL_IDENTIFIER("");//54
                    bean.setREASON_ISSUING("");//55
                    bean.setPURCHASE_BILL_VOUCHER_NO(OREFNO);//56
                    bean.setPURCHASE_BILL_VOUCHER_DATE(resultSet.getString("EGACDT"));//57
                    bean.setPAYMENT_VOUCHER_NUMBER("");//58
                    bean.setPAYMENT_DATE("");//59
                    bean.setCONTRACT_NUMBER("");//60
                    bean.setCONTRACT_DATE("");//61
                    bean.setCONTRACT_VALUE(0);//62
                    bean.setUSER_DEFINED_FIELD_1("");//63
                    bean.setUSER_DEFINED_FIELD_2(resultSet.getString("EGACDT"));//64
                    bean.setUSER_DEFINED_FIELD_3(OREFNO);//65
                    beans.add(bean);
                }
                if(resultSet!=null){
                    resultSet.close();
                }
                if(stmt!=null){
                    stmt.close();
                }
            } catch (Exception e){
                System.out.println("GoodsSupplierAction.class "+e.getMessage());
            } finally{
                if(conn!=null){
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(SupplierInwordReportActionFC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return beans;
    }
}
