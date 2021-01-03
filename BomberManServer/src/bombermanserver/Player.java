/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import javafx.scene.paint.Color;

/**
 *
 * @author Alexander.Perathoner
 */
public class Player {
    static int timeToSleep = 500;
    int speed;
    int nBombsLimit;
    int range;
	int x, y;

    private int getTimeToSleep() {
        return timeToSleep-(speed*50);
    }
    
    public Player(int x, int y) {
		this.x = x;
		this.y = y;
        this.speed = 0;
        this.nBombsLimit = 1;
    }
    
    public void upgrade(Ability ability) {
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
    
    
    
    
}
