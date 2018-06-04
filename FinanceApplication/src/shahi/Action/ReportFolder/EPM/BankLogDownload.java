package shahi.Action.ReportFolder.EPM;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BankLogDownload extends ActionSupport{

	private String fileDate;
	public static ResourceBundle aResourcBundle = null;
	
	public BankLogDownload(){
		aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
	}
	
	public String execute(){
		return SUCCESS;
	}
	public String hdfc(){
		return "HDFC";
	}
	public void downloadHDFCFile() throws IOException{
		String host=aResourcBundle.getString("HBLIP");
		int port=Integer.parseInt(aResourcBundle.getString("HBLPORT"));
		String username=aResourcBundle.getString("UserNameHBL");
	    String password=aResourcBundle.getString("PasswordHBL");
	    String filePath=aResourcBundle.getString("HFilePath");
	    String file=filePath+"Log_"+getFileDate()+".log";
	    HttpServletResponse response =ServletActionContext.getResponse();
	    FTPClient client = new FTPClient();
	    
	    try {
			client.connect(host, port);
			boolean isConnected=client.login(username, password);
			if(isConnected){
				InputStream in=client.retrieveFileStream(file);
			    response.setHeader("Content-disposition", "attachment;filename="+"Log_"+getFileDate()+".log");
				response.setContentType("application/jpg");
				OutputStream out = response.getOutputStream();
				
				byte[] content = new byte[1024];
				int readByteLength = 0;
				while ((readByteLength = in.read(content)) != -1)
					out.write(content, 0, readByteLength);
				in.close();
				out.flush();
			}
		}catch(FileNotFoundException fnf){
			fnf.printStackTrace();
		}
	    catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			client.disconnect();
		}
	}
	public void downloadYesFile() throws IOException{
		String host=aResourcBundle.getString("YBLIP");
		int port=Integer.parseInt(aResourcBundle.getString("YBLPORT"));
		String username=aResourcBundle.getString("UserNameYBL");
	    String password=aResourcBundle.getString("PasswordYBL");
	    String filePath=aResourcBundle.getString("YFilePath");
	    String file=filePath+"Log_"+getFileDate()+".log";
	    HttpServletResponse response =ServletActionContext.getResponse();
	    FTPClient client = new FTPClient();
	    
	    try {
			client.connect(host, port);
			boolean isConnected=client.login(username, password);
			if(isConnected){
				InputStream in=client.retrieveFileStream(file);
			    response.setHeader("Content-disposition", "attachment;filename="+"Log_"+getFileDate()+".log");
				response.setContentType("application/jpg");
				OutputStream out = response.getOutputStream();
				
				byte[] content = new byte[1024];
				int readByteLength = 0;
				while ((readByteLength = in.read(content)) != -1)
					out.write(content, 0, readByteLength);
				in.close();
				out.flush();
			}
		}catch(FileNotFoundException fnf){
			fnf.printStackTrace();
		}
	    catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			client.disconnect();
		}
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
}
