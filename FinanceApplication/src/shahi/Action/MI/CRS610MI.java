/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MI;

/**
 *
 * @author Ranjeet
 */
import java.util.ArrayList;
import java.util.List;
public class CRS610MI extends BaseMI{
public CRS610MI() {
        setProgram("CRS610MI");
    }

   public String[] GetBasicDataList(String CUNO) {
        int recFlag;
       String identity = "GetBasicData";
        String par[] = new String[5];

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("CUNO", CUNO);

        recFlag = javaMI.mvxAccess("GetBasicData");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            par[3] = javaMI.mvxGetLastError();
        } else {
            par[0] = javaMI.mvxGetField("CUNM");
            par[1] = javaMI.mvxGetField("CUA1");
            par[2] = javaMI.mvxGetField("CUA2");
            par[3] = javaMI.mvxGetField("CUA3");
            par[4] = javaMI.mvxGetField("CUA4");


        }
        return par;
    }
   
    public String[] GetAddressList(String CUNO,String ADID) {
        int recFlag;
       String identity = "GetAddress";
        String par[] = new String[5];

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("CUNO", CUNO);
        javaMI.mvxSetField("ADRT", "01");
        javaMI.mvxSetField("ADID", ADID);

        recFlag = javaMI.mvxAccess("GetAddress");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            par[3] = javaMI.mvxGetLastError();
        } else {
            par[0] = javaMI.mvxGetField("CUNM");
            par[1] = javaMI.mvxGetField("CUA1");
            par[2] = javaMI.mvxGetField("CUA2");
            par[3] = javaMI.mvxGetField("CUA3");
            par[4] = javaMI.mvxGetField("CUA4");


        }
        return par;
    }




}
