/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 *
 * @author alex
 */
public class BomberMan extends Application {
	
    static Blocco[][] campo;
	static boolean partitaFinita;
	
	public void start(Stage primaryStage) {
		/*Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});*/
		
        //Server s=new Server(6789);
        //aspetto che si connettano tutti i player
		//s.attendi();
		
		//inizia la partita
		
		StackPane root = new StackPane();
		int width = 650;
		int height = 650;
		int blockDimension = 50;
		int rows = width/blockDimension;
		int columns = height/blockDimension;
		campo = new Blocco[rows][columns];
		
		
		Random r = new Random();
		for (int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				System.out.println(i + " " + j);
				boolean indistruttibile = ((i%2==1) && (j%2==1));
				Blocco b = new Blocco(blockDimension, i*blockDimension, j*blockDimension, indistruttibile);
				
				
				root.getChildren().add(b);
				campo[i][j] = b;
			}
		}
		
		Scene scene = new Scene(root, width, height);
		
		primaryStage.setTitle("Bomberman!");
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
