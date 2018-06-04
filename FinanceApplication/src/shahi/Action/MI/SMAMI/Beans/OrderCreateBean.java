/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.SMAMI.Beans;

/**
 *
 * @author User.
 */
public class OrderCreateBean {

    private String ITNO = null;
    private String OPT = null;
    private String ORQT = null;
    private boolean isEditable = true;
    private boolean isStatus90 = false;
    private String LineStatus = "";
    String ALQT=null;
    private String dupFlag="No";
    
    private String NUM_IN_STOCK;
    private String UPC;
    
    String BHANECOLOR = "";
    String BHANESIZE = "";
    String NUMINSTOCK = "";
    String UNITPRICE = "";
    String WEIGHTTEMP = "";
    String DEFAULTFLAG="";
    String BHANEHEXCODE="";
    private String MAST_SL_NO;
    private String SL_NO;

    public OrderCreateBean(String ITNO, String OPT) {
        this.ITNO = ITNO;
        this.OPT = OPT;
        this.ORQT = "0";
        isEditable = true;
        isStatus90 = false;
    }

    public OrderCreateBean(String ITNO, String OPT, boolean isStatus90) {
        this.ITNO = ITNO;
        this.OPT = OPT;
        this.ORQT = "0";
        isEditable = true;
        this.isStatus90 = isStatus90;
    }
    
        public OrderCreateBean(String ITNO, String OPT, boolean isStatus90,String NUM_IN_STOCK,String UPC) {
        this.ITNO = ITNO;
        this.OPT = OPT;
        this.ORQT = "0";
        isEditable = true;
        this.isStatus90 = isStatus90;
        this.NUM_IN_STOCK=NUM_IN_STOCK;
        this.UPC=UPC;
    }
        
       public OrderCreateBean(String ITNO, String OPT, boolean isStatus90,String NUM_IN_STOCK,String UPC,String BHANECOLOR,
               String BHANESIZE,String NUMINSTOCK ,String UNITPRICE,String WEIGHTTEMP,String DEFAULTFLAG,
               String BHANEHEXCODE,String MAST_SL_NO,String SL_NO) {
        this.ITNO = ITNO;
        this.OPT = OPT;
        this.ORQT = "0";
        isEditable = true;
        this.isStatus90 = isStatus90;
        this.NUM_IN_STOCK=NUM_IN_STOCK;
        this.UPC=UPC;
        this.BHANECOLOR=BHANECOLOR;
        this.BHANESIZE=BHANESIZE;
        this.NUMINSTOCK=NUMINSTOCK;
        this.UNITPRICE=UNITPRICE;
        this.WEIGHTTEMP=WEIGHTTEMP;
        this.DEFAULTFLAG=DEFAULTFLAG;
        this.BHANEHEXCODE=BHANEHEXCODE;
        this.MAST_SL_NO=MAST_SL_NO;
        this.SL_NO=SL_NO;
    
    }  
     public OrderCreateBean(String ITNO, String OPT, boolean isStatus90,String NUM_IN_STOCK,String UPC,String BHANECOLOR,
               String BHANESIZE,String NUMINSTOCK ,String UNITPRICE,String WEIGHTTEMP,String DEFAULTFLAG,
               String BHANEHEXCODE) {
        this.ITNO = ITNO;
        this.OPT = OPT;
        this.ORQT = "0";
        isEditable = true;
        this.isStatus90 = isStatus90;
        this.NUM_IN_STOCK=NUM_IN_STOCK;
        this.UPC=UPC;
        this.BHANECOLOR=BHANECOLOR;
        this.BHANESIZE=BHANESIZE;
        this.NUMINSTOCK=NUMINSTOCK;
        this.UNITPRICE=UNITPRICE;
        this.WEIGHTTEMP=WEIGHTTEMP;
        this.DEFAULTFLAG=DEFAULTFLAG;
        this.BHANEHEXCODE=BHANEHEXCODE;
       
    
    }      

    public OrderCreateBean(String ITNO, String OPT, String ORQT) {
        this.ITNO = ITNO;
        this.OPT = OPT;
        this.ORQT = ORQT;
        isEditable = false;
        isStatus90 = false;
    }

    
    
    
    
    public OrderCreateBean(String ITNO, String OPT, String ORQT, boolean isStatus90, String LineStatus,String ALQT,String dupFlag) {
        this.ITNO = ITNO;
        this.OPT = OPT;
        this.ORQT = ORQT;
        isEditable = false;
//        this.isStatus90 = isStatus90;
        this.LineStatus = LineStatus;
        if(Integer.parseInt(LineStatus)> 39){
            this.isStatus90 = true;
        }else{
            this.isStatus90 = false;
        }
        this.ALQT = ALQT;
        this.dupFlag = dupFlag;
        

    }

    public String getORQT() {
        return ORQT;
    }

    public void setORQT(String ORQT) {
        this.ORQT = ORQT;
    }

    public String getITNO() {
        return ITNO;
    }

    public void setITNO(String ITNO) {
        this.ITNO = ITNO;
    }

    public String getOPT() {
        return OPT;
    }

    public boolean isIsStatus90() {
        return isStatus90;
    }

    public void setIsStatus90(boolean isStatus90) {
        this.isStatus90 = isStatus90;
    }

    public void setOPT(String OPT) {
        this.OPT = OPT;
    }

    public boolean isIsEditable() {
        return isEditable;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public String getALQT() {
        return ALQT;
    }

    public void setALQT(String ALQT) {
        this.ALQT = ALQT;
    }

    public String getLineStatus() {
        return LineStatus;
    }

    public void setLineStatus(String LineStatus) {
        this.LineStatus = LineStatus;
    }

    public String getDupFlag() {
        return dupFlag;
    }

    public void setDupFlag(String dupFlag) {
        this.dupFlag = dupFlag;
    }

    public String getNUM_IN_STOCK() {
        return NUM_IN_STOCK;
    }

    public void setNUM_IN_STOCK(String NUM_IN_STOCK) {
        this.NUM_IN_STOCK = NUM_IN_STOCK;
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

    public String getBHANECOLOR() {
        return BHANECOLOR;
    }

    public void setBHANECOLOR(String BHANECOLOR) {
        this.BHANECOLOR = BHANECOLOR;
    }

    public String getBHANESIZE() {
        return BHANESIZE;
    }

    public void setBHANESIZE(String BHANESIZE) {
        this.BHANESIZE = BHANESIZE;
    }

    public String getNUMINSTOCK() {
        return NUMINSTOCK;
    }

    public void setNUMINSTOCK(String NUMINSTOCK) {
        this.NUMINSTOCK = NUMINSTOCK;
    }

    public String getUNITPRICE() {
        return UNITPRICE;
    }

    public void setUNITPRICE(String UNITPRICE) {
        this.UNITPRICE = UNITPRICE;
    }

    public String getWEIGHTTEMP() {
        return WEIGHTTEMP;
    }

    public void setWEIGHTTEMP(String WEIGHTTEMP) {
        this.WEIGHTTEMP = WEIGHTTEMP;
    }

    public String getDEFAULTFLAG() {
        return DEFAULTFLAG;
    }

    public void setDEFAULTFLAG(String DEFAULTFLAG) {
        this.DEFAULTFLAG = DEFAULTFLAG;
    }

    public String getBHANEHEXCODE() {
        return BHANEHEXCODE;
    }

    public void setBHANEHEXCODE(String BHANEHEXCODE) {
        this.BHANEHEXCODE = BHANEHEXCODE;
    }

    public String getMAST_SL_NO() {
        return MAST_SL_NO;
    }

    public void setMAST_SL_NO(String MAST_SL_NO) {
        this.MAST_SL_NO = MAST_SL_NO;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }



}
