/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Campo extends StackPane implements Serializable { //Serializable necessario per inviare l'oggetto con writeObject ai client
	public Elemento[][] griglia;
	public Player[] player;
	
	public Pane draw() {
		Pane root = new Pane();
		int rows = griglia.length;
		int columns = griglia[0].length;
		for (int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Elemento item = griglia[i][j];
				item.draw();
				item.setTranslateX(i*Constants.blockDimension);
				item.setTranslateY(j*Constants.blockDimension);
				root.getChildren().add(griglia[i][j]);
			}
		}
		return root;
	}
	
	public Pane drawPlayers() {
		Pane root = new Pane();
		for(Player p : player) {
			p.draw();
			root.getChildren().add(p);
		}
		return root;
	}
}
