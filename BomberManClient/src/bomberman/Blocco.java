/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.Serializable;
import java.util.Random;

public class Blocco extends Elemento implements Serializable {
    boolean distruttibile;

    //costruttore del blocco dove diciamo al programma se è distruttibile o meno
    public Blocco(boolean distruttibile) {
        this.distruttibile = distruttibile;
    }
	
	public boolean getDistruttibile() {
		return distruttibile;
	}
}
