package es.ies.puerto;

/**
 * Clase que representa al monstruo
 */
public class Monstruo {
    private final String nombre;
    private int[] ubicacion;

    /**
     * Constructor con el nombre del monstruo
     * @param nombre del monstruo
     */
    public Monstruo(String nombre) {
        this.nombre = nombre;
    }

    // GETTER Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public int[] getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int[] ubicacion) {
        this.ubicacion = ubicacion;
    }
}