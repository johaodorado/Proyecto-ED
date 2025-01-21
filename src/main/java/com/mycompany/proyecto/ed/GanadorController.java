/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto.ed;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logicajuego.Jugador;
import logicajuego.Simbolo;

/**
 * FXML Controller class
 *
 * @author genzo
 */
    public class GanadorController {

        

        @FXML
        private ImageView icon;

        @FXML
        private Button returnButton;

        public void initData(Jugador winner) {
            //playerName.setText(winner.getNombre());
            Simbolo symbol = winner.getSimbolo();
            setSymbol(symbol);
        }

        @FXML
        public void handleReturn() {
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
        }

        private void setSymbol(Simbolo symbol) {
            if (symbol.equals(Simbolo.X)) {
                icon.setImage(new Image("file:src/main/resources/imagenes/ximagen.png"));
            } else {
                icon.setImage(new Image("file:src/main/resources/imagenes/circle.png"));
            }
        }
    }