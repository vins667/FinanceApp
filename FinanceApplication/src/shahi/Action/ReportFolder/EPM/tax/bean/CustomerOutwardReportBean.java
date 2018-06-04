/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM.tax.bean;

/**
 *
 * @author Vivek
 */
public class CustomerOutwardReportBean {

    private String SOURCEIDENTIFIER;  //"STRING (Max Length:25)"
    private String SOURCEFILENAME;  //"STRING (Max Length:50)"
    private String GLACCOUNTCODE;  //"STRING (Max Length:25)"
    private String DIVISION;  //"STRING (Max Length:20)"
    private String SUBDIVISION;  //"STRING (Max Length:20)"
    private String PROFITCENTRE1;  //"STRING (Max Length:20)"
    private String PROFITCENTRE2;  //"STRING (Max Length:20)"
    private String PLANTCODE;  //"STRING (Max Length:20)"
    private String RETURNPERIOD;  //"STRING (MMYYYY)"
    private String SUPPLIERGSTIN;  //ALPHA-NUMERIC (15)
    private String DOCUMENTTYPE;  //"STRING (Max Length:5)"
    private String SUPPLYTYPE;  //"STRING (Max Length:5)"
    private String DOCUMENTNUMBER;  //STRING (Max Length:16)
    private String DOCUMENTDATE;  //"STRING (YYYY-MM-DD)"
    private String ORIGINALDOCUMENTNUMBER;  //STRING (Max Length:16)
    private String ORIGINALDOCUMENTDATE;  //"STRING (YYYY-MM-DD)"
    private String CRDRPREGST;  //"STRING (Length:1)"
    private Integer LINENUMBER;  //INTEGER (Max Length:4)
    private String CUSTOMERGSTIN;  //ALPHA-NUMERIC (15)
    private String UINORCOMPOSITION;  //"STRING (Length:1)"
    private String ORIGINALCUSTOMERGSTIN;  //ALPHA-NUMERIC (15)
    private String CUSTOMERNAME;  //STRING (Max Length:100)
    private String CUSTOMERCODE;  //"STRING (Max Length:20)"
    private String BILLTOSTATE;  //"STRING (Max Length:2)"
    private String SHIPTOSTATE;  //"STRING (Max Length:2)"
    private String POS;  //"STRING (Max Length:2)"
    private String PORTCODE;  //String (Max Length:6)
    private String SHIPPINGBILLNUMBER;  //Integer (Max Length:7)
    private String SHIPPINGBILLDATE;  //"STRING (YYYY-MM-DD)"
    private Double FOB;  //DECIMAL(15,2)
    private Double EXPORTDUTY;  //DECIMAL(15,2)
    private String HSNORSAC;  //ALPHA-NUMERIC (Max Length:10)
    private String PRODUCTCODE;  //"STRING (Max Length:20)"
    private String PRODUCTDESCRIPTION;  //"STRING (Max Length:100)"
    private String CATEGORYOFPRODUCT;  //"STRING (Max Length:30)"
    private String UNITOFMEASUREMENT;  //"STRING (Max Length:30)"
    private Double QUANTITY;  //DECIMAL(15,3)
    private Double TAXABLEVALUE;  //DECIMAL(15,2)
    private Double INTEGRATEDTAXRATE;  //DECIMAL(15,2)
    private Double INTEGRATEDTAXAMOUNT;  //DECIMAL(15,2)
    private Double CENTRALTAXRATE;  //DECIMAL(15,2)
    private Double CENTRALTAXAMOUNT;  //DECIMAL(15,2)
    private Double STATEUTTAXRATE;  //DECIMAL(15,2)
    private Double STATEUTTAXAMOUNT;  //DECIMAL(15,2)
    private Double CESSRATEADVALOREM;  //DECIMAL(15,2)
    private Double CESSAMOUNTADVALOREM;  //DECIMAL(15,2)
    private Double CESSRATESPECIFIC;  //DECIMAL(15,2)
    private Double CESSAMOUNTSPECIFIC;  //DECIMAL(15,2)
    private Double INVOICEVALUE;  //DECIMAL(15,2)
    private String REVERSECHARGEFLAG;  //"STRING (Length:1)"
    private String TCSFLAG;  //"STRING (Length:1)"
    private String ECOMGSTIN;  //ALPHA-NUMERIC (15)
    private String ITCFLAG;  //"STRING (Max Length:2)"
    private String REASONFORCREDITDEBITNOTE;  //"STRING (Max Length:30)"
    private String ACCOUNTINGVOUCHERNUMBER;  //STRING (Max Length:50)
    private String ACCOUNTINGVOUCHERDATE;  //"STRING (YYYY-MM-DD)"
    private String USERDEFINEDFIELD1;  //STRING (Max Length:100)
    private String USERDEFINEDFIELD2;  //STRING (Max Length:100)
    private String USERDEFINEDFIELD3;  //STRING (Max Length:100)

    public String getSOURCEIDENTIFIER() {
        return SOURCEIDENTIFIER;
    }

    public void setSOURCEIDENTIFIER(String SOURCEIDENTIFIER) {
        this.SOURCEIDENTIFIER = SOURCEIDENTIFIER;
    }

    public String getSOURCEFILENAME() {
        return SOURCEFILENAME;
    }

    public void setSOURCEFILENAME(String SOURCEFILENAME) {
        this.SOURCEFILENAME = SOURCEFILENAME;
    }

    public String getGLACCOUNTCODE() {
        return GLACCOUNTCODE;
    }

    public void setGLACCOUNTCODE(String GLACCOUNTCODE) {
        this.GLACCOUNTCODE = GLACCOUNTCODE;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }

    public String getSUBDIVISION() {
        return SUBDIVISION;
    }

    public void setSUBDIVISION(String SUBDIVISION) {
        this.SUBDIVISION = SUBDIVISION;
    }

    public String getPROFITCENTRE1() {
        return PROFITCENTRE1;
    }

    public void setPROFITCENTRE1(String PROFITCENTRE1) {
        this.PROFITCENTRE1 = PROFITCENTRE1;
    }

    public String getPROFITCENTRE2() {
        return PROFITCENTRE2;
    }

    public void setPROFITCENTRE2(String PROFITCENTRE2) {
        this.PROFITCENTRE2 = PROFITCENTRE2;
    }

    public String getPLANTCODE() {
        return PLANTCODE;
    }

    public void setPLANTCODE(String PLANTCODE) {
        this.PLANTCODE = PLANTCODE;
    }

    public String getRETURNPERIOD() {
        return RETURNPERIOD;
    }

    public void setRETURNPERIOD(String RETURNPERIOD) {
        this.RETURNPERIOD = RETURNPERIOD;
    }

    public String getSUPPLIERGSTIN() {
        return SUPPLIERGSTIN;
    }

    public void setSUPPLIERGSTIN(String SUPPLIERGSTIN) {
        this.SUPPLIERGSTIN = SUPPLIERGSTIN;
    }

    public String getDOCUMENTTYPE() {
        return DOCUMENTTYPE;
    }

    public void setDOCUMENTTYPE(String DOCUMENTTYPE) {
        this.DOCUMENTTYPE = DOCUMENTTYPE;
    }

    public String getSUPPLYTYPE() {
        return SUPPLYTYPE;
    }

    public void setSUPPLYTYPE(String SUPPLYTYPE) {
        this.SUPPLYTYPE = SUPPLYTYPE;
    }

    public String getDOCUMENTNUMBER() {
        return DOCUMENTNUMBER;
    }

    public void setDOCUMENTNUMBER(String DOCUMENTNUMBER) {
        this.DOCUMENTNUMBER = DOCUMENTNUMBER;
    }

    public String getDOCUMENTDATE() {
        return DOCUMENTDATE;
    }

    public void setDOCUMENTDATE(String DOCUMENTDATE) {
        this.DOCUMENTDATE = DOCUMENTDATE;
    }

    public String getORIGINALDOCUMENTNUMBER() {
        return ORIGINALDOCUMENTNUMBER;
    }

    public void setORIGINALDOCUMENTNUMBER(String ORIGINALDOCUMENTNUMBER) {
        this.ORIGINALDOCUMENTNUMBER = ORIGINALDOCUMENTNUMBER;
    }

    public String getORIGINALDOCUMENTDATE() {
        return ORIGINALDOCUMENTDATE;
    }

    public void setORIGINALDOCUMENTDATE(String ORIGINALDOCUMENTDATE) {
        this.ORIGINALDOCUMENTDATE = ORIGINALDOCUMENTDATE;
    }

    public String getCRDRPREGST() {
        return CRDRPREGST;
    }

    public void setCRDRPREGST(String CRDRPREGST) {
        this.CRDRPREGST = CRDRPREGST;
    }

    public Integer getLINENUMBER() {
        return LINENUMBER;
    }

    public void setLINENUMBER(Integer LINENUMBER) {
        this.LINENUMBER = LINENUMBER;
    }

    public String getCUSTOMERGSTIN() {
        return CUSTOMERGSTIN;
    }

    public void setCUSTOMERGSTIN(String CUSTOMERGSTIN) {
        this.CUSTOMERGSTIN = CUSTOMERGSTIN;
    }

    public String getUINORCOMPOSITION() {
        return UINORCOMPOSITION;
    }

    public void setUINORCOMPOSITION(String UINORCOMPOSITION) {
        this.UINORCOMPOSITION = UINORCOMPOSITION;
    }

    public String getORIGINALCUSTOMERGSTIN() {
        return ORIGINALCUSTOMERGSTIN;
    }

    public void setORIGINALCUSTOMERGSTIN(String ORIGINALCUSTOMERGSTIN) {
        this.ORIGINALCUSTOMERGSTIN = ORIGINALCUSTOMERGSTIN;
    }

    public String getCUSTOMERNAME() {
        return CUSTOMERNAME;
    }

    public void setCUSTOMERNAME(String CUSTOMERNAME) {
        this.CUSTOMERNAME = CUSTOMERNAME;
    }

    public String getCUSTOMERCODE() {
        return CUSTOMERCODE;
    }

    public void setCUSTOMERCODE(String CUSTOMERCODE) {
        this.CUSTOMERCODE = CUSTOMERCODE;
    }

    public String getBILLTOSTATE() {
        return BILLTOSTATE;
    }

    public void setBILLTOSTATE(String BILLTOSTATE) {
        this.BILLTOSTATE = BILLTOSTATE;
    }

    public String getSHIPTOSTATE() {
        return SHIPTOSTATE;
    }

    public void setSHIPTOSTATE(String SHIPTOSTATE) {
        this.SHIPTOSTATE = SHIPTOSTATE;
    }

    public String getPOS() {
        return POS;
    }

    public void setPOS(String POS) {
        this.POS = POS;
    }

    public String getPORTCODE() {
        return PORTCODE;
    }

    public void setPORTCODE(String PORTCODE) {
        this.PORTCODE = PORTCODE;
    }

    public String getSHIPPINGBILLNUMBER() {
        return SHIPPINGBILLNUMBER;
    }

    public void setSHIPPINGBILLNUMBER(String SHIPPINGBILLNUMBER) {
        this.SHIPPINGBILLNUMBER = SHIPPINGBILLNUMBER;
    }

    public String getSHIPPINGBILLDATE() {
        return SHIPPINGBILLDATE;
    }

    public void setSHIPPINGBILLDATE(String SHIPPINGBILLDATE) {
        this.SHIPPINGBILLDATE = SHIPPINGBILLDATE;
    }

    public Double getFOB() {
        return FOB;
    }

    public void setFOB(Double FOB) {
        this.FOB = FOB;
    }

    public Double getEXPORTDUTY() {
        return EXPORTDUTY;
    }

    public void setEXPORTDUTY(Double EXPORTDUTY) {
        this.EXPORTDUTY = EXPORTDUTY;
    }

    public String getHSNORSAC() {
        return HSNORSAC;
    }

    public void setHSNORSAC(String HSNORSAC) {
        this.HSNORSAC = HSNORSAC;
    }

    public String getPRODUCTCODE() {
        return PRODUCTCODE;
    }

    public void setPRODUCTCODE(String PRODUCTCODE) {
        this.PRODUCTCODE = PRODUCTCODE;
    }

    public String getPRODUCTDESCRIPTION() {
        return PRODUCTDESCRIPTION;
    }

    public void setPRODUCTDESCRIPTION(String PRODUCTDESCRIPTION) {
        this.PRODUCTDESCRIPTION = PRODUCTDESCRIPTION;
    }

    public String getCATEGORYOFPRODUCT() {
        return CATEGORYOFPRODUCT;
    }

    public void setCATEGORYOFPRODUCT(String CATEGORYOFPRODUCT) {
        this.CATEGORYOFPRODUCT = CATEGORYOFPRODUCT;
    }

    public String getUNITOFMEASUREMENT() {
        return UNITOFMEASUREMENT;
    }

    public void setUNITOFMEASUREMENT(String UNITOFMEASUREMENT) {
        this.UNITOFMEASUREMENT = UNITOFMEASUREMENT;
    }

    public Double getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(Double QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public Double getTAXABLEVALUE() {
        return TAXABLEVALUE;
    }

    public void setTAXABLEVALUE(Double TAXABLEVALUE) {
        this.TAXABLEVALUE = TAXABLEVALUE;
    }

    public Double getINTEGRATEDTAXRATE() {
        return INTEGRATEDTAXRATE;
    }

    public void setINTEGRATEDTAXRATE(Double INTEGRATEDTAXRATE) {
        this.INTEGRATEDTAXRATE = INTEGRATEDTAXRATE;
    }

    public Double getINTEGRATEDTAXAMOUNT() {
        return INTEGRATEDTAXAMOUNT;
    }

    public void setINTEGRATEDTAXAMOUNT(Double INTEGRATEDTAXAMOUNT) {
        this.INTEGRATEDTAXAMOUNT = INTEGRATEDTAXAMOUNT;
    }

    public Double getCENTRALTAXRATE() {
        return CENTRALTAXRATE;
    }

    public void setCENTRALTAXRATE(Double CENTRALTAXRATE) {
        this.CENTRALTAXRATE = CENTRALTAXRATE;
    }

    public Double getCENTRALTAXAMOUNT() {
        return CENTRALTAXAMOUNT;
    }

    public void setCENTRALTAXAMOUNT(Double CENTRALTAXAMOUNT) {
        this.CENTRALTAXAMOUNT = CENTRALTAXAMOUNT;
    }

    public Double getSTATEUTTAXRATE() {
        return STATEUTTAXRATE;
    }

    public void setSTATEUTTAXRATE(Double STATEUTTAXRATE) {
        this.STATEUTTAXRATE = STATEUTTAXRATE;
    }

    public Double getSTATEUTTAXAMOUNT() {
        return STATEUTTAXAMOUNT;
    }

    public void setSTATEUTTAXAMOUNT(Double STATEUTTAXAMOUNT) {
        this.STATEUTTAXAMOUNT = STATEUTTAXAMOUNT;
    }

    public Double getCESSRATEADVALOREM() {
        return CESSRATEADVALOREM;
    }

    public void setCESSRATEADVALOREM(Double CESSRATEADVALOREM) {
        this.CESSRATEADVALOREM = CESSRATEADVALOREM;
    }

    public Double getCESSAMOUNTADVALOREM() {
        return CESSAMOUNTADVALOREM;
    }

    public void setCESSAMOUNTADVALOREM(Double CESSAMOUNTADVALOREM) {
        this.CESSAMOUNTADVALOREM = CESSAMOUNTADVALOREM;
    }

    public Double getCESSRATESPECIFIC() {
        return CESSRATESPECIFIC;
    }

    public void setCESSRATESPECIFIC(Double CESSRATESPECIFIC) {
        this.CESSRATESPECIFIC = CESSRATESPECIFIC;
    }

    public Double getCESSAMOUNTSPECIFIC() {
        return CESSAMOUNTSPECIFIC;
    }

    public void setCESSAMOUNTSPECIFIC(Double CESSAMOUNTSPECIFIC) {
        this.CESSAMOUNTSPECIFIC = CESSAMOUNTSPECIFIC;
    }

    public Double getINVOICEVALUE() {
        return INVOICEVALUE;
    }

    public void setINVOICEVALUE(Double INVOICEVALUE) {
        this.INVOICEVALUE = INVOICEVALUE;
    }

    public String getREVERSECHARGEFLAG() {
        return REVERSECHARGEFLAG;
    }

    public void setREVERSECHARGEFLAG(String REVERSECHARGEFLAG) {
        this.REVERSECHARGEFLAG = REVERSECHARGEFLAG;
    }

    public String getTCSFLAG() {
        return TCSFLAG;
    }

    public void setTCSFLAG(String TCSFLAG) {
        this.TCSFLAG = TCSFLAG;
    }

    public String getECOMGSTIN() {
        return ECOMGSTIN;
    }

    public void setECOMGSTIN(String ECOMGSTIN) {
        this.ECOMGSTIN = ECOMGSTIN;
    }

    public String getITCFLAG() {
        return ITCFLAG;
    }

    public void setITCFLAG(String ITCFLAG) {
        this.ITCFLAG = ITCFLAG;
    }

    public String getREASONFORCREDITDEBITNOTE() {
        return REASONFORCREDITDEBITNOTE;
    }

    public void setREASONFORCREDITDEBITNOTE(String REASONFORCREDITDEBITNOTE) {
        this.REASONFORCREDITDEBITNOTE = REASONFORCREDITDEBITNOTE;
    }

    public String getACCOUNTINGVOUCHERNUMBER() {
        return ACCOUNTINGVOUCHERNUMBER;
    }

    public void setACCOUNTINGVOUCHERNUMBER(String ACCOUNTINGVOUCHERNUMBER) {
        this.ACCOUNTINGVOUCHERNUMBER = ACCOUNTINGVOUCHERNUMBER;
    }

    public String getACCOUNTINGVOUCHERDATE() {
        return ACCOUNTINGVOUCHERDATE;
    }

    public void setACCOUNTINGVOUCHERDATE(String ACCOUNTINGVOUCHERDATE) {
        this.ACCOUNTINGVOUCHERDATE = ACCOUNTINGVOUCHERDATE;
    }

    public String getUSERDEFINEDFIELD1() {
        return USERDEFINEDFIELD1;
    }

    public void setUSERDEFINEDFIELD1(String USERDEFINEDFIELD1) {
        this.USERDEFINEDFIELD1 = USERDEFINEDFIELD1;
    }

    public String getUSERDEFINEDFIELD2() {
        return USERDEFINEDFIELD2;
    }

    public void setUSERDEFINEDFIELD2(String USERDEFINEDFIELD2) {
        this.USERDEFINEDFIELD2 = USERDEFINEDFIELD2;
    }

    public String getUSERDEFINEDFIELD3() {
        return USERDEFINEDFIELD3;
    }

    public void setUSERDEFINEDFIELD3(String USERDEFINEDFIELD3) {
        this.USERDEFINEDFIELD3 = USERDEFINEDFIELD3;
    }
}