/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/**
 *
 * @author Sanjeev
 */
public class FittInvBean {
    
    private String EXCS_INV_NO;
    private String PCH;
    private String BUYER;
    private String INV_DATE;
    private String TO_DATE;
    private String ETD_DATE;
    private String FTP_DATE;
    private String AWB_DATE;
    private String FIN_DATE;
    private String CRNCY_CODE;
    private String INV_QTY;
    private String FOB_FC;
    private String GR_DECL;
    private String NET_FOB;

    public FittInvBean(String EXCS_INV_NO, String PCH, String BUYER, String INV_DATE, String TO_DATE, String ETD_DATE, String FTP_DATE, String AWB_DATE, String FIN_DATE, String CRNCY_CODE, String INV_QTY, String FOB_FC, String GR_DECL,String NET_FOB) {
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.PCH = PCH;
        this.BUYER = BUYER;
        this.INV_DATE = INV_DATE;
        this.TO_DATE = TO_DATE;
        this.ETD_DATE = ETD_DATE;
        this.FTP_DATE = FTP_DATE;
        this.AWB_DATE = AWB_DATE;
        this.FIN_DATE = FIN_DATE;
        this.CRNCY_CODE = CRNCY_CODE;
        this.INV_QTY = INV_QTY;
        this.FOB_FC = FOB_FC;
        this.GR_DECL = GR_DECL;
        this.NET_FOB=NET_FOB;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getINV_DATE() {
        return INV_DATE;
    }

    public void setINV_DATE(String INV_DATE) {
        this.INV_DATE = INV_DATE;
    }

    public String getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(String TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public String getETD_DATE() {
        return ETD_DATE;
    }

    public void setETD_DATE(String ETD_DATE) {
        this.ETD_DATE = ETD_DATE;
    }

    public String getFTP_DATE() {
        return FTP_DATE;
    }

    public void setFTP_DATE(String FTP_DATE) {
        this.FTP_DATE = FTP_DATE;
    }

    public String getAWB_DATE() {
        return AWB_DATE;
    }

    public void setAWB_DATE(String AWB_DATE) {
        this.AWB_DATE = AWB_DATE;
    }

    public String getFIN_DATE() {
        return FIN_DATE;
    }

    public void setFIN_DATE(String FIN_DATE) {
        this.FIN_DATE = FIN_DATE;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public String getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(String INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public String getFOB_FC() {
        return FOB_FC;
    }

    public void setFOB_FC(String FOB_FC) {
        this.FOB_FC = FOB_FC;
    }

    public String getGR_DECL() {
        return GR_DECL;
    }

    public void setGR_DECL(String GR_DECL) {
        this.GR_DECL = GR_DECL;
    }

    public String getNET_FOB() {
        return NET_FOB;
    }

    public void setNET_FOB(String NET_FOB) {
        this.NET_FOB = NET_FOB;
    }
    
      
    
    
}
