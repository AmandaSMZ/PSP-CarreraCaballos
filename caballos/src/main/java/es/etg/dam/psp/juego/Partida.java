package es.etg.dam.psp.juego;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Partida {
    public static final int MAX_CABALLOS = 4;
    public static final int MAX_PUNTOS = 50;
    private Boolean partidaEnCurso = true;
    private int caballosRegistrados = 0;
    private boolean turnoActivo = false;

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock r = lock.readLock(); 
    private Lock w = lock.writeLock(); 

    public synchronized void empezarPartida() throws InterruptedException{
        while (caballosRegistrados < MAX_CABALLOS){
            wait();
        }
        notifyAll();
    }

    public synchronized int avanzar(Caballo caballo) throws InterruptedException{
        while(turnoActivo){
            wait();
        }
        if (!partidaEnCurso) return -1;
        turnoActivo = true;
        int puntos = aleatorio();
        caballo.setPuntos(puntos);
        comprobarGanador(caballo);
        turnoActivo = false;
        notify();
        return puntos;
        
    }

    private boolean comprobarGanador(Caballo caballo) throws InterruptedException{
        if (caballo.getPuntos() >= MAX_PUNTOS && partidaEnCurso){
            partidaEnCurso = false;
            return true;
            
        }
        return  false;
    }
    public int getCaballosRegistrados(){
        return caballosRegistrados;
    }
    public void setCaballosRegistrados(){
        caballosRegistrados++;
    }
    public boolean isPartidaEnCurso(){
        r.lock();
        try {
            return partidaEnCurso;
        } finally {
            r.unlock();
        }
    }

    private static int aleatorio() {
        return (int) (Math.random() * 10) + 1;
    }
}
