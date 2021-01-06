/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient.UI;

import bomberman.*;
import bombermanclient.Constants;
import bombermanclient.GameLoop;
import bombermanclient.Sandbox;
import com.sun.media.jfxmedia.events.PlayerEvent;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author alex
 */
public class Drawer {
	static private GraphicsContext gc;
	
	static private void drawRectangle(Rectangle rect){
		gc.fillRect(rect.getX(),      
					rect.getY(), 
					rect.getWidth(), 
					rect.getHeight());
		gc.setFill(rect.getFill());
		gc.setStroke(rect.getStroke());
	}
	
	static private Paint getElementoFill(Elemento e) {
		Paint colore = Color.WHITE;
		if(e.getClass() == Blocco.class) {
			Blocco b = (Blocco) e;
			if(b.getDistruttibile()) {
				colore = Color.BLACK;
			} else {
				colore = Color.GREY;
			}
		} else if(e.getClass() == Bomb.class) {
			colore = Color.ORANGE;
		}
		return colore;
	}
	
	static private Paint getElementoStroke(Elemento e) {
		Paint colore = Color.WHITE;
		if(e.getClass() == Blocco.class) {
			colore = Color.BLACK;
		} else if(e.getClass() == Bomb.class) {
			colore = Color.RED;
		}
		return colore;
	}
	
	static private void drawElemento(int x, int y, Elemento e) {
		Rectangle border = new Rectangle(x, y, Constants.blockDimension, Constants.blockDimension);
		border.setFill(getElementoFill(e));
		border.setStroke(getElementoStroke(e));
		drawRectangle(border);
	}
	
	static public void drawCampo(Campo c) {
		gc = Sandbox.getGraphicsContext();		
		//disegno lo sfondo
		if(c != null) {
			drawGriglia(c.getGriglia());
			drawPlayers(c.getPlayers());
		} else {
			System.out.println("Il campo è null!");
		}
	}
	
	static private void drawGriglia(Elemento[][] griglia) {
		int rows = griglia.length;
		int columns = griglia[0].length;
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				//disegno un blocco nello spazio corrispondente
				if(j==12) {
					drawElemento(i*Constants.blockDimension, j*Constants.blockDimension, griglia[(j+1)%columns][(i+1)%rows]);
				} else {
					drawElemento(i*Constants.blockDimension, j*Constants.blockDimension, griglia[(j+1)%columns][i]);
				}
			}
		}
	}
	
	static private void drawPlayers(Player[] players) {
		Node[] nodes = new Node[players.length];
		for(int i=0; i<players.length; i++) {
			drawPlayer(players[i]);
		}
	}
	
	static private void drawPlayer(Player p) {
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
		drawRectangle(border);
	}
	
	
}
