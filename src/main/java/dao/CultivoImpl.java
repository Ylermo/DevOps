package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cultivo;

public class CultivoImpl extends Conexion implements CRUD<Cultivo> {

    @Override
    public void Registrar(Cultivo cultivo) throws Exception {
        this.conectar();
        try {
            String sql = "insert into CULTIVO (NOMCUL,TIPCUL, ESTACUL) Values (?,?,?)";
            PreparedStatement sp = getCn().prepareStatement(sql);
            sp.setString(1, cultivo.getNOMCUL().trim());
            sp.setString(2, cultivo.getTIPCUL());
            sp.setString(3, "A");
            sp.executeUpdate();
            sp.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Modificar(Cultivo cultivo) throws Exception {
        this.conectar();
        try {
            String sql = "update CULTIVO set NOMCUL = ?, TIPCUL = ? where IDCUL = ? ";
            PreparedStatement sp = getCn().prepareStatement(sql);
            sp.setString(1, cultivo.getNOMCUL());
            sp.setString(2, cultivo.getTIPCUL());
            sp.setInt(3, cultivo.getIDCUL());
            sp.executeUpdate();
            sp.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Eliminar(Cultivo cultivo) throws Exception {
        this.conectar();
        try {
            String sql = "update CULTIVO set ESTACUL='I' where IDCUL = ? ";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, cultivo.getIDCUL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void habilitarCultivo(Cultivo cultivo) throws Exception {
        this.conectar();
        try {
            String sql = "update CULTIVO set ESTACUL='A' where IDCUL = ? ";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, cultivo.getIDCUL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Cultivo> listar(String estado) throws Exception {
        List<Cultivo> listCult;
        Cultivo cultivoM;
        this.conectar();
        try {
            String sql = "select * from CULTIVO where ESTACUL='" + estado + "' order by IDCUL";
            listCult = new ArrayList();
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cultivoM = new Cultivo();
                cultivoM.setIDCUL(rs.getInt("IDCUL"));
                cultivoM.setNOMCUL(rs.getString("NOMCUL"));
                cultivoM.setTIPCUL(rs.getString("TIPCUL"));
                cultivoM.setESTACUL(rs.getString("ESTACUL"));
                listCult.add(cultivoM);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listCult;
    }

    public Cultivo validarExistenciaCultivo(String cultivo) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDCUL, NOMCUL from CULTIVO where NOMCUL = '" + cultivo + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Cultivo cultivoM = new Cultivo();
            while (rs.next()) {
                cultivoM.setIDCUL(rs.getInt("IDCUL"));
                cultivoM.setNOMCUL(rs.getString("NOMCUL"));
                break;
            }
            return cultivoM;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public Cultivo validarExistenciaCultivoModificar(String cultivo, Cultivo cult) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDCUL, NOMCUL from CULTIVO where NOMCUL = '" + cultivo + "' and IDCUL = " + cult.getIDCUL();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Cultivo cultivoM = new Cultivo();
            while (rs.next()) {
                cultivoM.setIDCUL(rs.getInt("IDCUL"));
                cultivoM.setNOMCUL(rs.getString("NOMCUL"));
                break;
            }
            return cultivoM;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Cultivo> lista() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
