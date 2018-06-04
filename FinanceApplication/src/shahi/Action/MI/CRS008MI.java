/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.MoClose.Beans.FacilityBean;


/**
 *
 * @author Ranjeet Gautam
 */
public class CRS008MI extends BaseMI {
 public CRS008MI() {
        setProgram("CRS008MI");
    }

  public List<FacilityBean> ListFacility() {
      String identity = "ListFacility";
        int recFlag;
        String result = ""; 
        List<FacilityBean> items = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        recFlag = javaMI.mvxAccess("ListFacility");

        if (recFlag > 0) {
              result = javaMI.mvxGetLastError();
              System.out.println(identity +"::"+result);
        } else {
            items = new ArrayList<FacilityBean>();
            while (javaMI.mvxMore()) {

                items.add(new FacilityBean(javaMI.mvxGetField("FACI"), javaMI.mvxGetField("FACN")));
                javaMI.mvxAccess(null);
            }
        }
        return items;
    }
}
