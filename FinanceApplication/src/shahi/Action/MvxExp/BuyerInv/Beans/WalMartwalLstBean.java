/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class WalMartwalLstBean {
    private String ITMNO;
    private String BOXNO;
    private String PCSBOXNO;
    private String NETTOT;
    private String GROSTOT;
    private String PCST;
    private String RATIO;
    
    public WalMartwalLstBean(){
        
    }
    
    public WalMartwalLstBean(String ITMNO) {
        this.ITMNO = ITMNO;
    }

    public WalMartwalLstBean(String ITMNO, String BOXNO, String PCSBOXNO, String NETTOT, String GROSTOT, String PCST, String RATIO) {
        this.ITMNO = ITMNO;
        this.BOXNO = BOXNO;
        this.PCSBOXNO = PCSBOXNO;
        this.NETTOT = NETTOT;
        this.GROSTOT = GROSTOT;
        this.PCST = PCST;
        this.RATIO = RATIO;
    }

    public String getITMNO() {
        return ITMNO;
    }

    public void setITMNO(String ITMNO) {
        this.ITMNO = ITMNO;
    }

    public String getBOXNO() {
        return BOXNO;
    }

    public void setBOXNO(String BOXNO) {
        this.BOXNO = BOXNO;
    }

    public String getPCSBOXNO() {
        return PCSBOXNO;
    }

    public void setPCSBOXNO(String PCSBOXNO) {
        this.PCSBOXNO = PCSBOXNO;
    }

    public String getNETTOT() {
        return NETTOT;
    }

    public void setNETTOT(String NETTOT) {
        this.NETTOT = NETTOT;
    }

    public String getGROSTOT() {
        return GROSTOT;
    }

    public void setGROSTOT(String GROSTOT) {
        this.GROSTOT = GROSTOT;
    }

    public String getPCST() {
        return PCST;
    }

    public void setPCST(String PCST) {
        this.PCST = PCST;
    }

    public String getRATIO() {
        return RATIO;
    }

    public void setRATIO(String RATIO) {
        this.RATIO = RATIO;
    }

  
    
    
}
