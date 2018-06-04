package shahi.Action.ReportFolder.EPM.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shahi.Action.database.Connectiondb2New;

public class VoucherDao {

	private static Connection conndb2new = null;
	SimpleDateFormat format=new SimpleDateFormat("yyyymmdd");
	String currentDate=format.format(new Date());
	static{
		conndb2new= new Connectiondb2New().getConnection();
	}

	public List<Voucher>loadAllVouchers(){
		List<Voucher>voucherList=new ArrayList<>();

		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql="select EPCONO,EPDIVI,EPYEA4,EPVSER,EPVONO,EPSUNO,EPSINO,EPIVDT,EPCUAM,EPCHID from m3fdbprd.fpledg where epcono=111 and epdivi='100' and epyea4>=2017 and eppyst<>4 and epaprv=0 and epcucd='INR' and epvser='404' and eptrcd=40 and epapcd='AUDITBLR' and ABS(epcuam)<=12500 ";
				
		try{
			ps=conndb2new.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				voucherList.add( new Voucher(rs.getString("EPCONO"),rs.getString("EPDIVI"),rs.getString("EPYEA4"),rs.getString("EPVSER"),
						rs.getString("EPVONO"),rs.getString("EPSUNO"),rs.getString("EPSINO"),rs.getString("EPIVDT"),
						rs.getString("EPCUAM"),rs.getString("EPCHID")));

			}
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
		 // System.out.println("Voucher List:"+voucherList);	
			return voucherList;
		}catch(SQLException ex){
			ex.printStackTrace();
			//System.out.println("Error Maessage from Voucher Dao: "+ex.getLocalizedMessage());
			return null;
		}
	}
	
	public int updateVoucherStatus(Voucher voucher) throws SQLException{
		PreparedStatement ps = null;
		int status=0;
		String sql="update m3fdbprd.fpledg set epaprv=1,eplmdt=? where epcono=? and epdivi=? and epyea4=? and epvser=? and epvono=? and eppyst<>4 "
				+ "and epaprv=0 and epcucd='INR'    and epvser='404' and epapcd='AUDITBLR' and ABS(epcuam)<=12500 ";
		ps=conndb2new.prepareStatement(sql);
		ps.setString(1, currentDate);
		ps.setString(2, voucher.getEPCONO());
		ps.setString(3, voucher.getEPDIVI());
		ps.setInt(4, Integer.valueOf(voucher.getEPYEA4()));
		ps.setString(5, voucher.getEPVSER());
		ps.setInt(6, Integer.valueOf(voucher.getEPVONO()));
		
		status=ps.executeUpdate();
		
		if(ps!=null){
			ps.close();
		}
		return status;
	}
	public void closeConnection(){
		if(conndb2new!=null){
			try {
				conndb2new.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
