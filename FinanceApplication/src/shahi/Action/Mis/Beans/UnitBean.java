package shahi.Action.Mis.Beans;

public class UnitBean
{
  private String CTSTKY;
  private String CTTX40;
  
  public String getCTSTKY()
  {
    return this.CTSTKY;
  }
  
  public void setCTSTKY(String CTSTKY)
  {
    this.CTSTKY = CTSTKY;
  }
  
  public String getCTTX40()
  {
    return this.CTTX40;
  }
  
  public void setCTTX40(String CTTX40)
  {
    this.CTTX40 = CTTX40;
  }
  
  public UnitBean(String CTSTKY, String CTTX40)
  {
    this.CTSTKY = CTSTKY;
    this.CTTX40 = CTTX40;
  }
  
  public UnitBean() {}
}
