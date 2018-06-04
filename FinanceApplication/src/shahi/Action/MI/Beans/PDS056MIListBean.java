/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

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

}
