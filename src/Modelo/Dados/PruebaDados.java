package Modelo.Dados;

import Modelo.Util.Elemento;

public class PruebaDados {
    public static void main(String[] args) {
        // Crear un arreglo que contenga 7 dados (6 comunes + 1 blanco)
        Dado[] dados = new Dado[7];

        // Inicializar los 6 dados comunes
        for (int i = 0; i < 6; i++) {
            dados[i] = new Dado();
        }

        // Inicializar el dado blanco
        dados[6] = new DadoBlanco();

        // Mostrar la configuración de los dados
        for (int i = 0; i < dados.length; i++) {
            System.out.println("Modelo.Dados.Dado " + (i + 1) + ":\n" + dados[i]);
        }

        // Tirar todos los dados
        Elemento resultadoTirada;
        for (int i = 0; i < 5; i++) {
            System.out.println("\nTirada " + (i + 1) + ":");
            for (int j = 0; j < dados.length; j++) {
                resultadoTirada = dados[j].tirarDado();
                System.out.println("Modelo.Dados.Dado " + (j + 1) + ": " + resultadoTirada);
            }
        }
    }
}

