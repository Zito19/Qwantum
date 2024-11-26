package Modelo.Tablas;

public class TablaResultados extends Tabla {
    private int[] tablaResultados;

    public TablaResultados() {
        super(6); // Suponiendo que tendrá 6 posiciones para los resultados
        this.tablaResultados = new int[6];  // Inicializa la tabla de resultados con 6 posiciones
    }

    public void calcularResultado(TablaPuntos[] tablaPuntos) {
        // Reiniciar tabla de Resultados para evitar acumulaciones
        for (int i = 0; i < tablaResultados.length; i++) {
            tablaResultados[i] = 0;
        }

        // Sumar los valores de las tablas de puntos por color
        for (int i = 0; i < tablaPuntos.length; i++) {
            int[] tabla = tablaPuntos[i].getTabla();

            // Sumar los valores de cada posición en la tabla individual a tablaResultados
            for (int j = 0; j < tabla.length; j++) {
                tablaResultados[j] += tabla[j];
            }
        }
    }

    // Metodo para obtener la tabla de resultados
    public int[] getTabla() {
        return tablaResultados;
    }

    // Mostrar la tabla de resultados (si es necesario)
    public void mostrarTablaResultados() {
        System.out.println("Tabla de Resultados:");
        for (int i = 0; i < tablaResultados.length; i++) {
            System.out.println("Posición " + (i + 1) + ": " + tablaResultados[i]);
        }
    }

    @Override
    public String toString() {
        return "";
    }

    public int getSumaTotal() {
        int suma = 0;
        // Recorrer todos los elementos de la tabla de resultados y sumarlos
        for (int i = 0; i < tablaResultados.length; i++) {
            suma += tablaResultados[i];
        }
        return suma;
    }
    @Override
    public boolean tablaLLena() {
        // Recorre toda la tabla y verifica si alguna posición es igual a 0
        for (int i = 0; i < tablaResultados.length; i++) {
            if (tablaResultados[i] == 0) {
                return false;  // Si se encuentra un 0, la tabla no está llena
            }
        }
        return true;  // Si no se encontró ningún 0, la tabla está llena
    }
}

