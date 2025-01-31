package es.etg.dam.psp.utilidades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Utilidades {
    public final static String CHARSET = "UTF-8";
    public final static String MENSAJE_GANADOR = "ENHORABUENA";
    public final static String MENSAJE_PERDEDOR = "GAME-OVER";
    public final static String MENSAJE_OK = "OK";
    public final static int MAX_CABALLOS = 4;
    public static int PUERTO = 8880;

    public static synchronized void enviar(String mensaje, Socket cliente) throws IOException {

        DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
        byte[] mensajeBytes = mensaje.getBytes(CHARSET);
        output.writeInt(mensajeBytes.length);
        output.write(mensajeBytes);
    }

    public static void enviar(int numero, Socket cliente) throws IOException {
        String numeroString = String.valueOf(numero);
        enviar(numeroString, cliente);
    }

    public static String recibir(Socket cliente) throws IOException {
        
        InputStream aux = cliente.getInputStream();
        DataInputStream input = new DataInputStream(aux);
        int longitud = input.readInt();
        byte[] mensajeBytes = new byte[longitud];
        input.readFully(mensajeBytes);

        return new String(mensajeBytes, CHARSET);
    }

}
