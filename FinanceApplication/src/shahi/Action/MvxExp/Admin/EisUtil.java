
package shahi.Action.MvxExp.Admin;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHrisNew;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EisUtil {
 
    private static Connection conn;
    private static Connection connBI;
    public EisUtil() {
    	System.out.println("Inside Constructor");
    }

   /*static{
	   
	   conn = new connection().getConnection();
       connBI = new ConnectionShahiHrisNew().getConnection();
       System.out.println("Connection has been initilised");
       System.out.println("conn"+conn);
   }*/
   
   public static void openConnection(){
	   conn = new connection().getConnection();
       connBI = new ConnectionShahiHrisNew().getConnection();
   }
        public ResultSet getCSYTAB(String CTSTCO,String CTSTKY) {
        	
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try { 
        
            voustat = connBI.prepareStatement(" select cttx40,cttx15 from prodbi.csytab where ctcono=111 and ctstco=? and ctstky=?" );
            voustat.setString(1,CTSTCO);
            voustat.setString(2,CTSTKY);
            vouresult = voustat.executeQuery();
           
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        
         return vouresult;
    }
    
    public ResultSet getTableAccess(String PAMenu, String myusrid) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select nvl(ACCESS_FLAG,'N') ACCESS_FLAG, nvl(entry_flag,'N') entry_flag,nvl(update_flag,'N') update_flag,nvl(delete_flag,'N') delete_flag,nvl(show_flag,'N') show_flag from hrsec a,HRAPPSMS b where a.sUB_aPPS_CODE= b.apps_code and  a.sub_apps_code =  ? and a.user_code=?" );
            voustat.setString(1,PAMenu);
            voustat.setString(2,myusrid);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        
      
         return vouresult;
    }

    
    
    public ResultSet getGenList(String Gentype) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select gen_code,gen_desc  from HRGENMST where GEN_TYPE = ? AND NVL(FLAG,'A') = 'A' ORDER BY 2" );
            voustat.setString(1,Gentype);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
         
         return vouresult;
    }

        public ResultSet getBankList(String bcond) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            if (bcond!=null && bcond.equals("ALL"))
            {  voustat = conn.prepareStatement("select distinct  bank_code,bank_desc  from HRBANKMST  ORDER BY 2" );  }
             else
            { voustat = conn.prepareStatement("select bank_code,bank_desc  from HRBANKMST where end_date is null  ORDER BY 2" );
            }
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

     public ResultSet getLoctList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select LOCATION_CODE  ,LOCATION_desc from HRLOCT where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    
         return vouresult;
    }

        public ResultSet getPisUnitLoctList( String muser ) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select distinct  b.location_CODE, ( b.location_desc||' - '|| b.location_CODE ) location_desc from HRUNIT a, HRLOCT b, HRAUTHMS c where b.LOCATION_CODE = a.LOCATION_CODE AND a.unit_code= c.unit_code and  c.auth_type ='PISINFO' and c.emp_code = ? " );
            voustat.setString(1,muser);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }


        public ResultSet getTrvUnitList( String muser ) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select distinct  a.unit_CODE, ( a.unit_desc||' - '|| a.unit_CODE ) unit_desc from HRUNIT a, HRAUTHMS c where a.unit_code= c.unit_code and  c.auth_type ='TRVINFO' and c.emp_code = ? " );
            voustat.setString(1,muser);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }

      public ResultSet getPisUnitList(String muser) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select distinct a.unit_code, a.unit_desc||' [ '||a.unit_code||' ]'  unit_desc from hrunit a, HRAUTHMS b WHERE b.auth_type ='PISINFO' and b.emp_code = ? "+
                                        " and  a.unit_code= b.unit_code and NVL(a.FLAG,'A') = 'A' order by 2" );
           voustat.setString(1,muser);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }


        public ResultSet getPayUnitLoctList( String muser ) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select distinct  b.location_CODE, ( b.location_desc||' - '|| b.location_CODE ) location_desc from HRUNIT a, HRLOCT b, HRAUTHMS c where b.LOCATION_CODE = a.LOCATION_CODE AND a.unit_code= c.unit_code and  c.auth_type ='PAYINFO' and c.emp_code = ? " );
            voustat.setString(1,muser);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }

      public ResultSet getPayUnitList(String muser) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select distinct a.unit_code, a.unit_desc||' [ '||a.unit_code||' ]'  unit_desc from hrunit a, HRAUTHMS b WHERE b.auth_type ='PAYINFO' and b.emp_code = ? "+
                                        " and  a.unit_code= b.unit_code and NVL(a.FLAG,'A') = 'A' order by 2" );
           voustat.setString(1,muser);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

     public ResultSet getPayLoctUnitList(String loct, String muser) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select b.location_CODE, b.location_desc, a.unit_code,  a.unit_desc||' [ '||a.unit_code||' ]'  unit_desc from HRUNIT a, HRLOCT b, HRAUTHMS c where A.LOCATION_CODE = B.LOCATION_CODE AND A.LOCATION_CODE =? and c.auth_type ='PAYINFO' and c.emp_code = ? and  c.unit_code= a.unit_code" );
            voustat.setString(1,loct);
            voustat.setString(2,muser);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

      public ResultSet getAuthUnitList(String muser, String authtype) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try { 
        
            voustat = conn.prepareStatement("select distinct a.unit_code, a.unit_desc||' [ '||a.unit_code||' ]'  unit_desc from hrunit a, HRAUTHMS b WHERE b.auth_type =? and b.emp_code = ? "+
                                        " and  a.unit_code= b.unit_code and NVL(a.FLAG,'A') = 'A' order by 2" );
            voustat.setString(1,authtype);
            voustat.setString(2,muser);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    } 

     public ResultSet getUnitList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select UNIT_CODE  ,UNIT_desc||' - '||unit_code unit_desc from HRUNIT where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }
     
     public ResultSet getDeptList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select dept_code,dept_desc from HRDEPT where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }

      public  ResultSet getSubDeptList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select sub_dept_code,sub_dept_desc||' - '||sub_dept_code sub_dept_desc from HRSUBDEPT where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
   
         return vouresult;
    }

     public ResultSet getCategoryList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select cat_code,cat_desc from HRCATMST where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }

    public ResultSet getBandList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select BAND_code,BAND_desc from HRBAND where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

         return vouresult;
    }

        public ResultSet getGradeList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select grade_code,grade_desc||' - '||grade_code grade_desc from HRGRADE where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

         return vouresult;
    }

        public ResultSet getCompanyList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select comp_code,comp_desc from HRCMPMST where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
         
    }

     public ResultSet getUnitLoctList(String unit) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select b.location_CODE, b.location_desc, unit_code, unit_desc from HRUNIT a, HRLOCT b where A.LOCATION_CODE = B.LOCATION_CODE AND A.UNIT_CODE =?" );
            voustat.setString(1,unit);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }


     public ResultSet getSubDeptList(String subdept) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select b.dept_code, b.dept_desc, sub_dept_code, sub_dept_desc||' - '||sub_dept_code sub_dept_desc from HRSUBDEPT a, HRDEPT b where A.DEPT_CODE = B.DEPT_CODE AND A.SUB_DEPT_CODE =?" );
            voustat.setString(1,subdept);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
   
         return vouresult;
    } 

     public ResultSet getGradeBandList(String grade) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select b.band_code, b.band_desc, grade_code, grade_desc from hrgrade a, hrband b where A.band_code = B.band_code AND A.grade_CODE =?" );
            voustat.setString(1,grade);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }

        public ResultSet getBandCatList(String band) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select a.CAT_CODE, a.cat_desc, band_code, band_desc from hrcatmst a, hrband b where A.cat_code = B.cat_code AND b.band_CODE =?" );
            voustat.setString(1,band);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

        public ResultSet getCatBandList(String catg) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select a.CAT_CODE, a.cat_desc, band_code, band_desc from hrcatmst a, hrband b where A.cat_code = B.cat_code AND b.cat_CODE =?" );
            voustat.setString(1,catg);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

        public ResultSet getEmpList(String ename) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select emp_code, emp_name||' - '||emp_code emp_name  from hrempmst where emp_name like upper(?) ");
            voustat.setString(1,ename);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }
        
    public String GetDate()
    {
        Date sysdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentdate = sdf.format(sysdate);

        return currentdate;
    }

    public ResultSet getQualList(String qual) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select qual_code, qual_desc, qual_type from HRQUAL where NVL(FLAG,'A') = 'A'  and qual_code like ? ORDER BY QUAL_TYPE, QUAL_DESC " );
            voustat.setString(1,qual);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

    public ResultSet getAuthTypeList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select AUTH_TYPE  ,AUTH_DESC from hrauthtpms where NVL(FLAG,'A') = 'A' ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    
         return vouresult;
    }

    public ResultSet getPerkList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select perk_code  ,perk_Desc||' - '||perk_code perk_desc from hrperkmst ORDER BY 2" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
   
         return vouresult;
    }

    public ResultSet SearchPerkList(String perk) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select perk_code  ,perk_Desc||' - '||perk_code perk_desc from hrperkmst where perk_desc like upper(?) ORDER BY 2" );
            voustat.setString(1,perk);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
   
         return vouresult;
    }
     public ResultSet getLoctUnitList(String loct) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select b.location_CODE, b.location_desc, unit_code, unit_desc from HRUNIT a, HRLOCT b where A.LOCATION_CODE = B.LOCATION_CODE AND A.LOCATION_CODE =?" );
            voustat.setString(1,loct);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }

      public ResultSet getPerkEDList(String ptype) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select perk_code  ,perk_Desc||' - '||perk_code perk_desc from hrperkmst where PERK_TYPE =? ORDER BY 2" );
            voustat.setString(1, ptype); 
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }

    public ResultSet getPerkGrpList(String Grptype) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("SELECT DISTINCT B.PERK_CODE,C.PERK_DESC FROM HRPERKGRPMST A,HRPERKGRPDTL B,HRPERKMST C WHERE A.CTRL_NO=B.CTRL_NO AND B.PERK_CODE=C.PERK_CODE  AND A.PERK_GRP=? ORDER BY 1,2" );
            voustat.setString(1,Grptype);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

    public ResultSet getSalTempList(String vcomp , String vloct, String vunit, String vcat, String vband) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("SELECT stru_code, stru_desc,SAL_FROM,SAL_TO,SALCAL_ON FROM HRsalmst WHERE COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= ? AND CAT_CODE= ? and band_code = ? and flag = 'A' "+
                                     " union SELECT stru_code, stru_desc,SAL_FROM,SAL_TO,SALCAL_ON FROM HRsalmst WHERE COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= ? AND CAT_CODE= ? and band_code = 'ALL' and flag = 'A' "+
                                     " union SELECT stru_code, stru_desc,SAL_FROM,SAL_TO,SALCAL_ON FROM HRsalmst WHERE COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= 'ALL' AND CAT_CODE= ? and band_code= ? and flag = 'A' "+
                                     " union SELECT stru_code, stru_desc,SAL_FROM,SAL_TO,SALCAL_ON FROM HRsalmst WHERE COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= 'ALL' AND CAT_CODE= ? and band_code='ALL'  and flag = 'A'  order by 1" );
            voustat.setString(1,vcomp);
            voustat.setString(2,vloct);
            voustat.setString(3,vunit);
            voustat.setString(4,vcat);
            voustat.setString(5,vband);
            voustat.setString(6,vcomp);
            voustat.setString(7,vloct);
            voustat.setString(8,vunit);
            voustat.setString(9,vcat);
            voustat.setString(10,vcomp);
            voustat.setString(11,vloct);
            voustat.setString(12,vcat);
            voustat.setString(13,vband);
            voustat.setString(14,vcomp);
            voustat.setString(15,vloct);
            voustat.setString(16,vcat);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

         return vouresult;
    }

   public ResultSet getPerkGrpList(String vcomp , String vloct, String vunit, String vcat, String vgrp) {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("SELECT  A.CTRL_NO,COMP_CODE,LOCATION_CODE, UNIT_CODE,  CAT_CODE,PERK_GRP ,PERK_CODE, EFF_DATE, END_DATE FROM HRPERKgrpmst A , HRPERKgrpDTL B WHERE A.CTRL_NO= B.CTRL_NO AND A.END_DATE IS NULL and COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= ? AND CAT_CODE= ? and perk_grp= ?  "+
                                     " union SELECT  A.CTRL_NO,COMP_CODE,LOCATION_CODE, UNIT_CODE,  CAT_CODE,PERK_GRP ,PERK_CODE, EFF_DATE, END_DATE FROM HRPERKgrpmst A , HRPERKgrpDTL B WHERE A.CTRL_NO= B.CTRL_NO AND A.END_DATE IS NULL and COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= ? AND CAT_CODE= 'ALL' and perk_grp= ?"+
                                     " union SELECT  A.CTRL_NO,COMP_CODE,LOCATION_CODE, UNIT_CODE,  CAT_CODE,PERK_GRP ,PERK_CODE, EFF_DATE, END_DATE FROM HRPERKgrpmst A , HRPERKgrpDTL B WHERE A.CTRL_NO= B.CTRL_NO AND A.END_DATE IS NULL and COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= 'ALL' AND CAT_CODE= ? and perk_grp= ?"+
                                     " union SELECT  A.CTRL_NO,COMP_CODE,LOCATION_CODE, UNIT_CODE,  CAT_CODE,PERK_GRP ,PERK_CODE, EFF_DATE, END_DATE FROM HRPERKgrpmst A , HRPERKgrpDTL B WHERE A.CTRL_NO= B.CTRL_NO AND A.END_DATE IS NULL and COMP_CODE= ? AND LOCATION_CODE= ? AND UNIT_CODE= 'ALL' AND CAT_CODE= 'ALL' and perk_grp= ?" );
            voustat.setString(1,vcomp);
            voustat.setString(2,vloct);
            voustat.setString(3,vunit);
            voustat.setString(4,vcat);
            voustat.setString(5,vgrp);
            voustat.setString(6,vcomp);
            voustat.setString(7,vloct);
            voustat.setString(8,vunit);
            voustat.setString(9,vgrp);
            voustat.setString(10,vcomp);
            voustat.setString(11,vloct);
            voustat.setString(12,vunit);
            voustat.setString(13,vgrp);
            voustat.setString(14,vcomp);
            voustat.setString(15,vloct);
            voustat.setString(16,vgrp);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }

   public ResultSet getGradeCatList(String Cat) { 
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
        
            voustat = conn.prepareStatement("select grade_code,grade_desc from HRGRADE a,hrband b where a.band_code=b.band_code and b.cat_code=? and NVL(a.FLAG,'A') = 'A' ORDER BY 2" );
             voustat.setString(1,Cat);
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
 
         return vouresult;
    }
 
    public  ResultSet getCurrencyList() {
        PreparedStatement voustat = null;
        ResultSet vouresult = null;
        try {
            voustat = conn.prepareStatement("SELECT CTSTKY, CTSTKY||' - '||CTTX40  CTTX40 FROM CSYTAB    WHERE CTCONO = 111 AND CTSTCO = 'CUCD' order by 1" );
            vouresult = voustat.executeQuery();
           
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
  
         return vouresult;
    }



    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
        if (connBI != null) {
            connBI.close();
        }
    }
    
}
 