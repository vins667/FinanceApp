/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.compcal;

/**
 *
 * @author Ranjeet Singh
 */
import shahi.Action.Sampling.FabricrequestKnits.*;
import shahi.Action.Sampling.*;
import com.opensymphony.xwork2.ActionContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;
import shahi.Action.Sampling.Beans.StyleBean;
import shahi.Action.compcal.upload.FileNewBean;
import shahi.Action.database.connection;
 
public class fileUploadAction extends ActionSupport{
 
	private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadContentType = new ArrayList<String>();
	private List<String> fileUploadFileName = new ArrayList<String>();
        private String COMPLIANCE_ID;
        private String DEL_ID;
        private String DISPLAY_FLAG;
       
        private List<FileNewBean> filelist = new ArrayList<FileNewBean>();
       
       
        @Override
        public String execute() throws Exception {
 System.out.println(COMPLIANCE_ID);
        int flag = 0;
  
        Connection conn = null;
        try {
            conn = new connection().getConnection();
            

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        ResultSet result1 = null;
        ResultSet result2 = null;

        try {
            Map session = ActionContext.getContext().getSession();
            String usrid = (String) session.get("sessUserId");
           System.out.println(COMPLIANCE_ID);
                    ActionContext ac = ActionContext.getContext();
                     ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                     File uploadDir = new File(sc.getRealPath("/shahiwebpages/compcal/upload/"));
                     if (uploadDir.exists() == false) {
                        uploadDir.mkdirs();
                       }
            
               if(DEL_ID!=null && DEL_ID.length()>0)    
               {
               stat1 = conn.prepareStatement("delete from CC_COMPLIANCE_CALENDER_File where ID=?");
               stat1.setString(1, DEL_ID);
               stat1.executeUpdate();
               
               }
                     
                     
          
                     
                     
                     
            if (COMPLIANCE_ID != null && COMPLIANCE_ID.length() > 0) {
                if (fileUpload != null && fileUpload.size()>0) {
                   
                    int i=0;
                    for (File file : fileUpload) {

                        File fileToCreate = new File(uploadDir, COMPLIANCE_ID+"-"+fileUploadFileName.get(i));
                        FileUtils.copyFile(file, fileToCreate);
                        stat1=conn.prepareStatement("insert into CC_COMPLIANCE_CALENDER_File(ID,COMPLIANCE_ID,FILE_NAME,USER_ID,TDATE,MUSER_ID,MDATE,FILE_SIZE) values(CC_COMPLIANCE_CALENDER_File_Sq.nextval,?,?,?,sysdate,?,sysdate,?)");
                        stat1.setString(1, COMPLIANCE_ID);
                         stat1.setString(2, COMPLIANCE_ID+"-"+fileUploadFileName.get(i));
                         stat1.setString(3, usrid);
                         stat1.setString(4, usrid);
                         stat1.setString(5, "0");
                        stat1.executeUpdate();
                       
                        i++;
                    }
                }
            }
            
            System.out.println(COMPLIANCE_ID);
              stat1 = conn.prepareStatement("Select ID,COMPLIANCE_ID,FILE_NAME,FILE_SIZE FROM CC_COMPLIANCE_CALENDER_File where COMPLIANCE_ID=?  order by FILE_NAME");
              stat1.setString(1, COMPLIANCE_ID);
              result1 = stat1.executeQuery();
            while (result1.next()) {
                filelist.add(new FileNewBean(result1.getString("ID"),result1.getString("COMPLIANCE_ID"), result1.getString("FILE_NAME"), result1.getString("FILE_NAME").substring(result1.getString("FILE_NAME").indexOf("-")+1,result1.getString("FILE_NAME").length())));
            }
              
             

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : execute fileUploadAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : execute fileUploadAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
               
                if (conn != null) {
                    conn.close();
                }
                result1 = null;
                stat1 = null;
               
                conn = null;

            } catch (Exception e) {
                System.out.print("File Name : execute fileUploadAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }

     
        if (flag == 0) {
            addActionMessage("Record Not Save !!");
        }
        return SUCCESS;
    }
        
        
    
      public String upload() throws Exception{
 
	    for (File file: fileUpload) {
	        System.out.println("File :" + file);
	    }
 
	    for (String fileName: fileUploadFileName) {
	        System.out.println("Filename : " + fileName);
	    }
 
	    for (String fileContentType: fileUploadContentType) {
	        System.out.println("File type : " + fileContentType);
	    }
 
	    return SUCCESS;
 
	}  
        
        
        
        
	public List<File> getFileUpload() {
		return fileUpload;
	}
 
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}
 
	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}
 
	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
 
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

    public String getCOMPLIANCE_ID() {
        return COMPLIANCE_ID;
    }

    public void setCOMPLIANCE_ID(String COMPLIANCE_ID) {
        this.COMPLIANCE_ID = COMPLIANCE_ID;
    }

    public String getDEL_ID() {
        return DEL_ID;
    }

    public void setDEL_ID(String DEL_ID) {
        this.DEL_ID = DEL_ID;
    }

    public List<FileNewBean> getFilelist() {
        return filelist;
    }

    public void setFilelist(List<FileNewBean> filelist) {
        this.filelist = filelist;
    }

    public String getDISPLAY_FLAG() {
        return DISPLAY_FLAG;
    }

    public void setDISPLAY_FLAG(String DISPLAY_FLAG) {
        this.DISPLAY_FLAG = DISPLAY_FLAG;
    }
 
	

 
}