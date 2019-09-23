package servicios;

import controlador.PersonalC;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarUsarioExistente")
public class ValidarUsuario implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean existe = false;
        
        String usuario = value.toString().trim();
        
        PersonalC personalC = new PersonalC();
        
        try {
            if(personalC.validarUsarioExistente(usuario)){
                existe = true;
            }
        } catch (Exception e) {
        }
        if(existe){
            FacesMessage msg = new FacesMessage("Usuario existe");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
}
