package shahi.Action.restresponse;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;



public class RootObject {

	@JsonProperty("Program")
	private String Program;

	public String getProgram() { return this.Program; }

	public void setProgram(String Program) { this.Program = Program; }
	@JsonProperty("Transaction")
	private String Transaction;

	public String getTransaction() { return this.Transaction; }

	public void setTransaction(String Transaction) { this.Transaction = Transaction; }
	@JsonProperty("Metadata")
	private Metadata Metadata;

	public Metadata getMetadata() { return this.Metadata; }

	public void setMetadata(Metadata Metadata) { this.Metadata = Metadata; }

	@JsonProperty("MIRecord")
	private ArrayList<MIRecord> MIRecord;

	public ArrayList<MIRecord> getMIRecord() { return this.MIRecord; }

	public void setMIRecord(ArrayList<MIRecord> MIRecord) { this.MIRecord = MIRecord; }

	@Override
	public String toString() {
		return "RootObject [Program=" + Program + ", Transaction=" + Transaction + ", Metadata=" + Metadata
				+ ", MIRecord=" + MIRecord + "]";
	}

}
