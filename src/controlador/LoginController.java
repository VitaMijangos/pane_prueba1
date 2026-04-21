package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.LoginView;
import vista.MenuPrincipalView;

public class LoginController implements ActionListener {

    private LoginView vista;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginView vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();

        this.vista.getBtnIngresar().addActionListener(this);
        this.vista.getBtnSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnIngresar()) {
            iniciarSesion();
        }

        if (e.getSource() == vista.getBtnSalir()) {
            System.exit(0);
        }
    }

    private void iniciarSesion() {
        String nombreUsuario = vista.getTxtUsuario().getText().trim();
        String contrasena = new String(vista.getTxtContrasena().getPassword()).trim();

        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete usuario y contraseña.");
            return;
        }

        Usuario usuario = usuarioDAO.validarUsuario(nombreUsuario, contrasena);

        if (usuario != null) {
            JOptionPane.showMessageDialog(vista, "Bienvenido, " + usuario.getNombreUsuario());

            MenuPrincipalView menu = new MenuPrincipalView();
            menu.setDatosUsuario(usuario.getNombreUsuario(), usuario.getRol());
            menu.aplicarRol(usuario.getRol());

            MenuPrincipalController menuController = new MenuPrincipalController(menu);
            menuController.iniciarVista();

            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.");
        }
    }
}