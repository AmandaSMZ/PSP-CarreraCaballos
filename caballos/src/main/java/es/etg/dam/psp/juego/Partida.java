package es.etg.dam.psp.juego;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.etg.dam.psp.utilidades.Utilidades;

public class Partida {
    private static final int MAX_PUNTOS_TURNO = 10;
    public static final int MAX_CABALLOS = 4;
    public static final int MAX_PUNTOS = 50;
    private Boolean partidaEnCurso = true;
    private List<Caballo> caballos = new ArrayList<>();

    public synchronized void empezarPartida() throws InterruptedException, IOException {
        while (partidaEnCurso) {
            avanzar();
        }
    }

    public void avanzar() throws InterruptedException, IOException {
        while (partidaEnCurso) {
            Thread.sleep(200);
            int puntos = numeroAleatorio(MAX_PUNTOS_TURNO);
            Caballo caballoTurno = caballos.get(numeroAleatorio(caballos.size()));
            Utilidades.enviar(puntos, caballoTurno.getConexion());
            caballoTurno.setPuntos(puntos);
            if (comprobarGanador(caballoTurno)) {
                partidaEnCurso = false;
                enviarMensajeFinal(caballoTurno);
            }
        }
    }

    private void enviarMensajeFinal(Caballo caballo) throws IOException {
        for (Caballo cliente : caballos) {
            if (cliente == caballo) {
                Utilidades.enviar(Utilidades.MENSAJE_GANADOR, cliente.getConexion());
            } else {
                Utilidades.enviar(Utilidades.MENSAJE_PERDEDOR, cliente.getConexion());
            }
            cliente.getConexion().close();
        }
    }

    private boolean comprobarGanador(Caballo caballo) throws InterruptedException {
        if (caballo.getPuntos() >= MAX_PUNTOS) {
            return true;

        }
        return false;
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

}
