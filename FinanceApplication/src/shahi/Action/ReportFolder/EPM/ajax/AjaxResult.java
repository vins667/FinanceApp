package shahi.Action.ReportFolder.EPM.ajax;

public class AjaxResult {

	private boolean voucherExist;

	private AjaxResult(){
		
	}
	
	public AjaxResult(boolean isVoucherExist) {
		super();
		this.voucherExist = isVoucherExist;
	}

	public boolean isVoucherExist() {
		return voucherExist;
	}

	public void setVoucherExist(boolean voucherExist) {
		this.voucherExist = voucherExist;
	}

	
}
