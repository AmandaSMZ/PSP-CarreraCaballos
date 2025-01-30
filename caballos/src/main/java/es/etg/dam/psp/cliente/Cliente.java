package es.etg.dam.psp.cliente;

import java.io.IOException;
import java.net.Socket;

import es.etg.dam.psp.Utilidades;

public class Cliente {
    static final int PUERTO = 8880;
    static final String HOST = "localhost";

    public static void main(String[] args) throws IOException, InterruptedException {

            Thread.sleep(2000);
            Socket cliente = new Socket(HOST, PUERTO);
            Utilidades.enviar("Juan", cliente);
            
            System.out.println("Recibido: "+Utilidades.recibir(cliente));
            String mensaje;
            do { 
                mensaje = Utilidades.recibir(cliente);
                System.out.println(mensaje);
            } while (!mensaje.equals("ENHORABUENA") && (!mensaje.equals("GAME-OVER")));
            cliente.close();

    }

}
