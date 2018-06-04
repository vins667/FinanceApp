package shahi.connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class CorrectInvoiceConnection {
 	private static ApplicationContext ctx;
    static{
    	ctx=new ClassPathXmlApplicationContext("correctinvoiceconnection.xml");
    }
 	private  CorrectInvoiceConnection(){
 	}
 	
 	public static JdbcTemplate getDB2Connection(){
 		return (JdbcTemplate)ctx.getBean("db2");
 	}
 	
 	
}
