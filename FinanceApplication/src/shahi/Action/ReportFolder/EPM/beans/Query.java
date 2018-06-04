package shahi.Action.ReportFolder.EPM.beans;

public class Query {

	private String searchKey;
	private String searchValue;
	
	public Query(){
		
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "Query [searchKey=" + searchKey + ", searchValue=" + searchValue + "]";
	}
	
}
