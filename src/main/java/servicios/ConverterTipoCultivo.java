package servicios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("TipCulConverter")
public class ConverterTipoCultivo implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return value;
    
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    
        String TIPCUL = "";
        TIPCUL = (String) value;
        
        if(value != null){
            
            switch(TIPCUL){
                case "PM":
                    TIPCUL = "Permanente";
                    break;
                case "TS":
                    TIPCUL = "Transitorios";
                    break;
                case "SP":
                    TIPCUL = "Semi permanente";
                    break;
                case "HT":
                    TIPCUL = "Hortalizas";
                    break;
                case "OT":
                    TIPCUL = "Otros";
                    break;            
            }            
        }      
        return TIPCUL;
    }
    
}
