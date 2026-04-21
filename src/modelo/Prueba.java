package modelo;

public class Prueba {

    private int idPrueba;
    private String fecha;
    private int velocidadPWM;
    private String bateria;
    private String tiempoRespuesta;
    private String observaciones;

    private int idOperador;
    private int idModo;
    private int idMovimiento;
    private int idResultado;

    // Campos descriptivos para mostrar en historial
    private String nombreOperador;
    private String nombreModo;
    private String nombreMovimiento;
    private String nombreResultado;

    public Prueba() {
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getVelocidadPWM() {
        return velocidadPWM;
    }

    public void setVelocidadPWM(int velocidadPWM) {
        this.velocidadPWM = velocidadPWM;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(String tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public int getIdModo() {
        return idModo;
    }

    public void setIdModo(int idModo) {
        this.idModo = idModo;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getNombreOperador() {
        return nombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        this.nombreOperador = nombreOperador;
    }

    public String getNombreModo() {
        return nombreModo;
    }

    public void setNombreModo(String nombreModo) {
        this.nombreModo = nombreModo;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public String getNombreResultado() {
        return nombreResultado;
    }

    public void setNombreResultado(String nombreResultado) {
        this.nombreResultado = nombreResultado;
    }
}