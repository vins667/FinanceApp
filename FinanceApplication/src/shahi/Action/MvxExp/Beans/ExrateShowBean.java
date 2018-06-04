

package shahi.Action.MvxExp.Beans;


public class ExrateShowBean {
    private String curr_desc;
    private String curr_code;
    private String exp_rate;
    private String imp_rate;
    private String begin_date;
    private String end_date;

    public ExrateShowBean(String curr_desc, String curr_code, String exp_rate, String imp_rate, String begin_date, String end_date) {
        this.curr_desc = curr_desc;
        this.curr_code = curr_code;
        this.exp_rate = exp_rate;
        this.imp_rate = imp_rate;
        this.begin_date = begin_date;
        this.end_date = end_date;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getCurr_code() {
        return curr_code;
    }

    public void setCurr_code(String curr_code) {
        this.curr_code = curr_code;
    }

    public String getCurr_desc() {
        return curr_desc;
    }

    public void setCurr_desc(String curr_desc) {
        this.curr_desc = curr_desc;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getExp_rate() {
        return exp_rate;
    }

    public void setExp_rate(String exp_rate) {
        this.exp_rate = exp_rate;
    }

    public String getImp_rate() {
        return imp_rate;
    }

    public void setImp_rate(String imp_rate) {
        this.imp_rate = imp_rate;
    }


}
