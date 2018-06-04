/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/**
 *
 * @author Sanjeev
 */
public class PostFinInvBean {
    
    private String INVOICENO;
    private String BUYER;
    private String BUYER_ADDR;
    private String CONS_ADDR;
    private String BUYER_GLCODE;
    private String CRNCY_CODE;
    private String CNTRY;
    private String LC_NO;
    private String LC_METHOD;
    private String XSTEPY;
    private String XSNODY;
   
    private Double FOB_AMT;
    private Double GR_DECL;
    private Double DISC_AMT;
    private Double TAX_PERCENT;
    private String DUEDESC;
    private String POSTYEAR;
    private String POSTLINK;

    public PostFinInvBean(String INVOICENO, String BUYER, String BUYER_ADDR, String CONS_ADDR, String BUYER_GLCODE, String CRNCY_CODE, String CNTRY, String LC_NO, String LC_METHOD, String XSTEPY, String XSNODY,  Double FOB_AMT, Double GR_DECL, Double DISC_AMT, Double TAX_PERCENT,String DUEDESC,String POSTYEAR,String POSTLINK) {
        this.INVOICENO = INVOICENO;
        this.BUYER = BUYER;
        this.BUYER_ADDR = BUYER_ADDR;
        this.CONS_ADDR = CONS_ADDR;
        this.BUYER_GLCODE = BUYER_GLCODE;
        this.CRNCY_CODE = CRNCY_CODE;
        this.CNTRY = CNTRY;
        this.LC_NO = LC_NO;
        this.LC_METHOD = LC_METHOD;
        this.XSTEPY = XSTEPY;
        this.XSNODY = XSNODY;
        this.FOB_AMT = FOB_AMT;
        this.GR_DECL = GR_DECL;
        this.DISC_AMT = DISC_AMT;
        this.TAX_PERCENT = TAX_PERCENT;
        this.DUEDESC=DUEDESC;
        this.POSTLINK=POSTLINK;
        this.POSTYEAR=POSTYEAR;
        
    }

    public String getINVOICENO() {
        return INVOICENO;
    }

    public void setINVOICENO(String INVOICENO) {
        this.INVOICENO = INVOICENO;
    }

    public String getDUEDESC() {
        return DUEDESC;
    }

    public void setDUEDESC(String DUEDESC) {
        this.DUEDESC = DUEDESC;
    }

    public String getPOSTYEAR() {
        return POSTYEAR;
    }

    public void setPOSTYEAR(String POSTYEAR) {
        this.POSTYEAR = POSTYEAR;
    }

    public String getPOSTLINK() {
        return POSTLINK;
    }

    public void setPOSTLINK(String POSTLINK) {
        this.POSTLINK = POSTLINK;
    }

    
    
    
    
    
    

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getBUYER_ADDR() {
        return BUYER_ADDR;
    }

    public void setBUYER_ADDR(String BUYER_ADDR) {
        this.BUYER_ADDR = BUYER_ADDR;
    }

    public String getCONS_ADDR() {
        return CONS_ADDR;
    }

    public void setCONS_ADDR(String CONS_ADDR) {
        this.CONS_ADDR = CONS_ADDR;
    }

    public String getBUYER_GLCODE() {
        return BUYER_GLCODE;
    }

    public void setBUYER_GLCODE(String BUYER_GLCODE) {
        this.BUYER_GLCODE = BUYER_GLCODE;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public String getCNTRY() {
        return CNTRY;
    }

    public void setCNTRY(String CNTRY) {
        this.CNTRY = CNTRY;
    }

    public String getLC_NO() {
        return LC_NO;
    }

    public void setLC_NO(String LC_NO) {
        this.LC_NO = LC_NO;
    }

    public String getLC_METHOD() {
        return LC_METHOD;
    }

    public void setLC_METHOD(String LC_METHOD) {
        this.LC_METHOD = LC_METHOD;
    }

    public String getXSTEPY() {
        return XSTEPY;
    }

    public void setXSTEPY(String XSTEPY) {
        this.XSTEPY = XSTEPY;
    }

    public String getXSNODY() {
        return XSNODY;
    }

    public void setXSNODY(String XSNODY) {
        this.XSNODY = XSNODY;
    }

    

    public Double getFOB_AMT() {
        return FOB_AMT;
    }

    public void setFOB_AMT(Double FOB_AMT) {
        this.FOB_AMT = FOB_AMT;
    }

    public Double getGR_DECL() {
        return GR_DECL;
    }

    public void setGR_DECL(Double GR_DECL) {
        this.GR_DECL = GR_DECL;
    }

    public Double getDISC_AMT() {
        return DISC_AMT;
    }

    public void setDISC_AMT(Double DISC_AMT) {
        this.DISC_AMT = DISC_AMT;
    }

    public Double getTAX_PERCENT() {
        return TAX_PERCENT;
    }

    public void setTAX_PERCENT(Double TAX_PERCENT) {
        this.TAX_PERCENT = TAX_PERCENT;
    }
   
    
    
    
}
