package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelRegistroPruebas extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txtFecha;
    private JTextField txtVelocidad;
    private JTextField txtBateria;
    private JTextField txtTiempoRespuesta;

    private JComboBox<String> cmbModoPrueba;
    private JComboBox<String> cmbOperador;
    private JComboBox<String> cmbMovimiento;
    private JComboBox<String> cmbResultado;

    private JTextArea txtObservaciones;

    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnVolver;

    private MenuPrincipalView menuPrincipal;

    public PanelRegistroPruebas(MenuPrincipalView menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setLayout(null);
        setBackground(new Color(45, 45, 50));

        inicializarComponentes();
        configurarEventos();
    }
    

    private void inicializarComponentes() {

       
        Color verdeNeon = new Color(57, 255, 20);

        JLabel lblTitulo = new JLabel("Registro de pruebas del robot Mini-Sumo");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 23));
        lblTitulo.setForeground(verdeNeon);
        lblTitulo.setBounds(250, 20, 520, 32);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Capture los datos de cada prueba realizada al robot");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(160, 65, 360, 20);
        add(lblSubtitulo);

        // =====================
        // ETIQUETAS IZQUIERDA
        // =====================
        JLabel lblFecha = crearLabel("Fecha:");
        lblFecha.setBounds(120, 130, 170, 28);
        add(lblFecha);

        JLabel lblVelocidad = crearLabel("Velocidad PWM:");
        lblVelocidad.setBounds(120, 185, 170, 28);
        add(lblVelocidad);

        JLabel lblModoPrueba = crearLabel("Modo de prueba:");
        lblModoPrueba.setBounds(120, 240, 170, 28);
        add(lblModoPrueba);

        JLabel lblOperador = crearLabel("Operador:");
        lblOperador.setBounds(120, 295, 170, 28);
        add(lblOperador);

        JLabel lblObservaciones = crearLabel("Observaciones:");
        lblObservaciones.setBounds(120, 365, 180, 28);
        add(lblObservaciones);

        // =====================
        // ETIQUETAS DERECHA
        // =====================
        JLabel lblBateria = crearLabel("Batería:");
        lblBateria.setBounds(560, 130, 170, 28);
        add(lblBateria);

        JLabel lblTiempoRespuesta = crearLabel("Tiempo de respuesta:");
        lblTiempoRespuesta.setBounds(560, 185, 210, 28);
        add(lblTiempoRespuesta);

        JLabel lblMovimiento = crearLabel("Movimiento:");
        lblMovimiento.setBounds(560, 240, 170, 28);
        add(lblMovimiento);

        JLabel lblResultado = crearLabel("Resultado:");
        lblResultado.setBounds(560, 295, 170, 28);
        add(lblResultado);

        // =====================
        // CAMPOS IZQUIERDA
        // =====================
        txtFecha = crearTextField();
        txtFecha.setBounds(290, 128, 240, 34);
        add(txtFecha);

        txtVelocidad = crearTextField();
        txtVelocidad.setBounds(290, 183, 240, 34);
        add(txtVelocidad);

        cmbModoPrueba = new JComboBox<>();
        cmbModoPrueba.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar"}));
        cmbModoPrueba.setFont(new Font("Arial", Font.PLAIN, 15));
        cmbModoPrueba.setBounds(290, 238, 240, 34);
        add(cmbModoPrueba);

        cmbOperador = new JComboBox<>();
        cmbOperador.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar"}));
        cmbOperador.setFont(new Font("Arial", Font.PLAIN, 15));
        cmbOperador.setBounds(290, 293, 240, 34);
        add(cmbOperador);

        // =====================
        // CAMPOS DERECHA
        // =====================
        txtBateria = crearTextField();
        txtBateria.setBounds(730, 128, 240, 34);
        add(txtBateria);

        txtTiempoRespuesta = crearTextField();
        txtTiempoRespuesta.setBounds(730, 183, 240, 34);
        add(txtTiempoRespuesta);

        cmbMovimiento = new JComboBox<>();
        cmbMovimiento.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar"}));
        cmbMovimiento.setFont(new Font("Arial", Font.PLAIN, 15));
        cmbMovimiento.setBounds(730, 238, 240, 34);
        add(cmbMovimiento);

        cmbResultado = new JComboBox<>();
        cmbResultado.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar"}));
        cmbResultado.setFont(new Font("Arial", Font.PLAIN, 15));
        cmbResultado.setBounds(730, 293, 240, 34);
        add(cmbResultado);

        // =====================
        // OBSERVACIONES
        // =====================
        txtObservaciones = new JTextArea();
        txtObservaciones.setFont(new Font("Arial", Font.PLAIN, 15));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        txtObservaciones.setBackground(Color.WHITE);
        txtObservaciones.setForeground(Color.BLACK);
        txtObservaciones.setCaretColor(Color.BLACK);
        txtObservaciones.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)));

        JScrollPane scrollObservaciones = new JScrollPane(txtObservaciones);
        scrollObservaciones.setBounds(290, 365, 680, 95);
        add(scrollObservaciones);

        // =====================
        // BOTONES
        // =====================
        btnGuardar = crearBoton("Guardar");
        btnGuardar.setBounds(330, 490, 145, 40);
        add(btnGuardar);

        btnLimpiar = crearBoton("Limpiar");
        btnLimpiar.setBounds(515, 490, 145, 40);
        add(btnLimpiar);

        btnVolver = crearBoton("Volver al menú");
        btnVolver.setBounds(700, 490, 185, 40);
        add(btnVolver);
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void configurarEventos() {
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnVolver.addActionListener(e -> menuPrincipal.mostrarBienvenida());
    }

    private JTextField crearTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 15));
        txt.setBackground(Color.WHITE);
        txt.setForeground(Color.BLACK);
        txt.setCaretColor(Color.BLACK);
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
        txtFecha.setText("");
        txtVelocidad.setText("");
        txtBateria.setText("");
        txtTiempoRespuesta.setText("");
        txtObservaciones.setText("");

        cmbModoPrueba.setSelectedIndex(0);
        cmbOperador.setSelectedIndex(0);
        cmbMovimiento.setSelectedIndex(0);
        cmbResultado.setSelectedIndex(0);
    }

    public void cargarOperadoresEnCombo(java.util.ArrayList<modelo.Operador> listaOperadores) {
        cmbOperador.removeAllItems();
        cmbOperador.addItem("Seleccionar");
        for (modelo.Operador operador : listaOperadores) {
            cmbOperador.addItem(operador.getIdOperador() + " - " + operador.getNombreOperador());
        }
    }

    public void cargarModosEnCombo(java.util.ArrayList<modelo.ModoPrueba> listaModos) {
        cmbModoPrueba.removeAllItems();
        cmbModoPrueba.addItem("Seleccionar");
        for (modelo.ModoPrueba modo : listaModos) {
            cmbModoPrueba.addItem(modo.getIdModo() + " - " + modo.getNombreModo());
        }
    }

    public void cargarMovimientosEnCombo(java.util.ArrayList<modelo.Movimiento> listaMovimientos) {
        cmbMovimiento.removeAllItems();
        cmbMovimiento.addItem("Seleccionar");
        for (modelo.Movimiento movimiento : listaMovimientos) {
            cmbMovimiento.addItem(movimiento.getIdMovimiento() + " - " + movimiento.getNombreMovimiento());
        }
    }

    public void cargarResultadosEnCombo(java.util.ArrayList<modelo.Resultado> listaResultados) {
        cmbResultado.removeAllItems();
        cmbResultado.addItem("Seleccionar");
        for (modelo.Resultado resultado : listaResultados) {
            cmbResultado.addItem(resultado.getIdResultado() + " - " + resultado.getDescripcionResultado());
        }
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JTextField getTxtVelocidad() {
        return txtVelocidad;
    }

    public JTextField getTxtBateria() {
        return txtBateria;
    }

    public JTextField getTxtTiempoRespuesta() {
        return txtTiempoRespuesta;
    }

    public JComboBox<String> getCmbModoPrueba() {
        return cmbModoPrueba;
    }

    public JComboBox<String> getCmbOperador() {
        return cmbOperador;
    }

    public JComboBox<String> getCmbMovimiento() {
        return cmbMovimiento;
    }

    public JComboBox<String> getCmbResultado() {
        return cmbResultado;
    }

    public JTextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}