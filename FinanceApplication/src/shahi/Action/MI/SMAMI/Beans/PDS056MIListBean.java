/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.SMAMI.Beans;

import java.io.Serializable;

/**
 *
 * @author Utkarsh.
 */
public class PDS056MIListBean implements Serializable {

    int id;
    String OPTN;
    String OPTX;
    String isDisabled = "false";
    String SQNU;
    double qty;
    String FLAG;
    String FLAG1;
    String FLAG2;

    public PDS056MIListBean() {
    }

    
    
    public PDS056MIListBean(int id, String OPTN, String OPTX, String SQNU) {
        this.id = id;
        this.OPTN = OPTN;
        this.OPTX = OPTX;
        this.SQNU=SQNU;
    }

    public PDS056MIListBean(String OPTX, double qty) {
        this.OPTX = OPTX;
        this.qty = qty;
    }

     public PDS056MIListBean(String OPTX, String FLAG) {
        this.OPTX = OPTX;
        this.FLAG = FLAG;
    }

    public PDS056MIListBean(String OPTX, String FLAG, String FLAG1, String FLAG2) {
        this.OPTX = OPTX;
        this.FLAG = FLAG;
        this.FLAG1 = FLAG1;
        this.FLAG2 = FLAG2;
    }
    
     
     
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOPTN() {
        return OPTN;
    }

    public void setOPTN(String OPTN) {
        this.OPTN = OPTN;
    }

    public String getOPTX() {
        return OPTX;
    }

    public void setOPTX(String OPTX) {
        this.OPTX = OPTX;
    }

    public String getSQNU() {
        return SQNU;
    }

    public void setSQNU(String SQNU) {
        this.SQNU = SQNU;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }




    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        if (isDisabled) {
            this.isDisabled = "true";
        } else {
            this.isDisabled = "false";
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.OPTX != null ? this.OPTX.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PDS056MIListBean other = (PDS056MIListBean) obj;
        if ((this.OPTX == null) ? (other.OPTX != null) : !this.OPTX.equals(other.OPTX)) {
            return false;
        }
        return true;
    }

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }

    public String getFLAG1() {
        return FLAG1;
    }

    public void setFLAG1(String FLAG1) {
        this.FLAG1 = FLAG1;
    }

    public String getFLAG2() {
        return FLAG2;
    }

    public void setFLAG2(String FLAG2) {
        this.FLAG2 = FLAG2;
    }

    
    
}
