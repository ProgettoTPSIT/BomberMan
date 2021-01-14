/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirintmanserver;

/**
 *
 * @author Rizzi
 */
import labirintman.Campo;
import labirintman.Elemento;
import labirintman.Player;
import java.lang.Math;

public class LabirintManServer {

    static Campo campo;
    static boolean partitaIniziata = false;
    static boolean partitaFinita = false;
	
    public static void main(String[] args) {
        Server s=new Server(6789);
        //aspetto che si connettano tutti i player
		int nPlayer = 4;
		campo = new Campo(11, 7, nPlayer);
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
        
        public static int playersWon() {
            int x,y;
            x=Math.round(campo.getGriglia().length/2-1);
            y=Math.round(campo.getGriglia()[0].length/2-1);
            Player[] p = campo.getPlayers();
		if(p.length < 2) {
                    return -1;
		}
                //se il player è alla posizione del tesoro, allora ha vinto
                for(int i=0;i<p.length;i++){
                    Player pi=p[i];
                    if(pi.getY()==y && pi.getX()==x){
                        return i;
                    }
                }
                return -1;

		
	}
	
	
}