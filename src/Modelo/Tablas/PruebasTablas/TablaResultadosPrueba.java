package Modelo.Tablas.PruebasTablas;

import Modelo.Tablas.TablaGeneral;
import Modelo.Tablas.TablaPuntos;
import Modelo.Tablas.TablaResultados;
import Modelo.Util.Color;
import Modelo.Util.Elemento;

public class TablaResultadosPrueba {
    public static void main(String[] args) {
        // Crear una instancia de TablaPuntos para cada color (en este caso 4 colores)
        TablaPuntos tablaPuntosVioleta = new TablaPuntos();
        TablaPuntos tablaPuntosAzul = new TablaPuntos();
        TablaPuntos tablaPuntosRojo = new TablaPuntos();
        TablaPuntos tablaPuntosAmarillo = new TablaPuntos();

        // Agregar puntos a las tablas de cada color
        tablaPuntosVioleta.agregarPuntos(1, true);  // Violeta, posición 1
        tablaPuntosVioleta.agregarPuntos(2, true);  // Violeta, posición 2
        tablaPuntosAzul.agregarPuntos(1, true);    // Azul, posición 1
        tablaPuntosAzul.agregarPuntos(3, true);    // Azul, posición 3
        tablaPuntosRojo.agregarPuntos(2, true);    // Rojo, posición 2
        tablaPuntosAmarillo.agregarPuntos(5, true); // Amarillo, posición 5

        // Crear un arreglo con las tablas de puntos
        TablaPuntos[] tablaPuntos = new TablaPuntos[4];
        tablaPuntos[0] = tablaPuntosVioleta;
        tablaPuntos[1] = tablaPuntosAzul;
        tablaPuntos[2] = tablaPuntosRojo;
        tablaPuntos[3] = tablaPuntosAmarillo;

        // Crear una instancia de TablaResultados
        TablaResultados tablaResultados = new TablaResultados();

        // Calcular los resultados a partir de las tablas de puntos
        tablaResultados.calcularResultado(tablaPuntos);

        // Mostrar la tabla de resultados
        tablaResultados.mostrarTablaResultados();

        // Mostrar la suma total de los resultados
//        System.out.println("Suma Total de los Resultados: " + tablaResultados.getSumaTotal());
    }
}


