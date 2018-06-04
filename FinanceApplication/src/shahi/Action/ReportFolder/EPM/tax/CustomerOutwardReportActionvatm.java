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
import shahi.Action.ReportFolder.EPM.tax.bean.CustomerOutwardReportBean;
import shahi.Action.database.connectionShahiHris;

/**
 *
 * @author Vivek
 */
public class CustomerOutwardReportActionvatm {
    public List<CustomerOutwardReportBean> generateReport(String COMPANY,String DIVISION,String dateFrom, String dateTo,List<String> GEOCODE) {
        List<CustomerOutwardReportBean> beans = new ArrayList<CustomerOutwardReportBean>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        if (dateFrom != null && dateTo != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
                dateFrom = format1.format(format.parse(dateFrom));
                dateTo = format1.format(format.parse(dateTo));
                String query="";
                if(GEOCODE!=null && GEOCODE.size()>0){
                    query+=" AND B.T3GEOF IN(";
                    for(String GEO : GEOCODE){
                        query+="'"+GEO+"',";
                    }
                    query=query.substring(0,query.lastIndexOf(","));
                    query+=")";
                }
                conn = new connectionShahiHris().getConnection();
                /*stmt = conn.prepareStatement("SELECT DISTINCT C.OKCUNM,C.OKCFC3,NVL(B.T3EXIN,B.T3IVNO) T3IVNO,"
                        +" substr(B.T3IVDT,1, 4)||'-'||substr(B.T3IVDT,5,2)||'-'||substr(B.T3IVDT, 7,2) T3IVDT,B.T3IVSQ,"
                        +" D.OPVRNO,B.T3CUNO,D.OPECAR,SUBSTR(A.T3CSNO,0,INSTR(A.T3CSNO,'-')-1) T3CSNO,A.T3ITNO,E.MMITDS,A.T3UNMS,A.T3ZQTY,"
                        +" B.T3VTAM-(B.T3TXA1+B.T3TXA2+B.T3TXA3) TXVAL, B.T3TAR3, B.T3TXA3, B.T3TAR1, B.T3TXA1, B.T3TAR2, B.T3TXA2,"
                        +" B.T3VTAM,F.GSTN,SUBSTR(F.GSTN,0,2) POS,SUBSTR(B.T3GEOT,6,2) CSTAT"
                        +" FROM PRODBI.ZCTAXL A,PRODBI.CTAXLN B ,PRODBI.OCUSMA C,PRODBI.OCUSAD D,PRODBI.MITMAS E,SEPLWEB.M4_WHLO_MASTER@IBM.WORLD@IBM F"
                        +" WHERE A.T3CONO=B.T3CONO AND A.T3DIVI=B.T3DIVI AND A.T3YEA4=B.T3YEA4 AND A.T3INPX=B.T3INPX AND"
                        +" A.T3IVNO=B.T3IVNO AND A.T3IVSQ=B.T3IVSQ AND B.T3CONO=C.OKCONO AND B.T3CUNO=C.OKCUNO AND"
                        +" B.T3CONO=D.OPCONO AND B.T3CUNO=D.OPCUNO AND B.T3GEOT=D.OPGEOC AND"
                        +" A.T3CONO=E.MMCONO(+) AND A.T3ITNO=E.MMITNO(+) AND"
                        +" B.T3CONO=F.M4CONO AND B.T3DIVI=F.M4DIVI AND B.T3GEOF=F.M4GEOC AND F.M4GEOC IS NOT NULL AND F.GSTN IS NOT NULL AND"
                        +" A.T3CONO=? AND A.T3DIVI=? AND"
                        +" B.T3IVDT BETWEEN ? AND ? AND (B.T3TXA1+B.T3TXA2+B.T3TXA3)<>0"+query
                        +" ORDER BY 1, 2, 3, 4, 5");*/
                stmt = conn.prepareStatement("SELECT OKCUNM,OKCFC3,T3IVNO,T3IVDT,T3CUNO,T3CSNO,MMITDS,T3UNMS,T3ZQTY,OPVRNO,TXVAL,T3TAR3,T3TXA3,T3TAR1,T3TXA1,T3TAR2,T3TXA2,T3VTAM,GSTN,"
                        +" POS,CSTAT,XSSBNO,XSSBDT,XSEXIV,XSREM2,DOCTP,ESACDT,ESYEA4,ESVSER,ESVONO,XSCUCD,XSACAM,sum(T3VTAM+(T3TXA1+T3TXA2+T3TXA3)) over (partition by"
                        +" T3CUNO,T3IVNO) INVVAL,ROW_NUMBER () OVER (PARTITION BY T3CUNO,T3IVNO ORDER BY T3IVNO) SEQ_NO"
                        +" from ("
                        +" SELECT C.OKCUNM,C.OKCFC3,NVL(B.T3EXIN,B.T3IVNO) T3IVNO,"
                        +" substr(B.T3IVDT,1, 4)||'-'||substr(B.T3IVDT,5,2)||'-'||substr(B.T3IVDT, 7,2) T3IVDT,"
                        +" B.T3CUNO,SUBSTR(A.T3CSNO,0,INSTR(A.T3CSNO,'-')-1) T3CSNO,MAX(E.MMITDS) MMITDS,MAX(A.T3UNMS) T3UNMS,"
                        +" sum(case when(B.t3vtam<0) then 0 else A.t3zqty end) T3ZQTY,D.OPVRNO,"
                        +" SUM(B.T3VTAM) TXVAL,B.T3TAR3, SUM(B.T3TXA3) T3TXA3,B.T3TAR1," //MAX(B.T3TAR1) T3TAR1,MAX(B.T3TAR2) T3TAR2,MAX(B.T3TAR3) T3TAR3
                        +" SUM(B.T3TXA1) T3TXA1,B.T3TAR2, SUM(B.T3TXA2) T3TXA2,"
                        +" SUM(B.T3VTAM) T3VTAM,MAX(G.XSACAM) XSACAM,F.GSTN,SUBSTR(F.GSTN,0,2) POS,SUBSTR(B.T3GEOT,6,2) CSTAT,"
                        +" G.XSSBNO,substr(G.XSSBDT,1, 4)||'-'||substr(G.XSSBDT,5,2)||'-'||substr(G.XSSBDT, 7,2) XSSBDT,G.XSEXIV,G.XSREM2,"
                        +" MAX(CASE WHEN TRIM(G.XSCUCD)!='INR' THEN 'EXPWT' ELSE 'INV' END) DOCTP,MAX(G.XSCUCD) XSCUCD,ESACDT,MAX(ESYEA4) ESYEA4,MAX(ESVSER) ESVSER,MAX(ESVONO) ESVONO"
                        +" FROM PRODBI.ZCTAXL A,PRODBI.CTAXLN B ,PRODBI.OCUSMA C,"
                        +" (select opcono,opcuno,opgeoc,max(opvrno) opvrno from prodbi.ocusad where opcOno='111' group by  opcono,opcuno,opgeoc) D,PRODBI.MITMAS E,SEPLWEB.M4_WHLO_MASTER@IBM.WORLD@IBM F,"
                        +" PRODBI.XSHPIN G,PRODBI.FSLEDG H"
                        +" WHERE A.T3CONO=B.T3CONO AND A.T3DIVI=B.T3DIVI AND A.T3YEA4=B.T3YEA4 AND A.T3INPX=B.T3INPX AND"
                        +" A.T3IVNO=B.T3IVNO AND A.T3IVSQ=B.T3IVSQ AND B.T3CONO=C.OKCONO AND B.T3CUNO=C.OKCUNO AND"
                        +" A.T3CONO=E.MMCONO(+) AND A.T3ITNO=E.MMITNO(+) AND"
                        +" B.T3CONO=F.M4CONO AND B.T3DIVI=F.M4DIVI AND B.T3GEOF=F.M4GEOC AND F.M4GEOC IS NOT NULL AND F.GSTN IS NOT NULL AND"
                        +" B.T3CONO=D.OPCONO(+) AND B.T3CUNO=D.OPCUNO(+) AND B.T3GEOT=D.OPGEOC(+) AND"
                        +" B.T3CONO=XSCONO(+) AND B.T3DIVI=XSDIVI(+) AND B.T3YEA4=XSYEA4(+) AND B.T3CUNO=XSCUNO(+) AND NVL(B.T3EXIN,B.T3IVNO)=XSIVNO(+) AND nvl(TRIM(G.XSCUCD),'INR')='INR' AND"
                        +" B.T3CONO=H.ESCONO AND B.T3DIVI=H.ESDIVI AND B.T3YEA4=H.ESYEA4 AND B.T3CUNO=H.ESCUNO AND NVL(B.T3EXIN,B.T3IVNO)=H.ESCINO AND"
                        +" H.ESCONO=? AND H.ESDIVI=? AND H.ESYEA4='2017' AND"
                        +" H.ESACDT BETWEEN ? AND ?"+query +"  AND A.T3STAT='01' AND H.ESTRCD='10'"
                        +" GROUP BY C.OKCUNM,C.OKCFC3,NVL(B.T3EXIN,B.T3IVNO),"
                        +" substr(B.T3IVDT,1, 4)||'-'||substr(B.T3IVDT,5,2)||'-'||substr(B.T3IVDT, 7,2),"
                        +" B.T3CUNO,F.GSTN,SUBSTR(F.GSTN,0,2),SUBSTR(B.T3GEOT,6,2),SUBSTR(A.T3CSNO,0,INSTR(A.T3CSNO,'-')-1),D.OPVRNO,B.T3TAR3,B.T3TAR2,B.T3TAR1,"
                        +" G.XSSBNO,substr(G.XSSBDT,1, 4)||'-'||substr(G.XSSBDT,5,2)||'-'||substr(G.XSSBDT, 7,2),G.XSEXIV,G.XSREM2,ESACDT"
                        +" )"
                        +" ORDER BY 1, 2, 3, 4, 5");
                stmt.setString(1, COMPANY);
                stmt.setString(2, DIVISION);
                stmt.setString(3, dateFrom);
                stmt.setString(4, dateTo);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("MMyyyy");
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    CustomerOutwardReportBean bean = new CustomerOutwardReportBean();
                    bean.setSOURCEIDENTIFIER("");//PENDING KULDEEP SIR//1
                    bean.setSOURCEFILENAME("");//PENDING KULDEEP SIR//2
                    bean.setGLACCOUNTCODE("");//PENDING KULDEEP SIR//3
                    bean.setDIVISION("");//PENDING KULDEEP SIR//4
                    bean.setSUBDIVISION("");//PENDING KULDEEP SIR//5
                    bean.setPROFITCENTRE1("");//PENDING KULDEEP SIR//6
                    bean.setPROFITCENTRE2("");//PENDING KULDEEP SIR//7
                    bean.setPLANTCODE("");//PENDING KULDEEP SIR//8
                    bean.setRETURNPERIOD(formatter1.format(formatter.parse(dateTo)));//9
                    bean.setSUPPLIERGSTIN(resultSet.getString("GSTN"));//PENDING KULDEEP SIR//10
                    bean.setDOCUMENTTYPE("INV");//11
                    if(resultSet.getString("XSCUCD")==null || (resultSet.getString("XSCUCD")!=null && resultSet.getString("XSCUCD").trim().equalsIgnoreCase("INR"))){
                        if((resultSet.getDouble("T3TXA1")+resultSet.getDouble("T3TXA2")+resultSet.getDouble("T3TXA3"))>0){
                            bean.setSUPPLYTYPE("TAX");//12
                        } else{
                            bean.setSUPPLYTYPE("TAX");//12  -- EXP removed on 20/12/17
                        }
                    } else{                        
                        bean.setSUPPLYTYPE("EXPWT");//12
                    }
                    if(resultSet.getString("XSEXIV")!=null && resultSet.getString("XSEXIV").length()>0){
                        bean.setDOCUMENTNUMBER(resultSet.getString("XSEXIV"));//13
                    } else{
                        bean.setDOCUMENTNUMBER(resultSet.getString("T3IVNO"));//13
                    }
                    bean.setDOCUMENTDATE(resultSet.getString("T3IVDT"));//14
                    bean.setORIGINALDOCUMENTNUMBER("");//PENDING KULDEEP SIR//15
                    bean.setORIGINALDOCUMENTDATE("");//PENDING KULDEEP SIR//16
                    bean.setCRDRPREGST("");//PENDING KULDEEP SIR//17
                    bean.setLINENUMBER(resultSet.getInt("SEQ_NO"));//18
                    bean.setCUSTOMERGSTIN(resultSet.getString("OPVRNO"));//19
                    bean.setUINORCOMPOSITION("");//PENDING KULDEEP SIR//20
                    bean.setORIGINALCUSTOMERGSTIN("");//PENDING KULDEEP SIR//21
                    bean.setCUSTOMERNAME(resultSet.getString("OKCUNM"));//22
                    bean.setCUSTOMERCODE(resultSet.getString("T3CUNO"));//23
                    bean.setBILLTOSTATE(resultSet.getString("CSTAT"));//24
                    bean.setSHIPTOSTATE(resultSet.getString("CSTAT"));//25
                    bean.setPOS(resultSet.getString("CSTAT"));//26
                    bean.setPORTCODE(resultSet.getString("XSREM2"));//27
                    if(resultSet.getString("XSSBNO")!=null && resultSet.getString("XSSBNO").length()>7){
                        bean.setSHIPPINGBILLNUMBER(resultSet.getString("XSSBNO").substring(resultSet.getString("XSSBNO").length()-7));//28
                    } else{
                        bean.setSHIPPINGBILLNUMBER(resultSet.getString("XSSBNO"));//28
                    }
                    
                    bean.setSHIPPINGBILLDATE(resultSet.getString("XSSBDT"));//29
                    if(resultSet.getString("XSCUCD")==null || (resultSet.getString("XSCUCD")!=null && resultSet.getString("XSCUCD").trim().equalsIgnoreCase("INR"))){
                    } else{
                        bean.setFOB(resultSet.getDouble("T3VTAM"));//30
                    }
                    bean.setEXPORTDUTY(0.0);//PENDING KULDEEP SIR//31
                    bean.setHSNORSAC(resultSet.getString("T3CSNO"));//32
                    bean.setPRODUCTCODE("");//REMOVED KULDEEP SIR//33
                    bean.setPRODUCTDESCRIPTION(resultSet.getString("MMITDS"));//34
                    bean.setCATEGORYOFPRODUCT("");//PENDING KULDEEP SIR//35
                    bean.setUNITOFMEASUREMENT(resultSet.getString("T3UNMS"));//36
                    bean.setQUANTITY(resultSet.getDouble("T3ZQTY"));//37
                                        
                    if(resultSet.getString("XSCUCD")==null || (resultSet.getString("XSCUCD")!=null && resultSet.getString("XSCUCD").trim().equalsIgnoreCase("INR"))){
                        bean.setTAXABLEVALUE(resultSet.getDouble("TXVAL"));//38
                    } else{
                        if(resultSet.getDouble("XSACAM")==0){
                            bean.setTAXABLEVALUE(resultSet.getDouble("TXVAL"));//38
                        }else{
                            bean.setTAXABLEVALUE(resultSet.getDouble("XSACAM"));//38 // 
                        }
                    }
                    bean.setINTEGRATEDTAXRATE(resultSet.getDouble("T3TAR3"));//39
                    bean.setINTEGRATEDTAXAMOUNT(resultSet.getDouble("T3TXA3"));//40
                    bean.setCENTRALTAXRATE(resultSet.getDouble("T3TAR1"));//41
                    bean.setCENTRALTAXAMOUNT(resultSet.getDouble("T3TXA1"));//42
                    bean.setSTATEUTTAXRATE(resultSet.getDouble("T3TAR2"));//43
                    bean.setSTATEUTTAXAMOUNT(resultSet.getDouble("T3TXA2"));//44
                    bean.setCESSRATEADVALOREM(0.0);//PENDING KULDEEP SIR//45
                    bean.setCESSAMOUNTADVALOREM(0.0);//PENDING KULDEEP SIR//46
                    bean.setCESSRATESPECIFIC(0.0);//PENDING KULDEEP SIR//47
                    bean.setCESSAMOUNTSPECIFIC(0.0);//PENDING KULDEEP SIR//48
                    
                    if(resultSet.getString("XSCUCD")==null || (resultSet.getString("XSCUCD")!=null && resultSet.getString("XSCUCD").trim().equalsIgnoreCase("INR"))){
                        bean.setINVOICEVALUE(resultSet.getDouble("INVVAL"));//49 T3VTAM remove on 07122017
                        
                    }else{
                        //bean.setINVOICEVALUE(resultSet.getDouble("XSACAM"));//49//Removed on 05102017
                        bean.setINVOICEVALUE(resultSet.getDouble("INVVAL"));//49
                    }
                    
                    if(resultSet.getString("OKCFC3")!=null && resultSet.getString("OKCFC3").trim().equalsIgnoreCase("REG")){
                        bean.setREVERSECHARGEFLAG("N");//50
                    }else{
                        bean.setREVERSECHARGEFLAG("Y");//50
                    }
                  /*   if (resultSet.getString("T3CSNO")!=null && resultSet.getString("T3CUNO").substring(0,3).equals("SEPL") && (resultSet.getString("T3CSNO").substring(0,3).equals("5802") || resultSet.getString("T3CSNO").substring(0,3).equals("5202")))
                         { bean.setTCSFLAG("Y");
                     }else{
                          bean.setTCSFLAG("N");//51
                     }*/
                    bean.setTCSFLAG("N");//51
                    bean.setECOMGSTIN(resultSet.getString("OPVRNO"));//52
                    bean.setITCFLAG("");//PENDING KULDEEP SIR//53
                    bean.setREASONFORCREDITDEBITNOTE("");//PENDING KULDEEP SIR//54
                    bean.setACCOUNTINGVOUCHERNUMBER(resultSet.getString("ESYEA4")+"/"+resultSet.getString("ESVSER")+"/"+resultSet.getString("ESVONO"));//PENDING KULDEEP SIR//55
                    bean.setACCOUNTINGVOUCHERDATE(resultSet.getString("ESACDT"));//PENDING KULDEEP SIR//56
                    bean.setUSERDEFINEDFIELD1("");//PENDING KULDEEP SIR//57
                    bean.setUSERDEFINEDFIELD2("");//PENDING KULDEEP SIR//58
                    bean.setUSERDEFINEDFIELD3("");//PENDING KULDEEP SIR//59
                    beans.add(bean);
                }
                if(resultSet!=null){
                    resultSet.close();
                }
                if(stmt!=null){
                    stmt.close();
                }
            } catch (Exception e) {
                System.out.println("CustomerOutwardReportActionvatm.class " + e.getMessage());
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerOutwardReportActionvatm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return beans;
    }
}
