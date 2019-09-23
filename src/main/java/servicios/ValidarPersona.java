package servicios;

import controlador.PersonalC;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarExistenciaPersona")
public class ValidarPersona implements Validator {

    @Override
    public void validate(FacesContext context,
            UIComponent component, Object value)
            throws ValidatorException {
        boolean existe = false;
        String DNIPER = value.toString().trim();

        PersonalC personalC = new PersonalC();
        
        try {
            
            if (personalC.validarExistenciaPersona(DNIPER)) {
                existe = true;
            }

        } catch (Exception e) {

        }
        if (existe) {
            FacesMessage msg = new FacesMessage("DNI existente");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
