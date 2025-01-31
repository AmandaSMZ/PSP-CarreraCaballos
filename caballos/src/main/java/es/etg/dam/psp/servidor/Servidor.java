package es.etg.dam.psp.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import es.etg.dam.psp.juego.Caballo;
import es.etg.dam.psp.juego.Partida;
import es.etg.dam.psp.utilidades.Utilidades;

public class Servidor {
    public final static String MENSAJE_INICIADO = "Servidor iniciado en el puerto ";
    public final static String MENSAJE_CLIENTE_CONECTADO = "Cliente conectado: ";
    public final static String MENSAJE_OK = "OK";

    public static void main(String[] args) throws IOException, InterruptedException {
        Partida partida = new Partida();

        try (ServerSocket servidor = new ServerSocket(Utilidades.PUERTO)) {
            System.out.println(MENSAJE_INICIADO + Utilidades.PUERTO);

            while (partida.getCaballosRegistrados() < Utilidades.MAX_CABALLOS) {
                Socket socket = servidor.accept();
                String nombre = Utilidades.recibir(socket);
                System.out.println(MENSAJE_CLIENTE_CONECTADO + nombre);
                partida.aniadir(new Caballo(nombre, socket));
                Utilidades.enviar(MENSAJE_OK, socket);
            }

        }
        partida.empezarPartida();

    }
}
