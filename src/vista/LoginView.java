package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JButton btnSalir;

    public LoginView() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Login - Robot Mini-Sumo");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(35, 35, 40));
        setContentPane(contentPane);
    }

    private void inicializarComponentes() {
        Color verdeNeon = new Color(57, 255, 20);

        JLabel lblTitulo = new JLabel("Inicio de sesión");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(verdeNeon);
        lblTitulo.setBounds(120, 30, 250, 35);
        contentPane.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(70, 100, 100, 25);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
        txtUsuario.setBounds(180, 100, 220, 32);
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));
        contentPane.add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 16));
        lblContrasena.setForeground(Color.WHITE);
        lblContrasena.setBounds(70, 160, 110, 25);
        contentPane.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 15));
        txtContrasena.setBounds(180, 160, 220, 32);
        txtContrasena.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));
        contentPane.add(txtContrasena);

        btnIngresar = crearBoton("Ingresar");
        btnIngresar.setBounds(110, 235, 120, 38);
        contentPane.add(btnIngresar);

        btnSalir = crearBoton("Salir");
        btnSalir.setBounds(260, 235, 120, 38);
        contentPane.add(btnSalir);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);

        Color verdeNeon = new Color(57, 255, 20);
        Color fondoBoton = new Color(35, 35, 35);

        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(fondoBoton);
        boton.setForeground(verdeNeon);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(verdeNeon, 2));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(verdeNeon);
                boton.setForeground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(fondoBoton);
                boton.setForeground(verdeNeon);
            }
        });

        return boton;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}