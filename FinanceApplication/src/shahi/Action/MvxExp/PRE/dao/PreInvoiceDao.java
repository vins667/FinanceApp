/*      */ package shahi.Action.MvxExp.PRE.dao;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import shahi.Action.MvxExp.PRE.Beans.AgentBean;
/*      */ import shahi.Action.MvxExp.PRE.Beans.BuyerBean;
/*      */ import shahi.Action.MvxExp.PRE.Beans.PreInvBEBean;
/*      */ import shahi.Action.MvxExp.PRE.Beans.PreInvLicBean;
/*      */ import shahi.Action.MvxExp.PRE.Beans.UnitBean;
           import shahi.Action.MvxExp.PRE.Beans.GSTCMPBEAN;
/*      */ import shahi.Action.database.connection;
/*      */ import shahi.Action.database.ConnectionShahiHrisNew;
           import shahi.Action.database.ConnectionShahiHris;
/*      */ import shahi.Action.database.connectiondb2;
/*      */ 
/*      */ public class PreInvoiceDao
/*      */ {
/*      */   private PreparedStatement stat;
/*      */   private ResultSet result;
/*      */   private PreparedStatement statement1;
/*      */   private ResultSet result1;
/*      */   private PreparedStatement statement2;
/*      */   private ResultSet resultSet;
/*   36 */   Connection conn = null;
    private String se;
/*      */ 
/*      */   public List<UnitBean> getBuyeraddList(String para)
/*      */   {
/*   43 */     List unitlist = new ArrayList();
/*      */     try {
/*   45 */       this.conn = new connectiondb2().getConnection();
/*   46 */       this.stat = this.conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from m3fdbprd.OCUSAD where OPCONO =111 and OPADRT ='1' and opcuno=? order by OPCUNO,OPADID");
/*   47 */       this.stat.setString(1, para);
/*   48 */       this.resultSet = this.stat.executeQuery();
/*   49 */       while (this.resultSet.next()) {
/*   50 */         String address = "";
/*   51 */         if ((this.resultSet.getString("OPCUA1") != null) && (this.resultSet.getString("OPCUA1").trim().length() > 0))
/*      */         {
/*   53 */           address = address + this.resultSet.getString("OPCUA1").trim() + ", ";
/*      */         }
/*   55 */         if ((this.resultSet.getString("OPCUA2") != null) && (this.resultSet.getString("OPCUA2").trim().length() > 0))
/*      */         {
/*   57 */           address = address + this.resultSet.getString("OPCUA2").trim() + ", ";
/*      */         }
/*   59 */         if ((this.resultSet.getString("OPCUA3") != null) && (this.resultSet.getString("OPCUA3").trim().length() > 0))
/*      */         {
/*   61 */           address = address + this.resultSet.getString("OPCUA3").trim() + ", ";
/*      */         }
/*   63 */         if ((this.resultSet.getString("OPCUA4") != null) && (this.resultSet.getString("OPCUA4").trim().length() > 0))
/*      */         {
/*   65 */           address = address + this.resultSet.getString("OPCUA4").trim();
/*      */         }
/*      */ 
/*   69 */         unitlist.add(new UnitBean(this.resultSet.getString("OPADID").trim(), this.resultSet.getString("OPCUNM").trim(), address));
/*      */       }
/*      */     } catch (SQLException e) {
/*   72 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*   73 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*   75 */       if (this.conn != null)
/*      */         try {
/*   77 */           this.conn.close();
/*   78 */           if (this.stat != null) this.stat.close();
/*   79 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*   82 */           e.printStackTrace();
/*      */         }
/*      */     }
/*   85 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public List<BuyerBean> getBuyeraddListbyName(String para)
/*      */   {
/*   91 */     List unitlist = new ArrayList();
/*   92 */     if ((para != null) && (para.length() > 0)) {
/*      */       try
/*      */       {
/*   95 */         this.conn = new connectiondb2().getConnection();
/*      */ 
/*   97 */         String sqlstr = " and (OPADID='" + para + "' or opcuno like '" + para.toUpperCase() + "%' or OPCUNM like '" + para.toUpperCase() + "%')";
/*   98 */         this.stat = this.conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from m3fdbprd.OCUSAD where OPCONO =111 and OPADRT ='1' " + sqlstr + " order by OPCUNO,OPADID");
/*      */ 
/*  100 */         this.resultSet = this.stat.executeQuery();
/*  101 */         while (this.resultSet.next()) {
/*  102 */           String address = "";
/*  103 */           if ((this.resultSet.getString("OPCUA1") != null) && (this.resultSet.getString("OPCUA1").trim().length() > 0))
/*      */           {
/*  105 */             address = address + this.resultSet.getString("OPCUA1").trim() + ", ";
/*      */           }
/*  107 */           if ((this.resultSet.getString("OPCUA2") != null) && (this.resultSet.getString("OPCUA2").trim().length() > 0))
/*      */           {
/*  109 */             address = address + this.resultSet.getString("OPCUA2").trim() + ", ";
/*      */           }
/*  111 */           if ((this.resultSet.getString("OPCUA3") != null) && (this.resultSet.getString("OPCUA3").trim().length() > 0))
/*      */           {
/*  113 */             address = address + this.resultSet.getString("OPCUA3").trim() + ", ";
/*      */           }
/*  115 */           if ((this.resultSet.getString("OPCUA4") != null) && (this.resultSet.getString("OPCUA4").trim().length() > 0))
/*      */           {
/*  117 */             address = address + this.resultSet.getString("OPCUA4").trim();
/*      */           }
/*      */ 
/*  121 */           unitlist.add(new BuyerBean(this.resultSet.getString("OPCUNM").trim(), address, this.resultSet.getString("OPCUNO").trim(), this.resultSet.getString("OPADID").trim()));
/*      */         }
/*      */       } catch (SQLException e) {
/*  124 */         System.out.println("UnitBean" + se); } catch (Exception e) {
/*  125 */         System.out.println("UnitBean " + e);
/*      */       } finally {
/*  127 */         if (this.conn != null)
/*      */           try {
/*  129 */             this.conn.close();
/*  130 */             if (this.stat != null) this.stat.close();
/*  131 */             if (this.resultSet != null) this.resultSet.close(); 
/*      */           }
/*      */           catch (SQLException e)
/*      */           {
/*  134 */             e.printStackTrace();
/*      */           }
/*      */       }
/*      */     }
/*  138 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public List<UnitBean> getUnitListByName(String para,String  para1) {
/*  142 */     if ((para != null) && (para.length() > 0)) {
/*  143 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  146 */       para = "%";
/*      */     } 
/*  148 */     List unitlist = new ArrayList();
/*      */     try {
/*  150 */       this.conn = new connectiondb2().getConnection();
               
/*  151 */       this.stat = this.conn.prepareStatement("select oaadK1 code,oaconm name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM m3fdbprd.ciaddr where oaadth='4' and oaecar=? and (oaadk1 like ? or oaconm like ?) order by 2");
/*  152 */       this.stat.setString(1, para1);
                 this.stat.setString(2, para);
/*  153 */       this.stat.setString(3, para); 
/*  154 */       this.resultSet = this.stat.executeQuery();
/*  155 */       while (this.resultSet.next())
/*  156 */         unitlist.add(new UnitBean(this.resultSet.getString("code"), this.resultSet.getString("name"), this.resultSet.getString("UADDR")));
/*      */     }
/*      */     catch (SQLException e) {
/*  159 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  160 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  162 */       if (this.conn != null)
/*      */         try {
/*  164 */           this.conn.close();
/*  165 */           if (this.stat != null) this.stat.close();
/*  166 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  169 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  172 */     return unitlist;
/*      */   } 


               public List<UnitBean> getgstcmpListByName(String para,String  para1) {
/*  142 */     if ((para != null) && (para.length() > 0)) {
/*  143 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  146 */       para = "%";
/*      */     } 
/*  148 */     List unitlist = new ArrayList();
/*      */     try { 
/*  150 */       conn = new connection().getConnection();
               
/*  151 */       stat = conn.prepareStatement(" select  OAADK3,OACONM,trim(OAADR1)||' '||trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,aRXCSN cst,aRXLSN tin,  " +
                                                  " arxlcn gstin,arfre1 statecode,OAGEOC  from seplweb.ciaddr_VIEW115 a,seplweb.xinddr_view115 b ,seplweb.pr_ship_plan_master P " +
                                                  " where oacono=111 and OAADK2 IN ('100','200','210','400')   and oaadth=1 and  " +
                                                  " oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and OAECAR=P.state_code and p.plan_numb=? order by 2   ")  ;
                     
                stat.setString(1, para1);
              
               resultSet =  stat.executeQuery(); 
/*  155 */     while ( resultSet.next()) 
                {
                       unitlist.add(new GSTCMPBEAN( resultSet.getString("OAADK3"),  resultSet.getString("OACONM"),  resultSet.getString("addr"),resultSet.getString("OAGEOC"),resultSet.getString("gstin")));
/*      */     }
            }
/*      */     catch (SQLException e) { 
/*  159 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  160 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  162 */       if ( conn != null)
/*      */         try {
/*  164 */            conn.close();
/*  165 */           if ( stat != null) this.stat.close();
/*  166 */           if ( resultSet != null)  resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  169 */           e.printStackTrace();
/*      */         }
/*      */     }  
/*  172 */     return unitlist;
/*      */   }
/*      */   public UnitBean getUnitByName(String para)
/*      */   {
/*  179 */     UnitBean unitlist = new UnitBean(); 
/*      */     try { 
/*  181 */       this.conn = new connectiondb2().getConnection();
/*  182 */     //  this.stat = this.conn.prepareStatement("select oaadK1 code,oaconm name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3)||' '||TRIM(OAADR4) UADDR FROM m3fdbprd.CIADDR where oaadth='4' and trim(oaadK1)=? ");
/*  183 */        this.stat = this.conn.prepareStatement("select  OAADK2 code,OACONM name,trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) UADDR,OATAXC, OAECAR ,aRXCSN cst,aRXLSN tin,arxlcn gstin,arfre1 statecode,oageoc  from m3fdbprd.ciaddr a,minfdbprd.xinddr b where oacono=111 and oaadth=1 and oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and trim(oaadk3)=? ");

                   this.stat.setString(1, para);
/*      */ 
/*  185 */       this.resultSet = this.stat.executeQuery();
/*  186 */       while (this.resultSet.next()) {
/*  187 */         unitlist.setUNIT_DESC(this.resultSet.getString("name"));
/*  188 */         unitlist.setUNIT_ADDRESS(this.resultSet.getString("UADDR"));
/*  189 */        // unitlist.setUNIT_CODE(this.resultSet.getString("code"));
                    unitlist.setUNIT_CODE(this.resultSet.getString("oageoc"));
/*      */       }
/*      */     } catch (SQLException e) {
/*  192 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  193 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  195 */       if (this.conn != null)
/*      */         try {
/*  197 */           this.conn.close();
/*  198 */           if (this.stat != null) this.stat.close();
/*  199 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  202 */           e.printStackTrace();
/*      */         } 
/*      */     } 
/*  205 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public List<UnitBean> getCsytabByName(String para, String type)
/*      */   {
/*  210 */     if ((para != null) && (para.length() > 0)) {
/*  211 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  214 */       para = "%";
/*      */     }
/*      */  
/*  218 */     List unitlist = new ArrayList();
/*      */     try {
/*  220 */       this.conn = new connectiondb2().getConnection();
/*  221 */       this.stat = this.conn.prepareStatement("select  cttx40||'-'||ctstky desc,ctstky from m3fdbprd.csytab  where ctcono=111 and cttx15<>'NA' and ctstco=? and (ctstky like ? or cttx40 like ?)  order by 1");
/*  222 */       this.stat.setString(1, type);
/*  223 */       this.stat.setString(2, para);
/*  224 */       this.stat.setString(3, para);
/*  225 */       this.resultSet = this.stat.executeQuery();
/*  226 */       while (this.resultSet.next())
/*  227 */         unitlist.add(new UnitBean(this.resultSet.getString("ctstky"), this.resultSet.getString("desc"), ""));
/*      */     }
/*      */     catch (SQLException e) {
/*  230 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  231 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  233 */       if (this.conn != null)
/*      */         try {
/*  235 */           this.conn.close();
/*  236 */           if (this.stat != null) this.stat.close();
/*  237 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  240 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  243 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public String getCsytabByNameAjax(String para, String type)
/*      */   {
/*  248 */     if ((para != null) && (para.length() > 0)) {
/*  249 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  252 */       para = "%";
/*      */     }
/*      */  
/*  256 */     String unitlist = "";
/*      */     try { 
/*  258 */       this.conn = new connectiondb2().getConnection();
/*      */ 
/*  263 */       this.stat = this.conn.prepareStatement("select  cttx40||'-'||ctstky desc,ctstky from m3fdbprd.csytab  where ctcono=111 and cttx15<>'NA' and ctstco=? and ctstky like ?   order by 1");
/*  264 */       this.stat.setString(1, type);
/*  265 */       this.stat.setString(2, para);
/*      */ 
/*  267 */       this.resultSet = this.stat.executeQuery();
/*  268 */       while (this.resultSet.next())
/*  269 */         unitlist = unitlist + this.resultSet.getString("ctstky") + "#" + this.resultSet.getString("desc");
/*      */     }
/*      */     catch (SQLException e) {
/*  272 */       System.out.println("getCsytabByNameAjax" + se); } catch (Exception e) {
/*  273 */       System.out.println("getCsytabByNameAjax " + e);
/*      */     } finally {
/*  275 */       if (this.conn != null)
/*      */         try {
/*  277 */           this.conn.close();
/*  278 */           if (this.stat != null) this.stat.close();
/*  279 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  282 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  285 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public String getGruptypeAjax(String para, String type) {
/*  289 */     if ((para != null) && (para.length() > 0)) {
/*  290 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  293 */       para = "%";
/*      */     }
/*      */   
/*  297 */     String unitlist = "";
/*      */     try {
/*  299 */       this.conn = new connection().getConnection();
/*  300 */       this.stat = this.conn.prepareStatement("select type_Desc,type_code from ei_grup_type_dtls where close_date is null and grup_type_code=?  and (type_Desc like ? or type_code like ?) order by 1 ");
/*  301 */       this.stat.setString(1, type);
/*  302 */       this.stat.setString(2, para);
/*  303 */       this.stat.setString(3, para);
/*  304 */       this.resultSet = this.stat.executeQuery();
/*  305 */       while (this.resultSet.next())
/*  306 */         unitlist = unitlist + this.resultSet.getString("type_code") + "#" + this.resultSet.getString("type_Desc");
/*      */     }
/*      */     catch (SQLException e) {
/*  309 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  310 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  312 */       if (this.conn != null)
/*      */         try {
/*  314 */           this.conn.close();
/*  315 */           if (this.stat != null) this.stat.close();
/*  316 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  319 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  322 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public String getlcListAjax(String para)
/*      */   {
/*  331 */     if ((para != null) && (para.length() > 0)) {
/*  332 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  335 */       para = "%";
/*      */     }
/*  337 */     String unitlist = "";
/*      */     try {
/*  339 */       this.conn = new connection().getConnection();
/*  340 */       this.stat = this.conn.prepareStatement("select ref_no  from ei_lc_lic_mast WHERE REF_TYPE='LC' and ref_no like ? order by 1 ");
/*  341 */       this.stat.setString(1, para);
/*  342 */       this.resultSet = this.stat.executeQuery();
/*  343 */       while (this.resultSet.next())
/*  344 */         unitlist = unitlist + this.resultSet.getString("ref_no") + "#" + this.resultSet.getString("ref_no");
/*      */     }
/*      */     catch (SQLException e) {
/*  347 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  348 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  350 */       if (this.conn != null)
/*      */         try {
/*  352 */           this.conn.close();
/*  353 */           if (this.stat != null) this.stat.close();
/*  354 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  357 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  360 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public String getCHAListAjax(String para)
/*      */   {
/*  365 */     if ((para != null) && (para.length() > 0)) {
/*  366 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  369 */       para = "%";
/*      */     } 
/*  371 */     String unitlist = "";
/*      */     try {
/*  373 */       this.conn = new connectiondb2().getConnection();
/*      */ 
/*  375 */       this.stat = this.conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and idsuno like ?  order by idsunm");
/*  376 */       this.stat.setString(1, para);
/*      */ 
/*  378 */       this.resultSet = this.stat.executeQuery();
/*  379 */       while (this.resultSet.next())
/*  380 */         unitlist = unitlist + this.resultSet.getString("CHA_CODE") + "#" + this.resultSet.getString("CHA_NAME");
/*      */     }
/*      */     catch (SQLException e) {
/*  383 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  384 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  386 */       if (this.conn != null)
/*      */         try {
/*  388 */           this.conn.close();
/*  389 */           if (this.stat != null) this.stat.close();
/*  390 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  393 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  396 */     return unitlist;
/*      */   }
 
            public String getnotifyListAjax(String para)
/*      */   {
/*  365 */     if ((para != null) && (para.length() > 0)) {
/*  366 */       para = "'"+para.toUpperCase() + "%'";
/*      */     }
/*      */     else {
/*  369 */       para = "%";
/*      */     } 
/*  371 */     String unitlist = "";
/*      */     try {
/*  373 */       this.conn = new connection().getConnection();
/*      */      
/*  375 */       this.stat = this.conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and idsuno like ?  order by idsunm");
/*  376 */       this.stat.setString(1, para);
/*      */ 
/*  378 */       this.resultSet = this.stat.executeQuery();
/*  379 */       while (this.resultSet.next())
                  
/*  380 */         unitlist = unitlist + this.resultSet.getString("CHA_CODE") + "#" + this.resultSet.getString("CHA_NAME");
/*      */     }
/*      */     catch (SQLException e) {
/*  383 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  384 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  386 */       if (this.conn != null)
/*      */         try {
/*  388 */           this.conn.close();
/*  389 */           if (this.stat != null) this.stat.close();
/*  390 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  393 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  396 */     return unitlist;
/*      */   }
  
             public String getUnitListByNameAjax(String para, String para1)
/*      */   {
/*  401 */     if ((para != null) && (para.length() > 0)) {
/*  402 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  405 */       para = "%";
/*      */     }
/*  407 */     String unitlist = "";
/*      */     try {
/*  409 */       this.conn = new connectiondb2().getConnection();
                 // System.out.println("para1 "+para1+" ajax");
/*  410 */       this.stat = this.conn.prepareStatement("select oaadK1 code,oaconm name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM m3fdbprd.ciaddr where oaadth='4' and (oaadk1 like ? or oaconm like ?) order by 2");
/*  411 */       this.stat.setString(1, para);
/*  412 */       this.stat.setString(2, para);
                 
/*  413 */       this.resultSet = this.stat.executeQuery();
/*  414 */       while (this.resultSet.next())
/*  415 */         unitlist = unitlist + this.resultSet.getString("code") + "#" + this.resultSet.getString("name") + "#" + this.resultSet.getString("UADDR");
/*      */     } 
/*      */     catch (SQLException e) {
/*  418 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  419 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  421 */       if (this.conn != null)
/*      */         try {
/*  423 */           this.conn.close();
/*  424 */           if (this.stat != null) this.stat.close();
/*  425 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  428 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  431 */     return unitlist;
/*      */   }
/*      */  
/*      */   public String getTaxCodeListAjax(String para)
/*      */   {
/*  436 */     if ((para != null) && (para.length() > 0)) {
/*  437 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  440 */       para = "%";
/*      */     }
/*  442 */     String unitlist = "";
/*      */     try {
/*  444 */       this.conn = new connectiondb2().getConnection();
/*      */ 
/*  446 */       this.stat = this.conn.prepareStatement(" select uscrid, uscrd0,  uscram taxpercent from m3fdbprd.odchrg where uscono=111  and uscucd='INR' and uscrme=0 and uscram<>0  and (uscrid like ? or uscrd0 like ?)");
/*  447 */       this.stat.setString(1, para);
/*  448 */       this.stat.setString(2, para);
/*  449 */       this.resultSet = this.stat.executeQuery();
/*  450 */       while (this.resultSet.next())
/*  451 */         unitlist = unitlist + this.resultSet.getString("uscrid") + "#" + this.resultSet.getString("uscrd0") + "#" + this.resultSet.getString("taxpercent");
/*      */     }
/*      */     catch (SQLException e) {
/*  454 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  455 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  457 */       if (this.conn != null)
/*      */         try {
/*  459 */           this.conn.close();
/*  460 */           if (this.stat != null) this.stat.close();
/*  461 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  464 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  467 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public UnitBean getCsytabBeanByName(String para, String type)
/*      */   {
/*  474 */     UnitBean unitlist = new UnitBean();
/*      */     try {  
/*  476 */       this.conn = new connectiondb2().getConnection();
/*      */  
/*  478 */       this.stat = this.conn.prepareStatement("select cttx40,cttx15,ctstky from m3fdbprd.csytab  where ctcono=111 and ctstco=? and ctstky=?");
/*  479 */       this.stat.setString(1, type);
/*  480 */       this.stat.setString(2, para);
                  this.resultSet = this.stat.executeQuery();
/*  482 */       while (this.resultSet.next()) {
                   unitlist.setUNIT_CODE(this.resultSet.getString("ctstky"));
/*  484 */         unitlist.setUNIT_DESC(this.resultSet.getString("cttx40"));
/*  485 */         unitlist.setUNIT_ADDRESS(this.resultSet.getString("cttx15"));
/*      */       }
/*      */     } catch (SQLException e) {
/*  488 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  489 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  491 */       if (this.conn != null)
/*      */         try {
/*  493 */           this.conn.close();
/*  494 */           if (this.stat != null) this.stat.close();
/*  495 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  498 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  501 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public List<UnitBean> getGruptype(String para, String type)
/*      */   {
/*  506 */     if ((para != null) && (para.length() > 0)) {
/*  507 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  510 */       para = "%";
/*      */     }
/*      */ 
/*  514 */     List unitlist = new ArrayList();
/*      */     try {
/*  516 */       this.conn = new connection().getConnection();
/*  517 */       this.stat = this.conn.prepareStatement("select type_Desc,type_code from ei_grup_type_dtls where close_date is null and grup_type_code=?  and (type_Desc like ? or type_code like ?) order by 1 ");
/*  518 */       this.stat.setString(1, type);
/*  519 */       this.stat.setString(2, para);
/*  520 */       this.stat.setString(3, para);
/*  521 */       this.resultSet = this.stat.executeQuery();
/*  522 */       while (this.resultSet.next())
/*  523 */         unitlist.add(new UnitBean(this.resultSet.getString("type_code"), this.resultSet.getString("type_Desc"), ""));
/*      */     }
/*      */     catch (SQLException e) {
/*  526 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  527 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  529 */       if (this.conn != null)
/*      */         try {
/*  531 */           this.conn.close();
/*  532 */           if (this.stat != null) this.stat.close();
/*  533 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  536 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  539 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public UnitBean getGruptypeBean(String para, String type)
/*      */   {
/*  545 */     UnitBean unitlist = new UnitBean();
/*      */     try {
/*  547 */       this.conn = new connection().getConnection();
/*  548 */       this.stat = this.conn.prepareStatement("select type_Desc,type_code from ei_grup_type_dtls where close_date is null and grup_type_code=?  and type_code=? order by 1 ");
/*  549 */       this.stat.setString(1, type);
/*  550 */       this.stat.setString(2, para);
/*      */ 
/*  552 */       this.resultSet = this.stat.executeQuery();
/*  553 */       while (this.resultSet.next()) {
/*  554 */         unitlist.setUNIT_CODE(this.resultSet.getString("type_code"));
/*  555 */         unitlist.setUNIT_DESC(this.resultSet.getString("type_Desc"));
/*      */       }
/*      */     }
/*      */     catch (SQLException e) {
/*  559 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  560 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  562 */       if (this.conn != null)
/*      */         try {
/*  564 */           this.conn.close(); 
/*  565 */           if (this.stat != null) this.stat.close();
/*  566 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  569 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  572 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public UnitBean getBuyeraddress(String para1, String para2)
/*      */   {
/*  579 */     UnitBean unitlist = new UnitBean();
/*      */     try {
/*  581 */       this.conn = new connectiondb2().getConnection();
/*  582 */       this.stat = this.conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from m3fdbprd.OCUSAD where OPCONO =111 and OPADRT ='1' and opcuno=? and OPADID=? order by OPCUNO,OPADID");
/*  583 */       this.stat.setString(1, para1);
/*  584 */       this.stat.setString(2, para2);
/*  585 */       this.resultSet = this.stat.executeQuery();
/*  586 */       if (this.resultSet.next()) {
/*  587 */         String address = "";
/*  588 */         if ((this.resultSet.getString("OPCUA1") != null) && (this.resultSet.getString("OPCUA1").trim().length() > 0))
/*      */         {
/*  590 */           address = address + this.resultSet.getString("OPCUA1").trim() + ", ";
/*      */         }
/*  592 */         if ((this.resultSet.getString("OPCUA2") != null) && (this.resultSet.getString("OPCUA2").trim().length() > 0))
/*      */         {
/*  594 */           address = address + this.resultSet.getString("OPCUA2").trim() + ", ";
/*      */         }
/*  596 */         if ((this.resultSet.getString("OPCUA3") != null) && (this.resultSet.getString("OPCUA3").trim().length() > 0))
/*      */         {
/*  598 */           address = address + this.resultSet.getString("OPCUA3").trim() + ", ";
/*      */         }
/*  600 */         if ((this.resultSet.getString("OPCUA4") != null) && (this.resultSet.getString("OPCUA4").trim().length() > 0))
/*      */         {
/*  602 */           address = address + this.resultSet.getString("OPCUA4").trim();
/*      */         }
/*  604 */         unitlist.setUNIT_CODE(this.resultSet.getString("OPADID"));
/*  605 */         unitlist.setUNIT_DESC(this.resultSet.getString("OPCUNM"));
/*  606 */         unitlist.setUNIT_ADDRESS(address);
/*      */       }
/*      */     }
/*      */     catch (SQLException e)
/*      */     {
/*  611 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  612 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  614 */       if (this.conn != null)
/*      */         try {
/*  616 */           this.conn.close();
/*  617 */           if (this.stat != null) this.stat.close();
/*  618 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  621 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  624 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public List<UnitBean> getUnitList(String para) {
/*  628 */     if ((para != null) && (para.length() > 0)) {
/*  629 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  632 */       para = "%";
/*      */     }
/*  634 */     List unitlist = new ArrayList();
/*      */     try {
/*  636 */       this.conn = new connectiondb2().getConnection();
/*  637 */       this.stat = this.conn.prepareStatement("select oaadk1 Location,oaadr4 Location_Name,oaadr1||',   '||oaadr2 Address from m3fdbprd.ciaddr  where oaadth='4' and (oaadk1 like ? or oaadr4 like ?)");
/*  638 */       this.stat.setString(1, para);
/*  639 */       this.stat.setString(2, para);
/*  640 */       this.resultSet = this.stat.executeQuery();
/*  641 */       while (this.resultSet.next())
/*  642 */         unitlist.add(new UnitBean(this.resultSet.getString("Location"), this.resultSet.getString("Location_Name"), this.resultSet.getString("Address")));
/*      */     }
/*      */     catch (SQLException e) {
/*  645 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  646 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  648 */       if (this.conn != null)
/*      */         try {
/*  650 */           this.conn.close();
/*  651 */           if (this.stat != null) this.stat.close();
/*  652 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  655 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  658 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public List<AgentBean> getAgentList(String para)
/*      */   {
/*  664 */     if ((para != null) && (para.length() > 0)) {
/*  665 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  668 */       para = "%";
/*      */     }
/*  670 */     List agentlist = new ArrayList();
/*      */     try {
/*  672 */       this.conn = new connectiondb2().getConnection();
/*  673 */       this.stat = this.conn.prepareStatement("Select DISTINCT sasunm AGENT_NAME ,trim(SAADR1)||trim(SAADR2)||trim(SAADR3)||trim(SAADR4) AGENT_ADDRESS, SASUNO AGENT_CODE ,SAADID VEND_ID  from m3fdbprd.cidadr where saadte=1 and sacono=111 and (sasuno like ? or sasunm like ?) order by sasunm");
/*  674 */       this.stat.setString(1, para);
/*  675 */       this.stat.setString(2, para);
/*  676 */       this.resultSet = this.stat.executeQuery();
/*  677 */       while (this.resultSet.next())
/*  678 */         agentlist.add(new AgentBean(this.resultSet.getString("AGENT_NAME"), this.resultSet.getString("AGENT_ADDRESS"), this.resultSet.getString("AGENT_CODE"), this.resultSet.getString("VEND_ID")));
/*      */     }
/*      */     catch (SQLException e) {
/*  681 */       System.out.println("AgentBean" + se); } catch (Exception e) {
/*  682 */       System.out.println("AgentBean " + e);
/*      */     } finally {
/*  684 */       if (this.conn != null)
/*      */         try {
/*  686 */           this.conn.close();
/*  687 */           if (this.stat != null) this.stat.close();
/*  688 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  691 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  694 */     return agentlist;
/*      */   }
/*      */ 
/*      */   public AgentBean getAgentName(String para1, String para2)
/*      */   {
/*  699 */     AgentBean agentlist = new AgentBean();
/*      */     try {
/*  701 */       this.conn = new connectiondb2().getConnection();
/*  702 */       this.stat = this.conn.prepareStatement("Select DISTINCT sasunm AGENT_NAME ,trim(SAADR1)||trim(SAADR2)||trim(SAADR3)||trim(SAADR4) AGENT_ADDRESS, SASUNO AGENT_CODE ,SAADID VEND_ID  from m3fdbprd.cidadr where saadte=1 and sacono=111 and sasuno=? and SAADID=? ");
/*  703 */       this.stat.setString(1, para1);
/*  704 */       this.stat.setString(2, para2);
/*  705 */       this.resultSet = this.stat.executeQuery();
/*  706 */       while (this.resultSet.next()) {
/*  707 */         agentlist.setAgentName(this.resultSet.getString("AGENT_NAME"));
/*  708 */         agentlist.setAgentAdd(this.resultSet.getString("AGENT_ADDRESS"));
/*  709 */         agentlist.setAgentCode(this.resultSet.getString("AGENT_CODE"));
/*  710 */         agentlist.setVendId(this.resultSet.getString("VEND_ID"));
/*      */       }
/*      */     } catch (SQLException e) {
/*  713 */       System.out.println("AgentBean" + se); } catch (Exception e) {
/*  714 */       System.out.println("AgentBean " + e);
/*      */     } finally {
/*  716 */       if (this.conn != null)
/*      */         try {
/*  718 */           this.conn.close();
/*  719 */           if (this.stat != null) this.stat.close();
/*  720 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  723 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  726 */     return agentlist;
/*      */   }
/*      */ 
/*      */   public List<UnitBean> getlcList(String para)
/*      */   {
/*  733 */     if ((para != null) && (para.length() > 0)) {
/*  734 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/*  737 */       para = "%";
/*      */     }
/*  739 */     List unitlist = new ArrayList();
/*      */     try {
/*  741 */       this.conn = new connection().getConnection();
/*  742 */       this.stat = this.conn.prepareStatement("select ref_no  from ei_lc_lic_mast WHERE REF_TYPE='LC' and ref_no like ? order by 1 ");
/*  743 */       this.stat.setString(1, para);
/*  744 */       this.resultSet = this.stat.executeQuery();
/*  745 */       while (this.resultSet.next())
/*  746 */         unitlist.add(new UnitBean(this.resultSet.getString("ref_no"), this.resultSet.getString("ref_no"), ""));
/*      */     }
/*      */     catch (SQLException e) {
/*  749 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/*  750 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/*  752 */       if (this.conn != null)
/*      */         try {
/*  754 */           this.conn.close();
/*  755 */           if (this.stat != null) this.stat.close();
/*  756 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  759 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  762 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public UnitBean getlcName(String para1, String para2)
/*      */   {
/*  768 */     UnitBean unitlist = new UnitBean();
/*      */     try {
/*  770 */       this.conn = new connection().getConnection();
/*  771 */       this.stat = this.conn.prepareStatement("select ref_no  from ei_lc_lic_mast WHERE REF_TYPE='LC' and ref_no=? order by 1  ");
/*  772 */       this.stat.setString(1, para1);
/*  773 */       this.resultSet = this.stat.executeQuery();
/*  774 */       while (this.resultSet.next()) {
/*  775 */         unitlist.setUNIT_CODE(this.resultSet.getString("ref_no"));
/*  776 */         unitlist.setUNIT_DESC(this.resultSet.getString("ref_no"));
/*      */       }
/*      */     }
/*      */     catch (SQLException e) {
/*  780 */       System.out.println("AgentBean" + se); } catch (Exception e) {
/*  781 */       System.out.println("AgentBean " + e);
/*      */     } finally {
/*  783 */       if (this.conn != null)
/*      */         try {
/*  785 */           this.conn.close();
/*  786 */           if (this.stat != null) this.stat.close();
/*  787 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/*  790 */           e.printStackTrace();
/*      */         }
/*      */     }
/*  793 */     return unitlist;
/*      */   }
/*      */  
              public List<UnitBean> getCHAList(String para)
             {
              if ((para != null) && (para.length() > 0)) {
                  para = para.toUpperCase() + "%";
                  }
              else {
                para = "%";
               }
               List unitlist = new ArrayList();
                try {
                 this.conn = new connectiondb2().getConnection();
                 this.stat = this.conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and (idsuno like ? or idsunm like ?) order by idsunm");
                 this.stat.setString(1, para);
                 this.stat.setString(2, para);
                 this.resultSet = this.stat.executeQuery();
                   
                 while (this.resultSet.next())
                  {
                       
                    unitlist.add(new UnitBean(this.resultSet.getString("CHA_CODE"), this.resultSet.getString("CHA_NAME"), ""));
                 }
                 } catch (SQLException e) {
                    System.out.println("UnitBean" + se); } catch (Exception e) {
                    System.out.println("UnitBean " + e);
                   } finally {
                 if (this.conn != null)
                   try {
                    this.conn.close();
                     if (this.stat != null) this.stat.close();
                    if (this.resultSet != null) this.resultSet.close(); 
                    }
                   catch (SQLException e)
                   {
                      e.printStackTrace();
                   }
                 }
                return unitlist;
           }
               public List<UnitBean> getNotifyList(String para)
             {
              if ((para != null) && (para.length() > 0)) {
                  para = para.toUpperCase() + "%";
                  }
              else {
                para = "%";
               }
               List unitlist = new ArrayList();
                try { 
               //  this.conn = new connectiondb2().getConnection();
                     
                   this.conn= new connectiondb2().getConnection();
                 this.stat = this.conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and (idsuno like ? or idsunm like ?) order by idsunm");
                 this.stat.setString(1, para);
                 this.stat.setString(2, para);
                 this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next())
                  {
                    unitlist.add(new UnitBean(this.resultSet.getString("CHA_CODE"), this.resultSet.getString("CHA_NAME"), ""));
                 }
                 } catch (SQLException e) {
                    System.out.println("UnitBean" + se); } catch (Exception e) {
                    System.out.println("UnitBean " + e);
                   } finally {
                 if (this.conn != null)
                   try {
                    this.conn.close();
                     if (this.stat != null) this.stat.close();
                    if (this.resultSet != null) this.resultSet.close(); 
                    }
                   catch (SQLException e)
                   {
                      e.printStackTrace();
                   }
                 }
                return unitlist;
           }
               public UnitBean getCHAName(String para1)
              {
               UnitBean unitlist = new UnitBean();
               try {
                 this.conn = new connectiondb2().getConnection();
                 this.stat = this.conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and  idsuno=?  ");
                 this.stat.setString(1, para1);
                 this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next()) {
                  unitlist.setUNIT_CODE(this.resultSet.getString("CHA_CODE"));
                  unitlist.setUNIT_DESC(this.resultSet.getString("CHA_NAME"));
                   }
               } catch (SQLException e) {
                  System.out.println("UnitBean" + se); } catch (Exception e) {
                  System.out.println("UnitBean " + e);
                } finally {
                  if (this.conn != null)
                   try {
                     this.conn.close();
                   if (this.stat != null) this.stat.close();
                if (this.resultSet != null) this.resultSet.close(); 
                  }
                  catch (SQLException e)
                  {
                   e.printStackTrace();
                 }
             }
             return unitlist;
            }
                 public UnitBean getNotifyName(String para1)
              {
               UnitBean unitlist = new UnitBean();
               try {
                 this.conn = new connectiondb2().getConnection();
                 this.stat = this.conn.prepareStatement("Select idsunm CHA_NAME, idsuno CHA_CODE from m3fdbprd.CIDMAS where idcono=111 and  idsuno=?   ");
                 this.stat.setString(1, para1);
                 this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next()) {
                 unitlist.setUNIT_CODE(this.resultSet.getString("CHA_CODE"));
                  unitlist.setUNIT_DESC(this.resultSet.getString("CHA_NAME"));
                   }
               } catch (SQLException e) {
                  System.out.println("UnitBean" + se); } catch (Exception e) {
                  System.out.println("UnitBean " + e);
                } finally {
                  if (this.conn != null)
                   try {
                     this.conn.close();
                   if (this.stat != null) this.stat.close();
                if (this.resultSet != null) this.resultSet.close(); 
                  }
                  catch (SQLException e)
                  {
                   e.printStackTrace();
                 }
             }
             return unitlist;
            }
                 
             public List getWareHouseList(String userId)
            {
                List whlist = new ArrayList();
              try {
                 this.conn = new connection().getConnection();
 
                this.stat = this.conn.prepareStatement("select distinct warehouse from production.user_para_mast where user_id=? order by 1");
               this.stat.setString(1, userId);
   
                 this.resultSet = this.stat.executeQuery();
             while (this.resultSet.next())
                   whlist.add(this.resultSet.getString("warehouse"));
               }
               catch (SQLException e) {
                 System.out.println("wh" + se); } catch (Exception e) {
                System.out.println("wh " + e);
                } finally {
                if (this.conn != null)
                 try {
                     this.conn.close();
                   if (this.stat != null) this.stat.close();
                  if (this.resultSet != null) this.resultSet.close(); 
                    }
                   catch (SQLException e)
                   {
                 e.printStackTrace();
                }
              }
             return whlist;
              }
  
            public List<BuyerBean> getBuyerList(String para)
            {
              if ((para != null) && (para.length() > 0)) {
               para = para.toUpperCase() + "%";
              }
           else {
               para = "%";
              }
              List buyerlist = new ArrayList();
               try {
                 this.conn = new connectiondb2().getConnection();
                 this.stat = this.conn.prepareStatement("Select distinct OKCUNM BUYER,trim(OKCUA1)||','||trim(OKCUA2)||','||trim(OKCUA3)||','||trim(OKCUA4)  BUYER_ADDRESS, OKCUNO BUYER_CODE,OKADID BUYER_ID FROM m3fdbprd.OCUSMA WHERE okcono=111 and (OKCUNO like ? or OKCUNM  like ?) order by 1 ");
                 this.stat.setString(1, para);
                 this.stat.setString(2, para);
                 this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next())
                  buyerlist.add(new BuyerBean(this.resultSet.getString("BUYER"), this.resultSet.getString("BUYER_ADDRESS"), this.resultSet.getString("BUYER_CODE"), this.resultSet.getString("BUYER_ID")));
                }
               catch (SQLException e) {
                System.out.println("BuyerBean" + se); } catch (Exception e) {
                 System.out.println("BuyerBean " + e);
               } finally {
              if (this.conn != null)
                 try {
                  this.conn.close();
                    if (this.stat != null) this.stat.close();
                  if (this.resultSet != null) this.resultSet.close(); 
                }
                catch (SQLException e)
               {
                   e.printStackTrace();
                }
               }
               return buyerlist;
            }
  
              public List<UnitBean> getTaxCodeList(String para)
            {
            if ((para != null) && (para.length() > 0)) {
                  para = para.toUpperCase() + "%";
             }
            else {
               para = "%";
              }
                List unitlist = new ArrayList();
              try {
                 this.conn = new connectiondb2().getConnection();
 
                  this.stat = this.conn.prepareStatement(" select uscrid, uscrd0,  uscram taxpercent from m3fdbprd.ODCHRG  where uscono=111  and uscucd='INR' and uscrme=0 and uscram<>0  and (uscrid like ? )");
                  this.stat.setString(1, para);
   
                   this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next()) {
                  
                unitlist.add(new UnitBean(this.resultSet.getString("uscrid"), this.resultSet.getString("uscrd0"), this.resultSet.getString("taxpercent")));
           
                 }
              } catch (SQLException e) {
                 System.out.println("UnitBean" + se); } catch (Exception e) {
                 System.out.println("UnitBean " + e);
              } finally {
              if (this.conn != null)
                try {
                  this.conn.close();
                 if (this.stat != null) this.stat.close();
                if (this.resultSet != null) this.resultSet.close(); 
                }
                 catch (SQLException e)
               {
                  e.printStackTrace();
             }
              }
            return unitlist;
           }
 
             public UnitBean getTaxcodeName(String para1, String para2)
             {
              UnitBean unitlist = new UnitBean();
             try {
                 this.conn = new connectiondb2().getConnection();
   
                  this.stat = this.conn.prepareStatement("select uscrid, uscrd0,  uscram taxpercent from m3fdbprd.odchrg where uscono=111  and uscucd='INR' and uscrme=0 and uscram<>0 and uscrid=? ");
                 this.stat.setString(1, para1);
                 this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next()) {
                   unitlist.setUNIT_DESC(this.resultSet.getString("uscrd0"));
                   unitlist.setUNIT_ADDRESS(this.resultSet.getString("taxpercent"));
                   unitlist.setUNIT_CODE(this.resultSet.getString("uscrid"));
               }
               } catch (SQLException e) {
                System.out.println("UnitBean" + se); } catch (Exception e) {
                System.out.println("UnitBean " + e);
               } finally {
                if (this.conn != null)
                try {
                    this.conn.close();
                   if (this.stat != null) this.stat.close();
                      if (this.resultSet != null) this.resultSet.close(); 
                   }
                   catch (SQLException e)
                  {
                     e.printStackTrace();
                 }
                 }
              return unitlist;
             }
        
               public List<UnitBean> getcatg(String para, String desti_cntry) {
               if ((para != null) && (para.length() > 0)) {
                  para = para.toUpperCase() + "%";
                 }
               else {
                 para = "%";
              }
                   String str = " and (category like '" + para + "' or catg_desc like '" + para + "')";
      
               List unitlist = new ArrayList();
            try {
                  this.conn = new connection().getConnection();
   
                this.stat = this.conn.prepareStatement("select category,catg_desc from ei_category_mast where  country=?  " + str.toUpperCase() + " order by 1");
                   this.stat.setString(1, desti_cntry);
       
                  this.resultSet = this.stat.executeQuery();
                   while (this.resultSet.next())
                    unitlist.add(new UnitBean(this.resultSet.getString("category"), this.resultSet.getString("catg_desc"), ""));
                }
                catch (SQLException e) {
                 System.out.println("UnitBean" + se); } catch (Exception e) {
                  System.out.println("UnitBean " + e);
                } finally {
                  if (this.conn != null)
                   try {
                  this.conn.close();
/* 1025 */           if (this.stat != null) this.stat.close();
/* 1026 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/* 1029 */           e.printStackTrace();
/*      */         }
/*      */     }
/* 1032 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public String getcatgAjax(String para, String desti_cntry)
/*      */   {
/* 1038 */     if ((para != null) && (para.length() > 0)) {
/* 1039 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/* 1042 */       para = "%";
/*      */     }
/* 1044 */     String str = " and (category like '" + para + "' or catg_desc like '" + para + "')";
/*      */ 
/* 1046 */     String unitlist = "";
/*      */     try {
/* 1048 */       this.conn = new connection().getConnection();
/*      */ 
/* 1050 */       this.stat = this.conn.prepareStatement("select category,catg_desc from ei_category_mast where  country=?  " + str.toUpperCase() + " order by 1");
/* 1051 */       this.stat.setString(1, desti_cntry);
/*      */ 
/* 1053 */       this.resultSet = this.stat.executeQuery();
/* 1054 */       while (this.resultSet.next())
/*      */       {
/* 1056 */         unitlist = this.resultSet.getString("category") + "#" + this.resultSet.getString("catg_desc");
/* 1057 */         System.out.println(unitlist);
/*      */       }
/*      */     } catch (SQLException e) {
/* 1060 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/* 1061 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/* 1063 */       if (this.conn != null)
/*      */         try {
/* 1065 */           this.conn.close();
/* 1066 */           if (this.stat != null) this.stat.close();
/* 1067 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/* 1070 */           e.printStackTrace();
/*      */         }
/*      */     }
/* 1073 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public List getPreLicence(String str)
/*      */     throws SQLException
/*      */   {
/* 1079 */     List LicenceList = new ArrayList();
/* 1080 */     if ((str != null) && (str.length() > 0)) {
/*      */       try {
/* 1082 */         if ((str != null) && (str.length() > 0))
/*      */         {
/* 1085 */           this.conn = new connection().getConnection();
/*      */ 
/* 1087 */           this.stat = this.conn.prepareStatement("select A.ref_no,A.ref_type,b.item_desc, b.item_no ref_ctrl_no,io_norms,nvl(b.qty,0) licqty,nvl(b.QTY_ISSUE,0) qty_issue,round(b.fc_amt+(b.fc_amt*nvl(ADD_VALUE,0))/100,2) exp_ob,nvl(b.AMT_ISSUE,0) amt_issue,date_from ref_date,location,comp_code  from pi_imp_lic_mast a,pi_imp_lic_exp b where a.ref_no like ? and a.REF_TYPE<>'LC' AND a.ref_type=b.ref_type(+) and a.ref_no=B.ref_no(+)  and (is_epc = 'N' and  is_lc = 'N') order by 1,4 ");
/*      */ 
/* 1089 */           str = str + "%";
/* 1090 */           this.stat.setString(1, str);
/* 1091 */           this.resultSet = this.stat.executeQuery();
/* 1092 */           while (this.resultSet.next())
/*      */           {
/* 1094 */             LicenceList.add(new PreInvLicBean(this.resultSet.getString("ref_type"), this.resultSet.getString("ref_no"), this.resultSet.getString("ref_date"), this.resultSet.getString("location"), this.resultSet.getString("comp_code"), this.resultSet.getString("ref_ctrl_no"), this.resultSet.getString("item_desc"), this.resultSet.getString("io_norms"), Double.valueOf(this.resultSet.getDouble("licqty")), Double.valueOf(this.resultSet.getDouble("exp_ob")), Double.valueOf(this.resultSet.getDouble("amt_issue")), Double.valueOf(this.resultSet.getDouble("qty_issue"))));
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception ee) {
/* 1099 */         System.out.println(ee.toString());
/*      */       }
/*      */       finally {
/* 1102 */         if (this.resultSet != null) {
/* 1103 */           this.resultSet.close();
/*      */         }
/* 1105 */         if (this.stat != null) {
/* 1106 */           this.stat.close();
/*      */         }
/* 1108 */         if (this.conn != null) {
/* 1109 */           this.conn.close();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1114 */     return LicenceList;
/*      */   }
/*      */ 
/*      */   public List getPreBE(String str, String REF_TYPE, String REF_NO) throws SQLException {
/* 1118 */     List BEList = new ArrayList();
/* 1119 */     if ((REF_TYPE != null) && (REF_TYPE.length() > 0)) {
/*      */       try {
/* 1121 */         if ((REF_TYPE != null) && (REF_TYPE.length() > 0))
/*      */         {
/* 1124 */           this.conn = new connection().getConnection();
/*      */ 
/* 1126 */           this.stat = this.conn.prepareStatement("select d.be_no,B.be_desc,sum(B.LIC_QTY) ref_qty ,nvl(e.qty,0)-nvl(e.exp_qty,0) beqty,b.item_no from pi_imp_awbl_mast A, pi_imp_cinv_lic_dtls  b, pi_imp_awbl_cinv c, pi_imp_boe_dtls d , pi_imp_lic_dtls e   where a.ref_no=c.ref_no and b.ind_no = c.ind_no and b.cinv_no = c.cinv_no  and d.ref_no = a.ref_No and e.ref_type = b.lic_type and e.ref_no = b.lic_no and e.item_no = b.item_no and D.BE_NO LIKE ? AND b.lic_type=? and b.lic_no=? group by d.be_no,B.be_desc,b.item_no,e.QTY, e.exp_qty ");
/*      */ 
/* 1128 */           if (str == null)
/* 1129 */             str = "";
/* 1130 */           str = str + "%";
/* 1131 */           this.stat.setString(1, str);
/* 1132 */           this.stat.setString(2, REF_TYPE);
/* 1133 */           this.stat.setString(3, REF_NO);
/* 1134 */           this.resultSet = this.stat.executeQuery();
/* 1135 */           while (this.resultSet.next())
/*      */           {
/* 1137 */             BEList.add(new PreInvBEBean(this.resultSet.getString("be_no"), this.resultSet.getString("be_desc"), this.resultSet.getString("ref_qty"), this.resultSet.getString("beqty"), this.resultSet.getString("item_no")));
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception ee) {
/* 1142 */         System.out.println(ee.toString());
/*      */       }
/*      */       finally { 
/* 1145 */         if (this.resultSet != null) {
/* 1146 */           this.resultSet.close();
/*      */         }
/* 1148 */         if (this.stat != null) {
/* 1149 */           this.stat.close();
/*      */         }
/* 1151 */         if (this.conn != null) {
/* 1152 */           this.conn.close();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1157 */     return BEList;
/*      */   }
/*      */ 
               public List getPreDBKSL(String str, String invdate) throws SQLException {
               List unitList = new ArrayList();
                if ((str != null) && (str.length() > 0)) {
                   try {
                    if ((str != null) && (str.length() > 0))
                      {
                   this.conn = new connection().getConnection();
 
                this.stat = this.conn.prepareStatement("select dbk_slno,dbk_rate, dbk_unit from ei_dbk_rate_mast where to_date(?,'dd-Mon-yyyy') between dbk_begin_date and dbk_end_date and dbk_slno like ? order by 1");
                  str = str + "%";
                  this.stat.setString(1, invdate);
                   this.stat.setString(2, str);
                    
                    this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next())
                   {
                        unitList.add(new UnitBean(this.resultSet.getString("dbk_slno"), this.resultSet.getString("dbk_unit"), this.resultSet.getString("dbk_rate")));
                   }
                     }
                       } 
                       catch (Exception ee) {
                     System.out.println(ee.toString());
                      }
                   finally {
                    if (this.resultSet != null) {
                       this.resultSet.close();
                     }
                     if (this.stat != null) {
                      this.stat.close();
                    }
                 if (this.conn != null) {
                    this.conn.close();
                  }
                }
               }
    
              return unitList;
          }
  public List getPreROSLSL(String str, String invdate) throws SQLException {
               List unitList = new ArrayList();
                if ((str != null) && (str.length() > 0)) {
                   try {
                    if ((str != null) && (str.length() > 0))
                      {
                   this.conn = new connection().getConnection(); 
 
                   this.stat = this.conn.prepareStatement("select rosl_slno,rosl_rate,rosl_uOM from ei_rosl_rate_mast where to_date(?,'dd-Mon-yyyy') between begin_date and end_date and trim(rosl_slno) like ? order by 1");
                   //  this.stat = this.conn.prepareStatement("select rosl_slno,rosl_rate,rosl_uom from ei_rosl_rate_mast where  trim(rosl_slno) like ? order by 1");
                    
                   str = str + "%";
                   this.stat.setString(1, invdate);
                   this.stat.setString(2, str.trim());
                    
                    this.resultSet = this.stat.executeQuery();
                 while (this.resultSet.next())
                   {
                        unitList.add(new UnitBean(this.resultSet.getString("rosl_slno"), this.resultSet.getString("rosl_uom"), this.resultSet.getString("rosl_rate")));
                   }
                     }
                       }
                       catch (Exception ee) {
                     System.out.println(ee.toString());
                      }
                   finally {
                    if (this.resultSet != null) {
                       this.resultSet.close();
                     }
                     if (this.stat != null) {
                      this.stat.close();
                    }
                 if (this.conn != null) {
                    this.conn.close();
                  }
                }
               }
    
              return unitList;
          }
 
/*      */   public List getPreSTRSL(String str, String parab, String invdate) throws SQLException {
/* 1201 */     List unitList = new ArrayList();
/* 1202 */     if ((invdate != null) && (invdate.length() > 0)) {
/*      */       try {
/* 1204 */         if ((invdate != null) && (invdate.length() > 0))
/*      */         {
/* 1207 */           this.conn = new connection().getConnection();
/*      */ 
/* 1209 */           this.stat = this.conn.prepareStatement("select str_slno,str_rate from ei_str_rate_mast where str_slno like ? and to_date(?,'dd-Mon-yyyy') between begin_date and end_date order by 1 ");
/* 1210 */           if ((parab.equals("STR")) && (str.length() > 0)) str = str.substring(0, 2) + "%";
/* 1211 */           if ((parab.equals("STR")) && (str.length() == 0)) str = "%";
/* 1212 */           if (parab.equals("STRMISC")) str = str + "%";
/* 1213 */           this.stat.setString(1, str);
/* 1214 */           this.stat.setString(2, invdate);
/* 1215 */           this.resultSet = this.stat.executeQuery();
/* 1216 */           while (this.resultSet.next())
/*      */           {
/* 1218 */             unitList.add(new UnitBean(this.resultSet.getString("str_slno"), this.resultSet.getString("str_rate"), ""));
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception ee) {
/* 1223 */         System.out.println(ee.toString());
/*      */       }
/*      */       finally {
/* 1226 */         if (this.resultSet != null) {
/* 1227 */           this.resultSet.close();
/*      */         }
/* 1229 */         if (this.stat != null) {
/* 1230 */           this.stat.close();
/*      */         }
/* 1232 */         if (this.conn != null) {
/* 1233 */           this.conn.close();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1238 */     return unitList;
/*      */   }
            public String getdbkslnoAjax(String PARAA, String PARAB, String INVDATE)
           {
              String unitlist = "";
                try {
                    conn = new connection().getConnection();
                    stat = this.conn.prepareStatement("select dbk_slno,dbk_rate, dbk_unit from ei_dbk_rate_mast where to_date(?,'dd-Mon-yyyy') between dbk_begin_date and dbk_end_date and dbk_slno like ? ");
                    stat.setString(1, INVDATE);
                    stat.setString(2, PARAA.trim());
      
                      result = stat.executeQuery();
                   if (result.next())  
                     {
    
                          unitlist = unitlist + result.getString("dbk_slno") + "#" + this.result.getString("dbk_slno");
                       // System.out.println("1. Doa result "+unitlist);
                        }
                 } 
                catch (SQLException e)
                {
                  System.out.println("msg" + se); } catch (Exception e) {
                  System.out.println("msg " + e);
                   } finally {
                   if (this.conn != null)
                    try {
                       this.conn.close();
                      if (this.stat != null) this.stat.close();
                       if (this.resultSet != null) this.resultSet.close(); 
                     }
                     catch (SQLException e)
                     {
                      e.printStackTrace();
                    }
                     }
                     return unitlist;
       }
             
           public String getroslslnoAjax(String PARAA, String PARAB, String INVDATE)
           {
              String unitlist = "";
                try {
                    conn = new connection().getConnection();
                    stat = this.conn.prepareStatement("select rosl_slno,rosl_rate, rosl_uOM from ei_rosl_rate_mast where to_date(?,'dd-Mon-yyyy') between begin_date and end_date and trim(rosl_slno) like ? ");
                  //   stat = this.conn.prepareStatement("select rosl_slno,rosl_rate, rosl_uom from ei_rosl_rate_mast where trim(rosl_slno) like ? ");
                    
                    stat.setString(1, INVDATE);
                    stat.setString(2, PARAA.trim());
                   
                      result = stat.executeQuery();
                   if (result.next())  
                     { 
                       //  System.out.println("ROSL RESULT");
                          unitlist = unitlist + result.getString("rosl_slno") + "#" + this.result.getString("rosl_slno");
                      //  System.out.println("1. Doa result "+unitlist);
                        }
                 } 
                catch (SQLException e)
                {
                  System.out.println("msg" + se); } catch (Exception e) {
                  System.out.println("msg " + e);
                   } finally {
                   if (this.conn != null)
                    try {
                       this.conn.close();
                      if (this.stat != null) this.stat.close();
                       if (this.resultSet != null) this.resultSet.close(); 
                     }
                     catch (SQLException e)
                     {
                      e.printStackTrace();
                    }
                     }
                     return unitlist;
       }     
             public String getstrslnoAjax(String PARAA, String PARAB, String PARAC,String PARAD,String INVDATE)
           {
             String unitlist = "";
              try {
/* 1248 */       this.conn = new connection().getConnection();
/* 1249 */       this.stat = this.conn.prepareStatement("select str_slno,str_rate from ei_str_rate_mast where str_slno like ? and to_date(?,'dd-Mon-yyyy') between begin_date and end_date");
/* 1250 */         
                   this.stat.setString(1, PARAA);
/* 1251 */         this.stat.setString(2, INVDATE); 
/* 1253 */       this.result = this.stat.executeQuery();
/* 1254 */       if (this.result.next())  
/*      */       {
/* 1257 */            unitlist = unitlist + result.getString("str_slno") + "#" + this.result.getString("str_slno");
/*      */     
/*      */       }
/*      */     }
/*      */     catch (SQLException e)
/*      */     {
/* 1262 */       System.out.println("msg" + se); } catch (Exception e) {
/* 1263 */       System.out.println("msg " + e);
/*      */     } finally {
/* 1265 */       if (this.conn != null)
/*      */         try {
/* 1267 */           this.conn.close();
/* 1268 */           if (this.stat != null) this.stat.close();
/* 1269 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/* 1272 */           e.printStackTrace();
/*      */         }
/*      */     } 
/* 1275 */     return unitlist;
/*      */   }
/*      */   public String getCoAppr(String YEAR, String COMPANY, String INV_NO)
/*      */   {
/* 1246 */     String msg = "";
/*      */     try {
          
/* 1248 */       this.conn = new connection().getConnection();
/* 1249 */       this.stat = this.conn.prepareStatement("select exports.FUN_COAPRV115(?,?,?)  aa from dual ");
/* 1250 */       this.stat.setString(1, YEAR);
/* 1251 */       this.stat.setString(2, COMPANY);
/* 1252 */       this.stat.setString(3, INV_NO);
/* 1253 */       this.result1 = this.stat.executeQuery();
                  
/* 1254 */       if (this.result1.next())
                 {   
/* 1255 */        if  (this.result1.getString("aa") != null)
/*      */       {
/* 1257 */         msg = " Error Missing CO Approval : " + this.result1.getString("aa");
/*      */       }}
/*      */     }   
/*      */     catch (SQLException e)
/*      */     {
/* 1262 */       System.out.println("msg" + se); } catch (Exception e) {
/* 1263 */       System.out.println("msg " + e);
/*      */     } finally {
/* 1265 */       if (this.conn != null)
/*      */         try {
/* 1267 */           this.conn.close();
/* 1268 */           if (this.stat != null) this.stat.close();
/* 1269 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/* 1272 */           e.printStackTrace();
/*      */         }
/*      */     }
/* 1275 */     return msg;
/*      */   }
/*      */ 

/*      */   public List<UnitBean> getSBList(String para)
/*      */   {
/* 1282 */     if ((para != null) && (para.length() > 0)) {
/* 1283 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/* 1286 */       para = "%";
/*      */     }
/* 1288 */     List unitlist = new ArrayList();
/*      */     try {
/* 1290 */       this.conn = new connection().getConnection();
/* 1291 */       this.stat = this.conn.prepareStatement("Select shp_bill_no,to_char(shp_bill_date,'dd-Mon-yyyy') shp_bill_date from exports.ei_sbill_master where shp_bill_date>='01-apr-2012' and  shp_bill_no like ?  order by 1");
/* 1292 */       this.stat.setString(1, para);
/* 1293 */       this.resultSet = this.stat.executeQuery();
/* 1294 */       while (this.resultSet.next())
/*      */       {
/* 1296 */         unitlist.add(new UnitBean(this.resultSet.getString("shp_bill_no"), this.resultSet.getString("shp_bill_date"), ""));
/*      */       }
/*      */     } catch (SQLException e) {
/* 1299 */       System.out.println("UnitBean" + se); } catch (Exception e) {
/* 1300 */       System.out.println("UnitBean " + e);
/*      */     } finally {
/* 1302 */       if (this.conn != null)
/*      */         try {
/* 1304 */           this.conn.close();
/* 1305 */           if (this.stat != null) this.stat.close();
/* 1306 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/* 1309 */           e.printStackTrace();
/*      */         }
/*      */     }
/* 1312 */     return unitlist;
/*      */   }
/*      */ 
/*      */   public String getSBByNameAjax(String para) {
/* 1316 */     if ((para != null) && (para.length() > 0)) {
/* 1317 */       para = para.toUpperCase() + "%";
/*      */     }
/*      */     else {
/* 1320 */       para = "%";
/*      */     }
/*      */ 
/* 1324 */     String unitlist = "";
/*      */     try {
/* 1326 */       this.conn = new connection().getConnection();
/*      */ 
/* 1328 */       this.stat = this.conn.prepareStatement("select  shp_bill_no,to_char(shp_bill_DATE,'dd-Mon-yyyy') shp_bill_date from exports.ei_sbill_master  where shp_bill_date>='01-APR-2012' and shp_bill_no like ?   order by 1");
/* 1329 */       this.stat.setString(1, para);
/* 1330 */       this.resultSet = this.stat.executeQuery();
/* 1331 */       while (this.resultSet.next())
/* 1332 */         unitlist = unitlist + this.resultSet.getString("shp_bill_no") + "#" + this.resultSet.getString("shp_bill_date");
/*      */     }
/*      */     catch (SQLException e) {
/* 1335 */       System.out.println("getSBByNameAjax" + se); } catch (Exception e) {
/* 1336 */       System.out.println("getSBByNameAjax " + e);
/*      */     } finally {
/* 1338 */       if (this.conn != null)
/*      */         try {
/* 1340 */           this.conn.close();
/* 1341 */           if (this.stat != null) this.stat.close();
/* 1342 */           if (this.resultSet != null) this.resultSet.close(); 
/*      */         }
/*      */         catch (SQLException e)
/*      */         {
/* 1345 */           e.printStackTrace();
/*      */         }
/*      */     }
/* 1348 */     return unitlist;
/*      */   }
/*      */ }
 