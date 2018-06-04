/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Ranjeet
 */
public class BillOfSalesMastBean {
    
     private String UPDATE_ALLOW="YES";
     private String DOC_FWD;
     private String BOS_DATE;
     private String INTERUNIT;
     private String INTERUNIT_DIS;
     private String DRIVER_NAME;
     private String LOCATION_CODE; 
     private String SHIP_MODE;
     private String DRIVER_MOBILE;
     private String BOS_NO;
     private String LR_NO;
     private String DL_NO;
     private String LR_DATE;
     private String VEHICLE_TYPE;
     private String VEHICLE_TYPE_DESC;
     private String BUYER;
     private String BUYER_ADDR;
     private String BUYER_DESC;
     private String BUYER_ADDRESS_NAME;
     private String VEHICLE_NO;
     private String TO_DATE;
     private String CHA;
     private String CHA_ADDR;
     private String CHA_DESC;
     private String CHA_ADDRESS_NAME;
     private String VCH_ARV_DATE;
     private String HALT_HRS;
      private String UNIT;
      private String UNIT_DESC;
     private String VCH_DEP_DATE;
     private String SEAL_NO;
     private String UNIT_TO;
     private String UNIT_TO_DESC;
     private String VCH_REP_DATE;
     private String GR_WT;
     private String TRANSPORTER;
     private String DISPATCH_VIA;
     private String DAMAGE;
     private String PORT;
     private String PORT_DESC;
     private String PLAN_CFT;
     private String PLAN_SIZE;
     private String DESTINATION;
     private String DESTINATION_DESC;
     private String ACTUAL_CFT;
     private String ACTUAL_SIZE;
     private String REMARKS;
     private String CFS_CODE;
     private String CFS_DESC;
     private String CUTOFF_DATE;
     private String CANCEL_DATE;
     private String BOSNO;
     
     private List PKGS;
     private List FY_PKGS;
     private List INV_NO;
     private List INV_DESC;
     private List QNTY;
     private List FY_QNTY;
     private List AVG_RATE;
     private List CRNCY;
     private List FOB;
     private List INR_CONV;
     private List CFT_PLAN;
     private List CFT_ACTUAL;
     private List GRWT;
     private List PRINT_DATE;
     private List PRINT_DATE_FLAG;
   
     private List YEAR;
     private List COMPANY;
     private List DISPATCH_YN;
     private List UOM;
     private List CBM;
     private List VOL;
     private List EXCS_INV_NO;
     private List FY_TDATE;
     private List FY_USER;
     private String SEARCH_INVOICE; 
     private String SEARCH_WAREHOUSE;
     private String SERACH_PLAN_NUMB;
     private Date SEARCH_PLAN_DATE;

     private Date SEARCH_TOPLAN_DATE;
     private String SSEARCH_PLAN_DATE;
     private String SSEARCH_TOPLAN_DATE;
     
     private List DETAILLIST;
     private String QNTY_TOTAL;
     private String CTN_TOTAL;
     private String FYQNTY_TOTAL;
     private String FYCTN_TOTAL; 
     private String FOB_TOTAL;
     private String GRWT_TOTAL;
     private String CBM_TOTAL;
     private String VOL_TOTAL;
     private String CFTPLAN_TOTAL;
     private String CFTACT_TOTAL;
     private String INR_CONV_TOTAL;
     private String SEARCH_SUBMIT;
     private String FY_HALT;        
     private String FY_CANCEL   ;   
     private String CONTAINER_TYPE;
     private String CONTAINER_NO  ; 
    
    private String GPRS_YN;
    private String LEASE_YN;
    private String RETURN_YN;
    private String RETURN_CARGO;
    private String RFID_SEAL_NO;

    public BillOfSalesMastBean() {
    }

      
     
    public BillOfSalesMastBean(String INTERUNIT, String LOCATION_CODE, String BOS_NO,String BOS_DATE, String BUYER, String CHA, String UNIT, String UNIT_TO,String TRANSPORTER) {
        this.INTERUNIT = INTERUNIT;
        this.LOCATION_CODE = LOCATION_CODE;
        this.BOS_NO = BOS_NO;
        this.BOS_DATE=BOS_DATE;
        this.BUYER = BUYER;
        this.CHA = CHA;
        this.UNIT = UNIT;
        this.UNIT_TO = UNIT_TO;
        this.TRANSPORTER=TRANSPORTER;
    }
      

    public String getGPRS_YN() {
		return GPRS_YN;
	}



	public void setGPRS_YN(String gPRS_YN) {
		GPRS_YN = gPRS_YN;
	}



	public String getLEASE_YN() {
		return LEASE_YN;
	}



	public void setLEASE_YN(String lEASE_YN) {
		LEASE_YN = lEASE_YN;
	}



	public String getRETURN_YN() {
		return RETURN_YN;
	}



	public void setRETURN_YN(String rETURN_YN) {
		RETURN_YN = rETURN_YN;
	}



	public String getRETURN_CARGO() {
		return RETURN_CARGO;
	}



	public void setRETURN_CARGO(String rETURN_CARGO) {
		RETURN_CARGO = rETURN_CARGO;
	}



	public String getACTUAL_CFT() {
        return ACTUAL_CFT;
    }

    public void setACTUAL_CFT(String ACTUAL_CFT) {
        this.ACTUAL_CFT = ACTUAL_CFT;
    }

    public String getACTUAL_SIZE() {
        return ACTUAL_SIZE;
    }

    public void setACTUAL_SIZE(String ACTUAL_SIZE) {
        this.ACTUAL_SIZE = ACTUAL_SIZE;
    }

    public List getAVG_RATE() {
        return AVG_RATE;
    }

    public void setAVG_RATE(List AVG_RATE) {
        this.AVG_RATE = AVG_RATE;
    }

    public String getBOS_DATE() {
        return BOS_DATE;
    }

    public void setBOS_DATE(String BOS_DATE) {
        this.BOS_DATE = BOS_DATE;
    }

    public String getBOS_NO() {
        return BOS_NO;
    }

    public void setBOS_NO(String BOS_NO) {
        this.BOS_NO = BOS_NO;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getBUYER_ADDR() {
        return BUYER_ADDR;
    }

    public void setBUYER_ADDR(String BUYER_ADDR) {
        this.BUYER_ADDR = BUYER_ADDR;
    }

    public String getBUYER_DESC() {
        return BUYER_DESC;
    }

    public void setBUYER_DESC(String BUYER_DESC) {
        this.BUYER_DESC = BUYER_DESC;
    }

    public String getCANCEL_DATE() {
        return CANCEL_DATE;
    }

    public void setCANCEL_DATE(String CANCEL_DATE) {
        this.CANCEL_DATE = CANCEL_DATE;
    }

    public String getCFS_CODE() {
        return CFS_CODE;
    }

    public void setCFS_CODE(String CFS_CODE) {
        this.CFS_CODE = CFS_CODE;
    }

    public String getCFS_DESC() {
        return CFS_DESC;
    }

    public void setCFS_DESC(String CFS_DESC) {
        this.CFS_DESC = CFS_DESC;
    }

    public List getCFT_ACTUAL() {
        return CFT_ACTUAL;
    }

    public void setCFT_ACTUAL(List CFT_ACTUAL) {
        this.CFT_ACTUAL = CFT_ACTUAL;
    }

    public List getCFT_PLAN() {
        return CFT_PLAN;
    }

    public void setCFT_PLAN(List CFT_PLAN) {
        this.CFT_PLAN = CFT_PLAN;
    }

    public String getCHA() {
        return CHA;
    }

    public void setCHA(String CHA) {
        this.CHA = CHA;
    }

    public String getCHA_ADDR() {
        return CHA_ADDR;
    }

    public void setCHA_ADDR(String CHA_ADDR) {
        this.CHA_ADDR = CHA_ADDR;
    }

    public String getCHA_DESC() {
        return CHA_DESC;
    }

    public void setCHA_DESC(String CHA_DESC) {
        this.CHA_DESC = CHA_DESC;
    }

    public List getCRNCY() {
        return CRNCY;
    }

    public void setCRNCY(List CRNCY) {
        this.CRNCY = CRNCY;
    }

    public String getCUTOFF_DATE() {
        return CUTOFF_DATE;
    }

    public void setCUTOFF_DATE(String CUTOFF_DATE) {
        this.CUTOFF_DATE = CUTOFF_DATE;
    }

    public String getDAMAGE() {
        return DAMAGE;
    }

    public void setDAMAGE(String DAMAGE) {
        this.DAMAGE = DAMAGE;
    }

    public String getDESTINATION() {
        return DESTINATION;
    }

    public void setDESTINATION(String DESTINATION) {
        this.DESTINATION = DESTINATION;
    }

    public String getDESTINATION_DESC() {
        return DESTINATION_DESC;
    }

    public void setDESTINATION_DESC(String DESTINATION_DESC) {
        this.DESTINATION_DESC = DESTINATION_DESC;
    }

    public String getDISPATCH_VIA() {
        return DISPATCH_VIA;
    }

    public void setDISPATCH_VIA(String DISPATCH_VIA) {
        this.DISPATCH_VIA = DISPATCH_VIA;
    }

    public String getDL_NO() {
        return DL_NO;
    }

    public void setDL_NO(String DL_NO) {
        this.DL_NO = DL_NO;
    }

    public String getDRIVER_MOBILE() {
        return DRIVER_MOBILE;
    }

    public void setDRIVER_MOBILE(String DRIVER_MOBILE) {
        this.DRIVER_MOBILE = DRIVER_MOBILE;
    }

    public String getDRIVER_NAME() {
        return DRIVER_NAME;
    }

    public void setDRIVER_NAME(String DRIVER_NAME) {
        this.DRIVER_NAME = DRIVER_NAME;
    }

    public List getFOB() {
        return FOB;
    }

    public void setFOB(List FOB) {
        this.FOB = FOB;
    }

    public List getGRWT() {
        return GRWT;
    }

    public void setGRWT(List GRWT) {
        this.GRWT = GRWT;
    }

    public String getGR_WT() {
        return GR_WT;
    }

    public void setGR_WT(String GR_WT) {
        this.GR_WT = GR_WT;
    }

    public String getHALT_HRS() {
        return HALT_HRS;
    }

    public void setHALT_HRS(String HALT_HRS) {
        this.HALT_HRS = HALT_HRS;
    }

    public List getINR_CONV() {
        return INR_CONV;
    }

    public void setINR_CONV(List INR_CONV) {
        this.INR_CONV = INR_CONV;
    }

    public String getINTERUNIT() {
        return INTERUNIT;
    }

    public void setINTERUNIT(String INTERUNIT) {
        this.INTERUNIT = INTERUNIT;
    }

    public String getINTERUNIT_DIS() {
        return INTERUNIT_DIS;
    }

    public void setINTERUNIT_DIS(String INTERUNIT_DIS) {
        this.INTERUNIT_DIS = INTERUNIT_DIS;
    }

    public List getINV_DESC() {
        return INV_DESC;
    }

    public void setINV_DESC(List INV_DESC) {
        this.INV_DESC = INV_DESC;
    }

    public List getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(List INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getLOCATION_CODE() {
        return LOCATION_CODE;
    }

    public void setLOCATION_CODE(String LOCATION_CODE) {
        this.LOCATION_CODE = LOCATION_CODE;
    }

    public String getLR_DATE() {
        return LR_DATE;
    }

    public void setLR_DATE(String LR_DATE) {
        this.LR_DATE = LR_DATE;
    }

    public String getLR_NO() {
        return LR_NO;
    }

    public void setLR_NO(String LR_NO) {
        this.LR_NO = LR_NO;
    }

    public List getPKGS() {
        return PKGS;
    }

    public void setPKGS(List PKGS) {
        this.PKGS = PKGS;
    }

    public String getPLAN_CFT() {
        return PLAN_CFT;
    }

    public void setPLAN_CFT(String PLAN_CFT) {
        this.PLAN_CFT = PLAN_CFT;
    }

    public String getPLAN_SIZE() {
        return PLAN_SIZE;
    }

    public void setPLAN_SIZE(String PLAN_SIZE) {
        this.PLAN_SIZE = PLAN_SIZE;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getPORT_DESC() {
        return PORT_DESC;
    }

    public void setPORT_DESC(String PORT_DESC) {
        this.PORT_DESC = PORT_DESC;
    }

    public List getPRINT_DATE() {
        return PRINT_DATE;
    }

    public void setPRINT_DATE(List PRINT_DATE) {
        this.PRINT_DATE = PRINT_DATE;
    }

    public List getQNTY() {
        return QNTY;
    }

    public void setQNTY(List QNTY) {
        this.QNTY = QNTY;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getSEAL_NO() {
        return SEAL_NO;
    }

    public void setSEAL_NO(String SEAL_NO) {
        this.SEAL_NO = SEAL_NO;
    }

    public String getSHIP_MODE() {
        return SHIP_MODE;
    }

    public void setSHIP_MODE(String SHIP_MODE) {
        this.SHIP_MODE = SHIP_MODE;
    }

    public String getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(String TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public String getTRANSPORTER() {
        return TRANSPORTER;
    }

    public void setTRANSPORTER(String TRANSPORTER) {
        this.TRANSPORTER = TRANSPORTER;
    }

    public String getUNIT() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    public String getUNIT_DESC() {
        return UNIT_DESC;
    }

    public void setUNIT_DESC(String UNIT_DESC) {
        this.UNIT_DESC = UNIT_DESC;
    }

    public String getUNIT_TO() {
        return UNIT_TO;
    }

    public void setUNIT_TO(String UNIT_TO) {
        this.UNIT_TO = UNIT_TO;
    }

    public String getUNIT_TO_DESC() {
        return UNIT_TO_DESC;
    }

    public void setUNIT_TO_DESC(String UNIT_TO_DESC) {
        this.UNIT_TO_DESC = UNIT_TO_DESC;
    }

    public String getVCH_ARV_DATE() {
        return VCH_ARV_DATE;
    }

    public void setVCH_ARV_DATE(String VCH_ARV_DATE) {
        this.VCH_ARV_DATE = VCH_ARV_DATE;
    }

    public String getVCH_DEP_DATE() {
        return VCH_DEP_DATE;
    }

    public void setVCH_DEP_DATE(String VCH_DEP_DATE) {
        this.VCH_DEP_DATE = VCH_DEP_DATE;
    }

    public String getVCH_REP_DATE() {
        return VCH_REP_DATE;
    }

    public void setVCH_REP_DATE(String VCH_REP_DATE) {
        this.VCH_REP_DATE = VCH_REP_DATE;
    }

    public String getVEHICLE_NO() {
        return VEHICLE_NO;
    }

    public void setVEHICLE_NO(String VEHICLE_NO) {
        this.VEHICLE_NO = VEHICLE_NO;
    }

    public String getVEHICLE_TYPE() {
        return VEHICLE_TYPE;
    }

    public void setVEHICLE_TYPE(String VEHICLE_TYPE) {
        this.VEHICLE_TYPE = VEHICLE_TYPE;
    }

    public String getVEHICLE_TYPE_DESC() {
        return VEHICLE_TYPE_DESC;
    }

    public void setVEHICLE_TYPE_DESC(String VEHICLE_TYPE_DESC) {
        this.VEHICLE_TYPE_DESC = VEHICLE_TYPE_DESC;
    }

    public List getDISPATCH_YN() {
        return DISPATCH_YN;
    }

    public void setDISPATCH_YN(List DISPATCH_YN) {
        this.DISPATCH_YN = DISPATCH_YN;
    }

    

    public List getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(List COMPANY) {
        this.COMPANY = COMPANY;
    }

    public List getYEAR() {
        return YEAR;
    }

    public void setYEAR(List YEAR) {
        this.YEAR = YEAR;
    }

    public List getUOM() {
        return UOM;
    }

    public void setUOM(List UOM) {
        this.UOM = UOM;
    }

    public Date getSEARCH_PLAN_DATE() {
        return SEARCH_PLAN_DATE;
    }

    public void setSEARCH_PLAN_DATE(Date SEARCH_PLAN_DATE) {
        this.SEARCH_PLAN_DATE = SEARCH_PLAN_DATE;
    }

    public Date getSEARCH_TOPLAN_DATE() {
        return SEARCH_TOPLAN_DATE;
    }

    public void setSEARCH_TOPLAN_DATE(Date SEARCH_TOPLAN_DATE) {
        this.SEARCH_TOPLAN_DATE = SEARCH_TOPLAN_DATE;
    }

    

    public String getSEARCH_WAREHOUSE() {
        return SEARCH_WAREHOUSE;
    }

    public void setSEARCH_WAREHOUSE(String SEARCH_WAREHOUSE) {
        this.SEARCH_WAREHOUSE = SEARCH_WAREHOUSE;
    }

    public String getSERACH_PLAN_NUMB() {
        return SERACH_PLAN_NUMB;
    }

    public void setSERACH_PLAN_NUMB(String SERACH_PLAN_NUMB) {
        this.SERACH_PLAN_NUMB = SERACH_PLAN_NUMB;
    }

    public String getSSEARCH_PLAN_DATE() {
        return SSEARCH_PLAN_DATE;
    }

    public void setSSEARCH_PLAN_DATE(String SSEARCH_PLAN_DATE) {
        this.SSEARCH_PLAN_DATE = SSEARCH_PLAN_DATE;
    }

    public String getSSEARCH_TOPLAN_DATE() {
        return SSEARCH_TOPLAN_DATE;
    }

    public void setSSEARCH_TOPLAN_DATE(String SSEARCH_TOPLAN_DATE) {
        this.SSEARCH_TOPLAN_DATE = SSEARCH_TOPLAN_DATE;
    }

    public String getBUYER_ADDRESS_NAME() {
        return BUYER_ADDRESS_NAME;
    }

    public void setBUYER_ADDRESS_NAME(String BUYER_ADDRESS_NAME) {
        this.BUYER_ADDRESS_NAME = BUYER_ADDRESS_NAME;
    }

    public String getCHA_ADDRESS_NAME() {
        return CHA_ADDRESS_NAME;
    }

    public void setCHA_ADDRESS_NAME(String CHA_ADDRESS_NAME) {
        this.CHA_ADDRESS_NAME = CHA_ADDRESS_NAME;
    }

    public List getDETAILLIST() {
        return DETAILLIST;
    }

    public void setDETAILLIST(List DETAILLIST) {
        this.DETAILLIST = DETAILLIST;
    }

    public String getFOB_TOTAL() {
        return FOB_TOTAL;
    }

    public void setFOB_TOTAL(String FOB_TOTAL) {
        this.FOB_TOTAL = FOB_TOTAL;
    }

    public String getINR_CONV_TOTAL() {
        return INR_CONV_TOTAL;
    }

    public void setINR_CONV_TOTAL(String INR_CONV_TOTAL) {
        this.INR_CONV_TOTAL = INR_CONV_TOTAL;
    }

    public String getQNTY_TOTAL() {
        return QNTY_TOTAL;
    }

    public void setQNTY_TOTAL(String QNTY_TOTAL) {
        this.QNTY_TOTAL = QNTY_TOTAL;
    }

    public List getPRINT_DATE_FLAG() {
        return PRINT_DATE_FLAG;
    }

    public void setPRINT_DATE_FLAG(List PRINT_DATE_FLAG) {
        this.PRINT_DATE_FLAG = PRINT_DATE_FLAG;
    }

    public String getSEARCH_SUBMIT() {
        return SEARCH_SUBMIT;
    }

    public void setSEARCH_SUBMIT(String SEARCH_SUBMIT) {
        this.SEARCH_SUBMIT = SEARCH_SUBMIT;
    }

    public List getCBM() {
        return CBM;
    }

    public void setCBM(List CBM) {
        this.CBM = CBM;
    }

    public List getVOL() {
        return VOL;
    }

    public void setVOL(List VOL) {
        this.VOL = VOL;
    }

    public String getCONTAINER_NO() {
        return CONTAINER_NO;
    }

    public void setCONTAINER_NO(String CONTAINER_NO) {
        this.CONTAINER_NO = CONTAINER_NO;
    }

    public String getCONTAINER_TYPE() {
        return CONTAINER_TYPE;
    }

    public void setCONTAINER_TYPE(String CONTAINER_TYPE) {
        this.CONTAINER_TYPE = CONTAINER_TYPE;
    }

    public String getFY_CANCEL() {
        return FY_CANCEL;
    }

    public void setFY_CANCEL(String FY_CANCEL) {
        this.FY_CANCEL = FY_CANCEL;
    }

    public String getFY_HALT() {
        return FY_HALT;
    }

    public void setFY_HALT(String FY_HALT) {
        this.FY_HALT = FY_HALT;
    }

    public List getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(List EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

	public String getBOSNO() {
		return BOSNO;
	}

	public void setBOSNO(String bOSNO) {
		BOSNO = bOSNO;
	}

    public List getFY_PKGS() {
        return FY_PKGS;
    }

    public void setFY_PKGS(List FY_PKGS) {
        this.FY_PKGS = FY_PKGS;
    }

    public List getFY_QNTY() {
        return FY_QNTY;
    }

    public void setFY_QNTY(List FY_QNTY) {
        this.FY_QNTY = FY_QNTY;
    }

    public String getCTN_TOTAL() {
        return CTN_TOTAL;
    }

    public void setCTN_TOTAL(String CTN_TOTAL) {
        this.CTN_TOTAL = CTN_TOTAL;
    }

    public String getFYQNTY_TOTAL() {
        return FYQNTY_TOTAL;
    }

    public void setFYQNTY_TOTAL(String FYQNTY_TOTAL) {
        this.FYQNTY_TOTAL = FYQNTY_TOTAL;
    }

    public String getFYCTN_TOTAL() {
        return FYCTN_TOTAL;
    }

    public void setFYCTN_TOTAL(String FYCTN_TOTAL) {
        this.FYCTN_TOTAL = FYCTN_TOTAL;
    }

    public String getUPDATE_ALLOW() {
        return UPDATE_ALLOW;
    }

    public void setUPDATE_ALLOW(String UPDATE_ALLOW) {
        this.UPDATE_ALLOW = UPDATE_ALLOW;
    }

    public String getGRWT_TOTAL() {
        return GRWT_TOTAL;
    }

    public void setGRWT_TOTAL(String GRWT_TOTAL) {
        this.GRWT_TOTAL = GRWT_TOTAL;
    }

    public String getCBM_TOTAL() {
        return CBM_TOTAL;
    }

    public void setCBM_TOTAL(String CBM_TOTAL) {
        this.CBM_TOTAL = CBM_TOTAL;
    }

    public String getVOL_TOTAL() {
        return VOL_TOTAL;
    }

    public void setVOL_TOTAL(String VOL_TOTAL) {
        this.VOL_TOTAL = VOL_TOTAL;
    }

    public String getCFTPLAN_TOTAL() {
        return CFTPLAN_TOTAL;
    }
 
    public void setCFTPLAN_TOTAL(String CFTPLAN_TOTAL) {
        this.CFTPLAN_TOTAL = CFTPLAN_TOTAL;
    }

    public String getCFTACT_TOTAL() {
        return CFTACT_TOTAL;
    }

    public void setCFTACT_TOTAL(String CFTACT_TOTAL) {
        this.CFTACT_TOTAL = CFTACT_TOTAL;
    }

    public List getFY_TDATE() {
        return FY_TDATE;
    }

    public void setFY_TDATE(List FY_TDATE) {
        this.FY_TDATE = FY_TDATE;
    }

    public String getSEARCH_INVOICE() {
        return SEARCH_INVOICE;
    }

    public void setSEARCH_INVOICE(String SEARCH_INVOICE) {
        this.SEARCH_INVOICE = SEARCH_INVOICE;
    }

    public List getFY_USER() {
        return FY_USER;
    }

    public void setFY_USER(List FY_USER) {
        this.FY_USER = FY_USER;
    }

    public String getDOC_FWD() {
        return DOC_FWD;
    }

    public void setDOC_FWD(String DOC_FWD) {
        this.DOC_FWD = DOC_FWD;
    }

    public String getRFID_SEAL_NO() {
        return RFID_SEAL_NO;
    }

    public void setRFID_SEAL_NO(String RFID_SEAL_NO) {
        this.RFID_SEAL_NO = RFID_SEAL_NO;
    }
    
      
       
     
}
