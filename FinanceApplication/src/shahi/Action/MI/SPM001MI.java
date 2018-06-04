package shahi.Action.MI;

public class SPM001MI  extends BaseMI{
	 public SPM001MI() {
	        setProgram("SPM001MI");
	 }
	 public String UpdHead(String PUNO, String SUST,String POLK) {
	        String status;
	        int recFlag;
	        String identity = "UpdHead";
	        javaMI.mvxClearFields();
	        javaMI.mvxSetField("CONO", "111");
	        javaMI.mvxSetField("PUNO", PUNO);
	        javaMI.mvxSetField("POLK", POLK);
	        recFlag = javaMI.mvxAccess("UpdHead");
	        if (recFlag > 0) {
	            status = javaMI.mvxGetLastError();
	            System.out.println(status);
	            status="NOK-P";
	        } else {
	            status = "OK-P";
	        }
	        return status;
	    }
}
