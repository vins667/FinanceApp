/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.MI.Beans.PPS170MILstProposalBean;

/**
 *
 * @author Shilpa
 */
public class PPS170MI extends BaseMI {

    public PPS170MI() {
        setProgram("PPS170MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public List<PPS170MILstProposalBean> LstProposal(String WHLO, String ITNO) {
        List<PPS170MILstProposalBean> pps170miLstProposalBeans = new ArrayList<PPS170MILstProposalBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstProposal";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("LstProposal");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                PPS170MILstProposalBean pps170miLstProposalBean = new PPS170MILstProposalBean();

                pps170miLstProposalBean.setFACI(javaMI.mvxGetField("FACI"));
                pps170miLstProposalBean.setWHLO(javaMI.mvxGetField("WHLO"));
                pps170miLstProposalBean.setITNO(javaMI.mvxGetField("ITNO"));
                pps170miLstProposalBean.setPRCS(javaMI.mvxGetField("PRCS"));
                pps170miLstProposalBean.setSUFI(javaMI.mvxGetField("SUFI"));
                pps170miLstProposalBean.setPLPN(javaMI.mvxGetField("PLPN"));
                pps170miLstProposalBean.setGETY(javaMI.mvxGetField("GETY"));
                pps170miLstProposalBean.setACTP(javaMI.mvxGetField("ACTP"));
                pps170miLstProposalBean.setPRIF(javaMI.mvxGetField("PRIF"));
                pps170miLstProposalBean.setRELD(javaMI.mvxGetField("RELD"));
                pps170miLstProposalBean.setDLDT(javaMI.mvxGetField("DLDT"));
                pps170miLstProposalBean.setPPQT(javaMI.mvxGetField("PPQT"));
                pps170miLstProposalBean.setRESP(javaMI.mvxGetField("RESP"));
                pps170miLstProposalBean.setBUYE(javaMI.mvxGetField("BUYE"));
                pps170miLstProposalBean.setPSTS(javaMI.mvxGetField("PSTS"));
                pps170miLstProposalBean.setPUSL(javaMI.mvxGetField("PUSL"));
                pps170miLstProposalBean.setMSG1(javaMI.mvxGetField("MSG1"));
                pps170miLstProposalBean.setMSG2(javaMI.mvxGetField("MSG2"));
                pps170miLstProposalBean.setMSG3(javaMI.mvxGetField("MSG3"));
                pps170miLstProposalBean.setMSG4(javaMI.mvxGetField("MSG4"));
                pps170miLstProposalBean.setPURC(javaMI.mvxGetField("PURC"));
                pps170miLstProposalBean.setRFID(javaMI.mvxGetField("RFID"));
                pps170miLstProposalBean.setYRE1(javaMI.mvxGetField("YRE1"));
                pps170miLstProposalBean.setAURE(javaMI.mvxGetField("AURE"));
                pps170miLstProposalBean.setORTY(javaMI.mvxGetField("ORTY"));
                pps170miLstProposalBean.setPOTC(javaMI.mvxGetField("POTC"));
                pps170miLstProposalBean.setRORN(javaMI.mvxGetField("RORN"));
                pps170miLstProposalBean.setRORL(javaMI.mvxGetField("RORL"));
                pps170miLstProposalBean.setRORC(javaMI.mvxGetField("RORC"));
                pps170miLstProposalBean.setOSHV(javaMI.mvxGetField("OSHV"));
                pps170miLstProposalBean.setOFID(javaMI.mvxGetField("OFID"));
                pps170miLstProposalBean.setTEDL(javaMI.mvxGetField("TEDL"));
                pps170miLstProposalBean.setMODL(javaMI.mvxGetField("MODL"));
                pps170miLstProposalBean.setTEPY(javaMI.mvxGetField("TEPY"));
                pps170miLstProposalBean.setPRIP(javaMI.mvxGetField("PRIP"));
                pps170miLstProposalBean.setSUNO(javaMI.mvxGetField("SUNO"));
                pps170miLstProposalBean.setSUAG(javaMI.mvxGetField("SUAG"));
                pps170miLstProposalBean.setOURR(javaMI.mvxGetField("OURR"));
                pps170miLstProposalBean.setOURT(javaMI.mvxGetField("OURT"));
                pps170miLstProposalBean.setCUCD(javaMI.mvxGetField("CUCD"));
                pps170miLstProposalBean.setSITE(javaMI.mvxGetField("SITE"));
                pps170miLstProposalBean.setPITD(javaMI.mvxGetField("PITD"));
                pps170miLstProposalBean.setPITT(javaMI.mvxGetField("PITT"));
                pps170miLstProposalBean.setPUPR(javaMI.mvxGetField("PUPR"));
                pps170miLstProposalBean.setPPUN(javaMI.mvxGetField("PPUN"));
                pps170miLstProposalBean.setPUUN(javaMI.mvxGetField("PUUN"));
                pps170miLstProposalBean.setPUCD(javaMI.mvxGetField("PUCD"));
                pps170miLstProposalBean.setPTCD(javaMI.mvxGetField("PTCD"));
                pps170miLstProposalBean.setODI1(javaMI.mvxGetField("ODI1"));
                pps170miLstProposalBean.setODI2(javaMI.mvxGetField("ODI2"));
                pps170miLstProposalBean.setODI3(javaMI.mvxGetField("ODI3"));
                pps170miLstProposalBean.setPPSQ(javaMI.mvxGetField("PPSQ"));
                pps170miLstProposalBean.setPROD(javaMI.mvxGetField("PROD"));
                pps170miLstProposalBean.setIRCV(javaMI.mvxGetField("IRCV"));
                pps170miLstProposalBean.setGRMT(javaMI.mvxGetField("GRMT"));
                pps170miLstProposalBean.setUPAV(javaMI.mvxGetField("UPAV"));
                pps170miLstProposalBean.setSTRT(javaMI.mvxGetField("STRT"));
                pps170miLstProposalBean.setACRF(javaMI.mvxGetField("ACRF"));
                pps170miLstProposalBean.setCOCE(javaMI.mvxGetField("COCE"));
                pps170miLstProposalBean.setBANO(javaMI.mvxGetField("BANO"));
                pps170miLstProposalBean.setPROJ(javaMI.mvxGetField("PROJ"));
                pps170miLstProposalBean.setELNO(javaMI.mvxGetField("ELNO"));
                pps170miLstProposalBean.setHDPR(javaMI.mvxGetField("HDPR"));
                pps170miLstProposalBean.setSTYN(javaMI.mvxGetField("STYN"));
                pps170miLstProposalBean.setOPTZ(javaMI.mvxGetField("OPTZ"));
                pps170miLstProposalBean.setTZ15(javaMI.mvxGetField("TZ15"));
                pps170miLstProposalBean.setOPTX(javaMI.mvxGetField("OPTX"));
                pps170miLstProposalBean.setTX15(javaMI.mvxGetField("TX15"));
                pps170miLstProposalBean.setOPTY(javaMI.mvxGetField("OPTY"));
                pps170miLstProposalBean.setTY15(javaMI.mvxGetField("TY15"));
                pps170miLstProposalBean.setCFI1(javaMI.mvxGetField("CFI1"));
                pps170miLstProposalBean.setCFI2(javaMI.mvxGetField("CFI2"));
                pps170miLstProposalBean.setCFI3(javaMI.mvxGetField("CFI3"));
                pps170miLstProposalBean.setCFI4(javaMI.mvxGetField("CFI4"));
                pps170miLstProposalBean.setCFI5(javaMI.mvxGetField("CFI5"));
                pps170miLstProposalBean.setCFMB(javaMI.mvxGetField("CFMB"));
                pps170miLstProposalBean.setCFPB(javaMI.mvxGetField("CFPB"));
                pps170miLstProposalBean.setPLPS(javaMI.mvxGetField("PLPS"));

                pps170miLstProposalBeans.add(pps170miLstProposalBean);
                javaMI.mvxAccess(null);
            }
        }
        return pps170miLstProposalBeans;
    }
}
