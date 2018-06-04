package shahi.Action.restresponse;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

 public class MIRecord {

	 @JsonProperty("RowIndex")
	private String rowIndex;
	 @JsonProperty("NameValue")
	private List<NameValue>nameValue;
	public String getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}
	public List<NameValue> getNameValue() {
		return nameValue;
	}
	public void setNameValue(List<NameValue> nameValue) {
		this.nameValue = nameValue;
	}
	@Override
	public String toString() {
		return "MIRecord [rowIndex=" + rowIndex + ", nameValue=" + nameValue + "]";
	}
	
}
