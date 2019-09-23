package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Asignacion;
import modelo.Informacion;

public class InformacionImpl extends Conexion implements CRUD<Informacion> {

    @Override
    public void Registrar(Informacion infoM) throws Exception {
        this.conectar();
        try {
            String sql = "insert into INFORMACION (FECINFO,IDASIG,IDPER,TOTAREDET, ESTAINFO) "
                    + "values (CAST(sys_extract_utc(SYSTIMESTAMP) AS DATE)-(5/24)),?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, infoM.getIDASIG());
            ps.setInt(2, infoM.getIDPER());
            ps.setDouble(3, infoM.getTOTAREDET());
            ps.setString(4, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Modificar(Informacion infoM) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE INFORMACION set FECINFO=?, IDASIG=?, IDPER=?, TOTAREDET=? where IDINFO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(infoM.getFECINFO().getTime()));
            ps.setInt(2, infoM.getIDASIG());
            ps.setInt(3, infoM.getIDPER());
            ps.setDouble(4, infoM.getTOTAREDET());
            ps.setInt(5, infoM.getIDINFO());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<String> listaAnios() throws Exception {
        List<String> listAnios;
        this.conectar();
        try {
            listAnios = new ArrayList();
            String sql = "SELECT EXTRACT(YEAR FROM FECINFO) FECINFO"
                    + " FROM INFORMACION "
                    + " GROUP BY EXTRACT(YEAR FROM FECINFO)"
                    + " ORDER BY FECINFO ASC";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                listAnios.add(rs.getString("FECINFO"));
            }
            if (listAnios.size() == 0) {
                listAnios.add("2019");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        System.out.println(listAnios);
        return listAnios;
    }

    public List<Informacion> listaFiltroMes(Date primerDiaMes, Date ultimoDiaMes) throws Exception {
        List<Informacion> listM;
        Informacion infoM;
        this.conectar();
        try {
            listM = new ArrayList();
            String sql = "Select I.*,P.APEPER, P.NOMPER,\n"
                    + "       C.NOMSECT \n"
                    + "from INFORMACION I\n"
                    + "inner join ASIGNACION_PERSONA  A ON I.IDASIG = A.IDASIGPER \n"
                    + "inner join PERSONA  P ON A.IDPER = P.IDPER\n"
                    + "inner join SECTOR  C ON A.IDSECT = C.IDSECT \n"
                    + "where I.FECINFO BETWEEN ? and ?\n"
                    + "ORDER BY I.FECINFO DESC";
            //+ "where I.FECINFO between '"+ new java.sql.Date(inico.getTime())+"' and '"+ new java.sql.Date(ultimo.getTime()) +"'";
            Statement st = getCn().createStatement();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(primerDiaMes.getTime()));
            ps.setDate(2, new java.sql.Date(ultimoDiaMes.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                infoM = new Informacion();
                infoM.setIDINFO(rs.getInt("IDINFO"));
                infoM.setFECINFO(rs.getDate("FECINFO"));
                infoM.setIDASIG(rs.getInt("IDASIG"));
                infoM.setIDPER(rs.getInt("IDPER"));
                infoM.setTOTAREDET(rs.getInt("TOTAREDET"));
                infoM.setAPEPER(rs.getString("APEPER"));
                infoM.setNOMPER(rs.getString("NOMPER"));
                infoM.setNOMSECT(rs.getString("NOMSECT"));
                infoM.setESTAINFO(rs.getString("ESTAINFO"));
                listM.add(infoM);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listM;
    }

    public List<Informacion> getInformacionSinDetalle(int IDPER) throws Exception {

        this.conectar();
        List<Informacion> listM = new ArrayList();
        Informacion infoM;
        try {
            String sql = "select I.IDINFO, AP.IDSECT from INFORMACION I\n"
                    + "inner join ASIGNACION_PERSONA AP\n"
                    + "    ON I.IDASIG = AP.IDASIGPER\n"
                    + "where NOT EXISTS(select *\n"
                    + "                from INFORMACION I1\n"
                    + "                inner join DETALLE_INFORMACION DI1\n"
                    + "                    ON I1.IDINFO = DI1.IDINFO\n"
                    + "                where DI1.IDINFO = I.IDINFO and I1.FECINFO between TRUNC(CAST(sys_extract_utc(SYSTIMESTAMP) AS DATE)-(5/24), 'MM') \n"
                    + "                    and TRUNC(LAST_DAY(CAST(sys_extract_utc(SYSTIMESTAMP) AS DATE)-(5/24))))\n"
                    + "    and I.FECINFO between TRUNC(CAST(sys_extract_utc(SYSTIMESTAMP) AS DATE)-(5/24), 'MM') \n"
                    + "                    and TRUNC(LAST_DAY(CAST(sys_extract_utc(SYSTIMESTAMP) AS DATE)-(5/24)))\n"
                    + "    and I.IDPER = ? and I.ESTAINFO = 'A'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, IDPER);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                infoM = new Informacion();
                infoM.setIDINFO(rs.getInt("IDINFO"));
                infoM.setIDSECT(rs.getInt("IDSECT"));
                listM.add(infoM);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listM;
    }

    public List<Asignacion> asigRestantes() throws Exception {
        List<Asignacion> asignaciones = new ArrayList();
        Asignacion asignacion;
        this.conectar();
        try {
            String sql = "select ASIGNACION_PERSONA.IDASIGPER, SECTOR.NOMSECT, SECTOR.IDSECT from ASIGNACION_PERSONA \n"
                    + "inner join SECTOR \n"
                    + "    ON SECTOR.IDSECT = ASIGNACION_PERSONA.IDSECT\n"
                    + "where \n"
                    + "NOT EXISTS(select IDASIG\n"
                    + "from INFORMACION\n"
                    + "where INFORMACION.IDASIG = ASIGNACION_PERSONA.IDASIGPER AND\n"
                    + "INFORMACION.FECINFO between TRUNC(CAST(sys_extract_utc(SYSTIMESTAMP) AS DATE)-(5/24), 'MM') \n"
                    + "and TRUNC(LAST_DAY(CAST(sys_extract_utc(SYSTIMESTAMP) AS DATE)-(5/24))))\n"
                    + "AND EXISTS (select IDSECT from ASIGNACION_CULTIVO\n"
                    + "where ASIGNACION_PERSONA.IDSECT = ASIGNACION_CULTIVO.IDSECT)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                asignacion = new Asignacion();
                asignacion.setIDASIGPER(rs.getInt("IDASIGPER"));
                asignacion.setIDSECT(rs.getInt("IDSECT"));
                asignacion.setSECTOR(rs.getString("NOMSECT"));
                asignaciones.add(asignacion);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return asignaciones;
    }

    @Override
    public void Eliminar(Informacion gen) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Informacion> lista() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Informacion> listar() throws Exception {
        this.conectar();
        List<Informacion> info = null;
        Informacion informa;
        try {
            info= new ArrayList();
            String sql = "SELECT  DISTINCT UU.CODUBI,\n"
                    + "         UU.NOMDIST\n"
                    + "FROM INFORMACION I \n"
                    + "INNER JOIN ASIGNACION_PERSONA PAP ON PAP.IDASIGPER = I.IDASIG \n"
                    + "INNER JOIN SECTOR US ON US.IDSECT = PAP.IDSECT\n"
                    + "INNER JOIN UBIGEO UU ON UU.CODUBI = US.CODUBI";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                informa = new Informacion();
                informa.setCODUBI(rs.getInt("CODUBI"));
                informa.setNOMDIST(rs.getString("NOMDIST"));
                info.add(informa);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }finally{
            this.Cerrar();
        }
        return info;
    }
}
