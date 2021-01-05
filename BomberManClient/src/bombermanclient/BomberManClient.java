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
	
	static Campo campo;
    private Pane root = new Pane();
	static boolean vivo = true;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Bomberman!");
		primaryStage.setScene(new Scene(root, Constants.width, Constants.height));
		primaryStage.show();
		
        Client c=new Client("127.0.0.1", 6789);
		Thread t = new Thread(c);
		t.start();
		
		
		while(BomberManClient.vivo) {
			root.getChildren().removeAll();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(BomberManClient.class.getName()).log(Level.SEVERE, null, ex);
			}
			if(campo != null) {
				root.getChildren().add(Drawer.drawCampo(campo));
			}
		}
	}
	

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
