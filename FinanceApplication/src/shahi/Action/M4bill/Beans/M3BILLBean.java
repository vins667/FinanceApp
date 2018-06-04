package shahi.Action.M4bill.Beans;

public class M3BILLBean
{
  private String SL_NO;
  private String TYPE_SL_NO;
  private String SUB_TYPE_CODE;
  private String SUB_TYPE_DESC;
  private String FLAG;
  private String EAAITM;
  private String EATX40;
  private String DELFLAG;
  private String DESC;
  private String PCH;
  private String BOS_DATE;
  private String BOS_LOCT;

  public M3BILLBean(String EAAITM)
  {
    this.EAAITM = EAAITM;
  }

  public M3BILLBean(String SL_NO, String SUB_TYPE_CODE, String SUB_TYPE_DESC, String FLAG)
  {
    this.SL_NO = SL_NO;
    this.SUB_TYPE_CODE = SUB_TYPE_CODE;
    this.SUB_TYPE_DESC = SUB_TYPE_DESC;
    this.FLAG = FLAG;
  }

  public M3BILLBean(String SL_NO, String TYPE_SL_NO, String SUB_TYPE_CODE, String SUB_TYPE_DESC, String FLAG) {
    this.SL_NO = SL_NO;
    this.TYPE_SL_NO = TYPE_SL_NO;
    this.SUB_TYPE_CODE = SUB_TYPE_CODE;
    this.SUB_TYPE_DESC = SUB_TYPE_DESC;
    this.FLAG = FLAG;
  }

  public M3BILLBean(String SL_NO, String TYPE_SL_NO, String SUB_TYPE_CODE, String SUB_TYPE_DESC, String FLAG, String DELFLAG) {
    this.SL_NO = SL_NO;
    this.TYPE_SL_NO = TYPE_SL_NO;
    this.SUB_TYPE_CODE = SUB_TYPE_CODE;
    this.SUB_TYPE_DESC = SUB_TYPE_DESC;
    this.FLAG = FLAG;
    this.DELFLAG = DELFLAG;
  }

  public M3BILLBean(String SL_NO, String TYPE_SL_NO, String SUB_TYPE_CODE, String SUB_TYPE_DESC, String FLAG, String DELFLAG, String DESC) {
    this.SL_NO = SL_NO;
    this.TYPE_SL_NO = TYPE_SL_NO;
    this.SUB_TYPE_CODE = SUB_TYPE_CODE;
    this.SUB_TYPE_DESC = SUB_TYPE_DESC;
    this.FLAG = FLAG;
    this.DELFLAG = DELFLAG;
    this.DESC = DESC;
  }

  public M3BILLBean(String EAAITM, String EATX40)
  {
    this.EAAITM = EAAITM;
    this.EATX40 = EATX40;
  }

  public M3BILLBean(String EAAITM, String EATX40, String DESC) {
    this.EAAITM = EAAITM;
    this.EATX40 = EATX40;
    this.DESC = DESC;
  }

  public M3BILLBean(String EAAITM, String EATX40, String DESC, String PCH, int a) {
    this.EAAITM = EAAITM;
    this.EATX40 = EATX40;
    this.DESC = DESC;
    this.PCH = PCH;
  }

  public M3BILLBean(String EAAITM, String EATX40, String DESC, String PCH, int a, String BOS_DATE, String BOS_LOCT) {
    this.EAAITM = EAAITM;
    this.EATX40 = EATX40;
    this.DESC = DESC;
    this.PCH = PCH;
    this.BOS_DATE = BOS_DATE;
    this.BOS_LOCT = BOS_LOCT;
  }
  public M3BILLBean(String EAAITM, String EATX40, String DESC, String PCH, int a, String BOS_DATE) {
    this.EAAITM = EAAITM;
    this.EATX40 = EATX40;
    this.DESC = DESC;
    this.PCH = PCH;
    this.BOS_DATE = BOS_DATE;
  }

  public String getSL_NO()
  {
    return this.SL_NO;
  }

  public void setSL_NO(String SL_NO) {
    this.SL_NO = SL_NO;
  }

  public String getTYPE_SL_NO() {
    return this.TYPE_SL_NO;
  }

  public void setTYPE_SL_NO(String TYPE_SL_NO) {
    this.TYPE_SL_NO = TYPE_SL_NO;
  }

  public String getSUB_TYPE_CODE() {
    return this.SUB_TYPE_CODE;
  }

  public void setSUB_TYPE_CODE(String SUB_TYPE_CODE) {
    this.SUB_TYPE_CODE = SUB_TYPE_CODE;
  }

  public String getSUB_TYPE_DESC() {
    return this.SUB_TYPE_DESC;
  }

  public void setSUB_TYPE_DESC(String SUB_TYPE_DESC) {
    this.SUB_TYPE_DESC = SUB_TYPE_DESC;
  }

  public String getFLAG() {
    return this.FLAG;
  }

  public void setFLAG(String FLAG) {
    this.FLAG = FLAG;
  }

  public String getEAAITM() {
    return this.EAAITM;
  }

  public void setEAAITM(String EAAITM) {
    this.EAAITM = EAAITM;
  }

  public String getEATX40() {
    return this.EATX40;
  }

  public void setEATX40(String EATX40) {
    this.EATX40 = EATX40;
  }

  public String getDELFLAG() {
    return this.DELFLAG;
  }

  public void setDELFLAG(String DELFLAG) {
    this.DELFLAG = DELFLAG;
  }

  public String getDESC() {
    return this.DESC;
  }

  public void setDESC(String DESC) {
    this.DESC = DESC;
  }

  public String getPCH() {
    return this.PCH;
  }

  public void setPCH(String PCH) {
    this.PCH = PCH;
  }

  public String getBOS_DATE() {
    return this.BOS_DATE;
  }

  public void setBOS_DATE(String BOS_DATE) {
    this.BOS_DATE = BOS_DATE;
  }

  public String getBOS_LOCT() {
    return this.BOS_LOCT;
  }

  public void setBOS_LOCT(String BOS_LOCT) {
    this.BOS_LOCT = BOS_LOCT;
  }
}