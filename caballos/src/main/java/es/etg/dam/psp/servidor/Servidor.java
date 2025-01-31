package es.etg.dam.psp.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import es.etg.dam.psp.juego.Caballo;
import es.etg.dam.psp.juego.Partida;
import es.etg.dam.psp.utilidades.Utilidades;

public class Servidor {
    public final static String MENSAJE_INICIADO = "Servidor iniciado en el puerto ";
    public final static String MENSAJE_CLIENTE_CONECTADO = "Cliente conectado: ";
    private final static String MENSAJE_CABALLO_GANADOR = "El ganador ha sido: ";
    

    public static void main(String[] args) throws IOException, InterruptedException {
        Partida partida = new Partida();

        try (ServerSocket servidor = new ServerSocket(Utilidades.PUERTO)) {
            System.out.println(MENSAJE_INICIADO + Utilidades.PUERTO);

            while (partida.getCaballosRegistrados() < Utilidades.MAX_CABALLOS) {
                Socket socket = servidor.accept();
                registrarCaballo(socket, partida);
                Utilidades.enviar(Utilidades.MENSAJE_OK, socket);
            }
        }

        Thread.sleep(2000);
        while (partida.getCaballoGanador() == null){
            jugarTurno(partida);
        }

        System.out.println(MENSAJE_CABALLO_GANADOR+partida.getCaballoGanador().getNombre());
        enviarMensajeFinal(partida.getCaballos(),partida.getCaballoGanador());
    }

    public static void registrarCaballo(Socket conexion, Partida partida) throws IOException{
        String nombre = Utilidades.recibir(conexion);
        System.out.println(MENSAJE_CLIENTE_CONECTADO + nombre);
        partida.aniadir(new Caballo(nombre, conexion));
    }

    public static void jugarTurno(Partida partida) throws InterruptedException, IOException{
        Caballo caballoTurno = partida.getTurnoCaballo();
        int puntos = partida.avanzar(caballoTurno);
        Utilidades.enviar(puntos, caballoTurno.getConexion());
    }

    public static void enviarMensajeFinal(List<Caballo> caballos, Caballo caballoGanador) throws IOException {
        for (Caballo cliente : caballos) {
            if (cliente == caballoGanador) {
                Utilidades.enviar(Utilidades.MENSAJE_GANADOR, cliente.getConexion());
            } else {
                Utilidades.enviar(Utilidades.MENSAJE_PERDEDOR, cliente.getConexion());
            }
            cliente.getConexion().close();
        }
    }
}
