/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

/**
 *
 * @author alex
 */
public class GestoreInput {

    public static int gestisciMovimentiPlayer(){
        List keyboardInputs = GestoreEventi.getInputList();
		int comando = 0;
        //movimenti
        if(keyboardInputs.contains(KeyCode.UP) || keyboardInputs.contains(KeyCode.W)){
			comando = 1;
        }
        if(keyboardInputs.contains(KeyCode.DOWN) || keyboardInputs.contains(KeyCode.S)){
			comando = 2;
        }
        if(keyboardInputs.contains(KeyCode.LEFT) || keyboardInputs.contains(KeyCode.A)){
			comando = 4;
        }
        if(keyboardInputs.contains(KeyCode.RIGHT) || keyboardInputs.contains(KeyCode.D)){
			comando = 3;
        }
		
		keyboardInputs.clear();
		return comando;
    }

}
