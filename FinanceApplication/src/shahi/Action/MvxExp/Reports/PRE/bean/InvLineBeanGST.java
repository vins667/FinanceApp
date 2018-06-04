/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author Sanjeev
 */
public class InvLineBeanGST {
    private String UNIT;
    private String INV_DESC;
    private String INV_QTY;
    private double KGS_QTY;
    private double INV_RATE;
    private double FOB_FC; 
    private double DISCOUNT;
    private double BCGST_PER;
    private double BSGST_PER;
    private double BIGST_PER;
    private String HSN_CODE;
    private Double UPCH_AMT; 
    private Double CGST_AMT;
    private Double SGST_AMT;
    private Double IGST_AMT;

    public InvLineBeanGST(){
        
    }

    public InvLineBeanGST(String UNIT, String INV_DESC, String INV_QTY, double KGS_QTY, double INV_RATE, double FOB_FC, double DISCOUNT, double BCGST_PER, double BSGST_PER, double BIGST_PER,String HSN_CODE,Double UPCH_AMT,
                          Double CGST_AMT,Double SGST_AMT, Double  IGST_AMT) {
        this.UNIT = UNIT;
        this.INV_DESC = INV_DESC;
        this.INV_QTY = INV_QTY;
        this.KGS_QTY = KGS_QTY;
        this.INV_RATE = INV_RATE;
        this.FOB_FC = FOB_FC;
        this.DISCOUNT = DISCOUNT;
        this.BCGST_PER = BCGST_PER;
        this.BSGST_PER = BSGST_PER;
        this.BIGST_PER = BIGST_PER;
        this.HSN_CODE=HSN_CODE;
        this.UPCH_AMT=UPCH_AMT;
        this.CGST_AMT=CGST_AMT;
        this.SGST_AMT=SGST_AMT;
        this.IGST_AMT=IGST_AMT;
        
    }

    public String getUNIT() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    public String getINV_DESC() {
        return INV_DESC;
    }

    public void setINV_DESC(String INV_DESC) {
        this.INV_DESC = INV_DESC;
    }

    public String getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(String INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public double getKGS_QTY() {
        return KGS_QTY;
    }

    public void setKGS_QTY(double KGS_QTY) {
        this.KGS_QTY = KGS_QTY;
    }

    public double getINV_RATE() {
        return INV_RATE;
    }

    public void setINV_RATE(double INV_RATE) {
        this.INV_RATE = INV_RATE;
    }

    public double getFOB_FC() {
        return FOB_FC;
    }

    public void setFOB_FC(double FOB_FC) {
        this.FOB_FC = FOB_FC;
    }

    public double getDISCOUNT() {
        return DISCOUNT;
    }

    public void setDISCOUNT(double DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }

    public double getBCGST_PER() {
        return BCGST_PER;
    }

    public void setBCGST_PER(double BCGST_PER) {
        this.BCGST_PER = BCGST_PER;
    }

    public double getBSGST_PER() {
        return BSGST_PER;
    }

    public void setBSGST_PER(double BSGST_PER) {
        this.BSGST_PER = BSGST_PER;
    }

    public double getBIGST_PER() {
        return BIGST_PER;
    }

    public void setBIGST_PER(double BIGST_PER) {
        this.BIGST_PER = BIGST_PER;
    }

    public String getHSN_CODE() {
        return HSN_CODE;
    }

    public void setHSN_CODE(String HSN_CODE) {
        this.HSN_CODE = HSN_CODE;
    }

    public Double getUPCH_AMT() {
        return UPCH_AMT;
    }

    public void setUPCH_AMT(Double UPCH_AMT) {
        this.UPCH_AMT = UPCH_AMT;
    }

    public Double getCGST_AMT() {
        return CGST_AMT;
    }

    public void setCGST_AMT(Double CGST_AMT) {
        this.CGST_AMT = CGST_AMT;
    }

    public Double getSGST_AMT() {
        return SGST_AMT;
    }

    public void setSGST_AMT(Double SGST_AMT) {
        this.SGST_AMT = SGST_AMT;
    }

    public Double getIGST_AMT() {
        return IGST_AMT;
    }

    public void setIGST_AMT(Double IGST_AMT) {
        this.IGST_AMT = IGST_AMT;
    }
    
     
   
     
    
}
