package com.company;

public class Tablero {
    private final int numColumnas = 8;
    private final int numFilas = 7;
    private final String[][] tablero = new String[numFilas][numColumnas];

    private  boolean finJuego = false;
    private final String simbolTapat = "Ø";


    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "Ø";
            }
        }
    }
}
