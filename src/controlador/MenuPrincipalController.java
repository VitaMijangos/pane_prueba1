package controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vista.MenuPrincipalView;
import vista.PanelRegistroPruebas;
import vista.PanelHistorialPruebas;
import vista.PanelEstadisticas;
import vista.PanelOperadores;
import vista.LoginView;
import vista.PanelUsuarios;



public class MenuPrincipalController implements ActionListener {

    private MenuPrincipalView vista;

    public MenuPrincipalController(MenuPrincipalView vista) {
        this.vista = vista;

        this.vista.getBtnRegistro().addActionListener(this);
        this.vista.getBtnHistorial().addActionListener(this);
        this.vista.getBtnEstadisticas().addActionListener(this);
        this.vista.getBtnSalir().addActionListener(this);
        this.vista.getBtnOperadores().addActionListener(this);
        this.vista.getBtnUsuarios().addActionListener(this);
    }

    public void iniciarVista() {
        vista.setVisible(true);
        vista.mostrarBienvenida();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getBtnRegistro()) {
            PanelRegistroPruebas panelRegistro = new PanelRegistroPruebas(vista);
            new RegistroPruebasController(panelRegistro);

            vista.getPanelContenido().removeAll();
            vista.getPanelContenido().add(panelRegistro, BorderLayout.CENTER);
            vista.getPanelContenido().revalidate();
            vista.getPanelContenido().repaint();
        }

        if (e.getSource() == vista.getBtnHistorial()) {
            PanelHistorialPruebas panelHistorial = new PanelHistorialPruebas(vista);
            HistorialPruebasController historialController = new HistorialPruebasController(panelHistorial);

            vista.getPanelContenido().removeAll();
            vista.getPanelContenido().add(panelHistorial, BorderLayout.CENTER);
            vista.getPanelContenido().revalidate();
            vista.getPanelContenido().repaint();

            historialController.cargarHistorial();
        }

        if (e.getSource() == vista.getBtnEstadisticas()) {
            PanelEstadisticas panelEstadisticas = new PanelEstadisticas(vista);
            EstadisticasController estadisticasController = new EstadisticasController(panelEstadisticas);

            vista.getPanelContenido().removeAll();
            vista.getPanelContenido().add(panelEstadisticas, BorderLayout.CENTER);
            vista.getPanelContenido().revalidate();
            vista.getPanelContenido().repaint();

            estadisticasController.cargarEstadisticas();
        }
        
        if (e.getSource() == vista.getBtnOperadores()) {
            PanelOperadores panelOperadores = new PanelOperadores(vista);
            OperadorController operadorController = new OperadorController(panelOperadores);

            vista.getPanelContenido().removeAll();
            vista.getPanelContenido().add(panelOperadores, BorderLayout.CENTER);
            vista.getPanelContenido().revalidate();
            vista.getPanelContenido().repaint();

            operadorController.cargarOperadores();
        }

        if (e.getSource() == vista.getBtnSalir()) {
            int opcion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Deseas cerrar sesión?",
                    "Cerrar sesión",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {

                vista.dispose();

                LoginView login = new LoginView();
                new LoginController(login);
                login.setVisible(true);
            }
        }
        
        if (e.getSource() == vista.getBtnUsuarios()) {
            PanelUsuarios panelUsuarios = new PanelUsuarios(vista);
            UsuarioController usuarioController = new UsuarioController(panelUsuarios);

            vista.getPanelContenido().removeAll();
            vista.getPanelContenido().add(panelUsuarios, BorderLayout.CENTER);
            vista.getPanelContenido().revalidate();
            vista.getPanelContenido().repaint();

            usuarioController.cargarUsuarios();
        }
    }
}