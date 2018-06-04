package shahi.Action.restresponse;

import org.codehaus.jackson.annotate.JsonProperty;

public class NameValue {

	@JsonProperty("Name")
	private String name;
	@JsonProperty("Value")
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "NameValue [name=" + name + ", value=" + value + "]";
	}
	
}
