/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;

/** 
 *
 * @author Sanjeev
 */
public class DBKRATEBEAN {
   private String EFF_DATE;
   private String END_DATE;
   private String DBK_SLNO;
   private String DBK_RATE;
   private String DBK_CAPVAL;
   private String DBK_UOM;
   private String DBK_SEGMENT;

    public DBKRATEBEAN(String EFF_DATE, String END_DATE, String DBK_SLNO,String DBK_RATE, String DBK_CAPVAL, String DBK_UOM, String DBK_SEGMENT) {
        this.EFF_DATE = EFF_DATE;
        this.END_DATE = END_DATE;
        this.DBK_SLNO = DBK_SLNO; 
        this.DBK_RATE = DBK_RATE;
        this.DBK_CAPVAL = DBK_CAPVAL;
        this.DBK_UOM = DBK_UOM;
        this.DBK_SEGMENT = DBK_SEGMENT;
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

    public String getDBK_SLNO() {
        return DBK_SLNO;
    }

    public void setDBK_SLNO(String DBK_SLNO) {
        this.DBK_SLNO = DBK_SLNO;
    }

    public String getDBK_RATE() {
        return DBK_RATE;
    }

    public void setDBK_RATE(String DBK_RATE) {
        this.DBK_RATE = DBK_RATE;
    }

    

   

    public String getDBK_CAPVAL() {
        return DBK_CAPVAL;
    }

    public void setDBK_CAPVAL(String DBK_CAPVAL) {
        this.DBK_CAPVAL = DBK_CAPVAL;
    }

    public String getDBK_UOM() {
        return DBK_UOM;
    }

    public void setDBK_UOM(String DBK_UOM) {
        this.DBK_UOM = DBK_UOM;
    }

    public String getDBK_SEGMENT() {
        return DBK_SEGMENT;
    }

    public void setDBK_SEGMENT(String DBK_SEGMENT) {
        this.DBK_SEGMENT = DBK_SEGMENT;
    }
   
   
    
}
