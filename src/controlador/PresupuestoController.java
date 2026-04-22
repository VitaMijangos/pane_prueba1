package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Gasto;
import modelo.GastoDAO;
import modelo.Presupuesto;
import modelo.PresupuestoDAO;
import vista.PanelPresupuesto;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PresupuestoController implements ActionListener, ListSelectionListener {

    private PanelPresupuesto vista;
    private PresupuestoDAO presupuestoDAO;
    private GastoDAO gastoDAO;
    private Presupuesto presupuestoActual;

    public PresupuestoController(PanelPresupuesto vista) {
        this.vista = vista;
        this.presupuestoDAO = new PresupuestoDAO();
        this.gastoDAO = new GastoDAO();

        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnCargar().addActionListener(this);
        this.vista.getTablaGastos().getSelectionModel().addListSelectionListener(this);
        this.vista.getBtnActualizarPresupuesto().addActionListener(this);
        
        

        cargarPresupuesto();
        cargarGastos();
        actualizarResumen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarGasto();
        }

        if (e.getSource() == vista.getBtnEliminar()) {
            eliminarGasto();
        }

        if (e.getSource() == vista.getBtnCargar()) {
            cargarGastos();
            actualizarResumen();
        }
        
        if (e.getSource() == vista.getBtnActualizarPresupuesto()) {
            actualizarPresupuestoInicial();
        }
    }

    private void cargarPresupuesto() {
        presupuestoActual = presupuestoDAO.obtenerPresupuesto();

        if (presupuestoActual != null) {
            vista.getLblProyectoValor().setText(presupuestoActual.getNombreProyecto());
            vista.getLblSaldoInicialValor().setText("$" + String.format("%.2f", presupuestoActual.getSaldoInicial()));
            vista.getTxtSaldoInicialEditable().setText("");
        } else {
            vista.getLblProyectoValor().setText("No definido");
            vista.getLblSaldoInicialValor().setText("$0.00");
            vista.getTxtSaldoInicialEditable().setText("");
        }
    }

    private void guardarGasto() {
        try {
            if (presupuestoActual == null) {
                JOptionPane.showMessageDialog(vista, "No existe un presupuesto registrado.");
                return;
            }

            String concepto = vista.getTxtConcepto().getText().trim();
            String categoria = vista.getCmbCategoria().getSelectedItem().toString();
            String cantidadTexto = vista.getTxtCantidad().getText().trim();
            String costoTexto = vista.getTxtCostoUnitario().getText().trim();
            String fecha = vista.getTxtFecha().getText().trim();
            String observaciones = vista.getTxtObservaciones().getText().trim();

            if (concepto.isEmpty() || cantidadTexto.isEmpty() || costoTexto.isEmpty() || fecha.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos obligatorios.");
                return;
            }

            if (vista.getCmbCategoria().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vista, "Seleccione una categoría.");
                return;
            }

            int cantidad = Integer.parseInt(cantidadTexto);
            double costoUnitario = Double.parseDouble(costoTexto);

            if (cantidad <= 0 || costoUnitario < 0) {
                JOptionPane.showMessageDialog(vista, "Cantidad y costo deben ser válidos.");
                return;
            }

            double total = cantidad * costoUnitario;

            Gasto gasto = new Gasto();
            gasto.setConcepto(concepto);
            gasto.setCategoria(categoria);
            gasto.setCantidad(cantidad);
            gasto.setCostoUnitario(costoUnitario);
            gasto.setTotal(total);
            gasto.setFecha(fecha);
            gasto.setObservaciones(observaciones);
            gasto.setIdPresupuesto(presupuestoActual.getIdPresupuesto());

            boolean guardado = gastoDAO.guardarGasto(gasto);

            if (guardado) {
                JOptionPane.showMessageDialog(vista, "Gasto guardado correctamente.");
                vista.limpiarCampos();
                cargarGastos();
                actualizarResumen();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo guardar el gasto.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Cantidad y costo unitario deben ser numéricos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error:\n" + ex.getMessage());
        }
    }

    public void cargarGastos() {
        ArrayList<Gasto> lista = gastoDAO.obtenerGastos();
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);

        for (Gasto gasto : lista) {
            modelo.addRow(new Object[]{
                    gasto.getIdGasto(),
                    gasto.getConcepto(),
                    gasto.getCategoria(),
                    gasto.getCantidad(),
                    gasto.getCostoUnitario(),
                    gasto.getTotal(),
                    gasto.getFecha(),
                    resumirTexto(gasto.getObservaciones())
            });
        }
    }

    private void eliminarGasto() {
        try {
            int filaSeleccionada = vista.getTablaGastos().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un gasto de la tabla.");
                return;
            }

            int idGasto = Integer.parseInt(
                    vista.getModeloTabla().getValueAt(filaSeleccionada, 0).toString()
            );

            int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Desea eliminar el gasto seleccionado?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = gastoDAO.eliminarGasto(idGasto);

                if (eliminado) {
                    JOptionPane.showMessageDialog(vista, "Gasto eliminado correctamente.");
                    cargarGastos();
                    actualizarResumen();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo eliminar el gasto.");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error:\n" + ex.getMessage());
        }
    }

    private void actualizarResumen() {
        if (presupuestoActual != null) {
            double saldoInicial = presupuestoActual.getSaldoInicial();
            double totalGastado = gastoDAO.obtenerTotalGastado();
            double saldoRestante = saldoInicial - totalGastado;

            vista.getLblSaldoInicialValor().setText("$" + String.format("%.2f", saldoInicial));
            vista.getLblTotalGastadoValor().setText("$" + String.format("%.2f", totalGastado));
            vista.getLblSaldoRestanteValor().setText("$" + String.format("%.2f", saldoRestante));

            if (saldoRestante < 0) {
                vista.getLblSaldoRestanteValor().setForeground(Color.RED);
            } else {
                vista.getLblSaldoRestanteValor().setForeground(new Color(57, 255, 20));
            }
        }
    }

    private String resumirTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        if (texto.length() <= 25) {
            return texto;
        }

        return texto.substring(0, 25) + "...";
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int filaSeleccionada = vista.getTablaGastos().getSelectedRow();

            if (filaSeleccionada >= 0) {
                String observacionCompleta = obtenerObservacionCompletaDesdeFila(filaSeleccionada);
                vista.getTxtDetalleObservacion().setText(observacionCompleta);
            }
        }
    }
    
    private String obtenerObservacionCompletaDesdeFila(int filaSeleccionada) {
        ArrayList<Gasto> lista = gastoDAO.obtenerGastos();

        if (filaSeleccionada >= 0 && filaSeleccionada < lista.size()) {
            String obs = lista.get(filaSeleccionada).getObservaciones();

            if (obs == null || obs.trim().isEmpty()) {
                return "Este gasto no contiene observaciones.";
            }

            return obs;
        }

        return "";
    }
    
    private void actualizarPresupuestoInicial() {
        try {
            if (presupuestoActual == null) {
                JOptionPane.showMessageDialog(vista, "No existe un presupuesto registrado.");
                return;
            }

            String textoSaldo = vista.getTxtSaldoInicialEditable().getText().trim();

            if (textoSaldo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese el nuevo saldo inicial.");
                return;
            }

            double nuevoSaldo = Double.parseDouble(textoSaldo);

            if (nuevoSaldo < 0) {
                JOptionPane.showMessageDialog(vista, "El saldo inicial no puede ser negativo.");
                return;
            }

            boolean actualizado = presupuestoDAO.actualizarSaldoInicial(
                    presupuestoActual.getIdPresupuesto(),
                    nuevoSaldo
            );

            if (actualizado) {
                vista.getTxtSaldoInicialEditable().setText("");
                cargarPresupuesto();
                actualizarResumen();

                JOptionPane.showMessageDialog(vista, "Presupuesto actualizado correctamente ✔");
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar el presupuesto.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingrese un valor numérico válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error:\n" + ex.getMessage());
        }
    }
}