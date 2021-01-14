/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirintmanclient.UI;

/**
 *
 * @author Rizzi
 */
import labirintman.*;
import labirintmanclient.LabirintManClient;
import labirintmanclient.Constants;
import labirintmanclient.Sandbox;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author alex
 */
public class Drawer {
	static private GraphicsContext gc;
	
	static private void drawRectangle(Rectangle rect){
		gc.setFill(rect.getFill());
		gc.setStroke(rect.getStroke());
		gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}
	
	static private Paint getElementoFill(Elemento e) {
		Paint colore = Color.WHITE;
		if(e.getClass() == Blocco.class) {
			Blocco b = (Blocco) e;
			if(b.getDistruttibile()) {
				colore = Color.GREY;
			} else {
				colore = Color.BLACK;
			}
		}
		return colore;
	}
	
	static private Paint getElementoStroke(Elemento e) {
		Paint colore;
		if(e.getClass() == Blocco.class) {
			colore = Color.BLACK;
		} else {
			colore = Color.WHITE;
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
		if(c != null) {
			drawGriglia(c.getGriglia());
			drawPlayers(c.getPlayers());
		} else {
			if(LabirintManClient.finita) {
				if(LabirintManClient.vittoria){
					drawSchermata("Hai vinto!");
				} else {
					drawSchermata("Hai perso eheheheh");
				}	
			} else {
				drawSchermata("Aspettando che si connettano gli altri giocatori...");
			}
		}
	}
	
	static private void drawSchermata(String text) {
        GraphicsContext gc = Sandbox.getCanvas().getGraphicsContext2D();
		gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
            text, 
            Math.round(Constants.width  / 2), 
            Math.round(Constants.height / 2)
        );
	}
	
	static private void drawGriglia(Elemento[][] griglia) {
		int rows = griglia.length;
		int columns = griglia[0].length;
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				//disegno un blocco nello spazio corrispondente
				drawElemento(i*Constants.blockDimension, j*Constants.blockDimension, griglia[i][j]);
			}
		}
		//coloriamo il tesoro nel campo
		drawTesoro(rows, columns);
	}

	static private void drawTesoro(int rows, int columns){
		int x,y;
		x=Math.round(rows/2);
		y=Math.round(columns/2);
		Rectangle border = new Rectangle(x*Constants.blockDimension, y*Constants.blockDimension, Constants.blockDimension, Constants.blockDimension);
		border.setFill(Color.ORANGE);
		border.setStroke(Color.ORANGE);
		drawRectangle(border);
	}
	
	static private void drawPlayers(Player[] players) {
		for(int i=0; i<players.length; i++) {
			drawPlayer(players[i]);
		}
	}
	
	static private void drawPlayer(Player p) {
		Color color;
		int id = p.getId();
		switch (id) {
			case 0:
				color = Color.RED;
				break;
			case 1:
				color = Color.BLUE;
				break;
			case 2:
				color = Color.GREEN;
				break;
			default:
				color = Color.YELLOW;
				break;
		}
		gc.setFill(color);
		gc.fillRoundRect(p.getX()*Constants.blockDimension, p.getY()*Constants.blockDimension, Constants.blockDimension, Constants.blockDimension, 15, 15);
	}
	
	
}

