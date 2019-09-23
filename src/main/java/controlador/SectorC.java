package controlador;

import dao.ReportesDao;
import dao.SectorImpl;
import dao.UbigeoImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelo.Sector;

@Named(value = "sectorC")
@ViewScoped
public class SectorC implements Serializable {

    private Sector sectorM = new Sector();
    private Sector sectorEdit = new Sector();
    private List<Sector> listaSector;
    private List<Sector> listaSectorFiltro;
    private boolean bt;

    @PostConstruct
    public void inicio() {
        try {
            listar("A");
        } catch (Exception e) {
        }
    }

    public void Registrar() throws Exception {
        SectorImpl dao;
        UbigeoImpl daoUbigeo = new UbigeoImpl();
        dao = new SectorImpl();
        try {
            sectorM.setCODUBI(daoUbigeo.obtenerCodigoUbigeo(sectorM.getCODUBI()));
            dao.Registrar(sectorM);
            listar("A");
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completo"));
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        SectorImpl dao;
        UbigeoImpl daoUbigeo = new UbigeoImpl();
        dao = new SectorImpl();
        try {
            sectorEdit.setCODUBI(daoUbigeo.obtenerCodigoUbigeo(sectorEdit.getCODUBI()));
            dao.Modificar(sectorEdit);
            listar("A");
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar", "Completo"));
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Sector sector) throws Exception {
        SectorImpl dao;
        dao = new SectorImpl();
        try {
            dao.Eliminar(sector);
            listar("A");
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar", "Completo"));
            limpiar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void habilitar(Sector sector) throws Exception {
        SectorImpl dao;
        dao = new SectorImpl();
        try {
            dao.Habilitar(sector);
            listar("I");
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Habilitar", "Completo"));
            limpiar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void listar(String estado) throws Exception {
        SectorImpl dao;
        dao = new SectorImpl();
        try {
            if (estado.equals("A")) {
                bt = true;
            } else {
                bt = false;
            }
            listaSector = dao.listado(estado);
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        try {
            sectorM = new Sector();
        } catch (Exception e) {
        }

    }

    public boolean validarExistenciaSector(String sector) {
        SectorImpl dao;
        dao = new SectorImpl();
        try {
            Sector cultivoValidacion = new Sector();
            cultivoValidacion = dao.validarExistenciaSector(sector);
            String NOMSECT = cultivoValidacion.getNOMSECT().toLowerCase().trim();
            if (NOMSECT.equals(sector.toLowerCase().trim())) {
                System.out.println("Es igual al sector enviado");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public List<String> completeUbigeo(String query) throws Exception {
        UbigeoImpl dao = new UbigeoImpl();
        List<String> lista = new ArrayList();
        try {
            lista = dao.listaAutoCompleteUbigeo(query);
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public List<Sector> completeText(String query) throws Exception {
        SectorImpl dao = new SectorImpl();
        return dao.listaAutoComplete(query);
    }

    public Sector obtenerCurso_codigo(int id_curso) {
        Sector sector = new Sector();
        sector.setIDSECT(1);
        sector.setNOMSECT("Canal 1");
        sector.setAREASECT(200);
        sector.setIDVALL(1);
        sector.setCODUBI("101010");
        return sector;
    }


    public Sector getSectorM() {
        return sectorM;
    }

    public void setSectorM(Sector sectorM) {
        this.sectorM = sectorM;
    }

    public List<Sector> getListaSector() {
        return listaSector;
    }

    public void setListaSector(List<Sector> listaSector) {
        this.listaSector = listaSector;
    }

    public Sector getSectorEdit() {
        return sectorEdit;
    }

    public void setSectorEdit(Sector sectorEdit) {
        this.sectorEdit = sectorEdit;
    }

    public List<Sector> getListaSectorFiltro() {
        return listaSectorFiltro;
    }

    public void setListaSectorFiltro(List<Sector> listaSectorFiltro) {
        this.listaSectorFiltro = listaSectorFiltro;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }
}
