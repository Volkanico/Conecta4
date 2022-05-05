package com.company;
import java.util.Objects;
import java.util.Scanner;

public class JugadorContraJugador {
    Scanner lector = new Scanner(System.in);
    boolean verificador = false;

    String mensajeError = "";

    int jugadasMaximas = 1;
    int columna = 0;
    int contador = 0;
    String auxiliarJugador;
    Decoracio decoracio = new Decoracio();
    Tablero tablero = new Tablero();

    Jugador jugador1 = new Jugador("Jugador 1", "X");
    Jugador jugador2 = new Jugador("Jugador 2", "O");

    public void partidaTurnos () {
        controlErrorColumnasJugadorYsuJuego();
    }

    public void opcionJugadorContraJugador () {
        System.out.println("============================================");
        System.out.println("MODO SELECCIONADO:");
        System.out.println("             \033[32mJugador 1\u001B[0m vs \033[36mJugador 2\u001B[0m");
        //datos Jugador 1
        System.out.println("\033[32mJugador 1\u001B[0m ingrese un nick");
        jugador1.setJugador(lector.nextLine());
        if ( jugador1.getJugador().equals("")) {
            jugador1.setJugador("Jugador 1");
        }
        decoracio.cambiarColorNombres1(jugador1.getJugador());
        jugador1.setJugador(decoracio.getJugador1J());

        System.out.print(jugador1.getJugador() + " ingrese un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
        jugador1.setCaracter(lector.nextLine());
        posiblesErroresEnCaracterDeJugador1();
        jugador1.setCaracter(jugador1.getCaracter().toUpperCase());
        decoracio.cambiarColorCaracteres1(jugador1.getCaracter());
        jugador1.setCaracter(decoracio.getCaracter1J());  ;
        System.out.println("======================================================================================");

        //datos Jugador 2
        System.out.println("\033[36mJugador 2\u001B[0m ingrese un nick");
        jugador2.setJugador(lector.nextLine());
        while (Objects.equals(jugador2.getJugador(), jugador1.getJugador())) {
            System.out.println("\033[35m**ERROR:\u001B[0m El jugador 1 ya ocupo este nick, ingreso otro");
            jugador2.setJugador(lector.nextLine());
        }
        if (jugador2.getJugador().equals("")) {
            jugador2.setJugador("Jugador 2");
        }
        decoracio.cambiarColorNombres2(jugador2.getJugador());
        jugador2.setJugador(decoracio.getJugador2J());
        System.out.print(jugador2.getJugador() + " ingrese un caracter para usarlo en el juego (Puede ser un signo, numero, letra, etc)");
        jugador2.setCaracter(lector.nextLine());
        posiblesErroresEnCaracterDeJugador2();
        jugador2.setCaracter(jugador2.getCaracter().toUpperCase());
        decoracio.cambiarColorCaracteres2(jugador2.getCaracter());
        jugador2.setCaracter(decoracio.getCaracter2J());

        while (!verificador) {

            tablero.mostrarTablero(tablero.getTablero());
            tablero.mostrarInterfazTablero(jugador1.getJugador(),jugador1.getCaracter(), jugador2.getJugador(),jugador2.getCaracter(),jugadasMaximas);
            System.out.println(mensajeError);
            mensajeError = "";
            partidaTurnos();
        }
    }

    public void controlErrorColumnasJugadorYsuJuego () {
        try {
            System.out.println(jugador1.getJugador() + " escriba el numero de columna para poner su ficha:");
            String auxiliar = lector.nextLine();
            columna = Integer.parseInt(auxiliar) - 1;
            if (columna > tablero.getCapacidadColumnas() - 1 || columna < 0) {
                mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + tablero.getCapacidadColumnas();
            } else {
                contador = 1;
                for (int i = tablero.getCapacidadColumnas() - 1; i > -1; i = i - 1) {
                    if (!verificador) {
                        //Si se cumple esta condicion, termina el turno del jugador 1
                        if (!Objects.equals(Tablero.tablero[i][columna], jugador1.getCaracter()) && !Objects.equals(Tablero.tablero[i][columna], jugador2.getCaracter())) {
                            Tablero.tablero[i][columna] = jugador1.getCaracter();
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
        auxiliarJugador = jugador1.getCaracter();
        tablero.verificadorGanador(jugador1.getJugador(), jugador1.getCaracter());
        tablero.mostrarTablero(tablero.getTablero());
        empate();
        //Si el jugador 1 no gano en su turno continua con el jugador 2
        if (!tablero.isFinJuego()) {
            //turno jugador 2
            while (!verificador) {
                tablero.getNumColumnas(tablero);
                tablero.mostrarInterfazTablero(jugador1.getJugador(), jugador1.getCaracter(), jugador2.getJugador(), jugador2.getCaracter(), jugadasMaximas);
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
                                if (!Objects.equals(Tablero.tablero[i][columna], jugador2.getCaracter()) && !Objects.equals(Tablero.tablero[i][columna], jugador1.getCaracter())) {
                                    Tablero.tablero[i][columna] = jugador2.getCaracter();
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
            auxiliarJugador = jugador2.getCaracter();
            tablero.verificadorGanador(jugador2.getJugador(), jugador2.getCaracter());
            empate();
        }
    }

    private void empate() {
        if (jugadasMaximas == (tablero.getCapacidadFilas() * tablero.getCapacidadColumnas()) + 1) {
            tablero.setFinJuego(true);
            tablero.mostrarTablero(tablero.getTablero());
            System.out.println("======================");
            System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
            System.out.println("El tablero esta lleno");
            System.out.println("======================");
        }
    }

    public void posiblesErroresEnCaracterDeJugador1 (){
        while (jugador1.getCaracter().length() > 1 || jugador1.getCaracter().equals("")) {
            if (jugador1.getCaracter().length() > 1) {       //Si el caracter ingresado es de mas de una letra
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter debe ser de una sola letra");
                System.out.println("ejemplo: 'X' , 'O' , 'V' ...");
                jugador1.setCaracter(lector.nextLine());
                jugador1.setCaracter(jugador1.getCaracter().toUpperCase());
            }
            if (jugador1.getCaracter().equals("")) {   //Si el caracter no contiene nada
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter no puede estar vacio, intente nuevamente");
                jugador1.setCaracter(lector.nextLine());
                jugador1.setCaracter(jugador1.getCaracter().toUpperCase());
            }
        }
    }
    public void posiblesErroresEnCaracterDeJugador2 (){
        while (jugador2.getCaracter().equals(jugador1.getCaracter()) || jugador2.getCaracter().length() > 1 || jugador2.getCaracter().equals("")) {
            if (jugador2.getCaracter().equals("")) {       //Si el caracter ingresado no contiene nada
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter no puede estar vacio, intente nuevamente");
                jugador2.setCaracter(lector.nextLine());
                jugador2.setCaracter(jugador2.getCaracter().toUpperCase());
            }
            if (jugador2.getCaracter().equals(jugador1.getCaracter())) {     //Si el caracter ingresado es igual al del jugador 1
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter es igual al del jugador 1, ingrese otro");
                jugador2.setCaracter(lector.nextLine());
                jugador2.setCaracter(jugador2.getCaracter().toUpperCase());
            }
            if (jugador2.getCaracter().length() > 1) {     //Si el caracter ingresado es de mas de una letra
                System.out.println("\033[35m**ERROR:\u001B[0m El caracter debe ser de una sola letra, intente nuevamente");
                jugador2.setCaracter(lector.nextLine());
                jugador2.setCaracter(jugador2.getCaracter().toUpperCase());  ;
            }
        }
    }

}
