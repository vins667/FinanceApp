/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.FundReq.Beans;

import java.util.List;

import shahi.Action.ReportFolder.EPM.beans.Code;

public class FundReqBean {

    private String REQNO;
    private String REQDT;
    private String REQSUNO;
    private String REQTYP;    
    private String REQTXT;
    private String DLVPLC;
    private String REQSTS;
    private String PAYLOCT;
    private String REQCHQ;
    private String RQLMDT;
    private String REQCHQDT;
    private String CHQDELV;
    private String VODATE;
    private List<Code>VOTYPES;
    private String REQDIVI;
    private String VOTYPE;
    private String REQVONO;
    
    public FundReqBean(){
    	
    }
    public FundReqBean(String REQNO, String REQDT, String REQSUNO, String REQTYP, String REQTXT, String DLVPLC, String REQSTS, String PAYLOCT) {
        this.REQNO = REQNO;
        this.REQDT = REQDT;
        this.REQSUNO = REQSUNO;
        this.REQTYP = REQTYP;
        this.REQTXT = REQTXT;
        this.DLVPLC = DLVPLC;
        this.REQSTS = REQSTS;
        this.PAYLOCT = PAYLOCT;
    }

    
    public String getREQNO() {
        return REQNO;
    }

    public void setREQNO(String REQNO) {
        this.REQNO = REQNO;
    }

    public String getREQDT() {
        return REQDT;
    }

    public void setREQDT(String REQDT) {
        this.REQDT = REQDT;
    }

    public String getREQSUNO() {
        return REQSUNO;
    }

    public void setREQSUNO(String REQSUNO) {
        this.REQSUNO = REQSUNO;
    }

    public String getREQTYP() {
        return REQTYP;
    }

    public void setREQTYP(String REQTYP) {
        this.REQTYP = REQTYP;
    }

    public String getREQTXT() {
        return REQTXT;
    }

    public void setREQTXT(String REQTXT) {
        this.REQTXT = REQTXT;
    }

    public String getDLVPLC() {
        return DLVPLC;
    }

    public void setDLVPLC(String DLVPLC) {
        this.DLVPLC = DLVPLC;
    }

    public String getREQSTS() {
        return REQSTS;
    }

    public void setREQSTS(String REQSTS) {
        this.REQSTS = REQSTS;
    }

    public String getPAYLOCT() {
        return PAYLOCT;
    }

    public void setPAYLOCT(String PAYLOCT) {
        this.PAYLOCT = PAYLOCT;
    }
	public String getREQCHQ() {
		return REQCHQ;
	}
	public void setREQCHQ(String rEQCHQ) {
		REQCHQ = rEQCHQ;
	}
	public String getRQLMDT() {
		return RQLMDT;
	}
	public void setRQLMDT(String rQLMDT) {
		RQLMDT = rQLMDT;
	}
	public String getREQCHQDT() {
		return REQCHQDT;
	}
	public void setREQCHQDT(String rEQCHQDT) {
		REQCHQDT = rEQCHQDT;
	}
	public String getCHQDELV() {
		return CHQDELV;
	}
	public void setCHQDELV(String cHQDELV) {
		CHQDELV = cHQDELV;
	}
	public String getVODATE() {
		return VODATE;
	}
	public void setVODATE(String vODATE) {
		VODATE = vODATE;
	}
	public List<Code> getVOTYPES() {
		return VOTYPES;
	}
	public void setVOTYPES(List<Code> vOTYPES) {
		VOTYPES = vOTYPES;
	}
	public String getREQDIVI() {
		return REQDIVI;
	}
	public void setREQDIVI(String rEQDIVI) {
		REQDIVI = rEQDIVI;
	}
	public String getVOTYPE() {
		return VOTYPE;
	}
	public void setVOTYPE(String vOTYPE) {
		VOTYPE = vOTYPE;
	}
	public String getREQVONO() {
		return REQVONO;
	}
	public void setREQVONO(String rEQVONO) {
		REQVONO = rEQVONO;
	}
	@Override
	public String toString() {
		return "FundReqBean [REQNO=" + REQNO + ", REQDT=" + REQDT + ", REQSUNO=" + REQSUNO + ", REQTYP=" + REQTYP
				+ ", REQTXT=" + REQTXT + ", DLVPLC=" + DLVPLC + ", REQSTS=" + REQSTS + ", PAYLOCT=" + PAYLOCT
				+ ", REQCHQ=" + REQCHQ + ", RQLMDT=" + RQLMDT + ", REQCHQDT=" + REQCHQDT + ", CHQDELV=" + CHQDELV
				+ ", VODATE=" + VODATE + ", VOTYPES=" + VOTYPES + ", REQDIVI=" + REQDIVI + ", VOTYPE=" + VOTYPE
				+ ", REQVONO=" + REQVONO + "]";
	}
	    
}
