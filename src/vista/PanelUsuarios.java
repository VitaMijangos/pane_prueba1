package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelUsuarios extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombreUsuario;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbRol;

    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnCargar;
    private JButton btnVolver;

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    private MenuPrincipalView menuPrincipal;

    public PanelUsuarios(MenuPrincipalView menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setLayout(null);
        setBackground(new Color(45, 45, 50));

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("Gestión de usuarios");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(57, 255, 20));
        lblTitulo.setBounds(250, 20, 350, 30);
        add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(80, 90, 100, 25);
        add(lblUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNombreUsuario.setBounds(170, 90, 180, 30);
        add(txtNombreUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 16));
        lblContrasena.setForeground(Color.WHITE);
        lblContrasena.setBounds(380, 90, 110, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 15));
        txtContrasena.setBounds(490, 90, 180, 30);
        add(txtContrasena);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setFont(new Font("Arial", Font.BOLD, 16));
        lblRol.setForeground(Color.WHITE);
        lblRol.setBounds(700, 90, 50, 25);
        add(lblRol);

        cmbRol = new JComboBox<>(new String[]{"Seleccionar", "Administrador", "Operador"});
        cmbRol.setFont(new Font("Arial", Font.PLAIN, 15));
        cmbRol.setBounds(750, 90, 170, 30);
        add(cmbRol);

        btnGuardar = crearBoton("Guardar");
        btnGuardar.setBounds(180, 145, 120, 35);
        add(btnGuardar);

        btnLimpiar = crearBoton("Limpiar");
        btnLimpiar.setBounds(330, 145, 120, 35);
        add(btnLimpiar);

        btnEliminar = crearBoton("Eliminar");
        btnEliminar.setBounds(480, 145, 120, 35);
        add(btnEliminar);

        btnCargar = crearBoton("Cargar");
        btnCargar.setBounds(630, 145, 120, 35);
        add(btnCargar);

        btnVolver = crearBoton("Volver al menú");
        btnVolver.setBounds(780, 145, 160, 35);
        add(btnVolver);

        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Usuario", "Contraseña", "Rol"});

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaUsuarios.setRowHeight(24);
        tablaUsuarios.getTableHeader().setBackground(new Color(57, 255, 20));
        tablaUsuarios.getTableHeader().setForeground(Color.BLACK);
        tablaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        scroll.setBounds(80, 220, 860, 260);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(57, 255, 20), 2));
        add(scroll);
    }

    private void configurarEventos() {
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> menuPrincipal.mostrarBienvenida());
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

    public void limpiarCampos() {
        txtNombreUsuario.setText("");
        txtContrasena.setText("");
        cmbRol.setSelectedIndex(0);
    }

    public JTextField getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public JComboBox<String> getCmbRol() {
        return cmbRol;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}