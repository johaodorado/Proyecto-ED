/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

/**
 *
 * @author genzo
 */
public class ComputadoravsComputadora extends MododeJuego {
       @Override
    public String toString() {
        return "Computadora vs Computadora";
    }

    @Override
    public Jugador j1() {
        return new Computadora();
    }

    @Override
    public Jugador j2() {
        return new Computadora();
    }
}

