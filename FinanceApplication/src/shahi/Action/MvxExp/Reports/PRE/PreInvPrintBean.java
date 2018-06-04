/*     */ package shahi.Action.MvxExp.Reports.PRE.bean;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class PreInvPrintBean
/*     */ {
/*     */   private String currentdate;
/*     */   private String aausrid;
/*     */   private String excs_inv_no;
/*     */   private String plan_no;
/*     */   private String inv_date;
/*     */   private String self_tp;
/*     */   private String exp_type;
/*     */   private String exp_type_desc;
/*     */   private String inv_qty;
/*     */   private String cost_centre;
/*     */   private String buyer;
/*     */   private String mode_of_ship;
/*     */   private String pre_carriage;
/*     */   private String lcno;
/*     */   private String comm_per;
/*     */   private Double upcharge_per;
/*     */   private String location;
/*     */   private String FACILITY;
/*     */   private String buyer_addr;
/*     */   private String buyer_name;
/*     */   private String buyer_address;
/*     */   private String fwd_custom;
/*     */   private String cons_addr;
/*     */   private String cons_name;
/*     */   private String cons_address;
/*     */   private String ac_holder;
/*     */   private String hs_code;
/*     */   private String CLR_PORT;
/*     */   private String CLR_PORT_DESC;
/*     */   private String LOADING_PORT;
/*     */   private String LOADING_PORT_DESC;
/*     */   private String agent;
/*     */   private String fwd_code;
/*     */   private String notify;
/*     */   private String NOTIFY_NAME;
/*     */   private String NOTIFY_ADDRESS;
/*     */   private String CHA_NAME;
/*     */   private String FWD_NAME;
/*     */   private String DESTI_CNTRY;
/*     */   private String DESTI_CNTRY_DESC;
/*     */   private String DIS_CNTRY;
/*     */   private String DIS_CNTRY_DESC;
/*     */   private String DISCHARGE;
/*     */   private String DISCHARGE_DESC;
/*     */   private String DESTI_CODE;
/*     */   private String DESTI_CODE_DESC;
/*     */   private String CNTRY_ORIGIN;
/*     */   private String transport_cost;
/*     */   private String CNTRY_ORIGIN_DESC;
/*     */   private String PLACE;
/*     */   private String PLACE_DESC;
/*  68 */   private String SHIP_DESC = null;
/*  69 */   private String CRNCY_CODE = null;
/*  70 */   private String MANUF_STATE = null;
/*     */   private String MANUF_CODE;
/*     */   private String MANUF_DESC;
/*     */   private String MANUF_ADDRESS;
/*     */   private String MANUF_CST;
/*     */   private String MANUF_TIN;
/*  76 */   private String TAX_TYPE = null;
/*     */   private Double TAX_PERCENT;
/*  78 */   private String TAX_CODE = null;
/*     */   private Double TAX_CAL_PER;
/*     */   private Double TAXABLE_VALUE;
/*     */   private Double TAX_AMT;
/*     */   private String ship_term;
/*     */   private String payment_term;
/*     */   private String pay_term;
/*     */   private String ship_cancel;
/*     */   private String pay_term_desc;
/*     */   private String CTNS;
/*     */   private String LIC_DECL_TEXT;
/*     */   private String ERR_MSG;
/*     */   private String MSG1;
/*     */   private String MSG2;
/*     */   private List INVLINELIST;
/*     */   private List UNITLIST;
/*     */   private List BPOLIST;
/*     */   private List STYLIST;
/*     */   private List DBKLIST;
/*     */   private List STRLIST;
/*     */   private List STRMISCLIST;
/*     */   private List HNGRLIST;
/*     */   private List ACCRLIST;
/*     */   private List ACCRDBKLIST;
/*     */   private List ACCRSTRLIST;
/*     */   private List BELIST;
/*     */   private String YEAR;
/*     */   private String COMPANY;
/*     */   private String INV_NO;
/*     */   private double TFOB;
/*     */   private double TNETWT;
/*     */   private int TINVQTY;
/*     */   private double TDBKINR;
/*     */   private double TMISCINR;
/*     */   private int TINR;
/*     */   private double GRPER;
/*     */   private double TGRDECL;
/*     */   private double INV_FC;
/*     */   private double UP_AMT;
/*     */   private String INVDATE;
/*     */   private String AMT_IN_WORD;
/*     */   private int CHKDBK;
/*     */   private int CHKDBKACCR;
/*     */   private int CHKSTRACCR;
/*     */   private int CHKBE;
            private String LUT_IGST;
/*     */  
/*     */   public String getCurrentdate()
/*     */   {
/* 126 */     return this.currentdate;
/*     */   }
/*     */ 
/*     */   public void setCurrentdate(String currentdate) {
/* 130 */     this.currentdate = currentdate;
/*     */   }
/*     */ 
/*     */   public String getAausrid()
/*     */   {
/* 135 */     return this.aausrid;
/*     */   }
/*     */ 
/*     */   public void setAausrid(String aausrid) {
/* 139 */     this.aausrid = aausrid;
/*     */   }
/*     */ 
/*     */   public String getExcs_inv_no() {
/* 143 */     return this.excs_inv_no;
/*     */   }
/*     */ 
/*     */   public void setExcs_inv_no(String excs_inv_no) {
/* 147 */     this.excs_inv_no = excs_inv_no;
/*     */   }
/*     */ 
/*     */   public String getPlan_no() {
/* 151 */     return this.plan_no;
/*     */   }
/*     */ 
/*     */   public void setPlan_no(String plan_no) {
/* 155 */     this.plan_no = plan_no;
/*     */   }
/*     */ 
/*     */   public String getInv_date() {
/* 159 */     return this.inv_date;
/*     */   }
/*     */ 
/*     */   public void setInv_date(String inv_date) {
/* 163 */     this.inv_date = inv_date;
/*     */   }
/*     */ 
/*     */   public String getSelf_tp() {
/* 167 */     return this.self_tp;
/*     */   }
/*     */ 
/*     */   public void setSelf_tp(String self_tp) {
/* 171 */     this.self_tp = self_tp;
/*     */   }
/*     */ 
/*     */   public String getExp_type() {
/* 175 */     return this.exp_type;
/*     */   }
/*     */ 
/*     */   public void setExp_type(String exp_type) {
/* 179 */     this.exp_type = exp_type;
/*     */   }
/*     */ 
/*     */   public String getInv_qty() {
/* 183 */     return this.inv_qty;
/*     */   }
/*     */ 
/*     */   public void setInv_qty(String inv_qty) {
/* 187 */     this.inv_qty = inv_qty;
/*     */   }
/*     */ 
/*     */   public String getCost_centre() {
/* 191 */     return this.cost_centre;
/*     */   }
/*     */ 
/*     */   public void setCost_centre(String cost_centre) {
/* 195 */     this.cost_centre = cost_centre;
/*     */   }
/*     */ 
/*     */   public String getBuyer() {
/* 199 */     return this.buyer;
/*     */   }
/*     */ 
/*     */   public void setBuyer(String buyer) {
/* 203 */     this.buyer = buyer;
/*     */   }
/*     */ 
/*     */   public String getMode_of_ship() {
/* 207 */     return this.mode_of_ship;
/*     */   }
/*     */ 
/*     */   public void setMode_of_ship(String mode_of_ship) {
/* 211 */     this.mode_of_ship = mode_of_ship;
/*     */   }
/*     */ 
/*     */   public String getPre_carriage() {
/* 215 */     return this.pre_carriage;
/*     */   }
/*     */ 
/*     */   public void setPre_carriage(String pre_carriage) {
/* 219 */     this.pre_carriage = pre_carriage;
/*     */   }
/*     */ 
/*     */   public String getLcno() {
/* 223 */     return this.lcno;
/*     */   }
/*     */ 
/*     */   public void setLcno(String lcno) {
/* 227 */     this.lcno = lcno;
/*     */   }
/*     */ 
/*     */   public String getComm_per() {
/* 231 */     return this.comm_per;
/*     */   }
/*     */ 
/*     */   public void setComm_per(String comm_per) {
/* 235 */     this.comm_per = comm_per;
/*     */   }
/*     */ 
/*     */   public Double getUpcharge_per() {
/* 239 */     return this.upcharge_per;
/*     */   }
/*     */ 
/*     */   public void setUpcharge_per(Double upcharge_per) {
/* 243 */     this.upcharge_per = upcharge_per;
/*     */   }
/*     */ 
/*     */   public String getLocation()
/*     */   {
/* 249 */     return this.location;
/*     */   }
/*     */ 
/*     */   public void setLocation(String location) {
/* 253 */     this.location = location;
/*     */   }
/*     */ 
/*     */   public String getBuyer_addr() {
/* 257 */     return this.buyer_addr;
/*     */   }
/*     */ 
/*     */   public void setBuyer_addr(String buyer_addr) {
/* 261 */     this.buyer_addr = buyer_addr;
/*     */   }
/*     */ 
/*     */   public String getBuyer_name() {
/* 265 */     return this.buyer_name;
/*     */   }
/*     */ 
/*     */   public void setBuyer_name(String buyer_name) {
/* 269 */     this.buyer_name = buyer_name;
/*     */   }
/*     */ 
/*     */   public String getBuyer_address() {
/* 273 */     return this.buyer_address;
/*     */   }
/*     */ 
/*     */   public void setBuyer_address(String buyer_address) {
/* 277 */     this.buyer_address = buyer_address;
/*     */   }
/*     */ 
/*     */   public String getCons_addr() {
/* 281 */     return this.cons_addr;
/*     */   }
/*     */ 
/*     */   public void setCons_addr(String cons_addr) {
/* 285 */     this.cons_addr = cons_addr;
/*     */   }
/*     */ 
/*     */   public String getCons_name() {
/* 289 */     return this.cons_name;
/*     */   }
/*     */ 
/*     */   public void setCons_name(String cons_name) {
/* 293 */     this.cons_name = cons_name;
/*     */   }
/*     */ 
/*     */   public String getCons_address() {
/* 297 */     return this.cons_address;
/*     */   }
/*     */ 
/*     */   public void setCons_address(String cons_address) {
/* 301 */     this.cons_address = cons_address;
/*     */   }
/*     */ 
/*     */   public String getAc_holder() {
/* 305 */     return this.ac_holder;
/*     */   }
/*     */ 
/*     */   public void setAc_holder(String ac_holder) {
/* 309 */     this.ac_holder = ac_holder;
/*     */   }
/*     */ 
/*     */   public String getHs_code() {
/* 313 */     return this.hs_code;
/*     */   }
/*     */ 
/*     */   public void setHs_code(String hs_code) {
/* 317 */     this.hs_code = hs_code;
/*     */   }
/*     */ 
/*     */   public String getCLR_PORT()
/*     */   {
/* 322 */     return this.CLR_PORT;
/*     */   }
/*     */ 
/*     */   public void setCLR_PORT(String CLR_PORT) {
/* 326 */     this.CLR_PORT = CLR_PORT;
/*     */   }
/*     */ 
/*     */   public String getCLR_PORT_DESC() {
/* 330 */     return this.CLR_PORT_DESC;
/*     */   }
/*     */ 
/*     */   public void setCLR_PORT_DESC(String CLR_PORT_DESC) {
/* 334 */     this.CLR_PORT_DESC = CLR_PORT_DESC;
/*     */   }
/*     */ 
/*     */   public String getLOADING_PORT() {
/* 338 */     return this.LOADING_PORT;
/*     */   }
/*     */ 
/*     */   public void setLOADING_PORT(String LOADING_PORT) {
/* 342 */     this.LOADING_PORT = LOADING_PORT;
/*     */   }
/*     */ 
/*     */   public String getLOADING_PORT_DESC() {
/* 346 */     return this.LOADING_PORT_DESC;
/*     */   }
/*     */ 
/*     */   public void setLOADING_PORT_DESC(String LOADING_PORT_DESC) {
/* 350 */     this.LOADING_PORT_DESC = LOADING_PORT_DESC;
/*     */   }
/*     */ 
/*     */   public String getAgent()
/*     */   {
/* 356 */     return this.agent;
/*     */   }
/*     */ 
/*     */   public void setAgent(String agent) {
/* 360 */     this.agent = agent;
/*     */   }
/*     */ 
/*     */   public String getFwd_code() {
/* 364 */     return this.fwd_code;
/*     */   }
/*     */ 
/*     */   public void setFwd_code(String fwd_code) {
/* 368 */     this.fwd_code = fwd_code;
/*     */   }
/*     */ 
/*     */   public String getNotify() {
/* 372 */     return this.notify;
/*     */   }
/*     */ 
/*     */   public void setNotify(String notify) {
/* 376 */     this.notify = notify;
/*     */   }
/*     */ 
/*     */   public String getNOTIFY_NAME() {
/* 380 */     return this.NOTIFY_NAME;
/*     */   }
/*     */ 
/*     */   public void setNOTIFY_NAME(String NOTIFY_NAME) {
/* 384 */     this.NOTIFY_NAME = NOTIFY_NAME;
/*     */   }
/*     */ 
/*     */   public String getCHA_NAME() {
/* 388 */     return this.CHA_NAME;
/*     */   }
/*     */ 
/*     */   public void setCHA_NAME(String CHA_NAME) {
/* 392 */     this.CHA_NAME = CHA_NAME;
/*     */   }
/*     */ 
/*     */   public String getFWD_NAME() {
/* 396 */     return this.FWD_NAME;
/*     */   }
/*     */ 
/*     */   public void setFWD_NAME(String FWD_NAME) {
/* 400 */     this.FWD_NAME = FWD_NAME;
/*     */   }
/*     */ 
/*     */   public String getDESTI_CNTRY() {
/* 404 */     return this.DESTI_CNTRY;
/*     */   }
/*     */ 
/*     */   public void setDESTI_CNTRY(String DESTI_CNTRY) {
/* 408 */     this.DESTI_CNTRY = DESTI_CNTRY;
/*     */   }
/*     */ 
/*     */   public String getDESTI_CNTRY_DESC() {
/* 412 */     return this.DESTI_CNTRY_DESC;
/*     */   }
/*     */ 
/*     */   public void setDESTI_CNTRY_DESC(String DESTI_CNTRY_DESC) {
/* 416 */     this.DESTI_CNTRY_DESC = DESTI_CNTRY_DESC;
/*     */   }
/*     */ 
/*     */   public String getDIS_CNTRY() {
/* 420 */     return this.DIS_CNTRY;
/*     */   }
/*     */ 
/*     */   public void setDIS_CNTRY(String DIS_CNTRY) {
/* 424 */     this.DIS_CNTRY = DIS_CNTRY;
/*     */   }
/*     */ 
/*     */   public String getDIS_CNTRY_DESC() {
/* 428 */     return this.DIS_CNTRY_DESC;
/*     */   }
/*     */ 
/*     */   public void setDIS_CNTRY_DESC(String DIS_CNTRY_DESC) {
/* 432 */     this.DIS_CNTRY_DESC = DIS_CNTRY_DESC;
/*     */   }
/*     */ 
/*     */   public String getDISCHARGE() {
/* 436 */     return this.DISCHARGE;
/*     */   }
/*     */ 
/*     */   public void setDISCHARGE(String DISCHARGE) {
/* 440 */     this.DISCHARGE = DISCHARGE;
/*     */   }
/*     */ 
/*     */   public String getDISCHARGE_DESC() {
/* 444 */     return this.DISCHARGE_DESC;
/*     */   }
/*     */ 
/*     */   public void setDISCHARGE_DESC(String DISCHARGE_DESC) {
/* 448 */     this.DISCHARGE_DESC = DISCHARGE_DESC;
/*     */   }
/*     */ 
/*     */   public String getDESTI_CODE() {
/* 452 */     return this.DESTI_CODE;
/*     */   }
/*     */ 
/*     */   public void setDESTI_CODE(String DESTI_CODE) {
/* 456 */     this.DESTI_CODE = DESTI_CODE;
/*     */   }
/*     */ 
/*     */   public String getDESTI_CODE_DESC() {
/* 460 */     return this.DESTI_CODE_DESC;
/*     */   }
/*     */ 
/*     */   public void setDESTI_CODE_DESC(String DESTI_CODE_DESC) {
/* 464 */     this.DESTI_CODE_DESC = DESTI_CODE_DESC;
/*     */   }
/*     */ 
/*     */   public String getCNTRY_ORIGIN() {
/* 468 */     return this.CNTRY_ORIGIN;
/*     */   }
/*     */ 
/*     */   public void setCNTRY_ORIGIN(String CNTRY_ORIGIN) {
/* 472 */     this.CNTRY_ORIGIN = CNTRY_ORIGIN;
/*     */   }
/*     */ 
/*     */   public String getTransport_cost() {
/* 476 */     return this.transport_cost;
/*     */   }
/*     */ 
/*     */   public void setTransport_cost(String transport_cost) {
/* 480 */     this.transport_cost = transport_cost;
/*     */   }
/*     */ 
/*     */   public String getCNTRY_ORIGIN_DESC() {
/* 484 */     return this.CNTRY_ORIGIN_DESC;
/*     */   }
/*     */ 
/*     */   public void setCNTRY_ORIGIN_DESC(String CNTRY_ORIGIN_DESC) {
/* 488 */     this.CNTRY_ORIGIN_DESC = CNTRY_ORIGIN_DESC;
/*     */   }
/*     */ 
/*     */   public String getPLACE() {
/* 492 */     return this.PLACE;
/*     */   }
/*     */ 
/*     */   public void setPLACE(String PLACE) {
/* 496 */     this.PLACE = PLACE;
/*     */   }
/*     */ 
/*     */   public String getPLACE_DESC() {
/* 500 */     return this.PLACE_DESC;
/*     */   }
/*     */ 
/*     */   public void setPLACE_DESC(String PLACE_DESC) {
/* 504 */     this.PLACE_DESC = PLACE_DESC;
/*     */   }
/*     */ 
/*     */   public String getSHIP_DESC() {
/* 508 */     return this.SHIP_DESC;
/*     */   }
/*     */ 
/*     */   public void setSHIP_DESC(String SHIP_DESC) {
/* 512 */     this.SHIP_DESC = SHIP_DESC;
/*     */   }
/*     */ 
/*     */   public String getCRNCY_CODE() {
/* 516 */     return this.CRNCY_CODE;
/*     */   }
/*     */ 
/*     */   public void setCRNCY_CODE(String CRNCY_CODE) {
/* 520 */     this.CRNCY_CODE = CRNCY_CODE;
/*     */   }
/*     */ 
/*     */   public String getMANUF_STATE() {
/* 524 */     return this.MANUF_STATE;
/*     */   }
/*     */ 
/*     */   public void setMANUF_STATE(String MANUF_STATE) {
/* 528 */     this.MANUF_STATE = MANUF_STATE;
/*     */   }
/*     */ 
/*     */   public String getMANUF_CODE() {
/* 532 */     return this.MANUF_CODE;
/*     */   }
/*     */ 
/*     */   public void setMANUF_CODE(String MANUF_CODE) {
/* 536 */     this.MANUF_CODE = MANUF_CODE;
/*     */   }
/*     */ 
/*     */   public String getMANUF_DESC() {
/* 540 */     return this.MANUF_DESC;
/*     */   }
/*     */ 
/*     */   public void setMANUF_DESC(String MANUF_DESC) {
/* 544 */     this.MANUF_DESC = MANUF_DESC;
/*     */   }
/*     */ 
/*     */   public String getTAX_TYPE() {
/* 548 */     return this.TAX_TYPE;
/*     */   }
/*     */ 
/*     */   public void setTAX_TYPE(String TAX_TYPE) {
/* 552 */     this.TAX_TYPE = TAX_TYPE;
/*     */   }
/*     */ 
/*     */   public Double getTAX_PERCENT() {
/* 556 */     return this.TAX_PERCENT;
/*     */   }
/*     */ 
/*     */   public void setTAX_PERCENT(Double TAX_PERCENT) {
/* 560 */     this.TAX_PERCENT = TAX_PERCENT;
/*     */   }
/*     */ 
/*     */   public String getTAX_CODE()
/*     */   {
/* 566 */     return this.TAX_CODE;
/*     */   }
/*     */ 
/*     */   public void setTAX_CODE(String TAX_CODE) {
/* 570 */     this.TAX_CODE = TAX_CODE;
/*     */   }
/*     */ 
/*     */   public Double getTAX_CAL_PER() {
/* 574 */     return this.TAX_CAL_PER;
/*     */   }
/*     */ 
/*     */   public void setTAX_CAL_PER(Double TAX_CAL_PER) {
/* 578 */     this.TAX_CAL_PER = TAX_CAL_PER;
/*     */   }
/*     */ 
/*     */   public String getShip_term()
/*     */   {
/* 584 */     return this.ship_term;
/*     */   }
/*     */ 
/*     */   public void setShip_term(String ship_term) {
/* 588 */     this.ship_term = ship_term;
/*     */   }
/*     */ 
/*     */   public String getPayment_term() {
/* 592 */     return this.payment_term;
/*     */   }
/*     */ 
/*     */   public void setPayment_term(String payment_term) {
/* 596 */     this.payment_term = payment_term;
/*     */   }
/*     */ 
/*     */   public String getPay_term() {
/* 600 */     return this.pay_term;
/*     */   }
/*     */ 
/*     */   public void setPay_term(String pay_term) {
/* 604 */     this.pay_term = pay_term;
/*     */   }
/*     */ 
/*     */   public String getShip_cancel() {
/* 608 */     return this.ship_cancel;
/*     */   }
/*     */ 
/*     */   public void setShip_cancel(String ship_cancel) {
/* 612 */     this.ship_cancel = ship_cancel;
/*     */   }
/*     */ 
/*     */   public String getPay_term_desc() {
/* 616 */     return this.pay_term_desc;
/*     */   }
/*     */ 
/*     */   public void setPay_term_desc(String pay_term_desc) {
/* 620 */     this.pay_term_desc = pay_term_desc;
/*     */   }
/*     */ 
/*     */   public List getBPOLIST() {
/* 624 */     return this.BPOLIST;
/*     */   }
/*     */ 
/*     */   public void setBPOLIST(List BPOLIST) {
/* 628 */     this.BPOLIST = BPOLIST;
/*     */   }
/*     */ 
/*     */   public List getINVLINELIST() {
/* 632 */     return this.INVLINELIST;
/*     */   }
/*     */ 
/*     */   public void setINVLINELIST(List INVLINELIST) {
/* 636 */     this.INVLINELIST = INVLINELIST;
/*     */   }
/*     */ 
/*     */   public List getUNITLIST() {
/* 640 */     return this.UNITLIST;
/*     */   }
/*     */ 
/*     */   public void setUNITLIST(List UNITLIST) {
/* 644 */     this.UNITLIST = UNITLIST;
/*     */   }
/*     */ 
/*     */   public List getSTYLIST() {
/* 648 */     return this.STYLIST;
/*     */   }
/*     */ 
/*     */   public void setSTYLIST(List STYLIST) {
/* 652 */     this.STYLIST = STYLIST;
/*     */   }
/*     */ 
/*     */   public List getDBKLIST() {
/* 656 */     return this.DBKLIST;
/*     */   }
/*     */ 
/*     */   public void setDBKLIST(List DBKLIST) {
/* 660 */     this.DBKLIST = DBKLIST;
/*     */   }
/*     */ 
/*     */   public List getSTRLIST() {
/* 664 */     return this.STRLIST;
/*     */   }
/*     */ 
/*     */   public void setSTRLIST(List STRLIST) {
/* 668 */     this.STRLIST = STRLIST;
/*     */   }
/*     */ 
/*     */   public List getSTRMISCLIST() {
/* 672 */     return this.STRMISCLIST;
/*     */   }
/*     */ 
/*     */   public void setSTRMISCLIST(List STRMISCLIST) {
/* 676 */     this.STRMISCLIST = STRMISCLIST;
/*     */   }
/*     */ 
/*     */   public List getHNGRLIST() {
/* 680 */     return this.HNGRLIST;
/*     */   }
/*     */ 
/*     */   public void setHNGRLIST(List HNGRLIST) {
/* 684 */     this.HNGRLIST = HNGRLIST;
/*     */   }
/*     */ 
/*     */   public List getACCRLIST() {
/* 688 */     return this.ACCRLIST;
/*     */   }
/*     */ 
/*     */   public void setACCRLIST(List ACCRLIST) {
/* 692 */     this.ACCRLIST = ACCRLIST;
/*     */   }
/*     */ 
/*     */   public List getACCRDBKLIST() {
/* 696 */     return this.ACCRDBKLIST;
/*     */   }
/*     */ 
/*     */   public void setACCRDBKLIST(List ACCRDBKLIST) {
/* 700 */     this.ACCRDBKLIST = ACCRDBKLIST;
/*     */   }
/*     */ 
/*     */   public List getACCRSTRLIST() {
/* 704 */     return this.ACCRSTRLIST;
/*     */   }
/*     */ 
/*     */   public void setACCRSTRLIST(List ACCRSTRLIST) {
/* 708 */     this.ACCRSTRLIST = ACCRSTRLIST;
/*     */   }
/*     */ 
/*     */   public List getBELIST() {
/* 712 */     return this.BELIST;
/*     */   }
/*     */ 
/*     */   public void setBELIST(List BELIST) {
/* 716 */     this.BELIST = BELIST;
/*     */   }
/*     */ 
/*     */   public String getYEAR()
/*     */   {
/* 723 */     return this.YEAR;
/*     */   }
/*     */ 
/*     */   public void setYEAR(String YEAR) {
/* 727 */     this.YEAR = YEAR;
/*     */   }
/*     */ 
/*     */   public String getCOMPANY() {
/* 731 */     return this.COMPANY;
/*     */   }
/*     */ 
/*     */   public void setCOMPANY(String COMPANY) {
/* 735 */     this.COMPANY = COMPANY;
/*     */   }
/*     */ 
/*     */   public String getINV_NO() {
/* 739 */     return this.INV_NO;
/*     */   }
/*     */ 
/*     */   public void setINV_NO(String INV_NO) {
/* 743 */     this.INV_NO = INV_NO;
/*     */   }
/*     */ 
/*     */   public String getINVDATE() {
/* 747 */     return this.INVDATE;
/*     */   }
/*     */ 
/*     */   public void setINVDATE(String INVDATE) {
/* 751 */     this.INVDATE = INVDATE;
/*     */   }
/*     */ 
/*     */   public String getLIC_DECL_TEXT()
/*     */   {
/* 757 */     return this.LIC_DECL_TEXT;
/*     */   }
/*     */ 
/*     */   public void setLIC_DECL_TEXT(String LIC_DECL_TEXT) {
/* 761 */     this.LIC_DECL_TEXT = LIC_DECL_TEXT;
/*     */   }
/*     */ 
/*     */   public String getMANUF_ADDRESS() {
/* 765 */     return this.MANUF_ADDRESS;
/*     */   }
/*     */ 
/*     */   public void setMANUF_ADDRESS(String MANUF_ADDRESS) {
/* 769 */     this.MANUF_ADDRESS = MANUF_ADDRESS;
/*     */   }
/*     */ 
/*     */   public String getNOTIFY_ADDRESS() {
/* 773 */     return this.NOTIFY_ADDRESS;
/*     */   }
/*     */ 
/*     */   public double getTFOB() {
/* 777 */     return this.TFOB;
/*     */   }
/*     */ 
/*     */   public void setTFOB(double TFOB) {
/* 781 */     this.TFOB = TFOB;
/*     */   }
/*     */ 
/*     */   public double getTNETWT() {
/* 785 */     return this.TNETWT;
/*     */   }
/*     */ 
/*     */   public void setTNETWT(double TNETWT) {
/* 789 */     this.TNETWT = TNETWT;
/*     */   }
/*     */ 
/*     */   public int getTINVQTY() {
/* 793 */     return this.TINVQTY;
/*     */   }
/*     */ 
/*     */   public void setTINVQTY(int TINVQTY) {
/* 797 */     this.TINVQTY = TINVQTY;
/*     */   }
/*     */ 
/*     */   public void setNOTIFY_ADDRESS(String NOTIFY_ADDRESS)
/*     */   {
/* 803 */     this.NOTIFY_ADDRESS = NOTIFY_ADDRESS;
/*     */   }
/*     */ 
/*     */   public String getExp_type_desc() {
/* 807 */     return this.exp_type_desc;
/*     */   }
/*     */ 
/*     */   public void setExp_type_desc(String exp_type_desc) {
/* 811 */     this.exp_type_desc = exp_type_desc;
/*     */   }
/*     */ 
/*     */   public String getFwd_custom() {
/* 815 */     return this.fwd_custom;
/*     */   }
/*     */ 
/*     */   public void setFwd_custom(String fwd_custom) {
/* 819 */     this.fwd_custom = fwd_custom;
/*     */   }
/*     */ 
/*     */   public String getERR_MSG() {
/* 823 */     return this.ERR_MSG;
/*     */   }
/*     */ 
/*     */   public void setERR_MSG(String ERR_MSG) {
/* 827 */     this.ERR_MSG = ERR_MSG;
/*     */   }
/*     */ 
/*     */   public String getMSG1() {
/* 831 */     return this.MSG1;
/*     */   }
/*     */ 
/*     */   public void setMSG1(String MSG1) {
/* 835 */     this.MSG1 = MSG1;
/*     */   }
/*     */ 
/*     */   public String getMSG2() {
/* 839 */     return this.MSG2;
/*     */   }
/*     */ 
/*     */   public void setMSG2(String MSG2) {
/* 843 */     this.MSG2 = MSG2;
/*     */   }
/*     */ 
/*     */   public double getTMISCINR()
/*     */   {
/* 849 */     return this.TMISCINR;
/*     */   }
/*     */ 
/*     */   public void setTMISCINR(double TMISCINR) {
/* 853 */     this.TMISCINR = TMISCINR;
/*     */   }
/*     */ 
/*     */   public int getTINR()
/*     */   {
/* 859 */     return this.TINR;
/*     */   }
/*     */ 
/*     */   public void setTINR(int TINR) {
/* 863 */     this.TINR = TINR;
/*     */   }
/*     */ 
/*     */   public double getTGRDECL() {
/* 867 */     return this.TGRDECL;
/*     */   }
/*     */ 
/*     */   public void setTGRDECL(double TGRDECL) {
/* 871 */     this.TGRDECL = TGRDECL;
/*     */   }
/*     */ 
/*     */   public double getGRPER() {
/* 875 */     return this.GRPER;
/*     */   }
/*     */ 
/*     */   public void setGRPER(double GRPER) {
/* 879 */     this.GRPER = GRPER;
/*     */   }
/*     */ 
/*     */   public double getINV_FC() {
/* 883 */     return this.INV_FC;
/*     */   }
/*     */ 
/*     */   public void setINV_FC(double INV_FC) {
/* 887 */     this.INV_FC = INV_FC;
/*     */   }
/*     */ 
/*     */   public String getAMT_IN_WORD() {
/* 891 */     return this.AMT_IN_WORD;
/*     */   }
/*     */ 
/*     */   public void setAMT_IN_WORD(String AMT_IN_WORD) {
/* 895 */     this.AMT_IN_WORD = AMT_IN_WORD;
/*     */   }
/*     */ 
/*     */   public double getTDBKINR() {
/* 899 */     return this.TDBKINR;
/*     */   }
/*     */ 
/*     */   public void setTDBKINR(double TDBKINR) {
/* 903 */     this.TDBKINR = TDBKINR;
/*     */   }
/*     */ 
/*     */   public int getCHKDBK() {
/* 907 */     return this.CHKDBK;
/*     */   }
/*     */ 
/*     */   public void setCHKDBK(int CHKDBK) {
/* 911 */     this.CHKDBK = CHKDBK;
/*     */   }
/*     */ 
/*     */   public int getCHKDBKACCR() {
/* 915 */     return this.CHKDBKACCR;
/*     */   }
/*     */ 
/*     */   public void setCHKDBKACCR(int CHKDBKACCR) {
/* 919 */     this.CHKDBKACCR = CHKDBKACCR;
/*     */   }
/*     */ 
/*     */   public int getCHKSTRACCR() {
/* 923 */     return this.CHKSTRACCR;
/*     */   }
/*     */ 
/*     */   public void setCHKSTRACCR(int CHKSTRACCR) {
/* 927 */     this.CHKSTRACCR = CHKSTRACCR;
/*     */   }
/*     */ 
/*     */   public int getCHKBE() {
/* 931 */     return this.CHKBE;
/*     */   }
/*     */ 
/*     */   public void setCHKBE(int CHKBE) {
/* 935 */     this.CHKBE = CHKBE;
/*     */   }
/*     */ 
/*     */   public String getCTNS() {
/* 939 */     return this.CTNS;
/*     */   }
/*     */ 
/*     */   public void setCTNS(String CTNS) {
/* 943 */     this.CTNS = CTNS;
/*     */   }
/*     */ 
/*     */   public String getFACILITY() {
/* 947 */     return this.FACILITY;
/*     */   }
/*     */ 
/*     */   public void setFACILITY(String FACILITY) {
/* 951 */     this.FACILITY = FACILITY;
/*     */   }
/*     */ 
/*     */   public String getMANUF_CST() {
/* 955 */     return this.MANUF_CST;
/*     */   }
/*     */ 
/*     */   public void setMANUF_CST(String MANUF_CST) {
/* 959 */     this.MANUF_CST = MANUF_CST;
/*     */   }
/*     */ 
/*     */   public String getMANUF_TIN() {
/* 963 */     return this.MANUF_TIN;
/*     */   }
/*     */ 
/*     */   public void setMANUF_TIN(String MANUF_TIN) {
/* 967 */     this.MANUF_TIN = MANUF_TIN;
/*     */   }
/*     */ 
/*     */   public double getUP_AMT() {
/* 971 */     return this.UP_AMT;
/*     */   }
/*     */ 
/*     */   public void setUP_AMT(double UP_AMT) {
/* 975 */     this.UP_AMT = UP_AMT;
/*     */   }
/*     */ 
/*     */   public Double getTAXABLE_VALUE() {
/* 979 */     return this.TAXABLE_VALUE;
/*     */   }
/*     */ 
/*     */   public void setTAXABLE_VALUE(Double TAXABLE_VALUE) {
/* 983 */     this.TAXABLE_VALUE = TAXABLE_VALUE;
/*     */   }
/*     */ 
/*     */   public Double getTAX_AMT() {
/* 987 */     return this.TAX_AMT;
/*     */   }
/*     */ 
/*     */   public void setTAX_AMT(Double TAX_AMT) {
/* 991 */     this.TAX_AMT = TAX_AMT;
/*     */   }

/*     */ 

    public String getLUT_IGST() {
        return LUT_IGST;
    }

    public void setLUT_IGST(String LUT_IGST) {
        this.LUT_IGST = LUT_IGST;
    }
 }

 