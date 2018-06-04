package shahi.pagination;

import java.util.ArrayList;
import java.util.List;

public class Page<E> {

	private int pageNumber;
    private int pagesAvailable;
    private List<E> pageItems = new ArrayList<E>();
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPagesAvailable() {
		return pagesAvailable;
	}
	public void setPagesAvailable(int pagesAvailable) {
		this.pagesAvailable = pagesAvailable;
	}
	public List<E> getPageItems() {
		return pageItems;
	}
	public void setPageItems(List<E> pageItems) {
		this.pageItems = pageItems;
	}
}
