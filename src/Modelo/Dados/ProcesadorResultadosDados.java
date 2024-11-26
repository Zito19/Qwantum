package Modelo.Dados;

import Modelo.Util.Color;
import Modelo.Util.Elemento;
import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ProcesadorResultadosDados implements Observable {
    private static ProcesadorResultadosDados instancia; // Instancia única de la clase
    private TiradorDeDados tiradorDeDados = TiradorDeDados.getInstancia();
    private List<Observer> observadores = new ArrayList<>();
    private Elemento[] resultadosXColor; // Atributo para guardar los resultados procesados

    // Constructor privado para evitar la instanciación directa
    private ProcesadorResultadosDados() {}

    // Metodo para obtener la instancia única
    public static ProcesadorResultadosDados getInstancia() {
        if (instancia == null) {
            instancia = new ProcesadorResultadosDados();
        }
        return instancia;
    }

    // Procesa los resultados y notifica a los observadores
    public void procesarResultadosDados() {
        // Crear un arreglo de Elemento para cada color, inicializado con valor 0
        resultadosXColor = new Elemento[Color.cantidadColores()];
        for (int i = 0; i < resultadosXColor.length; i++) {
            resultadosXColor[i] = new Elemento(0, Color.getColorPorNumero(i));
        }

        // Sumar los valores de los dados al color correspondiente
        for (Elemento elemento : tiradorDeDados.getResultadoDados()) {
            int valor = elemento.getValor();

            if (elemento.getColor() == Color.BLANCO) {
                // Si el color es blanco, sumamos su valor a todos los colores
                for (Elemento resultado : resultadosXColor) {
                    resultado.setValor(resultado.getValor() + valor);
                }
            } else {
                // De lo contrario, sumamos el valor al color correspondiente
                int i = elemento.getColorOrdinal();
                resultadosXColor[i].setValor(resultadosXColor[i].getValor() + valor);
            }
        }

        // Notificar a los observadores
        notificar();
    }
    public void mostrarResultadosPorColorRestantes(){
        notificar();
    }
    // Reinicia los resultados de un color específico a 0
    public void reiniciarColor(Color color) {
        for (Elemento elemento : resultadosXColor) {
            if (elemento.getColor().equals(color)) {
                elemento.setValor(0); // Establece el valor a 0
                break; // Sale del bucle al encontrar el color
            }
        }
        notificar(); // Notifica a los observadores sobre el cambio
    }

    public Elemento getResultadoPorColor(Color color) {
        for (Elemento elemento : resultadosXColor) {
            if (elemento.getColor().equals(color)) {
                return elemento; // Devuelve el Elemento si el color coincide
            }
        }
        return null; // Si no se encuentra el color, retorna null o un valor por defecto si prefieres
    }

    // Devuelve los resultados almacenados
    public Elemento[] getResultadosXColor() {
        return resultadosXColor.clone();
    }

    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void notificar() {
        for (Observer observador : observadores) {
            observador.actualizarResultadosXColor(resultadosXColor.clone());
        }
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }
}
