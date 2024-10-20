package es.ies.puerto;


import java.util.concurrent.Semaphore;

public class Cueva {
    private final Semaphore semaforo;

    public Cueva(int capacidad) {
        this.semaforo = new Semaphore(capacidad);
    }

    public void entrar(String monstruo) throws InterruptedException {
        semaforo.acquire();
        System.out.println(monstruo + " ha entrado en la cueva.");
    }

    public void salir(String monstruo) {
        System.out.println(monstruo + " ha salido de la cueva.");
        semaforo.release();
    }
}

