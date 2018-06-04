/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class MultipleInvBean {
    
    private String LOCATION;
    private String PLAN_NO;
    private String EXCS_INV_NO;
    private String HSCODE;       
    private String INV_DATE;
    private String PRICE_FC;
    private String PRICE_MISC;
    private String CRNCY_CODE;
    private String DESCRIPTION;
    private String DBK_SLNO;
    private String STR_SLNO;
    private String SCHEME_CODE;
    private String LINE_QTY;
    private double GR_DISC;
    private double FOB_AMT; 
    private String STR_MISC;
    private double IGST_PER;
    private double CGST_PER;
    private double SGST_PER;

    public MultipleInvBean(String LOCATION, String PLAN_NO,String EXCS_INV_NO, String HSCODE, String INV_DATE, String PRICE_FC, String PRICE_MISC, String CRNCY_CODE, String DESCRIPTION, String DBK_SLNO, String STR_SLNO, String SCHEME_CODE, String LINE_QTY, double GR_DISC, double FOB_AMT,String STR_MISC,double IGST_PER,double CGST_PER,double SGST_PER) {
        
        this.LOCATION = LOCATION;
        this.PLAN_NO=PLAN_NO;
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.HSCODE = HSCODE;
        this.INV_DATE = INV_DATE;
        this.PRICE_FC = PRICE_FC;
        this.PRICE_MISC = PRICE_MISC;
        this.CRNCY_CODE = CRNCY_CODE;
        this.DESCRIPTION = DESCRIPTION;
        this.DBK_SLNO = DBK_SLNO;
        this.STR_SLNO = STR_SLNO;
        this.SCHEME_CODE = SCHEME_CODE;
        this.LINE_QTY = LINE_QTY;
        this.GR_DISC = GR_DISC;
        this.FOB_AMT = FOB_AMT;
        this.STR_MISC=STR_MISC;
        this.IGST_PER=IGST_PER;
        this.CGST_PER=CGST_PER;
        this.SGST_PER=SGST_PER;
             
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getHSCODE() {
        return HSCODE;
    }

    public void setHSCODE(String HSCODE) {
        this.HSCODE = HSCODE;
    }

    public String getINV_DATE() {
        return INV_DATE;
    }

    public void setINV_DATE(String INV_DATE) {
        this.INV_DATE = INV_DATE;
    }

    public String getPRICE_FC() {
        return PRICE_FC;
    }

    public void setPRICE_FC(String PRICE_FC) {
        this.PRICE_FC = PRICE_FC;
    }

    public String getPRICE_MISC() {
        return PRICE_MISC;
    }

    public void setPRICE_MISC(String PRICE_MISC) {
        this.PRICE_MISC = PRICE_MISC;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDBK_SLNO() {
        return DBK_SLNO;
    }

    public void setDBK_SLNO(String DBK_SLNO) {
        this.DBK_SLNO = DBK_SLNO;
    }

    public String getSTR_SLNO() {
        return STR_SLNO;
    }

    public void setSTR_SLNO(String STR_SLNO) {
        this.STR_SLNO = STR_SLNO;
    }

    public String getSCHEME_CODE() {
        return SCHEME_CODE;
    }

    public void setSCHEME_CODE(String SCHEME_CODE) {
        this.SCHEME_CODE = SCHEME_CODE;
    }

    public String getLINE_QTY() {
        return LINE_QTY;
    }

    public void setLINE_QTY(String LINE_QTY) {
        this.LINE_QTY = LINE_QTY;
    }

    public double getGR_DISC() {
        return GR_DISC;
    }

    public void setGR_DISC(double GR_DISC) {
        this.GR_DISC = GR_DISC;
    }

    public double getFOB_AMT() {
        return FOB_AMT;
    }

    public void setFOB_AMT(double FOB_AMT) {
        this.FOB_AMT = FOB_AMT;
    }

    public String getSTR_MISC() {
        return STR_MISC;
    }

    public void setSTR_MISC(String STR_MISC) {
        this.STR_MISC = STR_MISC;
    }

    public String getPLAN_NO() {
        return PLAN_NO;
    }

    public void setPLAN_NO(String PLAN_NO) {
        this.PLAN_NO = PLAN_NO;
    }

    public double getIGST_PER() {
        return IGST_PER;
    }

    public void setIGST_PER(double IGST_PER) {
        this.IGST_PER = IGST_PER;
    }

    public double getCGST_PER() {
        return CGST_PER;
    }

    public void setCGST_PER(double CGST_PER) {
        this.CGST_PER = CGST_PER;
    }

    public double getSGST_PER() {
        return SGST_PER;
    }

    public void setSGST_PER(double SGST_PER) {
        this.SGST_PER = SGST_PER;
    }
    
  
      
    
}
