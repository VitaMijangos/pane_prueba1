package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

public class PanelAnalisisPresupuesto extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel lblProyectoValor;
    private JLabel lblSaldoInicialValor;
    private JLabel lblTotalGastadoValor;
    private JLabel lblSaldoRestanteValor;

    private JLabel lblCantidadGastosValor;
    private JLabel lblPorcentajeUsadoValor;
    private JLabel lblCategoriaMayorValor;
    private JLabel lblComponenteCaroValor;

    private JProgressBar barraPresupuesto;

    private JButton btnCargarAnalisis;
    private JButton btnVolver;

    private MenuPrincipalView menuPrincipal;

    public PanelAnalisisPresupuesto(MenuPrincipalView menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setLayout(null);
        setBackground(new Color(45, 45, 50));

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        Color verdeNeon = new Color(57, 255, 20);

        JLabel lblTitulo = new JLabel("Análisis de presupuesto");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(verdeNeon);
        lblTitulo.setBounds(310, 20, 420, 30);
        add(lblTitulo);

        // Tarjeta 1
        JPanel card1 = crearCard();
        card1.setBounds(50, 90, 280, 120);
        card1.setLayout(null);

        JLabel lblProyecto = crearLabelCard("Proyecto:");
        lblProyecto.setBounds(20, 20, 100, 25);
        card1.add(lblProyecto);

        lblProyectoValor = crearValorCard("---");
        lblProyectoValor.setBounds(120, 20, 140, 25);
        card1.add(lblProyectoValor);

        JLabel lblSaldoInicial = crearLabelCard("Saldo inicial:");
        lblSaldoInicial.setBounds(20, 60, 110, 25);
        card1.add(lblSaldoInicial);

        lblSaldoInicialValor = crearValorCard("$0.00");
        lblSaldoInicialValor.setBounds(130, 60, 120, 25);
        card1.add(lblSaldoInicialValor);

        add(card1);

        // Tarjeta 2
        JPanel card2 = crearCard();
        card2.setBounds(350, 90, 280, 120);
        card2.setLayout(null);

        JLabel lblTotalGastado = crearLabelCard("Total gastado:");
        lblTotalGastado.setBounds(20, 20, 110, 25);
        card2.add(lblTotalGastado);

        lblTotalGastadoValor = crearValorCard("$0.00");
        lblTotalGastadoValor.setBounds(140, 20, 120, 25);
        card2.add(lblTotalGastadoValor);

        JLabel lblSaldoRestante = crearLabelCard("Saldo restante:");
        lblSaldoRestante.setBounds(20, 60, 120, 25);
        card2.add(lblSaldoRestante);

        lblSaldoRestanteValor = crearValorCard("$0.00");
        lblSaldoRestanteValor.setBounds(140, 60, 120, 25);
        card2.add(lblSaldoRestanteValor);

        add(card2);

        // Tarjeta 3
        JPanel card3 = crearCard();
        card3.setBounds(650, 90, 280, 120);
        card3.setLayout(null);

        JLabel lblCantidadGastos = crearLabelCard("Cantidad de gastos:");
        lblCantidadGastos.setBounds(20, 20, 160, 25);
        card3.add(lblCantidadGastos);

        lblCantidadGastosValor = crearValorCard("0");
        lblCantidadGastosValor.setBounds(180, 20, 80, 25);
        card3.add(lblCantidadGastosValor);

        JLabel lblPorcentajeUsado = crearLabelCard("% usado:");
        lblPorcentajeUsado.setBounds(20, 60, 100, 25);
        card3.add(lblPorcentajeUsado);

        lblPorcentajeUsadoValor = crearValorCard("0%");
        lblPorcentajeUsadoValor.setBounds(93, 60, 100, 25);
        card3.add(lblPorcentajeUsadoValor);

        add(card3);

        // Barra de progreso
        JLabel lblUso = new JLabel("Uso del presupuesto");
        lblUso.setFont(new Font("Arial", Font.BOLD, 18));
        lblUso.setForeground(Color.WHITE);
        lblUso.setBounds(50, 250, 220, 30);
        add(lblUso);

        barraPresupuesto = new JProgressBar(0, 100);
        barraPresupuesto.setBounds(50, 290, 900, 30);
        barraPresupuesto.setValue(0);
        barraPresupuesto.setStringPainted(true);
        barraPresupuesto.setForeground(verdeNeon);
        barraPresupuesto.setBackground(Color.WHITE);
        add(barraPresupuesto);

        // Analíticas detalle
        JPanel card4 = crearCard();
        card4.setBounds(50, 360, 420, 140);
        card4.setLayout(null);

        JLabel lblCategoriaMayor = crearLabelCard("Categoría más costosa:");
        lblCategoriaMayor.setBounds(20, 25, 200, 25);
        card4.add(lblCategoriaMayor);

        lblCategoriaMayorValor = crearValorCard("Sin datos");
        lblCategoriaMayorValor.setBounds(20, 60, 360, 25);
        card4.add(lblCategoriaMayorValor);

        add(card4);

        JPanel card5 = crearCard();
        card5.setBounds(510, 360, 420, 140);
        card5.setLayout(null);

        JLabel lblComponenteCaro = crearLabelCard("Componente más caro:");
        lblComponenteCaro.setBounds(20, 25, 180, 25);
        card5.add(lblComponenteCaro);

        lblComponenteCaroValor = crearValorCard("Sin datos");
        lblComponenteCaroValor.setBounds(20, 60, 360, 45);
        card5.add(lblComponenteCaroValor);

        add(card5);

        btnCargarAnalisis = crearBoton("Cargar análisis");
        btnCargarAnalisis.setBounds(300, 550, 170, 38);
        add(btnCargarAnalisis);

        btnVolver = crearBoton("Volver al menú");
        btnVolver.setBounds(560, 550, 170, 38);
        add(btnVolver);
    }

    private void configurarEventos() {
        btnVolver.addActionListener(e -> menuPrincipal.mostrarBienvenida());
    }

    private JPanel crearCard() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(35, 35, 35));
        panel.setBorder(BorderFactory.createLineBorder(new Color(57, 255, 20), 2));
        return panel;
    }

    private JLabel crearLabelCard(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private JLabel crearValorCard(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setForeground(new Color(57, 255, 20));
        return lbl;
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

    public JLabel getLblProyectoValor() {
        return lblProyectoValor;
    }

    public JLabel getLblSaldoInicialValor() {
        return lblSaldoInicialValor;
    }

    public JLabel getLblTotalGastadoValor() {
        return lblTotalGastadoValor;
    }

    public JLabel getLblSaldoRestanteValor() {
        return lblSaldoRestanteValor;
    }

    public JLabel getLblCantidadGastosValor() {
        return lblCantidadGastosValor;
    }

    public JLabel getLblPorcentajeUsadoValor() {
        return lblPorcentajeUsadoValor;
    }

    public JLabel getLblCategoriaMayorValor() {
        return lblCategoriaMayorValor;
    }

    public JLabel getLblComponenteCaroValor() {
        return lblComponenteCaroValor;
    }

    public JProgressBar getBarraPresupuesto() {
        return barraPresupuesto;
    }

    public JButton getBtnCargarAnalisis() {
        return btnCargarAnalisis;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}