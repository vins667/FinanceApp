/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class BuyerBean {
    private String BUYER;
    private String BUYER_ADDRESS;
    private String BUYER_CODE;
    private String BUYER_ID;
    private String buyerName;
    private String addressId;
    private String destinationState;
    
     public BuyerBean(String BUYER, String BUYER_ADDRESS,String BUYER_CODE,String BUYER_ID) {
		super();
		this.BUYER = BUYER;
		this.BUYER_ADDRESS = BUYER_ADDRESS;
		this.BUYER_CODE = BUYER_CODE;
                this.BUYER_ID=BUYER_ID;
	}
	public BuyerBean() {
		super();
	}

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getBUYER_ADDRESS() {
        return BUYER_ADDRESS;
    }

    public void setBUYER_ADDRESS(String BUYER_ADDRESS) {
        this.BUYER_ADDRESS = BUYER_ADDRESS;
    }

    public String getBUYER_CODE() {
        return BUYER_CODE;
    }

    public void setBUYER_CODE(String BUYER_CODE) {
        this.BUYER_CODE = BUYER_CODE;
    }

    public String getBUYER_ID() {
        return BUYER_ID;
    }

    public void setBUYER_ID(String BUYER_ID) {
        this.BUYER_ID = BUYER_ID;
    }
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getAddressId() {
		return addressId;
	}
	public String getDestinationState() {
		return destinationState;
	}
	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	@Override
	public String toString() {
		return "BuyerBean [BUYER=" + BUYER + ", BUYER_ADDRESS=" + BUYER_ADDRESS + ", BUYER_CODE=" + BUYER_CODE
				+ ", BUYER_ID=" + BUYER_ID + ", buyerName=" + buyerName + ", addressId=" + addressId
				+ ", destinationState=" + destinationState + "]";
	}
	
}
