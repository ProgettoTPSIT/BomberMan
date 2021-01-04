/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import bomberman.Campo;

/**
 *
 * @author alex
 */

public class AggiornaClient implements Runnable {
	static Pane rootView;
	static Campo campo;
	
	public AggiornaClient(Pane root) {
		rootView = root;
	}
	
	@Override
	public void run() {
		
		/*while(BomberManClient.vivo) {
			rootView.getChildren().removeAll();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(BomberManClient.class.getName()).log(Level.SEVERE, null, ex);
			}
			if(campo != null) {
				rootView.getChildren().add(campo.draw());
				rootView.getChildren().add(campo.drawPlayers());
			}
		}
		System.out.println("Hai perso!");*/
	}
	

}
