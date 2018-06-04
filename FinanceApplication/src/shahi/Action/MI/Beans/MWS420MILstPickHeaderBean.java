/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

/**
 *
 * @author User
 */
public class MWS420MILstPickHeaderBean {
  		
    String CONO; //Company	
    String DLIX; //Delivery number	
    String PLSX ;	//Picking list suffix	

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getDLIX() {
        return DLIX;
    }

    public void setDLIX(String DLIX) {
        this.DLIX = DLIX;
    }

    public String getPLSX() {
        return PLSX;
    }

    public void setPLSX(String PLSX) {
        this.PLSX = PLSX;
    }
 
    
}
