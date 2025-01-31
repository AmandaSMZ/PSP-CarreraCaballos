package es.etg.dam.psp.juego;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
    private static final int MAX_PUNTOS_TURNO = 10;
    private static final int MAX_PUNTOS = 100;
    private Caballo caballoGanador = null;
    private List<Caballo> caballos = new ArrayList<>();

    public int avanzar(Caballo caballo) throws InterruptedException, IOException {
            Thread.sleep(200);
            int puntos = numeroAleatorio(MAX_PUNTOS_TURNO);
            caballo.setPuntos(puntos);
            comprobarGanador(caballo);
            return puntos;
    }

    private void comprobarGanador(Caballo caballo) throws InterruptedException {
        if (caballo.getPuntos() >= MAX_PUNTOS) setCaballoGanador(caballo);
    }

    public int getCaballosRegistrados() {
        return caballos.size();
    }

    public void aniadir(Caballo caballo) {
        caballos.add(caballo);
    }

    private int numeroAleatorio(int maximo) {
        Random random = new Random();
        return random.nextInt(maximo);
    }
    public Caballo getTurnoCaballo(){
        return caballos.get(numeroAleatorio(caballos.size()));
    }
    public Caballo getCaballoGanador(){
        return caballoGanador;
    }
    public void setCaballoGanador(Caballo caballo){
        caballoGanador = caballo;
    }
    public List<Caballo> getCaballos(){
        return caballos;
    }

}
