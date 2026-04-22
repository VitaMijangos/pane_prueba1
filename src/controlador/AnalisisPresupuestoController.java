package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.GastoDAO;
import modelo.Presupuesto;
import modelo.PresupuestoDAO;
import vista.PanelAnalisisPresupuesto;

public class AnalisisPresupuestoController implements ActionListener {

    private PanelAnalisisPresupuesto vista;
    private PresupuestoDAO presupuestoDAO;
    private GastoDAO gastoDAO;
    private Presupuesto presupuestoActual;

    public AnalisisPresupuestoController(PanelAnalisisPresupuesto vista) {
        this.vista = vista;
        this.presupuestoDAO = new PresupuestoDAO();
        this.gastoDAO = new GastoDAO();

        this.vista.getBtnCargarAnalisis().addActionListener(this);

        cargarAnalisis();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnCargarAnalisis()) {
            cargarAnalisis();
        }
    }

    public void cargarAnalisis() {
        presupuestoActual = presupuestoDAO.obtenerPresupuesto();

        if (presupuestoActual != null) {
            double saldoInicial = presupuestoActual.getSaldoInicial();
            double totalGastado = gastoDAO.obtenerTotalGastado();
            double saldoRestante = saldoInicial - totalGastado;
            int cantidadGastos = gastoDAO.contarGastos();

            double porcentajeUsado = 0;
            if (saldoInicial > 0) {
                porcentajeUsado = (totalGastado / saldoInicial) * 100;
            }

            vista.getLblProyectoValor().setText(presupuestoActual.getNombreProyecto());
            vista.getLblSaldoInicialValor().setText("$" + String.format("%.2f", saldoInicial));
            vista.getLblTotalGastadoValor().setText("$" + String.format("%.2f", totalGastado));
            vista.getLblSaldoRestanteValor().setText("$" + String.format("%.2f", saldoRestante));
            vista.getLblCantidadGastosValor().setText(String.valueOf(cantidadGastos));
            vista.getLblPorcentajeUsadoValor().setText(String.format("%.2f", porcentajeUsado) + "%");
            vista.getLblCategoriaMayorValor().setText(gastoDAO.obtenerCategoriaMayorGasto());
            vista.getLblComponenteCaroValor().setText(gastoDAO.obtenerComponenteMasCaro());

            int valorBarra = (int) Math.round(porcentajeUsado);
            vista.getBarraPresupuesto().setValue(Math.min(valorBarra, 100));

            if (porcentajeUsado < 70) {
                vista.getBarraPresupuesto().setForeground(new Color(57, 255, 20));
            } else if (porcentajeUsado < 100) {
                vista.getBarraPresupuesto().setForeground(Color.ORANGE);
            } else {
                vista.getBarraPresupuesto().setForeground(Color.RED);
            }

            if (saldoRestante < 0) {
                vista.getLblSaldoRestanteValor().setForeground(Color.RED);
            } else {
                vista.getLblSaldoRestanteValor().setForeground(new Color(57, 255, 20));
            }
        }
    }
}