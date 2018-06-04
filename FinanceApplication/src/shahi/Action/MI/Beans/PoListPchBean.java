/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

import java.util.List;

/**
 *
 * @author VIVEK
 */
public class PoListPchBean {
    private String CONO;
    private String FACI;
    private String PUNO;
    private List PCH;

    public PoListPchBean() {
    }

    public PoListPchBean(String CONO,String FACI,String PUNO, List PCH) {
        this.CONO = CONO;
        this.FACI = FACI;
        this.PUNO = PUNO;
        this.PCH = PCH;
    }

    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public List getPCH() {
        return PCH;
    }

    public void setPCH(List PCH) {
        this.PCH = PCH;
    }

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getFACI() {
        return FACI;
    }

    public void setFACI(String FACI) {
        this.FACI = FACI;
    }
    
}
