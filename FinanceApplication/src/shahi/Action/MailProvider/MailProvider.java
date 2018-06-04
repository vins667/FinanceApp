package shahi.Action.MailProvider;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import com.sun.mail.smtp.SMTPMessage;
import java.util.ResourceBundle;


/**
 *
 * @author Ranjeet Gautam
 */
public class MailProvider {
	ResourceBundle aResourcBundle = null;
	public MailProvider () {
		aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
	}

	private String getValue(String key) {
		return aResourcBundle.getString(key);
	}

	private int getIntValue(String key) {
		return Integer.parseInt(getValue(key));
	}
	/*

private static final String SMTP_HOST_NAME = "172.17.3.122";
private static final String SMTP_AUTH_USER = "";
private static final String SMTP_AUTH_PWD = "";
private static final String mailport="26";
private static final String EnvelopeFrom="bal.singh@shahi.co.in";
	 */

	private static final String SMTP_AUTH_USER = "";
	private static final String SMTP_AUTH_PWD = "";

	/*private static final String emailMsgTxt = "This is aTest mail for Mailing program using java";
private static final String emailSubjectTxt = "Good One!!!!!";
private static final String emailFromAddress = "ranjeet.gautam@shahi.co.in";

// Add List of Email address to who email needs to be sent to
private static final String[] emailList = {"ranjeet.gautam@shahi.co.in"};

public static void main(String args[]) throws Exception
{

MailProvider smtpMailSender = new MailProvider();
smtpMailSender.postMail( emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress,null);
System.out.println("Sucessfully Sent mail to All Users");
}
	 */
	public void postMail( String recipients[ ], String subject,
			String message , String from,String[] attachments) throws MessagingException
	{

		boolean debug = false;
		boolean isHTML = true;

		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", getValue("mailhost"));
		props.put("mail.smtp.port",getValue("mailport"));
		props.put("mail.smtp.auth", "false");

		Authenticator auth = new SMTPAuthenticator();
		//Session session = Session.getDefaultInstance(props, auth);
		Session session = Session.getInstance(props, auth);
		session.setDebug(debug);

		// create a message
		SMTPMessage msg = new SMTPMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		msg.setEnvelopeFrom(getValue("EnvelopeFrom"));
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++)
		{
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);


		// Setting the Subject and Content Type
		msg.setSubject(subject);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(message);
		if (isHTML)
		{

			messageBodyPart.setContent(message, "text/html");
		}
		else
		{
			messageBodyPart.setContent(message, "text/plain");
		}
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		if(attachments!=null)
		{
			addAtachments(attachments, multipart);
		}

		msg.setContent(multipart);
		Transport.send(msg);
	}

	protected void addAtachments(String[] attachments, Multipart multipart)
			throws MessagingException, AddressException
	{
		for(int i = 0; i<= attachments.length -1; i++)
		{
			String filename = attachments[i];
			File filetemppth = new File(attachments[i]);

			if(filetemppth.exists())
			{
				String fileNameadd= filetemppth.getName();
				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(filename);
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName(fileNameadd);
				multipart.addBodyPart(attachmentBodyPart);
			}
		}
	}

	/*
SimpleAuthenticator is used to do simple authentication
when the SMTP server requires it.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

		public PasswordAuthentication getPasswordAuthentication()
		{
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}



}