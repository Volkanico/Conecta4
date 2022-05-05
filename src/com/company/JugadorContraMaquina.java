package com.company;

import java.util.Objects;
import java.util.Scanner;

public class JugadorContraMaquina {
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
    boolean finJuego;
    Tablero tablero = new Tablero();
    Decoracio decoracio = new Decoracio();

    public void PartidaTurnos () {
        controlErrorColumnasJugadorYsuJuego();
    }

    public void opcionJugadorContraMaquina () {
        System.out.println("============================================");
        System.out.println("MODO SELECCIONADO:");
        System.out.println("             \033[32mJugador 1\u001B[0m vs \033[33mOrdenador\u001B[0m");
        //datos Jugador 1
        System.out.println("\033[32mJugador 1\u001B[0m ingrese un nick");
        jugador1 = lector.nextLine();
        if (jugador1 == null || jugador1.equals("")) {
            jugador1 = "Jugador 1";
        }
       //COLORES
        jugador1 = decoracio.cambiarColorNombres1(jugador1);
        jugador1 = decoracio.getJugador1J();

        System.out.println(jugador1 + " ingrese un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
        caracter1 = lector.nextLine();
        caracter1 = caracter1.toUpperCase();    //Transforma en mayusculas el caracter

        while (caracter1.length() > 1 || caracter1.equals("")) {
            if (caracter1.length() > 1) {       //Si el caracter ingresado es de mas de una letra
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter debe ser de una sola letra");
                System.out.println("ejemplo: 'X' , 'O' , 'V' ...");
                caracter1 = lector.nextLine();
                caracter1 = caracter1.toUpperCase();
            }
            if (caracter1.equals("")) {   //Si el caracter no contiene nada
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter no puede estar vacio, intente nuevamente");
                caracter1 = lector.nextLine();
                caracter1 = caracter1.toUpperCase();
            }
        }
        //COLORES
        decoracio.cambiarColorCaracteres1(caracter1);
        caracter1 = decoracio.getCaracter1J();
        jugador2 = "\033[33mOrdenador\u001B[0m";
        caracter2 = "\033[33m§\u001B[0m";

        while (!finJuego) {  //turno jugador 1
            while (!verificador) {
                tablero.mostrarTablero(tablero.getTablero());
                tablero.mostrarInterfazTablero(jugador1, caracter1, jugador2, caracter2, jugadasMaximas);
                System.out.println(mensajeError);
                mensajeError = "";
                PartidaTurnos();
            }
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
                                if (!Objects.equals(Tablero.tablero[i][columna], caracter1) && !Objects.equals(Tablero.tablero[i][columna], caracter2)) {
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
                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Ingreso un caracter no valido, debe ingresar un NUMERO";
                } catch (Exception e) {
                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + tablero.getCapacidadColumnas();
                }
            verificador = false;
            auxiliarJugador = caracter1;
            tablero.verificadorGanador(jugador1, caracter1);
            empate();

            //Si el jugador 1 no gano en su turno continua con el ordenador
            if (!finJuego) {

                while (!verificador) { //turno ordenador
                    columna = (int) (Math.random() * tablero.getCapacidadColumnas());
                    for (int i = tablero.getCapacidadColumnas() - 1; i > -1; i = i - 1) {
                        if (!verificador) {
                            //Si se cumple esta condicion, termina el turno del jugador 2 / ordenador
                            if (!Objects.equals(Tablero.tablero[i][columna], caracter2) && !Objects.equals(Tablero.tablero[i][columna], caracter1)) {
                                Tablero.tablero[i][columna] = caracter2;

                                verificador = true;
                                jugadasMaximas++;
                            }
                        }
                    }
                }
                verificador = false;
                auxiliarJugador = caracter2;
                tablero.verificadorGanador(jugador2, caracter2);
                empate();
            }
        }
    private void empate() {
        if (jugadasMaximas == (tablero.getCapacidadFilas() * tablero.getCapacidadColumnas()) + 1) {
            finJuego = true;
            tablero.mostrarTablero(tablero.getTablero());
            System.out.println("======================");
            System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
            System.out.println("El tablero esta lleno");
            System.out.println("======================");
        }
    }

    /* Comprovar amb random fins que la jugada de l'ordenador sigui una jugada eficient, comprovant a cada clicada amb el random si guanya o no al jugador */
}


