
package shahi.Action.MailProvider;

/**
 *
 * @author Ranjeet Gautam
 */
public class pass_decoder {

public String passdecoder (String  passwrd) {
     String pass=passwrd;
    int lintlen=0;
    int lintasc=0;
   String vstrPassword1=pass;
  lintlen =vstrPassword1.length();

 String lstrpass =null;
 for(int i=0; i<lintlen; i++)
 {
 lintasc=(int) vstrPassword1.charAt(i);
 
 if(lintasc > 32 && lintasc < 127)
 {
 lintasc=lintasc-25-(i+1);
 if(lintasc < 32)
 {
     lintasc = lintasc + 94;
 }
 }

 
 if(lstrpass==null)
 {
 lstrpass =Character.toString((char) lintasc);
 }else{
 lstrpass = lstrpass + Character.toString((char) lintasc);
 }
 }


 return lstrpass;


    }

}
