package Modelo.Tablas;

public class TablaFaltas extends Tabla{

    public TablaFaltas(){
        super(5);
    }
    @Override

    public int[] getTabla(){
        return this.tabla.clone();
    }

    public boolean setFalta(){
        if(!tablaLLena()){
            for(int i =0; i < tabla.length; i++){
                if(tabla[i]==0){
                    tabla[i] = 1;
                    return true;
                }
            }
        }
        System.out.println("Limite de faltas");
        return false;
    }

    public void sacarFalta(){
        if(!tablaLLena()){
            for(int i = tabla.length - 1; i >= 0; i--){
                if(tabla[i] != 0){
                    tabla[i] = 0;
                    break;
                }
            }
        }
    }

    public int calcularPenalizacion() {
        int penalizacion = 0;
        // Itera a través del array de bits
        for (int i = 0; i < tabla.length; i++) {
            // Si el bit en la posición i está activado (es 1)
            if (tabla[i] == 1) {
                penalizacion += (i + 1); // Suma la posición (i + 1) según la lógica
            }
        }
        return penalizacion; // La penalización será la suma de los valores de las posiciones con 1
    }

    @Override
    public boolean tablaLLena() {
        // Recorremos toda la tabla y si encontramos un 0, devolvemos false
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == 0) {
                return false;  // Si encontramos un 0, la tabla no está llena
            }
        }
        return true;  // Si no encontramos ningún 0, la tabla está llena
    }
    @Override
    public String toString() {
        String resultado = "ESTADO TABLA DE FALTAS: \n";
        for (int i = 0; i < tabla.length; i++) {
            resultado += "["+(i+1)+":"+tabla[i]+"]";
        }
        return resultado.trim();
    }
}
