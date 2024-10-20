package es.ies.puerto;

import java.util.List;
import java.util.Random;

/**
 * Clase que representa a los cazadores de monstruos
 */
public class Cazador implements Runnable{
    private final String nombre;
    private final Mapa mapa;
    private final Cueva cueva;
    private final List<Monstruo> monstruos;
    private final Random rand = new Random();
    private int monstruosAtrapados = 0;

    public Cazador(String nombre, Mapa mapa, Cueva cueva, List<Monstruo> monstruos) {
        this.nombre = nombre;
        this.mapa = mapa;
        this.cueva = cueva;
        this.monstruos = monstruos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(rand.nextInt(3000));
                synchronized (monstruos) {
                    if (monstruos.isEmpty()) {
                        System.out.println(nombre + " ha terminado la caza. Monstruos atrapados: " + monstruosAtrapados);
                        return;
                    }
                    for (int i = 0; i < monstruos.size(); i++) {
                        Monstruo monstruo = monstruos.get(i);
                        if (monstruo.isCapturable()) {
                            System.out.println(nombre + " ha atrapado a " + monstruo.getNombre() + "!");
                            monstruos.remove(i);
                            monstruosAtrapados++;
                            break;
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(nombre + " ha terminado la caza. Monstruos atrapados: " + monstruosAtrapados);
                return;
            }
        }
    }
}
