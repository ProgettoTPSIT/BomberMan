/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Campo implements Serializable { //Serializable necessario per inviare l'oggetto con writeObject ai client
	Elemento[][] griglia;
	Player[] player;
	
	public Campo(Campo c) {
		player = c.getPlayers();
		commonInit(c.griglia.length, c.griglia[0].length);
	}
	
	static int x = 5;
	public Campo(int rows, int columns, Player[] players) {
		player = players;
		commonInit(rows, columns);
	}
	
	public Campo(int rows, int columns, int nPlayer) {
		player = new Player[nPlayer];
		commonInit(rows, columns);
		posizionaPlayer();
	}
	
	private void commonInit(int rows, int columns) {
		griglia = new Elemento[rows][columns];
		Random r = new Random();
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Elemento b = new Pavimento();
				//tutti i blocchi che si trovano su una colonna e riga dispari devono essere indistruttibili
				if ((i%2==1) && (j%2==1)) {
					b = new Blocco(false);
					//System.out.println("Blocco: " + i + " " + j);
				} else { //se non mi trovo sull'intersezione
					if(r.nextDouble() < 0.22) { //nel 22% dei casi aggiungo un blocco distruttibile
						b = new Blocco(true); 
					}
				}
				griglia[i][j] = b;
			}
		}
		for(Player p : player) {
			if(p != null) {
				distruggiBlocco(p.getX(), p.getY());
			}
		}
	}
	
	
	public void setGriglia(Elemento[][] campo) {
		griglia = campo;
	}
	
	public Elemento[][] getGriglia() {
		return griglia;
	}
	
	public Player[] getPlayers() {
		return player;
	}
	
	void distruggiBlocco(int x, int y) {
		if(x<0 || x>=griglia.length) {
			return;
		}
		if(y<0 || y>=griglia[0].length) {
			return;
		}
		Elemento elem = griglia[x][y];
		if(elem.getClass() == Blocco.class) {
			if(((Blocco) elem).distruttibile) {
				griglia[x][y] = new Pavimento();
			}
		}
	}
	
	public void aggiornaPlayer(int id, int azione) {
		switch(azione) {
			case 1:
				movePlayerUp(id);
				break;
			case 2:
				movePlayerDown(id);
				break;
			case 3:
				movePlayerRight(id);
				break;
			case 4:
				movePlayerLeft(id);
				break;
			default:
				
		}
	}
	
	private void movePlayerUp(int id) {
		Player p = player[id]; //otteniamo il player interessato
		if(p.getY()==0) {
			return;
		}
		if(griglia[p.getX()][p.getY()-1].getClass() == Pavimento.class) { //verifichiamo se dove vuole andare si trova un blocco
			p.moveUp(); //altrimenti lo lasciamo muovere
		}
		System.out.println("Posizione player "  + p.getX() + " " + (p.getY()));
	}
		
	private void movePlayerDown(int id) {
		Player p = player[id];
		if(p.getY()==griglia[0].length-1) { //verifichiamo se si trova già sul bordo
			System.out.println("Il giocatore è già sul bordo!");
			return;
		}
		if(griglia[p.getX()][p.getY()+1].getClass() == Pavimento.class) {
			player[id].moveDown();
			System.out.println("Il player si è mosso verso il basso!");
		} else {
			System.out.println("Il blocco è già occupato");
		}
		System.out.println("Posizione player "  + p.getX() + " " + (p.getY()));
	}
	
	private void movePlayerRight(int id) {
		Player p = player[id];
		if(p.getX()==griglia.length-1) {
			return;
		}
		if(griglia[p.getX()+1][p.getY()].getClass() == Pavimento.class) {
			p.moveRight();
		}
		System.out.println("Posizione player "  + p.getX() + " " + (p.getY()));
	}
	
	private void movePlayerLeft(int id) {
		Player p = player[id];
		if(p.getX()==0) {
			return;
		}
		if(griglia[p.getX()-1][p.getY()].getClass() == Pavimento.class) {
			p.moveLeft();
		}
		System.out.println("Posizione player "  + p.getX() + " " + (p.getY()));
	}
	
	public void posizionaPlayer() {
		int[][] angoli = {{0,0},{0,griglia[0].length-1},{griglia.length-1,griglia[0].length-1},{griglia.length-1,0}};
		for(int n=0;n<player.length;n++) {
			//rimuoviaom i blocchi distruttibili intorno ai player
			int x = angoli[n][0];
			int y = angoli[n][1];
			for(int i=-1;i<2;i++) {
				for(int j=-1;j<2;j++) {
					distruggiBlocco(x+i, y+j);
				}
			}
			player[n] = new Player(x, y, n);
		}
	}
	
	public void setPlayers(Player[] player) {
		this.player = player;
	}
}	
