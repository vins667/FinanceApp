package shahi.Action.MvxExp.Admin.Beans;

/**
 *
 * @author aswal
 */
public class GetListBean {
    private String LIST_CODE ;
    private String LIST_NAME ;
    private String LIST_ID ;

    public GetListBean(String LIST_CODE, String LIST_NAME)
    {
       this.LIST_CODE = LIST_CODE;
       this.LIST_NAME = LIST_NAME;

    }

     public GetListBean(String LIST_CODE, String LIST_NAME, String LIST_ID )
    {
       this.LIST_CODE = LIST_CODE;
       this.LIST_NAME = LIST_NAME;
       this.LIST_ID = LIST_ID;

    }

    public String getLIST_CODE() {
        return LIST_CODE;
    }

    public void setLIST_CODE(String LIST_CODE) {
        this.LIST_CODE = LIST_CODE;
    }

    public String getLIST_NAME() {
        return LIST_NAME;
    }

    public void setLIST_NAME(String LIST_NAME) {
        this.LIST_NAME = LIST_NAME;
    }

    public String getLIST_ID() {
        return LIST_ID;
    }

    public void setLIST_ID(String LIST_ID) {
        this.LIST_ID = LIST_ID;
    }

   
}
