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
        switch ability {
            case Ability.RANGE
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
