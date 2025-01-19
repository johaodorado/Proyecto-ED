/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

/**
 *
 * @author genzo
 */
public class InstanciaJuego {
    private final static InstanciaJuego instance = new InstanciaJuego();

    public static InstanciaJuego getInstance() {
        return instance;
    }

    private final Juego game = new Juego();

    public Juego getGameData() {
        return game;
    }
}

