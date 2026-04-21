package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Operador;
import modelo.OperadorDAO;
import vista.PanelOperadores;

public class OperadorController implements ActionListener {

    private PanelOperadores vista;
    private OperadorDAO operadorDAO;

    public OperadorController(PanelOperadores vista) {
        this.vista = vista;
        this.operadorDAO = new OperadorDAO();

        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnCargar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarOperador();
        }

        if (e.getSource() == vista.getBtnCargar()) {
            cargarOperadores();
        }
        
        if (e.getSource() == vista.getBtnEliminar()) {
            eliminarOperador();
        }
    }
    
    private void eliminarOperador() {
        try {
            int filaSeleccionada = vista.getTablaOperadores().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un operador de la tabla para eliminar.");
                return;
            }

            int idOperador = Integer.parseInt(
                    vista.getModeloTabla().getValueAt(filaSeleccionada, 0).toString()
            );

            String nombreOperador = vista.getModeloTabla().getValueAt(filaSeleccionada, 1).toString();

            int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Desea eliminar al operador: " + nombreOperador + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = operadorDAO.eliminarOperador(idOperador);

                if (eliminado) {
                    JOptionPane.showMessageDialog(vista, "Operador eliminado correctamente.");
                    cargarOperadores();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo eliminar el operador.");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    vista,
                    "No se puede eliminar el operador porque puede estar relacionado con pruebas registradas.\n"
                    + ex.getMessage()
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }

    private void guardarOperador() {
        try {
            String nombre = vista.getTxtNombreOperador().getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese el nombre del operador.");
                return;
            }

            Operador operador = new Operador();
            operador.setNombreOperador(nombre);

            boolean guardado = operadorDAO.guardarOperador(operador);

            if (guardado) {
                JOptionPane.showMessageDialog(vista, "Operador guardado correctamente.");
                vista.getTxtNombreOperador().setText("");
                cargarOperadores();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo guardar el operador.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error en BD:\n" + ex.getMessage());
        }
    }

    public void cargarOperadores() {
        ArrayList<Operador> lista = operadorDAO.obtenerOperadores();
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);

        for (Operador op : lista) {
            modelo.addRow(new Object[]{op.getIdOperador(), op.getNombreOperador()});
        }
    }
}