/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logicajuego;

import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        //Gaz = new Game();
        Computadora pc = new Computadora(Simbolo.X);
        Scanner s = new Scanner(System.in);
        
        Tablero tablero = new Tablero();
        int x, y;
        String c = "";
        while (!c.equals("No")){
            
            
            tablero = pc.tomarDecision(tablero, Simbolo.O);
            System.out.println(tablero);
            int[] rec = Juego.recomendacion(tablero, Simbolo.O);
            System.out.println("recomendacion"+rec[0]+" "+rec[1]);
            y = Integer.parseInt(s.nextLine());
            x = Integer.parseInt(s.nextLine());
            tablero.getTablero()[y][x] = Simbolo.O;
            System.out.println(tablero);
            //c = s.nextLine();
            
        }
        //if(Game.win(matrix, Symbol.X)) System.out.println("Gano X");
        //else System.out.println("Gano O");
        
        //game.next(m);
    }
}
