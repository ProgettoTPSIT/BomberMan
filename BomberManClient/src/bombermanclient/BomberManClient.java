/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author alex
 */

public class BomberManClient extends Application {
	
	static Campo campo;
    private Pane root = new Pane();
	static boolean vivo = true;
	
	@Override
	public void start(Stage primaryStage) {
		
        Client c=new Client("127.0.0.1", 6789);
		
		primaryStage.setTitle("Bomberman!");
		primaryStage.setScene(new Scene(root, Constants.width, Constants.height));
		primaryStage.show();
		
		
		Thread t = new Thread(c);
		t.start();
		
		
		while(BomberManClient.vivo) {
			root.getChildren().removeAll();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(BomberManClient.class.getName()).log(Level.SEVERE, null, ex);
			}
			drawCampo();
			drawPlayers();
		}
		System.out.println("Hai perso!");
	}
	
	public void drawCampo() {
		if (campo == null) { return; }
			System.out.println("Trovato campo!");
		int rows = campo.griglia.length;
		int columns = campo.griglia[0].length;
		for (int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Elemento item = campo.griglia[i][j];
				item.draw();
				item.setTranslateX(i*Constants.blockDimension);
				item.setTranslateY(j*Constants.blockDimension);
				root.getChildren().add(campo.griglia[i][j]);
			}
		}
	}
	
	public void drawPlayers() {
		if (campo == null) { return; }
		for(Player p : campo.player) {
			p.draw();
			root.getChildren().add(p);
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}