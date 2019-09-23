package servicios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

@FacesConverter("ConverterDetInfoEstado")
public class ConverterDetInfoEstado implements Converter{
    
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
                case "E":
                    TIPPER = "Enviado";
                    break;
                case "V":
                    TIPPER = "Validado";
                    break;
                case "R":
                    TIPPER = "Rechazado";
                    break;
            }
        }
        return TIPPER;
    }
    
}
