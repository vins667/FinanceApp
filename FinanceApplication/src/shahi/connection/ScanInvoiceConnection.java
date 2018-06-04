package shahi.connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ScanInvoiceConnection {
 	private static ApplicationContext ctx;
    static{
    	ctx=new ClassPathXmlApplicationContext("scanconnection.xml");
    }
 	private  ScanInvoiceConnection(){
 	}
 	public static JdbcTemplate getScanConnection(){
 		return (JdbcTemplate)ctx.getBean("scan");
 	}
 	
 	public static JdbcTemplate getDB2Connection(){
 		return (JdbcTemplate)ctx.getBean("db2");
 	}
 	public static NamedParameterJdbcTemplate getSimpleJdbcInsert(){
 		return (NamedParameterJdbcTemplate)ctx.getBean("namedTemplate");
 	}
 	
}
