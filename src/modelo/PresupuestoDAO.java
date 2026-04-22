package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PresupuestoDAO {

    private static final String OBTENER_PRESUPUESTO =
            "SELECT * FROM presupuesto LIMIT 1";

    public Presupuesto obtenerPresupuesto() {
        Presupuesto presupuesto = null;

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_PRESUPUESTO);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                presupuesto = new Presupuesto();
                presupuesto.setIdPresupuesto(rs.getInt("id_presupuesto"));
                presupuesto.setNombreProyecto(rs.getString("nombre_proyecto"));
                presupuesto.setSaldoInicial(rs.getDouble("saldo_inicial"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return presupuesto;
    }
    
    public boolean actualizarSaldoInicial(int idPresupuesto, double nuevoSaldo) {
        String sql = "UPDATE presupuesto SET saldo_inicial = ? WHERE id_presupuesto = ?";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql)
        ) {
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, idPresupuesto);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}