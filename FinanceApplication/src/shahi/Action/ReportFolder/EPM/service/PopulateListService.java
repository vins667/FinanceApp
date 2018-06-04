package shahi.Action.ReportFolder.EPM.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.dao.PopulateListDao;

public class PopulateListService {

	@Autowired
	private PopulateListDao populateListDao;
	
	public List<Code>getAllCompanies(){
		return populateListDao.loadAllCompanies();
	}
	
	public List<Code>getAllDivisions(){
		return populateListDao.loadAllDivision();
	}
	public List<Code>getAllPayrollTypes(){
		return populateListDao.loadAllPayrollTypes();
	}
	public List<Code>getAllAccounts(){
		return populateListDao.loadAllAccounts();
	}
	public List<Code>getAllFileTypes(String division){
		return populateListDao.loadAllFileTypes(division);
	}
	
	public Map<String,String>getFileTypeMapping(String division){
		return populateListDao.getFileTypeMapping(division);
	}
	
	public List<Code> getDivisionList(){
		return populateListDao.getDivisionList();
	}
	
	public List<Code>getDivisionListByCompany(String cono){
		return populateListDao.getDivisionListByCompany(cono);
	}
	
	 public List<Code> getYearList(String divi){
		 return populateListDao.getYearList(divi);
	 }
	 
	 public List<Code> getSupplier(String regex){
		 return populateListDao.getSupplier(regex);
	 }
	 
	 public List<Code>getCustomers(String regex){
		 return populateListDao.getCustomer(regex);
	 }
	 public List<Code>getAllVouchers(){
		 return populateListDao.getAllVouchers();
	 }
	 
	 public List<Code>getVoucherByDivision(String division){
		 return populateListDao.getVouTypeListByDivison(division);
	 }
}
