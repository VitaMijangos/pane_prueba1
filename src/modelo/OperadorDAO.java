package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperadorDAO {

    private static final String INSERTAR_OPERADOR =
            "INSERT INTO operador (nombre_operador) VALUES (?)";

    private static final String OBTENER_OPERADORES =
            "SELECT id_operador, nombre_operador FROM operador ORDER BY nombre_operador";

    public boolean guardarOperador(Operador operador) throws SQLException {
        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(INSERTAR_OPERADOR)
        ) {
            ps.setString(1, operador.getNombreOperador());
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
    
    public boolean eliminarOperador(int idOperador) throws SQLException {
        String sql = "DELETE FROM operador WHERE id_operador = ?";

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sql)
        ) {
            ps.setInt(1, idOperador);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    public ArrayList<Operador> obtenerOperadores() {
        ArrayList<Operador> lista = new ArrayList<>();

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_OPERADORES);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Operador operador = new Operador();
                operador.setIdOperador(rs.getInt("id_operador"));
                operador.setNombreOperador(rs.getString("nombre_operador"));
                lista.add(operador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}