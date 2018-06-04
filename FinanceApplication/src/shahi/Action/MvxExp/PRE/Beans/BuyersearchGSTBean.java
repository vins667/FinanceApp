/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class BuyersearchGSTBean {
    
    private String BUYERCODE;
    private String BUYERNAME;
    private String BUYER_ADD_NO;
    private String BUYER_ADDRESS;
    private String STATECODE;
    
    public  BuyersearchGSTBean(){
        
    }

    public BuyersearchGSTBean(String BUYERCODE, String BUYERNAME, String BUYER_ADD_NO, String BUYER_ADDRESS, String STATECODE) {
        this.BUYERCODE = BUYERCODE;
        this.BUYERNAME = BUYERNAME;
        this.BUYER_ADD_NO = BUYER_ADD_NO;
        this.BUYER_ADDRESS = BUYER_ADDRESS;
        this.STATECODE = STATECODE;
    }

    public String getBUYERCODE() {
        return BUYERCODE;
    }

    public void setBUYERCODE(String BUYERCODE) {
        this.BUYERCODE = BUYERCODE;
    }

    public String getBUYERNAME() {
        return BUYERNAME;
    }

    public void setBUYERNAME(String BUYERNAME) {
        this.BUYERNAME = BUYERNAME;
    }

    public String getBUYER_ADD_NO() {
        return BUYER_ADD_NO;
    }

    public void setBUYER_ADD_NO(String BUYER_ADD_NO) {
        this.BUYER_ADD_NO = BUYER_ADD_NO;
    }

    public String getBUYER_ADDRESS() {
        return BUYER_ADDRESS;
    }

    public void setBUYER_ADDRESS(String BUYER_ADDRESS) {
        this.BUYER_ADDRESS = BUYER_ADDRESS;
    }

    public String getSTATECODE() {
        return STATECODE;
    }

    public void setSTATECODE(String STATECODE) {
        this.STATECODE = STATECODE;
    }

   
     
    
    
}
