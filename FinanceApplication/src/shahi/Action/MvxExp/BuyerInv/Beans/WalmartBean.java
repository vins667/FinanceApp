/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

import java.util.List;

/**
 *
 * @author Guddu
 */
public class WalmartBean {

    private String currentdate;
    private String aausrid;
    private String excs_inv_no;
    private String plan_no;
    private String inv_date;
    private String self_tp;
    private String exp_type;
    private String exp_type_desc;
    private String inv_qty;
    private String cost_centre;
    private String buyer;
    private String mode_of_ship;
    private String pre_carriage;
    private String lcno;
    private String comm_per;
    private Double upcharge_per;
    private String location;
    private String FACILITY;
    private String buyer_addr;
    private String buyer_name;
    private String buyer_address;
    private String fwd_custom;
    private String cons_addr;
    private String cons_name;
    private String cons_address;
    private String con_tin;
    private String con_cst;
    private String ac_holder;
    private String hs_code;
    private String CLR_PORT;
    private String CLR_PORT_DESC;
    private String LOADING_PORT;
    private String LOADING_PORT_DESC;
    private String agent;
    private String fwd_code;
    private String notify;
    private String NOTIFY_NAME;
    private String NOTIFY_ADDRESS;
    private String CHA_NAME;
    private String FWD_NAME;
    private String DESTI_CNTRY;
    private String DESTI_CNTRY_DESC;
    private String DIS_CNTRY;
    private String DIS_CNTRY_DESC;
    private String DISCHARGE;
    private String DISCHARGE_DESC;
    private String DESTI_CODE;
    private String DESTI_CODE_DESC;
    private String CNTRY_ORIGIN;
    private double transport_cost;
    private String CNTRY_ORIGIN_DESC;
    private String PLACE;
    private String PLACE_DESC;
    private String SHIP_DESC=null;
    private String CRNCY_CODE=null;
    private String MANUF_STATE=null;
    private String MANUF_CODE;
    private String MANUF_DESC;
    private String MANUF_ADDRESS;
    private String MANUF_CST;
    private String MANUF_TIN;
    private String TAX_TYPE=null;
    private Double TAX_PERCENT;
    private String TAX_CODE=null;
    private Double TAX_CAL_PER;
    private Double TAXABLE_VALUE;
    private Double TAX_AMT;
    private String ship_term;
    private String payment_term;
    private String pay_term;  
    private String ship_cancel;//invoice cancel
    private String pay_term_desc;
    private String CTNS;   
    private String LIC_DECL_TEXT; 
    private String ERR_MSG;
    private String MSG1;
    private String MSG2;
    private List INVLINELIST;
    private List UNITLIST;
    private List BPOLIST;
    private List STYLIST;
    private List DBKLIST;
    private List STRLIST;
    private List STRMISCLIST;
    private List HNGRLIST;
    private List ACCRLIST;
    private List ACCRDBKLIST;
    private List ACCRSTRLIST;
    private List BELIST;
    private String MITYPE;
    private String YEAR;
    private String COMPANY;
    private String INV_NO;
     
    private String TFOB;
    private String TNETWT;
    private String TINVQTY;
    private String TDBKINR;
    private String TMISCINR;
    private String TINR;
    private String GRPER;
    private double TGRDECL;
    private double INV_FC;
    private double UP_AMT;
    private String INVDATE;
    private String AMT_IN_WORD;
    private int CHKDBK;
    private int CHKDBKACCR;
    private int CHKSTRACCR;
    private int CHKBE;
    private double COMM_AMT;
    
    private double MRP_AMT;
    private int CAL_VAL;
    private double EXCISE_DUTY;
    private double TOTAL_INV;
    private String AMT_IN_WORD2;
     private double INV_RATE;
     private String INV_DESC;
     private String UNIT;
     private String GROSWT;
     private String NTWT;
     private String CBMVAL;
       private String CREATIONNO;
        private String MODLNO;
        private String LDPRT;
        private String CATE;
        private String MSRMNT;
        
        private String FIRSTQLITY;
        private String SESON;
        private String STYLENM;
        private String THDIVI;
        private String FABTYP;
        private String LICCODE;
        private String CRGODT;
        private String VESLDT;
        private String CTNMNT;
        
        private String VENDNM;
        private String VENDADD;
        private String FWDCONSO;
        private String TOT_CRTN;
        
         private double TOTCASE;
         private double TOTALUNIT;
         private double NETTOTAL;
         private double GORSTOTAL;
         private double TOTALVAL;
         private double TOTAMOUNT;
         private List INVLLST;
         private List ITEMLINELIST;
        
    
   

    public WalmartBean(){
        
    }

    public WalmartBean(String currentdate, String aausrid, String excs_inv_no, String plan_no, String inv_date, String self_tp, String exp_type, String exp_type_desc, String inv_qty, String cost_centre, String buyer, String mode_of_ship, String pre_carriage, String lcno, String comm_per, Double upcharge_per, String location, String FACILITY, String buyer_addr, String buyer_name, String buyer_address, String fwd_custom, String cons_addr, String cons_name, String cons_address, String con_tin, String con_cst, String ac_holder, String hs_code, String CLR_PORT, String CLR_PORT_DESC, String LOADING_PORT, String LOADING_PORT_DESC, String agent, String fwd_code, String notify, String NOTIFY_NAME, String NOTIFY_ADDRESS, String CHA_NAME, String FWD_NAME, String DESTI_CNTRY, String DESTI_CNTRY_DESC, String DIS_CNTRY, String DIS_CNTRY_DESC, String DISCHARGE, String DISCHARGE_DESC, String DESTI_CODE, String DESTI_CODE_DESC, String CNTRY_ORIGIN, double transport_cost, String CNTRY_ORIGIN_DESC, String PLACE, String PLACE_DESC, String MANUF_CODE, String MANUF_DESC, String MANUF_ADDRESS, String MANUF_CST, String MANUF_TIN, Double TAX_PERCENT, Double TAX_CAL_PER, Double TAXABLE_VALUE, Double TAX_AMT, String ship_term, String payment_term, String pay_term, String ship_cancel, String pay_term_desc, String CTNS, String LIC_DECL_TEXT, String ERR_MSG, String MSG1, String MSG2, List INVLINELIST, List UNITLIST, List BPOLIST, List STYLIST, List DBKLIST, List STRLIST, List STRMISCLIST, List HNGRLIST, List ACCRLIST, List ACCRDBKLIST, List ACCRSTRLIST, List BELIST, String MITYPE, String YEAR, String COMPANY, String INV_NO, String TFOB, String TNETWT, String TINVQTY, String TDBKINR, String TMISCINR, String TINR, String GRPER, double TGRDECL, double INV_FC, double UP_AMT, String INVDATE, String AMT_IN_WORD, int CHKDBK, int CHKDBKACCR, int CHKSTRACCR, int CHKBE, double COMM_AMT, double MRP_AMT, int CAL_VAL, double EXCISE_DUTY, double TOTAL_INV, String AMT_IN_WORD2, double INV_RATE, String INV_DESC, String UNIT, String GROSWT, String NTWT, String CBMVAL, String CREATIONNO, String MODLNO, String LDPRT, String CATE, String MSRMNT, String FIRSTQLITY, String SESON, String STYLENM, String THDIVI, String FABTYP, String LICCODE, String CRGODT, String VESLDT, String CTNMNT, String VENDNM, String VENDADD, String FWDCONSO, String TOT_CRTN, double TOTCASE, double TOTALUNIT, double NETTOTAL, double GORSTOTAL, double TOTALVAL, double TOTAMOUNT,List INVLLST,List ITEMLINELIST) {
        this.currentdate = currentdate;
        this.aausrid = aausrid;
        this.excs_inv_no = excs_inv_no;
        this.plan_no = plan_no;
        this.inv_date = inv_date;
        this.self_tp = self_tp;
        this.exp_type = exp_type;
        this.exp_type_desc = exp_type_desc;
        this.inv_qty = inv_qty;
        this.cost_centre = cost_centre;
        this.buyer = buyer;
        this.mode_of_ship = mode_of_ship;
        this.pre_carriage = pre_carriage;
        this.lcno = lcno;
        this.comm_per = comm_per;
        this.upcharge_per = upcharge_per;
        this.location = location;
        this.FACILITY = FACILITY;
        this.buyer_addr = buyer_addr;
        this.buyer_name = buyer_name;
        this.buyer_address = buyer_address;
        this.fwd_custom = fwd_custom;
        this.cons_addr = cons_addr;
        this.cons_name = cons_name;
        this.cons_address = cons_address;
        this.con_tin = con_tin;
        this.con_cst = con_cst;
        this.ac_holder = ac_holder;
        this.hs_code = hs_code;
        this.CLR_PORT = CLR_PORT;
        this.CLR_PORT_DESC = CLR_PORT_DESC;
        this.LOADING_PORT = LOADING_PORT;
        this.LOADING_PORT_DESC = LOADING_PORT_DESC;
        this.agent = agent;
        this.fwd_code = fwd_code;
        this.notify = notify;
        this.NOTIFY_NAME = NOTIFY_NAME;
        this.NOTIFY_ADDRESS = NOTIFY_ADDRESS;
        this.CHA_NAME = CHA_NAME;
        this.FWD_NAME = FWD_NAME;
        this.DESTI_CNTRY = DESTI_CNTRY;
        this.DESTI_CNTRY_DESC = DESTI_CNTRY_DESC;
        this.DIS_CNTRY = DIS_CNTRY;
        this.DIS_CNTRY_DESC = DIS_CNTRY_DESC;
        this.DISCHARGE = DISCHARGE;
        this.DISCHARGE_DESC = DISCHARGE_DESC;
        this.DESTI_CODE = DESTI_CODE;
        this.DESTI_CODE_DESC = DESTI_CODE_DESC;
        this.CNTRY_ORIGIN = CNTRY_ORIGIN;
        this.transport_cost = transport_cost;
        this.CNTRY_ORIGIN_DESC = CNTRY_ORIGIN_DESC;
        this.PLACE = PLACE;
        this.PLACE_DESC = PLACE_DESC;
        this.MANUF_CODE = MANUF_CODE;
        this.MANUF_DESC = MANUF_DESC;
        this.MANUF_ADDRESS = MANUF_ADDRESS;
        this.MANUF_CST = MANUF_CST;
        this.MANUF_TIN = MANUF_TIN;
        this.TAX_PERCENT = TAX_PERCENT;
        this.TAX_CAL_PER = TAX_CAL_PER;
        this.TAXABLE_VALUE = TAXABLE_VALUE;
        this.TAX_AMT = TAX_AMT;
        this.ship_term = ship_term;
        this.payment_term = payment_term;
        this.pay_term = pay_term;
        this.ship_cancel = ship_cancel;
        this.pay_term_desc = pay_term_desc;
        this.CTNS = CTNS;
        this.LIC_DECL_TEXT = LIC_DECL_TEXT;
        this.ERR_MSG = ERR_MSG;
        this.MSG1 = MSG1;
        this.MSG2 = MSG2;
        this.INVLINELIST = INVLINELIST;
        this.UNITLIST = UNITLIST;
        this.BPOLIST = BPOLIST;
        this.STYLIST = STYLIST;
        this.DBKLIST = DBKLIST;
        this.STRLIST = STRLIST;
        this.STRMISCLIST = STRMISCLIST;
        this.HNGRLIST = HNGRLIST;
        this.ACCRLIST = ACCRLIST;
        this.ACCRDBKLIST = ACCRDBKLIST;
        this.ACCRSTRLIST = ACCRSTRLIST;
        this.BELIST = BELIST;
        this.MITYPE = MITYPE;
        this.YEAR = YEAR;
        this.COMPANY = COMPANY;
        this.INV_NO = INV_NO;
        this.TFOB = TFOB;
        this.TNETWT = TNETWT;
        this.TINVQTY = TINVQTY;
        this.TDBKINR = TDBKINR;
        this.TMISCINR = TMISCINR;
        this.TINR = TINR;
        this.GRPER = GRPER;
        this.TGRDECL = TGRDECL;
        this.INV_FC = INV_FC;
        this.UP_AMT = UP_AMT;
        this.INVDATE = INVDATE;
        this.AMT_IN_WORD = AMT_IN_WORD;
        this.CHKDBK = CHKDBK;
        this.CHKDBKACCR = CHKDBKACCR;
        this.CHKSTRACCR = CHKSTRACCR;
        this.CHKBE = CHKBE;
        this.COMM_AMT = COMM_AMT;
        this.MRP_AMT = MRP_AMT;
        this.CAL_VAL = CAL_VAL;
        this.EXCISE_DUTY = EXCISE_DUTY;
        this.TOTAL_INV = TOTAL_INV;
        this.AMT_IN_WORD2 = AMT_IN_WORD2;
        this.INV_RATE = INV_RATE;
        this.INV_DESC = INV_DESC;
        this.UNIT = UNIT;
        this.GROSWT = GROSWT;
        this.NTWT = NTWT;
        this.CBMVAL = CBMVAL;
        this.CREATIONNO = CREATIONNO;
        this.MODLNO = MODLNO;
        this.LDPRT = LDPRT;
        this.CATE = CATE;
        this.MSRMNT = MSRMNT;
        this.FIRSTQLITY = FIRSTQLITY;
        this.SESON = SESON;
        this.STYLENM = STYLENM;
        this.THDIVI = THDIVI;
        this.FABTYP = FABTYP;
        this.LICCODE = LICCODE;
        this.CRGODT = CRGODT;
        this.VESLDT = VESLDT;
        this.CTNMNT = CTNMNT;
        this.VENDNM = VENDNM;
        this.VENDADD = VENDADD;
        this.FWDCONSO = FWDCONSO;
        this.TOT_CRTN = TOT_CRTN;
        this.TOTCASE = TOTCASE;
        this.TOTALUNIT = TOTALUNIT;
        this.NETTOTAL = NETTOTAL;
        this.GORSTOTAL = GORSTOTAL;
        this.TOTALVAL = TOTALVAL;
        this.TOTAMOUNT = TOTAMOUNT;
        this.INVLLST=INVLLST;
        this.ITEMLINELIST=ITEMLINELIST;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getExcs_inv_no() {
        return excs_inv_no;
    }

    public void setExcs_inv_no(String excs_inv_no) {
        this.excs_inv_no = excs_inv_no;
    }

    public String getPlan_no() {
        return plan_no;
    }

    public void setPlan_no(String plan_no) {
        this.plan_no = plan_no;
    }

    public String getInv_date() {
        return inv_date;
    }

    public void setInv_date(String inv_date) {
        this.inv_date = inv_date;
    }

    public String getSelf_tp() {
        return self_tp;
    }

    public void setSelf_tp(String self_tp) {
        this.self_tp = self_tp;
    }

    public String getExp_type() {
        return exp_type;
    }

    public void setExp_type(String exp_type) {
        this.exp_type = exp_type;
    }

    public String getExp_type_desc() {
        return exp_type_desc;
    }

    public void setExp_type_desc(String exp_type_desc) {
        this.exp_type_desc = exp_type_desc;
    }

    public String getInv_qty() {
        return inv_qty;
    }

    public void setInv_qty(String inv_qty) {
        this.inv_qty = inv_qty;
    }

    public String getCost_centre() {
        return cost_centre;
    }

    public void setCost_centre(String cost_centre) {
        this.cost_centre = cost_centre;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getMode_of_ship() {
        return mode_of_ship;
    }

    public void setMode_of_ship(String mode_of_ship) {
        this.mode_of_ship = mode_of_ship;
    }

    public String getPre_carriage() {
        return pre_carriage;
    }

    public void setPre_carriage(String pre_carriage) {
        this.pre_carriage = pre_carriage;
    }

    public String getLcno() {
        return lcno;
    }

    public void setLcno(String lcno) {
        this.lcno = lcno;
    }

    public String getComm_per() {
        return comm_per;
    }

    public void setComm_per(String comm_per) {
        this.comm_per = comm_per;
    }

    public Double getUpcharge_per() {
        return upcharge_per;
    }

    public void setUpcharge_per(Double upcharge_per) {
        this.upcharge_per = upcharge_per;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFACILITY() {
        return FACILITY;
    }

    public void setFACILITY(String FACILITY) {
        this.FACILITY = FACILITY;
    }

    public String getBuyer_addr() {
        return buyer_addr;
    }

    public void setBuyer_addr(String buyer_addr) {
        this.buyer_addr = buyer_addr;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_address() {
        return buyer_address;
    }

    public void setBuyer_address(String buyer_address) {
        this.buyer_address = buyer_address;
    }

    public String getFwd_custom() {
        return fwd_custom;
    }

    public void setFwd_custom(String fwd_custom) {
        this.fwd_custom = fwd_custom;
    }

    public String getCons_addr() {
        return cons_addr;
    }

    public void setCons_addr(String cons_addr) {
        this.cons_addr = cons_addr;
    }

    public String getCons_name() {
        return cons_name;
    }

    public void setCons_name(String cons_name) {
        this.cons_name = cons_name;
    }

    public String getCons_address() {
        return cons_address;
    }

    public void setCons_address(String cons_address) {
        this.cons_address = cons_address;
    }

    public String getCon_tin() {
        return con_tin;
    }

    public void setCon_tin(String con_tin) {
        this.con_tin = con_tin;
    }

    public String getCon_cst() {
        return con_cst;
    }

    public void setCon_cst(String con_cst) {
        this.con_cst = con_cst;
    }

    public String getAc_holder() {
        return ac_holder;
    }

    public void setAc_holder(String ac_holder) {
        this.ac_holder = ac_holder;
    }

    public String getHs_code() {
        return hs_code;
    }

    public void setHs_code(String hs_code) {
        this.hs_code = hs_code;
    }

    public String getCLR_PORT() {
        return CLR_PORT;
    }

    public void setCLR_PORT(String CLR_PORT) {
        this.CLR_PORT = CLR_PORT;
    }

    public String getCLR_PORT_DESC() {
        return CLR_PORT_DESC;
    }

    public void setCLR_PORT_DESC(String CLR_PORT_DESC) {
        this.CLR_PORT_DESC = CLR_PORT_DESC;
    }

    public String getLOADING_PORT() {
        return LOADING_PORT;
    }

    public void setLOADING_PORT(String LOADING_PORT) {
        this.LOADING_PORT = LOADING_PORT;
    }

    public String getLOADING_PORT_DESC() {
        return LOADING_PORT_DESC;
    }

    public void setLOADING_PORT_DESC(String LOADING_PORT_DESC) {
        this.LOADING_PORT_DESC = LOADING_PORT_DESC;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getFwd_code() {
        return fwd_code;
    }

    public void setFwd_code(String fwd_code) {
        this.fwd_code = fwd_code;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getNOTIFY_NAME() {
        return NOTIFY_NAME;
    }

    public void setNOTIFY_NAME(String NOTIFY_NAME) {
        this.NOTIFY_NAME = NOTIFY_NAME;
    }

    public String getNOTIFY_ADDRESS() {
        return NOTIFY_ADDRESS;
    }

    public void setNOTIFY_ADDRESS(String NOTIFY_ADDRESS) {
        this.NOTIFY_ADDRESS = NOTIFY_ADDRESS;
    }

    public String getCHA_NAME() {
        return CHA_NAME;
    }

    public void setCHA_NAME(String CHA_NAME) {
        this.CHA_NAME = CHA_NAME;
    }

    public String getFWD_NAME() {
        return FWD_NAME;
    }

    public void setFWD_NAME(String FWD_NAME) {
        this.FWD_NAME = FWD_NAME;
    }

    public String getDESTI_CNTRY() {
        return DESTI_CNTRY;
    }

    public void setDESTI_CNTRY(String DESTI_CNTRY) {
        this.DESTI_CNTRY = DESTI_CNTRY;
    }

    public String getDESTI_CNTRY_DESC() {
        return DESTI_CNTRY_DESC;
    }

    public void setDESTI_CNTRY_DESC(String DESTI_CNTRY_DESC) {
        this.DESTI_CNTRY_DESC = DESTI_CNTRY_DESC;
    }

    public String getDIS_CNTRY() {
        return DIS_CNTRY;
    }

    public void setDIS_CNTRY(String DIS_CNTRY) {
        this.DIS_CNTRY = DIS_CNTRY;
    }

    public String getDIS_CNTRY_DESC() {
        return DIS_CNTRY_DESC;
    }

    public void setDIS_CNTRY_DESC(String DIS_CNTRY_DESC) {
        this.DIS_CNTRY_DESC = DIS_CNTRY_DESC;
    }

    public String getDISCHARGE() {
        return DISCHARGE;
    }

    public void setDISCHARGE(String DISCHARGE) {
        this.DISCHARGE = DISCHARGE;
    }

    public String getDISCHARGE_DESC() {
        return DISCHARGE_DESC;
    }

    public void setDISCHARGE_DESC(String DISCHARGE_DESC) {
        this.DISCHARGE_DESC = DISCHARGE_DESC;
    }

    public String getDESTI_CODE() {
        return DESTI_CODE;
    }

    public void setDESTI_CODE(String DESTI_CODE) {
        this.DESTI_CODE = DESTI_CODE;
    }

    public String getDESTI_CODE_DESC() {
        return DESTI_CODE_DESC;
    }

    public void setDESTI_CODE_DESC(String DESTI_CODE_DESC) {
        this.DESTI_CODE_DESC = DESTI_CODE_DESC;
    }

    public String getCNTRY_ORIGIN() {
        return CNTRY_ORIGIN;
    }

    public void setCNTRY_ORIGIN(String CNTRY_ORIGIN) {
        this.CNTRY_ORIGIN = CNTRY_ORIGIN;
    }

    public double getTransport_cost() {
        return transport_cost;
    }

    public void setTransport_cost(double transport_cost) {
        this.transport_cost = transport_cost;
    }

    public String getCNTRY_ORIGIN_DESC() {
        return CNTRY_ORIGIN_DESC;
    }

    public void setCNTRY_ORIGIN_DESC(String CNTRY_ORIGIN_DESC) {
        this.CNTRY_ORIGIN_DESC = CNTRY_ORIGIN_DESC;
    }

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getPLACE_DESC() {
        return PLACE_DESC;
    }

    public void setPLACE_DESC(String PLACE_DESC) {
        this.PLACE_DESC = PLACE_DESC;
    }

    public String getSHIP_DESC() {
        return SHIP_DESC;
    }

    public void setSHIP_DESC(String SHIP_DESC) {
        this.SHIP_DESC = SHIP_DESC;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public String getMANUF_STATE() {
        return MANUF_STATE;
    }

    public void setMANUF_STATE(String MANUF_STATE) {
        this.MANUF_STATE = MANUF_STATE;
    }

    public String getMANUF_CODE() {
        return MANUF_CODE;
    }

    public void setMANUF_CODE(String MANUF_CODE) {
        this.MANUF_CODE = MANUF_CODE;
    }

    public String getMANUF_DESC() {
        return MANUF_DESC;
    }

    public void setMANUF_DESC(String MANUF_DESC) {
        this.MANUF_DESC = MANUF_DESC;
    }

    public String getMANUF_ADDRESS() {
        return MANUF_ADDRESS;
    }

    public void setMANUF_ADDRESS(String MANUF_ADDRESS) {
        this.MANUF_ADDRESS = MANUF_ADDRESS;
    }

    public String getMANUF_CST() {
        return MANUF_CST;
    }

    public void setMANUF_CST(String MANUF_CST) {
        this.MANUF_CST = MANUF_CST;
    }

    public String getMANUF_TIN() {
        return MANUF_TIN;
    }

    public void setMANUF_TIN(String MANUF_TIN) {
        this.MANUF_TIN = MANUF_TIN;
    }

    public String getTAX_TYPE() {
        return TAX_TYPE;
    }

    public void setTAX_TYPE(String TAX_TYPE) {
        this.TAX_TYPE = TAX_TYPE;
    }

    public Double getTAX_PERCENT() {
        return TAX_PERCENT;
    }

    public void setTAX_PERCENT(Double TAX_PERCENT) {
        this.TAX_PERCENT = TAX_PERCENT;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public Double getTAX_CAL_PER() {
        return TAX_CAL_PER;
    }

    public void setTAX_CAL_PER(Double TAX_CAL_PER) {
        this.TAX_CAL_PER = TAX_CAL_PER;
    }

    public Double getTAXABLE_VALUE() {
        return TAXABLE_VALUE;
    }

    public void setTAXABLE_VALUE(Double TAXABLE_VALUE) {
        this.TAXABLE_VALUE = TAXABLE_VALUE;
    }

    public Double getTAX_AMT() {
        return TAX_AMT;
    }

    public void setTAX_AMT(Double TAX_AMT) {
        this.TAX_AMT = TAX_AMT;
    }

    public String getShip_term() {
        return ship_term;
    }

    public void setShip_term(String ship_term) {
        this.ship_term = ship_term;
    }

    public String getPayment_term() {
        return payment_term;
    }

    public void setPayment_term(String payment_term) {
        this.payment_term = payment_term;
    }

    public String getPay_term() {
        return pay_term;
    }

    public void setPay_term(String pay_term) {
        this.pay_term = pay_term;
    }

    public String getShip_cancel() {
        return ship_cancel;
    }

    public void setShip_cancel(String ship_cancel) {
        this.ship_cancel = ship_cancel;
    }

    public String getPay_term_desc() {
        return pay_term_desc;
    }

    public void setPay_term_desc(String pay_term_desc) {
        this.pay_term_desc = pay_term_desc;
    }

    public String getCTNS() {
        return CTNS;
    }

    public void setCTNS(String CTNS) {
        this.CTNS = CTNS;
    }

    public String getLIC_DECL_TEXT() {
        return LIC_DECL_TEXT;
    }

    public void setLIC_DECL_TEXT(String LIC_DECL_TEXT) {
        this.LIC_DECL_TEXT = LIC_DECL_TEXT;
    }

    public String getERR_MSG() {
        return ERR_MSG;
    }

    public void setERR_MSG(String ERR_MSG) {
        this.ERR_MSG = ERR_MSG;
    }

    public String getMSG1() {
        return MSG1;
    }

    public void setMSG1(String MSG1) {
        this.MSG1 = MSG1;
    }

    public String getMSG2() {
        return MSG2;
    }

    public void setMSG2(String MSG2) {
        this.MSG2 = MSG2;
    }

    public List getINVLINELIST() {
        return INVLINELIST;
    }

    public void setINVLINELIST(List INVLINELIST) {
        this.INVLINELIST = INVLINELIST;
    }

    public List getUNITLIST() {
        return UNITLIST;
    }

    public void setUNITLIST(List UNITLIST) {
        this.UNITLIST = UNITLIST;
    }

    public List getBPOLIST() {
        return BPOLIST;
    }

    public void setBPOLIST(List BPOLIST) {
        this.BPOLIST = BPOLIST;
    }

    public List getSTYLIST() {
        return STYLIST;
    }

    public void setSTYLIST(List STYLIST) {
        this.STYLIST = STYLIST;
    }

    public List getDBKLIST() {
        return DBKLIST;
    }

    public void setDBKLIST(List DBKLIST) {
        this.DBKLIST = DBKLIST;
    }

    public List getSTRLIST() {
        return STRLIST;
    }

    public void setSTRLIST(List STRLIST) {
        this.STRLIST = STRLIST;
    }

    public List getSTRMISCLIST() {
        return STRMISCLIST;
    }

    public void setSTRMISCLIST(List STRMISCLIST) {
        this.STRMISCLIST = STRMISCLIST;
    }

    public List getHNGRLIST() {
        return HNGRLIST;
    }

    public void setHNGRLIST(List HNGRLIST) {
        this.HNGRLIST = HNGRLIST;
    }

    public List getACCRLIST() {
        return ACCRLIST;
    }

    public void setACCRLIST(List ACCRLIST) {
        this.ACCRLIST = ACCRLIST;
    }

    public List getACCRDBKLIST() {
        return ACCRDBKLIST;
    }

    public void setACCRDBKLIST(List ACCRDBKLIST) {
        this.ACCRDBKLIST = ACCRDBKLIST;
    }

    public List getACCRSTRLIST() {
        return ACCRSTRLIST;
    }

    public void setACCRSTRLIST(List ACCRSTRLIST) {
        this.ACCRSTRLIST = ACCRSTRLIST;
    }

    public List getBELIST() {
        return BELIST;
    }

    public void setBELIST(List BELIST) {
        this.BELIST = BELIST;
    }

    public String getMITYPE() {
        return MITYPE;
    }

    public void setMITYPE(String MITYPE) {
        this.MITYPE = MITYPE;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getTFOB() {
        return TFOB;
    }

    public void setTFOB(String TFOB) {
        this.TFOB = TFOB;
    }

    public String getTNETWT() {
        return TNETWT;
    }

    public void setTNETWT(String TNETWT) {
        this.TNETWT = TNETWT;
    }

    public String getTINVQTY() {
        return TINVQTY;
    }

    public void setTINVQTY(String TINVQTY) {
        this.TINVQTY = TINVQTY;
    }

    public String getTDBKINR() {
        return TDBKINR;
    }

    public void setTDBKINR(String TDBKINR) {
        this.TDBKINR = TDBKINR;
    }

    public String getTMISCINR() {
        return TMISCINR;
    }

    public void setTMISCINR(String TMISCINR) {
        this.TMISCINR = TMISCINR;
    }

    public String getTINR() {
        return TINR;
    }

    public void setTINR(String TINR) {
        this.TINR = TINR;
    }

    public String getGRPER() {
        return GRPER;
    }

    public void setGRPER(String GRPER) {
        this.GRPER = GRPER;
    }

    public double getTGRDECL() {
        return TGRDECL;
    }

    public void setTGRDECL(double TGRDECL) {
        this.TGRDECL = TGRDECL;
    }

    public double getINV_FC() {
        return INV_FC;
    }

    public void setINV_FC(double INV_FC) {
        this.INV_FC = INV_FC;
    }

    public double getUP_AMT() {
        return UP_AMT;
    }

    public void setUP_AMT(double UP_AMT) {
        this.UP_AMT = UP_AMT;
    }

    public String getINVDATE() {
        return INVDATE;
    }

    public void setINVDATE(String INVDATE) {
        this.INVDATE = INVDATE;
    }

    public String getAMT_IN_WORD() {
        return AMT_IN_WORD;
    }

    public void setAMT_IN_WORD(String AMT_IN_WORD) {
        this.AMT_IN_WORD = AMT_IN_WORD;
    }

    public int getCHKDBK() {
        return CHKDBK;
    }

    public void setCHKDBK(int CHKDBK) {
        this.CHKDBK = CHKDBK;
    }

    public int getCHKDBKACCR() {
        return CHKDBKACCR;
    }

    public void setCHKDBKACCR(int CHKDBKACCR) {
        this.CHKDBKACCR = CHKDBKACCR;
    }

    public int getCHKSTRACCR() {
        return CHKSTRACCR;
    }

    public void setCHKSTRACCR(int CHKSTRACCR) {
        this.CHKSTRACCR = CHKSTRACCR;
    }

    public int getCHKBE() {
        return CHKBE;
    }

    public void setCHKBE(int CHKBE) {
        this.CHKBE = CHKBE;
    }

    public double getCOMM_AMT() {
        return COMM_AMT;
    }

    public void setCOMM_AMT(double COMM_AMT) {
        this.COMM_AMT = COMM_AMT;
    }

    public double getMRP_AMT() {
        return MRP_AMT;
    }

    public void setMRP_AMT(double MRP_AMT) {
        this.MRP_AMT = MRP_AMT;
    }

    public int getCAL_VAL() {
        return CAL_VAL;
    }

    public void setCAL_VAL(int CAL_VAL) {
        this.CAL_VAL = CAL_VAL;
    }

    public double getEXCISE_DUTY() {
        return EXCISE_DUTY;
    }

    public void setEXCISE_DUTY(double EXCISE_DUTY) {
        this.EXCISE_DUTY = EXCISE_DUTY;
    }

    public double getTOTAL_INV() {
        return TOTAL_INV;
    }

    public void setTOTAL_INV(double TOTAL_INV) {
        this.TOTAL_INV = TOTAL_INV;
    }

    public String getAMT_IN_WORD2() {
        return AMT_IN_WORD2;
    }

    public void setAMT_IN_WORD2(String AMT_IN_WORD2) {
        this.AMT_IN_WORD2 = AMT_IN_WORD2;
    }

    public double getINV_RATE() {
        return INV_RATE;
    }

    public void setINV_RATE(double INV_RATE) {
        this.INV_RATE = INV_RATE;
    }

    public String getINV_DESC() {
        return INV_DESC;
    }

    public void setINV_DESC(String INV_DESC) {
        this.INV_DESC = INV_DESC;
    }

    public String getUNIT() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    public String getGROSWT() {
        return GROSWT;
    }

    public void setGROSWT(String GROSWT) {
        this.GROSWT = GROSWT;
    }

    public String getNTWT() {
        return NTWT;
    }

    public void setNTWT(String NTWT) {
        this.NTWT = NTWT;
    }

    public String getCBMVAL() {
        return CBMVAL;
    }

    public void setCBMVAL(String CBMVAL) {
        this.CBMVAL = CBMVAL;
    }

    public String getCREATIONNO() {
        return CREATIONNO;
    }

    public void setCREATIONNO(String CREATIONNO) {
        this.CREATIONNO = CREATIONNO;
    }

    public String getMODLNO() {
        return MODLNO;
    }

    public void setMODLNO(String MODLNO) {
        this.MODLNO = MODLNO;
    }

    public String getLDPRT() {
        return LDPRT;
    }

    public void setLDPRT(String LDPRT) {
        this.LDPRT = LDPRT;
    }

    public String getCATE() {
        return CATE;
    }

    public void setCATE(String CATE) {
        this.CATE = CATE;
    }

    public String getMSRMNT() {
        return MSRMNT;
    }

    public void setMSRMNT(String MSRMNT) {
        this.MSRMNT = MSRMNT;
    }

    public String getFIRSTQLITY() {
        return FIRSTQLITY;
    }

    public void setFIRSTQLITY(String FIRSTQLITY) {
        this.FIRSTQLITY = FIRSTQLITY;
    }

    public String getSESON() {
        return SESON;
    }

    public void setSESON(String SESON) {
        this.SESON = SESON;
    }

    public String getSTYLENM() {
        return STYLENM;
    }

    public void setSTYLENM(String STYLENM) {
        this.STYLENM = STYLENM;
    }

    public String getTHDIVI() {
        return THDIVI;
    }

    public void setTHDIVI(String THDIVI) {
        this.THDIVI = THDIVI;
    }

    public String getFABTYP() {
        return FABTYP;
    }

    public void setFABTYP(String FABTYP) {
        this.FABTYP = FABTYP;
    }

    public String getLICCODE() {
        return LICCODE;
    }

    public void setLICCODE(String LICCODE) {
        this.LICCODE = LICCODE;
    }

    public String getCRGODT() {
        return CRGODT;
    }

    public void setCRGODT(String CRGODT) {
        this.CRGODT = CRGODT;
    }

    public String getVESLDT() {
        return VESLDT;
    }

    public void setVESLDT(String VESLDT) {
        this.VESLDT = VESLDT;
    }

    public String getCTNMNT() {
        return CTNMNT;
    }

    public void setCTNMNT(String CTNMNT) {
        this.CTNMNT = CTNMNT;
    }

    public String getVENDNM() {
        return VENDNM;
    }

    public void setVENDNM(String VENDNM) {
        this.VENDNM = VENDNM;
    }

    public String getVENDADD() {
        return VENDADD;
    }

    public void setVENDADD(String VENDADD) {
        this.VENDADD = VENDADD;
    }

    public String getFWDCONSO() {
        return FWDCONSO;
    }

    public void setFWDCONSO(String FWDCONSO) {
        this.FWDCONSO = FWDCONSO;
    }

    public String getTOT_CRTN() {
        return TOT_CRTN;
    }

    public void setTOT_CRTN(String TOT_CRTN) {
        this.TOT_CRTN = TOT_CRTN;
    }

    public double getTOTCASE() {
        return TOTCASE;
    }

    public void setTOTCASE(double TOTCASE) {
        this.TOTCASE = TOTCASE;
    }

    public double getTOTALUNIT() {
        return TOTALUNIT;
    }

    public void setTOTALUNIT(double TOTALUNIT) {
        this.TOTALUNIT = TOTALUNIT;
    }

    public double getNETTOTAL() {
        return NETTOTAL;
    }

    public void setNETTOTAL(double NETTOTAL) {
        this.NETTOTAL = NETTOTAL;
    }

    public double getGORSTOTAL() {
        return GORSTOTAL;
    }

    public void setGORSTOTAL(double GORSTOTAL) {
        this.GORSTOTAL = GORSTOTAL;
    }

    public double getTOTALVAL() {
        return TOTALVAL;
    }

    public void setTOTALVAL(double TOTALVAL) {
        this.TOTALVAL = TOTALVAL;
    }

    public double getTOTAMOUNT() {
        return TOTAMOUNT;
    }

    public void setTOTAMOUNT(double TOTAMOUNT) {
        this.TOTAMOUNT = TOTAMOUNT;
    }

    
    public List getINVLLST() {
        return INVLLST;
    }

    public void setINVLLST(List INVLLST) {
        this.INVLLST = INVLLST;
    }
    
    public List getITEMLINELIST() {
        return ITEMLINELIST;
    }

    public void setITEMLINELIST(List ITEMLINELIST) {
        this.ITEMLINELIST = ITEMLINELIST;
    }
        
}
