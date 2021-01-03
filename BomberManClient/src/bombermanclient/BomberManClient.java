/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
public class BomberManClient extends Application {
	
	
	public void start(Stage primaryStage) {
		
        //Server s=new Server(6789);
        //aspetto che si connettano tutti i player
		//s.attendi();
		
		//inizia la partita
		
		Scene scena = constructScene();
		
		primaryStage.setTitle("Bomberman!");
		primaryStage.setScene(scena);
		primaryStage.show();
	}
	
	public Scene constructScene() {
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
		return new Scene(root, width, height);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}