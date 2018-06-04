/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Ranjeet
 */
public class BillOfSalesBean {
private String BOS_NO ;        
private String YEAR     ;      
private String COMPANY   ;     
private String INV_NO     ;    
private String PKGS        ;   
private String QNTY         ;  
private String FY_USER   ;
private String FY_PKGS ;
private String FY_QNTY;
private String FY_TDATE      ;
private String AVG_RATE      ; 
private String CRNCY          ;
private String INR_CONV       ;
private String INV_DESC       ;
private String EXCS_INV_NO    ;
private String BOS_LOCATION   ;
private String DISPATCH_YN    ;
private String CFT_PLAN       ;
private String CFT_ACTUAL     ;
private String UOM            ;
private String CBM            ;
private String VOL            ;
private String GRWT           ;
private String PRINT_DATE      ;
private String FOB;
private String INR;
private String i_port;
private String i_cha;
private String i_agent;
private String i_buyer;
private String i_address;
private String i_first_sale;
private String i_doc_send;
private String i_mos;


    public BillOfSalesBean(String YEAR, String COMPANY, String INV_NO, String QNTY, String AVG_RATE, String CRNCY, String INR_CONV, String INV_DESC, String UOM,String FOB,String INR,String EXCS_INV_NO,
            String	i_port,String	i_cha,String	i_agent,String	i_buyer,String	i_address,String	i_first_sale,String i_doc_send,String i_mos,String FY_USER,String FY_PKGS,String FY_QNTY,String FY_TDATE ) 
    {
        this.YEAR = YEAR;
        this.COMPANY = COMPANY;
        this.INV_NO = INV_NO;
        this.QNTY = QNTY;
        this.AVG_RATE = AVG_RATE;
        this.CRNCY = CRNCY;
        this.INR_CONV = INR_CONV;
        this.INV_DESC = INV_DESC;
        this.UOM = UOM;
        this.FOB=FOB;
        this.INR=INR;
        this.EXCS_INV_NO=EXCS_INV_NO;
                this.i_port=i_port;
                this.i_cha=i_cha;
                this.i_agent=i_agent;
                this.i_buyer=i_buyer;
                this.i_address=i_address;
                this.i_first_sale=i_first_sale;
                this.i_doc_send=i_doc_send;
                this.i_mos=i_mos;
                this.FY_USER=FY_USER;
                this.FY_PKGS=FY_PKGS;
                this.FY_QNTY=FY_QNTY;
                this.FY_TDATE=FY_TDATE;
    }
 
     
 public BillOfSalesBean(String YEAR, String COMPANY, String INV_NO, String QNTY, String AVG_RATE, 
         String CRNCY, String INR_CONV, String INV_DESC, String UOM,String FOB,String INR,
         String DISPATCH_YN,String CFT_PLAN,String CFT_ACTUAL,String GRWT,String PRINT_DATE,
         String PKGS,String CBM,String VOL,String EXCS_INV_NO, String	i_port,String	i_cha,String	i_agent,String	i_buyer,String	i_address,String	i_first_sale,String i_doc_send, 
         String i_mos,String FY_USER,String FY_PKGS,String FY_QNTY,String FY_TDATE) {
        this.YEAR = YEAR;
        this.COMPANY = COMPANY;
        this.INV_NO = INV_NO;
        this.QNTY = QNTY;
        this.AVG_RATE = AVG_RATE;
        this.CRNCY = CRNCY;
        this.INR_CONV = INR_CONV;
        this.INV_DESC = INV_DESC;
        this.UOM = UOM;
        this.FOB=FOB;
        this.INR=INR;
        this.DISPATCH_YN=DISPATCH_YN;
        this.CFT_PLAN=CFT_PLAN;
        this.CFT_ACTUAL=CFT_ACTUAL;
        this.GRWT=GRWT;
        this.PRINT_DATE=PRINT_DATE;
        this.PKGS=PKGS;
        this.CBM=CBM;
        this.VOL=VOL;
        this.EXCS_INV_NO=EXCS_INV_NO;
        this.i_port=i_port;
                this.i_cha=i_cha;
                this.i_agent=i_agent;
                this.i_buyer=i_buyer;
                this.i_address=i_address;
                this.i_first_sale=i_first_sale;
                this.i_doc_send=i_doc_send;
                this.i_mos=i_mos;
                this.FY_USER=FY_USER;
                this.FY_PKGS=FY_PKGS;
                this.FY_QNTY=FY_QNTY;
                this.FY_TDATE=FY_TDATE;
    }
    


 


    public String getAVG_RATE() {
        return AVG_RATE;
    }

    public void setAVG_RATE(String AVG_RATE) {
        this.AVG_RATE = AVG_RATE;
    }

    public String getBOS_LOCATION() {
        return BOS_LOCATION;
    }

    public void setBOS_LOCATION(String BOS_LOCATION) {
        this.BOS_LOCATION = BOS_LOCATION;
    }

    public String getBOS_NO() {
        return BOS_NO;
    }

    public void setBOS_NO(String BOS_NO) {
        this.BOS_NO = BOS_NO;
    }

    public String getCBM() {
        return CBM;
    }

    public void setCBM(String CBM) {
        this.CBM = CBM;
    }

    public String getCFT_ACTUAL() {
        return CFT_ACTUAL;
    }

    public void setCFT_ACTUAL(String CFT_ACTUAL) {
        this.CFT_ACTUAL = CFT_ACTUAL;
    }

    public String getCFT_PLAN() {
        return CFT_PLAN;
    }

    public void setCFT_PLAN(String CFT_PLAN) {
        this.CFT_PLAN = CFT_PLAN;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getCRNCY() {
        return CRNCY;
    }

    public void setCRNCY(String CRNCY) {
        this.CRNCY = CRNCY;
    }

    public String getDISPATCH_YN() {
        return DISPATCH_YN;
    }

    public void setDISPATCH_YN(String DISPATCH_YN) {
        this.DISPATCH_YN = DISPATCH_YN;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getGRWT() {
        return GRWT;
    }

    public void setGRWT(String GRWT) {
        this.GRWT = GRWT;
    }

    public String getINR_CONV() {
        return INR_CONV;
    }

    public void setINR_CONV(String INR_CONV) {
        this.INR_CONV = INR_CONV;
    }

    public String getINV_DESC() {
        return INV_DESC;
    }

    public void setINV_DESC(String INV_DESC) {
        this.INV_DESC = INV_DESC;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getPKGS() {
        return PKGS;
    }

    public void setPKGS(String PKGS) {
        this.PKGS = PKGS;
    }

    public String getPRINT_DATE() {
        return PRINT_DATE;
    }

    public void setPRINT_DATE(String PRINT_DATE) {
        this.PRINT_DATE = PRINT_DATE;
    }

    public String getQNTY() {
        return QNTY;
    }

    public void setQNTY(String QNTY) {
        this.QNTY = QNTY;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getVOL() {
        return VOL;
    }

    public void setVOL(String VOL) {
        this.VOL = VOL;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getFOB() {
        return FOB;
    }

    public void setFOB(String FOB) {
        this.FOB = FOB;
    }

    public String getI_doc_send() {
        return i_doc_send;
    }

    public void setI_doc_send(String i_doc_send) {
        this.i_doc_send = i_doc_send;
    }

   

    public String getI_address() {
        return i_address;
    }

    public void setI_address(String i_address) {
        this.i_address = i_address;
    }

    public String getI_agent() {
        return i_agent;
    }

    public void setI_agent(String i_agent) {
        this.i_agent = i_agent;
    }

    public String getI_buyer() {
        return i_buyer;
    }

    public void setI_buyer(String i_buyer) {
        this.i_buyer = i_buyer;
    }

    public String getI_cha() {
        return i_cha;
    }

    public void setI_cha(String i_cha) {
        this.i_cha = i_cha;
    }

    public String getI_first_sale() {
        return i_first_sale;
    }

    public void setI_first_sale(String i_first_sale) {
        this.i_first_sale = i_first_sale;
    }

    public String getI_port() {
        return i_port;
    }

    public void setI_port(String i_port) {
        this.i_port = i_port;
    }

    public String getINR() {
        return INR;
    }

    public void setINR(String INR) {
        this.INR = INR;
    }

    public String getI_mos() {
        return i_mos;
    }

    public void setI_mos(String i_mos) {
        this.i_mos = i_mos;
    }

    public String getFY_PKGS() {
        return FY_PKGS;
    }

    public void setFY_PKGS(String FY_PKGS) {
        this.FY_PKGS = FY_PKGS;
    }

    public String getFY_QNTY() {
        return FY_QNTY;
    }

    public void setFY_QNTY(String FY_QNTY) {
        this.FY_QNTY = FY_QNTY;
    }

    public String getFY_TDATE() {
        return FY_TDATE;
    }

    public void setFY_TDATE(String FY_TDATE) {
        this.FY_TDATE = FY_TDATE;
    }

    public String getFY_USER() {
        return FY_USER;
    }

    public void setFY_USER(String FY_USER) {
        this.FY_USER = FY_USER;
    }
  
   

}
