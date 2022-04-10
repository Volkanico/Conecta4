package com.company;

import java.util.Scanner;

public class InputOutput {
    int modoDeJuego = 0;
    Scanner lector = new Scanner(System.in);
    boolean verificador = false;
    boolean verificador2 = false;
    String mensajeError = "";
    String jugador1;
    String caracter1;
    String jugador2;
    String caracter2;
    int jugadasMaximas = 1;
    int columna = 0;
    int contador = 0;
    String auxiliarJugador;

    Tablero tablero = new Tablero();
    public void titol () {
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

    public void comprobarEntradaDificultad (){
        boolean verificador = false;
        while (!verificador) {
            try {
                String auxiliar = lector.nextLine();
                modoDeJuego = Integer.parseInt(auxiliar);
                if (modoDeJuego == 1 || modoDeJuego == 2) {
                    verificador = true;
                } else {
                    System.out.println("\033[35m**ERROR:\u001B[0m Debe ingresar 1 o 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("\033[35m**ERROR:\u001B[0m Debe ingresar 1 o 2");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        verificador = false;

    }

    public void opcionesDeJuego () {
        if (modoDeJuego == 1) {
            opcionJugadorContraJugador();
        }
    }

        public void opcionJugadorContraJugador () {
            System.out.println("============================================");
            System.out.println("MODO SELECCIONADO:");
            System.out.println("             \033[32mJugador 1\u001B[0m vs \033[36mJugador 2\u001B[0m");
            //datos Jugador 1
            System.out.println("\033[32mJugador 1\u001B[0m ingrese un nick");
            jugador1 = lector.nextLine();
            if (jugador1.equals(null) || jugador1.equals("")) {
                jugador1 = "Jugador 1";
            }

            jugador1 = "\033[32m" + jugador1 + "\u001B[0m";

            System.out.println(jugador1 + " ingrese un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
            caracter1 = lector.nextLine();
            posiblesErroresEnCaracterDeJugador1();
            caracter1 = caracter1.toUpperCase();
            System.out.println("======================================================================================");

            //datos Jugador 2
            System.out.println("\033[36mJugador 2\u001B[0m ingrese un nick");
            jugador2 = lector.nextLine();
            while (jugador2.equals(jugador1)) {
                System.out.println("\033[35m**ERROR:\u001B[0m El jugador 1 ya ocupo este nick, ingreso otro");
                jugador2 = lector.nextLine();
            }
            if (jugador2.equals(null) || jugador2.equals("")) {
                jugador2 = "Jugador 2";
            }

            jugador2 = "\033[36m" + jugador2 + "\u001B[0m";

            System.out.println(jugador2 + " ingrese un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
            caracter2 = lector.nextLine();
            posiblesErroresEnCaracterDeJugador2();
            caracter2 = caracter2.toUpperCase();
            cambiarColorNombres();

            while (!verificador) {
                tablero.mostrarTablero(tablero.getTablero());
                tablero.mostrarInterfazTablero(jugador1,caracter1, jugador2,caracter2,jugadasMaximas);
                System.out.println(mensajeError);
                mensajeError = "";
                partidaTurnos();
            }

        }


        public void controlErrorColumnasJugador1YsuJuego (){
            try {
                System.out.println(jugador1 + " escriba el numero de columna para poner su ficha:");
                String auxiliar = lector.nextLine();
                columna = Integer.parseInt(auxiliar) - 1;
                if (columna > tablero.getCapacidadColumnas() - 1 || tablero.getCapacidadColumnas() < 0) {
                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + tablero.getCapacidadColumnas();
                } else {
                    contador = 1;
                    for (int i = tablero.getCapacidadColumnas() - 1; i > -1; i = i - 1) {
                        if (!verificador2) {
                            //Si se cumple esta condicion, termina el turno del jugador 1
                            if (Tablero.tablero[i][columna] != caracter1 && Tablero.tablero[i][columna] != caracter2) {
                                Tablero.tablero[i][columna] = caracter1;
                                verificador2 = true;
                                verificador = true;
                                jugadasMaximas++;
                            } else {
                                contador += 1;
                            }
                        }
                        if (contador == tablero.getCapacidadFilas() + 1) {
                            mensajeError = "\n\033[35m**ERROR:\u001B[0m Esta columna esta completa, escoja otra";
                        }
                    }
                }
            } catch (NumberFormatException e) {
                mensajeError = "\n\033[35m**ERROR:\u001B[0m Ha ingresado un caracter no valido, debe ingresar un NUMERO";
            } catch (Exception e) {
                mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + tablero.getCapacidadColumnas();
            }
            verificador2 = false;
            verificador = false;
            tablero.verificadorGanador(jugador1, caracter1);

            if (jugadasMaximas == (tablero.getCapacidadFilas() * tablero.getCapacidadColumnas()) + 1) {
                tablero.setFinJuego(true);
                tablero.mostrarTablero(tablero.getTablero());
                System.out.println("======================");
                System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
                System.out.println("El tablero esta lleno");
                System.out.println("======================");
            }
        if (!tablero.isFinJuego()) {

            //turno jugador 2
            while (!verificador) {
                tablero.mostrarTablero(tablero.getTablero());
                tablero.mostrarInterfazTablero(jugador1, caracter1, jugador2, caracter2, jugadasMaximas);
                System.out.println(mensajeError);
                mensajeError = "";
                try {
                    System.out.println(jugador2 + " escriba el numero de columna para poner su ficha:");
                    String auxiliar = lector.nextLine();
                    columna = Integer.parseInt(auxiliar) - 1;
                    if (columna > tablero.getCapacidadColumnas() - 1 || columna < 0) {
                        mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + tablero.getCapacidadColumnas();
                    } else {
                        contador = 1;
                        for (int i = tablero.getCapacidadColumnas() - 1; i > -1; i = i - 1) {
                            if (!verificador2) {
                                //Si se cumple esta condicion, termina el turno del jugador 2
                                if (Tablero.tablero[i][columna] != caracter2 && Tablero.tablero[i][columna] != caracter1) {
                                    Tablero.tablero[i][columna] = caracter2;
                                    verificador2 = true;
                                    verificador = true;
                                    jugadasMaximas++;
                                } else {
                                    contador += 1;
                                }
                            }
                            if (contador == tablero.getCapacidadColumnas() + 1) {
                                mensajeError = "\n\033[35m**ERROR:\u001B[0m Esta columna esta completa, escoja otra";
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Ha ingresado un caracter no valido, debe ingresar un NUMERO";
                } catch (Exception e) {
                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + tablero.getCapacidadColumnas();
                }
            }
            verificador2 = false;
            verificador = false;
            auxiliarJugador = jugador2;
            auxiliarJugador = caracter2;
            //llamo a la funcion "verificarGanador" para controlar si gano el jugador 2
            tablero.verificadorGanador(jugador2, caracter2);

            if (jugadasMaximas == (tablero.getCapacidadFilas() * tablero.getCapacidadColumnas()) + 1) {
                tablero.setTapado(true);
                tablero.mostrarTablero(tablero.getTablero());
                System.out.println("======================");
                System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
                System.out.println("El tablero esta lleno");
                System.out.println("======================");
            }
        }
    }


        public void posiblesErroresEnCaracterDeJugador1 (){
            while (caracter1.length() > 1 || caracter1.equals(null) || caracter1.equals("")) {
                if (caracter1.length() > 1) {       //Si el caracter ingresado es de mas de una letra
                    System.out.println("\033[35m**ERROR:\u001B[0m El caracter debe ser de una sola letra");
                    System.out.println("ejemplo: 'X' , 'O' , 'V' ...");
                    caracter1 = lector.nextLine();
                    caracter1 = caracter1.toUpperCase();
                }
                if (caracter1.equals(null) || caracter1.equals("")) {   //Si el caracter no contiene nada
                    System.out.println("\033[35m**ERROR:\u001B[0m El caracter no puede estar vacio, intente nuevamente");
                    caracter1 = lector.nextLine();
                    caracter1 = caracter1.toUpperCase();
                }
            }
        }

    public void posiblesErroresEnCaracterDeJugador2 (){
        while (caracter2.equals(caracter1) || caracter2.length() > 1 || caracter2.equals(null) || caracter2.equals("")) {
            if (caracter2.equals(null) || caracter2.equals("")) {       //Si el caracter ingresado no contiene nada
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter no puede estar vacio, intente nuevamente");
                caracter2 = lector.nextLine();
                caracter2 = caracter2.toUpperCase();
            }
            if (caracter2.equals(caracter1)) {     //Si el caracter ingresado es igual al del jugador 1
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter es igual al del jugador 1, ingrese otro");
                caracter2 = lector.nextLine();
                caracter2 = caracter2.toUpperCase();
            }
            if (caracter2.length() > 1) {     //Si el caracter ingresado es de mas de una letra
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter debe ser de una sola letra, intente nuevamente");
                caracter2 = lector.nextLine();
                caracter2 = caracter2.toUpperCase();
            }
        }
    }

    public void cambiarColorNombres (){
        caracter1 = "\033[31m" + caracter1 + "\u001B[0m";
        caracter2 = "\033[34m" + caracter2 + "\u001B[0m";
    }

    public void partidaTurnos () {
        controlErrorColumnasJugador1YsuJuego();

    }

    public void juego () {
        titol();
        comprobarEntradaDificultad();
        opcionesDeJuego();
    }
}
