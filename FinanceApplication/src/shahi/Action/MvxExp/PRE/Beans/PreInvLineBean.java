/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;
import java.math.BigDecimal;



public class PreInvLineBean {
    private String SR_NO;
    private String CO_NO;
    private String CO_LINE; 
    private String ITEM_NO;
    private String UNIT;
    private Double QTY_ENDORS;
    private Double QTY_KGS;
    private BigDecimal PRICE_FC;
    private BigDecimal PRICE_MISC;
    private BigDecimal ADJUST_FC;
    private BigDecimal NET_PRICE;
    private Double FOB_FC;
    private BigDecimal GR_DECL_AMT;
    private Double GR_DECL_PER;
    private String DBK_SLNO;
    private String STR_SLNO;
    private String STR_MISC;
    private String PRE_PRINT_NO;
    private String TOKEN_NO;
    private String CATG_CODE;
    private String INV_DESC;
    private String MADE_FOR;
    private BigDecimal MOVEX_PRICE;
    private String TEMP_CAT;
    private String PLAN_DESC;
    private String ROSL_SLNO;
    private String SCHEME_CODE;
    private String HSCODE1;
    private BigDecimal HNGR_COST;
    private BigDecimal TAG_COST;
    private String HSN_CODE;
    private Double IGST_PER;
    private Double CGST_PER;
    private Double SGST_PER;
    
    private String exciseUnit;
    
    private String exInvoiceSNo;
    private String exInvoiceDate;
    
    public PreInvLineBean(){
    	
    }
    public PreInvLineBean(String SR_NO,String CO_NO, String CO_LINE, String ITEM_NO, String UNIT, Double QTY_ENDORS, Double QTY_KGS, BigDecimal PRICE_FC, BigDecimal PRICE_MISC, BigDecimal ADJUST_FC, BigDecimal NET_PRICE, Double FOB_FC, BigDecimal GR_DECL_AMT,Double GR_DECL_PER, String DBK_SLNO, 
                          String STR_SLNO, String STR_MISC, String PRE_PRINT_NO, String TOKEN_NO, String CATG_CODE, String INV_DESC,String MADE_FOR,BigDecimal MOVEX_PRICE,String TEMP_CAT,String PLAN_DESC,String ROSL_SLNO,String SCHEME_CODE,String HSCODE1,BigDecimal HNGR_COST,BigDecimal TAG_COST,
                          String HSN_CODE,Double IGST_PER,Double CGST_PER,Double SGST_PER) {
        this.SR_NO=SR_NO;
        this.CO_NO = CO_NO;
        this.CO_LINE = CO_LINE;
        this.ITEM_NO = ITEM_NO;
        this.UNIT = UNIT;
        this.QTY_ENDORS = QTY_ENDORS;
        this.QTY_KGS = QTY_KGS;
        this.PRICE_FC = PRICE_FC;
        this.PRICE_MISC = PRICE_MISC;
        this.ADJUST_FC = ADJUST_FC;
        this.NET_PRICE = NET_PRICE;
        this.FOB_FC = FOB_FC;
        this.GR_DECL_AMT = GR_DECL_AMT;
        this.GR_DECL_PER=GR_DECL_PER;
        this.DBK_SLNO = DBK_SLNO;
        this.STR_SLNO = STR_SLNO;
        this.STR_MISC = STR_MISC;
        this.PRE_PRINT_NO = PRE_PRINT_NO;
        this.TOKEN_NO = TOKEN_NO;
        this.CATG_CODE = CATG_CODE;
        this.INV_DESC = INV_DESC;
        this.MADE_FOR=MADE_FOR;
        this.MOVEX_PRICE=MOVEX_PRICE;
        this.TEMP_CAT=TEMP_CAT;
        this.PLAN_DESC=PLAN_DESC;
        this.ROSL_SLNO=ROSL_SLNO;
        this.SCHEME_CODE=SCHEME_CODE;
        this.HSCODE1=HSCODE1;
        this.HNGR_COST=HNGR_COST;
        this.TAG_COST=TAG_COST;
        this.HSN_CODE=HSN_CODE;
        this.SGST_PER=SGST_PER;
        this.IGST_PER=IGST_PER;
        this.CGST_PER=CGST_PER;
              
    }

   

    public PreInvLineBean(String sR_NO, String cO_NO, String cO_LINE, String iTEM_NO, String uNIT, Double qTY_ENDORS,
			Double qTY_KGS, BigDecimal pRICE_FC, BigDecimal pRICE_MISC, BigDecimal aDJUST_FC, BigDecimal nET_PRICE,
			Double fOB_FC, BigDecimal gR_DECL_AMT, Double gR_DECL_PER, String dBK_SLNO, String sTR_SLNO,
			String sTR_MISC, String pRE_PRINT_NO, String tOKEN_NO, String cATG_CODE, String iNV_DESC, String mADE_FOR,
			BigDecimal mOVEX_PRICE, String tEMP_CAT, String pLAN_DESC, String rOSL_SLNO, String sCHEME_CODE,
			String hSCODE1, BigDecimal hNGR_COST, BigDecimal tAG_COST, String hSN_CODE, Double iGST_PER,
			Double cGST_PER, Double sGST_PER, String exciseUnit, String exInvoiceSNo, String exInvoiceDate) {
		super();
		SR_NO = sR_NO;
		CO_NO = cO_NO;
		CO_LINE = cO_LINE;
		ITEM_NO = iTEM_NO;
		UNIT = uNIT;
		QTY_ENDORS = qTY_ENDORS;
		QTY_KGS = qTY_KGS;
		PRICE_FC = pRICE_FC;
		PRICE_MISC = pRICE_MISC;
		ADJUST_FC = aDJUST_FC;
		NET_PRICE = nET_PRICE;
		FOB_FC = fOB_FC;
		GR_DECL_AMT = gR_DECL_AMT;
		GR_DECL_PER = gR_DECL_PER;
		DBK_SLNO = dBK_SLNO;
		STR_SLNO = sTR_SLNO;
		STR_MISC = sTR_MISC;
		PRE_PRINT_NO = pRE_PRINT_NO;
		TOKEN_NO = tOKEN_NO;
		CATG_CODE = cATG_CODE;
		INV_DESC = iNV_DESC;
		MADE_FOR = mADE_FOR;
		MOVEX_PRICE = mOVEX_PRICE;
		TEMP_CAT = tEMP_CAT;
		PLAN_DESC = pLAN_DESC;
		ROSL_SLNO = rOSL_SLNO;
		SCHEME_CODE = sCHEME_CODE;
		HSCODE1 = hSCODE1;
		HNGR_COST = hNGR_COST;
		TAG_COST = tAG_COST;
		HSN_CODE = hSN_CODE;
		IGST_PER = iGST_PER;
		CGST_PER = cGST_PER;
		SGST_PER = sGST_PER;
		this.exciseUnit = exciseUnit;
		this.exInvoiceSNo = exInvoiceSNo;
		this.exInvoiceDate = exInvoiceDate;
	}
	public String getCATG_CODE() {
        return CATG_CODE;
    }

    public void setCATG_CODE(String CATG_CODE) {
        this.CATG_CODE = CATG_CODE;
    }

    

    public String getCO_LINE() {
        return CO_LINE;
    }

    public void setCO_LINE(String CO_LINE) {
        this.CO_LINE = CO_LINE;
    }

    public String getCO_NO() {
        return CO_NO;
    }

    public void setCO_NO(String CO_NO) {
        this.CO_NO = CO_NO;
    }

    public String getDBK_SLNO() {
        return DBK_SLNO;
    }

    public void setDBK_SLNO(String DBK_SLNO) {
        this.DBK_SLNO = DBK_SLNO;
    }

    public String getINV_DESC() {
        return INV_DESC;
    }

    public void setINV_DESC(String INV_DESC) {
        this.INV_DESC = INV_DESC;
    }

     
    public Double getFOB_FC() {
        return FOB_FC;
    }

    public void setFOB_FC(Double FOB_FC) {
        this.FOB_FC = FOB_FC;
    }

    public BigDecimal getGR_DECL_AMT() {
        return GR_DECL_AMT;
    }

    public void setGR_DECL_AMT(BigDecimal GR_DECL_AMT) {
        this.GR_DECL_AMT = GR_DECL_AMT;
    }

    

    public String getITEM_NO() {
        return ITEM_NO;
    }

    public void setITEM_NO(String ITEM_NO) {
        this.ITEM_NO = ITEM_NO;
    }

    public String getSTR_SLNO() {
        return STR_SLNO;
    }

    public void setSTR_SLNO(String STR_SLNO) {
        this.STR_SLNO = STR_SLNO;
    }

    public String getSTR_MISC() {
        return STR_MISC;
    }

    public void setSTR_MISC(String STR_MISC) {
        this.STR_MISC = STR_MISC;
    }

    public BigDecimal getNET_PRICE() {
        return NET_PRICE;
    }

    public void setNET_PRICE(BigDecimal NET_PRICE) {
        this.NET_PRICE = NET_PRICE;
    }

    

    public String getPRE_PRINT_NO() {
        return PRE_PRINT_NO;
    }

    public void setPRE_PRINT_NO(String PRE_PRINT_NO) {
        this.PRE_PRINT_NO = PRE_PRINT_NO;
    }

    
    public Double getQTY_ENDORS() {
        return QTY_ENDORS;
    }

    public void setQTY_ENDORS(Double QTY_ENDORS) {
        this.QTY_ENDORS = QTY_ENDORS;
    }

    public Double getQTY_KGS() {
        return QTY_KGS;
    }

    

    public String getTOKEN_NO() {
        return TOKEN_NO;
    }

    public void setTOKEN_NO(String TOKEN_NO) {
        this.TOKEN_NO = TOKEN_NO;
    }

    public String getUNIT() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    public Double getGR_DECL_PER() {
        return GR_DECL_PER;
    }

    public void setGR_DECL_PER(Double GR_DECL_PER) {
        this.GR_DECL_PER = GR_DECL_PER;
    }

    public String getSR_NO() {
        return SR_NO;
    }

    public void setSR_NO(String SR_NO) {
        this.SR_NO = SR_NO;
    }

    public String getMADE_FOR() {
        return MADE_FOR;
    }

    public void setMADE_FOR(String MADE_FOR) {
        this.MADE_FOR = MADE_FOR;
    }

    public BigDecimal getPRICE_FC() {
        return PRICE_FC;
    }

    public void setPRICE_FC(BigDecimal PRICE_FC) {
        this.PRICE_FC = PRICE_FC;
    }

    public BigDecimal getPRICE_MISC() {
        return PRICE_MISC;
    }

    public void setPRICE_MISC(BigDecimal PRICE_MISC) {
        this.PRICE_MISC = PRICE_MISC;
    }

    public BigDecimal getADJUST_FC() {
        return ADJUST_FC;
    }

    public void setADJUST_FC(BigDecimal ADJUST_FC) {
        this.ADJUST_FC = ADJUST_FC;
    }

    public BigDecimal getMOVEX_PRICE() {
        return MOVEX_PRICE;
    }

    public void setMOVEX_PRICE(BigDecimal MOVEX_PRICE) {
        this.MOVEX_PRICE = MOVEX_PRICE;
    }

    

    public String getTEMP_CAT() {
        return TEMP_CAT;
    }

    public void setTEMP_CAT(String TEMP_CAT) {
        this.TEMP_CAT = TEMP_CAT;
    }

    public String getPLAN_DESC() {
        return PLAN_DESC;
    }

    public void setPLAN_DESC(String PLAN_DESC) {
        this.PLAN_DESC = PLAN_DESC;
    }

    public String getROSL_SLNO() {
        return ROSL_SLNO;
    }

    public void setROSL_SLNO(String ROSL_SLNO) {
        this.ROSL_SLNO = ROSL_SLNO;
    }

    public String getHSCODE1() {
        return HSCODE1;
    }

    public void setHSCODE1(String HSCODE1) {
        this.HSCODE1 = HSCODE1;
    }

    public String getSCHEME_CODE() {
        return SCHEME_CODE;
    }

    public void setSCHEME_CODE(String SCHEME_CODE) {
        this.SCHEME_CODE = SCHEME_CODE;
    }

    public BigDecimal getHNGR_COST() {
        return HNGR_COST;
    }

    public void setHNGR_COST(BigDecimal HNGR_COST) {
        this.HNGR_COST = HNGR_COST;
    }

    public BigDecimal getTAG_COST() {
        return TAG_COST;
    }

    public void setTAG_COST(BigDecimal TAG_COST) {
        this.TAG_COST = TAG_COST;
    }

    public String getHSN_CODE() {
        return HSN_CODE;
    }

    public void setHSN_CODE(String HSN_CODE) {
        this.HSN_CODE = HSN_CODE;
    }
 
    public Double getIGST_PER() {
        return IGST_PER;
    }

    public void setIGST_PER(Double IGST_PER) {
        this.IGST_PER = IGST_PER;
    }

    public Double getCGST_PER() {
        return CGST_PER;
    }

    public void setCGST_PER(Double CGST_PER) {
        this.CGST_PER = CGST_PER;
    }

    public Double getSGST_PER() {
        return SGST_PER;
    }

    public void setSGST_PER(Double SGST_PER) {
        this.SGST_PER = SGST_PER;
    }
	public String getExciseUnit() {
		return exciseUnit;
	}
	public void setExciseUnit(String exciseUnit) {
		this.exciseUnit = exciseUnit;
	}
	public String getExInvoiceSNo() {
		return exInvoiceSNo;
	}
	public void setExInvoiceSNo(String exInvoiceSNo) {
		this.exInvoiceSNo = exInvoiceSNo;
	}
	public String getExInvoiceDate() {
		return exInvoiceDate;
	}
	public void setExInvoiceDate(String exInvoiceDate) {
		this.exInvoiceDate = exInvoiceDate;
	}
	public void setQTY_KGS(Double qTY_KGS) {
		QTY_KGS = qTY_KGS;
	}
         
       
}
 