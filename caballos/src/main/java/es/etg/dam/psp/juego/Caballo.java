package es.etg.dam.psp.juego;

import java.net.Socket;

import es.etg.dam.psp.utilidades.Utilidades;

public class Caballo implements Runnable {
    private String nombre;
    private int puntos = 0;
    private Socket conexion;
    private Partida partida;

    public Caballo(String nombre, Socket conexion, Partida partida) {
        this.nombre = nombre;
        this.conexion = conexion;
        this.partida = partida;
    }

    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void run() {
        try {
            Utilidades.enviar("OK", conexion);
            partida.empezarPartida();
            Thread.sleep(2000);
            while (partida.isPartidaEnCurso()) {
                Thread.sleep((int) (Math.random() * 300)); 
                int avance = partida.avanzar(this);
                if (avance > 0) Utilidades.enviar(avance, conexion);
                
            }
            String mensaje = "GAME-OVER";
            if (puntos >= partida.MAX_PUNTOS) {
                mensaje = "ENHORABUENA";
            }
            String puntosFinales = "Puntos: "+puntos;
            Utilidades.enviar(puntosFinales,conexion);
            Utilidades.enviar(mensaje, conexion);

        } catch (Exception ex) {
        }

    }
}
