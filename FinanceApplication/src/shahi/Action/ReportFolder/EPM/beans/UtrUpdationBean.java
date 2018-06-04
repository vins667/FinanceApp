/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.ReportFolder.EPM.beans;

/**
 *
 * @author Vivek
 */
public class UtrUpdationBean {
    private String DIVISION;
    private String CHQ_NO;
    private String YEAR;
    private String VSER;
    private String VONO;
    private String UTR_FLAG;

    public UtrUpdationBean(String DIVISION, String CHQ_NO, String YEAR, String VSER, String VONO, String UTR_FLAG) {
        this.DIVISION = DIVISION;
        this.CHQ_NO = CHQ_NO;
        this.YEAR = YEAR;
        this.VSER = VSER;
        this.VONO = VONO;
        this.UTR_FLAG = UTR_FLAG;
    }
   
    public String getCHQ_NO() {
        return CHQ_NO;
    }

    public void setCHQ_NO(String CHQ_NO) {
        this.CHQ_NO = CHQ_NO;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }

    public String getUTR_FLAG() {
        return UTR_FLAG;
    }

    public void setUTR_FLAG(String UTR_FLAG) {
        this.UTR_FLAG = UTR_FLAG;
    }

    public String getVONO() {
        return VONO;
    }

    public void setVONO(String VONO) {
        this.VONO = VONO;
    }

    public String getVSER() {
        return VSER;
    }

    public void setVSER(String VSER) {
        this.VSER = VSER;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

}
