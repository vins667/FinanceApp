package shahi.Action.ReportFolder.EPM.Invoice;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VoucherListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Called contextInitialized() ");
		ApplicationContext ctx=new ClassPathXmlApplicationContext("schedular.xml");
		/*UpdateShahiVoucherFunction update=ctx.getBean(UpdateShahiVoucherFunction.class);
		update.updateInvoice();*/
		
	}

}
