package shahi.Action.ReportFolder.EPM.Invoice;

public class MailHelper {

	private static class InnerMailHelper{
		private static final ShahiMail shahiMail=new ShahiMail();
		
	}
	public static ShahiMail getShahiMail(){
		return InnerMailHelper.shahiMail;
	}
}
