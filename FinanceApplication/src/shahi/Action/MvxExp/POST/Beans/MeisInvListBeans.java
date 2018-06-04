/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

import java.util.List;

/**
 *
 * @author Guddu Kumar
 */
public class MeisInvListBeans {
    
    private String COMP_NAME;
    private String COMP_ADDR;
    private String COMP_CST;
    private String COMP_TIN;
    private String CONS_NAME;
    private String CONS_ADDR;
    private String CONS_TIN;
    private String INVOICE_B;
    private String INVOICE_DATE;
    private String WORDAMT;
     private String TAXTYPE;
      private double TAXPERCNT;
    
    private List INVLINELST;
    private double TOTAL_AMT;
    private double TOTAL_SAL_AMT;
    private double TOTAL_SAL_AMT_PER;
    private String BUYER_STATE;
    
    public MeisInvListBeans(){
        
    }

    public MeisInvListBeans(String COMP_NAME, String COMP_ADDR, String COMP_CST, String COMP_TIN, String CONS_NAME, String CONS_ADDR, String CONS_TIN, String INVOICE_B, String INVOICE_DATE, String WORDAMT, String TAXTYPE, double TAXPERCNT, List INVLINELST, double TOTAL_AMT, double TOTAL_SAL_AMT, double TOTAL_SAL_AMT_PER,String BUYER_STATE) {
        this.COMP_NAME = COMP_NAME;
        this.COMP_ADDR = COMP_ADDR;
        this.COMP_CST = COMP_CST;
        this.COMP_TIN = COMP_TIN;
        this.CONS_NAME = CONS_NAME;
        this.CONS_ADDR = CONS_ADDR;
        this.CONS_TIN = CONS_TIN;
        this.INVOICE_B = INVOICE_B;
        this.INVOICE_DATE = INVOICE_DATE;
        this.WORDAMT = WORDAMT;
        this.TAXTYPE = TAXTYPE;
        this.TAXPERCNT = TAXPERCNT;
        this.INVLINELST = INVLINELST;
        this.TOTAL_AMT = TOTAL_AMT;
        this.TOTAL_SAL_AMT = TOTAL_SAL_AMT;
        this.TOTAL_SAL_AMT_PER = TOTAL_SAL_AMT_PER;
    }

    public String getCOMP_NAME() {
        return COMP_NAME;
    }

    public void setCOMP_NAME(String COMP_NAME) {
        this.COMP_NAME = COMP_NAME;
    }

    public String getCOMP_ADDR() {
        return COMP_ADDR;
    }

    public void setCOMP_ADDR(String COMP_ADDR) {
        this.COMP_ADDR = COMP_ADDR;
    }

    public String getCOMP_CST() {
        return COMP_CST;
    }

    public void setCOMP_CST(String COMP_CST) {
        this.COMP_CST = COMP_CST;
    }

    public String getCOMP_TIN() {
        return COMP_TIN;
    }

    public void setCOMP_TIN(String COMP_TIN) {
        this.COMP_TIN = COMP_TIN;
    }

    public String getCONS_NAME() {
        return CONS_NAME;
    }

    public void setCONS_NAME(String CONS_NAME) {
        this.CONS_NAME = CONS_NAME;
    }

    public String getCONS_ADDR() {
        return CONS_ADDR;
    }

    public void setCONS_ADDR(String CONS_ADDR) {
        this.CONS_ADDR = CONS_ADDR;
    }

    public String getCONS_TIN() {
        return CONS_TIN;
    }

    public void setCONS_TIN(String CONS_TIN) {
        this.CONS_TIN = CONS_TIN;
    }

    public String getINVOICE_B() {
        return INVOICE_B;
    }

    public void setINVOICE_B(String INVOICE_B) {
        this.INVOICE_B = INVOICE_B;
    }

    public String getINVOICE_DATE() {
        return INVOICE_DATE;
    }

    public void setINVOICE_DATE(String INVOICE_DATE) {
        this.INVOICE_DATE = INVOICE_DATE;
    }

    public String getWORDAMT() {
        return WORDAMT;
    }

    public void setWORDAMT(String WORDAMT) {
        this.WORDAMT = WORDAMT;
    }

    public String getTAXTYPE() {
        return TAXTYPE;
    }

    public void setTAXTYPE(String TAXTYPE) {
        this.TAXTYPE = TAXTYPE;
    }

    public double getTAXPERCNT() {
        return TAXPERCNT;
    }

    public void setTAXPERCNT(double TAXPERCNT) {
        this.TAXPERCNT = TAXPERCNT;
    }

    public List getINVLINELST() {
        return INVLINELST;
    }

    public void setINVLINELST(List INVLINELST) {
        this.INVLINELST = INVLINELST;
    }

    public double getTOTAL_AMT() {
        return TOTAL_AMT;
    }

    public void setTOTAL_AMT(double TOTAL_AMT) {
        this.TOTAL_AMT = TOTAL_AMT;
    }

    public double getTOTAL_SAL_AMT() {
        return TOTAL_SAL_AMT;
    }

    public void setTOTAL_SAL_AMT(double TOTAL_SAL_AMT) {
        this.TOTAL_SAL_AMT = TOTAL_SAL_AMT;
    }

    public double getTOTAL_SAL_AMT_PER() {
        return TOTAL_SAL_AMT_PER;
    }

    public void setTOTAL_SAL_AMT_PER(double TOTAL_SAL_AMT_PER) {
        this.TOTAL_SAL_AMT_PER = TOTAL_SAL_AMT_PER;
    }

    public String getBUYER_STATE() {
        return BUYER_STATE;
    }

    public void setBUYER_STATE(String BUYER_STATE) {
        this.BUYER_STATE = BUYER_STATE;
    }

    
    
    
    
}
