package com.company;
import java.util.Scanner;

public class InputOutput {
    int modoDeJuego = 0;
    Scanner lector = new Scanner(System.in);
    Tablero tablero = new Tablero();

    public void juego () {
        Decoracio.titol();
        tablero.imprimirTablero();
        comprobarEntradaDificultad();
    }

    public void opcionesDeJuego () {
        if (modoDeJuego == 1) {
            JugadorContraJugador jugadorContraJugador = new JugadorContraJugador();
            jugadorContraJugador.opcionJugadorContraJugador();
        }
        if (modoDeJuego == 2) {
            JugadorContraMaquina  jugadorContraMaquina= new JugadorContraMaquina();
            jugadorContraMaquina.opcionJugadorContraMaquina();
        }
    }

    public void comprobarEntradaDificultad (){
        boolean verificador = false;
        while (!verificador) {
            try {
                String auxiliar = lector.nextLine();
                modoDeJuego = Integer.parseInt(auxiliar);
                if (modoDeJuego == 1 || modoDeJuego == 2) {
                    verificador = true;
                    opcionesDeJuego();
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










}