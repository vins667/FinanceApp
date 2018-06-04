package shahi.Action.MI;

/**
 * @author Ranjeet
 * @Category MWS410MI
 * @since 11/10/2011
 */
public class MWS410MI extends BaseMI {

    public MWS410MI() {
        setProgram("MWS410MI");
    }

    public String RelForPick(String CONO, String DLIX, String OLUP) {
        int recFlag;
        String identity = "RelForPick";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("OLUP", OLUP);
        recFlag = javaMI.mvxAccess("RelForPick");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            //status="NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }

    public String getWhloHead(String DLIX) {
        int recFlag;
        String identity = "GetHead";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DLIX", DLIX);
        recFlag = javaMI.mvxAccess("GetHead");
        String status = null;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = null;

        } else {
            while (javaMI.mvxMore()) {
                status = javaMI.mvxGetField("WHLO");
                javaMI.mvxAccess(null);
            }
        }
        return status;
    }

    public String RelForPick(String CONO, String DLIX) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);//            
        javaMI.mvxSetField("DLIX", DLIX);//		Delivery number
        javaMI.mvxSetField("OLUP", "1");//		Order line
        recFlag = javaMI.mvxAccess("RelForPick");

        if (recFlag > 0) {
            status = "NO";
            System.out.println(javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }
}
