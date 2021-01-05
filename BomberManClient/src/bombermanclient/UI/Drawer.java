/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient.UI;

import bomberman.*;
import bombermanclient.Constants;
import com.sun.media.jfxmedia.events.PlayerEvent;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author alex
 */
public class Drawer {
	static private Node drawBlocco(Blocco b) {
		Rectangle border = new Rectangle(Constants.blockDimension, Constants.blockDimension);
		border.setStroke(Color.BLACK);
		if(b.getDistruttibile()) {
			border.setFill(Color.BLACK);
		} else {
			border.setFill(Color.GREY);
		}
		return (border);
	}
	
	static private Node drawBomb(Bomb b) {
		Rectangle border = new Rectangle(Constants.blockDimension, Constants.blockDimension);
		border.setStroke(Color.RED);
		border.setFill(Color.ORANGE);
		return (border);
	}
	
	static private Node drawPavimento(Pavimento p) {
		Rectangle border = new Rectangle(Constants.blockDimension, Constants.blockDimension);
		border.setStroke(null);
		border.setFill(Color.WHITE);
		return (border);
	}
	
	static private Node drawElemento(Elemento e) {
		Node node;
		if(e.getClass() == Blocco.class) {
			node = drawBlocco((Blocco) e);
		} else if(e.getClass() == Bomb.class) {
			node = drawBomb((Bomb) e);
		} else {
			node = drawPavimento((Pavimento) e);
		}
		return node;
	}
	
	static public StackPane drawCampo(Campo c) {
		StackPane sp = drawGriglia(c.getGriglia());
		for(Node n : drawPlayers(c.getPlayers())) {
			sp.getChildren().add(n);
		}
		
		return sp;
	}
	
	static private StackPane drawGriglia(Elemento[][] griglia) {
		int rows = griglia.length;
		int columns = griglia[0].length;
		Rectangle border = new Rectangle(Constants.blockDimension*rows, Constants.blockDimension*columns);
		border.setStroke(Color.BLACK);
		StackPane sp = new StackPane(border);
		ArrayList<Node> nodes = null;
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Node drawElemento = Drawer.drawElemento(griglia[i][j]);
				sp.getChildren().add(drawElemento);
			}
		}
		return sp;
	}
	
	static private Node[] drawPlayers(Player[] players) {
		Node[] nodes = new Node[players.length];
		for(int i=0; i<players.length; i++) {
			nodes[i] = drawPlayer(players[i]);
		}
		return nodes;
	}
	
	static private Node drawPlayer(Player p) {
		Rectangle border = new Rectangle(Constants.blockDimension, Constants.blockDimension);
		border.setStroke(Color.BLACK);
		border.setTranslateX(p.getX());
		border.setTranslateY(p.getY());
		
		Color color = Color.RED;
		switch(p.getId()) {
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
		return border;
	}
	
	
}
