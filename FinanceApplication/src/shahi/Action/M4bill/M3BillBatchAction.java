package shahi.Action.M4bill;

import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
*
* @author Vivek
*/
public class M3BillBatchAction extends HttpServlet implements Runnable {
	static final long serialVersionUID = 1;
    Thread t = new Thread(this);
    static boolean READ = true;
    ResourceBundle aResourcBundle = null;
    
    public M3BillBatchAction() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }
    
    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
    
    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        if (getValue("SCHEDULAR_ON_M3BILL").equals("1")) {
            try {
                
                t.start();
                System.out.println("M3 Bill function started");
            } catch (Exception e) {
                System.out.println("M3 Bill.java "+e.getMessage());
            }
        } else {
            System.out.println("M3 Bill function disabled");
        }
    }
    
    public void run() {
        try {
            //Connect Oracle
            while (READ) {
            	
                 M3BillBatchFunction obj=new M3BillBatchFunction();
                 obj.UpdateBatch();

//Sleep Thread
                if (READ) {
                    t.sleep(Integer.parseInt(getValue("SCHEDULAR_SLEEP_TIME_M3BILL")));

                    System.out.println("M3 Bill function started" + new Date());
                }
            }


        } catch (Exception ex) {
            System.out.println("M3Bill function disabled "+ex.getMessage());
        } finally {

            try {
                /*if (READ) {
                t.sleep(Integer.parseInt(getValue("SCHEDULAR_SLEEP_TIME")));

                System.out.println("GrnUpdateInMovex function started"+new Date());
                }*/
            } catch (Exception e) {

                System.out.println("M3Bill function disabled "+e.getMessage());
            }
        }
    }
    @Override
    public void destroy() {
        try {
            if (t.isAlive()) {
                READ = false;
                t.join();
                System.out.println("M3Bill function disabled");
            }
        } catch (Exception ex) {
            System.out.println("M3Bill function disabled.java "+ex.getMessage());
        }
        super.destroy();
    }
}
