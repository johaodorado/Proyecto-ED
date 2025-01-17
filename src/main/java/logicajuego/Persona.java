/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

/**
 *
 * @author ariel
 */
public class Persona extends Jugador {

    public Persona() {
        super();
    }

    @Override
    public String toString() {
        return "Persona:"+"nombre: "+this.getNombre()+"simbolo: "+this.getSimbolo();
    }
    
}
