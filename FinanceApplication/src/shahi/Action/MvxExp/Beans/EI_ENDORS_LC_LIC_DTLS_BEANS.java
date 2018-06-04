package shahi.Action.MvxExp.Beans;

public class EI_ENDORS_LC_LIC_DTLS_BEANS {
    private String REF_TYPE;
    private String REF_NO;

    public EI_ENDORS_LC_LIC_DTLS_BEANS(String REF_TYPE, String REF_NO) {
        this.REF_TYPE = REF_TYPE;
        this.REF_NO = REF_NO;
    }

    public String getREF_NO() {
        return REF_NO;
    }

    public void setREF_NO(String REF_NO) {
        this.REF_NO = REF_NO;
    }

    public String getREF_TYPE() {
        return REF_TYPE;
    }

    public void setREF_TYPE(String REF_TYPE) {
        this.REF_TYPE = REF_TYPE;
    }

    @Override
    public String toString() {
        return "EI_ENDORS_LC_LIC_DTLS_BEANS{" + "REF_TYPE=" + REF_TYPE + "REF_NO=" + REF_NO + '}';
    }

}
