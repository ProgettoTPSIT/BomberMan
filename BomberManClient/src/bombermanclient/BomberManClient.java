/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import bomberman.Campo;
import bombermanclient.UI.Drawer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author alex
 */

public class BomberManClient extends Application {
	
	public static boolean vittoria = false;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Bomberman!");
		Sandbox.setupScene();
        Scene s = Sandbox.getScene();
        primaryStage.setScene(s);
		primaryStage.show();
		
        Client c=new Client("127.0.0.1", 6789);
		Thread t = new Thread(c);
		t.start();
	}
	

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
