/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Transport.Beans;

/**
 *
 * @author Sanjeev
 */
public class TrLaneBean {
    
     String CTRL_NO;
     String TR_CODE;
     String ORGIN_CODE;
     String DESTN_CODE;
     String TRK_SIZE;
     String AUCTION_YN;
     String TR_DESC;
     String ORIGIN_DESC;
     String EFF_DATE;
     String END_DATE;
     

    public TrLaneBean(String CTRL_NO, String TR_CODE, String ORGIN_CODE, String DESTN_CODE, String TRK_SIZE,String AUCTION_YN,String TR_DESC,String ORIGIN_DESC,String EFF_DATE,String END_DATE) {
        this.CTRL_NO = CTRL_NO;
        this.TR_CODE = TR_CODE;
        this.ORGIN_CODE = ORGIN_CODE;
        this.DESTN_CODE = DESTN_CODE;
        this.TRK_SIZE = TRK_SIZE;
        this.AUCTION_YN=AUCTION_YN;
        this.TR_DESC=TR_DESC;
        this.ORIGIN_DESC=ORIGIN_DESC;
        this.EFF_DATE=EFF_DATE;
        this.END_DATE=END_DATE;
    }

     
     
     
    public String getCTRL_NO() {
        return CTRL_NO;
    }

    public void setCTRL_NO(String CTRL_NO) {
        this.CTRL_NO = CTRL_NO;
    }

    public String getTR_CODE() {
        return TR_CODE;
    }

    public void setTR_CODE(String TR_CODE) {
        this.TR_CODE = TR_CODE;
    }

    public String getORGIN_CODE() {
        return ORGIN_CODE;
    }

    public void setORGIN_CODE(String ORGIN_CODE) {
        this.ORGIN_CODE = ORGIN_CODE;
    }

    public String getDESTN_CODE() {
        return DESTN_CODE;
    }

    public void setDESTN_CODE(String DESTN_CODE) {
        this.DESTN_CODE = DESTN_CODE;
    }

    public String getTRK_SIZE() {
        return TRK_SIZE;
    }

    public void setTRK_SIZE(String TRK_SIZE) {
        this.TRK_SIZE = TRK_SIZE;
    }

    public String getAUCTION_YN() {
        return AUCTION_YN;
    }

    public void setAUCTION_YN(String AUCTION_YN) {
        this.AUCTION_YN = AUCTION_YN;
    }

    public String getTR_DESC() {
        return TR_DESC;
    }

    public void setTR_DESC(String TR_DESC) {
        this.TR_DESC = TR_DESC;
    }

    public String getORIGIN_DESC() {
        return ORIGIN_DESC;
    }

    public void setORIGIN_DESC(String ORIGIN_DESC) {
        this.ORIGIN_DESC = ORIGIN_DESC;
    }

    public String getEFF_DATE() {
        return EFF_DATE;
    }

    public void setEFF_DATE(String EFF_DATE) {
        this.EFF_DATE = EFF_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }
     
     
     
}
