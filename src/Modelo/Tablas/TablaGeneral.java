package Modelo.Tablas;

import Modelo.Util.Elemento;
import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class TablaGeneral implements Observable {
    private List<Observer> observadores = new ArrayList<>();

    private TablaResultados tablaResultados;
    private TablaFaltas tablaFaltas;
    private TablaPuntos [] tablaPuntos;
    private static final int COLORES = 4;
    private static final int RESULTADOS = 6;

    public TablaGeneral(){
        this.tablaPuntos = new TablaPuntos[COLORES];
        this.tablaResultados = new TablaResultados();
        this.tablaFaltas = new TablaFaltas();

        // Inicializar Tabla de puntos para cada color.
        for (int i = 0; i < COLORES; i++) {
            this.tablaPuntos[i] = new TablaPuntos();
        }
    }
    //METODOS PARA ASIGNAR FALTAS
    public boolean setFalta(){
        boolean resultado =  this.tablaFaltas.setFalta();

        // si la falta fue exitosa
        if (resultado) {
            this.notificar();  // Llamamos a la función notificar si fue exitosa
        }

        // Retornamos el resultado de la inserción
        return resultado;

    }

    public boolean setPunto(Elemento elemento, boolean booleano) {
        //TRUE = MENORES || FALSE MAYORES
        // Intentar agregar el punto en la tabla correspondiente
        boolean resultado = tablaPuntos[elemento.getColorOrdinal()].agregarPuntos(elemento.getValor(), booleano);

        // Si la inserción fue exitosa, notificar
        if (resultado) {
            this.tablaResultados.calcularResultado(this.tablaPuntos);

            this.notificar();  // Llamamos a la función notificar si fue exitosa
        }

        // Retornamos el resultado de la inserción
        return resultado;
    }

    //METODOS PARA CALCULAR RESULTADOS
    public int getResultadoFinal(){
       return this.tablaResultados.getSumaTotal() - this.tablaFaltas.calcularPenalizacion();

    }
    public boolean tablaResultadosLlena(){
        return this.tablaResultados.tablaLLena();
    }
    public boolean tablaFaltasLlena(){
        return this.tablaFaltas.tablaLLena();
    }

    public List<int[]> obtenerTodasLasTablas() {
        List<int[]> tablas = new ArrayList<>();

        // Agregar las 4 tablas de puntos
        for (int i = 0; i < COLORES; i++) {
            int[] tablaPuntosArray = this.tablaPuntos[i].getTabla();  // Recupera los datos de la tabla de puntos
            tablas.add(tablaPuntosArray);  // Agrega los datos de la tabla de puntos
        }

        // Agregar tabla de resultados
        int[] tablaResultadosArray = this.tablaResultados.getTabla();  // Recupera los datos de la tabla de resultados
        tablas.add(tablaResultadosArray);  // Agrega los datos de la tabla de resultados

        // Agregar tabla de faltas
        int[] tablaFaltasArray = this.tablaFaltas.getTabla();  // Recupera los datos de la tabla de faltas
        tablas.add(tablaFaltasArray);  // Agrega los datos de la tabla de faltas

        return tablas;  // Devolver todas las tablas
    }

    public int[] obtenerResultadosFinales() {
        int puntajeResultados = this.tablaResultados.getSumaTotal();
        int puntajeFaltas = this.tablaFaltas.calcularPenalizacion();
        int diferencia = getResultadoFinal();

        return new int[] { puntajeResultados, puntajeFaltas, diferencia };
    }
    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);  // Agrega el observador a la lista
    }
    public void mostrarTabla(){
        notificar();
    }

    @Override
    public void notificar() {
        for (Observer observador : observadores) {
            observador.actualizarTablasJugador(this.obtenerTodasLasTablas());
        }
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }
}
