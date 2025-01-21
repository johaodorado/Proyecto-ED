/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

import com.mycompany.proyecto.ed.TertiaryController;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Computadora extends Jugador {

    public Computadora() {
        super();
    }

    public Computadora(Simbolo simbolo) {
        super("Computadora", simbolo);
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    // Método principal para decidir jugadas utilizando Minimax
    public Tablero tomarDecision(Tablero tablero, Simbolo simboloOponente) {
        int mejorValor = Integer.MIN_VALUE;
        Tablero mejorTablero = null;

        // Generar todas las posibles jugadas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero.getTablero()[i][j] == Simbolo.vacio) {
                    Tablero copia = tablero.copiarTablero();
                    copia.setTablero(i, j, this.simbolo);

                    // Evaluar la jugada usando Minimax
                    int valor = minimax(copia, 0, false, this.simbolo, simboloOponente);

                    if (valor > mejorValor) {
                        mejorValor = valor;
                        mejorTablero = copia;
                    }
                }
            }
        }

        return mejorTablero;
    }

    // Implementación del algoritmo Minimax
    private int minimax(Tablero tablero, int profundidad, boolean esMax, Simbolo simboloActual, Simbolo simboloOponente) {
        // Verificar si el juego termina
        if (Juego.verificarGanador(tablero, simboloActual)) {
            return 10 - profundidad; // Victoria del bot
        }
        if (Juego.verificarGanador(tablero, simboloOponente)) {
            return profundidad - 10; // Victoria del oponente
        }
        if (Tablero.isFull(tablero)) {
            return 0; // Empate
        }

        if (esMax) {
            int mejorValor = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.getTablero()[i][j] == Simbolo.vacio) {
                        Tablero copia = tablero.copiarTablero();
                        copia.setTablero(i, j, simboloActual);
                        int valor = minimax(copia, profundidad + 1, false, simboloActual, simboloOponente);
                        mejorValor = Math.max(mejorValor, valor);
                    }
                }
            }
            return mejorValor;
        } else {
            int mejorValor = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero.getTablero()[i][j] == Simbolo.vacio) {
                        Tablero copia = tablero.copiarTablero();
                        copia.setTablero(i, j, simboloOponente);
                        int valor = minimax(copia, profundidad + 1, true, simboloActual, simboloOponente);
                        mejorValor = Math.min(mejorValor, valor);
                    }
                }
            }
            return mejorValor;
        }
    }

    @Override
    public void play(Jugador opponentPlayer, TertiaryController iJuego) {
        boolean canPlay = super.canPlay(iJuego.getTablero());
        if (!canPlay) {
            iJuego.checkWinners();
            return;
        }

        iJuego.setPlayerTurn(this);
        iJuego.clearBestPlay();

        Tablero tablero = iJuego.getTablero();
        Tablero newTablero = tomarDecision(tablero, opponentPlayer.getSimbolo());
        iJuego.setMatrix(newTablero);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            Platform.runLater(iJuego::rePaint);
            opponentPlayer.play(this, iJuego);
        });
        pause.play();
    }
}
