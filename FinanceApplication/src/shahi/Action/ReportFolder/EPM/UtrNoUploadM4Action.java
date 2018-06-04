package shahi.Action.ReportFolder.EPM;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shahi.Action.database.connection;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;

/**
 *
 * @author Vivek
 */
public class UtrNoUploadM4Action extends ActionSupport {

    private File sketchFile;
    private String sketchFileContentType;
    private String sketchFileFileName;
    private String aausrid;
    private List errorlist;

    @SuppressWarnings("CallToThreadDumpStack")
    @Override
    public String execute() throws Exception {
        errorlist = new ArrayList();
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if (usrid == null) {
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection connbi = null;
        PreparedStatement stat = null;
        try {
            connbi = new connection().getConnection();
            connbi.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            ActionContext ac = ActionContext.getContext();
            ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
            File uploadDir = new File(sc.getRealPath("/shahiwebpages/ReportFolder/EPM/FILE_UPLOAD/"));
            if (uploadDir.exists() == false) {
                uploadDir.mkdirs();
            }
            if (sketchFile != null) {
                String ext = this.sketchFileFileName.substring(this.sketchFileFileName.lastIndexOf("."));
                this.sketchFileFileName = "UTR_No_File" + ext;
                File fileToCreate = new File(uploadDir, this.sketchFileFileName.toUpperCase());
                FileUtils.copyFile(this.sketchFile, fileToCreate);
                try {
                    int counter = 0;
                    Scanner scanner = new Scanner(this.sketchFile);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("");
                        while (lineScanner.hasNext()) {
                            ++counter;
                            String part = lineScanner.nextLine();
                            String YEAR = part.substring(part.indexOf("/") + 1, part.indexOf("/") + 5);
                            String str = "";
                            String VSER = "";
                            String VONO = "";
                            String TEMP_BANK="";
                            
                            if (part.indexOf("/406") != -1) {
                                str = part.substring(part.indexOf("/406"), part.indexOf(","));
                             //   System.out.println(str);
                                if (str != null && str.length() > 0) {
                                    VSER = str.substring(1, 4);
                                    VONO = str.substring(4, str.length());
                                    TEMP_BANK = "18057";
                                 //    System.out.println(VSER);
                                 //     System.out.println(VONO);
                                    
                                }
                            }
                            else if(part.indexOf("/426") != -1) {
                                str = part.substring(part.indexOf("/426"), part.indexOf(","));
                                if (str != null && str.length() > 0) {
                                    VSER = str.substring(1, 4);
                                    VONO = str.substring(4, str.length());
                                    TEMP_BANK = "18057";
                                }
                            }
                            else if(part.indexOf("/106") != -1) {
                                str = part.substring(part.indexOf("/106"), part.indexOf(","));
                                if (str != null && str.length() > 0) {
                                    VSER = str.substring(1, 4);
                                    VONO = str.substring(4, str.length());
                                    TEMP_BANK = "18057";
                                }
                            }
                            str = "";
                            String CHQ_NO = "";
                            if (part.indexOf("UTIBH") != -1) {
                                str = part.substring(part.indexOf("UTIBH"));
                                if (str != null && str.length() > 0) {
                                    CHQ_NO = str.substring(5, 16);
                                }
                            }
                            if (part.indexOf("AXISCN") != -1) {
                                str = part.substring(part.indexOf("AXISCN"));
                                if (str != null && str.length() > 0) {
                                    CHQ_NO = str.substring(6, 16);
                                }
                            }
                            if (part.indexOf("/406") != -1 || part.indexOf("/426") != -1 || part.indexOf("/106") != -1) {
                            
                            try {
                                stat = connbi.prepareStatement("SELECT * FROM FA_BANK_STATEMENT_DUMMY WHERE COMP_ID='122' AND BANK_CODE='18057' AND YEAR=? AND VSER=? AND VONO=?");
                                stat.setString(1, YEAR);
                                stat.setString(2, VSER);
                                stat.setString(3, VONO);
                                ResultSet result = stat.executeQuery();
                                if(result.next()){
                                    errorlist.add(VONO);
                                    //addActionError(" VONO Already exist: "+VONO);
                                }
                                else{
                                    stat = connbi.prepareStatement("INSERT INTO FA_BANK_STATEMENT_DUMMY (COMP_ID,DIVISION,BANK_CODE,YEAR,VSER,VONO,CHQ_NO,SR_NO,LINE_DESC,SEH_USER,TDATE,CHQ_DESC) values ('122','100',?,?,?,?,?,?,?,?,sysdate,'M4')");
                                    stat.setString(1, TEMP_BANK);
                                    stat.setString(2, YEAR);
                                    stat.setString(3, VSER);
                                    stat.setString(4, VONO);
                                    stat.setString(5, CHQ_NO);
                                       stat.setInt(6, counter);
                                    stat.setString(7, line);
                                    stat.setString(8, usrid);
                                    stat.executeUpdate();
                                }
                            
                            } catch (SQLException se) {
                                connbi.rollback();
                                addActionError(se.getMessage());
                                se.printStackTrace();
                            } catch (Exception e) {
                                connbi.rollback();
                                addActionError(e.getMessage());
                                e.printStackTrace();
                            }}
                        }
                    }
                } catch (FileNotFoundException e) {
                    connbi.rollback();
                    addActionError(e.getMessage());
                    e.printStackTrace();
                } catch (IOException ie) {
                    connbi.rollback();
                    addActionError(ie.getMessage());
                    ie.printStackTrace();
                } catch (Exception e) {
                    connbi.rollback();
                    addActionError(e.getMessage());
                    e.printStackTrace();
                }
            }
            connbi.commit();
            addActionError("Data Updated");
        } catch (Exception e) {
            connbi.rollback();
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        } finally {
            if (connbi != null) {
                connbi.close();
            }
        }
        return SUCCESS;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public File getSketchFile() {
        return sketchFile;
    }

    public void setSketchFile(File sketchFile) {
        this.sketchFile = sketchFile;
    }

    public String getSketchFileContentType() {
        return sketchFileContentType;
    }

    public void setSketchFileContentType(String sketchFileContentType) {
        this.sketchFileContentType = sketchFileContentType;
    }

    public String getSketchFileFileName() {
        return sketchFileFileName;
    }

    public void setSketchFileFileName(String sketchFileFileName) {
        this.sketchFileFileName = sketchFileFileName;
    }

    public List getErrorlist() {
        return errorlist;
    }

    public void setErrorlist(List errorlist) {
        this.errorlist = errorlist;
    }
}
