package modelo;

public class Resultado {

    private int idResultado;
    private String descripcionResultado;

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getDescripcionResultado() {
        return descripcionResultado;
    }

    public void setDescripcionResultado(String descripcionResultado) {
        this.descripcionResultado = descripcionResultado;
    }

    @Override
    public String toString() {
        return descripcionResultado;
    }
}