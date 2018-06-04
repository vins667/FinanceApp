package shahi.Action.restresponse;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Metadata {

	@JsonProperty("Field")
	 private ArrayList<Field> Field;

	  public ArrayList<Field> getField() { return this.Field; }

	  public void setField(ArrayList<Field> Field) { this.Field = Field; }

	@Override
	public String toString() {
		return "Metadata [Field=" + Field + "]";
	}
	  
	  
}
