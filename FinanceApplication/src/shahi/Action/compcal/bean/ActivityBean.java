package shahi.Action.compcal.bean;

public class ActivityBean
{
  private long ID;
  private long ACTIVITY_ID;
  private String ACTIVITY_NAME;
  private String LOCATION_CODE;
  private String DEPT_ID;
  private String DEPT_NAME;
  private String SUBDEPT_ID;
  private String SUBDEPT_NAME;
  private String ALERT_TYPE1;
  private String ALERT_TYPE2;
  private String ALERT_TYPE3;
  private String FLAG;
  private String USER_ID;
  private String TDATE;
  private String MUSER_ID;
  private String MDATE;
  private String UPD_FLAG;
  private String CAL_FLAG;
  
  public ActivityBean() {}
  
  public ActivityBean(long iD, long aCTIVITY_ID, String aCTIVITY_NAME, String lOCATION_CODE, String dEPT_ID, String dEPT_NAME, String sUBDEPT_ID, String sUBDEPT_NAME, String aLERT_TYPE1, String aLERT_TYPE2, String aLERT_TYPE3, String fLAG, String uSER_ID, String tDATE, String mUSER_ID, String mDATE, String uPD_FLAG, String cAL_FLAG)
  {
    this.ID = iD;
    this.ACTIVITY_ID = aCTIVITY_ID;
    this.ACTIVITY_NAME = aCTIVITY_NAME;
    this.LOCATION_CODE = lOCATION_CODE;
    this.DEPT_ID = dEPT_ID;
    this.DEPT_NAME = dEPT_NAME;
    this.SUBDEPT_ID = sUBDEPT_ID;
    this.SUBDEPT_NAME = sUBDEPT_NAME;
    this.ALERT_TYPE1 = aLERT_TYPE1;
    this.ALERT_TYPE2 = aLERT_TYPE2;
    this.ALERT_TYPE3 = aLERT_TYPE3;
    this.FLAG = fLAG;
    this.USER_ID = uSER_ID;
    this.TDATE = tDATE;
    this.MUSER_ID = mUSER_ID;
    this.MDATE = mDATE;
    this.UPD_FLAG = uPD_FLAG;
    this.CAL_FLAG = cAL_FLAG;
  }
  
  public long getID()
  {
    return this.ID;
  }
  
  public void setID(long iD)
  {
    this.ID = iD;
  }
  
  public long getACTIVITY_ID()
  {
    return this.ACTIVITY_ID;
  }
  
  public void setACTIVITY_ID(long aCTIVITY_ID)
  {
    this.ACTIVITY_ID = aCTIVITY_ID;
  }
  
  public String getACTIVITY_NAME()
  {
    return this.ACTIVITY_NAME;
  }
  
  public void setACTIVITY_NAME(String aCTIVITY_NAME)
  {
    this.ACTIVITY_NAME = aCTIVITY_NAME;
  }
  
  public String getLOCATION_CODE()
  {
    return this.LOCATION_CODE;
  }
  
  public void setLOCATION_CODE(String lOCATION_CODE)
  {
    this.LOCATION_CODE = lOCATION_CODE;
  }
  
  public String getDEPT_ID()
  {
    return this.DEPT_ID;
  }
  
  public void setDEPT_ID(String dEPT_ID)
  {
    this.DEPT_ID = dEPT_ID;
  }
  
  public String getSUBDEPT_ID()
  {
    return this.SUBDEPT_ID;
  }
  
  public void setSUBDEPT_ID(String sUBDEPT_ID)
  {
    this.SUBDEPT_ID = sUBDEPT_ID;
  }
  
  public String getALERT_TYPE1()
  {
    return this.ALERT_TYPE1;
  }
  
  public void setALERT_TYPE1(String aLERT_TYPE1)
  {
    this.ALERT_TYPE1 = aLERT_TYPE1;
  }
  
  public String getALERT_TYPE2()
  {
    return this.ALERT_TYPE2;
  }
  
  public void setALERT_TYPE2(String aLERT_TYPE2)
  {
    this.ALERT_TYPE2 = aLERT_TYPE2;
  }
  
  public String getALERT_TYPE3()
  {
    return this.ALERT_TYPE3;
  }
  
  public void setALERT_TYPE3(String aLERT_TYPE3)
  {
    this.ALERT_TYPE3 = aLERT_TYPE3;
  }
  
  public String getFLAG()
  {
    return this.FLAG;
  }
  
  public void setFLAG(String fLAG)
  {
    this.FLAG = fLAG;
  }
  
  public String getUSER_ID()
  {
    return this.USER_ID;
  }
  
  public void setUSER_ID(String uSER_ID)
  {
    this.USER_ID = uSER_ID;
  }
  
  public String getTDATE()
  {
    return this.TDATE;
  }
  
  public void setTDATE(String tDATE)
  {
    this.TDATE = tDATE;
  }
  
  public String getMUSER_ID()
  {
    return this.MUSER_ID;
  }
  
  public void setMUSER_ID(String mUSER_ID)
  {
    this.MUSER_ID = mUSER_ID;
  }
  
  public String getMDATE()
  {
    return this.MDATE;
  }
  
  public void setMDATE(String mDATE)
  {
    this.MDATE = mDATE;
  }
  
  public String getDEPT_NAME()
  {
    return this.DEPT_NAME;
  }
  
  public void setDEPT_NAME(String dEPT_NAME)
  {
    this.DEPT_NAME = dEPT_NAME;
  }
  
  public String getSUBDEPT_NAME()
  {
    return this.SUBDEPT_NAME;
  }
  
  public void setSUBDEPT_NAME(String sUBDEPT_NAME)
  {
    this.SUBDEPT_NAME = sUBDEPT_NAME;
  }
  
  public String getUPD_FLAG()
  {
    return this.UPD_FLAG;
  }
  
  public void setUPD_FLAG(String uPD_FLAG)
  {
    this.UPD_FLAG = uPD_FLAG;
  }
  
  public String getCAL_FLAG()
  {
    return this.CAL_FLAG;
  }
  
  public void setCAL_FLAG(String cAL_FLAG)
  {
    this.CAL_FLAG = cAL_FLAG;
  }
}
