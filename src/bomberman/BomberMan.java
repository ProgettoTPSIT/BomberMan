/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author alex
 */
public class BomberMan extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		/*Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});*/
		
		StackPane root = new StackPane();
		int width = 600;
		int height = 600;
		int blockDimension = 50;
		int rows = width/blockDimension;
		int columns = height/blockDimension;
		
		for (int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				//TODO: stabilire se un blocco è distruttibile o meno, per ora sono tutti distrubbili
				Blocco b = new Blocco(i*blockDimension, j*blockDimension, true);
				root.getChildren().add(b);
			}
		}
		
		Scene scene = new Scene(root, 600, 600);
		
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
