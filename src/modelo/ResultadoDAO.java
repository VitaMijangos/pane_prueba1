package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ResultadoDAO {

    private static final String OBTENER_RESULTADOS =
            "SELECT id_resultado, descripcion_resultado FROM resultado ORDER BY id_resultado";

    public ArrayList<Resultado> obtenerResultados() {
        ArrayList<Resultado> lista = new ArrayList<>();

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_RESULTADOS);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Resultado resultado = new Resultado();
                resultado.setIdResultado(rs.getInt("id_resultado"));
                resultado.setDescripcionResultado(rs.getString("descripcion_resultado"));
                lista.add(resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}