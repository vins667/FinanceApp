package shahi.Action.ReportFolder.EPM.Invoice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MailBody {
    
	
	private MailBody(){
		
	}
	public static String getMailBody(){
		String fromname="movex@shahi.co.in";
		String messageBodyText=null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		messageBodyText="<html>";
		messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
		messageBodyText += "<body bgcolor=#95b174>";
		messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
		messageBodyText+="Dear Sir,<br/>";
		messageBodyText += "This is to inform you that attached bills are auto approved in Movex on Dated:"+format.format(new Date());
		messageBodyText += "</br></br>";
		messageBodyText += "</font>";

		messageBodyText += "<table cellpadding='4' width='600'>";
		messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
		messageBodyText += "<tr style='font-size:14px;'><td>"+fromname+"</td></tr>";
		messageBodyText += "</table>";
		messageBodyText += "</body>";
		messageBodyText += "</html>";
		return messageBodyText;
	}
}
