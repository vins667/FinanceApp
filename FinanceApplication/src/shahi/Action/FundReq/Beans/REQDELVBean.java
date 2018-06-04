/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.FundReq.Beans;

/**
 *
 * @author Vivek
 */
public class REQDELVBean {
    private String DLRQNO;     
    private String DLRQPO;
    private String DELQTY;
    private String DELVDT;
    private String PCDDT;  

    public REQDELVBean(String DLRQNO, String DLRQPO, String DELQTY, String DELVDT, String PCDDT) {
        this.DLRQNO = DLRQNO;
        this.DLRQPO = DLRQPO;
        this.DELQTY = DELQTY;
        this.DELVDT = DELVDT;
        this.PCDDT = PCDDT;
    }
    
    

    public String getDLRQNO() {
        return DLRQNO;
    }

    public void setDLRQNO(String DLRQNO) {
        this.DLRQNO = DLRQNO;
    }

    public String getDLRQPO() {
        return DLRQPO;
    }

    public void setDLRQPO(String DLRQPO) {
        this.DLRQPO = DLRQPO;
    }

    public String getDELQTY() {
        return DELQTY;
    }

    public void setDELQTY(String DELQTY) {
        this.DELQTY = DELQTY;
    }

    public String getDELVDT() {
        return DELVDT;
    }

    public void setDELVDT(String DELVDT) {
        this.DELVDT = DELVDT;
    }

    public String getPCDDT() {
        return PCDDT;
    }

    public void setPCDDT(String PCDDT) {
        this.PCDDT = PCDDT;
    }
    
}
