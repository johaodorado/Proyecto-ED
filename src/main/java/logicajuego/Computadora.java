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
    
}
