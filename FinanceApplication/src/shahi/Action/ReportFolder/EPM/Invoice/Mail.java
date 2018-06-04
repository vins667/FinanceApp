package shahi.Action.ReportFolder.EPM.Invoice;

import shahi.Action.MailProvider.MailProvider;

public interface Mail {

	public boolean postMail(MailProvider mailProvider);
}
