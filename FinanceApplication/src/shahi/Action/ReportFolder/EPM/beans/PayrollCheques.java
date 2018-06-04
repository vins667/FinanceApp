package shahi.Action.ReportFolder.EPM.beans;

import java.util.List;

import shahi.Action.ReportFolder.EPM.movex.bean.FCR040;
import shahi.Action.ReportFolder.EPM.movex.bean.FDLEDG;

public class PayrollCheques {

	private List<FCR040> chequeList;
	
	private List<FDLEDG>ledgerList;

	public List<FCR040> getChequeList() {
		return chequeList;
	}

	public void setChequeList(List<FCR040> chequeList) {
		this.chequeList = chequeList;
	}

	public List<FDLEDG> getLedgerList() {
		return ledgerList;
	}

	public void setLedgerList(List<FDLEDG> ledgerList) {
		this.ledgerList = ledgerList;
	}

	@Override
	public String toString() {
		return "PayrollCheques [chequeList=" + chequeList + ", ledgerList=" + ledgerList + "]";
	}
	
}
