/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.SMAMI;

import shahi.Action.MI.BaseSMAMI;



/**
 *
 * @author Jitendra Kothari
 */
public class MMS015MI extends BaseSMAMI {

    public MMS015MI() {
        setProgram("MMS015MI");
    }


    public String getCovfactorList(String ITNO) {
        
        int recFlag;
        String curr="1";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "777");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("List");
        if (recFlag > 0) {
       } else {
            while (javaMI.mvxMore()) {
                if(javaMI.mvxGetField("AUTP")!=null && javaMI.mvxGetField("AUTP").trim().equals("1")){
                curr = javaMI.mvxGetField("COFA");
                }
               javaMI.mvxAccess(null);
            }
        }
        return curr;
    }

}
