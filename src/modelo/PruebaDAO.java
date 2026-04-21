package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PruebaDAO {

    private static final String INSERTAR_PRUEBA =
            "INSERT INTO prueba (fecha, velocidad_pwm, bateria, tiempo_respuesta, observaciones, id_operador, id_modo, id_movimiento, id_resultado) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String OBTENER_PRUEBAS =
            "SELECT p.id_prueba, p.fecha, p.velocidad_pwm, p.bateria, p.tiempo_respuesta, p.observaciones, "
            + "p.id_operador, p.id_modo, p.id_movimiento, p.id_resultado, "
            + "o.nombre_operador, m.nombre_modo, mv.nombre_movimiento, r.descripcion_resultado "
            + "FROM prueba p "
            + "INNER JOIN operador o ON p.id_operador = o.id_operador "
            + "INNER JOIN modo_prueba m ON p.id_modo = m.id_modo "
            + "INNER JOIN movimiento mv ON p.id_movimiento = mv.id_movimiento "
            + "INNER JOIN resultado r ON p.id_resultado = r.id_resultado";

    public boolean guardarPrueba(Prueba prueba) throws SQLException {
        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(INSERTAR_PRUEBA)
        ) {
            ps.setString(1, prueba.getFecha());
            ps.setInt(2, prueba.getVelocidadPWM());
            ps.setString(3, prueba.getBateria());
            ps.setString(4, prueba.getTiempoRespuesta());
            ps.setString(5, prueba.getObservaciones());
            ps.setInt(6, prueba.getIdOperador());
            ps.setInt(7, prueba.getIdModo());
            ps.setInt(8, prueba.getIdMovimiento());
            ps.setInt(9, prueba.getIdResultado());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }

    public ArrayList<Prueba> obtenerPruebas() {
        ArrayList<Prueba> listaPruebas = new ArrayList<>();

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_PRUEBAS);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Prueba prueba = new Prueba();

                prueba.setIdPrueba(rs.getInt("id_prueba"));
                prueba.setFecha(rs.getString("fecha"));
                prueba.setVelocidadPWM(rs.getInt("velocidad_pwm"));
                prueba.setBateria(rs.getString("bateria"));
                prueba.setTiempoRespuesta(rs.getString("tiempo_respuesta"));
                prueba.setObservaciones(rs.getString("observaciones"));

                prueba.setIdOperador(rs.getInt("id_operador"));
                prueba.setIdModo(rs.getInt("id_modo"));
                prueba.setIdMovimiento(rs.getInt("id_movimiento"));
                prueba.setIdResultado(rs.getInt("id_resultado"));

                prueba.setNombreOperador(rs.getString("nombre_operador"));
                prueba.setNombreModo(rs.getString("nombre_modo"));
                prueba.setNombreMovimiento(rs.getString("nombre_movimiento"));
                prueba.setNombreResultado(rs.getString("descripcion_resultado"));

                listaPruebas.add(prueba);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPruebas;
    }

    public int contarPruebas() {
        String sql = "SELECT COUNT(*) FROM prueba";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int contarPruebasExitosas() {
        String sql = "SELECT COUNT(*) FROM prueba WHERE id_resultado = 1";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int contarPruebasFallidas() {
        String sql = "SELECT COUNT(*) FROM prueba WHERE id_resultado = 2";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double promedioVelocidadPWM() {
        String sql = "SELECT AVG(velocidad_pwm) FROM prueba";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}