/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import bomberman.Campo;
import bomberman.Player;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author alex
 */
public class Sandbox {
	static Campo campo;
    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;
    private static boolean sceneStarted = false;
	
    private static void init() {
		System.out.println("Init Sandbox....");
        root = new Group();
        s = new Scene(root, Constants.width, Constants.height);
        c = new Canvas(Constants.width, Constants.height);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.setFill(Color.BLUE);
		GameLoop.start(gc);
		
        GestoreEventi.attachEventHandlers(s);
	}
	
	public static Player getPlayer(int id) throws NullPointerException {
		if(!playerIsAlive(id)) {
			throw new NullPointerException("Il player con l'id inserito non è più vivo");
		}
		return campo.getPlayers()[id];
	}
	
	public static boolean playerIsAlive(int id) {
		return campo.getPlayers()[id] != null;
	}
	
	public static Player[] getPlayers() {
		return campo.getPlayers();
	}
	
	
	public static void setupScene() {
        if(!sceneStarted){
            init();
            sceneStarted=true;
        }
	}
	
    public static Scene getScene() {
        return s;
    }

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    public static Canvas getCanvas() {
        return c;
    }
}
