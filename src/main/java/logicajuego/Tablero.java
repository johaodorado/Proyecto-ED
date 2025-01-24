/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicajuego;

/**
 *
 * @author ariel
 */
public class Tablero {
    private Simbolo[][] tablero;

    public Tablero() {
        tablero=new Simbolo[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tablero[i][j]=Simbolo.vacio;
            }
        }
    }

    public Simbolo[][] getTablero() {
        return tablero;
    }

    public void setTablero(int i,int j, Simbolo simbolo) {
        tablero[i][j] = simbolo;
    }
    
    
    
    public Tablero copiarTablero(){
        Tablero tbl=new Tablero();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tbl.getTablero()[i][j]=tablero[i][j];
            }
        }
        return tbl;
    }
    
    public static boolean isFull(Tablero tbl){
        int size=0;
        int num=0;
        Simbolo[][] tablero=tbl.getTablero();
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero.length;j++){
                size++;
                if(!tablero[i][j].equals(Simbolo.vacio)){
                    num++;
                }
            }
        }
        return num==size;
    }
    
    public int size(){
        int size=0;
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero.length;j++){
                if(!tablero[i][j].equals(Simbolo.vacio)){
                    size++;
                }
            }
        }
        return size;
    }
    
    @Override
    public String toString(){
        String texto="";
        for(int i=0;i<3;i++){
            texto+="\n";
            for (int j=0;j<3;j++){
                texto+=" "+tablero[i][j].toString();
            }
        }
        return texto;
    }
    
}
