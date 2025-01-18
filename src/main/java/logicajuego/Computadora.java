/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

/**
 *
 * @author ariel
 */
public class Computadora extends Jugador {

    public Computadora() {
        super();
    }

    @Override
    public String toString() {
       return this.getNombre();
    }
    
    private int minimax(Tablero tablero, boolean esMaximizador, Simbolo miSimbolo) {
        Simbolo rival = (miSimbolo == Simbolo.X) ? Simbolo.O : Simbolo.X;

        // Evaluar el estado actual del tablero
        if (esVictoria(tablero, miSimbolo)) return 10;  // Victoria para la computadora.
        if (esVictoria(tablero, rival)) return -10;     // Victoria para el humano.
        if (Tablero.isFull(tablero)) return 0;          // Empate.

        if (esMaximizador) {
            int mejorValor = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.getTablero()[i][j] == Simbolo.vacio) {
                        Tablero copia = tablero.copiarTablero();
                        copia.setTablero(i, j, miSimbolo); // Simular movimiento.

                        int valor = minimax(copia, false, miSimbolo);
                        mejorValor = Math.max(mejorValor, valor);
                    }
                }
            }
            return mejorValor;
        } else {
            int peorValor = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.getTablero()[i][j] == Simbolo.vacio) {
                        Tablero copia = tablero.copiarTablero();
                        copia.setTablero(i, j, rival); // Simular movimiento del oponente.

                        int valor = minimax(copia, true, miSimbolo);
                        peorValor = Math.min(peorValor, valor);
                    }
                }
            }
            return peorValor;
        }
    }

    private boolean esVictoria(Tablero tablero, Simbolo simbolo) {
        Simbolo[][] t = tablero.getTablero();
        for (int i = 0; i < 3; i++) {
            if (t[i][0] == simbolo && t[i][1] == simbolo && t[i][2] == simbolo) return true;
            if (t[0][i] == simbolo && t[1][i] == simbolo && t[2][i] == simbolo) return true;
        }
        if (t[0][0] == simbolo && t[1][1] == simbolo && t[2][2] == simbolo) return true;
        if (t[0][2] == simbolo && t[1][1] == simbolo && t[2][0] == simbolo) return true;
        return false;
    }
    
}
