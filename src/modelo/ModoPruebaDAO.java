package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModoPruebaDAO {

    private static final String OBTENER_MODOS =
            "SELECT id_modo, nombre_modo FROM modo_prueba ORDER BY id_modo";

    public ArrayList<ModoPrueba> obtenerModos() {
        ArrayList<ModoPrueba> lista = new ArrayList<>();

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_MODOS);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                ModoPrueba modo = new ModoPrueba();
                modo.setIdModo(rs.getInt("id_modo"));
                modo.setNombreModo(rs.getString("nombre_modo"));
                lista.add(modo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}