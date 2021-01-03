/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author Alexander.Perathoner
 */
public class Player extends Elemento {
    static int timeToSleep = 500;
    int speed;
    int nBombsLimit;
    int range;
    

    private int getTimeToSleep() {
        return timeToSleep-(speed*50);
    }
    
    public Player() {
        this.speed = 0;
        this.nBombsLimit = 1;
    }
    
    public void gotUpgrade(Ability ability) {
        
    }
    
    
    public void moveUp() {
		setTranslateY(getTranslateY()+50);
    }
    public void moveDown() {
		setTranslateY(getTranslateY()-50);
    }
    public void moveRight() {
		setTranslateX(getTranslateX()+50);
    }
    public void moveLeft() {
		setTranslateX(getTranslateX()-50);
    }

    private void setPosition(int x, int y) {
		setTranslateX(x);
		setTranslateY(y);
    }
    
    
    
    
}
