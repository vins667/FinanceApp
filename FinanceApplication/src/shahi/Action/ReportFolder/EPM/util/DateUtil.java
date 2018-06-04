package shahi.Action.ReportFolder.EPM.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat fromDate=new SimpleDateFormat("MM/dd/yyyy");
	private static SimpleDateFormat toDate=new SimpleDateFormat("yyyyMMdd");
	//private static SimpleDateFormat ddmmyyyy=new SimpleDateFormat("ddMMyyyy");
	public  static String  convertToDB2DateFormat(String date){
		
		try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getFinancialYear(){
		 return getCurrentFinancialYear()+"0401";
		//return "20180201";
	}
	
	public static String convertToDDMMYYYY(String date){
		String newDate=date.substring(6)+"/"+date.substring(4, 6)+"/"+date.substring(0, 4);
		return newDate;
	}
	public static String converToDDMMMYYYY(Date date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		 try {
			 return toDate.format(fromDate.parse(fromDate.format(date)));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	public static String converToDDMMMYYYY(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	public static String converFromDDMMYYYY(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd-MM-yyyy");
		 SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yyyy");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	public static String converToDDMMYY(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd-MM-yyyy");
		 SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	public static String converToDDMMYYYY(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat toDate=new SimpleDateFormat("dd-MM-yyyy");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		 
	}
	public static String getOracleDate(Date date){
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		return toDate.format(date);
	}
	public static String converToYYYYMMDD(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat toDate=new SimpleDateFormat("yyyyMMdd");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getSFLDateFormat(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat toDate=new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String convertFromYYYYMMDD(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat toDate=new SimpleDateFormat("dd-MM-yyyy");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getCurrentDateInYYYYMMDD(){
		Date date=new Date();
		return toDate.format(date);
	}
	public static String getCurrentDateInString(){
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		Date date=new Date();
		return toDate.format(date);
	}
	public static Date getCurrentDateInDDMMMYY(){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd-MMM-yy");
		 Date date=null;
		 try {
			 date=fromDate.parse(fromDate.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return date;
	}
	public static Date getCurrentDateInDDMMMYY(String date){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd/MM/yy");
		 SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		 Date date1=null;
		 try {
			 date1=fromDate.parse(fromDate.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return date1;
	}	
	public static  String convertToGSTFormat(String date ){
		 SimpleDateFormat fromDate=new SimpleDateFormat("dd-MM-yyyy");
		 SimpleDateFormat toDate=new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date getOracleDate(String date){
		SimpleDateFormat fromDate=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		try {
			return toDate.parse(toDate.format(fromDate.parse(date)));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date convertFromDDMMYY(String date){
		SimpleDateFormat fromDate=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yyyy");
		try {
			return toDate.parse(toDate.format(fromDate.parse(date)));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}	
	public static Date convertToDDMMMYY(String date){
		SimpleDateFormat fromDate=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yyyy");
		try {
			return toDate.parse(toDate.format(fromDate.parse(date)));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	public static Date convertToDDMMMYY(Date date){
		SimpleDateFormat fromDate=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		try {
			return toDate.parse(toDate.format(fromDate.parse(fromDate.format(date))));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}	
	public static Date convertFromYYMMDD(String date){
		SimpleDateFormat fromDate=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yyyy");
		try {
			return toDate.parse(toDate.format(fromDate.parse(date)));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}	
	public static String covertToDDMMMYYYY(String date){
		SimpleDateFormat fromDate=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat toDate=new SimpleDateFormat("dd-MMM-yy");
		try {
			return toDate.format(fromDate.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getCurrentFinancialYear(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String date=sdf.format(new Date());
		String []temp=date.split("-");
		int month=Integer.parseInt(temp[1]);
		int year=Integer.parseInt(temp[2]);
		if(month==1||month==2 ||month==3){
			return (--year);
		}
		return year;
	}
	public static int getCurrentFinancialYear(String date){
		String []temp=date.split("/");
		int month=Integer.parseInt(temp[1]);
		int year=Integer.parseInt(temp[2]);
		if(month==1||month==2 ||month==3){
			return (--year);
		}
		return year;
	}
}
