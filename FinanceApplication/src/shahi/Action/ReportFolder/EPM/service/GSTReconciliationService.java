package shahi.Action.ReportFolder.EPM.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.beans.GSTReconciliation;
import shahi.Action.ReportFolder.EPM.dao.GSTReconciliationDao;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class GSTReconciliationService {

	@Autowired
	private GSTReconciliationDao gstDao;
	private int flag=0;
    private List<GSTReconciliation>matchedInvoices=new ArrayList<>();

	public boolean save(List<GSTReconciliation> list){
		return gstDao.save(list);
	}

	public List<Code>loadAllCompanies(){
		return gstDao.getAllCompanies();
	}
	public List<Code>loadShahiGSTNByDivision(String division){
		return gstDao.getShahiGSTNByDivision(division);
	}
	
	public List<GSTReconciliation> filterData(String vendorGSTN,String annexure,String fromDate,String toDate){
		return gstDao.getReconcilliationData(vendorGSTN, annexure,fromDate, toDate);
	}

	public List<Code>loadAllVendorGSTN(){
		return gstDao.getAllVendorsGSTN();
	}
	public List<GSTReconciliation> loadAllShahiGSTNData(String shahiGSTN,String userId,String divi,String fromDate,String toDate) throws RuntimeException {
		if (fromDate!=null && fromDate.isEmpty()){
			fromDate=DateUtil.getFinancialYear();
		}else{
			fromDate=DateUtil.converToYYYYMMDD(fromDate);
		}
		if(toDate!=null && toDate.isEmpty()){
			toDate=DateUtil.getCurrentDateInYYYYMMDD();
		}else{
			toDate=DateUtil.converToYYYYMMDD(toDate);
		}
		List<GSTReconciliation> shahiData=gstDao.loadAllGSTNInvoices(shahiGSTN, userId, divi,fromDate,toDate);
		List<GSTReconciliation>supplierData=gstDao.loadVendorGSTNInvoices(shahiGSTN, userId, divi, fromDate, toDate);
		List<GSTReconciliation> mismatch=performRoundOneCalculation(supplierData,shahiData,toDate);
		mismatch.addAll(performRoundTwoCalculation(shahiData,supplierData,toDate));
		saveMatchedInvoices(matchedInvoices,toDate);
		return mismatch;
	}

	private List<GSTReconciliation> performRoundOneCalculation(List<GSTReconciliation>supplierData,List<GSTReconciliation>shahiData,String recoDate){
		List<GSTReconciliation>mismatch=new ArrayList<>(shahiData.size());
		for(GSTReconciliation supplier:supplierData ){
			if(!isVendorInvoiceEqual(supplier,shahiData)){
				supplier.setANX_TYPE("1");
				supplier.setDOC_TYPE("S");
				supplier.setINVOICEDT(DateUtil.converToDDMMYYYY(supplier.getINVOICEDT()));
				saveIntoTemp(supplier);
				mismatch.add(supplier);
			}else if(!isVendorInvoiceTaxEqual(supplier,shahiData) ){
				supplier.setANX_TYPE("2");
				supplier.setDOC_TYPE("S");
				supplier.setINVOICEDT(DateUtil.converToDDMMYYYY(supplier.getINVOICEDT()));
				saveIntoTemp(supplier);
				mismatch.add(supplier);
			}else if(isVendorInvoiceTaxEqual(supplier,shahiData)){
				supplier.setINVOICEDT(DateUtil.converFromDDMMYYYY(DateUtil.converToDDMMYYYY(supplier.getINVOICEDT())));
				matchedInvoices.add(supplier);
			}
		}
		return mismatch;
	}
	private List<GSTReconciliation> performRoundTwoCalculation(List<GSTReconciliation>shahiData,List<GSTReconciliation>supplierData,String recoDate){
			List<GSTReconciliation>mismatch=new ArrayList<>(shahiData.size());
		for(GSTReconciliation shahi : shahiData){
				if(!isShahiInvoiceEqual(shahi,supplierData)){
					shahi.setANX_TYPE("1");
					shahi.setDOC_TYPE("C");
					saveIntoTemp(shahi);
					mismatch.add(shahi);
				}else if(!isShahiInvoiceTaxEqual(shahi,supplierData)){
					shahi.setANX_TYPE("2");
					shahi.setDOC_TYPE("C");
				//shahi.setINVOICEDT(DateUtil.converToDDMMYYYY(shahi.getINVOICEDT()));
					saveIntoTemp(shahi);
					mismatch.add(shahi);
				}else if(isShahiInvoiceTaxEqual(shahi,supplierData)){
					shahi.setINVOICEDT(DateUtil.converFromDDMMYYYY(DateUtil.converToDDMMYYYY(shahi.getINVOICEDT())));
					matchedInvoices.add(shahi);
				}
		}
		return mismatch;
	}
	private void saveIntoTemp(GSTReconciliation supplier){
		try{
			gstDao.insertIntoTemp(supplier);
		}catch(RuntimeException ex){
			System.out.println(ex.getLocalizedMessage());
		}
	}
	private boolean isVendorInvoiceEqual(GSTReconciliation vendorInvoice,List<GSTReconciliation> shahiInvoice){
		for(GSTReconciliation shahi:shahiInvoice){
			if(shahi.getINVOICENO().trim().equals(vendorInvoice.getINVOICENO().trim()) && 
					shahi.getSHAHIGSTN().trim().equals(vendorInvoice.getSHAHIGSTN()) && 
					DateUtil.converToDDMMYYYY(vendorInvoice.getINVOICEDT().trim()).equals(shahi.getINVOICEDT().trim())){
				flag=1;
				break;
			}
		}
		if(flag==1){
			flag=0;
			return true;
		}
		return false;

	}
	private boolean isShahiInvoiceEqual(GSTReconciliation shahiInvoice,List<GSTReconciliation> vendorInvoice){
		for(GSTReconciliation vendor:vendorInvoice){
			if(shahiInvoice.getINVOICENO().trim().equals(vendor.getINVOICENO().trim()) && 
					shahiInvoice.getSHAHIGSTN().trim().equals(vendor.getSHAHIGSTN()) && 
					DateUtil.converToDDMMYYYY(shahiInvoice.getINVOICEDT().trim()).equals(vendor.getINVOICEDT().trim())){
				flag=1;
				break;
			}
		}
		if(flag==1){
			flag=0;
			return true;
		}
		return false;

	}

	private boolean isVendorInvoiceTaxEqual(GSTReconciliation vendorInvoice,List<GSTReconciliation>  shahiInvoice){
		try{
			Double vendInvAmount=Double.valueOf(vendorInvoice.getLineItemAmount().trim())+Double.valueOf(vendorInvoice.getTAXVALUE().trim());
			Double shahiInvAmount=0d;
			for(GSTReconciliation shahi:shahiInvoice){
				if(shahi.getINVOICENO().trim().equals(vendorInvoice.getINVOICENO().trim()) && 
						shahi.getSHAHIGSTN().trim().equals(vendorInvoice.getSHAHIGSTN()) && 
						DateUtil.converToDDMMYYYY(vendorInvoice.getINVOICEDT().trim()).equals(shahi.getINVOICEDT().trim())){
					shahiInvAmount=0d;
					shahiInvAmount=Double.valueOf(shahi.getLineItemAmount().trim())+Double.valueOf(shahi.getTAXVALUE().trim());
					if(Math.abs(vendInvAmount-shahiInvAmount)<1){
						flag=1;
						break;
					}
				}
			}
		}catch(Exception ex){
			flag=1;
			System.out.println(ex.getMessage());
		}

		if(flag==1){
			flag=0;
			return true;
		}
		return false;
	}
	
	private boolean isShahiInvoiceTaxEqual(GSTReconciliation shahiInvoice,List<GSTReconciliation>  vendorInvoices){
		try{
			Double shahiInvAmount=Double.valueOf(shahiInvoice.getLineItemAmount().trim())+Double.valueOf(shahiInvoice.getTAXVALUE().trim());
			Double  vendInvAmount=0d;
			for(GSTReconciliation vendor:vendorInvoices){
				if(shahiInvoice.getINVOICENO().trim().equals(vendor.getINVOICENO().trim()) && 
						shahiInvoice.getSHAHIGSTN().trim().equals(vendor.getSHAHIGSTN()) && 
						DateUtil.converToDDMMYYYY(shahiInvoice.getINVOICEDT().trim()).equals(vendor.getINVOICEDT().trim())){
					vendInvAmount=0d;
					vendInvAmount=Double.valueOf(vendor.getLineItemAmount().trim())+Double.valueOf(vendor.getTAXVALUE().trim());
					if(Math.abs(vendInvAmount-shahiInvAmount)<1){
						flag=1;
						break;
					}
				}
			}
		}catch(Exception ex){
			flag=1;
			System.out.println(ex.getMessage());
		}

		if(flag==1){
			flag=0;
			return true;
		}
		return false;
	}
	
	private void saveMatchedInvoices(List<GSTReconciliation>list,String recoDate){
		Date date=DateUtil.convertFromDDMMYY(recoDate);
		for(GSTReconciliation invoice:list){
			updateRecoInvoices(invoice,date);
		}
		
	}
	@Transactional("txManager")
	public  boolean updateRecoDate(GSTReconciliation updateInvoice,Date recoDate){
		if(updateInvoice.getANX_TYPE().equals("1") && updateInvoice.getDOC_TYPE().equals("S")){
			return gstDao.updateManualVendorInvoice(updateInvoice, recoDate);
		}else if(updateInvoice.getANX_TYPE().equals("1") && updateInvoice.getDOC_TYPE().equals("C")){
			return gstDao.updateManualShahiInvoice(updateInvoice, recoDate);
		}
		return false;
	}
	@Transactional("txManager")
	public void updateRecoInvoices(GSTReconciliation updateInvoice,Date recoDate){
		gstDao.updateVendorInvoice(updateInvoice, recoDate);
		gstDao.updateShahiInvoice(updateInvoice, recoDate);
	}
	 
	public List<GSTReconciliation>getUnReconciliedInvoices(String division,String shahigstn,String fromDate,String toDate){
		
		return gstDao.loadUnReconciliedInvoices(division, shahigstn, fromDate, toDate);
	}
}
