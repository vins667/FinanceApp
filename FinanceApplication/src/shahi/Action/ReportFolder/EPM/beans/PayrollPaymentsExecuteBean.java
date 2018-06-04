/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM.beans;

/**
 *
 * @author Vivek
 */
public class PayrollPaymentsExecuteBean {
    private String DIVI;
    private String BATCH_NO;
    private String CHQ_NO;
    private String REMARKS;
    private String YEAR;
    private String VSER;
    private String VONO;
    private String EMP_CODE;
    private String ACC_DATA_DESC;    
    private String CHQ_DATE;
    private double CHQ_AMT;
    private String EGAIT1;

    public PayrollPaymentsExecuteBean(String DIVI, String BATCH_NO, String CHQ_NO, String REMARKS, String YEAR, String VSER, String VONO, String EMP_CODE, String ACC_DATA_DESC, String CHQ_DATE,double CHQ_AMT,String EGAIT1) {
        this.DIVI = DIVI;
        this.BATCH_NO = BATCH_NO;
        this.CHQ_NO = CHQ_NO;
        this.REMARKS = REMARKS;
        this.YEAR = YEAR;
        this.VSER = VSER;
        this.VONO = VONO;
        this.EMP_CODE = EMP_CODE;
        this.ACC_DATA_DESC = ACC_DATA_DESC;
        this.CHQ_DATE = CHQ_DATE;
        this.CHQ_AMT = CHQ_AMT;
        this.EGAIT1 = EGAIT1;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getBATCH_NO() {
        return BATCH_NO;
    }

    public void setBATCH_NO(String BATCH_NO) {
        this.BATCH_NO = BATCH_NO;
    }

    public String getCHQ_NO() {
        return CHQ_NO;
    }

    public void setCHQ_NO(String CHQ_NO) {
        this.CHQ_NO = CHQ_NO;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getVSER() {
        return VSER;
    }

    public void setVSER(String VSER) {
        this.VSER = VSER;
    }

    public String getVONO() {
        return VONO;
    }

    public void setVONO(String VONO) {
        this.VONO = VONO;
    }

    public String getEMP_CODE() {
        return EMP_CODE;
    }

    public void setEMP_CODE(String EMP_CODE) {
        this.EMP_CODE = EMP_CODE;
    }

    public String getACC_DATA_DESC() {
        return ACC_DATA_DESC;
    }

    public void setACC_DATA_DESC(String ACC_DATA_DESC) {
        this.ACC_DATA_DESC = ACC_DATA_DESC;
    }

    public String getCHQ_DATE() {
        return CHQ_DATE;
    }

    public void setCHQ_DATE(String CHQ_DATE) {
        this.CHQ_DATE = CHQ_DATE;
    }

    public double getCHQ_AMT() {
        return CHQ_AMT;
    }

    public void setCHQ_AMT(double CHQ_AMT) {
        this.CHQ_AMT = CHQ_AMT;
    }

    public String getEGAIT1() {
        return EGAIT1;
    }

    public void setEGAIT1(String EGAIT1) {
        this.EGAIT1 = EGAIT1;
    }
    
}
