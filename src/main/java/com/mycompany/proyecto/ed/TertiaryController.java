/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto.ed;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logicajuego.InstanciaJuego;
import logicajuego.Juego;
import logicajuego.Jugador;
import logicajuego.Simbolo;
import logicajuego.Tablero;

/**
 * FXML Controller class
 *
 * @author genzo
 */
public class TertiaryController {

  @FXML
    private Label gameMode;

    @FXML
    private Label playerOne;

    @FXML
    private Label playerTwo;

    @FXML
    private Button recommend;

    @FXML
    private Label bestPlay;

    @FXML
    private GridPane board;

    @FXML
    public static GridPane gameMatrix;

    private Juego game;

    private Tablero tablero;

    private Jugador turno;

    public void initialize() {
        this.game = InstanciaJuego.getInstance().getGameData();
        gameMode.setText(game.getModoDeJuego().toString());
        playerOne.setText(game.getJugadorUno().getNombre());
        playerTwo.setText(game.getJugadorDos().getNombre());

        // Not always root, because may be opening a saved game
        this.tablero = game.getTablero().getRoot();
        this.paintMatrix(this.tablero);
        bestPlay.setText("");

        recommend.setOnMouseClicked(e -> {
            int[] best = Juego.recomendacion(this.tablero, this.turno.getSimbolo());
            bestPlay.setText("Se recomienda jugar en la fila " + (best[0]+1)  + " columna " + (best[1]+1) + ".");
        });

        this.play();
    }

    @FXML
    private void returnHome() {
        try {
            App.setRoot("Primary");
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setX(int row, int col) {
        setImage(row, col, "file:src/main/resources/imagenes/ximagen.png");
    }

    public void setO(int row, int col) {
        setImage(row, col, "file:src/main/resources/imagenes/circle.png");
    }



    private void setImage(int row, int col, String imgPath) {
        VBox cell = getCell(row, col);
        assert cell != null;
        ImageView imgView = (ImageView) cell.getChildren().get(0);

        Image img = new Image(imgPath);
        imgView.setImage(img);
    }

    private void disableCell(int row, int col) {
        VBox cell = getCell(row, col);
        assert cell != null;
        cell.setDisable(true);
    }

    public void clearImage(int row, int col) {
        Simbolo[][] matrixPlay = this.tablero.getTablero();
        Simbolo symbol = matrixPlay[row][col];
        boolean isEmpty = symbol.equals(Simbolo.vacio);

        if (isEmpty) {
            VBox cell = getCell(row, col);
            assert cell != null;
            ImageView img = (ImageView) cell.getChildren().get(0);
            img.setImage(null);
        }
    }

    private VBox getCell(Integer row, Integer col) {
        for (Node node : board.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
            if (Objects.equals(rowIndex, row) && Objects.equals(colIndex, col)) {
                return (VBox) node;
            }
        }
        return null;
    }

    public GridPane getBoard(){
        return board;
    }

    public void paintMatrix(Tablero tablero) {
        for (int i = 0; i < tablero.getTablero().length; i++) {
            for (int j = 0; j < tablero.getTablero()[i].length; j++) {
                if (tablero.getTablero()[i][j].equals(Simbolo.X)) {
                    setX(i, j);
                } else if (tablero.getTablero()[i][j].equals(Simbolo.O)) {
                    setO(i, j);
                } else {
                    clearImage(i, j);
                }
            }
        }
    }

    public void rePaint() {
        for (int i = 0; i < tablero.getTablero().length; i++) {
            for (int j = 0; j < tablero.getTablero()[i].length; j++) {
                if (tablero.getTablero()[i][j].equals(Simbolo.X)) {
                    setX(i, j);
                    disableCell(i, j);
                } else if (tablero.getTablero()[i][j].equals(Simbolo.O)) {
                    setO(i, j);
                    disableCell(i, j);
                } else {
                    clearImage(i, j);
                }
            }
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setMatrix(Tablero tablero) {
        this.tablero = tablero;
    }

    private void play() {
        Simbolo startingSymbol = game.getSimboloInicial();
        Jugador one = game.getJugadorUno();
        Jugador two = game.getJugadorDos();

        boolean startsOne = startingSymbol.equals(one.getSimbolo());

        Jugador startingPlayer = startsOne ? one : two;
        Jugador secondPlayer = startsOne ? two : one;

        startingPlayer.play(secondPlayer, this);
    }

    public void checkWinners() {
        boolean xWon = Juego.verificarGanador(this.tablero, Simbolo.X);
        boolean oWon = Juego.verificarGanador(this.tablero, Simbolo.O);

        if (xWon || oWon) {
            Jugador winner = oWon ? game.getJugadorUno(): game.getJugadorDos();

            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("ganador.fxml"));
                Parent root = loader.load();
                GanadorController winnerController = loader.getController();

                winnerController.initData(winner);

                Stage stage = new Stage();

                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Congratulations!");

                stage.setScene(new Scene(root));
                stage.show();
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            }
            this.returnHome();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("empate.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();

                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Game Over");

                stage.setScene(new Scene(root));
                stage.show();
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            }
            this.returnHome();
        }
    }

    public void setPlayerTurn(Jugador p) {
        this.turno = p;
    }

    public void clearBestPlay(){
        bestPlay.setText("");
    }
} 
    

