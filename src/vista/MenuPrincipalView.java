package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class MenuPrincipalView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelEncabezado;
    private JPanel panelLateral;
    private JPanel panelBotones;
    private JPanel panelSalir;
    private JPanel panelContenido;

    private JLabel lblTitulo;
    private JLabel lblBienvenida;
    
    private JLabel lblUsuarioActivo;
    private JLabel lblRolActivo;

    private JButton btnRegistro;
    private JButton btnHistorial;
    private JButton btnOperadores;
    private JButton btnEstadisticas;
    private JButton btnSalir;
    private JButton btnUsuarios;

    public MenuPrincipalView() {
        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
    }

    private void configurarVentana() {
        setTitle("Menú Principal - Robot Mini-Sumo");
        setSize(1280, 880);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        Color verdeNeon = new Color(57, 255, 20);
        Color fondoOscuro = new Color(20, 20, 20);
        Color panelOscuro = new Color(45, 45, 50);

        // Encabezado
        panelEncabezado = new JPanel(new BorderLayout());
        panelEncabezado.setBackground(verdeNeon);
        panelEncabezado.setPreferredSize(new Dimension(1200, 110));
        panelEncabezado.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblTitulo = new JLabel("-Sistema de monitoreo y registro del robot Mini-Sumo-");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        // Panel Bienvenida usuario
        lblUsuarioActivo = new JLabel("Bienvenido: ---");
        lblUsuarioActivo.setForeground(Color.WHITE);
        lblUsuarioActivo.setFont(new Font("Arial", Font.BOLD, 15));
        lblUsuarioActivo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblRolActivo = new JLabel("Rol: ---");
        lblRolActivo.setForeground(Color.WHITE);
        lblRolActivo.setFont(new Font("Arial", Font.BOLD, 13));
        lblRolActivo.setHorizontalAlignment(SwingConstants.RIGHT);
        
        

        // Panel lateral principal
        panelLateral = new JPanel(new BorderLayout());
        panelLateral.setBackground(fondoOscuro);
        panelLateral.setPreferredSize(new Dimension(240, 650));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(22, 20, 22, 20));

        // Panel de botones superiores
        panelBotones = new JPanel(new GridLayout(5, 1, 0, 18));
        panelBotones.setBackground(fondoOscuro);

        btnRegistro = crearBoton("REGISTRO DE PRUEBA");
        btnHistorial = crearBoton("HISTORIAL");
        btnOperadores = crearBoton("OPERADORES");
        btnEstadisticas = crearBoton("ESTADÍSTICAS");
        btnUsuarios = crearBoton("USUARIOS");

        // Panel de salir
        panelSalir = new JPanel(new BorderLayout());
        panelSalir.setBackground(fondoOscuro);

        btnSalir = crearBoton("CERRAR SESIÓN");
        btnSalir.setPreferredSize(new Dimension(180, 78));

        // Panel contenido
        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(panelOscuro);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        lblBienvenida = new JLabel(
                "<html><div style='text-align:center;'>"
                + "<h1 style='color:#39FF14; margin-bottom:10px;'>MENÚ PRINCIPAL</h1>"
                + "<p style='color:white; font-size:18px;'>"
                + "Seleccione una opción del menú lateral para continuar."
                + "</p>"
                + "</div></html>",
                SwingConstants.CENTER
        );
    }

    private void agregarComponentes() {
    	JPanel panelDatosUsuario = new JPanel(new GridLayout(2, 1));
    	panelDatosUsuario.setOpaque(false);
    	panelDatosUsuario.add(lblUsuarioActivo);
    	panelDatosUsuario.add(lblRolActivo);

    	panelEncabezado.add(lblTitulo, BorderLayout.CENTER);
    	panelEncabezado.add(panelDatosUsuario, BorderLayout.EAST);

        panelBotones.add(btnRegistro);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnOperadores);
        panelBotones.add(btnEstadisticas);
        panelBotones.add(btnUsuarios);

        panelSalir.add(btnSalir, BorderLayout.SOUTH);

        panelLateral.add(panelBotones, BorderLayout.NORTH);
        panelLateral.add(panelSalir, BorderLayout.SOUTH);

        panelContenido.add(lblBienvenida, BorderLayout.CENTER);

        getContentPane().add(panelEncabezado, BorderLayout.NORTH);
        getContentPane().add(panelLateral, BorderLayout.WEST);
        getContentPane().add(panelContenido, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        Color verdeNeon = new Color(57, 255, 20);
        Color fondoBoton = new Color(40, 40, 45);

        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(fondoBoton);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 1));
        boton.setPreferredSize(new Dimension(180, 72));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(verdeNeon);
                boton.setForeground(Color.BLACK);
                boton.setBorder(BorderFactory.createLineBorder(verdeNeon, 2));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(fondoBoton);
                boton.setForeground(Color.WHITE);
                boton.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 1));
            }
        });

        return boton;
    }
    
    public void aplicarRol(String rol) {
        if (rol == null) {
            return;
        }

        if (rol.equalsIgnoreCase("Administrador")) {
            btnRegistro.setVisible(true);
            btnHistorial.setVisible(true);
            btnOperadores.setVisible(true);
            btnEstadisticas.setVisible(true);
            btnUsuarios.setVisible(true);
        } else if (rol.equalsIgnoreCase("Operador")) {
            btnRegistro.setVisible(true);
            btnHistorial.setVisible(true);
            btnOperadores.setVisible(false);
            btnEstadisticas.setVisible(false);
            btnUsuarios.setVisible(false);
        } else {
            btnRegistro.setVisible(true);
            btnHistorial.setVisible(false);
            btnOperadores.setVisible(false);
            btnEstadisticas.setVisible(false);
            btnUsuarios.setVisible(false);
        }

        panelLateral.revalidate();
        panelLateral.repaint();
    }
    
    public void setDatosUsuario(String nombreUsuario, String rol) {
        lblUsuarioActivo.setText("Bienvenido: " + nombreUsuario);
        lblRolActivo.setText("Rol: " + rol);
    }

    public JButton getBtnRegistro() {
        return btnRegistro;
    }

    public JButton getBtnHistorial() {
        return btnHistorial;
    }

    public JButton getBtnOperadores() {
        return btnOperadores;
    }

    public JButton getBtnEstadisticas() {
        return btnEstadisticas;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public void mostrarBienvenida() {
        panelContenido.removeAll();
        panelContenido.add(lblBienvenida, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
    
    public JButton getBtnUsuarios() {
        return btnUsuarios;
    }
}