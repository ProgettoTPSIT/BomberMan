/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author Alexander.Perathoner
 */
public class Blocco extends Elemento {
    
    boolean distruttibile;
    
    Ability ability;
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
