package shahi.Action.MI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import shahi.Action.LstDelivery.beans.LstDeliveryBean;
import shahi.Action.MI.Beans.MWS411MILstDelLnByOrdBean;
import shahi.Action.ShipmentSet.Benas.DeliveryBean;

/**
 * @author Ranjeet
 * @Category MWS411MI
 *
 */
public class MWS411MI extends BaseMI {

    public MWS411MI() {
        setProgram("MWS411MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public List LstDelivery(String DLIX) {
        int recFlag;
        DecimalFormat df = new DecimalFormat("#.##");
        String identity = "LstDelivery";
        List lstDeliveryList = new ArrayList();


        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DLIX", DLIX);

        recFlag = javaMI.mvxAccess("LstDelivery");

        if (recFlag > 0) {
            lstDeliveryList.add(javaMI.mvxGetLastError());
            System.out.println(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                String decValue = df.format(Double.parseDouble(javaMI.mvxGetField("TRQT")));
                lstDeliveryList.add(new LstDeliveryBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("DLIX"), javaMI.mvxGetField("RORC"), javaMI.mvxGetField("RIDN"), javaMI.mvxGetField("RIDL"), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("FACI"), javaMI.mvxGetField("PRNO"), javaMI.mvxGetField("POPN"), javaMI.mvxGetField("ALWT"), javaMI.mvxGetField("ALWQ"), decValue, javaMI.mvxGetField("PLQT"), javaMI.mvxGetField("PHLD"), javaMI.mvxGetField("JDCD"), javaMI.mvxGetField("STCD"), javaMI.mvxGetField("BLOP"), javaMI.mvxGetField("TRQA"), javaMI.mvxGetField("PLQA"), javaMI.mvxGetField("ALUN")));
                javaMI.mvxAccess(null);
            }
        }
        return lstDeliveryList;
    }

    public List<DeliveryBean> LstDeliveryShip(String DLIX) {
        int recFlag;
        DecimalFormat df = new DecimalFormat("#.##");
        String identity = "LstDelivery";
        List<DeliveryBean> lstDeliveryList = new ArrayList<DeliveryBean>();


        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DLIX", DLIX);

        recFlag = javaMI.mvxAccess("LstDelivery");

        if (recFlag > 0) {

            System.out.println(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                //String decValue = df.format(Double.parseDouble(javaMI.mvxGetField("TRQT")));

                lstDeliveryList.add(new DeliveryBean(javaMI.mvxGetField("DLIX"), javaMI.mvxGetField("RIDN"), javaMI.mvxGetField("RIDL"), javaMI.mvxGetField("ITNO"), Double.parseDouble(javaMI.mvxGetField("TRQT")), Double.parseDouble(javaMI.mvxGetField("PLQT"))));
                javaMI.mvxAccess(null);
            }
        }
        return lstDeliveryList;
    }

    public List LstDelLnByOrd(String RORC, String RIDN, String RIDL, String RIDX) {
        List ls = new ArrayList();
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("RORC", RORC);
        javaMI.mvxSetField("RIDN", RIDN);
        javaMI.mvxSetField("RIDL", RIDL);
        //javaMI.mvxSetField("RIDX", RIDX);
        recFlag = javaMI.mvxAccess("LstDelLnByOrd");
        if (recFlag > 0) {
        } else {

            while (javaMI.mvxMore()) {
                MWS411MILstDelLnByOrdBean mws411miLstDelLnByOrdBean = new MWS411MILstDelLnByOrdBean();
                mws411miLstDelLnByOrdBean.setDLIX(javaMI.mvxGetField("DLIX"));   //Delivery number
                mws411miLstDelLnByOrdBean.setRORC(javaMI.mvxGetField("RORC"));   //Reference order category
                mws411miLstDelLnByOrdBean.setRIDN(javaMI.mvxGetField("RIDN"));   //Order number
                mws411miLstDelLnByOrdBean.setRIDL(javaMI.mvxGetField("RIDL"));   //Order line
                mws411miLstDelLnByOrdBean.setRIDX(javaMI.mvxGetField("RIDX"));   //Line suffix
                mws411miLstDelLnByOrdBean.setITNO(javaMI.mvxGetField("ITNO"));   //Item number
                mws411miLstDelLnByOrdBean.setFACI(javaMI.mvxGetField("FACI"));   //Facility
                mws411miLstDelLnByOrdBean.setPRNO(javaMI.mvxGetField("PRNO"));   //Product
                mws411miLstDelLnByOrdBean.setPOPN(javaMI.mvxGetField("POPN"));   //Alias number
                mws411miLstDelLnByOrdBean.setALWT(javaMI.mvxGetField("ALWT"));   //Alias category
                mws411miLstDelLnByOrdBean.setALWQ(javaMI.mvxGetField("ALWQ"));   //Alias qualifier
                mws411miLstDelLnByOrdBean.setTRQT(javaMI.mvxGetField("TRQT"));   //Transaction quantity - basic U/M
                mws411miLstDelLnByOrdBean.setPLQT(javaMI.mvxGetField("PLQT"));   //Picking list quantity - basic U/M
                mws411miLstDelLnByOrdBean.setPHLD(javaMI.mvxGetField("PHLD"));   //Pick hold
                mws411miLstDelLnByOrdBean.setSTCD(javaMI.mvxGetField("STCD"));   //Inventory accounting
                mws411miLstDelLnByOrdBean.setJDCD(javaMI.mvxGetField("JDCD"));   //Joint delivery
                mws411miLstDelLnByOrdBean.setBLOP(javaMI.mvxGetField("BLOP"));   //Blocked delivery number
                mws411miLstDelLnByOrdBean.setTRQA(javaMI.mvxGetField("TRQA"));   //Transaction quantity in alt U/M
                mws411miLstDelLnByOrdBean.setPLQA(javaMI.mvxGetField("PLQA"));   //Picking list quantity - alternate U/M
                mws411miLstDelLnByOrdBean.setALUN(javaMI.mvxGetField("ALUN"));   //Alternate U/M
                mws411miLstDelLnByOrdBean.setGRWE(javaMI.mvxGetField("GRWE"));   //Gross weight
                mws411miLstDelLnByOrdBean.setNEWE(javaMI.mvxGetField("NEWE"));   //Net weight
                mws411miLstDelLnByOrdBean.setVOL3(javaMI.mvxGetField("VOL3"));   //Volume
                mws411miLstDelLnByOrdBean.setFCU1(javaMI.mvxGetField("FCU1"));   //Estimated free unit of assigned goods
                mws411miLstDelLnByOrdBean.setNOWA(javaMI.mvxGetField("NOWA"));   //Excluded from wave
                mws411miLstDelLnByOrdBean.setLSHL(javaMI.mvxGetField("LSHL"));   //Stop ship hold
                mws411miLstDelLnByOrdBean.setRGDT(javaMI.mvxGetField("RGDT"));   //Entry date
                mws411miLstDelLnByOrdBean.setRGTM(javaMI.mvxGetField("RGTM"));   //Entry time
                mws411miLstDelLnByOrdBean.setLMDT(javaMI.mvxGetField("LMDT"));   //Change date
                mws411miLstDelLnByOrdBean.setCHNO(javaMI.mvxGetField("CHNO"));   //Change number
                mws411miLstDelLnByOrdBean.setCHID(javaMI.mvxGetField("CHID"));   //Changed by
                mws411miLstDelLnByOrdBean.setLMTS(javaMI.mvxGetField("LMTS"));   //Timestamp
                mws411miLstDelLnByOrdBean.setSCNB(javaMI.mvxGetField("SCNB"));   //Supply chain number
                mws411miLstDelLnByOrdBean.setSCES(javaMI.mvxGetField("SCES"));   //Regrouping status
                ls.add(mws411miLstDelLnByOrdBean);
                javaMI.mvxAccess(null);
            }

        }

        return ls;
    }

    public List LstDeliveryLine(String DLIX, String CONO) {
        int recFlag;
        DecimalFormat df = new DecimalFormat("#.##");
        String identity = "LstDeliveryLine";
        List lstDeliveryList = new ArrayList();


        javaMI.mvxClearFields();

        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("RIDN", CONO);

        recFlag = javaMI.mvxAccess("LstDeliveryLine");

        if (recFlag > 0) {

            System.out.println(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                //String decValue = df.format(Double.parseDouble(javaMI.mvxGetField("TRQT")));

                lstDeliveryList.add(javaMI.mvxGetField("RIDL"));
                javaMI.mvxAccess(null);
            }
        }
        return lstDeliveryList;
    }
    public List LstDeliveryLine(String DLIX)
    {
      DecimalFormat df = new DecimalFormat("#.##");
      String identity = "LstDeliveryLine";
      List lstDeliveryList = new ArrayList();


      this.javaMI.mvxClearFields();

      this.javaMI.mvxSetField("DLIX", DLIX);
      this.javaMI.mvxSetField("RIDN", "");

      int recFlag = this.javaMI.mvxAccess("LstDeliveryLine");
      if (recFlag > 0)
      {
        lstDeliveryList.add(this.javaMI.mvxGetLastError());
        System.out.println(this.javaMI.mvxGetLastError());
      }
      else
      {
        while (this.javaMI.mvxMore())
        {
          String decValue = df.format(Double.parseDouble(this.javaMI.mvxGetField("TRQT")));
          lstDeliveryList.add(new LstDeliveryBean(this.javaMI.mvxGetField("CONO"), this.javaMI.mvxGetField("DLIX"), this.javaMI.mvxGetField("RORC"), this.javaMI.mvxGetField("RIDN"), this.javaMI.mvxGetField("RIDL"), this.javaMI.mvxGetField("ITNO"), this.javaMI.mvxGetField("FACI"), this.javaMI.mvxGetField("PRNO"), this.javaMI.mvxGetField("POPN"), this.javaMI.mvxGetField("ALWT"), this.javaMI.mvxGetField("ALWQ"), decValue, this.javaMI.mvxGetField("PLQT"), this.javaMI.mvxGetField("PHLD"), this.javaMI.mvxGetField("JDCD"), this.javaMI.mvxGetField("STCD"), this.javaMI.mvxGetField("BLOP"), this.javaMI.mvxGetField("TRQA"), this.javaMI.mvxGetField("PLQA"), this.javaMI.mvxGetField("ALUN"), this.javaMI.mvxGetField("ALQT"), this.javaMI.mvxGetField("PGRS"), ""));
          this.javaMI.mvxAccess(null);
        }
      }
      return lstDeliveryList;
    }
}
