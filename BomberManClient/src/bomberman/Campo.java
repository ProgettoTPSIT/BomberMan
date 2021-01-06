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
	
        //creazione campo da gioco
	public Campo(int rows, int columns, int nPlayer) {
                //creazione del campo di x colonne e x righe
		griglia = new Elemento[rows][columns];
                //istanziozione dei player (4)
		player = new Player[nPlayer];
		Random r = new Random();
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Elemento b = new Pavimento();
				//tutti i blocchi che si trovano su una colonna e riga dispari devono essere indistruttibili
				if ((i%2==1) && (j%2==0)) {
					b = new Blocco(true);
				} else { //se non mi trovo sull'intersezione
					if(r.nextDouble() < 0.66) { //nel 66% dei casi aggiungo un blocco distruttibile
						b = new Blocco(false); 
					}
				}
                                //aggiunta alla grigrla del blocco
				griglia[i][j] = b;
			}
		}
		posizionaPlayer();
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
	
        //metodo distruggi blocco
	Ability distruggiBlocco(int x, int y) {
                //controllo che il blocco da distruggere non sia oltre o il limite della mappa
		if(x<0 || x>=griglia.length) {
			return null;
		}
		if(y<0 || y>=griglia[0].length) {
			return null;
		}
                //creo un elemento e lo istanzio con le corrdinate del blocco da distuggere
		Elemento elem = griglia[x][y];
		Ability a = null;
                //controllo che ci sia effettivamente elemento da distuggere
		if(elem != null) {
                        //controllo che ci sia un blocco
			if(elem.getClass() == Blocco.class) {
                                //controllo che il blocco sia effettivamente distruttibile
				if(((Blocco) elem).distruttibile) {
                                        //distruggo il blocco e restitusco la sua abilità (che il player si prenderà)
					griglia[x][y] = new Pavimento();
					a = ((Blocco) elem).ability;
				}
			}
		}
		return a;
	}
	
        //metodo che piazza la bomba in gioco
	public void piazzaBomba(int id) {
                //istanzio un player locale del metodo con l'id del player che sta attualmente giocando
		Player p = player[id];
                //istanzio la bomba con il suo range
		Bomb bomb = new Bomb(p.range);
		griglia[p.x][p.y] = bomb;
		//la bomba esplode dopo 4s
		new java.util.Timer().schedule(
			new java.util.TimerTask() {
				@Override
				public void run() {
					//scorriamo le abbilità ottenute dall'esplossione dei blocchi intorno alla bomba
					for (Ability abilitaOttenuta : esplosioneBomba(p.x, p.y, bomb.range/2)) {
						p.upgrade(abilitaOttenuta);
					}
				}
			},
			4000
		);
	}
	
        //metodo dell'esplosione della bomba
	ArrayList<Ability> esplosioneBomba(int x, int y, int raggio) {
		ArrayList<Ability> abilitaOttenute = null;
                //creo un for che mi identifica il range che l'esplosione avrà tramite le coordinate e il raggio
		for(int i=x-raggio; i<x+raggio; i++) {
			for(int j=y-raggio; j<y+raggio; i++) {
				//distruggiamo i blocchi intorno alla bomba
				// otteniamo le abilita contenute nei blocchi
				abilitaOttenute.add(distruggiBlocco(i, j)); 
				
				//verifichiamo se è stato ucciso un player
				for(int id=0; id<player.length; i++) {
					Player p = player[id];
                                        //controllo se il player fosse effettivament presente in quelle coordinate
					if(p.x == i && p.y == j) {
						System.out.println("Un player è stato colpito da una bomba!");
						muore(id);
					}
				}
			}
		}
		return abilitaOttenute;
	}
	
        //uccisione player
	void muore(int id) {
		player[id] = null;
	}
	
        //metodo per spostare il player in su
	public void movePlayerUp(int id) {
		Player p = player[id]; //otteniamo il player interessato
		if(p.y>=griglia[0].length) { //verifichiamo se si trova già sul bordo
			return;
		}
		if(griglia[p.x][p.y+1].getClass() == Pavimento.class) { //verifichiamo se dove vuole andare si trova un blocco
			player[id].moveUp(); //altrimenti lo lasciamo muovere
		}
	}
	
        //metodo per spostare il player in giu
	public void movePlayerDown(int id) {
		Player p = player[id];
		if(p.y==0) {
			return;
		}
		if(griglia[p.x][p.y-1].getClass() == Pavimento.class) {
			player[id].moveUp();
		}
	}
	
        //metodo per spostare il player a destra
	public void movePlayerRight(int id) {
		Player p = player[id];
		if(p.x>=griglia.length) {
			return;
		}
		if(griglia[p.x+1][p.y].getClass() == Pavimento.class) {
			player[id].moveUp();
		}
	}
	
        //metodo per spostare il player a sinistra
	public void movePlayerLeft(int id) {
		Player p = player[id];
		if(p.x==0) {
			return;
		}
		if(griglia[p.x-1][p.y].getClass() == Pavimento.class) {
			player[id].moveUp();
		}
	}
	
        //metodo posizionamento player ad inizio game
	public void posizionaPlayer() {
                //istanziamo in un varray le coordinate iniziali dove i player si creeranno
		int[][] angoli = {{0,0},{0,12},{12,12},{12,0}};
		for(int n=0;n<player.length;n++) {
			//rimuoviamo i blocchi distruttibili intorno ai player
			int x = angoli[n][0];
			int y = angoli[n][1];
			for(int i=-1;i<2;i++) {
                                //distruzione blocchi attorno ai punti di creazione dei player
				for(int j=-1;j<2;j++) {
					distruggiBlocco(x+i, y+j);
				}
			}
                        //creazione player
			player[n] = new Player(x, y, n);
		}
	}
	
}	
