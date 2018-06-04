/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MvxExp.Beans;




public class CntryMasterBean {
    private String CONTENT_CODE ;
    private String CONTENT_DESC ;
    private String STATUS ;
    private String CONTENT_GRPCODE;

    public CntryMasterBean(String CONTENT_CODE, String CONTENT_DESC, String STATUS,String CONTENT_GRPCODE) {
        this.CONTENT_CODE = CONTENT_CODE;
        this.CONTENT_DESC = CONTENT_DESC;
        this.STATUS = STATUS;
        this.CONTENT_GRPCODE=CONTENT_GRPCODE;
    }


    public String getCONTENT_CODE() {
        return CONTENT_CODE;
    }

    public void setCONTENT_CODE(String CONTENT_CODE) {
        this.CONTENT_CODE = CONTENT_CODE;
    }

    public String getCONTENT_DESC() {
        return CONTENT_DESC;
    }

    public void setCONTENT_DESC(String CONTENT_DESC) {
        this.CONTENT_DESC = CONTENT_DESC;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCONTENT_GRPCODE() {
        return CONTENT_GRPCODE;
    }

    public void setCONTENT_GRPCODE(String CONTENT_GRPCODE) {
        this.CONTENT_GRPCODE = CONTENT_GRPCODE;
    }


}
