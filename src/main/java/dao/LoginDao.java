package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import modelo.Personal;
import servicios.Encriptar;

public class LoginDao extends Conexion{

    public Personal login(String User, String Pass) throws Exception {
        this.conectar();
        ResultSet rs;
        Personal personalM = null;
        try {
            String sql = "select NOMPER,APEPER,TIPPER,ESTAPER,USERPER,PSWPER from PERSONA where USERPER=? and PSWPER=? and ESTAPER='A'";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, User);
            ps.setString(2, Pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                personalM = new Personal();
                personalM.setNOMPER(rs.getString("NOMPER"));
                personalM.setAPEPER(rs.getString("APEPER"));
                personalM.setTIPPER(rs.getString("TIPPER"));
                personalM.setESTAPER(rs.getString("ESTAPER"));
                personalM.setUSERPER(User);
                personalM.setPSWPER(Pass);
            }
            return personalM;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }

    }

}
