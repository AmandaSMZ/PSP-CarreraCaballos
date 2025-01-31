package es.etg.dam.psp.juego;

import java.net.Socket;

public class Caballo {
    private Socket conexion;
    private int puntos = 0;
    private String nombre;

    public Caballo(String nombre, Socket conexion) {
        this.nombre = nombre;
        this.conexion = conexion;
    }

    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public String getNombre() {
        return this.nombre;
    }
    public Socket getConexion(){
        return conexion;
    }

}
