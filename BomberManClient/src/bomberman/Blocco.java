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
    Ability ability;//ogni blocco deve contenere un abilità e un boolean che ci dice se è distruttibile o meno
    

    //costruttore del blocco dove diciamo al programma se è distruttibile o meno
    public Blocco(boolean distruttibile) {
        this.distruttibile = distruttibile;
        if(distruttibile) {
            //se è distruttibile gli diamo un abilità random, altrimenti gli diamo l'abilità NONE, ovvero nessuna abilità
            ability = getRandomAbility();
        } else {
            ability = Ability.NONE;
        }
    }
    
    //metodo che da ad ogni blocco distruttibile un abilità random
    private Ability getRandomAbility() {
        //array di tipo ability con tutte le abilità
        Ability[] allAbilities = {Ability.NBOMBS, Ability.RANGE, Ability.SPEED, Ability.NONE, Ability.NONE, Ability.NONE}; //6 blocchi ogni 16 hanno un'abilità
        Random rand = new Random();
        //metodo che restituisce un abilità random che si va a scegliere nell'array
        return allAbilities[rand.nextInt(allAbilities.length)]; //generiamo un numero casuale tra 0 e 2 compresi e prendiamo l'abilità corrispondente
    }
	
	public boolean getDistruttibile() {
		return distruttibile;
	}
    
	public Ability getAbility() {
		return ability;
	}
}
