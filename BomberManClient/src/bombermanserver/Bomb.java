/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bomb extends Elemento {
    int range = 2;

	public Bomb(int range) {
		this.range = range;
	}

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
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
