package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.MI.Beans.ZNS151MILstStkZoneBean;

public class ZNS151MI extends BaseMI {

    public ZNS151MI() {
        setProgram("ZNS151MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public List<ZNS151MILstStkZoneBean> getLstStkZone(String CONO, String USID) {
        List<ZNS151MILstStkZoneBean> zns151miLstStkZoneBeans = new ArrayList<ZNS151MILstStkZoneBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstStkZone";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);     //Company
        javaMI.mvxSetField("USID", USID);    //User
        recFlag = javaMI.mvxAccess("LstStkZone");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                ZNS151MILstStkZoneBean zns151miLstStkZoneBean = new ZNS151MILstStkZoneBean();
                zns151miLstStkZoneBean.setCONO(javaMI.mvxGetField("CONO"));   //Company
                zns151miLstStkZoneBean.setUSID(javaMI.mvxGetField("USID"));   //User
                zns151miLstStkZoneBean.setWHLO(javaMI.mvxGetField("WHLO"));   //Warehouse
                zns151miLstStkZoneBean.setSLTP(javaMI.mvxGetField("SLTP"));   //Stock Zone
                zns151miLstStkZoneBean.setSTAT(javaMI.mvxGetField("STAT"));   //Status

                zns151miLstStkZoneBeans.add(zns151miLstStkZoneBean);
                javaMI.mvxAccess(null);

            }
        }
        return zns151miLstStkZoneBeans;
    }
}
