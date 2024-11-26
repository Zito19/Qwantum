package Modelo.Tablas;

public class TablaPuntos extends Tabla {

    private static final int TOTAL_ELEMENTOS = 6;
    private static final int MAYORES = 4; // Los primeros 4 son mayores
    private static final int MENORES = 2; // Los últimos 2 son menores

    private int[] tablaMayores = new int[MAYORES];
    private int[] tablaMenores = new int[MENORES];

    public TablaPuntos() {
        super(TOTAL_ELEMENTOS); // Inicializa la tabla con 6 posiciones
    }

    // Metodo para verificar si el valor se puede insertar en el arreglo de mayores
    public boolean puedeInsertarMayores(int valor) {
        for (int i = 0; i < tablaMayores.length; i++) {
            if (valor > tablaMayores[i] && tablaMayores[i] == 0) {
                return true;
            } else if (valor <= tablaMayores[i]) {
                return false;  // menor o igual no agrega
            }
        }
        return false;
    }

    // Metodo para verificar si el valor se puede insertar en el arreglo de menores
    public boolean puedeInsertarMenores(int valor) {
        if (valor > 0) {
            for (int i = 0; i < tablaMenores.length; i++) {
                if (tablaMenores[i] == 0) { // busca la primera posición vacía
                    if (i == 0 || valor < tablaMenores[0]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean agregarPuntos(int valor, boolean booleano) {
        //INSERCION: TRUE = MENORES || FALSE = MAYORES
        // Verificar primero si el valor puede ser insertado en la sección correspondiente
        if (booleano) {
            if (puedeInsertarMenores(valor)) {
                insertarEnMenores(valor); // Insertar en menores si es posible
                return true;  // Inserción exitosa
            } else {
                System.out.println("No se pudo insertar el valor " + valor + " en la tabla de menores.");
                return false; // No se pudo insertar en menores
            }
        } else {
            if (puedeInsertarMayores(valor)) {
                insertarEnMayores(valor); // Insertar en mayores si es posible
                return true;  // Inserción exitosa
            } else {
                System.out.println("No se pudo insertar el valor " + valor + " en la tabla de mayores.");
                return false; // No se pudo insertar en mayores
            }
        }
    }


    // Metodo para insertar en mayores
    private void insertarEnMayores(int valor) {
        for (int i = 0; i < tablaMayores.length; i++) {
            if (tablaMayores[i] == 0) {
                tablaMayores[i] = valor;
                tabla[i] = valor; // Actualizar la tabla base (tabla)
                break; // Salir del loop después de insertar el valor
            }
        }
    }

    // Metodo para insertar en menores
    private void insertarEnMenores(int valor) {
        for (int i = 0; i < tablaMenores.length; i++) {
            if (tablaMenores[i] == 0) {
                tablaMenores[i] = valor;
                tabla[MAYORES + i] = valor; // Actualizar la tabla base (tabla)
                break; // Salir del loop después de insertar el valor
            }
        }
    }

    @Override
    public boolean tablaLLena() {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == 0) {
                return false; // Si hay alguna posición vacía, la tabla no está llena
            }
        }
        return true; // Si no hay posiciones vacías, la tabla está llena
    }

    // Metodo para mostrar la tabla de puntos
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("CONTENIDO DE LA TABLA:\n");
        resultado.append("Mayores: ");
        for (int i = 0; i < tablaMayores.length; i++) {
            resultado.append("[" + (i + 1) + ":" + tablaMayores[i] + "] ");
        }
        resultado.append("| Menores: ");
        for (int i = 0; i < tablaMenores.length; i++) {
            resultado.append("[" + (i + 1 + MAYORES) + ":" + tablaMenores[i] + "] ");
        }
        return resultado.toString().trim();
    }
}


//// Metodo para evaluar en qué sección se puede insertar el valor
//public int evaluarValor(int valor){
//    // 0 - ninguno; 1- mayores; 2-menores; 3;ambos
//    int cont = 0;
//    if(valor > 0) {
//        if (puedeInsertarMayores(valor)) {
//            cont++;
//        }
//        if (puedeInsertarMenores(valor)) {
//            cont += 2;
//        }
//    }
//    return cont;
//}
