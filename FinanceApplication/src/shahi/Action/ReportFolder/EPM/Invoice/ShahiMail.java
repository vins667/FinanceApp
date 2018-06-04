package shahi.Action.ReportFolder.EPM.Invoice;

import java.util.Arrays;
import java.util.ResourceBundle;

public class ShahiMail {

	private String subject;
	private String content;
	private String from;
	private String[] recipients={"Rupanjan.bhattacharyya@shahi.co.in","ananthakrishna.rao@shahi.co.in","kuldeep.anandsingh@shahi.co.in","vinaykumar.munna@shahi.co.in"};
	private String []attachments;
	private String fromname ;
	private  ResourceBundle aResourcBundle = null;
	public ShahiMail(){
		subject="Auto approval bill process under Rs.12500/-";
		content=MailBody.getMailBody();
		from="movex@shahi.co.in";
		aResourcBundle=ResourceBundle.getBundle("shahi.Action.database.app");
		attachments=new String[]{aResourcBundle.getString("reportPdfPath")+"/voucher.txt"};
		
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	
	public String[] getAttachments() {
		return attachments;
	}
	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}
	public String[] getRecipients() {
		return recipients;
	}
	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}
	@Override
	public String toString() {
		return "ShahiMail [subject=" + subject + ", content=" + content + ", from=" + from + ", recipients="
				+ Arrays.toString(recipients) + ", attachments=" + Arrays.toString(attachments) + ", fromname="
				+ fromname + "]";
	}
	
}
