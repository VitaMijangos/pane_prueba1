package modelo;

public class ModoPrueba {

    private int idModo;
    private String nombreModo;

    public int getIdModo() {
        return idModo;
    }

    public void setIdModo(int idModo) {
        this.idModo = idModo;
    }

    public String getNombreModo() {
        return nombreModo;
    }

    public void setNombreModo(String nombreModo) {
        this.nombreModo = nombreModo;
    }

    @Override
    public String toString() {
        return nombreModo;
    }
}