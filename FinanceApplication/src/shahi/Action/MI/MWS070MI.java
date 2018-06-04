package shahi.Action.MI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import shahi.Action.MI.Beans.MMS070MILstByDeliveryBean;

public class MWS070MI extends BaseMI {

    public MWS070MI() {
        setProgram("MWS070MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public List<MMS070MILstByDeliveryBean> getLstByDelivery(String RIDI, String TTYP) {
        List<MMS070MILstByDeliveryBean> mws070miLstTransByOrderBeans = new ArrayList<MMS070MILstByDeliveryBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstTransByItem";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("RIDI", RIDI);    //RIDI
        javaMI.mvxSetField("TTYP", TTYP);    //Transaction Type	


        recFlag = javaMI.mvxAccess("LstByDelivery");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
            //System.out.println(ERROR);
        } else {
            while (javaMI.mvxMore()) {
                MMS070MILstByDeliveryBean mws070LstTransByItem = new MMS070MILstByDeliveryBean();
                mws070LstTransByItem.setCONO(javaMI.mvxGetField("CONO"));   //Company
                mws070LstTransByItem.setWHLO(javaMI.mvxGetField("WHLO"));   //Warehouse
                mws070LstTransByItem.setITNO(javaMI.mvxGetField("ITNO"));   //Item number
                mws070LstTransByItem.setRGDT(javaMI.mvxGetField("RGDT"));   //Entry date
                mws070LstTransByItem.setRGTM(javaMI.mvxGetField("RGTM"));   //Entry time
                mws070LstTransByItem.setTMSX(javaMI.mvxGetField("TMSX"));   //Time suffix
                mws070LstTransByItem.setTRDT(javaMI.mvxGetField("TRDT"));   //Transaction date
                mws070LstTransByItem.setTRTM(javaMI.mvxGetField("TRTM"));   //Transaction time
                mws070LstTransByItem.setRESP(javaMI.mvxGetField("RESP"));   //Responsible
                mws070LstTransByItem.setTTID(javaMI.mvxGetField("TTID"));   //Transaction identity
                mws070LstTransByItem.setTRTP(javaMI.mvxGetField("TRTP"));   //Order type
                mws070LstTransByItem.setTTYP(javaMI.mvxGetField("TTYP"));   //Stock transaction type
                mws070LstTransByItem.setSLTP(javaMI.mvxGetField("SLTP"));   //Stock zone
                mws070LstTransByItem.setWHSL(javaMI.mvxGetField("WHSL"));   //Location
                mws070LstTransByItem.setBANO(javaMI.mvxGetField("BANO"));   //Lot number
                mws070LstTransByItem.setCAMU(javaMI.mvxGetField("CAMU"));   //Container
                mws070LstTransByItem.setRIDN(javaMI.mvxGetField("RIDN"));   //Order number
                mws070LstTransByItem.setRIDL(javaMI.mvxGetField("RIDL"));   //Order line
                mws070LstTransByItem.setRIDX(javaMI.mvxGetField("RIDX"));   //Line suffix
                mws070LstTransByItem.setRIDI(javaMI.mvxGetField("RIDI"));   //Delivery number
                mws070LstTransByItem.setPLSX(javaMI.mvxGetField("PLSX"));   //Picking list suffix
                mws070LstTransByItem.setRFTX(javaMI.mvxGetField("RFTX"));   //Reference text
                mws070LstTransByItem.setTRQT(javaMI.mvxGetField("TRQT"));   //Transaction quantity - basic U/M
                mws070miLstTransByOrderBeans.add(mws070LstTransByItem);

                javaMI.mvxAccess(null);

            }
        }
        return mws070miLstTransByOrderBeans;
    }

    public HashMap<String, Integer> getLstByDeliveryWithQty(String RIDI, String TTYP) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int recFlag;
        String ERROR = null;
        String identity = "LstTransByItem";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("RIDI", RIDI);    //RIDI
        javaMI.mvxSetField("TTYP", TTYP);    //Transaction Type	


        recFlag = javaMI.mvxAccess("LstByDelivery");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
            //System.out.println(ERROR);
        } else {
            while (javaMI.mvxMore()) {
                String str = javaMI.mvxGetField("RIDI") + ":" + javaMI.mvxGetField("RIDN") + ":" + javaMI.mvxGetField("RIDL");
                try {
                    map.put(str, map.get(str) + (-Integer.parseInt(javaMI.mvxGetField("TRQT"))));

                } catch (Exception ee) {
                    map.put(str, (-Integer.parseInt(javaMI.mvxGetField("TRQT"))));
                }

                javaMI.mvxAccess(null);

            }
        }
        return map;
    }
}
