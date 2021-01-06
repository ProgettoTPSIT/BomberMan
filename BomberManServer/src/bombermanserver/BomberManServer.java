/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.util.Random;
import bomberman.Campo;
import bomberman.Elemento;
import javafx.scene.paint.Color;

public class BomberManServer {

    static Campo campo;
    static boolean partitaIniziata = false;
    static boolean partitaFinita = false;
	
    public static void main(String[] args) {
        Server s=new Server(6789);
        //aspetto che si connettano tutti i player
		int nPlayer = 2;
		campo = new Campo(43, 31, nPlayer);
		//printCampo(campo);
		//System.out.println("Costruito il campo");
		s.attendi(nPlayer);
		partitaIniziata = true;
		System.out.println(nPlayer + " player si sono connessi! Inizia la partita!");
	}
	
	public static void printCampo(Campo c) {
		Elemento[][] griglia = c.getGriglia();
		System.out.println("GRIGLIA");
		for(int i=0; i<griglia.length; i++) {
			for(int j=0;j<griglia[0].length; j++) {
				System.out.print("{" + griglia[i][j].getClass() + "}");
			}
			System.out.println("");
		}
		System.out.println("FINE GRIGLIA");
	}
	
	
}
