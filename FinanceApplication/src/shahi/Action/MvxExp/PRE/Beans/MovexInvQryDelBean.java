/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;


public class MovexInvQryDelBean {
    
    private String CO_NO;
    private String CO_LINE;
    private String ITEM_NO;
    private Double CO_QTY;
    private Double CO_RATE;
    private Double CO_GR;
    private Double INV_QTY;
    private Double INV_SALEPR;
    private Double INV_NETPR;
    private Double ADJUST_FC;
    private Double SALE_FOB;
    private Double NET_FOB;
    private String MSG1;

    public MovexInvQryDelBean(String CO_NO, String CO_LINE, String ITEM_NO, Double CO_QTY, Double CO_RATE, Double CO_GR, Double INV_QTY, Double INV_SALEPR, Double INV_NETPR, Double ADJUST_FC, Double SALE_FOB, Double NET_FOB,String MSG1) {
        this.CO_NO = CO_NO;
        this.CO_LINE = CO_LINE;
        this.ITEM_NO = ITEM_NO;
        this.CO_QTY = CO_QTY;
        this.CO_RATE = CO_RATE;
        this.CO_GR = CO_GR;
        this.INV_QTY = INV_QTY;
        this.INV_SALEPR = INV_SALEPR;
        this.INV_NETPR = INV_NETPR;
        this.ADJUST_FC = ADJUST_FC;
        this.SALE_FOB = SALE_FOB;
        this.NET_FOB = NET_FOB;
        this.MSG1=MSG1;
    }

    
    
    
    
    
    public String getCO_NO() {
        return CO_NO;
    }

    public void setCO_NO(String CO_NO) {
        this.CO_NO = CO_NO;
    }

    public String getCO_LINE() {
        return CO_LINE;
    }

    public void setCO_LINE(String CO_LINE) {
        this.CO_LINE = CO_LINE;
    }

    public String getITEM_NO() {
        return ITEM_NO;
    }

    public void setITEM_NO(String ITEM_NO) {
        this.ITEM_NO = ITEM_NO;
    }

    public Double getCO_QTY() {
        return CO_QTY;
    }

    public void setCO_QTY(Double CO_QTY) {
        this.CO_QTY = CO_QTY;
    }

    public Double getCO_RATE() {
        return CO_RATE;
    }

    public void setCO_RATE(Double CO_RATE) {
        this.CO_RATE = CO_RATE;
    }

    public Double getCO_GR() {
        return CO_GR;
    }

    public void setCO_GR(Double CO_GR) {
        this.CO_GR = CO_GR;
    }

    public Double getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(Double INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public Double getINV_SALEPR() {
        return INV_SALEPR;
    }

    public void setINV_SALEPR(Double INV_SALEPR) {
        this.INV_SALEPR = INV_SALEPR;
    }

    public Double getINV_NETPR() {
        return INV_NETPR;
    }

    public void setINV_NETPR(Double INV_NETPR) {
        this.INV_NETPR = INV_NETPR;
    }

    public Double getADJUST_FC() {
        return ADJUST_FC;
    }

    public void setADJUST_FC(Double ADJUST_FC) {
        this.ADJUST_FC = ADJUST_FC;
    }

    public Double getSALE_FOB() {
        return SALE_FOB;
    }

    public void setSALE_FOB(Double SALE_FOB) {
        this.SALE_FOB = SALE_FOB;
    }

    public Double getNET_FOB() {
        return NET_FOB;
    }

    public void setNET_FOB(Double NET_FOB) {
        this.NET_FOB = NET_FOB;
    }

    public String getMSG1() {
        return MSG1;
    }

    public void setMSG1(String MSG1) {
        this.MSG1 = MSG1;
    }
   
   

    
    

    
    
    
}