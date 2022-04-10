package com.company;

public class Main {

    public static void main(String[] args) {
	InputOutput inputOutput = new InputOutput();

        Tablero tablero = new Tablero();
        tablero.imprimirTablero();
        inputOutput.juego();
    }
}
