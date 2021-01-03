/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author alex
 */

/**
 *
 * @author Alexander.Perathoner
 */
public class Player extends StackPane {
    static int timeToSleep = 500;
    int speed;
    int nBombsLimit;
    int range;
	int id;
	int x, y;

    private int getTimeToSleep() {
        return timeToSleep-(speed*50);
    }
    
    public Player(int x, int y) {
		setTranslateX(x);
		setTranslateY(y);
        this.speed = 0;
        this.nBombsLimit = 1;
    }
	
	public void draw() {
		Rectangle border = new Rectangle(Constants.blockDimension, Constants.blockDimension);
		border.setStroke(Color.BLACK);
		setAlignment(Pos.CENTER);
		
		
		setTranslateX(x);
		setTranslateY(y);
		
		Color color = Color.RED;
		switch(id) {
			case 1:
				color = Color.BLUE;
				break;
			case 2:
				color = Color.GREEN;
				break;
			case 3:
				color = Color.YELLOW;
		}
		border.setFill(color);
		getChildren().addAll(border);
	}
    
}
