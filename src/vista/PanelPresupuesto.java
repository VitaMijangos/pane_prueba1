package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelPresupuesto extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel lblProyectoValor;
    private JLabel lblSaldoInicialValor;
    private JLabel lblTotalGastadoValor;
    private JLabel lblSaldoRestanteValor;

    private JTextField txtSaldoInicialEditable;
    private JButton btnActualizarPresupuesto;

    private JTextField txtConcepto;
    private JComboBox<String> cmbCategoria;
    private JTextField txtCantidad;
    private JTextField txtCostoUnitario;
    private JTextField txtFecha;
    private JTextArea txtObservaciones;

    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnCargar;
    private JButton btnVolver;

    private JTable tablaGastos;
    private DefaultTableModel modeloTabla;

    private JTextArea txtDetalleObservacion;

    private MenuPrincipalView menuPrincipal;

    public PanelPresupuesto(MenuPrincipalView menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setLayout(null);
        setBackground(new Color(45, 45, 50));

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        Color verdeNeon = new Color(57, 255, 20);

        JLabel lblTitulo = new JLabel("Gestión de presupuesto");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(verdeNeon);
        lblTitulo.setBounds(320, 15, 420, 30);
        add(lblTitulo);

        // RESUMEN
        JLabel lblProyecto = crearLabel("Proyecto:");
        lblProyecto.setBounds(70, 70, 100, 25);
        add(lblProyecto);

        lblProyectoValor = crearLabelValor("---");
        lblProyectoValor.setBounds(170, 70, 260, 25);
        add(lblProyectoValor);

        JLabel lblSaldoRestante = crearLabel("Saldo restante:");
        lblSaldoRestante.setBounds(70, 105, 140, 25);
        add(lblSaldoRestante);

        lblSaldoRestanteValor = crearLabelValor("$0.00");
        lblSaldoRestanteValor.setBounds(210, 105, 150, 25);
        add(lblSaldoRestanteValor);
////////////////////////
        JLabel lblSaldoActual = crearLabel("Saldo actual:");
        lblSaldoActual.setBounds(330, 70, 120, 25);
        add(lblSaldoActual);

        lblSaldoInicialValor = crearLabelValor("$0.00");
        lblSaldoInicialValor.setBounds(450, 70, 130, 25);
        add(lblSaldoInicialValor);

        JLabel lblNuevoSaldo = crearLabel("Nuevo saldo:");
        lblNuevoSaldo.setBounds(550, 70, 110, 25);
        add(lblNuevoSaldo);

        txtSaldoInicialEditable = crearTextField();
        txtSaldoInicialEditable.setBounds(650, 68, 110, 28);
        add(txtSaldoInicialEditable);

        btnActualizarPresupuesto = crearBoton("Actualizar");
        btnActualizarPresupuesto.setBounds(800, 66, 130, 32);
        add(btnActualizarPresupuesto);

        JLabel lblTotalGastado = crearLabel("Total gastado:");
        lblTotalGastado.setBounds(330, 105, 130, 25);
        add(lblTotalGastado);

        lblTotalGastadoValor = crearLabelValor("$0.00");
        lblTotalGastadoValor.setBounds(450, 105, 130, 25);
        add(lblTotalGastadoValor);

        // FORMULARIO
        JLabel lblConcepto = crearLabel("Concepto:");
        lblConcepto.setBounds(70, 170, 100, 25);
        add(lblConcepto);

        txtConcepto = crearTextField();
        txtConcepto.setBounds(180, 170, 250, 30);
        add(txtConcepto);

        JLabel lblCategoria = crearLabel("Categoría:");
        lblCategoria.setBounds(480, 170, 100, 25);
        add(lblCategoria);

        cmbCategoria = new JComboBox<>(new String[]{
                "Seleccionar",
                "Estructura y mecánica",
                "Sistema de movimiento",
                "Electrónica de control",
                "Alimentación y cableado",
                "Gastos operativos",
                "Otros"
        });
        cmbCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbCategoria.setBounds(590, 170, 260, 30);
        add(cmbCategoria);

        JLabel lblCantidad = crearLabel("Cantidad:");
        lblCantidad.setBounds(70, 215, 100, 25);
        add(lblCantidad);

        txtCantidad = crearTextField();
        txtCantidad.setBounds(180, 215, 120, 30);
        add(txtCantidad);

        JLabel lblCosto = crearLabel("Costo unitario:");
        lblCosto.setBounds(350, 215, 120, 25);
        add(lblCosto);

        txtCostoUnitario = crearTextField();
        txtCostoUnitario.setBounds(480, 215, 120, 30);
        add(txtCostoUnitario);

        JLabel lblFecha = crearLabel("Fecha:");
        lblFecha.setBounds(650, 215, 60, 25);
        add(lblFecha);

        txtFecha = crearTextField();
        txtFecha.setBounds(720, 215, 160, 30);
        add(txtFecha);

        JLabel lblObservaciones = crearLabel("Observaciones:");
        lblObservaciones.setBounds(70, 260, 120, 25);
        add(lblObservaciones);

        txtObservaciones = new JTextArea();
        txtObservaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        txtObservaciones.setBackground(Color.WHITE);
        txtObservaciones.setForeground(Color.BLACK);
        txtObservaciones.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));

        JScrollPane scrollObs = new JScrollPane(txtObservaciones);
        scrollObs.setBounds(190, 260, 690, 75);
        add(scrollObs);

        // BOTONES
        btnGuardar = crearBoton("Guardar gasto");
        btnGuardar.setBounds(90, 355, 150, 35);
        add(btnGuardar);

        btnLimpiar = crearBoton("Limpiar");
        btnLimpiar.setBounds(270, 355, 130, 35);
        add(btnLimpiar);

        btnEliminar = crearBoton("Eliminar gasto");
        btnEliminar.setBounds(430, 355, 150, 35);
        add(btnEliminar);

        btnCargar = crearBoton("Cargar gastos");
        btnCargar.setBounds(610, 355, 150, 35);
        add(btnCargar);

        btnVolver = crearBoton("Volver al menú");
        btnVolver.setBounds(790, 355, 160, 35);
        add(btnVolver);

        // TABLA
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{
                "ID", "Concepto", "Categoría", "Cantidad",
                "Costo Unitario", "Total", "Fecha", "Observaciones"
        });

        tablaGastos = new JTable(modeloTabla);
        tablaGastos.getColumnModel().getColumn(0).setMinWidth(35);
        tablaGastos.getColumnModel().getColumn(0).setMaxWidth(40);
        tablaGastos.getColumnModel().getColumn(0).setPreferredWidth(38);
        tablaGastos.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaGastos.getColumnModel().getColumn(2).setPreferredWidth(190);
        tablaGastos.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablaGastos.getColumnModel().getColumn(4).setPreferredWidth(110);
        tablaGastos.getColumnModel().getColumn(5).setPreferredWidth(90);
        tablaGastos.getColumnModel().getColumn(6).setPreferredWidth(140);
        tablaGastos.getColumnModel().getColumn(7).setPreferredWidth(320);

        tablaGastos.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaGastos.setRowHeight(24);
        tablaGastos.setGridColor(new Color(90, 90, 90));
        tablaGastos.setSelectionBackground(new Color(57, 255, 20));
        tablaGastos.setSelectionForeground(Color.BLACK);
        tablaGastos.getTableHeader().setBackground(verdeNeon);
        tablaGastos.getTableHeader().setForeground(Color.BLACK);
        tablaGastos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
////////////////
        JScrollPane scrollTabla = new JScrollPane(tablaGastos);
        scrollTabla.setBounds(30, 420, 930, 150);
        scrollTabla.setBorder(BorderFactory.createLineBorder(verdeNeon, 2));
        add(scrollTabla);

        // DETALLE
        JLabel lblDetalleObs = crearLabel("Detalle de observación:");
        lblDetalleObs.setBounds(30, 590, 180, 25);
        add(lblDetalleObs);

        txtDetalleObservacion = new JTextArea();
        txtDetalleObservacion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDetalleObservacion.setLineWrap(true);
        txtDetalleObservacion.setWrapStyleWord(true);
        txtDetalleObservacion.setEditable(false);
        txtDetalleObservacion.setBackground(Color.WHITE);
        txtDetalleObservacion.setForeground(Color.BLACK);
        txtDetalleObservacion.setText("Seleccione un gasto para ver la observación completa.");

        JScrollPane scrollDetalleObs = new JScrollPane(txtDetalleObservacion);
        scrollDetalleObs.setBounds(210, 585, 750, 70);
        scrollDetalleObs.setBorder(BorderFactory.createLineBorder(verdeNeon, 2));
        add(scrollDetalleObs);
    }

    private void configurarEventos() {
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> menuPrincipal.mostrarBienvenida());
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 15));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private JLabel crearLabelValor(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 15));
        lbl.setForeground(new Color(57, 255, 20));
        return lbl;
    }

    private JTextField crearTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setBackground(Color.WHITE);
        txt.setForeground(Color.BLACK);
        txt.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));
        return txt;
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
        txtConcepto.setText("");
        cmbCategoria.setSelectedIndex(0);
        txtCantidad.setText("");
        txtCostoUnitario.setText("");
        txtFecha.setText("");
        txtObservaciones.setText("");
    }

    public JLabel getLblProyectoValor() { return lblProyectoValor; }
    public JLabel getLblSaldoInicialValor() { return lblSaldoInicialValor; }
    public JLabel getLblTotalGastadoValor() { return lblTotalGastadoValor; }
    public JLabel getLblSaldoRestanteValor() { return lblSaldoRestanteValor; }

    public JTextField getTxtSaldoInicialEditable() { return txtSaldoInicialEditable; }
    public JButton getBtnActualizarPresupuesto() { return btnActualizarPresupuesto; }

    public JTextField getTxtConcepto() { return txtConcepto; }
    public JComboBox<String> getCmbCategoria() { return cmbCategoria; }
    public JTextField getTxtCantidad() { return txtCantidad; }
    public JTextField getTxtCostoUnitario() { return txtCostoUnitario; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JTextArea getTxtObservaciones() { return txtObservaciones; }

    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnCargar() { return btnCargar; }
    public JButton getBtnVolver() { return btnVolver; }

    public JTable getTablaGastos() { return tablaGastos; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JTextArea getTxtDetalleObservacion() { return txtDetalleObservacion; }
}