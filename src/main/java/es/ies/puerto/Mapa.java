package es.ies.puerto;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que representa el mapa del jueo
 */
public class Mapa {
    private final int size;
    private final ConcurrentHashMap<String, int[]> ubicaciones;

    /**
     * Constructor con el tamaño del mapa
     * @param size del mapa
     */
    public Mapa(int size) {
        this.size = size;
        this.ubicaciones = new ConcurrentHashMap<>();
    }

    /**
     * Metodo para generar posición aleatoria del monstruo
     * @return posicion
     */
    public int[] generarUbicacion() {
        Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        return new int[]{x, y};
    }


    /**
     * Metodo que mueve al personaje por el mapa
     * @param nombre
     * @param nuevaUbicacion
     */
    public void moverPersonaje(String nombre, int[] nuevaUbicacion) {
        ubicaciones.put(nombre, nuevaUbicacion);
    }

    /**
     * Determina si hay un encuentro de cazador-monstruo en el mapa
     * @param nombreCazador cazador
     * @param nombreMonstruo monstruo
     * @return verdadero o falso
     */
    public boolean hayEncuentro(String nombreCazador, String nombreMonstruo) {
        int[] ubicacionCazador = ubicaciones.get(nombreCazador);
        int[] ubicacionMonstruo = ubicaciones.get(nombreMonstruo);

        return ubicacionCazador != null && ubicacionMonstruo != null
                && ubicacionCazador[0] == ubicacionMonstruo[0]
                && ubicacionCazador[1] == ubicacionMonstruo[1];
    }

    /**
     * Metodo para mostrar el mapa
     */
    public void mostrarMapa() {
        for (String nombre : ubicaciones.keySet()) {
            int[] ubicacion = ubicaciones.get(nombre);
            System.out.println(nombre + " está en (" + ubicacion[0] + ", " + ubicacion[1] + ")");
        }
        System.out.println();
    }
}