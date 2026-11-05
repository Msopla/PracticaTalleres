package modelo;

public class Inscripcion {
    private final String identificacion;
    private final String nombre;
    private final String taller;

    public Inscripcion(String identificacion, String nombre, String taller) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.taller = taller;
    }

    public String getIdentificacion() { return identificacion; }
    public String getNombre() { return nombre; }
    public String getTaller() { return taller; }
}
