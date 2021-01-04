/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author alex
 */

public class BomberManClient extends Application {
	
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
		
		AggiornaClient ac = new AggiornaClient(root);
		Thread t1 = new Thread(ac);
		t1.start();
	}
	

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
