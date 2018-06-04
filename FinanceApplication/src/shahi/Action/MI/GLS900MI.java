package shahi.Action.MI;

public class GLS900MI  extends BaseMI {
	
	public GLS900MI() {
        setProgram("GLS900MI");
    }
	
    String identity=null;
    
	public String VoucherReverse(String DIVI,String YEA4,String VSER,String VONO){
		identity = "VoucherReverse";
		int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("YEA4", YEA4);
        javaMI.mvxSetField("VSER", VSER);
        javaMI.mvxSetField("VONO", VONO);
        recFlag = javaMI.mvxAccess("VoucherReverse");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status="NOK";
        } else {
            status = "OK";
        }
        return status;
	}
}
