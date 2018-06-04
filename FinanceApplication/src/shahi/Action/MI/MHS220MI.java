/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import shahi.Action.Fabric.Beans.MaterialBean;
import shahi.Action.MI.Beans.MHS220MIListBean;

/**
 *
 * @author user
 */
public class MHS220MI extends BaseMI {

    public MHS220MI() {
        setProgram("MHS220MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public List<MHS220MIListBean> LstStySkuRelItm(String ITNO) {
        int recFlag;
        MMS200MI MI2 = new MMS200MI();
        List<MHS220MIListBean> list = new ArrayList<MHS220MIListBean>();

        String identity = "LstStySkuRelItm";
        javaMI.mvxClearFields();
        // javaMI.mvxSetField("CONO", HelperConstantsFnl.COMPANY);
        javaMI.mvxSetField("ISTY", ITNO);

        recFlag = javaMI.mvxAccess("LstStySkuRel");
        if (recFlag > 0) {
            javaMI.mvxGetLastError();
            return null;
        } else {
            MI2.connect();
            List<MaterialBean> Listtemp = MI2.LstvarByStyle(ITNO);
            MI2.destroyMI();
            MI2 = null;
            while (javaMI.mvxMore()) {

                MHS220MIListBean viewBean = new MHS220MIListBean();
                viewBean.setId(1);
                viewBean.setITNO(javaMI.mvxGetField("ITNO"));
                viewBean.setOPTX(javaMI.mvxGetField("OPTX"));
                viewBean.setOPTY(javaMI.mvxGetField("OPTY"));
                viewBean.setOPTZ(javaMI.mvxGetField("OPTZ"));
                viewBean.setTX15(javaMI.mvxGetField("TX15"));
                viewBean.setTY15(javaMI.mvxGetField("TY15"));

                viewBean.setFlag("true");
                boolean isStatus = false;
                if (Listtemp != null) {
                    Iterator itr = Listtemp.iterator();

                    while (itr.hasNext()) {
                        MaterialBean tempbean = (MaterialBean) itr.next();
                        if (tempbean.getITNO().equalsIgnoreCase(viewBean.getITNO())) {
                            if (tempbean.getSTAT().equals("90")) {
                                isStatus = true;
                            }
                            if (Integer.parseInt(tempbean.getSTAT()) > 39) {
                                isStatus = true;
                            }
                        }


                    }
                }
                viewBean.setIsStstus90(isStatus);

                list.add(viewBean);
                javaMI.mvxAccess(null);
            }
        }

        return list;
    }
}
