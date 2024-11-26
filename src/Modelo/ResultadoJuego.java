package Modelo;

import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ResultadoJuego implements Observable {
    private List<Observer> observadores = new ArrayList<>();
    private List<Object[]> resultados = new ArrayList<>();

    public void obtenerResultadosDeJugadores(List<Jugador> jugadores) {
        // Usamos directamente la lista de resultados de la clase
        this.resultados = new ArrayList<>();  // Aseguramos que es la lista correcta

        // Recorremos todos los jugadores
        for (Jugador jugador : jugadores) {
            // Obtenemos los resultados finales del jugador
            int[] resultadosJugador = jugador.getTablaJugador().obtenerResultadosFinales();

            // Creamos un arreglo que contiene el nombre del jugador y sus 3 resultados
            Object[] resultado = new Object[4];
            resultado[0] = jugador.getNombre();  // Nombre del jugador
            resultado[1] = resultadosJugador[0]; // Puntos
            resultado[2] = resultadosJugador[1]; // Faltas
            resultado[3] = resultadosJugador[2]; // Puntaje final
            this.resultados.add(resultado);
        }
        this.resultados.sort((a, b) -> Integer.compare((int) b[3], (int) a[3]));
     notificar();
    }

    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void notificar() {
        for (Observer observador : observadores) {
            observador.resultadosFinales(this.resultados);
        }
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }
}
