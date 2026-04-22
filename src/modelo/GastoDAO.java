package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GastoDAO {

    private static final String INSERTAR_GASTO =
            "INSERT INTO gasto (concepto, categoria, cantidad, costo_unitario, total, fecha, observaciones, id_presupuesto) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String OBTENER_GASTOS =
            "SELECT * FROM gasto ORDER BY id_gasto";

    private static final String ELIMINAR_GASTO =
            "DELETE FROM gasto WHERE id_gasto = ?";

    private static final String TOTAL_GASTADO =
            "SELECT SUM(total) AS total_gastado FROM gasto";

    public boolean guardarGasto(Gasto gasto) throws Exception {
        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(INSERTAR_GASTO)
        ) {
            ps.setString(1, gasto.getConcepto());
            ps.setString(2, gasto.getCategoria());
            ps.setInt(3, gasto.getCantidad());
            ps.setDouble(4, gasto.getCostoUnitario());
            ps.setDouble(5, gasto.getTotal());
            ps.setString(6, gasto.getFecha());
            ps.setString(7, gasto.getObservaciones());
            ps.setInt(8, gasto.getIdPresupuesto());

            return ps.executeUpdate() > 0;
        }
    }

    public ArrayList<Gasto> obtenerGastos() {
        ArrayList<Gasto> lista = new ArrayList<>();

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_GASTOS);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Gasto gasto = new Gasto();
                gasto.setIdGasto(rs.getInt("id_gasto"));
                gasto.setConcepto(rs.getString("concepto"));
                gasto.setCategoria(rs.getString("categoria"));
                gasto.setCantidad(rs.getInt("cantidad"));
                gasto.setCostoUnitario(rs.getDouble("costo_unitario"));
                gasto.setTotal(rs.getDouble("total"));
                gasto.setFecha(rs.getString("fecha"));
                gasto.setObservaciones(rs.getString("observaciones"));
                gasto.setIdPresupuesto(rs.getInt("id_presupuesto"));

                lista.add(gasto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean eliminarGasto(int idGasto) throws Exception {
        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(ELIMINAR_GASTO)
        ) {
            ps.setInt(1, idGasto);
            return ps.executeUpdate() > 0;
        }
    }

    public double obtenerTotalGastado() {
        double totalGastado = 0;

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(TOTAL_GASTADO);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                totalGastado = rs.getDouble("total_gastado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalGastado;
    }
    
    public int contarGastos() {
        String sql = "SELECT COUNT(*) AS total_gastos FROM gasto";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt("total_gastos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String obtenerCategoriaMayorGasto() {
        String sql = "SELECT categoria, SUM(total) AS total_categoria "
                   + "FROM gasto "
                   + "GROUP BY categoria "
                   + "ORDER BY total_categoria DESC "
                   + "LIMIT 1";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getString("categoria") + " ($" + rs.getDouble("total_categoria") + ")";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Sin datos";
    }

    public String obtenerComponenteMasCaro() {
        String sql = "SELECT concepto, total "
                   + "FROM gasto "
                   + "ORDER BY total DESC "
                   + "LIMIT 1";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getString("concepto") + " ($" + rs.getDouble("total") + ")";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Sin datos";
    }
}