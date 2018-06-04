/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import shahi.Action.MI.Beans.APS106MIGetBean;

/**
 *
 * @author Vivek
 */

public class APS106MI extends BaseMI{
    public APS106MI()
    {
        setProgram("APS106MI");
    }
    public int SetLstMaxRec()
    {
        int recFlag=0;
        javaMI.mvxClearFields();
        recFlag=javaMI.mvxAccess("SetLstMaxRec 0");
        return recFlag;
    }
    public String Add(String CONO, String DIVI, String SPYN, String SUNO, String PPYR, String PPYN, String YEA4, String APCD,
            String CRTP, String ARAT, String CUCD, String CKDT, String PYME, String TEPY, String PUNO, String DARC,
            String DUDT, String VTCD, String VPPM, String AMT1,String NAKU) {
        int recFlag;
        String ERROR = null;
        String identity = "Add";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);// Company Number
        javaMI.mvxSetField("DIVI", DIVI);// Division
        javaMI.mvxSetField("SPYN", SPYN);// Payee
        javaMI.mvxSetField("SUNO", SUNO);// Supplier
        javaMI.mvxSetField("PPYR", PPYR);// Referance Number
        javaMI.mvxSetField("PPYN", PPYN);// Payment Request number
        javaMI.mvxSetField("YEA4", YEA4);// Year
        javaMI.mvxSetField("APCD", APCD);// Authorised User
        javaMI.mvxSetField("CRTP", CRTP);// Exchange Rate Type
        javaMI.mvxSetField("ARAT", ARAT);// Invoice rate
        javaMI.mvxSetField("CUCD", CUCD);// Currency
        javaMI.mvxSetField("CKDT", CKDT);// Check Date
        javaMI.mvxSetField("PYME", PYME);// Payment Method
        javaMI.mvxSetField("TEPY", TEPY);// Payment Terms
        javaMI.mvxSetField("PUNO", PUNO);// PO Number
        javaMI.mvxSetField("DARC", DARC);// Date Received
        javaMI.mvxSetField("DUDT", DUDT);// Due Date
        javaMI.mvxSetField("VTCD", VTCD);// VAT Code
        javaMI.mvxSetField("VPPM", VPPM);// VAT On Prepay
        javaMI.mvxSetField("AMT1", AMT1);// On Account Amount
        javaMI.mvxSetField("NAKU", NAKU);// Non TDS Amount
        recFlag = javaMI.mvxAccess("Add");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            ERROR = "OK";
        }
        return ERROR;
    }
    public String Delete(String CONO,String DIVI,String SPYN,String SUNO,String PPYR,String PPYN,String YEA4){
        int recFlag;
        String ERROR = null;
        String identity = "Delete";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);// Company Number
        javaMI.mvxSetField("DIVI", DIVI);// Division
        javaMI.mvxSetField("SPYN", SPYN);// Payee
        javaMI.mvxSetField("SUNO", SUNO);// Supplier
        javaMI.mvxSetField("PPYR", PPYR);// Referance Number
        javaMI.mvxSetField("PPYN", PPYN);// Payment Request number
        javaMI.mvxSetField("YEA4", YEA4);// Year
        recFlag = javaMI.mvxAccess("Delete");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            ERROR = "OK";
        }
        return ERROR;
    }
    public APS106MIGetBean Get(String CONO,String DIVI,String SPYN,String SUNO,String PPYR,String PPYN,String YEA4){
        int recFlag;
        String ERROR = null;
        APS106MIGetBean aPS106MIGetBean = new APS106MIGetBean();
        String identity = "Get";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);// Company Number
        javaMI.mvxSetField("DIVI", DIVI);// Division
        javaMI.mvxSetField("SPYN", SPYN);// Payee
        javaMI.mvxSetField("SUNO", SUNO);// Supplier
        javaMI.mvxSetField("PPYR", PPYR);// Referance Number
        javaMI.mvxSetField("PPYN", PPYN);// Payment Request number
        javaMI.mvxSetField("YEA4", YEA4);// Year
        recFlag = javaMI.mvxAccess("Get");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            aPS106MIGetBean.setCONO(javaMI.mvxGetField("CONO"));
            aPS106MIGetBean.setDIVI(javaMI.mvxGetField("DIVI"));
            aPS106MIGetBean.setSPYN(javaMI.mvxGetField("SPYN"));
            aPS106MIGetBean.setSUNO(javaMI.mvxGetField("SUNO"));
            aPS106MIGetBean.setPPYR(javaMI.mvxGetField("PPYR"));
            aPS106MIGetBean.setPPYN(javaMI.mvxGetField("PPYN"));
            aPS106MIGetBean.setYEA4(javaMI.mvxGetField("YEA4"));
            aPS106MIGetBean.setAPCD(javaMI.mvxGetField("APCD"));
            aPS106MIGetBean.setCRTP(javaMI.mvxGetField("CRTP"));
            aPS106MIGetBean.setARAT(javaMI.mvxGetField("ARAT"));
            aPS106MIGetBean.setCUCD(javaMI.mvxGetField("CUCD"));
            aPS106MIGetBean.setCKDT(javaMI.mvxGetField("CKDT"));
            aPS106MIGetBean.setPYME(javaMI.mvxGetField("PYME"));
            aPS106MIGetBean.setTEPY(javaMI.mvxGetField("TEPY"));
            aPS106MIGetBean.setPUNO(javaMI.mvxGetField("PUNO"));
            aPS106MIGetBean.setDARC(javaMI.mvxGetField("DARC"));
            aPS106MIGetBean.setDUDT(javaMI.mvxGetField("DUDT"));
            aPS106MIGetBean.setVTCD(javaMI.mvxGetField("VTCD"));
            aPS106MIGetBean.setVPPM(javaMI.mvxGetField("VPPM"));
            aPS106MIGetBean.setCUAM(javaMI.mvxGetField("CUAM"));
            aPS106MIGetBean.setAMT2(javaMI.mvxGetField("AMT2"));
            aPS106MIGetBean.setAMT1(javaMI.mvxGetField("AMT1"));
            aPS106MIGetBean.setPPYS(javaMI.mvxGetField("PPYS"));
            aPS106MIGetBean.setACAM(javaMI.mvxGetField("ACAM"));
            aPS106MIGetBean.setADMT(javaMI.mvxGetField("ADMT"));
        }
        return aPS106MIGetBean;
    }
}
