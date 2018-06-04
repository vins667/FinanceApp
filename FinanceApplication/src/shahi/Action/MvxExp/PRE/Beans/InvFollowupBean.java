/*     */ package shahi.Action.MvxExp.PRE.Beans;
/*     */ 
/*     */ public class InvFollowupBean
/*     */ {
/*     */   private String EXCS_INV_NO;
/*     */   private String TTO_DATE;
/*     */   private String TO_DATE;
/*     */   private String ETD_DATE;
/*     */   private String PCH;
/*     */   private String MOS;
/*     */   private String BUYER;
/*     */   private String PORT;
/*     */   private String PLAN_NO;
/*     */   private String INV_QTY;
/*     */   private String ERRMSG;
            private String PRE_DOCS;
            private String DESTI_CNTRY;
/*     */ 
/*     */   public InvFollowupBean(String EXCS_INV_NO, String TTO_DATE, String TO_DATE, String ETD_DATE, String PCH, String MOS, String BUYER, String PORT, String PLAN_NO, String INV_QTY, String ERRMSG,String PRE_DOCS,String DESTI_CNTRY)
/*     */   {
/*  25 */     this.EXCS_INV_NO = EXCS_INV_NO;
/*  26 */     this.TTO_DATE = TTO_DATE;
/*  27 */     this.TO_DATE = TO_DATE;
/*  28 */     this.ETD_DATE = ETD_DATE;
/*  29 */     this.PCH = PCH;
/*  30 */     this.MOS = MOS;
/*  31 */     this.BUYER = BUYER;
/*  32 */     this.PORT = PORT;
/*  33 */     this.PLAN_NO = PLAN_NO;
/*  34 */     this.INV_QTY = INV_QTY;
/*  35 */     this.ERRMSG = ERRMSG;
              this.PRE_DOCS=PRE_DOCS;
              this.DESTI_CNTRY=DESTI_CNTRY;
/*     */   }
/*     */ 
/*     */   public String getEXCS_INV_NO() {
/*  39 */     return this.EXCS_INV_NO;
/*     */   }
/*     */ 
/*     */   public void setEXCS_INV_NO(String EXCS_INV_NO) {
/*  43 */     this.EXCS_INV_NO = EXCS_INV_NO;
/*     */   }
/*     */ 
/*     */   public String getTTO_DATE() {
/*  47 */     return this.TTO_DATE;
/*     */   }
/*     */ 
/*     */   public void setTTO_DATE(String TTO_DATE) {
/*  51 */     this.TTO_DATE = TTO_DATE;
/*     */   }
/*     */ 
/*     */   public String getTO_DATE() {
/*  55 */     return this.TO_DATE;
/*     */   }
/*     */ 
/*     */   public void setTO_DATE(String TO_DATE) {
/*  59 */     this.TO_DATE = TO_DATE;
/*     */   }
/*     */ 
/*     */   public String getETD_DATE() {
/*  63 */     return this.ETD_DATE;
/*     */   }
/*     */ 
/*     */   public void setETD_DATE(String ETD_DATE) {
/*  67 */     this.ETD_DATE = ETD_DATE;
/*     */   }
/*     */ 
/*     */   public String getPCH() {
/*  71 */     return this.PCH;
/*     */   }
/*     */ 
/*     */   public void setPCH(String PCH) {
/*  75 */     this.PCH = PCH;
/*     */   }
/*     */ 
/*     */   public String getMOS() {
/*  79 */     return this.MOS;
/*     */   }
/*     */ 
/*     */   public void setMOS(String MOS) {
/*  83 */     this.MOS = MOS;
/*     */   }
/*     */ 
/*     */   public String getBUYER() {
/*  87 */     return this.BUYER;
/*     */   }
/*     */ 
/*     */   public void setBUYER(String BUYER) {
/*  91 */     this.BUYER = BUYER;
/*     */   }
/*     */ 
/*     */   public String getPORT() {
/*  95 */     return this.PORT;
/*     */   }
/*     */ 
/*     */   public void setPORT(String PORT) {
/*  99 */     this.PORT = PORT;
/*     */   }
/*     */ 
/*     */   public String getPLAN_NO() {
/* 103 */     return this.PLAN_NO;
/*     */   }
/*     */ 
/*     */   public void setPLAN_NO(String PLAN_NO) {
/* 107 */     this.PLAN_NO = PLAN_NO;
/*     */   }
/*     */ 
/*     */   public String getINV_QTY() {
/* 111 */     return this.INV_QTY;
/*     */   }
/*     */ 
/*     */   public void setINV_QTY(String INV_QTY) {
/* 115 */     this.INV_QTY = INV_QTY;
/*     */   }
/*     */ 
/*     */   public String getERRMSG() {
/* 119 */     return this.ERRMSG;
/*     */   }
/*     */ 
/*     */   public void setERRMSG(String ERRMSG) {
/* 123 */     this.ERRMSG = ERRMSG;
/*     */   }

    public String getDESTI_CNTRY() {
        return DESTI_CNTRY;
    }

    public void setDESTI_CNTRY(String DESTI_CNTRY) {
        this.DESTI_CNTRY = DESTI_CNTRY;
    }

/*     */

    public String getPRE_DOCS() {
        return PRE_DOCS;
    }

    public void setPRE_DOCS(String PRE_DOCS) {
        this.PRE_DOCS = PRE_DOCS;
    }
 } 

/* Location:           D:\SHAHIPROJECTNEW\ShahiApplication\build\web\WEB-INF\classes\
 * Qualified Name:     shahi.Action.MvxExp.PRE.Beans.InvFollowupBean
 * JD-Core Version:    0.6.0
 */