package Modelo.Tablas.PruebasTablas;

import Modelo.Tablas.TablaPuntos;

public class TablaPuntosTest {
        public static void main(String[] args) {
                        // Crear una instancia de TablaPuntos
                        TablaPuntos tabla = new TablaPuntos();

                        // Imprimir el estado inicial de la tabla
                        System.out.println(tabla);

                        // Intentar agregar puntos en los arreglos mayores y menores
                        System.out.println("Insertar 10 en mayores: " + tabla.agregarPuntos(10, false)); // Mayores
                        System.out.println("Insertar 5 en menores: " + tabla.agregarPuntos(5, true)); // Menores
                        System.out.println("Insertar 8 en menores: " + tabla.agregarPuntos(8, true)); // Menores

                        // Imprimir el estado de la tabla después de las inserciones
                        System.out.println(tabla);

                        // Intentar insertar un valor que no se puede agregar
                        System.out.println("Insertar 20 en menores (debería fallar): " + tabla.agregarPuntos(20, true));

                        // Verificar si la tabla está llena
                        System.out.println("¿La tabla está llena?: " + tabla.tablaLLena());

        }
}