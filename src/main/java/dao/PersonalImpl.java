package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Personal;
import servicios.Encriptar;

public class PersonalImpl extends Conexion implements CRUD<Personal> {

    @Override
    public void Registrar(Personal personalM) throws Exception {
        Encriptar encriptar = new Encriptar();
        this.conectar();
        try {
            String sql = "insert into persona(NOMPER,APEPER,DNIPER,TELPER,TIPPER,ESTAPER,USERPER,PSWPER) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, personalM.getNOMPER().trim());
            ps.setString(2, personalM.getAPEPER().trim());
            ps.setString(3, personalM.getDNIPER());
            ps.setString(4, personalM.getTELPER());
            ps.setString(5, personalM.getTIPPER());
            ps.setString(6, "A");
            ps.setString(7, personalM.getUSERPER());
            ps.setString(8, encriptar.encriptar(personalM.getPSWPER()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Modificar(Personal personalM) throws Exception {
        this.conectar();
        Encriptar encriptar = new Encriptar();
        try {
            String sql = "update persona set NOMPER=?,APEPER=?,DNIPER=?,TELPER=?,TIPPER=?,ESTAPER=? where IDPER=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, personalM.getNOMPER());
            ps.setString(2, personalM.getAPEPER());
            ps.setString(3, personalM.getDNIPER());
            ps.setString(4, personalM.getTELPER());
            ps.setString(5, personalM.getTIPPER());
            ps.setString(6, personalM.getESTAPER());
            ps.setInt(7, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void ModificarCredencial(Personal personalM) throws Exception {
        this.conectar();
        Encriptar encriptar = new Encriptar();
        try {
            String sql = "update persona set USERPER=?,PSWPER=? where IDPER=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, personalM.getUSERPER());
            ps.setString(2, encriptar.encriptar(personalM.getPSWPER()));
            ps.setInt(3, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Eliminar(Personal personalM) throws Exception {
        this.conectar();
        try {
            String sql = "update PERSONA set ESTAPER='I' where IDPER=? ";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public Personal validarExistenciaPersonal(String DNIPER) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDPER, DNIPER from PERSONA where DNIPER = '" + DNIPER + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Personal personalM = new Personal();
            while (rs.next()) {
                personalM.setIDPER(rs.getInt("IDPER"));
                personalM.setDNIPER(rs.getString("DNIPER"));
                break;
            }
            return personalM;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void habilitarPersonal(Personal personalM) throws Exception {
        this.conectar();
        try {
            String sql = "update PERSONA set ESTAPER='A' where IDPER=? ";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public Personal validarUsuarioExistente(String USERPER) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDPER,USERPER from PERSONA where USERPER='" + USERPER + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Personal personalM = new Personal();
            while (rs.next()) {
                personalM.setIDPER(rs.getInt("IDPER"));
                personalM.setUSERPER(rs.getString("USERPER"));
                break;
            }
            return personalM;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }

    }

    public Personal validarTelefono(String TELPER) throws Exception {
        this.conectar();
        try {
            String sql = "select IDPER,TELPER from PERSONA where TELPER='" + TELPER + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Personal personalM = new Personal();
            while (rs.next()) {
                personalM.setIDPER(rs.getInt("IDPER"));
                personalM.setTELPER(rs.getString("TELPER"));
                break;
            }
            return personalM;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Personal> listaTipo(String filtro) throws Exception {
        List<Personal> listper;
        Personal persM;
        this.conectar();
        try {
            listper = new ArrayList();
            String sql = "select*from persona where ESTAPER='" + filtro + "'";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persM = new Personal();
                persM.setIDPER(rs.getInt("IDPER"));
                persM.setNOMPER(rs.getString("NOMPER"));
                persM.setAPEPER(rs.getString("APEPER"));
                persM.setDNIPER(rs.getString("DNIPER"));
                persM.setTELPER(rs.getString("TELPER"));
                persM.setTIPPER(rs.getString("TIPPER"));
                persM.setESTAPER(rs.getString("ESTAPER"));
                persM.setUSERPER(rs.getString("USERPER"));
                persM.setPSWPER(rs.getString("PSWPER"));
                listper.add(persM);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listper;
    }

    @Override
    public List<Personal> lista() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
