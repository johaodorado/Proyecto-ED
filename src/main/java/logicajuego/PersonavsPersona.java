/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

/**
 *
 * @author genzo
 */
public class PersonavsPersona extends MododeJuego {
  @Override
    public String toString() {
        return "Persona vs Persona";
    }

  @Override
    public Jugador j1() {
        return new Persona();
    }

  @Override
    public Jugador j2() {
        return new Persona();
    }
}    

