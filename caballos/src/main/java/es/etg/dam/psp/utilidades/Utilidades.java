package es.etg.dam.psp.utilidades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Utilidades {
    public final String MENSAJE_GANADOR = "ENHORABUENA";
    public final String MENSAJE_PERDEDOR = "GAME_OVER";

    public static synchronized void enviar(String mensaje, Socket cliente) throws IOException {

        DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
        byte[] mensajeBytes = mensaje.getBytes("UTF-8");
        output.writeInt(mensajeBytes.length);
        output.write(mensajeBytes);
    }

    public static synchronized void enviar(int numero, Socket cliente) throws IOException {
        String numeroString = String.valueOf(numero);
        enviar(numeroString, cliente);
    }

    public static synchronized String recibir(Socket cliente) throws IOException {
        
        InputStream aux = cliente.getInputStream();
        DataInputStream input = new DataInputStream(aux);
        int longitud = input.readInt();
        byte[] mensajeBytes = new byte[longitud];
        input.readFully(mensajeBytes);

        return new String(mensajeBytes, "UTF-8");
    }

}
