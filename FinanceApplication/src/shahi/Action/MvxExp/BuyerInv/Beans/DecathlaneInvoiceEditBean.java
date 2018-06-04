/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author User
 */
public class DecathlaneInvoiceEditBean {
    
    private String ITEMDESC;
    private String PCH;
    private String QNTY;
    private String UOM;
    private String RATE;
    private String FOB;
    private String TAXTYPE;
    private String TAXPER;
    private String TAXAMT;
    private String TAXGL;
    private String TCS;
    private String TCSGL;
    private String TCSPER;
    private String TCSAMT;
    private String INVNO;
    private String TAX_CODE;
    private String TAX_CODE2; 

    public DecathlaneInvoiceEditBean(String ITEMDESC, String PCH, String QNTY, String UOM, String RATE, String FOB, String TAXTYPE, String TAXPER, String TAXAMT, String TAXGL, String TCS, String TCSGL, String TCSPER, String TCSAMT, String INVNO,String TAX_CODE,String TAX_CODE2) {
        this.ITEMDESC = ITEMDESC;
        this.PCH = PCH;
        this.QNTY = QNTY;
        this.UOM = UOM;
        this.RATE = RATE;
        this.FOB = FOB;
        this.TAXTYPE = TAXTYPE;
        this.TAXPER = TAXPER;
        this.TAXAMT = TAXAMT;
        this.TAXGL = TAXGL;
        this.TCS = TCS;
        this.TCSGL = TCSGL;
        this.TCSPER = TCSPER;
        this.TCSAMT = TCSAMT;
        this.INVNO = INVNO;
        this.TAX_CODE=TAX_CODE;
        this.TAX_CODE2=TAX_CODE2;
    }

    public String getITEMDESC() {
        return ITEMDESC;
    }

    public void setITEMDESC(String ITEMDESC) {
        this.ITEMDESC = ITEMDESC;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
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

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getFOB() {
        return FOB;
    }

    public void setFOB(String FOB) {
        this.FOB = FOB;
    }

    public String getTAXTYPE() {
        return TAXTYPE;
    }

    public void setTAXTYPE(String TAXTYPE) {
        this.TAXTYPE = TAXTYPE;
    }

    public String getTAXPER() {
        return TAXPER;
    }

    public void setTAXPER(String TAXPER) {
        this.TAXPER = TAXPER;
    }

    public String getTAXAMT() {
        return TAXAMT;
    }

    public void setTAXAMT(String TAXAMT) {
        this.TAXAMT = TAXAMT;
    }

    public String getTAXGL() {
        return TAXGL;
    }

    public void setTAXGL(String TAXGL) {
        this.TAXGL = TAXGL;
    }

    public String getTCS() {
        return TCS;
    }

    public void setTCS(String TCS) {
        this.TCS = TCS;
    }

    public String getTCSGL() {
        return TCSGL;
    }

    public void setTCSGL(String TCSGL) {
        this.TCSGL = TCSGL;
    }

    public String getTCSPER() {
        return TCSPER;
    }

    public void setTCSPER(String TCSPER) {
        this.TCSPER = TCSPER;
    }

    public String getTCSAMT() {
        return TCSAMT;
    }

    public void setTCSAMT(String TCSAMT) {
        this.TCSAMT = TCSAMT;
    }

    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public String getTAX_CODE2() {
        return TAX_CODE2;
    }

    public void setTAX_CODE2(String TAX_CODE2) {
        this.TAX_CODE2 = TAX_CODE2;
    }

     
     
    
}
