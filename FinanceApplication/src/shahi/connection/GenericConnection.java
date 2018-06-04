package shahi.connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class GenericConnection {
 	private static ApplicationContext ctx;
    static{
    	ctx=new ClassPathXmlApplicationContext("scanconnection.xml");
    }
 	private  GenericConnection(){
 	}
 	
 	
 	public static JdbcTemplate getShahiWebConnection(){
 		return (JdbcTemplate)ctx.getBean("shahiWeb");
 	}
 	public static JdbcTemplate getDB2Connection(){
 		return (JdbcTemplate)ctx.getBean("db2");
 	}
 	
 	public static JdbcTemplate getMovexBI(){
 		return (JdbcTemplate)ctx.getBean("movexInsert");
 	}
 	
 	public static JdbcTemplate getOldMovex(){
 		return (JdbcTemplate)ctx.getBean("oldScan");
 	}
 	
 	public static SimpleJdbcInsert getNewScannerInsert(){
 		return (SimpleJdbcInsert)ctx.getBean("newScanInsert");
 	}
}
