package controlador;

import dao.CultivoImpl;
import dao.ReportesDao;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelo.Cultivo;

@Named(value = "cultivoC")
@ViewScoped
public class CultivoC implements Serializable {

    private Cultivo cultivoM;
    private Cultivo cultivoEdit;
    private CultivoImpl dao;
    private List<Cultivo> listaCultivo;
    private List<Cultivo> listaCultivoFilter;
    private boolean bt;

    public CultivoC() {
        cultivoM = new Cultivo();
        cultivoEdit = new Cultivo();
        dao = new CultivoImpl();
        listaCultivo = new ArrayList();
    }

    @PostConstruct
    public void init() {
        try {
            listCultivo("A");
        } catch (Exception e) {
        }
    }

    public void Registrar() throws Exception {
        try {
            dao.Registrar(cultivoM);
            limpiar();
            listCultivo("A");
            FacesContext.getCurrentInstance().
                    addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completo"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void Modificar() throws Exception {
        try {
            dao.Modificar(cultivoEdit);
            limpiar();
            listCultivo("A");
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar", "Completo"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void Eliminar(Cultivo cultivoM) throws Exception {
        try {
            dao.Eliminar(cultivoM);
            limpiar();
            listCultivo("A");
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar", "Completo"));
        } catch (Exception e) {
            throw e;
        }

    }

    public List<Cultivo> listCultivo(String estado) throws Exception {
        try {
            if (estado.equals("A")) {
                bt = true;
            } else {
                bt = false;
            }
            listaCultivo = dao.listar(estado);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public void limpiar() {
        try {
            cultivoM = new Cultivo();
        } catch (Exception e) {
        }
    }

    public boolean validarExistenciaCultivo(String cultivo) {
        CultivoImpl dao;
        try {
            dao = new CultivoImpl();
            Cultivo cultivoValidacion = new Cultivo();
            cultivoValidacion = dao.validarExistenciaCultivo(cultivo);
            String NOMCUL = cultivoValidacion.getNOMCUL().toLowerCase().trim();
            if (NOMCUL.equals(cultivo.toLowerCase().trim())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean validarExistenciaCultivoModificar(String cultivo) {
        CultivoImpl dao;
        Cultivo cultivoValidacion = new Cultivo();
        try {
            dao = new CultivoImpl();
            cultivoValidacion = dao.validarExistenciaCultivo(cultivo);
            String NOMCUL = cultivoValidacion.getNOMCUL().toLowerCase().trim();
            if (NOMCUL.equals(cultivo.toLowerCase().trim())) {
                if (cultivoValidacion.getIDCUL() == cultivoEdit.getIDCUL()) {
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(CultivoC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public void habilitar(Cultivo cultivoM) throws Exception {
        try {
            dao.habilitarCultivo(cultivoM);
            listCultivo("I");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Habilitar", null));
        } catch (Exception e) {
            throw e;
        }
    }

    public Cultivo getCultivoM() {
        return cultivoM;
    }

    public void setCultivoM(Cultivo cultivoM) {
        this.cultivoM = cultivoM;
    }

    public CultivoImpl getDao() {
        return dao;
    }

    public void setDao(CultivoImpl dao) {
        this.dao = dao;
    }

    public List<Cultivo> getListaCultivo() {
        return listaCultivo;
    }

    public void setListaCultivo(List<Cultivo> listaCultivo) {
        this.listaCultivo = listaCultivo;
    }

    public List<Cultivo> getListaCultivoFilter() {
        return listaCultivoFilter;
    }

    public void setListaCultivoFilter(List<Cultivo> listaCultivoFilter) {
        this.listaCultivoFilter = listaCultivoFilter;
    }

    public Cultivo getCultivoEdit() {
        return cultivoEdit;
    }

    public void setCultivoEdit(Cultivo cultivoEdit) {
        this.cultivoEdit = cultivoEdit;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

}
