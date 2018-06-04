/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.compcal.upload;

/**
 *
 * @author Ranjeet
 */
public class FileNewBean {
    
    private String ID;
    private String COMPLIANCE_ID;
    private String FILE_NAME;
    private Long FILE_SIZE;
    private String DisfileName;
    
    public FileNewBean(String ID, String COMPLIANCE_ID, String FILE_NAME,  String DisfileName) {
        this.ID = ID;
        this.COMPLIANCE_ID = COMPLIANCE_ID;
        this.FILE_NAME = FILE_NAME;
        this.FILE_SIZE = FILE_SIZE;
        this.DisfileName = DisfileName;
    }

    public FileNewBean(String ID, String COMPLIANCE_ID, String FILE_NAME, Long FILE_SIZE, String DisfileName) {
        this.ID = ID;
        this.COMPLIANCE_ID = COMPLIANCE_ID;
        this.FILE_NAME = FILE_NAME;
        this.FILE_SIZE = FILE_SIZE;
        this.DisfileName = DisfileName;
    }

    
    
    public String getCOMPLIANCE_ID() {
        return COMPLIANCE_ID;
    }

    public void setCOMPLIANCE_ID(String COMPLIANCE_ID) {
        this.COMPLIANCE_ID = COMPLIANCE_ID;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public Long getFILE_SIZE() {
        return FILE_SIZE;
    }

    public void setFILE_SIZE(Long FILE_SIZE) {
        this.FILE_SIZE = FILE_SIZE;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDisfileName() {
        return DisfileName;
    }

    public void setDisfileName(String DisfileName) {
        this.DisfileName = DisfileName;
    }
    
    
    
}
