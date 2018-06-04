/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.FundReq.Beans;

/**
 *
 * @author Vivek
 */
public class ForwardListBean {
    private String EMP_CODE;
    private String EMP_NAME;
    private String EMP_MAIL;

    public ForwardListBean(String EMP_CODE, String EMP_NAME) {
        this.EMP_CODE = EMP_CODE;
        this.EMP_NAME = EMP_NAME;
    }

    public ForwardListBean(String EMP_CODE, String EMP_NAME, String EMP_MAIL) {
        this.EMP_CODE = EMP_CODE;
        this.EMP_NAME = EMP_NAME;
        this.EMP_MAIL = EMP_MAIL;
    }
    
    public String getEMP_CODE() {
        return EMP_CODE;
    }

    public void setEMP_CODE(String EMP_CODE) {
        this.EMP_CODE = EMP_CODE;
    }

    public String getEMP_NAME() {
        return EMP_NAME;
    }

    public void setEMP_NAME(String EMP_NAME) {
        this.EMP_NAME = EMP_NAME;
    }

    public String getEMP_MAIL() {
        return EMP_MAIL;
    }

    public void setEMP_MAIL(String EMP_MAIL) {
        this.EMP_MAIL = EMP_MAIL;
    }
    
    
}
