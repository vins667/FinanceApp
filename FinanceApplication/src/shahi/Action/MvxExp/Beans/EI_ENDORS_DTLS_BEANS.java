package shahi.Action.MvxExp.Beans;

public class EI_ENDORS_DTLS_BEANS {
    private String CO_NO;
    private String CO_LINE;
    private String ITEM_NO;
    private String UNIT;
    private int QTY_ENDORS;
    private int QTY_KGS;
    private double PRICE_FC;
    private double PRICE_MISC;
    private double ADJUST_FC;
    private double NET_PRICE;
    private double FOB_FC;
    private double GR_DECL_AMT;
    private String DBK_SLNO;
    private String MRP_RATE;
    private String MRP_FOB;
    private String PRE_PRINT_NO;
    private String TOKEN_NO;
    private String CATEGORY;
    private String DESCRIPTION;

    public EI_ENDORS_DTLS_BEANS(String CO_NO, String CO_LINE, String ITEM_NO, String UNIT, int QTY_ENDORS, int QTY_KGS, double PRICE_FC, double PRICE_MISC, double ADJUST_FC, double NET_PRICE, double FOB_FC, double GR_DECL_AMT, String DBK_SLNO, String MRP_RATE, String MRP_FOB, String PRE_PRINT_NO, String TOKEN_NO, String CATEGORY, String DESCRIPTION) {
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
        this.DBK_SLNO = DBK_SLNO;
        this.MRP_RATE = MRP_RATE;
        this.MRP_FOB = MRP_FOB;
        this.PRE_PRINT_NO = PRE_PRINT_NO;
        this.TOKEN_NO = TOKEN_NO;
        this.CATEGORY = CATEGORY;
        this.DESCRIPTION = DESCRIPTION;
    }

    public double getADJUST_FC() {
        return ADJUST_FC;
    }

    public void setADJUST_FC(double ADJUST_FC) {
        this.ADJUST_FC = ADJUST_FC;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
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

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public double getFOB_FC() {
        return FOB_FC;
    }

    public void setFOB_FC(double FOB_FC) {
        this.FOB_FC = FOB_FC;
    }

    public double getGR_DECL_AMT() {
        return GR_DECL_AMT;
    }

    public void setGR_DECL_AMT(double GR_DECL_AMT) {
        this.GR_DECL_AMT = GR_DECL_AMT;
    }

    public String getITEM_NO() {
        return ITEM_NO;
    }

    public void setITEM_NO(String ITEM_NO) {
        this.ITEM_NO = ITEM_NO;
    }

    public String getMRP_FOB() {
        return MRP_FOB;
    }

    public void setMRP_FOB(String MRP_FOB) {
        this.MRP_FOB = MRP_FOB;
    }

    public String getMRP_RATE() {
        return MRP_RATE;
    }

    public void setMRP_RATE(String MRP_RATE) {
        this.MRP_RATE = MRP_RATE;
    }

    public double getNET_PRICE() {
        return NET_PRICE;
    }

    public void setNET_PRICE(double NET_PRICE) {
        this.NET_PRICE = NET_PRICE;
    }

    public String getPRE_PRINT_NO() {
        return PRE_PRINT_NO;
    }

    public void setPRE_PRINT_NO(String PRE_PRINT_NO) {
        this.PRE_PRINT_NO = PRE_PRINT_NO;
    }

    public double getPRICE_FC() {
        return PRICE_FC;
    }

    public void setPRICE_FC(double PRICE_FC) {
        this.PRICE_FC = PRICE_FC;
    }

    public double getPRICE_MISC() {
        return PRICE_MISC;
    }

    public void setPRICE_MISC(double PRICE_MISC) {
        this.PRICE_MISC = PRICE_MISC;
    }

    public int getQTY_ENDORS() {
        return QTY_ENDORS;
    }

    public void setQTY_ENDORS(int QTY_ENDORS) {
        this.QTY_ENDORS = QTY_ENDORS;
    }

    public int getQTY_KGS() {
        return QTY_KGS;
    }

    public void setQTY_KGS(int QTY_KGS) {
        this.QTY_KGS = QTY_KGS;
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
}
