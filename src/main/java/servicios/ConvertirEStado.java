package servicios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("Convertir")
public class ConvertirEStado implements Converter{

    @Override
    public Object getAsObject(FacesContext contex, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext contex, UIComponent component, Object value) {
        String TIPPER = "";
        if (value != null) {
            TIPPER = (String) value;
            switch (TIPPER) {
                case "A":
                    TIPPER = "Administrador";
                    break;
                case "U":
                    TIPPER = "Usuario";
                    break;
            }
        }
        return TIPPER;
    }

}
