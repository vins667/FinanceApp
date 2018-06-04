package shahi.Action.compcal.bean;

public class ComplianceCalenderDtBean
{
  private long ID;
  private String ACTIVITY_NAME;
  private String DUE_DATE;
  private String COMPANY_NAME;
  private String LOCATION_NAME;
  private String DEPT_NAME;
  private String SUBDEPT_NAME;
  private String CALENDER_TYPE;
  private String ACCESS_FLAG;
  private String EDIT_FLAG;
  private String ACTIVITY_MONTH;
  
  public ComplianceCalenderDtBean() {}
  
  public ComplianceCalenderDtBean(long iD, String aCTIVITY_NAME, String dUE_DATE, String cOMPANY_NAME, String lOCATION_NAME, String dEPT_NAME, String sUBDEPT_NAME)
  {
    this.ID = iD;
    this.ACTIVITY_NAME = aCTIVITY_NAME;
    this.DUE_DATE = dUE_DATE;
    this.COMPANY_NAME = cOMPANY_NAME;
    this.LOCATION_NAME = lOCATION_NAME;
    this.DEPT_NAME = dEPT_NAME;
    this.SUBDEPT_NAME = sUBDEPT_NAME;
  }
  
  public ComplianceCalenderDtBean(long iD, String aCTIVITY_NAME, String dUE_DATE, String cOMPANY_NAME, String lOCATION_NAME, String dEPT_NAME, String sUBDEPT_NAME, String cALENDER_TYPE, String aCCESS_FLAG)
  {
    this.ID = iD;
    this.ACTIVITY_NAME = aCTIVITY_NAME;
    this.DUE_DATE = dUE_DATE;
    this.COMPANY_NAME = cOMPANY_NAME;
    this.LOCATION_NAME = lOCATION_NAME;
    this.DEPT_NAME = dEPT_NAME;
    this.SUBDEPT_NAME = sUBDEPT_NAME;
    this.CALENDER_TYPE = cALENDER_TYPE;
    this.ACCESS_FLAG = aCCESS_FLAG;
  }
  
  public ComplianceCalenderDtBean(long iD, String aCTIVITY_NAME, String dUE_DATE, String cOMPANY_NAME, String lOCATION_NAME, String dEPT_NAME, String sUBDEPT_NAME, String cALENDER_TYPE, String aCCESS_FLAG, String eDIT_FLAG, String aCTIVITY_MONTH)
  {
    this.ID = iD;
    this.ACTIVITY_NAME = aCTIVITY_NAME;
    this.DUE_DATE = dUE_DATE;
    this.COMPANY_NAME = cOMPANY_NAME;
    this.LOCATION_NAME = lOCATION_NAME;
    this.DEPT_NAME = dEPT_NAME;
    this.SUBDEPT_NAME = sUBDEPT_NAME;
    this.CALENDER_TYPE = cALENDER_TYPE;
    this.ACCESS_FLAG = aCCESS_FLAG;
    this.EDIT_FLAG = eDIT_FLAG;
    this.ACTIVITY_MONTH = aCTIVITY_MONTH;
  }
  
  public long getID()
  {
    return this.ID;
  }
  
  public void setID(long iD)
  {
    this.ID = iD;
  }
  
  public String getACTIVITY_NAME()
  {
    return this.ACTIVITY_NAME;
  }
  
  public void setACTIVITY_NAME(String aCTIVITY_NAME)
  {
    this.ACTIVITY_NAME = aCTIVITY_NAME;
  }
  
  public String getDUE_DATE()
  {
    return this.DUE_DATE;
  }
  
  public void setDUE_DATE(String dUE_DATE)
  {
    this.DUE_DATE = dUE_DATE;
  }
  
  public String getCOMPANY_NAME()
  {
    return this.COMPANY_NAME;
  }
  
  public void setCOMPANY_NAME(String cOMPANY_NAME)
  {
    this.COMPANY_NAME = cOMPANY_NAME;
  }
  
  public String getLOCATION_NAME()
  {
    return this.LOCATION_NAME;
  }
  
  public void setLOCATION_NAME(String lOCATION_NAME)
  {
    this.LOCATION_NAME = lOCATION_NAME;
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
  
  public String getCALENDER_TYPE()
  {
    return this.CALENDER_TYPE;
  }
  
  public void setCALENDER_TYPE(String CALENDER_TYPE)
  {
    this.CALENDER_TYPE = CALENDER_TYPE;
  }
  
  public String getACCESS_FLAG()
  {
    return this.ACCESS_FLAG;
  }
  
  public void setACCESS_FLAG(String ACCESS_FLAG)
  {
    this.ACCESS_FLAG = ACCESS_FLAG;
  }
  
  public String getEDIT_FLAG()
  {
    return this.EDIT_FLAG;
  }
  
  public void setEDIT_FLAG(String EDIT_FLAG)
  {
    this.EDIT_FLAG = EDIT_FLAG;
  }
  
  public String getACTIVITY_MONTH()
  {
    return this.ACTIVITY_MONTH;
  }
  
  public void setACTIVITY_MONTH(String ACTIVITY_MONTH)
  {
    this.ACTIVITY_MONTH = ACTIVITY_MONTH;
  }
}
