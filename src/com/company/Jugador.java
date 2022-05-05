package com.company;

public class Jugador {
    private String jugador;
    private String caracter;

    public Jugador(String jugador, String caracter) {
        this.jugador = jugador;
        this.caracter = caracter;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }
}
