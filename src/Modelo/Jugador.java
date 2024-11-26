package Modelo;

import Modelo.Tablas.TablaGeneral;


public class Jugador {
        private static int contadorIds = 0; // Contador estático para generar IDs únicos
        private int idJugador; // ID único del jugador
        private String nombre; // Nombre del jugador
        private TablaGeneral tabla; // Instancia de TablaGeneral
        private int turnosSalteados = 0;

        // Constructor que inicializa el nombre y asigna un ID único
        public Jugador(String nombre) {
            this.nombre = nombre;
            this.idJugador = ++contadorIds; // Incrementa el contador y asigna el ID
            this.tabla = new TablaGeneral(); // Inicializa la tabla
        }
        public int getIdJugador() {
            return idJugador;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public TablaGeneral getTablaJugador(){
            return this.tabla;
        }

        public int getTurnosSalteados() {
        return turnosSalteados;
        }
        public void setSaltoturno() {
        this.turnosSalteados += 1;
        }
        public void resetTurnosSalteados() {
            this.turnosSalteados = 0;
        }
}
