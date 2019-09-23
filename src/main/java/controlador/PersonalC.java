package controlador;

import dao.PersonalImpl;
import dao.ReportesDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Personal;

@Named(value = "personalC")
@SessionScoped
public class PersonalC implements Serializable {

    private Personal personalM = new Personal();
    private List<Personal> persList;
    private Personal perEdit;
    private List<Personal> persListFilter;
    private boolean bt;

    @PostConstruct
    public void inicio() {
        try {
            listar("A");
        } catch (Exception e) {
        }
    }

    public void registar() throws Exception {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            dao.Registrar(personalM);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se registro satisfactoriamente", ""));
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            dao.Modificar(perEdit);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Moficacion", "Completa"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarCredencial() throws Exception {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            dao.Modificar(perEdit);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Moficacion", "Completa"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Personal perM) throws Exception {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            dao.Eliminar(perM);
            listar("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminacion", "Completa"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void habilitar(Personal personalM) throws Exception {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            dao.habilitarPersonal(personalM);
            listar("I");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Habilitar", null));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar(String filtro) throws Exception {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            if (filtro.equals("A")) {
                bt = true;
            } else {
                bt = false;
            }
            persList = dao.listaTipo(filtro);
        } catch (Exception e) {
            throw e;
        }

    }

    public boolean validarUsarioExistente(String usuario) {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            Personal usarioValidacion = new Personal();
            usarioValidacion = dao.validarUsuarioExistente(usuario);
            String USERPER = usarioValidacion.getUSERPER().toLowerCase().trim();
            if (USERPER.equals(usuario.toLowerCase().trim())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean validarExistenciaPersona(String dniPersona) {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            Personal personalValidacion = new Personal();
            personalValidacion = dao.validarExistenciaPersonal(dniPersona);
            String DNIPER = personalValidacion.getDNIPER().toLowerCase().trim();
            if (DNIPER.equals(dniPersona)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean validarExistenciasNumeros(String telefono) throws Exception {
        PersonalImpl dao;
        dao = new PersonalImpl();
        try {
            Personal telefonoValidacion = new Personal();
            telefonoValidacion = dao.validarTelefono(telefono);
            String TELPER = telefonoValidacion.getTELPER().toLowerCase().trim();
            if (TELPER.equals(telefono)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }



//    hola
    public void limpiar() {
        personalM = new Personal();
    }

    public Personal getPersonalM() {
        return personalM;
    }

    public void setPersonalM(Personal personalM) {
        this.personalM = personalM;
    }

    public List<Personal> getPersList() {
        return persList;
    }

    public void setPersList(List<Personal> persList) {
        this.persList = persList;
    }

    public Personal getPerEdit() {
        return perEdit;
    }

    public void setPerEdit(Personal perEdit) {
        this.perEdit = perEdit;
    }

    public List<Personal> getPersListFilter() {
        return persListFilter;
    }

    public void setPersListFilter(List<Personal> persListFilter) {
        this.persListFilter = persListFilter;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

}
