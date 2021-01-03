/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
class Campo {
	Elemento[][] griglia;
	Player[] player;
	
	public Campo(int x, int y) {
		griglia = new Blocco[x][y];
	}

	void setGriglia(Blocco[][] campo) {
		griglia = campo;
	}	
	
	Ability distruggiBlocco(int x, int y) {
		if(x<0 || x>griglia[0].length) {
			return null;
		}
		if(y<0 || y>griglia[0].length) {
			return null;
		}
		Elemento elem = griglia[x][y];
		Ability a = null;
		if(elem.getClass() == Blocco.class) {
			if(((Blocco) elem).distruttibile) {
				griglia[x][y] = null;
				a = ((Blocco) elem).ability;
			}
		}
		return a;
	}
	
	void piazzaBomba(int id) {
		Player p = player[id];
		Bomb bomb = new Bomb(p.x, p.y, p.range);
		griglia[p.x][p.y] = bomb;
		//la bomba esplode dopo 4s
		new java.util.Timer().schedule(
			new java.util.TimerTask() {
				@Override
				public void run() {
					//scorriamo le abbilità ottenute dall'esplossione dei blocchi intorno alla bomba
					for (Ability abilitaOttenuta : esplosioneBomba(bomb)) {
						p.upgrade(abilitaOttenuta);
					}
				}
			},
			4000
		);
	}
	
	ArrayList<Ability> esplosioneBomba(Bomb bomba) {
		ArrayList<Ability> abilitaOttenute = null;
		int raggio = bomba.range/2;
		for(int i=bomba.x-raggio; i<bomba.x+raggio; i++) {
			for(int j=bomba.y-raggio; j<bomba.y+raggio; i++) {
				//distruggiamo i blocchi intorno alla bomba
				// otteniamo le abilita contenute nei blocchi
				abilitaOttenute.add(distruggiBlocco(i, j)); 
				
				//verifichiamo se è stato ucciso un player
				for(int id=0; id<player.length; i++) {
					Player p = player[id];
					if(p.x == i && p.y == j) {
						muore(id);
					}
				}
			}
		}
		return abilitaOttenute;
	}
	
	void muore(int id) {
		player[id] = null;
	}
	
	void movePlayerUp(int id) {
		Player p = player[id]; //otteniamo il player interessato
		if(p.y>=griglia[0].length) { //verifichiamo se si trova già sul bordo
			return;
		}
		if(griglia[p.x][p.y+1] == null) { //verifichiamo se dove vuole andare si trova un blocco
			player[id].moveUp(); //altrimenti lo lasciamo muovere
		}
	}
	
	void movePlayerDown(int id) {
		Player p = player[id];
		if(p.y==0) {
			return;
		}
		if(griglia[p.x][p.y-1] == null) {
			player[id].moveUp();
		}
	}
	
	void movePlayerRight(int id) {
		Player p = player[id];
		if(p.x>=griglia[0].length) {
			return;
		}
		if(griglia[p.x+1][p.y] == null) {
			player[id].moveUp();
		}
	}
	
	void movePlayerLeft(int id) {
		Player p = player[id];
		if(p.x==0) {
			return;
		}
		if(griglia[p.x-1][p.y] == null) {
			player[id].moveUp();
		}
	}
}
