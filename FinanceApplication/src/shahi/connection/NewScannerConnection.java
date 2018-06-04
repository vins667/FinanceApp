package shahi.connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class NewScannerConnection {
 	private static ApplicationContext ctx;
    static{
    	ctx=new ClassPathXmlApplicationContext("newscannerconnection.xml");
    }
 	private  NewScannerConnection(){
 	}
 	
 	
 	public static JdbcTemplate getOldMovex(){
 		return (JdbcTemplate)ctx.getBean("oldScan");
 	}
 	
 	public static SimpleJdbcInsert getNewScannerInsert(){
 		return (SimpleJdbcInsert)ctx.getBean("newScanInsert");
 	}
}
