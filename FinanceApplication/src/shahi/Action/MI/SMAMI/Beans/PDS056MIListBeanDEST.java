/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.SMAMI.Beans;

/**
 *
 * @author Ranjeet
 */
public class PDS056MIListBeanDEST {
    int id;
    String OPTN;
    String OPTX;
    String SQNU;

    public PDS056MIListBeanDEST(int id, String OPTN, String OPTX, String SQNU) {
        this.id = id;
        this.OPTN = OPTN;
        this.OPTX = OPTX;
        this.SQNU = SQNU;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
