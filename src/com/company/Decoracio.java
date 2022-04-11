package com.company;

public class Decoracio {

    String caracter1J;
    String caracter2J;
    String jugador1J;
    String jugador2J;

    public static void titol() {
        System.out.println("\033[31m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m\033[34m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m");
        System.out.println("\033[31m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m \033[31mCONE\u001B[0m\033[34mCTA4\u001B[0m \033[34m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m");
        System.out.println("\033[31m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m\033[34m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m");
        System.out.println("\033[31m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m \033[31mHECHO POR \u001B[0m\033[34mVOLKAN :)!\u001B[0m \033[34m‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡\u001B[0m");
        System.out.println();
        System.out.println("================================================");
        System.out.println("       1.- \033[32mJugador 1\u001B[0m vs \033[36mJugador 2\u001B[0m");
        System.out.println("       2.- \033[32mJugador 1\u001B[0m vs \033[33mOrdenador (FACIL)\u001B[0m");
        System.out.println("  ============================================");
        System.out.println("=== ¡ESCRIBE LA MODALIDAD QUE QUIERES JUGAR! ===");
        System.out.println("  ============================================");
    }

    public String cambiarColorCaracteres1 (String caracter1){
        caracter1J = caracter1;
        caracter1J= "\033[31m" + caracter1J + "\u001B[0m";
        return caracter1J;
    }
    public String cambiarColorCaracteres2 (String caracter2){
        caracter2J = caracter2;
        caracter2J = "\033[34m" + caracter2J + "\u001B[0m";
        return caracter2J;
    }
    public String cambiarColorNombres1 (String jugador1) {
        jugador1J = jugador1;
        jugador1J = "\033[32m" + jugador1J + "\u001B[0m";
       return jugador1J;
    }
    public String cambiarColorNombres2 (String jugador2) {
        jugador2J = jugador2;
        jugador2J = "\033[36m" + jugador2J + "\u001B[0m";
        return jugador2J;
    }


    public String getCaracter1J() {
        return caracter1J;
    }

    public String getCaracter2J() {
        return caracter2J;
    }

    public String getJugador1J() {
        return jugador1J;
    }

    public String getJugador2J() {
        return jugador2J;
    }
}
