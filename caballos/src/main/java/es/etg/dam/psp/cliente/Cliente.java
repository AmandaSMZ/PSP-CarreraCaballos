package es.etg.dam.psp.cliente;

import java.io.IOException;
import java.net.Socket;

import es.etg.dam.psp.Utilidades;

public class Cliente {
    static final int PUERTO = 8888;
    static final String HOST = "localhost";

    public static void main(String[] args) throws IOException, InterruptedException {

        while (true) {
            Socket cliente = new Socket(HOST, PUERTO);
            Utilidades.enviar("Juan", cliente);
            Thread.sleep(2000);

            Utilidades.recibir(cliente);
            cliente.close();
        }

    }

}
