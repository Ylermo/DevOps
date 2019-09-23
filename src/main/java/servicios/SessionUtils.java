package servicios;

import javax.faces.context.FacesContext;
import modelo.Personal;

public class SessionUtils {
    
        public static Personal obtenerObjetoSesion() {
        return (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }

    public static String ObtenerNombreSesion() {
        Personal us = (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getNOMPER();
        } else {
            return null;
        }
    }

    public static String ObtenerCodigoSesion() {
        Personal us = (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return String.valueOf(us.getIDPER());
        } else {
            return null;
        }
    }
    
}
