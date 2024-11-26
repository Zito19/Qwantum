package Controlador;

import Modelo.Jugador;
import Modelo.ModeloJuego;
import Vista.Vista;

public class Controlador {
        private ModeloJuego juego;
        private Vista vista;

        public Controlador(ModeloJuego modelo, Vista vista) {
            this.juego = modelo;
            this.vista = vista;
           juego.getTiradorDeDados().agregarObservador(vista);
            juego.getProcesadorResultadosDados().agregarObservador(vista);
            juego.getResultadoJuego().agregarObservador(vista);
        }

    public void jugar() {
            int opcionAccion;
        boolean juegoTerminado = false;
        vista.mostrarBienvenida();
        while (!juegoTerminado) {
            Integer opcionElegida = vista.inputMenuPrincipal();
            Boolean agregado = false;
            while (!agregado) {
                if (opcionElegida == 1 || opcionElegida == 2) {
                    agregado = true;
                } else {
                    vista.mostrarOpcionInvalida();
                    opcionElegida = vista.inputMenuPrincipal();
                }
            }
            switch (opcionElegida) {
                case 1:
                    Integer cantidadJugadores = vista.inputCantidadJugadores();
                    agregado = false;
                    while (!agregado) {
                        if (juego.validarCantidadJugadores(cantidadJugadores)) {
                            agregado = true;
                        } else {
                            vista.mostrarCantidadDeJugadoresIncorrecta();
                            cantidadJugadores = vista.inputCantidadJugadores();
                        }
                    }
                    for (Integer i = 0; i < cantidadJugadores; i++) {
                        agregado = false;
                        while (!agregado) {
                            String nombre = vista.inputNombreJugador(i + 1);
                            if (juego.validarNombreJugador(nombre)) {
                                Jugador jugadorAgregado = juego.agregarJugador(nombre);
                                jugadorAgregado.getTablaJugador().agregarObservador(vista);
                                agregado = true;
                            } else {
                                vista.mostrarNombreDeJugadorIncorrecto();
                                nombre = vista.inputNombreJugador(i + 1);
                            }
                        }
                    }
                    while(!juego.juegoTerminado()) {

                        Jugador turnoActual = juego.cambiarTurno(); // Cambia el turno al siguiente jugador
                        vista.mostrarNombreTurnoJugador(juego.getJugadorActual().getNombre()); // Muestra al jugador cuyo turno es
                        juego.tirarDados(); // El jugador tira los dados
                        juego.resultadosTiradaXColor(); // Calcula los resultados de la tirada por color
                        juego.mostrarTabla(turnoActual); // Muestra la tabla de resultados

                        // Pregunta al jugador actual qué desea hacer
                        opcionAccion = vista.inputSeleccionarAccionJugada();  // Aquí se permite seleccionar jugar o saltar turno

                        while (!verificarInputEnteros(opcionAccion, 1, 2)) {  // 1 jugar 2 pasa turno
                            vista.mostrarOpcionInvalida();  // Muestra el mensaje de opción no válida
                            opcionAccion = vista.inputSeleccionarAccionJugada();  // Vuelve a pedir la opción
                        }

                        if (opcionAccion == 1) {
                            // Si el jugador elige jugar
                            insertarPuntos(turnoActual);
                        } else if (opcionAccion == 2) {
                            juego.setFaltaJugador(turnoActual); // Si pasa turno, marca la falta
                        }

                        // Obtener el jugador actual
                        Jugador jugadorActual = turnoActual;

                        // Aquí comienza el bloque donde los demás jugadores toman su turno para decidir si desean agregar puntos restantes
                        for (Jugador jugador : juego.getJugadores()) {
                            if (!jugador.equals(jugadorActual)) {  // Solo los jugadores que no sean el actual
                                vista.mostrarNombreTurnoJugador(jugador.getNombre());  // Muestra el nombre del siguiente jugador
                                opcionAccion = vista.inputSeleccionarAccionJugada();  // Pregunta si quieren agarrar los puntos restantes

                                while (!verificarInputEnteros(opcionAccion, 1, 2)) {  // Verifica que la opción sea válida
                                    vista.mostrarOpcionInvalida();  // Muestra el mensaje de opción no válida
                                    opcionAccion = vista.inputSeleccionarAccionJugada();  // Vuelve a preguntar
                                }

                                if (opcionAccion == 1) {
                                    // Si el jugador decide tomar los puntos restantes
                                    juego.getProcesadorResultadosDados().mostrarResultadosPorColorRestantes(); // Muestra los puntos restantes
                                    juego.mostrarTabla(jugador); // Muestra la tabla actualizada

                                    // Si el jugador elige jugar
                                    insertarPuntos(jugador); // El jugador agrega puntos
                                }
                            }
                        }
                    }
                    juego.resultadosFinales();
                case 2:
                    juegoTerminado = true;
                    vista.mostrarGameover();
                    break;
                default:
                    vista.mostrarOpcionInvalida();
                    break;
            } break;
        }
    }


    public boolean verificarInputEnteros(int input, int x, int y) {
        //verifica si el input fue valido dentro de un rango.
        if (input >= x && input <= y) {
            return true;
        }
        return false;
    }

    public boolean insertarPuntos(Jugador jugador) {
        boolean resultado; // Variable para almacenar el resultado de la inserción
        int color;
        int insercion;
        boolean booleanoInsercion;

        do {
            color = vista.inputSeleccionarColor(); // Selecciona el color
            while (!verificarInputEnteros(color, 1, 4)) { // Verificar si el color es válido
                vista.mostrarOpcionInvalida(); // Si no es válido, muestra un mensaje de error
                color = vista.inputSeleccionarColor(); // Vuelve a preguntar por el color
            }

            insercion = vista.inputSeleccionarModoDeInsercion(); // Selecciona el modo de inserción
            while (!(insercion == 1 || insercion == 2)) { // Verificar si el tipo de inserción es válido
                vista.mostrarOpcionInvalida(); // Si no es válido, muestra un mensaje de error
                insercion = vista.inputSeleccionarModoDeInsercion(); // Vuelve a preguntar por el modo de inserción
            }

            booleanoInsercion = insercion != 1; // Determina el modo de inserción

            // Intenta agregar los puntos
            resultado = juego.agregarPuntos(jugador, juego.getResultadoPorColor(color), booleanoInsercion);

            if (resultado) {
                juego.eliminarResultado(color); // Si fue exitosa, elimina el resultado usado
            } else {
                // Si el resultado es falso, preguntar si quiere intentarlo de nuevo o salir
                int continuar = vista.inputIntentarDeNuevoInsercion(); // Metodo que te pregunte si desea continuar
                if (continuar == 2) {
                    juego.setFaltaJugador(jugador);
                    break; // Salir del ciclo si no desea continuar
                }
            }

        } while (!resultado); // Si resultado es false y no desea salir, repite el proceso

        return booleanoInsercion; // Devuelve el tipo de inserción
    }

}
