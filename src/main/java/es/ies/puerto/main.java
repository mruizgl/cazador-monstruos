package es.ies.puerto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class main {
    /**
     *Metodo main para ejecutar el programa
     * @param args del main
     * @throws InterruptedException exception
     */
    public static void main(String[] args) {
        int numMonstruos = 5;
        int numCazadores = 3;
        int capacidadCueva = numMonstruos / 2;

        Cueva cueva = new Cueva(capacidadCueva);
        Mapa mapa = new Mapa(10);
        List<Monstruo> monstruos = new ArrayList<>();
        List<Cazador> cazadores = new ArrayList<>();

        ExecutorService pool = Executors.newFixedThreadPool(numCazadores + numMonstruos);

        for (int i = 0; i < numMonstruos; i++) {
            Monstruo monstruo = new Monstruo("Monstruo" + (i + 1), cueva);
            monstruos.add(monstruo);
            pool.execute(monstruo);
        }

        for (int i = 0; i < numCazadores; i++) {
            Cazador cazador = new Cazador("Cazador" + (i + 1), mapa, cueva, monstruos);
            cazadores.add(cazador);
            pool.execute(cazador);
        }

        new Thread(() -> {
            while (true) {
                synchronized (monstruos) {
                    if (monstruos.isEmpty()) {
                        System.out.println("Todos los monstruos han sido cazados. El juego ha terminado.");
                        for (Monstruo monstruo : monstruos) {
                            monstruo.setActivo(false);
                        }
                        pool.shutdownNow(); //
                        return;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }


}