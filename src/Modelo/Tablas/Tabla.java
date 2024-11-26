package Modelo.Tablas;

public abstract class Tabla {
    public int getSumaTotal;
    protected int[] tabla; // Arreglo que representa la tabla

    // Constructor que inicializa la tabla con el tamaño proporcionado
    public Tabla(int tamano) {
        tabla = new int[tamano];
        // Inicializamos la tabla con ceros
        for (int i = 0; i < tamano; i++) {
            tabla[i] = 0;
        }
    }

    // Método para verificar si un valor ya está presente en la tabla
    public boolean contiene(int valor) {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == valor) {
                return true;
            }
        }
        return false;
    }

    // Método abstracto que permite a las subclases definir cómo insertar en la tabla
   // public abstract boolean setPunto(int valor);

    // devuelve el contenido de la tabla.
    public int[] getTabla(){
        return this.tabla.clone();
    }

    public boolean tablaLLena(){return tabla[tabla.length-1] != 0;}
    public boolean tablaVacia(){return tabla[0] == 0;}

    public abstract String toString();
}
