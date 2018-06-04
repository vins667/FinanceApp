/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.util;

/**
 *
 * @author Vivek
 */
public class HelperConstantsFnl {
    public static final String COMPANY = "111";
	public static final String FILE_TYPE_SYSTEM = "FILE_TYPE_SYSTEM";
	public static final String DB2_DATABASE_FILE = SRMPropertyHelper.getValueWithPath(FILE_TYPE_SYSTEM, "DB2_DATABASE_FILE");
	public static final String CONFIG_PATH_PROPERTY = "SRMDATA/properties/srm_system.properties";
        public static final String CINFDB_PACKAGE = "CINFDBPRD.";
        public static final String DATABASE_LIBRARY = "M3FDBPRD";
        public static final String DATABASE_LIBRARY_CRM="CRMDATA";
        public static final String LINE_TYPE = "0";
        public static final String PARTNER = "R100";
        public static final String MESSAGE_TYPE = "DESADV";
         //constants hardcoded
    public static final String MODE_MFG = "1-Mfg";
    public static final String MODE_PUR = "2-Pur";
    public static final String MODE_DIS = "3-Dis";
    public static final String MODE_QTY = "1-Qty";
    public static final String MODE_ITEM_QTY = "3-Item/Qty";
    public static final String RESULT_SUCCESS = "Success";
    public static final String PROD_STRU_TYPE = "001";
    public static final String SUB_TYPE = "2";
    
     //session related constatnts
    public static final String USER_SESSION_DATA = "USER_SESSION_DATA";
    public static final String SESSION_AFB = "AFB";
    public static final String SESSION_MERC_IFIN = "IFIN";
    public static final String SESSION_STYLE_IFIN = "STYLEIFIN";
    public static final String SESSION_STYLE_DKSU = "DSKU";
    public static final String SESSION_MERC_DKSU = "DSKU_MAK";
}
