package com.mycompany.proyecto.ed;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {
   @FXML
    private Button salir;

    @FXML
    private Button jugar;

    public void initialize() {
        this.setInitialActions();
    }

    private void exit() {
        System.exit(0);
    }

 

    private void play() {
        try {
            App.setRoot("secondary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInitialActions() {
        salir.setOnAction(e -> exit());
        jugar.setOnAction( e -> play());

    }
  
    }

