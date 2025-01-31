package es.etg.dam.psp.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import es.etg.dam.psp.utilidades.Utilidades;

public class Cliente {
    static final String HOST = "localhost";
    static final String MENSAJE_INTRODUCE_NOMBRE = "Introduce el nombre del caballo";
    static final String MENSAJE_RECIBIDO = "Recibido: ";

    public static void main(String[] args) throws IOException, InterruptedException {
            Scanner teclado = new Scanner(System.in);
            System.out.println(MENSAJE_INTRODUCE_NOMBRE);
            String nombre = teclado.next();
            Socket cliente = new Socket(HOST, Utilidades.PUERTO);
            Utilidades.enviar(nombre, cliente);
            
            System.out.println(MENSAJE_RECIBIDO + Utilidades.recibir(cliente));
            String mensaje;
            do { 
                mensaje = Utilidades.recibir(cliente);
                System.out.println(mensaje);
            } while (!mensaje.equals(Utilidades.MENSAJE_GANADOR) && (!mensaje.equals(Utilidades.MENSAJE_PERDEDOR)));
            cliente.close();

    }

}
