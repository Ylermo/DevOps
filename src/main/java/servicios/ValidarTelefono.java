package servicios;

import controlador.PersonalC;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarExistenciasNumeros")
public class ValidarTelefono implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean exite = false;
        
        String telefono = value.toString().trim();
        
        PersonalC personalC = new PersonalC();
        try {
            if(personalC.validarExistenciasNumeros(telefono)){
                exite = true;
            }
        } catch (Exception e) {          
        }
            if(exite){
            FacesMessage msg = new FacesMessage("Telefono existe");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
}
