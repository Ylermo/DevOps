package controlador;

import dao.AsignacionCultImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.AsignacionCult;

@Named(value = "asignacionCultC")
@ViewScoped
public class AsignacionCultC implements Serializable {

    private AsignacionCultImpl dao;
    private AsignacionCult asigM;
    private AsignacionCult asigMEdit;
    private List<AsignacionCult> listAsig;
    private List<AsignacionCult> listAsigRegistrar;
    private String[] listAsigSelect;
    private List<AsignacionCult> listAsigFilter;
    private boolean bt;

    public AsignacionCultC() {
        asigM = new AsignacionCult();
        asigMEdit = new AsignacionCult();
        dao = new AsignacionCultImpl();
        listAsig = new ArrayList();
    }

    @PostConstruct
    public void inicio() {
        try {
            listarTipo("A");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void asignarcult() throws Exception {
        try {
            if (dao.verificarExistecia(asigM)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "El cultivo ya esta asignado a ese sector", null));
            } else {
                dao.Registrar(asigM);
                listarTipo("A");
                limpiar();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Se asigno correctamente.", null));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarasig() throws Exception {
        int id = asigMEdit.getIDASIGCUL();
        try {
            dao.Modificar(asigMEdit);
            if (id > 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Completa"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ""));
            }
            listarTipo("A");
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(AsignacionCult asigM) throws Exception {
        try {
            dao.Eliminar(asigM);
            listarTipo("A");
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminacion", "Completa"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarTipo(String estado) throws Exception {
        try {
            if (estado.equals("A")) {
                bt = true;
            } else {
                bt = false;
            }
            listAsig = dao.listarTipo(estado);
        } catch (Exception e) {
            throw e;
        }
    }

    public void habilitar(AsignacionCult asigM) throws Exception {
        try {
            dao.habilitarAsignacionCultivo(asigM);
            listarTipo("I");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Habilitar", null));
        } catch (Exception e) {
            throw e;
        }
    }

    public void verInactivos() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/Vistas/Asignacion/ListAsigCulInactivos.xhtml");
    }

    public void limpiar() {
        asigM = new AsignacionCult();
    }

    public AsignacionCultImpl getDao() {
        return dao;
    }

    public void setDao(AsignacionCultImpl dao) {
        this.dao = dao;
    }

    public AsignacionCult getAsigM() {
        return asigM;
    }

    public void setAsigM(AsignacionCult asigM) {
        this.asigM = asigM;
    }

    public List<AsignacionCult> getListAsig() {
        return listAsig;
    }

    public void setListAsig(List<AsignacionCult> listAsig) {
        this.listAsig = listAsig;
    }

    public AsignacionCult getAsigMEdit() {
        return asigMEdit;
    }

    public void setAsigMEdit(AsignacionCult asigMEdit) {
        this.asigMEdit = asigMEdit;
    }

    public List<AsignacionCult> getListAsigFilter() {
        return listAsigFilter;
    }

    public void setListAsigFilter(List<AsignacionCult> listAsigFilter) {
        this.listAsigFilter = listAsigFilter;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

    public String[] getListAsigSelect() {
        return listAsigSelect;
    }

    public void setListAsigSelect(String[] listAsigSelect) {
        this.listAsigSelect = listAsigSelect;
    }

}
