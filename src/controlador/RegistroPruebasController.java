package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Prueba;
import modelo.PruebaDAO;
import vista.PanelRegistroPruebas;
import modelo.Operador;
import modelo.OperadorDAO;
import java.util.ArrayList;

import modelo.ModoPrueba;
import modelo.ModoPruebaDAO;
import modelo.Movimiento;
import modelo.MovimientoDAO;
import modelo.Resultado;
import modelo.ResultadoDAO;

public class RegistroPruebasController implements ActionListener {

    private PanelRegistroPruebas vista;
    private PruebaDAO pruebaDAO;
    private OperadorDAO operadorDAO;
    private ArrayList<Operador> listaOperadores;
    
    private ModoPruebaDAO modoPruebaDAO;
    private MovimientoDAO movimientoDAO;
    private ResultadoDAO resultadoDAO;

    private ArrayList<ModoPrueba> listaModos;
    private ArrayList<Movimiento> listaMovimientos;
    private ArrayList<Resultado> listaResultados;

    public RegistroPruebasController(PanelRegistroPruebas vista) {
        this.vista = vista;
        this.pruebaDAO = new PruebaDAO();

        this.operadorDAO = new OperadorDAO();
        this.modoPruebaDAO = new ModoPruebaDAO();
        this.movimientoDAO = new MovimientoDAO();
        this.resultadoDAO = new ResultadoDAO();

        this.listaOperadores = operadorDAO.obtenerOperadores();
        this.listaModos = modoPruebaDAO.obtenerModos();
        this.listaMovimientos = movimientoDAO.obtenerMovimientos();
        this.listaResultados = resultadoDAO.obtenerResultados();

        this.vista.cargarOperadoresEnCombo(listaOperadores);
        this.vista.cargarModosEnCombo(listaModos);
        this.vista.cargarMovimientosEnCombo(listaMovimientos);
        this.vista.cargarResultadosEnCombo(listaResultados);

        this.vista.getBtnGuardar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarPrueba();
        }
    }


    private void guardarPrueba() {
        try {
            if (vista.getTxtFecha().getText().trim().isEmpty() ||
                vista.getTxtVelocidad().getText().trim().isEmpty() ||
                vista.getTxtBateria().getText().trim().isEmpty() ||
                vista.getTxtTiempoRespuesta().getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(vista, "Complete todos los campos de texto.");
                return;
            }

            if (vista.getCmbModoPrueba().getSelectedIndex() == 0 ||
                vista.getCmbOperador().getSelectedIndex() == 0 ||
                vista.getCmbMovimiento().getSelectedIndex() == 0 ||
                vista.getCmbResultado().getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(vista, "Seleccione una opción válida en todos los ComboBox.");
                return;
            }

            int velocidadPWM = Integer.parseInt(vista.getTxtVelocidad().getText().trim());

            if (velocidadPWM < 0 || velocidadPWM > 255) {
                JOptionPane.showMessageDialog(vista, "La velocidad PWM debe estar entre 0 y 255.");
                return;
            }

            int idOperador = obtenerIdOperadorSeleccionado();
            int idModo = obtenerIdModoSeleccionado();
            int idMovimiento = obtenerIdMovimientoSeleccionado();
            int idResultado = obtenerIdResultadoSeleccionado();

            Prueba prueba = new Prueba();
            prueba.setFecha(vista.getTxtFecha().getText().trim());
            prueba.setVelocidadPWM(velocidadPWM);
            prueba.setBateria(vista.getTxtBateria().getText().trim());
            prueba.setTiempoRespuesta(vista.getTxtTiempoRespuesta().getText().trim());
            prueba.setObservaciones(vista.getTxtObservaciones().getText().trim());

            prueba.setIdOperador(idOperador);
            prueba.setIdModo(idModo);
            prueba.setIdMovimiento(idMovimiento);
            prueba.setIdResultado(idResultado);

            boolean guardado = pruebaDAO.guardarPrueba(prueba);

            if (guardado) {
                JOptionPane.showMessageDialog(vista, "Prueba guardada correctamente.");
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo guardar la prueba.");
            }

        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar en la base de datos:\n" + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "La Velocidad PWM debe ser numérica.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
    
    private int obtenerIdOperadorSeleccionado() {
        int indice = vista.getCmbOperador().getSelectedIndex();

        if (indice <= 0) {
            return -1;
        }

        return listaOperadores.get(indice - 1).getIdOperador();
    }

    private int obtenerIdModoSeleccionado() {
        int indice = vista.getCmbModoPrueba().getSelectedIndex();

        if (indice <= 0) {
            return -1;
        }

        return listaModos.get(indice - 1).getIdModo();
    }

    private int obtenerIdMovimientoSeleccionado() {
        int indice = vista.getCmbMovimiento().getSelectedIndex();

        if (indice <= 0) {
            return -1;
        }

        return listaMovimientos.get(indice - 1).getIdMovimiento();
    }

    private int obtenerIdResultadoSeleccionado() {
        int indice = vista.getCmbResultado().getSelectedIndex();

        if (indice <= 0) {
            return -1;
        }

        return listaResultados.get(indice - 1).getIdResultado();
    }
    
    
}