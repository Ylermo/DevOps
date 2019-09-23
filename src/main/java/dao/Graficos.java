package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Contar;
import modelo.Cultivo;
import modelo.Informacion;
import modelo.Personal;

public class Graficos extends Conexion {

    public List<Cultivo> listar() throws Exception {
        List<Cultivo> lista = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "SELECT case TIPCUL when 'HT' then 'HORTALIZAS' when 'OT' THEN 'OTROS' when 'PM' THEN 'PERMANENTES' WHEN 'SP' THEN 'SEMI-PERMANENTES' ELSE 'TRANSITORIOS' END TIPCUL,COUNT (TIPCUL) AS TIP  FROM CULTIVO where ESTACUL='A' GROUP BY TIPCUL";
            PreparedStatement ps = getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Cultivo cultivoM = new Cultivo();
                cultivoM.setTIPCUL(rs.getString("TIPCUL"));
                cultivoM.setTIP(rs.getInt("TIP"));
                lista.add(cultivoM);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public List<Personal> listarPer() throws Exception {
        List<Personal> lista = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "select  TIPPER , count(TIPPER) AS TIP from PERSONA where ESTAPER='A' group by tipper";
            PreparedStatement ps = getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Personal personalM = new Personal();
                personalM.setTIPPER(rs.getString("TIPPER"));
                personalM.setTIP(rs.getInt("TIP"));
                lista.add(personalM);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public List<Contar> listarContar() throws Exception {
        List<Contar> lista = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "select * from VW_PERSONA,VW_CULTIVO,VW_SECTORCHAR";
            PreparedStatement ps = getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Contar contar = new Contar();
                contar.setPERSONA(rs.getInt("PERSONA"));
                contar.setCULTIVO(rs.getInt("CULTIVO"));
                contar.setSECTOR(rs.getInt("SECTOR"));
                lista.add(contar);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public List<Informacion> listargraf() throws Exception {
        List<Informacion> listado = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "select i.FECINFO, sum(di.VERDMES) VERDMES  from detalle_informacion di\n"
                    + "inner join informacion i on i.IDINFO = di.IDINFO\n"
                    + "GROUP BY i.FECINFO";
            PreparedStatement ps = getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listado = new ArrayList();
            while (rs.next()) {
                Informacion info = new Informacion();
                info.setFECINFO(rs.getDate("FECINFO"));
                info.setVERDMES(rs.getInt("VERDMES"));
                listado.add(info);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }
}
