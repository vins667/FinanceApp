/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

import java.util.List;


public class PreInvPrintPDFBean {
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
    private String buyer_state;
    private String buyer_gstin;
    private String cons_state;
    private String cons_gstin;
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
    private String MANUF_STATE;
    private String MANUF_GSTIN;
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
    private String BPO;
    private List STYLIST;
    private String STYLE;
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
    private String ROSL;
    private double TFOB;
    private double TNETWT;
    private double NETWT;
    private double TINVQTY;
    private double TDBKINR;
    private double TMISCINR;
    private double TINR;
    private double GRPER;
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
    private String SCHEME_CODE;
    private String AD_CODE;
    private int INVYR;
    private String PDBK; 
    private String PSTR;
    private String PACCRDBK;
    private String PACCRSTR;
    
    
    private double TOT_AMT_CGST;
    private double TOT_AMT_SGST;
    private double TOT_AMT_IGST;
    private double TOT_FOB;
    private double TOT_FOBDISCOUNT;
    private double TRANS_TAX;
    private String MSTATE;
    private String BSTATE;
    private String CSTATE;
    private String LUT_IGST;
    
    public PreInvPrintPDFBean(){
        
    }

    public PreInvPrintPDFBean(String currentdate, String aausrid, String excs_inv_no, String plan_no, String inv_date, String self_tp, String exp_type, String exp_type_desc, String inv_qty, String cost_centre, String buyer, String mode_of_ship, String pre_carriage, String lcno, String comm_per, 
            Double upcharge_per, String location, String FACILITY, String buyer_addr, String buyer_name, String buyer_address, String fwd_custom, 
            String cons_addr, String cons_name, String cons_address, String con_tin, String con_cst, String ac_holder, String hs_code, String CLR_PORT, 
            String CLR_PORT_DESC, String LOADING_PORT, String LOADING_PORT_DESC, String agent, String fwd_code, String notify, String NOTIFY_NAME, 
            String NOTIFY_ADDRESS, String CHA_NAME, String FWD_NAME, String DESTI_CNTRY, String DESTI_CNTRY_DESC, String DIS_CNTRY, String DIS_CNTRY_DESC, 
            String DISCHARGE, String DISCHARGE_DESC, String DESTI_CODE, String DESTI_CODE_DESC, String CNTRY_ORIGIN, double transport_cost, String CNTRY_ORIGIN_DESC, 
            String PLACE, String PLACE_DESC, String MANUF_STATE, String MANUF_GSTIN, String MANUF_CODE, String MANUF_DESC, String MANUF_ADDRESS, 
            String MANUF_CST, String MANUF_TIN, Double TAX_PERCENT, Double TAX_CAL_PER, Double TAXABLE_VALUE, Double TAX_AMT, String ship_term, 
            String payment_term, String pay_term, String ship_cancel, String pay_term_desc, String CTNS, String LIC_DECL_TEXT, String ERR_MSG, String MSG1, 
            String MSG2, List INVLINELIST, List UNITLIST, List BPOLIST, String BPO, List STYLIST, String STYLE, List DBKLIST, List STRLIST, List STRMISCLIST,
            List HNGRLIST, List ACCRLIST, List ACCRDBKLIST, List ACCRSTRLIST, List BELIST, String MITYPE, String YEAR, String COMPANY, String INV_NO, String 
                    ROSL, double TFOB, double TNETWT, double NETWT, double TINVQTY, double TDBKINR, double TMISCINR, double TINR, double GRPER, double TGRDECL,
                    double INV_FC, double UP_AMT, String INVDATE, String AMT_IN_WORD, int CHKDBK, int CHKDBKACCR, int CHKSTRACCR, int CHKBE, double COMM_AMT,
                    String SCHEME_CODE, String AD_CODE, int INVYR, String PDBK, String PSTR, String PACCRDBK, String PACCRSTR, double TOT_AMT_CGST, 
                    double TOT_AMT_SGST, double TOT_AMT_IGST,double TOT_FOB,double TOT_FOBDISCOUNT,double TRANS_TAX ,String MSTATE,String CSTATE,String BSTATE) {
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
        this.MANUF_STATE = MANUF_STATE;
        this.MANUF_GSTIN = MANUF_GSTIN;
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
        this.BPO = BPO;
        this.STYLIST = STYLIST;
        this.STYLE = STYLE;
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
        this.ROSL = ROSL;
        this.TFOB = TFOB;
        this.TNETWT = TNETWT;
        this.NETWT = NETWT;
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
        this.SCHEME_CODE = SCHEME_CODE;
        this.AD_CODE = AD_CODE;
        this.INVYR = INVYR;
        this.PDBK = PDBK; 
        this.PSTR = PSTR;
        this.PACCRDBK = PACCRDBK;
        this.PACCRSTR = PACCRSTR;
        this.TOT_AMT_CGST = TOT_AMT_CGST;
        this.TOT_AMT_SGST = TOT_AMT_SGST;
        this.TOT_AMT_IGST = TOT_AMT_IGST;
        this.TOT_FOB = TOT_FOB;
        this.TOT_FOBDISCOUNT = TOT_FOBDISCOUNT;
        this.TRANS_TAX=TRANS_TAX;
        this.MSTATE=MSTATE;
        this.CSTATE=CSTATE;
        this.BSTATE=BSTATE;
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

    public String getMANUF_GSTIN() {
        return MANUF_GSTIN;
    }

    public void setMANUF_GSTIN(String MANUF_GSTIN) {
        this.MANUF_GSTIN = MANUF_GSTIN;
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

    public String getBPO() {
        return BPO;
    }

    public void setBPO(String BPO) {
        this.BPO = BPO;
    }

    public List getSTYLIST() {
        return STYLIST;
    }

    public void setSTYLIST(List STYLIST) {
        this.STYLIST = STYLIST;
    }

    public String getSTYLE() {
        return STYLE;
    }

    public void setSTYLE(String STYLE) {
        this.STYLE = STYLE;
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

    public String getROSL() {
        return ROSL;
    }

    public void setROSL(String ROSL) {
        this.ROSL = ROSL;
    }

    public double getTFOB() {
        return TFOB;
    }

    public void setTFOB(double TFOB) {
        this.TFOB = TFOB;
    }

    public double getTNETWT() {
        return TNETWT;
    }

    public void setTNETWT(double TNETWT) {
        this.TNETWT = TNETWT;
    }

    public double getNETWT() {
        return NETWT;
    }

    public void setNETWT(double NETWT) {
        this.NETWT = NETWT;
    }

    public double getTINVQTY() {
        return TINVQTY;
    }

    public void setTINVQTY(double TINVQTY) {
        this.TINVQTY = TINVQTY;
    }

    public double getTDBKINR() {
        return TDBKINR;
    }

    public void setTDBKINR(double TDBKINR) {
        this.TDBKINR = TDBKINR;
    }

    public double getTMISCINR() {
        return TMISCINR;
    }

    public void setTMISCINR(double TMISCINR) {
        this.TMISCINR = TMISCINR;
    }

    public double getTINR() {
        return TINR;
    }

    public void setTINR(double TINR) {
        this.TINR = TINR;
    }

    public double getGRPER() {
        return GRPER;
    }

    public void setGRPER(double GRPER) {
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

    public String getSCHEME_CODE() {
        return SCHEME_CODE;
    }

    public void setSCHEME_CODE(String SCHEME_CODE) {
        this.SCHEME_CODE = SCHEME_CODE;
    }

    public String getAD_CODE() {
        return AD_CODE;
    }

    public void setAD_CODE(String AD_CODE) {
        this.AD_CODE = AD_CODE;
    }

    public int getINVYR() {
        return INVYR;
    }

    public void setINVYR(int INVYR) {
        this.INVYR = INVYR;
    }

    public String getPDBK() {
        return PDBK;
    }

    public void setPDBK(String PDBK) {
        this.PDBK = PDBK;
    }

    public String getPSTR() {
        return PSTR;
    }

    public void setPSTR(String PSTR) {
        this.PSTR = PSTR;
    }

    public String getPACCRDBK() {
        return PACCRDBK;
    }

    public void setPACCRDBK(String PACCRDBK) {
        this.PACCRDBK = PACCRDBK;
    }

    public String getPACCRSTR() {
        return PACCRSTR;
    }

    public void setPACCRSTR(String PACCRSTR) {
        this.PACCRSTR = PACCRSTR;
    }

    public double getTOT_AMT_CGST() {
        return TOT_AMT_CGST;
    }

    public void setTOT_AMT_CGST(double TOT_AMT_CGST) {
        this.TOT_AMT_CGST = TOT_AMT_CGST;
    }

    public double getTOT_AMT_SGST() {
        return TOT_AMT_SGST;
    }

    public void setTOT_AMT_SGST(double TOT_AMT_SGST) {
        this.TOT_AMT_SGST = TOT_AMT_SGST;
    }

    public double getTOT_AMT_IGST() {
        return TOT_AMT_IGST;
    }

    public void setTOT_AMT_IGST(double TOT_AMT_IGST) {
        this.TOT_AMT_IGST = TOT_AMT_IGST;
    }
    
    public double getTOT_FOB() {
        return TOT_FOB;
    }

    public void setTOT_FOB(double TOT_FOB) {
        this.TOT_FOB = TOT_FOB;
    }
    
    public double getTOT_FOBDISCOUNT() {
        return TOT_FOBDISCOUNT;
    }

    public void setTOT_FOBDISCOUNT(double TOT_FOBDISCOUNT) {
        this.TOT_FOBDISCOUNT = TOT_FOBDISCOUNT;
    }

    public String getBuyer_state() {
        return buyer_state;
    }

    public void setBuyer_state(String buyer_state) {
        this.buyer_state = buyer_state;
    }

    public String getBuyer_gstin() {
        return buyer_gstin;
    }

    public void setBuyer_gstin(String buyer_gstin) {
        this.buyer_gstin = buyer_gstin;
    }

    public String getCons_state() {
        return cons_state;
    }

    public void setCons_state(String cons_state) {
        this.cons_state = cons_state;
    }

    public String getCons_gstin() {
        return cons_gstin;
    }

    public void setCons_gstin(String cons_gstin) {
        this.cons_gstin = cons_gstin;
    }

    public double getTRANS_TAX() {
        return TRANS_TAX;
    }

    public void setTRANS_TAX(double TRANS_TAX) {
        this.TRANS_TAX = TRANS_TAX;
    }

    public String getMSTATE() {
        return MSTATE;
    }

    public void setMSTATE(String MSTATE) {
        this.MSTATE = MSTATE;
    }

    public String getBSTATE() {
        return BSTATE;
    }

    public void setBSTATE(String BSTATE) {
        this.BSTATE = BSTATE;
    }

    public String getCSTATE() {
        return CSTATE;
    }

    public void setCSTATE(String CSTATE) {
        this.CSTATE = CSTATE;
    }

    public String getLUT_IGST() {
        return LUT_IGST;
    }

    public void setLUT_IGST(String LUT_IGST) {
        this.LUT_IGST = LUT_IGST;
    }

    
       
    
    
   
     
      
    
}
