package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovimientoDAO {

    private static final String OBTENER_MOVIMIENTOS =
            "SELECT id_movimiento, nombre_movimiento FROM movimiento ORDER BY id_movimiento";

    public ArrayList<Movimiento> obtenerMovimientos() {
        ArrayList<Movimiento> lista = new ArrayList<>();

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_MOVIMIENTOS);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
                movimiento.setNombreMovimiento(rs.getString("nombre_movimiento"));
                lista.add(movimiento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}