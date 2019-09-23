package controlador;

import dao.DetalleInfoImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelo.DetalleInformacion;

@Named(value = "detalleInformacionC")
@ViewScoped
public class DetalleInformacionC implements Serializable {

    List<DetalleInformacion> listaDetInfo;
    List<DetalleInformacion> listaDetInfoFilter;
    DetalleInformacion detInfoMEditar;

    FacesContext facesContext = FacesContext.getCurrentInstance();
    Map params = facesContext.getExternalContext().getRequestParameterMap();
    Integer parametroObtenido = new Integer((String) params.get("id"));

    public DetalleInformacionC() {
    }

    @PostConstruct
    public void Inicio() {
        try {
            listar();
        } catch (Exception ex) {
            Logger.getLogger(DetalleInformacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() throws Exception {
        DetalleInfoImpl dao;
        dao = new DetalleInfoImpl();
        try {
            dao.Modificar(detInfoMEditar);
            listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void invalidar(DetalleInformacion detInfoM) throws Exception {
        DetalleInfoImpl dao;
        dao = new DetalleInfoImpl();
        try {
            dao.Invalidar(detInfoM);
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void validar(DetalleInformacion detInfoM) throws Exception {
        DetalleInfoImpl dao;
        dao = new DetalleInfoImpl();
        try {
            dao.Validar(detInfoM);
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        DetalleInfoImpl dao;
        dao = new DetalleInfoImpl();
        try { 
            if(parametroObtenido != null){
                listaDetInfo = dao.listarDetalles(String.valueOf(parametroObtenido));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<modelo.DetalleInformacion> getListaDetInfo() {
        return listaDetInfo;
    }

    public void setListaDetInfo(List<modelo.DetalleInformacion> listaDetInfo) {
        this.listaDetInfo = listaDetInfo;
    }

    public DetalleInformacion getDetInfoMEditar() {
        return detInfoMEditar;
    }

    public void setDetInfoMEditar(DetalleInformacion detInfoMEditar) {
        this.detInfoMEditar = detInfoMEditar;
    }

    public List<DetalleInformacion> getListaDetInfoFilter() {
        return listaDetInfoFilter;
    }

    public void setListaDetInfoFilter(List<DetalleInformacion> listaDetInfoFilter) {
        this.listaDetInfoFilter = listaDetInfoFilter;
    }

}
