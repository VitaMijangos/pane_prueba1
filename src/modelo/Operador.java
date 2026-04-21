package modelo;

public class Operador {

    private int idOperador;
    private String nombreOperador;

    public Operador() {
    }

    public Operador(int idOperador, String nombreOperador) {
        this.idOperador = idOperador;
        this.nombreOperador = nombreOperador;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public String getNombreOperador() {
        return nombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        this.nombreOperador = nombreOperador;
    }

    @Override
    public String toString() {
        return nombreOperador;
    }
}