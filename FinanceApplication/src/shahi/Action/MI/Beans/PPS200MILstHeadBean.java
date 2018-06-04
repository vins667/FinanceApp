/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author VIVEK
 */
public class PPS200MILstHeadBean  implements Comparable<PPS200MILstHeadBean>{
    private String CONO;   //Company
    private String PUNO;   //Purchase Order number
    private String DONR;   //Document number
    private String DOVA;   //Document variant
    private String DATE;   //Entry date
    private String PUDT;   //Order date
    private String DPUDT;   //Order date
    private String DWDT;   //Requested delivery date
    private String LEDT;   //First printout date
    private String CONM;   //Company name
    private String REVN;   //Revision number
    private String RENM;   //Name of user responsible
    private String SUNO;   //Supplier number
    private String CSCD;   //Country
    private String BUYE;   //Buyer
    private String CUCD;   //Currency
    private String TXCU;   //Currency name
    private String TXPY;   //Terms of Payment name
    private String TXMO;   //Delivery Mode name
    private String TXDL;   //Terms of Delivery name
    private String TXAF;   //Terms of Freight name
    private String TXPA;   //Terms of Packaging name
    private String TXHA;   //Harbor or airport name
    private String YRE1;   //Your reference
    private String PHNO;   //Telephone number
    private String TFNO;   //Facsimile transmission number
    private String REVV;   //Revision amount
    private String REMK;   //Remark
    private String REV1;   //Amount before revision
    private String COAM;   //Total order cost
    private String DIVI;   //Division
    private String FACI;   //Facility
    private String WHLO;   //Warehouse
    private String ORTY;   //Order type
    private String LNCD;   //Language
    private String TEPY;   //Payment terms
    private String MODL;   //Delivery method
    private String TEDL;   //Delivery terms
    private String TEAF;   //Freight terms
    private String TEPA;   //Packaging terms
    private String RFID;   //Reference
    private String TEL1;   //Terms text
    private String HAFE;   //Harbor or airport
    private String POTC;   //Purchase order category
    private String PYAD;   //Our invoicing address
    private String MTDP;   //Multiple delivery addresses
    private String MTWP;   //Multiple warehouses
    private String OURR;   //Our reference number
    private String OURT;   //Reference type
    private String PRSU;   //Payee
    private String AGNT;   //Agent
    private String NTAM;   //Net order value
    private String TOQT;   //Total quantity
    private String SAAM;   //Total order cost - local currency
    private String LOCD;   //Local currency
    private String RASN;   //Rail station
    private String EXAT;   //Monitoring active
    private String FUSC;   //Monitoring activity list
    private String PYME;   //Payment Method AP
    private String PURC;   //Requisition BY
    private String TEOR;   //Telephone Order
    private String TLEX;   //External Charges
    private String TLIN;   //Internal Charges
    private String PUSL;   //Lowest Status
    private String PUST;   //Higher Status
    private String SUNM;   //Supplier Name
    private String TX15;   //Order Type Desc
    private String USER;//Approval Page
    private long AAMT;//Approval Page
    private String COLO;//Approval Page
    private String TOER;
    private List PCH;
    
    private boolean POPCHK=false;
    private String TEMP;
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if ((obj instanceof PPS200MILstHeadBean) && ((PPS200MILstHeadBean) obj).getPUNO().equals(this.PUNO)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        try {
            hash = Integer.parseInt(this.PUNO);
        } catch (NumberFormatException e) {
            hash = 1;
        } catch (Exception e) {
        }
        return hash;
    }

    public int compareTo(PPS200MILstHeadBean ovb) {
        return this.getPUNO().compareTo(ovb.getPUNO());
    }
    public static Comparator <PPS200MILstHeadBean> SortByPODATEComparator = new Comparator<PPS200MILstHeadBean>() {

        public int compare(PPS200MILstHeadBean o, PPS200MILstHeadBean o1) {

            String StyleName1 = o.getPUDT().toUpperCase();
            String StyleName2 = o1.getPUDT().toUpperCase();
               
            //ascending order
            return StyleName1.compareTo(StyleName2);


        }
    };
    public static Comparator <PPS200MILstHeadBean> DescSortByPODATEComparator = new Comparator<PPS200MILstHeadBean>() {

        public int compare(PPS200MILstHeadBean o, PPS200MILstHeadBean o1) {

            String StyleName1 = o.getPUDT().toUpperCase();
            String StyleName2 = o1.getPUDT().toUpperCase();

            //descending order
            return StyleName2.compareTo(StyleName1);
        }
    };

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public String getDONR() {
        return DONR;
    }

    public void setDONR(String DONR) {
        this.DONR = DONR;
    }

    public String getDOVA() {
        return DOVA;
    }

    public void setDOVA(String DOVA) {
        this.DOVA = DOVA;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getPUDT() {
        return PUDT;
    }

    public void setPUDT(String PUDT) {
        this.PUDT = PUDT;
    }

    public String getDWDT() {
        return DWDT;
    }

    public void setDWDT(String DWDT) {
        this.DWDT = DWDT;
    }

    public String getLEDT() {
        return LEDT;
    }

    public void setLEDT(String LEDT) {
        this.LEDT = LEDT;
    }

    public String getCONM() {
        return CONM;
    }

    public void setCONM(String CONM) {
        this.CONM = CONM;
    }

    public String getREVN() {
        return REVN;
    }

    public void setREVN(String REVN) {
        this.REVN = REVN;
    }

    public String getRENM() {
        return RENM;
    }

    public void setRENM(String RENM) {
        this.RENM = RENM;
    }

    public String getSUNO() {
        return SUNO;
    }

    public void setSUNO(String SUNO) {
        this.SUNO = SUNO;
    }

    public String getCSCD() {
        return CSCD;
    }

    public void setCSCD(String CSCD) {
        this.CSCD = CSCD;
    }

    public String getBUYE() {
        return BUYE;
    }

    public void setBUYE(String BUYE) {
        this.BUYE = BUYE;
    }

    public String getCUCD() {
        return CUCD;
    }

    public void setCUCD(String CUCD) {
        this.CUCD = CUCD;
    }

    public String getTXCU() {
        return TXCU;
    }

    public void setTXCU(String TXCU) {
        this.TXCU = TXCU;
    }

    public String getTXPY() {
        return TXPY;
    }

    public void setTXPY(String TXPY) {
        this.TXPY = TXPY;
    }

    public String getTXMO() {
        return TXMO;
    }

    public void setTXMO(String TXMO) {
        this.TXMO = TXMO;
    }

    public String getTXDL() {
        return TXDL;
    }

    public void setTXDL(String TXDL) {
        this.TXDL = TXDL;
    }

    public String getTXAF() {
        return TXAF;
    }

    public void setTXAF(String TXAF) {
        this.TXAF = TXAF;
    }

    public String getTXPA() {
        return TXPA;
    }

    public void setTXPA(String TXPA) {
        this.TXPA = TXPA;
    }

    public String getTXHA() {
        return TXHA;
    }

    public void setTXHA(String TXHA) {
        this.TXHA = TXHA;
    }

    public String getYRE1() {
        return YRE1;
    }

    public void setYRE1(String YRE1) {
        this.YRE1 = YRE1;
    }

    public String getPHNO() {
        return PHNO;
    }

    public void setPHNO(String PHNO) {
        this.PHNO = PHNO;
    }

    public String getTFNO() {
        return TFNO;
    }

    public void setTFNO(String TFNO) {
        this.TFNO = TFNO;
    }

    public String getREVV() {
        return REVV;
    }

    public void setREVV(String REVV) {
        this.REVV = REVV;
    }

    public String getREMK() {
        return REMK;
    }

    public void setREMK(String REMK) {
        this.REMK = REMK;
    }

    public String getREV1() {
        return REV1;
    }

    public void setREV1(String REV1) {
        this.REV1 = REV1;
    }

    public String getCOAM() {
        return COAM;
    }

    public void setCOAM(String COAM) {
        this.COAM = COAM;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getFACI() {
        return FACI;
    }

    public void setFACI(String FACI) {
        this.FACI = FACI;
    }

    public String getWHLO() {
        return WHLO;
    }

    public void setWHLO(String WHLO) {
        this.WHLO = WHLO;
    }

    public String getORTY() {
        return ORTY;
    }

    public void setORTY(String ORTY) {
        this.ORTY = ORTY;
    }

    public String getLNCD() {
        return LNCD;
    }

    public void setLNCD(String LNCD) {
        this.LNCD = LNCD;
    }

    public String getTEPY() {
        return TEPY;
    }

    public void setTEPY(String TEPY) {
        this.TEPY = TEPY;
    }

    public String getMODL() {
        return MODL;
    }

    public void setMODL(String MODL) {
        this.MODL = MODL;
    }

    public String getTEDL() {
        return TEDL;
    }

    public void setTEDL(String TEDL) {
        this.TEDL = TEDL;
    }

    public String getTEAF() {
        return TEAF;
    }

    public void setTEAF(String TEAF) {
        this.TEAF = TEAF;
    }

    public String getTEPA() {
        return TEPA;
    }

    public void setTEPA(String TEPA) {
        this.TEPA = TEPA;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
    }

    public String getHAFE() {
        return HAFE;
    }

    public void setHAFE(String HAFE) {
        this.HAFE = HAFE;
    }

    public String getPOTC() {
        return POTC;
    }

    public void setPOTC(String POTC) {
        this.POTC = POTC;
    }

    public String getPYAD() {
        return PYAD;
    }

    public void setPYAD(String PYAD) {
        this.PYAD = PYAD;
    }

    public String getMTDP() {
        return MTDP;
    }

    public void setMTDP(String MTDP) {
        this.MTDP = MTDP;
    }

    public String getMTWP() {
        return MTWP;
    }

    public void setMTWP(String MTWP) {
        this.MTWP = MTWP;
    }

    public String getOURR() {
        return OURR;
    }

    public void setOURR(String OURR) {
        this.OURR = OURR;
    }

    public String getOURT() {
        return OURT;
    }

    public void setOURT(String OURT) {
        this.OURT = OURT;
    }

    public String getPRSU() {
        return PRSU;
    }

    public void setPRSU(String PRSU) {
        this.PRSU = PRSU;
    }

    public String getAGNT() {
        return AGNT;
    }

    public void setAGNT(String AGNT) {
        this.AGNT = AGNT;
    }

    public String getNTAM() {
        return NTAM;
    }

    public void setNTAM(String NTAM) {
        this.NTAM = NTAM;
    }

    public String getTOQT() {
        return TOQT;
    }

    public void setTOQT(String TOQT) {
        this.TOQT = TOQT;
    }

    public String getSAAM() {
        return SAAM;
    }

    public void setSAAM(String SAAM) {
        this.SAAM = SAAM;
    }

    public String getLOCD() {
        return LOCD;
    }

    public void setLOCD(String LOCD) {
        this.LOCD = LOCD;
    }

    public String getRASN() {
        return RASN;
    }

    public void setRASN(String RASN) {
        this.RASN = RASN;
    }

    public String getEXAT() {
        return EXAT;
    }

    public void setEXAT(String EXAT) {
        this.EXAT = EXAT;
    }

    public String getFUSC() {
        return FUSC;
    }

    public void setFUSC(String FUSC) {
        this.FUSC = FUSC;
    }

    public String getPYME() {
        return PYME;
    }

    public void setPYME(String PYME) {
        this.PYME = PYME;
    }

    public String getPURC() {
        return PURC;
    }

    public void setPURC(String PURC) {
        this.PURC = PURC;
    }

    public String getTEOR() {
        return TEOR;
    }

    public void setTEOR(String TEOR) {
        this.TEOR = TEOR;
    }

    public String getTLEX() {
        return TLEX;
    }

    public void setTLEX(String TLEX) {
        this.TLEX = TLEX;
    }

    public String getTLIN() {
        return TLIN;
    }

    public void setTLIN(String TLIN) {
        this.TLIN = TLIN;
    }

    public String getPUSL() {
        return PUSL;
    }

    public void setPUSL(String PUSL) {
        this.PUSL = PUSL;
    }

    public String getPUST() {
        return PUST;
    }

    public void setPUST(String PUST) {
        this.PUST = PUST;
    }

    public PPS200MILstHeadBean() {
    }

    public boolean isPOPCHK() {
        return POPCHK;
    }

    public String getSUNM() {
        return SUNM;
    }

    public void setSUNM(String SUNM) {
        this.SUNM = SUNM;
    }

    public String getTX15() {
        return TX15;
    }

    public void setTX15(String TX15) {
        this.TX15 = TX15;
    }

    public void setPOPCHK(boolean POPCHK) {
        this.POPCHK = POPCHK;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public long getAAMT() {
        return AAMT;
    }

    public void setAAMT(long AAMT) {
        this.AAMT = AAMT;
    }

    public String getCOLO() {
        return COLO;
    }

    public void setCOLO(String COLO) {
        this.COLO = COLO;
    }

    public String getTOER() {
        return TOER;
    }

    public void setTOER(String TOER) {
        this.TOER = TOER;
    }

    public List getPCH() {
        return PCH;
    }

    public void setPCH(List PCH) {
        this.PCH = PCH;
    }

    public String getDPUDT() {
        return DPUDT;
    }

    public void setDPUDT(String DPUDT) {
        this.DPUDT = DPUDT;
    }

    public String getTEMP() {
        return TEMP;
    }

    public void setTEMP(String TEMP) {
        this.TEMP = TEMP;
    }

    public PPS200MILstHeadBean(String CONO, String PUNO, String DONR, String DOVA, String DATE, String PUDT, String DWDT, String LEDT, String CONM, String REVN, String RENM, String SUNO, String CSCD, String BUYE, String CUCD, String TXCU, String TXPY, String TXMO, String TXDL, String TXAF, String TXPA, String TXHA, String YRE1, String PHNO, String TFNO, String REVV, String REMK, String REV1, String COAM, String DIVI, String FACI, String WHLO, String ORTY, String LNCD, String TEPY, String MODL, String TEDL, String TEAF, String TEPA, String RFID, String TEL1, String HAFE, String POTC, String PYAD, String MTDP, String MTWP, String OURR, String OURT, String PRSU, String AGNT, String NTAM, String TOQT, String SAAM, String LOCD, String RASN, String EXAT, String FUSC, String PYME, String PURC, String TEOR, String TLEX, String TLIN, String PUSL, String PUST) {
        this.CONO = CONO;
        this.PUNO = PUNO;
        this.DONR = DONR;
        this.DOVA = DOVA;
        this.DATE = DATE;
        this.PUDT = PUDT;
        this.DWDT = DWDT;
        this.LEDT = LEDT;
        this.CONM = CONM;
        this.REVN = REVN;
        this.RENM = RENM;
        this.SUNO = SUNO;
        this.CSCD = CSCD;
        this.BUYE = BUYE;
        this.CUCD = CUCD;
        this.TXCU = TXCU;
        this.TXPY = TXPY;
        this.TXMO = TXMO;
        this.TXDL = TXDL;
        this.TXAF = TXAF;
        this.TXPA = TXPA;
        this.TXHA = TXHA;
        this.YRE1 = YRE1;
        this.PHNO = PHNO;
        this.TFNO = TFNO;
        this.REVV = REVV;
        this.REMK = REMK;
        this.REV1 = REV1;
        this.COAM = COAM;
        this.DIVI = DIVI;
        this.FACI = FACI;
        this.WHLO = WHLO;
        this.ORTY = ORTY;
        this.LNCD = LNCD;
        this.TEPY = TEPY;
        this.MODL = MODL;
        this.TEDL = TEDL;
        this.TEAF = TEAF;
        this.TEPA = TEPA;
        this.RFID = RFID;
        this.TEL1 = TEL1;
        this.HAFE = HAFE;
        this.POTC = POTC;
        this.PYAD = PYAD;
        this.MTDP = MTDP;
        this.MTWP = MTWP;
        this.OURR = OURR;
        this.OURT = OURT;
        this.PRSU = PRSU;
        this.AGNT = AGNT;
        this.NTAM = NTAM;
        this.TOQT = TOQT;
        this.SAAM = SAAM;
        this.LOCD = LOCD;
        this.RASN = RASN;
        this.EXAT = EXAT;
        this.FUSC = FUSC;
        this.PYME = PYME;
        this.PURC = PURC;
        this.TEOR = TEOR;
        this.TLEX = TLEX;
        this.TLIN = TLIN;
        this.PUSL = PUSL;
        this.PUST = PUST;
    }
    
}
