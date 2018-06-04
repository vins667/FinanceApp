package shahi.Action.restresponse;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
	
	@JsonProperty("name")
	  private String name;

	  public String getName() { return this.name; }

	  public void setName(String name) { this.name = name; }

	  @JsonProperty("type")
	  private String type;

	  public String getType() { return this.type; }

	  public void setType(String type) { this.type = type; }

	  @JsonProperty("length")
	  private String length;

	  public String getLength() { return this.length; }

	  public void setLength(String length) { this.length = length; }

	  @JsonProperty("description")
	  private String description;

	  public String getDescription() { return this.description; }

	  public void setDescription(String description) { this.description = description; }

	@Override
	public String toString() {
		return "Field [name=" + name + ", type=" + type + ", length=" + length + ", description=" + description + "]";
	}
	  
}
