/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MIM4;


/**
 *
 * @author Ranjeet Gautam
 */
public class APZ100MI extends BaseMI {
 public APZ100MI() {
        setProgram("APZ100MI");
    }

  public String UpdRecord(String DIVI,String SUNO,String SINO,String YEAR,String REPORT)
  {
      String identity = "UpdRecord";
        int recFlag;
        String result = "";
        String st = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("SINO", SINO);
        javaMI.mvxSetField("YEA4", YEAR);
        javaMI.mvxSetField("ACFG", "0");
        javaMI.mvxSetField("BTNO", REPORT);
        recFlag = javaMI.mvxAccess("UpdRecord");

        if (recFlag > 0) {
              result = javaMI.mvxGetLastError();
              st="No";
              System.out.println(identity +"::"+result);
        } 
        
        return st;
    }
}
