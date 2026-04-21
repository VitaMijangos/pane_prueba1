package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Prueba;
import modelo.PruebaDAO;
import vista.PanelHistorialPruebas;

public class HistorialPruebasController implements ActionListener, ListSelectionListener {

    private PanelHistorialPruebas vista;
    private PruebaDAO pruebaDAO;
    private ArrayList<Prueba> listaPruebas;

    public HistorialPruebasController(PanelHistorialPruebas vista) {
        this.vista = vista;
        this.pruebaDAO = new PruebaDAO();
        this.listaPruebas = new ArrayList<>();

        this.vista.getBtnCargar().addActionListener(this);
        this.vista.getTablaPruebas().getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnCargar()) {
            cargarHistorial();
        }
    }

    public void cargarHistorial() {
        listaPruebas = pruebaDAO.obtenerPruebas();
        DefaultTableModel modelo = vista.getModeloTabla();

        modelo.setRowCount(0);
        vista.getTxtDetalleObservacion().setText("Seleccione una fila para ver la observación completa.");

        if (listaPruebas.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay pruebas registradas.");
            return;
        }

        for (Prueba prueba : listaPruebas) {
        	Object[] fila = {
        	        prueba.getIdPrueba(),
        	        prueba.getFecha(),
        	        prueba.getVelocidadPWM(),
        	        prueba.getBateria(),
        	        prueba.getTiempoRespuesta(),
        	        prueba.getNombreModo(),
        	        prueba.getNombreOperador(),
        	        prueba.getNombreMovimiento(),
        	        prueba.getNombreResultado(),
        	        resumirTexto(prueba.getObservaciones())
        	};

            modelo.addRow(fila);
        }
    }

    private String resumirTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        if (texto.length() <= 30) {
            return texto;
        }

        return texto.substring(0, 30) + "...";
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int filaSeleccionada = vista.getTablaPruebas().getSelectedRow();

            if (filaSeleccionada >= 0 && filaSeleccionada < listaPruebas.size()) {
                String observacionCompleta = listaPruebas.get(filaSeleccionada).getObservaciones();

                if (observacionCompleta == null || observacionCompleta.trim().isEmpty()) {
                    vista.getTxtDetalleObservacion().setText("Esta prueba no contiene observaciones.");
                } else {
                    vista.getTxtDetalleObservacion().setText(observacionCompleta);
                }
            }
        }
    }
}