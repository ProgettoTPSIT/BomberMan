/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author alex
 */
public class BomberManServer {

    static Campo campo;
	static boolean partitaFinita;
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Server s=new Server(6789);
        //aspetto che si connettano tutti i player
		int nPlayer = 2;
		s.attendi(nPlayer);
		
		campo.setGriglia(costruisciCampo());
		campo.player = posizionaPlayer(nPlayer);
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
	
	
	static private Player[] posizionaPlayer(int nPlayer) {
		int[][] angoli = {{0,0},{0,12},{12,12},{12,0}};
		Player[] playerCreati = new Player[nPlayer];
		for(int n=0;n<nPlayer;n++) {
			int x = angoli[n][0];
			int y = angoli[n][1];
			//assegniamo un colore ad ogni player
			/*Color color = Color.RED;
			switch(n) {
				case 1:
					color = Color.BLUE;
					break;
				case 2:
					color = Color.GREEN;
					break;
				case 3:
					color = Color.YELLOW;
			}*/
			Player p = new Player(x, y);
			playerCreati[n] = p;
			//rimuoviaom i blocchi distruttibili intorno ai player
			for(int i=-1;i<2;i++) {
				for(int j=-1;j<2;j++) {
					campo.distruggiBlocco(x+i, y+j);
				}
			}
		}
		return playerCreati;
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
					b = new Blocco(i, j, true);
				} else { //se non mi trovo sull'intersezione
					if(r.nextDouble() < 0.66) { //nel 66% dei casi aggiungo un blocco distruttibile
						b = new Blocco(i, j, false); 
					}
				}
				
				campo[i][j] = b;
			}
		}
		return campo;
	}
	
}
