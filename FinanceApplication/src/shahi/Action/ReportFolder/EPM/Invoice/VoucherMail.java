package shahi.Action.ReportFolder.EPM.Invoice;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import shahi.Action.MailProvider.MailProvider;

public class VoucherMail {
	private final VoucherDao dao=new VoucherDao();
	public void sendMail(){
		ShahiMail mail=MailHelper.getShahiMail();
		MailProvider mailProvider=new MailProvider();
		List<Voucher>voucherStatus=dao.loadAllVouchers();
		GenerateCSV.generate(voucherStatus);
		
		try {
			for(Voucher voucher:voucherStatus){
				dao.updateVoucherStatus(voucher);
			}
			dao.closeConnection();
			mailProvider.postMail(mail.getRecipients(), mail.getSubject(), mail.getContent(), mail.getFrom(),mail.getAttachments());
			System.out.println("Mail has been sent to respective users on  Dated: "+new Date());
		} catch (SQLException e) {
			System.out.println("Error while updating status: "+new Date());
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println("Send Mail Error"+ex.getMessage()+new Date());
		}

	}
}
