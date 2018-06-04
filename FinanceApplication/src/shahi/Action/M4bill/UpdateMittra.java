/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author Ranjeet
 */
public class UpdateMittra {
   
    
    public static void main(String[] args) {
    
      UpdateMittra obj=new UpdateMittra();
      obj.updatedate();
      

  }
    
    
    void updatedate()
    {
    Connection conn = null;
     Connection conn1 = null;

            try {
                conn = new ConnectionShahiHrisNew().getConnection();
                conn1 = new connectiondb2().getConnection();
                
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            try{
            stat1=conn.prepareStatement("select * from prodbi.mittra where mtcono=111 and mtttid like 'P%' and mtridn  in ( '81000090', '81000091', '81000092', '81000093', '81000094', '81000095', '81000107', '81000110', '81000111', '81000113', '81000115', '81000117', '81000122', '81000138', '81000140', '81000141', '81000142', '81000143', '81000146', '81000147', '81000149', '81000152', '81000154', '81000156', '81000160', '81000161', '81000163', '81000166', '81000175', '81000180', '81000182', '81000183', '81000184', '81000185', '81000186', '81000199', '81000205', '81000210', '81000221', '81000237', '81000238', '81000236', '81000270', '81000239', '81000240', '81000241', '81000242', '81000245', '81000250', '81000254', '81000271', '81000255', '81000256', '81000272', '81000257', '81000258', '81000259', '81000260', '81000261', '81000262', '81000263', '81000264', '81000265', '81000137', '81000273', '81000136', '81000135', '81000134', '81000133', '81000132', '81000131', '81000130', '81000129', '81000128', '81000274', '81000126', '81000125', '81000124', '81000120', '81000118', '81000114', '81000275', '81000109', '81000144', '81000148', '81000150', '81000155', '81000300', '81000159', '81000162', '81000164', '81000276', '81000165', '81000167', '81000168', '81000169', '81000170', '81000172', '81000173', '81000174', '81000176', '81000177', '81000178', '81000179', '81000188', '81000191', '81000194', '81000195', '81000196', '81000197', '81000198', '81000200', '81000201', '81000277', '81000207', '81000209', '81000212', '81000213', '81000214', '81000215', '81000216', '81000217', '81000219', '81000222', '81000223', '81000225', '81000226', '81000151', '81000230', '81000231', '81000234', '81000235', '81000153', '81000305', '81000317', '81000318', '81000319', '81000306', '81000303', '81000307', '81000304', '81000320', '81000189', '81000309', '81000310', '81000321', '81000322', '81000323', '81000324', '81000312', '81000325', '81000314', '81000315', '81000313', '81000316', '81000326', '81000279', '81000280', '81000282', '81000283', '81000285', '81000288', '81000290', '81000291', '81000308', '81000243', '81000244', '81000246', '81000247', '81000311', '81000327', '81000248', '81000293', '81000292', '81000328', '81000329', '81000330', '81000331', '81000332', '81000333', '81000334', '81000335', '81000336', '81000337', '81000302', '81000338', '81000190', '81000339', '81000340', '81000294', '81000295', '81000296', '81000297', '81000341', '81000342', '81000192', '81000193', '81000343', '81000344', '81000345', '81000346', '81000347', '81000301', '81000249', '81000348', '81000349', '81000350', '81000351', '81000352', '81000353', '81000299', '81000298', '81000354', '81000251', '81000252', '81000355', '81000356', '81000357', '81000358', '81000253')");
            result1=stat1.executeQuery();
            while(result1.next())
            {
            stat2=conn1.prepareStatement("update m3fdbprd.mittra set mtrgdt=?,mttrdt=? where mtcono=111 and mtttid like 'P%'  and "+
                " MTCONO=? and  MTWHLO=? and MTITNO =? and MTLMTS=? and MTRGTM=? and MTTMSX=?");
            stat2.setString(1, result1.getString("mtrgdt"));
            stat2.setString(2, result1.getString("mtrgdt"));
            stat2.setString(3, result1.getString("MTCONO"));
            stat2.setString(4, result1.getString("MTWHLO"));
            stat2.setString(5, result1.getString("MTITNO"));
            stat2.setString(6, result1.getString("MTLMTS"));
            stat2.setString(7, result1.getString("MTRGTM"));
            stat2.setString(8, result1.getString("MTTMSX"));
            int s=stat2.executeUpdate();
           // System.out.println(s);
            if(stat2!=null)
            {
            stat2.close();
            }
            }
            
            if(conn1!=null){
               conn1.close();
              }
            if(conn!=null){
               conn.close();
              }
            }catch(Exception ee)
            {
            System.out.println(ee.toString());
            }
        
    }
}
