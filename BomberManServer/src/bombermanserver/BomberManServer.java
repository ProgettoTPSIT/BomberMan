/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.util.Random;
import javafx.scene.paint.Color;

public class BomberManServer {

    static Campo campo;
	static boolean partitaFinita = false;
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Server s=new Server(6789);
        //aspetto che si connettano tutti i player
		int nPlayer = 2;
		s.attendi(nPlayer);
		System.out.println(nPlayer + " si sono connessi! Inizia la partita!");
		campo = new Campo(13, 13, nPlayer);
		campo.setGriglia(costruisciCampo());
		System.out.println("Costruito il campo");
		campo.posizionaPlayer();
		System.out.println("Posizionati i player");
	}
	
	static void aggiornaPlayer(int id, String azione) {
		switch(azione) {
			case "1":
				campo.movePlayerUp(id);
				break;
			case "2":
				campo.movePlayerDown(id);
				break;
			case "3":
				campo.movePlayerRight(id);
				break;
			case "4":
				campo.movePlayerLeft(id);
				break;
			case "5":
				campo.piazzaBomba(id);
				break;
			default:
				System.out.println("OPS");
		}
	}
	
	static private Blocco[][] costruisciCampo() {
		int rows = 13;
		int columns = 13;
		Blocco[][] campo = new Blocco[rows][columns];
		
		Random r = new Random();
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Blocco b = null;
				//tutti i blocchi che si trovano su una colonna e riga dispari devono essere indistruttibili
				if ((i%2==1) && (j%2==1)) {
					b = new Blocco(true);
				} else { //se non mi trovo sull'intersezione
					if(r.nextDouble() < 0.66) { //nel 66% dei casi aggiungo un blocco distruttibile
						b = new Blocco(false); 
					}
				}
				
				campo[i][j] = b;
			}
		}
		return campo;
	}
	
}
