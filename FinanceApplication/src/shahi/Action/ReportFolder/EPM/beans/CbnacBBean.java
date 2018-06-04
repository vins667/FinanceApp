/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.ReportFolder.EPM.beans;



/**
 *
 * @author Vivek
 */
public class CbnacBBean {
    private String CODE;
    private String DESC;
    private String BKID;
    private String longDescription;
    
    public CbnacBBean (){
    	
    }
    public CbnacBBean(String CODE, String DESC) {
        this.CODE = CODE;
        this.DESC = DESC;

    }
    public CbnacBBean(String CODE, String DESC, String BKID) {
        this.CODE = CODE;
        this.DESC = DESC;
        this.BKID = BKID;
    }
    
    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }
   
    public String getBKID() {
        return BKID;
    }

    public void setBKID(String BKID) {
        this.BKID = BKID;
    }
	
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

}
