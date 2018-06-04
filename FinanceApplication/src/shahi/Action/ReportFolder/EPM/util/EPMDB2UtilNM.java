package shahi.Action.ReportFolder.EPM.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shahi.Action.ReportFolder.EPM.beans.Code;

public class EPMDB2UtilNM {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate db2Connecction;

	public EPMDB2UtilNM() {
	}


	public List<Code> getDivisionList() {
		String sql="select ccdivi, ccconm ccname from m3fdbprd.cmndiv where  cccono=111 and CCDIVI <>' '";
		return db2Connecction.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getDivisionListByCompany(String CONO) {
		String sql="select ccdivi, ccconm ccname from m3fdbprd.cmndiv where  cccono=? and CCDIVI <>' '";
		return db2Connecction.query(sql,new Object[]{CONO.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code>getAllVouchers(){
		String sql="select distinct DVVSER, DVTX40 from m3fdbprd.CSYNBV order by DVVSER";
		return db2Connecction.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});	
	}
	public List<Code> getVouTypeListByDivison(String divi) {
		String sql="select distinct DVVSER, DVTX40 from m3fdbprd.CSYNBV where dvcono=111  and  dvdivi=? order by DVVSER";
		return db2Connecction.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code>getPurchaseVoucherListByDivision(String divi){
		String sql="select distinct DVVSER, DVTX40 from m3fdbprd.CSYNBV where dvcono=111 and substr(dvvser,3,1)='6' and  dvdivi=? order by DVVSER";
		return db2Connecction.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}
	public List<Code> getWarehouseListByDivision(String divi) {
		String sql="select mwwhlo,mwwhnm from m3fdbprd.mitwhl where mwcono=111 and mwdivi=? order by mwwhlo";
		return db2Connecction.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getYearList(String divi) {
		String sql="select distinct cpyea4 from m3fdbprd.csyper where cpcono=111 and cpdivi=? and CPPETP=1 and cpyea4>2004 order by cpyea4 desc";
		return db2Connecction.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code> getBankList(String divi) {
		String sql="select BCBKID, BCBANA, BCAIT1 from m3fdbprd.CBANAC where BCCONO=111 and BCDIVI=? and BCBKTP='1' order by BCBKID";
		return db2Connecction.query(sql,new Object[]{divi.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getOrtpList() {

		String sql="select OOORTP,OOTX40 from m3fdbprd.OOTYPE where OOCONO=111 order by 1";

		return db2Connecction.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code> getALocList(String ptype) {
		String sql="select distinct ctstky,cttx40 from m3fdbprd.csyTAB where ctstco=? order by ctstky";
		return db2Connecction.query(sql,new Object[]{ptype.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});
	}

	public List<Code> getGLExp(String regex) {

		String sql="select distinct EAAITM,EAAITM||' - '||TRIM(EATX40) EMDESC from m3fdbprd.fchacc where eacono=111 and eaaitp=1 and upper(eatx40) like ? order by eaaitm";
		return db2Connecction.query(sql,new Object[]{regex},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});


	}
	public List<Code> getBankGLList() {
		String sql="select EAAITM EMAIT1,EAAITM||' - '||EATX40 EMDESC from m3fdbprd.FCHACC where eacono='111' and eaaitp=1 and eaat12=1 order by eaaitm";
		return db2Connecction.query(sql,new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

	public List<Code> getSupplier(String regex) {
		String sql="select distinct IDSUNO, IDSUNM||' - '||IDSUNO IDSUNM from m3fdbprd.CIDMAS  where  upper(IDSUNM) like ? ORDER BY IDSUNM";
		return db2Connecction.query(sql,new Object[]{regex.trim()},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

	}

}
