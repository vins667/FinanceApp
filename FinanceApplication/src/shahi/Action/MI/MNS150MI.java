package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;

import shahi.Action.Beans.MNS150MIGetUserDataBean;
import shahi.Action.LstDelivery.beans.GetItmBasicBean;

public class MNS150MI extends BaseMI {

    public MNS150MI() {
        setProgram("MNS150MI");
    }

    public MNS150MIGetUserDataBean GetUserData(String USID) {
        int recFlag;
        String identity = "GetUserData";
        MNS150MIGetUserDataBean mns150miGetUserDataBean = new MNS150MIGetUserDataBean();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("USID", USID);
        recFlag = javaMI.mvxAccess("GetUserData");
        if (recFlag > 0) {
            //getItmBasicList.add(javaMI.mvxGetLastError());
        } else {

            mns150miGetUserDataBean.setCONO(javaMI.mvxGetField("CONO"));
            mns150miGetUserDataBean.setDIVI(javaMI.mvxGetField("DIVI"));
            mns150miGetUserDataBean.setLANC(javaMI.mvxGetField("LANC"));
            mns150miGetUserDataBean.setDTFM(javaMI.mvxGetField("DTFM"));
            mns150miGetUserDataBean.setDCFM(javaMI.mvxGetField("DCFM"));
            mns150miGetUserDataBean.setTIZO(javaMI.mvxGetField("TIZO"));
            mns150miGetUserDataBean.setFACI(javaMI.mvxGetField("FACI"));
            mns150miGetUserDataBean.setWHLO(javaMI.mvxGetField("WHLO"));
            mns150miGetUserDataBean.setCUNO(javaMI.mvxGetField("CUNO"));
            mns150miGetUserDataBean.setDEPT(javaMI.mvxGetField("DEPT"));
            mns150miGetUserDataBean.setTX40(javaMI.mvxGetField("TX40"));
            mns150miGetUserDataBean.setCONM(javaMI.mvxGetField("CONM"));
            mns150miGetUserDataBean.setMNVR(javaMI.mvxGetField("MNVR"));
            mns150miGetUserDataBean.setDFMN(javaMI.mvxGetField("DFMN"));
            mns150miGetUserDataBean.setUSID(javaMI.mvxGetField("USID"));
            mns150miGetUserDataBean.setNAME(javaMI.mvxGetField("NAME"));

            javaMI.mvxAccess(null);

        }
        return mns150miGetUserDataBean;
    }
}
