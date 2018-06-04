/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MI;

/**
 *
 * @author Ranjeet
 */

public class CRS624MI extends BaseMI{
public CRS624MI() {
        setProgram("CRS624MI");
    }

String identity=null;
//, String IDTIZO, String IDTXID

public String AddSupplier624(String IDSUNO, String XTAP,  String XTDC, String XCTR,String IDCSCD,String XPAN)
    
 {
     identity = "AddSupplier";
     int recFlag;  
     javaMI.mvxClearFields();
     
        javaMI.mvxSetField("CONO","111");
        javaMI.mvxSetField("DIVI","100");
        javaMI.mvxSetField("SUNO",IDSUNO);
        javaMI.mvxSetField("XTAP",XTAP);
         if(XTAP!=null && XTAP.length()>0 && XTAP.equals("1"))
        {
        javaMI.mvxSetField("XTDC",XTDC);
        javaMI.mvxSetField("XCTR",XCTR);
        javaMI.mvxSetField("CSCD",IDCSCD);
        javaMI.mvxSetField("XPAN",XPAN);
        javaMI.mvxSetField("XTCT","1");
        
        }else{
        
        javaMI.mvxSetField("XTDC"," ");
        javaMI.mvxSetField("XCTR"," ");
        javaMI.mvxSetField("CSCD"," ");
        javaMI.mvxSetField("XPAN"," ");
        javaMI.mvxSetField("XTCT"," ");
         
        }
        
        javaMI.mvxSetField("XCSR","0");
        javaMI.mvxSetField("XEC1","0");
        
        
        
        
        recFlag = javaMI.mvxAccess("AddSupplier");
         String status="1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("AddSupplier624"+status);
            status="0";
            
        } 
        return status;
 }

public String UpdSupplier624(String IDSUNO, String XTAP,  String XTDC, String XCTR,String IDCSCD,String XPAN)
    
 {
     identity = "UpdSupplier";
     int recFlag;  
     javaMI.mvxClearFields();
     
        javaMI.mvxSetField("CONO","111");
        javaMI.mvxSetField("DIVI","100");
        javaMI.mvxSetField("SUNO",IDSUNO);
        javaMI.mvxSetField("XTAP",XTAP);
          if(XTAP!=null && XTAP.length()>0 && XTAP.equals("1"))
        {
        javaMI.mvxSetField("XTDC",XTDC);
        javaMI.mvxSetField("XCTR",XCTR);
        javaMI.mvxSetField("CSCD",IDCSCD);
        javaMI.mvxSetField("XPAN",XPAN);
        javaMI.mvxSetField("XTCT","1");
        
        }else{
        
        javaMI.mvxSetField("XTDC"," ");
        javaMI.mvxSetField("XCTR"," ");
        javaMI.mvxSetField("CSCD"," ");
        javaMI.mvxSetField("XPAN"," ");
        javaMI.mvxSetField("XTCT","1");
         
        }
        recFlag = javaMI.mvxAccess("UpdSupplier");
         String status="1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("UpdSupplier624"+status);
            status="0";
            
        } 
        return status;
 }
 
 public boolean FindGetSupData(String SUNO) {
        int recFlag;
        identity = "GetSupData";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("DIVI","100");
        recFlag = javaMI.mvxAccess("GetSupData");
        if (recFlag > 0) {
            return false;
        } else {
            return true;
        }
    }

 
 public String AddExciseInfo(String IDSUNO, String ADTE,  String ADID, String STDT,String XECD,String XRGN,
         String XRAN, String XRA1,  String XRA2, String XDA1,String XDA2,String XEDI,
         String XLSN,  String XCSN, String FRE1,String FRE2
         )
    
 {
     identity = "AddExciseInfo";
     int recFlag;  
     javaMI.mvxClearFields();
     
javaMI.mvxSetField("CONO","111");
javaMI.mvxSetField("SUNO",IDSUNO);
javaMI.mvxSetField("ADTE",ADTE);
javaMI.mvxSetField("ADID",ADID);
javaMI.mvxSetField("STDT",STDT);
javaMI.mvxSetField("XECD",XECD);
javaMI.mvxSetField("XRGN",XRGN);
javaMI.mvxSetField("XRAN",XRAN);
javaMI.mvxSetField("XRA1",XRA1);
javaMI.mvxSetField("XRA2",XRA2);
javaMI.mvxSetField("XDA1",XDA1);
javaMI.mvxSetField("XDA2",XDA2);
javaMI.mvxSetField("XEDI",XEDI);
//javaMI.mvxSetField("XECO",XECO);
javaMI.mvxSetField("XLSN",XLSN);
javaMI.mvxSetField("XCSN",XCSN);
//javaMI.mvxSetField("XPLN",XPLN);
//javaMI.mvxSetField("XLCN",XLCN);
javaMI.mvxSetField("FRE1",FRE1);
javaMI.mvxSetField("FRE2",FRE2);

        
        
        
        
        recFlag = javaMI.mvxAccess("AddExciseInfo");
         String status="1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("AddExciseInfo"+status);
            status="0";
            
        } 
        return status;
 }
 
 

 
 

}
