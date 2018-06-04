package shahi.Action.Common;

import com.ibm.as400.access.*;
import java.io.*;
import java.util.*;

public class Ftpfileupload
{
  public String  FtpFileCopy(String connst,String cuser,String cpass,String srFile,String dtFile )
{
  String Result = "OK";
  FTP client = new FTP(connst,cuser,cpass);
  try
  {
      client.cd("/");
      //client.setDataTransferType(FTP.BINARY);\\
      client.put(srFile,dtFile);

      // Here I only display the FTP completion message
      // but you can analyze the returned text string
      //System.out.println(client.getLastMessage());

      client.disconnect();
  }
  catch (Exception e)
  {
     System.out.println( "FTP Command did not run"+e.toString() );
      Result = "NOK";
  }

  return (Result);
}
}
