package Vista;

import Modelo.Util.Color;
import Modelo.Util.Elemento;
import Observer.Observer;

import java.util.List;
import java.util.Scanner;

public class Vista implements Observer {
    private Scanner sc = new Scanner(System.in);
    @Override
    public void actualizarDados(Elemento[] TiradaDados) {
        System.out.println("Dados: ");
        for (Elemento elemento : TiradaDados) {
            System.out.print("[" + elemento.getValor() +"-"+ elemento.getColor() + "]");
        }
        System.out.println();
    }

    public void actualizarResultadosXColor(Elemento[] resultadosXColor) {
        System.out.println("Posibles puntos: ");
        int i = 1; // Inicializa el número ordinal en 1
        for (Elemento elemento : resultadosXColor) {
            System.out.println(i+"- " + " [" + elemento.getValor() + "][" + elemento.getColor() + "]");
            i++; // Incrementa el número ordinal
        }
        System.out.println();
    }
    @Override
    public void actualizarTablasJugador(List<int[]> tablas) {
        // Mostrar las 4 tablas de colores (TablaPuntos)
        System.out.println(" ");
        System.out.println("TABLAS DE PUNTOS POR COLOR:");

        for (int i = 0; i < tablas.size() - 2; i++) {  // Las tablas de puntos están antes de las de resultados y faltas
            Color color = Color.getColorPorNumero(i);  // Obtener el color según el índice
            int[] tablaPuntos = tablas.get(i);  // Obtener la tabla de puntos de la lista

            // Imprimir el color y los valores de la tabla de puntos en una sola línea
            System.out.print(color + ": ");
            for (int j = 0; j < tablaPuntos.length; j++) {
                System.out.print("[" + (j + 1) + ":" + tablaPuntos[j] + "] ");
            }
            System.out.println();  // Salto de línea después de cada tabla de color
        }

        // Mostrar la tabla de resultados
        System.out.println("TABLA DE RESULTADOS:");
        int[] tablaResultados = tablas.get(tablas.size() - 2);  // La tabla de resultados está antes de la tabla de faltas
        System.out.print("Resultados: ");
        for (int i = 0; i < tablaResultados.length; i++) {
            System.out.print("[" + (i + 1) + ":" + tablaResultados[i] + "] ");
        }
        System.out.println();  // Salto de línea después de la tabla de resultados

        // Mostrar la tabla de faltas
        System.out.println("TABLA DE FALTAS:");
        int[] tablaFaltas = tablas.get(tablas.size() - 1);  // La última tabla es la de faltas
        System.out.print("Faltas: ");
        for (int i = 0; i < tablaFaltas.length; i++) {
            System.out.print("[" + (i + 1) + ":" + tablaFaltas[i] + "] ");
        }
        System.out.println();  // Salto de línea después de la tabla de faltas
    }

    @Override
    public void resultadosFinales(List<Object[]> resultados) {
        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No hay resultados para mostrar.");
            return;
        }

        // Mostrar el nombre del ganador (primer jugador con mayor puntaje)
        Object[] ganador = resultados.get(0);
        System.out.println("¡El ganador es: " + ganador[0] + " con " + ganador[3] + " puntos!");

        // Mostrar los resultados de todos los jugadores
        System.out.println("Resultados finales:");
        for (Object[] resultado : resultados) {
            String nombre = (String) resultado[0];
            int puntos = (int) resultado[1];
            int faltas = (int) resultado[2];
            int puntajeFinal = (int) resultado[3];

            System.out.println(nombre + " - Puntos: " + puntos + ", Faltas: " + faltas + ", Puntaje final: " + puntajeFinal);
        }
    }

    // INPUTS
    public Integer inputCantidadJugadores() {
        System.out.print("\u001B[33m Ingrese la cantidad de jugadores: ");
        System.out.print("\u001B[0m");
        while (!sc.hasNextInt()) {
            System.out.print("\u001B[31m Ingrese un número válido (cant. de jugadores)");
            System.out.println("\u001B[0m");
            System.out.print("\u001B[33m Ingrese la cantidad de jugadores: ");
            System.out.print("\u001B[0m");
            sc.next();
        }
        Integer cantidadJugadores = sc.nextInt();
        return cantidadJugadores;
    }

    public void mostrarBienvenida() {
        System.out.print("\u001B[34m");
        System.out.println("====================");
        System.out.println("¡Bienvenido a Qwantum!");
        System.out.println("====================");
        System.out.print("\u001B[0m");
    }
    public Integer inputMenuPrincipal() {
        sc = new Scanner(System.in);
        System.out.println("\u001B[32m 1. JUGAR");
        System.out.println("\u001B[31m 2. SALIR");
        System.out.print("\u001B[0m");
        System.out.print("\u001B[33m Ingrese una opción: ");
        System.out.print("\u001B[0m");
        while (!sc.hasNextInt()) {
            System.out.println("\u001B[31m Ingrese una opción válida");
            System.out.print("\u001B[0m");
            System.out.println("\u001B[32m 1. JUGAR");
            System.out.println("\u001B[31m 2. SALIR");
            System.out.print("\u001B[33m Ingrese una opción: ");
            System.out.print("\u001B[0m");
            sc.next();
        }
        Integer opcionElegida = sc.nextInt();
        return opcionElegida;
    }
    public void mostrarOpcionInvalida() {
        System.out.println("\u001B[31m" + "Opción incorrecta");
        System.out.print("\u001B[0m");
    }
    public void mostrarNombreDeJugadorIncorrecto() {
        System.out.println("\u001B[31m ¡El nombre del jugador no puede estar vacío!");
        System.out.print("\u001B[0m");
    }
    public void mostrarCantidadDeJugadoresIncorrecta() {
        System.out.println("\u001B[31m ¡La cantidad de jugadores debe ser entre 2 y 4!");
        System.out.print("\u001B[0m");
    }
    public String inputNombreJugador(Integer numeroJugador) {
        System.out.print("\u001B[33mIngrese el nombre del jugador " + numeroJugador + ": ");
        System.out.print("\u001B[0m");

        // Usa next() para leer y combina con una verificación de espacios
        String nombre = sc.next();
        if (sc.hasNextLine()) {
            nombre += sc.nextLine(); // Si hay más texto después, lo concatenamos
        }
        return nombre.trim(); // Quitamos espacios al inicio y final
    }
    public int inputSeleccionarColor() {
        System.out.print("\u001B[33m Ingrese el color de los puntos a guardar (1-4): ");
        System.out.print("\u001B[0m");

        // Asegúrate de que la entrada sea un número entero
        while (!sc.hasNextInt()) {
            System.out.println("Por favor ingrese un número válido.");
            sc.next(); // Descarta la entrada incorrecta
        }

        // Devuelve el número ingresado
        return sc.nextInt();
    }
    public int inputSeleccionarModoDeInsercion(){
        System.out.print("\u001B[33m Ingrese el modo de inserción 1. MAYOR 2. MENOR: ");
        System.out.print("\u001B[0m");
       return sc.nextInt();
    }
    public int inputSeleccionarAccionJugada(){
        System.out.print("\u001B[33m1. Jugar 2. Pasar turno : ");
        System.out.print("\u001B[0m");
        return sc.nextInt();
    }
    public void mostrarNombreTurnoJugador(String nombre){
        System.out.println("\u001B[32mTurno de: ["+nombre+"]");
        System.out.print("\u001B[0m");
    }
    public int inputIntentarDeNuevoInsercion(){
        System.out.print("\u001B[33m1. IntentarDenuevo 2. Pasar turno (falta) : ");
        System.out.print("\u001B[0m");
        return sc.nextInt();
    }
    public void mostrarGameover() {
        System.out.print("\u001B[31m");
        System.out.println("=======================");
        System.out.println("JUEGO TERMINADO");
        System.out.println("=======================");
        System.out.print("\u001B[0m");
    }
}
