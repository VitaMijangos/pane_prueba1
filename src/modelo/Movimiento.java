package modelo;

public class Movimiento {

    private int idMovimiento;
    private String nombreMovimiento;

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    @Override
    public String toString() {
        return nombreMovimiento;
    }
}