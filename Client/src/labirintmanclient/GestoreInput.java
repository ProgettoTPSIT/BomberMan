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
import java.util.List;
import javafx.scene.input.KeyCode;

/**
 *
 * @author alex
 */
//classe di gestione dei tasti premuti
public class GestoreInput {

    public static int gestisciMovimentiPlayer(){
        //crezione lista degli input da tastiera
        List keyboardInputs = GestoreEventi.getInputList();
	int comando = 0;
        //movimenti
        //assegnazione del comando da inviare al server in base al tasto premuto sulla tastiera
        //WASD - tasti di movimento
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