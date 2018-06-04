/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.cashbill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shahi.Action.MI.Beans.MDBREADMILstXINADRBean;
import shahi.Action.MIM4.MDBREADMI;
import shahi.Action.cashbill.bean.SupplierAddressBean;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author Vivek
 */
public class CustomerAddressMaster {
    public static List<SupplierAddressBean> getCustomerAddressWithoutConnection(Connection conn, String CONO, String CUNO, String ADTE) throws SQLException {
        List<SupplierAddressBean> supplierAddressBeans = new ArrayList<SupplierAddressBean>();
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try {
            stat = conn.prepareStatement("SELECT OPADID,OPCUNM,OPCUA1,OPCUA2,OPCUA3,OPCUA4,OPTOWN,OPECAR,OPPONO,OPCSCD,OPVRNO FROM OCUSAD WHERE OPCONO=? AND OPCUNO=? AND OPADRT=?");
            stat.setString(1, CONO);
            stat.setString(2, CUNO);
            stat.setString(3, ADTE);
            resultSet = stat.executeQuery();
            while (resultSet.next()) {
                SupplierAddressBean customerAddressBean = new SupplierAddressBean();
                customerAddressBean.setADID(resultSet.getString("OPADID"));
                customerAddressBean.setCONM(resultSet.getString("OPCUNM"));
                customerAddressBean.setADR1(resultSet.getString("OPCUA1"));
                customerAddressBean.setADR2(resultSet.getString("OPCUA2"));
                customerAddressBean.setADR3(resultSet.getString("OPCUA3"));
                customerAddressBean.setADR4(resultSet.getString("OPCUA4"));
                customerAddressBean.setTOWN(resultSet.getString("OPTOWN"));
                customerAddressBean.setECAR(resultSet.getString("OPECAR"));
                customerAddressBean.setPONO(resultSet.getString("OPPONO"));
                customerAddressBean.setCSCD(resultSet.getString("OPCSCD"));
                customerAddressBean.setGSTN(resultSet.getString("OPVRNO"));
                supplierAddressBeans.add(customerAddressBean);
            }

        } catch (SQLException se) {
            System.out.println("com.shahi.SupplierAddressMaster " + se);
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return supplierAddressBeans;
    }

    public static List<SupplierAddressBean> getSupplierAddressWithoutConnection(Connection conn, String CONO, String SUNO, String ADTE, MDBREADMI mdbreadmi) throws SQLException {
        List<SupplierAddressBean> supplierAddressBeans = new ArrayList<SupplierAddressBean>();
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try {
            stat = conn.prepareStatement("SELECT SAADID,SASUNM,SAADR1,SAADR2,SAADR3,SAADR4,SATOWN,SAECAR,SAPONO,SACSCD,SASTDT SARGDT FROM CIDADR WHERE SACONO=? AND SASUNO=? AND SAADTE=?");
            stat.setString(1, CONO);
            stat.setString(2, SUNO);
            stat.setString(3, ADTE);
            resultSet = stat.executeQuery();
            while (resultSet.next()) {
                SupplierAddressBean supplierAddressBean = new SupplierAddressBean();
                supplierAddressBean.setADID(resultSet.getString("SAADID"));
                supplierAddressBean.setCONM(resultSet.getString("SASUNM"));
                supplierAddressBean.setADR1(resultSet.getString("SAADR1"));
                supplierAddressBean.setADR2(resultSet.getString("SAADR2"));
                supplierAddressBean.setADR3(resultSet.getString("SAADR3"));
                supplierAddressBean.setADR4(resultSet.getString("SAADR4"));
                supplierAddressBean.setTOWN(resultSet.getString("SATOWN"));
                supplierAddressBean.setECAR(resultSet.getString("SAECAR"));
                supplierAddressBean.setPONO(resultSet.getString("SAPONO"));
                supplierAddressBean.setCSCD(resultSet.getString("SACSCD"));
                supplierAddressBean.setRGDT(resultSet.getString("SARGDT"));
                
                if (supplierAddressBean.getCSCD() != null && supplierAddressBean.getCSCD().trim().equals("IN")) 
                {
                    if (resultSet.getString("SAADID") != null && resultSet.getString("SAADID").length() > 0) {
                        List<MDBREADMILstXINADRBean> beans = mdbreadmi.LstXINADR00(SUNO, "1", resultSet.getString("SAADID"), resultSet.getString("SARGDT"));
                        MDBREADMILstXINADRBean bean = new MDBREADMILstXINADRBean();
                        if (beans != null && beans.size() > 0) {
                            bean = beans.get(0);
                        }
                        if (bean != null) {
                            supplierAddressBean.setGSTN(bean.getXLCN());
                        }
                    }
                }
                
                supplierAddressBeans.add(supplierAddressBean);
            }

        } catch (SQLException se) {
            System.out.println("com.shahi.SupplierAddressMaster " + se);
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return supplierAddressBeans;
    }
}
