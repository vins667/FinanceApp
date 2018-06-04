/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill.Beans;

import java.util.List;

/**
 *
 * @author RANJEET
 */
public class M3BillEntryBean {
    
private String SL_NO    ;      
private String DEPT_SL_NO;     
private String BILL_NO    ;    
private String BILL_DATE   ;   
private String SUPPLIER_CODE;
private double BILL_AMOUNT   ;

private String BILL_SL_NO  ;           
private String CC_CODE   ;             
private String TYPE_SL_NO   ;          
private String SUB_TYPE_SL_NO ;        
private String PRODUCT_SL_NO  ;        
private double PRODUCT_AMOUNT ;   

private String CC_DESC;
private String TYPE_DESC;
private String SUB_TYPE_DESC;
private String PRODUCT_DESC;
private String SEARCHDESC;
private String PCH;
private double GROSS_AMT;
private String ACC_DATE;
private String ACC_NAME;
private String REMARKS;
private String ACC_RE_DATE;
private String ACC_RE_NAME;
private String BILL_WHLO;
private String REPORT_NO;
private String TAXABLE;
private String TAXABLEDESC;
private String REC_ACC_USER;
private String REC_ACC_DATE;
private String TEMPSTR;
private List ACREMARK;
private String DEBIT_AMOUNT;
private String REPORT_OLD;
private String GL_FLAG;
private String GST_PER;
private String HSNCODE;
private String HSNSLNO;
private String PRODUCT_QUANTITY;
private String UOM;


    public M3BillEntryBean(String SL_NO, String DEPT_SL_NO, String BILL_NO, String BILL_DATE, String SUPPLIER_CODE, double BILL_AMOUNT) {
        this.SL_NO = SL_NO;
        this.DEPT_SL_NO = DEPT_SL_NO;
        this.BILL_NO = BILL_NO;
        this.BILL_DATE = BILL_DATE;
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.BILL_AMOUNT = BILL_AMOUNT;
        
    }
    
     public M3BillEntryBean(String SL_NO, String DEPT_SL_NO, String BILL_NO, String BILL_DATE, String SUPPLIER_CODE, double BILL_AMOUNT,String HSNCODE,String HSNSLNO) {
        this.SL_NO = SL_NO;
        this.DEPT_SL_NO = DEPT_SL_NO;
        this.BILL_NO = BILL_NO;
        this.BILL_DATE = BILL_DATE;
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.BILL_AMOUNT = BILL_AMOUNT;
        this.HSNCODE=HSNCODE;
        this.HSNSLNO=HSNSLNO;
        
    }
    
     public M3BillEntryBean(String SL_NO, String DEPT_SL_NO, String BILL_NO, String BILL_DATE, String SUPPLIER_CODE, double BILL_AMOUNT,String GST_PER) {
        this.SL_NO = SL_NO;
        this.DEPT_SL_NO = DEPT_SL_NO;
        this.BILL_NO = BILL_NO;
        this.BILL_DATE = BILL_DATE;
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.BILL_AMOUNT = BILL_AMOUNT;
        this.GST_PER=GST_PER;
        
    }
    
     public M3BillEntryBean(String SL_NO, String DEPT_SL_NO, String BILL_NO, String BILL_DATE, String SUPPLIER_CODE, double BILL_AMOUNT,double PRODUCT_AMOUNT,
             double GROSS_AMT
            ,String ACC_DATE,String ACC_NAME,String BILL_WHLO,String REPORT_NO,List ACREMARK,String DEBIT_AMOUNT) {
        this.SL_NO = SL_NO;
        this.DEPT_SL_NO = DEPT_SL_NO;
        this.BILL_NO = BILL_NO;
        this.BILL_DATE = BILL_DATE;
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.BILL_AMOUNT = BILL_AMOUNT;
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
        this.GROSS_AMT=GROSS_AMT;
        this.ACC_DATE=ACC_DATE;
        this.ACC_NAME=ACC_NAME;
        this.BILL_WHLO=BILL_WHLO;
        this.REPORT_NO=REPORT_NO;
        this.ACREMARK=ACREMARK;
        this.DEBIT_AMOUNT=DEBIT_AMOUNT;
        
        
    }
    
    public M3BillEntryBean(String SL_NO, String DEPT_SL_NO, String BILL_NO, String BILL_DATE, String SUPPLIER_CODE, double BILL_AMOUNT,double PRODUCT_AMOUNT,double GROSS_AMT
            ,String ACC_DATE,String ACC_NAME,String ACC_RE_DATE,String ACC_RE_NAME,
            String BILL_WHLO,String REPORT_NO,String REC_ACC_DATE,String REC_ACC_USER,String TEMPSTR,
            String DEBIT_AMOUNT,String REPORT_OLD,String GL_FLAG) {
        this.SL_NO = SL_NO;
        this.DEPT_SL_NO = DEPT_SL_NO;
        this.BILL_NO = BILL_NO;
        this.BILL_DATE = BILL_DATE;
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.BILL_AMOUNT = BILL_AMOUNT;
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
        this.GROSS_AMT=GROSS_AMT;
        this.ACC_DATE=ACC_DATE;
        this.ACC_NAME=ACC_NAME;
        this.ACC_RE_DATE=ACC_RE_DATE;
        this.ACC_RE_NAME=ACC_RE_NAME;
         this.BILL_WHLO=BILL_WHLO;
         this.REPORT_NO=REPORT_NO;
         this.REC_ACC_DATE=REC_ACC_DATE;
         this.REC_ACC_USER=REC_ACC_USER;
         this.TEMPSTR=TEMPSTR;
         this.DEBIT_AMOUNT=DEBIT_AMOUNT;
         this.REPORT_OLD=REPORT_OLD;
         this.GL_FLAG=GL_FLAG;
        
    }

    public M3BillEntryBean(String DEPT_SL_NO, String SUPPLIER_CODE, String REPORT_NO,String REMARKS,String TEMPSTR ) {
        this.DEPT_SL_NO = DEPT_SL_NO;
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.REPORT_NO = REPORT_NO;
        this.REMARKS=REMARKS;
         this.TEMPSTR=TEMPSTR;
                
    }
    
    
    

    public M3BillEntryBean(String SL_NO, String BILL_SL_NO, String CC_CODE, String TYPE_SL_NO, String SUB_TYPE_SL_NO, String PRODUCT_SL_NO, double PRODUCT_AMOUNT) {
        this.SL_NO = SL_NO;
        this.BILL_SL_NO = BILL_SL_NO;
        this.CC_CODE = CC_CODE;
        this.TYPE_SL_NO = TYPE_SL_NO;
        this.SUB_TYPE_SL_NO = SUB_TYPE_SL_NO;
        this.PRODUCT_SL_NO = PRODUCT_SL_NO;
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
        
    }

    public M3BillEntryBean(String SL_NO, String BILL_SL_NO, String CC_CODE, String TYPE_SL_NO, String SUB_TYPE_SL_NO, String PRODUCT_SL_NO, double PRODUCT_AMOUNT, String CC_DESC, String TYPE_DESC, String SUB_TYPE_DESC,
            String PRODUCT_DESC,String SEARCHDESC,String PCH,String REMARKS,String TAXABLE,String TAXABLEDESC,String PRODUCT_QUANTITY,String UOM) {
        this.SL_NO = SL_NO;
        this.BILL_SL_NO = BILL_SL_NO;
        this.CC_CODE = CC_CODE;
        this.TYPE_SL_NO = TYPE_SL_NO;
        this.SUB_TYPE_SL_NO = SUB_TYPE_SL_NO;
        this.PRODUCT_SL_NO = PRODUCT_SL_NO;
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
        this.CC_DESC = CC_DESC;
        this.TYPE_DESC = TYPE_DESC;
        this.SUB_TYPE_DESC = SUB_TYPE_DESC;
        this.PRODUCT_DESC = PRODUCT_DESC;
        this.SEARCHDESC=SEARCHDESC;
        this.PCH=PCH;
        this.REMARKS=REMARKS;
        this.TAXABLE=TAXABLE;
        this.TAXABLEDESC=TAXABLEDESC;
        this.PRODUCT_QUANTITY=PRODUCT_QUANTITY;
        this.UOM=UOM;
    }

    public M3BillEntryBean(String CC_CODE, String TYPE_SL_NO, String SUB_TYPE_SL_NO, String SEARCHDESC) {
        this.CC_CODE = CC_CODE;
        this.TYPE_SL_NO = TYPE_SL_NO;
        this.SUB_TYPE_SL_NO = SUB_TYPE_SL_NO;
        this.SEARCHDESC = SEARCHDESC;
    }

    public M3BillEntryBean(String SL_NO, String PRODUCT_SL_NO, double PRODUCT_AMOUNT,String REMARKS,String TAXABLE,String TAXABLEDESC,String PRODUCT_QUANTITY,String UOM) {
        this.SL_NO = SL_NO;
        this.PRODUCT_SL_NO = PRODUCT_SL_NO;
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
        this.REMARKS=REMARKS;
        this.TAXABLE=TAXABLE;
        this.TAXABLEDESC=TAXABLEDESC;
        this.PRODUCT_QUANTITY=PRODUCT_QUANTITY;
        this.UOM=UOM;
    }






    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getDEPT_SL_NO() {
        return DEPT_SL_NO;
    }

    public void setDEPT_SL_NO(String DEPT_SL_NO) {
        this.DEPT_SL_NO = DEPT_SL_NO;
    }

    public String getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    public String getBILL_DATE() {
        return BILL_DATE;
    }

    public void setBILL_DATE(String BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
    }

    public String getSUPPLIER_CODE() {
        return SUPPLIER_CODE;
    }

    public void setSUPPLIER_CODE(String SUPPLIER_CODE) {
        this.SUPPLIER_CODE = SUPPLIER_CODE;
    }

    public double getBILL_AMOUNT() {
        return BILL_AMOUNT;
    }

    public void setBILL_AMOUNT(double BILL_AMOUNT) {
        this.BILL_AMOUNT = BILL_AMOUNT;
    }

   

    public String getBILL_SL_NO() {
        return BILL_SL_NO;
    }

    public void setBILL_SL_NO(String BILL_SL_NO) {
        this.BILL_SL_NO = BILL_SL_NO;
    }

    public String getCC_CODE() {
        return CC_CODE;
    }

    public void setCC_CODE(String CC_CODE) {
        this.CC_CODE = CC_CODE;
    }

    public String getTYPE_SL_NO() {
        return TYPE_SL_NO;
    }

    public void setTYPE_SL_NO(String TYPE_SL_NO) {
        this.TYPE_SL_NO = TYPE_SL_NO;
    }

    public String getSUB_TYPE_SL_NO() {
        return SUB_TYPE_SL_NO;
    }

    public void setSUB_TYPE_SL_NO(String SUB_TYPE_SL_NO) {
        this.SUB_TYPE_SL_NO = SUB_TYPE_SL_NO;
    }

    public String getPRODUCT_SL_NO() {
        return PRODUCT_SL_NO;
    }

    public void setPRODUCT_SL_NO(String PRODUCT_SL_NO) {
        this.PRODUCT_SL_NO = PRODUCT_SL_NO;
    }

    public double getPRODUCT_AMOUNT() {
        return PRODUCT_AMOUNT;
    }

    public void setPRODUCT_AMOUNT(double PRODUCT_AMOUNT) {
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
    }

    public String getCC_DESC() {
        return CC_DESC;
    }

    public void setCC_DESC(String CC_DESC) {
        this.CC_DESC = CC_DESC;
    }

    public String getTYPE_DESC() {
        return TYPE_DESC;
    }

    public void setTYPE_DESC(String TYPE_DESC) {
        this.TYPE_DESC = TYPE_DESC;
    }

    public String getSUB_TYPE_DESC() {
        return SUB_TYPE_DESC;
    }

    public void setSUB_TYPE_DESC(String SUB_TYPE_DESC) {
        this.SUB_TYPE_DESC = SUB_TYPE_DESC;
    }

    public String getPRODUCT_DESC() {
        return PRODUCT_DESC;
    }

    public void setPRODUCT_DESC(String PRODUCT_DESC) {
        this.PRODUCT_DESC = PRODUCT_DESC;
    }

    public String getSEARCHDESC() {
        return SEARCHDESC;
    }

    public void setSEARCHDESC(String SEARCHDESC) {
        this.SEARCHDESC = SEARCHDESC;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public double getGROSS_AMT() {
        return GROSS_AMT;
    }

    public void setGROSS_AMT(double GROSS_AMT) {
        this.GROSS_AMT = GROSS_AMT;
    }

    public String getACC_DATE() {
        return ACC_DATE;
    }

    public void setACC_DATE(String ACC_DATE) {
        this.ACC_DATE = ACC_DATE;
    }

    public String getACC_NAME() {
        return ACC_NAME;
    }

    public void setACC_NAME(String ACC_NAME) {
        this.ACC_NAME = ACC_NAME;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getACC_RE_DATE() {
        return ACC_RE_DATE;
    }

    public void setACC_RE_DATE(String ACC_RE_DATE) {
        this.ACC_RE_DATE = ACC_RE_DATE;
    }

    public String getACC_RE_NAME() {
        return ACC_RE_NAME;
    }

    public void setACC_RE_NAME(String ACC_RE_NAME) {
        this.ACC_RE_NAME = ACC_RE_NAME;
    }

    public String getBILL_WHLO() {
        return BILL_WHLO;
    }

    public void setBILL_WHLO(String BILL_WHLO) {
        this.BILL_WHLO = BILL_WHLO;
    }

    public String getREPORT_NO() {
        return REPORT_NO;
    }

    public void setREPORT_NO(String REPORT_NO) {
        this.REPORT_NO = REPORT_NO;
    }

    public String getTAXABLE() {
        return TAXABLE;
    }

    public void setTAXABLE(String TAXABLE) {
        this.TAXABLE = TAXABLE;
    }

    public String getTAXABLEDESC() {
        return TAXABLEDESC;
    }

    public void setTAXABLEDESC(String TAXABLEDESC) {
        this.TAXABLEDESC = TAXABLEDESC;
    }

    public String getREC_ACC_USER() {
        return REC_ACC_USER;
    }

    public void setREC_ACC_USER(String REC_ACC_USER) {
        this.REC_ACC_USER = REC_ACC_USER;
    }

    public String getREC_ACC_DATE() {
        return REC_ACC_DATE;
    }

    public void setREC_ACC_DATE(String REC_ACC_DATE) {
        this.REC_ACC_DATE = REC_ACC_DATE;
    }

    public String getTEMPSTR() {
        return TEMPSTR;
    }

    public void setTEMPSTR(String TEMPSTR) {
        this.TEMPSTR = TEMPSTR;
    }

    public List getACREMARK() {
        return ACREMARK;
    }

    public void setACREMARK(List ACREMARK) {
        this.ACREMARK = ACREMARK;
    }

    public String getDEBIT_AMOUNT() {
        return DEBIT_AMOUNT;
    }

    public void setDEBIT_AMOUNT(String DEBIT_AMOUNT) {
        this.DEBIT_AMOUNT = DEBIT_AMOUNT;
    }

    public String getREPORT_OLD() {
        return REPORT_OLD;
    }

    public void setREPORT_OLD(String REPORT_OLD) {
        this.REPORT_OLD = REPORT_OLD;
    }

    public String getGL_FLAG() {
        return GL_FLAG;
    }

    public void setGL_FLAG(String GL_FLAG) {
        this.GL_FLAG = GL_FLAG;
    }

    public String getHSNCODE() {
        return HSNCODE;
    }

    public void setHSNCODE(String HSNCODE) {
        this.HSNCODE = HSNCODE;
    }

    public String getHSNSLNO() {
        return HSNSLNO;
    }

    public void setHSNSLNO(String HSNSLNO) {
        this.HSNSLNO = HSNSLNO;
    }

    public String getPRODUCT_QUANTITY() {
        return PRODUCT_QUANTITY;
    }

    public void setPRODUCT_QUANTITY(String PRODUCT_QUANTITY) {
        this.PRODUCT_QUANTITY = PRODUCT_QUANTITY;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }



    
}
