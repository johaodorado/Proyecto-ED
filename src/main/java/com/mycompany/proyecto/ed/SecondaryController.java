package com.mycompany.proyecto.ed;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logicajuego.ComputadoravsComputadora;
import logicajuego.InstanciaJuego;
import logicajuego.Juego;
import logicajuego.Jugador;
import logicajuego.MododeJuego;
import logicajuego.PersonaVsComputadora;
import logicajuego.PersonavsPersona;
import logicajuego.Simbolo;


public class  SecondaryController{
 
    @FXML
    private ImageView returnButton;
    @FXML
    private HBox gameModes;
    @FXML
    private VBox chooseNames;
    @FXML
    private Label playerOneType;
    @FXML
    private Label playerTwoType;
    @FXML
    private TextField playerOneName;
    @FXML
    private TextField playerTwoName;
    @FXML
    private ComboBox<Simbolo> orders;
    private Juego game;
    private MododeJuego[] gameModesList;
    public void initialize() {
        game = new Juego();
        this.setInitialActions();
        this.initializeGameModes();
        chooseNames.setVisible(false);
        orders.getItems().addAll(Simbolo.X, Simbolo.O);
    }
    private void returnHome() {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void initializeGameModes() {
        gameModesList = new MododeJuego[3];
        gameModesList[0] = new ComputadoravsComputadora();
        gameModesList[1] = new PersonaVsComputadora();
        gameModesList[2] = new PersonavsPersona();
    }
    private void setInitialActions() {
        returnButton.setOnMouseClicked((event) -> {
            returnHome();
        });
    }
    @FXML
    private void setGameMode(MouseEvent e) {
        gameModes.getChildren().forEach((child) -> {
            VBox box = (VBox) child;
            if (box.equals(e.getSource())) {
                box.setStyle("-fx-background-color: #233542; -fx-background-radius: 10px;");
                game.setModoDeJuego(gameModesList[gameModes.getChildren().indexOf(box)]);
            } else {
                box.setStyle("");
            }
        });
        chooseNames.setVisible(true);
        setPlayerTypes();
    }
    
    private void setPlayerTypes() {
        String[] playerType = game.getModoDeJuego().toString().split(" vs ");
        playerOneType.setText(playerType[0]);
        playerTwoType.setText(playerType[1]);
    }
    private void setPlayerOne() {
    MododeJuego gameMode = game.getModoDeJuego();
    if (gameMode == null) {
        throw new IllegalStateException("Modo de juego no seleccionado.");
    }
    Jugador player = gameMode.j1();
    player.setNombre(playerOneName.getText().isEmpty() ? "Jugador 1" : playerOneName.getText());
    player.setSimbolo(Simbolo.O);
    game.setJugadorUno(player);
}
  private void setPlayerTwo() {
    MododeJuego gameMode = game.getModoDeJuego();
    if (gameMode == null) {
        throw new IllegalStateException("Modo de juego no seleccionado.");
    }
    Jugador player = gameMode.j2();
    player.setNombre(playerTwoName.getText().isEmpty() ? "Jugador 2" : playerTwoName.getText());
    player.setSimbolo(Simbolo.X);
    game.setJugadorDos(player);
}

   @FXML
    private void play() {
    setPlayerOne();
    setPlayerTwo();
    game.setSimboloInicial(orders.getValue());
    InstanciaJuego.getInstance().getGameData().dtagame(game);
    try {
        App.setRoot("tertiary");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}}
    
   
   
