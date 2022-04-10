package com.company;

public class Tablero {
    private static final int numColumnas = 7;
    private static final int numFilas = 7;
    public static String[][] tablero = new String[numFilas][numColumnas];

    private boolean finJuego = false;
    private boolean tapado;
    private final String simbolTapat = "Ø";

    private int capacidadColumnas = tablero[1].length;
    private int capacidadFilas = tablero.length;

    public void mostrarTablero(String[][] tablero) {
        System.out.print("          ");
        for (int i = 1; i < capacidadColumnas + 1; i += 1) {
            System.out.print(i + "   ");
        }
        System.out.println("");
        System.out.print("         ");
        for (int i = 0; i < capacidadColumnas; i += 1) {
            System.out.print("____");
        }
        System.out.println("");
        for (int i = 0; i < capacidadFilas; i++) {
            System.out.print("       ");
            for (int j = 0; j < capacidadFilas; j++) {
                System.out.print(" | " + tablero[i][j]);
            }
            System.out.println(" |");
            if (i < capacidadFilas - 1) {
                System.out.print("        |");
                for (int p = 1; p < capacidadColumnas - 1; p += 1) {
                    System.out.print("––––––");
                }
                System.out.println("|");
            }
        }
        System.out.print("         ");
        for (int i = 1; i < capacidadColumnas + 1; i += 1) {
            System.out.print("¯¯¯¯");
        }
        System.out.println("");
    }

    public void mostrarInterfazTablero(String jugador1, String caracter1, String jugador2, String caracter2, int jugadasMaximas) {
        System.out.println("=================================================================");
        System.out.println(jugador1 + " : " + caracter1 + "      " + jugador2 + " : " + caracter2);
        System.out.print("==============================================||| JUGADA N°" + jugadasMaximas + " |||");
    }


    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "Ø";
            }
        }
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
        }


    }



    public void verificadorGanador(String auxiliarJugador, String auxilarCaracter) {
        //verificacion ganador (horizontal)
        for (int i = 1; i < capacidadFilas; i += 1) {
            for (int j = 0; j < capacidadColumnas - 3; j += 1) {
                if (tablero[i][j].equals(auxilarCaracter) && tablero[i][j + 1].equals(auxilarCaracter) && tablero[i][j + 2].equals(auxilarCaracter) && tablero[i][j + 3].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mHORIZONTALMENTE!!!\u001B[0m");
                }
            }
        }

        //verificacion ganador (vertical)
        for (int i = 0; i < capacidadFilas; i += 1) {
            for (int j = 0; j < capacidadColumnas - 3; j += 1) {
                if (tablero[j][i].equals(auxilarCaracter) && tablero[j + 1][i].equals(auxilarCaracter) && tablero[j + 2][i].equals(auxilarCaracter) && tablero[j + 3][i].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mVERTICALMENTE!!!\u001B[0m");
                }
            }
        }

        //verificacion ganador (diagonal)
        for (int i = 0; i < capacidadColumnas - 4 + 1; i += 1) {
            for (int j = 0; j < capacidadFilas - 4 + 1; j += 1) {
                if (tablero[j][i].equals(auxilarCaracter) && tablero[j + 1][i + 1].equals(auxilarCaracter) && tablero[j + 2][i + 2].equals(auxilarCaracter) && tablero[j + 3][i + 3].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mDIAGONALMENTE!!!\u001B[0m");
                }
            }
        }
        for (int i = capacidadColumnas; i > 3; i -= 1) {
            for (int j = 0; j < capacidadFilas - 3; j += 1) {
                if (tablero[j][i - 1].equals(auxilarCaracter) && tablero[j + 1][i - 2].equals(auxilarCaracter) && tablero[j + 2][i - 3].equals(auxilarCaracter) && tablero[j + 3][i - 4].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mDIAGONALMENTE!!!\u001B[0m");
                }
            }
        }
    }



    public boolean isTapado() {
        return tapado;
    }

    public void setTapado(boolean tapado) {
        this.tapado = tapado;
    }

    public boolean isFinJuego() {
        return finJuego;
    }

    public void setFinJuego(boolean finJuego) {
        this.finJuego = finJuego;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public int getNumColumnas() {
        return numColumnas;
    }

    public int getCapacidadColumnas() {
        return capacidadColumnas;
    }

    public int getCapacidadFilas() {
        return capacidadFilas;
    }

    public String[][] getTablero() {
        return tablero;
    }
}
