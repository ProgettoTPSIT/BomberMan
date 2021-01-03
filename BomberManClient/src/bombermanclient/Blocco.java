/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

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

    public Blocco(int dimension, double x, double y, boolean distruttibile) {
		
		Rectangle border = new Rectangle(dimension, dimension);
		
		border.setStroke(Color.BLACK);
		if(distruttibile) {
			border.setFill(Color.BLACK);
		} else {
			border.setFill(null);
		}
		getChildren().addAll(border);
		
		setAlignment(Pos.CENTER);
		setTranslateX(x);
		setTranslateY(y);
        this.distruttibile = distruttibile;
        if(distruttibile) {
            ability = getRandomAbility();
        } else {
            ability = Ability.NONE;
        }
    }
    
    private Ability getRandomAbility() {
        Ability[] allAbilities = {Ability.NBOMBS, Ability.RANGE, Ability.SPEED, Ability.NONE, Ability.NONE, Ability.NONE}; //6 blocchi ogni 16 hanno un'abilità
        Random rand = new Random();
        return allAbilities[rand.nextInt(allAbilities.length)]; //generiamo un numero casuale tra 0 e 2 compresi e prendiamo l'abilità corrispondente
    }
    
    
    
    
    
    
}
