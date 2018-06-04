package shahi.Action.compcal.bean;

public class CompCalBean {
	private String ID;
	private String NAME;
        private String ACT_FLAG;
	
	public CompCalBean() {
		super();
	}
	public CompCalBean(String iD, String nAME) {
		super();
		ID = iD;
		NAME = nAME;
	}
        
        public CompCalBean(String iD, String nAME,String aCT_FLAG) {
		super();
		ID = iD;
		NAME = nAME;
                ACT_FLAG=aCT_FLAG;
	}
        
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}

    public String getACT_FLAG() {
        return ACT_FLAG;
    }

    public void setACT_FLAG(String ACT_FLAG) {
        this.ACT_FLAG = ACT_FLAG;
    }
        
        
	
}
