package shahi.Action.MIM4;


public class PMS060MI
  extends BaseMI
{
  public PMS060MI()
  {
    setProgram("PMS060MI");
  }
  
  public String RptIssue(String FACI, String PRNO, String MFNO, String OPNO, String MSEQ, String RPDT, String TRDT, String RPQA, String REND, String WHSL, String BANO, String CHID)
  {
    String status = null;
    this.javaMI.mvxClearFields();
    this.javaMI.mvxSetField("CONO", "111"); 
    this.javaMI.mvxSetField("FACI", FACI);   //100
    this.javaMI.mvxSetField("PRNO", PRNO);   //2884001
    this.javaMI.mvxSetField("MFNO", MFNO);   //1000100942
    this.javaMI.mvxSetField("OPNO", OPNO);   //10
    this.javaMI.mvxSetField("MSEQ", MSEQ);
    this.javaMI.mvxSetField("RPDT", RPDT);
    //this.javaMI.mvxSetField("TRDT", TRDT);
    this.javaMI.mvxSetField("RPQA", RPQA);
    this.javaMI.mvxSetField("REND", REND);
    this.javaMI.mvxSetField("WHSL", WHSL);
    this.javaMI.mvxSetField("BANO", BANO);
    this.javaMI.mvxSetField("CHID", CHID);
    this.javaMI.mvxSetField("DSP1", "1");
    this.javaMI.mvxSetField("DSP2", "1");
    this.javaMI.mvxSetField("DSP3", "1");
    this.javaMI.mvxSetField("DSP4", "1");
    this.javaMI.mvxSetField("DSP5", "1");
    this.javaMI.mvxSetField("DSP6", "1");
    this.javaMI.mvxSetField("DSP7", "1");
    
    int recFlag = this.javaMI.mvxAccess("RptIssue");
    if (recFlag > 0)
    {
      status = "NO";
      System.out.println("Returned from applicationserver" + this.javaMI.mvxGetLastError());
    }
    else
    {
      status = "Yes";
    }
    return status;
  }
  
  public String RptIssueMo(String FACI, String PRNO, String MFNO, String OPNO, String REND, String vmwosq)
  {
    String status = null;
    this.javaMI.mvxClearFields();
    this.javaMI.mvxSetField("CONO", "111");
    this.javaMI.mvxSetField("FACI", FACI);
    this.javaMI.mvxSetField("PRNO", PRNO);
    this.javaMI.mvxSetField("MFNO", MFNO);
    this.javaMI.mvxSetField("MTNO", OPNO);
    this.javaMI.mvxSetField("REND", REND);
    this.javaMI.mvxSetField("WOSQ", vmwosq);
    this.javaMI.mvxSetField("DSP1", "1");
    this.javaMI.mvxSetField("DSP2", "1");
    this.javaMI.mvxSetField("DSP3", "1");
    this.javaMI.mvxSetField("DSP4", "1");
    this.javaMI.mvxSetField("DSP5", "1");
    this.javaMI.mvxSetField("DSP6", "1");
    this.javaMI.mvxSetField("DSP7", "1");
    int recFlag = this.javaMI.mvxAccess("RptIssue");
    if (recFlag > 0)
    {
      status = "NO";
      System.out.println("Returned from application server" + this.javaMI.mvxGetLastError());
    }
    else
    {
      status = "Yes";
    }
    return status;
  }
  
  public String RptIssueMowithseq(String FACI, String PRNO, String MFNO, String OPNO, String REND, String vmwosq)
  {
    String status = null;
    this.javaMI.mvxClearFields();
    this.javaMI.mvxSetField("CONO", "111");
    this.javaMI.mvxSetField("FACI", FACI);
    this.javaMI.mvxSetField("PRNO", PRNO);
    this.javaMI.mvxSetField("MFNO", MFNO);
    this.javaMI.mvxSetField("MTNO", OPNO);
    this.javaMI.mvxSetField("REND", REND);
    this.javaMI.mvxSetField("MSEQ", vmwosq);
    this.javaMI.mvxSetField("DSP1", "1");
    this.javaMI.mvxSetField("DSP2", "1");
    this.javaMI.mvxSetField("DSP3", "1");
    this.javaMI.mvxSetField("DSP4", "1");
    this.javaMI.mvxSetField("DSP5", "1");
    this.javaMI.mvxSetField("DSP6", "1");
    this.javaMI.mvxSetField("DSP7", "1");
    int recFlag = this.javaMI.mvxAccess("RptIssue");
    if (recFlag > 0)
    {
      status = "NO";
      System.out.println("Returned from application server" + this.javaMI.mvxGetLastError());
    }
    else
    {
      status = "Yes";
    }
    return status;
  }
  
  
}
