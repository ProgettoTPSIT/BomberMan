/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.Serializable;

/**
 *
 * @author alex
 */
public class Player implements Serializable {
    int timeToSleep = 500;
    int speed;
    int nBombsLimit;
    int range;
    int x, y;
    int id;
	
    //costruttore player
    public Player(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.speed = 0;
            this.nBombsLimit = 1;
            this.id = id;
    }
	
    //metodo che aumenta le statistiche del player in base all'abilità trovata nel blocco
    public void upgrade(Ability ability) {
            //switch che in base all'abilità trovata aumenta la statistca specifica
            switch(ability) {
                    case RANGE:
                            range++;
                            break;
                    case NBOMBS:
                            nBombsLimit++;
                            break;
                    case SPEED:
                            speed++;
                            break;
            }
    }

    public int getSpeed() {
            return speed;
    }

    public int getnBombsLimit() {
            return nBombsLimit;
    }

    public int getRange() {
            return range;
    }

    public int getX() {
            return x;
    }

    public int getY() {
            return y;
    }

    public int getId() {
            return id;
    }

    public void moveUp() {
            y++;
    }
    public void moveDown() {
            y--;
    }
    public void moveRight() {
            x++;
    }
    public void moveLeft() {
            x--;
    }

    private void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
    }

    private int getTimeToSleep() {
            return timeToSleep-(speed*50);
    }
}