package shahi.Action.M4;

public class M4WhoMaster {

	private String M4WHLO;
	private String M4GEOC;
	private String WHVSER;
	
	public M4WhoMaster(){
		
	}
	public String getM4WHLO() {
		return M4WHLO;
	}
	public void setM4WHLO(String m4whlo) {
		M4WHLO = m4whlo;
	}
	public String getM4GEOC() {
		return M4GEOC;
	}
	public void setM4GEOC(String m4geoc) {
		M4GEOC = m4geoc;
	}
	public String getWHVSER() {
		return WHVSER;
	}
	public void setWHVSER(String wHVSER) {
		WHVSER = wHVSER;
	}
	@Override
	public String toString() {
		return "M4WhoMaster [M4WHLO=" + M4WHLO + ", M4GEOC=" + M4GEOC + ", WHVSER=" + WHVSER + "]";
	}
	
}
