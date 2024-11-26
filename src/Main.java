import Controlador.Controlador;
import Modelo.ModeloJuego;
import Modelo.Util.Elemento;
import Vista.Vista;

public class Main {
    public static void main(String[] args) {
        ModeloJuego modeloJuego = ModeloJuego.getInstancia();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modeloJuego,vista);
        controlador.jugar();

    }
}

