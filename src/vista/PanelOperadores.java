package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelOperadores extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombreOperador;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnCargar;
    private JButton btnVolver;
    private JButton btnEliminar;

    private JTable tablaOperadores;
    private DefaultTableModel modeloTabla;

    private MenuPrincipalView menuPrincipal;

    public PanelOperadores(MenuPrincipalView menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setLayout(null);
        setBackground(new Color(45, 45, 50));

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("Registro de operadores");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(57, 255, 20));
        lblTitulo.setBounds(220, 20, 400, 30);
        add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre del operador:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(80, 90, 180, 25);
        add(lblNombre);

        txtNombreOperador = new JTextField();
        txtNombreOperador.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNombreOperador.setBounds(270, 90, 250, 30);
        add(txtNombreOperador);

        btnGuardar = crearBoton("Guardar");
        btnGuardar.setBounds(560, 90, 120, 35);
        add(btnGuardar);
             
        btnEliminar = crearBoton("Eliminar");
        btnEliminar.setBounds(630, 135, 120, 35);
        add(btnEliminar);

        btnLimpiar = crearBoton("Limpiar");
        btnLimpiar.setBounds(700, 90, 120, 35);
        add(btnLimpiar);

        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Nombre del operador"});

        tablaOperadores = new JTable(modeloTabla);
        tablaOperadores.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaOperadores.setRowHeight(24);
        tablaOperadores.getTableHeader().setBackground(new Color(57, 255, 20));
        tablaOperadores.getTableHeader().setForeground(Color.BLACK);
        tablaOperadores.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tablaOperadores);
        scroll.setBounds(80, 220, 740, 250);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(57, 255, 20), 2));
        add(scroll);

        btnCargar = crearBoton("Cargar operadores");
        btnCargar.setBounds(230, 490, 180, 35);
        add(btnCargar);

        btnVolver = crearBoton("Volver al menú");
        btnVolver.setBounds(470, 490, 180, 35);
        add(btnVolver);
    }

    private void configurarEventos() {
        btnLimpiar.addActionListener(e -> txtNombreOperador.setText(""));
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

    public JTextField getTxtNombreOperador() {
        return txtNombreOperador;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
    
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
    
    public JTable getTablaOperadores() {
        return tablaOperadores;
    }
}