package shahi.Action.MvxExp.PRE.Beans;

public class CodeList {

	private String code;
	private String description;
	
	public CodeList(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CodeList [code=" + code + ", description=" + description + "]";
	}
	
}
