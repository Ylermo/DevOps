package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Sector;

public class SectorImpl extends Conexion implements CRUD<Sector> {

    @Override
    public void Registrar(Sector sectorM) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO SECTOR (NOMSECT,AREASECT,IDVALL,CODUBI, ESTSECT) values(?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, sectorM.getNOMSECT());
            ps.setDouble(2, sectorM.getAREASECT());
            ps.setInt(3, 1);
            ps.setString(4, sectorM.getCODUBI());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }

    }

    @Override
    public void Modificar(Sector sectorM) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE SECTOR set NOMSECT=?, AREASECT=?, IDVALL=? ,CODUBI=? where IDSECT=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, sectorM.getNOMSECT());
            ps.setDouble(2, sectorM.getAREASECT());
            ps.setInt(3, 1);
            ps.setString(4, sectorM.getCODUBI());
            ps.setInt(5, sectorM.getIDSECT());
            ps.executeUpdate();
            ps.close();
            System.out.println("Entro dao");
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Eliminar(Sector sectorM) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE SECTOR set ESTSECT = 'I' where IDSECT=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, sectorM.getIDSECT());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }

    }

    public void Habilitar(Sector sectorM) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE SECTOR set ESTSECT = 'A' where IDSECT=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, sectorM.getIDSECT());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }

    }

    public List<Sector> listado(String estado) throws Exception {
        this.conectar();
        List<Sector> listaSector;
        Sector sectorM;
        try {
            String sql = "Select * from vw_SECTOR where ESTSECT = '" + estado + "'";
            listaSector = new ArrayList();
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sectorM = new Sector();
                sectorM.setIDSECT(rs.getInt("IDSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                sectorM.setAREASECT(rs.getDouble("AREASECT"));
                sectorM.setCODUBI(rs.getString("NOMDIST"));
                sectorM.setESTSECT(rs.getString("ESTSECT"));
                listaSector.add(sectorM);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaSector;
    }

    @Override
    public List<Sector> lista() throws Exception {
        this.conectar();
        List<Sector> listaSector;
        Sector sectorM;
        try {
            String sql = "Select * from vw_SECTOR where ESTSECT = 'A'";
            listaSector = new ArrayList();
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sectorM = new Sector();
                sectorM.setIDSECT(rs.getInt("IDSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                sectorM.setAREASECT(rs.getDouble("AREASECT"));
                sectorM.setCODUBI(rs.getString("NOMDIST"));
                sectorM.setESTSECT(rs.getString("ESTSECT"));
                listaSector.add(sectorM);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaSector;
    }

    public Sector validarExistenciaSector(String sector) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDSECT, NOMSECT from SECTOR where NOMSECT = '" + sector + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Sector sectorM = new Sector();
            while (rs.next()) {
                sectorM.setIDSECT(rs.getInt("IDSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                break;
            }
            return sectorM;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Sector> listaAutoComplete(String sectorComplete) throws Exception {
        List<Sector> listaSector;
        Sector sectorM;
        this.conectar();
        try {
            String sql = "Select * from SECTOR where NOMSECT like '%" + sectorComplete + "%'";
            listaSector = new ArrayList();
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sectorM = new Sector();
                sectorM.setIDSECT(rs.getInt("NUMSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                sectorM.setAREASECT(rs.getDouble("AREASECT"));
                sectorM.setIDVALL(rs.getInt("IDVALL"));
                sectorM.setCODUBI(rs.getString("CODUBI"));
                listaSector.add(sectorM);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaSector;
    }

}
