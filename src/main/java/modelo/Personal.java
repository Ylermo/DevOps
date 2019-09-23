package modelo;

import java.io.Serializable;

public class Personal implements Serializable {

    private int IDPER;
    private String NOMPER;
    private String APEPER;
    private String DNIPER;
    private int TIP;
    private String TELPER;
    private String TIPPER;
    private String ESTAPER;
    private String USERPER;
    private String PSWPER;

    public int getIDPER() {
        return IDPER;
    }

    public void setIDPER(int IDPER) {
        this.IDPER = IDPER;
    }
    
    public String getNOMPER() {
        return NOMPER;
    }

    public void setNOMPER(String NOMPER) {
        this.NOMPER = NOMPER;
    }

    public String getAPEPER() {
        return APEPER;
    }

    public void setAPEPER(String APEPER) {
        this.APEPER = APEPER;
    }

    public String getDNIPER() {
        return DNIPER;
    }

    public void setDNIPER(String DNIPER) {
        this.DNIPER = DNIPER;
    }

    public String getTELPER() {
        return TELPER;
    }

    public void setTELPER(String TELPER) {
        this.TELPER = TELPER;
    }

    public String getTIPPER() {
        return TIPPER;
    }

    public void setTIPPER(String TIPPER) {
        this.TIPPER = TIPPER;
    }

    public String getESTAPER() {
        return ESTAPER;
    }

    public void setESTAPER(String ESTAPER) {
        this.ESTAPER = ESTAPER;
    }

    public String getUSERPER() {
        return USERPER;
    }

    public void setUSERPER(String USERPER) {
        this.USERPER = USERPER;
    }

    public String getPSWPER() {
        return PSWPER;
    }

    public void setPSWPER(String PSWPER) {
        this.PSWPER = PSWPER;
    }

    public int getTIP() {
        return TIP;
    }

    public void setTIP(int TIP) {
        this.TIP = TIP;
    }   
    
}

