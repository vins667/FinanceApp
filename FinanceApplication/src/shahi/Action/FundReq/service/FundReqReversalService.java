package shahi.Action.FundReq.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import shahi.Action.FundReq.Query;
import shahi.Action.FundReq.Beans.FaPayrollTypeMastBean;
import shahi.Action.FundReq.Beans.FundReqBean;
import shahi.Action.FundReq.dao.FundReqReversalDao;

public class FundReqReversalService {

	@Autowired
	private FundReqReversalDao fundReqReversalDao;
   
	public List<FaPayrollTypeMastBean>getAllRequests(){
		return fundReqReversalDao.loadRequesTypes();
	}
	
	public List<FaPayrollTypeMastBean>getAllPayList(){
		return fundReqReversalDao.loadPayList();
	}
	
	public List<FundReqBean>getAllFundRequests(Query query){
		return fundReqReversalDao.loadAllFundRequests(query);
	}
	public List<FundReqBean>getAllFundRequestsForUTRUpdation(String fromDate,String toDate){
		return fundReqReversalDao.loadAllFundRequestsForUTRUpdation(fromDate,toDate);
	}
	
	public boolean validateVoucher(String vtype,String vono,String year){
		return fundReqReversalDao.validateVoucher(vtype, vono, year);
	}
	@Transactional
	public boolean reverse(List<FundReqBean> list,String userId){
		
		if(list!=null && list.size()==1){
			FundReqBean bean=list.get(0);
			if(fundReqReversalDao.cancelRequest(bean,userId)){
				//sendMail(bean,userId);
				return true;
			}
		}
		
		return false;
		
	}
	
	public List<FundReqBean> getRequestByNo(String reqNo){
		return fundReqReversalDao.getRequestByNo(reqNo);
	}
	private void sendMail(FundReqBean req,String userId){
		String emailId=fundReqReversalDao.getEmailId(userId);
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailId);
        email.setSubject("Fund Request Cancellation No:"+req.getREQNO());
        email.setText(getMailBody());
         
        // sends the e-mail
        //mailSender.send(email);
	}
	
	public void updateUTRNo(FundReqBean update) throws RuntimeException{
		fundReqReversalDao.updateUTRNo(update);
	}
	private String getMailBody(){
		String fromname="movex@shahi.co.in";
		String messageBodyText=null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		messageBodyText="<html>";
		messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
		messageBodyText += "<body bgcolor=#95b174>";
		messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
		messageBodyText+="Dear Sir,<br/>";
		messageBodyText += "This is to inform you subject fund request  has been cancelled  on Dated:"+format.format(new Date());
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
