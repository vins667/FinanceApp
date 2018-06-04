/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/** 
 *
 * @author Sanjeev
 */
public class PostChargeBean {
    
    private String EXCS_INV_NO;
    private String CO_NO;
    private String DEL_NO;
    private String PORT;
    private String TYPE;
    private String SB_NO;
    private Double DBK_AMT;
    private Double STR_AMT;
    private Double ROSL_AMT;
    private Double TAX_AMT;
    private String TAX_PER;

    public PostChargeBean(String EXCS_INV_NO, String CO_NO, String DEL_NO, String PORT, String TYPE, String SB_NO, Double DBK_AMT, Double STR_AMT, Double ROSL_AMT, Double TAX_AMT, String TAX_PER) {
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.CO_NO = CO_NO;
        this.DEL_NO = DEL_NO;
        this.PORT = PORT;
        this.TYPE = TYPE;
        this.SB_NO = SB_NO;
        this.DBK_AMT = DBK_AMT;
        this.STR_AMT = STR_AMT;
        this.ROSL_AMT = ROSL_AMT;
        this.TAX_AMT = TAX_AMT;
        this.TAX_PER = TAX_PER;
    }

    
    
    
    
    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getCO_NO() {
        return CO_NO;
    }

    public void setCO_NO(String CO_NO) {
        this.CO_NO = CO_NO;
    }

    public String getDEL_NO() {
        return DEL_NO;
    }

    public void setDEL_NO(String DEL_NO) {
        this.DEL_NO = DEL_NO;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getSB_NO() {
        return SB_NO;
    }

    public void setSB_NO(String SB_NO) {
        this.SB_NO = SB_NO;
    }

    

    public Double getDBK_AMT() {
        return DBK_AMT;
    }

    public void setDBK_AMT(Double DBK_AMT) {
        this.DBK_AMT = DBK_AMT;
    }

    public Double getSTR_AMT() {
        return STR_AMT;
    }

    public void setSTR_AMT(Double STR_AMT) {
        this.STR_AMT = STR_AMT;
    }

    public Double getROSL_AMT() {
        return ROSL_AMT;
    }

    public void setROSL_AMT(Double ROSL_AMT) {
        this.ROSL_AMT = ROSL_AMT;
    }

    public Double getTAX_AMT() {
        return TAX_AMT;
    }

    public void setTAX_AMT(Double TAX_AMT) {
        this.TAX_AMT = TAX_AMT;
    }

    public String getTAX_PER() {
        return TAX_PER;
    }

    public void setTAX_PER(String TAX_PER) {
        this.TAX_PER = TAX_PER;
    }
    
    
     
            
    
}
