/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

import com.mycompany.proyecto.ed.TertiaryController;

/**
 *
 * @author ariel
 */
public abstract class Jugador {
    private String nombre;
    Simbolo simbolo;

    public Jugador(){}
    
    public Jugador(String nombre, Simbolo simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }
    
    @Override
    public abstract String toString();
public abstract void play(Jugador opponentPlayer, TertiaryController iJuego);

    public boolean canPlay(Tablero matrix){
        boolean xWon = Juego.verificarGanador(matrix, Simbolo.X);
        boolean oWon = Juego.verificarGanador(matrix, Simbolo.O);

        boolean isFull = Tablero.isFull(matrix);

        return !xWon && !oWon && !isFull;
    }

}
