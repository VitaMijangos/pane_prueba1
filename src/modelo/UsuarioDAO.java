package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    private static final String VALIDAR_USUARIO =
            "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasena = ?";

    private static final String INSERTAR_USUARIO =
            "INSERT INTO usuario (nombre_usuario, contrasena, rol) VALUES (?, ?, ?)";

    private static final String OBTENER_USUARIOS =
            "SELECT * FROM usuario ORDER BY id_usuario";

    private static final String ELIMINAR_USUARIO =
            "DELETE FROM usuario WHERE id_usuario = ?";

    public Usuario validarUsuario(String nombreUsuario, String contrasena) {
        Usuario usuario = null;

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(VALIDAR_USUARIO)
        ) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public boolean guardarUsuario(Usuario usuario) throws Exception {
        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(INSERTAR_USUARIO)
        ) {
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getRol());

            return ps.executeUpdate() > 0;
        }
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(OBTENER_USUARIOS);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                lista.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean eliminarUsuario(int idUsuario) throws Exception {
        try (
            Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(ELIMINAR_USUARIO)
        ) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        }
    }
}