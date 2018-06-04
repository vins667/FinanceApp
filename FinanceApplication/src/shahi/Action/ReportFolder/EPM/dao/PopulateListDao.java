package shahi.Action.ReportFolder.EPM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shahi.Action.ReportFolder.EPM.beans.Code;

public class PopulateListDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JdbcTemplate scanTemplate;
	
	private Map<String,String>fileTypeMapping;
	
	public List<Code>loadAllCompanies(){
		String sql="select ccdivi, ccconm ccname from m3fdbprd.cmndiv where cccono = 111 and cctlno = 'GLS850'";
		return jdbcTemplate.query(sql,new RowMapper<Code>(){

			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
			
		});
	}
	public List<Code>loadAllDivision(){
		String sql="SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST where req_type='RQDV'";
		List<Code>divisions=new ArrayList<Code>();
		divisions.add(new Code("0","Select Division"));
		divisions.addAll(scanTemplate.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
			
		}));
		return divisions;
	}
	public List<Code>loadAllPayrollTypes(){
		String sql="SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST where req_type='RQTP'";
		List<Code>payrolls=new ArrayList<Code>();
		payrolls.add(new Code("0","Select Payroll"));
		payrolls.addAll(scanTemplate.query(sql,new RowMapper<Code>(){

			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
			
		}));
		return payrolls;
	}
	public List<Code>loadAllAccounts(){
		String sql="SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST where req_type='RQTO'";
		List<Code>accounts=new ArrayList<Code>();
		accounts.add(new Code("0","Select Account"));
		accounts.addAll(scanTemplate.query(sql,new RowMapper<Code>(){

			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
			
		}));
		return accounts;
	}
	public List<Code>loadAllFileTypes(String division){
		String sql="select  DHINTN, DHTX40,DHPCDN, DHFEID, DHFNCN, DHIFTP from m3fdbprd.FFIHEA  where DHCONO= 111 AND DHDIVI=? and dhfldr='GLS850MI' ORDER BY DHTX40";
		return jdbcTemplate.query(sql,new Object[]{division},new RowMapper<Code>(){

			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(3),rs.getString(2)+"("+rs.getString(3)+")");
			}
			
		});
	}
	
	public Map<String,String> getFileTypeMapping(String division){
		fileTypeMapping=new LinkedHashMap<String,String>();
		String sql="select  DHINTN, DHTX40,DHPCDN, DHFEID, DHFNCN, DHIFTP from m3fdbprd.FFIHEA  where DHCONO= 111 AND DHDIVI=? and dhfldr='GLS850MI' ORDER BY DHTX40";
		 jdbcTemplate.query(sql,new Object[]{division},new RowMapper<Code>(){

			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				Code code= new Code(rs.getString(3),rs.getString(2)+"("+rs.getString(3)+")");
				fileTypeMapping.put(code.getCode().trim(), code.getCode().trim());
				return code;
			}
			
		});
		 return fileTypeMapping;
	}
	public List<Code> getDivisionList() {
		String sql="select ccdivi, ccconm ccname from m3fdbprd.cmndiv where  cccono=111 and CCDIVI <>' '";
		return jdbcTemplate.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getDivisionListByCompany(String CONO) {
		String sql="select ccdivi, ccconm ||' - '|| ccdivi ccname from m3fdbprd.cmndiv where  cccono=? and CCDIVI <>' '";
		return jdbcTemplate.query(sql,new Object[]{CONO.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}


	public List<Code> getVouTypeListByDivison(String divi) {
		String sql="select distinct DVVSER, DVTX40 from m3fdbprd.CSYNBV where dvdivi=? order by DVVSER";
		return jdbcTemplate.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code> getWarehouseListByDivision(String divi) {
		String sql="select mwwhlo,mwwhnm from m3fdbprd.mitwhl where mwcono=111 and mwdivi=? order by mwwhlo";
		return jdbcTemplate.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getYearList(String divi) {
		String sql="select distinct cpyea4 from m3fdbprd.csyper where cpcono=111 and cpdivi=? and CPPETP=1 and cpyea4>2004 order by cpyea4 desc";
		List<Code>yearList=new ArrayList<>(0);
		yearList.add(0, new Code("0","Select Year"));
		 yearList.addAll(jdbcTemplate.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(1));
			}
		}));
		 return yearList;
	}

	public List<Code> getBankList(String divi) {
		String sql="select BCBKID, BCBANA, BCAIT1 from m3fdbprd.CBANAC where BCCONO=111 and BCDIVI=? and BCBKTP='1' order by BCBKID";
		return jdbcTemplate.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getOrtpList() {

		String sql="select OOORTP,OOTX40 from m3fdbprd.OOTYPE where OOCONO=111 order by 1";

		return jdbcTemplate.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code> getALocList(String ptype) {
		String sql="select distinct ctstky,cttx40 from m3fdbprd.csyTAB where ctstco=? order by ctstky";
		return jdbcTemplate.query(sql,new Object[]{ptype.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getGLExp(String regex) {

		String sql="select distinct EAAITM,EAAITM||' - '||TRIM(EATX40) EMDESC from m3fdbprd.fchacc where eacono=111 and eaaitp=1 and upper(eatx40) like ? order by eaaitm";
		return jdbcTemplate.query(sql,new Object[]{regex},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});


	}
	public List<Code> getBankGLList() {
		String sql="select EAAITM EMAIT1,EAAITM||' - '||EATX40 EMDESC from m3fdbprd.FCHACC where eacono='111' and eaaitp=1 and eaat12=1 order by eaaitm";
		return jdbcTemplate.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code> getSupplier(String regex) {
		String sql="select distinct IDSUNO, IDSUNM||' - '||IDSUNO IDSUNM from m3fdbprd.CIDMAS  where  upper(IDSUNM) like '"+ regex+ "%' ORDER BY IDSUNM";
		return jdbcTemplate.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

}

	public List<Code> getCustomer(String regex) {
		String sql="select distinct okcuno,okcunm ||' - '|| okcuno okcunm  from m3fdbprd.ocusma where okcono=111 and upper(okcunm) like '"+ regex+ "%' ORDER BY okcunm";
		return jdbcTemplate.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

}
	
	public List<Code>getAllVouchers(){
		/*List<Code>voucherList=new ArrayList<>(6);
		voucherList.add(new Code("0","ALL"));
		voucherList.add(new Code("40","BLR"));
		voucherList.add(new Code("42","TPUR"));
		voucherList.add(new Code("44","SHMG"));
		voucherList.add(new Code("10","FBAD"));
		voucherList.add(new Code("16","HBAD"));
		return voucherList;*/
		
		String sql="select distinct DVVSER, DVTX40 from m3fdbprd.CSYNBV  order by DVVSER";
		return jdbcTemplate.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

		
	}
}