package shahi.Action.FundReq.specification;

import java.util.List;

import shahi.Action.FundReq.Query;
import shahi.Action.FundReq.Beans.SearchCriteria;

public interface Specification {

	public String getSpecification(List<SearchCriteria> criteria);
	
	
}
