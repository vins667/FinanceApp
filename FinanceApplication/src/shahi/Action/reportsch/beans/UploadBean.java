/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.reportsch.beans;

/**
 *
 * @author Vivek
 */
public class UploadBean {
    private String CODE;
    private String DESC;

    public UploadBean(String CODE, String DESC) {
        this.CODE = CODE;
        this.DESC = DESC;
    }

    
    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }
    
    
}