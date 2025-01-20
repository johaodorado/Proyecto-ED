/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

import com.mycompany.proyecto.ed.TertiaryController;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
    @Override
    public void play(Jugador opponentPlayer, TertiaryController iJuego) {
        boolean canPlay = super.canPlay(iJuego.getTablero());
        if (!canPlay) {
            iJuego.checkWinners();
            return;
        }

        GridPane board = iJuego.getBoard();
        iJuego.setSymbolOnHover(this.simbolo);
        iJuego.setPlayerTurn(this);
        iJuego.clearBestPlay();

        board.setOnMouseClicked(e -> {
            Node node = (Node) e.getTarget();
            Node parent = node.getParent();

            Integer rowIndex = GridPane.getRowIndex(parent);
            Integer colIndex = GridPane.getColumnIndex(parent);
            int row = rowIndex == null ? 0 : rowIndex;
            int column = colIndex == null ? 0 : colIndex;

            Tablero newMatrix = iJuego.getTablero().copiarTablero();
            newMatrix.setTablero(row, column, this.simbolo);
            iJuego.setMatrix(newMatrix);
            iJuego.rePaint();
            opponentPlayer.play(this, iJuego);
        });
    }
}

