package shahi.Action.FundReq.Beans;

public class UTRNoAlreadyExist extends RuntimeException {

	private String message;
	
	public UTRNoAlreadyExist(String message){
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UTRNoAlreadyExist [message=" + message + "]";
	}
	
	
}
