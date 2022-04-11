package com.company;
import java.util.Scanner;

public class InputOutput {
    int modoDeJuego = 0;
    Scanner lector = new Scanner(System.in);
    boolean verificador = false;

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
        if ( jugador1.equals("")) {
            jugador1 = "Jugador 1";
        }
        cambiarColorNombres();


        System.out.println(jugador1 + " ingrese un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
        caracter1 = lector.nextLine();
        posiblesErroresEnCaracterDeJugador1();
        caracter1 = caracter1.toUpperCase();
        cambiarColorCaracteres();
        System.out.println("======================================================================================");

        //datos Jugador 2
        System.out.println("\033[36mJugador 2\u001B[0m ingrese un nick");
        jugador2 = lector.nextLine();
        while (jugador2.equals(jugador1)) {
            System.out.println("\033[35m**ERROR:\u001B[0m El jugador 1 ya ocupo este nick, ingreso otro");
            jugador2 = lector.nextLine();
        }
        if (jugador2.equals("")) {
            jugador2 = "Jugador 2";
        }
        cambiarColorNombres();


        System.out.println(jugador2 + " ingrese un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
        caracter2 = lector.nextLine();
        posiblesErroresEnCaracterDeJugador2();
        caracter2 = caracter2.toUpperCase();
        cambiarColorCaracteres();

        while (!verificador) {

            tablero.mostrarTablero(tablero.getTablero());
            tablero.mostrarInterfazTablero(jugador1,caracter1, jugador2,caracter2,jugadasMaximas);
            System.out.println(mensajeError);
            mensajeError = "";
            partidaTurnos();
        }
    }

    public void controlErrorColumnasJugadorYsuJuego () {
        try {
            System.out.println(jugador1 + " escriba el numero de columna para poner su ficha:");
            String auxiliar = lector.nextLine();
            columna = Integer.parseInt(auxiliar) - 1;
            if (columna > tablero.getCapacidadColumnas() - 1 || columna < 0) {
                mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + tablero.getCapacidadColumnas();
            } else {
                contador = 1;
                for (int i = tablero.getCapacidadColumnas() - 1; i > -1; i = i - 1) {
                    if (!verificador) {
                        //Si se cumple esta condicion, termina el turno del jugador 1
                        if (Tablero.tablero[i][columna] != caracter1 && Tablero.tablero[i][columna] != caracter2) {
                            Tablero.tablero[i][columna] = caracter1;
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
        verificador = false;
        auxiliarJugador = caracter1;
        tablero.verificadorGanador(jugador1, caracter1);
        tablero.mostrarTablero(tablero.getTablero());
        if (jugadasMaximas == (tablero.getCapacidadFilas() * tablero.getCapacidadColumnas()) + 1) {
            tablero.setFinJuego(true);
            tablero.mostrarTablero(tablero.getTablero());
            System.out.println("======================");
            System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
            System.out.println("El tablero esta lleno");
            System.out.println("======================");
        }
        //Si el jugador 1 no gano en su turno continua con el jugador 2
        if (!tablero.isFinJuego()) {
            //turno jugador 2
            while (!verificador) {
                tablero.getNumColumnas(tablero);
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
                            if (!verificador) {
                                //Si se cumple esta condicion, termina el turno del jugador 2
                                if (Tablero.tablero[i][columna] != caracter2 && Tablero.tablero[i][columna] != caracter1) {
                                    Tablero.tablero[i][columna] = caracter2;
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
            }
            verificador = false;
            auxiliarJugador = caracter2;
            tablero.verificadorGanador(jugador2, caracter2);

            if (jugadasMaximas == (tablero.getCapacidadFilas() * tablero.getCapacidadColumnas()) + 1) {
                tablero.setFinJuego(true);
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

    public void cambiarColorCaracteres (){
        caracter1 = "\033[31m" + caracter1 + "\u001B[0m";
        caracter2 = "\033[34m" + caracter2 + "\u001B[0m";
    }

    public void cambiarColorNombres () {
        jugador1 = "\033[32m" + jugador1 + "\u001B[0m";
        jugador2 = "\033[36m" + jugador2 + "\u001B[0m";
    }



    public void partidaTurnos () {
        controlErrorColumnasJugadorYsuJuego();
    }
    public void juego () {
        Decoracio.titol();
        comprobarEntradaDificultad();
        tablero.imprimirTablero();
        opcionesDeJuego();
    }
}