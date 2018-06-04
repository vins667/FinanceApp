package shahi.Action.ReportFolder.EPM.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.ResourceBundle;

public class GenerateCSV {
	
	public static void generate(List<Voucher>voucherList) {
		 FileWriter fw=null;
		 File file=null;
	      ResourceBundle aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
		   String fileUploadPath;
	
		try {
			fileUploadPath=aResourcBundle.getString("reportPdfPath")+"voucher.txt";
			file=new File(fileUploadPath);
			if(file.exists()){
				file.delete();
			}
			fw = new FileWriter(file);
			
			String header="Company,Division,Year,Voucher Type,Voucher No,Supplier,Bill No,Invoice Date,Invoice Amount,Uesr Id\n";
			fw.write(header);
			for(Voucher voucher:voucherList){
				fw.write(getLine(voucher)+"\n");
			}
			System.out.println("File has been write successfully");
			if(fw!=null){
				fw.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static String getLine(Voucher voucher){
		return voucher.convertToString();
	}
}
