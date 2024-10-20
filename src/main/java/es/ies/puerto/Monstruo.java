package es.ies.puerto;

import java.util.Random;

/**
 * Clase que representa al monstruo
 */
public class Monstruo implements Runnable{
    private final String nombre;
    private final Cueva cueva;
    private final Random rand = new Random();
    private boolean capturable = false;
    private volatile boolean activo = true; 

    public Monstruo(String nombre, Cueva cueva) {
        this.nombre = nombre;
        this.cueva = cueva;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isCapturable() {
        return capturable;
    }

    public void setCapturable(boolean capturable) {
        this.capturable = capturable;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public void run() {
        try {
            while (activo) {
                cueva.entrar(nombre);
                Thread.sleep(rand.nextInt(5000));
                cueva.salir(nombre);
                setCapturable(true);
                Thread.sleep(rand.nextInt(2000));
            }
            System.out.println(nombre + " ha terminado.");
        } catch (InterruptedException e) {
            System.out.println(nombre + " ha terminado.");
        }
    }
}