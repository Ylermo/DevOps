package modelo;

import java.util.Date;

public class Asignacion {
    private int IDASIGPER;
    private Date FECASIGS;
    private String SECTOR;
    private int IDSECT;
    private String PERSONA;
    private int IDPER;
    private Date FECASIGD;
    private String ESTAPER;
    
    public Date getFECASIGD() {
        return FECASIGD;
    }

    public void setFECASIGD(Date FECASIGD) {
        this.FECASIGD = FECASIGD;
    }

    public int getIDASIGPER() {
        return IDASIGPER;
    }

    public void setIDASIGPER(int IDASIGPER) {
        this.IDASIGPER = IDASIGPER;
    }

    public Date getFECASIGS() {
        return FECASIGS;
    }

    public void setFECASIGS(Date FECASIGS) {
        this.FECASIGS = FECASIGS;
    }

    public int getIDSECT() {
        return IDSECT;
    }

    public void setIDSECT(int IDSECT) {
        this.IDSECT = IDSECT;
    }

    public int getIDPER() {
        return IDPER;
    }

    public void setIDPER(int IDPER) {
        this.IDPER = IDPER;
    }

    public String getSECTOR() {
        return SECTOR;
    }

    public void setSECTOR(String SECTOR) {
        this.SECTOR = SECTOR;
    }

    public String getPERSONA() {
        return PERSONA;
    }

    public void setPERSONA(String PERSONA) {
        this.PERSONA = PERSONA;
    }

    public String getESTAPER() {
        return ESTAPER;
    }

    public void setESTAPER(String ESTAPER) {
        this.ESTAPER = ESTAPER;
    }
 
}