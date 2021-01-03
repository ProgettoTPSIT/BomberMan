/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author alex
 */

public class Bomb extends Elemento {
    int range = 2;

	/*public Bomb(int x, int y, int range) {
		setTranslateX(x);
		setTranslateY(y);
		this.range = range;
	}*/

    public int getRange() {
        return range;
    }
	
	@Override
	public void draw() {
		Rectangle border = new Rectangle(Constants.blockDimension, Constants.blockDimension);
		border.setStroke(Color.ORANGE);
		setAlignment(Pos.CENTER);
		border.setFill(Color.MOCCASIN);
		getChildren().addAll(border);
	}

}