package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ValleImpl extends Conexion{
  
    public List<String> listaAutoCompleteValle(String ubigeo) throws SQLException{
        this.conectar();
        List<String> lista;
        try {
            lista = new ArrayList();
            String sql = "Select \n" +
                      "    CONCAT(NOMDEP,CONCAT(CONCAT(' ',NOMPROV),CONCAT(' ',NOMDIST))) UBIGEO \n"
                    + "from UBIGEO WHERE NOMDIST like ?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, "%"+ubigeo+"%"); 
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lista.add(rs.getString("UBIGEO"));
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            this.Cerrar();
        }
        return lista;
    }
       
    public String obtenerCodigoValle(String Ubigeo) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODUBI FROM UBIGEO WHERE CONCAT(NOMDEP,',',NOMPROV,',',NOMDIST) LIKE ? and NOMPROV = 'Cañete';";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Ubigeo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }finally{
            this.Cerrar();
        }
    }
}

