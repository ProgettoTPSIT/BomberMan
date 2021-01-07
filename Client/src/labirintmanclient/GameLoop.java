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
import labirintmanclient.UI.Drawer;
import java.util.Iterator;
import java.util.Vector;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;


/**
 *
 * @author alex
 */
//classe che permette il continuo aggiornamento della scena di gioco
public class GameLoop {

    static double currentGameTime;
    static double oldGameTime;
    static double deltaTime;
    final static long startNanoTime = System.nanoTime();

    public static double getCurrentGameTime() {
        return currentGameTime;
    }

	
    public static void start(GraphicsContext gc) {
	System.out.println("Started Gameloop");
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                oldGameTime = currentGameTime;
                currentGameTime = (currentNanoTime - startNanoTime) / 1000000000.0;
                deltaTime = currentGameTime - oldGameTime;
                gc.clearRect(0, 0, Constants.width, Constants.height);
                renderGame();
            }
        }.start();
    }
    //calcolo del tempo utilizzato
    public static double getDeltaTime() {
    	return deltaTime * 100;
    }


    public static void renderGame() {
		Drawer.drawCampo(Sandbox.campo);
    }

}
