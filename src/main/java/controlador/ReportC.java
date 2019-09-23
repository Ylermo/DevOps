package controlador;

import dao.ReportesDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC03
 */
@Named(value = "reportC")
@SessionScoped
public class ReportC implements Serializable {

    public void reportcultivo() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Cultivo.jasper", "Lista de Cultivo.pdf");
        } catch (Exception e) {
        }
    }

    public void reportpersonal() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Personal.jasper", "Lista del Personal.pdf");
        } catch (Exception e) {
        }
    }

    public void reportsector() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Sector.jasper", "Lista de Sectores.pdf");
        } catch (Exception e) {
            System.out.println("Error: "+e );
        }
    }

    public void reportasignacionpersonal() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "PersonaAsignacion.jasper", "Lista de Asignacion del Personal.pdf");
        } catch (Exception e) {
        }
    }

    public void reportasignacioncultivo() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "AsignacionCultivo.jasper", "Lista de Asignacion de Cutlivo.pdf");
        } catch (Exception e) {
        }
    }

    public void reporteinformacion(int IDINFO) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            HashMap parameters = new HashMap();
            parameters.put("IDINFO", IDINFO);
            report.exportarXLSX(parameters, "Informacion.jasper","informacion.xlsx");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reportpara(String TIPCUL) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            if (TIPCUL == null) {
                TIPCUL = "";
            }
            HashMap parameters = new HashMap();
            parameters.put("TIPCUL", TIPCUL);
            report.exportarPDFGlobal(parameters, "CultivoParameters.jasper", "Lista de cultivo.pdf");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reporteinformacions(int IDINFO) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            HashMap parameters = new HashMap();
            parameters.put("IDINFO", IDINFO);
            //report.exportarPDFGlobal(parameters, "Informacion.jasper", "Informacion de Sector.pdf");
            report.exportarXLSX(parameters, "Informacion.jasper","informacion.xlsx");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reporteinformacionMes(Date primerDiaMes, Date ultimoDiaMes) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            HashMap parameters = new HashMap();
            parameters.put("PRIMERDIA", new java.sql.Date(primerDiaMes.getTime()));
            parameters.put("ULTIMODIA", new java.sql.Date(ultimoDiaMes.getTime()));
            //report.exportarPDFGlobal(parameters, "Informacion.jasper", "Informacion de Sector.pdf");
            report.exportarXLSX(parameters, "InformacionMes.jasper","informacion.xlsx");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void reporteinfodist(int CODUBI){
        ReportesDao report = new ReportesDao();
        try{
            HashMap parameters = new HashMap();
            parameters.put("CODUBI", CODUBI);
            report.exportarXLSX(parameters, "InformacionDistrito.jasper", "informacion.xlsx");
        }catch(Exception e){
            
        }
    }
}
