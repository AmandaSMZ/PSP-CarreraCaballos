package es.etg.dam.psp.juego;

public class Partida {
    public static final int MAX_CABALLOS = 4;
    public static final int MAX_PUNTOS = 100;
    private Boolean partidaEnCurso = true;
    private int caballosRegistrados = 0;

    public synchronized void empezarPartida() throws InterruptedException{
        while (caballosRegistrados < MAX_CABALLOS){
            wait();
        }
        notifyAll();
    }

    public synchronized void comprobarGanador(Caballo caballo){
        if (caballo.getPuntos() >= MAX_PUNTOS){
            partidaEnCurso = false;
        }
    }
    public int getCaballosRegistrados(){
        return caballosRegistrados;
    }
}
