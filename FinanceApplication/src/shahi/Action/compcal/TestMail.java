/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.compcal;

/**
 *
 * @author Ranjeet
 */
public class TestMail {
        public static void main(String[] args) {
  try {
                   
     ActivityMailFunction abc=new ActivityMailFunction();
     abc.sendmail();
     
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }
}
