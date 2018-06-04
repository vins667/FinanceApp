/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;
import java.util.List;

/**
 *
 * @author Ranjeet Singh
 */
public class PomastBean {
    private String PUNO;
    private List mastbean;

    public PomastBean() {
    }

    
    public PomastBean(String PUNO, List mastbean) {
        this.PUNO = PUNO;
        this.mastbean = mastbean;
    }

    
    
    
    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public List getMastbean() {
        return mastbean;
    }

    public void setMastbean(List mastbean) {
        this.mastbean = mastbean;
    }
    
    
}
