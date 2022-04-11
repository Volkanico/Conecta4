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

    public void partidaTurnos () {
        controlErrorColumnasJugadorYsuJuego();
    }
    public void juego () {
        Decoracio.titol();
        comprobarEntradaDificultad();
        tablero.imprimirTablero();
        opcionesDeJuego();
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
    }

    public void opcionesDeJuego () {
        if (modoDeJuego == 1) {
            JugadorContraJugador jugadorContraJugador = new JugadorContraJugador();
            jugadorContraJugador.opcionJugadorContraJugador();
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





}