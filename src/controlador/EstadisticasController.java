package controlador;


import java.text.DecimalFormat;

import modelo.PruebaDAO;
import vista.PanelEstadisticas;

public class EstadisticasController {

    private PanelEstadisticas vista;
    private PruebaDAO pruebaDAO;

    public EstadisticasController(PanelEstadisticas vista) {
        this.vista = vista;
        this.pruebaDAO = new PruebaDAO();

    }

 

    public void cargarEstadisticas() {
        int total = pruebaDAO.contarPruebas();
        int exitosas = pruebaDAO.contarPruebasExitosas();
        int fallidas = pruebaDAO.contarPruebasFallidas();
        double promedioVelocidad = pruebaDAO.promedioVelocidadPWM();

        DecimalFormat df = new DecimalFormat("#.##");

        vista.getLblTotalPruebasValor().setText(String.valueOf(total));
        vista.getLblExitosasValor().setText(String.valueOf(exitosas));
        vista.getLblFallidasValor().setText(String.valueOf(fallidas));
        vista.getLblPromedioVelocidadValor().setText(df.format(promedioVelocidad));
    }
}