/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;
import java.math.BigDecimal;
/**
 *
 * @author Sanjeev
 */
public class MeisSBBean {
    
    private String SB_NO;
    private String SB_DATE;
    private String PORT_CODE;
    private String LOCT_CODE;
    private BigDecimal FOB_FC;
    private BigDecimal FOB_INR;
    private BigDecimal RELS_AMT;

    public MeisSBBean(String SB_NO, String SB_DATE, String PORT_CODE, String LOCT_CODE, BigDecimal FOB_FC, BigDecimal FOB_INR, BigDecimal RELS_AMT) {
        this.SB_NO = SB_NO;
        this.SB_DATE = SB_DATE;
        this.PORT_CODE = PORT_CODE;
        this.LOCT_CODE = LOCT_CODE;
        this.FOB_FC = FOB_FC;
        this.FOB_INR = FOB_INR;
        this.RELS_AMT = RELS_AMT;
    }

    
    
    
    
    public String getSB_NO() {
        return SB_NO;
    }

    public void setSB_NO(String SB_NO) {
        this.SB_NO = SB_NO;
    }

    public String getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(String SB_DATE) {
        this.SB_DATE = SB_DATE;
    }

    public String getPORT_CODE() {
        return PORT_CODE;
    }

    public void setPORT_CODE(String PORT_CODE) {
        this.PORT_CODE = PORT_CODE;
    }

    public String getLOCT_CODE() {
        return LOCT_CODE;
    }

    public void setLOCT_CODE(String LOCT_CODE) {
        this.LOCT_CODE = LOCT_CODE;
    }

    public BigDecimal getFOB_FC() {
        return FOB_FC;
    }

    public void setFOB_FC(BigDecimal FOB_FC) {
        this.FOB_FC = FOB_FC;
    }

    public BigDecimal getFOB_INR() {
        return FOB_INR;
    }

    public void setFOB_INR(BigDecimal FOB_INR) {
        this.FOB_INR = FOB_INR;
    }

    public BigDecimal getRELS_AMT() {
        return RELS_AMT;
    }

    public void setRELS_AMT(BigDecimal RELS_AMT) {
        this.RELS_AMT = RELS_AMT;
    }
    
    
    
    
    
}
