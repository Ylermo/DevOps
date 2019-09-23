package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleInformacion;

public class DetalleInfoImpl extends Conexion{

    
    public void Modificar(DetalleInformacion detInfo) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE DETALLE_INFORMACION set IDINFO=?,IDASIGCUL=?, CREDET=?, PRODET=?, SEMDET=?,COSDET=?, PERDET=?,AFEDET=?, ROTDET=?, VERDMES=?, PRODDET=?,PRECHA=?, FECCOS=?, VALDET=? where IDDETINFO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, detInfo.getIDINFO());
            ps.setInt(2, detInfo.getIDCUL());
            ps.setDouble(3, detInfo.getCREDET());
            ps.setDouble(4, detInfo.getPRODET());
            ps.setDouble(5, detInfo.getSEMDET());
            ps.setDouble(6, detInfo.getCOSDET());
            ps.setDouble(7, detInfo.getPERDET());
            ps.setDouble(8, detInfo.getAFEDET());
            ps.setDouble(9, detInfo.getROTDET());
            ps.setDouble(10, detInfo.getVERDMES());
            ps.setDouble(11, detInfo.getPRODDET());
            ps.setDouble(12, detInfo.getPRECHA());
            try {
                ps.setDate(13, new java.sql.Date(detInfo.getFECCOS().getTime()));
            } catch (Exception e) {
                ps.setDate(13, null);
            }
            ps.setString(14, detInfo.getVALDET());
            ps.setInt(15, detInfo.getIDDETINFO());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Invalidar(DetalleInformacion detInfo) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE DETALLE_INFORMACION set VALDET='R'where IDDETINFO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, detInfo.getIDDETINFO());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Validar(DetalleInformacion detInfo) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE DETALLE_INFORMACION set VALDET='V' where IDDETINFO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, detInfo.getIDDETINFO());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<DetalleInformacion> listarDetalles(String id) throws Exception {
        List<DetalleInformacion> detInformacionLista;
        DetalleInformacion detInfo;
        this.conectar();
        try {
            String sql = "select C.NOMCUL, C.TIPCUL , A.* from DETALLE_INFORMACION A Inner join ASIGNACION_CULTIVO B ON A.IDASIGCUL = B.IDASIGCUL Inner join CULTIVO C ON B.IDCUL = C.IDCUL where A.IDINFO=" + id;
            detInformacionLista = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                detInfo = new DetalleInformacion();
                detInfo.setIDDETINFO(rs.getInt("IDDETINFO"));
                detInfo.setIDINFO(rs.getInt("IDINFO"));
                detInfo.setIDCUL(rs.getInt("IDASIGCUL"));
                detInfo.setNOMCUL(rs.getString("NOMCUL"));
                detInfo.setTIPCUL(rs.getString("TIPCUL"));
                detInfo.setVERMESANT(rs.getDouble("VERMESANT"));
                detInfo.setCREDET(rs.getDouble("CREDET"));
                detInfo.setPRODET(rs.getDouble("PRODET"));
                detInfo.setSEMDET(rs.getDouble("SEMDET"));
                detInfo.setCOSDET(rs.getDouble("COSDET"));
                detInfo.setPERDET(rs.getDouble("PERDET"));
                detInfo.setAFEDET(rs.getDouble("AFEDET"));
                detInfo.setROTDET(rs.getDouble("ROTDET"));
                detInfo.setVERDMES(rs.getDouble("VERDMES"));
                detInfo.setPRODDET(rs.getDouble("PRODDET"));
                detInfo.setPRECHA(rs.getDouble("PRECHA"));
                detInfo.setFECCOS(rs.getDate("FECCOS"));
                detInfo.setVALDET(rs.getString("VALDET"));
                detInformacionLista.add(detInfo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return detInformacionLista;
    }

    
    public void Registrar(int IDINFO, int IDSECT) throws Exception {
        this.conectar();
        try {
            String query = "{call registroMes(?,?)}"; 
            CallableStatement statement = this.getCn().prepareCall(query);  
            statement.setInt(1, IDINFO);  
            statement.setInt(2, IDSECT);  
            statement.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
