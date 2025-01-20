/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

import com.espol.proyectoed.arbol.Tree;



public class Juego {
    private MododeJuego modoDeJuego; // Modo de juego (e.g., Persona vs Computadora)
    private Jugador jugadorUno;      // Primer jugador
    private Jugador jugadorDos;      // Segundo jugador
    private Simbolo simboloInicial; // Símbolo que comienza el juego
    private Tree<Tablero> tablero;        // Representa el tablero del juego

    // Constructor
    public Juego() {
        this.modoDeJuego = null;
        this.jugadorUno = null;
        this.jugadorDos = null;
        this.simboloInicial = null;
        this.tablero = new Tree<>(new Tablero());
    }

    // Métodos de configuración
    public void setModoDeJuego(MododeJuego modoDeJuego) {
        this.modoDeJuego = modoDeJuego;
    }

    public void setJugadorUno(Jugador jugadorUno) {
        this.jugadorUno = jugadorUno;
    }

    public void setJugadorDos(Jugador jugadorDos) {
        this.jugadorDos = jugadorDos;
    }

    public void setSimboloInicial(Simbolo simboloInicial) {
        this.simboloInicial = simboloInicial;
    }

    // Métodos de acceso
    public MododeJuego getModoDeJuego() {
        return modoDeJuego;
    }

    public Jugador getJugadorUno() {
        return jugadorUno;
    }

    public Jugador getJugadorDos() {
        return jugadorDos;
    }

    public Simbolo getSimboloInicial() {
        return simboloInicial;
    }

    public Tree<Tablero> getTablero() {
        return tablero;
    }
public void dtagame(Juego juego) {
        this.modoDeJuego = juego.getModoDeJuego();
        this.jugadorUno = juego.getJugadorUno();
        this.jugadorDos = juego.getJugadorDos();
        this.simboloInicial = juego.getSimboloInicial();
        this.tablero = juego.getTablero();
    }
    
    // Verificar si hay un ganador
    public static boolean verificarGanador(Tablero tablero, Simbolo simbolo) {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if ((tablero.getTablero()[i][0].equals(simbolo) && tablero.getTablero()[i][1].equals(simbolo) && tablero.getTablero()[i][2].equals(simbolo)) ||
                    (tablero.getTablero()[0][i].equals(simbolo) && tablero.getTablero()[1][i].equals(simbolo) && tablero.getTablero()[2][i].equals(simbolo))) {
                return true;
            }
        }

        // Verificar diagonales
        return (tablero.getTablero()[0][0].equals(simbolo) && tablero.getTablero()[1][1].equals(simbolo) && tablero.getTablero()[2][2].equals(simbolo)) ||
                (tablero.getTablero()[0][2].equals(simbolo) && tablero.getTablero()[1][1].equals(simbolo) && tablero.getTablero()[2][0].equals(simbolo));
    }

    // Recomendar una jugada usando un bot
    public static int[] recomendacion(Tablero tablero, Simbolo simbolo) {
    // Crear una instancia del bot (Computadora)
    Computadora bot = new Computadora(simbolo);

    // Obtener el símbolo del oponente
    Simbolo simboloOponente = (simbolo == Simbolo.X) ? Simbolo.O : Simbolo.X;

    // El bot calcula el mejor movimiento basado en el tablero actual
    Tablero nuevoTablero = bot.tomarDecision(tablero.copiarTablero(), simboloOponente);

    // Comparar el tablero original con el nuevo para identificar la jugada
    int[] posicion = new int[2];
    for (int i = 0; i < nuevoTablero.getTablero().length; i++) {
        for (int j = 0; j < nuevoTablero.getTablero()[i].length; j++) {
            if (!nuevoTablero.getTablero()[i][j].equals(tablero.getTablero()[i][j])) {
                posicion[0] = i;
                posicion[1] = j;
                 // Devolver la posición de la jugada
            }
        }
    }return posicion;}

    // Reiniciar el juego
 

    @Override
    public String toString() {
        return "Game{" +
               "modoDeJuego=" + modoDeJuego +
               ", jugadorUno=" + jugadorUno +
               ", jugadorDos=" + jugadorDos +
               ", simboloInicial=" + simboloInicial +
               ", tablero=" + tablero +
               '}';
    }
}
 


