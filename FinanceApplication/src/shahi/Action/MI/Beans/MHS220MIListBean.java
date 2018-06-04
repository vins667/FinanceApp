/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

/**
 *
 * @author Jitendra Kothari
 */

public class MHS220MIListBean {

    int id;
    String OPTY;
    String OPTX;
    String OPTZ;
    String ITNO;
    String flag;
    String isStstus90 = "false";
    String TX15;
    String TY15;

    public MHS220MIListBean() {
        this.id = 0;
    }

    public MHS220MIListBean(String OPTZ, String OPTX, String OPTY, String flag) {
        this.OPTZ = OPTZ;
        this.OPTY = OPTY;
        this.OPTX = OPTX;
        this.flag = flag;
    }
    
     public MHS220MIListBean(String OPTZ, String OPTX, String OPTY, String flag,String TX15,String TY15) {
        this.OPTZ = OPTZ;
        this.OPTY = OPTY;
        this.OPTX = OPTX;
        this.flag = flag;
        this.TX15 = TX15;
        this.TY15 = TY15;
        
    }

    
    
    
    @Override
    public String toString() {
        return "Item Number->" + this.ITNO + " Size->" + this.OPTX + " Color->" + this.OPTY + " Dest->" + this.OPTZ + " Checked->" + this.flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOPTX() {
        return OPTX;
    }

    public void setOPTX(String OPTX) {
        this.OPTX = OPTX;
    }

    public String getITNO() {
        return ITNO;
    }

    public void setITNO(String ITNO) {
        this.ITNO = ITNO;
    }

    public String getOPTY() {
        return OPTY;
    }

    public void setOPTY(String OPTY) {
        this.OPTY = OPTY;
    }

    public String getOPTZ() {
        return OPTZ;
    }

    public void setOPTZ(String OPTZ) {
        this.OPTZ = OPTZ;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTX15() {
        return TX15;
    }

    public void setTX15(String TX15) {
        this.TX15 = TX15;
    }

    public String getTY15() {
        return TY15;
    }

    public void setTY15(String TY15) {
        this.TY15 = TY15;
    }
    
    
    
    

    public String getIsStstus90() {
        return isStstus90;
    }
    
    
    

    public void setIsStstus90(boolean isStstus90) {
        if (isStstus90) {
            this.isStstus90 = "true";
        } else {
            this.isStstus90 = "false";
        }
    }
}
