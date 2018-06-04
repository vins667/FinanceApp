/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class TOPlanBean {
    private String EXCS_INV_NO;
    private String INV_DATE;
    private String PORT;
    private String BUYER;
    private String CHA;
    private String BPO;
    private String STYLE;
    private String CFS_CODE;
    private String FY_CODE;
    private String DEL_DATE;
    private String EX_FY_DATE;
    private String CBM;
    private String INVQTY;
    private String FWD_DATE;

    public TOPlanBean(String EXCS_INV_NO, String INV_DATE, String PORT, String BUYER, String CHA, String BPO, String STYLE, String CFS_CODE, String FY_CODE, String DEL_DATE, String EX_FY_DATE, String CBM, String INVQTY, String FWD_DATE) {
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.INV_DATE = INV_DATE;
        this.PORT = PORT;
        this.BUYER = BUYER;
        this.CHA = CHA;
        this.BPO = BPO;
        this.STYLE = STYLE;
        this.CFS_CODE = CFS_CODE;
        this.FY_CODE = FY_CODE;
        this.DEL_DATE = DEL_DATE;
        this.EX_FY_DATE = EX_FY_DATE;
        this.CBM = CBM;
        this.INVQTY = INVQTY;
        this.FWD_DATE=FWD_DATE;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getINV_DATE() {
        return INV_DATE;
    }

    public void setINV_DATE(String INV_DATE) {
        this.INV_DATE = INV_DATE;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getCHA() {
        return CHA;
    }

    public void setCHA(String CHA) {
        this.CHA = CHA;
    }

    public String getBPO() {
        return BPO;
    }

    public void setBPO(String BPO) {
        this.BPO = BPO;
    }

    public String getSTYLE() {
        return STYLE;
    }

    public void setSTYLE(String STYLE) {
        this.STYLE = STYLE;
    }

    public String getCFS_CODE() {
        return CFS_CODE;
    }

    public void setCFS_CODE(String CFS_CODE) {
        this.CFS_CODE = CFS_CODE;
    }

    public String getFY_CODE() {
        return FY_CODE;
    }

    public void setFY_CODE(String FY_CODE) {
        this.FY_CODE = FY_CODE;
    }

    public String getDEL_DATE() {
        return DEL_DATE;
    }

    public void setDEL_DATE(String DEL_DATE) {
        this.DEL_DATE = DEL_DATE;
    }

    public String getEX_FY_DATE() {
        return EX_FY_DATE;
    }

    public void setEX_FY_DATE(String EX_FY_DATE) {
        this.EX_FY_DATE = EX_FY_DATE;
    }

    public String getCBM() {
        return CBM;
    }

    public void setCBM(String CBM) {
        this.CBM = CBM;
    }

    public String getINVQTY() {
        return INVQTY;
    }

    public void setINVQTY(String INVQTY) {
        this.INVQTY = INVQTY;
    }

    public String getFWD_DATE() {
        return FWD_DATE;
    }

    public void setFWD_DATE(String FWD_DATE) {
        this.FWD_DATE = FWD_DATE;
    }
    
    
       
    
}
