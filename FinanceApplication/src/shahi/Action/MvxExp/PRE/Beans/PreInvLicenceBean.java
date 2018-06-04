/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class PreInvLicenceBean {
    
   private String REF_TYPE;
   private String REF_NO;
   private String BE_NO;
   private String LIC_DATE;
   private String LIC_COMP;
   private String LIC_LOCT;
   private double QTY;
   private double ADJS_QTY;
   private double FOB_AMT;
   private double QTY_SQM;
   private double IO_NORMS;
   private String EXP_REF_CTRL_NO;
   private String IMP_REF_CTRL_NO;
   private String BE_DESC;
   private String LITEM_DESC;
   private double LIC_AMT_OB;
   private double LIC_AMT_ISSUE;
   private double LIC_QTY_OB;
   private double LIC_QTY_ISSUE;
   

    public PreInvLicenceBean(String REF_TYPE, String REF_NO, String BE_NO, String LIC_DATE, String LIC_COMP, String LIC_LOCT, double QTY, double ADJS_QTY, double FOB_AMT, double QTY_SQM, double IO_NORMS, String EXP_REF_CTRL_NO, String IMP_REF_CTRL_NO, String BE_DESC, String LITEM_DESC,double LIC_AMT_OB,double LIC_AMT_ISSUE,double LIC_QTY_OB,double LIC_QTY_ISSUE) {
        this.REF_TYPE = REF_TYPE;
        this.REF_NO = REF_NO;
        this.BE_NO = BE_NO;
        this.LIC_DATE = LIC_DATE;
        this.LIC_COMP = LIC_COMP;
        this.LIC_LOCT = LIC_LOCT;
        this.QTY = QTY;
        this.ADJS_QTY = ADJS_QTY;
        this.FOB_AMT = FOB_AMT;
        this.QTY_SQM = QTY_SQM;
        this.IO_NORMS = IO_NORMS;
        this.EXP_REF_CTRL_NO = EXP_REF_CTRL_NO;
        this.IMP_REF_CTRL_NO = IMP_REF_CTRL_NO;
        this.BE_DESC = BE_DESC;
        this.LITEM_DESC= LITEM_DESC;
        this.LIC_AMT_OB=LIC_AMT_OB;
        this.LIC_AMT_ISSUE=LIC_AMT_ISSUE;
        this.LIC_QTY_OB=LIC_QTY_OB;
        this.LIC_QTY_ISSUE=LIC_QTY_ISSUE;
    }

    public String getREF_TYPE() {
        return REF_TYPE;
    }

    public void setREF_TYPE(String REF_TYPE) {
        this.REF_TYPE = REF_TYPE;
    }

    public String getREF_NO() {
        return REF_NO;
    }

    public void setREF_NO(String REF_NO) {
        this.REF_NO = REF_NO;
    }

    public String getBE_NO() {
        return BE_NO;
    }

    public void setBE_NO(String BE_NO) {
        this.BE_NO = BE_NO;
    }

    public String getLIC_DATE() {
        return LIC_DATE;
    }

    public void setLIC_DATE(String LIC_DATE) {
        this.LIC_DATE = LIC_DATE;
    }

    public String getLIC_COMP() {
        return LIC_COMP;
    }

    public void setLIC_COMP(String LIC_COMP) {
        this.LIC_COMP = LIC_COMP;
    }

    public String getLIC_LOCT() {
        return LIC_LOCT;
    }

    public void setLIC_LOCT(String LIC_LOCT) {
        this.LIC_LOCT = LIC_LOCT;
    }

    public double getQTY() {
        return QTY;
    }

    public void setQTY(double QTY) {
        this.QTY = QTY;
    }

    public double getADJS_QTY() {
        return ADJS_QTY;
    }

    public void setADJS_QTY(double ADJS_QTY) {
        this.ADJS_QTY = ADJS_QTY;
    }

    public double getFOB_AMT() {
        return FOB_AMT;
    }

    public void setFOB_AMT(double FOB_AMT) {
        this.FOB_AMT = FOB_AMT;
    }

    public double getQTY_SQM() {
        return QTY_SQM;
    }

    public void setQTY_SQM(double QTY_SQM) {
        this.QTY_SQM = QTY_SQM;
    }

    public double getIO_NORMS() {
        return IO_NORMS;
    } 

    public void setIO_NORMS(double IO_NORMS) {
        this.IO_NORMS = IO_NORMS;
    }

    public String getEXP_REF_CTRL_NO() {
        return EXP_REF_CTRL_NO;
    }

    public void setEXP_REF_CTRL_NO(String EXP_REF_CTRL_NO) {
        this.EXP_REF_CTRL_NO = EXP_REF_CTRL_NO;
    }

    public String getIMP_REF_CTRL_NO() {
        return IMP_REF_CTRL_NO;
    }

    public void setIMP_REF_CTRL_NO(String IMP_REF_CTRL_NO) {
        this.IMP_REF_CTRL_NO = IMP_REF_CTRL_NO;
    }

    public String getBE_DESC() {
        return BE_DESC;
    }

    public void setBE_DESC(String BE_DESC) {
        this.BE_DESC = BE_DESC;
    }

    public String getLITEM_DESC() {
        return LITEM_DESC;
    }

    public void setLITEM_DESC(String LITEM_DESC) {
        this.LITEM_DESC = LITEM_DESC;
    }

    public double getLIC_AMT_OB() {
        return LIC_AMT_OB;
    }

    public void setLIC_AMT_OB(double LIC_AMT_OB) {
        this.LIC_AMT_OB = LIC_AMT_OB;
    }

    public double getLIC_AMT_ISSUE() {
        return LIC_AMT_ISSUE;
    }

    public void setLIC_AMT_ISSUE(double LIC_AMT_ISSUE) {
        this.LIC_AMT_ISSUE = LIC_AMT_ISSUE;
    }

    public double getLIC_QTY_OB() {
        return LIC_QTY_OB;
    }

    public void setLIC_QTY_OB(double LIC_QTY_OB) {
        this.LIC_QTY_OB = LIC_QTY_OB;
    }

    public double getLIC_QTY_ISSUE() {
        return LIC_QTY_ISSUE;
    }

    public void setLIC_QTY_ISSUE(double LIC_QTY_ISSUE) {
        this.LIC_QTY_ISSUE = LIC_QTY_ISSUE;
    }

    
    
}
