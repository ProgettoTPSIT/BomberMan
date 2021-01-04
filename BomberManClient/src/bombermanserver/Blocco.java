/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Blocco extends Elemento {
    
    boolean distruttibile;
    
    bombermanclient.Ability ability;
/*
    public Blocco(, double x, double y, boolean distruttibile) {
		
		setTranslateX(x);
		setTranslateY(y);
        this.distruttibile = distruttibile;
    }*/
	
	@Override
	public void draw() {
		Rectangle border = new Rectangle(Constants.blockDimension, Constants.blockDimension);
		border.setStroke(Color.BLACK);
		setAlignment(Pos.CENTER);
		if(distruttibile) {
			border.setFill(Color.BLACK);
		} else {
			border.setFill(Color.GREY);
		}
		getChildren().addAll(border);
	}
	
    
}
