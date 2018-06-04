/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MI;

import shahi.Action.MI.Beans.GLS850Bean;
import java.util.List;
import java.util.ArrayList;

public class GLS850SMAMI extends BaseSMAMI {

     public GLS850SMAMI() {
        setProgram("GLS850MI");
    }

   public String DataUpload(String CONO, String DIVI, String INTN, String PCDN,String FLDR, String UPCD )
    {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("INTN", INTN);
        javaMI.mvxSetField("PCDN", PCDN);
        javaMI.mvxSetField("FLDR", FLDR);
        javaMI.mvxSetField("UPCD", UPCD);


        recFlag = javaMI.mvxAccess("DataUpload");
        if (recFlag > 0) {
            status="NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
        }else{
        status="Yes";
        }
      return status;

    } // END DataUpload


       public List LstHistory(String CONO,String DIVI, String INTN,String RGDT) {
        int recFlag;
        String identity = "LstHistory";
        List GLList = new ArrayList();

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("INTN", INTN);
        javaMI.mvxSetField("RGDT", RGDT);

        recFlag = javaMI.mvxAccess("LstHistory");

        if (recFlag > 0) { 
             GLList.add(new GLS850Bean (CONO,DIVI,INTN,RGDT,".",".","10","0","No Record Found",javaMI.mvxGetLastError() ));
        } else {
            while (javaMI.mvxMore()) {
               GLList.add(new GLS850Bean (CONO,DIVI,INTN,
                    javaMI.mvxGetField("RGDT"),
                    javaMI.mvxGetField("RGTM"),
                    javaMI.mvxGetField("RNNO"),
                    javaMI.mvxGetField("IFST"),
                    javaMI.mvxGetField("ERRS"),
                    javaMI.mvxGetField("PCDN"),
                    javaMI.mvxGetField("FLDR") ));
                   javaMI.mvxAccess(null);

            }
        }
        return GLList;
    }

}
