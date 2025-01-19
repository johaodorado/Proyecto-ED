/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

import com.espol.proyectoed.arbol.Tree;
/**
 *
 * @author ariel
 */import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Computadora extends Jugador {

    // Constructor predeterminado
    public Computadora() {
        super();
    }

    // Constructor con símbolo
    public Computadora(Simbolo simbolo) {
        super("Computadora", simbolo);
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    // Método principal para decidir jugadas
    public Tablero tomarDecision(Tablero tablero, Simbolo simboloOponente) {
        List<Integer> utilidades = new ArrayList<>();
        List<Integer> utilidadesParciales = new ArrayList<>();

        // Si el tablero tiene 8 movimientos, realiza una decisión final
        if (tablero.size() == 8) {
            tomarDecisionFinal(tablero);
            return tablero;
        }

        // Crear árbol de jugadas
        Tree<Tablero> arbolJugadas = new Tree<>(tablero);
        pensarProximasJugadas(arbolJugadas, simbolo);

        // Evaluar las utilidades de cada jugada
        for (Tree<Tablero> hijo : arbolJugadas.getChildren()) {
            pensarProximasJugadas(hijo, simboloOponente);
            utilidadesParciales.clear();

            for (Tree<Tablero> nieto : hijo.getChildren()) {
                Tablero tableroHijo = nieto.getRoot();
                int utilidad = tableroHijo.calcularUtilidad(simbolo);
                utilidadesParciales.add(utilidad);
            }

            utilidades.add(Collections.min(utilidadesParciales)); // Minimizar utilidad del oponente
        }

        // Escoger la jugada con la mayor utilidad
        int maxUtilidad = Collections.max(utilidades);
        return arbolJugadas.getChildren().get(utilidades.indexOf(maxUtilidad)).getRoot();
    }

    // Generar todas las posibles jugadas desde el tablero actual
    private void pensarProximasJugadas(Tree<Tablero> arbol, Simbolo simbolo) {
        Tablero tableroActual = arbol.getRoot();

        for (int i = 0; i < tableroActual.getTablero().length; i++) {
            for (int j = 0; j < tableroActual.getTablero()[i].length; j++) {
                if (tableroActual.getTablero()[i][j] == Simbolo.vacio) {
                    Tablero nuevo = tableroActual.copiarTablero(); // Crear una copia del tablero actual
                    nuevo.getTablero()[i][j] = simbolo; // Realizar la jugada
                    arbol.addChildren(nuevo); // Agregar el tablero resultante al árbol
                }
            }
        }
    }

    // Realiza una decisión final para completar el tablero
    private void tomarDecisionFinal(Tablero tablero) {
        for (int i = 0; i < tablero.getTablero().length; i++) {
            for (int j = 0; j < tablero.getTablero()[i].length; j++) {
               if (tablero.getTablero()[i][j].equals(Simbolo.vacio)){tablero.getTablero()[i][j] = simbolo;}
                    return; // Realiza el primer movimiento disponible y termina
                }
            }
        }
    

    
    
}

