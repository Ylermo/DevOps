package servicios;

import controlador.SectorC;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("validarExistenciaSector")
public class ValidarSector implements Validator {

    @Override
    public void validate(FacesContext context,
            UIComponent component, Object value)
            throws ValidatorException {
        
        boolean existe = false;
        String sector = value.toString().trim();

        SectorC sectorC = new SectorC();
        
        try {
            
            if (sectorC.validarExistenciaSector(sector)) {
                existe = true;
            }

        } catch (Exception e) {

        }
        if (existe) {
            FacesMessage msg = new FacesMessage("Sector Existente");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}

