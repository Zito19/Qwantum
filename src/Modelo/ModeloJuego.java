package Modelo;
import Modelo.Dados.ProcesadorResultadosDados;
import Modelo.Dados.TiradorDeDados;
import Modelo.Tablas.TablaGeneral;
import Modelo.Util.Color;
import Modelo.Util.Elemento;
import java.util.ArrayList;
import java.util.List;

public class ModeloJuego {
    private static ModeloJuego instancia;          // Instancia única de la clase Juego
    private TiradorDeDados tiradorDeDados;   // Instancia del tirador de dados
    private List<Jugador> jugadores;        // Lista de jugadores
    private Jugador jugadorActual;
    private ResultadoJuego procesadorResultadosFinales;// Turno actual del jugador

    //private List<Observer> observadores = new ArrayList<>();  // Lista de observadores
    private ProcesadorResultadosDados procesadorResultadosDados = ProcesadorResultadosDados.getInstancia();

    // Constructor privado para evitar instanciación externa
    private ModeloJuego() {
        this.tiradorDeDados = TiradorDeDados.getInstancia();
        this.jugadores = new ArrayList<>();
        this.procesadorResultadosFinales = new ResultadoJuego();
    }
    // Instancia única de Juego
    public static ModeloJuego getInstancia() {
        if (instancia == null) {
            instancia = new ModeloJuego();                // Crea la instancia única si no existe
        }
        return instancia;
    }

    //-----------DADOS Y RESULTADOS DADOS----------

    // Metodo para tirar los dados y obtener los resultados por color
    public void tirarDados() {
        tiradorDeDados.tirarDados();  // Delegamos la responsabilidad al tirador de dados
    }
    // Metodo para obtener los resultados de los dados por color
    public void resultadosTiradaXColor() {
       procesadorResultadosDados.procesarResultadosDados();
    }
    public TiradorDeDados getTiradorDeDados(){
        return this.tiradorDeDados;
    }
    public ProcesadorResultadosDados getProcesadorResultadosDados(){
        return this.procesadorResultadosDados;
    }
    public Elemento getResultadoPorColor(int color){
      return  procesadorResultadosDados.getResultadoPorColor(Color.getColorPorNumero(color-1));
    }
    public boolean eliminarResultado(int color) {
        procesadorResultadosDados.reiniciarColor(Color.getColorPorNumero(color - 1));
        return true; // Devuelve true si necesitas un indicador de éxito
    }
    //----------METODOS JUGADOR-------------
    // Agrega un jugador a la lista
    public Jugador agregarJugador(String nombre) {
        Jugador nuevo = new Jugador(nombre);
        jugadores.add(nuevo);
        return nuevo;
    }

    // Devuelve la lista de jugadores
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    // Metodo para cambiar el turno entre jugadores
    public Jugador cambiarTurno() {
        // Si el jugadorActual es null, asignamos al primer jugador
        if (jugadorActual == null) {
            jugadorActual = jugadores.get(0);
            return this.jugadorActual;
        }

        // Encontramos el índice del jugador actual
        int index = jugadores.indexOf(jugadorActual);

        // Si el jugador actual es el último, el siguiente será el primer jugador
        if (index == jugadores.size() - 1) {
            jugadorActual = jugadores.get(0);
        } else {
            jugadorActual = jugadores.get(index + 1);
        }
        return this.jugadorActual;
    }
    //-------------METODOS TABLA X JUGADOR--------------
    //metodo tabla.
    public void mostrarTabla(Jugador jugador){
        jugador.getTablaJugador().mostrarTabla();
    }
    public TablaGeneral getTablaJugador(){
        return jugadorActual.getTablaJugador();
    }
    public boolean agregarPuntos(Jugador jugador, Elemento elemento, boolean booleano) {
        // Intentamos insertar el punto en la tabla del jugador actual
        boolean insercionValida = jugador.getTablaJugador().setPunto(elemento, booleano);

        // Si la inserción fue válida, restamos el turno salteado
        if (insercionValida) {
            jugador.resetTurnosSalteados();
        }

        // Devolvemos el resultado de la inserción
        return insercionValida;
    }

    public Boolean validarCantidadJugadores(Integer cantidadJugadores) {
        return cantidadJugadores >= 2 && cantidadJugadores <= 4;
    }
    public Boolean validarNombreJugador(String nombre) {
        nombre = nombre.trim();
        if (nombre == null || nombre.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    public Jugador getJugadorActual() {
        return jugadorActual;
    }
    public void setFaltaJugador(Jugador jugador){
        if(jugador == jugadorActual) {
            jugadorActual.getTablaJugador().setFalta();
            jugadorActual.setSaltoturno();
        }
    }
    public boolean tablaResultadosLlena(Jugador jugador){
        return jugador.getTablaJugador().tablaResultadosLlena();
    }
    public boolean tablaFaltasLlena(Jugador jugador){
        return jugador.getTablaJugador().tablaFaltasLlena();
    }

    @Override
    public String toString() {
        return "Juego con " + jugadores.size() + " jugadores y 7 dados.";
    }

    public void setProcesadorResultadosDados(ProcesadorResultadosDados procesadorResultadosDados) {
        this.procesadorResultadosDados = procesadorResultadosDados;
    }

    public boolean juegoTerminado() {
        for (Jugador jugador : jugadores) {
            // Verificar si la tabla de faltas está llena
            if (tablaFaltasLlena(jugador)) {
                return true; // El juego termina si la tabla de faltas de un jugador está llena
            }

            // Verificar si la tabla de resultados está llena
           if (tablaResultadosLlena(jugador)) {
           return true; // El juego termina si la tabla de resultados de un jugador está llena
            }

            // Verificar si el jugador se saltó dos turnos consecutivos
            if (jugador.getTurnosSalteados() > 1) {
               return true; // El juego termina si un jugador se saltó dos turnos consecutivos
          }
        }
        return false; // El juego no ha terminado
    }
    public void resultadosFinales(){
        this.procesadorResultadosFinales.obtenerResultadosDeJugadores(this.jugadores);
    }
    public ResultadoJuego getResultadoJuego(){
        return  this.procesadorResultadosFinales;
    }
}
