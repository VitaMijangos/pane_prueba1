package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;

public class PanelHistorialPruebas extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable tablaPruebas;
    private DefaultTableModel modeloTabla;

    private JTextArea txtDetalleObservacion;

    private JButton btnCargar;
    private JButton btnVolver;

    private MenuPrincipalView menuPrincipal;

    public PanelHistorialPruebas(MenuPrincipalView menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setLayout(null);
        setBackground(new Color(45, 45, 50));

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("Historial de pruebas del robot Mini-Sumo");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(57, 255, 20));
        lblTitulo.setBounds(170, 20, 550, 30);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Consulta los registros almacenados en la base de datos");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(30, 60, 400, 20);
        add(lblSubtitulo);

        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{
                "ID",
                "Fecha",
                "Velocidad",
                "Batería",
                "Tiempo",
                "Modo de prueba",
                "Operador",
                "Movimiento",
                "Resultado",
                "Observaciones"
        });

        tablaPruebas = new JTable(modeloTabla);
        tablaPruebas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaPruebas.setRowHeight(25);
        tablaPruebas.setBackground(Color.WHITE);
        tablaPruebas.setForeground(Color.BLACK);
        tablaPruebas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tablaPruebas.getTableHeader().setBackground(new Color(57, 255, 20));
        tablaPruebas.getTableHeader().setForeground(Color.BLACK);
        tablaPruebas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        tablaPruebas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tablaPruebas.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaPruebas.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaPruebas.getColumnModel().getColumn(2).setPreferredWidth(80);
        tablaPruebas.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablaPruebas.getColumnModel().getColumn(4).setPreferredWidth(90);
        tablaPruebas.getColumnModel().getColumn(5).setPreferredWidth(130);
        tablaPruebas.getColumnModel().getColumn(6).setPreferredWidth(100);
        tablaPruebas.getColumnModel().getColumn(7).setPreferredWidth(100);
        tablaPruebas.getColumnModel().getColumn(8).setPreferredWidth(90);
        tablaPruebas.getColumnModel().getColumn(9).setPreferredWidth(220);

        JScrollPane scrollTabla = new JScrollPane(tablaPruebas);
        scrollTabla.setBounds(30, 100, 820, 250);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(57, 255, 20), 2));
        add(scrollTabla);

        JLabel lblDetalle = new JLabel("Detalle de observaciones:");
        lblDetalle.setFont(new Font("Arial", Font.BOLD, 16));
        lblDetalle.setForeground(Color.WHITE);
        lblDetalle.setBounds(30, 370, 220, 25);
        add(lblDetalle);

        txtDetalleObservacion = new JTextArea();
        txtDetalleObservacion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDetalleObservacion.setLineWrap(true);
        txtDetalleObservacion.setWrapStyleWord(true);
        txtDetalleObservacion.setEditable(false);
        txtDetalleObservacion.setBackground(Color.WHITE);
        txtDetalleObservacion.setForeground(Color.BLACK);
        txtDetalleObservacion.setText("Seleccione una fila para ver la observación completa.");

        JScrollPane scrollDetalle = new JScrollPane(txtDetalleObservacion);
        scrollDetalle.setBounds(30, 405, 820, 100);
        scrollDetalle.setBorder(BorderFactory.createLineBorder(new Color(57, 255, 20), 2));
        add(scrollDetalle);

        btnCargar = crearBoton("Cargar historial");
        btnCargar.setBounds(240, 530, 180, 35);
        add(btnCargar);

        btnVolver = crearBoton("Volver al menú");
        btnVolver.setBounds(450, 530, 180, 35);
        add(btnVolver);
    }

    private void configurarEventos() {
        btnVolver.addActionListener(e -> {
            menuPrincipal.mostrarBienvenida();
        });
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

    public JTable getTablaPruebas() {
        return tablaPruebas;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JTextArea getTxtDetalleObservacion() {
        return txtDetalleObservacion;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}