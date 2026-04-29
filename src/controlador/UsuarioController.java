package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.PanelUsuarios;

public class UsuarioController implements ActionListener {

    private PanelUsuarios vista;
    private UsuarioDAO usuarioDAO;

    public UsuarioController(PanelUsuarios vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();

        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnCargar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarUsuario();
        }

        if (e.getSource() == vista.getBtnEliminar()) {
            eliminarUsuario();
        }

        if (e.getSource() == vista.getBtnCargar()) {
            cargarUsuarios();
        }
    }

    private void guardarUsuario() {
        try {
            String nombre = vista.getTxtNombreUsuario().getText().trim();
            String contrasena = new String(vista.getTxtContrasena().getPassword()).trim();
            String rol = vista.getCmbRol().getSelectedItem().toString();

            if (nombre.isEmpty() || contrasena.isEmpty() || vista.getCmbRol().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombre);
            usuario.setContrasena(contrasena);
            usuario.setRol(rol);

            boolean guardado = usuarioDAO.guardarUsuario(usuario);

            if (guardado) {
                JOptionPane.showMessageDialog(vista, "Usuario guardado correctamente.");
                vista.limpiarCampos();
                cargarUsuarios();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo guardar el usuario.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error:\n" + ex.getMessage());
        }
    }

    private void eliminarUsuario() {
        try {
            int filaSeleccionada = vista.getTablaUsuarios().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un usuario de la tabla.");
                return;
            }

            int idUsuario = Integer.parseInt(vista.getModeloTabla().getValueAt(filaSeleccionada, 0).toString());
            String nombreUsuario = vista.getModeloTabla().getValueAt(filaSeleccionada, 1).toString();

            int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Desea eliminar al usuario: " + nombreUsuario + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = usuarioDAO.eliminarUsuario(idUsuario);

                if (eliminado) {
                    JOptionPane.showMessageDialog(vista, "Usuario eliminado correctamente.");
                    cargarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo eliminar el usuario.");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error:\n" + ex.getMessage());
        }
    }

    public void cargarUsuarios() {
        ArrayList<Usuario> lista = usuarioDAO.obtenerUsuarios();
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);

        for (Usuario usuario : lista) {
        	modelo.addRow(new Object[]{
        	        usuario.getIdUsuario(),
        	        usuario.getNombreUsuario(),
        	        "******",
        	        usuario.getRol()
        	});
        }
    }
}