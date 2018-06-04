/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.FundReq.Beans;

/**
 *
 * @author Sanjeev
 */
public class FundReqDelvBean {

    private String dlrqpo;
    private String delqty;
    private String delvdt;
    private String pcddt;

    public FundReqDelvBean(String dlrqpo, String delqty, String delvdt, String pcddt) {
        this.dlrqpo = dlrqpo;
        this.delqty = delqty;
        this.delvdt = delvdt;
        this.pcddt = pcddt;
    }




    




    public String getDelqty() {
        return delqty;
    }

    public void setDelqty(String delqty) {
        this.delqty = delqty;
    }

    public String getDelvdt() {
        return delvdt;
    }

    public void setDelvdt(String delvdt) {
        this.delvdt = delvdt;
    }

    public String getDlrqpo() {
        return dlrqpo;
    }

    public void setDlrqpo(String dlrqpo) {
        this.dlrqpo = dlrqpo;
    }

    public String getPcddt() {
        return pcddt;
    }

    public void setPcddt(String pcddt) {
        this.pcddt = pcddt;
    }



}
