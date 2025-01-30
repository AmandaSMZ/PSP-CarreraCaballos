package es.etg.dam.psp.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import es.etg.dam.psp.Utilidades;
import es.etg.dam.psp.juego.Caballo;
import es.etg.dam.psp.juego.Partida;

public class Servidor {
    public static final int PUERTO = 8880;
    public static boolean juegoActivo = true;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PUERTO);
        Partida partida = new Partida();


        while (partida.getCaballosRegistrados() < Partida.MAX_CABALLOS){
            System.out.println("escuchando");
            Socket cliente = server.accept();
            
            String nombre = Utilidades.recibir(cliente);
            partida.setCaballosRegistrados();
            Thread hilo = new Thread(new Caballo(nombre,cliente, partida));
            hilo.start();
        }

}
}
