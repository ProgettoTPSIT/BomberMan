/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirintmanclient;

/**
 *
 * @author Rizzi
 */
import labirintman.Campo;
import labirintman.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;

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
        //creazione del campo da gioco di dimensioni costanti
        s = new Scene(root, Constants.width, Constants.height);
        c = new Canvas(Constants.width, Constants.height);
        root.getChildren().add(c);
        //creazione grafica del campo da gioco
        gc = c.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.setFill(Color.BLUE);
		GameLoop.start(gc);
		
        GestoreEventi.attachEventHandlers(s);
	}
	
	public static Player getPlayer(int id) {
		return campo.getPlayers()[id];
	}
	
	public static int playersWon() {
            int x,y;
            x=Math.round(campo.getGriglia().length/2-1);
            y=Math.round(campo.getGriglia()[0].length/2-1);
            Player[] p = campo.getPlayers();
		if(p.length < 2) {
                    return -1;
		}
                //se il player è alla posizione del tesoro, allora ha vinto
                for(int i=0;i<p.length;i++){
                    Player pi=p[i];
                    if(pi.getY()==y && pi.getX()==x){
                        return i;
                    }
                }
                return -1;	
	}
	
        //restituisce i player in un array
	public static Player[] getPlayers() {
            return campo.getPlayers();
	}
	
	//setta la finstra di gioco
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
