/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Vivek
 */
public class SRMPropertyHelper {
    private static Map propertyMap = new HashMap();
    private static Properties configProperties;
    private static String className = "SRMPropertyHelper";
    private static String path;
    
    /**
     * The method is called on start up and is used to load all the property
     * files. It uses -HelperConstantsFnl.CONFIG_PATH_PROPERTY to get the path of the file that contains
     * all the property files paths
     *
     * @throws Exception - If an error occurs while loading the file.
     **/
    public static void load(String filePath) throws Exception {
        path = filePath;
        String configFilePath = filePath + HelperConstantsFnl.CONFIG_PATH_PROPERTY;
        System.out.println("configProperties -> " + configFilePath);
        configProperties = loadProperties(configFilePath);
        propertyMap.put(HelperConstantsFnl.FILE_TYPE_SYSTEM, configProperties);
    }
    
    /**
     * The method is called is used to load the property file whose path is
     * sent as argument.
     *
     * @param filePath String filePath of the file to be loaded
     * @return Properties object
     * @throws Exception - If an error occurs while loading the file.
     **/
    private static Properties loadProperties(String filePath) throws Exception {
        Properties properties = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (FileNotFoundException fne) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, className, fne, CRMLogHelper.STR_FATAL_PRIORITY);
            throw new Exception(fne.getMessage());
        } catch (IOException ioe) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, className, ioe, CRMLogHelper.STR_FATAL_PRIORITY);
            throw new Exception(ioe.getMessage());
        }
        return properties;
    }

	public static String getValueWithPath(String arg_type, String arg_property) {
        String propertyVal = null;
        Properties properties = (Properties) propertyMap.get(arg_type);
        if (properties != null) {
            propertyVal = (String) properties.get(arg_property);
            if (propertyVal != null) {
                propertyVal = propertyVal.trim();
            }
        }
        propertyVal = path + propertyVal;
        return propertyVal;
    }
}
