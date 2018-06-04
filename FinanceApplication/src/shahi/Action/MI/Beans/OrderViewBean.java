/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

//import com.symphony.util.CRMLogHelper;
//import com.symphony.util.HelperConstantsFnl;
import java.util.Comparator;

/**
 *
 * @author User.
 */

public class OrderViewBean implements Comparable<OrderViewBean> {

    private String Facility;
    private String Whs;
    private String OrderDate;
    private String DelyDate;
    private String OrderNo;
    private String TempOrderNo;
    private String PymtTerms;
    private String PymntMtd;
    private String DelyMtd;
    private String DelyTerms;
    private String BuyerPO;
    private String Qty;
    private String FOB;
    private String CUR;
    private String Price;
    private String Dest;
    private String AddrNo;
    private String LowerStatus;
    private String HigherStatus;
    private String Buyer;
    private String BuyerStyle;
    private String BuyerAddr;
    private String OrderType;
    private String SalesPerson;
    private String packagingTerms;
    private String fre1="";
    private String YREF;
    private String APPROVEFLAG;
    private String UNAPPROVE;
    private String DLSP;
    private String DELIVERYSPECDESC;
  
    private String INVQTY;

    public String getDELIVERYSPECDESC() {
        return DELIVERYSPECDESC;
    }

    public void setDELIVERYSPECDESC(String DELIVERYSPECDESC) {
        this.DELIVERYSPECDESC = DELIVERYSPECDESC;
    }
    
    

    public String getFre1() {
        return fre1;
    }

    public void setFre1(String fre1) {
        this.fre1 = fre1;
    }

    public String getSalesPerson() {
        return SalesPerson;
    }

    public void setSalesPerson(String SalesPerson) {
        this.SalesPerson = SalesPerson;
    }

    public String getBuyerAddr() {
        return BuyerAddr;
    }

    public void setBuyerAddr(String BuyerAddr) {
        this.BuyerAddr = BuyerAddr;
    }

    public String getBuyerStyle() {
        return BuyerStyle;
    }

    public void setBuyerStyle(String BuyerStyle) {
        this.BuyerStyle = BuyerStyle;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public String getBuyer() {
        return Buyer;
    }

    public void setBuyer(String Buyer) {
        this.Buyer = Buyer;
    }

    public String getAddrNo() {
        return AddrNo;
    }

    public void setAddrNo(String AddrNo) {
        this.AddrNo = AddrNo;
    }

    public String getBuyerPO() {
        return BuyerPO;
    }

    public void setBuyerPO(String BuyerPO) {
        this.BuyerPO = BuyerPO;
    }

    public String getCUR() {
        return CUR;
    }

    public void setCUR(String CUR) {
        this.CUR = CUR;
    }

    public String getDelyDate() {
        return DelyDate;
    }

    public void setDelyDate(String DelyDate) {
        this.DelyDate = DelyDate;
    }

    public String getDelyMtd() {
        return DelyMtd;
    }

    public void setDelyMtd(String DelyMtd) {
        this.DelyMtd = DelyMtd;
    }

    public String getDelyTerms() {
        return DelyTerms;
    }

    public void setDelyTerms(String DelyTerms) {
        this.DelyTerms = DelyTerms;
    }

    public String getDest() {
        return Dest;
    }

    public void setDest(String Dest) {
        this.Dest = Dest;
    }

    public String getFOB() {
        return FOB;
    }

    public void setFOB(String FOB) {
        this.FOB = FOB;
    }

    public String getFacility() {
        return Facility;
    }

    public void setFacility(String Facility) {
        this.Facility = Facility;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getPymntMtd() {
        return PymntMtd;
    }

    public void setPymntMtd(String PymntMtd) {
        this.PymntMtd = PymntMtd;
    }

    public String getPymtTerms() {
        return PymtTerms;
    }

    public void setPymtTerms(String PymtTerms) {
        this.PymtTerms = PymtTerms;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String Qty) {
        this.Qty = Qty;
    }

    public String getWhs() {
        return Whs;
    }

    public void setWhs(String Whs) {
        this.Whs = Whs;
    }

    public String getTempOrderNo() {
        return TempOrderNo;
    }

    public void setTempOrderNo(String TempOrderNo) {
        this.TempOrderNo = TempOrderNo;
    }

    public String getHigherStatus() {
        return HigherStatus;
    }

    public void setHigherStatus(String HigherStatus) {
        this.HigherStatus = HigherStatus;
    }

    public String getLowerStatus() {
        return LowerStatus;
    }

    public void setLowerStatus(String LowerStatus) {
        this.LowerStatus = LowerStatus;
    }

    public String getPackagingTerms() {
        return packagingTerms;
    }

    public void setPackagingTerms(String packagingTerms) {
        this.packagingTerms = packagingTerms;
    }

    public String getYREF() {
        return YREF;
    }

    public void setYREF(String YREF) {
        this.YREF = YREF;
    }

    public String getAPPROVEFLAG() {
        return APPROVEFLAG;
    }

    public void setAPPROVEFLAG(String APPROVEFLAG) {
        this.APPROVEFLAG = APPROVEFLAG;
    }

    public String getUNAPPROVE() {
        return UNAPPROVE;
    }

    public void setUNAPPROVE(String UNAPPROVE) {
        this.UNAPPROVE = UNAPPROVE;
    }

    public String getDLSP() {
        return DLSP;
    }

    public void setDLSP(String DLSP) {
        this.DLSP = DLSP;
    }

    public String getINVQTY() {
        return INVQTY;
    }

    public void setINVQTY(String INVQTY) {
        this.INVQTY = INVQTY;
    }




    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if ((obj instanceof OrderViewBean) && ((OrderViewBean) obj).getOrderNo().equals(this.OrderNo)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        try {
            hash = Integer.parseInt(this.OrderNo);
        } catch (NumberFormatException e) {
            hash = 1;
        } catch (Exception e) {
           // CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), e, CRMLogHelper.STR_ERROR_PRIORITY);
        }
        return hash;
    }

    public int compareTo(OrderViewBean ovb) {
        return this.getOrderNo().compareTo(ovb.getOrderNo());
    }


     public static // <editor-fold defaultstate="collapsed" desc="comment">
             Comparator// </editor-fold>
<OrderViewBean> SortByStyleComparator
                          = new Comparator<OrderViewBean>() {

	    public int compare(OrderViewBean o, OrderViewBean o1) {

	      String StyleName1 = o.getBuyerStyle().toUpperCase();
	      String StyleName2 = o1.getBuyerStyle().toUpperCase();

	      //ascending order
	      return StyleName1.compareTo(StyleName2);

	      //descending order
	      //return fruitName2.compareTo(fruitName1);
	    }

	};

           public static // <editor-fold defaultstate="collapsed" desc="comment">
             Comparator// </editor-fold>
<OrderViewBean> DescSortByStyleComparator
                          = new Comparator<OrderViewBean>() {

	    public int compare(OrderViewBean o, OrderViewBean o1) {

	      String StyleName1 = o.getBuyerStyle().toUpperCase();
	      String StyleName2 = o1.getBuyerStyle().toUpperCase();

	      //descending order
	      return StyleName2.compareTo(StyleName1);
	    }

	};

        public static // <editor-fold defaultstate="collapsed" desc="comment">
             Comparator// </editor-fold>
<OrderViewBean> SortByBuyerPOComparator
                          = new Comparator<OrderViewBean>() {

	    public int compare(OrderViewBean o, OrderViewBean o1) {

	      String StyleName1 = o.getBuyerPO().toUpperCase();
	      String StyleName2 = o1.getBuyerPO().toUpperCase();

	      //ascending order
	      return StyleName1.compareTo(StyleName2);

	      //descending order
	      //return fruitName2.compareTo(fruitName1);
	    }

	};

           public static // <editor-fold defaultstate="collapsed" desc="comment">
             Comparator// </editor-fold>
<OrderViewBean> DescSortByBuyerPOComparator
                          = new Comparator<OrderViewBean>() {

	    public int compare(OrderViewBean o, OrderViewBean o1) {

	      String StyleName1 = o.getBuyerPO().toUpperCase();
	      String StyleName2 = o1.getBuyerPO().toUpperCase();

	      //descending order
	      return StyleName2.compareTo(StyleName1);
	    }

	};


                public static // <editor-fold defaultstate="collapsed" desc="comment">
             Comparator// </editor-fold>
<OrderViewBean> SortByDelyDateComparator
                          = new Comparator<OrderViewBean>() {

	    public int compare(OrderViewBean o, OrderViewBean o1) {

	      String StyleName1 = o.getDelyDate().toUpperCase();
	      String StyleName2 = o1.getDelyDate().toUpperCase();

	      //ascending order
	      return StyleName1.compareTo(StyleName2);

	      //descending order
	      //return fruitName2.compareTo(fruitName1);
	    }

	};

           public static // <editor-fold defaultstate="collapsed" desc="comment">
             Comparator// </editor-fold>
<OrderViewBean> DescSortByDelyDateComparator
                          = new Comparator<OrderViewBean>() {

	    public int compare(OrderViewBean o, OrderViewBean o1) {

	      String StyleName1 = o.getDelyDate().toUpperCase();
	      String StyleName2 = o1.getDelyDate().toUpperCase();

	      //descending order
	      return StyleName2.compareTo(StyleName1);
	    }

	};
}
