/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto.ed;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author genzo
 */
public class EmpateController {
    @FXML
    private Button returnButton;
     @FXML
    private Label playerOnePoints;

    @FXML
    private Label playerTwoPoints;
    
    public void initData(int puntosJugadorUno, int puntosJugadorDos) {
        // Mostrar los puntos actualizados
        playerOnePoints.setText("Jugador 1: " + puntosJugadorUno + " puntos");
        playerTwoPoints.setText("Jugador 2: " + puntosJugadorDos + " puntos");
    }
    
    @FXML
    public void handleReturn() {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }
}
