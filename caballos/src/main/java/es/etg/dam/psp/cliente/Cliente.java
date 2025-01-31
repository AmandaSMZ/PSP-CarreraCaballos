package es.etg.dam.psp.cliente;

import java.io.IOException;
import java.net.Socket;

import es.etg.dam.psp.utilidades.Utilidades;

public class Cliente {
    public static final String HOST = "localhost";
    public static final String MENSAJE_RECIBIDO = "Recibido: ";
    public static final String NOMBRE_CABALLO = "Caballo-";

    public static void main(String[] args) throws IOException, InterruptedException {
        long tiempo = System.currentTimeMillis() % 10000;
        String nombre = NOMBRE_CABALLO + tiempo;
        try (Socket cliente = new Socket(HOST, Utilidades.PUERTO)) {
            Utilidades.enviar(nombre, cliente);
            
            System.out.println(MENSAJE_RECIBIDO + Utilidades.recibir(cliente));
            String mensaje;
            do { 
                mensaje = Utilidades.recibir(cliente);
                System.out.println(mensaje);
            } while (!mensaje.equals(Utilidades.MENSAJE_GANADOR) && (!mensaje.equals(Utilidades.MENSAJE_PERDEDOR)));
        }

    }

}
