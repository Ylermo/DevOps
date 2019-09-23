package controlador;

import dao.Conexion;
import servicios.SessionUtils;
import dao.LoginDao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Personal;
import servicios.Encriptar;

@Named(value = "loginC")
@SessionScoped
public class LoginC implements Serializable {

    Personal personalM = new Personal();
    LoginDao dao;
    private String User;
    private String Pass;

    public void session() throws Exception {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            dao = new LoginDao();
            Encriptar encriptar = new Encriptar();
            //personalM = dao.login(User, encriptar.encriptar(Pass));
            personalM = dao.login(User, Pass);
            if (personalM != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", personalM);
                switch (personalM.getTIPPER()) {
                    case "A":
                        ec.redirect(ec.getRequestContextPath() + "/faces/Vistas/index/index.xhtml");
                        break;
                    case "U":
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Debes ser administrador", "para iniciar session"));
                        break;
                }
            } else {
                setPass(null);
                personalM = new Personal();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Ingreso mal el usuario o contraseña", "o el usuario debe estar inactivo"));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void securityLogin() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        personalM = SessionUtils.obtenerObjetoSesion();
        if (personalM != null) {
            switch (personalM.getTIPPER()) {
                case "A":
                    ec.redirect(ec.getRequestContextPath() + "/faces/Vistas/index/index.xhtml");
                    break;
                case "U":
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Debes ser administrador", "para iniciar session"));
                    break;
            }
        }
    }

    public void securitySession() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (SessionUtils.obtenerObjetoSesion() == null) {
            ec.redirect(ec.getRequestContextPath() + "/faces/Vistas/Template/Login2.xhtml");
        }
    }

   public void cerrar() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect(ec.getRequestContextPath());
    }

    public Personal getPersonalM() {
        return personalM;
    }

    public void setPersonalM(Personal personalM) {
        this.personalM = personalM;
    }

    public LoginDao getDao() {
        return dao;
    }

    public void setDao(LoginDao dao) {
        this.dao = dao;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

}
