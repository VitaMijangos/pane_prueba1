package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelEstadisticas extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel lblTotalPruebasValor;
    private JLabel lblExitosasValor;
    private JLabel lblFallidasValor;
    private JLabel lblPromedioVelocidadValor;

   
    private JButton btnVolver;

    private MenuPrincipalView menuPrincipal;

    public PanelEstadisticas(MenuPrincipalView menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setLayout(null);
        setBackground(new Color(45, 45, 50));

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JLabel lblTitulo = new JLabel("Estadísticas del robot Mini-Sumo");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(57, 255, 20));
        lblTitulo.setBounds(180, 20, 500, 30);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Resumen general de las pruebas registradas");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(30, 60, 350, 20);
        add(lblSubtitulo);

        JPanel tarjeta1 = crearTarjeta("Total de pruebas");
        tarjeta1.setBounds(80, 120, 300, 120);
        add(tarjeta1);

        lblTotalPruebasValor = crearValor();
        lblTotalPruebasValor.setBounds(0, 45, 300, 40);
        tarjeta1.add(lblTotalPruebasValor);

        JPanel tarjeta2 = crearTarjeta("Pruebas exitosas");
        tarjeta2.setBounds(460, 120, 300, 120);
        add(tarjeta2);

        lblExitosasValor = crearValor();
        lblExitosasValor.setBounds(0, 45, 300, 40);
        tarjeta2.add(lblExitosasValor);

        JPanel tarjeta3 = crearTarjeta("Pruebas fallidas");
        tarjeta3.setBounds(80, 290, 300, 120);
        add(tarjeta3);

        lblFallidasValor = crearValor();
        lblFallidasValor.setBounds(0, 45, 300, 40);
        tarjeta3.add(lblFallidasValor);

        JPanel tarjeta4 = crearTarjeta("Promedio velocidad PWM");
        tarjeta4.setBounds(460, 290, 300, 120);
        add(tarjeta4);

        lblPromedioVelocidadValor = crearValor();
        lblPromedioVelocidadValor.setBounds(0, 45, 300, 40);
        tarjeta4.add(lblPromedioVelocidadValor);


        btnVolver = crearBoton("Volver al menú");
        btnVolver.setBounds(360, 500, 180, 35);
        add(btnVolver);
    }

    private void configurarEventos() {
        btnVolver.addActionListener(e -> {
            menuPrincipal.mostrarBienvenida();
        });
    }

    private JPanel crearTarjeta(String titulo) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(35, 35, 35));
        panel.setBorder(BorderFactory.createLineBorder(new Color(57, 255, 20), 2));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 15, 300, 25);
        panel.add(lblTitulo);

        return panel;
    }

    private JLabel crearValor() {
        JLabel lbl = new JLabel("0");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 30));
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

    public JLabel getLblTotalPruebasValor() {
        return lblTotalPruebasValor;
    }

    public JLabel getLblExitosasValor() {
        return lblExitosasValor;
    }

    public JLabel getLblFallidasValor() {
        return lblFallidasValor;
    }

    public JLabel getLblPromedioVelocidadValor() {
        return lblPromedioVelocidadValor;
    }


    public JButton getBtnVolver() {
        return btnVolver;
    }
}