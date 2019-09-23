package servicios;

import controlador.SectorC;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modelo.Sector;

@FacesConverter("ConvertirSector")
public class ConverterSector implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("component");
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{sectorC}", SectorC.class);
        SectorC bean = (SectorC)vex.getValue(ctx.getELContext());
        Sector sectorM;
        try {
            sectorM = bean.obtenerCurso_codigo(new Integer(value));
        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Desconocido", "Error en Codigo");
            throw new ConverterException(message);
        }
        if (sectorM == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Desconocido", "Error en Objeto");
            throw new ConverterException(message);
        }
        return sectorM;

        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(context == null){
            throw new NullPointerException("Context");
        }
        
        if(component == null){
            throw new NullPointerException("Component");
        } 
        
        if(value == null || String.valueOf(((Sector) value).getIDSECT()) == null){
            return "";
        }
        
        return String.valueOf(((Sector) value).getIDSECT());

    }

}
