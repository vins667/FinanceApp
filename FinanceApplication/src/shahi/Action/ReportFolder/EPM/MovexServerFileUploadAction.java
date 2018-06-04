package shahi.Action.ReportFolder.EPM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.Common.Ftpfileupload;
import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.service.PopulateListService;



public class MovexServerFileUploadAction extends ActionSupport {

	private List<Code>cmpList;
	private List<Code>fileList=new ArrayList<>();
	private File unfile;
	
	private String division;
	private String fileType;
	private ResourceBundle aResourcBundle;
	private Map<String,String>fileNameMapping;
	public MovexServerFileUploadAction(){
		aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
	}
	@Override
	public String execute(){
		loadAllList();
		return SUCCESS;
	}
	private PopulateListService getService(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext());
		PopulateListService service=(PopulateListService)context.getBean(PopulateListService.class);
		return service;
	}
	private void loadAllList(){
		setCmpList(getService().getAllCompanies());
		setFileList(getService().getAllFileTypes(division));
	}
   
	public String uploadFile(){
		 loadAllList();
		 FileWriter fw=null;
		 File file=null;
		 String line=null;
		try {
			
			String fileUploadPath=aResourcBundle.getString("reportPdfPath")+getFileType();
			file=new File(fileUploadPath);
			if(file.exists()){
				file.delete();
			}
			fw = new FileWriter(file);
			InputStream input=new FileInputStream(getUnfile());
			BufferedReader br=new BufferedReader(new InputStreamReader(input));
			
			while((line=br.readLine())!=null){
				fw.write(line);
			}
			
			br.close();
			fw.close();
			if(uploadFileToFtp(file).equals("OK")){
				addActionError("File has been uploaded on server");
			}else{
				addActionError("Unable to upload file on server.Kindly try after some time");
			}
			
		} catch (Exception  e) {
			addActionError(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String uploadFileToFtp(File file) throws Exception {
		 String host=aResourcBundle.getString("MIIP");
         String cuser = aResourcBundle.getString("UserNameDB2");
         String cpass = aResourcBundle.getString("PasswordDB2");
         String filepath="M3BE1512/env/M3BE_15.1_UAT/transfer/GLS850MI/"+file.getName();
         String ftpout="";
        
         Ftpfileupload fileobj = new Ftpfileupload();
         ftpout= fileobj.FtpFileCopy(host, cuser, cpass, file.getAbsolutePath(), filepath);
		
		return ftpout;
	}
	public List<Code> getCmpList() {
		return cmpList;
	}

	public void setCmpList(List<Code> cmpList) {
		this.cmpList = cmpList;
	}

	public List<Code> getFileList() {
		return fileList;
	}

	public void setFileList(List<Code> fileList) {
		this.fileList = fileList;
	}

	public File getUnfile() {
		return unfile;
	}

	public void setUnfile(File unfile) {
		this.unfile = unfile;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Map<String, String> getFileNameMapping() {
		return fileNameMapping;
	}
	public void setFileNameMapping(Map<String, String> fileNameMapping) {
		this.fileNameMapping = fileNameMapping;
	}


	
}
